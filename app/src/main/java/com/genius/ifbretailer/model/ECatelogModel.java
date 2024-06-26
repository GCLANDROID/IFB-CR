package com.genius.ifbretailer.model;

public class ECatelogModel {
    String catId,catName;
    String ECatalogID;

    public ECatelogModel(String catId, String catName) {
        this.catId = catId;
        this.catName = catName;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getECatalogID() {
        return ECatalogID;
    }

    public void setECatalogID(String ECatalogID) {
        this.ECatalogID = ECatalogID;
    }
}
