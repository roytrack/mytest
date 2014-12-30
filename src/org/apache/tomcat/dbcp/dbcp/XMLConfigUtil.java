package org.apache.tomcat.dbcp.dbcp;

import java.io.File;
import java.io.PrintStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLConfigUtil {
    public static Document loadInit(String filePath) {
        Document document = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(filePath));
            document.normalize();
            return document;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static boolean updateXML(String filePath) {
        Document document = loadInit(filePath);
        try {
            NodeList nodeList = document.getElementsByTagName("Resource");

            NamedNodeMap attr = nodeList.item(0).getAttributes();

            String driverClassName = attr.getNamedItem("driverClassName").getNodeValue();
            String url = attr.getNamedItem("url").getNodeValue();
            String username = attr.getNamedItem("username").getNodeValue();
            String password = attr.getNamedItem("password").getNodeValue();
            attr.getNamedItem("isEncrypt").setNodeValue("1");
            attr.getNamedItem("driverClassName").setNodeValue(AESUtil.encrypt(driverClassName, "!@#$QWER"));
            attr.getNamedItem("url").setNodeValue(AESUtil.encrypt(url, "!@#$QWER"));
            attr.getNamedItem("username").setNodeValue(AESUtil.encrypt(username, "!@#$QWER"));
            attr.getNamedItem("password").setNodeValue(AESUtil.encrypt(password, "!@#$QWER"));

            saveXML(document, filePath);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean saveXML(Document document, String filePath) {
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }
}

