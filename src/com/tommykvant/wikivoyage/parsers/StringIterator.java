package com.tommykvant.wikivoyage.parsers;

public class StringIterator {

	char[] chars;
	int i;

	public StringIterator(String s) {
		chars = s.toCharArray();
		i = -1;
	}

	public char next() {
		return chars[++i];
	}

	public char peekNext() {
		return chars[i + 1];
	}

	public String peekNext2() {
		if (i + 2 < chars.length) {
			return "" + chars[i + 1] + chars[i + 2];
		}
		return "" + chars[i + 1];
	}

	public boolean hasNext() {
		return i + 1 < chars.length;
	}
}
