/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.Interfaces;

import java.util.List;
import tunisiamall.entities.PackPublicitaire;


public interface PackPublicitaireIDAO {
    void add(PackPublicitaire p);

    void update(PackPublicitaire p,float prix);

    void removeById(int id);

    List<PackPublicitaire> findAll();

    PackPublicitaire findById(int id);
    
    
}
