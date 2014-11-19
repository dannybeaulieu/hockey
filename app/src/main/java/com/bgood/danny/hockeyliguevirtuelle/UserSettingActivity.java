package com.bgood.danny.hockeyliguevirtuelle;
import android.preference.*;
import android.os.*;

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
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            preference.setSummary((String) newValue);
            return true;
        }
    }
}
