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
import tunisiamall.Interfaces.PacksPubIDAO;
import tunisiamall.Technique.DataSource;
import tunisiamall.entities.PackPublicitaire;
import tunisiamall.entities.PacksPub;


public class PacksPubDAO implements PacksPubIDAO {
    private Connection connection;

    public PacksPubDAO() {
        connection = DataSource.getInstance().getConnection();
    }
    @Override
    public void addPacksNad(String nom ,String description, float prix) {
        try {
            String req = "insert into packspub (prix,description,nom) values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setFloat(1, prix);
            ps.setString(2, nom);
            ps.setString(3, description);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PackPublicitaireDAO.class.getName()).log(Level.SEVERE, null, ex);
    
       }
    }
    public void removeById(String id)
    {
        String requete = "delete from packspub where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("delete effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
    public List<PacksPub> findAll()
    {
        List<PacksPub> packs = new ArrayList();
        String req = "Select * from packspub";
         try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(req);

            while (resultat.next()) {
                PacksPub p = new PacksPub();
                p.setId(resultat.getInt(1));
                p.setDescription(resultat.getString(2));
                p.setNom(resultat.getString(3));
                p.setPrix(resultat.getFloat(4));
                   packs.add(p);
            }
            return packs;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des pack publicitaires " + ex.getMessage());
            return null;
        }
    }
    
     public int NombrePack(){
            String requete = "select count(*) from packspub";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                return resultat.getInt(1);
                
            }
            return 0;
            } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return 0;
        }
    }
    public int SommePack(){
            String requete = "select sum(prix) from packspub";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                return resultat.getInt(1);
                
            }
            return 0;
            } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return 0;
        }
    }
}
