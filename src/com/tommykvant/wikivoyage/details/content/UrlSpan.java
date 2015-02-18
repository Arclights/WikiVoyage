package com.tommykvant.wikivoyage.details.content;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.provider.Browser;
import android.text.ParcelableSpan;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.tommykvant.wikivoyage.details.DestinationListActivity;

public class UrlSpan extends ClickableSpan implements ParcelableSpan {
    protected final String mURL;

    public UrlSpan(String url) {
        mURL = url;
    }

    public UrlSpan(Parcel src) {
        mURL = src.readString();
    }

    public int getSpanTypeId() {
        return 1542187;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mURL);
    }

    public String getURL() {
        return mURL;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
    }

    @Override
    public void onClick(View widget) {
        Context context = widget.getContext();
        if (mURL.startsWith("http") || mURL.startsWith("www")) {
            System.out.println("Clicking link: " + getURL());
            Uri uri = Uri.parse(getURL());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.putExtra(Browser.EXTRA_APPLICATION_ID,
                    context.getPackageName());
            context.startActivity(intent);
        } else if (mURL.startsWith("mailto")) {
            System.out.println("Clicking email: " + getURL() + " " + getURL().split(":")[1]);
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getURL().split(":")[1]});
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }

        } else if (mURL.startsWith("tel:")) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(getURL()));
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }
        } else if (mURL.startsWith("map:")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:0,0?q=" + getURL().split(":")[1].replace(' ', '+')));
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }
        } else {
            System.out.println("Clicking internal link: " + getURL());
            Intent intent = new Intent(context, DestinationListActivity.class);
            intent.putExtra(DestinationListActivity.DETAIL_PAGE_NAME, getURL());
            context.startActivity(intent);
        }
    }
}
