
public class PersonnageHorsPlateauException extends RuntimeException {

	int dice;
	
	public PersonnageHorsPlateauException(String message, int dice) {
		super(message);
		this.dice = dice;
	}
	
	public int getDice() {
		return dice;
	}
	
}
