package tunisiamall.IController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tunisiamall.entities.Enseigne;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class FXMLEnseigneModifController implements Initializable{
     @FXML
    ImageView imageView;
    Enseigne e=new Enseigne();

    public Enseigne getE() {
        return e;
    }

    public void setE(Enseigne e) {
        this.e = e;
    }
    
public void chooseFile(ActionEvent actionEvent){
    FileChooser chooser = new FileChooser();
    chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
     
    chooser.setTitle("Open File");
    File file = chooser.showOpenDialog(new Stage());
    if(file != null) {
        String imagepath = file.getPath();
        System.out.println("file:"+imagepath);
       Image image = new Image(imagepath);
        imageView.setImage(image);
    }
}
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         File file2 = new File("src/tunisiamall/GUI/images/enseigne/celio.jpg");
        Image image2 = new Image(file2.toURI().toString());
        imageView.setImage(image2);
     
        
    }
    @FXML
    public void back(ActionEvent event) throws IOException{
        ((Node)event.getSource()).getScene().getWindow().hide();
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/tunisiamall/GUI/FXMLResponsableEchoisirEnseigneModif.fxml"));
        loader.load();
        Parent p=loader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(p));
        stage.show();
    }
    
    
}
