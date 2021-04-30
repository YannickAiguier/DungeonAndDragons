
public class Warrior extends Player {

	// constructeur, utilise aussi celui de la classe parente Player
	public Warrior(String name, int life, int attack) {
		super(name, life, attack);
		this.firstAttack = new Weapon("Sword", 5);
		this.protectionType = "Shield";
	}
}
