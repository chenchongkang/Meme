package com.chen.meme.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Entitypicture {
    @Id
    @GeneratedValue
    private Integer imgID;
    private Integer memeID;
    private String memeDescribe;
    private String upTime;
    private String path;

    public Integer getImgID() {
        return imgID;
    }

    public void setImgID(Integer imgID) {
        this.imgID = imgID;
    }

    public Integer getMemeID() {
        return memeID;
    }

    public void setMemeID(Integer memeID) {
        this.memeID = memeID;
    }

    public String getMemeDescribe() {
        return memeDescribe;
    }

    public void setMemeDescribe(String memeDescribe) {
        this.memeDescribe = memeDescribe;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
