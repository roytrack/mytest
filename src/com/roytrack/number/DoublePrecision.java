package com.roytrack.number;

import java.math.BigDecimal;

public class DoublePrecision {
public static void main(String[] args) {
	
	double a=55;
	double b=6.01;
	double c=a-b;
	System.out.println(a>b?"a":"b");
	System.out.println(c>b?"c":"b");
	System.out.println(a==b?"equal":"no");
	System.out.println(c==b?"equal":"no");
	System.out.println(a+"@"+b+"@"+c);
	Double aa=a;
	aa.compareTo(b);
	BigDecimal aaa=BigDecimal.valueOf(a);
	BigDecimal bbb=BigDecimal.valueOf(b);
	aaa.compareTo(bbb);
	aaa.setScale(2);
	String valuea="15.14";
	String valueb="62.07";
	String valuec="77.21";
	double result = 0;
	result+=Double.valueOf(valuea);
	result+=Double.valueOf(valueb);
	System.out.println(result);
	System.out.println(result>Double.valueOf(valuec)?"yes":"no");
	
	
}
}


