package com.realfarmsystem.rfs.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chinchilla {

    @SerializedName(value = "fatherId",alternate = {"FatherId"})
    @Expose
    private String fatherId;
    @SerializedName(value = "motherId",alternate = {"MotherId"})
    @Expose
    private String motherId;
    @SerializedName(value = "isInFamily",alternate = {"IsInFamily"})
    @Expose
    private Boolean isInFamily;
    @SerializedName(value = "isMale",alternate = {"IsMale"})
    @Expose
    private Boolean isMale;
    @SerializedName(value = "shelfRowId",alternate = {"ShelfRowId"})
    @Expose
    private Object shelfRowId;
    @SerializedName(value = "cageId",alternate = {"CageId"})
    @Expose
    private String cageId;
    @SerializedName(value = "color",alternate = {"Color"})
    @Expose
    private Integer color;
    @SerializedName(value = "aimOfExistence",alternate = {"AimOfExistence"})
    @Expose
    private Integer aimOfExistence;
    @SerializedName(value = "bornOnUtc",alternate = {"BornOnUtc"})
    @Expose
    private Object bornOnUtc;
    @SerializedName(value = "goneOnUtc",alternate = {"GoneOnUtc"})
    @Expose
    private Object goneOnUtc;
    @SerializedName(value = "goneReason",alternate = {"GoneReason"})
    @Expose
    private Object goneReason;
    @SerializedName(value = "deathReasonId",alternate = {"DeathReasonId"})
    @Expose
    private Object deathReasonId;
    @SerializedName(value = "code",alternate = {"Code"})
    @Expose
    private String code;
    @SerializedName(value = "shelfRow",alternate = {"ShelfRow"})
    @Expose
    private Object shelfRow;
    @SerializedName(value = "id",alternate = {"Id"})
    @Expose
    private String id;
    @SerializedName(value = "updatedOnUtc",alternate = {"UpdatedOnUtc"})
    @Expose
    private String updatedOnUtc;
    @SerializedName(value = "createdOnUtc",alternate = {"CreatedOnUtc"})
    @Expose
    private String createdOnUtc;
    @SerializedName(value = "updatedByUserId",alternate = {"UpdatedByUserId"})
    @Expose
    private String updatedByUserId;
    @SerializedName(value = "isDeleted",alternate = {"IsDeleted"})
    @Expose
    private Boolean isDeleted;

    public Chinchilla(String cageId, Integer color, String code,Boolean isMale) {
        this.cageId = cageId;
        this.color = color;
        this.code = code;
        this.isMale = isMale;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getMotherId() {
        return motherId;
    }

    public void setMotherId(String motherId) {
        this.motherId = motherId;
    }

    public Boolean getIsInFamily() {
        return isInFamily;
    }

    public void setIsInFamily(Boolean isInFamily) {
        this.isInFamily = isInFamily;
    }

    public Boolean getIsMale() {
        return isMale;
    }

    public void setIsMale(Boolean isMale) {
        this.isMale = isMale;
    }

    public Object getShelfRowId() {
        return shelfRowId;
    }

    public void setShelfRowId(Object shelfRowId) {
        this.shelfRowId = shelfRowId;
    }

    public String getCageId() {
        return cageId;
    }

    public void setCageId(String cageId) {
        this.cageId = cageId;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Integer getAimOfExistence() {
        return aimOfExistence;
    }

    public void setAimOfExistence(Integer aimOfExistence) {
        this.aimOfExistence = aimOfExistence;
    }

    public Object getBornOnUtc() {
        return bornOnUtc;
    }

    public void setBornOnUtc(Object bornOnUtc) {
        this.bornOnUtc = bornOnUtc;
    }

    public Object getGoneOnUtc() {
        return goneOnUtc;
    }

    public void setGoneOnUtc(Object goneOnUtc) {
        this.goneOnUtc = goneOnUtc;
    }

    public Object getGoneReason() {
        return goneReason;
    }

    public void setGoneReason(Object goneReason) {
        this.goneReason = goneReason;
    }

    public Object getDeathReasonId() {
        return deathReasonId;
    }

    public void setDeathReasonId(Object deathReasonId) {
        this.deathReasonId = deathReasonId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getShelfRow() {
        return shelfRow;
    }

    public void setShelfRow(Object shelfRow) {
        this.shelfRow = shelfRow;
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Chinchilla{" +
                "fatherId='" + fatherId + '\'' +
                ", motherId='" + motherId + '\'' +
                ", isInFamily=" + isInFamily +
                ", isMale=" + isMale +
                ", shelfRowId=" + shelfRowId +
                ", cageId='" + cageId + '\'' +
                ", color=" + color +
                ", aimOfExistence=" + aimOfExistence +
                ", bornOnUtc=" + bornOnUtc +
                ", goneOnUtc=" + goneOnUtc +
                ", goneReason=" + goneReason +
                ", deathReasonId=" + deathReasonId +
                ", code='" + code + '\'' +
                ", shelfRow=" + shelfRow +
                ", id='" + id + '\'' +
                ", updatedOnUtc='" + updatedOnUtc + '\'' +
                ", createdOnUtc='" + createdOnUtc + '\'' +
                ", updatedByUserId='" + updatedByUserId + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
