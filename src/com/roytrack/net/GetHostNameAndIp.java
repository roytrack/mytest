package com.roytrack.net;

import java.net.InetAddress;

public class GetHostNameAndIp {
public static void main(String[] args) {
	
	try{
		
        InetAddress s = InetAddress.getLocalHost();
        System.out.println(s.getHostName());//主机名
        byte[] ipAddr = s.getAddress();//ip
        ///还差mac和网关
        
		String ipAddrStr = "";
		for (int i = 0; i < ipAddr.length; i++) {
			if (i > 0) {
				ipAddrStr += ".";
			}
			ipAddrStr += ipAddr[i] & 0xFF;
		}  
        System.out.println(ipAddrStr);
}
catch (Exception e)
{
}

}
}
