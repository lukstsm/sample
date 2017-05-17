package com.bbt.sampleproject.data.network.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GameSession implements Parcelable {

    @SerializedName("name")
    private String mName;

    @SerializedName("jackpot")
    private double mJackpot;

    @SerializedName("date")
    private String mDate;

    public String getName() {
        return mName;
    }

    public double getJackpot() {
        return mJackpot;
    }

    public String getDate() {
        return mDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeDouble(this.mJackpot);
        dest.writeString(this.mDate);
    }

    public GameSession() {
    }

    protected GameSession(Parcel in) {
        this.mName = in.readString();
        this.mJackpot = in.readDouble();
        this.mDate = in.readString();
    }

    public static final Creator<GameSession> CREATOR = new Creator<GameSession>() {
        @Override
        public GameSession createFromParcel(Parcel source) {
            return new GameSession(source);
        }

        @Override
        public GameSession[] newArray(int size) {
            return new GameSession[size];
        }
    };
}
