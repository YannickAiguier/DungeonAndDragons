
public class Warrior extends Player {

	// constructeur, utilise aussi celui de la classe parente Player
	public Warrior(String name, int life, int baseAttack) {
		super(name, life, baseAttack);
		this.protectionType = "Shield";
	}
}
