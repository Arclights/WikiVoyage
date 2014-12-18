package com.tommykvant.wikivotage.creators;


public class TextFormatter {
	public static String format(String text) {
		// Bold
		text = text.replaceAll("\'\'\'(.+?)\'\'\'", "<b>$1</b>");
		// Iltalic
		text = text.replaceAll("\'\'(.+?)\'\'", "<i>$1</i>");
		// Bold italic
		text = text.replaceAll("\'\'\'\'\'(.+?)\'\'\'\'\'", "<b><i>$1</i></b>");
		// Internal links without text
		text = text.replaceAll("\\[\\[([^:\\|]+?)\\]\\]",
				"<a href=\"$1\">$1</a>");
		// Internal links with text
		text = text.replaceAll("\\[\\[([^:\\|]+?)\\|(.+?)\\]\\]",
				"<a href=\"$1\">$2</a>");
		// Links without text
		text = text.replaceAll("(?<!\\[)\\[([^ \\[]+?)\\]",
				"<a href=\"$1\">Link</a>");
		// Links with text
		text = text.replaceAll("(?<!\\[)\\[([^ \\[]+?) (.+?)\\]",
				"<a href=\"$1\">$2</a>");
		// Skip images
		text = text.replaceAll("\\[\\[Image.+?\\]\\]", "");
		// Skip files
		text = text.replaceAll("\\[\\[File.+?\\]\\]", "");
		// Remove list markup
		text = text.replaceAll("^\\*+", "");
		// Remove indentation markup
		text = text.replaceAll("^:+", "");

		return text;
	}
}
