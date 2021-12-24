package com.zyhwjl.jdbc.jdbc3_PreparedStatement;

import com.zyhwjl.jdbc.jdbc3_bean.Order;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

import static com.zyhwjl.jdbc.jdbc3_util.JDBCUtils.getConnection;

/**
 * @Description : 查询的问题:对于表的字段名与实体类的属性名不一致时，须使用别名对列名进行替换。
 *                         而getColumnName无法获取别名，不建议使用该方法。
 *                         应使用getColumnLabel获取别名或列名（未指定别名）
 *
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2021/12/24 15:44
 */
public class OrderCommonQuery {

    @Test
    public void OrderTest() throws Exception {
        String sql = "select order_name orderName from `order` where order_id = ?";
        System.out.println(OrderQuery(sql,1));
    }

    public Order OrderQuery(String sql,Object ...args) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            if(rs.next()){
                Order order = new Order();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    Object columnValue = rs.getObject(i + 1);
//                    获取数据集列名（非别名），不推荐使用
//                    String columnName = rsmd.getColumnName(i+1);
//                    获取数据集别名或列名（未指定别名），建议使用
                    String columnName = rsmd.getColumnLabel(i+1);

                    Field field = Order.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(order,columnValue);
                }
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn!=null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
