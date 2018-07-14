package lesClassesMetiers;



import java.util.Vector;
import javax.swing.JTable;
import com.mysql.jdbc.ResultSetMetaData;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import lesClassesMetiers.AccessBD;
import static lesClassesMetiers.Membre.access;


public class Depense {
	static AccessBD access = new AccessBD();
	
	//enregistrer une depense en espèces
	public static void enregistrerDepenseEspece(String motif, Float montant, Date date) {
		String enregistrer = "INSERT INTO depense(motif, montant, date)" +"VALUES('"+motif+"','"+montant+"',"
				+ "'"+date+"');";
		access.Connexion();
		access.executeUpdate(enregistrer);
	}
	
	//enregistrer une depense par chèque
	public static void enregistrerDepenseCheque(int numero_cheque, String nom_beneficiaire, String motif, Float montant, Date date) {
		String enregistrer = "INSERT INTO depense_par_cheque(numero_cheque,nom_beneficiaire,motif, montant,date)" +"VALUES('"+numero_cheque+"','"+nom_beneficiaire+"',"
				+ "'"+motif+"','"+montant+"','"+date+"');";
		access.Connexion();
		access.executeUpdate(enregistrer);
               
                
	}
        
	//consulter toutes les dépenses en espèces 
	public static DefaultTableModel consulterDepenseEspese(DefaultTableModel dftm) {
	      Vector lignesTableau = new Vector(), nomCols = new Vector();
	      int i;
	       access.Connexion();
	      try {
	    	  String requete = "SELECT * from  depense";
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
                   
                   
                   dftm.addRow(ligne);
	         }
	       }
	     catch(Exception e) { 
	         System.out.print("erreur : "+e.getMessage());
	     }

	     return dftm;
        }

	
	//consulter toutes les dépenses par chèque
		 
        public static DefaultTableModel consulterDepenseCheque(DefaultTableModel dftml) {
	      Vector lignesTableau = new Vector(), nomCols = new Vector();
	      int i;
	       access.Connexion();
	      try {
	    	  String requete = "SELECT * from  depense_par_cheque";
	    	 access.statement =  access.cnx.createStatement();
	         access.resultat =  access.statement.executeQuery(requete);
                 
	       
	        ResultSetMetaData result = (ResultSetMetaData)  access.resultat.getMetaData();
                

	        nomCols = new Vector();
	        for( i=1; i<=result.getColumnCount (); i++)
	             nomCols.add( result.getColumnLabel(i));
	        
	        while(  access.resultat.next()) {
	         
	           Vector ligne=new Vector();
	           
	           for(i=1; i<=6; i++) 
	           {
	               String chaine =  access.resultat.getString(i);
	               ligne.add(chaine);
	           }
	           lignesTableau.add(ligne);
                   
                   
                   dftml.addRow(ligne);
	         }
	       }
	     catch(Exception e) { 
	         System.out.print("erreur : "+e.getMessage());
	     }

	     return dftml;
        }
}
