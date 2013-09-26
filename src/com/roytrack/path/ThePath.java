package com.roytrack.path;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class ThePath {
	public static void main(String[] args) throws DocumentException {
		ThePath t=new ThePath();
		t.rcm();
	}
	
	void rcm() throws DocumentException{
		SAXReader reader = new SAXReader();  
		InputStream is=this.getClass().getResourceAsStream("pageManage.xml");
		Document doc = reader.read(is);  
		System.out.println(doc.asXML());
		
	}
}
