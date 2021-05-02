
public class Monster {

	int life;
	int attack;
	
	/**
	 * Constructeur
	 * @param life
	 * @param attack
	 */
	public Monster(int life, int attack) {
		this.life = life;
		this.attack = attack;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + " a " + life + " points de vie et " + attack + " points d'attaque.";
	}
	
	// méthode d'attaque d'un joueur
		public String attackPlayer(Player player) {
			player.setLife(player.getLife() - attack);
			return this.getClass().getName() + " riposte et lui inflige " + attack + " points de dégâts.";
		}
}
