package com.stormcloud.weatherforecast;

import com.stormcloud.main.R;

import Utils.Globals;
import Utils.Helper;
import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;

import android.content.Intent;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class WeatherTabMainActivity extends TabActivity {

	private TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_host);
	    tabHost = super.getTabHost();
		
		Intent snowIntent = new Intent(this, SnowConditionsMainActivity.class);

		Intent alpineIntent = new Intent(this, AlpineWeatherMainActivity.class);
		Bundle alpineBundle = new Bundle();
		//alpineBundle.putBoolean("ISALPINE", true);
		//alpineIntent.putExtras(alpineBundle);

		Intent valleyIntent = new Intent(this, ValleyWeatherMainActivity.class);
		Bundle valleyBundle = new Bundle();
	//	valleyBundle.putBoolean("ISALPINE", false);
	//	valleyIntent.putExtras(valleyBundle);

		TabSpec spec1 = tabHost.newTabSpec("Snow Report");
		spec1.setContent(snowIntent);
		spec1.setIndicator("Snow Report");

		TabSpec spec2 = tabHost.newTabSpec("Alpine");
		spec2.setIndicator("Alpine");
		spec2.setContent(alpineIntent);

		TabSpec spec3 = tabHost.newTabSpec("Valley");
		spec3.setIndicator("Valley");
		spec3.setContent(valleyIntent);

		tabHost.addTab(spec1);
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

		Activity MyActivity = this.getCurrentActivity();
		//if (item.getItemId() == R.id.refresh) {

			if (ValleyWeatherMainActivity.class.isInstance(MyActivity) == true) {
				((ValleyWeatherMainActivity) MyActivity).Refresh();
			}
			else if (AlpineWeatherMainActivity.class.isInstance(MyActivity) == true) {
				((AlpineWeatherMainActivity) MyActivity).Refresh();
			}
			else{
				((SnowConditionsMainActivity) MyActivity).Refresh();}
		//}
		return super.onOptionsItemSelected(item);

	}
}
