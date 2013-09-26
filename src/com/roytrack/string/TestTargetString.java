package com.roytrack.string;

public class TestTargetString {
public static void main(String[] args) {
	String aa="长度不统一,字段名:aaa,定义长度为:10,实际长度:15</br>";
	String[] bb=aa.split(",");
	System.out.println(bb[1]);
	String [] cc=bb[1].split(":");
	System.out.println(cc[1]);
}
}
