package com.genius.ifbretailer.model;

public class DeliveryDetailsModel {
    String refNo,date,cusName,category;

    public DeliveryDetailsModel(String refNo, String date, String cusName, String category) {
        this.refNo = refNo;
        this.date = date;
        this.cusName = cusName;
        this.category = category;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
