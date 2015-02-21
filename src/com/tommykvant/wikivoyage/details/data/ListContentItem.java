package com.tommykvant.wikivoyage.details.data;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tommykvant.wikivoyage.details.content.Content;
import com.tommykvant.wikivoyage.parsers.LineIterator;
import com.tommykvant.wikivoyage.utils.Utils;

import java.util.ArrayList;

public class ListContentItem implements Content {
    Content item;

    public ListContentItem(LineIterator iterator, ArrayList<Content> sectionContent) {
        this.item = new TextContent(iterator, sectionContent);
    }

    public View getView(Context context) {
        RelativeLayout l = new RelativeLayout(context);
        l.setPadding(Utils.dpsToPixels(context.getResources(), 5), 0, 0, 0);

        TextView bullet = new TextView(context);
        bullet.setText("•");
        bullet.setId(1542);
        bullet.setPadding(0, 0, Utils.dpsToPixels(context.getResources(), 5), 0);

        l.addView(bullet);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.RIGHT_OF, bullet.getId());
        l.addView(item.getView(context), lp);

        return l;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(item, 0);
    }

    public static final Parcelable.Creator<ListContentItem> CREATOR
            = new Parcelable.Creator<ListContentItem>() {
        public ListContentItem createFromParcel(Parcel in) {
            return new ListContentItem(in);
        }

        public ListContentItem[] newArray(int size) {
            return new ListContentItem[size];
        }
    };

    private ListContentItem(Parcel in) {
        item = in.readParcelable(Content.class.getClassLoader());
    }
}
