package com.stormcloud.weatherforecast;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONException;

import com.stormcloud.main.R;
import com.stormcloud.weatherforecast.WeatherForecastAdapter;

import DomainModel.WeatherForecast;
import Utils.DateTimeHelper;
import Utils.Globals;
import Utils.Helper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;

import android.app.ProgressDialog;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class ValleyWeatherMainActivity extends Activity {

	private ProgressDialog progress;
	
	private static List<WeatherForecast> valleyWeather;
	private static boolean ISALPINE=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weatherforecast_main);

		
		if (!Helper.IsNetworkAvailable(this)) {
			Helper.ShowNoNetworkMessage(this);
			// alert.create().show();
			return;
		}
		if ( valleyWeather == null) {
			Refresh();
		}

		else
			setAdapter();
	}


/*
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if (item.getItemId() == R.id.refresh) {

			// new DummyAsyncTask().execute((Void[]) null);
			progress = Helper.ShowLoader(this);
			progress.show();
			JSONWeatherTask task = new JSONWeatherTask();
			task.execute("");
			return true;

		}

		return super.onOptionsItemSelected(item);

	}*/

	public void setAdapter() {
		ListView lv = (ListView) findViewById(R.id.weatherList);
		TextView txtError = (TextView) findViewById(R.id.weatherNoData);
		
			if (valleyWeather == null || valleyWeather.size() == 0) {
				txtError.setText("There are no current valley forecasts available. Please check again later.");
			}
			WeatherForecastAdapter wfa = new WeatherForecastAdapter(
					valleyWeather, this, ISALPINE);
			lv.setAdapter(wfa);
			lv.setSelector(android.R.color.transparent);
			txtError.setHeight(0);
		

	}

	public void buildWeather(String data) {
		try {
			
				valleyWeather = WeatherForecast.getWeather(data);

			
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

	private class JSONWeatherTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			String data;
			
				data = JSON.GetJson
						.requestWebService(Globals.VALLEY_WEATHER_FORECAST_URL
								+ DateTimeHelper.getCurrentDateString() + "/6");
			
			return data;

		}

		@Override
		protected void onPostExecute(String data) {
			super.onPostExecute(data);

			buildWeather(data);

		}

	}

	public void Refresh() {
		progress = Helper.ShowLoader(this);
		progress.show();
		JSONWeatherTask task = new JSONWeatherTask();

		task.execute("");
		
	}
}
