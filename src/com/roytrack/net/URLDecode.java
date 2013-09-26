package com.roytrack.net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URLDecode {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String aa="%cc%ec%bd%f2%c8%fd%d0%c7%cd%a8%d0%c5%bc%bc%ca%f5%d3%d0%cf%de%b9%ab%cb%be";
		String bb=URLDecoder.decode(aa,"UTF-8");
		System.out.println(bb);
	}

}
