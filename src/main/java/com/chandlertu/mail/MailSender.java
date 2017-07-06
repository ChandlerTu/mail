package com.chandlertu.mail;

import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailSender {

	private String account;
	private String password;
	private String smtp;

	private Logger logger = LoggerFactory.getLogger(MailSender.class);

	public MailSender(String account, String password, String smtp) {
		this.account = account;
		this.password = password;
		this.smtp = smtp;
	}

	public void send(String to, String subject, String text) {
		send(to, subject, text, "false");
	}

	public void send(String to, String subject, String text, String ssl) {
		Properties props = new Properties();
		props.put("mail.smtp.host", smtp);
		if (ssl.equals("true")) {
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.ssl.enable", true);
		}

		Session session = Session.getInstance(props);
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(account));
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(text);
			Transport.send(message, account, password);
		} catch (MessagingException e) {
			logger.error("", e);
		}
	}

}
