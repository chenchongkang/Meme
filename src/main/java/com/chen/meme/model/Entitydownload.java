package com.chen.meme.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Entitydownload {
    @Id
    @GeneratedValue
    private Integer downloadID;
    private Integer userID;
    private Integer memeID;
    private String upTime;

    public Integer getDownloadID() {
        return downloadID;
    }

    public void setDownloadID(Integer downloadID) {
        this.downloadID = downloadID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getMemeID() {
        return memeID;
    }

    public void setMemeID(Integer memeID) {
        this.memeID = memeID;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }
}
