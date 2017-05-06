/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.Interfaces;

import java.util.List;
import tunisiamall.entities.Pages;


public interface PageIDAO {
    
    public void add(Pages p);
    
    public void update(Pages p,int nbPages);
    
    public List FindAll();
    
    public Pages FindByIdCatalogue(int idCatalogue);
    
    public void removeByIdCatalogue(int idCatalogue);
    
}
