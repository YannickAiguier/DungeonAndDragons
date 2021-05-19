package dad;

import java.util.ArrayList;

import box.Box;
import box.Dragon;
import box.Gobelin;
import box.Monster;
import box.Potion;
import box.Sorcerer;
import box.Spell;
import box.Weapon;
import viewers.MyUtils;

/**
 * Classe représentant le plateau de jeu.
 * 
 * <p>
 * Un plateau de jeu est représenté par les informations suivantes :
 * <ul>
 * <li>Un tableau de 64 cases d'objets Box.</li>
 * <li>La position du joueur sur ce plateau.</li>
 * </ul>
 * 
 * 
 * @author yannick
 *
 */
public class GameBoard {

	private ArrayList<Box> board;
	private int playerPos;

	/**
	 * Constructeur : sans paramètres, initialisation du plateau à 64 cases, joueur
	 * en case 1.
	 */
	public GameBoard() {
		this.board = new ArrayList<Box>();
		this.playerPos = 0;
	}

	/**
	 * @return the playerPos
	 */
	public int getPlayerPos() {
		return playerPos;
	}

	/**
	 * @param playerPos the playerPos to set
	 */
	public void setPlayerPos(int playerPos) {
		this.playerPos = playerPos;
	}

	/**
	 * @return the board
	 */
	public Box getBox(int i) {
		return board.get(i);
	}

	/**
	 * Retourne le contenu de la case où se trouve le joueur.
	 * 
	 * @return L'objet de type Box à l"emplacement du joueur.
	 */
	public Box getBox() {
		return board.get(playerPos);
	}

	/**
	 * Insère un objet dans la case board[index]
	 * 
	 * @param index : le numéro de la case où on veut écrire.
	 * @param box   : le contenu à insérer.
	 */
	private void setBox(int index, Box box) {
		board.set(index, box);
	}

	@Override
	public String toString() {
		return " en case " + (playerPos + 1) + " sur 64.";
	}

	public void showBoard() {
		for (Box b : board) {
			if (b == null) {
				System.out.println("Case vide.");
			} else {
				System.out.println(b.toString());
			}
		}
	}

	/**
	 * Fait avancer le joueur d'un D6 et retourne la valeur du dé.
	 * <p>
	 * Une exception est déclenchée si le joueur dépasse la dernière case, sa
	 * position est alors ramenée à la dernière case.
	 * </p>
	 * 
	 * @return Un entier, qui représente la position du joueur sur le plateau de
	 *         jeu.
	 */
	public int advancePlayer() {
		try {
			int dice = new MyUtils().rollDice(6);
			playerPos += dice;
			if (playerPos > 63) {
				throw new PersonnageHorsPlateauException("Impossible de dépasser la case 64", dice);
			}
			return dice;
		} catch (PersonnageHorsPlateauException e) {
			playerPos = 63;
			return e.getDice();
		}
	}

	/**
	 * Vérifie si le joueur n'est pas arrivé au bout du plateau.
	 * 
	 * @return Un booléen, false si le joueur est arrivé au bout du plateau sinon
	 *         true.
	 */
	public boolean playerNotOnLastBox() {
		return playerPos < 63;
	}

	/**
	 * Initialise le plateau de jeu.
	 * <p>
	 * Cases paires = rien, cases impaires = surprise !
	 * </p>
	 */
	public void oldInitBoard() {
		// pour l'instant les objets seront placés toujours à la même place
		// trésor et monstre une fois sur deux
		for (int i = 1; i < 63; i += 4) {
			this.setBox(i, createMonster());
		}
		for (int i = 3; i < 63; i += 8) {
			this.setBox(i, createPotion());
		}
		for (int i = 7; i < 63; i += 8) {
			if (new MyUtils().rollDice(2) == 1) {
				this.setBox(i, createSpell());
			} else {
				this.setBox(i, createWeapon());
			}
		}
	}
	
