/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tunisiamall.DAO.EnseigneDAO;
import tunisiamall.DAO.ProduitDAO;
import tunisiamall.entities.Enseigne;
import tunisiamall.entities.Produit;
import tunisiamall.main.MainRespE;



public class FXMLModifProduitRespEController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private MainRespE main;
    Stage stage;
    
   

    @FXML
    TextField produitlabel;
   
     @FXML
    TextField enseignelabel;
     
     @FXML
     TextField infolabel;
     @FXML
     TextField ptslabel;
     @FXML 
    Button update;
     
     @FXML 
    Button cancel;
     
      @FXML 
    Button parcourir;
      
     @FXML
    Label imagenameLabel ; 
     
    
             
      
     @FXML
    ImageView  updateimageView;
        
    
     ProduitDAO pDao = new ProduitDAO();
     
     Produit p = new Produit();
     EnseigneDAO eDao = new EnseigneDAO();
    List<Enseigne> le = new ArrayList<>();
     
//    private FXMLProduitRespEController mainP;
    private boolean okClicked=false;
    
      public void setMain(MainRespE main, Produit p,Stage stage){
        this.p=p;
        this.main=main;
        this.stage=stage;
        fillProduitDetails();
        imagenameLabel.setText(p.getUrl());
        File file = new File("src/tunisiamall/GUI/images/produit/" + p.getUrl());
        Image image = new Image(file.toURI().toString());
        updateimageView.setImage(image);
    }
      
      
     public void update()
     
     {
    
        p.setNom(produitlabel.getText());
        p.setNonEnseigne(enseignelabel.getText());
        p.setInfo(infolabel.getText());
        p.setUrl(imagenameLabel.getText());
        p.setPtsfidelite(Integer.parseInt(ptslabel.getText()));
        
     
       pDao.updateProduitE(p,produitlabel.getText(),enseignelabel.getText(),infolabel.getText(),imagenameLabel.getText(),Integer.parseInt(ptslabel.getText()));
       
        okClicked=true;
       
             

       stage.close();
       
     }
     public boolean isOKClicked(){
         
        
         return okClicked;
     }
       public void handleCancel() {

        stage.close();
    }
     
     public void fillProduitDetails() {
        File file = new File("src/tunisiamall/GUI/images/produit/" + p.getUrl());
        Image image = new Image(file.toURI().toString());
        produitlabel.setText(p.getNonEnseigne());
        enseignelabel.setText(p.getNom());
        infolabel.setText(p.getInfo());
        updateimageView.setImage(image);
        imagenameLabel.setText(p.getUrl());
        ptslabel.setText(Integer.toString(p.getPtsfidelite()));
    }

      
         public void  parcourir()
         
         {
      FileChooser fileChooser = new FileChooser();

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            imagenameLabel.setText(selectedFile.getName());
            File file = new File("src/tunisiamall/GUI/images/produit/" + selectedFile.getName());
            File source = new File(selectedFile.getAbsolutePath());
            Image image = new Image(file.toURI().toString());
            updateimageView.setImage(image);
                        try {
                org.apache.commons.io.FileUtils.copyFile(source, file);
                Image image2 = new Image(source.toURI().toString());
                updateimageView.setImage(image2);
            } catch (IOException ef) {
                ef.printStackTrace();
            }

        }
            }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
   
}
