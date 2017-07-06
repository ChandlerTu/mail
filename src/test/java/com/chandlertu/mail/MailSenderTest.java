package com.chandlertu.mail;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.junit.Test;

public class MailSenderTest {

	@Test
	public void testSend() {
		Properties props = new Properties();
		Path path = Paths.get(System.getProperty("user.home"), ".chandlertu", "mail.properties");
		try (InputStreamReader inStream = new InputStreamReader(new FileInputStream(path.toFile()), "utf-8")) {
			props.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String account = props.getProperty("account");
		String password = props.getProperty("password");
		String smtp = props.getProperty("smtp");
		String to = props.getProperty("to");
		String subject = props.getProperty("subject");
		String text = props.getProperty("text");
		String ssl = props.getProperty("ssl", "false");
		MailSender sender = new MailSender(account, password, smtp);
		sender.send(to, subject, text, ssl);
	}

}
