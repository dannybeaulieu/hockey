package com.bgood.danny.hockeyliguevirtuelle;
import java.util.*;
import com.bgood.danny.hockeyliguevirtuelle.DataModel.*;

class Cache
{
	static WebContentProvider provider;
	private static ArrayList<TeamPlayer> players;
	private static ArrayList<Team> teams;
	static String currentProTeam;
	static String currentProTeamSelection;
	static String currentFarmTeam;
	private static String updatedDate;
	
	static ArrayList<TeamPlayer> getPlayers() {
		if (players == null) {
			players = provider.getTeamPlayers(currentProTeam, currentFarmTeam);
		}
		
		return players;
	}
	
	static ArrayList<Team> getTeams() {
		if (teams == null) {
			teams = provider.getTeams();
		}

		return teams;
	}
	
	static String getDate() {
		if (updatedDate == null) {
			updatedDate = provider.getDate();
		}

		return updatedDate;
	}
	
	static void resetPlayers() {
		players = null;
	}
	
	static void reset() {
		provider.UpdateContent();
		currentFarmTeam = null;
		currentProTeam = null;
		updatedDate = null;
		players = null;
		teams = null;
	}
}
