package com.roytrack.file;

import java.io.File;
import java.io.IOException;
import java.net.*;


public class FilePath {
public static void main(String[] args) throws IOException {
	String path=FilePath.class.getResource("/").getPath().toString();
	System.out.println(path);
	path=path.substring(path.indexOf("file:")+6, path.indexOf("bin"));
	System.out.println(path);
	path+="aa cc/";
	System.out.println(path);
//	path=URLEncoder.encode(path);
//	System.out.println(path);
//	path="D:/rcm good/";
	File f=new File(path);
	if(!f.exists())f.mkdir();
	path+="abc.txt";
	f=new File(path);
	if(!f.exists())f.createNewFile();
	System.out.println(f.getPath());
	System.out.println(f.getParent());
	path=f.getPath();
	f=new File(path);
	f.delete();
	System.out.println(f.exists());
	f.createNewFile();
	System.out.println(f.exists());
	
	
}
}
