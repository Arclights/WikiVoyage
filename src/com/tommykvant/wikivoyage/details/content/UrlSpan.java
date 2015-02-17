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
		if (mURL.startsWith("http") || mURL.startsWith("www")) {
			System.out.println("Clicking link: " + getURL());
			Uri uri = Uri.parse(getURL());
			Context context = widget.getContext();
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			intent.putExtra(Browser.EXTRA_APPLICATION_ID,
					context.getPackageName());
			context.startActivity(intent);
		} else {
			System.out.println("Clicking internal link: " + getURL());
			Uri uri = Uri.parse(getURL());
			Context context = widget.getContext();

			Intent intent = new Intent(context, DestinationListActivity.class);
			intent.putExtra(DestinationListActivity.DETAIL_PAGE_NAME, uri);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			context.startActivity(intent);

			// Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			// intent.putExtra(Browser.EXTRA_APPLICATION_ID,
			// context.getPackageName());
			// context.startActivity(intent);
		}
	}
}
