package com.roytrack.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class CompareStringInFile {

	public static void main(String[] args) throws IOException {
		String Path="D:\\rcm\\工作文件\\20120502回归测试\\temp_tradeData.txt";
		File file=new File(Path);
		FileReader fr=new FileReader(file);
		BufferedReader bfr=new BufferedReader(fr);
		String[] aString=bfr.readLine().split(",");
		String[] bString=bfr.readLine().split(",");
		HashSet ahs=new HashSet();
		HashSet bhs=new HashSet();
		HashSet hs=new HashSet();
		for(int i=0 ;i<aString.length;i++){
			ahs.add(aString[i]);
			System.out.print(aString[i]+"\t");
		}
		System.out.println();
		System.out.println("%%%");
		for(int i=0 ;i<bString.length;i++){
			bhs.add(bString[i]);
			System.out.print(bString[i]+"\t");
		}
		hs=(HashSet)ahs.clone();
		System.out.println();
		System.out.println("hs size is "+hs.size());
		System.out.println("ahs size is "+ahs.size());
		System.out.println("bhs size is "+bhs.size());
		System.out.println("aString length is "+aString.length);
		System.out.println("bString length is "+bString.length);
		ahs.removeAll(bhs);
		bhs.removeAll(hs);
		System.out.println(ahs.toString());
		System.out.println(bhs.toString());
		
			
	
		
	}
}
