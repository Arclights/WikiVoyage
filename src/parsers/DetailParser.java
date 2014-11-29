package parsers;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.tommykvant.wikivoyage.details.data.Details;
import com.tommykvant.wikivoyage.details.data.Section;

public class DetailParser {

	public static Details parse(String toParse, String title)
			throws IOException, JSONException {
		String content = new JSONObject(toParse).getJSONObject("parse")
				.getJSONObject("wikitext").getString("*");
		LineIterator iterator = new LineIterator(content);
		Details d = new Details(title);

		while (iterator.hasNext()) {
			String line = iterator.next();
			if (line.length() > 0) {
				if (line.charAt(0) == '=') {
					int headerCounter = countChars(line, 0, '=');
					String headerText = line.split(multipleChars('=',
							headerCounter))[1];
					StringBuilder text = new StringBuilder();
					while (iterator.peekNext().length() == 0 || iterator.peekNext().charAt(0) != '=') {
						text.append(iterator.next());
						if (!iterator.hasNext()) {
							break;
						}
					}
					Section section = new Section(headerText, text.toString(),
							headerCounter);
					d.addSection(section);

				} else if (line.charAt(0) == '{' && line.charAt(1) == '{'
						&& line.length() > 4) {
					System.out.println(line);
					String[] options = line.replace("{", "").replace("}", "")
							.split("\\|");
					if (options[0].equals("pagebanner")) {
						System.out.println("Pagebanner: " + options[1]);
					}
				}
			}
		}

		return d;
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
