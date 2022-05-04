package com.rr.trackdata.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
	public static String convertToString(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	
	public static Date convertToDate(String strDate, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date parsedDate = null;
		try {
			parsedDate = (Date) dateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsedDate;
	
	}
}