	public void initBoard() {
		// créer 64 cases
		initEmptyBoard();
		
		// placer les monstres
		int[] dragonsPositions = {45, 52, 56, 62};
		for (int i: dragonsPositions) {
			this.board.set(i,  new Dragon());
		}
		int[] sorcerersPositions = {10, 20, 25, 32, 35, 36, 37, 40, 44, 47};
		for (int i: sorcerersPositions) {
			this.board.set(i,  new Sorcerer());
		}
		
		for (int i = 3; i < 31; i += 3) {
			this.board.set(i,  new Gobelin());
		}
		
		// placer les surprises
		int[] clubsPositions = {2, 11, 15, 22, 38};
		for (int i: clubsPositions) {
			this.board.set(i,  new Weapon("Massue", "club.png", 3));
		}
		int[] swordsPositions = {19, 26, 42, 53};
		for (int i: swordsPositions) {
			this.board.set(i,  new Weapon("Epée", "sword.png", 5));
		}
		int[] lightningsPositions = {1, 4, 8, 17 ,23};
		for (int i: lightningsPositions) {
			this.board.set(i,  new Spell("Lightning", "lightning.png", 2));
		}
		int[] fireballsPositions = {48, 49};
		for (int i: fireballsPositions) {
			this.board.set(i,  new Spell("Fireball", "fireball.png", 7));
		}
		int[] potionsPositions = {7, 13, 31, 33, 39, 43};
		for (int i: potionsPositions) {
			this.board.set(i,  new Potion("Potion de soin", "potion.png", 2));
		}
		int[] bigPotionsPositions = {28, 41};
		for (int i: bigPotionsPositions) {
			this.board.set(i,  new Potion("Grande potion de soin", "big_potion.png", 5));
		}
		
		this.showBoard();
	}

	public void initEmptyBoard() {
		for (int i = 1; i < 65; i++) {
			this.board.add(null);
		}
	}

	/**
	 * Tire au hasard un monstre, l'instancie et le renvoie.
	 * <p>
	 * D3 : 1 = gobelin, 2 = sorcier, 3 = dragon.
	 * </p>
	 * 
	 * @return Un objet Monster.
	 */
	private Monster createMonster() {
		switch (new MyUtils().rollDice(3)) {
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

	/**
	 * Tire au hasard une potion, l'instancie et la renvoie.
	 * <p>
	 * D3 : 1/2 = potion (2) , 3 = grande potion (5).
	 * </p>
	 * 
	 * @return Un objet Potion.
	 */
	private Potion createPotion() {
		switch (new MyUtils().rollDice(3)) {
		case 1:
		case 2:
			return new Potion("Potion de soin", "potion.png", 2);
		case 3:
			return new Potion("Grande potion de soin", "big_potion.png", 5);
		default:
			return null;
		}

	}

	/**
	 * Tire au hasard une arme, l'instancie et la renvoie.
	 * <p>
	 * D2 : 1 = massue, 2 = épée.
	 * </p>
	 * 
	 * @return Un objet Weapon.
	 */
	private Weapon createWeapon() {
		switch (new MyUtils().rollDice(2)) {
		case 1:
			return new Weapon("Massue", "club.png", 3);
		case 2:
			return new Weapon("Epée", "sword.png", 5);
		default:
			return null;
		}
	}

	/**
	 * Tire au hasard un sort, l'instancie et le renvoie.
	 * <p>
	 * D2 : 1 = éclair, 2 = boule de feu.
	 * </p>
	 * 
	 * @return Un objet Spell.
	 */
	private Spell createSpell() {
		switch (new MyUtils().rollDice(2)) {
		case 1:
			return new Spell("Lightning", "lightning.png", 2);
		case 2:
			return new Spell("Fireball", "fireball.png", 7);
		default:
			return null;
		}
	}

	public void createBox(int index, String name, String img, int life, int attack, String forclass) {
		Box b = null;
		switch (name) {
		case "Gobelin":
			b = new Gobelin();
			break;
		case "Sorcerer":
			b = new Sorcerer();
			break;
		case "Dragon":
			b = new Dragon();
			break;
		case "Epée":
			b = new Weapon("Epée", "sword.png", 5);
			break;
		case "Massue":
			b = new Weapon("Massue", "club.png", 3);
			break;
		case "Lightning":
			b = new Spell("Lightning", "lightning.png", 2);
			break;
		case "Fireball":
			b = new Spell("Fireball", "fireball.png", 7);
			break;
		case "Potion de soin":
			b = new Potion("Potion de soin", "potion.png", 2);
			break;
		case "Grande potion de soin":
			b = new Potion("Grande potion de soin", "big_potion.png", 5);
			break;
		default:
			b = null;
			break;
		}
		this.board.set(index,b);
	}

	public void createNullBox(int index) {
		this.board.set(index, null);
	}

}
