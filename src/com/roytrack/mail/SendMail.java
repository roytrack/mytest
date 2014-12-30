package com.roytrack.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;



import org.apache.tools.zip.ZipFile;
import  org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;







import com.yss.util.YssException;


public class SendMail {
	


	public void doSendMail(String date, String data) throws YssException {
		try {
			StringBuffer mailList=new StringBuffer();
			String path="";
			try{
			SAXReader reader = new SAXReader();  
			Document doc = reader.read("/ParametersInfo.xml");
			Element root=doc.getRootElement();
			Element MailParameters=root.element("MailParameters");
			Element MailToList=MailParameters.element("MailToList");
			List MailTo=MailToList.elements();
			if(MailTo.size()==0){
				mailList.append("ruanchangming@ysstech.com,");
			}	
			else{
			for(int i=0;i<MailTo.size();i++){
				mailList.append(((Element)MailTo.get(i)).getTextTrim()+",");
					}	
				}	
			Element Path = MailParameters.element("Path");
			 path = (Path==null?"":((Element)Path).getTextTrim());
			// ��������ļ������·��Ϊ�ջ���·�������ڣ�������Ĭ��·��Ϊ"/YssReport/"
			if (path.trim().length() == 0 || !(new File(path)).exists()) {
				path = "/YssReport/";
			}
			}catch(Exception e){
				mailList.append("ruanchangming@ysstech.com,");
				path = "/YssReport/";
			}
			String strMailList=mailList.substring(0, mailList.length()-1).toString();
			
			// �������Ҫ�������ʼ�
			MailSenderInfo mailInfo = new MailSenderInfo();
			mailInfo.setMailServerHost("smtp.163.com");
			// mailInfo.setMailServerHost("mail.ysstech.com");
			mailInfo.setMailServerPort("25");
			mailInfo.setValidate(true);
			
			mailInfo.setUserName("RegressionYSS@163.com");
			mailInfo.setPassword("ssynoisserger");// ������������

			mailInfo.setFromAddress("RegressionYSS@163.com");
			
			String toAddress = strMailList;// �ʼ�������
			String toCcs = "ruanchangming@ysstech.com"; //����
			String toa=toAddress;
			String toc="ruanchangming@ysstech.com,rcm8848@163.com";
			mailInfo.setCcs(toCcs);
			if (toa.length() != 0) {// �ʼ�������
				if (toa.split(",").length==1) {
					mailInfo.setToAddress(toa);
					mailInfo.setToAddresses(null);
				}else {
					mailInfo.setToAddress(null);
					mailInfo.setToAddresses(toa.split(","));
				}
			}else {
				mailInfo.setToAddress(null);
				mailInfo.setToAddresses(null);
			}
			if (toc.length() != 0) {//����
				if (toc.split(",").length==1) {
					mailInfo.setCcs(toc);
					mailInfo.setCcses(null);
				}else {
					mailInfo.setCcs(null);
					mailInfo.setCcses(toc.split(","));
				}
			}else {
				mailInfo.setCcs(null);
				mailInfo.setCcses(null);
			}
			mailInfo.setSubject("����"+date + "�ջع���ԱȶԽ��"); // �����������
			
			data = data.replaceAll("\r\n", "<br>");
			data = data.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			String content = "<font  size=3>���ã�"
					+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ date + "�յĻع���ԱȶԽ���ڸ����У���鿴.";
			content += "<br>" + data + "</font>";
			mailInfo.setContent(content); // ������������

			String fileNames = "";
			
			
			File sourceFile = new File(path);
			File[] files = sourceFile.listFiles();// ��ȡ����·���µ��ļ��б�
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						continue;
					} else {
						String filename = files[i].getName();
						if ((filename.equalsIgnoreCase("regressionTest"+date+".txt"))
								||(filename.equalsIgnoreCase("tkprofReport"+date+".txt"))
								||(filename.equalsIgnoreCase("checkSQL.txt"))
								||(filename.equalsIgnoreCase("checkStatement.txt"))
								||(filename.equalsIgnoreCase("report"+date+".html"))
								||(filename.equalsIgnoreCase("RegressTable" + date + ".xls"))) {
							if (fileNames.length() > 0) {
								fileNames += ",";
							}
							fileNames += files[i].getPath();
						}
					}
				}
			}
			File thefindbugs=new File("D:/yss_gz_ant/report/bxfund.xml");
			if(thefindbugs.exists()){
				fileNames+=",D:/yss_gz_ant/report/bxfund.xml";
			}
			File[] srcfile = new File[fileNames.split(",").length];
			for (int i = 0; i < fileNames.split(",").length; i++) {
				srcfile[i] = new File(fileNames.split(",")[i]);
			}
			File zipfile = new File(path + "/����" + date + "�ع���Խ��.rar");
			if (zipfile.exists()) {// �����ѹ���ļ��Ѿ����ڣ���ɾ��ԭѹ���ļ���ѹ���ļ�
				zipfile.delete();
			}
			// ������ļ�ѹ����rar��ʽ����ӵ������У�����ԭ��ɾ��
			zipFiles(srcfile, zipfile);
			mailInfo.setAttachFileNames(zipfile.getPath());

			// �������Ҫ�������ʼ�
			SimpleMailSender sms = new SimpleMailSender();

			// sms.sendTextMail(mailInfo);//���������ʽ
			sms.sendHtmlMail(mailInfo);// ����html��ʽ
			for (int i = 0; i < srcfile.length; i++) { //��ѹ��ǰ��ԭ��ɾ��
				srcfile[i].delete();
			}
		} catch (NoClassDefFoundError e) {
			e.printStackTrace();
			throw new YssException("�����ʼ�����" + "\r\n" + e);
		} catch (Exception e) {
			throw new YssException("�����ʼ�����"  + "\r\n"  + e);
		}
	}

	/**
	 * ����:ѹ������ļ���һ��zip�ļ�
	 * 
	 * @param srcfile��Դ�ļ��б�
	 * @param zipfile��ѹ������ļ�
	 */
	public static void zipFiles(File[] srcfile, File zipfile) throws YssException{// liuynni
																// 2012-05-03
		byte[] buf = new byte[1024];
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					zipfile));
			for (int i = 0; i < srcfile.length; i++) {
				FileInputStream in = new FileInputStream(srcfile[i]);
				out.putNextEntry(new ZipEntry(srcfile[i].getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new YssException("ѹ���ļ�����" + "\r\n" + e);
		}
	}

	/**
	 * ����:��ѹ��
	 * 
	 * @param zipfile����Ҫ��ѹ�����ļ�
	 * @param descDir����ѹ���Ŀ��Ŀ¼
	 */
	public static void unZipFiles(File zipfile, String descDir) throws YssException {// liuynni
																	// 2012-05-03
		try {
			ZipFile zf = new ZipFile(zipfile);
			for (Enumeration entries = zf.getEntries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String zipEntryName = entry.getName();
				InputStream in = zf.getInputStream(entry);
				OutputStream out = new FileOutputStream(descDir + zipEntryName);
				byte[] buf1 = new byte[1024];
				int len;
				while ((len = in.read(buf1)) > 0) {
					out.write(buf1, 0, len);
				}
				in.close();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}