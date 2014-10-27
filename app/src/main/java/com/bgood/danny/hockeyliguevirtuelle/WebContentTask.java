package com.bgood.danny.hockeyliguevirtuelle;

import android.os.*;
import org.jsoup.*;
import java.io.*;
import org.jsoup.nodes.*;
import android.content.*;
import android.app.*;

/**
 * Created by Danny on 2014-10-15.
 */
public class WebContentTask implements Runnable {
    private String urlPlayers = "http://www.lhvqr.com/saison%202014-2015/LHVQ2014-15-ProTeamRoster.html";
    private String content = "";
	private Global _global;
	
	public WebContentTask(Global global) {
		_global = global;
	}

    public String getContent() {
        return content;
    }

    @Override
    public void run() {
        StringBuilder response_str = new StringBuilder();
        content = "";
        try
        {
            Document doc = Jsoup.connect(urlPlayers).get();
            response_str.append(doc.html());
        }
        catch (IOException e)
        {
            response_str.append(FormatException.FormatExceptionMessage(e));
        }
        catch (Exception e)
        {
            response_str.append(FormatException.FormatExceptionMessage(e));
        }
		//_global.getProgressDialog().dismiss();

        content = response_str.toString();
    }
}
