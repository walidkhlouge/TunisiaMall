/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static sun.security.jgss.GSSUtil.login;
import tunisiamall.DAO.CatalogueDAO;
import tunisiamall.entities.Catalogue;


public class FXMLCataloguesController implements Initializable {
@FXML
ListView listeCatalogue;
    CatalogueDAO cataDao = new CatalogueDAO();
    ArrayList<Catalogue> catalogues = new ArrayList();
    ArrayList<String> cIntitule = new ArrayList();
    
    ObservableList items=FXCollections.observableArrayList ();
    /**
     * Initializes the controller class.
     */
    @FXML
    private void onClick(MouseEvent event) throws IOException
    {
        int catalogueInd = 0;
         catalogueInd = listeCatalogue.getSelectionModel().getSelectedIndex();
        
        if (catalogueInd!=0){
         FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/tunisiamall/GUI/FXMLClientCata.fxml"));
                    loader.load();
                    Parent p=loader.getRoot();
    Stage stage = new Stage();
                    stage.setScene(new Scene(p));
        FXMLClientCataController controller=loader.getController();
        controller.initData(catalogues.get(catalogueInd).getId());
        stage.show();
        }
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        catalogues=(ArrayList<Catalogue>) cataDao.findAll();
        for( Catalogue c : catalogues)
        {
        items.add(c.getIntitule());
        }
        listeCatalogue.setItems(items);
        // TODO
    }    
    
}
