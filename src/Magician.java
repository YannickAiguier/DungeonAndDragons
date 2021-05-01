
public class Magician extends Player {

	// constructeur, utilise aussi celui de la classe parente Player
		public Magician(String name, int life, int attack) {
			super(name, life, attack, new Spell("Fireball", 7), "Philter");
		}

}
