package com.stormcloud.weatherforecast;

import java.util.List;

import com.stormcloud.main.R;
import com.stormcloud.main.R.id;
import com.stormcloud.main.R.layout;

import DomainModel.WeatherForecast;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherForecastAdapter extends ArrayAdapter<WeatherForecast> {

	private List<WeatherForecast> weatherList;
	private Context context;
	private boolean IsAlpine;

	public WeatherForecastAdapter(List<WeatherForecast> weatherList, Context ctx, boolean IsAlpine) {
		super(ctx, R.layout.weather_row_layout, weatherList);
		this.weatherList = weatherList;
		this.context = ctx;
		this.IsAlpine = IsAlpine;
	}

	

	public int getCount() {
		return weatherList.size();
	}

	public WeatherForecast getItem(int position) {
		return weatherList.get(position);
	}

	public long getItemId(int position) {
		return weatherList.get(position).hashCode();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		WeatherHolder holder = new WeatherHolder();

		// First let's verify the convertView is not null
		if (convertView == null) {
			// This a new view we inflate the new layout
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.weather_row_layout, null);
			// Now we can fill the layout with the right values
			TextView tvCloud = (TextView) v.findViewById(R.id.cloud);
			TextView tvTemp = (TextView) v.findViewById(R.id.temperature);
			TextView tvDate = (TextView) v.findViewById(R.id.date);
			TextView tvSynopsis = (TextView) v.findViewById(R.id.synopsis);
			ImageView icon = (ImageView) v.findViewById(R.id.weatherIcon);

			holder.cloud = tvCloud;
			holder.temp = tvTemp;
			holder.date = tvDate;
			holder.synopsis = tvSynopsis;
			holder.icon = icon;
			v.setTag(holder);
		} else
			holder = (WeatherHolder) v.getTag();

		WeatherForecast p = weatherList.get(position);
		holder.cloud.setText(p.getCloud());
		holder.temp.setText(p.getTemp());
		holder.date.setText(p.getDateString());
		holder.synopsis.setText(p.getSynopsis());
		//holder.icon.setImageResource(R.drawable.icon_02);
		if(p.getCloud().isEmpty()){
			 holder.cloud.setHeight(0);
		}
		if(p.getTemp().isEmpty()){
			 holder.temp.setHeight(0);
		}
		if(p.getSynopsis().isEmpty()){
			 holder.synopsis.setHeight(0);
		}
		 int drawable = v.getResources().getIdentifier("icon_"+p.getIconCode(), "drawable", context.getPackageName());
		 if (drawable != 0) {
			 holder.icon.setImageResource(drawable);}
		 
		 if(!IsAlpine){
			 holder.temp.setHeight(0);
			 holder.cloud.setHeight(0);
		 }
		return v;
	}

	/* *********************************
	 * We use the holder pattern It makes the view faster and avoid finding the
	 * component *********************************
	 */

	private static class WeatherHolder {
		public TextView cloud;
		public TextView temp;
		public TextView date;
		public TextView synopsis;
		public ImageView icon;
	}

}
