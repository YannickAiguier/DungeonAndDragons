
public interface Viewer {

	void showMove(int dice, int playerPosition);

	void showEvent(String s);

	void showDetail(String s);
	
	void addDetail(String s);

	void showPlayer(Player player);

	void showBox(Box box);
	
	boolean waitDice();

}