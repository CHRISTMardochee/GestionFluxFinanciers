package lesClassesMetiers;



import java.util.Vector;
import javax.swing.JTable;
import com.mysql.jdbc.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import lesClassesMetiers.AccessBD;


public class Recette {
	static AccessBD access = new AccessBD();
	
	//enregistrer une Recette_principale
	public static void enregistrerRecette_principale( int id_membre,Float montant, Date date) {
		String enregistrer = "INSERT INTO recette_principale(id_membre, montant,date_versement)" +"VALUES('"+id_membre+"',"
				+ "'"+montant+"','"+date+"');";
		access.Connexion();
		access.executeUpdate(enregistrer);
	}
	
        //enregistrer une Recette_principaleGroupe
	public static void enregistrerRecette_principaleGroupe(Float montant, Date date) {
		String enregistrer = "INSERT INTO recette_principale_groupe(montant,date)" +"VALUES("
				+ "'"+montant+"','"+date+"');";
		access.Connexion();
		access.executeUpdate(enregistrer);
	}
	
	//enregistrer une recette_secondaire_tiers
        
	public static void enregistrerRecette_secondaire_tiers(String libelle, String nom , String prenom ,float montant, Date date_versement) {
		String enregistrer = "INSERT INTO recette_secondaire_tiers(libelle,nom,prenom,montant,date_versement)" +"VALUES('"+libelle+"','"+nom+"','"+prenom+"','"+montant+"','"+date_versement+"');";
		access.Connexion();
		access.executeUpdate(enregistrer);
               
                
	}
        
        
        //enregistrer une recette_secondaire_membre
        
	public static void enregistrerRecette_secondaire_membre(int id_membre, float montant , Date date) {
		String enregistrer = "INSERT INTO recette_secondaire_membre(id_membre,montant,date)" +"VALUES('"+id_membre+"','"+montant+"','"+date+"');";
		access.Connexion();
		access.executeUpdate(enregistrer);
               
                
	}
        //REchercher les un membre
        public static void  rechercherMembre(String nom){
             access.Connexion();
	      try {
	       
	    	  String requete = "SELECT nom FROM membre WHERE nom='"+nom+"'  ";
	        access.statement = access.cnx.createStatement();
	        access.resultat = access.statement.executeQuery(requete);
	        
	        ResultSetMetaData result = (ResultSetMetaData) access.resultat.getMetaData();
	       /* if(result==null){
                 JOptionPane.showMessageDialog(null, "Le membre n'existe pas ");
                 return 0;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Le membre existe ");
                    return 1;
                }*/
                    
	     }catch(Exception e) { 
	         System.out.print("erreur1 "+e.getMessage());
	     }
            
        }
        public static int   recherche(String nom){
             access.Connexion();
             int status=2;
             String name="";
	      try {
	       
	    	  String requete = "SELECT nom FROM membre where nom='"+nom+"' AND etat = 'activé'  ";
	        access.statement = access.cnx.createStatement();
	        access.resultat = access.statement.executeQuery(requete);
	        
	        //ResultSetMetaData result = (ResultSetMetaData) access.resultat.getMetaData();
                while(access.resultat.next()){
                   name= access.resultat.getString("nom");
                }
	       if(name.equals(nom)){
                 
                 status=1;
                }else
                   status=0;
 
                    
	     }catch(Exception e) { 
	         System.out.print("erreur1 "+e.getMessage());
	     }
            return status;
        }
      /*  public static int   Trouve(String nom){
             access.Connexion();
             int status=2;
	      try {
	       
	    	  String requete = "SELECT nom FROM membre where nom="+nom+"  ";
	        access.statement = access.cnx.createStatement();
	        access.resultat = access.statement.executeQuery(requete);
	        
	        ResultSetMetaData result = (ResultSetMetaData) access.resultat.getMetaData();
	       if(result!=null){
                 
                 status=1;
                }
 
                    
	     }catch(Exception e) { 
	         System.out.print("erreur1 "+e.getMessage());
	     }
            return status;
        }*/
	
