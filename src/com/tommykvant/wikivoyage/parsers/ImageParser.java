package com.tommykvant.wikivoyage.parsers;

import android.graphics.Bitmap;

import com.tommykvant.wikivoyage.fetchers.ImageFetcher;
import com.tommykvant.wikivoyage.fetchers.UriGenerator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

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
