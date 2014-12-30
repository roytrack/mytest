package com.yss.xmlparse;

import java.io.File;
import java.io.IOException;

public class Filemake {
public static void main(String[] args) throws IOException {
	String filePath =Filemake.class.getClass().getResource("/").toString().substring(6).replace("classes", "");
	
	File file = new File(filePath);
	file.createNewFile();
}
}
