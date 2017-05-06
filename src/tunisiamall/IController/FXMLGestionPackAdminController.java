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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tunisiamall.DAO.PacksPubDAO;


public class FXMLGestionPackAdminController implements Initializable {
@FXML
ImageView precedent;
@FXML
TextField nomI;
@FXML
TextField descI;
@FXML
TextField prixI;
@FXML
Button ajoutB;
PacksPubDAO packdao=new PacksPubDAO();
@FXML
Text succ;
@FXML
ImageView voirB;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
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
            voirB.setOnMouseClicked((MouseEvent event) -> {
                ((Node) event.getSource()).getScene().getWindow().hide();
           Parent root = null;
           try {
               root = FXMLLoader.load(getClass().getResource("/tunisiamall/GUI/FXMLAfficherPacksAdmin.fxml"));
           } catch (IOException ex) {
               Logger.getLogger(FXMLAcueilAdminController.class.getName()).log(Level.SEVERE, null, ex);
           }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

            });
            
            
            ajoutB.setOnAction((event) -> {
                if    (!(nomI.getText().toString().equals("")|| descI.getText().toString().equals("")|| prixI.getText().toString().equals("") ))
                {
                    packdao.addPacksNad(nomI.getText().toString(),descI.getText().toString(),Float.parseFloat(prixI.getText().toString()));
                succ.setText(" pack ajoute avec success");
                }
                 else
                succ.setText(" veuillez remplire tous les champs*");
            });
             prixI.textProperty().addListener(new ChangeListener<String>() {
   

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                     try 
                     {if (newValue.matches("\\d*")) {
            int value = Integer.parseInt(newValue);
        } 
                     else {
            prixI.setText(oldValue);
        }
                     }
                catch(IllegalArgumentException |StackOverflowError ex){
                prixI.setText("");
            }
                }

            });
       
        // TODO
    }    
    
}
