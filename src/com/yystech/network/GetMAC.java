package com.yystech.network;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;

public class GetMAC {
 public static void main(String [] args){
String ipAddress="192.168.100.71";
String str="";
String strMAC="";
	 try {
		 System.out.println(ipAddress);
			Process pp = Runtime.getRuntime().exec("nbtstat -a " + ipAddress);
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				System.out.println(str);
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						strMAC = str.substring(str.indexOf("MAC Address") + 14,
								str.length());
						System.out.println(strMAC);
						break;
					}
				}
				
				
				InetAddress	addr = InetAddress.getLocalHost();
				System.out.println(addr.toString());
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
		
 }
}
