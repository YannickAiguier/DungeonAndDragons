import java.util.Arrays;

public class GameBoard {

	Object[] board;
	int playerPos;

	// Constructeur : initialisation du plateau à 64 cases, joueur en case 1
	public GameBoard() {
		board = new Object[64];
		System.out.println(Arrays.toString(board));
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
		return " en case " + (playerPos + 1) + " sur 64.";
	}

	// méthode qui fait avancer le joueur d'un D6
	public void advancePlayer() {
		try {
			playerPos += MyUtils.rollDice(6);
			if (playerPos > 63) {
				throw new PersonnageHorsPlateauException("Impossible de dépasser la case 64");
			}
		} catch (PersonnageHorsPlateauException e) {
			playerPos = 63;
		}
	}

	// méthode qui vérifie si un joueur est arrivé au bout du plateau
	public boolean playerNotOnLastBox() {
		return playerPos != 63;
	}
	
	// méthode qui retourne le contenu de la case où se trouve le joueur
	public Object getBox() {
		return board[playerPos];
	}
	
	// méthode qui met un objet dans la case board[index]
	public void setBox(int index, Object object) {
		board[index] = object;
	}

}
