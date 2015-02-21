package com.tommykvant.wikivoyage.details.data.templates;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.tommykvant.wikivoyage.R;
import com.tommykvant.wikivoyage.creators.TextFormatter;
import com.tommykvant.wikivoyage.details.content.Content;
import com.tommykvant.wikivoyage.details.content.ContentHtml;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by tommy on 2015-02-18.
 */
public class Climate extends Template implements Content {
    static final int UNIT_METRIC = 1;
    static final int UNIT_IMPERIAL = 2;

    static final int DEGREES = 10;
    static final int LENGTH = 20;

    int unit;
    HashMap<String, Measurement> values;
    String description;
    int unitToShow;

    public Climate(ArrayList<String> parts) {
        super(parts);
        unitToShow = UNIT_METRIC;
    }

    @Override
    protected void parse(ArrayList<String> parts) {
        values = new HashMap<>();
        Iterator<String> iter = parts.iterator();
        iter.next();
        while (iter.hasNext()) {
            String[] params = iter.next().split("=");
            switch (params[0].trim()) {
                case "units":
                    switch (params[1].trim()) {
                        case "Metric":
                            unit = UNIT_METRIC;
                            break;
                        case "Imperial":
                            unit = UNIT_IMPERIAL;
                            break;
                    }
                    break;
                case "description":
                    description = params[1];
                    break;
                default:
                    if (params[0].trim().endsWith("precip")) {
                        values.put(params[0].trim(), new Length(params[1].trim(), unit));
                    } else if (params[0].trim().endsWith("low") || params[0].trim().endsWith("high")) {
                        values.put(params[0].trim(), new Degrees(params[1].trim(), unit));
                    }
                    break;

            }
        }
    }

    @Override
    public String getContent() {
        return "";
    }

    @Override
    public View getView(Context context) {
        HorizontalScrollView out = new HorizontalScrollView(context);
        out.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        out.setFillViewport(true);

        // The outside border
        LinearLayout border = new LinearLayout(context);
        border.setBackgroundColor(Color.parseColor("#9EBDC7"));
        border.setPadding(2, 2, 2, 2);

        int bgColor = Color.parseColor("#f3f3ff");
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setBackgroundColor(bgColor);
        ll.setPadding(10, 10, 10, 10);
        ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // The table
        TableLayout table = new TableLayout(context);
        table.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TableRow.LayoutParams lp = new TableRow.LayoutParams();
        lp.setMargins(10, 0, 0, 0);

        TableRow row;

        // Title row
        table.addView(getTitleRow(context, lp));

        // Daily highs
        row = new TableRow(context);
        int color = Color.parseColor("#e85801");
        addToRow(row, "Daily highs (" + unit2Text(DEGREES, unitToShow) + ")", color, lp);
        addValueToRow(row, "janhigh", color, lp);
        addValueToRow(row, "febhigh", color, lp);
        addValueToRow(row, "marhigh", color, lp);
        addValueToRow(row, "aprhigh", color, lp);
        addValueToRow(row, "mayhigh", color, lp);
        addValueToRow(row, "junhigh", color, lp);
        addValueToRow(row, "julhigh", color, lp);
        addValueToRow(row, "aughigh", color, lp);
        addValueToRow(row, "sephigh", color, lp);
        addValueToRow(row, "octhigh", color, lp);
        addValueToRow(row, "novhigh", color, lp);
        addValueToRow(row, "dechigh", color, lp);
        table.addView(row);

        // Nightly lows
        row = new TableRow(context);
        color = Color.parseColor("#346388");
        addToRow(row, "Nightly lows (" + unit2Text(DEGREES, unitToShow) + ")", color, lp);
        addValueToRow(row, "janlow", color, lp);
        addValueToRow(row, "feblow", color, lp);
        addValueToRow(row, "marlow", color, lp);
        addValueToRow(row, "aprlow", color, lp);
        addValueToRow(row, "maylow", color, lp);
        addValueToRow(row, "junlow", color, lp);
        addValueToRow(row, "jullow", color, lp);
        addValueToRow(row, "auglow", color, lp);
        addValueToRow(row, "seplow", color, lp);
        addValueToRow(row, "octlow", color, lp);
        addValueToRow(row, "novlow", color, lp);
        addValueToRow(row, "declow", color, lp);
        table.addView(row);

        // Precipitation
        row = new TableRow(context);
        color = Color.BLACK;
        addToRow(row, "Precipitation (" + unit2Text(LENGTH, unitToShow) + ")", color, lp);
        addValueToRow(row, "janprecip", color, lp);
        addValueToRow(row, "febprecip", color, lp);
        addValueToRow(row, "marprecip", color, lp);
        addValueToRow(row, "aprprecip", color, lp);
        addValueToRow(row, "mayprecip", color, lp);
        addValueToRow(row, "junprecip", color, lp);
        addValueToRow(row, "julprecip", color, lp);
        addValueToRow(row, "augprecip", color, lp);
        addValueToRow(row, "sepprecip", color, lp);
        addValueToRow(row, "octprecip", color, lp);
        addValueToRow(row, "novprecip", color, lp);
        addValueToRow(row, "decprecip", color, lp);
        table.addView(row);

        ll.addView(table);

        ll.addView(getDivider(context));

        TextView tv = new TextView(context);
        System.out.println(TextFormatter.format(description));
        tv.setText(ContentHtml.fromHtml(TextFormatter.format(description), context));
        tv.setLinksClickable(true);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        ll.addView(tv);

        border.addView(ll);

        out.addView(border);

        return out;
    }

