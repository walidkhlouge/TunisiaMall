/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.Interfaces;

import java.util.List;
import tunisiamall.entities.EmplacementPublicite;


public interface EmplacementPubliciteIDAO {
    void add(EmplacementPublicite e);

    void update(EmplacementPublicite e,int idPack);

    void removeById(int id);

    List<EmplacementPublicite> findAll();

    EmplacementPublicite findById(int id);
    
}
