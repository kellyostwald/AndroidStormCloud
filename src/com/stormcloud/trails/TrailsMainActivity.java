package com.stormcloud.trails;

import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.stormcloud.location.LocationUtils;
import com.stormcloud.main.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.location.Location;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

public class TrailsMainActivity  extends Activity  {
	static final LatLng WHISTLER = new LatLng(50.057983, -122.957096);

	private GoogleMap mMap;
	 // A request to connect to Location Services
    private LocationRequest mLocationRequest;

    // Stores the current instantiation of the location client in this object
    private LocationClient mLocationClient;

  

    // Handle to SharedPreferences for this app
    SharedPreferences mPrefs;

    // Handle to a SharedPreferences editor
    SharedPreferences.Editor mEditor;

    /*
     * Note if updates have been turned on. Starts out as "false"; is set to "true" in the
     * method handleRequestSuccess of LocationUpdateReceiver.
     *
     */
    boolean mUpdatesRequested = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trails_main);

		if (mMap == null) {
			mMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();
		}
		// Check if we were successful in obtaining the map.
		if (mMap != null) {
		//	mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			mMap.setMyLocationEnabled(true);
		
		//	mMap.addMarker(new MarkerOptions().position(WHISTLER).title(
		//			"Whistler"));

		//	mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(WHISTLER, 15));
		}
		// Zoom in, animating the camera.
		// map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
		// isGooglePlayServicesAvailable(this);
		
		
	}
	

	 
    
}
