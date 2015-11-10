package com.stormcloud.liftstatus;

import com.stormcloud.main.R;

import Utils.Globals;
import Utils.Helper;
import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;

import android.content.Intent;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class LiftTabMainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_host);
		TabHost tabHost = super.getTabHost();

	
		Intent whistlerIntent = new Intent(this, LiftMainActivity.class);
		Bundle whistlerBundle = new Bundle();
		whistlerBundle.putBoolean("IsWhistlerTab", true);
		whistlerIntent.putExtras(whistlerBundle);

		Intent blackcombIntent = new Intent(this, LiftMainActivity.class);
		Bundle blackcombBundle = new Bundle();
		blackcombBundle.putBoolean("IsWhistlerTab", false);
		blackcombIntent.putExtras(blackcombBundle);

		
		TabSpec spec2 = tabHost.newTabSpec("Whistler");
		spec2.setIndicator("Whistler");
		spec2.setContent(whistlerIntent);

		TabSpec spec3 = tabHost.newTabSpec("Blackcomb");
		spec3.setIndicator("Blackcomb");
		spec3.setContent(blackcombIntent);

		
		tabHost.addTab(spec2);
		tabHost.addTab(spec3);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.refresh, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		//Activity MyActivity = this.getCurrentActivity();
		//if (item.getItemId() == R.id.refresh) {

		
			((LiftMainActivity) this.getCurrentActivity()).Refresh();
		
		//}
		return super.onOptionsItemSelected(item);

	}
}
