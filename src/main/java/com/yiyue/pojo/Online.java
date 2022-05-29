package com.yiyue.pojo;

public class Online {
    private Integer userid;
    private String username;
    private Integer type;
    private String IP;
    private String login;
    private String logout;

    @Override
    public String toString() {
        return "Online{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", type=" + type +
                ", IP='" + IP + '\'' +
                ", login='" + login + '\'' +
                ", logout='" + logout + '\'' +
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }
}
