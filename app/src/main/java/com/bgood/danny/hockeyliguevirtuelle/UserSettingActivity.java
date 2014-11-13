package com.bgood.danny.hockeyliguevirtuelle;
import android.preference.*;
import android.os.*;
import com.bgood.danny.hockeyliguevirtuelle.DataModel.*;
import java.util.*;

public class UserSettingActivity extends PreferenceActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.settings);
		
		final ListPreference prefs = (ListPreference)findPreference("prefDefaultTeam");
		
		setListPreference(prefs);
	}
	
	private void setListPreference(ListPreference prefs) {
		WebContentProvider provider = new WebContentProvider(this, null, null);
		ArrayList<String> teams = new ArrayList<String>();
		
		for (team t : provider.getTeams()) {
			teams.add(t.getName());
		}
		prefs.setDefaultValue(teams.get(0));		
		prefs.setEntries(teams.toArray(new String[teams.size()]));
		prefs.setEntryValues(teams.toArray(new String[teams.size()]));
	}
}
