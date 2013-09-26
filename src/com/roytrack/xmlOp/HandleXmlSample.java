package com.roytrack.xmlOp;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class HandleXmlSample {
/**
* ����һ��XML�ĵ�,�ĵ������������Ծ���,�ĵ��������ڲ���initialDocument��Document document�����г�ʼ��
*
* @param filename
* �轨�����ļ���,��"test.xml",���Լ�·��
* @return���ز������, 0��ʧ��, 1��ɹ�
*/
public int createXMLFile(String filename) {
// ���ز������, 0��ʧ��, 1��ɹ�
int returnValue = 0;
// ����document����
Document document = DocumentHelper.createDocument();
// ��document��ʼ��������xml����Ҫ�����ݽڵ�
document = initialDocument(document);
try {
// ��document��ʽ�������ָ�������ļ�, ��ʽ����ʹxml���Ͻڵ�������ʽ
formatXMLOutput(document, filename);
// �����ȷ���޸ķ���ֵΪ1
returnValue = 1;
} catch (Exception e) {
System.out.println("��ʽ������ļ�����");
e.printStackTrace();
}
return returnValue;
}
/**
* <b>��������</b>�� ����xml�ַ���,�ĵ��������ڲ���initialDocument��Document document�����г�ʼ��
* <p>
* <b>��������</b>��
* <p>
*
* @return
*/
public String createXMLStr() {
// ����document����
Document document = DocumentHelper.createDocument();
// ��document��ʼ��������xml����Ҫ�����ݽڵ�
document = initialDocument(document);
return document.asXML();
}
/**
* <b>��������</b>�� ��ʼ��document����
* <p>
* <b>��������</b>��
* <p>
*
* @return ���س�ʼ����ɶ���
*/
private Document initialDocument(Document document) {
/** ����XML�ĵ��ĸ�books */
Element booksElement = document.addElement("books");
/** ����һ��ע�� */
booksElement.addComment("This is a test for dom4j");
/** �����һ��book�ڵ� */
Element bookElement = booksElement.addElement("book");
/** ����show�������� */
bookElement.addAttribute("show", "yes");
/** ����title�ڵ� */
Element titleElement = bookElement.addElement("title");
/** Ϊtitle�������� */
titleElement.setText("java�������");
/** ���Ƶ���ɺ�����book */
bookElement = booksElement.addElement("book");
bookElement.addAttribute("show", "yes");
titleElement = bookElement.addElement("title");
titleElement.setText("java���˼��");
bookElement = booksElement.addElement("book");
bookElement.addAttribute("show", "no");
titleElement = bookElement.addElement("title");
titleElement.setText("Head First ���ģʽ");
/** ����owner�ڵ� */
Element ownerElement = booksElement.addElement("owner");
ownerElement.setText("vcom");
return document;
}
/**
* <b>��������</b>�� ��ʽ��xml�����ʽ��ָ������ļ��������ñ����ʽ
* <p>
* <b>��������</b>��
* <p>
*
* @param document
* @param filename
* �ļ���
* @param encoding
* �����ʽ
* @throws Exception
*/
private void formatXMLOutput(Document document, String filename) throws Exception {
OutputFormat format = OutputFormat.createPrettyPrint();
// Ĭ��ΪUtf-8 ���룬���Ը�����Ҫ�ı�����ʽ
// format.setEncoding("GBK");
/** ��document�е�����д���ļ��� */
XMLWriter writer = new XMLWriter(new FileOutputStream(new File(filename)), format);
writer.write(document);
writer.close();
}
/**
* �޸�XML�ļ�������,�����Ϊһ�����ļ� �ص�����dom4j�������ӽڵ�,�޸Ľڵ�,ɾ���ڵ�
*
* @param filename
* �޸Ķ����ļ�
* @param newfilename
* �޸ĺ����Ϊ���ļ�
* @return ���ز������, 0��ʧ��, 1��ɹ�
*/
public int modiXMLFile(String filename, String newFilename) {
int returnValue = 0;
try {
SAXReader saxReader = new SAXReader();
Document document = saxReader.read(new java.io.File(filename));
/** �޸�����֮һ: ���book�ڵ���show���Ե�����Ϊyes,���޸ĳ�no */
/** ����xpath���Ҷ��� */
List list = document.selectNodes("/books/book/@show");
Iterator iter = list.iterator();
while (iter.hasNext()) {
Attribute attribute = (Attribute) iter.next();
if (attribute.getValue().equals("yes")) {
attribute.setValue("no");
}
}
/**
* �޸�����֮��: ��owner�����ݸ�Ϊ"zzvcom"
* ����owner�ڵ��м���date�ڵ�,date�ڵ������Ϊ2009-10-22,��Ϊdate�ڵ����һ������type
*/
list = document.selectNodes("/books/owner");
iter = list.iterator();
if (iter.hasNext()) {
Element ownerElement = (Element) iter.next();
ownerElement.setText("�����޸�");
Element dateElement = ownerElement.addElement("date");
dateElement.setText("2009-10-22");
dateElement.addAttribute("type", "����");
}
/** �޸�����֮��: ��title����Ϊ"Head First ���ģʽ",��ɾ���ýڵ� */
list = document.selectNodes("/books/book");
iter = list.iterator();
while (iter.hasNext()) {
Element bookElement = (Element) iter.next();
Iterator iterator = bookElement.elementIterator("title");
while (iterator.hasNext()) {
Element titleElement = (Element) iterator.next();
if (titleElement.getText().equals("Head First ���ģʽ")) {
bookElement.remove(titleElement);
}
}
}
formatXMLOutput(document, newFilename);
/** ִ�гɹ�,�践��1 */
returnValue = 1;
} catch (Exception ex) {
ex.printStackTrace();
}
return returnValue;
}
/**
* <b>��������</b>�� �õ�xml�ַ�����owner�ڵ��ֵ
* <p>
* <b>��������</b>��
* <p>
*
* @param xmlStr
* @return
*/
public String getOwnerValue(String xmlStr) {
Document document;
try {
document = DocumentHelper.parseText(xmlStr);
Element rootElement = document.getRootElement();
return rootElement.element("owner").getText();
} catch (DocumentException e) {
e.printStackTrace();
}
return null;
}
/**
* <b>��������</b>�� �õ�xml�ַ�����book�ڵ��ֵ�ļ���
* <p>
* <b>��������</b>��
* <p>
*
* @param xmlStr
* @return
*/
public List getBookListValue(String xmlStr) {
Document document;
List list = new ArrayList();
try {
document = DocumentHelper.parseText(xmlStr);
Element rootElement = document.getRootElement();
List<Element> elementList = rootElement.elements("book");
for (Element element : elementList) {
list.add(element.elementText("title"));
}
return list;
} catch (DocumentException e) {
e.printStackTrace();
}
return null;
}
public static void main(String[] args) {
HandleXmlSample handleXml = new HandleXmlSample();
// ����xml�ַ���
String xmlStr = handleXml.createXMLStr();
// �õ�xml�ַ�����Ψһ�ڵ�"owner"���Ե�ֵ
String ownerValue = handleXml.getOwnerValue(xmlStr);
System.out.println("bookӵ���ߣ�" + ownerValue);
// �õ�xml�ַ���������"book"�µ�"title"��ֵ
List list = handleXml.getBookListValue(xmlStr);
for (Iterator it = list.iterator(); it.hasNext();) {
System.out.println("book����: " + it.next());
}

// ����xml�ļ��Ĳ��Դ���
handleXml.createXMLFile("����.xml");
// �޸�������xml��ע��:�˷����ڲ����õ�selectNodes������Ҫjaxen-1.1-beta-6.jar����������
handleXml.modiXMLFile("����.xml", "�����޸ĺ�.xml");
}
}

