package com.schaeckel.parser;

import java.io.IOException;
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
	
	public static void main(String[] args) throws IOException {

		String baseURL = "http://suche.fussball.de/to/fbde/all";
		String searchString = "MSV Börde";
		
		Map<String, String> params = new HashMap<String, String>(1);
		params.put("q", URLEncoder.encode(searchString, "UTF-8"));
		String url = baseURL;
		int i = 0;
		for (Entry<String, String> value: params.entrySet()){
			url += (i==0?"?":"&");
			url += value.getKey()+"="+value.getValue();
			i++;
		}
		// System.out.println(url);
		Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
		Elements elements = doc.select("td.search-result-content[id=fbde_vereine]");
		elements = elements.select("a[href]");
		for (Element el: elements){
			// Vereinsnamen umsetzen
			System.out.println(el.text());
			// link auf die Vereinseite Filtern
			String clubUrl = URLDecoder.decode(el.toString().substring(el.toString().indexOf("r=")+2, el.toString().indexOf("\" onclick")), "UTF-8");
			System.out.println(clubUrl);
		}
	}

}
