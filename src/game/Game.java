package game;

import java.util.ArrayList;
import java.util.List;

public class Game {
	static class Hero {
		public String name;
		public int force;
		public int life;

		public Hero(String name, int force, int life) throws Exception {
			this.name = name;
			this.force = force;
			if (life > 99) {
				throw new LifeToHigh(life);
			} else {
				this.life = life;
			}
			
		}
	}

	private List<Hero> heroes;

	public Game() throws Exception {
		this.heroes = new ArrayList<Hero>();
		this.heroes.add(new Hero("Thomas", 4, 8));
		this.heroes.add(new Hero("Johnn", 2, 8));
		this.heroes.add(new Hero("McGregor", 8, 8));
		this.heroes.add(new Hero("Lalanne", 3, 8));
		this.heroes.add(new Hero("Yves", 9, 7));
	}

	/**
	 * Should iterate over all heroes to find the strongest one
	 * 
	 * @return the hero which has the bigger force attribute
	 */
	public Hero findStrongest() {
		Hero result = this.heroes.get(0);
		for (Hero h: this.heroes) {
			if (h.force > result.force) {
				result = h;
			}
		}
		return result;
	}
	
	public void attackHero(Hero hero) {
		hero.life--;
	}
}