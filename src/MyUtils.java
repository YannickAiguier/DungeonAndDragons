import java.util.Scanner;

/**
 * Classe qui regroupe les fonctions utilitaires pour le jeu.
 * <p>Lancer de dé à x faces, affichage/saisie de texte en mode console.</p>
 * 
 * @author yannick
 *
 */
public class MyUtils {

	public MyUtils() {
		super();
	}

	
	/**
	 * Lance un dé à x faces et renvoie le résultat.
	 * 
	 * @param sides : le nombre de faces du dé.
	 * @return Le résultat du lancer de dé.
	 */
	public int rollDice(int sides) {
		return (int) (Math.random() * sides) + 1;
	}

	
	/**
	 * Affiche un message dans la console.
	 * 
	 * @param s : le message à afficher.
	 */
	public void print(String s) {
		System.out.println(s);
	}

	
	/**
	 * En mode console, affiche une question et retourne la réponse saisie.
	 * 
	 * @param s : la question.
	 * @return La chaine de cractères correspondant à la réponse à la question.
	 */
	public String getText(String s) {
		System.out.print(s);
		Scanner keyboard = new Scanner(System.in);
		String read = keyboard.nextLine();
		return read;
	}

	
	/**
	 * En mode console, pose une question et retourne l'entier saisi en réponse.
	 * 
	 * @param s : la question.
	 * @return Un entier correspondant à la réponse à la question.
	 */
	public int getInt(String s) {
		System.out.print(s);
		Scanner keyboard = new Scanner(System.in);
		int read = keyboard.nextInt();
		keyboard.nextLine();
		return read;
	}
}
