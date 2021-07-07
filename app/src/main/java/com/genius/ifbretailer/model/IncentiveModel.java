package com.genius.ifbretailer.model;

public class IncentiveModel {
    String incMonth,baseTarget,monthTarget,totalSold,percentage,amount;

    public IncentiveModel(String incMonth, String baseTarget, String monthTarget, String totalSold, String percentage, String amount) {
        this.incMonth = incMonth;
        this.baseTarget = baseTarget;
        this.monthTarget = monthTarget;
        this.totalSold = totalSold;
        this.percentage = percentage;
        this.amount = amount;
    }

    public String getIncMonth() {
        return incMonth;
    }

    public void setIncMonth(String incMonth) {
        this.incMonth = incMonth;
    }

    public String getBaseTarget() {
        return baseTarget;
    }

    public void setBaseTarget(String baseTarget) {
        this.baseTarget = baseTarget;
    }

    public String getMonthTarget() {
        return monthTarget;
    }

    public void setMonthTarget(String monthTarget) {
        this.monthTarget = monthTarget;
    }

    public String getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(String totalSold) {
        this.totalSold = totalSold;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
