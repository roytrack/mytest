package com.roytrack.mail;

import java.util.Date;

public class SendmailDemo {
	public static void main(String[] args) {
		
		Date date =new Date();
		// �������Ҫ�������ʼ�
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		// mailInfo.setMailServerHost("mail.ysstech.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);

		mailInfo.setUserName("RegressionYSS@163.com");
		mailInfo.setPassword("ssynoisserger");// ������������

		mailInfo.setFromAddress("RegressionYSS@163.com");

		String toAddress = "ruanchangming@ysstech.com,rcm8848@163.com";// �ʼ�������
		String toCcs = "rcm8848@163.com,ruanchangming@ysstech.com"; //����
		mailInfo.setToAddresses(toAddress.split(","));
		mailInfo.setCcses(toCcs.split(",") );
		mailInfo.setAttachFileNames("/dbsetting.txt");

		mailInfo.setSubject(date + "�ջع���ԱȶԽ��"); // �����������
		
	 
		String content = "<font color='#FF00FF' size=3>���ã�"
				+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ date + "�յĻع���ԱȶԽ���ڸ����У���鿴.";
		 
		mailInfo.setContent(content); // ������������

		
		SimpleMailSender sms = new SimpleMailSender();

		// sms.sendTextMail(mailInfo);//���������ʽ
		sms.sendHtmlMail(mailInfo);// ����html��ʽ
	}
}
