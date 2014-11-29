package fetcher;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class UriGenerator {

	public static URI generateSearch(String title, String sroffset)
			throws URISyntaxException, UnsupportedEncodingException {
		return new URI(
				"http://en.wikivoyage.org/w/api.php?action=query&format=json&list=search&rawcontinue=&sroffset="
						+ sroffset + "&srsearch=" + encodeString(title));
	}

	public static URI generateImageSearch(String title)
			throws URISyntaxException, UnsupportedEncodingException {
		return new URI(
				"http://en.wikivoyage.org/w/api.php?action=parse&format=json&rawcontinue=&prop=images&page="
						+ encodeString(title));
	}

	public static URI generateFileDownload(String fileName)
			throws URISyntaxException, UnsupportedEncodingException {
		return new URI("http://en.wikivoyage.org/wiki/File:"
				+ encodeString(fileName));
	}

	public static URI generateDetailURI(String title)
			throws URISyntaxException, UnsupportedEncodingException {
		return new URI(
				"http://en.wikivoyage.org/w/api.php?action=parse&format=json&prop=wikitext&page="
						+ encodeString(title));
	}

	private static String encodeString(String in)
			throws UnsupportedEncodingException {
		return URLEncoder.encode(in.replace(" ", "_"), "utf-8");
	}
}
