package dad;

import javax.swing.JFrame;
import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import jdbc.svgJDBC;
import player.Magician;
import player.Player;
import player.Warrior;
import viewers.MyGame;

/**
 * Classe qui gère la fenêtre principale de l'interface graphique, ainsi que les
 * interactions (menus, boutons, dialogBox pour création de personnage).
 * 
 * <p>
 * Elle prend également en charge le rôle de moteur de jeu.<br>
 * Cette classe utilise :
 * <ul>
 * <li>Un joueur.</li>
 * <li>Un viewer MyGame, pour la représentation graphique du jeu.</li>
 * <li>Une JFrame, la fenêtre principale.</li>
 * <li>Un moteur de jeu, pour utiliser ses fonctions de gestion du jeu.</li>
 * </ul>
 * </p>
 * 
 * @author yannick
 *
 */
/**
 * @author yannick
 *
 */
public class MainGraphics {

	private Player player;
	private MyGame game;
	private JFrame myWindow;
	private GameEngine myEngine;

	/**
	 * Constructeur, initialise tous les composant à null. Ils seront remplis plus
	 * tard.
	 */
	public MainGraphics() {
		player = null;
		game = null;
		myWindow = null;
		myEngine = null;
	}

	/**
	 * Crée la fenêtre graphique, met en place les ActonListener et lance le jeu.
	 */
	public void start() {

		// la fenêtre principale
		myWindow = new JFrame("DungeonsAndDragons");
		myWindow.setSize(1200, 900);
		myWindow.setResizable(false);
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myWindow.setLocationRelativeTo(null);

		// l'objet MyGame qui crée tout l'interface graphique du jeu
		MyGame game = new MyGame();

		// l'objet svgJDBC qui permet de gérer les sauvegardes
		svgJDBC svg = new svgJDBC();

		// le menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Action");
		JMenu menuNewPlayer = new JMenu("Nouveau Personnage");
		JMenuItem menuLaunchGame = new JMenuItem("Lancer le jeu");
		JMenuItem menuSaveGame = new JMenuItem("Sauvegarder");
		JMenuItem menuLoadGame = new JMenuItem("Charger une partie");
		JMenuItem menuExit = new JMenuItem("Quitter");
		JMenuItem menuDeleteGame = new JMenuItem("Effacer une sauvegarde");
		JMenuItem menuCreateWarrior = new JMenuItem("Guerrier");
		JMenuItem menuCreateMagician = new JMenuItem("Magicien");

		// paramétrage
		myWindow.setSize(1200, 900);
		myWindow.setResizable(false);
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myWindow.setLocationRelativeTo(null);

		menuLaunchGame.setEnabled(false);
		menuSaveGame.setEnabled(false);

		// listeners
		menuLaunchGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.showGamePanel(true);
				myEngine = new GameEngine(player, game);
				myEngine.initBoard();
				game.showPlayer(player);
				menuSaveGame.setEnabled(true);
			}
		});

		menuSaveGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// faire le nécessaire pour sauvegarder le jeu : dialogbox demandant le nom de
				// la sauvegarde
				String name = (String) JOptionPane.showInputDialog(myWindow, "Quel nom pour votre sauvegarde ?",
						"Sauvegarder le jeu", JOptionPane.QUESTION_MESSAGE);
				try {
					svg.startConnection();
					svg.saveGame(myEngine.getMyGameBoard(), player, name);
					svg.closeConnection();
				} catch (SQLException ex) {
					System.out.println("SQLException Main : " + ex);
				}
			}
		});

		menuLoadGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// récupération de la liste des sauvegardes
				List<String> values = null;
				try {
					svg.startConnection();
					values = svg.showGames();
					svg.closeConnection();
				} catch (SQLException ex) {
					System.out.println("SQLException : " + ex.getMessage());
				}

				// création de la dialogbox avec liste
				Object[] possibleValues = values.toArray();
				Object selectedValue = JOptionPane.showInputDialog(null, "Choisissez votre sauvegarde",
						"Charger une partie", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
				if (selectedValue != null) {
					// préparer
					game.showGamePanel(true);
					myEngine = new GameEngine(player, game);
					myEngine.initBoard();

					// charger la svg
					try {
						svg.startConnection();
						player = svg.loadGame(selectedValue.toString(), player, myEngine.getMyGameBoard());
						svg.closeConnection();
					} catch (SQLException ex) {
						System.out.println("SQLException Main : " + ex);
					}
					myEngine.setPlayer1(player);
					myEngine.getMyGameBoard().showBoard();
					game.showPlayer(player);
					menuSaveGame.setEnabled(true);
					
					// gérer l'affichage
					game.resetShowBox();
					game.showDetail("Sauvegardé chargée, vous êtes en case " + myEngine.getMyGameBoard().getPlayerPos());
					game.showEvent("");
					game.resetMove();
				}

			}
		});
		menuExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int choose = JOptionPane.showConfirmDialog(myWindow, "Quitter le jeu ?", "Quitter",
						JOptionPane.YES_NO_OPTION);
				if (choose == 0) {
					System.exit(0);
				}
			}
		});

		menuDeleteGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// récupération de la liste des sauvegardes
				List<String> values = null;
				try {
					svg.startConnection();
					values = svg.showGames();
					svg.closeConnection();
				} catch (SQLException ex) {
					System.out.println("SQLException : " + ex.getMessage());
				}

				// création de la dialogbox avec liste
				Object[] possibleValues = values.toArray();
				Object selectedValue = JOptionPane.showInputDialog(null, "Choisissez la sauvegarde à supprimer",
						"Supprimer une sauvegarde", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
				
				if (selectedValue != null) {
					try {
						svg.startConnection();
						svg.deleteGame(selectedValue.toString());
						svg.closeConnection();
					} catch (SQLException ex) {
						System.out.println("SQLException : " + ex.getMessage());
					}
				}
			}
		});

		menuCreateWarrior.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String) JOptionPane.showInputDialog(myWindow, "Quel nom pour votre Guerrier ?",
						"Nouveau Guerrier", JOptionPane.QUESTION_MESSAGE);
				createPlayer("Warrior", name);

				menuLaunchGame.setEnabled(true);
			}
		});

		menuCreateMagician.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String) JOptionPane.showInputDialog(myWindow, "Quel nom pour votre Magicien ?",
						"Nouveau Magicien", JOptionPane.QUESTION_MESSAGE);
				createPlayer("Magician", name);
				menuLaunchGame.setEnabled(true);
			}
		});
	}

	/**
	 * Crée le listener du bouton de lancer du dé.
	 * 
	 * @param game
	 */
	private void createDiceListener(MyGame game) {
		game.getRollDice().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.showDetail("");
				myEngine.letsGo();
				myEngine.boxProcess();
				if (myEngine.isGameOver()) {
					game.getRollDice().setEnabled(false);
					myEngine.gameEnd();
				}
			}
		});

		// construction du menu
		menu.add(menuNewPlayer);
		menu.add(menuLaunchGame);
		menu.add(menuSaveGame);
		menu.add(menuLoadGame);
		menu.add(menuDeleteGame);
		menu.add(menuExit);
		menuNewPlayer.add(menuCreateWarrior);
		menuNewPlayer.add(menuCreateMagician);
		menuBar.add(menu);

		// construction de la fenêtre principale
		myWindow.setJMenuBar(menuBar);
		myWindow.setContentPane(game.getAll());

		// affichage de la fenêtre
		myWindow.setVisible(true);
	}

	/**
	 * Crée un joueur de type guerrier ou un magicien.
	 * 
	 * @param myClass : la classe de joueur à créer.
	 * @param name    : le nom du joueur.
	 */
	private void createPlayer(String myClass, String name) {
		if (myClass == "Warrior") {
			this.player = new Warrior(name);
		}
		if (myClass == "Magician") {
			this.player = new Magician(name);
		}
	}

}
