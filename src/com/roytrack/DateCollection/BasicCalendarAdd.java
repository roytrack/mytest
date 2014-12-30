package com.roytrack.DateCollection;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BasicCalendarAdd {
public static void main(String[] args) {
	 SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	GregorianCalendar calendar=new GregorianCalendar();
	calendar.set(Calendar.DAY_OF_MONTH, 1);
	calendar.add(GregorianCalendar.MONTH, 1);
	calendar.add(Calendar.DAY_OF_MONTH, -1);
	System.out.println(sDateFormat.format(calendar.getTime()));
	calendar.add(Calendar.DAY_OF_MONTH, 1);
	System.out.println(sDateFormat.format(calendar.getTime()));
}
}
