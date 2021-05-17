package dad;
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

	private Box[] board;
	private int playerPos;

	/**
	 * Constructeur : sans paramètres, initialisation du plateau à 64 cases, joueur
	 * en case 1.
	 */
	public GameBoard() {
		this.board = new Box[64];
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
		return board[i];
	}

	/**
	 * Retourne le contenu de la case où se trouve le joueur.
	 * 
	 * @return L'objet de type Box à l"emplacement du joueur.
	 */
	public Box getBox() {
		return board[playerPos];
	}

	/**
	 * Insère un objet dans la case board[index]
	 * 
	 * @param index : le numéro de la case où on veut écrire.
	 * @param box   : le contenu à insérer.
	 */
	private void setBox(int index, Box box) {
		board[index] = box;
	}

	@Override
	public String toString() {
		return " en case " + (playerPos + 1) + " sur 64.";
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
	 * <p>Cases paires = rien, cases impaires = surprise !</p>
	 */
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
			if (new MyUtils().rollDice(2) == 1) {
				this.setBox(i, createSpell());
			} else {
				this.setBox(i, createWeapon());
			}
		}
	}

	/**
	 * Tire au hasard un monstre, l'instancie et le renvoie.
	 * <p>D3 : 1 = gobelin, 2 = sorcier, 3 = dragon.</p>
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
	 * <p>D3 : 1/2 = potion (2) , 3 = grande potion (5).</p>
	 * 
	 * @return Un objet Potion.
	 */
	private Potion createPotion() {
		switch (new MyUtils().rollDice(3)) {
		case 1:
		case 2:
			return new Potion("potion de soin", "potion.png", 2);
		case 3:
			return new Potion("grande potion de soin", "big_potion.png", 5);
		default:
			return null;
		}

	}

	/**
	 * Tire au hasard une arme, l'instancie et la renvoie.
	 * <p>D2 : 1 = massue, 2 = épée.</p>
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
	 * <p>D2 : 1 = éclair, 2 = boule de feu.</p>
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

}
