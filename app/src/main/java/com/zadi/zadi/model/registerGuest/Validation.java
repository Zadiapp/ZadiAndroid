package com.zadi.zadi.model.registerGuest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Validation {
    @SerializedName("device_id")
    @Expose
    private List<String> deviceId = null;
    @SerializedName("language")
    @Expose
    private List<String> language = null;
    @SerializedName("latitude")
    @Expose
    private List<String> latitude = null;
    @SerializedName("longitude")
    @Expose
    private List<String> longitude = null;
    @SerializedName("token")
    @Expose
    private List<String> token = null;

    public List<String> getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(List<String> deviceId) {
        this.deviceId = deviceId;
    }

    public List<String> getLanguage() {
        return this.language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public List<String> getToken() {
        return this.token;
    }

    public void setToken(List<String> token) {
        this.token = token;
    }

    public List<String> getLatitude() {
        return this.latitude;
    }

    public void setLatitude(List<String> latitude) {
        this.latitude = latitude;
    }

    public List<String> getLongitude() {
        return this.longitude;
    }

    public void setLongitude(List<String> longitude) {
        this.longitude = longitude;
    }
}
