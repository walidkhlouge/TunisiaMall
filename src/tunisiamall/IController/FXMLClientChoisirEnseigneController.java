/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tunisiamall.DAO.AbonnementDAO;
import tunisiamall.DAO.EnseigneDAO;
import tunisiamall.entities.Enseigne;
import tunisiamall.main.includeClient;



public class FXMLClientChoisirEnseigneController implements Initializable{
    @FXML
    AnchorPane anchor1;
    @FXML
    private TextField recherche;
    EnseigneDAO e=new  EnseigneDAO();
    private String rech="";
     AbonnementDAO ab=new  AbonnementDAO();
  


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        List<Enseigne>l=new ArrayList<Enseigne>();

        if(rech.equals(""))    //pr la recherche dyn
        l=e.findAll();
     else{  
   l=e.findEnseigneBylogDynamic(rech);
            System.out.println(l);
        recherche=  (TextField) anchor1.getChildren().get(0);    //trekuperi el node recherche
        anchor1.getChildren().clear();
        anchor1.getChildren().add(recherche);
     }
         int x=10;int y=100;
        for(Enseigne en : l)
        {
           Button abon=new Button();
            ImageView img0=new ImageView();
 img0.setImage(new Image(getClass().getResource("../GUI/images/enseigne/"+en.getUrl()).toExternalForm()));
        img0.setLayoutX(x);
        img0.setLayoutY(y);
        img0.setFitWidth(150);
        img0.setFitHeight(170);       
         Label lab=new Label(en.getNom());
         lab.setLayoutX(x+37.5);//150 / 4 bech tji fel west
         lab.setLayoutY(y+170+10);
         abon.setLayoutX(x+37);
         abon.setLayoutY(y+180);
          
            try {
                if(  ab.findAbonnement("bassemlog", en.getNom()))
                
                    
                     abon.setText("se Desabonner");
                   else 
                abon.setText("sabonner");
            } catch (SQLException ex) {
                Logger.getLogger(FXMLClientChoisirEnseigneController.class.getName()).log(Level.SEVERE, null, ex);
            }
                abon.setOnAction((event) -> {
               try {
                   if(  ab.findAbonnement("bassemlog", en.getNom())==true)
                   {  
                       abon.setText("sabonner");
                       ab.suppAbonnementByPK("bassemlog",en.getNom());
                    
                   }
                   else if( ab.findAbonnement("bassemlog", en.getNom())==false) {
                     
                   
                   abon.setText("se Desabonner");
                   ab.addAbonnement("bassemlog", en.getNom());
                   }
               } catch (SQLException ex) {
                   Logger.getLogger(FXMLClientChoisirEnseigneController.class.getName()).log(Level.SEVERE, null, ex);
               }
                         });
              
         x += 230;
            if (x > 920) {
                y += 250;
                x = 0;
            }
         anchor1.getChildren().addAll(lab,img0,abon);
         img0.setOnMouseClicked((MouseEvent event) -> {
          //   ((Node)event.getSource()).getScene().getWindow().hide();

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("../GUI/FXMLClientChoisirBoutique.fxml"));
                try {
                    loader.load();
                    } catch (IOException ex) {
                    Logger.getLogger(FXMLClientChoisirEnseigneController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    /*Parent p=loader.getRoot();
                    Stage stage=new Stage();
                    stage.setScene(new Scene(p));*/
        //        Audio audio=Audio.getInstance();
                
              /*  try {
                    InputStream sound =audio.getAudio("Bonjour", Language.ENGLISH);
                    audio.play(sound);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLClientChoisirEnseigneController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JavaLayerException ex) {
                    Logger.getLogger(FXMLClientChoisirEnseigneController.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                
        FXMLClientChoisirBoutiqueController cb=loader.getController();
                try {
                    cb.afficherBoutiqueEnseigne(en.getNom());
                } catch (IOException ex) {
                    Logger.getLogger(FXMLClientChoisirEnseigneController.class.getName()).log(Level.SEVERE, null, ex);
                }
        });     
     
        
            }
            recherche.textProperty().addListener((observable, oldValue, newValue) -> {
                rech=newValue;
                this.initialize(location, resources);
});
 }
    
 

  
}
