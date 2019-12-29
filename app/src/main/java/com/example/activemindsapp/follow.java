package com.example.activemindsapp;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class follow implements Serializable {
    private String username;

    public follow(String username) {
        this.username = username;
    }

    /*protected follow(Parcel in) {
        username = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<follow> CREATOR = new Creator<follow>() {
        @Override
        public follow createFromParcel(Parcel in) {
            return new follow(in);
        }

        @Override
        public follow[] newArray(int size) {
            return new follow[size];
        }
    };*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
