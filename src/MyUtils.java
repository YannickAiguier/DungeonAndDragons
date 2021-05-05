import java.util.Scanner;

public class MyUtils {

	public MyUtils() {
		super();
	}

	// fonction pout lancer un dé à 'sides' faces
	public int rollDice(int sides) {
		return (int) (Math.random() * sides) + 1;
	}

	// fonction pour afficher un texte dans la console
	public void print(String s) {
		System.out.println(s);
	}

	// fonction pour poser une question et saisir un texte en réponse
	public String getText(String s) {
		System.out.print(s);
		Scanner keyboard = new Scanner(System.in);
		String read = keyboard.nextLine();
		return read;
	}

	// fonction pour poser une question et saisir un entier en réponse
	public int getInt(String s) {
		System.out.print(s);
		Scanner keyboard = new Scanner(System.in);
		int read = keyboard.nextInt();
		keyboard.nextLine();
		return read;
	}
}
