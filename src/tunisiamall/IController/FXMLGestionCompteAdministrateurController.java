/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import tunisiamall.entities.Compte;


public class FXMLGestionCompteAdministrateurController implements Initializable {
@FXML
ImageView precedent;
@FXML
        TableView<dataCompte> table = new TableView<dataCompte>();
ObservableList<dataCompte> data = FXCollections.observableArrayList();
dataCompte dC = null;
@FXML
        TableColumn c1;
@FXML
        TableColumn c2;
@FXML
        TableColumn c3;
@FXML
        Text alert;
 CompteDAO cDAO = new CompteDAO();
ArrayList<Compte> comptes = new ArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        upData();
precedent.setOnMouseClicked((MouseEvent event) -> {
                ((Node) event.getSource()).getScene().getWindow().hide();
           Parent root = null;
           try {
               root = FXMLLoader.load(getClass().getResource("/tunisiamall/GUI/FXMLAcueilAdmin.fxml"));
           } catch (IOException ex) {
               Logger.getLogger(FXMLAcueilAdminController.class.getName()).log(Level.SEVERE, null, ex);
           }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

            });
    }    
    @FXML
    private void activer(MouseEvent event)
    {
        cDAO.updateEtat("activated", table.getSelectionModel().getSelectedItem().getLogin());
        alert.setText("Le compte a été activer");
        upData();
    }
    @FXML
    private void supprimer(MouseEvent event)
    {
        cDAO.removeCompteByLogin(table.getSelectionModel().getSelectedItem().getLogin());
                alert.setText("Le compte a été supprimer");
        upData();
    }
    @FXML
    private void bloquer(MouseEvent event)
    {
        cDAO.updateEtat("suspended",table.getSelectionModel().getSelectedItem().getLogin() );
                alert.setText("Le compte a été bloquer");
        upData();
    }
    public void upData()
    {
        data.removeAll(data);
                comptes=(ArrayList<Compte>) cDAO.findAll();
        for(Compte compte : comptes)
        {
            dataCompte dC = new dataCompte();
            dC.setLogin(compte.getLogin());
            dC.setType(compte.getType());
            dC.setEtat(compte.getEtat());
            data.add(dC);
        }
        c1.setCellValueFactory(
                new PropertyValueFactory<dataCompte, String>("type"));
         c2.setCellValueFactory(
                new PropertyValueFactory<dataCompte, String>("etat"));
         c3.setCellValueFactory(
                new PropertyValueFactory<dataCompte, String>("login"));
         table.setItems(data);

    }
    public class dataCompte
    {
        String login;
        String type;
        String etat;

        public dataCompte() {
        }

        public dataCompte(String login, String type, String etat) {
            this.login = login;
            this.type = type;
            this.etat = etat;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getEtat() {
            return etat;
        }

        public void setEtat(String etat) {
            this.etat = etat;
        }
        
    }
}
