package com.zadi.zadi.model.Nearby;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NearbyMarketsModel {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("data")
    @Expose
    private List<NearbyData> data = null;
    @SerializedName("validation")
    @Expose
    private String validation;
    @SerializedName("itemsCount")
    @Expose
    private int itemsCount;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<NearbyData> getData() {
        return data;
    }

    public void setData(List<NearbyData> data) {
        this.data = data;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

}