	//consulter toutes les recette_principale
	public static JTable consulterRecette_principale() {
	      Vector lignesTableau = new Vector(), nomCols = new Vector();
	      int i;
	      access.Connexion();
	      try {
	       
	    	  String requete = "SELECT * FROM recette_principale";
	        access.statement = access.cnx.createStatement();
	        access.resultat = access.statement.executeQuery(requete);
	        
	        ResultSetMetaData result = (ResultSetMetaData) access.resultat.getMetaData();
	        nomCols = new Vector();
	        for( i=1; i<=result.getColumnCount (); i++)
	             nomCols.add( result.getColumnLabel(i));
	        
	        while( access.resultat.next()) {
	        	
	           Vector ligne=new Vector();
	           
	           for(i=1; i<=4; i++) 
	           {
	               String chaine=access.resultat.getString(i);
	               ligne.add(chaine);
	           }
	           lignesTableau.add(ligne);
	           
	         }
	        
	      }catch(Exception e) { 
	         System.out.print("erreur1 "+e.getMessage());
	     }
	      return new JTable(lignesTableau, nomCols);
	}
	
	
        //consulter toutes les recette_principaleGroupe
	public static JTable consulterRecette_principaleGroupe() {
	      Vector lignesTableau = new Vector(), nomCols = new Vector();
	      int i;
	      access.Connexion();
	      try {
	       
	    	  String requete = "SELECT * FROM recette_principale_groupe";
	        access.statement = access.cnx.createStatement();
	        access.resultat = access.statement.executeQuery(requete);
	        
	        ResultSetMetaData result = (ResultSetMetaData) access.resultat.getMetaData();
	        nomCols = new Vector();
	        for( i=1; i<=result.getColumnCount (); i++)
	             nomCols.add( result.getColumnLabel(i));
	        
	        while( access.resultat.next()) {
	        	
	           Vector ligne=new Vector();
	           
	           for(i=1; i<=3; i++) 
	           {
	               String chaine=access.resultat.getString(i);
	               ligne.add(chaine);
	           }
	           lignesTableau.add(ligne);
	           
	         }
	        
	      }catch(Exception e) { 
	         System.out.print("erreur1 "+e.getMessage());
	     }
	      return new JTable(lignesTableau, nomCols);
	}
	
	//consulter toutes les Recette_secondaire_tiers
	public static JTable consulterRecette_secondaire_tiers() {
	      Vector lignesTableau = new Vector(), nomCols = new Vector();
	      int i;
	      access.Connexion();
	      try {
	       
	    	  String requete = "SELECT * FROM recette_secondaire_tiers";
	        access.statement = access.cnx.createStatement();
	        access.resultat = access.statement.executeQuery(requete);
	        
	        ResultSetMetaData result = (ResultSetMetaData) access.resultat.getMetaData();
	        nomCols = new Vector();
	        for( i=1; i<=result.getColumnCount (); i++)
	             nomCols.add( result.getColumnLabel(i));
	        
	        while( access.resultat.next()) {
	        	
	           Vector ligne=new Vector();
	           
	           for(i=1; i<=6; i++) 
	           {
	               String chaine=access.resultat.getString(i);
	               ligne.add(chaine);
	           }
	           lignesTableau.add(ligne);
	           
	         }
	        
	      }catch(Exception e) { 
	         System.out.print("erreur1 "+e.getMessage());
	     }
	      return new JTable(lignesTableau, nomCols);
	}
	
