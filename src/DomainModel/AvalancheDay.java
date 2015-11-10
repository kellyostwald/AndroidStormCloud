package DomainModel;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class AvalancheDay {

	private String mName;
	private String mAlpine;
	private String mTreeline;
	private String mBelowTreeline;
	private Date mDate;
	
	public String getName() {
		return mName;
	}

	private void setName(String x) {
		this.mName = x;
	}

	public Date getDate() {
		return mDate;
	}

	public String getDateString() {
		return Utils.DateTimeHelper.getWeekdayDateString(mDate);
	}

	private void setDate(Date x) {
		//long d = Long.parseLong(x);
		this.mDate = x;
	}
	public String getAlpine() {
		return mAlpine;
	}

	private void setAlpine(String x) {
		this.mAlpine = x;
	}

	public String getTreeline() {
		return mTreeline;
	}

	private void setTreeline(String x) {
		this.mTreeline = x;
	}

	public String getBelowTreeline() {
		return mBelowTreeline;
	}

	private void setBelowTreeline(String x) {
		this.mBelowTreeline = x;
	}
	public static AvalancheDay BuildAvalancheDay(JSONObject jObj, String dayName, Date date) throws JSONException{
		
		AvalancheDay ad = new AvalancheDay();
		JSONObject jSubObj = JSON.JSONHelper.getObject(dayName, jObj);
		ad.setAlpine(JSON.JSONHelper.getString("Alpine",jSubObj));
		ad.setBelowTreeline(JSON.JSONHelper.getString("BelowTreeline",jSubObj));
		ad.setTreeline(JSON.JSONHelper.getString("Treeline",jSubObj));
		ad.setName(dayName);
		ad.setDate(date);
		return ad;
	}
}

