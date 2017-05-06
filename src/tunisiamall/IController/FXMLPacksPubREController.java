/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.xml.bind.DatatypeConverter;
import tunisiamall.DAO.PackPublicitaireDAO;
import tunisiamall.entities.PackPublicitaire;
import tunisiamall.main.MainRespE;


public class FXMLPacksPubREController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private MainRespE main;
    private Stage stage;
    @FXML
    AnchorPane anchor;
    PackPublicitaireDAO pd = new PackPublicitaireDAO();
    List<PackPublicitaire> lp = new ArrayList<>();

    @Override

    public void initialize(URL url, ResourceBundle rb) {

        lp = pd.findAll2();
        int layoutx = 14;
        int layouty = 50;
        for (PackPublicitaire p : lp) {
            Text d = new Text();
            Text t = new Text();
            JFXButton b = new JFXButton();
            b.setFont(new Font("Arial", 30));
            b.setText(p.getNom());
            t.setFont(new Font("Arial", 20));
            t.setText("Prix :" + String.valueOf(p.getPrix()));
            d.setFont(new Font("Arial", 15));
            d.setText(p.getDesc());
            b.setStyle("-fx-background-color: rgb(255,15,0)");
            b.setPrefHeight(400);
            b.setPrefWidth(170);
            b.setLayoutX(layoutx);
            b.setLayoutY(layouty);
            t.setLayoutX(layoutx + 35);
            t.setLayoutY(layouty * 9);
            d.setLayoutX(layoutx + 15);
            d.setLayoutY(layouty * 7);
            layoutx = layoutx + 180;
            anchor.getChildren().add(b);
            anchor.getChildren().add(t);
            anchor.getChildren().add(d);
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    main.acheterPackWindow(p);
                }
            });
        }
    }

    public void setMain(MainRespE main, Stage stage) {
        this.main = main;
        this.stage = stage;

    }
}
