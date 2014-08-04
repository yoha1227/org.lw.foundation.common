package org.hy.foundation.utils.mail;

import java.util.Vector;

public class MailInfos {

	// 要发送Mail地址
	private String[] mailTo = null;

	// 
	// 两者的区别在于在BCC栏中的收件人可以看到所有的收件人名(TO,CC,BCC)，而在TO 和CC栏中的收件人看不到BBC的收件人名。
	//
	// 要暗抄送Mail地址 BCC英文全称是 Blind CarbonCopy(暗抄送)。
	private String[] mailbccTo = null;
	// 要抄送Mail地址 CC 英文全称是 Carbon Copy(抄送);
	private String[] mailccTo = null;
	// 

	// Mail发送的起始地址
	private String mailFrom = null;
	// Mail发送的起始地址
	private String mailFromName = "";
	// 附件地址列表
	private Vector<String> attachedFileList;

	// Mail主题
	private String subject;
	// Mail内容
	private String msgContent;
	// 编码格式
	private String messageContentMimeType = "text/html; charset=utf-8";

	public String[] getMailTo() {
		return mailTo;
	}

	public void setMailTo(String[] mailTo) {
		this.mailTo = mailTo;
	}

	public String[] getMailbccTo() {
		return mailbccTo;
	}

	public void setMailbccTo(String[] mailbccTo) {
		this.mailbccTo = mailbccTo;
	}

	public String[] getMailccTo() {
		return mailccTo;
	}

	public void setMailccTo(String[] mailccTo) {
		this.mailccTo = mailccTo;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMessageContentMimeType() {
		return messageContentMimeType;
	}

	public void setMessageContentMimeType(String messageContentMimeType) {
		this.messageContentMimeType = messageContentMimeType;
	}

	public Vector<String> getAttachedFileList() {
		return attachedFileList;
	}

	public void setAttachedFileList(Vector<String> attachedFileList) {
		this.attachedFileList = attachedFileList;
	}

	public String getMailFromName() {
		return mailFromName;
	}

	public void setMailFromName(String mailFromName) {
		this.mailFromName = mailFromName;
	}

}
