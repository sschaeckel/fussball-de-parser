package com.schaeckel.parser.model;

public class Team {

	private String name;
	private String url;
	private String ageClass; // Spielklasse
	
	public Team(){ }
	public Team(String name, String url) {
		this.name = name;
		this.url = url;
		setDivision();
	}
	
	private void setDivision() {
		this.ageClass = name.substring(name.indexOf('(')+1, name.indexOf(')'));
		this.name = name.substring(0, name.indexOf('(')-1);
	}
	
	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public int hashCode() {
		return (name+"~"+url+"~"+ageClass).hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj==null?false:((getClass() != obj.getClass()?false:this.hashCode()==obj.hashCode())));
	}

	@Override
	public String toString() {
		return "name: " + this.name + "\n" +
				"url: " + this.url + "\n" +
				"division: " + this.ageClass;
	}
}
