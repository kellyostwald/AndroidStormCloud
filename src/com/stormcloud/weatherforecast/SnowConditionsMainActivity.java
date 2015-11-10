package com.stormcloud.weatherforecast;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONException;

import com.stormcloud.main.R;
import com.stormcloud.weatherforecast.WeatherForecastAdapter;

import DomainModel.SnowConditions;
import DomainModel.WeatherForecast;
import Utils.Globals;
import Utils.Helper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;

import android.app.ProgressDialog;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SnowConditionsMainActivity extends Activity {

	private ProgressDialog progress;
	private static SnowConditions snowConditions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.snow_conditions_main);

		if (!Helper.IsNetworkAvailable(this)) {
		Helper.ShowNoNetworkMessage(this);
		//	alert.create().show();
			return;
		}
		if (snowConditions == null) {
			Refresh();
		} else
			setAdapter();
	}
	//@Override
	//public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.main, menu);
		//MenuItem item = menu.findItem(R.id.refresh);

	//	return true;
	//
	//}

	/*@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.id.refresh) {

			//new DummyAsyncTask().execute((Void[]) null);
			progress = Helper.ShowLoader(this);
			progress.show();
			JSONSnowTask task = new JSONSnowTask();
			task.execute("");
			return true;

		}

		return super.onOptionsItemSelected(item);

	}*/
	public void setAdapter() {
		LinearLayout snowConditionsLayout = (LinearLayout)findViewById(R.id.snowCondtionsMainLayout);
		TextView txt7Days = (TextView)findViewById(R.id.last7days);
		TextView txt24Hours = (TextView)findViewById(R.id.last24Hours);
		TextView txt48Hours = (TextView)findViewById(R.id.last48Hours);
		TextView txtSnowbase = (TextView)findViewById(R.id.snowbase);
		TextView txtFreez = (TextView)findViewById(R.id.snowFreezingLevel);
		TextView txtDate = (TextView)findViewById(R.id.dateSnowUpdated);
		TextView txtNewSnow = (TextView)findViewById(R.id.newSnow);
		
		txt7Days.setText(snowConditions.getSnow7Day());
		txt24Hours.setText(snowConditions.getLast24());
		txt48Hours.setText(snowConditions.getLast48());
		txtSnowbase.setText(snowConditions.getBase());
		txtFreez.setText(snowConditions.getFreezingLevel());
		txtDate.setText("Last Updated: "+snowConditions.getDateString());
		txtNewSnow.setText(snowConditions.getNew());
		snowConditionsLayout.setVisibility(1);
	}
	public void Refresh() {
		// TODO Auto-generated method stub
		progress = Helper.ShowLoader(this);
		progress.show();
		JSONSnowTask task = new JSONSnowTask();

		task.execute("");
	}
	public void buildSnowConditons(String data) {
		try {

			snowConditions = SnowConditions.getSnowConditions(data);
		
			setAdapter();
			// Helper.CacheString(data, Globals.WEATHERFORECAST_CACHED_FILENAME,
			// this);

		} catch (NullPointerException e) {

			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		progress.dismiss();

	}

	private class JSONSnowTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {

			String data = JSON.GetJson
					.requestWebService(Globals.SNOW_CONDITIONS_URL);
			return data;

		}

		@Override
		protected void onPostExecute(String data) {
			super.onPostExecute(data);

			buildSnowConditons(data);

		}

	}

	
}
