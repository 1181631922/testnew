package com.fanyafeng.testnew.FrascoOkhttp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fanyafeng on 2015/12/23,0023.
 */
public class FrescoBean implements Comparable, Parcelable {
    private String ImageUrl;

    public FrescoBean(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public FrescoBean(Parcel source) {
        setImageUrl(source.readString());
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getImageUrl());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FrescoBean> CREATOR = new Creator<FrescoBean>() {
        @Override
        public FrescoBean createFromParcel(Parcel in) {
            return new FrescoBean(in);
        }

        @Override
        public FrescoBean[] newArray(int size) {
            return new FrescoBean[size];
        }
    };

    @Override
    public int compareTo(Object another) {
        return 0;
    }
}
