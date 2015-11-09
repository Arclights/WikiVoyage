package com.tommykvant.wikivoyage.details.data.templates;

import android.util.Log;

import com.tommykvant.wikivoyage.parsers.StringIterator;

import java.util.ArrayList;

/**
 * Created by tommy on 2015-02-17.
 */
public class TemplateFactory {

    public static Template getTemplate(String content) {
//        String[] parts = content.substring(2, content.length() - 3).split("\\|");
        ArrayList<String> parts = split(content.substring(2, content.length() - 2), '|');
        String type = parts.get(0);

        switch (type) {
            case "do":
                return new Do(parts);
            case "see":
                return new See(parts);
            case "buy":
                return new Buy(parts);
            case "eat":
                return new Eat(parts);
            case "drink":
                return new Drink(parts);
            case "sleep":
                return new Sleep(parts);
            case "listing":
                return new Listing(parts);
            case "Regionlist":
                return new RegionList(parts);
            case "flag":
                return new Flag(parts);
            case "Climate":
                return new Climate(parts);
            case "IATA":
                return new IATA(parts);
            default:
                if (type.startsWith("routebox")) {
                    return new RouteBox(parts);
                }
                Log.e("TemplateFactory", "Unknown template: " + type);
                return new NullTemplate(parts);
        }
    }

    private static ArrayList<String> split(String string, char delim) {
        ArrayList<String> parts = new ArrayList<>();
        int start = 0;
        int end = 0;
        boolean inBrackets = false;
        boolean inCurlBrackets = false;
        StringIterator iter = new StringIterator(string);
        while (iter.hasNext()) {
            if (iter.next2IsStartBrackets()) {
                iter.next();
                iter.next();
                end += 2;
                inBrackets = true;
            } else if (iter.next2IsEndBrackets()) {
                iter.next();
                iter.next();
                end += 2;
                inBrackets = false;
            } else if (iter.isAtStartOfTemplate()) {
                iter.next();
                iter.next();
                end += 2;
                inCurlBrackets = true;
            } else if (iter.isAtEndOfTemplate()) {
                iter.next();
                iter.next();
                end += 2;
                inCurlBrackets = false;
            }
            if (!iter.hasNext()) {
                break;
            }
            if (iter.next() == delim && !inBrackets && !inCurlBrackets) {
                parts.add(string.substring(start, end));
                start = end + 1;
            }
            end++;
        }
        parts.add(string.substring(start, end));

        return parts;
    }
}
