package com.roytrack.collectionTest;

import java.util.HashSet;

public class SetTest {
public static void main(String[] args) {
	HashSet aSet=new HashSet();
	aSet.add("aa");
	aSet.add("bb");
	String aa=aSet.toString();
	System.out.println(aa.substring(1,aa.length()-1));
}
}
