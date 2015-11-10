package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Helper  {
	
	public static boolean IsNetworkAvailable(Context context) {

		try {
			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = cm.getActiveNetworkInfo();
			if (info == null)
				return false;
			if (info.isConnected())
				return true;

			boolean connecting = info.isConnectedOrConnecting();
			if (connecting) {
				URL url = new URL("http://www.google.com");
				HttpURLConnection urlc = (HttpURLConnection) url.openConnection();

				urlc.setConnectTimeout(1000); // mTimeout is in seconds

				urlc.connect();

				if (urlc.getResponseCode() == 200) {
					return true;
				} else {
					return false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static void ShowNoNetworkMessage(Context ctx){
		
		AlertDialog.Builder alert  = new AlertDialog.Builder(ctx);

		alert.setMessage("No network connection available.");
		//alert.setTitle("StormCloud");
		alert.setPositiveButton("OK", null);
		alert.setCancelable(true);
		alert.create().show();
		//return alert;
	
	}
	public static ProgressDialog ShowLoader(Context ctx){
		ProgressDialog progress = new ProgressDialog(ctx);
		//progress.setTitle("StormCloud");
		progress.setMessage("Loading...");
		return progress;
		//progress.show();
		// To dismiss the dialog
		//progress.dismiss();
	
	}
	
	public static void CacheString(String data, String filename, Context ctx) {
		try {
			FileOutputStream fos = ctx.openFileOutput(filename,
					Context.MODE_PRIVATE);
			fos.write(data.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String GetCachedString(String filename, Context ctx) {
		try {
			InputStream inputStream = ctx.openFileInput(filename);

			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();

				while ((receiveString = bufferedReader.readLine()) != null) {
					stringBuilder.append(receiveString);
				}

				inputStream.close();
				return stringBuilder.toString();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";

	}
}
