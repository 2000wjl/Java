package com.zyhwjl.customer.domain;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/23 11:41
 */
public class Contexts {
    Integer id;
    String title;
    LocalDateTime datetime;
    Integer cusID;

    public Contexts() {
    }

    public Contexts(String title, LocalDateTime datetime, Integer cusID) {
        this.title = title;
        this.datetime = datetime;
        this.cusID = cusID;
    }

    public Integer getCusID() {
        return cusID;
    }

    public void setCusID(Integer cusID) {
        this.cusID = cusID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }
}
