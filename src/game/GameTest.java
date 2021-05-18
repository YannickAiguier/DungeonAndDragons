package game;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import game.Game.Hero;

/*
 * Compétences à valider en plus:
 * - Manipuler l'environnement de développement
 * - Débugger un programme
 * - Générer la Javadoc
 */
class GameTest {
	/*
	 * Compétences: - Programmer avec des variables - Programmer une boucle -
	 * Programmer une condition
	 */
	@Test
	void testShouldFindTheStrongest() throws Exception {
		Game game = new Game();
		Hero hero = game.findStrongest();
		Assert.assertEquals("Yves", hero.name);
		Assert.assertEquals(9, hero.force);
		Assert.assertEquals(7, hero.life);
	}

	/*
	 * Compétences: - Définir et utiliser une méthode
	 */
	@Test
	void testShouldRemoveOneLifePointWhenAttacked() throws Exception {
		Game game = new Game();
		Hero hero = game.findStrongest();
		game.attackHero(hero);
		Assert.assertEquals(6, hero.life);
	}

	/*
	 * Compétences: - Définir des classes - Instancier des objets - Gérer les
	 * exceptions
	 */
	@Test
	void testShouldDenyLifePointsOver99() throws Exception {
		try {
			new Hero("Sergueï", 20, 100);
			Assert.fail("No exception has been raised");
		} catch (Exception error) {
			Assert.assertEquals("100 life points is too much!", error.getMessage());
		}
	}

	/*
	 * Bonus
	 */
	@Test
	void testShouldHeroShouldBeComparable() throws Exception {
		Game game = new Game();
		Hero hero = game.findStrongest();
		Assert.assertEquals(new Hero("Yves", 9, 7), hero);
	}
}