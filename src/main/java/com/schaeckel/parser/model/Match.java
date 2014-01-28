package com.schaeckel.parser.model;

public class Match {

	private String url;
	
	public Match(String html) {
		this.url = html;
	}

	public String getUrl(){
		return url;
	}
}
