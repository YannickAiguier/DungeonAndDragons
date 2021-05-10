/**
 * Interface pour les viewers (console ou graphique).
 * @author yannick
 *
 */
public interface Viewer {

	/**
	 * Gère l'affichage de la progression du joueur.
	 * 
	 * @param dice : le résultat du lancer de dé.
	 * @param playerPosition : la position du joueur sur le plateau de jeu.
	 * 
	 * @see Menu#showMove(int, int)
	 * @see MyGame#showMove(int, int)
	 */
	void showMove(int dice, int playerPosition);

	/**
	 * Gère l'affichage de l'évènement sur la case.
	 * 
	 * @param s : le message à afficher.
	 * 
	 * @see Menu#showEvent(String)
	 * @see MyGame#showEvent(String)
	 */
	void showEvent(String s);

	/**
	 * Gère l'affichage des détails de l'évènement (combat, s'équiper, boire une potion,...).
	 * 
	 * @param s : le message à afficher.
	 * 
	 * @see Menu#showDetail(String)
	 * @see MyGame#showDetail(String)
	 */
	void showDetail(String s);
	
	/**
	 * Gère l'affichage des détails de l'évènement (combat, s'équiper, boire une potion,...).
	 * 
	 * @param s : le message à afficher.
	 * 
	 * @see Menu#addDetail(String)
	 * @see MyGame#addDetail(String)
	 */
	void addDetail(String s);

	/**
	 * Gère l'affichage récapitulatif de l'état du joueur et de son équipement.
	 * 
	 * @param player : le joueur à afficher.
	 * 
	 * @see Menu#showPlayer(Player)
	 * @see MyGame#showPlayer(Player)
	 */
	void showPlayer(Player player);

	/**
	 * Gère l'affichage graphique du contenu de la case.
	 * 
	 * @param box : la case à afficher.
	 * 
	 * @see MyGame#showBox(Box)
	 */
	void showBox(Box box);
	
	/**
	 * Gère l'attente d'appui sur la touche entrée pour lancer le dé en mode console.
	 * 
	 * @return booléen : false lorsque l'utilisateur a appuyé sur la touche entrée seule.
	 */
	boolean waitDice();

}