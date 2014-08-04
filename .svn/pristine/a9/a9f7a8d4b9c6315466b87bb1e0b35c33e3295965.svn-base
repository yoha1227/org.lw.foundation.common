package org.hy.foundation.utils.mail;

import javax.mail.*;

public class MailAuthenticator extends Authenticator {

	// 由于发送邮件的地方比较多,
	// 下面统一定义用户名,口令.

	public static String USER = "";

	public static String PASSWORD = "";

	public MailAuthenticator(String smtpAuth, String smtpPwd) {
		USER = smtpAuth;
		PASSWORD = smtpPwd;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(USER, PASSWORD);
	}

}
