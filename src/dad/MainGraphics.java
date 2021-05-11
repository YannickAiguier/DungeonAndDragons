package dad;

import javax.swing.JFrame;
import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

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

		// le menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menuAction = new JMenu("Action");
		JMenu menuNew = new JMenu("Nouveau Personnage");
		JMenuItem menuLaunch = new JMenuItem("Lancer le jeu");
		JMenuItem menuExit = new JMenuItem("Quitter");
		JMenuItem menuWarrior = new JMenuItem("Guerrier");
		JMenuItem menuMagician = new JMenuItem("Magicien");
		menuLaunch.setEnabled(false);
		menuAction.add(menuNew);
		menuAction.add(menuLaunch);
		menuAction.add(menuExit);
		menuNew.add(menuWarrior);
		menuNew.add(menuMagician);
		menuBar.add(menuAction);

		// ajout du menu et du panneau de jeu à la fenêtre principale
		myWindow.setJMenuBar(menuBar);
		myWindow.setContentPane(game.init());

		// ajout des Listeners du menu
		createMenuListeners(game, menuLaunch, menuExit, menuWarrior, menuMagician);

		// ajout du listener du bouton de dé
		createDiceListener(game);

		// affichage de la fenêtre
		myWindow.setVisible(true);

	}

	/**
	 * Crée les listeners du menu.
	 * 
	 * @param game
	 * @param menuLaunch
	 * @param menuExit
	 * @param menuWarrior
	 * @param menuMagician
	 */
	private void createMenuListeners(MyGame game, JMenuItem menuLaunch, JMenuItem menuExit, JMenuItem menuWarrior,
			JMenuItem menuMagician) {

		// Lancer le jeu
		menuLaunch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.showGamePanel(true);
				myEngine = new GameEngine(player, game);
				myEngine.initBoard();
				game.showPlayer(player);
			}
		});

		// Quitter le jeu ?
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

		// créer d'un joueur guerrier et autoriser le lancement du jeu
		menuWarrior.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String) JOptionPane.showInputDialog(myWindow, "Quel nom pour votre Guerrier ?",
						"Nouveau Guerrier", JOptionPane.QUESTION_MESSAGE);
				createPlayer("Warrior", name);
				menuLaunch.setEnabled(true);
			}
		});

		// création d'un joueur magicien et autoriser le lancement du jeu
		menuMagician.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String) JOptionPane.showInputDialog(myWindow, "Quel nom pour votre Magicien ?",
						"Nouveau Magicien", JOptionPane.QUESTION_MESSAGE);
				createPlayer("Magician", name);
				menuLaunch.setEnabled(true);
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
