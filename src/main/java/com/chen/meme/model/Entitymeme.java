package com.chen.meme.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class Entitymeme {
    @Id
    @GeneratedValue
    private Integer memeID;
    private String memeName;
    private Integer downloads;
    private String memeIntro;
    private Time upTime;
    private String classis;//classes 貌似会出错

    public String getClassis() {
        return classis;
    }

    public void setClassis(String classis) {
        this.classis = classis;
    }





    public String getMemeIntro() {
        return memeIntro;
    }

    public void setMemeIntro(String memeIntro) {
        this.memeIntro = memeIntro;
    }

    public Time getUpTime() {
        return upTime;
    }

    public void setUpTime(Time upTime) {
        this.upTime = upTime;
    }




    public Entitymeme() {
    }

    public Integer getMemeID() {
        return memeID;
    }

    public void setMemeID(Integer memeID) {
        this.memeID = memeID;
    }

    public String getMemeName() {
        return memeName;
    }

    public void setMemeName(String memeName) {
        this.memeName = memeName;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }
}
