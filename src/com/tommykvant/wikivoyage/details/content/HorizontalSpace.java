package com.tommykvant.wikivoyage.details.content;

import android.content.Context;
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
