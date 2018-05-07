package com.chen.meme.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityevaluationHelper {
    private Integer userID;
    //      Integer memeID, Double evaluations;
    private Map<Integer, Double> Entityevaluation;

    private EntityevaluationHelper() {
    }

    private double distance(EntityevaluationHelper other){
        double sum = 0;
        double count = 0;
        Double evaluations;
        Map<Integer, Double> userEntityevaluation = getEntityevaluation();
        Map<Integer, Double> otherEntityevaluation = other.getEntityevaluation();
        for (Map.Entry<Integer, Double> entry:userEntityevaluation.entrySet()){
            evaluations = otherEntityevaluation.get(entry.getKey());
            if(evaluations!=null){
                count = entry.getValue() - evaluations;
                sum += count * count;
            }
        }
        return Math.sqrt(sum);
    }
    public double similarity(EntityevaluationHelper other){
        return 1 /(1+ distance(other));
    }
    public static List<EntityevaluationHelper> build(List<Entityevaluation> entityevaluations){
        List<EntityevaluationHelper> helpers = new ArrayList<>();
        Map<Integer, EntityevaluationHelper> map = new HashMap<>();
        for(Entityevaluation user:entityevaluations){
            EntityevaluationHelper other = map.get(user.getUserID());
            if(other!=null){
                other.getEntityevaluation().put(user.getMemeID(), user.getEvaluations());
            }else{
                other = new EntityevaluationHelper();
                other.setUserID(user.getUserID());
                Map<Integer, Double> e = new HashMap<>();
                e.put(user.getMemeID(), user.getEvaluations());
                other.setEntityevaluation(e);
                map.put(user.getUserID(), other);
            }
        }
        for (Map.Entry<Integer, EntityevaluationHelper> entry:map.entrySet()){
            helpers.add(entry.getValue());
        }
        return helpers;
    }

    public static EntityevaluationHelper build(Integer userID,List<Entityevaluation> entityevaluations){
        EntityevaluationHelper userHelpers= new EntityevaluationHelper();
        userHelpers.setUserID(userID);
        Map<Integer, Double> e = new HashMap<>();
        for(Entityevaluation user:entityevaluations){
            if(user.getUserID()==userID){
                e.put(user.getMemeID(), user.getEvaluations());
            }
        }
        userHelpers.setEntityevaluation(e);
        return userHelpers;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Map<Integer, Double> getEntityevaluation() {
        return Entityevaluation;
    }

    public void setEntityevaluation(Map<Integer, Double> entityevaluation) {
        Entityevaluation = entityevaluation;
    }
}
