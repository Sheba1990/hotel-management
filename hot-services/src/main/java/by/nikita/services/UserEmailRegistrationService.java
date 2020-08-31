package by.nikita.services;

import by.nikita.dto.UserDto;
import by.nikita.services.api.IRegistrationService;
import by.nikita.services.config.EmailProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class UserEmailRegistrationService implements IRegistrationService {

    @Autowired
    EmailProperties emailProperties;

    @Override
    public void registerUser(UserDto userDto) {
        sendEmail(userDto);
    }

    private void sendEmail(UserDto userDto) {
        // Recipient's email ID needs to be mentioned.
        String to = userDto.getUserEmail();
        // Sender's email ID needs to be mentioned
        String from = "norply@gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", emailProperties.getHost());
        properties.put("mail.smtp.port", emailProperties.getPort());
        properties.put("mail.smtp.ssl.enable", emailProperties.getSsl());
        properties.put("mail.smtp.auth", emailProperties.getAuth());

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailProperties.getUsername(), emailProperties.getPass());
            }
        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setContent("<a href='http://localhost:8080/register/" + userDto.getUserName() + "'>Lets try</a>", "text/html");
            // Send message
            Transport.send(message);
            System.out.println("Message has been sent successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    @Override
    public void activateUser(String username) {
    }
}
