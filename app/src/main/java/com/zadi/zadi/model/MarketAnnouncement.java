package com.zadi.zadi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MarketAnnouncement {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("validation")
    @Expose
    private String validation;
    @SerializedName("itemsCount")
    @Expose
    private Object itemsCount;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public Object getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(Object itemsCount) {
        this.itemsCount = itemsCount;
    }

}