
public abstract class MyUtils {
	
	// fonction pout lancer un dé à 'sides' faces
	public static int rollDice(int sides) {
		return (int)(Math.random()*sides) + 1;
	}
	
}
