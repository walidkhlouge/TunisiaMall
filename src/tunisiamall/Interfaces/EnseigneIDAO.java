/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.Interfaces;

import java.util.List;
import tunisiamall.entities.Enseigne;

/**
 *
 * @author 
 */
public interface EnseigneIDAO {
     void addEnseigne(Enseigne e);

    void updateEnseigne(Enseigne e,String nom,String adresse);

    void removeEnseigneByNom(String nom);

    List<Enseigne> findAll();
    List<Enseigne> searchByNom(String nom) ;

    Enseigne findEnseigneByNom(String nom);
    
}
