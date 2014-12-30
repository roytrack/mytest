package com.roytrack.time;

import java.text.SimpleDateFormat;
import java.util.Date;



public class DateAndFormat {
public static void main(String[] args) {
	Date today=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
	System.out.println(sdf.format(today));
}
}
