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
import tunisiamall.Interfaces.PackPublicitaireIDAO;
import tunisiamall.Technique.DataSource;
import tunisiamall.entities.PackPublicitaire;

public class PackPublicitaireDAO implements PackPublicitaireIDAO {
    private Connection connection;

    public PackPublicitaireDAO() {
        connection = DataSource.getInstance().getConnection();
    }
    
         public List<PackPublicitaire> findAll2() {
        List<PackPublicitaire> packs = new ArrayList<>();
        String requete = "select * from packspub";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                PackPublicitaire p= new PackPublicitaire();
                p.setId(resultat.getInt(1));
                p.setNom(resultat.getString(3));
                p.setPrix(resultat.getFloat(4));
                p.setDesc(resultat.getString(2));
                packs.add(p);
            }
            return packs;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des pack publicitaires " + ex.getMessage());
            return null;
        }
    }
    
    
    
    
    @Override
    public void add(PackPublicitaire p) {
        try {
            String req = "insert into packPublicitaire (prix,) values (?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setFloat(1, p.getPrix());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PackPublicitaireDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    @Override
    public void update(PackPublicitaire p,float prix) {
        String requete = "update packPublicitaire set prix=? where idPackPublicitaire=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setFloat(1, prix);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }       
    }
    
    
    @Override
    public List<PackPublicitaire> findAll() {
        List<PackPublicitaire> packs = new ArrayList<>();
        String requete = "select * from packPublicitaire";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                PackPublicitaire p= new PackPublicitaire();
                p.setId(resultat.getInt(1));
                p.setPrix(resultat.getFloat(2));
                packs.add(p);
            }
            return packs;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des pack publicitaires " + ex.getMessage());
            return null;
        }
    }

    @Override
    public PackPublicitaire findById(int id) {
        String requete = "select * from packPublicitaire where idPackPublicitaire=? ";
        try {
            
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery(requete);
            PackPublicitaire p = new PackPublicitaire();
            if (resultat.getRow()==1) {
                p.setPrix(resultat.getFloat(2));
            }
            p=null;
            return p;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des pack publicitaires " + ex.getMessage());
            return null;
        }   
    }

    @Override
    public void removeById(int id) {
        String requete = "delete from packPublicitaire where idPackPublicitaire=?";
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
