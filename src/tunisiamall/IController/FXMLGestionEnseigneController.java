/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tunisiamall.DAO.EnseigneDAO;
import tunisiamall.entities.Enseigne;
import tunisiamall.main.MainRespE;


public class FXMLGestionEnseigneController {

    @FXML
    TableView<Enseigne> tableView;
    @FXML
    TableColumn<Enseigne, String> nomColumn;
    @FXML
    TableColumn<Enseigne, String> adresseColumn;
    @FXML
    Label nomLabel, adresseLabel, responsableLabel;
    @FXML
    ImageView imageView;
    @FXML
    TextField rechercherField;
    @FXML
    Button ajoutButton, modifButton, suppButton;

    private MainRespE main;
    private Stage stage;

    public void initialize() {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));
    }

    public void refreshTableView() {
        tableView.setItems(null);
        tableView.layout();
        tableView.setItems(main.getenseigneData());
    }

    public void setMain(MainRespE main,Stage stage) {
        this.main = main;
        this.stage=stage;
        tableView.setItems(main.getenseigneData());
        responsableLabel.setText(MainRespE.loginR);
    }


    public void showDetails(Enseigne enseigne) {
        File file = new File("src/tunisiamall/GUI/images/enseigne/" + enseigne.getUrl());
        Image image = new Image(file.toURI().toString());
        nomLabel.setText(enseigne.getNom());
        adresseLabel.setText(enseigne.getAdresse());
        imageView.setImage(image);

    }

    @FXML
    public void HandleNew() {
        main.ajouterEnseigneWindow();
    }


    @FXML
    public void HandleEdit() {
        Enseigne e = tableView.getSelectionModel().getSelectedItem();
        boolean OkClicked = main.modifierEnseigneWindow(e);
        if (OkClicked) {
            refreshTableView();
        }
    }

    @FXML
    public void HandleDelete() {
        EnseigneDAO e = new EnseigneDAO();
        File i = new File("src/tunisiamall/GUI/images/enseigne/" + tableView.getSelectionModel().getSelectedItem().getUrl());
        String nom = tableView.getSelectionModel().getSelectedItem().getNom();
        int index = tableView.getSelectionModel().getSelectedIndex();
        main.enseigneData.remove(index);
        e.removeEnseigneByNom(nom);
        org.apache.commons.io.FileUtils.deleteQuietly(i);
    }

}
