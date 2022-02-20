package com.zyhwjl.jdbc.test;

/**
 * @Description : 实体类
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2021/12/24 18:41
 */
public class examStudent {
    int FlowID;
    int Type;
    String IDCard;
    String ExamCard;
    String StudentName;
    String Location;
    int Grade;

    @Override
    public String toString() {
        return  "流水号    " + FlowID +
                "\n四级/六级    " + Type +
                "\n身份证号   " + IDCard +
                "\n准考证号   " + ExamCard +
                "\n学生姓名   " + StudentName +
                "\n区域 " + Location +
                "\n成绩 " + Grade ;
    }

    public int getFlowID() {
        return FlowID;
    }

    public void setFlowID(int flowID) {
        FlowID = flowID;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getExamCard() {
        return ExamCard;
    }

    public void setExamCard(String examCard) {
        ExamCard = examCard;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }

    public examStudent(int flowID, int type, String IDCard, String examCard, String studentName, String location, int grade) {
        FlowID = flowID;
        Type = type;
        this.IDCard = IDCard;
        ExamCard = examCard;
        StudentName = studentName;
        Location = location;
        Grade = grade;
    }

    public examStudent() {
    }
}
