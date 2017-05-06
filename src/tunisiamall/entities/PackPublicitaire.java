/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entities;

import java.util.HashSet;
import java.util.Set;


public class PackPublicitaire {
    private int id;
    private float prix;
    private String nom;
    private String desc;
    Set<EmplacementPublicite> Emplacements;

    public PackPublicitaire() {
    }

    public PackPublicitaire(float prix) {
        this.prix = prix;
        Emplacements=new HashSet<EmplacementPublicite>();
    }

    public PackPublicitaire(int id, float prix) {
        this.id = id;
        this.prix = prix;
    }
    
      public PackPublicitaire(int id,String nom,float prix,String desc) {
        this.id = id;
        this.prix = prix;
        this.nom=nom;
        this.desc=desc;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
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
    

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "PackPublicitaire{" + "prix=" + prix + '}';
    }
    
}
