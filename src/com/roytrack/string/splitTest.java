package com.roytrack.string;

import java.util.HashSet;

public class splitTest {
public static void main(String[] args) {
	String testString=new String("aaa,bb,cc");
	String testString1=new String("aaa,bb,cc,,,e");
	String testString2=new String("aaa,bb,cc,,,e, , ,");
	String[] splitStr=testString.split(",");
	String[] splitStr1=testString1.split(",");
	String[] splitStr2=testString2.split(",",-1);
	HashSet hs=new HashSet();
	System.out.println("splitStr length "+splitStr.length+" splitStr2 length "+splitStr1.length+" splitStr2 length "+splitStr2.length);
	for(int i =0;i<splitStr.length;i++)
		System.out.print(splitStr[i]+"%");
	System.out.println();
	for(int i =0;i<splitStr1.length;i++){
		System.out.print(splitStr1[i]+"%");
	hs.add(splitStr1[i]);}
	hs.remove("");
	System.out.println(hs.toString());
}
}
