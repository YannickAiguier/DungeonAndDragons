
public class Magician extends Player {

	// constructeur, utilise aussi celui de la classe parente Player
		public Magician(String name) {
			super(name, "magician.png", 3, 6, 8, new Spell("Rien", "primary_attack.png", 0), "Philter");
		}

}
