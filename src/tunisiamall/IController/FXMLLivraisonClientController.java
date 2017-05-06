/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tunisiamall.DAO.CommandeDAO;
import tunisiamall.DAO.ProduitDAO;
import tunisiamall.entities.Commande;
import tunisiamall.entities.Compte;
import tunisiamall.entities.Produit;


public class FXMLLivraisonClientController implements Initializable {
@FXML 
TableView<dataLivraison> table = new TableView<dataLivraison>();
ObservableList<dataLivraison> data = FXCollections.observableArrayList();
@FXML
TableColumn p;
@FXML
TableColumn l;
@FXML
TableColumn c;
@FXML
TableColumn cp;
CommandeDAO cDao = new CommandeDAO();
ArrayList<Commande> commandes = new ArrayList();
Produit produit;
ProduitDAO pDao = new ProduitDAO();
int prix=0;
int nb=1;
dataLivraison dL = new dataLivraison();
    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        commandes=cDao.findAllByDateTime(Compte.connected.getLogin());
        for(int i=0; i<commandes.size();i++){
            produit= pDao.findProduitById(commandes.get(i).getIdProduit());
           if(i==0)
           {
               dL.setAdresseLivraison(commandes.get(0).getAdresseLivraison());
               dL.setCommande(String.valueOf(nb));
               nb++;
               dL.setDateTime(commandes.get(0).getTimeLivraison().toString());
               prix+=produit.getPrixUnitaire()*commandes.get(0).getQuantite();
           }
            else if(commandes.get(i).getIdCommande()==1)
           {
               dL.setPrix(String.valueOf(prix));
               data.add(dL);
               prix=0;
               dL= new dataLivraison();
               dL.setAdresseLivraison(commandes.get(i).getAdresseLivraison());
               dL.setCommande(String.valueOf(nb));
               nb++;
               dL.setDateTime(commandes.get(i).getTimeLivraison().toString());
               prix+=produit.getPrixUnitaire()*commandes.get(i).getQuantite();
           }
            else
           {
               prix+=produit.getPrixUnitaire()*commandes.get(i).getQuantite();
           }
           if(i==commandes.size()-1)
           {
               dL.setPrix(String.valueOf(prix));
               data.add(dL);
           }
          
               }
           
            
                
         c.setCellValueFactory(
                new PropertyValueFactory<dataLivraison, String>("Commande"));
         cp.setCellValueFactory(
                new PropertyValueFactory<dataLivraison, String>("dateTime"));
         p.setCellValueFactory(
                new PropertyValueFactory<dataLivraison, String>("Prix"));
         l.setCellValueFactory(
                new PropertyValueFactory<dataLivraison, String>("adresseLivraison"));
 
table.setItems(data);
    }
public static class dataLivraison
{
    private  String Commande;
    private String prix;
    private String adresseLivraison;
    private  String dateTime;

        public dataLivraison() {
        }

        public dataLivraison(String Commande, String prix, String adresseLivraison, String dateTime) {
            this.Commande = Commande;
            this.prix = prix;
            this.adresseLivraison = adresseLivraison;
            this.dateTime = dateTime;
        }

        public void setCommande(String Commande) {
            this.Commande = Commande;
        }

        public void setPrix(String prix) {
            this.prix = prix;
        }

        public void setAdresseLivraison(String adresseLivraison) {
            this.adresseLivraison = adresseLivraison;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }
        
        public String getCommande() {
            return Commande;
        }

        public String getPrix() {
            return prix;
        }

        public String getAdresseLivraison() {
            return adresseLivraison;
        }

        public String getDateTime() {
            return dateTime;
        }
    
    
    
    
    
}
    
}
