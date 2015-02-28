package com.tommykvant.wikivoyage.details.data;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.tommykvant.wikivoyage.MainPage;

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

    public Bitmap getImage() {
        return image;
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

    public static float getIconSizeForDevice() {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round((float) 32 * density);
    }
}
