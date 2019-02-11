package com.realfarmsystem.rfs.Entity;

import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Shelf {

    @SerializedName("CreatedOnUtc")
    @Expose
    private String createdOnUtc;
    @SerializedName("UserOwnerId")
    @Expose
    private String userOwnerId;
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Note")
    @Expose
    private String note;
    @SerializedName("Number")
    @Expose
    private Integer number;
    @SerializedName("CountOfYangChinchillas")
    @Expose
    private Integer countOfYangChinchillas;
    @SerializedName("CountOfFreeRows")
    @Expose
    private Integer countOfFreeRows;
    @SerializedName("CountOfFreeCages")
    @Expose
    private Integer countOfFreeCages;
    @SerializedName("UpdatedOnUtc")
    @Expose
    private String updatedOnUtc;

    @SerializedName("RowsOrderedByFlor")
    @Expose
    private ArrayList<RowOfShelf> rowsOrderedByFlor=null;


    public ArrayList<RowOfShelf> getRowsOrderedByFlor() {
        return rowsOrderedByFlor;
    }

    public void setRowsOrderedByFlor(ArrayList<RowOfShelf> rowsOrderedByFlor) {
        this.rowsOrderedByFlor = rowsOrderedByFlor;
    }

    public String getCreatedOnUtc() {
        return createdOnUtc;
    }

    public void setCreatedOnUtc(String createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }

    public String getUserOwnerId() {
        return userOwnerId;
    }

    public void setUserOwnerId(String userOwnerId) {
        this.userOwnerId = userOwnerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCountOfYangChinchillas() {
        return countOfYangChinchillas;
    }

    public void setCountOfYangChinchillas(Integer countOfYangChinchillas) {
        this.countOfYangChinchillas = countOfYangChinchillas;
    }

    public Integer getCountOfFreeRows() {
        return countOfFreeRows;
    }

    public void setCountOfFreeRows(Integer countOfFreeRows) {
        this.countOfFreeRows = countOfFreeRows;
    }

    public Integer getCountOfFreeCages() {
        return countOfFreeCages;
    }

    public void setCountOfFreeCages(Integer countOfFreeCages) {
        this.countOfFreeCages = countOfFreeCages;
    }

    public String getUpdatedOnUtc() {
        return updatedOnUtc;
    }

    public void setUpdatedOnUtc(String updatedOnUtc) {
        this.updatedOnUtc = updatedOnUtc;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "rowsOrderedByFlor=" + rowsOrderedByFlor +
                '}';
    }
}
