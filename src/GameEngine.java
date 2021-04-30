
public class GameEngine {
	
	Player player1;
	
	public GameEngine(Player player) {
		player1 = player;
	}
	
	public void start() {
		System.out.println("Jeu lancé");
		GameBoard myGameBoard = new GameBoard();
		while (myGameBoard.playerNotOnLastBox()) {
			myGameBoard.advancePlayer();
			System.out.println(player1.getName() + myGameBoard);
		}
		System.out.println(player1.getName() + " est arrivé à la fin du plateau. Jeu terminé !");
	}
}
