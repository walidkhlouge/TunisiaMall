/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.main;

import insidefx.undecorator.Undecorator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunisiamall.DAO.BoutiqueDAO;
import tunisiamall.DAO.CompteDAO;
import tunisiamall.DAO.EnseigneDAO;
import tunisiamall.DAO.ProduitDAO;
import tunisiamall.IController.FXMLAccueilREnController;
import tunisiamall.IController.FXMLAchatPacksController;
import tunisiamall.IController.FXMLAjBoutiqueRespEController;
import tunisiamall.IController.FXMLAjProduitRespEController;
import tunisiamall.IController.FXMLBoutiqueRespEController;
import tunisiamall.IController.FXMLCarteFideliteRespEController;
import tunisiamall.IController.FXMLCatalogueController;
import tunisiamall.IController.FXMLGestionCompteController;
import tunisiamall.IController.FXMLGestionEnseigneAjoutController;
import tunisiamall.IController.FXMLGestionEnseigneController;
import tunisiamall.IController.FXMLGestionEnseigneModifController;
import tunisiamall.IController.FXMLModifBoutiqueRespEController;
import tunisiamall.IController.FXMLModifProduitRespEController;
import tunisiamall.IController.FXMLPacksPubREController;
import tunisiamall.IController.FXMLProduitRespEController;
import tunisiamall.IController.FXMLStatsREController;
import tunisiamall.IController.FXMLVisiteVirtuelleController;
import tunisiamall.entities.Boutique;
import tunisiamall.entities.Compte;
import tunisiamall.entities.Enseigne;
import tunisiamall.entities.PackPublicitaire;
import tunisiamall.entities.Produit;


public class MainRespE extends Application{

    File file = new File("src/tunisiamall/GUI/images/enseigne/icon.png");
    Image imageIcon = new Image(file.toURI().toString());
    EnseigneDAO e = new EnseigneDAO();
    List<Enseigne> l = new ArrayList<>();
    CompteDAO c = new CompteDAO();
    List<Compte> lc = new ArrayList<>();
    BoutiqueDAO b = new BoutiqueDAO();
    EnseigneDAO en = new EnseigneDAO();
    List<Boutique> lb = new ArrayList<>();
    List<Enseigne> le = new ArrayList<>();
    BoutiqueDAO bDao = new BoutiqueDAO();
    ProduitDAO pDao = new ProduitDAO();
    public ObservableList<Produit> dataP;

    public ObservableList<Boutique> data;

    public ObservableList<Compte> compteData = FXCollections.observableArrayList();

    public ObservableList<Enseigne> enseigneData = FXCollections.observableArrayList();

    private Stage PrimaryStage;

    public static String loginR = Compte.connected.getLogin();
    @Override
    public void start(Stage PrimaryStage) {
        this.PrimaryStage = PrimaryStage;
        mainWindow();
    }

