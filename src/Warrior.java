
public class Warrior extends Player {

	// constructeur, utilise aussi celui de la classe parente Player
	public Warrior(String name, int life, int attack) {
		super(name, life, attack, new Weapon("Sword", 3), "Shield");
	}
}
