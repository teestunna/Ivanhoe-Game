package userinterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.LineBorder;

import config.Config;
import entity.Player;


public class MyBoard extends JPanel {
	
	public JList<ImageIcon> aList1,aList2, aList3, aList4, aList5;
	
	public DefaultListModel<ImageIcon> player1displayList = new DefaultListModel<ImageIcon>();
	public DefaultListModel<ImageIcon> player2displayList = new DefaultListModel<ImageIcon>();
	public DefaultListModel<ImageIcon> player3displayList = new DefaultListModel<ImageIcon>();
	public DefaultListModel<ImageIcon> player4displayList = new DefaultListModel<ImageIcon>();
	public DefaultListModel<ImageIcon> player5displayList = new DefaultListModel<ImageIcon>();

	public JLabel player1,player2,player3,player4,player5;

	public  JLabel background;

	public String player1Card1Path, player1Card2Path, player1Card3Path, player1Card4Path, player1Card5Path, player1Card6Path, player1Card7Path, player1Card8Path;
	public String player2Card1Path, player2Card2Path, player2Card3Path, player2Card4Path, player2Card5Path, player2Card6Path, player2Card7Path, player2Card8Path;
	public String player3Card1Path, player3Card2Path, player3Card3Path, player3Card4Path, player3Card5Path, player3Card6Path, player3Card7Path, player3Card8Path;
	public JScrollPane scrollPane2;
	public JScrollPane scrollPane1;
	public JScrollPane scrollPane3;
	public JScrollPane scrollPane4;
	public JScrollPane scrollPane5;
	public ArrayList<JLabel> handPlayer1 = new ArrayList<JLabel>(9);
	public ArrayList<JLabel> handPlayer2 = new ArrayList<JLabel>(9);
	public ArrayList<JLabel> handPlayer3 = new ArrayList<JLabel>(9);
	public ArrayList<JLabel> handPlayer4 = new ArrayList<JLabel>(9);
	public ArrayList<JLabel> handPlayer5 = new ArrayList<JLabel>(9);
	public ArrayList<JLabel> handPlayer6 = new ArrayList<JLabel>(9);
	public ArrayList<JLabel> handPlayer7 = new ArrayList<JLabel>(9);
	public ArrayList<JLabel> handPlayer8 = new ArrayList<JLabel>(9);
	public ArrayList<JLabel> handPlayer9 = new ArrayList<JLabel>(9);
	
	public HashMap<String, String>   namesToLabels = new HashMap<String, String>();

	public JLabel tournament, tournamentNo, tournamentColour1;
	
	public JLabel player1Card2,player1Card3,player1Card8,player1Card4,player1Card1,player1Card5,player1Card6,player1Card7;
	public JLabel player2Card3,player2Card1,player2Card2,player2Card4,player2Card5,player2Card6,player2Card7,player2Card8;
	public JLabel player3Card3,player3Card1,player3Card2,player3Card4,player3Card5,player3Card6,player3Card7,player3Card8;
	public JLabel player4Card3,player4Card1,player4Card2,player4Card4,player4Card5,player4Card6,player4Card7,player4Card8;
	public JLabel player5Card3,player5Card1,player5Card2,player5Card4,player5Card5,player5Card6,player5Card7,player5Card8;
	public JLabel token1,token2,token3,token4,token5;

	public JLabel player1Card9,player2Card9,player3Card9,player4Card9,player5Card9;

	public JLabel player1faceValue,player2faceValue,player3faceValue,player4faceValue,player5faceValue;
	public JLabel player1token1,player1token2,player1token3,player1token4,player1token5;
	public JLabel player2token1,player2token2,player2token3,player2token4,player2token5;
	public JLabel player3token1,player3token2,player3token3,player3token4,player3token5;
	public JLabel player4token1,player4token2,player4token3,player4token4,player4token5;
	public JLabel player5token1,player5token2,player5token3,player5token4,player5token5;


	JLabel discard,discard1,discard2,discard3;

	JLabel market1,market2,market3;

	public JTextField player1nameLabel,player2nameLabel,player3nameLabel,player4nameLabel,player5nameLabel;

	public JButton withDraw, distribute, distributeCards,pickCard,tournamentColour,done;
	
	public JLabel player1Shield,player2Shield,player3Shield,player4Shield,player5Shield;
	public JLabel player1Stunned,player2Stunned,player3Stunned,player4Stunned,player5Stunned;

	@SuppressWarnings("unused")
	private Player model;

	public JLabel congratsMessage;

