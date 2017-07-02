package com.chandlertu.mail;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.junit.Test;

public class MailSenderTest {

	@Test
	public void testSend() {
		Properties props = new Properties();
		Path path = Paths.get(System.getProperty("user.home"), ".chandlertu", "mail.properties");
		try (FileInputStream inStream = new FileInputStream(path.toFile())) {
			props.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String account = props.getProperty("account");
		String password = props.getProperty("password");
		String smtp = props.getProperty("smtp");
		String to = account;
		String subject = "test";
		String text = "";
		MailSender sender = new MailSender(account, password, smtp);
		sender.send(to, subject, text);
	}

}
