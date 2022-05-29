package com.yiyue.pojo;

public class Seller {
    private Integer   userid;
    private String   username;
    private String   brandname;

    @Override
    public String toString() {
        return "Seller{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", brandname='" + brandname + '\'' +
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

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }
}
