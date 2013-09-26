package com.roytrack.xmlOp;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;

public class TransformerTest {

public static void main(String[] args) throws Exception {
System.out.println(TransformerTest.class.getResource("/").getPath());
TransformerFactory factory = TransformerFactory.newInstance();
Transformer transformer = factory.newTransformer(new StreamSource(new File("aa.xsl")));
SAXReader reader = new SAXReader();
DocumentSource source = new DocumentSource(reader.read("aa.xml"));
DocumentResult result = new DocumentResult();
transformer.transform(source, result);
Document transformedDoc = result.getDocument();
toPretyHtml(transformedDoc);
}

public static Document read(String fileName) throws MalformedURLException, DocumentException {

SAXReader reader = new SAXReader();
Document document = reader.read(new File(fileName));

return document;
}

public static void toPretyHtml(Document document) throws Exception {

StringWriter sw = new StringWriter();
OutputFormat format = OutputFormat.createPrettyPrint();
HTMLWriter writer;
try {
writer = new HTMLWriter(sw, format);
Document doc = DocumentHelper.parseText(document.asXML());
writer.write(doc);
writer.flush();
System.out.println(sw.toString());
} catch (UnsupportedEncodingException e) {
e.printStackTrace();
} catch (IOException e) {
e.printStackTrace();
}
}

// public static Document styleDocument(Document document, String stylesheet)
// throws Exception {
//
// TransformerFactory factory = TransformerFactory.newInstance();
// Transformer transformer = factory.newTransformer(new StreamSource(stylesheet));
// DocumentSource source = new DocumentSource(document);
// DocumentResult result = new DocumentResult();
// transformer.transform(source, result);
// Document transformedDoc = result.getDocument();
//
// return transformedDoc;
// }
//
// public static void main(String[] argv) throws Exception {
//
// Document document = read("test1.xml");
// Document transformedDoc = styleDocument(document, "test1.xsl");
//
// //System.out.println(transformedDoc.asXML());
// toPretyHtml(transformedDoc);
// }
}