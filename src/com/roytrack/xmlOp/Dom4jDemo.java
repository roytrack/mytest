package com.roytrack.xmlOp;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import java.util.HashMap;
import java.util.Iterator;

import javax.print.Doc;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;


import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
/***
 * 
 * 生成一个xml，读取一个xml，先写后读，两个方法
 * 
 * 
 * **/
public class Dom4jDemo {
	public static void createBackupFile(String path) throws IOException{  
        File file = new File(path);  
        if(!file.exists()){  
            try {  
                file.createNewFile();  
            } catch (IOException e) {  
              
                e.printStackTrace();  
            }  
        }  
         // OutputFormat opt=new OutputFormat().createPrettyPrint();
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("GBK");
            XMLWriter writer = new XMLWriter(new FileOutputStream(path),format);  
              
            Document doc = DocumentHelper.createDocument();    
            Element root = doc.addElement("report");    
            //一级节点
            Element dataStatistics=root.addElement("dataStatistics");//数据统计
            Element resultInDetail=root.addElement("ResultInDetail");//结果明细
            //二级节点及其子节点
            Element reportDate=dataStatistics.addElement("reportDate");//报告日期
             reportDate.addElement("beginDate");//开始日期
             reportDate.addElement("endDate");//结束日期
             reportDate.addElement("today");//今天
            Element executeTime=dataStatistics.addElement("executeTime");//执行时间
            executeTime.addElement("beginTime");//开始时间
            executeTime.addElement("endTime");//结束时间
            Element tableRecord=dataStatistics.addElement("tableRecord");//表记录数
            Element table=tableRecord.addElement("table");//表节点
            table.addElement("tableName");//表名
            table.addElement("originalTable");//原始记录数
            table.addElement("tempTable");//重新生成记录数
            Element exceptionStats=dataStatistics.addElement("exceptionStats");//异常情况
            Element exception=exceptionStats.addElement("exception");//异常节点
            exception.addElement("exceptionText");//异常信息
            exception.addElement("exceptionNo");//异常数
            Element tradeDataElement=resultInDetail.addElement("tradeDataElement");//处理交易数据的详细分析结果（就是把fcwfx的内容显示出来，初步是显示出不一致的字段和主键）
            Element voucherElement=resultInDetail.addElement("voucherElement");//生成凭证的详细分析结果（就是把fcwfx的内容显示出来，初步是显示出不一致的字段和主键）
            Element valuationTableElement=resultInDetail.addElement("valuationTableElement");//估值表的详细分析结果（就是把fcwfx的内容显示出来，初步是显示出不一致的字段和主键）
            writer.write(doc);    
            writer.close();   
              
     
    }  
      
    public static void recoveryFromBackup(String path) throws DocumentException{  
     
           
              
            SAXReader reader = new SAXReader();     
            Document document = reader.read(new File(path));  
            Element rootElm  =  document.getRootElement();    
            Element TradeData=rootElm.element("TradeData");
            Element sss=TradeData.element("aaa");
//            Iterator it =TradeData.elementIterator();
            if(sss==null){
            	System.out.println("没有取到这个元素");
            }else{
            System.out.println(sss.getText());
            }

//          while(it.hasNext()){
//        	  Element condition=(Element)it.next();
//        	  System.out.println("the condition attribute  error is : "+condition.attribute("error").getValue());
//        	  System.out.println("the condition attribute  sql is : "+condition.attribute("sql").getValue());
//        	  System.out.println("the condition Qname is : "+condition.getQName().getQualifiedName());
//        	  Node errorNum=condition.selectSingleNode("ErrorNum");
//        	  System.out.println("the condition get single node errorNum trim text : "+errorNum.getText());
//          }
            
     
            }
      
           
          
              
              public static void changeContent(String qname,String text) throws DocumentException, IOException{
            	  
            	  SAXReader reader = new SAXReader();     
                  Document document = reader.read(new File("D://regressReport.xml"));  
                  Element rootElm  =  document.getRootElement();    
                  Element TradeData=rootElm.element("report");
                  OutputFormat format = OutputFormat.createPrettyPrint();
                  format.setEncoding("GBK");
                  XMLWriter writer = new XMLWriter(new FileOutputStream("D://regressReport.xml"),format);  
                  Node changeElement =rootElm.selectSingleNode(qname);
                  changeElement.setText(text);
                  writer.write(document);
                  writer.close();
        
              }
              /***
               * 用来增加子元素（先以表记录为例  tableRecord   子元素tableName 属性  originalTable  tempTable ）
             * @throws IOException 
             * @throws DocumentException 
               * 
               * */
              public static void addChildren(HashMap hm) throws IOException, DocumentException{
            	  String tableName=hm.get("tableName").toString();
            	  String originalTable=hm.get("originalTable").toString();
            	  String tempTable=hm.get("tempTable").toString();
            	  SAXReader reader = new SAXReader();     
                  Document document = reader.read(new File("D://regressReport.xml"));  
                  Element rootElm  =  document.getRootElement();    
                  OutputFormat format = OutputFormat.createPrettyPrint();
                  format.setEncoding("GBK");
                  XMLWriter writer = new XMLWriter(new FileOutputStream("D://regressReport.xml"),format);  
                  Node changeNode=rootElm.selectSingleNode("dataStatistics/tableRecord");
                 
                  Element changeElement=(Element)changeNode;
                  Element table=changeElement.addElement("tableName");
                  table.setText(tableName);
                  table.addAttribute("originalTable", originalTable);
                  table.addAttribute("tempTable", tempTable);
                  writer.write(document);
                  writer.close();
            	  
            	  
              }
          
  
    	
    
public void xmlToHtml() throws DocumentException, TransformerException, IOException{
	TransformerFactory factory = TransformerFactory.newInstance();
	Transformer transformer = factory.newTransformer(new StreamSource(new File("src/report.xsl")));

	OutputFormat format = OutputFormat.createPrettyPrint();
	format.setEncoding("GBK");
	StringWriter sw = new StringWriter();
	HTMLWriter writer = new HTMLWriter(new FileOutputStream("report.html"), format);
	SAXReader reader = new SAXReader();
	DocumentSource source = new DocumentSource(reader.read("regressReport.xml"));
	DocumentResult result = new DocumentResult();
	transformer.transform(source, result);
	Document transformedDoc = result.getDocument();
	writer.write(transformedDoc);
	writer.flush();
	}
      
    public static void main(String[] args) throws DocumentException, IOException, TransformerException {  
    	Dom4jDemo dd=new Dom4jDemo();
    	//createBackupFile("D://regressReport.xml");
    	//changeContent("dataStatistics/tableRecord/tableName","RE_HZJKQSYSS_COMPARE");
       // recoveryFromBackup("D://ErrorCondition.xml");
    	//HashMap hm=new HashMap();
//    	hm.put("tableName", "HZJKQSYSS");
//    	hm.put("originalTable", "10000");
//    	hm.put("tempTable", "8000");
//    	addChildren(hm);
    	//System.out.println(dd.getClass().getResource("/"));
    	
    	//System.out.println(Dom4jDemo.class.getClassLoader().getResource("").getPath() );
    	//dd.xmlToHtml();
    	SAXReader reader=new SAXReader();
    	File file=new File("/ParametersInfo.xml");
    	Document doc=reader.read(file);
    	Element aa=(Element)doc.selectSingleNode("/*/*/TransData");
    	System.out.println(aa.getText().trim().length());
    } 
}
