package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC {

	private Connection conn;
	private String query;

	/**
	 * @param conn
	 * @param query
	 * @param st
	 * @param rs
	 */
	public TestJDBC() {
		this.conn = null;
		this.query = null;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	public Connection startConnection() {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost/ytest?user=yannick&password=javapassword");
			// connecté
			return conn;

		} catch (SQLException ex) {
			// affiche les erreurs
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return null;
		}
	}

	public void executeReadQuery() throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(this.query);
			while (rs.next()) {
				int id = rs.getInt(1);
				String type = rs.getString(2);
				String nom = rs.getString(3);
				int vie = rs.getInt(4);
				int attaque = rs.getInt("NiveauForce");
				String arme = rs.getString("MoyenAttaque");
				String bouclier = rs.getString("Bouclier");
				System.out.println(type + " " + nom);
				System.out.println("Id : " + id + ", vie : " + vie + ", force : " + attaque + ", arme : " + arme
						+ ", bouclier : " + bouclier);
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
	}

	public void executeWriteQuery() throws SQLException {
		conn.setAutoCommit(false);
		try (Statement stmt = conn.createStatement()) {

			stmt.addBatch(this.query);

			stmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("SQLException : " + e);
		} finally {
			conn.setAutoCommit(true);
		}
	}

	public void createTable(int nb) throws SQLException {
		String query1 = "CREATE TABLE IF NOT EXISTS Hero (Id int(11) NOT NULL auto_increment, Type varchar(20), Nom varchar(30), NiveauVie tinyint, NiveauForce tinyint, MoyenAttaque char(5), Bouclier varchar(10), PRIMARY KEY (Id))";
		String query2 = "INSERT INTO Hero(Type, Nom, NiveauVie, NiveauForce, MoyenAttaque, Bouclier) VALUES (\"Guerrier\", \"\", 5, 5, \"Epée\", \"Bouclier\")";
		String query3 = "INSERT INTO Hero(Type, Nom, NiveauVie, NiveauForce, MoyenAttaque, Bouclier) VALUES (\"Magicien\", \"\", 3, 8, \"Sort\", \"Philtre\")";
		String q = "CREATE TABLE IF NOT EXISTS " + nb + "boxes (Id int(11) NOT NULL auto_increment, name varchar(20), life tinyint, PRIMARY KEY (Id))";
		this.setQuery(q);
		this.executeWriteQuery();
	}

	public void removeTable(String table) throws SQLException {
		String query = "DROP TABLE IF EXISTS " + table;
		this.setQuery(query);
		this.executeWriteQuery();
	}

	public static void main(String[] args) throws SQLException {
		TestJDBC test = new TestJDBC();
		String q = "SELECT * FROM Hero";
		String q2 = "INSERT INTO Hero(Type, Nom, NiveauVie, NiveauForce, MoyenAttaque, Bouclier) VALUES (\"Guerrier\", \"Moa\", 5, 5, \"Epée\", \"Ecu\")";

		test.startConnection();
		test.createTable(5);
//		test.setQuery(q);
//		test.executeReadQuery();
	}

}