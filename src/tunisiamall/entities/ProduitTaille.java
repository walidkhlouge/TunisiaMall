/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entities;


public class ProduitTaille {
    int idProduit ;
    String taille;
    int quantite;

    public ProduitTaille(int idProduit, String taille, int quantite) {
        this.idProduit = idProduit;
        this.taille = taille;
        this.quantite = quantite;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public String getTaille() {
        return taille;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "ProduitTaille{" + "idProduit=" + idProduit + ", taille=" + taille + ", quantite=" + quantite + '}';
    }
}