    public void mainWindow() {
        try {

            FXMLLoader loader = new FXMLLoader(MainRespE.class.getResource("/tunisiamall/GUI/FXMLAccueilREn.fxml"));
            AnchorPane pane = loader.load();
            //Scene scene = new Scene(pane);
            pane.setMinSize(566, 561);
            Undecorator undecorator = new Undecorator(PrimaryStage, pane);
            undecorator.getStylesheets().add("skin/undecorator.css");
            Scene scene = new Scene(undecorator);
            scene.setFill(Color.TRANSPARENT);
            PrimaryStage.initStyle(StageStyle.TRANSPARENT);

            FXMLAccueilREnController controller = loader.getController();
            controller.setMain(this);

            PrimaryStage.setScene(scene);
            PrimaryStage.show();
            PrimaryStage.setResizable(false);
            PrimaryStage.setTitle("Espace responsable enseigne");
        } catch (IOException ex) {
            Logger.getLogger(MainRespE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mainWindowE() {

        try {
            FXMLLoader loader = new FXMLLoader(MainRespE.class.getResource("/tunisiamall/GUI/FXMLGestionEnseigne.fxml"));
            AnchorPane pane = loader.load();
            Stage stage = new Stage();
//Scene scene = new Scene(pane);
            pane.setMinSize(735, 574);
            Undecorator undecorator = new Undecorator(stage, pane);
            undecorator.getStylesheets().add("skin/undecorator.css");
            Scene scene = new Scene(undecorator);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);

            FXMLGestionEnseigneController controller = loader.getController();
            controller.setMain(this, stage);

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            stage.getIcons().add(imageIcon);
            stage.setTitle("Espace responsable enseigne");
        } catch (IOException ex) {
            Logger.getLogger(MainRespE.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<Enseigne> getenseigneData() {
        return enseigneData;
    }

    public MainRespE() {

        l = e.findAllbyResponsableEns(loginR);

        for (Enseigne en : l) {
            enseigneData.add(en);
        }

    }

    public void ajouterEnseigneWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(MainRespE.class.getResource("/tunisiamall/GUI/FXMLGestionEnseigneAjout.fxml"));
            AnchorPane pane = loader.load();
            //Scene scene = new Scene(pane);

            Stage stage = new Stage();
            pane.setMinSize(652, 452);
            Undecorator undecorator = new Undecorator(stage, pane);
            undecorator.getStylesheets().add("skin/undecorator.css");
            Scene scene = new Scene(undecorator);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);

            FXMLGestionEnseigneAjoutController controller = loader.getController();

            controller.setMain(this, stage);

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            stage.setTitle("Espace responsable enseigne");
            stage.getIcons().add(imageIcon);
        } catch (IOException ex) {
            Logger.getLogger(MainRespE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean modifierEnseigneWindow(Enseigne e) {
        try {
            FXMLLoader loader = new FXMLLoader(MainRespE.class.getResource("/tunisiamall/GUI/FXMLGestionEnseigneModif.fxml"));
            AnchorPane pane = loader.load();
            //Scene scene = new Scene(pane);

            Stage stage = new Stage();
            pane.setMinSize(652, 452);
            Undecorator undecorator = new Undecorator(stage, pane);
            undecorator.getStylesheets().add("skin/undecorator.css");
            Scene scene = new Scene(undecorator);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);

            FXMLGestionEnseigneModifController controller = loader.getController();
            controller.setMain(this, stage, e);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Espace responsable enseigne");
            stage.getIcons().add(imageIcon);
            stage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException ex) {
            Logger.getLogger(MainRespE.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ObservableList<Compte> getcompteData() {
        return compteData;

    }

//    public MainRespE(String cf) {
//
//        /* l = c.findAll();
//         //c.findCompteByLogin()
//        
//
//         for (Compte cn : l) {
//         compteData.add(cn);
//         } */
//        le = en.findAllbyResponsableEns("ramilog");
//        for (Enseigne ne : le) {
//            lb = b.findBoutiqueByEnseigne(ne.getNom());
//            System.out.println("++++++++++++++++++***"+lb);
//            for (Boutique bn : lb) {
//                lc = c.findAllbyResponsableboutique(bn.getLoginResponsableBoutique());
//                System.out.println("*************************************" + lc);
//                //compteData.add();
//            }
//        }
//        for (Compte d : lc) {
//            compteData.add(d);
//        }
//
//    }
    public void mainWindowC() {
        try {
            FXMLLoader loader = new FXMLLoader(MainRespE.class.getResource("/tunisiamall/GUI/FXMLGestionCompte.fxml"));
            AnchorPane pane = loader.load();
            //Scene scene = new Scene(pane);
            Stage stage = new Stage();
            pane.setMinSize(712, 500);
            Undecorator undecorator = new Undecorator(stage, pane);
            undecorator.getStylesheets().add("skin/undecorator.css");
            Scene scene = new Scene(undecorator);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);

            FXMLGestionCompteController controller = loader.getController();
            controller.setMain(this, stage);

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            stage.setTitle("Espace responsable enseigne");
        } catch (IOException ex) {
            Logger.getLogger(MainRespE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void window1() {

        try {

            FXMLLoader loader = new FXMLLoader(MainRespE.class.getResource("/tunisiamall/GUI/BoutiqueRE.fxml"));
            AnchorPane pane = loader.load();
            pane.setMinSize(728, 531);
            Stage stage = new Stage();
            Undecorator undecorator = new Undecorator(stage, pane);
            undecorator.getStylesheets().add("skin/undecorator.css");
            Scene scene = new Scene(undecorator);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            //Scene scene = new Scene(pane);

            FXMLBoutiqueRespEController controller = loader.getController();
            controller.setMain(this, stage);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void changeWindowAj() {

        try {

            FXMLLoader loader = new FXMLLoader(MainRespE.class.getResource("/tunisiamall/GUI/FXMLAjBoutiqueRespE.fxml"));
            AnchorPane pane = loader.load();
            pane.setMinSize(600, 400);
            // Scene scene = new Scene(pane);
            Stage secondaryStage = new Stage();

            Undecorator undecorator = new Undecorator(secondaryStage, pane);
            undecorator.getStylesheets().add("skin/undecorator.css");
            Scene scene = new Scene(undecorator);
            scene.setFill(Color.TRANSPARENT);
            secondaryStage.initStyle(StageStyle.TRANSPARENT);

            FXMLAjBoutiqueRespEController controller = loader.getController();
            controller.setMain(this, secondaryStage);

            secondaryStage.setScene(scene);
            secondaryStage.setResizable(false);
            secondaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public boolean changeWindowModif(Boutique b) {

        try {

            FXMLLoader loader = new FXMLLoader(MainRespE.class.getResource("/tunisiamall/GUI/FXMLModifBoutiqueRespE.fxml"));
            AnchorPane pane = loader.load();
            pane.setMinSize(600, 400);
            //Scene scene = new Scene(pane);
            Stage thirdStage = new Stage();
            Undecorator undecorator = new Undecorator(thirdStage, pane);
            undecorator.getStylesheets().add("skin/undecorator.css");
            Scene scene = new Scene(undecorator);
            scene.setFill(Color.TRANSPARENT);
            thirdStage.initStyle(StageStyle.TRANSPARENT);

            FXMLModifBoutiqueRespEController controller = loader.getController();
            controller.setMain(this, b, thirdStage);
            controller.fillBoutiqueDetails();

            thirdStage.setScene(scene);
            thirdStage.setResizable(false);
            thirdStage.showAndWait();
            return controller.isOKClicked();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void setData() {

        List<Boutique> listB = null;
        List<Enseigne> listE;
        data = FXCollections.observableArrayList();
        //listB = bDao.findAll();
        listE = en.findAllbyResponsableEns(loginR);
        for (Enseigne e : listE) {
            listB = bDao.findBoutiqueByEnseigne(e.getNom());
            for (Boutique b : listB) {
                data.add(new Boutique(b.getIntitule(), b.getId(), b.getNomEnseigne(), b.getLoginResponsableBoutique(), b.getUrl()));
            }
        }

    }

    public ObservableList<Boutique> getData() {
        return data;
    }

    public void window1P() {

        try {

            FXMLLoader loader = new FXMLLoader(MainRespE.class.getResource("/tunisiamall/GUI/ProduitRE.fxml"));
            AnchorPane pane = loader.load();
            pane.setMinSize(728, 531);
            //Scene scene = new Scene(pane);
            Stage stage = new Stage();
            Undecorator undecorator = new Undecorator(stage, pane);
            undecorator.getStylesheets().add("skin/undecorator.css");
            Scene scene = new Scene(undecorator);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            FXMLProduitRespEController controller = loader.getController();
            controller.setMain(this, stage);
            stage.setScene(scene);

            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void changeWindowAjP() {

        try {

            FXMLLoader loader = new FXMLLoader(MainRespE.class.getResource("/tunisiamall/GUI/FXMLAjProduitRespE.fxml"));
            AnchorPane pane = loader.load();
            //Scene scene = new Scene(pane);
            pane.setMinSize(600, 400);
            Stage secondaryStage = new Stage();
            Undecorator undecorator = new Undecorator(secondaryStage, pane);
            undecorator.getStylesheets().add("skin/undecorator.css");
            Scene scene = new Scene(undecorator);
            scene.setFill(Color.TRANSPARENT);
            secondaryStage.initStyle(StageStyle.TRANSPARENT);

//            secondaryStage.getIcons().add(
//                    new Image("/tunisiamall/GUI/images/tunisiamall.jpg"));
            secondaryStage.setTitle("Tunisia Mall");
            FXMLAjProduitRespEController controller = loader.getController();
            controller.setMain(this, secondaryStage);

            secondaryStage.setScene(scene);
            secondaryStage.setResizable(false);
            secondaryStage.show();

        } catch (Exception e) {
        }

    }

    public boolean changeWindowModif(Produit p) {

        try {

            FXMLLoader loader = new FXMLLoader(MainRespE.class.getResource("/tunisiamall/GUI/FXMLModifProduitRespE.fxml"));
            AnchorPane pane = loader.load();
            //Scene scene = new Scene(pane);
            pane.setMinSize(600, 400);
            Stage thirdStage = new Stage();

            Undecorator undecorator = new Undecorator(thirdStage, pane);
            undecorator.getStylesheets().add("skin/undecorator.css");
            Scene scene = new Scene(undecorator);
            scene.setFill(Color.TRANSPARENT);
            thirdStage.initStyle(StageStyle.TRANSPARENT);

//            thirdStage.getIcons().add(
//                    new Image("/tunisiamall/GUI/images/tunisiamall.jpg")); 
            thirdStage.setTitle("Tunisia Mall");

            FXMLModifProduitRespEController controller = loader.getController();
            controller.setMain(this, p, thirdStage);
            controller.fillProduitDetails();

            thirdStage.setScene(scene);
            thirdStage.setResizable(false);
            thirdStage.showAndWait();
            return controller.isOKClicked();
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }

    }

    public void setDataP() {
        List<Produit> listP = null ;
        List<Enseigne> listE=en.findAllbyResponsableEns(loginR);
        dataP = FXCollections.observableArrayList();
        for (Enseigne e : listE) {
            listP =pDao.findProduitByEnseigne(e.getNom());
            for (Produit p : listP) {
                dataP.add(new Produit(p.getId(), p.getNom(), p.getNonEnseigne(), p.getInfo(), p.getUrl(), p.getPtsfidelite()));
            }
        }
//        for (Produit p : listP)
//        {
//             dataP.add(new Produit(p.getId(), p.getNom(), p.getNonEnseigne(), p.getInfo(), p.getUrl(), p.getPtsfidelite()));
//        }
    }

    public ObservableList<Produit> getDataP() {
        return dataP;
    }

    public void changeWindowCarte() {

        try {

            FXMLLoader loader = new FXMLLoader(MainRespE.class.getResource("/tunisiamall/GUI/FXMLCarteFideliteRespE.fxml"));
            AnchorPane pane = loader.load();
            //Scene scene = new Scene(pane);
            pane.setMinSize(600, 400);
            Stage secondaryStage = new Stage();
            Undecorator undecorator = new Undecorator(secondaryStage, pane);
            undecorator.getStylesheets().add("skin/undecorator.css");
            Scene scene = new Scene(undecorator);
            scene.setFill(Color.TRANSPARENT);
            secondaryStage.initStyle(StageStyle.TRANSPARENT);

//            secondaryStage.getIcons().add(
//                    new Image("/tunisiamall/GUI/images/tunisiamall.jpg"));
            secondaryStage.setTitle("Tunisia Mall");
            FXMLCarteFideliteRespEController controller = loader.getController();
            controller.setMain(this, secondaryStage);

            secondaryStage.setScene(scene);
            secondaryStage.setResizable(false);
            secondaryStage.show();

        } catch (Exception e) {
        }

    }

    public void mainWindowPub() {
        try {
            FXMLLoader loader = new FXMLLoader(MainRespE.class.getResource("/tunisiamall/GUI/FXMLPacksPubRE.fxml"));
            AnchorPane pane = loader.load();
            //Scene scene = new Scene(pane);
            Stage stage = new Stage();
            pane.setMinSize(712, 500);
            Undecorator undecorator = new Undecorator(stage, pane);
            undecorator.getStylesheets().add("skin/undecorator.css");
            Scene scene = new Scene(undecorator);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);

            FXMLPacksPubREController controller = loader.getController();
            controller.setMain(this, stage);

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            stage.setTitle("Espace responsable enseigne");
        } catch (IOException ex) {
            Logger.getLogger(MainRespE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void acheterPackWindow(PackPublicitaire p) {
        try {
            FXMLLoader loader = new FXMLLoader(MainRespE.class.getResource("/tunisiamall/GUI/FXMLAchatPacks.fxml"));
            AnchorPane pane = loader.load();
            //Scene scene = new Scene(pane);

            Stage stage = new Stage();
            pane.setMinSize(250, 250);
            Undecorator undecorator = new Undecorator(stage, pane);
            undecorator.getStylesheets().add("skin/undecorator.css");
            Scene scene = new Scene(undecorator);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);

            FXMLAchatPacksController controller = loader.getController();

            controller.setMain(this, stage, p);

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            stage.setTitle("Espace responsable enseigne");
            stage.getIcons().add(imageIcon);
        } catch (IOException ex) {
            Logger.getLogger(MainRespE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mainWindowVisite() {
        try {
            FXMLLoader loader = new FXMLLoader(MainRespE.class.getResource("/tunisiamall/GUI/FXMLVisiteVirtuelle.fxml"));
            AnchorPane pane = loader.load();
            //Scene scene = new Scene(pane);
            Stage stage = new Stage();
            pane.setMinSize(1000, 500);
            Undecorator undecorator = new Undecorator(stage, pane);
            undecorator.getStylesheets().add("skin/undecorator.css");
            Scene scene = new Scene(undecorator);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);

            FXMLVisiteVirtuelleController controller = loader.getController();
            controller.setMain(this, stage);

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            stage.setTitle("Espace responsable enseigne");
        } catch (IOException ex) {
            Logger.getLogger(MainRespE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mainWindowStat() {

        try {
            FXMLLoader loader = new FXMLLoader(MainRespE.class.getResource("/tunisiamall/GUI/FXMLStatsRE.fxml"));
            AnchorPane pane = loader.load();
            Stage stage = new Stage();
//Scene scene = new Scene(pane);
            pane.setMinSize(735, 574);
            Undecorator undecorator = new Undecorator(stage, pane);
            undecorator.getStylesheets().add("skin/undecorator.css");
            Scene scene = new Scene(undecorator);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);

            FXMLStatsREController controller = loader.getController();
            controller.setMain(this, stage);

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            stage.getIcons().add(imageIcon);
            stage.setTitle("Espace responsable enseigne");
        } catch (IOException ex) {
            Logger.getLogger(MainRespE.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void mainWindowCatalogue() {
        try {
            FXMLLoader loader = new FXMLLoader(MainRespE.class.getResource("/tunisiamall/GUI/FXMLCatalogue.fxml"));
            AnchorPane pane = loader.load();
            //Scene scene = new Scene(pane);
            Stage stage = new Stage();
            pane.setMinSize(600, 400);
            Undecorator undecorator = new Undecorator(stage, pane);
            undecorator.getStylesheets().add("skin/undecorator.css");
            Scene scene = new Scene(undecorator);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);

            FXMLCatalogueController controller = loader.getController();
            controller.setMain(this, stage);

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            stage.setTitle("Espace responsable enseigne");
        } catch (IOException ex) {
            Logger.getLogger(MainRespE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
