package com.tommykvant.wikivoyage.details.data.templates;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcel;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.tommykvant.wikivoyage.R;
import com.tommykvant.wikivoyage.creators.TextFormatter;
import com.tommykvant.wikivoyage.details.content.Content;
import com.tommykvant.wikivoyage.details.content.ContentHtml;
import com.tommykvant.wikivoyage.details.data.Image;
import com.tommykvant.wikivoyage.fetchers.ImageFetcher;

import java.util.ArrayList;

/**
 * Created by tommy on 2015-02-17.
 */
public class RouteBox extends Template implements Content {

    ArrayList<Route> routes;

    public RouteBox(ArrayList<String> parts) {
        super(parts);
    }

    protected void parse(ArrayList<String> parts) {
        routes = new ArrayList<>();
        String imageName = null;
        String directionl = null;
        String majorl = null;
        String minorl = null;
        String directionr = null;
        String majorr = null;
        String minorr = null;
        for (String p : parts) {
            String[] params = p.split("=");
            if (params.length > 1 && !params[1].equals("")) {
                params[0] = params[0].trim();
                params[1] = params[1].trim();
                if (params[0].startsWith("image") && !params[0].startsWith("imagesize")) {
                    System.out.println("Image found: " + params[1]);
                    if (imageName != null) {
                        routes.add(new Route(imageName, directionl, majorl, minorl, directionr, majorr, minorr));
                    }
                    imageName = params[1];
                } else if (params[0].startsWith("directionl")) {
                    System.out.println("Directionl found: " + params[1]);
                    directionl = params[1];
                } else if (params[0].startsWith("majorl")) {
                    majorl = params[1];
                } else if (params[0].startsWith("minorl")) {
                    minorl = params[1];
                } else if (params[0].startsWith("directionr")) {
                    directionr = params[1];
                } else if (params[0].startsWith("majorr")) {
                    majorr = params[1];
                } else if (params[0].startsWith("minorr")) {
                    minorr = params[1];
                }
            }
        }
        if (imageName != null) {
            routes.add(new Route(imageName, directionl, majorl, minorl, directionr, majorr, minorr));
        }
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

    public static final Creator<RouteBox> CREATOR
            = new Creator<RouteBox>() {
        public RouteBox createFromParcel(Parcel in) {
            return new RouteBox(in);
        }

        public RouteBox[] newArray(int size) {
            return new RouteBox[size];
        }
    };

    private RouteBox(Parcel in) {
    }

    @Override
    public View getView(Context context) {
        TableLayout tl = new TableLayout(context);
        tl.setBackground(context.getDrawable(R.drawable.border));
        tl.setPadding(6, 0, 6, 0);
        tl.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        for (Route r : routes) {
            tl.addView(r.getView(context));
        }

        return tl;
    }

    private class Route {

        Image image;
        String directionl;
        String majorl;
        String minorl;
        String directionr;
        String majorr;
        String minorr;

        public Route(String imageName, String directionl, String majorl, String minorl, String directionr, String majorr, String minorr) {
            System.out.println(imageName + "\t" + directionl + "\t" + majorl + "\t" + minorl + "\t" + directionr + "\t" + majorr + "\t" + minorr);
            int size = (int) Image.getIconSizeForDevice();
            image = ImageFetcher.fetch(imageName, size, size);
            this.directionl = "<b>" + directionl + "</b>";
            this.majorl = TextFormatter.format(majorl);
            this.minorl = TextFormatter.format(minorl);
            this.directionr = "<b>" + directionr + "</b>";
            this.majorr = TextFormatter.format(majorr);
            this.minorr = TextFormatter.format(minorr);
        }

        public View getView(Context context) {
            TableRow tr = new TableRow(context);

            TextView tvLeft = new TextView(context);
            tvLeft.setText(ContentHtml.fromHtml(majorl + " ← " + minorl + " ← ", context));
            TextFormatter.makeTextViewLinkFriendly(tvLeft);
            tvLeft.setGravity(Gravity.RIGHT);
            tr.addView(tvLeft);

            int bgColor = Color.parseColor("#555555");
            TextView dirLeft = new TextView(context);
            dirLeft.setText(ContentHtml.fromHtml(directionl, context));
            dirLeft.setTextColor(Color.WHITE);
            dirLeft.setPadding(5, 0, 5, 0);
            dirLeft.setBackgroundColor(bgColor);
            dirLeft.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            tr.addView(dirLeft);

            ImageView route = new ImageView(context);
            route.setImageBitmap(image.getImage());
            route.setPadding(0, 5, 0, 5);
            route.setBackgroundColor(bgColor);
            route.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            tr.addView(route);

            TextView dirRight = new TextView(context);
            dirRight.setText(ContentHtml.fromHtml(directionr, context));
            dirRight.setTextColor(Color.WHITE);
            dirRight.setPadding(5, 0, 5, 0);
            dirRight.setBackgroundColor(bgColor);
            dirRight.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            tr.addView(dirRight);

            TextView tvRight = new TextView(context);
            tvRight.setText(ContentHtml.fromHtml(" → " + minorr + " → " + majorr, context));
            TextFormatter.makeTextViewLinkFriendly(tvRight);
            tr.addView(tvRight);

            return tr;
        }
    }
}
