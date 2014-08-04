package org.hy.foundation.utils.mail;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



/**
 * 邮件工具类整理，使用实例：<br>
 * 
 * 
 * 		MailInfos m=new MailInfos();
 * 		m.setMailTo("chenc@mail.pico.net");
 * 		m.setMailFrom("sjira@mail.pico.net");
 * 		m.setSubject("SC");
 * 		m.setMsgContent("test");
 * 		MailUtil.sendMail(m);
 * 
 * 
 * @date 2011-5-19
 * @author MipatchTeam#chenc
 * 
 */
public class MailUtil {

	/**
	 * 按列表发送邮件
	 * 
	 * @param mails
	 */
	public static void sendMail(List<MailInfos> mails) {
		int succ = 0;
		for (int i = 0; i < mails.size(); i++) {
			try {
				sendMail(mails.get(i));
				succ++;
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 发送单个邮件
	 * 
	 * @param mail
	 * @throws Exception
	 */
	public static void sendMail(MailInfos mail) throws Exception {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "");
		props.put("mail.smtp.port", "");

		MailAuthenticator auth = null;
		if (!isEmpty("")) {
			props.put("mail.smtp.auth", "true");
			auth = new MailAuthenticator("", "");
		}

		Session session = Session.getInstance(props, auth);
		session.setDebug(false);
		Message msg = new MimeMessage(session);
		try {
			fillMail(session, msg, mail);
			// send the message
			Transport.send(msg);
		} catch (MessagingException mex) {
			throw new Exception(mex);
		}
	}

	private static boolean isEmpty(String k) {
		return k == null || k.trim().length() == 0;
	}

	private static void fillMail(Session session, Message msg, MailInfos mail)
			throws IOException, MessagingException {
		String fileName = null;
		Multipart mPart = new MimeMultipart();

		// 发送人Mail地址
		msg.setFrom(new InternetAddress(mail.getMailFrom(), mail
				.getMailFromName()));

		if (mail.getMailTo() != null && mail.getMailTo().length > 0) {
			InternetAddress[] address = new InternetAddress[mail.getMailTo().length];
			for (int i = 0; i < mail.getMailTo().length; i++) {
				// 收件人Mail地址
				address[i] = new InternetAddress(mail.getMailTo()[i]);
			}
			msg.setRecipients(Message.RecipientType.TO, address);
		}

		if (mail.getMailccTo() != null && mail.getMailccTo().length > 0) {
			InternetAddress[] ccaddress = new InternetAddress[mail
					.getMailccTo().length];
			for (int i = 0; i < mail.getMailccTo().length; i++) {
				// 抄送收件人Mail地址
				ccaddress[i] = new InternetAddress(mail.getMailccTo()[i]);
			}
			msg.setRecipients(Message.RecipientType.CC, ccaddress);
		}
		if (mail.getMailbccTo() != null && mail.getMailbccTo().length > 0) {
			InternetAddress[] bccaddress = new InternetAddress[mail
					.getMailbccTo().length];
			for (int i = 0; i < mail.getMailbccTo().length; i++) {
				// 抄送收件人Mail地址
				bccaddress[i] = new InternetAddress(mail.getMailbccTo()[i]);
			}
			msg.setRecipients(Message.RecipientType.BCC, bccaddress);
		}

		// 标题
		msg.setSubject(mail.getSubject());

		// 来源
		InternetAddress[] replyAddress = { new InternetAddress(mail
				.getMailFrom()) };
		msg.setReplyTo(replyAddress);

		// create and fill the first message part
		MimeBodyPart mBodyContent = new MimeBodyPart();
		mBodyContent.setContent(mail.getMsgContent(), mail
				.getMessageContentMimeType());
		mPart.addBodyPart(mBodyContent);

		// attach the file to the message
		if (mail.getAttachedFileList() != null
				&& mail.getAttachedFileList().size() > 0) {
			for (int i = 0; i < mail.getAttachedFileList().size(); i++) {
				fileName = mail.getAttachedFileList().get(i);
				MimeBodyPart mBodyPart = new MimeBodyPart();
				// attach the file to the message
				FileDataSource fds = new FileDataSource(fileName);

				mBodyPart.setDataHandler(new DataHandler(fds));
				mBodyPart.setFileName(fileName);
				mPart.addBodyPart(mBodyPart);
			}
		}
		msg.setContent(mPart);
		msg.setSentDate(new Date());
	}
}
