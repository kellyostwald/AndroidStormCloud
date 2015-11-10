package com.stormcloud.liftstatus;

import java.util.List;

import com.stormcloud.main.R;
import com.stormcloud.main.R.id;
import com.stormcloud.main.R.layout;


import DomainModel.LiftStatus;
import DomainModel.WeatherForecast;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LiftStatusAdapter extends ArrayAdapter<LiftStatus> {

	private List<LiftStatus> liftStatusList;
	private Context context;
	private final static int RED = Color.RED;
	private final static int GREEN = Color.GREEN;
	private final static int YELLOW = Color.YELLOW;
	public LiftStatusAdapter(List<LiftStatus> liftList, Context ctx) {
		super(ctx, R.layout.lift_status_row_layout, liftList);
		this.liftStatusList = liftList;
		this.context = ctx;
		
	
	}
	
	public int getCount() {
		return liftStatusList.size();
	}

	public LiftStatus getItem(int position) {
		return liftStatusList.get(position);
	}

	public long getItemId(int position) {
		return liftStatusList.get(position).hashCode();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		
		LiftStatusHolder holder = new LiftStatusHolder();
		
		// First let's verify the convertView is not null
		if (convertView == null) {
			// This a new view we inflate the new layout
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.lift_status_row_layout, null);
			// Now we can fill the layout with the right values
			TextView tvStatus = (TextView) v.findViewById(R.id.status);
		//	TextView tvMountain = (TextView) v.findViewById(R.id.mountain);
		//	TextView tvDate = (TextView) v.findViewById(R.id.date);
			TextView tvLiftName = (TextView) v.findViewById(R.id.liftName);
			TextView tvColor = (TextView) v.findViewById(R.id.color);

			
			holder.status = tvStatus;
			//holder.mountain = tvMountain;
		//	holder.date = tvDate;
			holder.liftName = tvLiftName;
			holder.color = tvColor;
			v.setTag(holder);
		}
		else 
			holder = (LiftStatusHolder) v.getTag();
		
		LiftStatus p = liftStatusList.get(position);
	
		//holder.date.setText(p.getDateString());
		holder.liftName.setText(p.getLiftName());
		
		
		String status = p.getStatus();
		if(status.equalsIgnoreCase("CLOSED"))
			holder.color.setBackgroundColor(RED);
		else if(status.equalsIgnoreCase("OPEN"))
			holder.color.setBackgroundColor(GREEN);
		else
			holder.color.setBackgroundColor(YELLOW);
		
		holder.status.setText(status);
		//	holder.mountain.setText( p.getMountain());
		return v;
	}
	
	/* *********************************
	 * We use the holder pattern        
	 * It makes the view faster and avoid finding the component
	 * **********************************/
	
	private static class LiftStatusHolder {
	//	public TextView mountain;
		public TextView liftName;
		//public TextView date;
		public TextView status;
		public TextView color;
	}
	

}
