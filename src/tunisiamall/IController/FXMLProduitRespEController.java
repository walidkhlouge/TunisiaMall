/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import com.lowagie.text.DocumentException;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tunisiamall.DAO.EnseigneDAO;
import tunisiamall.DAO.ProduitDAO;
import tunisiamall.entities.Boutique;
import tunisiamall.entities.Enseigne;
import tunisiamall.entities.Produit;
import tunisiamall.main.MainRespE;


public class FXMLProduitRespEController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Produit> tableView;
//    @FXML
//    private TableColumn<Produit, Integer> id;
    @FXML
    private TableColumn<Produit, String> nomP;

    @FXML
    private Label produitlabel;
    @FXML
    private Label enseignelabel;
    @FXML
    private TextArea infolabel;
    @FXML
    private ImageView imageView;
    @FXML
      private Label tauxlabel;
    @FXML
     private Label fidelitelabel; 
    
    
    @FXML
    Button ajouter;
    @FXML
    Button supprimer;
    @FXML
    Button modifier;
    @FXML
    Button pdf;

    ProduitDAO pDao = new ProduitDAO();
//    ObservableList<Produit> dataP;
  
    MainRespE main;
    Stage stage;

    public FXMLProduitRespEController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomP.setCellValueFactory(new PropertyValueFactory<>("nom"));

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue)
        );
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

    public void ajouter() {

        main.changeWindowAjP();

    }

    public void modifier() {
        Produit p = tableView.getSelectionModel().getSelectedItem();
        Boolean okClicked = main.changeWindowModif(p);
        if (okClicked) {

            refreshTableView();
        }
    }

    public void pdf() throws DocumentException {

        pDao.toPdf();
    }

    public void supprimer() {
        File i = new File("src/tunisiamall/GUI/images/produit/" + tableView.getSelectionModel().getSelectedItem().getUrl());
        String nom = tableView.getSelectionModel().getSelectedItem().getNonEnseigne();
        int index = tableView.getSelectionModel().getSelectedIndex();
        pDao.removeProduitByNom(nom);
        main.dataP.remove(index);
        org.apache.commons.io.FileUtils.deleteQuietly(i);
    }

    public void showDetails(Produit p) {
        File file = new File("src/tunisiamall/GUI/images/produit/" + p.getUrl());
        Image image = new Image(file.toURI().toString());
        produitlabel.setText(p.getNom());
        enseignelabel.setText(p.getNonEnseigne());
        infolabel.setText(p.getInfo());
        String f=Integer.toString(p.getPtsfidelite());
        fidelitelabel.setText(f);
       
//        imageView.setImage(new Image(getClass().getResource("../GUI/images/produit/" + p.getUrl()).toExternalForm()));
        imageView.setImage(image);
    }

    public void setMain(MainRespE main, Stage stage) {
        this.main = main;
        this.stage = stage;
        main.setDataP();
        tableView.setItems(main.getDataP());
    }

    public void refreshTableView() {

//        tableView.setItems(null);
//        tableView.layout();
//        main.setDataP();
//        tableView.setItems(main.getDataP());
        tableView.setItems(null);
        tableView.layout();
        tableView.setItems(main.getDataP());

    }

}
