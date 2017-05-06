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
import tunisiamall.Interfaces.CarteFideliteIDAO;
import tunisiamall.Technique.DataSource;
import tunisiamall.entities.CarteFidelite;
import tunisiamall.entities.Compte;
import tunisiamall.entities.Enseigne;


public class CarteFideliteDAO implements CarteFideliteIDAO {

    private Connection connection;

    public CarteFideliteDAO() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(CarteFidelite c, Enseigne e, Compte cl) {
        try {
            String req = "insert into carte_fidelite (nomEnseigne,login,pointFidelite) values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, e.getNom());
            ps.setString(2, cl.getLogin());
            ps.setInt(13, c.getPoints());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CarteFideliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(CarteFidelite c, int pts, String enseigne, String login) {
        String requete = "update carte_fidelte set pointFidelite=? where idCarteFidelite=? and nomEnseigne=? and loginClient=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, pts);
            ps.setInt(2, c.getId());
            ps.setString(3, enseigne);
            ps.setString(4, login);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    public void updateTauxPts(int c, String enseigne, int pts, float taux) {
        String requete = "update carte_fidelite set pointFideleFixe=? , reductionFixe=? where idCarteFidelite=? and nomEnseigne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, pts);
            ps.setFloat(2, taux);
            ps.setInt(3, c);
            ps.setString(4, enseigne);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    public int seekPoints(CarteFidelite c, String enseigne, String login) {
        String requete = "select  pointFidelite from carte_fidelite where idCarteFidelite=? and nomEnseigne=? and loginClient=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery(requete);
            ps.setInt(1, c.getId());
            ps.setString(2, enseigne);
            ps.setString(3, login);
            int r = 0;
            if (resultat.getRow() == 1) {
                r = resultat.getInt(4);
            }
            return r;
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
            return 0;
        }
    }

    @Override
    public List<CarteFidelite> findAll() {
        List<CarteFidelite> cartes = new ArrayList<>();
        String requete = "select * from carte_fidelite";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                CarteFidelite c = new CarteFidelite();
                c.setId(resultat.getInt(1));
                c.setPoints(resultat.getInt(4));
                cartes.add(c);
            }
            return cartes;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Catlaogues " + ex.getMessage());
            return null;
        }
    }

    @Override
    public CarteFidelite findById(int id) {
        String requete = "select * from carte_fidelite where idCarteFidelite=?";
        try {

            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery(requete);
            CarteFidelite c = new CarteFidelite();
            if (resultat.getRow() == 1) {
                c.setId(resultat.getInt(1));
                c.setNomEnseigne(resultat.getString(2));
                c.setLogin(resultat.getString(3));
                //c.setIdClient(resultat.getInt(3));
                c.setPoints(resultat.getInt(4));
            } else {
                c = null;
            }
            return c;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des cartes " + ex.getMessage());
            return null;
        }
    }
       public CarteFidelite findCarteByI(int id) {

        CarteFidelite e = new CarteFidelite();
        String requete = "select * from carte_fidelite where idCarteFidelite=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                e.setId(resultat.getInt(1));
                e.setPoints(resultat.getInt(4));
            }
            return e;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche d'enseigne " + ex.getMessage());
            return null;
        }
    }

    public CarteFidelite findCarteBylogin(String login ,String enseigne) {

        CarteFidelite e = new CarteFidelite();
        String requete = "select * from carte_fidelite where loginClient=? and nomEnseigne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, login);
            ps.setString(2, login);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                e.setId(resultat.getInt(1));
                e.setNomEnseigne(resultat.getString(2));
                e.setLogin(resultat.getString(3));
                e.setPoints(resultat.getInt(4));
            }
            return e;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche d'enseigne " + ex.getMessage());
            return null;
        }
    }
    @Override
    public void removeById(int id) {
        String requete = "delete from carte_fidelite where idCarteFidelite=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public void update(CarteFidelite c, int p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
