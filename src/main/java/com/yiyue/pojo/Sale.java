package com.yiyue.pojo;

public class Sale {
    private Integer userid;
    private String username;
    private String brandname;
    private Double money;

    @Override
    public String toString() {
        return "Sale{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", brandname='" + brandname + '\'' +
                ", money=" + money +
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
