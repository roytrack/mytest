package com.roytrack.DateCollection;

import java.util.Calendar;
import java.util.Date;

public class ThemillionSecondOf1990 {
public static void main(String[] args) {
	Calendar calendar=Calendar.getInstance();
	calendar.set(1990, 0, 1,0,0,0);
	Date d=new Date(calendar.getTimeInMillis());
	System.out.println(calendar.getTimeInMillis());
	System.out.println(d);
}
}
