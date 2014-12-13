package com.tommykvant.wikivoyage.details.content;

import android.content.Context;
import android.os.Parcel;
import android.view.View;
import android.widget.TextView;

public class HorizontalSpace extends Content{

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public View getView(Context context) {
		TextView tv=new TextView(context);
		tv.setText("");
		return tv;
	}

}
