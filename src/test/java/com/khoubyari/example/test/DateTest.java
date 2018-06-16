package com.khoubyari.example.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTest {

	public static void main(String[] args) throws Exception {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm",Locale.US);
    	simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    	
    	Date choreDate = simpleDateFormat.parse("2013-11-10T01:46");
    	System.out.print("choreDate:"+choreDate);

	}

}
