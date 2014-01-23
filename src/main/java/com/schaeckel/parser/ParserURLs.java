package com.schaeckel.parser;

public enum ParserURLs {
	
	SEARCH_URL("http://suche.fussball.de/to/fbde/vereine");
	
	private String value;
	
	private ParserURLs(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}
