package com.realfarmsystem.rfs.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SortsStatistics {
    @SerializedName(value = "sorting",alternate = {"Sorting"})
    @Expose
    private int sorting;
    @SerializedName(value = "allocation",alternate = {"Allocation"})
    @Expose
    private float allocation;
    @SerializedName(value = "countryId",alternate = {"CountryId"})
    @Expose
    private int countryId;

    public int getSorting() {
        return sorting;
    }

    public void setSorting(int sorting) {
        this.sorting = sorting;
    }

    public float getAllocation() {
        return allocation;
    }

    public void setAllocation(float allocation) {
        this.allocation = allocation;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "SortsStatistics{" +
                "sorting=" + sorting +
                ", allocation='" + allocation + '\'' +
                ", countryId=" + countryId +
                '}';
    }
}
