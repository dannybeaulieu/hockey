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
					{
						response_str.append(FormatException.FormatExceptionMessage(e));
					}
					catch (Exception e)
					{
						response_str.append(FormatException.FormatExceptionMessage(e));
					}
					
					_global.getProgressDialog().dismiss();
					_handler.sendMessage(_handler.obtainMessage());
				}
			});
			t.start();
			
			doc = null;
    }
	
	public team[] getTeams() {
		ArrayList<team> teams = new ArrayList<team>();
		
		Elements links = getDocument().getElementsByAttributeValueStarting("href", "#");
		
		ListIterator linkIterator = links.listIterator();
	    while (linkIterator.hasNext()) {
			Element teamElement = ((Element)linkIterator.next());
			
			if (!teamElement.text().contains("Page Top")) {
				team newTeam = new team(teamElement.attr("href").replace("#", ""), teamElement.text());
				teams.add(newTeam);
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
	
	public ArrayList<TeamPlayer> getTeamPlayers(String teamName) {
		ArrayList<TeamPlayer> players = new ArrayList<TeamPlayer>();
		
		Element teamDiv = getDocument().getElementById("STHS_JS_Team_" + teamName);
		String[] line = teamDiv.outerHtml().split("\n");
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
						p.setHealth(line[i].substring(41,47).trim().replace(",","."));
					}
					else
					{
						p.setPosition(line[i].substring(30,40).trim());
						p.setOverall(line[i].substring(98,100).trim());
						p.setHealth(line[i].substring(34,40).trim().replace(",","."));
					}
					players.add(p);
				}
			}
		}
		
		return players;
	}
	
	public boolean ligueFileExist() {
		File ligueFile = new File(_context.getFilesDir() + "/ligue.txt");
		
		return ligueFile.exists();
	}
}
