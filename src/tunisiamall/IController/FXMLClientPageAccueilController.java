/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


public class FXMLClientPageAccueilController implements  Initializable {
@FXML
ImageView imageAccueil;
@FXML
BorderPane border;
            
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        imageAccueil.setImage(new Image(getClass().getResource("../GUI/images/tunisia.jpg").toExternalForm()));  
     
     imageAccueil.setPreserveRatio(false);
imageAccueil.setFitWidth(1000);
        border.setLayoutX(0);
        imageAccueil.setLayoutX(0);
     
     
     
     

     
    }
    
}