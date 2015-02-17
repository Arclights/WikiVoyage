package com.tommykvant.wikivoyage.parsers;

public class LineIterator {

	String[] lines;
	int i;

	public LineIterator(String s) {
		lines = s.split("\n");
		i = -1;
	}

	public String next() {
		return lines[++i];
	}

	public boolean hasNext() {
		return i < lines.length - 1;
	}

	public String peekNext() {
		return lines[i + 1];
	}

}
