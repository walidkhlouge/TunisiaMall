/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.Interfaces;

import java.util.ArrayList;
import java.util.List;
import tunisiamall.entities.Panier;


public interface PanierIDAO {
    void add(Panier c);

    void update(Panier c,int nombre,int idProduit);

    void removeByLoginProduit(String login,int idProduit);

    List<Panier> findAll();

    public ArrayList<Panier> findByLogin(String login);
    int findQuantiteProdByTaille(int idproduit,String taille);
     public void removeByLoginTailleProduit(String login, int idProduit,String taille);
     public void removeByLoginProduit(String login);
}
