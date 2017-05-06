/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entities;


public class EmplacementPublicite {
    private int id;
    private int idPack;

    public EmplacementPublicite() {
    }

    public EmplacementPublicite(int id, int idPack) {
        this.id = id;
        this.idPack = idPack;
    }
    public EmplacementPublicite(int id) {
        this.id = id;
    }

    public int getIdPack() {
        return idPack;
    }

    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EmplacementPublicite{" + "id=" + id + ", idPack=" + idPack + '}';
    }
    

    
    
}
