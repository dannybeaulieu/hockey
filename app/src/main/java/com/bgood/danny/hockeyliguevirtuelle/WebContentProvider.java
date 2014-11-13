package com.bgood.danny.hockeyliguevirtuelle;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jsoup.nodes.*;
import java.io.*;
import org.jsoup.*;
import org.jsoup.select.*;
import java.util.*;
import android.app.*;
import android.widget.*;
import android.os.*;
import com.bgood.danny.hockeyliguevirtuelle.DataModel.*;
import android.util.*;

/**
 * Created by Danny on 2014-10-15.
 */
public class WebContentProvider {
	
	private Document doc = null;
	private String baseUrl = "http://www.lhvqr.com/saison%202014-2015";
	private String urlPlayers = "http://www.lhvqr.com/saison%202014-2015/LHVQ2014-15-ProTeamRoster.html";
	private Context _context;
	private Global _global;
	private Handler _handler;
	
	public WebContentProvider(Context context, Global global, Handler handler) {
		_context = context;
		_global = global;
		_handler = handler;
	}
	
	private Document getDocument() {
		if (doc == null) {
			try {
				InputStream inputStream = _context.openFileInput("ligue.txt");
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
					
						FileOutputStream outputStream = _context.openFileOutput("ligue.txt", Context.MODE_PRIVATE);
						outputStream.write(response_str.toString().getBytes());
						outputStream.close();
					}
					catch (IOException e)
					{}
					
					_global.getProgressDialog().dismiss();
					_handler.sendMessage(_handler.obtainMessage());
				}
			});
			t.start();
			
			doc = null;
    }
	
	public void ResetFile() {
		_context.deleteFile("ligue.txt");
	}
	
	public team[] getTeams() {
		ArrayList<team> teams = new ArrayList<team>();
		
		Elements divs = getDocument().select("div[id*=\"STHS_JS_Team_\"]");
		Boolean newTeam = true;
		team t = null;
		
		ListIterator divIterator = divs.listIterator();
	    while (divIterator.hasNext()) {
			Element teamElement = ((Element)divIterator.next());
			
			if (newTeam) {
				String attrValue = teamElement.attr("id");
				t = new team(attrValue, attrValue.replace("STHS_JS_Team_",""));
				newTeam = false;
			}
			else {
				newTeam = true;
				String attrValue = teamElement.attr("id");
				t.setFarmName(attrValue);
				teams.add(t);
			}
		}
			
		return teams.toArray(new team[teams.size()]);
	}
	
	public String getContent() {
		return getDocument().html();
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

		for (int i=0; i < line.length; i++) {
			if (!line[i].trim().startsWith("<") &&
			    !line[i].trim().startsWith("Player") &&
				!line[i].trim().startsWith("-")) {

				TeamPlayer p = new TeamPlayer();
				if (line[i].trim().startsWith("Goalie")) {
					isGoalies = true;
				}
				else {
					p.setName(line[i].substring(0,30).trim());
					if (isGoalies) {
						p.setPosition(line[i].substring(30,33).trim());
						p.setOverall(line[i].substring(84,87).trim());
						p.setHealth(line[i].substring(33,39).trim().replace(",","."));
						p.setInjury(line[i].substring(40,43).trim());
						p.setAge(line[i].substring(94,97).trim());
						int pos = line[i].lastIndexOf("$");
						p.setSalary(line[i].substring(100,pos).trim().replace("&nbsp;",","));
						p.setContract(line[i].substring(pos + 2,pos + 3).trim());
					}
					else
					{
						p.setPosition(line[i].substring(30,40).trim());
						p.setOverall(line[i].substring(98,100).trim());
						p.setHealth(line[i].substring(40,46).trim().replace(",","."));
						p.setInjury(line[i].substring(47,50).trim());
						p.setAge(line[i].substring(107,110).trim());
						int pos = line[i].lastIndexOf("$");
						p.setSalary(line[i].substring(113,pos).trim().replace("&nbsp;",","));
						p.setContract(line[i].substring(pos + 2,pos + 3).trim());
						SetPlayerAttributes(line[i], p, PlayerAttributesMap.getPlayerAttrs());
					}
					p.setFarm(farm);
					players.add(p);
				}
			}
		}
	}
	
	private void SetPlayerAttributes(String line, TeamPlayer player, ArrayMap<String, PlayerAttribute> attrs) {
		
		for (String attr : attrs.keySet()) {
			int start = attrs.get(attr).getStart();
			int end = attrs.get(attr).getEnd();
			
			player.getAttributes().put(attrs.get(attr).getName(), line.substring(start,end).trim());
		}
	}
	
	public boolean ligueFileExist() {
		File ligueFile = _context.getFileStreamPath("ligue.txt");
		return ligueFile.exists();
	}
}
