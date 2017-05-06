/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entities;


public class CatProd {
    int idP;
    int idC;

    public CatProd() {
    }

    
    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public CatProd(int idP, int idC) {
        this.idP = idP;
        this.idC = idC;
    }
    
    
    
}
