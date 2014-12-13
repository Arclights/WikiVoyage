package parsers;

import java.io.IOException;

import com.tommykvant.wikivoyage.details.data.Text;

public class TextParser {
	public static Text parse(String text) throws IOException {
		// Bold
		text = text.replaceAll("\'\'\'(.+?)\'\'\'", "<b>$1</b>");
		// Iltalic
		text = text.replaceAll("\'\'(.+?)\'\'", "<i>$1</i>");
		// Bold italic
		text = text.replaceAll("\'\'\'\'\'(.+?)\'\'\'\'\'", "<b><i>$1</i></b>");
		// Internal links without text
		text = text.replaceAll("\\[\\[([^:\\|]+?)\\]\\]",
				"<aInternal href=\"$1\">$1</aInternal>");
		// Internal links with text
		text = text.replaceAll("\\[\\[([^:\\|]+?)\\|(.+?)\\]\\]",
				"<aInternal href=\"$1\">$2</aInternal>");
		// Links without text
		text = text.replaceAll("(?<!\\[)\\[([^ \\[]+?)\\]", "<a href=\"$1\">Link</a>");
		// Links with text
		text = text.replaceAll("(?<!\\[)\\[([^ \\[]+?) (.+?)\\]", "<a href=\"$1\">$2</a>");
		// Skip images
		text = text.replaceAll("\\[\\[Image.+?\\]\\]", "");
		// Skip files
		text = text.replaceAll("\\[\\[File.+?\\]\\]", "");
		Text out = new Text(text);
		return out;
	}

	private static String filterText(String text) {
		return text.replaceAll("<!--.*-->", "");
	}
}
