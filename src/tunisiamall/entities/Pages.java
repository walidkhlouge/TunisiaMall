/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entities;


public class Pages {
    private int NbPage;
    private int IdCatalogue;

    public Pages(int NbPage) {
        this.NbPage = NbPage;
    }

    public Pages(int NbPage, int IdCatalogue) {
        this.NbPage = NbPage;
        this.IdCatalogue = IdCatalogue;
    }

    public int getIdCatalogue() {
        return IdCatalogue;
    }

    public void setIdCatalogue(int IdCatalogue) {
        this.IdCatalogue = IdCatalogue;
    }
    

    public Pages() {
    }

    public int getNbPage() {
        return NbPage;
    }

    public void setNbPage(int NbPage) {
        this.NbPage = NbPage;
    }

    @Override
    public String toString() {
        return "Pages{" + "NbPage=" + NbPage + '}';
    }
    
    
    
}
