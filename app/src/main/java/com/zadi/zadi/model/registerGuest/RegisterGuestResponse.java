package com.zadi.zadi.model.registerGuest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zadi.zadi.model.BaseModel;

public class RegisterGuestResponse extends BaseModel {
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("itemsCount")
    @Expose
    private Object itemsCount;
    @SerializedName("validation")
    @Expose
    private Validation validation;

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Validation getValidation() {
        return this.validation;
    }

    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    public Object getItemsCount() {
        return this.itemsCount;
    }

    public void setItemsCount(Object itemsCount) {
        this.itemsCount = itemsCount;
    }
}
