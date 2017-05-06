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
import tunisiamall.Interfaces.EmplacementPubliciteIDAO;
import tunisiamall.Technique.DataSource;
import tunisiamall.entities.EmplacementPublicite;


public class EmplacementPubliciteDAO implements EmplacementPubliciteIDAO {
     private Connection connection;

    public EmplacementPubliciteDAO() {
        connection = DataSource.getInstance().getConnection();
    }
    
        
    @Override
    public void add(EmplacementPublicite e) {
        try {
            String req = "insert into emplacementPublicitaire (idEmplacementPublicitaire,idPackPublicitaire) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.setInt(2, e.getIdPack());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(EmplacementPubliciteDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    @Override
    public void update(EmplacementPublicite e, int idPack) {
        String requete = "update emplacementPublicitaire set idPackPublicitaire=? where idEmplacementPublicitaire=?";//nbaddel el pack fil emplacement héka
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idPack);
            ps.setInt(2, e.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }       
    }
    
    
    @Override
    public List<EmplacementPublicite> findAll() {
        List<EmplacementPublicite> places = new ArrayList<>();
        String requete = "select * from emplacementPublicitaire";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                EmplacementPublicite e= new EmplacementPublicite();
                e.setId(resultat.getInt(1));
                e.setIdPack(resultat.getInt(2));
                places.add(e);
            }
            return places;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des emplacements " + ex.getMessage());
            return null;
        }
    }

    @Override
    public EmplacementPublicite findById(int id) {
        String requete = "select * from emplacementPublicitaire where idEmplacementPublicitaire=? ";
        try {
            
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery(requete);
            EmplacementPublicite e = new EmplacementPublicite();
            if (resultat.getRow()==1) {
                e.setId(id);
                e.setIdPack(resultat.getInt(2));
            }
            else e=null;
            return e;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des emplacements publicitaires " + ex.getMessage());
            return null;
        }   
    }

    @Override
    public void removeById(int id) {
        String requete = "delete from emplacementPublicitaire where idEmplacementPublicitaire=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
        }    
    
}
