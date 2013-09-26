package com.roytrack.file;

import java.io.File;
import java.util.Date;

public class GetAllFileName {
	static int count=0;
	public static void main(String[] args) {
	
	String	filePath="D:\\my10wp\\directReportNew - 副本\\src";
		 File dir = new File(filePath);   
		 reverseDirectory(dir);
	        System.out.println("一共有"+count+"个文件");
	}
	
	public static void reverseDirectory(File f){
		if(f.isDirectory()){
			File []files=f.listFiles();
			for(File tmp:files)
			reverseDirectory(tmp);
		}else{
			if(f.getName().endsWith("java")) {
              //  System.out.println(f.getName());  
            	System.out.println(f.getPath());
            	count++;
            	}
		}
		
		
	}

}
