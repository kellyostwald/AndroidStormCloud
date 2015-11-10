package com.stormcloud.settings;

import com.stormcloud.main.R;

import Utils.Helper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsAboutActivity extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_about_main);	
	}
	public void SendMessage(View view) {

		//EditText editText = (EditText)findViewById(R.id.message);
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"stormcloud_app@outlook.com"});
		i.putExtra(Intent.EXTRA_SUBJECT, "StormCloud for Android");
		i.putExtra(Intent.EXTRA_TEXT   , "");
		try {
		    startActivity(Intent.createChooser(i, "Send email..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(SettingsAboutActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}
}
