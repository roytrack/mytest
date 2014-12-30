package com.roytrack.system;

import java.util.Properties;

public class SystemProperties {
public static void main(String[] args) {
	Properties  pp=System.getProperties();
	
	System.out.println(System.getProperty("file.separator"));
}
}
