package lesClassesMetiers;



import com.mysql.jdbc.ResultSetMetaData;
import java.util.Date;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static lesClassesMetiers.Utilisateur.access;

public class Membre {	

	
	static AccessBD access = new AccessBD();
	//methode pour ajouter ou créer un membre
		public static void creerMembre(String nom, String prenom, Date date_naissance, Date date_entree, String adresse) {
			String ajout = "INSERT INTO membre(nom, prenom, date_naissance,date_entree, adresse,etat)" +"VALUES('"+nom+"','"+prenom+"',"
					+ "'"+date_naissance+"', '"+date_entree+"', '"+adresse+"','activé');";
			access.Connexion();
			access.executeUpdate(ajout);
		}
		
	public static void activerMembre(String id) {
                      
			String query = "UPDATE membre set etat='activé' WHERE id_membre  = '"+id+"' ";
			access.Connexion();
			access.executeUpdate(query);
		}
        
		
	///desactiver un membre
		public static void desactiverMembre(String id) {
			String query = "UPDATE membre set etat='desactivé' WHERE id_membre = '"+id+"' ";
			access.Connexion();
			access.executeUpdate(query);
		}
               
          //liste de membres
	       
          //liste des financiers
        public static DefaultTableModel lesMembres(DefaultTableModel dftm) {
	      Vector lignesTableau = new Vector(), nomCols = new Vector();
	      int i;
	       access.Connexion();
	      try {
	    	  String requete = "SELECT id_membre,nom, prenom, date_naissance,date_entree, adresse,etat from membre";
	    	 access.statement =  access.cnx.createStatement();
	         access.resultat =  access.statement.executeQuery(requete);
                 
	       
	        ResultSetMetaData result = (ResultSetMetaData)  access.resultat.getMetaData();
                

	        nomCols = new Vector();
	        for( i=1; i<=result.getColumnCount (); i++)
	             nomCols.add( result.getColumnLabel(i));
	        
	        while(  access.resultat.next()) {
	         
	           Vector ligne=new Vector();
	           
                       int id =  access.resultat.getInt("id_membre");
	               String nom =  access.resultat.getString("nom");
                       String prenom =  access.resultat.getString("prenom");
                       Date date_naissance =  access.resultat.getDate("date_naissance");
                       Date date_entree =  access.resultat.getDate("date_entree");
                       String adresse =  access.resultat.getString("adresse");
                       String etat =  access.resultat.getString("etat");
                       
                       ligne.add(id);
	               ligne.add(nom);
                       ligne.add(prenom);
                       ligne.add(date_naissance);
                       ligne.add(date_entree);
                       ligne.add(adresse);
                        ligne.add(etat);
	           
	           lignesTableau.add(ligne);
                   
                   
                   dftm.addRow(ligne);
	         }
	       }
	     catch(Exception e) { 
	         System.out.print("erreur : "+e.getMessage());
	     }

	     return dftm;
        }
}

