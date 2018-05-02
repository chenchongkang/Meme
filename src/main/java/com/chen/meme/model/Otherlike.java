package com.chen.meme.model;

public class Otherlike {
    private Integer otheruserID;
    private Integer otherlisememeID;

    public Otherlike() {
    }

    public Otherlike(Integer otheruserID, Integer otherlisememeID) {
        this.otheruserID = otheruserID;
        this.otherlisememeID = otherlisememeID;
    }

    public Integer getOtheruserID() {
        return otheruserID;
    }

    public void setOtheruserID(Integer otheruserID) {
        this.otheruserID = otheruserID;
    }

    public Integer getOtherlisememeID() {
        return otherlisememeID;
    }

    public void setOtherlisememeID(Integer otherlisememeID) {
        this.otherlisememeID = otherlisememeID;
    }
}
