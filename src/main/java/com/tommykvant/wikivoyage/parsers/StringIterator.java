package com.tommykvant.wikivoyage.parsers;

public class StringIterator {

    char[] chars;
    int charsEnd;
    int i;

    public StringIterator(String s) {
        chars = s.toCharArray();
        charsEnd = chars.length - 1;
        i = -1;
    }

    public char next() {
        if (chars[i + 1] == '<' && chars[i + 2] == '!' && chars[i + 3] == '-' && chars[i + 4] == '-') {
            consumeComment();
        }
        return chars[++i];
    }

    public char peekNext() {
        return chars[i + 1];
    }

    public boolean isAtStartOfTemplate() {
        return chars[i + 1] == '{' && chars[i + 2] == '{';
    }

    public boolean isAtEndOfTemplate() {
        return chars[i + 1] == '}' && chars[i + 2] == '}';
    }

    public boolean next2IsStartBrackets() {
        return chars[i + 1] == '[' && chars[i + 2] == '[';
    }

    public boolean next2IsEndBrackets() {
        return chars[i + 1] == ']' && chars[i + 2] == ']';
    }

    private void consumeComment() {
        i += 4;
        while (chars[i + 1] != '-' && chars[i + 2] != '-' && chars[i + 3] != '>') {
            i++;
        }
        i += 3;
    }

    public boolean hasNext() {
        return i < charsEnd;
    }

    public void printCurrPlace() {
        StringBuilder sb = new StringBuilder();
        for (int j = i+1; j < i + 10; j++) {
            sb.append(chars[j]);
        }
        System.out.println("Iterator curent place: " + sb.toString());
    }
}
