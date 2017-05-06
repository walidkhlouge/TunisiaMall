/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tunisiamall.entities.Compte;


public class FXMLClientValidationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField code;
    String codeVal= new String();
    
    
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
            if(code.getText().equals(codeVal))
            {
                System.out.println("reussi");
            }
            else
                System.out.println("code faux");
        
    }
    public void initData(String login,String nom, String prenom, String email)
    {
          codeVal=Compte.calculateCode(login);
                final String username = "muertox@gmail.com";
		final String password = "papoose9392";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("tunisiamallesprit@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("Code de validation");
			message.setText("Salut Monsieur "+prenom+" "+nom+".\n\n Voici votre code de validation : "+codeVal);

			Transport.send(message);

			

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
