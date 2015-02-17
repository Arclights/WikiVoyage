package com.tommykvant.wikivoyage.details.data;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.LinearLayout;

import com.tommykvant.wikivoyage.details.content.Content;

import java.util.ArrayList;
import java.util.HashMap;

public class Section implements Parcelable {
	public Header header;
	public ArrayList<Content> content;

	public Section(Header header, ArrayList<Content> content) {
        this.header = header;
		this.content = content;
	}

	// public void addContent(ArrayList<Content> content) {
	// this.content.addAll(content);
	// }

	static HashMap<Integer, Integer> colors = new HashMap<Integer, Integer>();
	static {
		colors.put(0, Color.BLUE);
		colors.put(1, Color.CYAN);
		colors.put(2, Color.GREEN);
		colors.put(3, Color.MAGENTA);
		colors.put(4, Color.RED);
		colors.put(5, Color.YELLOW);
	}

	public View getView(Context context) {
		LinearLayout rootView = new LinearLayout(context);
		rootView.setOrientation(LinearLayout.VERTICAL);
		rootView.addView(header.getView(context));
		int i = 0;
		for (Content c : content) {
			View v = c.getView(context);
//			 v.setBackgroundColor(colors.get(i % 6));
//			 i++;
			rootView.addView(v);
		}
		return rootView;
	}

	public boolean noContent() {
		return content.size() == 0;
	}

	@Override
	public String toString() {
		return header.toString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	private Section(Parcel in) {
		header = in.readParcelable(Header.class.getClassLoader());
		// content = in.readParcelable(Content.class.getClassLoader());
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeParcelable(header, 0);
		// out.writeParcelable(content, 0);
	}

	public static final Parcelable.Creator<Section> CREATOR = new Parcelable.Creator<Section>() {
		public Section createFromParcel(Parcel in) {
			return new Section(in);
		}

		public Section[] newArray(int size) {
			return new Section[size];
		}
	};

}
