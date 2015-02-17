package com.tommykvant.wikivoyage.details.data;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import com.tommykvant.wikivoyage.utils.Utils;

public class Header implements Parcelable {
	private String text;
	private int headerCount;

	public Header(String headerText, int headerCount) {
		text = headerText;
		this.headerCount = headerCount;
	}

	public int getLeftPadding(Resources res) {
		return Utils.dpsToPixels(res, (headerCount - 2) * 20);
	}

	private Header(Parcel in) {
		text = in.readString();
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(text);
	}

	public static final Parcelable.Creator<Header> CREATOR = new Parcelable.Creator<Header>() {
		public Header createFromParcel(Parcel in) {
			return new Header(in);
		}

		public Header[] newArray(int size) {
			return new Header[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public String toString() {
		return headerCount + "." + text;
	}

	public View getView(Context context) {
		TextView tv = new TextView(context);
		tv.setText(text);
		tv.setTextSize(Utils.spToPixels(context.getResources(), 16));
		tv.setTypeface(null, Typeface.BOLD);
		return tv;
	}
}
