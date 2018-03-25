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
    private String  evaluations;
    private Time    evaluationTime;

    public Entityevaluation() {
    }

    public Integer getEvaluationID() {
        return evaluationID;
    }

    public void setEvaluationID(Integer evaluationID) {
        this.evaluationID = evaluationID;
    }

    public String getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(String evaluations) {
        this.evaluations = evaluations;
    }

    public Time getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Time evaluationTime) {
        this.evaluationTime = evaluationTime;
    }
}
