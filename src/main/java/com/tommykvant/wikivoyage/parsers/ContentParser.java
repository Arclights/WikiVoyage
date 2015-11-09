package com.tommykvant.wikivoyage.parsers;

import com.tommykvant.wikivoyage.creators.ListCreator;
import com.tommykvant.wikivoyage.details.content.Content;
import com.tommykvant.wikivoyage.details.content.HorizontalSpace;
import com.tommykvant.wikivoyage.details.data.IndentedTextContent;
import com.tommykvant.wikivoyage.details.data.TextContent;

import java.util.ArrayList;

public class ContentParser {

    public static ArrayList<Content> parse(StringIterator iterator) {
        ArrayList<Content> out = new ArrayList<Content>();

        while (iterator.hasNext() && iterator.peekNext() != '=') {
            if (iterator.peekNext() == '\n') {
                // deal with horizontal spacing
                out.add(new HorizontalSpace());
                iterator.next();
            } else if (iterator.peekNext() == '*') {
                // Parse lists separately
                out.add(ListCreator.create(iterator, out));
            } else if (iterator.peekNext() == ':') {
                // Indentation
                out.add(new IndentedTextContent(iterator, out));
            } else {
                // String text = parseText(iterator);
                out.add(new TextContent(iterator, out));
            }
        }

        return out;
    }
}
