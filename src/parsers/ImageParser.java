package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fetcher.ImageFetcher;
import fetcher.UriGenerator;
import android.graphics.Bitmap;

public class ImageParser {

	public static Bitmap parse(String in) throws JSONException,
			URISyntaxException, UnsupportedEncodingException {
		System.out.println("ImageParser: "+in);
		JSONArray images = new JSONObject(in).getJSONObject("parse")
				.getJSONArray("images");
		String image = null;
		for (int i = 0; i < images.length(); i++) {
			String currImage = (String) images.get(i);
			if (currImage.toLowerCase().contains("banner")) {
				image = currImage;
				break;
			}
		}
		if (image == null) {
			return null;
		}

		return ImageFetcher.fetch(UriGenerator.generateFileDownload(image));

	}

}
