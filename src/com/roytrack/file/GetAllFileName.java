package com.roytrack.file;

import java.io.File;
import java.util.Date;

public class GetAllFileName {
	static int count=0;
	public static void main(String[] args) {
	
	String	filePath="D:\\my10wp\\directReportNew - ����\\src";
		 File dir = new File(filePath);   
		 reverseDirectory(dir);
	        System.out.println("һ����"+count+"���ļ�");
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
