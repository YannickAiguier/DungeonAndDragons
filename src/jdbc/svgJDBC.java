package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import box.Box;
import dad.GameBoard;
import player.Player;

public class svgJDBC {
	private Connection conn;
	private String query;

	public svgJDBC() {
		this.conn = null;
		this.query = null;
	}

	public Connection startConnection() {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost/ytest?user=yannick&password=javapassword");
			// connecté
			return conn;

		} catch (SQLException e) {
			// affiche les erreurs
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			return null;
		}
	}

	public void closeConnection() throws SQLException {
		this.conn.close();
	}

	/**
	 * @param query the query to set
	 */
	private void setQuery(String query) {
		this.query = query;
	}

//	public void executeReadQuery() throws SQLException {
//		System.out.println(this.query);
//		try (Statement stmt = conn.createStatement()) {
//			ResultSet rs = stmt.executeQuery(this.query);
//			
//			return result;
//		} catch (SQLException e) {
//			System.out.println("SQLException: " + e.getMessage());
//			return null;
//		}
//	}

	private int executeWriteQuery() throws SQLException {
		int id = 0;
		try {
			PreparedStatement ps = this.conn.prepareStatement(this.query, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			return id;
		} catch (SQLException e) {
			System.out.println("SQLException : " + e);
			return 0;
		}
	}

	public void saveGame(GameBoard gameBoard, Player player, String svg_name) throws SQLException {
		String query = "SELECT id FROM MoA WHERE name='" + player.getFirstAttack().getName() + "'";
		int id = 0;

		// récupérer id MoA
		this.setQuery(query);
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(this.query);
			rs.next();
			id = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("SQLException : " + e.getMessage());
		}

		// insérer player et récupérer l'id
		query = "INSERT INTO player(name, img, life, maxlife, attack, protectiontype, id_MoA) VALUES ('"
				+ player.getName() + "', '" + player.getImg() + "', '" + player.getLife() + "', '" + player.getMaxLife()
				+ "', '" + player.getAttack() + "', '" + player.getProtectionType() + "', '" + id + "')";
		this.setQuery(query);
		id = executeWriteQuery();

		// créer svg avec fake boxes_table et récupérer id
		query = "INSERT INTO svg(position, svg_name, boxes_table, id_player) VALUES ('" + gameBoard.getPlayerPos()
				+ "', '" + svg_name + "', '" + "xxboxes" + "', '" + id + "')";
		this.setQuery(query);
		id = executeWriteQuery();

		// créer xxboxes avec cet id
		query = "CREATE TABLE " + id
				+ "boxes(nb int NOT NULL, name varchar(25) NOT NULL, img varchar(20) NOT NULL, life int NOT NULL, attack int NOT NULL, forclass varchar(20) NOT NULL)";
		this.setQuery(query);
		executeWriteQuery();

		// modifier svg avec le bon nom de boxes_table
		query = "UPDATE svg SET boxes_table='" + id + "boxes' WHERE id=" + id;
		this.setQuery(query);
		executeWriteQuery();

		// reste à remplir la table xxboxes avec les cases du plateau
		// il faut faire une boucle pour parcourir le plateau de jeu
		for (int i = 0; i < 64; i++) {
			Box b = gameBoard.getBox(i);
			if (b != null) {
				query = "INSERT INTO " + id + "boxes(nb, name, img, life, attack, forclass) VALUES(" + i + ", '"
						+ b.getName() + "', '" + b.getImg() + "', " + b.getLife() + ", " + b.getAttack() + ", '"
						+ b.getForClass() + "')";				
			} else {
				query = "INSERT INTO " + id + "boxes(nb, name, img, life, attack, forclass) VALUES(" + i + ", '', '', 0, 0, '')";	
			}
			this.setQuery(query);
			executeWriteQuery();
		}

	}

//	public static void main(String[] args) throws SQLException {
//		svgJDBC svg = new svgJDBC();
//
//		svg.startConnection();
//		svg.createSvg(null, null, null);
//
//	}	

}