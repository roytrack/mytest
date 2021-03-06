package com.roytrack.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
			// 如果配置文件中输出路径为空或者路径不存在，则设置默认路径为"/YssReport/"
			if (path.trim().length() == 0 || !(new File(path)).exists()) {
				path = "/YssReport/";
			}
			}catch(Exception e){
				mailList.append("ruanchangming@ysstech.com,");
				path = "/YssReport/";
			}
			String strMailList=mailList.substring(0, mailList.length()-1).toString();
			
			
			data = data.replaceAll("\r\n", "<br>");
			data = data.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			String content = "<font  size=3>您好！"
					+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ date + "日的回归测试比对结果在附件中，请查看.";
			content += "<br>" + data + "</font>";

			String fileNames = "";
			
			
			File sourceFile = new File(path);
			File[] files = sourceFile.listFiles();// 获取附件路径下的文件列表
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						continue;
					} else {
						String filename = files[i].getName();
						if ((filename.equalsIgnoreCase("regressionTest"+date+".txt"))
								||(filename.equalsIgnoreCase("tkprofReport"+date+".txt"))
								||(filename.contains("checkSQL"))//txt excel
								||(filename.contains("checkStatement"))//txt excel
								||(filename.contains("SvnLog.xls"))//svn 日志
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
			File zipfile = new File(path + "/保险" + date + "回归测试结果.rar");
			if (zipfile.exists()) {// 如果该压缩文件已经存在，先删除原压缩文件再压缩文件
				zipfile.delete();
			}
			// 将多个文件压缩成rar格式后添加到附件中，并将原件删除
			zipFiles(srcfile, zipfile);


			for (int i = 0; i < srcfile.length; i++) { //将压缩前的原件删除
				srcfile[i].delete();
			}
		} catch (NoClassDefFoundError e) {
			e.printStackTrace();
			throw new YssException("发送邮件出错" + "\r\n" + e);
		} catch (Exception e) {
			throw new YssException("发送邮件出错"  + "\r\n"  + e);
		}
	}

	/**
	 * 功能:压缩多个文件成一个zip文件
	 * 
	 * @param srcfile：源文件列表
	 * @param zipfile：压缩后的文件
	 */
	public static void zipFiles(File[] srcfile, File zipfile) throws YssException{ 
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
			throw new YssException("压缩文件出错！" + "\r\n" + e);
		}
	}

	/**
	 * 功能:解压缩
	 * 
	 * @param zipfile：需要解压缩的文件
	 * @param descDir：解压后的目标目录
	 */
	public static void unZipFiles(File zipfile, String descDir) throws YssException { 
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