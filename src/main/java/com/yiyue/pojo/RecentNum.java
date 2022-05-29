package com.yiyue.pojo;

public class RecentNum {
    private Integer sellnum;
    private String date;

    @Override
    public String toString() {
        return "RecentNum{" +
                "sellnum=" + sellnum +
                ", date='" + date + '\'' +
                '}';
    }

    public Integer getSellnum() {
        return sellnum;
    }

    public void setSellnum(Integer sellnum) {
        this.sellnum = sellnum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
