package com.tommykvant.wikivoyage.details.data.templates;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tommy on 2015-02-18.
 */
public class RegionList extends Template implements Content {
    ArrayList<Region> regions;

    public RegionList(ArrayList<String> parts) {
        super(parts);
    }

    @Override
    protected void parse(ArrayList<String> parts) {
        regions = new ArrayList<>();
        Iterator<String> iter = parts.iterator();
        Region r = null;
        Pattern namePattern = Pattern.compile("region\\d+name");
        Pattern colorPattern = Pattern.compile("region\\d+color");
        Pattern itemsPattern = Pattern.compile("region\\d+items");
        Pattern descriptionPattern = Pattern.compile("region\\d+description");
        while (iter.hasNext()) {
            String[] params = iter.next().split("=");
            if (params.length > 1 && !params[1].equals(" ")) {
                String key = params[0].trim();
                if (namePattern.matcher(key).matches()) {
                    if (r != null) {
                        regions.add(r);
                    }
                    r = new Region();
                    r.setName(params[1].trim());
                } else if (colorPattern.matcher(key).matches()) {
                    r.setColor(params[1].trim());
                } else if (itemsPattern.matcher(key).matches()) {
                    r.setItems(params[1].trim());
                } else if (descriptionPattern.matcher(key).matches()) {
                    r.setDescription(params[1].trim());
                }
            }
        }
        if (r != null) {
            regions.add(r);
        }
    }

    @Override
    public String getContent() {
        return "";
    }

    @Override
    public View getView(Context context) {
        LinearLayout regs = new LinearLayout(context);
        regs.setOrientation(LinearLayout.VERTICAL);

        for (Region r : regions) {
            regs.addView(r.getView(context));
        }

        return regs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(regions);
    }

    public static final Parcelable.Creator<RegionList> CREATOR
            = new Parcelable.Creator<RegionList>() {
        public RegionList createFromParcel(Parcel in) {
            return new RegionList(in);
        }

        public RegionList[] newArray(int size) {
            return new RegionList[size];
        }
    };

    public RegionList(Parcel in) {
        regions = new ArrayList<>();
        in.readList(regions, Region.class.getClassLoader());
    }

    private static class Region implements Content {
        String name;
        int color;
        String items;
        String description;

        public Region() {
        }

        private String getText() {
            StringBuilder out = new StringBuilder();
            out.append(TextFormatter.format(name));

            if (items != null) {
                out.append(" (").append(TextFormatter.format(items)).append(")");
            }

            if (description != null) {
                out.append("<br>");
                out.append(TextFormatter.format(description));
            }

            return out.toString();
        }

        @Override
        public View getView(Context context) {
            LinearLayout l = new LinearLayout(context);
            l.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            llp.setMargins(10, 10, 10, 10);
            l.setLayoutParams(llp);

            TextView colorBlock = new TextView(context);
            colorBlock.setText("      ");
            colorBlock.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            colorBlock.setBackgroundColor(color);
            l.addView(colorBlock);

            TextView tv = new TextView(context);
            tv.setText(ContentHtml.fromHtml(getText(), context));
            TextFormatter.makeTextViewLinkFriendly(tv);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.setMargins(10, 0, 0, 0);
            tv.setLayoutParams(lp);
            l.addView(tv);

            return l;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setColor(String color) {
            this.color = Color.parseColor(color);
        }

        public void setItems(String items) {
            this.items = items;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeInt(color);
            dest.writeString(items);
            dest.writeString(description);
        }

        public static final Parcelable.Creator<Region> CREATOR
                = new Parcelable.Creator<Region>() {
            public Region createFromParcel(Parcel in) {
                return new Region(in);
            }

            public Region[] newArray(int size) {
                return new Region[size];
            }
        };

        public Region(Parcel in) {
            name = in.readString();
            color = in.readInt();
            items = in.readString();
            description = in.readString();
        }

        @Override
        public String toString() {
            return name + " " + color + " " + items + " " + description;
        }
    }
}
