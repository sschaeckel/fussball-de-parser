package com.schaeckel.parser.model;

public class Team {

	private String name;
	private String url;

	public Team(){ }
	public Team(String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public int hashCode() {
		return (name+"~"+url).hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj==null?false:((getClass() != obj.getClass()?false:this.hashCode()==obj.hashCode())));
	}

	@Override
	public String toString() {
		return "name: " + this.name + "\n" +
				"url: " + this.url;
	}
}
