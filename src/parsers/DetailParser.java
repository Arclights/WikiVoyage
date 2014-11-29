package parsers;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.tommykvant.wikivoyage.details.data.Details;
import com.tommykvant.wikivoyage.details.data.Header;
import com.tommykvant.wikivoyage.details.data.Section;

public class DetailParser {

	public static Details parse(String toParse, String title)
			throws IOException, JSONException {
		String content = new JSONObject(toParse).getJSONObject("parse")
				.getJSONObject("wikitext").getString("*");
		LineIterator iterator = new LineIterator(content);
		Details d = new Details(title);

		parseIntro(d, iterator);
		parseSections(d, iterator);

		return d;
	}

	private static void parseIntro(Details details, LineIterator iterator) {
		while (iterator.hasNext()
				&& (iterator.peekNext().length() == 0 || iterator.peekNext()
						.charAt(0) == '{')) {
			iterator.next();
		}
		Section section = new Section(new Header("Intro", 2));
		section.addText(parseUntilCharlineilinei(iterator, '='));
		details.addSection(section);
	}

	private static void parseSections(Details details, LineIterator iterator) {
		while (iterator.hasNext()) {
			String line = iterator.next();
			if (line.length() > 0) {
				if (line.charAt(0) == '=') {
					int headerCounter = countChars(line, 0, '=');
					String headerText = line.split(multipleChars('=',
							headerCounter))[1];
					Header header = new Header(headerText, headerCounter);
					Section section = new Section(header);
					section.addText(parseUntilCharlineilinei(iterator, '='));
					details.addSection(section);

				}
			}
		}
	}

	private static String parseUntilCharlineilinei(LineIterator iterator, char c) {
		StringBuilder text = new StringBuilder();
		while (iterator.hasNext()
				&& (iterator.peekNext().length() == 0 || iterator.peekNext()
						.charAt(0) != c)) {
			text.append(iterator.next());
		}
		return text.toString();
	}

	private void parseText() {

	}

	private static void printArray(String[] in) {
		System.out.println("Array:");
		for (String s : in) {
			System.out.println(s);
		}
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
