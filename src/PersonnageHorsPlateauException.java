/**
 * Classe représentant l'exception provoquée lorsque le joueur dépasse la dernière case du plateau de jeu.
 * <p>
 * Elle utilise un entier pour stocker la valeur du dé et y accéder lors du traitement de l'exception.
 * </p>
 * 
 *  @see GameBoard#advancePlayer()
 * @author yannick
 *
 */
public class PersonnageHorsPlateauException extends RuntimeException {

	int dice;
	
	/**
	 * Constructeur de l'exception.
	 * 
	 * @param message : le message à renvoyer par l'exception.
	 * @param dice : la valeur du dé à stocker.
	 */
	public PersonnageHorsPlateauException(String message, int dice) {
		super(message);
		this.dice = dice;
	}

	/**
	 * @return the dice
	 */
	public int getDice() {
		return dice;
	}
	
}
