/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lesClassesMetiers;

import com.mysql.jdbc.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static lesClassesMetiers.Utilisateur.access;

/**
 *
 * @author Merveille
 */
public class ConsulterSms {
     static AccessBD  access = new AccessBD();
     public static String texte(){
             // ConsulterMessage.;
             String a="";
              access.Connexion();
             try {
                 String query0 = "SELECT contenu FROM message WHERE etat='à traiter'";
                 access.statement =  access.cnx.createStatement();
             access.resultat =  access.statement.executeQuery(query0);
             if(access.resultat.next()){
                 
                a=  access.resultat.getString("contenu");
             }
         } catch (SQLException e) {
              System.out.print("erreur : "+e.getMessage());
             
         }
             return a;
             
        
     }
     public static int controle(){
         String a="";
         int i=2;
              access.Connexion();
             try {
                 String query0 = "SELECT etat FROM message";
                 access.statement =  access.cnx.createStatement();
             access.resultat =  access.statement.executeQuery(query0);
             while(access.resultat.next()){
                 
                a=  access.resultat.getString("etat");
             }
         } catch (SQLException e) {
              System.out.print("erreur : "+e.getMessage());
             
         }
             if(a.equals("à traiter"))
                 i=0;
             else
                 i=1;
             return i;
         
     }
           
         public static void consulter(String nom,String prenom,Date anniv ,Date entre,String adresse){
              Connection con = null;
          try{
                
                  String url = "jdbc:mysql://localhost:3306/flux_financiers";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                con = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, "root", "");
             
               
			String ajout = "INSERT INTO membre(nom, prenom, date_naissance,date_entree, adresse,etat)" +"VALUES('"+nom+"','"+prenom+"',"
					+ "'"+anniv+"', '"+entre+"', '"+adresse+"','activé');";
			access.Connexion();
			access.executeUpdate(ajout);
                
                JOptionPane.showMessageDialog(null, "le membre a été ajouté avec Succès");
                //String query = "INSERT INTO `message`(`etat`) VALUES (?)";
                   String query = "UPDATE message set etat='traité'";
              
                PreparedStatement statement2 = (PreparedStatement) con.prepareStatement(query);
               // statement2.setString(1, "traité");
                statement2.executeUpdate();
             }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "erreur lors de l'Ajout "+ e);
            }
        } 
          
// TODO add your handling code here:
      
}

