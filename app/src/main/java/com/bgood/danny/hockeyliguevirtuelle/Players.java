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
import android.view.*;
import android.widget.AdapterView.*;


public class Players extends Activity {
	WebContentProvider provider = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
		Global global = ((Global)getApplicationContext());
		global.setPlayersActivity(Players.this);
		
		Spinner spinner = (Spinner) findViewById(R.id.activityplayersSpinner1);
		
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
					Toast.makeText(getBaseContext(), arg0.getSelectedItem().toString(),
								   Toast.LENGTH_LONG).show();         
								   
					team selectedTeam = (team)(arg0.getSelectedItem());
					ListView playerList = (ListView)findViewById(R.id.activityplayersList);
					
					ArrayAdapter<TeamPlayer> dataAdapter = new ArrayAdapter<TeamPlayer>(Players.this,
																			android.R.layout.simple_list_item_1, 
																			provider.getTeamPlayers(selectedTeam.getKey()));
																			
					dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
					playerList.setAdapter(dataAdapter);
				}
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub                  
				}
			});
		
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
