/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entities;


public class Produit {
   private int id;
   private String nom;
   private int quantite;
   private float prixUnitaire;
   private int idBoutique;
   private float tauxReduction;
   private String nonEnseigne;
   private String reference;
   private String info;
   private String url;
   private int ptsfidelite;
    public Produit() {
    }

    public Produit(int id, String nom, String nonEnseigne, String info, String url, int ptsfidelite) {
        this.id=id;
        this.nom=nom;
        this.nonEnseigne=nonEnseigne;
        this.info=info;
        this.url=url;
        this.ptsfidelite=ptsfidelite;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPtsfidelite() {
        return ptsfidelite;
    }

    public void setPtsfidelite(int ptsfidelite) {
        this.ptsfidelite = ptsfidelite;
    }

    
    public String getUrl() {
        return url;
    }

    public int getIdBoutique() {
        return idBoutique;
    }

    public void setIdBoutique(int idBoutique) {
        this.idBoutique = idBoutique;
    }

    
    public String getNonEnseigne() {
        return nonEnseigne;
    }

    public void setNonEnseigne(String nonEnseigne) {
        this.nonEnseigne = nonEnseigne;
    }
    

    

    public Produit(int id, String nom, int quantite, float prixUnitaire, float tauxReduction, String nonEnseigne, String reference, String info) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        
        this.tauxReduction = tauxReduction;
        this.nonEnseigne = nonEnseigne;
        this.reference = reference;
        this.info = info;
    }

    public String getReference() {
        return reference;
    }

    public String getInfo() {
        return info;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

   

    public void setTauxReduction(float tauxReduction) {
        this.tauxReduction = tauxReduction;
    }

    public int getQuantite() {
        return quantite;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

   

    public float getTauxReduction() {
        return tauxReduction;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", quantite=" + quantite + ", prixUnitaire=" + prixUnitaire +  ", tauxReduction=" + tauxReduction + ", nonEnseigne=" + nonEnseigne + ", reference=" + reference + ", info=" + info + '}';
    }
    
    
}
