package com.tommykvant.wikivoyage.parsers;

import com.tommykvant.wikivoyage.containers.SearchResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchParser {

	public static ArrayList<SearchResult> parse(String in) throws JSONException {
		ArrayList<SearchResult> out = new ArrayList<SearchResult>();

		JSONObject json = new JSONObject(in);
		// System.out.println(json);
		int sroffset = json.getJSONObject("query-continue")
				.getJSONObject("search").getInt("sroffset");
		JSONArray list = json.getJSONObject("query").getJSONArray("search");
		// System.out.println(list);
		for (int i = 0; i < list.length(); i++) {
			JSONObject r = list.getJSONObject(i);
			out.add(new SearchResult(r.getString("title"), formatSnippet(r
					.getString("snippet")), sroffset));
		}

		return out;
	}

	private static String formatSnippet(String s) {
		return s.replaceAll("<.*?>", "").replaceAll(".*  +", "") + " ...";
	}

}
