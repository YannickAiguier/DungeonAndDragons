
public class Warrior extends Player {

	// constructeur, utilise aussi celui de la classe parente Player
	public Warrior(String name) {
		super(name, "warrior.png", 5, 10, 5, new Weapon("Rien", "fist.png", 0), "Shield");
	}
}
