/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Catalogue {
    private int id;
    private String type;
    private String intitule;
   ArrayList<Produit> Produits;
    public Catalogue() {
    }
    
    public Catalogue(int id, String type, String intitule) {
        this.id = id;
        this.type = type;
        this.intitule=intitule;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Catalogue{" + "id=" + id + ", type=" + type + '}';
    }
    
    
}
