package com.schaeckel.parser;

import java.io.IOException;
import java.util.List;

import com.schaeckel.parser.model.Club;
import com.schaeckel.parser.model.Team;

public class FussballDeParser {
	
	public static void main(String[] args) throws IOException {

		List<Club> clubs = ClubSearcher.find("1.fc magdeburg");
		for (Club c : clubs){
			System.out.println(c.toString());
			List<Team> teams = TeamSearcher.find(c.getUrl());
			for (Team team: teams){
				System.out.println(team.toString());
			}
		}
	
	}

}
