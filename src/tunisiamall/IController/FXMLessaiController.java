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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tunisiamall.main.includeClient;


public class FXMLessaiController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    AnchorPane ok;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Button b=new Button("ok");
       
        b.setOnAction((event) -> {
              
         
         ((Node) event.getSource()).getScene().getWindow().hide();

            try {
                /*  FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../GUI/FXMLClientAccueilInclude.fxml"));
                
                try {
                loader.load();
                } catch (IOException ex) {
                Logger.getLogger(FXMLessaiController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Parent p = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                
                
                stage.show();*/
                includeClient.MainView();
            } catch (IOException ex) {
                Logger.getLogger(FXMLessaiController.class.getName()).log(Level.SEVERE, null, ex);
            }
      
           });
         ok.getChildren().add(b);
    }
                
    
}
