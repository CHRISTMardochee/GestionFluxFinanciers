/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lesClassesMetiers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author Merveille
 */
public class EnvoiMessage {
    public static void  sms(String contenu){
          Connection con = null;
    try{
               
                String query = "INSERT INTO `message`(`etat`,`contenu`) VALUES (?,?)";
                String url = "jdbc:mysql://localhost:3306/flux_financiers";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                con = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, "root", "");
                PreparedStatement statement = (PreparedStatement) con.prepareStatement(query);
                statement.setString(1, "à traiter");
                statement.setString(2, contenu);
                
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Message envoyé avec Succès");
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "erreur lors de l'Ajout "+ e);
            }
    
}
}
