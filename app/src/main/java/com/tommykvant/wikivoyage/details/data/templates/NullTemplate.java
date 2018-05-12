package com.tommykvant.wikivoyage.details.data.templates;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by tommy on 2015-02-17.
 */
public class NullTemplate extends Template {

    public NullTemplate(ArrayList<String> parts) {
        super(parts);
    }

    @Override
    protected void parse(ArrayList<String> parts) {

    }

    @Override
    public String getContent() {
        return "";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<NullTemplate> CREATOR
            = new Parcelable.Creator<NullTemplate>() {
        public NullTemplate createFromParcel(Parcel in) {
            return new NullTemplate(in);
        }

        public NullTemplate[] newArray(int size) {
            return new NullTemplate[size];
        }
    };

    public NullTemplate(Parcel in) {
    }
}
