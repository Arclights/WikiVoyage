package com.tommykvant.wikivoyage.parsers;

public class StringIterator {

    char[] chars;
    int charsEnd;
    int i;

    public StringIterator(String s) {
        chars = s.toCharArray();
        charsEnd = chars.length-1;
        i = -1;
    }

    public char next() {
        return chars[++i];
    }

    public char peekNext() {
        return chars[i + 1];
    }

    public boolean next2IsStartOfTemplate() {
        return chars[i + 1] == '{' && chars[i + 2] == '{';
    }

    public boolean next2IsEndOfTemplate() {
        return chars[i + 1] == '}' && chars[i + 2] == '}';
    }

    public boolean next2IsStartBrackets() {
        return chars[i + 1] == '[' && chars[i + 2] == '[';
    }

    public boolean next2IsEndBrackets() {
        return chars[i + 1] == ']' && chars[i + 2] == ']';
    }

//	public String peekNext2() {
//		if (i + 2 < chars.length) {
//			return "" + chars[i + 1] + chars[i + 2];
//		}
//		return "" + chars[i + 1];
//	}

    public boolean hasNext() {
        return i < charsEnd;
    }
}
