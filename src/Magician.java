
public class Magician extends Player {

	// constructeur, utilise aussi celui de la classe parente Player
		public Magician(String name) {
			super(name, 3, 6, 8, new Spell("Main", 0), "Philter");
		}

}
