package com.tommykvant.wikivoyage.details.data;

import android.content.Context;
import android.os.Parcel;
import android.view.View;
import android.widget.LinearLayout;

import com.tommykvant.wikivoyage.details.content.Content;
import com.tommykvant.wikivoyage.utils.Utils;

import java.util.ArrayList;

public class ListContent implements Content {

	ArrayList<Content> items;
	int level;

	public ListContent(int level) {
		items = new ArrayList<Content>();
		this.level = level;
	}

	public void addItem(Content item) {
		items.add(item);
	}

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

		LinearLayout list = new LinearLayout(context);
		list.setOrientation(LinearLayout.VERTICAL);
		list.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		list.setPadding(Utils.dpsToPixels(context.getResources(), 10), 0, 0,
				Utils.dpsToPixels(context.getResources(), 10));

		for (Content item : items) {
			list.addView(item.getView(context));
		}

		return list;
	}

}
