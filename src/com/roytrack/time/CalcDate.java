package com.roytrack.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalcDate {

public static void main(String[] args) throws ParseException {
	Date today=new Date();
	Date lastDay=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
	lastDay=sdf.parse("20121231");
	long between=lastDay.getTime()-today.getTime();
	System.out.println(between/(1000*3600*24));
}
}
