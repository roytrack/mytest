package com.roytrack.time;

import java.text.SimpleDateFormat;
import java.util.Date;



public class DateAndFormat {
public static void main(String[] args) {
	Date today=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy��MM��dd��HHʱmm��ss��");
	System.out.println(sdf.format(today));
}
}
