package com.roytrack.xmlOp;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.Element;

public class SimpleReader {
public static void main(String[] args) throws DocumentException {
	SAXReader reader = new SAXReader();  
	Document doc = reader.read("/ParametersInfo.xml");
	Node Path = doc.selectSingleNode("/ParametersInfo/MailParameters/Path");
	String path = (Path==null?"":((Element)Path).getTextTrim());
	System.out.println(path);
}
}
