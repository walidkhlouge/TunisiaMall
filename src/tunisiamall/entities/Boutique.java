/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entities;

public class Boutique {
    private String intitule;
    private int id;
    private String nomEnseigne;
    private String loginResponsableBoutique;
    private String url;
    
    public Boutique(String intitule, int id, String nomEnseigne, String loginResponsableBoutique, String url) {
        this.intitule = intitule;
        this.id = id;
        this.nomEnseigne = nomEnseigne;
        this.loginResponsableBoutique = loginResponsableBoutique;
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
   

    

    public Boutique() {
    }

    public void setNomEnseigne(String nomEnseigne) {
        this.nomEnseigne = nomEnseigne;
    }

    public void setLoginResponsableBoutique(String loginResponsableBoutique) {
        this.loginResponsableBoutique = loginResponsableBoutique;
    }
    

    public String getNomEnseigne() {
        return nomEnseigne;
    }

    public String getLoginResponsableBoutique() {
        return loginResponsableBoutique;
    }
    
    
    public String getIntitule() {
        return intitule;
    }

    public int getId() {
        return id;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Boutique{" + "intitule=" + intitule + ", id=" + id + ", nomEnseigne=" + nomEnseigne + ", loginResponsableBoutique=" + loginResponsableBoutique + ", url=" + url + '}';
    }

    
    
}
