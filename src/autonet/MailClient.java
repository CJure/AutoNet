/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autonet;


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 *
 * @author jur_1
 */
public class MailClient implements Runnable
{
    String to;
    String from;
    String password;
    String host = "localhost";
    String subject;
    String content;
    Properties properties = System.getProperties();

    public MailClient(String to, String from, String password, String subject, String content) 
    {
        this.to = to;
        this.from = from;
        this.password = password;
        this.subject = subject;
        this.content = content;
    }

    
    
    
    @Override
    public void run() 
    {
        try
        {
            setProperties();
            Session session = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress(to);
            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject("Najden nov avto: " + subject);
            message.setText(content);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception on sendMail: " + ex.toString());
            ex.printStackTrace();
        }
    }

    private void setProperties() 
    {
        properties.setProperty("mail.smtp.host", host);
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.user", from);
            properties.put("mail.smtp.password", password);
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
    }
 
    
    
}
