package com.schaeckel.parser;

import java.io.IOException;
import java.util.List;

import com.schaeckel.parser.model.Club;
import com.schaeckel.parser.model.Match;
import com.schaeckel.parser.model.Team;

public class FussballDeParser {
	
	public static void main(String[] args) throws IOException {

		List<Club> clubs = ClubParser.find("Bebertaler SV");
		for (Club c : clubs){
			System.out.println(c.toString());
			List<Team> teams = TeamParser.find(c.getUrl());
			for (Team team: teams){
				System.out.println(team.toString());
				MatchParser matchPlan = new MatchParser();
				matchPlan.find(team.getUrl());
				System.out.println(matchPlan.getTitle());
				List<Match> matches = matchPlan.find(team.getUrl().replace("begegnungen", "staffelspielplan"));
				for (Match m: matches){
					System.out.println(m.getUrl());
				}
				System.out.println("-----------------------------");
			}
		}
	
	}

}
