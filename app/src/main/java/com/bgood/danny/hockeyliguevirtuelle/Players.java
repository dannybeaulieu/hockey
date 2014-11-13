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
import com.bgood.danny.hockeyliguevirtuelle.Adaptor.*;
import java.io.*;
import android.preference.*;

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
		Spinner spinner = (Spinner) findViewById(R.id.activityplayersSpinner1);
		ListView playerList = (ListView)findViewById(R.id.activityplayersList);
		
		playerList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				TeamPlayer player = (TeamPlayer) a.getItemAtPosition(position);
				Intent intent = new Intent(v.getContext(), PlayerDetail.class);
				intent.putExtra("com.bgood.danny.hockeyliguevirtuelle", player);
				startActivity(intent);
			}
		});
		
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
					Toast.makeText(getBaseContext(), arg0.getSelectedItem().toString(),
								   Toast.LENGTH_LONG).show();         
								   
					team selectedTeam = (team)(arg0.getSelectedItem());
					ListView playerList = (ListView)findViewById(R.id.activityplayersList);
				
					PlayerArrayAdapter dataAdapter = new PlayerArrayAdapter(Players.this, 
																			provider.getTeamPlayers(selectedTeam.getKey(), selectedTeam.getFarmName()));						
					dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
					playerList.setAdapter(dataAdapter);
				}
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub                  
				}
			});
	
		if (!provider.ligueFileExist()) {
			provider.UpdateContent();
		}	
		else {
			bindData();
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.players, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_setting) {
            Intent i = new Intent(this, UserSettingActivity.class);
			startActivity(i);
        }
        if (item.getItemId() == R.id.action_update) {
            provider.UpdateContent();
        }
		if (item.getItemId() == R.id.action_reset) {
            provider.ResetFile();
			Toast.makeText(getBaseContext(), "File reset, please update.",
						   Toast.LENGTH_LONG).show();
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
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		teamSpinner.setSelection(getItemPosition(dataAdapter, prefs.getString("prefDefaultTeam", "default choice")), true);
	}
	
	private int getItemPosition(ArrayAdapter<team> adapter, String name) {
		int index = 0;
		for (int i = 0; i < adapter.getCount(); i++) {
			if (((team)adapter.getItem(i)).getName().equals(name)) {
				index = i;
				break;
			}
		}
		
		return index;
	}
}
