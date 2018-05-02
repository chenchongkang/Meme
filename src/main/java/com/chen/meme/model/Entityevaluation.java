package com.chen.meme.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.sql.Time;

@Entity
public class Entityevaluation {
    @Id
    @GeneratedValue
    private Integer evaluationID;
    private Integer userID;
    private Integer memeID;
    private Double evaluations;
    private String evaluationTime;

    public Entityevaluation() {
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

    public Integer getEvaluationID() {
        return evaluationID;
    }

    public void setEvaluationID(Integer evaluationID) {
        this.evaluationID = evaluationID;
    }

    public Double getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(Double evaluations) {
        this.evaluations = evaluations;
    }

    public String getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(String evaluationTime) {
        this.evaluationTime = evaluationTime;
    }
}