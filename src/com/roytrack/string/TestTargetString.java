package com.roytrack.string;

public class TestTargetString {
public static void main(String[] args) {
	String aa="���Ȳ�ͳһ,�ֶ���:aaa,���峤��Ϊ:10,ʵ�ʳ���:15</br>";
	String[] bb=aa.split(",");
	System.out.println(bb[1]);
	String [] cc=bb[1].split(":");
	System.out.println(cc[1]);
}
}
