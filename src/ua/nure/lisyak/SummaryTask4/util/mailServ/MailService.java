package ua.nure.lisyak.SummaryTask4.util.mailServ;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.exception.MediaException;


public class MailService {
	 	
		private static final Logger LOGGER = Logger.getLogger(MailService.class);
        
		private static final Session DEFAULT_SESSION;
        private static final String FILE = "/mail.properties";
	    private static final String EMAIL;
	    private static final String PASSWORD;
	    private static final String HOST;
	    private static final int PORT;

	    static {
	        try (InputStream resource = MailService.class.getResourceAsStream(FILE)) {
	            Properties prop = new Properties();
	            prop.load(resource);
	            EMAIL = prop.getProperty("mail.address");
	            PASSWORD = prop.getProperty("mail.password");
	            HOST = prop.getProperty("mail.host");
	            PORT = Integer.parseInt(prop.getProperty("mail.port"));
	            
	        } catch (IOException e) {
	            LOGGER.error("Unable to load file: "+ FILE);
	            throw new MediaException("Unable to load file: '" + FILE + "'", e);
	        }
	    }


	    static {
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", MailService.HOST);
	        props.put("mail.smtp.port", MailService.PORT);
	        
	        Authenticator authenticator = new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(MailService.EMAIL, MailService.PASSWORD);
	            }
	        };
	        DEFAULT_SESSION = Session.getInstance(props, authenticator);
	    }

	    /**
	     * Gets template for message as a {@link String}
	     * @param filePath path to template
	     * @return template {@link String} view of template
	     */
	    public static String getTemplate(String filePath) {
	        InputStream inputStream = MailService.class.getResourceAsStream(filePath);
	        return new Scanner(inputStream, "UTF-8").useDelimiter("\\A").next();
	    }

	    /**
	     * Used for sending email confirmation.
	     * @param from email sender
	     * @param to email recipient
	     * @param title email title
	     * @param content email content
	     * @return {@code true} if email sent, {@code false} if it is not
	     */
	    public static boolean send(String to, String title, String content) {
	        try {
	            MimeMessage messageObj = new MimeMessage(DEFAULT_SESSION);
	            messageObj.setFrom(new InternetAddress(MailService.EMAIL));
	            messageObj.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	            messageObj.setSubject(title, "UTF-8");
	            messageObj.setContent(content, "text/html; charset=UTF-8");
	            Transport.send(messageObj);
	            return true;
	        } catch (MessagingException e) {
	            LOGGER.error("Mail wasn't sent. Exception: "+ e.getMessage());
	            return false;
	        }
	    }
}
