package box;
import player.Player;
import viewers.Viewer;

/**
 * Classe abstraite représentant les cases du plateau de jeu.
 * 
 * <p>Une case est représentée par les informations suivantes :
 * <ul>
 * <li>Un nom, qui permet de savoir ce qu'il y a sur la case.</li>
 * <li>Le nom du fichier image la représentant (seulement en mode graphique).</li>
 * <li>De la vie, soit à ajouter à celle du héros (potion), soit indiquant la vie du monstre présent sur la case.</li>
 * <li>Une force d'attaque, représentant celle de l'arme trouvée ou celle du monstre présent sur la case.</li>
 * <li>Une "classe d'utilisation", permettant de savoir quel type de héro peut utiliser l'arme ou le sort trouvé.</li>
 * </ul>
 * 
 * 
 * @see Monster
 * @see MeanOfAttack
 * @see Potion
 * @author yannick
 * 
 */
public abstract class Box {
	
	protected String name;
	protected String img;
	protected int life;
	protected int attack;
	protected String forClass;	

	/**
	 * Constructeur de la classe
	 * 
	 * @param name : nom de l'objet qi'il y a sur la case.
	 * @param img : le nom du fichier image la représentant.
	 * @param life : de la vie, soit à ajouter à celle du héros (potion), soit indiquant la vie du monstre présent sur la case.
	 * @param attack : une force d'attaque, représentant celle de l'arme trouvée ou celle du monstre présent sur la case.
	 * @param forClass : une "classe d'utilisation", permettant de savoir quel type de héro peut utiliser l'arme ou le sort trouvé.
	 */
	public Box(String name, String img, int life, int attack, String forClass) {
		this.name = name;
		this.img = img;
		this.life = life;
		this.attack = attack;
		this.forClass = forClass;
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



	/**
	 * @return the forClass
	 */
	public String getForClass() {
		return forClass;
	}



	/**
	 * @param forClass the forClass to set
	 */
	public void setForClass(String forClass) {
		this.forClass = forClass;
	}



	@Override
	public String toString() {
		return "Box [name=" + name + ", img=" + img + ", life=" + life + ", attack=" + attack + ", forClass=" + forClass
				+ "]";
	}

	/**
	 * Gère le traitement d'une case de plateau de jeu lorsque le joueur s'y arrête.
	 * 
	 * @param player : le joueur sur la case.
	 * @param viewer : l'objet Viewer auquel passer les résultats pour affichage.
	 */
	public abstract void process(Player player, Viewer viewer);
	
}
