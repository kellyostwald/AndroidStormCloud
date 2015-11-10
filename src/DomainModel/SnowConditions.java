package DomainModel;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SnowConditions {

	private Date mDate;
	private String mNew;
	private String mLast24;
	private String mBase;
	private String mTotal;
	private String mLast48;
	private String mSnow7Day;
	private String mFreezingLevel;

	public Date getDate() {
		return mDate;
	}

	public String getDateString() {
		return Utils.DateTimeHelper.getLongDateString(mDate);
	}

	private void setDate(String x) {
		//long d = Long.parseLong(x);
		this.mDate = Utils.DateTimeHelper.getDateWithString(x);
	}

	public String getNew() {
		return mNew;
	}

	private void setNew(String x) {
		this.mNew = x;
	}

	public String getLast24() {
		return mLast24;
	}

	private void setLast24(String x) {
		this.mLast24 = x;
	}

	public String getBase() {
		return mBase;
	}

	private void setBase(String x) {
		this.mBase = x;
	}

	public String getTotal() {
		return mTotal;
	}

	private void setTotal(String x) {
		this.mTotal = x;
	}

	public String getLast48() {
		return mLast48;
	}

	private void setLast48(String x) {
		this.mLast48 = x;
	}

	public String getSnow7Day() {
		return mSnow7Day;
	}

	private void setSnow7Day(String x) {
		this.mSnow7Day = x;
	}

	public String getFreezingLevel() {
		return mFreezingLevel;
	}

	private void setFreezingLevel(String x) {
		this.mFreezingLevel = x;
	}

	public static SnowConditions getSnowConditions(String data)
			throws JSONException {

		
		JSONObject jObj = new JSONObject(data);
		SnowConditions sc = new SnowConditions();
		sc.setNew(JSON.JSONHelper.getString("New", jObj));
		sc.setFreezingLevel(JSON.JSONHelper.getString("FreezingLevel", jObj));
		sc.setLast24(JSON.JSONHelper.getString("Last24", jObj));
		sc.setBase(JSON.JSONHelper.getString("Base", jObj));
		sc.setTotal(JSON.JSONHelper.getString("Total", jObj));
		sc.setLast48(JSON.JSONHelper.getString("Last48", jObj));
		sc.setSnow7Day(JSON.JSONHelper.getString("Snow7Day", jObj));

		sc.setDate(JSON.JSONHelper.getString("Date", jObj));

		return sc;
	}



}
