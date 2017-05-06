
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
import tunisiamall.Interfaces.EnseigneIDAO;
import tunisiamall.Technique.DataSource;
import tunisiamall.entities.Enseigne;


public class EnseigneDAO implements EnseigneIDAO{
    private Connection connection;
    
    public EnseigneDAO(){
    connection = DataSource.getInstance().getConnection();
    }
   
    @Override
    public void addEnseigne(Enseigne e) {

    try {
            String req = "insert into enseigne (nom,idResponsbleEnseigne,adresse) values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, e.getNom());
            ps.setString(2, e.getLoginResponsableEnseigne());
            ps.setString(3, e.getAdresse());
           
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public int NombreEnseigne(){
        String requete = "select count(*) from enseigne";
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
     
     public void addEnseigne2(Enseigne e) {

        try {
            String req = "insert into enseigne (nomEnseigne,adresse,url,loginResponsableEnseigne) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, e.getNom());
            ps.setString(2, e.getAdresse());
            ps.setString(3, e.getUrl());
            ps.setString(4, e.getLoginResponsableEnseigne());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void updateEnseignePack(String nom,int id) {

        String requete = "update enseigne set idPack=? where loginResponsableEnseigne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.setString(2, nom);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
  public void updateEnseigne2(Enseigne e, String nom, String adresse, String url) {

        String requete = "update enseigne set adresse=? , url=? where nomEnseigne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, adresse);
            ps.setString(2, url);
            ps.setString(3, e.getNom());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void updateEnseigne(Enseigne e, String nom, String adresse) {
        
        String requete = "update enseigne set nom=? and adresse=? where nom=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,nom);
            ps.setString(2,adresse);
            ps.setString(3,e.getNom());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }}

    @Override
    public void removeEnseigneByNom(String nom) {

     String requete = "delete from enseigne where nom=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,nom);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<Enseigne> findAll() {

      List<Enseigne> enseignes = new ArrayList<>();
        String requete = "select * from enseigne";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Enseigne e = new Enseigne();
                e.setNom(resultat.getString(1));
                e.setLoginResponsableEnseigne(resultat.getString(2));
                e.setAdresse(resultat.getString(3));
                e.setUrl(resultat.getString(4));
                enseignes.add(e);
               
            }
            return enseignes;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    
    }
     @Override
    public List<Enseigne> searchByNom(String nom) {

      List<Enseigne> enseignes = new ArrayList<>();
        String requete = "select * from enseigne WHERE nomEnseigne LIKE '%?%'";

        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,nom);
            ResultSet resultat = ps.executeQuery(requete);

            while (resultat.next()) {
                Enseigne e = new Enseigne();
                e.setNom(resultat.getString(1));
                e.setLoginResponsableEnseigne(resultat.getString(2));
                e.setAdresse(resultat.getString(3));
                e.setUrl(resultat.getString(4));
                enseignes.add(e);
                System.err.println(e);
            }
            return enseignes;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    
    }
    
    
        public ArrayList<Enseigne> findEnseigneBylogDynamic(String login) {
   
        ArrayList<Enseigne> paniers = new ArrayList<>();
        String requete = "select * from enseigne where 	nomEnseigne LIKE '%"+login+"%'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery(requete);
            while (resultat.next()) {     
                Enseigne p = new Enseigne();
                p.setNom(resultat.getString("nomEnseigne"));
                p.setLoginResponsableEnseigne("loginResponsableEnseigne");
                p.setAdresse(resultat.getString("adresse"));
                p.setUrl(resultat.getString("url"));
                paniers.add(p);
            }
            return paniers;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Paniers " + ex.getMessage());
            return null;
        }
}
    
    
    

    @Override
    public Enseigne findEnseigneByNom(String nom) {
        
        Enseigne e= new Enseigne();
        String requete = "select * from enseigne where nom='"+nom+"'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
          
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                e.setNom(resultat.getString(1));
                e.setLoginResponsableEnseigne(resultat.getString(2));
                e.setAdresse(resultat.getString(3));
                e.setUrl(resultat.getString(4));
            }
            return e;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche d'enseigne " + ex.getMessage());
            return null;
        }
    }

    
   

    

       public Enseigne findEnseigneByNom2(String adresse) {

        Enseigne e = new Enseigne();
        String requete = "select * from enseigne where adressse=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, adresse);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                e.setNom(resultat.getString(1));
                e.setLoginResponsableEnseigne(resultat.getString(2));
                e.setAdresse(resultat.getString(3));
                e.setUrl(resultat.getString(4));
            }
            return e;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche d'enseigne " + ex.getMessage());
            return null;
        }
    }

    public boolean seekEnseignebyNom(String enseigne) {

        String requete = "select * from enseigne where nomEnseigne=?";
        try {

            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, enseigne);
            ResultSet resultat = ps.executeQuery();
            return resultat.first();

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche de l'enseigne " + ex.getMessage());
            return true;
        }
    }

    public List<Enseigne> findAllbyResponsableEns(String login) {

        List<Enseigne> enseignes = new ArrayList<>();

        String requete = "select * from enseigne where loginResponsableEnseigne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, login);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Enseigne e = new Enseigne();
                e.setNom(resultat.getString(1));
                e.setLoginResponsableEnseigne(resultat.getString(2));
                e.setAdresse(resultat.getString(3));
                e.setUrl(resultat.getString(4));
                System.out.println("afficher e");
                System.out.println(e);
                enseignes.add(e);
            }
            System.out.println("-----------------");
            for (int i = 0; i < enseignes.size() && i < 6; i++) {
                System.out.println(enseignes.get(i));
            }
            return enseignes;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche d'enseigne " + ex.getMessage());
            return null;
        }
    }

    }
    
     
