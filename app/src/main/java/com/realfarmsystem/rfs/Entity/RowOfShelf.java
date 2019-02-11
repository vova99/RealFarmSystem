package com.realfarmsystem.rfs.Entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;


public class RowOfShelf {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("male")
    @Expose
    private Chinchilla male;

    @SerializedName("cages")
    @Expose
    private ArrayList<Cage> cages=null;

    @SerializedName("shelfId")
    @Expose
    private String shelfId;

    @SerializedName("flowNumberInShelf")
    @Expose
    private String flowNumberInShelf;

    @SerializedName("updatedOnUtc")
    @Expose
    private String updatedOnUtc;

    @SerializedName("createdOnUtc")
    @Expose
    private String createdOnUtc;

    @SerializedName("updatedByUserId")
    @Expose
    private String updatedByUserId;

    @SerializedName("isDeleted")
    @Expose
    private boolean isDeleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Chinchilla getMale() {
        return male;
    }

    public void setMale(Chinchilla male) {
        this.male = male;
    }

    public ArrayList<Cage> getCages() {
        return cages;
    }

    public void setCages(ArrayList<Cage> cages) {
        this.cages = cages;
    }

    public String getShelfId() {
        return shelfId;
    }

    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }

    public String getFlowNumberInShelf() {
        return flowNumberInShelf;
    }

    public void setFlowNumberInShelf(String flowNumberInShelf) {
        this.flowNumberInShelf = flowNumberInShelf;
    }

    public String getUpdatedOnUtc() {
        return updatedOnUtc;
    }

    public void setUpdatedOnUtc(String updatedOnUtc) {
        this.updatedOnUtc = updatedOnUtc;
    }

    public String getCreatedOnUtc() {
        return createdOnUtc;
    }

    public void setCreatedOnUtc(String createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }

    public String getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(String updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "RowOfShelf{" +
                "cages=" + cages +
                '}';
    }
}
