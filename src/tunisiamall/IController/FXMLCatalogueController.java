/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tunisiamall.DAO.CatProdDAO;
import tunisiamall.DAO.CatalogueDAO;
import tunisiamall.entities.CatProd;
import tunisiamall.entities.Produit;
import tunisiamall.main.MainRespE;


public class FXMLCatalogueController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label produitlabel;
    @FXML
    JFXButton ajout;
    @FXML
    private ImageView imageView;
    @FXML
    private TableView<Produit> tableView;
    @FXML
    private TableColumn<Produit, String> nomP;
    MainRespE main;
    Stage stage;

    public FXMLCatalogueController() {
    }

    public void initialize(URL url, ResourceBundle rb) {

        nomP.setCellValueFactory(new PropertyValueFactory<>("nonEnseigne"));

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));
    }

    public void showDetails(Produit p) {

        File file = new File("src/tunisiamall/GUI/images/produit/" + p.getUrl());
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        produitlabel.setText(p.getNonEnseigne());
    }

    public void setMain(MainRespE main, Stage stage) {
        this.main = main;
        this.stage = stage;
        main.setDataP();
        tableView.setItems(main.getDataP());
    }

    public void supprimer() {

        int index = tableView.getSelectionModel().getSelectedIndex();

        main.dataP.remove(index);
    }

    public void ajouter() {

        Produit p = tableView.getSelectionModel().getSelectedItem();
        CatalogueDAO cat = new CatalogueDAO();

        int i = cat.findByE(p.getNom());
        CatProdDAO cp = new CatProdDAO();
        CatProd catP = new CatProd();
        catP.setIdC(1);
        // catP.setIdC(i); fazet can't update or add foreign key nsit kifech nsalahha :p 
        catP.setIdP(p.getId());
        cp.add(catP);
        supprimer();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confimation");
        alert.setHeaderText("Produit ajouté au catalogue");
        alert.showAndWait();
        stage.close();
    }
}
