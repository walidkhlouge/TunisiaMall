/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.Interfaces;

import java.util.ArrayList;
import java.util.List;
import tunisiamall.entities.Commande;


public interface CommandeIDAO {
    
    void add(Commande c);

    void update(Commande c,String adresseLivraison);

    void removeById(int id);

    List<Commande> findAll();

    Commande findCommandeById(int id);
     public ArrayList<Commande> findAllByDateTime(String login);
    
}
