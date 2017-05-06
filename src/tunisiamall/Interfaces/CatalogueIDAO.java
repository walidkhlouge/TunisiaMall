/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.Interfaces;

import java.util.List;
import tunisiamall.entities.Catalogue;


public interface CatalogueIDAO {
    void add(Catalogue c);

    void update(Catalogue c,String type);

    void removeById(int id);

    List<Catalogue> findAll();

    Catalogue findById(int id);
    
    List<Catalogue> findbyType(String type);
    
}
