package DomainModel;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LiftStatus {

	private Date mDate;
	private String mLiftName;
	private String mMountain;
	private String mStatus;

	public Date getDate() {
		return mDate;
	}

	public String getDateString() {
		return Utils.DateTimeHelper.getLongDateString(mDate);
	}

	private void setDate(String x) {
		long d = Long.parseLong(x);
		this.mDate = Utils.DateTimeHelper.getDate(d);
	}

	public String getLiftName() {
		return mLiftName;
	}

	private void setLiftName(String x) {
		this.mLiftName = x;
	}

	public String getStatus() {
		return mStatus;
	}

	private void setStatus(String x) {
		this.mStatus = x;
	}

	public String getMountain() {
		return mMountain;
	}

	private void setMountain(String x) {
		this.mMountain = x;
	}

	public static List<LiftStatus> getLiftStatusByMountain(String data, String mountain)
			throws JSONException {

		List<LiftStatus> liftStatus = new ArrayList<LiftStatus>();
		JSONArray jArray = new JSONArray(data);

		for (int i = 0; i < jArray.length(); i++) {

			JSONObject jObj = new JSONObject(jArray.get(i).toString());
			String mMountain = JSON.JSONHelper.getString("Mountain", jObj);
			if (mMountain.equalsIgnoreCase(mountain)) {
				LiftStatus l = new LiftStatus();

				l.setLiftName(JSON.JSONHelper.getString("LiftName", jObj));
				l.setStatus(JSON.JSONHelper.getString("Status", jObj));
				l.setMountain(mMountain);
				String newString = JSON.JSONHelper.getString("TimeStamp", jObj)
						.replace("/Date(", "");
				String newString2 = newString.replace(")/", "");
				l.setDate(newString2);

				liftStatus.add(l);
			}
		}

		return liftStatus;
	}

}
