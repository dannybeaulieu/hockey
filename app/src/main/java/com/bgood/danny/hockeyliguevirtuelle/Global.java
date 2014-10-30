package com.bgood.danny.hockeyliguevirtuelle;
import android.app.*;

public class Global extends Application
{
	private ProgressDialog progress = null;
	private Players playersActivity = null;
	
	public void setPlayersActivity(Players players) {
		playersActivity = players;
	}
	
	public ProgressDialog getProgressDialog() {
		if (progress == null) {
			progress = ProgressDialog.show(playersActivity, "Updating", "Please wait...", true, true);
		}
		return progress;
	}
}
