package com.tommykvant.wikivotage.creators;

import parsers.LineIterator;
import parsers.StringIterator;

import com.tommykvant.wikivoyage.details.data.ListContent;
import com.tommykvant.wikivoyage.details.data.ListContentItem;

public class ListCreator {

	public static ListContent create(LineIterator iterator) {
		// LineIterator iterator = new LineIterator(text);
		// System.out.println(text);
		return create(iterator, 1);
	}

	public static ListContent create(LineIterator iterator, int level) {
		ListContent out = new ListContent(level);
		while (iterator.hasNext() && iterator.peekNext().startsWith("*")) {
			String line = iterator.peekNext();
			int currLevel = getLevel(line);
			if (currLevel > level) {
				out.addItem(create(iterator, level + 1));
			} else if (currLevel < level) {
				return out;
			} else {
				String item = getItem(iterator);
				out.addItem(new ListContentItem(TextCreator.create(item
						.substring(level))));
			}
		}
		return out;
	}

	private static String getItem(LineIterator iterator) {
		StringBuilder item = new StringBuilder();
		if (iterator.hasNext()) {
			item.append(iterator.next());
			while (iterator.hasNext() && iterator.peekNext().length() > 0
					&& !iterator.peekNext().startsWith("*")) {
				item.append(iterator.next());
			}
		}

		return item.toString();
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