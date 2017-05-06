/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tunisiamall.DAO.CompteDAO;
import tunisiamall.entities.Compte;
import tunisiamall.main.Gestion;


public class FXMLModifierCoorRespBController implements Initializable{

    @FXML
    TextField txtNom;
    @FXML
    TextField txtPrenom;
    @FXML
    TextField txtMail;
    @FXML
    PasswordField txtMdp;
    @FXML
    PasswordField txtNMdp;
    @FXML
    Label l1;
    @FXML
    Label l2;
    @FXML
    Label l3;
    @FXML
    Label l4;
    @FXML
    Label l5;
    @FXML
    Button btnEnregistrer;
    @FXML
    Button btnBoutique;
    
    String login=Compte.connected.getLogin();
    CompteDAO Cdao;
    Compte C;
    
      @FXML
    Button btnParcourir;
    @FXML
    ImageView imgP;
    @FXML
    Label imgL;
    @FXML
    public void RedirectBou(ActionEvent event) throws Exception{
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Gestion M=new Gestion();
    Stage stage=new Stage();
    stage.setHeight(700);
    stage.setWidth(950);
    stage.sizeToScene();
    stage.show();
    M.start(stage);
    }
    @FXML
    public void Parcourir() {
        FileChooser fileChooser = new FileChooser();

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            imgL.setText(selectedFile.getName());
            File file = new File("src/tunisiamall/GUI/images/" + selectedFile.getName());
            File source = new File(selectedFile.getAbsolutePath());
            try {
                org.apache.commons.io.FileUtils.copyFile(source, file);
                Image image2 = new Image(source.toURI().toString());
                imgP.setImage(image2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    @FXML
    public void Enregistrer(){
      Cdao=new CompteDAO();
      C=new Compte();
        C=Cdao.findCompteByLogin(login);
        if (txtNom.getText().equals("") ||txtPrenom.getText().equals("") ||txtMail.getText().equals("") ){
            if (txtNom.getText().equals(""))
                l1.setVisible(true);
            else if (txtPrenom.getText().equals(""))
                l2.setVisible(true);
            else if (txtMail.getText().equals(""))
                l3.setVisible(true);}
        else if (txtNMdp.getText().equals("") && txtMdp.getText().equals("") && txtNom.getText()!="" 
                && txtPrenom.getText()!="" && txtMail.getText()!="" ){
        
            Cdao.updateCompte(login, txtNom.getText(),txtPrenom.getText(), txtMail.getText(),imgL.getText());
        Image image2 = new Image("src/tunisiamall/GUI/images/"+imgL.getText());
        imgP.setImage(image2);
        imgL.setText(C.getPhoto());
        txtNom.setText(C.getNom());
        txtPrenom.setText(C.getPrenom());
        txtMail.setText(C.getEmail());}
        else if (!(txtNMdp.getText().equals("")) && !(txtMdp.getText().equals("")) && (txtNom.getText()!="") &&
                (txtPrenom.getText()!="")  
                && (txtMail.getText()!="")&&(C.getPassword().equals(txtMdp.getText()))){
            Cdao.updateCompte(Compte.connected, txtNMdp.getText() ,txtNom.getText(), txtPrenom.getText(), txtMail.getText(),imgL.getText());
         C=Cdao.findCompteByLogin(login);
            
              
                Image image2 = new Image("src/tunisiamall/GUI/images/"+imgL.getText());
                imgP.setImage(image2);
           imgL.setText(C.getPhoto());
       
        txtNom.setText(C.getNom());
        txtPrenom.setText(C.getPrenom());
        txtMail.setText(C.getEmail());        
        }
        else {l4.setVisible(true);
             l5.setVisible(true);
        System.out.println("Erreur");}
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        C=new Compte();
        Cdao=new CompteDAO();
        C=Cdao.findCompteByLogin(login);
        File file = new File("src/tunisiamall/GUI/images/"+C.getPhoto());
            File source = new File(file.getAbsolutePath());
            
                
                Image image2 = new Image(source.toURI().toString());
                imgP.setImage(image2);
           imgL.setText(C.getPhoto());
       
        txtNom.setText(C.getNom());
        txtPrenom.setText(C.getPrenom());
        txtMail.setText(C.getEmail());
        
        
    }
    
}
