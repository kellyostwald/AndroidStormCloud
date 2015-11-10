package com.stormcloud.liftstatus;

import java.util.List;

import org.json.JSONException;

import com.stormcloud.main.R;
import DomainModel.LiftStatus;
import Utils.Globals;
import Utils.Helper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.ListView;
import android.widget.TextView;

public class LiftMainActivity extends Activity {

	private ProgressDialog progress;
	private static List<LiftStatus> liftStatusWhistler;
	private static List<LiftStatus> liftStatusBlackcomb;
	private static boolean IsWhistlerTab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lift_status_main);

		Bundle bundle = getIntent().getExtras();
		IsWhistlerTab = (Boolean) bundle.get("IsWhistlerTab");

		if (!Helper.IsNetworkAvailable(this)) {
			Helper.ShowNoNetworkMessage(this);
			// alert.create().show();
			return;
		}
		if (liftStatusWhistler == null) {
			Refresh();
		} else
			setAdapter();
	}



	public void setAdapter() {

		LiftStatusAdapter lsa;
		if (IsWhistlerTab) {
			lsa = new LiftStatusAdapter(liftStatusWhistler, this);
		} else
			lsa = new LiftStatusAdapter(liftStatusBlackcomb, this);

		ListView lv = (ListView) findViewById(R.id.liftStatusList);
		lv.setSelector(android.R.color.transparent);
		TextView txt = (TextView) findViewById(R.id.dateLiftUpdated);
		if (liftStatusWhistler == null || liftStatusWhistler.isEmpty()) {
			txt.setText("Lift status currently unavailable.");
		} else {
			String date = liftStatusWhistler.get(0).getDateString();
			txt.setText("Last updated: " + date);
		}

		lv.setAdapter(lsa);
	}

	public void BuildLiftStatus(String data) {
		try {

			liftStatusWhistler = LiftStatus.getLiftStatusByMountain(data,
					"WHISTLER");
			liftStatusBlackcomb = LiftStatus.getLiftStatusByMountain(data,
					"BLACKCOMB");
			/*
			 * Collections.sort(liftStatus, new Comparator<Object>() {
			 * 
			 * public int compare(Object o1, Object o2) { LiftStatus p1 =
			 * (LiftStatus) o1; LiftStatus p2 = (LiftStatus) o2; return
			 * p1.getMountain().compareToIgnoreCase( p2.getMountain()); }
			 * 
			 * });
			 */
			setAdapter();

		} catch (NullPointerException e) {

			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		progress.dismiss();

	}


	public void Refresh() {
		progress = Helper.ShowLoader(this);
		progress.show();
		JSONLiftStatusTask task = new JSONLiftStatusTask();

		task.execute("");
		

		
	}
	private class JSONLiftStatusTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {

			String data = JSON.GetJson
					.requestWebService(Globals.LIFT_STATUS_URL);
			return data;

		}

		@Override
		protected void onPostExecute(String data) {
			super.onPostExecute(data);

			BuildLiftStatus(data);

		}

	}

	

}
