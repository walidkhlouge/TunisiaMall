/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tunisiamall.Technique.DataSource;
import tunisiamall.entities.CatProd;


public class CatProdDAO {
    
    private Connection connection;

    public CatProdDAO() {
        connection = DataSource.getInstance().getConnection();

    }
     public void add(CatProd cp) {

    try {
             String req = "insert into prodcat (idProduit,idCatalogue) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,cp.getIdP());
            ps.setInt(2,cp.getIdC());
          
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
    } }
    
}
