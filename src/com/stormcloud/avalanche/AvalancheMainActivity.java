package com.stormcloud.avalanche;


import org.json.JSONException;

import com.stormcloud.main.R;


import DomainModel.Avalanche;

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

public class AvalancheMainActivity extends Activity {

	private ProgressDialog progress;
	private Avalanche avalanche;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avalanche_main);

		if (!Helper.IsNetworkAvailable(this)) {
			Helper.ShowNoNetworkMessage(this);
			
			return;
		}
		if (avalanche == null) {
			Refresh();
		} else
			setAdapter();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.refresh, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		//if (item.getItemId() == R.id.refresh) {

		Refresh();
		//return true;

		//}

		return super.onOptionsItemSelected(item);

	}
	public void Refresh(){
		//new DummyAsyncTask().execute((Void[]) null);
		progress = Helper.ShowLoader(this);
		progress.show();
		JSONAvalancheTask task = new JSONAvalancheTask();

		task.execute("");
	}
	public void setAdapter() {

		AvalancheAdapter lsa = new AvalancheAdapter(avalanche.getAvalancheDay(), this);
		ListView lv = (ListView) findViewById(R.id.avalancheDayList);
		lv.setSelector(android.R.color.transparent);
		TextView txt = (TextView) findViewById(R.id.dateAvalancheUpdated);
		TextView txtAdvisory = (TextView) findViewById(R.id.avalancheActivity);
		TextView txtSnowpack = (TextView) findViewById(R.id.avalancheSnowpack);
		TextView txtTravel = (TextView) findViewById(R.id.avalancheTravel);
		
		if (avalanche == null) {
			txt.setText("Lift status currently unavailable.");
		} else {
			
			txt.setText("Last updated: " + avalanche.getDateString());
			txtAdvisory.setText(avalanche.getActivity());
			txtSnowpack.setText(avalanche.getSnowpack());
			txtTravel.setText(avalanche.getTravel());
		}

		lv.setAdapter(lsa);
	}

	public void BuildAvalanche(String data) {
		try {

			avalanche = Avalanche.getAvalanche(data);
		
			setAdapter();
			

		} catch (NullPointerException e) {

			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		progress.dismiss();

	}

	private class JSONAvalancheTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {

			String data = JSON.GetJson
					.requestWebService(Globals.AVALANCHE_URL);
			return data;

		}

		@Override
		protected void onPostExecute(String data) {
			super.onPostExecute(data);

			BuildAvalanche(data);

		}

	}
}
