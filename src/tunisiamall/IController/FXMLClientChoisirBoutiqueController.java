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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tunisiamall.DAO.BoutiqueDAO;
import tunisiamall.entities.Boutique;
import tunisiamall.main.includeClient;


public class FXMLClientChoisirBoutiqueController implements Initializable {
    private String nomEnseigne="";
    private FXMLClientChoisirEnseigneController athis;
    public FXMLClientChoisirEnseigneController getAthis(){
        return athis;
    }
    @FXML
    AnchorPane anchor1;
    @FXML
    BorderPane anchor2;
    @FXML
    TextField recherche;
     BoutiqueDAO bt=new BoutiqueDAO();
      private String rech="";
    @FXML 
    BorderPane mainLayout;
    public String getNomEnseigne() {
        return nomEnseigne;
    }
    public void afficherBoutiqueEnseigne(String nomEnseigne) throws IOException {
            recherche=  (TextField) anchor1.getChildren().get(0);    //trekuperi el node recherche
        anchor1.getChildren().clear();
        anchor1.getChildren().add(recherche);
     
             int x=0;int y=100;
              ArrayList<Boutique> l;
             if (nomEnseigne.equals("") && rech.equals("")){
                  System.out.println("1");  
                 recherche=  (TextField) anchor1.getChildren().get(0);    //trekuperi el node recherche
        anchor1.getChildren().clear();
        anchor1.getChildren().add(recherche);
                   l=bt.findAll();
             }
             else if ( nomEnseigne.equals("") && !rech.equals(""))
               {
                        System.out.println("rech: "+rech);      
                   recherche=  (TextField) anchor1.getChildren().get(0);    //trekuperi el node recherche
                   anchor1.getChildren().clear();
        anchor1.getChildren().add(recherche);
              l=bt.findBoutByIntituleDynamic(rech);
               }
                       
               
               
               
             
             else 
             {
                     System.out.println("ensegne :"+nomEnseigne);
                 recherche=  (TextField) anchor1.getChildren().get(0);    //trekuperi el node recherche
        anchor1.getChildren().clear();
        anchor1.getChildren().add(recherche);
              l=bt.findBoutiqueByEnseigne(nomEnseigne);
             }
    
        for(Boutique en : l)
        {
        ImageView img0=new ImageView();
        img0.setImage(new Image(getClass().getResource("../GUI/images/boutique/"+en.getUrl()).toExternalForm()));
        img0.setLayoutX(x);
        img0.setLayoutY(y);
        img0.setFitWidth(150);
        img0.setFitHeight(170);       
         Label lab=new Label(en.getIntitule());
         lab.setLayoutX(x+37.5);//150 / 4 bech tji fel west
         lab.setLayoutY(y+170+10);
            x += 230;
            if (x == 920) {
                y += 250;
                x = 0;
            }
         anchor1.getChildren().add(lab);
         anchor1.getChildren().add(img0); 
   img0.setOnMouseClicked((MouseEvent event) -> {
          //   ((Node)event.getSource()).getScene().getWindow().hide();

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("../GUI/FXMLClientChoisirProduit.fxml"));
                try {
                    loader.load();
                    } catch (IOException ex) {
                    Logger.getLogger(FXMLClientChoisirEnseigneController.class.getName()).log(Level.SEVERE, null, ex);
                }
                  /*  Parent p=loader.getRoot();
                    Stage stage=new Stage();
                    stage.setScene(new Scene(p));*/
                
        FXMLClientChoisirProduitController cb=loader.getController();
                try {
                    
                   cb.afficherProduitBoutique(en.getId());
                } catch (IOException ex) {
                    Logger.getLogger(FXMLClientChoisirEnseigneController.class.getName()).log(Level.SEVERE, null, ex);
                }
       // stage.show();
        });      
   
    includeClient.mainLayout.setCenter(anchor2);
        
        
    }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        try {
         
            this.afficherBoutiqueEnseigne(nomEnseigne);
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLClientChoisirBoutiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
             recherche.textProperty().addListener((observable, oldValue, newValue) -> {
                rech=newValue;
                this.initialize(url, rb);
        
        });
    }

    
    
}
