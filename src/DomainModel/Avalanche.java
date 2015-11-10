package DomainModel;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Avalanche {


	private Date mDate;
	private String mTravel;
	private String mActivity;
	private String mSnowpack;
	private String mInfo;
	private List<AvalancheDay> mAvalancheDay;

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

	public String getTravel() {
		return mTravel;
	}

	private void setTravel(String x) {
		this.mTravel = x;
	}

	public String getActivity() {
		return mActivity;
	}

	private void setActivity(String x) {
		this.mActivity = x;
	}

	public String getSnowpack() {
		return mSnowpack;
	}

	private void setSnowpack(String x) {
		this.mSnowpack = x;
	}
	public String getInfo() {
		return mInfo;
	}

	private void setInfo(String x) {
		this.mInfo = x;
	}
	public List<AvalancheDay> getAvalancheDay(){
		return mAvalancheDay;
	}
	private void setAvalancheDay( List<AvalancheDay> x){
		 this.mAvalancheDay = x;
	}
	public static Avalanche getAvalanche(String data)
			throws JSONException {

	
			JSONObject jObj = new JSONObject(data);
			Avalanche a = new Avalanche();

			a.setSnowpack(JSON.JSONHelper.getString("Snowpack", jObj));
			a.setInfo(JSON.JSONHelper.getString("Info", jObj));
			a.setActivity(JSON.JSONHelper.getString("Activity", jObj));
			a.setTravel(JSON.JSONHelper.getString("Travel", jObj));
			
			String newString = JSON.JSONHelper.getString("Date", jObj)
					.replace("/Date(", "");
			String newString2 = newString.replace(")/", "");
			a.setDate(newString2);
		    List<AvalancheDay> ad = new ArrayList<AvalancheDay>();
		    
		    
		    long millis = a.getDate().getTime();
		    millis = millis + 1*24*60*60*1000;
		    Date nextDate = new Date();
		    nextDate.setTime(millis);
		    
		    long millis2 = a.getDate().getTime();
		    millis2 = millis2 + 2*24*60*60*1000;
		    Date nextDate2 = new Date();
		    nextDate2.setTime(millis2);
		    
		    ad.add(AvalancheDay.BuildAvalancheDay(jObj, "DayOne",a.getDate()));
		    ad.add(AvalancheDay.BuildAvalancheDay(jObj, "DayTwo",nextDate));
		    ad.add(AvalancheDay.BuildAvalancheDay(jObj, "DayThree",nextDate2));
		    a.setAvalancheDay(ad);
		

		return a;
	}

}


