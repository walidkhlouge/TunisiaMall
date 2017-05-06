/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import tunisiamall.DAO.EnseigneDAO;
import tunisiamall.entities.PackPublicitaire;
import tunisiamall.main.MainRespE;
import static tunisiamall.main.MainRespE.loginR;


public class FXMLAchatPacksController {

    /**
     * Initializes the controller class.
     */
    private Stage stage;
    private MainRespE main;
    PackPublicitaire p ;
    EnseigneDAO d = new EnseigneDAO();

    public void setMain(MainRespE main, Stage stage,PackPublicitaire p) {
        this.main = main;
        this.stage = stage;
        this.p=p;

    }

    @FXML
    public void HandleCancel() {
        stage.close();
    }

    @FXML
    public void HandleAcheter() {
        d.updateEnseignePack(loginR, p.getId());
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confimation");
        alert.setHeaderText("Pack Publicitaire acheté !!!");
        alert.showAndWait();
        stage.close();
    }

}