    private void addValueToRow(TableRow row, String key, int color, TableRow.LayoutParams lp) {
        if (values.containsKey(key)) {
            addToRow(row, values.get(key), color, lp);
        } else {
            addToRow(row, "-", lp, color);
        }
    }

    private void addToRow(TableRow row, Measurement m, int color, TableRow.LayoutParams lp) {
        row.addView(m.getView(row.getContext(), unitToShow, color), lp);
    }

    private static void addToRow(TableRow row, String text, int color, TableRow.LayoutParams lp) {
        TextView tv = new TextView(row.getContext());
        tv.setText(text);
        tv.setTextColor(color);
        row.addView(tv, lp);
    }

    private static void addToRow(TableRow row, String text, TableRow.LayoutParams lp, int bgColor) {
        TextView tv = new TextView(row.getContext());
        tv.setText(text);
        tv.setBackgroundColor(bgColor);
        row.addView(tv, lp);
    }

    private static String unit2Text(int measure, int unit) {
        switch (unit) {
            case UNIT_METRIC:
                switch (measure) {
                    case DEGREES:
                        return "°C";
                    case LENGTH:
                        return "mm";
                }
            case UNIT_IMPERIAL:
                switch (measure) {
                    case DEGREES:
                        return "°F";
                    case LENGTH:
                        return "in";
                }
            default:
                return "";
        }
    }

    private static View getDivider(Context context) {
        View out = new View(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1);
        lp.setMargins(0, 20, 0, 0);
        out.setLayoutParams(lp);
        out.setBackgroundColor(Color.GRAY);
        return out;
    }

    private TableRow getTitleRow(Context context, TableRow.LayoutParams lp) {
        TableRow row = new TableRow(context);

        int bgColor = Color.parseColor("#CEE0E7");

        addToRow(row, "Climate", lp, bgColor);
        addToRow(row, "Jan", lp, bgColor);
        addToRow(row, "Feb", lp, bgColor);
        addToRow(row, "Mar", lp, bgColor);
        addToRow(row, "Apr", lp, bgColor);
        addToRow(row, "May", lp, bgColor);
        addToRow(row, "Jun", lp, bgColor);
        addToRow(row, "Jul", lp, bgColor);
        addToRow(row, "Aug", lp, bgColor);
        addToRow(row, "Sep", lp, bgColor);
        addToRow(row, "Oct", lp, bgColor);
        addToRow(row, "Nov", lp, bgColor);
        addToRow(row, "Dec", lp, bgColor);
        return row;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(unit);
        dest.writeInt(unitToShow);
        dest.writeMap(values);
        dest.writeString(description);
    }