	public JLabel winnerMessage;

	
	public MyBoard(Player p) {

		model = p;

		setLayout(new BorderLayout());
		
		//congratsMessage MESSAGE
		congratsMessage = new JLabel("",JLabel.CENTER);
		add(congratsMessage);
		congratsMessage.setLayout(new FlowLayout());
		congratsMessage.setFont (new Font ("Serif",Font.ITALIC,18));
		congratsMessage.setLocation(new Point(400,200));
		congratsMessage.setSize(500,160);
		congratsMessage.setForeground(Color.YELLOW);
		congratsMessage.setIcon(new ImageIcon("./img/congrats-stars.gif"));
		congratsMessage.setVisible(false);

		//winnerMessage MESSAGE

		winnerMessage = new JLabel("",JLabel.CENTER);
		add(winnerMessage);
		winnerMessage.setLayout(new FlowLayout());
		winnerMessage.setFont (new Font ("Serif",Font.ITALIC,18));
		winnerMessage.setLocation(new Point(400,310));
		winnerMessage.setSize(500,310);
		winnerMessage.setForeground(Color.YELLOW);
		winnerMessage.setIcon(new ImageIcon("./img/winner.gif"));
		winnerMessage.setVisible(false);
		
		//SIDE TOKENS
		token1  = new JLabel(new ImageIcon("./img/blue_token.png"));
		add(token1);

		token1.setLayout(new FlowLayout());
		token1.setLocation(new Point(715,447));
		token1.setSize(36,36);
		token1.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		token1.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		token1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		token1.setVisible(true);

		setLayout(new BorderLayout());
		token2  = new JLabel(new ImageIcon("./img/green_token.png"));
		add(token2);

		token2.setLayout(new FlowLayout());
		token2.setLocation(new Point(757,447));
		token2.setSize(36,36);
		token2.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		token2.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		token2.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		token2.setVisible(true);

		setLayout(new BorderLayout());
		token3  = new JLabel(new ImageIcon("./img/purple_token.png"));
		add(token3);

		token3.setLayout(new FlowLayout());
		token3.setLocation(new Point(744,490));
		token3.setSize(36,36);
		token3.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		token3.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		token3.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		token3.setVisible(true);

		setLayout(new BorderLayout());
		token4  = new JLabel(new ImageIcon("./img/red_token.png"));
		add(token4);

		token4.setLayout(new FlowLayout());
		token4.setLocation(new Point(705,480));
		token4.setSize(36,36);
		token4.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		token4.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		token4.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		token4.setVisible(true);

		setLayout(new BorderLayout());
		token5  = new JLabel(new ImageIcon("./img/yellow_token.png"));
		add(token5);

		token5.setLayout(new FlowLayout());
		token5.setLocation(new Point(720,410));
		token5.setSize(36,36);
		token5.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		token5.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		token5.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		token5.setVisible(true);


		//Image for PLAYER1
		setLayout(new BorderLayout());
		player1  = new JLabel(new ImageIcon("./img/player1.png"));
		add(player1);

		player1.setLayout(new FlowLayout());
		player1.setLocation(new Point(0,393));
		player1.setSize(111,102);
		player1.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player1.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player1.setVisible(false);

		//Player1 Text Field
		player1nameLabel= new JTextField(" ",7);
		add(player1nameLabel);
		Font font1 = new Font("Arial", Font.ITALIC, 14);


		player1nameLabel.setEditable(false);
		player1nameLabel.setFont(font1);
		player1nameLabel.setForeground(Color.WHITE);
		player1nameLabel.setBackground(Color.BLACK);
		player1nameLabel.setLayout(new FlowLayout());
		player1nameLabel.setLocation(new Point(17,500));
		player1nameLabel.setSize(80,30);
		player1nameLabel.setHorizontalAlignment(JTextField.CENTER);
		player1nameLabel.setVisible(false);


		//Image for PLAYER2A
		setLayout(new BorderLayout());
		player2  = new JLabel(new ImageIcon("./img/player2.png"));
		add(player2);

		player2.setLayout(new FlowLayout());
		player2.setLocation(new Point(11,0));
		player2.setSize(111,110);
		player2.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player2.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player2.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player2.setVisible(false);


		//Player2A Text Field

		player2nameLabel= new JTextField("Player 2",7);
		add(player2nameLabel);
		Font font2 = new Font("Arial", Font.ITALIC, 14);

		player2nameLabel.setEditable(false);
		player2nameLabel.setFont(font2);
		player2nameLabel.setForeground(Color.WHITE);
		player2nameLabel.setBackground(Color.BLACK);
		player2nameLabel.setLayout(new FlowLayout());
		player2nameLabel.setLocation(new Point(25,110));
		player2nameLabel.setSize(80,30);
		player2nameLabel.setHorizontalAlignment(JTextField.CENTER);
		player2nameLabel.setVisible(false);


		//Image for PLAYER3
		setLayout(new BorderLayout());
		player3  = new JLabel(new ImageIcon("./img/player6.png"));
		add(player3);

		player3.setLayout(new FlowLayout());
		player3.setLocation(new Point(589,0));
		player3.setSize(111,106);
		player3.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player3.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player3.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player3.setVisible(false);

		player3nameLabel= new JTextField("Player 3",7);
		add(player3nameLabel);
		Font font3 = new Font("Arial", Font.ITALIC, 14);


		//Player3 Text Field

		player3nameLabel.setEditable(false);
		player3nameLabel.setFont(font3);
		player3nameLabel.setForeground(Color.WHITE);
		player3nameLabel.setBackground(Color.BLACK);
		player3nameLabel.setLayout(new FlowLayout());
		player3nameLabel.setLocation(new Point(603,100));
		player3nameLabel.setSize(80,30);
		player3nameLabel.setHorizontalAlignment(JTextField.CENTER);
		player3nameLabel.setVisible(false);

		//Image for PLAYER4
		setLayout(new BorderLayout());
		player4  = new JLabel(new ImageIcon("./img/player3.png"));
		add(player4);

		player4.setLayout(new FlowLayout());
		player4.setLocation(new Point(1170,0));
		player4.setSize(111,115);
		player4.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player4.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player4.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player4.setVisible(false);

		player4nameLabel= new JTextField("Player 4",7);
		add(player4nameLabel);
		Font font4 = new Font("Arial", Font.ITALIC, 14);


		//Player4 Text Field

		player4nameLabel.setEditable(false);
		player4nameLabel.setFont(font4);
		player4nameLabel.setForeground(Color.WHITE);
		player4nameLabel.setBackground(Color.BLACK);
		player4nameLabel.setLayout(new FlowLayout());
		player4nameLabel.setLocation(new Point(1190,110));
		player4nameLabel.setSize(80,30);
		player4nameLabel.setHorizontalAlignment(JTextField.CENTER);
		player4nameLabel.setVisible(false);


		//Image for PLAYER5
		setLayout(new BorderLayout());
		player5  = new JLabel(new ImageIcon("./img/player5.png"));
		add(player5);

		player5.setLayout(new FlowLayout());
		player5.setLocation(new Point(1170,383));
		player5.setSize(111,115);
		player5.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player5.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player5.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player5.setVisible(false);

		player5nameLabel= new JTextField("Player 5",7);
		add(player5nameLabel);
		Font font5 = new Font("Arial", Font.ITALIC, 14);


		//Player3 Text Field

		player5nameLabel.setEditable(false);
		player5nameLabel.setFont(font5);
		player5nameLabel.setForeground(Color.WHITE);
		player5nameLabel.setBackground(Color.BLACK);
		player5nameLabel.setLayout(new FlowLayout());
		player5nameLabel.setLocation(new Point(1184,500));
		player5nameLabel.setSize(80,30);
		player5nameLabel.setHorizontalAlignment(JTextField.CENTER);
		player5nameLabel.setVisible(false);



		//PLAYER 1 PERSONAL CARDS

		setLayout(new BorderLayout());
		player1Card1  = new JLabel();
		add(player1Card1);

		player1Card1.setLayout(new FlowLayout());
		player1Card1.setLocation(new Point(105,335));
		player1Card1.setSize(98,52);
		player1Card1.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player1Card1.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player1Card1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player1Card1.setVisible(false);

		setLayout(new BorderLayout());
		player1Card2  = new JLabel();
		add(player1Card2);

		player1Card2.setLayout(new FlowLayout());
		player1Card2.setLocation(new Point(105,385));
		player1Card2.setSize(98,52);
		player1Card2.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player1Card2.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player1Card2.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player1Card2.setVisible(false);

		setLayout(new BorderLayout());
		player1Card3  = new JLabel();
		add(player1Card3);

		player1Card3.setLayout(new FlowLayout());
		player1Card3.setLocation(new Point(105,435));
		player1Card3.setSize(98,52);
		player1Card3.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player1Card3.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player1Card3.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player1Card3.setVisible(false);

		setLayout(new BorderLayout());
		player1Card4  = new JLabel();
		add(player1Card4);

		player1Card4.setLayout(new FlowLayout());
		player1Card4.setLocation(new Point(105,485));
		player1Card4.setSize(98,52);
		player1Card4.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player1Card4.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player1Card4.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player1Card4.setVisible(false);

		setLayout(new BorderLayout());
		player1Card5  = new JLabel();
		add(player1Card5);

		player1Card5.setLayout(new FlowLayout());
		player1Card5.setLocation(new Point(105,535));
		player1Card5.setSize(98,52);
		player1Card5.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player1Card5.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player1Card5.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player1Card5.setVisible(false);

		setLayout(new BorderLayout());
		player1Card6  = new JLabel();
		add(player1Card6);

		player1Card6.setLayout(new FlowLayout());
		player1Card6.setLocation(new Point(105,585));
		player1Card6.setSize(98,52);
		player1Card6.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player1Card6.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player1Card6.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player1Card6.setVisible(false);

		setLayout(new BorderLayout());
		player1Card7  = new JLabel();
		add(player1Card7);

		player1Card7.setLayout(new FlowLayout());
		player1Card7.setLocation(new Point(105,635));
		player1Card7.setSize(98,52);
		player1Card7.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player1Card7.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player1Card7.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player1Card7.setVisible(false);

		setLayout(new BorderLayout());
		player1Card8 = new JLabel();
		add(player1Card8);

		player1Card8.setLayout(new FlowLayout());
		player1Card8.setLocation(new Point(105,685));
		player1Card8.setSize(98,52);
		player1Card8.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player1Card8.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player1Card8.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player1Card8.setVisible(false);

		//PLAYER 1 CARD PICKED
			
		setLayout(new BorderLayout());
		player1Card9 = new JLabel(new ImageIcon("./img/display-2.png"));
		add(player1Card9);
		player1Card9.setLayout(new FlowLayout());
		player1Card9.setLocation(new Point(15,685));
		player1Card9.setSize(98,52);
		player1Card9.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player1Card9.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player1Card9.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player1Card9.setVisible(false);
		
		//PLAYER 1 ACTION NON-DISPLAY ACTION CARDS
		
		setLayout(new BorderLayout());
		player1Shield  = new JLabel(new ImageIcon("./img/270_cards/action_card_shield.png"));
		add(player1Shield);

		player1Shield.setLayout(new FlowLayout());
		player1Shield.setLocation(new Point(180,335));
		player1Shield.setSize(98,52);
		player1Shield.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player1Shield.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player1Shield.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player1Shield.setVisible(false);

		setLayout(new BorderLayout());
		player1Stunned = new JLabel(new ImageIcon("./img/270_cards/action_card_stunned.png"));
		add(player1Stunned);

		player1Stunned.setLayout(new FlowLayout());
		player1Stunned.setLocation(new Point(180,686));
		player1Stunned.setSize(98,52);
		player1Stunned.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player1Stunned.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player1Stunned.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player1Stunned.setVisible(false);

		
		//PLAYER 2A PERSONAL CARDS

		setLayout(new BorderLayout());
		player2Card8  = new JLabel();
		add(player2Card8);
		player2Card9  = new JLabel(new ImageIcon("./img/display360.png"));
		add(player2Card9);
		player2Card9.setLayout(new FlowLayout());
		player2Card9.setLocation(new Point(255,60));
		player2Card9.setSize(50,70);
		player2Card9.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player2Card9.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player2Card9.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player2Card9.setVisible(false);
	
		setLayout(new BorderLayout());
		player2Card8.setLayout(new FlowLayout());
		player2Card8.setLocation(new Point(355,140));
		player2Card8.setSize(50,70);
		player2Card8.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player2Card8.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player2Card8.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player2Card8.setVisible(false);

		setLayout(new BorderLayout());
		player2Card7  = new JLabel();
		add(player2Card7);

		player2Card7.setLayout(new FlowLayout());
		player2Card7.setLocation(new Point(305,140));
		player2Card7.setSize(50,70);
		player2Card7.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player2Card7.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player2Card7.setVisible(false);

		setLayout(new BorderLayout());
		player2Card6  = new JLabel();
		add(player2Card6);

		player2Card6.setLayout(new FlowLayout());
		player2Card6.setLocation(new Point(255,140));
		player2Card6.setSize(50,70);
		player2Card6.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player2Card6.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player2Card6.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player2Card6.setVisible(false);

		setLayout(new BorderLayout());
		player2Card5  = new JLabel();
		add(player2Card5);

		player2Card5.setLayout(new FlowLayout());
		player2Card5.setLocation(new Point(205,140));
		player2Card5.setSize(50,70);
		player2Card5.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player2Card5.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player2Card5.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player2Card5.setVisible(false);

		setLayout(new BorderLayout());
		player2Card4  = new JLabel();
		add(player2Card4);

		player2Card4.setLayout(new FlowLayout());
		player2Card4.setLocation(new Point(155,140));
		player2Card4.setSize(50,70);
		player2Card4.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player2Card4.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player2Card4.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player2Card4.setVisible(false);

		setLayout(new BorderLayout());
		player2Card3  = new JLabel();
		add(player2Card3);

		player2Card3.setLayout(new FlowLayout());
		player2Card3.setLocation(new Point(105,140));
		player2Card3.setSize(50,70);
		player2Card3.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player2Card3.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player2Card3.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player2Card3.setVisible(false);

		setLayout(new BorderLayout());
		player2Card2  = new JLabel();
		add(player2Card2);

		player2Card2.setLayout(new FlowLayout());
		player2Card2.setLocation(new Point(55,140));
		player2Card2.setSize(50,70);
		player2Card2.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player2Card2.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player2Card2.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player2Card2.setVisible(false);

		setLayout(new BorderLayout());
		player2Card1  = new JLabel();
		add(player2Card1);

		player2Card1.setLayout(new FlowLayout());
		player2Card1.setLocation(new Point(5,140));
		player2Card1.setSize(50,70);
		player2Card1.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player2Card1.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player2Card1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player2Card1.setVisible(false);

		//PLAYER 2 ACTION NON-DISPLAY ACTION CARDS
		
		setLayout(new BorderLayout());
		player2Shield  = new JLabel(new ImageIcon("./img/360_cards/action_card_shield.png"));
		add(player2Shield);

		player2Shield.setLayout(new FlowLayout());
		player2Shield.setLocation(new Point(2,220));
		player2Shield.setSize(50,70);
		player2Shield.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player2Shield.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player2Shield.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player2Shield.setVisible(false);

		setLayout(new BorderLayout());
		player2Stunned = new JLabel(new ImageIcon("./img/360_cards/action_card_stunned.png"));
		add(player2Stunned);

		player2Stunned.setLayout(new FlowLayout());
		player2Stunned.setLocation(new Point(356,220));
		player2Stunned.setSize(50,70);
		player2Stunned.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player2Stunned.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player2Stunned.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player2Stunned.setVisible(false);
			
		

		//PLAYER 3 PERSONAL CARDS
		
		setLayout(new BorderLayout());
		player3Card9  = new JLabel(new ImageIcon("./img/display360.png"));
		add(player3Card9);										
		player3Card9.setLayout(new FlowLayout());
		player3Card9.setLocation(new Point(490,60));
		player3Card9.setSize(50,70);
		player3Card9.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player3Card9.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player3Card9.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player3Card9.setVisible(false);

		setLayout(new BorderLayout());
		player3Card8  = new JLabel();
		add(player3Card8);

		player3Card8.setLayout(new FlowLayout());
		player3Card8.setLocation(new Point(790,140));
		player3Card8.setSize(50,70);
		player3Card8.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player3Card8.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player3Card8.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player3Card8.setVisible(false);

		setLayout(new BorderLayout());
		player3Card7  = new JLabel();
		add(player3Card7);

		player3Card7.setLayout(new FlowLayout());
		player3Card7.setLocation(new Point(740,140));
		player3Card7.setSize(50,70);
		player3Card7.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player3Card7.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player3Card7.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player3Card7.setVisible(false);

		setLayout(new BorderLayout());
		player3Card6  = new JLabel();
		add(player3Card6);

		player3Card6.setLayout(new FlowLayout());
		player3Card6.setLocation(new Point(690,140));
		player3Card6.setSize(50,70);
		player3Card6.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player3Card6.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player3Card6.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player3Card6.setVisible(false);

		setLayout(new BorderLayout());
		player3Card5  = new JLabel();
		add(player3Card5);

		player3Card5.setLayout(new FlowLayout());
		player3Card5.setLocation(new Point(640,140));
		player3Card5.setSize(50,70);
		player3Card5.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player3Card5.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player3Card5.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player3Card5.setVisible(false);

		setLayout(new BorderLayout());
		player3Card4  = new JLabel();
		add(player3Card4);

		player3Card4.setLayout(new FlowLayout());
		player3Card4.setLocation(new Point(590,140));
		player3Card4.setSize(50,70);
		player3Card4.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player3Card4.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player3Card4.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player3Card4.setVisible(false);
		
		
		setLayout(new BorderLayout());
		player3Card3  = new JLabel();
		add(player3Card3);

		player3Card3.setLayout(new FlowLayout());
		player3Card3.setLocation(new Point(540,140));
		player3Card3.setSize(50,70);
		player3Card3.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player3Card3.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player3Card3.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player3Card3.setVisible(false);

		setLayout(new BorderLayout());
		player3Card2  = new JLabel();
		add(player3Card2);

		player3Card2.setLayout(new FlowLayout());
		player3Card2.setLocation(new Point(490,140));
		player3Card2.setSize(50,70);
		player3Card2.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player3Card2.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player3Card2.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player3Card2.setVisible(false);

		setLayout(new BorderLayout());
		player3Card1  = new JLabel();
		add(player3Card1);

		player3Card1.setLayout(new FlowLayout());
		player3Card1.setLocation(new Point(440,140));
		player3Card1.setSize(50,70);
		player3Card1.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player3Card1.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player3Card1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player3Card1.setVisible(false);
		
		//PLAYER 3 ACTION NON-DISPLAY ACTION CARDS
		
		setLayout(new BorderLayout());
		player3Shield  = new JLabel(new ImageIcon("./img/360_cards/action_card_shield.png"));
		add(player3Shield);

		player3Shield.setLayout(new FlowLayout());
		player3Shield.setLocation(new Point(438,220));
		player3Shield.setSize(50,70);
		player3Shield.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player3Shield.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player3Shield.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player3Shield.setVisible(false);

		setLayout(new BorderLayout());
		player3Stunned = new JLabel(new ImageIcon("./img/360_cards/action_card_stunned.png"));
		add(player3Stunned);

		player3Stunned.setLayout(new FlowLayout());
		player3Stunned.setLocation(new Point(792,220));
		player3Stunned.setSize(50,70);
		player3Stunned.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player3Stunned.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player3Stunned.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player3Stunned.setVisible(false);

		

		//PLAYER 4 PERSONAL CARDS

		player4Card9  = new JLabel(new ImageIcon("./img/display360.png"));
		add(player4Card9);
													
		player4Card9.setLayout(new FlowLayout());
		player4Card9.setLocation(new Point(975,60));
		player4Card9.setSize(50,70);
		player4Card9.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player4Card9.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player4Card9.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player4Card9.setVisible(false);
		
		setLayout(new BorderLayout());
		setLayout(new BorderLayout());
		player4Card8  = new JLabel();
		add(player4Card8);

		player4Card8.setLayout(new FlowLayout());
		player4Card8.setLocation(new Point(1225,140));
		player4Card8.setSize(50,70);
		player4Card8.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player4Card8.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player4Card8.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player4Card8.setVisible(false);

		setLayout(new BorderLayout());
		player4Card7  = new JLabel();
		add(player4Card7);

		player4Card7.setLayout(new FlowLayout());
		player4Card7.setLocation(new Point(1175,140));
		player4Card7.setSize(50,70);
		player4Card7.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player4Card7.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player4Card7.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player4Card7.setVisible(false);

		setLayout(new BorderLayout());
		player4Card6  = new JLabel();
		add(player4Card6);

		player4Card6.setLayout(new FlowLayout());
		player4Card6.setLocation(new Point(1125,140));
		player4Card6.setSize(50,70);
		player4Card6.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player4Card6.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player4Card6.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player4Card6.setVisible(false);

		setLayout(new BorderLayout());
		player4Card5  = new JLabel();
		add(player4Card5);

		player4Card5.setLayout(new FlowLayout());
		player4Card5.setLocation(new Point(1075,140));
		player4Card5.setSize(50,70);
		player4Card5.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player4Card5.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player4Card5.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player4Card5.setVisible(false);

		setLayout(new BorderLayout());
		player4Card4  = new JLabel();
		add(player4Card4);

		player4Card4.setLayout(new FlowLayout());
		player4Card4.setLocation(new Point(1025,140));
		player4Card4.setSize(50,70);
		player4Card4.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player4Card4.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player4Card4.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player4Card4.setVisible(false);

		setLayout(new BorderLayout());
		player4Card3  = new JLabel();
		add(player4Card3);

		player4Card3.setLayout(new FlowLayout());
		player4Card3.setLocation(new Point(975,140));
		player4Card3.setSize(50,70);
		player4Card3.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player4Card3.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player4Card3.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player4Card3.setVisible(false);

		setLayout(new BorderLayout());
		player4Card2  = new JLabel();
		add(player4Card2);

		player4Card2.setLayout(new FlowLayout());
		player4Card2.setLocation(new Point(925,140));
		player4Card2.setSize(50,70);
		player4Card2.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player4Card2.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player4Card2.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player4Card2.setVisible(false);

		setLayout(new BorderLayout());
		player4Card1  = new JLabel();
		add(player4Card1);

		player4Card1.setLayout(new FlowLayout());
		player4Card1.setLocation(new Point(875,140));
		player4Card1.setSize(50,70);
		player4Card1.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player4Card1.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player4Card1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player4Card1.setVisible(false);
		
		//PLAYER 4 ACTION NON-DISPLAY ACTION CARDS
		
		setLayout(new BorderLayout());
		player4Shield  = new JLabel(new ImageIcon("./img/360_cards/action_card_shield.png"));
		add(player4Shield);

		player4Shield.setLayout(new FlowLayout());
		player4Shield.setLocation(new Point(873,220));
		player4Shield.setSize(50,70);
		player4Shield.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player4Shield.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player4Shield.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player4Shield.setVisible(false);

		setLayout(new BorderLayout());
		player4Stunned = new JLabel(new ImageIcon("./img/360_cards/action_card_stunned.png"));
		add(player4Stunned);

		player4Stunned.setLayout(new FlowLayout());
		player4Stunned.setLocation(new Point(1227,220));
		player4Stunned.setSize(50,70);
		player4Stunned.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player4Stunned.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player4Stunned.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player4Stunned.setVisible(false);


		//PLAYER 5 PERSONAL CARDS
		setLayout(new BorderLayout());
		player5Card9  = new JLabel(new ImageIcon("./img/display90-2.png"));
		add(player5Card9);
													
		player5Card9.setLayout(new FlowLayout());
		player5Card9.setLocation(new Point(1170,685));
		player5Card9.setSize(98,52);
		player5Card9.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player5Card9.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player5Card9.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player5Card9.setVisible(false);
		
		
		setLayout(new BorderLayout());
		player5Card1  = new JLabel();
		add(player5Card1);

		player5Card1.setLayout(new FlowLayout());
		player5Card1.setLocation(new Point(1099,335));
		player5Card1.setSize(98,52);
		player5Card1.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player5Card1.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player5Card1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player5Card1.setVisible(false);

		setLayout(new BorderLayout());
		player5Card2  = new JLabel();
		add(player5Card2);

		player5Card2.setLayout(new FlowLayout());
		player5Card2.setLocation(new Point(1099,385));
		player5Card2.setSize(98,52);
		player5Card2.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player5Card2.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player5Card2.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player5Card2.setVisible(false);

		setLayout(new BorderLayout());
		player5Card3  = new JLabel();
		add(player5Card3);

		player5Card3.setLayout(new FlowLayout());
		player5Card3.setLocation(new Point(1099,435));
		player5Card3.setSize(98,52);
		player5Card3.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player5Card3.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player5Card3.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player5Card3.setVisible(false);

		setLayout(new BorderLayout());
		player5Card4  = new JLabel();
		add(player5Card4);

		player5Card4.setLayout(new FlowLayout());
		player5Card4.setLocation(new Point(1099,485));
		player5Card4.setSize(98,52);
		player5Card4.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player5Card4.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player5Card4.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player5Card4.setVisible(false);

		setLayout(new BorderLayout());
		player5Card5  = new JLabel();
		add(player5Card5);

		player5Card5.setLayout(new FlowLayout());
		player5Card5.setLocation(new Point(1099,535));
		player5Card5.setSize(98,52);
		player5Card5.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player5Card5.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player5Card5.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player5Card5.setVisible(false);

		setLayout(new BorderLayout());
		player5Card6  = new JLabel();
		add(player5Card6);

		player5Card6.setLayout(new FlowLayout());
		player5Card6.setLocation(new Point(1099,585));
		player5Card6.setSize(98,52);
		player5Card6.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player5Card6.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player5Card6.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player5Card6.setVisible(false);

		setLayout(new BorderLayout());
		player5Card7  = new JLabel();
		add(player5Card7);

		player5Card7.setLayout(new FlowLayout());
		player5Card7.setLocation(new Point(1099,635));
		player5Card7.setSize(98,52);
		player5Card7.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player5Card7.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player5Card7.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player5Card7.setVisible(false);

		setLayout(new BorderLayout());
		player5Card8  = new JLabel();
		add(player5Card8);

		player5Card8.setLayout(new FlowLayout());
		player5Card8.setLocation(new Point(1099,685));
		player5Card8.setSize(98,52);
		player5Card8.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player5Card8.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player5Card8.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player5Card8.setVisible(false);
		
		//PLAYER 5 ACTION NON-DISPLAY ACTION CARDS
		
		setLayout(new BorderLayout());
		player5Shield  = new JLabel(new ImageIcon("./img/90_cards/action_card_shield.png"));
		add(player5Shield);

		player5Shield.setLayout(new FlowLayout());
		player5Shield.setLocation(new Point(1000,335));
		player5Shield.setSize(98,52);
		player5Shield.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player5Shield.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player5Shield.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player5Shield.setVisible(false);

		setLayout(new BorderLayout());
		player5Stunned = new JLabel(new ImageIcon("./img/90_cards/action_card_stunned.png"));
		add(player5Stunned);

		player5Stunned.setLayout(new FlowLayout());
		player5Stunned.setLocation(new Point(1000,686));
		player5Stunned.setSize(98,52);
		player5Stunned.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player5Stunned.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player5Stunned.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player5Stunned.setVisible(false);


		//PLAYER 1 TOTAL FACE VALUE
		
		player1faceValue = new JLabel("TOTAL FACE VALUE:",JLabel.CENTER);
		add(player1faceValue);
				
		player1faceValue.setLayout(new FlowLayout());
		player1faceValue.setFont (new Font ("Serif",Font.ITALIC,15));
		player1faceValue.setLocation(new Point(240,580));
		player1faceValue.setSize(195,30);
		player1faceValue.setForeground(Color.YELLOW);
		player1faceValue.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player1faceValue.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player1faceValue.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player1faceValue.setVisible(false);
				
		//PLAYER 2 TOTAL FACE VALUE
		
		player2faceValue = new JLabel("TOTAL FACE VALUE:",JLabel.CENTER);
		add(player2faceValue);

		player2faceValue.setLayout(new FlowLayout());
		player2faceValue.setFont (new Font ("Serif",Font.ITALIC,15));
		player2faceValue.setLocation(new Point(120,301));
		player2faceValue.setSize(195,30);
		player2faceValue.setForeground(Color.YELLOW);
		player2faceValue.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player2faceValue.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player2faceValue.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player2faceValue.setVisible(false);


		//PLAYER 3 TOTAL FACE VALUE

		player3faceValue = new JLabel("TOTAL FACE VALUE:",JLabel.CENTER);
		add(player3faceValue);

		player3faceValue.setLayout(new FlowLayout());
		player3faceValue.setFont (new Font ("Serif",Font.ITALIC,15));
		player3faceValue.setLocation(new Point(550,301));
		player3faceValue.setSize(195,30);
		player3faceValue.setForeground(Color.YELLOW);
		player3faceValue.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player3faceValue.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player3faceValue.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player3faceValue.setVisible(false);


		//PLAYER 4 TOTAL FACE VALUE

		player4faceValue = new JLabel("TOTAL FACE VALUE:",JLabel.CENTER);
		add(player4faceValue);

		player4faceValue.setLayout(new FlowLayout());
		player4faceValue.setFont (new Font ("Serif",Font.ITALIC,15));
		player4faceValue.setLocation(new Point(1000,301));
		player4faceValue.setSize(195,30);
		player4faceValue.setForeground(Color.YELLOW);
		player4faceValue.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player4faceValue.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player4faceValue.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player4faceValue.setVisible(false);


		//PLAYER 5 TOTAL FACE VALUE

		player5faceValue = new JLabel("TOTAL FACE VALUE:",JLabel.CENTER);
		add(player5faceValue);

		player5faceValue.setLayout(new FlowLayout());
		player5faceValue.setFont (new Font ("Serif",Font.ITALIC,15));
		player5faceValue.setLocation(new Point(820,580));
		player5faceValue.setSize(195,30);
		player5faceValue.setForeground(Color.YELLOW);
		player5faceValue.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player5faceValue.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player5faceValue.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player5faceValue.setVisible(false);
		

		//PLAYER 1 TOKENS

		setLayout(new BorderLayout());
		player1token1  = new JLabel();
		add(player1token1);

		player1token1.setLayout(new FlowLayout());
		player1token1.setLocation(new Point(5,600));
		player1token1.setSize(36,36);
		player1token1.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player1token1.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player1token1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player1token1.setVisible(false);

		setLayout(new BorderLayout());
		player1token2  = new JLabel();
		add(player1token2);

		player1token2.setLayout(new FlowLayout());
		player1token2.setLocation(new Point(5,550));
		player1token2.setSize(36,36);
		player1token2.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player1token2.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player1token2.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player1token2.setVisible(false);

		setLayout(new BorderLayout());
		player1token3  = new JLabel();
		add(player1token3);

		player1token3.setLayout(new FlowLayout());
		player1token3.setLocation(new Point(40,600));
		player1token3.setSize(36,36);
		player1token3.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player1token3.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player1token3.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player1token3.setVisible(false);

		setLayout(new BorderLayout());
		player1token4  = new JLabel();
		add(player1token4);

		player1token4.setLayout(new FlowLayout());
		player1token4.setLocation(new Point(40,550));
		player1token4.setSize(36,36);
		player1token4.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player1token4.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player1token4.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player1token4.setVisible(false);
		
		
		setLayout(new BorderLayout());
		player1token5  = new JLabel();
		add(player1token5);
					
		player1token5.setLayout(new FlowLayout());
		player1token5.setLocation(new Point(5,650));
		player1token5.setSize(36,36);
		player1token5.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player1token5.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player1token5.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player1token5.setVisible(false);

		//PLAYER 5 TOKENS
		setLayout(new BorderLayout());
		player5token1  = new JLabel();
		add(player5token1);

		player5token1.setLayout(new FlowLayout());
		player5token1.setLocation(new Point(1183,600));
		player5token1.setSize(36,36);
		player5token1.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player5token1.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player5token1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player5token1.setVisible(false);

		setLayout(new BorderLayout());
		player5token2  = new JLabel();
		add(player5token2);

		player5token2.setLayout(new FlowLayout());
		player5token2.setLocation(new Point(1183,550));
		player5token2.setSize(36,36);
		player5token2.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player5token2.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player5token2.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player5token2.setVisible(false);

		setLayout(new BorderLayout());
		player5token3  = new JLabel();
		add(player5token3);

		player5token3.setLayout(new FlowLayout());
		player5token3.setLocation(new Point(1218,600));
		player5token3.setSize(36,36);
		player5token3.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player5token3.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player5token3.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player5token3.setVisible(false);

		setLayout(new BorderLayout());
		player5token4  = new JLabel();
		add(player5token4);

		player5token4.setLayout(new FlowLayout());
		player5token4.setLocation(new Point(1218,550));
		player5token4.setSize(36,36);
		player5token4.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player5token4.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player5token4.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player5token4.setVisible(false);

		
		setLayout(new BorderLayout());
		player5token5  = new JLabel();
		add(player5token5);
							
		player5token5.setLayout(new FlowLayout());
		player5token5.setLocation(new Point(1218,650));
		player5token5.setSize(36,36);
		player5token5.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player5token5.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player5token5.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player5token5.setVisible(false);
		//PLAYER 2 TOKENS

		setLayout(new BorderLayout());
		player2token1  = new JLabel();
		add(player2token1);

		player2token1.setLayout(new FlowLayout());
		player2token1.setLocation(new Point(120,5));
		player2token1.setSize(36,36);
		player2token1.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player2token1.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player2token1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player2token1.setVisible(false);

		setLayout(new BorderLayout());
		player2token2  = new JLabel();
		add(player2token2);

		player2token2.setLayout(new FlowLayout());
		player2token2.setLocation(new Point(160,5));
		player2token2.setSize(36,36);
		player2token2.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player2token2.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player2token2.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player2token2.setVisible(false);

		setLayout(new BorderLayout());
		player2token3  = new JLabel();
		add(player2token3);

		player2token3.setLayout(new FlowLayout());
		player2token3.setLocation(new Point(120,40));
		player2token3.setSize(36,36);
		player2token3.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player2token3.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player2token3.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player2token3.setVisible(false);

		setLayout(new BorderLayout());
		player2token4  = new JLabel();
		add(player2token4);

		player2token4.setLayout(new FlowLayout());
		player2token4.setLocation(new Point(160,40));
		player2token4.setSize(36,36);
		player2token4.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player2token4.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player2token4.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player2token4.setVisible(false);
		
		setLayout(new BorderLayout());
		player2token5  = new JLabel();
		add(player2token5);
											
		player2token5.setLayout(new FlowLayout());
		player2token5.setLocation(new Point(200,40));
		player2token5.setSize(36,36);
		player2token5.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player2token5.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player2token5.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player2token5.setVisible(false);
		

		//PLAYER 3 TOKENS

		setLayout(new BorderLayout());
		player3token1  = new JLabel();
		add(player3token1);

		player3token1.setLayout(new FlowLayout());
		player3token1.setLocation(new Point(695,5));
		player3token1.setSize(36,36);
		player3token1.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player3token1.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player3token1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player3token1.setVisible(false);

		setLayout(new BorderLayout());
		player3token2  = new JLabel();
		add(player3token2);

		player3token2.setLayout(new FlowLayout());
		player3token2.setLocation(new Point(735,5));
		player3token2.setSize(36,36);
		player3token2.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player3token2.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player3token2.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player3token2.setVisible(false);

		setLayout(new BorderLayout());
		player3token3  = new JLabel();
		add(player3token3);

		player3token3.setLayout(new FlowLayout());
		player3token3.setLocation(new Point(695,40));
		player3token3.setSize(36,36);
		player3token3.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player3token3.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player3token3.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player3token3.setVisible(false);

		setLayout(new BorderLayout());
		player3token4  = new JLabel();
		add(player3token4);

		player3token4.setLayout(new FlowLayout());
		player3token4.setLocation(new Point(735,40));
		player3token4.setSize(36,36);
		player3token4.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player3token4.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player3token4.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player3token4.setVisible(false);
		
		
		setLayout(new BorderLayout());
		player3token5  = new JLabel();
		add(player3token5);
											
		player3token5.setLayout(new FlowLayout());
		player3token5.setLocation(new Point(735,75));
		player3token5.setSize(36,36);
		player3token5.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player3token5.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player3token5.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player3token5.setVisible(false);
		

		//PLAYER 4 TOKENS

		setLayout(new BorderLayout());
		player4token1  = new JLabel();
		add(player4token1);

		player4token1.setLayout(new FlowLayout());
		player4token1.setLocation(new Point(1130,5));
		player4token1.setSize(36,36);
		player4token1.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player4token1.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player4token1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player4token1.setVisible(false);

		setLayout(new BorderLayout());
		player4token2  = new JLabel();
		add(player4token2);

		player4token2.setLayout(new FlowLayout());
		player4token2.setLocation(new Point(1090,5));
		player4token2.setSize(36,36);
		player4token2.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player4token2.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player4token2.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player4token2.setVisible(false);

		setLayout(new BorderLayout());
		player4token3  = new JLabel();
		add(player4token3);

		player4token3.setLayout(new FlowLayout());
		player4token3.setLocation(new Point(1130,40));
		player4token3.setSize(36,36);
		player4token3.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player4token3.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player4token3.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player4token3.setVisible(false);

		setLayout(new BorderLayout());
		player4token4  = new JLabel();
		add(player4token4);

		player4token4.setLayout(new FlowLayout());
		player4token4.setLocation(new Point(1090,40));
		player4token4.setSize(36,36);
		player4token4.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player4token4.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player4token4.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player4token4.setVisible(false);


		setLayout(new BorderLayout());
		player4token5  = new JLabel();
		add(player4token5);
													
		player4token5.setLayout(new FlowLayout());
		player4token5.setLocation(new Point(1090,75));
		player4token5.setSize(36,36);
		player4token5.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		player4token5.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		player4token5.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player4token5.setVisible(false);
		
		
		//DISCARD PILE FACE-UP

		setLayout(new BorderLayout());
		discard  = new JLabel(new ImageIcon("./img/display180.png"));
		add(discard);

		discard.setLayout(new FlowLayout());
		discard.setLocation(new Point(610,357));
		discard.setSize(50,70);
		discard.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		discard.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		discard.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		discard.setVisible(true);

		setLayout(new BorderLayout());
		discard1  = new JLabel(new ImageIcon("./img/display180.png"));
		add(discard1);

		discard1.setLayout(new FlowLayout());
		discard1.setLocation(new Point(607,360));
		discard1.setSize(50,70);
		discard1.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		discard1.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		discard1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		discard1.setVisible(true);

		setLayout(new BorderLayout());
		discard2  = new JLabel(new ImageIcon("./img/display180.png"));
		add(discard2);

		discard2.setLayout(new FlowLayout());
		discard2.setLocation(new Point(604,363));
		discard2.setSize(50,70);
		discard2.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		discard2.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		discard2.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		discard2.setVisible(true);

		setLayout(new BorderLayout());
		discard3  = new JLabel(new ImageIcon("./img/display180.png"));
		add(discard3);

		discard3.setLayout(new FlowLayout());
		discard3.setLocation(new Point(601,367));
		discard3.setSize(50,70);
		discard3.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		discard3.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		discard3.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		discard3.setVisible(true);

		//MARKET OR EXCESS CARD
		setLayout(new BorderLayout());
		market1  = new JLabel(new ImageIcon("./img/ivanhoe.png"));
		add(market1);

		market1.setLayout(new FlowLayout());
		market1.setLocation(new Point(510,393));
		market1.setSize(50,70);
		market1.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		market1.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		market1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		market1.setVisible(true);

		setLayout(new BorderLayout());
		market2  = new JLabel(new ImageIcon("./img/ivanhoe.png"));
		add(market2);

		market2.setLayout(new FlowLayout());
		market2.setLocation(new Point(507,395));
		market2.setSize(50,70);
		market2.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		market2.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		market2.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		market2.setVisible(true);

		setLayout(new BorderLayout());
		market3  = new JLabel(new ImageIcon("./img/ivanhoe.png"));
		add(market3);

		market3.setLayout(new FlowLayout());
		market3.setLocation(new Point(503,397));
		market3.setSize(50,70);
		market3.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		market3.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		market3.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		market3.setVisible(true);

		//WITHDRAW BUTTON
		withDraw = new JButton("WITHDRAW");
		add(withDraw);
		withDraw.setBounds(885,700,110,42);
		withDraw.setBackground(Color.RED);
		withDraw.setForeground(Color.RED);
		withDraw.setIcon(new ImageIcon("./img/eject-2.png"));
		withDraw.setEnabled(false);
		withDraw.setOpaque(false);
		withDraw.setVisible(true);

		//TOKEN BUTTON
		distribute = new JButton("TOKEN");
		add(distribute);
		distribute.setBounds(766,700,110,42);
		distribute.setBackground(Color.GREEN);
		distribute.setForeground(Color.GREEN);
		distribute.setIcon(new ImageIcon("./img/pick-button.png"));
		distribute.setEnabled(false);
		distribute.setOpaque(false);
		distribute.setVisible(true);

		//Distribute cards button
		distributeCards = new JButton("DISTRIBUTE");
		add(distributeCards);
		distributeCards.setBounds(643,700,111,42);
		distributeCards.setBackground(Color.BLUE);
		distributeCards.setForeground(Color.BLUE);
		distributeCards.setIcon(new ImageIcon("./img/shuffle.jpg"));
		distributeCards.setEnabled(false);
		distributeCards.setOpaque(false);
		distributeCards.setVisible(true);
		
		//PICK A CARD BUTTON
		pickCard = new JButton("PICK CARD");
		add(pickCard);
		pickCard.setBounds(400,700,110,42);
		pickCard.setBackground(Color.RED);
		pickCard.setForeground(Color.RED);
		pickCard.setIcon(new ImageIcon("./img/pickcard.png"));
		pickCard.setEnabled(false);
		pickCard.setOpaque(false);
		pickCard.setVisible(true);

		//SELECT TOURNAMENT COLOR
		tournamentColour = new JButton("T.COLOR");
		add(tournamentColour);
		tournamentColour.setBounds(522,700,110,42);
		tournamentColour.setBackground(Color.BLUE);
		tournamentColour.setForeground(Color.BLUE);
		tournamentColour.setIcon(new ImageIcon("./img/tournamentcolor.png"));
		tournamentColour.setEnabled(false);
		tournamentColour.setOpaque(false);
		tournamentColour.setVisible(true);

		//TOURNAMENT ROUND
		tournament = new JLabel("TOURNAMENT:",JLabel.CENTER);
		add(tournament);

		tournament.setLayout(new FlowLayout());
		//tournament.setEditable(false);
		tournament.setFont (new Font ("Serif",Font.BOLD,22));
		tournament.setLocation(new Point(505,640));
		tournament.setSize(195,30);
		tournament.setForeground(Color.YELLOW);
		tournament.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		tournament.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		tournament.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		tournament.setVisible(true);

		//TOURNAMENT NUMBER
		tournamentNo = new JLabel("NUMBER",JLabel.CENTER);
		add(tournamentNo);

		tournamentNo.setLayout(new FlowLayout());
		tournamentNo.setFont (new Font ("Serif",Font.ITALIC,22));
		tournamentNo.setLocation(new Point(650,640));
		tournamentNo.setSize(195,30);
		tournamentNo.setForeground(Color.YELLOW);
		tournamentNo.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		tournamentNo.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		tournamentNo.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		tournamentNo.setVisible(true);

		//TOURNAMENT COLOR
		JLabel tournamentColor = new JLabel("TOURNAMENT COLOR:",JLabel.CENTER);
		add(tournamentColor);
		tournamentColor.setLayout(new FlowLayout());
		tournamentColor.setFont (new Font ("Serif",Font.BOLD,22));
		tournamentColor.setLocation(new Point(405,670));
		tournamentColor.setSize(300,30);
		tournamentColor.setForeground(Color.YELLOW);
		tournamentColor.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		tournamentColor.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		tournamentColor.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		tournamentColor.setVisible(true);

		//ACTUAL TOURNAMENT COLOR
		tournamentColour1 = new JLabel("COLOR",JLabel.CENTER);
		add(tournamentColour1);

		tournamentColour1.setLayout(new FlowLayout());
		tournamentColour1.setFont (new Font ("Serif",Font.ITALIC,22));
		tournamentColour1.setLocation(new Point(641,670));
		tournamentColour1.setSize(195,30);
		tournamentColour1.setForeground(Color.YELLOW);
		tournamentColour1.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		tournamentColour1.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		tournamentColour1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		tournamentColour1.setVisible(true);
		
		//DONE BUTTON
			
		done = new JButton("");
		add(done);
		done.setBounds(320,700,43,40);
		done.setIcon(new ImageIcon("./img/done.png"));
		done.setOpaque(false);
		done.setEnabled(false);

		//add scroll pane for player 1
		aList1 = new JList<ImageIcon>(player1displayList);
		aList1.setBackground(Color.LIGHT_GRAY);
		aList1.setBorder(new LineBorder(Color.ORANGE));
		aList1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		aList1.setVisibleRowCount(-1);
		aList1.setLayoutOrientation(JList.HORIZONTAL_WRAP);

		scrollPane1 = new JScrollPane(aList1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scrollPane1.setLocation(185,388);
		scrollPane1.setSize(90,298);
		scrollPane1.setBorder(new LineBorder(Color.ORANGE));
		scrollPane1.setBackground(Color.LIGHT_GRAY);
		add(scrollPane1);
		scrollPane1.setVisible(false);

		//add scroll pane for player 2
		aList2 = new JList<ImageIcon>(player2displayList);
		aList2.setBackground(Color.LIGHT_GRAY);
		aList2.setBorder(new LineBorder(Color.ORANGE));
		aList2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		aList2.setVisibleRowCount(-1);
		aList2.setLayoutOrientation(JList.VERTICAL_WRAP);

		scrollPane2 = new JScrollPane(aList2,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scrollPane2.setLocation(54,219);
		scrollPane2.setSize(301,86);
		scrollPane2.setBorder(new LineBorder(Color.ORANGE));
		scrollPane2.setBackground(Color.LIGHT_GRAY);
		scrollPane2.getViewport().setBackground(Color.LIGHT_GRAY);
		add(scrollPane2);
		scrollPane2.setVisible(false);

		//add scroll pane for player 3
		aList3 = new JList<ImageIcon>(player3displayList);
		aList3.setBackground(Color.LIGHT_GRAY);
		aList3.setBorder(new LineBorder(Color.ORANGE));
		aList3.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		aList3.setVisibleRowCount(-1);
		aList3.setLayoutOrientation(JList.VERTICAL_WRAP);

		scrollPane3 = new JScrollPane(aList3,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scrollPane3.setLocation(490,219);
		scrollPane3.setSize(301,86);
		scrollPane3.setBorder(new LineBorder(Color.ORANGE));
		scrollPane3.setBackground(Color.LIGHT_GRAY);
		add(scrollPane3);
		scrollPane3.setVisible(false);

		//add scroll pane for player 4
		aList4 = new JList<ImageIcon>(player4displayList);
		aList4.setBackground(Color.LIGHT_GRAY);
		aList4.setBorder(new LineBorder(Color.ORANGE));
		aList4.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		aList4.setVisibleRowCount(-1);
		aList4.setLayoutOrientation(JList.VERTICAL_WRAP);

		scrollPane4 = new JScrollPane(aList4,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scrollPane4.setLocation(925,219);
		scrollPane4.setSize(301,86);
		scrollPane4.setBorder(new LineBorder(Color.ORANGE));
		scrollPane4.setBackground(Color.LIGHT_GRAY);
		add(scrollPane4);
		scrollPane4.setVisible(false);

		//add scroll pane for player 5
		aList5 = new JList<ImageIcon>(player5displayList);
		aList5.setBackground(Color.LIGHT_GRAY);
		aList5.setBorder(new LineBorder(Color.ORANGE));
		aList5.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		aList5.setVisibleRowCount(-1);
		aList5.setLayoutOrientation(JList.HORIZONTAL_WRAP);

		scrollPane5 = new JScrollPane(aList5,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scrollPane5.setLocation(1000,388);
		scrollPane5.setSize(90,298);
		scrollPane5.setBorder(new LineBorder(Color.ORANGE));
		scrollPane5.setBackground(Color.LIGHT_GRAY);
		add(scrollPane5);
		scrollPane5.setVisible(false);

		//BACKGROUND IVAHOE GAME
		background = new JLabel(new ImageIcon("./img/board-2.png"));
		add(background);
		background.setLayout(new FlowLayout());
		setVisible(true);
	}
	
	public void updatePlayer1Panel(ImageIcon[] images) {
		//Remember what was selected
		// Now re-populate the list
		aList1.setListData(images);
		aList1.setEnabled(true);
		scrollPane1.setVisible(true);
		scrollPane1.setEnabled(true);
    }
	
	public void updatePlayer2Panel(ImageIcon[] images) {
		//Remember what was selected
		// Now re-populate the list
		aList2.setListData(images);
		aList2.setEnabled(true);
		scrollPane2.setVisible(true);
		scrollPane2.setEnabled(true);
    }
	
	public void updatePlayer3Panel(ImageIcon[] images) {
		//Remember what was selected
		// Now re-populate the list
		aList3.setEnabled(true);
		aList3.setListData(images);
		scrollPane3.setVisible(true);
		scrollPane3.setEnabled(true);
    }
	
	public void updatePlayer4Panel(ImageIcon[] images) {
		//Remember what was selected
		// Now re-populate the list
		aList4.setListData(images);
		aList4.setEnabled(true);
		scrollPane4.setVisible(true);
		scrollPane4.setEnabled(true);
    }
	
	public void updatePlayer5Panel(ImageIcon[] images) {
		//Remember what was selected
		// Now re-populate the list
		aList5.setListData(images);
		aList5.setEnabled(true);
		scrollPane5.setVisible(true);
		scrollPane5.setEnabled(true);
    }
	
}