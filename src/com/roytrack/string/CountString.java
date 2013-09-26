package com.roytrack.string;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CountString {
	public static void main(String[] args) throws IOException {
		File f=new File("ÊýÁ´½Ó.txt");
		FileReader fr=new FileReader(f);
		char [] cbuf=new char[(int)f.length()];
		fr.read(cbuf);
		String alldata=String.valueOf(cbuf);
		String [] dataline=alldata.split("\r\n");
		HashMap<String,Integer> hm=new HashMap<String,Integer>();
		StringBuffer sb=new StringBuffer();
		for(String aa:dataline){
			if(hm.get(aa.trim())==null){
				hm.put(aa.trim(), 1);
				sb.append(aa.trim()+"\r\n");
			}else{
				hm.put(aa.trim(), hm.get(aa.trim())+1);
			}
		}
		String [] urls=sb.toString().split("\r\n");
		for(String aa:urls){
			System.out.println(aa+"\t\t\t@"+hm.get(aa));
		}
	}

}
