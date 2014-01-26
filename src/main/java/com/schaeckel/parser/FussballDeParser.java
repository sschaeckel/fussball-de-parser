package com.schaeckel.parser;

import java.io.IOException;
import java.util.List;

import com.schaeckel.parser.model.Club;

public class FussballDeParser {
	
//	private static void printTeams(String clubUrl) throws IOException {
//		Document doc = Jsoup.connect(clubUrl).get();
//		Elements teams = doc.select(".egmFixturesLink[href]");
//		System.out.println("Teams:");
//		for (Element team: teams.select(".egmFixturesLink")){
//			System.out.println("name: " + team.text());
//			System.out.println("teamlink: " + team.attr("href"));
//		}
//	}


	public static void main(String[] args) throws IOException {

		List<Club> clubs = ClubSearcher.find("1.fc magdeburg");
		for (Club c : clubs){
			System.out.println(c.toString());
		}
	
	}

}
