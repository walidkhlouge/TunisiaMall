/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import tunisiamall.DAO.PanierDAO;
import tunisiamall.DAO.ProduitDAO;
import tunisiamall.entities.Panier;
import tunisiamall.main.includeClient;

public class FXMLClientAfficherPanierController implements Initializable{
    @FXML 
    AnchorPane anchor1;
    @FXML
    BorderPane anchor2;
    PanierDAO pan=new PanierDAO();
    ProduitDAO prod=new ProduitDAO();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
         afficher();
    }
    
    public void afficher(){
           PanierDAO pdao=new PanierDAO();
       
          AnchorPane a1=new AnchorPane();
        a1.getChildren().clear();
        anchor1.getChildren().clear();
      ArrayList<Panier> paniers=pan.findPanierClient("bassemlog");
        
         a1=new AnchorPane();
        Label l=new Label("Produit");
        Label l2=new Label("quantite");
        Label l3=new Label("Prix Unitaire");
        Label l4=new Label("Total");
        l.setLayoutX(0);
        l.setLayoutY(0);
        l2.setLayoutX(201);
        l2.setLayoutY(0);
        l3.setLayoutX(402);
        l3.setLayoutY(0);
        l4.setLayoutX(603);
        l4.setLayoutY(0);
        a1.setLayoutX(0);
        int y=55;
        a1.setLayoutY(y);
        a1.setLayoutX(0);
        a1.setPrefSize(720,680);
        a1.setStyle("-fx-background-color: #F0F8FF;");
            a1.getChildren().addAll(l,l2,l3,l4);
        anchor1.getChildren().add(a1);
        int i=0;
        for(Panier p : paniers){
            l=new Label(prod.findProduitById(p.getIdProduit()).getNom());
         l2=new Label(prod.findProduitById(p.getIdProduit()).getQuantite()+"");
       l3=new Label(prod.findProduitById(p.getIdProduit()).getPrixUnitaire()+"");
        l4=new Label(prod.findProduitById(p.getIdProduit()).getPrixUnitaire()+"");
         ChoiceBox cb=new ChoiceBox();
      
         for(int j=1;j<=pdao.findQuantiteProdByTaille(p.getIdProduit(), p.getTaille());j++)
           cb.getItems().add(j); 
         
         cb.getSelectionModel().selectFirst();
      
        l.setLayoutX(0);
        l.setLayoutY(0);
        
        l3.setLayoutX(402);
        l3.setLayoutY(0);
        l4.setLayoutX(603);
        l4.setLayoutY(0);
        
        
        
        
        
                  
      
      
        
        
            a1=new AnchorPane();
        if(i%2==0)
                  a1.setStyle("-fx-background-color:  #FFFFFF;");
            else
                  a1.setStyle("-fx-background-color: #F0F8FF;");
             a1.setPrefSize(720,50);
            y+=51;
            a1.setLayoutX(0);
          a1.setLayoutY(y);
          cb.setLayoutX(201);
        cb.setLayoutY(0);
        cb.setPrefSize(100, 20);
        
        Label referencel4=l4;
        
        
         ImageView img0=new ImageView();
        img0.setImage(new Image(getClass().getResource("../GUI/images/statique/supprimer.png").toExternalForm()));
        
        img0.setLayoutX(302);
        img0.setLayoutY(0);
        img0.setFitWidth(20);
        img0.setFitHeight(30);
        img0.setOnMouseClicked((MouseEvent event) -> {
            pan.removeByLoginTailleProduit("bassemlog",p.getIdProduit(),p.getTaille() );
           afficher();
        });      
        
        cb.setOnAction((event)->{
        referencel4.setText((prod.findProduitById(p.getIdProduit()).getPrixUnitaire()*(cb.getSelectionModel().getSelectedIndex()+1))+"");
                });
            a1.getChildren().addAll(l,cb,l3,l4,img0);
            anchor1.getChildren().add(a1);
            i++;
            
        
        
    }
        includeClient.mainLayout.setCenter(anchor2);
    }
   
    
    
    
    

}
