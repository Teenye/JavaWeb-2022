package com.yiyue.pojo;

public class UserPic {
    private String username;
    private Integer buynum;
    private Double pay;
    private String offtenbuy;
    private String offtenbrowse;

    @Override
    public String toString() {
        return "UserPic{" +
                "username='" + username + '\'' +
                ", buynum=" + buynum +
                ", pay=" + pay +
                ", offtenbuy='" + offtenbuy + '\'' +
                ", offtenbrowse='" + offtenbrowse + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getBuynum() {
        return buynum;
    }

    public void setBuynum(Integer buynum) {
        this.buynum = buynum;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public String getOfftenbuy() {
        return offtenbuy;
    }

    public void setOfftenbuy(String offtenbuy) {
        this.offtenbuy = offtenbuy;
    }

    public String getOfftenbrowse() {
        return offtenbrowse;
    }

    public void setOfftenbrowse(String offtenbrowse) {
        this.offtenbrowse = offtenbrowse;
    }
}
