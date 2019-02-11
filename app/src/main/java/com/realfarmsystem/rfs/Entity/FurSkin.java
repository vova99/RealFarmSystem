package com.realfarmsystem.rfs.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FurSkin {

        @SerializedName("UserOwnerName")
        @Expose
        private Object userOwnerName;
        @SerializedName("Id")
        @Expose
        private String id;
        @SerializedName("UserOwnerSurName")
        @Expose
        private Object userOwnerSurName;
        @SerializedName("UpdatedOnUtc")
        @Expose
        private String updatedOnUtc;
        @SerializedName("Sortings")
        @Expose
        private Integer sorting;
        @SerializedName("ChangeHistory")
        @Expose
        private List<Object> changeHistory = null;
        @SerializedName("Status")
        @Expose
        private Integer status;
        @SerializedName("Note")
        @Expose
        private String note;
        @SerializedName("CreatedOnUtc")
        @Expose
        private String createdOnUtc;
        @SerializedName("UserOwnerId")
        @Expose
        private String userOwnerId;
        @SerializedName("IsForTanningAndSale")
        @Expose
        private Boolean isForTanningAndSale;
        @SerializedName("Code")
        @Expose
        private String code;

        public Object getUserOwnerName() {
            return userOwnerName;
        }

        public void setUserOwnerName(Object userOwnerName) {
            this.userOwnerName = userOwnerName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getUserOwnerSurName() {
            return userOwnerSurName;
        }

        public void setUserOwnerSurName(Object userOwnerSurName) {
            this.userOwnerSurName = userOwnerSurName;
        }

        public String getUpdatedOnUtc() {
            return updatedOnUtc;
        }

        public void setUpdatedOnUtc(String updatedOnUtc) {
            this.updatedOnUtc = updatedOnUtc;
        }

        public Integer getSorting() {
            return sorting;
        }

        public void setSorting(Integer sorting) {
            this.sorting = sorting;
        }

        public List<Object> getChangeHistory() {
            return changeHistory;
        }

        public void setChangeHistory(List<Object> changeHistory) {
            this.changeHistory = changeHistory;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
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

        public Boolean getIsForTanningAndSale() {
            return isForTanningAndSale;
        }

        public void setIsForTanningAndSale(Boolean isForTanningAndSale) {
            this.isForTanningAndSale = isForTanningAndSale;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

    @Override
    public String toString() {
        return "FurSkin{" +
                "userOwnerName=" + userOwnerName +
                ", id='" + id + '\'' +
                ", userOwnerSurName=" + userOwnerSurName +
                ", updatedOnUtc='" + updatedOnUtc + '\'' +
                ", sorting=" + sorting +
                ", changeHistory=" + changeHistory +
                ", status=" + status +
                ", note='" + note + '\'' +
                ", createdOnUtc='" + createdOnUtc + '\'' +
                ", userOwnerId='" + userOwnerId + '\'' +
                ", isForTanningAndSale=" + isForTanningAndSale +
                ", code='" + code + '\'' +
                '}';
    }
}