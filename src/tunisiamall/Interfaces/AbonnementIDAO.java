/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.Interfaces;


public interface AbonnementIDAO {
    void addAbonnement(String login, String nomEnseigne);
    void suppAbonnementByPK(String login, String nomEnseigne);
    
    
}
