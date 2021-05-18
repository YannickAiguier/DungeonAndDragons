package viewers;
import box.Box;
import dad.GameEngine;
import player.Magician;
import player.Player;
import player.Warrior;

/**
 * Classe pour gérer le menu en mode console, implémente l'interface Viewer pour
 * ce mode.
 * <p>
 * En tant que Menu et Viewer cette classe utilise :
 * <ul>
 * <li>Un joueur, qui sera créé avant de lancer le moteur de jeu.</li>
 * <li>Un objet MyUtils, pour les fonctions de lancer de dé à x faces et de
 * saisie/affichage dans la console.</li>
 * </ul>
 *
 * 
 * @see Viewer
 * @see MyUtils
 * @author yannick
 *
 */
public class Menu implements Viewer {

	Player player;
	MyUtils u;

	/**
	 * Constructeur, initialise l'objet MyUtils.
	 */
	public Menu() {
		u = new MyUtils();
	}

	/**
	 * Gère le premier niveau de menu. Appelée par Main lors d'un jeu en mode
	 * console.
	 */
	public void start() {
		int choix = 0;
		while (choix < 1 || choix > 2) {
			u.print(("1) Nouveau personnage"));
			u.print(("2) Quitter le jeu"));
			choix = u.getInt("Votre choix : ");
			switch (choix) {
			case 1:
				createMenu();
				choix = 0;
				break;
			case 2:
				u.print("Fin du programme.");
				System.exit(0);
				break;
			default:
				u.print("Choisissez 1 ou 2...");
				break;
			}
		}
	}

	/**
	 * Gère le deuxième niveau de menu, création d'un joueur.
	 */
	private void createMenu() {
		int choix = 0;
		while (choix < 1 || choix > 3) {
			u.print("1) Guerrier");
			u.print("2) Magicien");
			u.print("3) Retour");
			choix = u.getInt("Votre choix : ");
			switch (choix) {
			case 1:
				u.print("Création d'un guerrier");
				createPlayer("Warrior");
				playerMenu();
				choix = 0;
				break;
			case 2:
				u.print("Création d'un magicien");
				createPlayer("Magician");
				playerMenu();
				choix = 0;
				break;
			case 3:
				choix = 0;
				return;
			default:
				u.print("Choisissez 1, 2 ou 3...");
				break;
			}
		}
	}

	/**
	 * Gère le troisième niveau de menu, afficher/modifier le joueur créé ou lancer
	 * le moteur de jeu (GameEgine.start()).
	 * 
	 * @see GameEngine#start()
	 */
	private void playerMenu() {
		int choix = 0;
		while (choix != 1 && choix != 2) {
			u.print("1) Afficher les caractéristiques du personnage");
			u.print("2) Modifier les caractéristiques du personnage");
			u.print("3) Retour");
			if (player != null) {
				u.print("4) Démarrer le jeu");
			}
			choix = u.getInt("Votre choix : ");
			switch (choix) {
			case 1:
				u.print("Caractéristiques de votre personnage : ");
				u.print(player.toString());
				choix = 0;
				break;
			case 2:
				createPlayer(player.getClass().getSimpleName());
				choix = 0;
				break;
			case 3:
				choix = 0;
				return;
			case 4:
				GameEngine myEngine = new GameEngine(player, this);
				myEngine.start();
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}

	
	/**
	 * Crée un nouveau joueur.
	 * 
	 * @param myClass : la classe de personnage à créer (Warrior ou Magician).
	 */
	private void createPlayer(String myClass) {
		String name = u.getText("Nom de votre personnage : ");
		if (myClass == "Warrior") {
			player = new Warrior(name);
		}
		if (myClass == "Magician") {
			player = new Magician(name);
		}
		u.print("Personnage créé.");
	}

	/**
	 * Gère l'affichage de la progression du joueur.
	 * 
	 * @param dice : le résultat du lancer de dé.
	 * @param playerPosition : la position du joueur sur le plateau de jeu.
	 */
	@Override
	public void showMove(int dice, int playerPosition) {
		u.print("Vous avancez de " + dice + " case(s) et arrivez en case " + playerPosition + ".");
	}

	/**
	 * Gère l'affichage de l'évènement sur la case.
	 * 
	 * @param s : le message à afficher.
	 */
	@Override
	public void showEvent(String s) {
		u.print(s);

	}

	/**
	 * Gère l'affichage des détails de l'évènement (combat, s'équiper, boire une potion,...).
	 * 
	 * @param s : le message à afficher.
	 */
	@Override
	public void showDetail(String s) {
		u.print(s);

	}

	/**
	 * Gère l'affichage des détails de l'évènement (combat, s'équiper, boire une potion,...).
	 * <p>Surtout utile en mode graphique, présent en mode console pour des raisons de compatibilité avec l'interface Viewer.</p>
	 * 
	 * @param s : le message à afficher.
	 * @see MyGame#addDetail(String)
	 */
	@Override
	public void addDetail(String s) {
		u.print(s);

	}

	/**
	 * Gère l'affichage récapitulatif de l'état du joueur et de son équipement.
	 * 
	 * @param player : le joueur à afficher.
	 */
	@Override
	public void showPlayer(Player player) {
		u.print(player.getName() + " a " + player.getLife() + " points de vie, se bat avec "
				+ player.getFirstAttack().getName() + " pour une force d'attaque de "
				+ (player.getAttack() + player.getFirstAttack().getAttack()) + ".");

	}

	/**
	 * Gère l'affichage graphique du contenu de la case.
	 * <p>Inutile en mode console, implémentée pour compatibilité avec l'interface Viewer.</p>
	 * 
	 * @param box : la case à afficher.
	 * @see MyGame#showBox(Box)
	 */
	@Override
	public void showBox(Box box) {
		// rien à faire en mode texte
	}

	/**
	 * Gère l'attente d'appui sur la touche entrée pour lancer le dé.
	 * 
	 * @return booléen : false lorsque l'utilisateur a appuyé sur la touche entrée seule.
	 */
	@Override
	public boolean waitDice() {
		boolean wait = true;
		while (wait) {
			String read = u.getText("Appuyez sur Entrée pour avancer...");
			if (read.isEmpty()) {
				return false;
			} else {
				u.print("Merci de juste appuyer sur Entrée");
			}
		}
		return true;
	}

}