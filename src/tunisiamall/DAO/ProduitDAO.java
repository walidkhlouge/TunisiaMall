
package tunisiamall.DAO;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tunisiamall.Interfaces.ProduitIDAO;
import tunisiamall.Technique.DataSource;
import tunisiamall.entities.Boutique;
import tunisiamall.entities.Panier;
import tunisiamall.entities.Produit;


public class ProduitDAO implements ProduitIDAO {
    private Connection connection;
    
    public ProduitDAO() {
        connection = DataSource.getInstance().getConnection();
    }
 public void addProduitP(Produit p) {
        try {
            String req = "insert into produit (nomEnseigne,nom,info,url,ptsfidelite)values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);

            ps.setString(2, p.getNom());

            ps.setString(1, p.getNonEnseigne());

            ps.setString(3, p.getInfo());
            ps.setString(4, p.getUrl());
            ps.setInt(5, p.getPtsfidelite());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public List<Produit> findAllE(String nomE) {
        List<Produit> produits = new ArrayList<>();
        String requete = "select * from produit where nomEnseigne=?";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Produit p = new Produit();
                p.setId(resultat.getInt(1));
                p.setNonEnseigne(resultat.getString(2));
                p.setNom(resultat.getString(4));
                p.setInfo(resultat.getString(9));
                p.setUrl(resultat.getString(10));
                p.setTauxReduction(resultat.getFloat(7));
                p.setPtsfidelite(resultat.getInt(11));

                produits.add(p);
            }
            return produits;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }
    public void toPdf() throws DocumentException {
        String req = "select * from produit;";
        String filename = "C:\\Users\\ACER\\Desktop\\Produits.pdf";
        Document document = new Document();
        PdfPTable table = new PdfPTable(4);
        PdfPCell c1 = new PdfPCell(new Phrase("idProduit"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBorderWidth(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("nomEnseigne"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBorderWidth(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("nom"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBorderWidth(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("info"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);

        c1.setBorderWidth(2);
        table.addCell(c1);

        table.setHeaderRows(1);
        /*table.setBorderColor(BaseColor.GRAY);
         table.setPadding(4);
         table.setSpacing(4);
         table.setBorderWidth(1);*/

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            document.open();
            document.add(new Paragraph("La liste des produits disponibles"));
            document.add(new Paragraph("    "));
            while (rs.next()) {
                table.addCell(String.valueOf(rs.getInt("idProduit")));
                table.addCell(rs.getString("nomEnseigne"));
                table.addCell(rs.getString("nom"));
                //table.addCell(String.valueOf(rs.getInt("validité")));
                table.addCell(rs.getString("info"));
            }
            System.out.println("Génération PDF ");
            document.add(table);
            document.close();
        } catch (FileNotFoundException | SQLException ex) {
            System.out.println("Erreur lors de la génération du PDF. " + ex.getMessage());
        } catch (DocumentException ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Produit> findProduitByEnseigne(String nom) {
        ArrayList<Produit> produits = new ArrayList<Produit>();
        String requete = "select * from produit where nomEnseigne=?";
        try {

            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, nom);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Produit p = new Produit();
                p.setId(resultat.getInt(1));
                p.setNonEnseigne(resultat.getString(2));
                p.setNom(resultat.getString(4));
                p.setInfo(resultat.getString(9));
               
                p.setUrl(resultat.getString(10));
                produits.add(p);
            }
            System.out.println("-------------------" + produits);
            return produits;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche boutique byEnsiegne " + ex.getMessage());
            return null;
        }
    }
     public void updateProduitE(Produit p, String nom, String nonE, String info, String url, int pts) {
        String requete = "update produit set nom=?,nomEnseigne=? ,info=? , url=?, ptsfidelite=? where idProduit=?";//don't forget the set of update
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, nom);
            ps.setString(2, nonE);
            ps.setString(3, info);
            ps.setString(4, url);
            ps.setInt(5, pts);
            ps.setInt(6, p.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

   

   
    @Override
    public void addProduit(Produit p) {
        try {
            String req = "insert into produit (nom,quantite,prixVenteUnitaire,tauxReduction) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setInt(2, p.getQuantite());
            ps.setFloat(3,p.getPrixUnitaire());
            ps.setFloat(4,p.getTauxReduction());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public ArrayList<Produit> findProdByIntituleDynamic(String nom) {
   
        ArrayList<Produit> paniers = new ArrayList<>();
      

        String requete = "select * from produit where 	nom LIKE '%"+nom+"%' and idBoutique is not null";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery(requete);
            while (resultat.next()) {     
               Produit b = new Produit();
                   b.setId(resultat.getInt(1));
                b.setNonEnseigne(resultat.getString(2));
                b.setIdBoutique(resultat.getInt(3));
                b.setNom(resultat.getString(4));
                b.setQuantite(resultat.getInt(5));
                b.setPrixUnitaire(resultat.getFloat(6));
                b.setTauxReduction(resultat.getFloat(7));
                b.setReference(resultat.getString(8));
                b.setInfo(resultat.getString(9));
                b.setUrl(resultat.getString(10));
                paniers.add(b);
            }
            return paniers;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Boutiques Dynamiqyes " + ex.getMessage());
            return null;
        }
}
    
    public ArrayList<Produit> findAllB() {
        ArrayList<Produit> produits = new ArrayList<>();
        String requete = "select * from produit where idBoutique is not null";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
              
              
                            Produit b=new Produit();
                b.setId(resultat.getInt(1));
                b.setNonEnseigne(resultat.getString(2));
                b.setIdBoutique(resultat.getInt(3));
                b.setNom(resultat.getString(4));
                b.setQuantite(resultat.getInt(5));
                b.setPrixUnitaire(resultat.getFloat(6));
                b.setTauxReduction(resultat.getFloat(7));
                b.setReference(resultat.getString(8));
                b.setInfo(resultat.getString(9));
                b.setUrl(resultat.getString(10));
                produits.add(b);
    
                
            }
            return produits;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Produit findProduitByNom(String nom) {
        Produit p = new Produit();
        String requete = "select * from produit where nom=? and idBoutique is not null";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, nom);
            ResultSet resultat = ps.executeQuery();
            if (resultat.getRow()==1) {
                p.setNom(resultat.getString(1));
                p.setQuantite(resultat.getInt(2));
                p.setPrixUnitaire(resultat.getFloat(3));
                p.setTauxReduction(resultat.getFloat(4));
            }
            else p=null;
            return p;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du produit " + ex.getMessage());
            return null;
        }
    }

    
    @Override
    public ArrayList<Produit> searchProduitByNom(String nom) {
        String requete = "Select from produit where nom LIKE '%?%' and idBoutique is not null";
        ArrayList<Produit> produits = new ArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, nom);
              ResultSet resultat = ps.executeQuery(requete);

            while (resultat.next()) {
                Produit p = new Produit();
                p.setId(resultat.getInt(1));
                p.setNonEnseigne(resultat.getString(2));
                p.setIdBoutique(resultat.getInt(3));
                p.setNom(resultat.getString(4));
                p.setQuantite(resultat.getInt(5));
                p.setPrixUnitaire(resultat.getFloat(6));
                p.setTauxReduction(resultat.getFloat(7));
                p.setReference(resultat.getString(8));
                p.setInfo(resultat.getString(9));
                p.setUrl(resultat.getString(10));
                                
                produits.add(p);
            }
            return produits;
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
            return null;
        }
    }

    @Override
    public void updateProduit(Produit p,String nom,int quantite,float prixUnitaire,float prixGros,float tauxReduction) {
        String requete = "update produit set nom=? and quantite=? and  prixVenteUnitaire=? and tauxProduit=? where id=?";//don't forget the set of update
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,nom);
            ps.setInt(2,quantite);
            ps.setFloat(3,prixUnitaire);
            ps.setFloat(4,tauxReduction);
            ps.setInt(5, p.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
    
    public ArrayList<Produit> findProduitByBoutique(int id){
       ArrayList<Produit> produits=new ArrayList<Produit>();
        String requete = "select * from  produit where idBoutique=? and idBoutique is not null";
        System.out.println("requete final"+requete);
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Produit b=new Produit();
                b.setId(resultat.getInt(1));
                b.setNonEnseigne(resultat.getString(2));
                b.setIdBoutique(resultat.getInt(3));
                b.setNom(resultat.getString(4));
                b.setQuantite(resultat.getInt(5));
                b.setPrixUnitaire(resultat.getFloat(6));
                b.setTauxReduction(resultat.getFloat(7));
                b.setReference(resultat.getString(8));
                b.setInfo(resultat.getString(9));
                b.setUrl(resultat.getString(10));
                produits.add(b);
            }
    System.out.println("produits final"+produits);
            return produits;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche boutique byEnsiegne " + ex.getMessage());
            return null;
        }
    }
    
  @Override
    public void removeProduitByNom(String nom) {
        String requete = "delete from produit where nom=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, nom);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
    
    public Produit findProduitByIdBass(int id) {
        
         
       System.out.println(id);

        String requete = "select * from produit where 	idProduit ='"+id+"' and idBoutique is not null";
        System.out.println(requete);
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery(requete);
                Produit b = new Produit();
           if(resultat.next()) {     
           
                   b.setId(resultat.getInt(1));
                b.setNonEnseigne(resultat.getString(2));
                b.setIdBoutique(resultat.getInt(3));
                b.setNom(resultat.getString(4));
                b.setQuantite(resultat.getInt(5));
                b.setPrixUnitaire(resultat.getFloat(6));
                b.setTauxReduction(resultat.getFloat(7));
                b.setReference(resultat.getString(8));
                b.setInfo(resultat.getString(9));
                b.setUrl(resultat.getString(10));
                 System.out.println(b);
              
            }
           
            return b;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Boutiques Dynamiqyes " + ex.getMessage());
            return null;
        }
    }
   
    
    
     public ArrayList<Produit> findProduitBoutique(int id) {
        ArrayList<Produit> prods=new ArrayList<Produit>();
         
       

        String requete = "select * from produit where 	idBoutique ='"+id+"' ";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery(requete);
                
          while(resultat.next()) {     
           Produit b = new Produit();
                   b.setId(resultat.getInt(1));
                b.setNonEnseigne(resultat.getString(2));
                b.setIdBoutique(resultat.getInt(3));
                b.setNom(resultat.getString(4));
                b.setQuantite(resultat.getInt(5));
                b.setPrixUnitaire(resultat.getFloat(6));
                b.setTauxReduction(resultat.getFloat(7));
                b.setReference(resultat.getString(8));
                b.setInfo(resultat.getString(9));
                b.setUrl(resultat.getString(10));
                 System.out.println(b);
                 prods.add(b);
              
            }
           System.out.println("hahahahahah"+prods);
            return prods;
            
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Boutiques Dynamiqyes " + ex.getMessage());
            return null;
        }
    }
   
    
    
    public Produit findProduitById(int id) {
        
        String requete = "select * from produit where idProduit=? and idBoutique is not null";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            
            if (resultat.next()) {
                Produit b= new Produit();
              b.setId(resultat.getInt(1));
                b.setNonEnseigne(resultat.getString(2));
                b.setIdBoutique(resultat.getInt(3));
                b.setNom(resultat.getString(4));
                b.setQuantite(resultat.getInt(5));
                b.setPrixUnitaire(resultat.getFloat(6));
                b.setTauxReduction(resultat.getFloat(7));
                b.setReference(resultat.getString(8));
                b.setInfo(resultat.getString(9));
                b.setUrl(resultat.getString(10));
                return b;
            }
            return null;
            

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du produit " + ex.getMessage());
            return null;
        }
    }

    
     public void updateProduit(Produit p,int idBoutique,int quantite,float prixUnitaire) {
        String requete = "update produit set idBoutique=?,quantite=?,prixVenteUnitaire=? where idProduit=?";//don't forget the set of update
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,idBoutique);
            ps.setInt(2,quantite);
            ps.setFloat(3,prixUnitaire);
            ps.setInt(4, p.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
     public void updateProduit(Produit p,int quantite,float prixUnitaire) {
        String requete = "update produit set quantite=?,prixVenteUnitaire=? where idProduit=?";//don't forget the set of update
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
          
            ps.setInt(1,quantite);
            ps.setFloat(2,prixUnitaire);
            ps.setInt(3, p.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
      public ArrayList findProduitByEnseigne(String nomE,int id){
       ArrayList<Produit> produits=new ArrayList<Produit>();
        String requete = "select * from  produit where nomEnseigne=? and (idBoutique<>? or idBoutique is null)";
        
        
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,nomE);
            ps.setInt(2,id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Produit b=new Produit();
                b.setId(resultat.getInt(1));
                b.setNonEnseigne(resultat.getString(2));
                b.setIdBoutique(resultat.getInt(3));
                b.setNom(resultat.getString(4));
                b.setQuantite(resultat.getInt(5));
                b.setPrixUnitaire(resultat.getFloat(6));
                b.setTauxReduction(resultat.getFloat(7));
                b.setReference(resultat.getString(8));
                b.setInfo(resultat.getString(9));
                b.setUrl(resultat.getString(10));
                produits.add(b);
            }
            return produits;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche des produits " + ex.getMessage());
            return null;
        }
    }
        public void removeProduitFromBoutiqueById(int idP){
             String requete = "update produit set idBoutique=null where idProduit=?";//don't forget the set of update
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,idP);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
         public void AddTaille(String t,int q, int idp){
        try {
            String req = "insert into produittaille (idProduit,taille,quantite) values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, idp);
            ps.setString(2, t);
            ps.setInt(3,q);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public ArrayList<String> findTailleByProduit2(int id,String login){
        PanierDAO p=new PanierDAO();
        
       ArrayList<String> tailles=new ArrayList<String>();
     
        int k=0;
        int r;
        String requete = "select * from produittaille where idproduit='"+id+"'";
        System.out.println("c laa requeteeeeeeeeeeeeeeeeeeeee "+requete);
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery(requete);
          while (resultat.next()) {
               
                if(resultat.getInt(3)>0){
                     k=0;
                for(Panier p2: p.findPanierClient(login))
                {
                if(p2.getTaille().equals(resultat.getString(2))&& p2.getIdProduit()==resultat.getInt(1) )
                k=1;
                
                }
                    System.out.println("------"+k);
                if(k==0)
                tailles.add(resultat.getString(2)); 
                }
            }
            System.out.println("---------------------"+tailles);  
            return tailles;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche boutique byEnsiegne " + ex.getMessage());
            return null;
        }
    }
    
    public ArrayList<String> findTailleByProduit(int id,String login){
        PanierDAO p=new PanierDAO();
        
       ArrayList<String> tailles=new ArrayList<String>();
        String requete = "select * from  produittaille where idProduit=? ";
        int k=0;
        int r;
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
               
                if(resultat.getInt(3)>0){
                     k=0;
                for(Panier p2: p.findPanierClient(login))
                {
                if(p2.getTaille().equals(resultat.getString(2))&& p2.getIdProduit()==resultat.getInt(1) )
                k=1;
                
                }
                
                if(k==0)
                tailles.add(resultat.getString(2)); 
                }
            }
            System.out.println("---------------------"+tailles);  
            return tailles;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche boutique byEnsiegne " + ex.getMessage());
            return null;
        }
    }
    @Override
    public void removeProduitById(int idProduit) {
            String requete = "delete from enseigne where idProduit=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,idProduit);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
    

    @Override
    public List<Produit> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     @Override
    public ArrayList<Produit> findProduitsByCatalogue(int idCatalogue)
    {
        ArrayList<Produit> produits = new ArrayList();
            String req = "select * from prodcat where idCatalogue=?";
            try { 
                PreparedStatement ps = connection.prepareStatement(req);
                ps.setInt(1, idCatalogue);
                 ResultSet resultat = ps.executeQuery();
                 while(resultat.next())
                 {
                     produits.add(findProduitById(Integer.valueOf(resultat.getString(1))));
                     
                 }
                 return produits;
            }
            catch (SQLException ex)
            {
                System.out.println("erreur lors de la recherche : "+ ex.getMessage());
                return null;
            }
    }
    
}
