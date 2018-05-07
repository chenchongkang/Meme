package com.chen.meme.model;

public class Oujilide {
    private Integer userID;
    private Double ojldjl;
    private int index;

    public Oujilide() {
    }

    public Oujilide(Integer userID, Double ojldjl) {
        this.userID = userID;
        this.ojldjl = ojldjl;
    }

    public Oujilide(Integer userID, Double ojldjl, int i) {
        this.userID = userID;
        this.ojldjl = ojldjl;
        this.index = i;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Double getOjldjl() {
        return ojldjl;
    }

    public void setOjldjl(Double ojldjl) {
        this.ojldjl = ojldjl;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
