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
* �ʼ�������   
http://www.bt285.cn BT����
*/    
public class SimpleMailSender  {    
/** *//**   
  * ���ı���ʽ�����ʼ�   
  * @param mailInfo �����͵��ʼ�����Ϣ   
  */    
    public boolean sendTextMail(MailSenderInfo mailInfo) {    
      // �ж��Ƿ���Ҫ�����֤    
      MyAuthenticator authenticator = null;    
      Properties pro = mailInfo.getProperties(); 
      if (mailInfo.isValidate()) {    
	      // �����Ҫ�����֤���򴴽�һ��������֤��    
	      authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());    
      }  
      
      // �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session    
      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
      try {    
	      // ����session����һ���ʼ���Ϣ    
	      Message mailMessage = new MimeMessage(sendMailSession);    
	      
	      // �����ʼ������ߵ�ַ    
	      Address from = new InternetAddress(mailInfo.getFromAddress()); 
	      
	      // �����ʼ���Ϣ�ķ�����    
	      mailMessage.setFrom(from);    
	      
	      // �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��       ��ӷֵ��������˺Ͷ�������˵����  liuyanni
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
	      
	      // �����ʼ��ĳ����ߵ�ַ�������õ��ʼ���Ϣ��      ��ӷֵ��������˺Ͷ�������˵����  liuyanni
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
	      
	      // �����ʼ���Ϣ������    
	      mailMessage.setSubject(mailInfo.getSubject());  
	      
	      // �����ʼ���Ϣ���͵�ʱ��    
	      mailMessage.setSentDate(new Date()); 
	      
          //��MimeMessage��ӣ�Multipart�������ģ�
	      Multipart mp = new MimeMultipart();
	      MimeBodyPart content = new MimeBodyPart();
          content.setText(mailInfo.getContent());
	      mp.addBodyPart(content);
	      
//	       //��Ӷ������
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
         
	       //��ӵ�������
	        MimeBodyPart attachFile = new MimeBodyPart();
	        FileDataSource fds = new FileDataSource(mailInfo.getAttachFileNames().trim());
	        attachFile.setDataHandler(new DataHandler(fds));
	        attachFile.setFileName("=?GBK?B?"+enc.encode(fds.getName().getBytes())+"?=");
	        mp.addBodyPart(attachFile);
	      
	      // �����ʼ�    
	      Transport.send(mailMessage);  
	      return true;    
      }catch (NoClassDefFoundError e) {
		  e.printStackTrace();
		  System.out.println("�Ҳ����ļ�\n" + e.getMessage());
	 } catch (MessagingException ex) {    
          ex.printStackTrace();  
          System.out.println("�����ʼ�ʧ��" + ex.getMessage());
      }    
      return false;    
    }    
       
    /** *//**   
      * ��HTML��ʽ�����ʼ�   
      * @param mailInfo �����͵��ʼ���Ϣ   
      */    
    public  boolean sendHtmlMail (MailSenderInfo mailInfo){    
      // �ж��Ƿ���Ҫ�����֤    
      MyAuthenticator authenticator = null;   
      Properties pro = mailInfo.getProperties(); 
      
      //�����Ҫ�����֤���򴴽�һ��������֤��     
      if (mailInfo.isValidate()) {    
    	  authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
      }    
      
      // �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session    
      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
      try { 
	      // ����session����һ���ʼ���Ϣ    
	      Message mailMessage = new MimeMessage(sendMailSession); 
	      
	      // �����ʼ������ߵ�ַ    
	      Address from = new InternetAddress(mailInfo.getFromAddress()); 
	      
	      // �����ʼ���Ϣ�ķ�����    
	      mailMessage.setFrom(from);    
	      
	      // �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��      add ��ӷֵ��������˺Ͷ�������˵���� liuyanni
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

	      // �����ʼ��ĳ����ߵ�ַ�������õ��ʼ���Ϣ��     add ��ӷֵ��������˺Ͷ�������˵����  liuyanni
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
	      
	      // �����ʼ���Ϣ������    
	      mailMessage.setSubject(mailInfo.getSubject());    
	      
	      // �����ʼ���Ϣ���͵�ʱ��    
	      mailMessage.setSentDate(new Date());   
	      
	      // MiniMultipart����һ�������࣬����MimeBodyPart���͵Ķ���    
	      Multipart mainPart = new MimeMultipart(); 
	      MimeBodyPart content = new MimeBodyPart();
	      
	      // ����HTML����   
	      content.setContent(mailInfo.getContent(), "text/html; charset=utf-8");    
	      mainPart.addBodyPart(content);    
          sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
          
          //��Ӷ������
//          String[] filenames = mailInfo.getAttachFileNames();
//          for (int i = 0; i < filenames.length; i++) {
//              MimeBodyPart attachFile = new MimeBodyPart();
//              String filename = filenames[i].trim();
//              FileDataSource fds = new FileDataSource(filename);
//              attachFile.setDataHandler(new DataHandler(fds));
//              attachFile.setFileName("=?GBK?B?"+enc.encode(fds.getName().getBytes())+"?=");
//              mainPart.addBodyPart(attachFile);
//          }
         //��ӵ�������   ���û����Ӹ��������÷��͸���
          if (mailInfo.getAttachFileNames() != null) {
              MimeBodyPart attachFile = new MimeBodyPart();
              FileDataSource fds = new FileDataSource(mailInfo.getAttachFileNames().trim());
              attachFile.setDataHandler(new DataHandler(fds));
              attachFile.setFileName("=?GBK?B?"+enc.encode(fds.getName().getBytes())+"?=");
              mainPart.addBodyPart(attachFile);
		  }
          
          // ��MiniMultipart��������Ϊ�ʼ�����  
          mailMessage.setContent(mainPart);
            
	      // �����ʼ�    
	      Transport.send(mailMessage);    
	      return true;    
      } catch (MessagingException ex) {    
          ex.printStackTrace();    
      }    
      return false;    
    }    
}   

