package com.yystech.network;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;


public class GetMACAddress {
	public static void main(String[] args) {
		GetMACAddress gm=new GetMACAddress();
		System.out.println(gm.getLocalIP()); 
	}

	public String getMACAddress(String ipAddress) {

		String str = "", strMAC = "", macAddress = "";

		try {

			Process pp = Runtime.getRuntime().exec("nbtstat -a " + ipAddress);
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						strMAC = str.substring(str.indexOf("MAC Address") + 14,
								str.length());
						break;
					}
				}
			}
		} catch (IOException ex) {
			return "Can't Get MAC Address!";
		}
		if (strMAC.length() < 17) {
			return "Error!";
		}

		macAddress = strMAC.substring(0, 2) + "-" + strMAC.substring(3, 5)
				+ "-" + strMAC.substring(6, 8) + "-" + strMAC.substring(9, 11)
				+ "-" + strMAC.substring(12, 14) + "-"
				+ strMAC.substring(15, 17);
		return macAddress;

	}

//	public static void main(String[] args) {
//
//		GetMACAddress getMACAddress = new GetMACAddress();
//
//	}

	public static String procAll(String str) {

		return procStringEnd(procFirstMac(procAddress(str)));

	}

	public static String procAddress(String str) {

		int indexof = str.indexOf("Physical Address");
		if (indexof > 0) {
			return str.substring(indexof, str.length());
		}
		return str;
	}

	public static String procFirstMac(String str) {

		int indexof = str.indexOf(":");
		if (indexof > 0) {
			return str.substring(indexof + 1, str.length()).trim();
		}
		return str;
	}

	public static String procStringEnd(String str) {

		int indexof = str.indexOf("\r");

		if (indexof > 0) {

			return str.substring(0, indexof).trim();

		}

		return str;
	}
	
	public String getLocalIP() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			return null;
		}
		byte[] ipAddr = addr.getAddress();
		for(byte b:ipAddr)System.out.println("@@"+(short)(b&0xFF));
		String ipAddrStr = "";
		for (int i = 0; i < ipAddr.length; i++) {
			if (i > 0) {
				ipAddrStr += ".";
			}
			ipAddrStr += ipAddr[i] & 0xFF;
		}  
		return ipAddrStr;
	}  

	

}
