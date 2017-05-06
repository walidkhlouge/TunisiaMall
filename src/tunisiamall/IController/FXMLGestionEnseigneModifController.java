/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tunisiamall.DAO.EnseigneDAO;
import tunisiamall.entities.Enseigne;
import tunisiamall.main.MainRespE;


public class FXMLGestionEnseigneModifController {

    @FXML
    ImageView modifimageView;
    @FXML
    Label imageNameLabel;
    @FXML
    TextField nomField, adresseField;

    private Stage stage;
    private MainRespE main;
    private Enseigne e;
    private boolean OkClicked = false;

    public void setMain(MainRespE main, Stage stage, Enseigne e) {
        this.main = main;
        this.stage = stage;
        this.e = e;
        fillEnseigneDetails();
        imageNameLabel.setText(e.getUrl());
        File file = new File("src/tunisiamall/GUI/images/enseigne/" + e.getUrl());
        Image image = new Image(file.toURI().toString());
        modifimageView.setImage(image);
    }

    public boolean isOkClicked() {
        return OkClicked;
    }

    public void fillEnseigneDetails() {
        File file = new File("src/tunisiamall/GUI/images/enseigne/" + e.getUrl());
        Image image = new Image(file.toURI().toString());
        nomField.setText(e.getNom());
        adresseField.setText(e.getAdresse());
        modifimageView.setImage(image);
    }

    @FXML
    public void HandleParcourirButton() {
        FileChooser fileChooser = new FileChooser();

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            imageNameLabel.setText(selectedFile.getName());
            File file = new File("src/tunisiamall/GUI/images/enseigne/" + selectedFile.getName());
            File source = new File(selectedFile.getAbsolutePath());
            Image image = new Image(file.toURI().toString());
            modifimageView.setImage(image);
                        try {
                org.apache.commons.io.FileUtils.copyFile(source, file);
                Image image2 = new Image(source.toURI().toString());
                modifimageView.setImage(image2);
            } catch (IOException ef) {
                ef.printStackTrace();
            }

        }

    }

    @FXML
    public void HandleModifierButton() {
        EnseigneDAO daoE = new EnseigneDAO();
        e.setNom(nomField.getText());
        e.setAdresse(adresseField.getText());
        e.setUrl(imageNameLabel.getText());
        daoE.updateEnseigne2(e, e.getNom(), e.getAdresse(), e.getUrl());
        OkClicked = true;
        stage.close();
    }

    @FXML
    public void HandleAnnulerButton() {
        stage.close();
    }

}
