package userinterface;

import java.awt.*;

import javax.swing.*;

public class StartUp extends JPanel{

	public JButton startGame;
	public  JLabel background;

	public StartUp(String name) {

		setLayout(new BorderLayout());
		//START GAME BUTTON
		startGame = new JButton("");
		add(startGame);
		startGame.setBounds(1060,600,190,67);
		startGame.setIcon(new ImageIcon("./img/startgame.png"));
		startGame.setOpaque(false);
		startGame.setVisible(true);

		setLayout(new BorderLayout());
		//BACKGROUND START GAME
		background = new JLabel(new ImageIcon("./img/ivanhoeBackground.png"));
		add(background);
		background.setLayout(new FlowLayout());
		setVisible(true);


	}

}
