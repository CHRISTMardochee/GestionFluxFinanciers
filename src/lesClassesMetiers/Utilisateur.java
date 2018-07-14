package lesClassesMetiers;



import com.mysql.jdbc.ResultSetMetaData;
import java.util.Date;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static lesClassesMetiers.ConnexionUser.access;


public class Utilisateur {
	
	static AccessBD  access = new AccessBD();
	
		// créer un admin
		public static void ajoutAdmin(String grade, String login, String mdp) {
			String ajout = "INSERT INTO user(grade, login,mdp)" +"VALUES('"+grade+"', '"+login+"','"+mdp+"');";
			 access.Connexion();
			 access.executeUpdate(ajout);
                         
		}
		
		//modifier les infos d'un admin
		public static void modifierAdmin(String grade, String login, String mdp, int id_user) {
			String modifier = "UPDATE  user set grade='"+grade+"', login = '"+login+"',mdp ='"+mdp+"' where id_user = '"+id_user+"';";
			 access.Connexion();
			 access.executeUpdate(modifier);
		}
                
		// supprimer un admin
		public static void supprimerAdmin(String selectedRow) {
			String supprim = "DELETE FROM user where id_user = '"+(selectedRow)+"';";
			 access.Connexion();
			 access.executeUpdate(supprim);
		}
              
          //liste des administrateurs
        public static DefaultTableModel lesAdmin(DefaultTableModel dftm) {
	      Vector lignesTableau = new Vector(), nomCols = new Vector();
	      int i;
	       access.Connexion();
	      try {
	    	  String requete = "SELECT * from user";
	    	 access.statement =  access.cnx.createStatement();
	         access.resultat =  access.statement.executeQuery(requete);
                 
	        ResultSetMetaData result =(ResultSetMetaData)  access.resultat.getMetaData();
                

	        nomCols = new Vector(); 
	        for( i=1; i<=result.getColumnCount (); i++)
	             nomCols.add( result.getColumnLabel(i));
	        
	        while(  access.resultat.next()) {
	         
	           Vector ligne=new Vector();
	           
	           for(i=1; i<=4; i++) 
	           {
	               String chaine =  access.resultat.getString(i);
	               ligne.add(chaine);
	           }
	           lignesTableau.add(ligne);
                   dftm.addRow(ligne);
	         }
	       }
	     catch(Exception e) { 
	         System.out.print("erreur : "+e.getMessage());
	     }

	     return dftm;
	}
        
        

                //Côtés Financiers
		
		//créer un financier
		public static void ajoutFinancier(String grade , String login, String mdp) {
			String ajout = "INSERT INTO user(grade,login, mdp)" +"VALUES('"+grade+"','"+login+"', '"+mdp+"');";
			 access.Connexion();
			 access.executeUpdate(ajout);
		}
				
				
		//modifier les infos d'un financier
		public static void modifierFinancier(String grade , String login, String mdp) {
			String modifier = "UPDATE user set grade = '"+grade+"', login = '"+login+"',mdp = '"+mdp+"';";
			 access.Connexion();
			 access.executeUpdate(modifier);
		}
				
		// supprimer un financier
		public static void supprimerFinancier(int id_user) {
			String supprim = "DELETE FROM user where id = '"+id_user+"';";
			 access.Connexion();
			 access.executeUpdate(supprim);
		}
                
          //liste des financiers
        public static DefaultTableModel lesFinanciers(DefaultTableModel dftmf) {
	      Vector lignesTableau = new Vector(), nomCols = new Vector();
	      int i;
	       access.Connexion();
	      try {
	    	  String requete = "SELECT * from user";
	    	 access.statement =  access.cnx.createStatement();
	         access.resultat =  access.statement.executeQuery(requete);
                 
	       
	        ResultSetMetaData result = (ResultSetMetaData)  access.resultat.getMetaData();
                

	        nomCols = new Vector();
	        for( i=1; i<=result.getColumnCount (); i++)
	             nomCols.add( result.getColumnLabel(i));
	        
	        while(  access.resultat.next()) {
	         
	           Vector ligne=new Vector();
	           
	           for(i=1; i<=4; i++) 
	           {
	               String chaine =  access.resultat.getString(i);
	               ligne.add(chaine);
	           }
	           lignesTableau.add(ligne);
                   
                   
                   dftmf.addRow(ligne);
	         }
	       }
	     catch(Exception e) { 
	         System.out.print("erreur : "+e.getMessage());
	     }

	     return dftmf;
	
	}
}

