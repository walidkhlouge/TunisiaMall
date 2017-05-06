/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import tunisiamall.DAO.CompteDAO;
import tunisiamall.entities.Compte;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Post;
import com.restfb.types.User;
import java.sql.Date;


public class FXMLAuthFacebookController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
      @FXML
      private WebView web;
       URL u1 ;
      URL u2 ;
      CompteDAO daoC =new CompteDAO();
      
      
      @FXML
         private void verifURL(MouseEvent event)
         {  
             
             
             if(web.getEngine().getLocation().contains("access_token")){
                 System.out.println(web.getEngine().getLocation());
                 String at =web.getEngine().getLocation().substring(65, web.getEngine().getLocation().length()-19);
             
             FacebookClient fbClient = new DefaultFacebookClient(at,"1e55d70ce894f2b879b820ba68710459",Version.LATEST);
             
            User u = fbClient.fetchObject("me", User.class, new Parameter[]{Parameter.with("fields","id,name,email,gender,birthday")});
            String prenom = new String();
            String nom = new String();
            int i = 0;
            
            while(u.getName().length()>i&&!Character.isSpace(u.getName().charAt(i)))
            {
                prenom+=u.getName().charAt(i);
                i++;
            }
            i++;
            while (u.getName().length()>i)
            {
                
                nom+=u.getName().charAt(i);
                i++;
            }
            String sexe = new String();
            if (u.getGender().equals("male"))
            sexe="Homme";
            else
            sexe="Femme";
            daoC.addCompte(new Compte(nom, prenom, u.getEmail(), prenom+nom, prenom+nom, "Client", sexe, new java.sql.Date(u.getBirthdayAsDate().getTime()), "Activated",null));
            
             }
             }
         
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
          try {
              u1 = new URL("https://www.facebook.com/dialog/oauth?" +
                      "client_id=825239964271285"+
                      "&redirect_uri=https://www.facebook.com/connect/login_success.html" +
                      "&client_secret=1e55d70ce894f2b879b820ba68710459&response_type=token&scope=email");
          } catch (MalformedURLException ex) {
              Logger.getLogger(FXMLAuthFacebookController.class.getName()).log(Level.SEVERE, null, ex);
          }
        web.getEngine().load(u1.toString());
    }    
    
}
