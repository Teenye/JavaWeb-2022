package com.yiyue.pojo;

public class BrowseGood {
    private String username;
    private String goodname;
    private String date;
    private Integer duration;

    @Override
    public String toString() {
        return "BrowseGood{" +
                "username='" + username + '\'' +
                ", goodname='" + goodname + '\'' +
                ", date='" + date + '\'' +
                ", duration=" + duration +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
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
