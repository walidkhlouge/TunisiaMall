/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tunisiamall.DAO.BoutiqueDAO;
import tunisiamall.DAO.CompteDAO;
import tunisiamall.DAO.EnseigneDAO;
import tunisiamall.entities.Compte;
import tunisiamall.entities.Boutique;
import tunisiamall.entities.Enseigne;
import tunisiamall.main.MainRespE;


public class FXMLGestionCompteController {

    @FXML
    TableView<Compte> tableviewCompte;
    @FXML
    TableColumn<Compte, String> nomColumn;
    @FXML
    TableColumn<Compte, String> prenomColumn;
    @FXML
    TableColumn<Compte, String> mailColumn;
    @FXML
    TableColumn<Compte, String> loginColumn;
    @FXML
    TableColumn<Boutique, String> boutiqueColumn;
    @FXML
    TableColumn<Compte, String> enseigneColumn;
    @FXML
    ComboBox boutiqueCB;
    @FXML
    Label nomLabel, prenomLabel, emailLabel, loginLabel;

    private MainRespE main;
    Compte c;
    EnseigneDAO en = new EnseigneDAO();
    BoutiqueDAO b = new BoutiqueDAO();
    EnseigneDAO e = new EnseigneDAO();
    List<Boutique> l = new ArrayList<>();
    List<Enseigne> le = new ArrayList<>();
    List<Boutique> lb = new ArrayList<>();
    CompteDAO cb = new CompteDAO();
    List<Compte> lc = new ArrayList<>();
    private Stage stage;

    public void initialize() {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        le = e.findAllbyResponsableEns(MainRespE.loginR);

        for (Enseigne ne : le) {
            l = b.findBoutiqueByEnseigne(ne.getNom());
            for (Boutique bn : l) {
                boutiqueCB.getItems().add(bn.getIntitule());
            }
        }
        tableviewCompte.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));

    }

    public void showDetails(Compte c) {
        nomLabel.setText(c.getNom());
        prenomLabel.setText(c.getPrenom());
        emailLabel.setText(c.getEmail());
        loginLabel.setText(c.getLogin());
        boutiqueCB.setValue(b.findBoutiqueByLoginR(c.getLogin()).get(0).getIntitule());
    }

    @FXML
    public void HandleDelete() {
        CompteDAO c = new CompteDAO();
        String login = tableviewCompte.getSelectionModel().getSelectedItem().getLogin();
        int index = tableviewCompte.getSelectionModel().getSelectedIndex();
        main.compteData.remove(index);
        c.removeCompteByLogin(login);
    }

    public void HandleValider() {
        Boutique bo = new Boutique();
        BoutiqueDAO ba = new BoutiqueDAO();
        String s = (String) boutiqueCB.getValue();
        ba.updateBoutiqueResp(ba.findBoutiqueByNom2(s), tableviewCompte.getSelectionModel().getSelectedItem().getLogin());

    }

    public void setMain(MainRespE main, Stage stage) {
        this.main = main;
        this.stage = stage;
        tableviewCompte.setItems(main.getcompteData());
        le = en.findAllbyResponsableEns(MainRespE.loginR);
        for (Enseigne ne : le) {
            lb = b.findBoutiqueByEnseigne(ne.getNom());
            for (Boutique bn : lb) {
                lc = cb.findAllbyResponsableboutique(bn.getLoginResponsableBoutique());
                for (Compte d : lc) {
                    main.compteData.add(d);
                }
            }
        }
    }

}
