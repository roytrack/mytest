package com.roytrack.xmlOp;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;



public class ChangeText {
public static void main(String[] args) throws Exception {
	ChangeText ct=new ChangeText();
	SAXReader reader=new SAXReader(false);  
	StringBuffer sbuffer = new StringBuffer();
	String filename=ct.aaa();
		Document document=reader.read(filename);
	    Element root=document.getRootElement();
	    System.out.println(document.selectSingleNode("//*[@leafid=\"1111\"]"));
//	    List aa=root.selectNodes("//*");
//	    HashSet hs=new HashSet();
//	    System.out.println("begin "+aa.size());
//	    for(int k=0;k<aa.size();k++){
//			 Element leaf=(Element)aa.get(k);
//			 Attribute at=leaf.attribute(2);
//			 
//			 if(leaf.getName().contains("leaf")&&leaf.getTextTrim().length()<=1){
//			 leaf.setText(leaf.attribute("name").getText());
//			 System.out.println(leaf.attribute("name").getText());
//			 System.out.println(leaf.getTextTrim());
//			 }
//			 leaf.setText(" ");
			
//				 if(hs.toString().contains(leaf.attribute(1).getValue())){
//					 System.out.println(leaf.attribute(0).getValue()+"@"+leaf.attribute(1).getValue()+"@"
//							 +leaf.attribute(2).getValue()+"@"+leaf.attribute(3).getValue()+"@");
//					 
//				 }
//			
//			 hs.add(leaf.attribute(1).getValue());
	//	 }
	 //   System.out.println(hs.size());
	   // Element aaaElement=(Element)root.selectSingleNode("*/jksjgl/leaf[@name=\"API接口数据\"]");
	    //System.out.println("@@"+aaaElement.getTextTrim()+"@@");
//	    OutputFormat format=OutputFormat.createPrettyPrint();
//	    format.setEncoding("gb2312");
//	    File file=new File(filename);
//	    System.out.println(filename);
//	    FileOutputStream    out = new FileOutputStream(file,false);
		//将内容写到文件中
//	    XMLWriter	xmlout=new XMLWriter(out,format);
//		xmlout.write(document);
//		xmlout.close();
//		out.close();
		
}
public String  aaa(){
	String filename=this.getClass().getResource("/").getPath()+"pageManage.xml";
	return filename;
}
}
