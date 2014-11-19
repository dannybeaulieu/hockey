package com.bgood.danny.hockeyliguevirtuelle;

import android.widget.*;
import android.content.*;
import android.app.*;
import android.os.*;
import com.bgood.danny.hockeyliguevirtuelle.DataModel.*;
import android.view.*;
import android.widget.AdapterView.*;
import com.bgood.danny.hockeyliguevirtuelle.Adaptor.*;
import android.preference.*;
import android.view.ContextMenu.*;
import java.util.*;
import android.content.res.*;

public class Players extends Activity {
	private WebContentProvider provider = null;
	private PlayerArrayAdapter dataAdapter;
	private ArrayList<TeamPlayer> players;
	
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
		
		ImageButton menuPlayer = (ImageButton)findViewById(R.id.menuPlayerBack);

		menuPlayer.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					Intent intent = new Intent(v.getContext(), Main.class);
					startActivity(intent);
				}
			});
		
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
		
		registerForContextMenu(playerList);
		
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {	   
					team selectedTeam = (team)(arg0.getSelectedItem());
					ListView playerList = (ListView)findViewById(R.id.activityplayersList);
					
					players = provider.getTeamPlayers(selectedTeam.getKey(), selectedTeam.getFarmName());
					dataAdapter = new PlayerArrayAdapter(Players.this, players);									
					playerList.setAdapter(dataAdapter);
				}
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub                  
				}
			});
			
		ImageButton refresh = (ImageButton)findViewById(R.id.refreshBtn);
		ImageButton setting = (ImageButton)findViewById(R.id.settingBtn);
		
		refresh.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				provider.UpdateContent();
			}
		});
		
		setting.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(v.getContext(), UserSettingActivity.class);
				startActivity(i);
			}
		});		
		
		Resources res = getResources();
		ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, res.getStringArray(R.array.positions));
		Spinner pos = ((Spinner)findViewById(R.id.activityplayersPosition));
		pos.setAdapter(adapter);
		
		pos.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {	   
					dataAdapter.getFilter().filter((String)arg0.getSelectedItem());
				}
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub                  
				}
			});
			
		if (!provider.leagueFileExist()) {
			provider.UpdateContent();
		}	
		else {
			bindData();
		}
    }
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
		if (v.getId() == R.id.activityplayersList) {
			Global global = ((Global)getApplicationContext());
			
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.long_click_player, menu);
			
			if (global.getLeftPlayer() != null) {
				menu.findItem(R.id.rightPlayer).setEnabled(true);
			}
			if (global.getRightPlayer() != null) {
				menu.findItem(R.id.rightPlayer).setEnabled(false);
				global.setLeftPlayer(null);
				global.setRightPlayer(null);
			}
		}
	}

	@Override
    public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.leftPlayer) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
			Spinner spinner = (Spinner) findViewById(R.id.activityplayersSpinner1);
			
			team selTeam = (team) spinner.getSelectedItem();
			ArrayList<TeamPlayer> players = provider.getTeamPlayers(selTeam.getKey(), selTeam.getFarmName());
			
			Global global = ((Global)getApplicationContext());
			global.setLeftPlayer(players.get(info.position));
			global.setRightPlayer(null);
        }
		if (item.getItemId() == R.id.rightPlayer) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
			Spinner spinner = (Spinner) findViewById(R.id.activityplayersSpinner1);

			team selTeam = (team) spinner.getSelectedItem();
			ArrayList<TeamPlayer> players = provider.getTeamPlayers(selTeam.getKey(), selTeam.getFarmName());

			Global global = ((Global)getApplicationContext());
			
			if ((global.getLeftPlayer().getPosition().equals("G") &&
				players.get(info.position).getPosition().equals("G")) ||
				(!global.getLeftPlayer().getPosition().equals("G") &&
				!players.get(info.position).getPosition().equals("G"))) {
				
				global.setRightPlayer(players.get(info.position));

				Intent intent = new Intent(this, ComparePlayers.class);
				startActivity(intent);
			}
			else {
				Toast.makeText(this, "Must select player of same type", Toast.LENGTH_LONG).show();
			}
        }
        
        return true;
    }

	private void bindData() {
		TextView date = (TextView)findViewById(R.id.activityplayersDate);
		date.setText(provider.getDate());
		Spinner teamSpinner = (Spinner)findViewById(R.id.activityplayersSpinner1);
		ArrayAdapter<team> dataAdapter = new ArrayAdapter<team>(this,
																R.layout.spinner_team_item, 
																provider.getTeams());
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		teamSpinner.setAdapter(dataAdapter);
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		teamSpinner.setSelection(getItemPosition(dataAdapter, prefs.getString("prefDefaultTeam", "default choice")), true);
	}
	
	private int getItemPosition(ArrayAdapter<team> adapter, String name) {
		int index = 0;
		for (int i = 0; i < adapter.getCount(); i++) {
			if (adapter.getItem(i).getName().equals(name)) {
				index = i;
				break;
			}
		}
		
		return index;
	}
}
