
public class Warrior extends Player {

	// constructeur, utilise aussi celui de la classe parente Player
	public Warrior(String name) {
		super(name, 5, 10, 5, new Weapon("Rien", 0), "Shield");
	}
}
