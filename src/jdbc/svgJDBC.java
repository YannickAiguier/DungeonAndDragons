package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import box.Box;
import box.MeanOfAttack;
import box.Spell;
import box.Weapon;
import dad.GameBoard;
import player.Magician;
import player.Player;
import player.Warrior;

public class svgJDBC {
	private Connection conn;
	private String query;

	public svgJDBC() {
		this.conn = null;
		this.query = null;
	}

	public void startConnection() {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost/ytest?user=yannick&password=javapassword");

		} catch (SQLException e) {
			// affiche les erreurs
			System.out.println("SQLException : " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
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
		String type = "abc";

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
		if (player instanceof Warrior) {
			type = "Warrior";
		} else {
			type = "Magician";
		}

		query = "INSERT INTO player(type, name, img, life, maxlife, attack, protectiontype, id_MoA) VALUES ('" + type
				+ "', '" + player.getName() + "', '" + player.getImg() + "', " + player.getLife() + ", "
				+ player.getMaxLife() + ", " + player.getAttack() + ", '" + player.getProtectionType() + "', " + id
				+ ")";
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
				query = "INSERT INTO " + id + "boxes(nb, name, img, life, attack, forclass) VALUES(" + i
						+ ", '', '', 0, 0, '')";
			}
			this.setQuery(query);
			executeWriteQuery();
		}
	}

	public Player loadGame(String name, Player player, GameBoard board) throws SQLException {

		int playerId = 0;
		String boxes_table = null;
		MeanOfAttack moa = null;

		// récupérer dans la base la svg correspondant à name
		String query = "SELECT * FROM svg WHERE svg_name='" + name + "'";
		this.setQuery(query);
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(this.query);
			rs.next();
			board.setPlayerPos(rs.getInt("position"));
			boxes_table = rs.getString("boxes_table");
			playerId = rs.getInt("id_player");
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}

		// générer le plateau de jeu à partir de boxes_table
		query = "SELECT * FROM " + boxes_table;
		this.setQuery(query);
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(this.query);
			for (int i = 0; i < 64; i++) {
				rs.next();
				if (rs.getString("name").equals(null)) {
					board.createNullBox(i);
				} else {
					board.createBox(i, rs.getString("name"), rs.getString("img"), rs.getInt("life"),
							rs.getInt("attack"), rs.getString("forclass"));
				}
			}
			board.showBoard();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}

		// générer le joueur à partir de player (simple appel au constructeur ?)
		query = "SELECT * FROM player WHERE id=" + playerId;
		this.setQuery(query);
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(this.query);
			rs.next();
			if (rs.getString("type").equals("Warrior")) {
				// générer Warrior et Weapon
				player = new Warrior(rs.getString("name"));
			} else {
				// générer Magician et Spell
				player = new Magician(rs.getString("name"));
			}
			player.setImg(rs.getString("img"));
			player.setLife(rs.getInt("life"));
			player.setMaxLife(rs.getInt("maxlife"));
			player.setAttack(rs.getInt("attack"));
			player.setProtectionType(rs.getString("protectiontype"));
			query = "SELECT * FROM MoA WHERE id=" + rs.getInt("id_MoA");
			this.setQuery(query);
			try (Statement stmt2 = conn.createStatement()) {
				ResultSet rs2 = stmt.executeQuery(this.query);
				rs2.next();
				if (player instanceof Warrior) {
					// générer Warrior et Weapon
					moa = new Weapon(rs2.getString("name"), rs2.getString("img"), rs2.getInt("attack"));
				} else {
					// générer Magician et Spell
					moa = new Spell(rs2.getString("name"), rs2.getString("img"), rs2.getInt("attack"));
				}
				player.setFirstAttack(moa);
			} catch (SQLException e) {
				System.out.println("SQLException : " + e.getMessage());
			}
			System.out.println(player);
		} catch (SQLException e) {
			System.out.println("SQLException : " + e.getMessage());
		}
		return player;
	}

//	public static void main(String[] args) throws SQLException {
//		svgJDBC svg = new svgJDBC();
//
//		svg.startConnection();
//		svg.createSvg(null, null, null);
//
//	}	

}