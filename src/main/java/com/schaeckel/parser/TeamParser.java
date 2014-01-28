package com.schaeckel.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.schaeckel.parser.model.Team;

public class TeamParser {

	public static List<Team> find(String url) {
		List<Team> teams = new ArrayList<Team>(0);
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements elements = doc.select(".egmFixturesLink[href]");
			for (Element team: elements.select(".egmFixturesLink")){
				teams.add( new Team( team.text(), team.attr("href")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return teams;
	}

}
