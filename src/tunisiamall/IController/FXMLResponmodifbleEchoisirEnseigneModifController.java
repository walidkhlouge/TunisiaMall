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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tunisiamall.DAO.EnseigneDAO;
import tunisiamall.entities.Enseigne;


             
public class FXMLResponmodifbleEchoisirEnseigneModifController implements Initializable{
@FXML
ImageView img0;
@FXML
ImageView limg1;
@FXML
ImageView  limg2;
@FXML
ImageView  limg3;
@FXML
ImageView limg4;
@FXML
ImageView  limg5;
@FXML
ImageView  limg6;
@FXML
ImageView limg7;
@FXML
ImageView  limg8;
@FXML
ImageView  limg9;
@FXML
Label label1;
@FXML
Label label2;
@FXML
Label label3;
@FXML
Label label4;
@FXML
Label label5;
@FXML
Label label6;
@FXML
Label label7;
@FXML
Label label8;
@FXML
Label label9;
@FXML
Label label10;
@FXML
Button modifb1;
@FXML
Button modifb2;
@FXML
Button modifb3;
@FXML
Button modifb4;
@FXML
Button modifb5;
@FXML
Button modifb6;
@FXML
Button supb1;
@FXML
Button supb2;
@FXML
Button supb3;
@FXML
Button supb4;
@FXML
Button supb5;
@FXML
Button supb6;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EnseigneDAO e=new  EnseigneDAO();
        List<Enseigne>l=new ArrayList<Enseigne>();
        ArrayList<ImageView>  tabv=new ArrayList<ImageView>();
        ArrayList<Label>labels= new ArrayList<Label>();
        ArrayList <Button> boutonmod=new ArrayList<Button>();
        ArrayList <Button> boutonsup=new ArrayList<Button>();
        //ajouter les boutons modif
        boutonmod.add(modifb1);
        boutonmod.add(modifb2);
        boutonmod.add(modifb3);
        boutonmod.add(modifb4);
        boutonmod.add(modifb5);
        boutonmod.add(modifb6);
        //ajouter les boutons supp
        boutonsup.add(supb1);
        boutonsup.add(supb2);
        boutonsup.add(supb3);
        boutonsup.add(supb4);
        boutonsup.add(supb5);
        boutonsup.add(supb6);
        
        tabv.add(limg1);
        tabv.add(limg2);
        tabv.add(limg3);
        tabv.add(limg4);
        tabv.add(limg5);
        tabv.add(limg6);
        labels.add(label1);
        labels.add(label2);
        labels.add(label3);
        labels.add(label4);
        labels.add(label5);
        labels.add(label6);
        ArrayList<Enseigne>enseignes=new ArrayList<Enseigne>();
        l=e.findAllbyResponsableEns("bassemlog");
        
       File file2 = new File("src/tunisiamall/GUI/images/enseigne/homme2.jpg");
        Image image2 = new Image(file2.toURI().toString());
        img0.setImage(image2);
        int i;
        for(i=0;i<l.size()&& i<6;i++)
        {
        
        labels.get(i).setText(l.get(i).getNom());
        File file = new File(l.get(i).getUrl());
        Image image = new Image(file.toURI().toString());
        tabv.get(i).setImage(image);
        }
        for(int j=i;j<6;j++){
            
       tabv.get(j).setManaged(false);
        labels.get(j).setManaged(false);
        boutonmod.get(j).setManaged(false);
        boutonsup.get(j).setManaged(false);
        }
        
    }
    @FXML
    public void mod1(ActionEvent event) throws IOException{
        ((Node)event.getSource()).getScene().getWindow().hide();
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/tunisiamall/GUI/FXMLModifEnseigne.fxml"));
        loader.load();
        Parent p=loader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(p));
        stage.show();
    }
@FXML
public void abc(){
    
}
    
}
