package com.tommykvant.wikivoyage.creators;

import com.tommykvant.wikivoyage.details.content.Content;
import com.tommykvant.wikivoyage.details.data.ListContent;
import com.tommykvant.wikivoyage.details.data.ListContentItem;
import com.tommykvant.wikivoyage.parsers.LineIterator;
import com.tommykvant.wikivoyage.parsers.StringIterator;

import java.util.ArrayList;

public class ListCreator {

    public static ListContent create(LineIterator iterator, ArrayList<Content> sectionContent) {
        return create(iterator, 1, sectionContent);
    }

    public static ListContent create(LineIterator iterator, int level, ArrayList<Content> sectionContent) {
        ListContent out = new ListContent(level);
        while (iterator.hasNext() && iterator.peekNext().startsWith("*")) {
            String line = iterator.peekNext();
            int currLevel = getLevel(line);
            if (currLevel > level) {
                out.addItem(create(iterator, level + 1, sectionContent));
            } else if (currLevel < level) {
                return out;
            } else {
                out.addItem(new ListContentItem(iterator, sectionContent));
            }
        }
        return out;
    }

    private static int getLevel(String line) {
        int level = 0;
        StringIterator iterator = new StringIterator(line);
        while (iterator.hasNext() && iterator.peekNext() == '*') {
            iterator.next();
            level++;
        }
        return level;
    }

}