    public static final Creator<Climate> CREATOR
            = new Creator<Climate>() {
        public Climate createFromParcel(Parcel in) {
            return new Climate(in);
        }

        public Climate[] newArray(int size) {
            return new Climate[size];
        }
    };

    public Climate(Parcel in) {
        unit = in.readInt();
        unitToShow = in.readInt();
        values = new HashMap<>();
        in.readMap(values, Measurement.class.getClassLoader());
        description = in.readString();
    }

    private static class Degrees implements Measurement {

        double celsius;
        double fahrenheit;
        DecimalFormat twoDForm;

        public Degrees(String val, int unit) {
            twoDForm = new DecimalFormat("#.#");
            double value = Double.parseDouble(val);
            switch (unit) {
                case UNIT_METRIC:
                    celsius = value;
                    fahrenheit = c2f(value);
                    break;
                case UNIT_IMPERIAL:
                    fahrenheit = value;
                    celsius = f2c(value);
                    break;
            }
        }

        private double c2f(double celsius) {
            return Double.valueOf(twoDForm.format((celsius * 1.8) + 32));
        }

        private double f2c(double fahrenheit) {
            return Double.valueOf(twoDForm.format((fahrenheit - 32) / 1.8));
        }

        @Override
        public View getView(Context context, int unit, int color) {
            TextView tv = new TextView(context);
            tv.setTextColor(color);
            switch (unit) {
                case UNIT_METRIC:
                    tv.setText(celsius + "");
                    break;
                case UNIT_IMPERIAL:
                    tv.setText(fahrenheit + "");
                    break;
            }
            return tv;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(celsius);
            dest.writeDouble(fahrenheit);
        }

        public static final Creator<Degrees> CREATOR
                = new Creator<Degrees>() {
            public Degrees createFromParcel(Parcel in) {
                return new Degrees(in);
            }

            public Degrees[] newArray(int size) {
                return new Degrees[size];
            }
        };

        public Degrees(Parcel in) {
            celsius = in.readDouble();
            fahrenheit = in.readDouble();
        }
    }

    private static class Length implements Measurement {

        double mm;
        double inch;
        DecimalFormat twoDForm;

        public Length(String val, int unit) {
            twoDForm = new DecimalFormat("#.#");
            double value = Double.parseDouble(val);
            switch (unit) {
                case UNIT_METRIC:
                    mm = value;
                    inch = mm2inch(value);
                    break;
                case UNIT_IMPERIAL:
                    inch = value;
                    mm = inch2mm(value);
                    break;
            }
        }

        private double mm2inch(double mm) {
            return Double.valueOf(twoDForm.format(mm / 25.4));
        }

        private double inch2mm(double inch) {
            return Double.valueOf(twoDForm.format(inch * 25.4));
        }

        @Override
        public View getView(Context context, int unit, int color) {
            TextView tv = new TextView(context);
            tv.setTextColor(color);
            switch (unit) {
                case UNIT_METRIC:
                    tv.setText(mm + "");
                    break;
                case UNIT_IMPERIAL:
                    tv.setText(inch + "");
                    break;
            }
            return tv;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(mm);
            dest.writeDouble(inch);
        }

        public static final Creator<Length> CREATOR
                = new Creator<Length>() {
            public Length createFromParcel(Parcel in) {
                return new Length(in);
            }

            public Length[] newArray(int size) {
                return new Length[size];
            }
        };

        public Length(Parcel in) {
            mm = in.readDouble();
            inch = in.readDouble();
        }

    }

    private interface Measurement extends Parcelable {
        public View getView(Context context, int unit, int color);
    }

}
