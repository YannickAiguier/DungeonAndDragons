import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GraphArea extends JLabel {
	
	public GraphArea() {
		super();
	}
	
	public void setImg(String img, int width, int height) {
		try {
			File file = new File("/home/yannick/campus/DungeonsAndDragons/resources/images/" + img);
			Image resizedImage = ImageIO.read(file).getScaledInstance(width, height, Image.SCALE_SMOOTH);
			this.setIcon(new ImageIcon(resizedImage));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void removeImg() {
		this.setIcon(null);
	}
}
