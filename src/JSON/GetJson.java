package JSON;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import Utils.Globals;
import android.os.Build;


public class GetJson {
	private static final int CONNECTION_TIMEOUT = 10000;
	private static final int DATARETRIEVAL_TIMEOUT = 10000;

	public static String requestWebService(String serviceUrl) {
		disableConnectionReuseIfNecessary();

		HttpURLConnection urlConnection = null;
		try {
			// create connection
			URL urlToRequest = new URL(Globals.SERVERBASEPATH + serviceUrl);
			//URL urlToRequest = new URL(	"http://api.openweathermap.org/data/2.5/weather?q=city,country");
		
			urlConnection = (HttpURLConnection) urlToRequest.openConnection();
			urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
			urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);
			// urlConnection.setRequestProperty(field, newValue)

			
			// handle issues
			int statusCode = urlConnection.getResponseCode();
			if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
				// handle unauthorized (if service requires user login)
			} else if (statusCode != HttpURLConnection.HTTP_OK) {
				// handle any other errors, like 404, 500,..
			}
			InputStream in= null;

			StringBuffer buffer = new StringBuffer();
			in = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = br.readLine()) != null)
				buffer.append(line + "\r\n");

			
			return buffer.toString();

		} catch (MalformedURLException e) {
			// URL is invalid
		} catch (SocketTimeoutException e) {
			// data retrieval or connection timed out
		} catch (IOException e) {
			// could not read response body
			// (could not create input stream)
		} 
		finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}

		return null;
	}

	/**
	 * required in order to prevent issues in earlier Android version.
	 */
	private static void disableConnectionReuseIfNecessary() {
		// see HttpURLConnection API doc
		if (Integer.parseInt(Build.VERSION.SDK) < Build.VERSION_CODES.FROYO) {
			System.setProperty("http.keepAlive", "false");
		}
	}

}
