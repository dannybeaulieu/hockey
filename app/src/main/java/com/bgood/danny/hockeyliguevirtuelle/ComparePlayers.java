package com.bgood.danny.hockeyliguevirtuelle;
import android.app.*;
import android.os.*;
import android.widget.*;
import com.bgood.danny.hockeyliguevirtuelle.DataModel.*;

public class ComparePlayers extends Activity
{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.compare_players);
		Global global = ((Global)getApplicationContext());
		
		TeamPlayer lplayer = global.getLeftPlayer();
		((TextView)findViewById(R.id.lcondition)).setText(lplayer.getHealth() + "%");
		((TextView)findViewById(R.id.linfo)).setText(lplayer.getInfo());
		((TextView)findViewById(R.id.lname)).setText(lplayer.getName());
		((TextView)findViewById(R.id.lposition)).setText(lplayer.getPosition());
		((TextView)findViewById(R.id.loverall)).setText(lplayer.getOverall());
		
		TeamPlayer rplayer = global.getRightPlayer();
		((TextView)findViewById(R.id.rcondition)).setText(rplayer.getHealth() + "%");
		((TextView)findViewById(R.id.rinfo)).setText(rplayer.getInfo());
		((TextView)findViewById(R.id.rname)).setText(rplayer.getName());
		((TextView)findViewById(R.id.rposition)).setText(rplayer.getPosition());
		((TextView)findViewById(R.id.roverall)).setText(rplayer.getOverall());
		
		double con = 0;
		if (lplayer.getHealth().length() > 0) {
			con = Double.valueOf(lplayer.getHealth());
		}

		if (con == 100.0) {
			((TextView)findViewById(R.id.lcondition)).setBackgroundColor(0xFF99FFCC);
		} else if (con < 100.0 && lplayer.getInjiury().length() == 0) {
			((TextView)findViewById(R.id.lcondition)).setBackgroundColor(0xFFFFFF99);
		} else {
			((TextView)findViewById(R.id.lcondition)).setBackgroundColor(0xFFFF3333);
		} 
		
		if (rplayer.getHealth().length() > 0) {
			con = Double.valueOf(rplayer.getHealth());
		}

		if (con == 100.0) {
			((TextView)findViewById(R.id.rcondition)).setBackgroundColor(0xFF99FFCC);
		} else if (con < 100.0 && rplayer.getInjiury().length() == 0) {
			((TextView)findViewById(R.id.rcondition)).setBackgroundColor(0xFFFFFF99);
		} else {
			((TextView)findViewById(R.id.rcondition)).setBackgroundColor(0xFFFF3333);
		} 
	}
}
