/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.Interfaces;

import java.util.ArrayList;
import java.util.List;
import tunisiamall.entities.Boutique;
import tunisiamall.entities.Enseigne;


public interface BoutiqueIDAO {
    void addBoutique(Boutique b,Enseigne e);

    void updateBoutique(Boutique b,String intitule,String nom);

    void removeBoutiqueById(int id);

    List<Boutique> findAll();
    Boutique findProduitById(int id);
    ArrayList<Boutique> searchBoutiqueByIntitule(String nom);
    
}
