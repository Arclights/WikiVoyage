package parsers;

import java.util.ArrayList;

import com.tommykvant.wikivotage.creators.ListCreator;
import com.tommykvant.wikivotage.creators.TextFormatter;
import com.tommykvant.wikivoyage.details.content.Content;
import com.tommykvant.wikivoyage.details.content.HorizontalSpace;
import com.tommykvant.wikivoyage.details.data.IndentedTextContent;
import com.tommykvant.wikivoyage.details.data.TextContent;

public class ContentParser {

	public static ArrayList<Content> parse(LineIterator iterator) {
		ArrayList<Content> out = new ArrayList<Content>();

		while (iterator.hasNext() && !iterator.peekNext().startsWith("=")) {
			if (iterator.peekNext().equals("")) {
				// deal with horizontal spacing
				out.add(new HorizontalSpace());
				iterator.next();
			} else if (iterator.peekNext().startsWith("<!--")) {
				// Do not include comments
				parseComments(iterator);
			} else if (iterator.peekNext().startsWith("*")) {
				// Parse lists separately
				out.add(ListCreator.create(iterator));
			} else if (iterator.peekNext().startsWith(":")) {
				// Indention
				out.add(new IndentedTextContent(iterator));
			} else {
				// String text = parseText(iterator);
				out.add(new TextContent(iterator));
			}
		}

		return out;
	}

	private static String parseList(LineIterator iterator) {
		StringBuilder sb = new StringBuilder();
		while (iterator.hasNext() && iterator.peekNext().startsWith("*")) {
			sb.append(iterator.next()).append("\n");
		}
		return sb.toString();
	}

	private static String parseText(LineIterator iterator) {
		StringBuilder sb = new StringBuilder();
		while (iterator.hasNext() && !iterator.peekNext().equals("")
				&& !iterator.peekNext().startsWith("=")
				&& !iterator.peekNext().startsWith("*")) {
			String text = fixIndent(iterator.next());
			sb.append(text);

			if (iterator.hasNext() && !iterator.peekNext().equals("")
					&& !iterator.peekNext().startsWith("=")
					&& !iterator.peekNext().startsWith("*")) {
				sb.append("<br>");
			}
		}
		return sb.toString();
	}

	private static String fixIndent(String text) {
		int indentDepth = getIndentDepth(text);
		return generateIndent(indentDepth) + text.substring(indentDepth);
	}

	private static String generateIndent(int indentDepth) {
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < indentDepth; i++) {
			out.append("&nbsp;&nbsp;&nbsp;");
		}
		return out.toString();
	}

	private static int getIndentDepth(String text) {
		StringIterator iterator = new StringIterator(text);
		int indentDepth = 0;
		while (iterator.hasNext() && iterator.peekNext() == ':') {
			indentDepth++;
			iterator.next();
		}
		return indentDepth;
	}

	private static void parseComments(LineIterator iterator) {
		while (iterator.hasNext()) {
			String line = iterator.next();
			if (line.endsWith("-->") || line.startsWith("-->")) {
				break;
			}
		}
	}

}
