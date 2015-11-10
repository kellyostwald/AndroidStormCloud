package com.stormcloud.webcam;

import com.stormcloud.main.R;

import Utils.Helper;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebcamMainActivity extends Activity{
	//http://www.whistlerblackcomb.com/the-mountain/web-cams/whistler
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view);

		if (!Helper.IsNetworkAvailable(this)) {
			Helper.ShowNoNetworkMessage(this);
			
			return;
		}
		WebView webView = (WebView)findViewById(R.id.webView);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://www.whistlerblackcomb.com/the-mountain/web-cams/whistler");
		webView.setWebViewClient(new WebViewClient());
	}
}
