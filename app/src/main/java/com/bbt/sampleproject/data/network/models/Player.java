package com.bbt.sampleproject.data.network.models;


import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("name")
    private String mName;

    @SerializedName("balance")
    private double mBalance;

    @SerializedName("avatarLink")
    private String mAvatarLink;

    @SerializedName("lastLogindate")
    private String mLastLoginDate;

    public String getName() {
        return mName;
    }

    public double getBalance() {
        return mBalance;
    }

    public String getAvatarLink() {
        return mAvatarLink;
    }

    public String getLastLoginDate() {
        return mLastLoginDate;
    }
}
