package com.bgood.danny.hockeyliguevirtuelle;
import android.preference.*;
import android.os.*;

import com.bgood.danny.hockeyliguevirtuelle.DataModel.Team;

import java.util.ArrayList;
import android.view.*;
import android.content.*;
import android.widget.*;
import android.widget.ShareActionProvider.*;

public class UserSettingActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {	
		requestWindowFeature(Window.FEATURE_ACTION_BAR);  
		super.onCreate(savedInstanceState);
		
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new Prefs1Fragment()).commit();
    }

    public static class Prefs1Fragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);        

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.settings);
            WebContentProvider provider = new WebContentProvider(getActivity(), null, null);
            ListPreference prefs = (ListPreference)findPreference("prefDefaultTeam");

            ArrayList<String> teams = new ArrayList<String>();
            for (Team t : provider.getTeams()) {
                teams.add(t.getName());
            }
			
            prefs.setEntries(teams.toArray(new String[teams.size()]));
            prefs.setEntryValues(teams.toArray(new String[teams.size()]));
			prefs.setDefaultValue(teams.get(0));
			prefs.setSummary(prefs.getEntry());
        }
		
		@Override
		public void onResume() {
			super.onResume();

			// Set up a listener whenever a key changes
			getPreferenceScreen().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);
		}

		@Override
		public void onPause() {
			super.onPause();

			// Unregister the listener whenever a key changes
			getPreferenceScreen().getSharedPreferences()
				.unregisterOnSharedPreferenceChangeListener(this);
		}
		
		@Override
		public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
											  String key) {
			ListPreference teams = (ListPreference)findPreference(key);
			teams.setSummary(teams.getEntry());
		}
    }
}
