package userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ServerUI extends JPanel {

	public JLabel background,ipLabel,portLabel,commandLabel, numberOfPlayersLabel, messageRestriction;
	public JTextField startText,ipText,portText, numberOfPlayersTextField;
	public JButton runServer;

	public ServerUI(String name) {

		setLayout(new BorderLayout());

		//COMMAND LABEL
		commandLabel= new JLabel("TYPE START:");
		add(commandLabel);

		commandLabel.setLayout(new FlowLayout());
		commandLabel.setFont (new Font ("Serif",Font.ITALIC,18));
		commandLabel.setLocation(new Point(180,100));
		commandLabel.setSize(115,50);
		commandLabel.setForeground(Color.YELLOW);
		commandLabel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		commandLabel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		commandLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		commandLabel.setVisible(true);

		//PORT LABEL
		portLabel= new JLabel("PORT:");
		add(portLabel);

		portLabel.setLayout(new FlowLayout());
		portLabel.setFont (new Font ("Serif",Font.ITALIC,18));
		portLabel.setLocation(new Point(180,199));
		portLabel.setSize(60,50);
		portLabel.setForeground(Color.YELLOW);
		portLabel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		portLabel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		portLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		portLabel.setVisible(true);

		//START TEXTFIELD
		startText= new JTextField(0);
		add(startText);
		Font font4 = new Font("Arial", Font.ITALIC, 24);

		startText.setEditable(true);
		startText.setFont(font4);
		startText.setLayout(new FlowLayout());
		startText.setLocation(new Point(405,100));
		startText.setSize(300,50);


		//PORT TEXTFIELD
		portText = new JTextField(0);
		add(portText);

		portText.setEditable(true);
		portText.setFont(font4);
		portText.setLayout(new FlowLayout());
		portText.setLocation(new Point(405,200));
		portText.setSize(300,50);
		
		//Message restriction
		messageRestriction= new JLabel("Number of players should be between 2-5");
		add(messageRestriction);

		messageRestriction.setLayout(new FlowLayout());
		messageRestriction.setFont (new Font ("Serif",Font.ITALIC,18));
		messageRestriction.setLocation(new Point(350,400));
		messageRestriction.setSize(400,50);
		messageRestriction.setForeground(Color.YELLOW);
		messageRestriction.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		messageRestriction.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		messageRestriction.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		messageRestriction.setVisible(false);
		
		
		//RUN SERVER BUTTON
		runServer = new JButton("");
		add(runServer);
		runServer.setBounds(350,440,370,102);
		runServer.setIcon(new ImageIcon("./img/conn.png"));
		runServer.setOpaque(false);
		runServer.setVisible(true);

		numberOfPlayersLabel= new JLabel("NUMBER OF PLAYERS:");
		add(numberOfPlayersLabel);

		numberOfPlayersLabel.setLayout(new FlowLayout());
		numberOfPlayersLabel.setFont (new Font ("Serif",Font.ITALIC,20));
		numberOfPlayersLabel.setLocation(new Point(180,290));
		numberOfPlayersLabel.setSize(600,50);
		numberOfPlayersLabel.setForeground(Color.YELLOW);
		numberOfPlayersLabel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		numberOfPlayersLabel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		numberOfPlayersLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		numberOfPlayersLabel.setVisible(true);

		numberOfPlayersTextField= new JTextField(0);
		add(numberOfPlayersTextField);
		Font font5 = new Font("Arial", Font.ITALIC, 24);

		numberOfPlayersTextField.setEditable(true);
		numberOfPlayersTextField.setFont(font5);
		numberOfPlayersTextField.setLayout(new FlowLayout());
		numberOfPlayersTextField.setLocation(new Point(405,290));
		numberOfPlayersTextField.setSize(300,50);


		//BACKGROUND START GAME
		JLabel background = new JLabel(new ImageIcon("./img/server_start.png"));
		add(background);
		background.setLayout(new FlowLayout());
		setVisible(true);


	}

}
