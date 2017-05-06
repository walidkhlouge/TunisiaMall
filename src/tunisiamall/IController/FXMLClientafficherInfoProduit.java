/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tunisiamall.DAO.BoutiqueDAO;
import tunisiamall.DAO.ProduitDAO;
import tunisiamall.entities.Boutique;
import tunisiamall.entities.Produit;
import tunisiamall.main.includeClient;


public class FXMLClientafficherInfoProduit implements Initializable {

    @FXML
    AnchorPane anchor1;
    @FXML
    BorderPane anchor2;
    private int idProduit;
    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
    

    public void afficherInfoProduit(int id) {
       
      ProduitDAO bt=new ProduitDAO();
             int x=0;int y=50;
             Produit en=bt.findProduitById(id);
             

        
             

         
           
            ImageView img0=new ImageView();
        img0.setImage(new Image(getClass().getResource("../GUI/images/produit/"+en.getUrl()).toExternalForm()));
        img0.setLayoutX(20);
        img0.setLayoutY(40);
        img0.setFitWidth(500);
        img0.setFitHeight(400);       
         Label nom=new Label("Nom :"+en.getNom());
         Label info=new Label("Info :"+en.getInfo());
         Label nomens=new Label("nom Enseigne :"+en.getNonEnseigne());
         Label ref=new Label("reference :"+en.getReference());
         Label pu=new Label("Pu :"+en.getPrixUnitaire()+"");
         Label tr=new Label("TR :"+en.getTauxReduction()+"");
         nom.setLayoutX(550);
         nomens.setLayoutX(550);
         ref.setLayoutX(550);
         pu.setLayoutX(550);
         tr.setLayoutX(550);
         nom.setLayoutY(400);
         info.setLayoutY(420);
         nomens.setLayoutY(440);
         ref.setLayoutY(460);
         pu.setLayoutY(480);
         tr.setLayoutY(500);
         
            
         anchor1.getChildren().addAll(nom,info,nomens,ref,pu,tr,img0);
         /*
   img0.setOnMouseClicked((MouseEvent event) -> {
             ((Node)event.getSource()).getScene().getWindow().hide();

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("../GUI/FXMLClientChoisirProduit.fxml"));
                try {
                    loader.load();
                    } catch (IOException ex) {
                    Logger.getLogger(FXMLClientChoisirEnseigneController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    Parent p=loader.getRoot();
                    Stage stage=new Stage();
                    stage.setScene(new Scene(p));
                
        FXMLClientChoisirProduitController cb=loader.getController();
                try {
                   cb.afficherProduitBoutique(en.getId());
                } catch (IOException ex) {
                    Logger.getLogger(FXMLClientChoisirEnseigneController.class.getName()).log(Level.SEVERE, null, ex);
                }
           
              
       
        stage.show();
        }); */     
        
         includeClient.mainLayout.setCenter(anchor2);
    }
    
     
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
