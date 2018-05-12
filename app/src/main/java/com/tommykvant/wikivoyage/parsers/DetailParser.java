package com.tommykvant.wikivoyage.parsers;

import com.tommykvant.wikivoyage.client.Parse;
import com.tommykvant.wikivoyage.client.WikiVoyageDetails;
import com.tommykvant.wikivoyage.details.data.Details;
import com.tommykvant.wikivoyage.details.data.Header;
import com.tommykvant.wikivoyage.details.data.Section;

public class DetailParser {

    public static Details parse(WikiVoyageDetails wikiVoyageDetails) {
        Parse parse = wikiVoyageDetails.getParse();
        StringIterator iterator = new StringIterator(parse.getWikitext().getContent());
        Details d = new Details(parse.getTitle());
        parseSections(d, iterator);

        return d;
    }

    private static void parseSections(Details details, StringIterator iterator) {
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

    private static Header parseHeader(StringIterator iterator) {
        int headerCounter = 0;
        while (iterator.peekNext() == '=') {
            iterator.next();
            headerCounter++;
        }
        StringBuilder headerText = new StringBuilder();
        while (iterator.peekNext() != '=') {
            headerText.append(iterator.next());
        }
        int i = headerCounter;
        while (i > 0) {
            iterator.next();
            i--;
        }
        return new Header(headerText.toString(), headerCounter);
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
}
