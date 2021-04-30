
public final class MyUtils {
	
	public static int rollDice(int sides) {
		return (int)(Math.random()*sides) + 1;
	}

	public static void main(String[] args) {
		for (int i=0; i<10; i++) {
			System.out.println(rollDice(6));
		}
		
	}
}
