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
import tunisiamall.Interfaces.CatalogueIDAO;
import tunisiamall.Technique.DataSource;
import tunisiamall.entities.Catalogue;


public class CatalogueDAO implements CatalogueIDAO{
    private Connection connection;

    public CatalogueDAO() {
        connection = DataSource.getInstance().getConnection();
    }
    
        
    @Override
    public void add(Catalogue c) {
        try {
            String req = "insert into catalogue (type) values (?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, c.getType());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CatalogueDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    @Override
    public void update(Catalogue c,String type) {
        String requete = "update catalogue set type=? where idCatalogue=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, type);
            ps.setInt(2, c.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }       
    }
    
    
    @Override
    public List<Catalogue> findAll() {
        List<Catalogue> catalogues = new ArrayList<>();
        String requete = "select * from catalogue";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Catalogue c = new Catalogue();
                c.setId(resultat.getInt(1));
                c.setType(resultat.getString(2));
                c.setIntitule(resultat.getString(3));
                catalogues.add(c);
            }
            return catalogues;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Catlaogues " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Catalogue findById(int id) {
String requete = "select * from catalogue where idCatalogue=? ";
        try {
            
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery(requete);
                Catalogue c = new Catalogue();
            if (resultat.getRow()==1) {
                c.setType(resultat.getString(2));
            }
            else c=null;
            return c;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des catalogues " + ex.getMessage());
            return null;
        }   
    }

    @Override
    public List findbyType(String type) {
        List<Catalogue> catalogues = new ArrayList<>();
        String requete = "select * from catalogue where type=? ";
        try {
            
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, type);
            ResultSet resultat = ps.executeQuery(requete);
        
            while (resultat.next()) {
                Catalogue c = new Catalogue();
                c.setId(resultat.getInt(1));
                c.setType(resultat.getString(2));
                catalogues.add(c);
            }
            return catalogues;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des catalogues " + ex.getMessage());
            return null;
        }   
        
    }
    
    
     public int findByE(String nom) {
        int i = 0 ;
        String requete = "select idCatalogue  from catalogue where enseigne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, nom);
            ResultSet resultat = ps.executeQuery();
            if (resultat.getRow()==1) {
                i=resultat.getInt(1);
               
            }
           
            return i;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du produit " + ex.getMessage());
            return 0;
        }
     }

    @Override
    public void removeById(int id) {
        String requete = "delete from catalogue where idCatalogue=?";
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
