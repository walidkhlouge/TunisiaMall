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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tunisiamall.DAO.BoutiqueDAO;
import tunisiamall.entities.Boutique;
import tunisiamall.main.MainRespE;


public class FXMLModifBoutiqueRespEController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private MainRespE main;
    Stage stage;
    @FXML
    TextField intitule;

    @FXML
    TextField nomE;

    @FXML
    ImageView updateimageView;

    @FXML
    Button parcourir;

    @FXML
    Button update;
    @FXML
    Button cancel;
    @FXML
    Label imageLabel;

    BoutiqueDAO bDao = new BoutiqueDAO();
    Boutique b;
//    private FXMLBoutiqueRespEController mainB;
    boolean okClicked = false;

    public void setMain(MainRespE main, Boutique b, Stage stage) {
        this.b = b;
        this.main = main;
        this.stage = stage;
        fillBoutiqueDetails();
        imageLabel.setText(b.getUrl());
        File file = new File("src/tunisiamall/GUI/images/boutique/" + b.getUrl());
        Image image = new Image(file.toURI().toString());
        updateimageView.setImage(image);
    }

    public void update() {

        /* int i= mainB.edit();
         String s = parcourir();
         Boutique b = new Boutique(intitule.getText(),i,nomE.getText(),respB.getText(),s);
         bDao.updateBoutique2(i, intitule.getText(), nomE.getText(),respB.getText(),s);
         */
        b.setNomEnseigne(nomE.getText());
        b.setIntitule(intitule.getText());
        b.setUrl(imageLabel.getText());
        System.out.println(b.getId());
        bDao.updateBoutique(b, b.getIntitule(), b.getNomEnseigne(), b.getUrl());
        okClicked = true;

        stage.close();

    }

    public boolean isOKClicked() {

        return okClicked;
    }

    public void handleCancel() {

        stage.close();
    }

    public void fillBoutiqueDetails() {
        File file = new File("src/tunisiamall/GUI/images/boutique/" + b.getUrl());
        Image image = new Image(file.toURI().toString());

        nomE.setText(b.getNomEnseigne());
        intitule.setText(b.getIntitule());

        //imageLabel.setText(b.getUrl());
        updateimageView.setImage(image);
    }

    public void parcourir() {

        FileChooser fileChooser = new FileChooser();

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {

            imageLabel.setText(selectedFile.getName());
            File file = new File("src/tunisiamall/GUI/images/boutique/" + selectedFile.getName());
            File source = new File(selectedFile.getAbsolutePath());
            Image image = new Image(file.toURI().toString());
            updateimageView.setImage(image);
            try {
                org.apache.commons.io.FileUtils.copyFile(source, file);
                Image image2 = new Image(source.toURI().toString());
                updateimageView.setImage(image2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
