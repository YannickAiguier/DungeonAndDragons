
public class Magician extends Player {

	// constructeur, utilise aussi celui de la classe parente Player
		public Magician(String name, int life, int attack) {
			super(name, life, attack);
			this.firstAttack = new Spell("Fireball", 7);
			this.protectionType = "Philter";
		}

}
