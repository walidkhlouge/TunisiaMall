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
import tunisiamall.Interfaces.PanierIDAO;
import tunisiamall.Technique.DataSource;
import tunisiamall.entities.Panier;


public class PanierDAO implements PanierIDAO {
private Connection connection;

    
    public PanierDAO() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Panier c) {

    try {
            String req = "insert into panier (loginClient,idProduit,nombre,taille) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, c.getLogin());
            ps.setInt(2, c.getIdProduit());
            ps.setInt(3,c.getNombre());
            ps.setString(4, c.getTaille());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PanierDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public int compter() {

    
            
         String requete = "SELECT COUNT(*) AS total FROM panier";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            resultat.next();
             
            return resultat.getInt("total");
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Paniers " + ex.getMessage());
            
        }
        return 0;
    }

    @Override
    public void update(Panier c, int nombre, int idProduit) {
        String requete = "update panier set idProduit=? and  nombre=? where login=?";//parce que logiquement on va pas changer le proprietaire d'un panier
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,idProduit);
            ps.setInt(2,nombre);
            ps.setString(3,c.getLogin());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
    

    @Override
    public List<Panier> findAll() {
        List<Panier> paniers = new ArrayList<>();
        String requete = "select * from panier";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Panier p = new Panier();
                p.setLogin(resultat.getString(1));
                p.setIdProduit(resultat.getInt(2));
                p.setNombre(resultat.getInt(3));
                paniers.add(p);
            }
            return paniers;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Paniers " + ex.getMessage());
            return null;
        }
}

    public ArrayList<Panier> findPanierClient(String login) {
   
        ArrayList<Panier> paniers = new ArrayList<>();
        String requete = "select * from panier where loginClient='"+login+"'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery(requete);
            while (resultat.next()) {     
                Panier p = new Panier();
                p.setLogin(resultat.getString(1));
                p.setIdProduit(resultat.getInt(2));
                p.setNombre(resultat.getInt(3));
                p.setTaille(resultat.getString(4));
                paniers.add(p);
            }
            return paniers;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Paniers " + ex.getMessage());
            return null;
        }
}
    public boolean findPanierClientProduit(String login,int idProduit,String taille) {
   
        String requete = "select * from panier where loginClient='"+login+"' and idProduit='"+idProduit+"' and taille='"+taille+"'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery(requete);
            if(resultat.next())      
                return true;
                return false;
          
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Paniers " + ex.getMessage());
            return false;
        }
}
      @Override
    public ArrayList<Panier> findByLogin(String login) {
        ArrayList<Panier> paniers = new ArrayList();
 Panier p = new Panier();
        String requete = "select * from panier where loginClient=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, login);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                p.setLogin(resultat.getString(1));
                p.setIdProduit(resultat.getInt(2));
                p.setNombre(resultat.getInt(3));
                paniers.add(p);
            }
            return paniers;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du produit " + ex.getMessage());
            return null;
        }
    }
 @Override
    public void removeByLoginProduit(String login) {
        String requete = "delete from panier where loginClient=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, login);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
    
    public void removeByLoginTailleProduit(String login, int idProduit,String taille) {
        String requete = "delete from panier where loginClient=? and idProduit=? and taille=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, login);
            ps.setInt(2, idProduit);
            ps.setString(3,taille);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
    
    @Override
    public void removeByLoginProduit(String login, int idProduit) {
        String requete = "delete from panier where loginClient=? and idProduit=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, login);
            ps.setInt(2, idProduit);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public int findQuantiteProdByTaille(int idproduit, String taille) {
         String requete = "select * from produittaille where  idProduit='"+idproduit+"' and taille='"+taille+"'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery(requete);
             resultat.next();
                return resultat.getInt("quantite");
        
          
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Paniers " + ex.getMessage());
            return -1;
        }
        
        
        
    }
    


    
    
}
