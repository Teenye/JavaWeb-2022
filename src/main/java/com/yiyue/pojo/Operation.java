package com.yiyue.pojo;

public class Operation {
    private Integer userid;
    private String username;
    private String IP;
    private String date;
    private String operationname;
    private Integer toID;

    @Override
    public String toString() {
        return "Operation{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", IP='" + IP + '\'' +
                ", date='" + date + '\'' +
                ", operationname='" + operationname + '\'' +
                ", toID=" + toID +
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

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOperationname() {
        return operationname;
    }

    public void setOperationname(String operationname) {
        this.operationname = operationname;
    }

    public Integer getToID() {
        return toID;
    }

    public void setToID(Integer toID) {
        this.toID = toID;
    }
}
