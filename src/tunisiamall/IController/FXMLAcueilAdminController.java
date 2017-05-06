/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;



public class FXMLAcueilAdminController implements Initializable {
@FXML 
JFXButton compteB;
@FXML
JFXButton statB;
@FXML 
JFXButton packB;

/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       compteB.setOnAction((event) -> {
           ((Node) event.getSource()).getScene().getWindow().hide();
           Parent root = null;
           try {
               root = FXMLLoader.load(getClass().getResource("/tunisiamall/GUI/FXMLGestionCompteAdministrateur.fxml"));
           } catch (IOException ex) {
               Logger.getLogger(FXMLAcueilAdminController.class.getName()).log(Level.SEVERE, null, ex);
           }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

            });
       
      statB.setOnAction((event) -> {
           ((Node) event.getSource()).getScene().getWindow().hide();
           Parent root = null;
           try {
               root = FXMLLoader.load(getClass().getResource("/tunisiamall/GUI/FXMLStatistiqueAdmin.fxml"));
           } catch (IOException ex) {
              Logger.getLogger(FXMLAcueilAdminController.class.getName()).log(Level.SEVERE, null, ex);
           }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
           });
      packB.setOnAction((event) -> {
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
    
}
