/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tunisiamall.DAO.EnseigneDAO;
import tunisiamall.DAO.ProduitDAO;
import tunisiamall.entities.Enseigne;
import tunisiamall.entities.Produit;
import tunisiamall.main.MainRespE;


public class FXMLAjProduitRespEController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private MainRespE main;
    Stage stage;
    Parent root;

    @FXML
    TextField produitlabel;

   
    
    @FXML
    TextField enseignelabel;      
    
    @FXML
    TextField tauxlabel;
    
    @FXML
    TextField fidelitelabel;        
    @FXML
    TextField infolabel;

    @FXML
    Button newP;

    @FXML
    Button cancel;

    @FXML
    Button parcourir;

    @FXML
    Label imagenameLabel;

    @FXML
    ImageView ajoutimageView;

    ProduitDAO pDao = new ProduitDAO();
    EnseigneDAO eDao = new EnseigneDAO();
    List<Enseigne> le = new ArrayList<>();

//    private FXMLProduitRespEController mainP;
    public void setMain(MainRespE main, Stage stage) {

        this.main = main;
        this.stage = stage;
    }

    public void newP() {

        Produit p = new Produit();

        p.setNonEnseigne(enseignelabel.getText());
        p.setNom(produitlabel.getText());
        p.setInfo(infolabel.getText());

        p.setUrl(imagenameLabel.getText());

       String fidp=fidelitelabel.getText();
        int i=Integer.parseInt(fidp);
          p.setPtsfidelite(i);
       le = eDao.findAllbyResponsableEns("ramilog");
        
     
       //if (ne.getNom()== enseignelabel.getText()) {
        pDao.addProduitP(p);
        main.getDataP().add(p);
       
      // else {System.out.println(" nom enseigne non valide ");}
       
        stage.close();
    }
    

    public void handleCancel() {

        stage.close();
    }

    public void parcourir() {
        FileChooser fileChooser = new FileChooser();

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            imagenameLabel.setText(selectedFile.getName());
            File file = new File("src/tunisiamall/GUI/images/produit/" + selectedFile.getName());
            File source = new File(selectedFile.getAbsolutePath());
            try {
                org.apache.commons.io.FileUtils.copyFile(source, file);
                Image image2 = new Image(source.toURI().toString());
                ajoutimageView.setImage(image2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
