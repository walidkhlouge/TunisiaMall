/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entities;

import java.util.ArrayList;
import java.util.List;


public class Panier {
    private String login;
    private int idProduit;
    private int nombre;
    String taille;

    public Panier() {
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

   
    

    public Panier(String login, int idProduit, int nombre,String taille) {
        this.login = login;
        this.idProduit = idProduit;
        this.nombre = nombre;
        this.taille=taille;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public String getLogin() {
        return login;
    }

    public int getNombre() {
        return nombre;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Panier{" + "login=" + login + ", idProduit=" + idProduit + ", nombre=" + nombre + ", taille=" + taille + '}';
    }

  
    
    
}
