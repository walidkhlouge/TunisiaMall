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


public class FXMLGestionEnseigneAjoutController {

    @FXML
    TextField nomField, adresseField;
    @FXML
    Label imageNameLabel;
    @FXML
    ImageView ajoutimageView;

    private Stage stage;
    private MainRespE main;

    public void setMain(MainRespE main, Stage stage) {
        this.main = main;
        this.stage = stage;
    }

    @FXML
    public void HandleParcourirButton() {
        FileChooser fileChooser = new FileChooser();

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            imageNameLabel.setText(selectedFile.getName());
            File file = new File("src/tunisiamall/GUI/images/enseigne/" + selectedFile.getName());
            File source = new File(selectedFile.getAbsolutePath());
            try {
                org.apache.commons.io.FileUtils.copyFile(source, file);
                Image image2 = new Image(source.toURI().toString());
                ajoutimageView.setImage(image2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    public void HandleAjoutButton() {
        EnseigneDAO daoE = new EnseigneDAO();
        Enseigne e = new Enseigne(nomField.getText(), adresseField.getText(), imageNameLabel.getText(), MainRespE.loginR);
        daoE.addEnseigne2(e);
        main.getenseigneData().add(e);
        stage.close();
    }

    @FXML
    public void HandleAnnulerButton() {
        stage.close();
    }

}
