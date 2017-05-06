/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tunisiamall.Interfaces.CompteIDAO;
import tunisiamall.Technique.DataSource;
import tunisiamall.entities.BCrypt;
import tunisiamall.entities.Compte;


public class CompteDAO implements CompteIDAO {
    public static String login="bassemlog";
    private Connection connection;

    public CompteDAO() {
        connection = DataSource.getInstance().getConnection();
    }
    public void addCompte(Compte c) {
        try {
            String req = "insert into compte (login,pwd,nom,prenom,adresseMail,type,sexe,DateNaissance,etat,photo) values (?,?,?,?,?,?,?,?,?,null)";
            String salt = BCrypt.gensalt();
            String hashed = BCrypt.hashpw(c.getPassword(), salt);
            String req1 = "INSERT INTO `user`(`username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `locked`, `expired`, `roles`, `credentials_expired`,`nom`, `prenom`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            PreparedStatement ps1 = connection.prepareStatement(req1);
            ps1.setString(1, c.getLogin());
            ps1.setString(2, c.getLogin());
            ps1.setString(3, c.getEmail());
            ps1.setString(4,c.getEmail());
            ps1.setInt(5, 1);
            ps1.setString(6,salt);
            ps1.setString(7, hashed);
            ps1.setInt(8, 0);
            ps1.setInt(9, 0);
            ps1.setString(10, "a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
            ps1.setInt(11, 0);
            ps1.setString(12, c.getNom());
            ps1.setString(13, c.getPrenom());
            ps.setString(1, c.getLogin());
            ps.setString(2, hashed);
            ps.setString(3,c.getNom());
            ps.setString(4,c.getPrenom());
            ps.setString(5,c.getEmail());
            ps.setString(6,c.getType());
            ps.setString(7,c.getSexe());
            ps.setDate(8, (Date) c.getDateNaissance());
            ps.setString(9,c.getEtat());
            ps.executeUpdate();
            ps1.executeUpdate();          
        } catch (SQLException ex) {
            Logger.getLogger(CompteDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    }


   
public Compte chercherCompteLogin(String login) {
       Compte c = new Compte();
        String requete = "select * from compte where login='"+login+"'";
        try {
            
             PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery(requete);
           
            if (resultat.next()) {
                c.setLogin(resultat.getString(1));
                c.setPassword(resultat.getString(2));
                c.setNom(resultat.getString(3));
                c.setPrenom(resultat.getString(4));
                c.setEmail(resultat.getString(5));
                c.setPhoto(resultat.getString(10));
                System.out.println(c);
                return c;
            }
            else 
            return c;
            
        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du compte " + ex.getMessage());
            return null;
        }
    }
   

    @Override
    public void updateCompte(Compte c, String login, String pwd, String nom, String prenom, String mail) {
        String requete = "update compte set login=? and pwd=? and nom=? and prenom=? and adresseMail=? where login=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, login);
            ps.setString(2, pwd);
            ps.setString(3, nom);
            ps.setString(4, prenom);
            ps.setString(5, mail);
            ps.setString(6, login);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

   @Override
    public void updateCompte(String login,String pwd,String nom,String prenom,String mail) {
        String requete = "update compte set pwd=? , nom=? , prenom=? , adresseMail=? where login=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, pwd);
            ps.setString(2,nom);
            ps.setString(3,prenom);
            ps.setString(4,mail);
            ps.setString(5, login);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }       
    }
    @Override
    public List<Compte> findAll() {
        List<Compte> comptes = new ArrayList<>();
        String requete = "select * from compte";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Compte c = new Compte();
                c.setLogin(resultat.getString(1));
                c.setPassword(resultat.getString(2));
                c.setNom(resultat.getString(3));
                c.setPrenom(resultat.getString(4));
                c.setEmail(resultat.getString(5));
                c.setType(resultat.getString(6));
                c.setEtat(resultat.getString(9));
                comptes.add(c);
            }
            return comptes;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des comptes" + ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean seekCompteByLogin(String login) {
        String requete = "select * from compte where login=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, login);
            ResultSet resultat = ps.executeQuery();
            return resultat.first();

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du compte " + ex.getMessage());
            return true;
        }

    }

    @Override
    public boolean seekCompteByEmail(String email) {
        String requete = "select * from compte where adresseMail=?";
        try {

            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, email);
            ResultSet resultat = ps.executeQuery();
            return resultat.first();

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du compte " + ex.getMessage());
            return true;
        }
    }

