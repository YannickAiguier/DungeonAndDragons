
public class GameEngine {
	
	public GameEngine() {
	}
	
	public void start() {
		System.out.println("Jeu lancé");
		GameBoard myGameBoard = new GameBoard();
		while (myGameBoard.playerNotOnLastBox()) {
			myGameBoard.advancePlayer();
			System.out.println("Joueur en case " + (myGameBoard.getPlayerPos() + 1) + " sur 64.");
		}
		System.out.println("Le joueur est arrivé à la fin du plateau. Jeu terminé !");
	}
}
