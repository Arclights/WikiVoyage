package com.tommykvant.wikivoyage.details.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Section implements Parcelable {
	public Header header;
	public String text;

	public Section(Header header) {
		this.header = header;
	}

	public void addText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return header + "\n" + text + "\n\n";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	private Section(Parcel in) {
		header = in.readParcelable(Header.class.getClassLoader());
		text = in.readString();
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeParcelable(header, 0);
		out.writeString(text);
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
