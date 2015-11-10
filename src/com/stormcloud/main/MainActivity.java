package com.stormcloud.main;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
//import com.google.android.gms.samples.ads.ToastAdListener;
import com.stormcloud.main.R;
import com.stormcloud.weatherforecast.AlpineWeatherMainActivity;
import com.stormcloud.weatherforecast.SnowConditionsMainActivity;
import com.stormcloud.weatherforecast.ValleyWeatherMainActivity;

import Utils.ToastAdListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	  private AdView mAdView;

	   
	
	 //Ad unit ID: ca-app-pub-4023125678753147/2739487118
//	 Ad unit name: Home Banner 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

        mAdView = new AdView(this);
        mAdView.setAdUnitId(getResources().getString(R.string.ad_unit_id));
        mAdView.setAdSize(AdSize.BANNER);
       
      //  mAdView.setAdListener(new ToastAdListener(this));
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearmain);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(800, 150);
        layout.addView(mAdView, params);
        mAdView.loadAd(new AdRequest.Builder().build());
		// Button btnWeatherForecast = (Button)
		// findViewById(R.id.btnWeatherForecast);

		// btnWeatherForecast.setOnClickListener(OnClickListerner)
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		//MenuItem item = menu.findItem(R.id.refresh);
	//	item.setVisible(false);
		
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		
		if (item.getItemId() == R.id.action_about) {
			Intent intent = new Intent(this,
					com.stormcloud.settings.SettingsAboutActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);

	}
	public void LoadWeatherForecast(View view) {

		Intent intent = new Intent(this,
				com.stormcloud.weatherforecast.WeatherTabMainActivity.class);
		startActivity(intent);
	}

	public void LoadLiftStatus(View view) {

		Intent intent = new Intent(this,
				com.stormcloud.liftstatus.LiftTabMainActivity.class);
		
		//Intent intent = new Intent(this,
	///			com.stormcloud.webcam.WebcamMainActivity.class);
		startActivity(intent);
	}
	public void LoadAvalanche(View view) {

		Intent intent = new Intent(this,
				com.stormcloud.avalanche.AvalancheMainActivity.class);
		startActivity(intent);
	}
//	public void LoadSettings(View view) {

	//	Intent intent = new Intent(this,
	//			com.stormcloud.settings.SettingsAboutActivity.class);
	//	startActivity(intent);
	//}
	public void LoadTrails(View view) {

		Intent intent = new Intent(this,
				com.stormcloud.trails.TrailsMainActivity.class);
		startActivity(intent);
	}
}
