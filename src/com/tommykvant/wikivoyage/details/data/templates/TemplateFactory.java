package com.tommykvant.wikivoyage.details.data.templates;

import com.tommykvant.wikivoyage.parsers.StringIterator;

import java.util.ArrayList;

/**
 * Created by tommy on 2015-02-17.
 */
public class TemplateFactory {

    public static Template getTemplate(String content) {
//        String[] parts = content.substring(2, content.length() - 3).split("\\|");
        String[] parts = split(content.substring(2, content.length() - 3), '|');
        String type = parts[0];

        switch (type) {
            case "do":
                return new Do(parts);
            case "see":
                return new See(parts);
            case "eat":
                return new Eat(parts);
            case "sleep":
                return new Sleep(parts);
            case "listing":
                return new Listing(parts);
            default:
                return new NullTemplate(parts);
        }
    }

    private static String[] split(String string, char delim) {
        ArrayList<String> parts = new ArrayList<>();
        int start = 0;
        int end = 0;
        boolean inBrackets = false;
        StringIterator iter = new StringIterator(string);
        while (iter.hasNext()) {
            if (iter.peekNext2().equals("[[")) {
                iter.next();
                iter.next();
                end += 2;
                inBrackets = true;
            } else if (iter.peekNext2().equals("]]")) {
                iter.next();
                iter.next();
                end += 2;
                inBrackets = false;
            }
            if (iter.next() == delim && !inBrackets) {
                parts.add(string.substring(start, end));
                start = end + 1;
            }
            end++;
        }
        parts.add(string.substring(start, end));
        System.out.println(parts);

        String[] out = new String[parts.size()];
        int i = 0;
        for (String s : parts) {
            out[i] = s;
            i++;
        }
        return out;
    }
}
