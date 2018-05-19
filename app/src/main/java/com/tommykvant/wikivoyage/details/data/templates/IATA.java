package com.tommykvant.wikivoyage.details.data.templates;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcel;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tommykvant.wikivoyage.creators.TextFormatter;
import com.tommykvant.wikivoyage.details.content.Content;
import com.tommykvant.wikivoyage.details.content.ContentHtml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * Created by tommy on 2015-02-18.
 */
public class IATA extends Template implements Content {
    String IATA;

    public IATA(ArrayList<String> parts) {
        super(parts);
    }

    @Override
    protected void parse(ArrayList<String> parts) {
        IATA = parts.get(1).trim();
    }

    @Override
    public String getContent() {
        return "<a href=\"https://en.wikipedia.org/wiki/IATA_code\">IATA</a>: <b>" + IATA + "</b>";
    }

    @Override
    public View getView(Context context) {
        return new View(context);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(IATA);
    }

    public static final Creator<IATA> CREATOR
            = new Creator<IATA>() {
        public IATA createFromParcel(Parcel in) {
            return new IATA(in);
        }

        public IATA[] newArray(int size) {
            return new IATA[size];
        }
    };

    public IATA(Parcel in) {
        IATA = in.readString();
    }

}
