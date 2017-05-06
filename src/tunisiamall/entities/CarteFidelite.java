/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entities;


public class CarteFidelite {
    private int id;
    private int points;
    private String login;
    private float reductionFixe;
    private String nomEnseigne;
    private int pointFideleFixe;
    
    public CarteFidelite(int points) {
        this.points = points;
    }

    public String getNomEnseigne() {
        return nomEnseigne;
    }

    public void setNomEnseigne(String nomEnseigne) {
        this.nomEnseigne = nomEnseigne;
    }

    public CarteFidelite(int id, int points, String login, float reductionFixe, String nomEnseigne, int pointFideleFixe) {
        this.id = id;
        this.points = points;
        this.login = login;
        this.reductionFixe = reductionFixe;
        this.nomEnseigne = nomEnseigne;
        this.pointFideleFixe = pointFideleFixe;
    }

    public int getPointFideleFixe() {
        return pointFideleFixe;
    }

    public void setPointFideleFixe(int pointFideleFixe) {
        this.pointFideleFixe = pointFideleFixe;
    }

    public CarteFidelite() {
    }

    public CarteFidelite(int id, int points) {
        this.id = id;
        this.points = points;
    }

    public CarteFidelite(int id, int points, String login) {
        this.id = id;
        this.points = points;
        this.login = login;
       
    }

    public CarteFidelite(int id, int points, String login, float reductionFixe) {
        this.id = id;
        this.points = points;
        this.login = login;
        this.reductionFixe = reductionFixe;
    }

    public float getReductionFixe() {
        return reductionFixe;
    }

    public void setReductionFixe(float reductionFixe) {
        this.reductionFixe = reductionFixe;
    }

    public CarteFidelite(int id, int points, String login, float reductionFixe, String nomEnseigne) {
        this.id = id;
        this.points = points;
        this.login = login;
        this.reductionFixe = reductionFixe;
        this.nomEnseigne = nomEnseigne;
    }
     

    public String getLogin() {
        return login;
    }

    public float getReduction() {
        return reductionFixe;
    }

    public void setReduction(float reductionFixe) {
        this.reductionFixe = reductionFixe;
    }
    

    public void setLogin(String login) {
        this.login = login;
    }

   

   
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "CarteFidelite{" + "id=" + id + ", points=" + points + '}';
    }

    
    
    
}
