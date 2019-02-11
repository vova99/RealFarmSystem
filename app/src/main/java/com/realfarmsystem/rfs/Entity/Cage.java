package com.realfarmsystem.rfs.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cage {
    @SerializedName("chinchillas")
    @Expose
    private ArrayList<Chinchilla> chinchillas = null;

    @SerializedName("shelfRowId")
    @Expose
    private String shelfRowId;

    @SerializedName("numnerShelfRowInRow")
    @Expose
    private String numnerShelfRowInRow;

    @SerializedName("id")
    @Expose
    private String id;

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
    private Boolean isDeleted;


    public ArrayList<Chinchilla> getChinchillas() {
        return chinchillas;
    }

    public void setChinchillas(ArrayList<Chinchilla> chinchillas) {
        this.chinchillas = chinchillas;
    }

    public String getShelfRowId() {
        return shelfRowId;
    }

    public void setShelfRowId(String shelfRowId) {
        this.shelfRowId = shelfRowId;
    }

    public String getNumnerShelfRowInRow() {
        return numnerShelfRowInRow;
    }

    public void setNumnerShelfRowInRow(String numnerShelfRowInRow) {
        this.numnerShelfRowInRow = numnerShelfRowInRow;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Cage{" +
                "chinchillas=" + chinchillas +
                '}';
    }
}
