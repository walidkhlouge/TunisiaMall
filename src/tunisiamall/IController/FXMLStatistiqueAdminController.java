/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import tunisiamall.DAO.CompteDAO;
import tunisiamall.DAO.EnseigneDAO;
import tunisiamall.DAO.PacksPubDAO;


public class FXMLStatistiqueAdminController implements Initializable{
    @FXML
    BarChart<String,Number> chart;
    @FXML
    LineChart<String,Number> chartv;
    @FXML
    Label NE,NP,SP;
    
   
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CompteDAO Cdao=new CompteDAO();
        PacksPubDAO PaDAO=new PacksPubDAO();
        EnseigneDAO Edao=new EnseigneDAO();
        NE.setText(Integer.toString(Edao.NombreEnseigne()));
        NP.setText(Integer.toString(PaDAO.NombrePack()));
        SP.setText(Integer.toString(PaDAO.SommePack())+" Dinars");
      Date d=new Date();
      System.out.println(d.getDate());
       
       XYChart.Series<String,Number> series1 = new XYChart.Series();  
       series1.setName("Responsables Enseigne");
       
       series1.getData().add(new XYChart.Data<>("",Cdao.NombreActeur("Responsable Enseigne")));
       XYChart.Series<String,Number> series2 = new XYChart.Series();
       series2.setName("Responsables Boutique");
       series2.getData().add(new XYChart.Data<>("",Cdao.NombreActeur("Responsable Boutique")));
       XYChart.Series<String,Number> series3 = new XYChart.Series();
       series3.setName("Clients");
       series3.getData().add(new XYChart.Data<>("",Cdao.NombreActeur("client")));
        
     chart.getData().addAll(series1,series2,series3);
     XYChart.Series series = new XYChart.Series(); 
     series.setName("Nombre de visites des derniers jours");
     series.getData().add(new XYChart.Data<>(d.getMonth()+1+"/"+(d.getDate()-5),Cdao.NombreVisite(d.getMonth()+1,d.getDate()-5)));
     series.getData().add(new XYChart.Data<>(d.getMonth()+1+"/"+(d.getDate()-4),Cdao.NombreVisite(d.getMonth()+1,d.getDate()-4)));
     series.getData().add(new XYChart.Data<>(d.getMonth()+1+"/"+(d.getDate()-3),Cdao.NombreVisite(d.getMonth()+1,d.getDate()-3)));
     series.getData().add(new XYChart.Data<>(d.getMonth()+1+"/"+(d.getDate()-2),Cdao.NombreVisite(d.getMonth()+1,d.getDate()-2)));
     series.getData().add(new XYChart.Data<>(d.getMonth()+1+"/"+(d.getDate()-1),Cdao.NombreVisite(d.getMonth()+1,d.getDate()-1)));


     chartv.getData().add(series);
      
    }
}
