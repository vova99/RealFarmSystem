package com.realfarmsystem.rfs.Entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FurStatisticPerCountry {
    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("SortingStatistics")
    @Expose
    private JsonArray sortingStatistics;
    @SerializedName("Code")
    @Expose
    private String code;


    @Override
    public String toString() {
        return "FurStatisticPerCountry{" +
                "id=" + id +
                ", name=" + name +
                ", sortingStatistics=" + sortingStatistics +
                ", code=" + code +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonArray getSortingStatistics() {
        return sortingStatistics;
    }

    public void setSortingStatistics(JsonArray sortingStatistics) {
        this.sortingStatistics = sortingStatistics;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
