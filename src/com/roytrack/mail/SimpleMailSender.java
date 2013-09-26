package com.roytrack.mail; 
  
import java.util.Date;    
import java.util.Properties;   

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;      
import javax.mail.Message;    
import javax.mail.MessagingException;    
import javax.mail.Multipart;    
import javax.mail.Session;    
import javax.mail.Transport;    
import javax.mail.internet.InternetAddress;    
import javax.mail.internet.MimeBodyPart;    
import javax.mail.internet.MimeMessage;    
import javax.mail.internet.MimeMultipart;    

/** *//**   
* 邮件发送器   
http://www.bt285.cn BT下载
*/    
public class SimpleMailSender  {    
/** *//**   
  * 以文本格式发送邮件   
  * @param mailInfo 待发送的邮件的信息   
  */    
    public boolean sendTextMail(MailSenderInfo mailInfo) {    
      // 判断是否需要身份认证    
      MyAuthenticator authenticator = null;    
      Properties pro = mailInfo.getProperties(); 
      if (mailInfo.isValidate()) {    
	      // 如果需要身份认证，则创建一个密码验证器    
	      authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());    
      }  
      
      // 根据邮件会话属性和密码验证器构造一个发送邮件的session    
      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
      try {    
	      // 根据session创建一个邮件消息    
	      Message mailMessage = new MimeMessage(sendMailSession);    
	      
	      // 创建邮件发送者地址    
	      Address from = new InternetAddress(mailInfo.getFromAddress()); 
	      
	      // 设置邮件消息的发送者    
	      mailMessage.setFrom(from);    
	      
	      // 创建邮件的接收者地址，并设置到邮件消息中       添加分单个发件人和多个发件人的情况  liuyanni
	      if (mailInfo.getToAddresses() != null) {
		      InternetAddress[] sendTo = new InternetAddress[mailInfo.getToAddresses().length]; 
		      for (int i = 0; i < sendTo.length; i++) { 
		    	  sendTo[i] = new InternetAddress(mailInfo.getToAddresses()[i].trim());    	 
		      }
		      mailMessage.setRecipients(Message.RecipientType.TO,sendTo);
	      }else if (mailInfo.getToAddress() != null) {
	    	  InternetAddress sendTo = new InternetAddress(mailInfo.getToAddress().trim());
	    	  mailMessage.setRecipient(Message.RecipientType.TO , sendTo);
		  }
	      
	      // 创建邮件的抄送者地址，并设置到邮件消息中      添加分单个发件人和多个发件人的情况  liuyanni
	      if (mailInfo.getCcses() != null) {
		      InternetAddress[] Ccs = new InternetAddress[mailInfo.getCcses().length]; 
		      for (int i = 0; i < Ccs.length; i++) { 
		    	  Ccs[i] = new InternetAddress(mailInfo.getCcses()[i].trim());    	 
		      }
		      mailMessage.setRecipients(Message.RecipientType.CC, Ccs);
		  }else if (mailInfo.getCcs() != null) {
			  InternetAddress Ccs = new InternetAddress(mailInfo.getCcs().trim());
			  mailMessage.setRecipient(Message.RecipientType.CC, Ccs);
		  }
	      
	      // 设置邮件消息的主题    
	      mailMessage.setSubject(mailInfo.getSubject());  
	      
	      // 设置邮件消息发送的时间    
	      mailMessage.setSentDate(new Date()); 
	      
          //向MimeMessage添加（Multipart代表正文）
	      Multipart mp = new MimeMultipart();
	      MimeBodyPart content = new MimeBodyPart();
          content.setText(mailInfo.getContent());
	      mp.addBodyPart(content);
	      
//	       //添加多个附近
          sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
//          String[] filenames = mailInfo.getAttachFileNames();
//          for (int i = 0; i < filenames.length; i++) {
//              MimeBodyPart attachFile = new MimeBodyPart();
//              String filename = filenames[i].trim();
//              FileDataSource fds = new FileDataSource(filename);
//              attachFile.setDataHandler(new DataHandler(fds));
//              attachFile.setFileName("=?GBK?B?"+enc.encode(fds.getName().getBytes())+"?=");
//              mp.addBodyPart(attachFile);
//          }
//          mailMessage.setContent(mp);
         
	       //添加单个附近
	        MimeBodyPart attachFile = new MimeBodyPart();
	        FileDataSource fds = new FileDataSource(mailInfo.getAttachFileNames().trim());
	        attachFile.setDataHandler(new DataHandler(fds));
	        attachFile.setFileName("=?GBK?B?"+enc.encode(fds.getName().getBytes())+"?=");
	        mp.addBodyPart(attachFile);
	      
	      // 发送邮件    
	      Transport.send(mailMessage);  
	      return true;    
      }catch (NoClassDefFoundError e) {
		  e.printStackTrace();
		  System.out.println("找不到文件\n" + e.getMessage());
	 } catch (MessagingException ex) {    
          ex.printStackTrace();  
          System.out.println("发送邮件失败" + ex.getMessage());
      }    
      return false;    
    }    
       
    /** *//**   
      * 以HTML格式发送邮件   
      * @param mailInfo 待发送的邮件信息   
      */    
    public  boolean sendHtmlMail (MailSenderInfo mailInfo){    
      // 判断是否需要身份认证    
      MyAuthenticator authenticator = null;   
      Properties pro = mailInfo.getProperties(); 
      
      //如果需要身份认证，则创建一个密码验证器     
      if (mailInfo.isValidate()) {    
    	  authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
      }    
      
      // 根据邮件会话属性和密码验证器构造一个发送邮件的session    
      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
      try { 
	      // 根据session创建一个邮件消息    
	      Message mailMessage = new MimeMessage(sendMailSession); 
	      
	      // 创建邮件发送者地址    
	      Address from = new InternetAddress(mailInfo.getFromAddress()); 
	      
	      // 设置邮件消息的发送者    
	      mailMessage.setFrom(from);    
	      
	      // 创建邮件的接收者地址，并设置到邮件消息中      add 添加分单个发件人和多个发件人的情况 liuyanni
	      if (mailInfo.getToAddresses() != null) {
		      InternetAddress[] sendTo = new InternetAddress[mailInfo.getToAddresses().length]; 
		      for (int i = 0; i < sendTo.length; i++) { 
		    	  sendTo[i] = new InternetAddress(mailInfo.getToAddresses()[i].trim());    	 
		      }
		      mailMessage.setRecipients(Message.RecipientType.TO, sendTo);
	      }else if (mailInfo.getToAddress() != null) {
	    	  InternetAddress sendTo = new InternetAddress(mailInfo.getToAddress().trim());
	    	  mailMessage.setRecipient(Message.RecipientType.TO, sendTo);
		  }

	      // 创建邮件的抄送者地址，并设置到邮件消息中     add 添加分单个发件人和多个发件人的情况  liuyanni
	      if (mailInfo.getCcses() != null) {
		      InternetAddress[] Ccs = new InternetAddress[mailInfo.getCcses().length]; 
		      for (int i = 0; i < Ccs.length; i++) { 
		    	  Ccs[i] = new InternetAddress(mailInfo.getCcses()[i].trim());    	 
		      }
		      mailMessage.setRecipients(Message.RecipientType.CC, Ccs);
		  }else if (mailInfo.getCcs() != null) {
			  InternetAddress Ccs = new InternetAddress(mailInfo.getCcs().trim());
			  mailMessage.setRecipient(Message.RecipientType.CC, Ccs);
		  }
	      
	      // 设置邮件消息的主题    
	      mailMessage.setSubject(mailInfo.getSubject());    
	      
	      // 设置邮件消息发送的时间    
	      mailMessage.setSentDate(new Date());   
	      
	      // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象    
	      Multipart mainPart = new MimeMultipart(); 
	      MimeBodyPart content = new MimeBodyPart();
	      
	      // 设置HTML内容   
	      content.setContent(mailInfo.getContent(), "text/html; charset=utf-8");    
	      mainPart.addBodyPart(content);    
          sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
          
          //添加多个附近
//          String[] filenames = mailInfo.getAttachFileNames();
//          for (int i = 0; i < filenames.length; i++) {
//              MimeBodyPart attachFile = new MimeBodyPart();
//              String filename = filenames[i].trim();
//              FileDataSource fds = new FileDataSource(filename);
//              attachFile.setDataHandler(new DataHandler(fds));
//              attachFile.setFileName("=?GBK?B?"+enc.encode(fds.getName().getBytes())+"?=");
//              mainPart.addBodyPart(attachFile);
//          }
         //添加单个附近   如果没有添加附件，则不用发送附件
          if (mailInfo.getAttachFileNames() != null) {
              MimeBodyPart attachFile = new MimeBodyPart();
              FileDataSource fds = new FileDataSource(mailInfo.getAttachFileNames().trim());
              attachFile.setDataHandler(new DataHandler(fds));
              attachFile.setFileName("=?GBK?B?"+enc.encode(fds.getName().getBytes())+"?=");
              mainPart.addBodyPart(attachFile);
		  }
          
          // 将MiniMultipart对象设置为邮件内容  
          mailMessage.setContent(mainPart);
            
	      // 发送邮件    
	      Transport.send(mailMessage);    
	      return true;    
      } catch (MessagingException ex) {    
          ex.printStackTrace();    
      }    
      return false;    
    }    
}   

