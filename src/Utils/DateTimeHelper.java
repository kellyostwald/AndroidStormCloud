package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeHelper {

	
	/**
	 * 
	 * @return yyyy-mm-dd format
	 */
	public static String getCurrentDateString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
	public static String formatTime(long unixTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
	formatter.setTimeZone(getCurrentTimeZone());
		Date date = new Date(unixTime);
		String str = formatter.format(date);
		return str;
	}
	/*public static Date getDate(long unixTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd LLL yyyy");
		formatter.setTimeZone(getCurrentTimeZone());
		Date date = new Date(unixTime * 1000L);
		
		return date;
	}*/
	public static String getDateString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd LLL yyyy");
		formatter.setTimeZone(getCurrentTimeZone());
		
		String str = formatter.format(date);
		return str;
	}
	public static String getWeekdayDateString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM d");
		formatter.setTimeZone(getCurrentTimeZone());
		
		String str = formatter.format(date);
		return str;
	}
	public static String getLongDateString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd LLL yyyy h:mm a");
		formatter.setTimeZone(getCurrentTimeZone());
		
		String str = formatter.format(date);
		return str;
	}
	public static Date getDate(long unixTime) {
		Date date = new Date(unixTime );
		return date;
	}
	public static Date getDateWithString(String x) {
		Date date;
		try {
		
			date = new SimpleDateFormat("dd/LL/yyyy h:mm:ss a").parse(x);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static TimeZone getCurrentTimeZone() {
		return TimeZone.getTimeZone("UTC");
	}
}
