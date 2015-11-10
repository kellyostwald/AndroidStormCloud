package com.stormcloud.settings;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.stormcloud.main.R;

public class SettingsTabMainActivity extends TabActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_host);
		TabHost tabHost = super.getTabHost();

	
		Intent aboutIntent = new Intent(this, SettingsAboutActivity.class);
		
		Intent privacyIntent = new Intent(this, SettingsPrivacyActivity.class);
		Intent contactIntent = new Intent(this, SettingsContactActivity.class);
		
		
		TabSpec spec2 = tabHost.newTabSpec("About");
		spec2.setIndicator("About");
		spec2.setContent(aboutIntent);

		TabSpec spec3 = tabHost.newTabSpec("Privacy");
		spec3.setIndicator("Privacy");
		spec3.setContent(privacyIntent);


		TabSpec spec4 = tabHost.newTabSpec("Contact");
		spec4.setIndicator("Contact");
		spec4.setContent(contactIntent);

		
		tabHost.addTab(spec2);
		tabHost.addTab(spec3);
tabHost.addTab(spec4);
	}


}
