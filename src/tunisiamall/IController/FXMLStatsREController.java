/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import tunisiamall.main.MainRespE;


public class FXMLStatsREController implements Initializable {

    private MainRespE main;
    private Stage stage;
    @FXML
    PieChart chart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chart.setTitle("Nobmbre de visite par Enseigne");
        chart.getData().setAll(new PieChart.Data("Zara", 50), new PieChart.Data("Celio", 30),
                new PieChart.Data("Pull and Bear", 25), new PieChart.Data("City sport", 42),
                new PieChart.Data("Zen", 5), new PieChart.Data("Nike", 19)
        );
    }

    public void setMain(MainRespE main, Stage stage) {
        this.main = main;
        this.stage = stage;

    }

}
