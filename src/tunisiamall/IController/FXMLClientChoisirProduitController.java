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
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tunisiamall.DAO.BoutiqueDAO;
import tunisiamall.DAO.PanierDAO;
import tunisiamall.DAO.ProduitDAO;
import tunisiamall.entities.Boutique;
import tunisiamall.entities.Panier;

import tunisiamall.entities.Produit;
import tunisiamall.main.includeClient;


public class FXMLClientChoisirProduitController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField recherche;
    @FXML
    BorderPane anchor2;
    @FXML
    AnchorPane anchor1;
    @FXML
    Button panier;
    ProduitDAO bt = new ProduitDAO();
    PanierDAO pan = new PanierDAO();
     
   
      private String rech="";

    public void afficherProduitBoutique(int id) throws IOException {
         recherche=  (TextField)  anchor1.getChildren().get(anchor1.getChildren().indexOf(recherche));   //trekuperi el node recherche
        anchor1.getChildren().clear();
        anchor1.getChildren().add(recherche);
        int x =0;
        int y = 100;
        ArrayList<Produit> l = null;
        if(id==-1 && rech.equals("")){
          System.out.println("le 2 vides");
            recherche=  (TextField) anchor1.getChildren().get(anchor1.getChildren().indexOf(recherche));    //trekuperi el node recherche
        anchor1.getChildren().clear();
        anchor1.getChildren().add(recherche);
             l = bt.findAllB();
        }
        else if ( id==-1 && !rech.equals(""))
               {
                   System.out.println("rech different");
                    recherche=  (TextField) anchor1.getChildren().get(anchor1.getChildren().indexOf(recherche));    //trekuperi el node recherche
                   anchor1.getChildren().clear();
        anchor1.getChildren().add(recherche);
                 
         l = bt.findProdByIntituleDynamic(rech);
               }
        
         else if((id!=-1 && rech.equals("")))
             {
                   System.out.println("id m3eby");
                   System.out.println("-------------------"+bt.findProduitByIdBass(id).getNom());
                 recherche=  (TextField) anchor1.getChildren().get(anchor1.getChildren().indexOf(recherche));     //trekuperi el node recherche
        anchor1.getChildren().clear();
        anchor1.getChildren().add(recherche);
                  l = bt.findProduitBoutique(id);
             }
         else if((id!=-1 && !rech.equals("")))
             {
                   System.out.println("2 m3ebyin");
                   System.out.println("-------------------"+bt.findProduitByIdBass(id).getNom());
                 recherche=  (TextField) anchor1.getChildren().get(anchor1.getChildren().indexOf(recherche));     //trekuperi el node recherche
        anchor1.getChildren().clear();
        anchor1.getChildren().add(recherche);
                  l = bt.findProdByIntituleDynamic(rech);
             }
        panier.setText("nombre de produit dans le panier (" + pan.compter() + ")");
        System.out.println("iciiiiiiiiiiiiiiiiiiiiiiiiiiiiiii        "+l);
        for (Produit en : l) {
             
            ImageView img0 = new ImageView();
            img0.setImage(new Image(getClass().getResource("../GUI/images/produit/" + en.getUrl()).toExternalForm()));
            img0.setLayoutX(x);
            img0.setLayoutY(y);
            img0.setFitWidth(220);
            img0.setFitHeight(170);
            Label lab = new Label(en.getNom());
            Label prix = new Label("prix :" + en.getPrixUnitaire());
            Button bo = new Button("ajouter au panier");
            //Button binf = new Button("Plus D'Infos");
            ChoiceBox binf=new ChoiceBox();
          
        for(String t : bt.findTailleByProduit2(en.getId(),"bassemlog"))
           binf.getItems().add(t);
        Label lab2 = new Label("taille:");
         binf.getSelectionModel().selectFirst();
         
         
 lab2.setLayoutX(x +10);
            lab2.setLayoutY(y+240);         
            binf.setLayoutX(x +40);
            binf.setLayoutY(y+240);
            lab.setLayoutX(x + 10);//150 / 4 bech tji fel west
            lab.setLayoutY(y+180);
            prix.setLayoutX(x + 10);
            prix.setLayoutY(y+200);
            bo.setLayoutX(x + 100);
            bo.setLayoutY(y+240);
            img0.setOnMouseClicked((MouseEvent event) -> {
                InfoProduit(en.getId(), event);
            });
         ScrollPane   scrollPane = new ScrollPane();
        scrollPane.setPrefSize(400, 400);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
           
          /*  binf.setOnAction((event) -> {
                InfoProduit(en.getId(), event);
            });
  */
              if(!bt.findTailleByProduit(en.getId(),"bassemlog").isEmpty())
              {
        anchor1.getChildren().addAll(lab, prix, img0, bo, binf,lab2);
              
         x += 230;
            if (x == 920) {
                y += 300;
                x = 0;
            }
            
            bo.setId(en.getNom());
//            if (pan.findPanierClientProduit("bassemlog", en.getId())) {
//                bo.setVisible(false);
//            }
            bo.setOnAction((event) -> {
                pan.add(new Panier("bassemlog", en.getId(), 1,binf.getSelectionModel().getSelectedItem()+""));
                binf.getItems().remove(binf.getSelectionModel().getSelectedIndex());
               if(binf.getItems().size()==0)
               { bo.setVisible(false);
               binf.setVisible(false);
               lab2.setVisible(false);
               }
               else
                      binf.getSelectionModel().selectFirst();
                panier.setText("nombre de produit dans le panier (" + pan.compter() + ")");
            });
              }
        }
//----------------------    anchor1.getScene().lookup("#");
 includeClient.mainLayout.setCenter(anchor2);
    }

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
       recherche=  (TextField)  anchor1.getChildren().get(anchor1.getChildren().indexOf(recherche));   //trekuperi el node recherche
        anchor1.getChildren().clear();
        anchor1.getChildren().add(recherche);
            afficherProduitBoutique(-1);
        } catch (IOException ex) {
            Logger.getLogger(FXMLClientChoisirProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
                rech=newValue;
                this.initialize(url, rb);
});

    }

    public void InfoProduit(int id, Event event) {
        
     //   ((Node) event.getSource()).getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../GUI/FXMLClientafficherInfoProduit.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLClientChoisirEnseigneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       /* Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));*/

        FXMLClientafficherInfoProduit cb = loader.getController();
        
            cb.afficherInfoProduit(id);
       
      //  stage.show();
    }
}
