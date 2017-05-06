/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.io.File;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tunisiamall.DAO.BoutiqueDAO;
import tunisiamall.DAO.CompteDAO;
import tunisiamall.DAO.ProduitDAO;
import tunisiamall.entities.Compte;
import tunisiamall.entities.Produit;
import tunisiamall.main.Gestion;


public class FXMLGestionProduitController implements Initializable {

@FXML
Button btnDecon;
@FXML       
Button btnajouter; 
@FXML
Button btnmodifier;
@FXML
Button btnsupprimer;
@FXML
Button btnAjouTaille;
@FXML
TextField txtdetail;
@FXML
TextField txtstock;
@FXML
TextField txttaille;
@FXML
ComboBox cmbproduit;
@FXML
ComboBox ComboTaille;
@FXML
AnchorPane anchor;
@FXML
AnchorPane anchorp;
@FXML
Label labEnseigne;
@FXML
Label labUser;
@FXML
ImageView imgU;

CompteDAO Cdao;
Compte C;
ProduitDAO P;
Boolean b=false;
ImageView imgp=new ImageView();
ImageView img0;
Produit px;
BoutiqueDAO bdao=new BoutiqueDAO();
String login=Compte.connected.getLogin();
String nomEnseigne;
@FXML
public void deconnecter(){
    //Deconnexion
}
@FXML
public void ModifCoord(ActionEvent event) throws Exception{
    ((Node)(event.getSource())).getScene().getWindow().hide();
    Stage stage=new Stage();
    stage.setHeight(700);
    stage.setWidth(475);
    stage.sizeToScene();
    stage.show();
}
@FXML
public void test(){
    if(txtdetail.getText().equals("") || txtstock.getText().equals("") )
        btnajouter.setDisable(true);
    else if ((b=true) && (cmbproduit.getSelectionModel().getSelectedItem()=="Produits Enseigne"))
        btnajouter.setDisable(false);
    
}

@FXML
public void modifier(ActionEvent e2){
    
    P.updateProduit(px, parseInt(txtstock.getText()),parseFloat(txtdetail.getText()));
         anchor.getChildren().clear();
           
            btnAjouTaille.setDisable(false);
        P=new ProduitDAO();
        ArrayList<Produit> L=new ArrayList<Produit>();
        int x=40,x1=150;
        int y=50,y1=50;
        int idB=bdao.findIdBoutiqueBylogin(login);
        L=P.findProduitByBoutique(idB);
        for(Produit en : L)
        {   
        ImageView img0=new ImageView();
        img0.setImage(new Image(getClass().getResource("../GUI/images/produit/"+en.getUrl()).toExternalForm()));
        img0.setLayoutX(x);
        img0.setLayoutY(y);
        img0.setFitWidth(150);
        img0.setFitHeight(170);       
        Label lab=new Label(en.getNom());
        lab.setLayoutX(x+37.5);
        lab.setLayoutY(y+180);
        anchor.getChildren().addAll(img0,lab);   
        y+=200;
                img0.setOnMouseClicked((MouseEvent e) -> {
                    anchorp.getChildren().clear();
        imgp.setImage(new Image(getClass().getResource("../GUI/images/produit/"+en.getUrl()).toExternalForm()));
        px=en;
        imgp.setLayoutX(x1);
        imgp.setLayoutY(y1);
        imgp.setFitWidth(300);
        imgp.setFitHeight(350);
        Label l1=new Label("Nom du Produit: "+en.getNom());  
        Label l2=new Label("Référence: "+en.getReference());  
        l1.setLayoutX(x1+350);
        l1.setLayoutY(y1+80);
        l2.setLayoutX(x1+350);
        l2.setLayoutY(y1+100);
          anchorp.getChildren().addAll(imgp,l1,l2);
          
            btnAjouTaille.setDisable(false);
          btnajouter.setDisable(true);
          btnmodifier.setDisable(false);
          btnsupprimer.setDisable(false);
          txtdetail.setText(en.getPrixUnitaire()+"");
          txtstock.setText(en.getQuantite()+"");
          
               });
    
    
}}
@FXML
public void ajouter(ActionEvent e3){ 
    if ((txtstock.getText()!="") && (txtdetail.getText())!=""){
        bdao=new BoutiqueDAO();
        
            btnAjouTaille.setDisable(false);
        P=new ProduitDAO();   
        anchor.getChildren().clear();
        anchorp.getChildren().clear();
        int idB=bdao.findIdBoutiqueBylogin(login);
        nomEnseigne=bdao.findEnseigneByIdBoutique(idB);
        P.updateProduit(px, idB, parseInt(txtstock.getText()),parseFloat(txtdetail.getText()));;
            
        ArrayList<Produit> L=new ArrayList<Produit>();
        L=P.findProduitByEnseigne(nomEnseigne,idB);
        int x=40,x1=150;
        int y=50,y1=50;
        for(Produit en : L)
        {   
        img0=new ImageView();
        img0.setImage(new Image(getClass().getResource("../GUI/images/produit/"+en.getUrl()).toExternalForm()));
        img0.setLayoutX(x);
        img0.setLayoutY(y);
        img0.setFitWidth(150);
        img0.setFitHeight(170);       
        Label lab=new Label(en.getNom());
        lab.setLayoutX(x+37.5);
        lab.setLayoutY(y+180);
        anchor.getChildren().addAll(img0,lab);   
        y+=200;
        img0.setOnMouseClicked((MouseEvent e) -> {
        anchorp.getChildren().clear();
        imgp.setImage(new Image(getClass().getResource("../GUI/images/produit/"+en.getUrl()).toExternalForm()));
        px=en;
        imgp.setLayoutX(x1);
        imgp.setLayoutY(y1);
        imgp.setFitWidth(300);
        imgp.setFitHeight(350);
        Label l1=new Label("Nom du Produit: "+en.getNom());  
        Label l2=new Label("Référence: "+en.getReference());  
        l1.setLayoutX(x1+350);
        l1.setLayoutY(y1+80);
        l2.setLayoutX(x1+350);
        l2.setLayoutY(y1+100);
          anchorp.getChildren().addAll(imgp,l1,l2);
          btnajouter.setDisable(true);
          btnmodifier.setDisable(true);
          btnsupprimer.setDisable(true);
          
            btnAjouTaille.setDisable(false);
      });
        txtdetail.setText("");
          txtstock.setText("");
     
}
    }
    btnajouter.setDisable(true);
   
}
@FXML
public void supprimer(ActionEvent e4){
    bdao=new BoutiqueDAO();
    P=new ProduitDAO();
    int idB=bdao.findIdBoutiqueBylogin(login);
    
    P.removeProduitFromBoutiqueById(px.getId());
    anchor.getChildren().clear();
    anchorp.getChildren().clear();
        
            btnAjouTaille.setDisable(true);
        btnajouter.setDisable(true);
        btnmodifier.setDisable(true);
        btnsupprimer.setDisable(true);
        txtdetail.setText("");
        txtstock.setText("");
       P=new ProduitDAO();
        ArrayList<Produit> L=new ArrayList<Produit>();
        int x=40,x1=150;
        int y=50,y1=50;
        L=P.findProduitByBoutique(idB);
        for(Produit en : L)
        {   
        ImageView img0=new ImageView();
        img0.setImage(new Image(getClass().getResource("../GUI/images/produit/"+en.getUrl()).toExternalForm()));
        img0.setLayoutX(x);
        img0.setLayoutY(y);
        img0.setFitWidth(150);
        img0.setFitHeight(170);       
        Label lab=new Label(en.getNom());
        lab.setLayoutX(x+37.5);
        lab.setLayoutY(y+180);
        anchor.getChildren().addAll(img0,lab);   
        y+=200;
                img0.setOnMouseClicked((MouseEvent e) -> {
                    anchorp.getChildren().clear();
        imgp.setImage(new Image(getClass().getResource("../GUI/images/produit/"+en.getUrl()).toExternalForm()));
        px=en;
        imgp.setLayoutX(x1);
        imgp.setLayoutY(y1);
        imgp.setFitWidth(300);
        imgp.setFitHeight(350);
        Label l1=new Label("Nom du Produit: "+en.getNom());  
        Label l2=new Label("Référence: "+en.getReference());  
        l1.setLayoutX(x1+350);
        l1.setLayoutY(y1+80);
        l2.setLayoutX(x1+350);
        l2.setLayoutY(y1+100);
          anchorp.getChildren().addAll(imgp,l1,l2);
          btnajouter.setDisable(true);
          btnmodifier.setDisable(false);
          btnsupprimer.setDisable(false);
          
            btnAjouTaille.setDisable(false);
          txtdetail.setText(en.getPrixUnitaire()+"");
          txtstock.setText(en.getQuantite()+"");
          b=true;
          
               });
    } 
}


@FXML
private void AjouterTaille(ActionEvent e17){
    ProduitDAO Pdao=new ProduitDAO();
    if ((txtstock.getText()!="")&& (txttaille.getText()!=""))
    {btnAjouTaille.setDisable(false); 
    Pdao.AddTaille(txttaille.getText(),Integer.parseInt(txtstock.getText()), px.getId());}
    
}
@FXML
private void handleButtonAction(ActionEvent event) {
    
    int idB=bdao.findIdBoutiqueBylogin(login);
    nomEnseigne=bdao.findEnseigneByIdBoutique(idB);
        if(cmbproduit.getSelectionModel().getSelectedItem()=="Produits Enseigne"){
            anchor.getChildren().clear();
            anchorp.getChildren().clear();
            btnajouter.setDisable(true);
            
            btnAjouTaille.setDisable(true);
        P=new ProduitDAO();
        ArrayList<Produit> L=new ArrayList<Produit>();
        int x=40,x1=150;
        int y=50,y1=50;
        L=P.findProduitByEnseigne(nomEnseigne,idB);
        for(Produit en : L)
        {   
        img0=new ImageView();
        img0.setImage(new Image(getClass().getResource("../GUI/images/produit/"+en.getUrl()).toExternalForm()));
        img0.setLayoutX(x);
        img0.setLayoutY(y);
        img0.setFitWidth(150);
        img0.setFitHeight(170);       
        Label lab=new Label(en.getNom());
        lab.setLayoutX(x+37.5);
        lab.setLayoutY(y+180);
        anchor.getChildren().addAll(img0,lab);   
        y+=200;
                img0.setOnMouseClicked((MouseEvent e) -> {
                    anchorp.getChildren().clear();
        imgp.setImage(new Image(getClass().getResource("../GUI/images/produit/"+en.getUrl()).toExternalForm()));
        px=en;
        
            btnAjouTaille.setDisable(false);
        imgp.setLayoutX(x1);
        imgp.setLayoutY(y1);
        imgp.setFitWidth(300);
        imgp.setFitHeight(350);
        Label l1=new Label("Nom du Produit: "+en.getNom());  
        Label l2=new Label("Référence: "+en.getReference());  
        l1.setLayoutX(x1+350);
        l1.setLayoutY(y1+80);
        l2.setLayoutX(x1+350);
        l2.setLayoutY(y1+100);
          anchorp.getChildren().addAll(imgp,l1,l2);
          btnajouter.setDisable(true);
          btnmodifier.setDisable(true);
          btnsupprimer.setDisable(true);
      });
        txtdetail.setText("");
          txtstock.setText("");
    }  }
        else if(cmbproduit.getSelectionModel().getSelectedItem()=="Produits Boutique"){
            anchor.getChildren().clear();
            anchorp.getChildren().clear();
        P=new ProduitDAO();
        ArrayList<Produit> L=new ArrayList<Produit>();
        int x=40,x1=150;
        int y=50,y1=50;
        L=P.findProduitByBoutique(idB);
        for(Produit en : L)
        {   
        ImageView img0=new ImageView();
        img0.setImage(new Image(getClass().getResource("../GUI/images/produit/"+en.getUrl()).toExternalForm()));
        img0.setLayoutX(x);
        img0.setLayoutY(y);
        img0.setFitWidth(150);
        img0.setFitHeight(170);       
        Label lab=new Label(en.getNom());
        lab.setLayoutX(x+37.5);
        lab.setLayoutY(y+180);
        anchor.getChildren().addAll(img0,lab);   
        y+=200;
                img0.setOnMouseClicked((MouseEvent e) -> {
                    anchorp.getChildren().clear();
        imgp.setImage(new Image(getClass().getResource("../GUI/images/produit/"+en.getUrl()).toExternalForm()));
        
        
            btnAjouTaille.setDisable(false);
        px=en;
        imgp.setLayoutX(x1);
        imgp.setLayoutY(y1);
        imgp.setFitWidth(300);
        imgp.setFitHeight(350);
        Label l1=new Label("Nom du Produit: "+en.getNom());  
        Label l2=new Label("Référence: "+en.getReference());  
        l1.setLayoutX(x1+350);
        l1.setLayoutY(y1+80);
        l2.setLayoutX(x1+350);
        l2.setLayoutY(y1+100);
          anchorp.getChildren().addAll(imgp,l1,l2);
          btnajouter.setDisable(true);
          btnmodifier.setDisable(false);
          btnsupprimer.setDisable(false);
          txtdetail.setText(en.getPrixUnitaire()+"");
          txtstock.setText(en.getQuantite()+"");
          b=true;
               });
    }  }
        
                
                    
                    
}        






@Override
    public void initialize(URL url, ResourceBundle rb) {
        labUser.setText(login);
        C=new Compte();
        Cdao=new CompteDAO();
        C=Cdao.findCompteByLogin(login);
        File file = new File("src/tunisiamall/GUI/images/"+C.getPhoto());
            File source = new File(file.getAbsolutePath());
            
                
                Image image2 = new Image(source.toURI().toString());
                imgU.setImage(image2);
          cmbproduit.getItems().addAll(
    "Produits Enseigne",
    "Produits Boutique"
);
        bdao=new BoutiqueDAO();
        btnajouter.setDisable(true);
        
            btnAjouTaille.setDisable(true);
       
        P=new ProduitDAO();
        int idB=bdao.findIdBoutiqueBylogin(login);
        
        
        nomEnseigne=bdao.findEnseigneByIdBoutique(idB);
        labEnseigne.setText(labEnseigne.getText()+" "+nomEnseigne);
        labUser.setText(login);
        ArrayList<Produit> L=new ArrayList<Produit>();
        int x=40,x1=150;
        int y=50,y1=50;
        L=P.findProduitByEnseigne(nomEnseigne,idB);;
        for(Produit en : L)
        {   
        img0=new ImageView();
        img0.setImage(new Image(getClass().getResource("../GUI/images/produit/"+en.getUrl()).toExternalForm()));
        img0.setLayoutX(x);
        img0.setLayoutY(y);
        img0.setFitWidth(150);
        img0.setFitHeight(170);       
        Label lab=new Label(en.getNom());
        lab.setLayoutX(x+37.5);
        lab.setLayoutY(y+180);
        anchor.getChildren().addAll(img0,lab);   
        y+=200;
            img0.setOnMouseClicked((MouseEvent event) -> {
                anchorp.getChildren().clear();
                imgp.setImage(new Image(getClass().getResource("../GUI/images/produit/"+en.getUrl()).toExternalForm()));
                imgp.setLayoutX(x1);
                //tailles
                
            
                imgp.setLayoutY(y1);
                imgp.setFitWidth(300);
                imgp.setFitHeight(350);
                Label l1=new Label("Nom du Produit: "+en.getNom());  
                Label l2=new Label("Référence: "+en.getReference());  
                l1.setLayoutX(x1+350);
                l1.setLayoutY(y1+80);
                l2.setLayoutX(x1+350);
                l2.setLayoutY(y1+100);
                anchorp.getChildren().addAll(imgp,l1,l2);
                //anchor.getChildren().clear();
                btnmodifier.setDisable(true);
                btnsupprimer.setDisable(true);
                
            btnAjouTaille.setDisable(false);
                px=en;
                
      });
    }
}
}
     
 
     
