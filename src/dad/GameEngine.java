package dad;
import player.Player;
import viewers.MyUtils;
import viewers.Viewer;

/**
 * Classe moteur de jeu qui fournit les outils pour gérer une partie. Mode
 * console : boucle gérée par le moteur de jeu, appelle les fonctions
 * nécessaires. Mode graphique : la classe MainGraphics gère l'interface et
 * appelle les fonctions nécessaires.
 * 
 * <p>
 * Un moteur de jeu est représenté par les informations suivantes :
 * <ul>
 * <li>Un joueur.</li>
 * <li>Un viewer, auquel seront passés les résultats pour affichage.</li>
 * <li>Un plateau de jeu, pour suivre le déroulement de la partie.</li>
 * </ul>
 * 
 * 
 * @author yannick
 *
 */
public class GameEngine {

	private Player player1;
	private Viewer viewer;
	private GameBoard myGameBoard;

	/**
	 * Constructeur sans paramètres.
	 */
	public GameEngine() {
		this.player1 = null;
		this.viewer = null;
		this.myGameBoard = null;
	}

	/**
	 * Constructeur, initialise le joueur et le viewer.
	 * 
	 * @param player : le joueur qui va jouer la partie.
	 * @param viewer : le viewer auquel seront passés les résultats pour affichage.
	 */
	public GameEngine(Player player, Viewer viewer) {
		this();
		this.player1 = player;
		this.viewer = viewer;
	}

	/**
	 * @return the player1
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * @param player1 the player1 to set
	 */
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	/**
	 * @return the viewer
	 */
	public Viewer getViewer() {
		return viewer;
	}

	/**
	 * @param viewer the viewer to set
	 */
	public void setViewer(Viewer viewer) {
		this.viewer = viewer;
	}

	/**
	 * @return the myGameBoard
	 */
	public GameBoard getMyGameBoard() {
		return myGameBoard;
	}

	/**
	 * Gère le jeu une fois la partie démarrée en mode console.
	 * 
	 * @see #initBoard()
	 * @see #isNotGameOver()
	 * @see #isGameOver()
	 * @see #letsGo()
	 * @see #boxProcess()
	 * @see #gameEnd()
	 */
	public void start() {
		MyUtils u = new MyUtils();
		u.print("Jeu lancé");

		// création et initialisation du plateau de jeu
		initBoard();

		// tant que le joueur n'est pas arrivé au bout et qu'il est en vie, jouer un
		// tour
		while (isNotGameOver()) {
			// atendre l'appui sur entrée pour lancer le dé
			while (viewer.waitDice()) {
			}
			// lancer le dé, avancer le joueur et afficher sa position
			letsGo();

			// traitement de la case
			boxProcess();
		}
		// Vérifier comment le jeu s'est terminé
		gameEnd();
	}

	/**
	 * Crée et initialise le plateau de jeu.
	 */
	public void initBoard() {
		myGameBoard = new GameBoard();
		myGameBoard.initBoard();
	}

	/**
	 * Vérifie que le jeu n'est pas terminé : le joueur n'est pas sur la dernière case et il est en vie. 
	 * 
	 * @return booléen.
	 */
	public boolean isNotGameOver() {
		return this.myGameBoard.playerNotOnLastBox() && player1.isAlive();
	}

	/**
	 * Vérifie que le jeu est terminé : le joueur est sur la dernière case ou il est mort. 
	 * 
	 * @return booléen.
	 */
	public boolean isGameOver() {
		return !(this.myGameBoard.playerNotOnLastBox() && player1.isAlive());
	}

	/**
	 * Lance le dé, affiche le résultat et déplace le joueur.
	 * 
	 * @see GameBoard#advancePlayer()
	 * @see GameBoard#getPlayerPos()
	 */
	public void letsGo() {
		viewer.showMove(myGameBoard.advancePlayer(), myGameBoard.getPlayerPos());
	}

	/**
	 * Traite la case su rlaquelle arrive le joueur.
	 */
	public void boxProcess() {
		if (myGameBoard.getBox() == null) {
			viewer.showEvent("Case vide, on continue...");
		} else {
			myGameBoard.getBox().process(player1, viewer);
		}
	}

	/**
	 * Vérifie comment le jeu s'est terminé : soit le joueur est vivant et il est sur la dernière case (victoire), soit il est mort (défaite).
	 */
	public void gameEnd() {
		if (player1.isAlive()) {
			viewer.showDetail(player1.getName() + " est arrivé à la fin du plateau. Jeu terminé !");
		} else {
			viewer.addDetail(
					player1.getName() + " a trouvé la mort en combattant un " + myGameBoard.getBox().getName());
		}
	}

}