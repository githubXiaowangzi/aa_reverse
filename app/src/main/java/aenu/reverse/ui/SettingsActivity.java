/**
 *  Copyright 2018 by aenu
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package aenu.reverse.ui;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.View;
import android.preference.Preference;
import android.preference.EditTextPreference;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingsActivity extends Activity implements Preference.OnPreferenceChangeListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        findViewById(R.id.list_navigation).setVisibility(View.GONE);
        PreferenceFragment fragment=new PreferenceFragment(){
            public void onCreate(Bundle savedInstanceState){
                super.onCreate(savedInstanceState);
                addPreferencesFromResource(R.xml.settings);               
                final String keys[]={
                    "decompile_framework_path",
                    "decompile_libs_path"
                };

                for(String key:keys){
                    EditTextPreference preference=(EditTextPreference)findPreference(key);
                    preference.setOnPreferenceChangeListener(SettingsActivity.this);
                    onPreferenceChange(preference,preference.getText());
                }     
            }
        };
        
        getFragmentManager()
            .beginTransaction()
            .replace(android.R.id.content,fragment)
            .commit(); 
    }
    
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue)
    {
        preference.setSummary((String)newValue);
        return true;
    }
}
