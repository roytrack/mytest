package com.yss.xmlparse;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class XmlWriteTest {
static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
static DocumentBuilder builder = null;
public static void readXML() {
try {
builder = factory .newDocumentBuilder();
Document document = builder.parse(new File("E:\\testFiles\\test.xml"));
Element rootElement = document.getDocumentElement();
NodeList list = rootElement.getElementsByTagName("Header");
Element element = (Element) list.item(0);
System.out.println(element.getChildNodes().item(0).getNodeValue());
} catch (Exception e) {
System.out.println("exception:" + e.getMessage());
}
}
public static void writeXML(Document document,String filename)
{
try {
builder = factory .newDocumentBuilder();
//Document document = builder.parse(new File("E:\\testFiles\\test.xml"));
document.normalize();
/** 将document中的内容写入文件中 */
TransformerFactory tFactory = TransformerFactory.newInstance();
Transformer transformer = tFactory.newTransformer();
transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//编码
DOMSource source = new DOMSource(document);
PrintWriter pw = new PrintWriter(new FileOutputStream(filename));
StreamResult result = new StreamResult(pw);
transformer.transform(source, result);
} catch (Exception e) {
e.printStackTrace();
}
}
public static void updateXML()
{
try {
builder = factory .newDocumentBuilder();
Document document = builder.parse(new File("E:\\testFiles\\test.xml"));
Node root = document.getDocumentElement();
/**如果root有子元素*/
if(root.hasChildNodes())
{
NodeList ftpnodes = root.getChildNodes();
/**循环取得ftpnodes所有节点*/
for(int i=0;i<ftpnodes.getLength();i++)
{
Node ftpList = ftpnodes.item(i);
System.out.println(ftpList.getTextContent());
}
for(int i=0;i<ftpnodes.getLength();i++)
{
Node ftpList = ftpnodes.item(i);
ftpList.setTextContent(ftpList.getTextContent()+" update");
}
}
writeXML(document,"E:\\testFiles\\test2.xml");
} catch (Exception e) {
e.printStackTrace();
}
}
public static void main(String[] args) throws SAXException, IOException
{
//read

//write
File f=new File("D:\\ParametersInfo.xml");
System.out.println(f.getAbsolutePath());
FileReader fops=new FileReader(f);
BufferedReader bfr=new BufferedReader(fops);
StringBuffer sbf=new StringBuffer();
while(bfr.ready()){
	sbf.append(bfr.readLine());
	System.out.println(bfr.readLine());
}
String fileString=sbf.toString();
System.out.println(fileString);

Document document = builder.parse("D:\\ParametersInfo.xml");
writeXML(document,"E:\\testFiles\\test.xml");
readXML();

//update
updateXML();
}
}