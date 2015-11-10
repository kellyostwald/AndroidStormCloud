package com.stormcloud.avalanche;

import java.util.List;

import com.stormcloud.main.R;
import com.stormcloud.main.R.id;
import com.stormcloud.main.R.layout;


import DomainModel.AvalancheDay;
import DomainModel.LiftStatus;
import DomainModel.WeatherForecast;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AvalancheAdapter extends ArrayAdapter<AvalancheDay> {

	private List<AvalancheDay> avalancheDayList;
	private Context context;
	private final int RED = Color.RED;
	private final int GREEN = Color.GREEN;
	public AvalancheAdapter(List<AvalancheDay> avalancheDayList, Context ctx) {
		super(ctx, R.layout.avalanche_row_layout, avalancheDayList);
		this.avalancheDayList = avalancheDayList;
		this.context = ctx;
		
	
	}
	
	public int getCount() {
		return avalancheDayList.size();
	}

	public AvalancheDay getItem(int position) {
		return avalancheDayList.get(position);
	}

	public long getItemId(int position) {
		return avalancheDayList.get(position).hashCode();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		
		LiftStatusHolder holder = new LiftStatusHolder();
		
		// First let's verify the convertView is not null
		if (convertView == null) {
			// This a new view we inflate the new layout
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.avalanche_row_layout, null);
			// Now we can fill the layout with the right values
			TextView tvAlpine = (TextView) v.findViewById(R.id.avalancheAlpine);
			TextView tvTreeline = (TextView) v.findViewById(R.id.avalancheTreeline);
		
			TextView tvBelowTreeline = (TextView) v.findViewById(R.id.avalancheBelowTreeline);
			TextView tvDayName = (TextView) v.findViewById(R.id.avalancheDayName);

			
			holder.alpine = tvAlpine;
			holder.belowTreeline = tvBelowTreeline;
			holder.dayName = tvDayName;
			holder.treeline = tvTreeline;
			v.setTag(holder);
		}
		else 
			holder = (LiftStatusHolder) v.getTag();
		
		AvalancheDay p = avalancheDayList.get(position);
		holder.alpine.setText(p.getAlpine());
		holder.belowTreeline.setText( p.getBelowTreeline());
		//holder.date.setText(p.getDateString());
		holder.treeline.setText(p.getTreeline());
		holder.dayName.setText(p.getDateString());
		
		
	
		return v;
	}
	
	/* *********************************
	 * We use the holder pattern        
	 * It makes the view faster and avoid finding the component
	 * **********************************/
	
	private static class LiftStatusHolder {
		public TextView alpine;
		public TextView treeline;
		//public TextView date;
		public TextView belowTreeline;
		public TextView dayName;
	}
	

}
