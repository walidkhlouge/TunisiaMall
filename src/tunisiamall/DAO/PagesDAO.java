/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tunisiamall.Interfaces.PageIDAO;
import tunisiamall.Technique.DataSource;
import tunisiamall.entities.Pages;


public class PagesDAO implements PageIDAO {
    private Connection connection;


    public PagesDAO() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Pages p) {
        try {
            String req = "insert into pages (numpages,idCatalogue) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, p.getNbPage());
            ps.setInt(2, p.getIdCatalogue());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PagesDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public void update(Pages p, int nbPages) {
        String requete = "update pagesr set numPages? where idCatalogue=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,nbPages);
            ps.setInt(2,p.getIdCatalogue());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public List FindAll() {
        List<Pages> pages = new ArrayList<>();
        String requete = "select * from pages";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Pages p = new Pages();
                p.setNbPage(resultat.getInt(1));
                p.setIdCatalogue(resultat.getInt(2));
                pages.add(p);
            }
            return pages;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Pages " + ex.getMessage());
            return null;
    
    }
    }
    @Override
    public Pages FindByIdCatalogue(int idCatalogue) {
        Pages p = new Pages();
        String requete = "select * from pages where idCatalogue=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idCatalogue);
            ResultSet resultat = ps.executeQuery();
            if (resultat.getRow()==1) {
                p.setNbPage(resultat.getInt(1));
                p.setIdCatalogue(resultat.getInt(2));
            }
            else p=null;
            return p;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du page " + ex.getMessage());
            return null;
        }
    }

    @Override
    public void removeByIdCatalogue(int idCatalogue) {
         String requete = "delete from pages where idCatalogue=? ";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idCatalogue);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    
    
    
    
}
