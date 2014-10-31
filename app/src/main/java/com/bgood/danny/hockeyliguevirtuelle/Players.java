package com.bgood.danny.hockeyliguevirtuelle;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.content.*;
import android.app.*;
import android.os.*;
import com.bgood.danny.hockeyliguevirtuelle.DataModel.*;


public class Players extends Activity {
	WebContentProvider provider = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
		Global global = ((Global)getApplicationContext());
		global.setPlayersActivity(Players.this);
		
		Handler mHandler = new Handler() {
			@Override
			public void handleMessage(Message message) {
				bindData();
			}
		};
		
		provider = new WebContentProvider(getBaseContext(), global, mHandler);
	
		if (!provider.ligueFileExist()) {
			provider.UpdateContent();
			Toast updateMsg = Toast.makeText(getBaseContext(), "updating...", Toast.LENGTH_LONG);
			updateMsg.show();
		}	
		bindData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.players, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_update) {
            provider.UpdateContent();
        }
        return super.onOptionsItemSelected(item);
    }
	
	private void bindData() {
		TextView date = (TextView)findViewById(R.id.activityplayersDate);
		date.setText(provider.getDate());
		Spinner teamSpinner = (Spinner)findViewById(R.id.activityplayersSpinner1);
		ArrayAdapter<team> dataAdapter = new ArrayAdapter<team>(this,
																android.R.layout.simple_spinner_item, 
																provider.getTeams());
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		teamSpinner.setAdapter(dataAdapter);
	}
}
