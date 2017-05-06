/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.io.IOException;
import tunisiamall.DAO.CompteDAO;
import tunisiamall.entities.Compte;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.InputEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tunisiamall.entities.BCrypt;


public class FXMLInscriptionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password1;
    @FXML
    private PasswordField password2;
    @FXML
    private TextField email;
    @FXML
    private ComboBox typeC;
    @FXML
    private Text loginExist;
    @FXML
    private Text emailExist;
    @FXML
    private Text pwdExist;
    @FXML
    private Text nomAlert;
    @FXML
    private Text prenomAlert;
    @FXML
    private Text sexeAlert;
    @FXML
    private Text dateAlert;
    @FXML
    private Text compteAlerte;
    @FXML 
    private DatePicker dateNaiss;
    @FXML
    private ToggleGroup sexe;
    @FXML
    private ProgressBar pwdProg;
    @FXML
    private Button annuler;
    @FXML
    private Button inscription;
    Stage stage;
    Parent root;
    CompteDAO daoC = new CompteDAO();
    @FXML
    private void loginVerifAction(InputEvent event) // Verifie si le login est disponible ou pas (onKeyReleased)
    {
        if (daoC.seekCompteByLogin(login.getText()) == true)
            loginExist.setText("Ce login existe déjà");
        else if (login.getText().isEmpty())
            loginExist.setText("");
        else
            loginExist.setText("");
    }
 
  
    @FXML
    private void passwordVerifAction(InputEvent event) // Verifie le niveau de sécurité du mot de passe (onKeyReleased)
    {
        pwdExist.setText("");
      int maj=0, min=0,number=0,caracSp=0,letter=0;
          int securityCount=0;
        securityCount=password1.getText().length();
        if(securityCount>7)
            securityCount=7;
        for(int i = 0; i<password1.getText().length();i++)
        {
        if(Character.isUpperCase(password1.getText().charAt(i)))
            maj++;
        else if (Character.isLowerCase(password1.getText().charAt(i)))
            min++;
        else if(Character.isDigit(password1.getText().charAt(i)))
            number++;
        else if(Character.isWhitespace(password1.getText().charAt(i)))
            caracSp++;
            else
            caracSp++;
        
        }
        letter=maj+min;
        if(maj<min)
            min=maj;
        if(min>5)
            securityCount+=5;
        else
            securityCount+=min;
        if(letter<number)
            number=letter;
        if(number>5)
            securityCount+=5;
        else
            securityCount+=number;
        if(caracSp>4)
            securityCount+=4;
        else
            securityCount+=number;
                pwdProg.setVisible(true);
        pwdProg.setProgress((double) securityCount/21);
        /*
        Ajouter un Text pour dire le niveau du mot de passe en dessous de la barre
        0->7:Faible
        8->14:Moyen
        15->21:Excelent
        (Ici on parle de la valeur de securityCount)
        */
    }
    
    @FXML
    private void dateNaissVerifAction(InputEvent event) // Verifie si la date de naissance est valide (?)
    {
        dateAlert.setText("");
        if(dateNaiss.valueProperty().getValue()==null)
        {
            dateAlert.setText("Veuillez selectionner votre date de naissance");
        }
        else if (new Date().before(java.sql.Date.valueOf(dateNaiss.valueProperty().getValue())))
        {
         dateAlert.setText("Vous venez du future ?");
        }
        
    }
    @FXML
    private void nomAlertVide(InputEvent event) // Vérifie si le nom a été saisie (onKeyPressed)
    {
        nomAlert.setText("");
    }
    @FXML
    private void prenomAlertVide(InputEvent event) // Verifie si le prenom a été saisie (onKeyPressed)
    {
        prenomAlert.setText("");
    }
    @FXML
    private void sexeAlertVide(InputEvent event) // Verifie si le sexe a été selectionné (?)
    {
        sexeAlert.setText("");
    }
    
    @FXML
    private void emailVerifAction(InputEvent event) //Verifie l'email en temps réel (onKeyReleased) 
    {
        boolean exact = false;
        for(int i=1; i <email.getText().length()-1;i++)
            if(email.getText().charAt(i)=='@')
                exact=true;
        if(!exact)
            emailExist.setText("Ceci n'est pas un email");
        else
            emailExist.setText("");
        if (email.getText().isEmpty()) {
            emailExist.setText("Veuillez saisir votre e-mail ");
        } else if (daoC.seekCompteByEmail(email.getText()) == true) {
            emailExist.setText("Cet email existe déjà");
            }

        
    }
    @FXML
    private void exitButtonAction(ActionEvent event)  throws IOException  // Revenir à l'interface d'authentification
    {
        stage = (Stage) annuler.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/tunisiamall/GUI/FXMLAuth.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException // Faire l'inscription + Verification des données ( OPTIMISATION : Enlever la verification ici /NOT SURE/)
    {
        loginExist.setText("");
        emailExist.setText("");
        nomAlert.setText("");
        prenomAlert.setText("");
        pwdExist.setText("");
        compteAlerte.setText("");
        dateAlert.setText("");
       boolean inscrire = true;
       boolean exact = false;
        if (nom.getText().isEmpty()) {
            nomAlert.setText("Veuillez saisir votre nom ");
            inscrire = false;
        }
        if (prenom.getText().isEmpty()) {
            prenomAlert.setText("Veuillez saisir votre prenom");
            inscrire = false;
        }
        for(int i=1; i <email.getText().length()-1;i++)
            if(email.getText().charAt(i)=='@')
                exact=true;
        if(!exact){
            emailExist.setText("Ceci n'est pas un email");
        inscrire=false;
        }
        else{
        if (email.getText().isEmpty()) {
            emailExist.setText("Veuillez saisir votre e-mail ");
            inscrire = false;
        } else if (daoC.seekCompteByEmail(email.getText()) == true) {
            emailExist.setText("Cet email existe déjà");
            inscrire = false;
            }
}
        if (password1.getText().isEmpty()) {
            pwdExist.setText("Veuillez saisir votre mot de passe");
            inscrire = false;
        }
        else if(!password1.getText().equals(password2.getText()))
        {
            pwdExist.setText("Les deux mots de passes ne correspondent pas");
            inscrire = false;
        }
        if (login.getText().isEmpty())
        {
            loginExist.setText("Veuillez saisir un login");
            inscrire = false;
        }
        else if (daoC.seekCompteByLogin(login.getText()) == true)
        {
            loginExist.setText("Ce login existe déjà");
            inscrire = false;
        }
        if(dateNaiss.valueProperty().getValue()==null)
        {
            dateAlert.setText("Veuillez selectionner votre date de naissance");
            inscrire = false;
        }
        else if (new Date().before(java.sql.Date.valueOf(dateNaiss.valueProperty().getValue())))
        {
         dateAlert.setText("Vous venez du future ?");
         inscrire = false;
        }
        if (typeC.getValue()==null)
        {
            compteAlerte.setText("Veuillez selectionner vote type de compte");
            inscrire = false;
        }
        
            if (inscrire) {
                daoC.addCompte(new Compte(nom.getText(), prenom.getText(), email.getText(), password1.getText(), login.getText(), (String)typeC.getValue(),sexe.getSelectedToggle().getUserData().toString(),java.sql.Date.valueOf(dateNaiss.getValue()),"Not Activated"));
 FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/tunisiamall/GUI/FXMLClientValidation.fxml"));
                    loader.load();
                    Parent p=loader.getRoot();
                    stage = new Stage();
                    stage.setScene(new Scene(p));
        FXMLClientValidationController controller=loader.getController();
        controller.initData(login.getText(), nom.getText(), prenom.getText(), email.getText());
        stage.show();
            }
    }


@Override
        public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        pwdProg.setVisible(false);
    }    
    
}
