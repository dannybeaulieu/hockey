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

/**
 * Created by Danny on 2014-10-15.
 */
public class WebContentProvider {
	
	private Document doc = null;
	private String baseUrl = "http://www.lhvqr.com/saison%202014-2015";
	private Context _context;
	
	public WebContentProvider(Context context) {
		_context = context;
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
        WebContentTask task = new WebContentTask();
        Thread t = new Thread(task);
        t.start();

        while (t.getState() != Thread.State.TERMINATED) {
        }

        try {
            FileOutputStream outputStream = _context.openFileOutput("ligue.txt", Context.MODE_PRIVATE);
            outputStream.write(task.getContent().getBytes());
            outputStream.close();
            Toast.makeText(_context,"Data file updated.",
                    Toast.LENGTH_LONG).show();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
	
	public String[] getTeams() {
		ArrayList<String> teams = new ArrayList<String>();
		
		Elements links = getDocument().getElementsByAttributeValueStarting("href", "#");
		
		int index = 0;
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
}
