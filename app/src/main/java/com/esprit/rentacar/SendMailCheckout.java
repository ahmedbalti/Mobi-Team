package com.esprit.rentacar;

import android.os.AsyncTask;
import android.util.Log;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class SendMailCheckout extends AsyncTask<Void, Void, Void> {

    private String email;
    private String subject;
    private String messageBody;

    public SendMailCheckout(String email, String subject, String messageBody) {
        this.email = email;
        this.subject = subject;
        this.messageBody = messageBody;
    }

    @Override
    protected Void doInBackground(Void... params) {
        sendEmail();
        return null;
    }

    private void sendEmail() {
        // Paramètres de configuration pour le serveur Gmail
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        // Adresse e-mail de l'expéditeur et mot de passe
        final String username = "rentacartunisie.mobiteam@gmail.com";
        final String password = "vfnj gxsn oxii ywlo";

        // Création d'une session avec l'authentification
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Création du message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);
            message.setText(messageBody);

            // Envoi du message
            Transport.send(message);

            Log.d("SendMail", "email sent successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}