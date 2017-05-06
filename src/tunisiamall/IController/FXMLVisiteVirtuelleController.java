/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import tunisiamall.main.MainRespE;


public class FXMLVisiteVirtuelleController {

    private MainRespE main;
    private Stage stage;
    @FXML
    WebView web;

    public void setMain(MainRespE main, Stage stage) {

        web.getEngine().load("file:///C:/Users/ACER/Desktop/test.html");
        // web.getEngine().load("http://krpano.com/krpano.html?xml=tours/corfu/tour.xml&html5=auto");
        this.main = main;
        this.stage = stage;

    }
}
