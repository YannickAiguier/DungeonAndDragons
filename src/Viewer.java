
public interface Viewer {

	void showMove(int dice, int playerPosition);

	void showEvent(String s);

	void showDetail(String s);

	void showPlayer(Player player);

	void showBox(Box box);

	void playRound(int dice, int playerPosition);

}