
public class GameBoard {

	Object[] board;
	int playerPos;

	// Constructeur : initialisation du plateau à 64 cases, joueur en case 1
	public GameBoard() {
		board = new Object[64];
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
		return playerPos < 63;
	}

	// méthode qui retourne le contenu de la case où se trouve le joueur
	public Object getBox() {
		return board[playerPos];
	}

	// méthode qui met un objet dans la case board[index]
	private void setBox(int index, Object object) {
		board[index] = object;
	}

	// méthode d'initialisation du plateau de jeu
	public void initBoard() {
		// pour l'instant les objets seront placés toujours à la même place
		// trésor et monstre une fois sur deux
		for (int i = 1; i < 63; i += 4) {
			this.setBox(i, createMonster());
		}
		for (int i = 3; i < 63; i += 8) {
			this.setBox(i, new Potion("Soin", 3));
		}
		for (int i = 7; i < 63; i += 8) {
			if ((int) (Math.random() * 2) == 0) {
				this.setBox(i, new Spell("ThunderBolt", 4));
			} else {
				this.setBox(i, new Weapon("Super Epée", 4));
			}
		}
	}

	// méthode qui tire au hasard un monstre, l'initialise et le renvoie
	// D6 : 1/2 = gobelin, 3/4 = sorcier, 5/6 = dragon
	private Monster createMonster() {
		switch (MyUtils.rollDice(6)) {
		case 1:
		case 2:
			return (Monster) new Gobelin();
		case 3:
		case 4:
			return (Monster) new Sorcerer();
		case 5:
		case 6:
			return (Monster) new Dragon();
		default:
			return null;
		}
	}

}
