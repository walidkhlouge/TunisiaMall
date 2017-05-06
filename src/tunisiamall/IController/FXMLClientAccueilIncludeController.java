/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tunisiamall.DAO.CompteDAO;
import tunisiamall.DAO.PanierDAO;
import tunisiamall.entities.Compte;


public class FXMLClientAccueilIncludeController  implements Initializable {
@FXML
BorderPane mainLayout;
@FXML
Button accueil;
@FXML
Button catalogue;
@FXML
Button enseigne;
@FXML
Button boutique;
@FXML
Button produit;
@FXML
Button notification;
@FXML
Button deconnexion;
@FXML 
Label bienvenue;        
@FXML
ImageView improfile ;
 @FXML
  Button panier;  
 @FXML
Button map;         
    PanierDAO pan = new PanierDAO();
    CompteDAO cp=new CompteDAO();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    Compte c = cp.chercherCompteLogin(Compte.connected.getLogin());
    bienvenue.setText("bievenue "+cp.chercherCompteLogin(Compte.connected.getLogin()).getNom());
    String photo=c.getPhoto();
        
/*        improfile.setImage(new Image(getClass().getResource("../GUI/images/compte/"+photo).toExternalForm()));
        improfile.setFitWidth(0);
        improfile.setLayoutX(20);
        improfile.setLayoutY(100);
        improfile.setFitHeight(202);*/
        panier.setWrapText(true);
         panier.setText("nombre de produit panier(" + pan.compter() + ")");
    goMenu("FXMLClientPageAccueil");
    accueil.setOnAction((event) -> {
             initialize(location,resources);
            });
   /*catalogue.setOnAction((event) -> {
                goMenu("FXMLCatalogue");
            });*/
   enseigne.setOnAction((event) -> {
            goMenu("FXMLClientChoisirEnseigne");
            });
   panier.setOnAction((event) -> {
            goMenu("FXMLClientAfficherPanier");
            });
   boutique.setOnAction((event) -> {
            goMenu("FXMLClientChoisirBoutique");
            });
   produit.setOnAction((event) -> {
            goMenu("FXMLClientChoisirProduit");
            });
   map.setOnAction((event) -> {
            goMenu("FXMLDocument");
            });
   deconnexion.setOnAction((event) -> {
            goMenu("FXMLAuth");
            });
 /*  map.setOnAction((event) -> {
            g.start(stage);
        } catch (Exception ex) {
            Logger.getLogger(FXMLClientAccueilIncludeController.class.getName()).log(Level.SEVERE, null, ex);
        }
            });*/
   }
    
    
    
    
    
    /*
    @FXML
    public void goPanier(ActionEvent event) {

      

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../GUI/FXMLClientAfficherPanier.fxml"));
      
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLClientChoisirProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         FXMLClientAfficherPanierController  cb = loader.getController();
          cb.afficher();
      
    }
    
    */
    
    public void goMenu(String ressources){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("../GUI/"+ressources+".fxml"));
    try {
        BorderPane mainItems=loader.load();
        
        mainLayout.setCenter(mainItems);
    } catch (IOException ex) {
        Logger.getLogger(FXMLClientAccueilIncludeController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
