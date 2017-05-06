/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.main;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class includeClient {

    static public BorderPane mainLayout;
    Stage stage;

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        MainView();

    }

    public static void MainView() throws IOException {
        Stage stage2 = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(includeClient.class.getResource("../GUI/FXMLClientAccueilInclude.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        stage2.setScene(scene);
        stage2.show();
        reste();
    }

    public static void reste() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(includeClient.class.getResource("../GUI/FXMLClientPageAccueil.fxml"));
        BorderPane o = loader.load();
        mainLayout.setCenter(o);
    }
    /**
     * @param args the command line arguments
     */

}
