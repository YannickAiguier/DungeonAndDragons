
public class Magician extends Player {

	// constructeur, utilise aussi celui de la classe parente Player
		public Magician(String name, int life, int baseAttack) {
			super(name, life, baseAttack);
			this.protectionType = "Philter";
		}

}
