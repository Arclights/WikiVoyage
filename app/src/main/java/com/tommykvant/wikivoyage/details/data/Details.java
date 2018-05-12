package com.tommykvant.wikivoyage.details.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Details implements Parcelable {
    ArrayList<Section> sections;
    public String title;

    public Details(String title) {
        sections = new ArrayList<Section>();
        this.title = title;
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public Section getSection(int index) {
        return sections.get(index);
    }

    public int nbrSections() {
        return sections.size();
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (Section s : sections) {
            out.append(s);
        }
        return out.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeList(sections);
    }

    public static final Parcelable.Creator<Details> CREATOR
            = new Parcelable.Creator<Details>() {
        public Details createFromParcel(Parcel in) {
            return new Details(in);
        }

        public Details[] newArray(int size) {
            return new Details[size];
        }
    };

    private Details(Parcel in) {
        title = in.readString();
        sections = new ArrayList<>();
        in.readList(sections, Section.class.getClassLoader());
    }
}
