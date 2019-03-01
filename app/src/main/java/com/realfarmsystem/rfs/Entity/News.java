package com.realfarmsystem.rfs.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("SeoDescription")
    @Expose
    public String seoDescription;
    @SerializedName("UpdatedOnUtc")
    @Expose
    public String updatedOnUtc;
    @SerializedName("WatchCount")
    @Expose
    public Integer watchCount;
    @SerializedName("Image")
    @Expose
    public String image;
    @SerializedName("Tegs")
    @Expose
    public String tegs;
    @SerializedName("Language")
    @Expose
    public Integer language;
    @SerializedName("Title")
    @Expose
    public String title;
    @SerializedName("Content")
    @Expose
    public String content;
    @SerializedName("Description")
    @Expose
    public String description;
    @SerializedName("SeoTitle")
    @Expose
    public String seoTitle;
    @SerializedName("CreatedOnUtc")
    @Expose
    public String createdOnUtc;
    @SerializedName("Id")
    @Expose
    public String id;

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public String getUpdatedOnUtc() {
        return updatedOnUtc;
    }

    public void setUpdatedOnUtc(String updatedOnUtc) {
        this.updatedOnUtc = updatedOnUtc;
    }

    public Integer getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(Integer watchCount) {
        this.watchCount = watchCount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTegs() {
        return tegs;
    }

    public void setTegs(String tegs) {
        this.tegs = tegs;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getCreatedOnUtc() {
        return createdOnUtc;
    }

    public void setCreatedOnUtc(String createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "News{" +
                "seoDescription='" + seoDescription + '\'' +
                ", updatedOnUtc='" + updatedOnUtc + '\'' +
                ", watchCount=" + watchCount +
                ", image='" + image + '\'' +
                ", tegs='" + tegs + '\'' +
                ", language=" + language +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", seoTitle='" + seoTitle + '\'' +
                ", createdOnUtc='" + createdOnUtc + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}