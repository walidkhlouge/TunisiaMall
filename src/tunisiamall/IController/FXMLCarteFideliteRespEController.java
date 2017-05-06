/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.IController;

import com.jfoenix.controls.JFXTextField;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import tunisiamall.DAO.CarteFideliteDAO;
import tunisiamall.DAO.EnseigneDAO;
import tunisiamall.entities.CarteFidelite;
import tunisiamall.entities.Enseigne;
import tunisiamall.main.MainRespE;
import static tunisiamall.main.MainRespE.loginR;


public class FXMLCarteFideliteRespEController {

    @FXML
    JFXTextField taux;
    @FXML
    JFXTextField points;
    @FXML
    Button tauxReduc;
    @FXML
    ComboBox EnseigneCB;

    private MainRespE main;
    private Stage stage;
    CarteFidelite c = new CarteFidelite();
    EnseigneDAO e = new EnseigneDAO();

    public void setMain(MainRespE main, Stage stage) {
        this.main = main;
        this.stage = stage;

        List<Enseigne> l = e.findAllbyResponsableEns(loginR);
        for (Enseigne bn : l) {
            EnseigneCB.getItems().add(bn.getNom());
        }

    }

    public float tauxReduc() {
        CarteFideliteDAO d = new CarteFideliteDAO();

        String enseigne = (String) EnseigneCB.getValue();
        Float tauxTot;
        CarteFidelite c = d.findCarteByI(1);
        System.out.println(c.getId());
        String tauxp = taux.getText();
        Float t = Float.valueOf(tauxp);
        String p = (String) points.getText();
        int pt = Integer.parseInt(p);
        d.updateTauxPts(1, enseigne, pt, t);
        tauxTot = (c.getPoints() * t) / pt;
        System.out.println(tauxTot);
     
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confimation");
        alert.setHeaderText("Taux et points definis");
        alert.showAndWait();
        stage.close();
         return tauxTot;
    }

}
