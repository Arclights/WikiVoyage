package com.tommykvant.wikivoyage.details.data;

import java.util.ArrayList;

public class Details {
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

}
