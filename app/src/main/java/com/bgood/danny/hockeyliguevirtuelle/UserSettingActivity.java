package com.bgood.danny.hockeyliguevirtuelle;
import android.preference.*;
import android.os.*;

import com.bgood.danny.hockeyliguevirtuelle.DataModel.Team;

import java.util.ArrayList;

public class UserSettingActivity extends PreferenceActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new Prefs1Fragment()).commit();
    }

    public static class Prefs1Fragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            PreferenceManager.setDefaultValues(getActivity(),
                    R.xml.settings, false);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.settings);
            WebContentProvider provider = new WebContentProvider(getActivity(), null, null);
            ListPreference prefs = new ListPreference(getActivity());

            ArrayList<String> teams = new ArrayList<String>();
            for (Team t : provider.getTeams()) {
                teams.add(t.getName());
            }
            prefs.setDefaultValue(teams.get(0));
            prefs.setEntries(teams.toArray(new String[teams.size()]));
            prefs.setEntryValues(teams.toArray(new String[teams.size()]));
            prefs.setSummary(prefs.getEntry());
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            preference.setSummary((String) newValue);
            return true;
        }
    }
}
