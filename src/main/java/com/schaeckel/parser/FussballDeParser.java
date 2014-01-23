package com.schaeckel.parser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FussballDeParser {
	
	private static String formatSearchURL(String searchString) throws UnsupportedEncodingException {
		
		String baseURL = ParserURLs.SEARCH_URL.getValue();
		Map<String, String> params = new HashMap<String, String>(1);
		params.put("q", URLEncoder.encode(searchString, "UTF-8"));
		String url = baseURL;
		int i = 0;
		for (Entry<String, String> value: params.entrySet()){
			url += (i==0?"?":"&");
			url += value.getKey()+"="+value.getValue();
			i++;
		}
		return url;
	}

	private static void printTeams(String clubUrl) throws IOException {
		Document doc = Jsoup.connect(clubUrl).get();
		Elements teams = doc.select(".egmFixturesLink[href]");
		System.out.println("Teams:");
		for (Element team: teams.select(".egmFixturesLink")){
			System.out.println("name: " + team.text());
			System.out.println("teamlink: " + team.attr("href"));
		}
	}


	public static void main(String[] args) throws IOException {

		// Format searchURL
		String url = formatSearchURL("Bebertaler SV");

		// System.out.println(url);
		Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
		Elements elements = doc.select("td.search-result-content[id=fbde_vereine]");
		elements = elements.select("a[href]");
		for (Element el: elements){
			if (el.toString().indexOf("r=")>0){
				// Vereinsnamen umsetzen
				System.out.println("name: " + el.text());
				// link auf die Vereinseite Filtern
				//String clubUrl = URLDecoder.decode(el.toString().substring(el.toString().indexOf("r=")+2, el.toString().indexOf("\" onclick")), "UTF-8");
				String clubUrl = URLDecoder.decode(el.toString().substring(el.toString().indexOf("r=")+2, el.toString().indexOf("\" onclick")), "UTF-8");
				System.out.println("link: " + clubUrl);
				printTeams(clubUrl);
			}
		}
	}

}