    @Override
    public Compte findCompteByLogin(String login) {
        Compte c = new Compte();
        String requete = "select * from compte where login=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, login);
            ResultSet resultat = ps.executeQuery();
            if (resultat.first()) {
                c.setLogin(resultat.getString(1));
                c.setPassword(resultat.getString(2));
                c.setNom(resultat.getString(3));
                c.setPrenom(resultat.getString(4));
                c.setEmail(resultat.getString(5));
                c.setType(resultat.getString(6));
                c.setSexe(resultat.getString(7));
                c.setDateNaissance(resultat.getDate(8));
                c.setEtat(resultat.getString(9));
                c.setPhoto(resultat.getString(10));
                return c;
            } else {
                c = null;
            }
            return c;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du compte " + ex.getMessage());
            return null;
        }
    }
    
          public boolean seekCompteByLoginType(String login)
{
    String requete = "select * from compte where login=? AND type='responsableBoutique'";
     try 
       {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, login);
            ResultSet resultat = ps.executeQuery();
        return resultat.first();

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du compte " + ex.getMessage());
            return true;
        }
     
    
}

    @Override
    public boolean seekCompteByAuth(String login, String password) {
        String requete = "select * from compte where login=? AND pwd=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet resultat = ps.executeQuery();
            return resultat.first();

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du compte " + ex.getMessage());
            return false;
        }

    }

    public List<Compte> findAllbyResponsableboutique(String login) {

        List<Compte> comptes = new ArrayList<>();

        String requete = "select * from compte where login=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, login);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Compte c = new Compte();
                c.setLogin(resultat.getString(1));
                c.setNom(resultat.getString(3));
                c.setPrenom(resultat.getString(4));
                c.setEmail(resultat.getString(5));
                System.out.println("afficher c");
                System.out.println(c);
                comptes.add(c);
            }
            System.out.println("-----------------");
            for (int i = 0; i < comptes.size() && i < 6; i++) {
                System.out.println(comptes.get(i));
            }
            return comptes;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche d'enseigne " + ex.getMessage());
            return null;
        }
    }
    public void updateEtat(String etat, String login)
    {
        String requete = "update compte set etat=? where login=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, etat);
            ps.setString(2, login);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        } 
    }
    @Override
    public Compte findCompteByAuth(String login, String password) {
      Compte c = new Compte();
        String requete = "select * from compte where login=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, login);
             
            ResultSet resultat = ps.executeQuery();
                 if(resultat.first()) {
                     System.out.println(password);
                     System.out.println(resultat.getString(2));
            if((BCrypt.checkpw(password, resultat.getString(2)))){
                c.setLogin(resultat.getString(1));
                c.setPassword(resultat.getString(2));
                System.out.println(resultat.getString(2));
                c.setNom(resultat.getString(3));
                c.setPrenom(resultat.getString(4));
                c.setEmail(resultat.getString(5));
                c.setType(resultat.getString(6));
                c.setSexe(resultat.getString(7));
                c.setDateNaissance(resultat.getDate(8));
                c.setEtat(resultat.getString(9));
                c.setPhoto(resultat.getString(10));
            }
            else
                c=null;
                 }
                 else
                     c=null;
            return c;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du compte " + ex.getMessage());
            return null;
        }
    }
            
  
   
    


    @Override
    public void updateComptePwd(String login, String password) {
        String requete = "update compte set pwd=? where login=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, password);
            ps.setString(2, login);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        } 
    }

    @Override
    public void updateCompteSexe(String login, String sexe) {
        String requete = "update compte set Sexe=? where login=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, sexe);
            ps.setString(2, login);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void updateCompteNom(String login, String nom) {
        String requete = "update compte set nom=? where login=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, nom);
            ps.setString(2, login);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void updateComptePrenom(String login, String prenom) {
String requete = "update compte set prenom=? where login=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, prenom);
            ps.setString(2, login);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void updateCompteDateNaissance(String login, java.sql.Date dateNaissance) {
        String requete = "update compte set DateNaissance=? where login=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setDate(1,(Date) dateNaissance);
            ps.setString(2, login);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void updateCompteEmail(String login, String email) {
String requete = "update compte set adresseMail=? where login=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, email);
            ps.setString(2, login);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }    }

    @Override
    public void removeCompteByLogin(String login) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
public int NombreVisite(int m,int day){
       
         String requete="select count(*)from user where EXTRACT(MONTH FROM last_login)="+m+" and EXTRACT(DAY FROM last_login)="+day+"  Group By EXTRACT(MONTH FROM last_login)";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                
                return (resultat.getInt(1));
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des comptes" + ex.getMessage());
            return 0;
        }
     
     
     }          
     public int NombreActeur(String acteur){
         
    String requete = "select Count(*) as nb from compte where type=?";
     try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, acteur);
            ResultSet resultat = ps.executeQuery();
            if (resultat.next())
            return resultat.getInt(1);
            else return 0;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du nombre des comptes" + ex.getMessage());
            return 0;
        }}
}
