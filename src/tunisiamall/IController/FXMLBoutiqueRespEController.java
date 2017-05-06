/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.io.File;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import tunisiamall.DAO.BoutiqueDAO;
import tunisiamall.entities.Boutique;
import tunisiamall.main.MainRespE;


public class FXMLBoutiqueRespEController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Boutique> tableView;
//    @FXML
//    private TableColumn<Boutique, Integer> id;
    @FXML
    private TableColumn<Boutique, String> intitule;

    @FXML
    private Label intitulelabel;
    @FXML
    private Label enseignelabel;
    @FXML
    private Label resplabel;
    @FXML
    private ImageView imageView;
    @FXML
    Button ajouter;
    @FXML
    Button supprimer;
    @FXML
    Button modifier;

    BoutiqueDAO bDao = new BoutiqueDAO();
//    ObservableList<Boutique> data;
    private MainRespE main;
    private Stage stage;

    public FXMLBoutiqueRespEController() {

    }
    DropShadow shadow = new DropShadow();

    @FXML
    public void handleButton1A() {
        shadow.setColor(Color.GREEN);
        ajouter.setEffect(shadow);
    }

    @FXML
    public void handleButton2A() {
        ajouter.setEffect(null);
    }

    @FXML
    public void handleButton1M() {
        shadow.setColor(Color.BLUE);
        modifier.setEffect(shadow);
    }

    @FXML
    public void handleButton2M() {
        modifier.setEffect(null);
    }

    @FXML
    public void handleButton1S() {
        shadow.setColor(Color.RED);
        supprimer.setEffect(shadow);
    }

    @FXML
    public void handleButton2S() {
        supprimer.setEffect(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        intitule.setCellValueFactory(new PropertyValueFactory<>("intitule"));

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue)
        );
    }

    public void modifier() {
        Boutique b = tableView.getSelectionModel().getSelectedItem();

        Boolean okClicked = main.changeWindowModif(b);
        if (okClicked) {

            refreshTableView();
        }
    }

    public void supprimer() {

        File i = new File("src/tunisiamall/GUI/images/boutique/" + tableView.getSelectionModel().getSelectedItem().getUrl());
        int id = tableView.getSelectionModel().getSelectedItem().getId();
        int index = tableView.getSelectionModel().getSelectedIndex();
        main.data.remove(index);
        bDao.removeBoutiqueById(id);
        org.apache.commons.io.FileUtils.deleteQuietly(i);

    }

    public void showDetails(Boutique b) {

        File file = new File("src/tunisiamall/GUI/images/boutique/" + b.getUrl());
        Image image = new Image(file.toURI().toString());
        intitulelabel.setText(b.getIntitule());
        enseignelabel.setText(b.getNomEnseigne());
        resplabel.setText(b.getLoginResponsableBoutique());
        imageView.setImage(image);
//            imageView.setImage(new Image(getClass().getResource("../GUI/images/boutique/" + b.getUrl()).toExternalForm()));
    }

    public void ajouter() {

        main.changeWindowAj();
    }

    public void setMain(MainRespE main, Stage stage) {
        this.main = main;
        this.stage = stage;
        main.setData();
        tableView.setItems(main.getData());
    }

    public void refreshTableView() {

        tableView.setItems(null);
        tableView.layout();
//         main.setData();
        tableView.setItems(main.getData());

    }

}
