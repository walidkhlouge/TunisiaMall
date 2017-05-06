/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entities;

import java.util.Date;


public class Compte {

    
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String login;
    private String type;
    private String sexe;
    private Date dateNaissance;
    private String etat;
    private String photo;
    public static Compte connected;
   
    public Compte() {
    }

    public Compte(String nom, String prenom, String email, String password, String login,String type,String sexe,Date dateNaissance,String etat,String photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.login = login;
        this.type=type;
        this.sexe=sexe;
        this.dateNaissance=dateNaissance;
        this.etat=etat;
        this.photo=photo;
        
    }

    public Compte(String nom, String prenom, String email, String password, String login,String type,String sexe,Date dateNaissance,String etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.login = login;
        this.type=type;
        this.sexe=sexe;
        this.dateNaissance=dateNaissance;
        this.etat=etat;
        
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public String getType() {
        return type;
    }

   
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

  
    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

  

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
    public static String calculateCode(String login)
    {
         String codeVal= new String();
        int i=0;
        double codeV;
         while(codeVal.length()<5)
         {
         codeVal+=String.valueOf((int)login.charAt(i));
             i++;
        }
        System.out.println(codeVal);
        
        codeV=Double.parseDouble(codeVal);
        codeV= (codeV/2.71828) *3.14;
        String codeVS = String.valueOf(Double.toHexString(codeV));
        codeVal=(String)codeVS.subSequence(4, 7);
        codeVal+=codeVS.subSequence(codeVS.length()-3, codeVS.length()-1);
        return codeVal;
    }

    @Override
    public String toString() {
        return "Compte{" + "nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", login=" + login + ", type=" + type + ", sexe=" + sexe + ", dateNaissance=" + dateNaissance + ", etat=" + etat + ", photo=" + photo + '}';
    }

    public void setType(String type) {
        this.type = type;
    }
   

}

