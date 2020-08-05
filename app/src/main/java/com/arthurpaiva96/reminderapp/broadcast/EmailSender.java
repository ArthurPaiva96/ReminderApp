package com.arthurpaiva96.reminderapp.broadcast;



import android.os.AsyncTask;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private final String senderAndReceiverEmail;
    private final String password;
    private String subject = "[ReminderApp] - Lembrete: ";
    private final String message;

    public EmailSender(String senderAndReceiverEmail,  String password, String subject, String message){
        this.senderAndReceiverEmail = senderAndReceiverEmail;
        this.password = password;
        this.subject += subject;
        this.message = message;
    }

    public void sendReminderEmail(){

        Properties properties = configureConnection();

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderAndReceiverEmail, password);
            }
        });

        try {

            Message message = configureEmail(session);

            new SendMail().execute(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Message configureEmail(Session session) throws MessagingException {
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(senderAndReceiverEmail));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(senderAndReceiverEmail));

        message.setSubject(this.subject);
        message.setText(this.message);

        return message;
    }

    //some anti-virus may block this. in my case, avast did it
    //I had to turn it off
    private Properties configureConnection() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port", "587");


        //Second option if the first break
//        properties.setProperty("mail.transport.protocol", "smtp");
//        properties.setProperty("mail.host", "smtp.gmail.com");
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.port", "465");
//        properties.put("mail.smtp.socketFactory.port", "465");
//        properties.put("mail.smtp.socketFactory.class",
//                "javax.net.ssl.SSLSocketFactory");
//        properties.put("mail.smtp.socketFactory.fallback", "false");
//        properties.setProperty("mail.smtp.quitwait", "false");

        return properties;
    }

    private static class SendMail extends AsyncTask<Message, String, String> {

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
