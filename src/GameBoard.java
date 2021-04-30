
public class GameBoard {
	
	int[] board;
	int playerPos;
	
	// Constructeur : initialisation du plateau à 64 cases, joueur en case 1
	public GameBoard() {
		board = new int[64];
		playerPos = 0;
	}

	public int getPlayerPos() {
		return playerPos;
	}

	public void setPlayerPos(int playerPos) {
		this.playerPos = playerPos;
	}

	@Override
	public String toString() {
		return "Joueur en case " + (playerPos + 1) + " sur 64.";
	}
	
	// méthode qui fait avancer le joueur d'un D6 
	public void advancePlayer() {
		playerPos += MyUtils.rollDice(6);
	}
		// méthode qui vérifie si un joueur est arrivé au bout du plateau
	public boolean playerOnLastBox() {
		if (playerPos > 63) {
			playerPos = 63;
		}
		return playerPos == 63;
	}
		
}
