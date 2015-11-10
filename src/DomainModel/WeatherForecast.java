package DomainModel;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.JsonReader;


public class WeatherForecast {

        private Date mDate;
        private  int Id ;
        private  String mCloud;
        private  String mPrecip ;
        private  String mPrecipType ;
        private  String mTemperature;
        private  String mWind ;
        private  String mFreezingLevel ;
        private  String mSynopsis ;
        private  String mIconCode ;
        
    	public Date getDate() {
			return mDate;
		}
    	public String getDateString() {
			return Utils.DateTimeHelper.getWeekdayDateString( mDate);
		}
    	private void setDate(String x) {
			long d = Long.parseLong(x);
			this.mDate = Utils.DateTimeHelper.getDate(d);
		}
		
		public String getCloud() {
			return mCloud;
		}
		private void setCloud(String x) {
			this.mCloud = x;
		}
		public String getPrecip() {
			return mPrecip;
		}
		private void setPrecip(String x) {
			this.mPrecip = x;
		}
		public String getPrecipType() {
			return mPrecipType;
		}
		private void setPrecipType(String x) {
			this.mPrecipType = x;
		}
		public String getTemp() {
			return mTemperature;
		}
		private void setTemp(String x) {
			this.mTemperature = x;
		}
		public String getWind() {
			return mWind;
		}
		private void setWind(String x) {
			this.mWind = x;
		}
		public String getFreezingLevel() {
			return mFreezingLevel;
		}
		private void setFreezingLevel(String x) {
			this.mFreezingLevel = x;
		}
		public String getSynopsis() {
			return mSynopsis;
		}
		private void setSynopsis(String x) {
			this.mSynopsis = x;
		}
		public String getIconCode() {
			return mIconCode;
		}
		private void setIconCode(String x) {
			this.mIconCode = x;
		}
		
		
		public static  List<WeatherForecast> getWeather(String data) throws JSONException  {
			
			 List<WeatherForecast> weatherForecast = new ArrayList<WeatherForecast>();
			 JSONArray jArray = new JSONArray(data);
		//sorts it by date
			 for(int i=jArray.length()-1; i>=0;i--){
			
				 JSONObject jObj = new JSONObject(jArray.get(i).toString());
					WeatherForecast w = new WeatherForecast();
					w.setCloud(JSON.JSONHelper.getString("Cloud",jObj));
					w.setFreezingLevel(JSON.JSONHelper.getString("FreezingLevel",jObj));
					w.setPrecip(JSON.JSONHelper.getString("Precip",jObj));
					w.setPrecipType(JSON.JSONHelper.getString("PrecipType",jObj));
					w.setSynopsis(JSON.JSONHelper.getString("Synopsis",jObj));
					w.setWind(JSON.JSONHelper.getString("Wind",jObj));
					w.setTemp(JSON.JSONHelper.getString("Temperature",jObj));
					w.setIconCode(JSON.JSONHelper.getString("IconCode", jObj));
					String newString = JSON.JSONHelper.getString("Date", jObj).replace("/Date(", "");
					String newString2 = newString.replace(")/","");
					w.setDate(newString2);
					weatherForecast.add(w);
			 }
			 
			 
			
			return weatherForecast;
		}
		

		
	
}
