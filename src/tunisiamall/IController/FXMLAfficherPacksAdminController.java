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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tunisiamall.DAO.CompteDAO;
import tunisiamall.DAO.PacksPubDAO;
import tunisiamall.entities.Compte;
import tunisiamall.entities.PacksPub;


public class FXMLAfficherPacksAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
        TableView<dataPack> table = new TableView<dataPack>();
ObservableList<dataPack> data = FXCollections.observableArrayList();
dataPack dP = null;
@FXML
        TableColumn c1;
@FXML
        TableColumn c2;
@FXML
        TableColumn c3;
@FXML
        Text alert;
@FXML 
        ImageView precedent;
 PacksPubDAO pDAO = new PacksPubDAO();
ArrayList<PacksPub>  packs= new ArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        upData();
         precedent.setOnMouseClicked((MouseEvent event) -> {
                ((Node) event.getSource()).getScene().getWindow().hide();
           Parent root = null;
           try {
               root = FXMLLoader.load(getClass().getResource("/tunisiamall/GUI/FXMLGestionPackAdmin.fxml"));
           } catch (IOException ex) {
               Logger.getLogger(FXMLAcueilAdminController.class.getName()).log(Level.SEVERE, null, ex);
           }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

            });
    }    
    public  void upData()
    {
           data.removeAll(data);
                packs=(ArrayList<PacksPub>) pDAO.findAll();
        for(PacksPub pack : packs)
        {
            dataPack dP = new dataPack();
            dP.setId(String.valueOf(pack.getId()));
            dP.setNom(pack.getNom());
            dP.setPrix(String.valueOf(pack.getPrix()));
            dP.setDescription(String.valueOf(pack.getDescription()));
            data.add(dP);
        }
        c1.setCellValueFactory(
                new PropertyValueFactory<dataPack, String>("nom"));
         c2.setCellValueFactory(
                new PropertyValueFactory<dataPack, String>("description"));
         c3.setCellValueFactory(
                new PropertyValueFactory<dataPack, String>("prix"));
         table.setItems(data);

    }
@FXML
private void  supprimer(MouseEvent event)
{
    pDAO.removeById(table.getSelectionModel().getSelectedItem().getId());
    alert.setText("Suppression faite ! ");
    upData();
}

    public class dataPack
    {
        String id ;
        String nom;
        String description;
        String prix;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public dataPack() {
        }

        public dataPack(String nom, String description, String prix) {
            this.nom = nom;
            this.description = description;
            this.prix = prix;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPrix() {
            return prix;
        }

        public void setPrix(String prix) {
            this.prix = prix;
        }

        
    }  
}
