package com.qsms.util;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class EmailSender {
	private static final String charset = "GBK";
	private static final String defaultMimetype = "text/plain";
	private static final String smtp = "smtp.ym.163.com";
	private static final String username = "service@qishimeishi.com";
	private static final String password = "qsms2015@hz8692";
	private static final String sendMail = "service@qishimeishi.com"; // 发件人邮件

	/**
	 * 发送邮件
	 * 
	 * @param receiver
	 *            ：多个收件人
	 * @param subject
	 *            ：标题
	 * @param mailContent
	 *            ：邮件内容
	 * @param mimetype
	 *            ：内容类型，默认为text/plain,如果要发送HTML内容,应设置为text/html
	 */
	public static void send(String receiver, String subject,
			String mailContent, String mimetype) {
		send(new String[] { receiver }, subject, mailContent, mimetype);
	}

	/**
	 * 发送邮件：
	 * 
	 * @param receiver
	 *            ：单一收件人
	 * @param subject
	 *            ：标题
	 * @param mailContent
	 *            ：邮件内容
	 * @param mimetype
	 *            ：内容类型，默认为text/plain,如果要发送HTML内容,应设置为text/html
	 */
	public static void send(String[] receivers, String subject,
			String mailContent, String mimetype) {
		send(receivers, subject, mailContent, null, mimetype);
	}

	/**
	 * 发送邮件:附件+text/html
	 * 
	 * @param receiver
	 *            ：收件人
	 * @param subject
	 *            ：标题
	 * @param mailContent
	 *            ：邮件内容
	 * @param mimetype
	 *            ：内容类型，默认为text/plain,如果要发送HTML内容,应设置为text/html
	 */
	public static void send(String[] receivers, String subject,
			String mailContent, File[] attachements, String mimetype) {
		Properties props = new Properties();
		props.put("mail.smtp.host", smtp);// Smtp服务器地址
		props.put("mail.smtp.auth", "true");// 需要校验
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);// 登录用户名/密码
					}
				});
		session.setDebug(true); // 是否开启调试
		try {
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(sendMail));// 发件人邮件

			InternetAddress[] toAddress = new InternetAddress[receivers.length];
			for (int i = 0; i < receivers.length; i++) {
				toAddress[i] = new InternetAddress(receivers[i]);
			}
			mimeMessage.setRecipients(Message.RecipientType.TO, toAddress);// 收件人邮件
			mimeMessage.setSubject(subject, charset);

			Multipart multipart = new MimeMultipart();
			// 正文
			MimeBodyPart body = new MimeBodyPart();
			// body.setText(message, charset);不支持html
			body.setContent(mailContent, (mimetype != null
					&& !"".equals(mimetype) ? mimetype : defaultMimetype)
					+ ";charset=" + charset);
			multipart.addBodyPart(body);// 发件内容
			// 附件
			if (attachements != null) {
				for (File attachement : attachements) {
					MimeBodyPart attache = new MimeBodyPart();
					// ByteArrayDataSource bads = new
					// ByteArrayDataSource(byte[],"application/x-any");
					attache.setDataHandler(new DataHandler(new FileDataSource(
							attachement)));
					String fileName = getLastName(attachement.getName());
					attache.setFileName(MimeUtility.encodeText(fileName,
							charset, null));
					multipart.addBodyPart(attache);
				}
			}
			mimeMessage.setContent(multipart);
			mimeMessage.setSentDate(new Date());
			/*
			 * SimpleDateFormat formcat = new SimpleDateFormat("yyyy-MM-dd");
			 * formcat.parse("2010-5-23"),邮件排前，可以修改邮件时间提前
			 */
			Transport.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getLastName(String fileName) {
		int pos = fileName.lastIndexOf("\\");
		if (pos > -1) {
			fileName = fileName.substring(pos + 1);
		}
		pos = fileName.lastIndexOf("/");
		if (pos > -1) {
			fileName = fileName.substring(pos + 1);
		}
		return fileName;
	}
}
