package model;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Email {	
    private static String from = "researchmatchmaker.email";  // GMail user name (just the part before "@gmail.com")
    private static String pass = "researchmatchmaker"; // GMail password
    private static String subject = "Testing sending email";
    private static String body = "Testing sending email from researchMatchmaker";

    public static void main(String[] args) {
        String to = "researchmatchmaker.email@gmail.com"; // list of recipient email addresses

        sendFromGMail(to);
    }

    private static void sendFromGMail(String to) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }

}