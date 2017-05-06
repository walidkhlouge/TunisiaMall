/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entities;

import java.util.List;
import java.util.Vector;


public class Enseigne {
    private String nom;
    private String adresse;
    private String loginResponsableEnseigne;
    private String url;
    List<Produit> Produits;
    List<Boutique> Boutiques;
    List<Catalogue> Catalogues;

    public Enseigne() {
    }

    public Enseigne(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
        Produits=new Vector<Produit>();//set
        Boutiques=new Vector<Boutique>();//set
        Catalogues=new Vector<Catalogue>();//???
    }

    public Enseigne(String nom, String adresse, String loginResponsableEnseigne) {
        this.nom = nom;
        this.adresse = adresse;
        this.loginResponsableEnseigne = loginResponsableEnseigne;
        
    }

    public Enseigne(String nom, String adresse, String url, String loginR) {
this.nom=nom;
this.adresse=adresse;
this.url=url;
this.loginResponsableEnseigne=loginR;
    }

    public String getLoginResponsableEnseigne() {
        return loginResponsableEnseigne;
    }

    public void setLoginResponsableEnseigne(String loginResponsableEnseigne) {
        this.loginResponsableEnseigne = loginResponsableEnseigne;
    }
    
    
    
    public String getNom() {
        return nom;
    }

  
    

    public String getAdresse() {
        return adresse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Enseigne{" + "nom=" + nom + ", adresse=" + adresse + '}';
    }
    
}
