/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entities;

import java.util.Date;


public class Commande {
    private int idCommande;
    private String login;
    private int idProduit;
    private String adresseLivraison;
    private int quantite;
    private String taille;
   private Date timeLivraison;

    public Commande() {
    }

    public Commande(int idCommande, String login, int idProduit, String adresseLivraison, int quantite, String taille, Date timeLivraison) {
        this.idCommande = idCommande;
        this.login = login;
        this.idProduit = idProduit;
        this.adresseLivraison = adresseLivraison;
        this.quantite = quantite;
        this.taille = taille;
        this.timeLivraison = timeLivraison;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public Date getTimeLivraison() {
        return timeLivraison;
    }

    public void setTimeLivraison(Date timeLivraison) {
        this.timeLivraison = timeLivraison;
    }
   

}