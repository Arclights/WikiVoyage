package com.tommykvant.wikivoyage.details.data.templates;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;

import com.tommykvant.wikivoyage.creators.TextFormatter;
import com.tommykvant.wikivoyage.details.data.Image;
import com.tommykvant.wikivoyage.fetchers.ImageFetcher;

import java.util.ArrayList;

/**
 * Created by tommy on 2015-02-17.
 */
public class Flag extends Template {

    String country;

    public Flag(ArrayList<String> parts) {
        super(parts);
    }

    protected void parse(ArrayList<String> parts) {
        country = parts.get(1);
    }

    private String convertCountry(String country) {
        return "flag_" + country.replace(' ', '_').toLowerCase();
    }

    @Override
    public String getContent() {
        return "<img src=\"flag:" + convertCountry(country) + "\"/> ";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(country);
    }

    public static final Creator<Flag> CREATOR
            = new Creator<Flag>() {
        public Flag createFromParcel(Parcel in) {
            return new Flag(in);
        }

        public Flag[] newArray(int size) {
            return new Flag[size];
        }
    };

    private Flag(Parcel in) {
        country = in.readString();
    }
}
