package com.roytrack.mail;

import java.util.Date;

public class SendmailDemo {
	public static void main(String[] args) {
		
		Date date =new Date();
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		// mailInfo.setMailServerHost("mail.ysstech.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);

		mailInfo.setUserName("RegressionYSS@163.com");
		mailInfo.setPassword("ssynoisserger");// 您的邮箱密码

		mailInfo.setFromAddress("RegressionYSS@163.com");

		String toAddress = "ruanchangming@ysstech.com,rcm8848@163.com";// 邮件接收者
		String toCcs = "rcm8848@163.com,ruanchangming@ysstech.com"; //抄送
		mailInfo.setToAddresses(toAddress.split(","));
		mailInfo.setCcses(toCcs.split(",") );
		mailInfo.setAttachFileNames("/dbsetting.txt");

		mailInfo.setSubject(date + "日回归测试比对结果"); // 设置邮箱标题
		
	 
		String content = "<font color='#FF00FF' size=3>您好！"
				+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ date + "日的回归测试比对结果在附件中，请查看.";
		 
		mailInfo.setContent(content); // 设置邮箱内容

		
		SimpleMailSender sms = new SimpleMailSender();

		// sms.sendTextMail(mailInfo);//发送文体格式
		sms.sendHtmlMail(mailInfo);// 发送html格式
	}
}
