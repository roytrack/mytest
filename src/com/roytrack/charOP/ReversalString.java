package com.roytrack.charOP;


/***
 * ���ַ��������ݷ�ת �ռ����ĵȼ�  
 * �Ժ󲹳�һ��ֻ��һ����ʱ�ռ��
 * */
public class ReversalString {
public static void main(String[] args) {
	
	String name="ruanchangming";
	char[] nameChar=new char [name.length()];
	char[] nameReversal=new char [name.length()];
	name.getChars(0, name.length(), nameChar, 0);
	System.out.println(nameChar.length);
	for(int i=0;i<nameChar.length;i++)
		System.out.println(nameChar[i]);
	for(int i=0;i<nameChar.length;i++)
		nameReversal[name.length()-i-1]=nameChar[i];
	String result=String.valueOf(nameReversal);
	System.out.println(result);
	
}
}
