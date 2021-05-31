package player;
import box.BadSpirit;
import box.Dragon;
import box.MeanOfAttack;
import box.Monster;

/**
 * Classe abstraite représentant le joueur.
 * <p>Un joueur est représenté par :
 * <ul>
 * <li>Un nom.</li>
 * <li>Une image (seulement en mode graphique).</li>
 * <li>De la vie.</li>
 * <li>Une vie maximum.</li>
 * <li>Une force d'attaque de base.<li>
 * <li>Un moyen d'attaque adapté à sa classe.</li>
 * <li>Un moyen de protection adapté à sa classe.</li>
 * </ul>
 * 
 * 
 * @see Magician
 * @see Warrior
 * @see MeanOfAttack
 * @author yannick
 *
 */
public abstract class Player {

	protected String name;
	protected String img;
	protected int life;
	protected int maxLife;
	protected int attack;
	protected MeanOfAttack[] MoAs = new MeanOfAttack[2];
	protected String protectionType;
	protected int chosenSlot;
	protected boolean doubleDamage;

	/**
	 * Constructeur sans paramètre
	 */
	public Player() {
		this.name = null;
		this.img = null;
		this.life = 0;
		this.maxLife = 0;
		this.attack = 0;
		this.protectionType = null;
		this.chosenSlot = 0;
		this.doubleDamage = false;
	}

	/**
	 * Constructeur avec le nom.
	 * 
	 * @param name : le nom du joueur.
	 */
	public Player(String name) {
		this();
		this.name = name;
	}

	/**
	 * Constructeur complet avec tous les paramètres.
	 * 
	 * @param name : le nom du joueur.
	 * @param img : le nom du fichier image correspondant au joueur.
	 * @param life : la vie du joueur.
	 * @param maxLife : la vie maximum du joueur.
	 * @param attack : la force d'attaque de base du joueur.
	 * @param firstAttack : le moyen d'attaque du joueur.
	 * @param protectionType : le type de protetion du joueur.
	 */
	public Player(String name, String img, int life, int maxLife, int attack, MeanOfAttack firstAttack, MeanOfAttack secondAttack, String protectionType) {
		this.name = name;
		this.img = img;
		this.life = life;
		this.maxLife = maxLife;
		this.attack = attack;
		this.MoAs[0] = firstAttack;
		this.MoAs[1] = secondAttack;
		this.protectionType = protectionType;
		this.chosenSlot = 0;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	

	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * @return the life
	 */
	public int getLife() {
		return life;
	}

	/**
	 * @param life the life to set
	 */
	public void setLife(int life) {
		this.life = life;
	}
	
	

	/**
	 * @return the maxLife
	 */
	public int getMaxLife() {
		return maxLife;
	}

	/**
	 * @param maxLife the maxLife to set
	 */
	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	/**
	 * @return the attack
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * @param attack the attack to set
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public MeanOfAttack getMoa(int index) {
		return this.MoAs[index];
	}
	
	public void setMoa(int index, MeanOfAttack moa) {
		this.MoAs[index] = moa;
	}
	
	/**
	 * @return the protectionType
	 */
	public String getProtectionType() {
		return protectionType;
	}

	/**
	 * @param protectionType the protectionType to set
	 */
	public void setProtectionType(String protectionType) {
		this.protectionType = protectionType;
	}
	

	/**
	 * @return the chosenSlot
	 */
	public int getChosenSlot() {
		return chosenSlot;
	}

	/**
	 * @param chosenSlot the chosenSlot to set
	 */
	public void setChosenSlot(int chosenSlot) {
		this.chosenSlot = chosenSlot;
	}
	
	

	/**
	 * @return the doubleDamage
	 */
	public boolean isDoubleDamage() {
		return doubleDamage;
	}

	/**
	 * @param doubleDamage the doubleDamage to set
	 */
	public void setDoubleDamage(boolean doubleDamage) {
		this.doubleDamage = doubleDamage;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", img=" + img + ", life=" + life + ", maxLife=" + maxLife + ", attack="
				+ attack + ", protectionType=" + protectionType + "]";
	}

	
	/**
	 * Gère une attaque du joueur sur un monstre.
	 * 
	 * @param monster : le monstre attaqué
	 * @return Une chaine de cractères correspondant au résultat du combat.
	 */
	public String attackMonster(Monster monster, MeanOfAttack attack) {
		int dmg;
		if (monster instanceof Dragon && attack.getName().equals("Bow")) {
			dmg = this.attack + attack.getAttack() + 2;
		} else if (monster instanceof BadSpirit && attack.getName().equals("Invisibility")) {
			dmg = this.attack + attack.getAttack() + 3;
		} else {
			dmg = this.attack + attack.getAttack();
		}		
		dmg = verifDoubleDamage(dmg);
		monster.setLife(Math.max(0, monster.getLife() - dmg));
		return name + " attaque un " + monster.getName() + " et lui inflige " + dmg + " points de dégâts.";
	}

	
	private int verifDoubleDamage(int damage) {
		if (isDoubleDamage()) {
			setDoubleDamage(false);
			return damage * 2;
		}
		return damage;
	}

	/**
	 * Change le moyen d'attaque du joueur si celui trouvé est plus intéressant que l'actuel.
	 * 
	 * @param item : le moyen d'attaque trouvé.
	 * @return Une chaine de cractères correspondant au changement (ou pas) de l'équipement. 
	 */
	public String changeItem(MeanOfAttack item, int index) {
		this.MoAs[index] = item;
		return name + " s'équipe de " + item.getName() + " qui inflige " + item.getAttack() + " points de dégâts.";
	}
	
	
	/**
	 * Vérifie si le joueur est en vie.
	 * 
	 * @return booléen : true si le joueur est vivant, false s'il est mort.
	 */
	public boolean isAlive() {
		return life > 0;
	}

}