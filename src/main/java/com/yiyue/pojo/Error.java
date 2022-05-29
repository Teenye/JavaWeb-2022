package com.yiyue.pojo;

public class Error {
    private Integer userid;
    private String username;
    private Integer num;
    private String date;

    @Override
    public String toString() {
        return "Error{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", num=" + num +
                ", date='" + date + '\'' +
                '}';
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
