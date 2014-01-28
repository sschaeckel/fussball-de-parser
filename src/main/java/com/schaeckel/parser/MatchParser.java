package com.schaeckel.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.schaeckel.parser.model.Match;

/*
 * TODO : aufräumen
 */
public class MatchParser {
	
	private String title;

	public List<Match> find(String url) throws IOException {
		List<Match> matches = new ArrayList<Match>(0);
		Document doc = Jsoup.connect(url).get();
		title = doc.select("title").text();
		// <a id="egmLink_MI_01HN62T8TK000000VV0AG812VV8OD2HP" class="egmMatchInfoLink" href="http://ergebnisdienst.fussball.de/spiel/1kreisklasse-staffel-1/kreis-boerde/1kreisklasse/herren/spieljahr1314/sachsen-anhalt/-/mandant/64/spiel/01HN62T8TK000000VV0AG812VV8OD2HP">
		Elements matchLinks = doc.select("a[id].egmMatchInfoLink");
		for (Element m: matchLinks){
			matches.add(new Match(m.select("[href]").attr("href")));
		}
		return matches;
	}

	public String getTitle() {
		return title;
	}


}
