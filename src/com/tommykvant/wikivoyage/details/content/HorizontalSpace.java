package com.tommykvant.wikivoyage.details.content;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

public class HorizontalSpace implements Content {

    public HorizontalSpace() {
    }

    @Override
    public View getView(Context context) {
        TextView tv = new TextView(context);
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(0xFF00FF00); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(5);
        gd.setStroke(1, 0xFF000000);
        tv.setBackground(gd);
        tv.setText("");
        return tv;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub

    }

    public static final Parcelable.Creator<HorizontalSpace> CREATOR
            = new Parcelable.Creator<HorizontalSpace>() {
        public HorizontalSpace createFromParcel(Parcel in) {
            return new HorizontalSpace(in);
        }

        public HorizontalSpace[] newArray(int size) {
            return new HorizontalSpace[size];
        }
    };

    public HorizontalSpace(Parcel in) {
    }

}
