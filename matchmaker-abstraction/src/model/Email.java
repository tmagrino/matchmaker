package model;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Email {	
    private static String from = "researchmatchmaker.email";  // GMail user name (just the part before "@gmail.com")
    private static String pass = "researchmatchmaker"; // GMail password
    private static String subject = "You have a new Message from the Matchmaker Team";
    private static String signature = "Matchmaker Team";
    static String body;
    
    
    public static void sendAcceptingMessage(Student s, Application a){
    	
    	body = "Dear " + s.getName() + ",\n\nCongratulations! Your application for the project "
    			+ a.getApplicationProject().getName() + 
    			" has been accepted! For more information, you can contact the project leader "+
    			a.getApplicationProject().getResearchers().get(0).getName()+ " on " +
    			a.getApplicationProject().getResearchers().get(0).getEmail()+ " or check the project "
    					+ "page here: " + a.getApplicationProject().getURL() + "\n\n" + signature;
    	
    	String to = s.getEmail();
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
    public static void sendRejectionMessage(Student s, Application a){
    	body = "Dear " + s.getName() + ",\n\nWe are sorry to inform you that your "
    			+ "application for the project " + a.getApplicationProject().getName() + 
    			" was declined. You can search for other interesting projects on our Website!\n\n"
    			+ signature;
    			
    	
    	String to = s.getEmail();
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
    public static void sendInvitationMessage(Student s, Application a ){
    	
    	if (a.getApplicationProject().getResearchers().size() > 1){
    		body = "Dear " + s.getName() + ",\n\nProfessors " + a.getApplicationProject().
    				getResearchersString() +" have invited you to apply to their project "
    				+ a.getApplicationProject().getName() + ". You can check it out here: " 
        			+ a.getApplicationProject().getURL() + "\n\n" + signature;
    				
    	}
    	else{
    		body = "Dear " + s.getName() + ",\n\nProfessor " + a.getApplicationProject().
    				getResearchersString() + " has invited you to apply for the project "
        			+ a.getApplicationProject().getName() + ". You can check it out here: " 
        			+ a.getApplicationProject().getURL()+"\n\n" + signature;
    	}

    	
    	String to = s.getEmail();
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
    public static void sendDeleteItemMessage(Student s, String description, String type){
    	
    	body = "Dear "+ s.getName() + ",\n\nYour "+ type + " named " + description+ " has been removed by an Administrator."
    			+"Please go to your profile page and make any necessary changes!\n\n" + signature;
    	
    	String to = s.getEmail();
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
public static void sendDeleteItemMessage(Researcher r, String description, String type){
    	
		body = "Dear "+ r.getName() + ",\n\nYour "+ type + " named " + description + 
				" has been removed by an Administrator."
			+"Please go to your profile page and make any necessary changes!\n\n" + signature;
    	
    	String to = r.getEmail();
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
