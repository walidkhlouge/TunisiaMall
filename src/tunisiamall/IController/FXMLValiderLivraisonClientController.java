/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import tunisiamall.DAO.CommandeDAO;
import tunisiamall.DAO.PanierDAO;
import tunisiamall.entities.Commande;
import tunisiamall.entities.Compte;
import tunisiamall.entities.Panier;


public class FXMLValiderLivraisonClientController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML 
    JFXButton valider;
    @FXML
    JFXButton pasEncore;
    @FXML
    JFXTextField adresseLivraison;
    @FXML
    JFXTextField nc;
    @FXML
    JFXTextField cs;
    @FXML
    Text prix ;
    @FXML
    Text alert;
    CommandeDAO cDAO = new CommandeDAO();
    PanierDAO pDAO = new PanierDAO();
    ArrayList<Panier> paniers = new ArrayList();
    Date d = new Date();
    @FXML
    private void acheter(ActionEvent event)
    {
     if(!adresseLivraison.getText().isEmpty() && !nc.getText().isEmpty() && !cs.getText().isEmpty())
     {
         paniers= pDAO.findByLogin(Compte.connected.getLogin());
         for(int i = 0 ; i< paniers.size();i++){
 Commande c = new Commande(i, Compte.connected.getLogin(), paniers.get(i).getIdProduit(), adresseLivraison.getText(), paniers.get(i).getNombre(),paniers.get(i).getTaille() , d);
      cDAO.add(c);
         }
         pDAO.removeByLoginProduit(Compte.connected.getLogin());
     }
     else
     {
         alert.setText("Veuillez remplir tous les champs");
     }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
