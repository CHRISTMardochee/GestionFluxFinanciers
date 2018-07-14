package lesClassesMetiers;


import java.sql.*;

import javax.swing.*;


@SuppressWarnings("unused")
public class AccessBD {

	public  Connection cnx = null;
	public  Statement statement = null;
	ResultSet resultat = null;
	
	//Connexion par défaut
	public void Connexion() {

		String url = "jdbc:mysql://localhost:3306/flux_financiers";
		String user = "root";
		String mdp = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnx = DriverManager.getConnection(url, user, mdp);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Connexion impossible :" +e.getMessage());
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Classe introuvable :" + e.getMessage());
		}

	}

	//Connexion paramétrée
	public void Connexion(String typeCnx, String url, String user, String mdp) {

		try {

			Class.forName("com.'"+typeCnx+"'.jdbc.Driver");
			cnx = DriverManager.getConnection(url, user, mdp);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	private void commit() {

		try {
			cnx.commit();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	private void rollback() {
		try {
			if (cnx != null)
				cnx.rollback();
		} catch (SQLException se2) {
			JOptionPane.showMessageDialog(null, se2);
		}
	}

	public void executeQuery(String requete) {
		try {
				statement = cnx.createStatement();
				resultat = statement.executeQuery(requete);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })

	public void executeUpdate(String query) {
		try {
			statement = cnx.createStatement();
			statement.executeUpdate(query);
                         cnx.close();
                        
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}

