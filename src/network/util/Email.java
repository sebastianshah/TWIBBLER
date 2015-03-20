/*
 To send an e-mail using your Java Application is simple enough but to
 start with you should have JavaMail API and Java Activation Framework (JAF)
 installed on your machine.

	* You can download latest version of JavaMail from 
	* http://www.oracle.com/technetwork/java/javamail/index-138643.html

	* You can download latest version of JAF from
	* http://www.oracle.com/technetwork/java/jaf11-139815.html

 Download and unzip these files. In the newly created top level directories
 you will find a number of jar files for both the applications.
 You need to add mail.jar and activation.jar files in your CLASSPATH.
 */

package network.util;

import network.query.MySQLAccess;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Email

{
	
	public static Properties properties = null;
	
    public static void sendEmail(String user, String twibblerMessage)
    {


        // ****************************** Send by Gmail ********************************

        // Specify which email server you want to use
        String host = "smtp.gmail.com";

        // Specify the port number. For "smtp" use 587 and for "smtps"
        // use 465
        int port = 587;

        // Sender's email ID needs to be mentioned
        String from = "system";

        String username = "twibblersystem@gmail.com";
        String password = "twibbler_System";

        System.out.println("email stage 1");

        // Recipient's email ID needs to be mentioned.
        //String to = "email@gmail.com, email@Live.ca";
        String to = MySQLAccess.getSubscribers(user);

        //pass from server to here

        // Get system properties
        //Properties properties = System.getProperties();

        // Configure mail server
        //properties.put("mail.smtp.starttls.enable", "true");
        //properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties);
        System.out.println("email stage 2");
        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            System.out.println("email stage 3");
            // Set To: header field of the header.
            //message.addRecipient(Message.RecipientType.TO,
            //        new InternetAddress(to));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to) );
         /* 
         If you want to send an e-mail to multiple recipients then following method would
         be used to specify multiple e-mail IDs:
			-------------------------------------------------------------------------
        	 void addRecipients(Message.RecipientType type, Address[] addresses)
        	 throws MessagingException
        	-------------------------------------------------------------------------
        	        	         	 
        	 type: This would be set to TO, CC or BCC
        	 addresses: This is the array of email IDs. You would need to 
        	 			use InternetAddress() method while specifying email IDs.
         */

            // Set Subject: header field
            message.setSubject("New twibbler notificatrion system from " +user);

            // Now set the actual message
            message.setText(twibblerMessage);
            System.out.println("email stage 4");
            // Connect to server and send message
            Transport transport = session.getTransport("smtp");
            System.out.println("email stage 5");
            transport.connect(host, port, username, password);
            System.out.println("working email");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }
}