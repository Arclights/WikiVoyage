package com.tommykvant.wikivoyage.details.data.templates;

import android.os.Parcel;
import android.os.Parcelable;

import com.tommykvant.wikivoyage.creators.TextFormatter;
import com.tommykvant.wikivoyage.details.data.TextContentContainer;

import java.util.ArrayList;

/**
 * Created by tommy on 2015-02-17.
 */
public class Listing extends Template {

    String name;
    String url;
    String alt;
    String email;
    String address;
    double latitude;
    double longitude;
    String directions;
    String phone;
    String tollfree;
    String fax;
    String hours;
    String price;
    String content;

    public Listing(ArrayList<String> parts) {
        super(parts);
    }

    protected void parse(ArrayList<String> parts) {
        for (String p : parts) {
            String[] params = p.split("=");
            if (params.length > 1 && !params[1].equals(" ")) {
                switch (params[0].trim()) {
                    case "name":
                        name = params[1].trim();
                        break;
                    case "url":
                        url = params[1].trim();
                        break;
                    case "alt":
                        alt = params[1].trim();
                        break;
                    case "email":
                        email = params[1].trim();
                        break;
                    case "address":
                        address = params[1].trim();
                        break;
                    case "lat":
                        latitude = Double.parseDouble(params[1].trim());
                        break;
                    case "long":
                        longitude = Double.parseDouble(params[1].trim());
                        break;
                    case "directions":
                        directions = TextFormatter.format(params[1].trim());
                        break;
                    case "phone":
                        phone = params[1].trim();
                        break;
                    case "tollfree":
                        tollfree = params[1].trim();
                        break;
                    case "fax":
                        fax = params[1].trim();
                        break;
                    case "hours":
                        hours = TextFormatter.format(params[1].trim());
                        break;
                    case "price":
                        price = TextFormatter.format(params[1].trim());
                        break;
                    case "content":
                        content = TextFormatter.format(params[1].trim());
                        break;
                }
            }
        }
    }

    @Override
    public String getContent() {
        StringBuilder out = new StringBuilder();
        if (url != null) {
            out.append("<b><a href=\"" + url + "\"a>" + name + "</a></b> ");
        } else {
            out.append("<b>" + name + "</b> ");
        }

        if (alt != null) {
            out.append("(").append(TextFormatter.format(alt)).append(") ");
        }

        if (address != null || directions != null) {
            out.append(", ");
            if (address != null) {
                out.append("<img src=\"map\"/>&nbsp;<a href=\"map:" + address + "\">" + address + "</a>");
            }

            if (directions != null) {
                out.append("(<i>" + directions + "</i>)");
            }
        }


        if (phone != null) {
            out.append(", ☎&nbsp;<a href=\"tel:" + phone + "\">" + phone + "</a>");
        }

        if (fax != null) {
            out.append(", fax: " + fax);
        }

        if (email != null) {
            out.append(", <b>@</b>&nbsp;<a href=\"mailto:" + email + "\">" + email + "</a>");
        }

        out.append(".");

        if (hours != null) {
            out.append(" ").append(hours);
            if (!hours.endsWith(".")) {
                out.append(".");
            }
        }

        if (content != null) {
            out.append(" ").append(content);
            if (!content.endsWith(".")) {
                out.append(".");
            }
        }

        if (price != null) {
            out.append(" ").append(price);
            if (!price.endsWith(".")) {
                out.append(".");
            }
        }

        System.out.println("Template html: " + out.toString());

        return out.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
        dest.writeString(alt);
        dest.writeString(email);
        dest.writeString(address);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(directions);
        dest.writeString(phone);
        dest.writeString(tollfree);
        dest.writeString(fax);
        dest.writeString(hours);
        dest.writeString(price);
        dest.writeString(content);
    }

    public static final Parcelable.Creator<Listing> CREATOR
            = new Parcelable.Creator<Listing>() {
        public Listing createFromParcel(Parcel in) {
            return new Listing(in);
        }

        public Listing[] newArray(int size) {
            return new Listing[size];
        }
    };

    private Listing(Parcel in) {
        name = in.readString();
        url = in.readString();
        alt = in.readString();
        email = in.readString();
        address = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        directions = in.readString();
        phone = in.readString();
        tollfree = in.readString();
        fax = in.readString();
        hours = in.readString();
        price = in.readString();
        content = in.readString();
    }
}
