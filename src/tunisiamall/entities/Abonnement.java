/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entities;

import java.util.Date;


public class Abonnement {
    private String login;
    private String nomEnseigne;
    private Date dateAbonnement;

    public Abonnement(String login, String nomEnseigne, Date dateAbonnement) {
        this.login = login;
        this.nomEnseigne = nomEnseigne;
        this.dateAbonnement = dateAbonnement;
    }

    public Abonnement() {
        
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNomEnseigne() {
        return nomEnseigne;
    }

    public void setNomEnseigne(String nomEnseigne) {
        this.nomEnseigne = nomEnseigne;
    }

    public Date getDateAbonnement() {
       return new Date();
    }

    public void setDateAbonnement(Date dateAbonnement) {
        this.dateAbonnement = dateAbonnement;
    }
    
    
    
}
