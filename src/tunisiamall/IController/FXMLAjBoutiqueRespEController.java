/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tunisiamall.DAO.BoutiqueDAO;
import tunisiamall.DAO.CompteDAO;
import tunisiamall.DAO.EnseigneDAO;
import tunisiamall.entities.Boutique;
import tunisiamall.main.MainRespE;


public class FXMLAjBoutiqueRespEController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    TextField intitule;

    @FXML
    TextField nomE;

    @FXML
    TextField respB;
    
      @FXML
    Button cancel;

      
      @FXML
    ImageView ajoutimageView;
    Stage stage;
    Parent root;

    @FXML
    Button parcourir;
    @FXML
    Button newB;
    @FXML
    Label imagenameLabel;
    
   
    
    BoutiqueDAO bDao = new BoutiqueDAO();
    CompteDAO cDao = new CompteDAO();
    EnseigneDAO eDao = new EnseigneDAO();
    private MainRespE main;

    private FXMLBoutiqueRespEController mainB;

    public void setMain(MainRespE main, Stage stage) {

        this.main = main;
        this.stage=stage;
    }

    public void newB() {

        Boutique b = new Boutique();
        b.setIntitule(intitule.getText());
        b.setLoginResponsableBoutique(respB.getText());
        b.setNomEnseigne(nomE.getText());
        
        b.setUrl(imagenameLabel.getText());

        if ((eDao.seekEnseignebyNom(nomE.getText())) && cDao.seekCompteByLoginType(respB.getText())) {

            bDao.addBoutique2(b);
            main.getData().add(b);
           

                }
     stage.close();
    }
    
     public void handleCancel() {

        stage.close();
    }

    @FXML
    public void parcourir() {
        
           FileChooser fileChooser = new FileChooser();

        File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {

            imagenameLabel.setText(selectedFile.getName());
            File file = new File("src/tunisiamall/GUI/images/boutique/" + selectedFile.getName());
            File source = new File(selectedFile.getAbsolutePath());
            try {
                org.apache.commons.io.FileUtils.copyFile(source, file);
               Image image2 = new Image(source.toURI().toString());
               ajoutimageView.setImage(image2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Open Resource File");
//
//        File file = fileChooser.showOpenDialog(stage);
//
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
//                new FileChooser.ExtensionFilter("PNG", "*.png")
//        );
//        imagenameLabel.setText(file.getName());
//        //  return file.getName();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
