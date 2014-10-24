package com.bgood.danny.hockeyliguevirtuelle;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;


public class Players extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
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
			WebContentProvider provider = new WebContentProvider(getBaseContext());
            provider.UpdateContent();
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
        return super.onOptionsItemSelected(item);
    }
}
