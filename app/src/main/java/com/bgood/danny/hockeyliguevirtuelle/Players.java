package com.bgood.danny.hockeyliguevirtuelle;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.content.*;
import android.app.*;


public class Players extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
		//Global global = ((Global)getApplicationContext());
		WebContentProvider provider = new WebContentProvider(getBaseContext(), null);
	
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_update) {
			WebContentProvider provider = new WebContentProvider(getBaseContext(), null);
            provider.UpdateContent();
			bindData();
        }
        return super.onOptionsItemSelected(item);
    }
	
	private void bindData() {
		WebContentProvider provider = new WebContentProvider(getBaseContext(), null);
		
		TextView content = (TextView)findViewById(R.id.activityplayersContent);
		content.setText(provider.getContent());
		TextView date = (TextView)findViewById(R.id.activityplayersDate);
		date.setText(provider.getDate());
		Spinner team = (Spinner)findViewById(R.id.activityplayersTeam);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
																	android.R.layout.simple_spinner_item, provider.getTeams());
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		team.setAdapter(dataAdapter);
	}
}
