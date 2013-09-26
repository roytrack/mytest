package com.roytrack.charOP;

import com.sun.xml.internal.fastinfoset.util.CharArray;

public class EmptyChar {

	public static void main(String[] args) {
		char a=new String(" ").charAt(0);
		System.out.println(Integer.valueOf(a));
		char c=0;
		System.out.println(c);
		String aa="  ";
		char d='\u0000';
		if(c==d)System.out.println("yes");
		else 
			System.out.println("no");
		byte e= Character.DIRECTIONALITY_UNDEFINED;
		System.out.println(e);
		System.out.println((short)e);
		System.out.println(Short.reverseBytes(e));
		int f=Character.digit(e, 10);
		System.out.println(f);
		char g=(char)e;
		System.out.println(g);
		short h=-1;
		System.out.println((char)h);
		String aaString="Œ“ «»Ó≥£√˙";
		char[] charArray =new char [10];
		for(int i=0;i<aaString.length();i++){
			charArray[i]=aaString.charAt(i);
		System.out.println(aaString.charAt(i));
		System.out.println(Integer.toHexString(charArray[i]));
		}
		System.out.println("\u6211\u662f\u962e\u5e38\u94ed");
		System.out.println("\u8BFB\u53D6\u6570\u636E\u5E93\u5C5E\u6027\u914D\u7F6E\u6587\u4EF6");
		System.out.println(String.valueOf(true));
	}
}
