package com.bgood.danny.hockeyliguevirtuelle;

import android.content.Context;
import org.jsoup.nodes.*;
import java.io.*;
import org.jsoup.*;
import org.jsoup.select.*;
import java.util.*;
import android.os.*;
import com.bgood.danny.hockeyliguevirtuelle.DataModel.*;
import android.util.*;

class WebContentProvider {
	
	private Document doc = null;
    private final String urlPlayers = "http://www.lhvqr.com/saison%202014-2015/LHVQ2014-15-ProTeamRoster.html";
    private final Context _context;
    private final Global _global;
    private final Handler _handler;
	
	public WebContentProvider(Context context, Global global, Handler handler) {
		_context = context;
		_global = global;
		_handler = handler;
	}
	
	private Document getDocument() {
		if (doc == null) {
			try {
				InputStream inputStream = _context.openFileInput("league.txt");
                String baseUrl = "http://www.lhvqr.com/saison%202014-2015";
                doc = Jsoup.parse(inputStream, null, baseUrl);
			}
			catch (IOException e) {
				Log.e("Exception", "File write failed: " + e.toString());
			}
		}
		return doc;
	}
	
    public void UpdateContent() {
		_global.getProgressDialog().show();
		
        Thread t = new Thread(new Runnable() 
			{                   
				public void run()
				{
					StringBuilder response_str = new StringBuilder();

					try
					{
						Document doc = Jsoup.connect(urlPlayers).get();
					
						response_str.append(doc.html());
					
						FileOutputStream outputStream = _context.openFileOutput("league.txt", Context.MODE_PRIVATE);
						outputStream.write(response_str.toString().getBytes());
						outputStream.close();
					}
					catch (IOException ignored)
					{
                    }
					
					_global.getProgressDialog().dismiss();
					_handler.sendMessage(_handler.obtainMessage());
				}
			});
			t.start();
			
			doc = null;
    }
	
	public ArrayList<Team> getTeams() {
		ArrayList<Team> teams = new ArrayList<Team>();
		
		Elements divs = getDocument().select("div[id*=\"STHS_JS_Team_\"]");
		Boolean newTeam = true;
		Team t = null;

        for (Element teamElement : divs) {

            if (newTeam) {
                String attrValue = teamElement.attr("id");
                t = new Team(attrValue, attrValue.replace("STHS_JS_Team_", ""));
                newTeam = false;
            } else {
                newTeam = true;
                String attrValue = teamElement.attr("id");
                t.setFarmName(attrValue);
                teams.add(t);
            }
        }
			
		return teams;
	}
	
	public String getDate() {
		Element h4 = getDocument().getElementsByTag("h4").first();
		return h4.text();
	}
	
	public ArrayList<TeamPlayer> getTeamPlayers(String teamName, String farmTeam) {
		ArrayList<TeamPlayer> players = new ArrayList<TeamPlayer>();
		Element teamDiv = getDocument().getElementById(teamName);
		Element farmDiv = getDocument().getElementById(farmTeam);
		
		ExtractPlayers(teamDiv.outerHtml(), players, false);
		ExtractPlayers(farmDiv.outerHtml(), players, true);
		
		return players;
	}
	
	private void ExtractPlayers(String html, ArrayList<TeamPlayer> players, Boolean farm) {
		String[] line = (html).split("\n");
		boolean isGoalies = false;

        for (String aLine : line) {
            if (!aLine.trim().startsWith("<") &&
                    !aLine.trim().startsWith("Player") &&
                    !aLine.trim().startsWith("-")) {

                TeamPlayer p = new TeamPlayer();
                if (aLine.trim().startsWith("Goalie")) {
                    isGoalies = true;
                } else {
                    p.setName(aLine.substring(0, 30).trim());
                    if (isGoalies) {
                        p.setPosition(aLine.substring(30, 33).trim());
                        p.setOverall(aLine.substring(84, 87).trim());
                        p.setHealth(aLine.substring(33, 39).trim().replace(",", "."));
                        p.setInjury(aLine.substring(40, 43).trim());
                        p.setAge(aLine.substring(94, 97).trim());
                        int pos = aLine.lastIndexOf("$");
                        p.setSalary(aLine.substring(100, pos).trim().replace("&nbsp;", ","));
                        p.setContract(aLine.substring(pos + 2, pos + 3).trim());
                        SetPlayerAttributes(aLine, p, PlayerAttributesMap.getGoalerAttrs());
                    } else {
                        p.setPosition(aLine.substring(30, 40).trim());
                        p.setOverall(aLine.substring(98, 100).trim());
                        p.setHealth(aLine.substring(40, 46).trim().replace(",", "."));
                        p.setInjury(aLine.substring(47, 50).trim());
                        p.setAge(aLine.substring(107, 110).trim());
                        int pos = aLine.lastIndexOf("$");
                        p.setSalary(aLine.substring(113, pos).trim().replace("&nbsp;", ","));
                        p.setContract(aLine.substring(pos + 2, pos + 3).trim());
                        SetPlayerAttributes(aLine, p, PlayerAttributesMap.getPlayerAttrs());
                    }
                    p.setFarm(farm);
                    players.add(p);
                }
            }
        }
	}
	
	private void SetPlayerAttributes(String line, TeamPlayer player, LinkedHashMap<String, PlayerAttribute> attrs) {
		
		for (PlayerAttribute attr : attrs.values()) {
			int start = attr.getStart();
			int end = attr.getEnd();
			
			player.getAttributes().put(attr.getName(), line.substring(start,end).trim());
		}
	}
	
	public boolean leagueFileExist() {
		File leagueFile = _context.getFileStreamPath("league.txt");
		return leagueFile.exists();
	}
}
