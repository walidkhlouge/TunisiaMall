/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.Interfaces;

import java.sql.Date;
import java.util.List;
import tunisiamall.entities.Compte;


public interface CompteIDAO {
    
    void addCompte(Compte c);

    void updateCompte(Compte c,String login,String pwd,String nom,String prenom,String mail);

    void removeCompteByLogin(String login);

    List<Compte> findAll();

    Compte findCompteByLogin(String login);
    boolean seekCompteByLogin(String login);
    boolean seekCompteByEmail(String email);
    boolean seekCompteByAuth(String login,String password);
    public Compte findCompteByAuth(String login, String password);
    void updateComptePwd(String login, String password);
    void updateCompteSexe(String login, String sexe);
    void updateCompteNom(String login, String nom);
    void updateComptePrenom(String login, String prenom);
    void updateCompteDateNaissance(String login, Date dateNaissance);
    void updateCompteEmail(String login, String email);
    void    updateCompte(String login,String pwd,String nom,String prenom,String mail);
    
}
