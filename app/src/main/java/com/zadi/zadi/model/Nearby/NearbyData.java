package com.zadi.zadi.model.Nearby;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NearbyData implements Parcelable{
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("opening_hour")
    @Expose
    private String openingHour;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("delivery_speed")
    @Expose
    private String deliverySpeed;
    @SerializedName("accuracy")
    @Expose
    private String accuracy;
    @SerializedName("min_charge")
    @Expose
    private String minCharge;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("distance")
    @Expose
    private double distance;

    protected NearbyData(Parcel in) {
        id = in.readInt();
        name = in.readString();
        email = in.readString();
        openingHour = in.readString();
        mobile = in.readString();
        phone = in.readString();
        address = in.readString();
        deliverySpeed = in.readString();
        accuracy = in.readString();
        minCharge = in.readString();
        logo = in.readString();
        distance = in.readDouble();
    }

    public static final Creator<NearbyData> CREATOR = new Creator<NearbyData>() {
        @Override
        public NearbyData createFromParcel(Parcel in) {
            return new NearbyData(in);
        }

        @Override
        public NearbyData[] newArray(int size) {
            return new NearbyData[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(String openingHour) {
        this.openingHour = openingHour;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeliverySpeed() {
        return deliverySpeed;
    }

    public void setDeliverySpeed(String deliverySpeed) {
        this.deliverySpeed = deliverySpeed;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getMinCharge() {
        return minCharge;
    }

    public void setMinCharge(String minCharge) {
        this.minCharge = minCharge;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(openingHour);
        dest.writeString(mobile);
        dest.writeString(phone);
        dest.writeString(address);
        dest.writeString(deliverySpeed);
        dest.writeString(accuracy);
        dest.writeString(minCharge);
        dest.writeString(logo);
        dest.writeDouble(distance);
    }
}