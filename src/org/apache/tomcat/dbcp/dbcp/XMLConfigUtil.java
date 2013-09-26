/*    */ package org.apache.tomcat.dbcp.dbcp;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.PrintStream;
/*    */ import javax.xml.parsers.DocumentBuilder;
/*    */ import javax.xml.parsers.DocumentBuilderFactory;
/*    */ import javax.xml.transform.Transformer;
/*    */ import javax.xml.transform.TransformerFactory;
/*    */ import javax.xml.transform.dom.DOMSource;
/*    */ import javax.xml.transform.stream.StreamResult;
/*    */ import org.w3c.dom.Document;
/*    */ import org.w3c.dom.NamedNodeMap;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.NodeList;
/*    */ 
/*    */ public class XMLConfigUtil
/*    */ {
/*    */   public static Document loadInit(String filePath)
/*    */   {
/* 23 */     Document document = null;
/*    */     try {
/* 25 */       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/* 26 */       DocumentBuilder builder = factory.newDocumentBuilder();
/* 27 */       document = builder.parse(new File(filePath));
/* 28 */       document.normalize();
/* 29 */       return document;
/*    */     } catch (Exception e) {
/* 31 */       e.printStackTrace();
/* 32 */       System.out.println(e.getMessage());
/* 33 */     }return null;
/*    */   }
/*    */ 
/*    */   public static boolean updateXML(String filePath)
/*    */   {
/* 44 */     Document document = loadInit(filePath);
/*    */     try
/*    */     {
/* 47 */       NodeList nodeList = document.getElementsByTagName("Resource");
/*    */ 
/* 49 */       NamedNodeMap attr = nodeList.item(0).getAttributes();
/*    */ 
/* 51 */       String driverClassName = attr.getNamedItem("driverClassName").getNodeValue();
/* 52 */       String url = attr.getNamedItem("url").getNodeValue();
/* 53 */       String username = attr.getNamedItem("username").getNodeValue();
/* 54 */       String password = attr.getNamedItem("password").getNodeValue();
/* 55 */       attr.getNamedItem("isEncrypt").setNodeValue("1");
/* 56 */       attr.getNamedItem("driverClassName").setNodeValue(AESUtil.encrypt(driverClassName, "!@#$QWER"));
/* 57 */       attr.getNamedItem("url").setNodeValue(AESUtil.encrypt(url, "!@#$QWER"));
/* 58 */       attr.getNamedItem("username").setNodeValue(AESUtil.encrypt(username, "!@#$QWER"));
/* 59 */       attr.getNamedItem("password").setNodeValue(AESUtil.encrypt(password, "!@#$QWER"));
/*    */ 
/* 61 */       saveXML(document, filePath);
/* 62 */       return true;
/*    */     } catch (Exception e) {
/* 64 */       e.printStackTrace();
/* 65 */       System.out.println(e.getMessage());
/* 66 */     }return false;
/*    */   }
/*    */ 
/*    */   public static boolean saveXML(Document document, String filePath)
/*    */   {
/*    */     try
/*    */     {
/* 77 */       TransformerFactory tFactory = TransformerFactory.newInstance();
/* 78 */       Transformer transformer = tFactory.newTransformer();
/*    */ 
/* 80 */       DOMSource source = new DOMSource(document);
/* 81 */       StreamResult result = new StreamResult(new File(filePath));
/* 82 */       transformer.transform(source, result);
/* 83 */       return true;
/*    */     } catch (Exception e) {
/* 85 */       e.printStackTrace();
/* 86 */       System.out.println(e.getMessage());
/* 87 */     }return false;
/*    */   }
/*    */ }

/* Location:           D:\赛迪文件\工作文件_detail\20130829tomcat的jndi加密\玉伟\tomcat-dbcp.jar
 * Qualified Name:     org.apache.tomcat.dbcp.dbcp.XMLConfigUtil
 * JD-Core Version:    0.6.0
 */