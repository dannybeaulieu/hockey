package com.bgood.danny.hockeyliguevirtuelle;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jsoup.nodes.*;
import java.io.*;
import org.jsoup.*;

/**
 * Created by Danny on 2014-10-15.
 */
public class WebContentProvider {
	
	private static Document doc = null;
	private static String baseUrl = "http://www.lhvqr.com/saison%202014-2015";
	
	private static Document getDocument(Context context) {
		if (doc == null) {
			try {
				InputStream inputStream = context.openFileInput("ligue.txt");
				doc = Jsoup.parse(inputStream, null, baseUrl);
			}
			catch (IOException e) {
				Log.e("Exception", "File write failed: " + e.toString());
			}
		}
		return doc;
	}
	
    public static void UpdateContent(Context context) {
        WebContentTask task = new WebContentTask();
        Thread t = new Thread(task);
        t.start();

        while (t.getState() != Thread.State.TERMINATED) {
        }

        try {
            FileOutputStream outputStream = context.openFileOutput("ligue.txt", Context.MODE_PRIVATE);
            outputStream.write(task.getContent().getBytes());
            outputStream.close();
            Toast.makeText(context,"Data file updated.",
                    Toast.LENGTH_LONG).show();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
	
	public String[] GetTeams(Context context) {
		String[] teams = new String[] {};
		
		
		return teams;
	}
	
	public static String getContent(Context context) {
		return getDocument(context).html();
	}
}
