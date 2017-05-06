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
import tunisiamall.Interfaces.CommandeIDAO;
import tunisiamall.Technique.DataSource;
import tunisiamall.entities.Commande;


public class CommandeDAO implements CommandeIDAO{
private Connection connection;
    public CommandeDAO() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Commande c) {
        try {
            String req = "insert into Commande (loginClient,idProduit,adresseLivraison,quantite,taille,timeLivraison) values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, c.getLogin());
            ps.setInt(2, c.getIdProduit());
            ps.setString(3,c.getAdresseLivraison());
            ps.setInt(4, c.getQuantite());
            ps.setString(5, c.getTaille());
            ps.setDate(6, (Date) c.getTimeLivraison());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CommandeDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public void update(Commande c, String adresseLivraison) {
String requete = "update commande set adresseLivraison=? where idCommande=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, adresseLivraison);
            ps.setInt(2, c.getIdCommande());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }          }

    @Override
    public List<Commande> findAll() {
        List<Commande> commandes = new ArrayList<>();
        String requete = "select * from Commande";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Commande c = new Commande();
                c.setIdCommande(resultat.getInt(1));
                c.setLogin(resultat.getString(2));
                c.setIdProduit(resultat.getInt(3));
                c.setAdresseLivraison(resultat.getString(4));
                commandes.add(c);
            }
            return commandes;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des comptes" + ex.getMessage());
            return null;
        }
    }

    @Override
    public Commande findCommandeById(int id) {
        Commande c = new Commande();
        String requete = "select * from commande where idCommande=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            if (resultat.getRow()==1) {
                c.setIdCommande(resultat.getInt(1));
                c.setLogin(resultat.getString(2));
                c.setIdProduit(resultat.getInt(3));
                c.setAdresseLivraison(resultat.getString(4));
            }
            else c=null;
            return c;
    } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du compte " + ex.getMessage());
            return null;
        }
    }

    @Override
    public void removeById(int id) {
        String requete = "delete from commande where idCommande=?";
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
    public ArrayList<Commande> findAllByDateTime(String login)
    {


        ArrayList<Commande> commandes = new ArrayList<>();
        String requete = "Select * from Commande Where loginClient=? order by timeLivraison ";
        try {
           PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, login);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Commande c = new Commande();
                c.setIdCommande(resultat.getInt(1));
                c.setLogin(resultat.getString(2));
                c.setIdProduit(resultat.getInt(3));
                c.setAdresseLivraison(resultat.getString(4));
                c.setQuantite(resultat.getInt(5));
                c.setTaille(resultat.getString(6));
                c.setTimeLivraison(resultat.getDate(7));
                commandes.add(c);
            }
                   return commandes;
        }
        catch (SQLException ex) {
            System.out.println("erreur lors de " + ex.getMessage());
            return null;
        }
     
    }
    
    
}
