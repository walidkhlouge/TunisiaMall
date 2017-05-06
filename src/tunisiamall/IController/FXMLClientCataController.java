/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import tunisiamall.DAO.ProduitDAO;
import tunisiamall.entities.Produit;


public class FXMLClientCataController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Pagination page;
    @FXML
    ImageView image1;
    @FXML
    ImageView image2;
    @FXML
    ImageView image3;
    @FXML
    ImageView image4;
    @FXML
    ImageView image5;
    @FXML
    ImageView image6;
    ProduitDAO pDao = new ProduitDAO();
   int compteur=0;
    ArrayList<Produit> produits = new ArrayList();
    int reste;
     public void initData(int catalogue)
     {
        produits=pDao.findProduitsByCatalogue(catalogue);
        if(produits.size()%6==0){
         page.setPageCount(produits.size()/6);
        }
        else{ 
            reste=produits.size()%6;
            page.setPageCount((int) produits.size()/6+1);
        }
        if (page.getPageCount()>1)
        {
            image1.setImage(new Image(produits.get(0).getUrl()+".jpg"));
            
            image2.setImage(new Image(produits.get(1).getUrl()+".jpg"));
            image3.setImage(new Image(produits.get(2).getUrl()+".jpg"));
            image4.setImage(new Image(produits.get(3).getUrl()+".jpg"));
            image5.setImage(new Image(produits.get(4).getUrl()+".jpg"));
            image6.setImage(new Image(produits.get(5).getUrl()+".jpg"));
        }
        else
        {
            if(reste>0)
                image1.setImage(new Image(produits.get(0).getUrl()+".jpg"));
            if(reste>1)
            image2.setImage(new Image(produits.get(1).getUrl()+".jpg"));
            if(reste>2)
            image3.setImage(new Image(produits.get(2).getUrl()+".jpg"));
            if(reste>3)
            image4.setImage(new Image(produits.get(3).getUrl()+".jpg"));
            if(reste>4)
            image5.setImage(new Image(produits.get(4).getUrl()+".jpg"));
            if(reste>5)
            image6.setImage(new Image(produits.get(5).getUrl()+".jpg"));
                
        }
     }
     @FXML
     private void onClick(MouseEvent event)
     {
        image1.setImage(null);
        image2.setImage(null);
        image3.setImage(null);
        image4.setImage(null);
        image5.setImage(null);
        image6.setImage(null);
        
        if(page.getCurrentPageIndex()==page.getPageCount()-1){
            if(reste>0) 
                image1.setImage(new Image(produits.get(0+page.getCurrentPageIndex()*6).getUrl()+"2.jpg"));
            if(reste>1)
            image2.setImage(new Image(produits.get(1+page.getCurrentPageIndex()*6).getUrl()+".jpg"));
            if(reste>2)
            image3.setImage(new Image(produits.get(2+page.getCurrentPageIndex()*6).getUrl()+".jpg"));
            if(reste>3)
            image4.setImage(new Image(produits.get(3+page.getCurrentPageIndex()*6).getUrl()+".jpg"));
            if(reste>4)
            image5.setImage(new Image(produits.get(4+page.getCurrentPageIndex()*6).getUrl()+".jpg"));
            if(reste>5)
            image6.setImage(new Image(produits.get(5+page.getCurrentPageIndex()*6).getUrl()+".jpg"));
        }
        else
        {
             image1.setImage(new Image(produits.get(0+page.getCurrentPageIndex()*6).getUrl()+".jpg"));
            image2.setImage(new Image(produits.get(1+page.getCurrentPageIndex()*6).getUrl()+".jpg"));
            image3.setImage(new Image(produits.get(2+page.getCurrentPageIndex()*6).getUrl()+".jpg"));
            image4.setImage(new Image(produits.get(3+page.getCurrentPageIndex()*6).getUrl()+".jpg"));
            image5.setImage(new Image(produits.get(4+page.getCurrentPageIndex()*6).getUrl()+".jpg"));
            image6.setImage(new Image(produits.get(5+page.getCurrentPageIndex()*6).getUrl()+".jpg"));
        }
         
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
