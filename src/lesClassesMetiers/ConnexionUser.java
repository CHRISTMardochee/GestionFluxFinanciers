package lesClassesMetiers;



import java.util.Vector;
import javax.swing.JOptionPane;
import com.mysql.jdbc.ResultSetMetaData;
import lesInterfacesGraphiques.Accueil;
import lesInterfacesGraphiques.Administrateur;
import lesInterfacesGraphiques.Financier;

public class ConnexionUser {
	static AccessBD access = new AccessBD();
	
	//connexion administrateur
	public static void connecterAdmin(String grade,String login, String mdp) {
		String connecter = "SELECT grade,login, mdp FROM user where grade = '"+grade+"' and login = '"+login+"' and mdp = '"+mdp+"' ";
		
		 access.Connexion();
	      try {
		    	  access.statement =  access.cnx.createStatement();
		    	  access.resultat =  access.statement.executeQuery(connecter);
		        
		        ResultSetMetaData result = (ResultSetMetaData)  access.resultat.getMetaData();
		        
		        if(access.resultat.next()) {
		        	
                                      
                                       Administrateur administrateur = new Administrateur();
                                       administrateur.setVisible(true);
                                       
                              
                                       
			               
                        } else {
			            	   JOptionPane.showMessageDialog(null, "Login ou mot de passe incorrecte", null, JOptionPane.ERROR_MESSAGE);
			               
			           
		        }
	      }catch(Exception e) { 
	    	  JOptionPane.showMessageDialog(null,"erreur1 "+e.getMessage()); 
	      }
	 }
	
        
	//connexion financier
	public static void connecterFinancier(String grade,String login, String mdp) {
		String connecter = "SELECT grade,login, mdp FROM user where grade = '"+grade+"' and login = '"+login+"' and mdp = '"+mdp+"' ";
		
		   access.Connexion();
	      try {
		    	  access.statement =  access.cnx.createStatement();
		    	  access.resultat =  access.statement.executeQuery(connecter);
		        
		        ResultSetMetaData result = (ResultSetMetaData)  access.resultat.getMetaData();
		        
		        if(access.resultat.next()) {
		        	
                                      
                                       Financier financier = new Financier(); 
                                       financier.setVisible(true);
                                       
                                     
                                       
                                       
			               
                        } else {
			            	   JOptionPane.showMessageDialog(null, "Login ou mot de passe incorrecte", null, JOptionPane.ERROR_MESSAGE);
                                           
			               
			           
		        }
	      }catch(Exception e) { 
	    	  JOptionPane.showMessageDialog(null,"erreur1 "+e.getMessage()); 
	      }
	 }
}
