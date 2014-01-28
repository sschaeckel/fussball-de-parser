package com.schaeckel.parser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.schaeckel.parser.model.Club;

public class ClubParser{

	private ClubParser() {}
	
	public static List<Club> find(String search) {
		List<Club> clubs = new ArrayList<Club>(0);

		String url;
		try {
			url = encodeAndMapQueryParams(search);
			Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
			Elements elements = doc.select("td.search-result-content[id=fbde_vereine]");
			elements = elements.select("a[href]");
			for (Element el: elements){
				if (el.toString().indexOf("r=")>0){
					clubs.add(new Club(el.text(), extractClubUrl(el)));
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return clubs;
	}
	
	private static String extractClubUrl(Element el) throws UnsupportedEncodingException {
		return URLDecoder.decode(el.toString().substring(el.toString().indexOf("r=")+2, el.toString().indexOf("\" onclick")), "UTF-8");
	}

	private static String encodeAndMapQueryParams(String searchString) throws UnsupportedEncodingException {
		
		Map<String, String> params = new HashMap<String, String>(1);
		params.put("q", URLEncoder.encode(searchString, "UTF-8"));
		String url = ParserURLs.SEARCH_URL.getValue();
		int i = 0;
		for (Entry<String, String> value: params.entrySet()){
			url += (i==0?"?":"&");
			url += value.getKey()+"="+value.getValue();
			i++;
		}
		return url;
	}


}
