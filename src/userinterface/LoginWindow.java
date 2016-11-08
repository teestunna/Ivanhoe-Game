package userinterface;

import java.awt.*;

import javax.swing.*;

public class LoginWindow extends JPanel {

	public JButton startGame;
	public JLabel background;
	public JTextField playernameLabel,playerIPLabel,playerPortLabel;
	public JLabel nameLabel,ipLabel,portLabel;
	
	public LoginWindow(String name) {

		setLayout(new BorderLayout());
		//UNEDITABLE NAME LABEL
		nameLabel= new JLabel("NAME:");
	    add(nameLabel);
	    
	    nameLabel.setLayout(new FlowLayout());
	    nameLabel.setFont (new Font ("Serif",Font.ITALIC,18));
	    nameLabel.setForeground(Color.YELLOW);
	    nameLabel.setLocation(new Point(200,100));
	    nameLabel.setSize(60,50);
	    nameLabel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
	    nameLabel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
	    nameLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	    nameLabel.setVisible(true);

		setLayout(new BorderLayout());
		//UNEDITABLE IP LABEL
		ipLabel= new JLabel("IP:");
	    add(ipLabel);
	    
	    ipLabel.setLayout(new FlowLayout());
	    ipLabel.setFont (new Font ("Serif",Font.ITALIC,18));
	    ipLabel.setForeground(Color.YELLOW);
	    ipLabel.setLocation(new Point(200,199));
	    ipLabel.setSize(60,50);
	    ipLabel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
	    ipLabel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
	    ipLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	    ipLabel.setVisible(true);

		setLayout(new BorderLayout());
		//UNEDITABLE PORT LABEL
		portLabel= new JLabel("PORT:");
	    add(portLabel);
	    
	    portLabel.setLayout(new FlowLayout());
	    portLabel.setFont (new Font ("Serif",Font.ITALIC,18));
	    portLabel.setForeground(Color.YELLOW);
	    portLabel.setLocation(new Point(200,299));
	    portLabel.setSize(60,50);
	    portLabel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
	    portLabel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
	    portLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	    portLabel.setVisible(true);

		setLayout(new BorderLayout());
		//PLAYERNAME LABEL
		playernameLabel= new JTextField(0);
	    add(playernameLabel);
		Font font4 = new Font("Arial", Font.ITALIC, 24);
	  
	    playernameLabel.setEditable(true);
	    playernameLabel.setFont(font4);
		playernameLabel.setLayout(new FlowLayout());
        playernameLabel.setLocation(new Point(300,100));
		playernameLabel.setSize(300,50);

		setLayout(new BorderLayout());
		//IP LABEL
		playerIPLabel = new JTextField(0);
		add(playerIPLabel);

		playerIPLabel.setEditable(true);
		playerIPLabel.setFont(font4);
		playerIPLabel.setLayout(new FlowLayout());
		playerIPLabel.setLocation(new Point(300,200));
		playerIPLabel.setSize(300,50);

		setLayout(new BorderLayout());
		//PORT LABEL
		playerPortLabel = new JTextField(0);
		add(playerPortLabel);

		playerPortLabel.setEditable(true);
		playerPortLabel.setFont(font4);
		playerPortLabel.setLayout(new FlowLayout());
		playerPortLabel.setLocation(new Point(300,300));
		playerPortLabel.setSize(300,50);

		setLayout(new BorderLayout());
		//CONNECT BUTTON
		startGame = new JButton("");
		add(startGame);
		startGame.setBounds(300,500,370,102);
		startGame.setIcon(new ImageIcon("./img/conn.png"));
		startGame.setOpaque(false);
		startGame.setVisible(true);

		//BACKGROUND START GAME
		background = new JLabel(new ImageIcon("./img/startupb.jpg"));
		add(background);
		background.setLayout(new FlowLayout());
		setVisible(true);


	}

}
