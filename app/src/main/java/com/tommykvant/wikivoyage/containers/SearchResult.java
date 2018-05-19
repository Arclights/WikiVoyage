package com.tommykvant.wikivoyage.containers;


public class SearchResult {
	public String title;
	public String snippet;
	public int sroffset;

	public SearchResult(String title, String snippet, int sroffset) {
		this.title = title;
		this.snippet = snippet;
		this.sroffset = sroffset;

	}

	@Override
	public String toString() {
		return "title: " + title + "\tsnippet: " + snippet;
	}

}
