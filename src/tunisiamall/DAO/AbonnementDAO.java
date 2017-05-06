/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.DAO;
import com.mysql.jdbc.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tunisiamall.Interfaces.AbonnementIDAO;
import tunisiamall.Technique.DataSource;
import tunisiamall.entities.Abonnement;


public class AbonnementDAO implements AbonnementIDAO{

private Connection connection;
    public AbonnementDAO() {
        connection= (Connection) DataSource.getInstance().getConnection();
    }
    
    @Override
    public void addAbonnement(String login, String nomEnseigne) {
        Abonnement a = new Abonnement(login, nomEnseigne, new java.util.Date());
           try {
               
           // String req = "insert into abonnement (login,nomEnseigne,dateAbonnement) values (?,?,?)";
            String req = "insert into abonnement (login,nomEnseigne) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, a.getLogin());
            ps.setString(2, a.getNomEnseigne());
           // ps.setDate(3, (Date) a.getDateAbonnement());
           
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public boolean findAbonnement(String login,String nomEnseigne) throws SQLException {
        Abonnement p = new Abonnement();
     
        String requete = "select * from abonnement where login='"+login+"' and nomEnseigne='"+nomEnseigne+"'";

           PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery(requete);
            if (resultat.next()) 
                return true;
           
            return false;
        }
           
    

    @Override
    public void suppAbonnementByPK(String login, String nomEnseigne) {
            try {
               
            String req = "DELETE from abonnement WHERE login =? AND nomEnseigne = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, login);
            ps.setString(2, nomEnseigne);
           
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
