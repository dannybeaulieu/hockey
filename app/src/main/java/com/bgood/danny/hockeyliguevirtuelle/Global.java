package com.bgood.danny.hockeyliguevirtuelle;
import android.app.*;
import com.bgood.danny.hockeyliguevirtuelle.DataModel.*;

public class Global extends Application
{
	private ProgressDialog progress = null;
	private Players playersActivity = null;
	private TeamPlayer leftPlayer = null;
	private TeamPlayer rightPlayer = null;

	public void setRightPlayer(TeamPlayer rightPlayer)
	{
		this.rightPlayer = rightPlayer;
	}

	public TeamPlayer getRightPlayer()
	{
		return rightPlayer;
	}

	public void setLeftPlayer(TeamPlayer leftPlayer)
	{
		this.leftPlayer = leftPlayer;
	}

	public TeamPlayer getLeftPlayer()
	{
		return leftPlayer;
	}
	
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
