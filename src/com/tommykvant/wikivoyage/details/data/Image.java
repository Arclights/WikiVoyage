package com.tommykvant.wikivoyage.details.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by tommy on 2015-02-20.
 */
public class Image implements Parcelable {
    private Bitmap image;
    private String imageUrl;
    private String author;

    public Image() {

    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String inline() {
        return "<img src=\"" + imageUrl + "\"/>";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(image, 0);
        dest.writeString(author);
    }

    public static final Parcelable.Creator<Image> CREATOR
            = new Parcelable.Creator<Image>() {
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    private Image(Parcel in) {
        image = in.readParcelable(Bitmap.class.getClassLoader());
        author = in.readString();
    }
}
