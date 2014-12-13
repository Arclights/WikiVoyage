package com.tommykvant.wikivoyage.details.content;

import com.tommykvant.wikivoyage.details.DestinationListActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Parcel;
import android.provider.Browser;
import android.text.ParcelableSpan;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

public class InternalUrlSpan extends ClickableSpan implements ParcelableSpan{
	private final String mURL;

    public InternalUrlSpan(String url) {
        mURL = url;
    }

    public InternalUrlSpan(Parcel src) {
        mURL = src.readString();
    }
    
    public int getSpanTypeId() {
//        return TextUtils.URL_SPAN;
    	return 19900829;
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
    	ds.setColor(Color.GREEN);
    	ds.setUnderlineText(false);
    }

    @Override
    public void onClick(View widget) {
    	System.out.println("Clicking internal link: "+getURL());
        Uri uri = Uri.parse(getURL());
        Context context = widget.getContext();
        
        Intent intent = new Intent(context, DestinationListActivity.class);
		intent.putExtra(DestinationListActivity.DETAIL_PAGE_NAME, uri);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
        
//		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//		intent.putExtra(Browser.EXTRA_APPLICATION_ID, context.getPackageName());
//		context.startActivity(intent);
    }
}
