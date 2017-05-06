/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.Interfaces;

import java.util.List;
import tunisiamall.entities.CarteFidelite;
import tunisiamall.entities.Compte;
import tunisiamall.entities.Enseigne;


public interface CarteFideliteIDAO {
    void add(CarteFidelite c,Enseigne e, Compte cl);

    void update(CarteFidelite c,int p);

    void removeById(int id);

    List<CarteFidelite> findAll();

    CarteFidelite findById(int id);
    
}
