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
import tunisiamall.Interfaces.BoutiqueIDAO;
import tunisiamall.Technique.DataSource;
import tunisiamall.entities.Boutique;
import tunisiamall.entities.Enseigne;


public class BoutiqueDAO implements BoutiqueIDAO{
    private Connection connection;
  public BoutiqueDAO() {
        connection = DataSource.getInstance().getConnection();
        
    }
  public String findEnseigneByIdBoutique(int idB) {
        
        
        String requete = "select * from boutique where idBoutique=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,idB);
            ResultSet resultat = ps.executeQuery();
            if (resultat.next()) {
               return resultat.getString(3);
            }
            else return null;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherchdesddfse d'enseigne " + ex.getMessage());
            return null;
        }
    }
    public int findIdBoutiqueBylogin(String login){
         String requete = "select * from boutique where loginResponsableBoutique='"+login+"'";
         
        try {
           Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            
             if (resultat.next()){
                 
                 
                 return resultat.getInt(1);
                 
             }
             else 
                 return 99;
        
                 
                 
        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du boutique " + ex.getMessage());
            return 0;
        }     
    }
    @Override
    public void addBoutique(Boutique b, Enseigne e) {

    try {
            String req = "insert into boutique (intitulé,nom) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, b.getIntitule());
            ps.setString(2,e.getNom());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
    } }
      public void updateBoutique(Boutique b,String intitule,String nom , String url) {
        String requete = "update boutique set intitule=?, nomEnseigne=?,url=? where idBoutique=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,intitule);
            ps.setString(2, nom);
            ps.setString(3, url);
            ps.setInt(4, b.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuee avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
 
 
     public void updateBoutique2(Boutique b,String intitule,String nom) {
        String requete = "update boutique set intitule=?, nomEnseigne=?  where idBoutique=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,intitule);
            ps.setString(2, nom);
            ps.setInt(3, b.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuee avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
    public void addBoutique2(Boutique b) {

    try {
             String req = "insert into boutique (intitule,nomEnseigne,loginResponsableBoutique,url) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, b.getIntitule());
            ps.setString(2,b.getNomEnseigne());
            ps.setString(3,b.getLoginResponsableBoutique());
            ps.setString(4,b.getUrl());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
    } }
    
    
      public ArrayList<Boutique> findBoutByIntituleDynamic(String intitule) {
   
        ArrayList<Boutique> paniers = new ArrayList<>();
      

        String requete = "select * from boutique where 	intitulé LIKE '%"+intitule+"%'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery(requete);
            while (resultat.next()) {     
                Boutique p = new Boutique();
                p.setId(resultat.getInt("idBoutique"));
                p.setIntitule(resultat.getString("intitulé"));
                p.setNomEnseigne(resultat.getString("nomEnseigne"));
                p.setLoginResponsableBoutique("loginResponsableBoutique");
                p.setUrl(resultat.getString("url"));
                paniers.add(p);
            }
            return paniers;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Boutiques Dynamiqyes " + ex.getMessage());
            return null;
        }
}
//      public ArrayList<Boutique> findBoutByIntituleEnseigneDynamic(String intitule,String nomEnseigne) {
//   
//        ArrayList<Boutique> paniers = new ArrayList<>();
//      
//
//        String requete = "select * from boutique where nomEnseigne='"+nomEnseigne+"' 	intitulé LIKE '%"+intitule+"%'";
//        try {
//            PreparedStatement ps = connection.prepareStatement(requete);
//            ResultSet resultat = ps.executeQuery(requete);
//            while (resultat.next()) {     
//                Boutique p = new Boutique();
//                p.setId(resultat.getInt("idBoutique"));
//                p.setIntitule("intitulé");
//                p.setNomEnseigne(resultat.getString("nomEnseigne"));
//                p.setLoginResponsableBoutique("loginResponsableBoutique");
//                p.setUrl(resultat.getString("url"));
//                paniers.add(p);
//            }
//            return paniers;
//        } catch (SQLException ex) {
//            System.out.println("erreur lors du chargement des Boutiques Dynamiqyes " + ex.getMessage());
//            return null;
//        }
//}
public void updateBoutiqueResp(Boutique b,String nom) {
        String requete = "update boutique set loginResponsableBoutique=? where idBoutique=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, nom);
            ps.setInt(2, b.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuee avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void updateBoutique(Boutique b,String intitule,String nom) {
        String requete = "update boutique set intitulé=? and nom=? where idBoutique=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,intitule);
            ps.setString(2, nom);
            ps.setInt(3, b.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
    @Override
    public void removeBoutiqueById(int id) {
        String requete = "delete from boutique where idBoutique=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
    @Override
    public ArrayList<Boutique> findAll() {

     
        ArrayList<Boutique> boutiques = new ArrayList<>();
        String requete = "select * from boutique";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Boutique b=new Boutique();
                b.setId(resultat.getInt(1));
                b.setIntitule(resultat.getString(2));
                b.setNomEnseigne(resultat.getString(3));
                b.setLoginResponsableBoutique(resultat.getString(4));
                b.setUrl(resultat.getString(5));
                boutiques.add(b);
            }
            return boutiques;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des boutiques " + ex.getMessage());
            return null;
        }
    }
    public ArrayList<Boutique> findBoutiqueByEnseigne(String nom){
       ArrayList<Boutique> boutiques=new ArrayList<Boutique>();
        String requete = "select * from boutique where nomEnseigne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,nom);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Boutique b=new Boutique();
                b.setId(resultat.getInt(1));
                b.setIntitule(resultat.getString(2));
                b.setNomEnseigne(resultat.getString(3));
                b.setLoginResponsableBoutique(resultat.getString(4));
                b.setUrl(resultat.getString(5));
                boutiques.add(b);
            }
            System.out.println("-------------------"+boutiques);
            return boutiques;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche boutique byEnsiegne " + ex.getMessage());
            return null;
        }
    }
    public ArrayList<Boutique> searchBoutiqueByIntitule(String nom){
       ArrayList<Boutique> boutiques=new ArrayList<Boutique>();
        String requete = "select * from boutique where intitule LIKE '%?%'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,nom);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Boutique b=new Boutique();
                b.setId(resultat.getInt(1));
                b.setIntitule(resultat.getString(2));
                b.setNomEnseigne(resultat.getString(3));
                b.setLoginResponsableBoutique(resultat.getString(4));
                b.setUrl(resultat.getString(5));
                boutiques.add(b);
            }
            System.out.println("-------------------"+boutiques);
            return boutiques;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche boutique byEnsiegne " + ex.getMessage());
            return null;
        }
    }
    
    
    @Override
    public Boutique findProduitById(int id) {
        Boutique b = new Boutique();
        String requete = "select * from boutique where idBoutique=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,id);
            ResultSet resultat = ps.executeQuery();
            if (resultat.getRow()==1) {
                b.setId(resultat.getInt(1));
                b.setIntitule(resultat.getString(2));
                
            }
            else b=null;
            return b;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du produit " + ex.getMessage());
            return null;
        }
    }
     public Boutique findBoutiqueByNom2(String nom) {

        Boutique e = new Boutique();
        String requete = "select * from boutique where intitule=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, nom);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                e.setId(resultat.getInt(1));
                e.setIntitule(resultat.getString(2));
                e.setNomEnseigne(resultat.getString(3));
                e.setLoginResponsableBoutique(resultat.getString(4));
                e.setUrl(resultat.getString(5));
            }
            return e;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche de boutique " + ex.getMessage());
            return null;
        }
    }
    
   public Boutique findByIntitule(String nom) {
       Boutique p = new Boutique();
        String requete = "select * from boutique where intitule=?";
        try {
            
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, nom);
            ResultSet resultat = ps.executeQuery();
            if (resultat.getRow()==1) {
                p.setId(resultat.getInt(1));
                p.setIntitule(resultat.getString(2));
               /* p.setIntitule(resultat.getString(2));
                p.setIntitule(resultat.getString(2));
                p.setIntitule(resultat.getString(2)); */
            }else{
                p=null;
            }
            return p;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des boutiques " + ex.getMessage());
            return null;
        }   
    }
    

    public List<Boutique> findBoutiqueByLoginR(String LoginResponsableLogin) {
        List<Boutique> boutiques = new ArrayList<>();
        String requete = "select * from boutique where loginResponsableBoutique=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, LoginResponsableLogin);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Boutique b = new Boutique();
                b.setId(resultat.getInt(1));
                b.setIntitule(resultat.getString(2));
                b.setNomEnseigne(resultat.getString(3));
                b.setLoginResponsableBoutique(resultat.getString(4));
                b.setUrl(resultat.getString(5));
                boutiques.add(b);
            }
            System.out.println("-------------------" + boutiques);
            return boutiques;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche boutique byLoginR " + ex.getMessage());
            return null;
        }
    }
        public Boutique findProduitByLogin(String login) {
        Boutique b = new Boutique();
        String requete = "select * from boutique where loginResponsableBoutique=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, login);
            ResultSet resultat = ps.executeQuery();
            if (resultat.getRow() == 1) {
                b.setId(resultat.getInt(1));
                b.setIntitule(resultat.getString(2));

            } else {
                b = null;
            }
            return b;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du produit " + ex.getMessage());
            return null;
        }
    }
}


    
   
