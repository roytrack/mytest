package com.roytrack.DateCollection;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Generated;

public class GetDateBetweenTwoDay {
	public static void main(String[] args) throws ParseException {
		GetDateBetweenTwoDay gbTwoDay=new GetDateBetweenTwoDay();
		System.out.println(gbTwoDay.getDateList("2012-05-12","2012-05-12").toString());
	}

	
	
	/**
	 * 生成两日期间的yyyy-MM-dd格式字符串的List
	 * @param String beginDate String endDate
	 * return ArrayList aList
	 * @throws ParseException 
	 * */
	public ArrayList getDateList(String beginDate, String endDate) throws ParseException{
		ArrayList  aList=new ArrayList();
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date begin=df.parse(beginDate);
		Date end=df.parse(endDate);
		GregorianCalendar  calendar1=new GregorianCalendar();
		GregorianCalendar  calendar2=new GregorianCalendar();
		calendar1.setTime(begin);
		calendar2.setTime(end);
		while(calendar2.getTimeInMillis()-calendar1.getTimeInMillis()>=0)
		{
			aList.add(df.format(calendar1.getTime()));
			if(calendar1.getMaximum(Calendar.DAY_OF_MONTH)==calendar1.get(Calendar.DAY_OF_MONTH))
				calendar1.add(Calendar.MONTH,1);
			else
				calendar1.add(Calendar.DATE,1);	
		}
		System.out.println(aList.size());
		return aList;
		
	}
	public static void generate(String beginDate,String endDate) throws ParseException{
		ArrayList  aList=new ArrayList();
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date begin=df.parse(beginDate);
		Date end=df.parse(endDate);
		GregorianCalendar  calendar1=new GregorianCalendar();
		GregorianCalendar  calendar2=new GregorianCalendar();
		calendar1.setTime(begin);
		calendar2.setTime(end);
		System.out.println(df.format(calendar1.getTime()));
		System.out.println(df.format(calendar2.getTime()));
		System.out.println(calendar1.getMaximum(Calendar.DAY_OF_MONTH));
		for(;calendar2.getTimeInMillis()-calendar1.getTimeInMillis()>=0;)
		{
			aList.add(df.format(calendar1.getTime()));
			if(calendar1.getMaximum(Calendar.DAY_OF_MONTH)==calendar1.get(Calendar.DAY_OF_MONTH))
				calendar1.add(Calendar.MONTH,1);
			else
				calendar1.add(Calendar.DATE,1);
			System.out.println(df.format(calendar1.getTime()));
			
		}
	
		
		
	}
}
