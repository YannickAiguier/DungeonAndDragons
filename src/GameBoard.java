
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
			this.setBox(i, createPotion());
		}
		for (int i = 7; i < 63; i += 8) {
			if (MyUtils.rollDice(2) == 1) {
				this.setBox(i, createSpell());
			} else {
				this.setBox(i, createWeapon());
			}
		}
	}

	// méthode qui tire au hasard un monstre, l'instancie et le renvoie
	// D3 : 1 = gobelin, 2 = sorcier, 3 = dragon
	private Monster createMonster() {
		switch (MyUtils.rollDice(3)) {
		case 1:
			return (Monster) new Gobelin();
		case 2:
			return (Monster) new Sorcerer();
		case 3:
			return (Monster) new Dragon();
		default:
			return null;
		}
	}

	// méthode qui tire au hasard une potion, l'instancie et la renvoie
	// D3 : 1/2 = potion (2) , 3 = grande potion (5)
	private Potion createPotion() {
		switch (MyUtils.rollDice(3)) {
		case 1:
		case 2:
			return new Potion("potion de soin", 2);
		case 3:
			return new Potion("grande potion de soin ", 5);
		default:
			return null;
		}

	}
	
	// méthode qui tire au hasard une arme, l'instancie et la renvoie
	// D2 : 1 = massue, 2 = épée
	private Weapon createWeapon() {
		switch (MyUtils.rollDice(2)) {
		case 1:
			return new Weapon("Club", 3);
		case 2:
			return new Weapon("Sword", 5);
		default:
			return null;
		}
	}
	
	// méthode qui tire au hasard un sort, l'instancie et le renvoie
		// D2 : 1 = éclair, 2 = boule de feu
		private Spell createSpell() {
			switch (MyUtils.rollDice(2)) {
			case 1:
				return new Spell("Lightning", 2);
			case 2:
				return new Spell("Fireball", 7);
			default:
				return null;
			}
		}

}
