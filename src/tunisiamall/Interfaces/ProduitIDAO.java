/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.Interfaces;

import java.util.ArrayList;
import java.util.List;
import tunisiamall.entities.Produit;


public interface ProduitIDAO {
    void addProduit(Produit p);

    void updateProduit(Produit p,String nom,int quantite,float prixUnitaire,float prixGros,float tauxReduction);

    void removeProduitById(int idProduit);
    public void removeProduitByNom(String nom);
    List<Produit> findAll();
        public Produit findProduitByNom(String nom);
    ArrayList<Produit> searchProduitByNom(String nom);
     public ArrayList<Produit> findProduitsByCatalogue(int idCatalogue);
}
