package game;

public class LifeToHigh extends Exception {
	
	public LifeToHigh(int life) {
		
		super(life + " life points is too much!");
		
	}
}