	//consulter toutes les Recette_secondaire_membre
	public static JTable consulterRecette_secondaire_membre() {
	      Vector lignesTableau = new Vector(), nomCols = new Vector();
	      int i;
	      access.Connexion();
	      try {
	       
	    	  String requete = "SELECT * FROM recette_secondaire_membre";
	        access.statement = access.cnx.createStatement();
	        access.resultat = access.statement.executeQuery(requete);
	        
	        ResultSetMetaData result = (ResultSetMetaData) access.resultat.getMetaData();
	        nomCols = new Vector();
	        for( i=1; i<=result.getColumnCount (); i++)
	             nomCols.add( result.getColumnLabel(i));
	        
	        while( access.resultat.next()) {
	        	
	           Vector ligne=new Vector();
	           
	           for(i=1; i<=4; i++) 
	           {
	               String chaine=access.resultat.getString(i);
	               ligne.add(chaine);
	           }
	           lignesTableau.add(ligne);
	           
	         }
	        
	      }catch(Exception e) { 
	         System.out.print("erreur1 "+e.getMessage());
	     }
	      return new JTable(lignesTableau, nomCols);
	}
        
       
	public static DefaultTableModel consulterRecetteP(DefaultTableModel dftm, String idMembre, String firstDate, String lastDate) {
	      Vector lignesTableau = new Vector(), nomCols = new Vector();
	      int i;
	       access.Connexion();
	      try {
	    	  String requete = "Select m.id_membre,m.nom,m.prenom,r.date,r.montant from membre m INNER JOIN recette_principale r ON m.id_membre = r.id_membre  WHERE r.id_membre = '"+idMembre+"' and date between '"+firstDate+"' and '"+lastDate+"';";
	    	 access.statement =  access.cnx.createStatement();
	         access.resultat =  access.statement.executeQuery(requete);
                 
	        ResultSetMetaData result =(ResultSetMetaData)  access.resultat.getMetaData();
                

	        nomCols = new Vector(); 
	        for( i=1; i<=result.getColumnCount (); i++)
	             nomCols.add( result.getColumnLabel(i));
	        
	        while(  access.resultat.next()) {
	         
	           Vector ligne=new Vector();
	           
	           for(i=1; i<=5; i++) 
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
	
//**************************************************************************************************************************************************
//recette secondaire par membre
	public static DefaultTableModel consulterRecetteS(DefaultTableModel dftm,String idMembre, String firstDate, String lastDate) {
		 Vector lignesTableau = new Vector(), nomCols = new Vector();
	      int i;
	       access.Connexion();
	      try {
	    	  String requete = "Select m.id_membre,m.nom,m.prenom,r.date,r.montant from membre m INNER JOIN recette_secondaire_membre r ON m.id_membre = r.id_membre  WHERE r.id_membre = '"+idMembre+"' and date between '"+firstDate+"' and '"+lastDate+"';";
	    	 access.statement =  access.cnx.createStatement();
	         access.resultat =  access.statement.executeQuery(requete);
                 
	        ResultSetMetaData result =(ResultSetMetaData)  access.resultat.getMetaData();
                

	        nomCols = new Vector(); 
	        for( i=1; i<=result.getColumnCount (); i++)
	             nomCols.add( result.getColumnLabel(i));
	        
	        while(  access.resultat.next()) {
	         
	           Vector ligne=new Vector();
	           
	           for(i=1; i<=5; i++) 
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
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////**************************************************************************************************************************************************
	//Calcul du total des recettes principaux pour un membre donné
	public static String CalculMontantRecettePMembre(String idMembre, String firstDate, String lastDate ) {
		
		access.Connexion();
		String query ="select sum(montant) as totale from recette_principale where id_membre= '"+idMembre+"' and date between '"+firstDate+"' and '"+lastDate+"'";
		
		String total = "";
		try {
                    access.resultat=access.statement.executeQuery(query);
			access.resultat.last();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			total = access.resultat.getString("totale");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return total;
	
	}
	
//**************************************************************************************************************************************************
	//Calcul du total des recettes secondaires pour un membre donné
public static String CalculMontantRecetteSMembre(String idMembre,String firstDate, String lastDate) {
		
		access.Connexion();
		String query ="select sum(montant) as totale from recette_secondaire_membre where id_membre= '"+idMembre+"' and date between '"+firstDate+"' and '"+lastDate+"'";
		
		String total = "";
		try {
                    access.resultat=access.statement.executeQuery(query);
			access.resultat.last();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			total = access.resultat.getString("totale");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return total;
		
	}

//**************************************************************************************************************************************************
	//consulter toutes les recettes Principales enregistrées sur une période donnée
public static  DefaultTableModel consulterTotalRecettep(DefaultTableModel dftm, String firstDate, String lastDate) {
	 Vector lignesTableau = new Vector(), nomCols = new Vector();
	      int i;
	       access.Connexion();
	      try {
	    	  String requete = "Select id_membre,montant,date from recette_principale WHERE date between '"+firstDate+"' and '"+lastDate+"';";
	    	 access.statement =  access.cnx.createStatement();
	         access.resultat =  access.statement.executeQuery(requete);
                 
	        ResultSetMetaData result =(ResultSetMetaData)access.resultat.getMetaData();
                

	        nomCols = new Vector(); 
	        for( i=1; i<=result.getColumnCount (); i++)
	             nomCols.add( result.getColumnLabel(i));
	        
	        while(  access.resultat.next()) {
	         
	           Vector ligne=new Vector();
	           
	           for(i=1; i<=3; i++) 
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

//**************************************************************************************************************************************************
	//consulter toutes les recettes Secondaires enrigistrées sur une période donnée
	public static DefaultTableModel consulterTotalRecetteS(DefaultTableModel dftm, String firstDate, String lastDate) {
		Vector lignesTableau = new Vector(), nomCols = new Vector();
	      int i;
	       access.Connexion();
	      try {
	    	  String requete = "Select id_membre,montant,date from recette_secondaire_membre WHERE date between '"+firstDate+"' and '"+lastDate+"';";
	    	 access.statement =  access.cnx.createStatement();
	         access.resultat =  access.statement.executeQuery(requete);
                 
	        ResultSetMetaData result =(ResultSetMetaData)access.resultat.getMetaData();
                

	        nomCols = new Vector(); 
	        for( i=1; i<=result.getColumnCount (); i++)
	             nomCols.add( result.getColumnLabel(i));
	        
	        while(  access.resultat.next()) {
	         
	           Vector ligne=new Vector();
	           
	           for(i=1; i<=3; i++) 
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
	
//**************************************************************************************************************************************************
		//la somme totale des cotisation au niveau de recettes secondaires
		public static String CalculMontantRecetteS(String firstDate,String lastDate) {
				
				access.Connexion();
				String query ="select sum(montant) as totale from recette_secondaire_membre where date between '"+firstDate+"' and '"+lastDate+"' ";
				
				String total = "";
				try {
                                    access.resultat=access.statement.executeQuery(query);
					access.resultat.last();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					total = access.resultat.getString("totale");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return total;
				
			}
		
//**************************************************************************************************************************************************
		//la somme totale des cotisation au niveau de recettes principales
		public static String CalculMontantRecetteP(String firstDate,String lastDate) {
			
			access.Connexion();
			String query ="select sum(montant) as totale from recette_principale where date between '"+firstDate+"' and '"+lastDate+"' ";
			
			String total = "";
			try {
                            access.resultat=access.statement.executeQuery(query);
				access.resultat.last();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				total = access.resultat.getString("totale");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return total;
			
		}
		
//**************************************************************************************************************************************************
		//3-Consulter toutes les recettes enrigistrées sur une période 						String query = "SELECT * FROM `recetteprincipale` as rp where dateVersement between '"+firstDate+"' and '"+lastDate+"' UNION ALL SELECT * FROM `recettesecondaire` as rs where dateVersement between '"+firstDate+"' and '"+lastDate+"' ORDER BY `dateVersement` ASC";

		public static DefaultTableModel consulterTotalRecette(DefaultTableModel dftm,String firstDate, String lastDate) {
		Vector lignesTableau = new Vector(), nomCols = new Vector();
	      int i;
	       access.Connexion();
	      try {
            String query = "SELECT montant,date FROM `recette_principale` as rp where date between '"+firstDate+"' and '"+lastDate+"' UNION ALL SELECT montant,date FROM `recette_secondaire_membre` as rs where date between '"+firstDate+"' and '"+lastDate+"' ORDER BY `date` ASC";
	    	 access.statement =  access.cnx.createStatement();
	         access.resultat =  access.statement.executeQuery(query);
                 
	        ResultSetMetaData result =(ResultSetMetaData)access.resultat.getMetaData();
                

	        nomCols = new Vector(); 
	        for( i=1; i<=result.getColumnCount (); i++)
	             nomCols.add( result.getColumnLabel(i));
	        
	        while(  access.resultat.next()) {
	         
	           Vector ligne=new Vector();
	           
	           for(i=1; i<=2; i++) 
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
		
//*************************************************************************************************************************************************
		////3-calcul du montant total de toutes les recettes enrigistrées sur une période
		public static String CalculMontantTotal(String firstDate,String lastDate) {

			access.Connexion();
			int A = Integer.parseInt(CalculMontantRecetteP(firstDate,lastDate));
			int B= Integer.parseInt(CalculMontantRecetteS(firstDate,lastDate));
			int T = A+B;
			String S = new String();
			S=S.valueOf((T));
			
			return S;
			
		}
		
//*************************************************************************************************************************************************
}
        
        
        
        
