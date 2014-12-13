package parsers;

import java.io.IOException;
import java.util.ArrayList;

import android.util.Log;

import com.tommykvant.wikivoyage.details.content.Content;
import com.tommykvant.wikivoyage.details.content.HorizontalSpace;

public class ContentParser {

	public static ArrayList<Content> parse(LineIterator iterator) {
		ArrayList<Content> out = new ArrayList<Content>();

		while (iterator.hasNext() && !iterator.peekNext().startsWith("=")) {
			if (iterator.peekNext().equals("")) {
				// TODO Deal with horizontal spacing
				out.add(new HorizontalSpace());
				iterator.next();
			} else if (iterator.peekNext().startsWith("<!--")) {
				// Do not include comments
				parseComments(iterator);
			} else if (iterator.peekNext().startsWith("*")) {
				// Parse lists separately
				String text = parseList(iterator);
				try {
					out.add(TextParser.parse(text));
				} catch (IOException e) {
					Log.e("ContentParser", "Corld not parse: " + text);
				}
			} else {
				String text = parseText(iterator);
				try {
					out.add(TextParser.parse(text));
				} catch (IOException e) {
					Log.e("ContentParser", "Corld not parse: " + text);
				}
			}
		}

		return out;
	}

	private static String parseList(LineIterator iterator) {
		StringBuilder sb = new StringBuilder();
		while (iterator.hasNext() && !iterator.peekNext().equals("")
				&& !iterator.peekNext().startsWith("=")) {
			sb.append(iterator.next());

			if (iterator.hasNext() && !iterator.peekNext().equals("")
					&& !iterator.peekNext().startsWith("=")) {
				sb.append("<br>");
			}
		}
		return sb.toString();
	}

	private static String parseText(LineIterator iterator) {
		StringBuilder sb = new StringBuilder();
		while (iterator.hasNext() && !iterator.peekNext().equals("")
				&& !iterator.peekNext().startsWith("=")
				&& !iterator.peekNext().startsWith("*")) {
			sb.append(iterator.next());
			
			if (iterator.hasNext() && !iterator.peekNext().equals("")
					&& !iterator.peekNext().startsWith("=")
					&& !iterator.peekNext().startsWith("*")) {
				sb.append("<br>");
			}
		}
		return sb.toString();
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
