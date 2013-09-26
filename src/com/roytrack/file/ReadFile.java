package com.roytrack.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.yss.util.YssException;

public class ReadFile {
	public static void main(String[] args) throws YssException {
		BufferedReader reader = null;
		String result ="";
		File ff=new File("/dbsetting.txt");
		try {
			reader = new BufferedReader(new FileReader(ff));
			String temp = null;
            while ((temp = reader.readLine()) != null) {
            	result += temp;
			}
		} catch (Exception e) {
			throw new YssException("读取文件" + ff.toString() + "出错！");
		}
		System.out.println(result); 
	}

}
