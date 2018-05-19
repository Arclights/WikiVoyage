package com.tommykvant.wikivoyage.details.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by tommy on 2015-02-17.
 */
public class TextContentText implements TextContentContainer {
    String text;

    public TextContentText(String text) {
        this.text = text;
    }

    @Override
    public String getContent() {
        return text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
    }

    public static final Parcelable.Creator<TextContentText> CREATOR
            = new Parcelable.Creator<TextContentText>() {
        public TextContentText createFromParcel(Parcel in) {
            return new TextContentText(in);
        }

        public TextContentText[] newArray(int size) {
            return new TextContentText[size];
        }
    };

    private TextContentText(Parcel in) {
        text = in.readString();
    }

}
