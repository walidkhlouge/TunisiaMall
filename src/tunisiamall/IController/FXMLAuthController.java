/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunisiamall.DAO.CompteDAO;
import tunisiamall.entities.Compte;
import tunisiamall.main.MainRespE;


public class FXMLAuthController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button inscrire;
    @FXML
    Button connecter;
    @FXML
    TextField login;
    @FXML
    TextField pwd;
    Stage stage;
    Parent root;
    CompteDAO cDao = new CompteDAO();
    @FXML
    Text alert;
    @FXML
    JFXButton facebook;
    
    
    @FXML
    private void inscription(ActionEvent event) throws IOException {
        stage = (Stage) inscrire.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/tunisiamall/GUI/FXMLInscription.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }
    @FXML
    private void auth(ActionEvent event) throws IOException, InterruptedException
{
    Compte.connected=null;
    Compte.connected=cDao.findCompteByAuth(login.getText(), pwd.getText());
    
    if(Compte.connected!=null)
    {
           stage = (Stage) connecter.getScene().getWindow();
      
   
if (Compte.connected.getType().equals("ResponsableBoutique")){
    

        root = FXMLLoader.load(getClass().getResource("/tunisiamall/GUI/FXMLModifierCoordonneesResBoutique.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

}
        else if (Compte.connected.getType().equals("Client")){
      root = FXMLLoader.load(getClass().getResource("/tunisiamall/GUI/FXMLClientAccueilInclude.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();}
else if (Compte.connected.getType().equals("ResponsableEnseigne"))
       {
           stage.close();
       stage = new Stage();
         MainRespE n = new MainRespE();
        n.start(stage);
       }
else {
         root = FXMLLoader.load(getClass().getResource("/tunisiamall/GUI/FXMLAcueilAdmin.fxml"));
        
Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
}
       
    }   
    else
    alert.setText("Login ou mot de passe incorrect");
            
            
}
    private void handle(ActionEvent event) throws IOException
    {
            root = FXMLLoader.load(getClass().getResource("/tunisiamall/GUI/FXMLAuthFacebook.fxml"));
        
Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    

}
