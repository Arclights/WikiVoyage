package com.tommykvant.wikivoyage.creators;

import com.tommykvant.wikivoyage.details.content.Content;
import com.tommykvant.wikivoyage.details.data.ListContent;
import com.tommykvant.wikivoyage.details.data.ListContentItem;
import com.tommykvant.wikivoyage.parsers.LineIterator;
import com.tommykvant.wikivoyage.parsers.StringIterator;

import java.util.ArrayList;

public class ListCreator {

    public static ListContent create(StringIterator iterator, ArrayList<Content> sectionContent) {
        return create(iterator, 1, sectionContent);
    }

    public static ListContent create(StringIterator iterator, int level, ArrayList<Content> sectionContent) {
        ListContent out = new ListContent(level);
        out.addItem(new ListContentItem(iterator, sectionContent));
        removeTrailingNewlines(iterator);
        while (iterator.hasNext() && iterator.peekNext() == '*') {
            int currLevel = getLevel(iterator);
            if (currLevel > level) {
                out.addItem(create(iterator, level + 1, sectionContent));
            } else if (currLevel < level) {
                return out;
            } else {
                out.addItem(new ListContentItem(iterator, sectionContent));
                removeTrailingNewlines(iterator);
            }
        }
        return out;
    }

    private static void removeTrailingNewlines(StringIterator iter) {
        while (iter.peekNext() == '\n') {
            iter.next();
        }
    }

    private static int getLevel(StringIterator iter) {
        int level = 0;
        while (iter.hasNext() && iter.peekNext() == '*') {
            iter.next();
            level++;
        }
        return level;
    }

}
