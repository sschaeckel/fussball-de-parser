package com.schaeckel.parser.model;

public class Club {
	
	private String name;
	private String url;
	
	public Club() {}
	public Club(String name, String url){
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
