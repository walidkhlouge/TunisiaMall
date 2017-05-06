/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import com.jfoenix.controls.JFXButton;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tunisiamall.main.MainRespE;


public class FXMLAccueilREnController {

    @FXML
    JFXButton compte;
    @FXML
    JFXButton boutique;
    @FXML
    JFXButton carte;
    @FXML
    JFXButton enseigne;
    @FXML
    JFXButton produit;
    @FXML
    JFXButton pack;
    @FXML
    JFXButton stats;
    @FXML
    JFXButton visite;
    @FXML
    JFXButton catalogue;
    @FXML
    Text node;
    @FXML
    Pane parentPane;

    private MainRespE main;

    public void setMain(MainRespE main) {
        this.main = main;
        File file1 = new File("src/tunisiamall/GUI/images/enseigne/Compte.png");
        File file2 = new File("src/tunisiamall/GUI/images/enseigne/boutique.png");
        File file3 = new File("src/tunisiamall/GUI/images/enseigne/carte.png");
        File file4 = new File("src/tunisiamall/GUI/images/enseigne/enseigne.png");
        File file5 = new File("src/tunisiamall/GUI/images/enseigne/produit.png");
        File file6 = new File("src/tunisiamall/GUI/images/enseigne/pub.png");
        File file7 = new File("src/tunisiamall/GUI/images/enseigne/catalogue.png");
        File file8 = new File("src/tunisiamall/GUI/images/enseigne/stats.png");
        File file9 = new File("src/tunisiamall/GUI/images/enseigne/visite.png");
        Image image1 = new Image(file1.toURI().toString());
        Image image2 = new Image(file2.toURI().toString());
        Image image3 = new Image(file3.toURI().toString());
        Image image4 = new Image(file4.toURI().toString());
        Image image5 = new Image(file5.toURI().toString());
        Image image6 = new Image(file6.toURI().toString());
        Image image7 = new Image(file7.toURI().toString());
        Image image8 = new Image(file8.toURI().toString());
        Image image9 = new Image(file9.toURI().toString());
        compte.setGraphic(new ImageView(image1));
        boutique.setGraphic(new ImageView(image2));
        enseigne.setGraphic(new ImageView(image4));
        produit.setGraphic(new ImageView(image5));
        carte.setGraphic(new ImageView(image3));
        pack.setGraphic(new ImageView(image6));
        catalogue.setGraphic(new ImageView(image7));
        stats.setGraphic(new ImageView(image8));
        visite.setGraphic(new ImageView(image9));
        Rss();
    }

    @FXML
    public void HandleEnseigne() {
        main.mainWindowE();
    }

    @FXML
    public void HandleCompte() {
        main.mainWindowC();
    }

    @FXML
    public void HandleBoutique() {
        main.window1();
    }

    @FXML
    public void HandleProduit() {
        main.window1P();
    }

    @FXML
    public void HandlePub() {
        main.mainWindowPub();
    }

    @FXML
    public void HandleCarte() {
        main.changeWindowCarte();
    }

    @FXML
    public void HandleVisite() {
        main.mainWindowVisite();
    }

    @FXML
    public void HandleStats() {
        main.mainWindowStat();
    }

    @FXML
    public void HandleCatalogue() {
        main.mainWindowCatalogue();
    }


    public static String readRSS(String urlAdress) {
        try {
            URL rssURL = new URL(urlAdress);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssURL.openStream()));
            String sourceCode = "";
            String line;
            while ((line = in.readLine()) != null) {
                if (line.contains("<title>")) {
                    int firstpos = line.indexOf("<title>");
                    String temp = line.substring(firstpos);
                    temp = temp.replace("<title>", "");
                    int lastPos = temp.indexOf("</title>");
                    temp = temp.substring(0, lastPos);
                    sourceCode += temp + "      ";
                }
            }
            in.close();
            return sourceCode;
        } catch (MalformedURLException ex) {
            Logger.getLogger(FXMLGestionEnseigneController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLGestionEnseigneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void Rss() {
        node.setText(readRSS("http://www.shop.com/top_100_electronics-rss.xhtml"));
        node.setTextOrigin(VPos.TOP);
        double sceneWidth = parentPane.getWidth();
        double msgWidth = node.getLayoutBounds().getWidth();
        KeyValue initKeyValue = new KeyValue(node.translateXProperty(), sceneWidth);
        KeyFrame initFrame = new KeyFrame(Duration.ZERO, initKeyValue);
        KeyValue endKeyValue = new KeyValue(node.translateXProperty(), -1.0 * msgWidth);
        KeyFrame endFrame = new KeyFrame(Duration.seconds(400), endKeyValue);
        Timeline timeline = new Timeline(initFrame, endFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

}
