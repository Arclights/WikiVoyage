package com.tommykvant.wikivoyage.parsers;

import com.tommykvant.wikivoyage.details.data.Details;
import com.tommykvant.wikivoyage.details.data.Header;
import com.tommykvant.wikivoyage.details.data.Section;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DetailParser {

    public static Details parse(String toParse, String title)
            throws IOException, JSONException {
//        String content = new JSONObject(toParse).getJSONObject("parse")
//                .getJSONObject("wikitext").getString("*");
        String content = extractWikiText(toParse, title);
        LineIterator iterator = new LineIterator(content);
        Details d = new Details(title);
        parseSections(d, iterator);

        return d;
    }

    private static void parseSections(Details details, LineIterator iterator) {
        Header header = new Header("Intro", 2);
        while (true) {
            details.addSection(new Section(header, ContentParser
                    .parse(iterator)));
            if (!iterator.hasNext()) {
                break;
            }
            header = parseHeader(iterator);
        }
    }

    private static Header parseHeader(LineIterator iterator) {
        String line = iterator.next();
        int headerCounter = countChars(line, 0, '=');
        String headerText = line.split(multipleChars('=', headerCounter))[1];
        return new Header(headerText, headerCounter);
    }

    private static int countChars(String line, int fromPos, char charToCount) {
        int pos = fromPos;
        int counter = 0;
        while (line.charAt(pos) == charToCount) {
            counter++;
            pos++;
        }
        return counter;
    }

    private static String multipleChars(char c, int nbr) {
        String out = "";
        for (int i = 0; i < nbr; i++) {
            out += c;
        }
        return out;
    }

    private static String extractWikiText(String json, String title) {
        return json.substring(38 + title.length(), json.length() - 5).replace("\\n", "\n");
    }

}
