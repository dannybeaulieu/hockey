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
					_handler.handleMessage(null);
				}
			});
			t.start();
    }
	
	public String[] getTeams() {
		ArrayList<String> teams = new ArrayList<String>();
		
		Elements links = getDocument().getElementsByAttributeValueStarting("href", "#");
		
		ListIterator linkIterator = links.listIterator();
	    while (linkIterator.hasNext()) {
			String team = ((Element)linkIterator.next()).text();
			
			if (!team.contains("Page Top")) {
				teams.add(team);
			}
		}
			
		return teams.toArray(new String[teams.size()]);
	}
	
	public String getContent() {
		return getDocument().html();
	}
	
	public String getDate() {
		Element h4 = getDocument().getElementsByTag("h4").first();
		
		return h4.text();
	}
	
	public boolean ligueFileExist() {
		File ligueFile = new File(_context.getFilesDir() + "/ligue.txt");
		
		return ligueFile.exists();
	}
}
