import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;


public class mainGraphics {

	public static void main(String[] args) throws IOException {

		// déclarations et instanciations
		JFrame myWindow = new JFrame("DaD");
		JPanel myPanel = new JPanel();
		JLabel myLabel = new JLabel("Texte à afficher");
		JTextField myTextField = new JTextField("");
		JButton myButton = new JButton("Cliquez");
		
		BufferedImage myImage = ImageIO.read(new File("orc.png"));
		JLabel pictLabel = new JLabel(new ImageIcon(myImage));
		

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Action");
		JMenu menuItem1 = new JMenu("Nouveau Personnage");
		JMenuItem menuItem2 = new JMenuItem("Lancer le jeu");
		JMenuItem menuItem3 = new JMenuItem("Quitter");
		JMenuItem warriorItem = new JMenuItem("Guerrier");
		JMenuItem MagicianItem = new JMenuItem("Magicien");

		// paramétrage
		myWindow.setSize(1000, 800);
		myWindow.setResizable(false);
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myWindow.setLocationRelativeTo(null);
		myWindow.setIconImage(myImage);
		
		myPanel.setBackground(Color.black);
		myLabel.setForeground(Color.white);
		myTextField.setColumns(20);

		// TODO décommenter ligne ci-dessous à la fin des tests
		//menuItem2.setEnabled(false);

		// listeners
		myButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clic clic !!");
			}
		});

		menuItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Ok Lancer le jeu");
			}
		});

		menuItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Ok quitter le jeu");
				int choose = JOptionPane.showConfirmDialog(myWindow, "Quitter le jeu ?", "Quitter", JOptionPane.YES_NO_OPTION);
				if (choose == 0) {
					System.exit(0);
				}
			}
		});
		
		warriorItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Ok créer guerrier");
			}
		});
		
		MagicianItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Ok créer magicien");
			}
		});

		// construction
		myPanel.add(myLabel);
		myPanel.add(myTextField);
		myPanel.add(myButton);
		myPanel.add(pictLabel);

		menu.add(menuItem1);
		menu.add(menuItem2);
		menu.add(menuItem3);
		menuItem1.add(warriorItem);
		menuItem1.add(MagicianItem);
		menuBar.add(menu);

		myWindow.setJMenuBar(menuBar);
		myWindow.setContentPane(myPanel);

		myWindow.setVisible(true);
		

	}

}
