package com.dimsen.dailyplanet.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {
    @SerializedName("status")
    private String status;

    @SerializedName("totalResult")
    private String totalResult;

    @SerializedName("articles")
    private List<Articles> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }
}
