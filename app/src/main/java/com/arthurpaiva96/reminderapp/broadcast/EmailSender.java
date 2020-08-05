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
    private String subject = "[ReminderApp] - Lembrete: ";
    private final String message;
    private final String password;

    public EmailSender(String senderAndReceiverEmail, String subject, String message, String password){
        this.senderAndReceiverEmail = senderAndReceiverEmail;
        this.subject += subject;
        this.message = message;
        this.password = password;
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

    private Properties configureConnection() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
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
