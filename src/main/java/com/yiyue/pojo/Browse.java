package com.yiyue.pojo;

public class Browse {
    private Integer userid;
    private Integer goodid;
    private String date;
    private Integer duration;

    @Override
    public String toString() {
        return "Browse{" +
                "userid=" + userid +
                ", goodid=" + goodid +
                ", date='" + date + '\'' +
                ", duration=" + duration +
                '}';
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
