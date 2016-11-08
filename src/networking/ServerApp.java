package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;
import java.util.Map.Entry;

import config.Config;
import entity.Player;
import ruleEngine.GameRuleEngine;

public class ServerApp implements Runnable {
	private int 										checkNames	= 0;
	private int                                       	clientCount = 0;
	private int 										holdWinnerOfPreviousTournamentKey = 0;
	private String 										previousTournamentColour = "";
	private Thread                            			thread = null;
	private ServerSocket                      			server = null;
	private String 										playerWhoWonTheCurrentTournament = "";
	private int											checkSomeonePlayedAction				=0;
	private HashMap<Integer, Player>		  			clientsToPlayers;
	private HashMap<Integer, ServerThread>    			clients;
	private ArrayList<Player>				 			oldPlayers;
	private ArrayList<Player> 				  			players  ;
	
	private GameRuleEngine    				  			ruleEngine;
	private String										playerWhoWonThePurpleTournament = "";
	private int											playerWhoWonThePurpleTournamentKey = 0;
	private int 										playerWhoWonCurrentTournamentKey = 0;
	private int 										playedLastAction =0;
	private String 										result 			 			= "";		
	private ArrayList<Player>				  			withdrawnPlayers 		;
	private List<String>					  			tokensForTwoPlayers       	= new ArrayList<String>(Arrays.asList("purple", "green"));
	private List<String> 					  			tokensForFivePlayers       	= new ArrayList<String>(Arrays.asList("red", "purple", "green", "yellow", "blue"));
	private List<String> 					  			tokensForThreePlayers      	= new ArrayList<String>(Arrays.asList("red", "purple", "green"));
	private List<String> 					  			tokensForFourPlayers       	= new ArrayList<String>(Arrays.asList("red", "purple", "green", "yellow"));
	

	private ArrayList<String>				  			winningTokens = new ArrayList<String>(Arrays.asList("red", "purple", "green", "yellow", "blue",
																											"red","purple", "green", "yellow", "blue",
																											"red", "purple", "green", "yellow", "blue", 
																											"red", "purple", "green", "yellow", "blue",  
																											"red", "purple", "green", "yellow", "blue"));
	private ArrayList<String>				  			discardPile = new ArrayList<String>();
	private ArrayList<String>                 			eightCardDealedToEachPlayer;
	private int 							  			countPlayersWhoDidntgGetPurple = 0;
	private int 							  			numberOfTokensGivenSoFar = 0;
	private String							  			playerToStartTournament = "";
	private int 							  			check = 0;
	private	int								  			tournamentCount = 1;
	private int  							  			numberOfTokensReceivedSoFar = 0;
	private int  							  			keyForPlayerWhoGotAPurple = 0;
	private String							  			nameOfPlayerWhoGotAPurple = "";
	private String 							  			playerToStartTheGameFirst = "";
	private int    							  			keyForPlayerWhoStartsFirst = 0;
	private String						      			playerNameWhoWantsToWithdraw = "";
	private int								  			playerWhoWantsToWithdrawID = 0;
	private int								  			turnToPlay = 0;
	private int 										check2 = 0;
	private boolean 									gaveUpToken = false;
	
	public  boolean										connected = false;
	public  String										maxNumberOfPlayersReached = "";
	
	private String										overallWinnerOfATournament = "";
	private String										currentColourOfPrevTournament = "";
	private boolean										doesPlayerToStartHaveOnlyActionCards = false;
	private int 										keyForPlayerWhoIsNextToPlayerWithOnlyActionCards = 0;
	private int 										countForCardGivenFirstTournament 				 = 0;
	private int 										distributionReceived = 0;
	private boolean 									tournamentColourChosen = false;
	private boolean										wantsToPlayIvanhoe      = false;
	private int 										numberOfPlayersSpecified			= 0;
	private boolean 									checkOneCardFirst = false;

	/* Constructor for our server */
	public ServerApp(int port, int numberPlayers) {
		try {
			numberOfPlayersSpecified = numberPlayers;
			
			oldPlayers 			= new ArrayList<Player>(numberOfPlayersSpecified);
			players    			= new ArrayList<Player>(numberOfPlayersSpecified);
			withdrawnPlayers 	=  new ArrayList<Player>(numberOfPlayersSpecified);
			
			/* init rule Engine */
			ruleEngine = new GameRuleEngine();
			System.out.println("Size of color and supporter cards is :" + ruleEngine.deckOfColorAndSupporters.size());
			System.out.println("Binding to port " + port + ", please wait  ...");
			System.out.println("Binding to port " + port);
			/**
			 * I use a HashMap to keep track of the client connections and their
			 * related thread
			 */
			clients = new HashMap<Integer, ServerThread>();
			clientsToPlayers = new HashMap<Integer, Player>();

			/** Establish the servers main port that it will listen on */
			server    = new ServerSocket(port);
			connected = true;
			/**
			 * Allows a ServerSocket to bind to the same address without raising
			 * an "already bind exception"
			 */
			server.setReuseAddress(true);
			start();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	
	/** Reset reuse port 
	 * @throws SocketException */
	public void resetPort() throws SocketException {
		server.setReuseAddress(true);
	}

	/** Now we start the servers main thread */
	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
			System.out.println(String.format("Server started: %s %d", server,thread.getId()));
		}
	}

	/** The main server thread starts and is listening for clients to connect */
	public void run() {
		while (thread != null) {
			try {
				addThread(server.accept());
			} catch (IOException e) {				
				System.out.println(e.getMessage());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
	}

	/** 
	 * Client connection is accepted and now we need to handle it and register it 
	 * and with the server | HashTable 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 **/
	private void addThread(Socket socket) throws IOException, ClassNotFoundException {
		System.out.println("Client accepted" + socket);
		ServerThread serverThread = null;
		if (clientCount < numberOfPlayersSpecified && numberOfPlayersSpecified >= 2) {
			try {
				/** Create a separate server thread for each client */
				serverThread = new ServerThread(this, socket);
				/** Open and start the thread */
				serverThread.open();
				serverThread.start();
				clients.put(serverThread.getID(), serverThread);
				this.clientCount++;
				sendToOneClient("number of players in the game is: " + numberOfPlayersSpecified, serverThread.getID());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} else {
			socket.close();
			System.out.println("Client Tried to connect" + socket );
			System.out.println("Client refused: maximum number of player reached");
			maxNumberOfPlayersReached = "Client refused: maximum number of player reached";
		}
	}


	@SuppressWarnings("unchecked")
	public synchronized void handle(int ID, String input) {

		Player gotPurple;
		

		if (input.startsWith("nameOfPlayer")) {
			String[] splitIt = input.split(" ");
			String newPlayerName 		    = splitIt[1];

			Player currentPlayer = new Player(newPlayerName);
			players.add(currentPlayer);
			clientsToPlayers.put(ID, currentPlayer);

			StringBuilder allPlayersNames = new StringBuilder();
			for(int i = 0; i < players.size(); i++) {
				allPlayersNames.append(players.get(i).getPlayerName());
				allPlayersNames.append(" ");
			}

			sendMessageToAllClients("Names of players " + clientCount + " " + allPlayersNames);
		}

		if((players.size() == numberOfPlayersSpecified) && (check==0)) {
			for(int i = 0; i < players.size(); i++) {
				sendMessageToAllClients("Pick a token");
			}
			check =1;
		}

		if(input.equals("Token is requested")) {
			String tokenForEachPlayer = "";
			if (players.size() == Config.TWO_PLAYERS) 
				tokenForEachPlayer = ruleEngine.distributeTokens(tokensForTwoPlayers);
			if(players.size() == Config.THREE_PLAYERS)
				tokenForEachPlayer = ruleEngine.distributeTokens(tokensForThreePlayers);
			else if(players.size() == Config.FOUR_PLAYERS) 
				tokenForEachPlayer = ruleEngine.distributeTokens(tokensForFourPlayers);
			else if(players.size() == Config.FIVE_PLAYERS)
				tokenForEachPlayer = ruleEngine.distributeTokens(tokensForFivePlayers);

			for (Integer key : clientsToPlayers.keySet()) {
				if (key == ID) {
					if (tokenForEachPlayer.equals("purple")) {
						gotPurple = clientsToPlayers.get(key);
						ruleEngine.playerWhoGotPurple = gotPurple.getPlayerName();

						gotPurple                 = clientsToPlayers.get(key);
						keyForPlayerWhoGotAPurple = key;
						nameOfPlayerWhoGotAPurple = gotPurple.getPlayerName();

					}

					clientsToPlayers.get(key).tokenColour = tokenForEachPlayer;

					/* This is where we send the token color to the player that requested for tokens */
					sendToOneClient("token " + tokenForEachPlayer, ID);
					System.out.println("Key is " + key + " " + clientsToPlayers.get(key));
					for (Player p : players) {
						if (p.getPlayerName().equals(clientsToPlayers.get(key).getPlayerName())) {
							clientsToPlayers.get(key).tokenColour = tokenForEachPlayer;
						}
					}
				}

			}	
		}

		if(input.equals("token was generated")) {
			numberOfTokensReceivedSoFar++;

			if(numberOfTokensReceivedSoFar == numberOfPlayersSpecified) {

				/* now we can send players who got a purple over to the client */
				sendToOneClient("now you can distribute cards", keyForPlayerWhoGotAPurple);
			}
		}

		if (input.equals("Distribute cards")) {

			for (Player p : players) {
				StringBuilder cardsToSend = new StringBuilder();
				p.handCards =  ruleEngine.distributeCardsToPlayers(p.getPlayerName(), ruleEngine.deckOfColorAndSupporters);
				System.out.println("Cards in a new distributed hand are " );
				for (String s: p.handCards) {
					System.out.println(s);
				}
				System.out.println("Hand of cards of " + p.getPlayerName() + " is " + p.handCards.get(0) + p.handCards.get(1));
				for(Entry<Integer, Player> entry: clientsToPlayers.entrySet()) {
					if(Objects.equals(p, entry.getValue())) {
						for(int i = 0; i < p.handCards.size(); i++) {
							cardsToSend.append(p.handCards.get(i));
							cardsToSend.append(" ");
						}
						sendToOneClient("Hand of card " + p.getPlayerName() + " " + cardsToSend.toString(), entry.getKey());

					}
				}
			}
		}

		if(input.equals("done distributing cards") && distributionReceived == 0) {

			if (tournamentCount == 1 || tournamentCount == 0) {
				sendToOneClient("Now everyone got a card set your self invisible", keyForPlayerWhoGotAPurple);
			}

			else  {
				sendToOneClient("Now everyone got a card set your self invisible", holdWinnerOfPreviousTournamentKey);
			}

			distributionReceived = 1;
		}


		if (input.contains("played shield")) {
			sendMessageToAllClients(input);
			checkSomeonePlayedAction = 1;
		}
		
		if (input.startsWith("Stunned")) {
			String[] splitIt = input.split(" ");
			ruleEngine.playActionCard(clientsToPlayers.get(ID), players, "action_card_stunned", splitIt[1], discardPile);
			sendMessageToAllClients("Player " + clientsToPlayers.get(ID).getPlayerName() + " played stunned on " + splitIt[1]);
			checkSomeonePlayedAction = 1;
		}
		
		if (input.startsWith("Outwit")) {
			String[] splitIt = input.split(" ");
			ruleEngine.playOutwitOn = splitIt[1];
			sendToOneClient("Choose your card to swap " + clientsToPlayers.get(ID).getPlayerName(), ID);
		}
		
		if (input.startsWith("Played outwit selected my card ")){
			String[] splitIt = input.split(" ");
			System.out.println("I'm trying to play " + splitIt[5] + " card");
			if (splitIt[5].equals("stunned") || splitIt[5].equals("shield")) {
				ruleEngine.cardToGive = splitIt[5];
			}
			
			else 
				ruleEngine.cardToGive = clientsToPlayers.get(ID).playerDisplay.get(Integer.parseInt(splitIt[5]));
			
			System.out.println("Playing outwit on " + ruleEngine.playOutwitOn);
			sendToOneClient("Choose your opponent's card to swap, " + ruleEngine.playOutwitOn , ID);
		}
		
		if (input.startsWith("Played outwit selected opponent's card")) {
			String[] splitIt = input.split(" ");
			
			if (splitIt[5].equals("stunned") || splitIt[5].equals("shield")) 
				ruleEngine.cardToTake = splitIt[5];
			
			else  {
				System.out.println("We are in an else statement" );
				for (Player p: players) {
					System.out.println("Player name is " +p.getPlayerName() );
					System.out.println("Played outwit on " + ruleEngine.playOutwitOn);
					if (p.getPlayerName().equals(ruleEngine.playOutwitOn)) {
						System.out.println("Card is " + p.playerDisplay.get(Integer.parseInt(splitIt[5])));
						ruleEngine.cardToTake = p.playerDisplay.get(Integer.parseInt(splitIt[5]));
					}
				}
			}
			
			ruleEngine.playActionCard(clientsToPlayers.get(ID), players, "action_card_outwit",  " ", discardPile);
			
			if (ruleEngine.cardToTake.equals("shield")) {
				sendMessageToAllClients("Result of outwit is shield " + clientsToPlayers.get(ID).getPlayerName() + " " + ruleEngine.playOutwitOn);
			}
			else if (ruleEngine.cardToTake.equals("stunned")) {
				System.out.println("Card to take is stunned" );
				sendMessageToAllClients("Result of outwit is stunned " + clientsToPlayers.get(ID).getPlayerName() + " " + ruleEngine.playOutwitOn);
			}
			
			if (ruleEngine.cardToGive.equals("shield")) 
				sendMessageToAllClients("Result of outwit is shield " + ruleEngine.playOutwitOn + " " +  clientsToPlayers.get(ID).getPlayerName() );
			
			else if (ruleEngine.cardToGive.equals("stunned")) 
				sendMessageToAllClients("Result of outwit is stunned " + ruleEngine.playOutwitOn + " " +  clientsToPlayers.get(ID).getPlayerName() );
		
			for (Player p: players) {
				String playerDisplay = "";
				for (String s : p.playerDisplay) {
					playerDisplay += s + " ";
				}
				sendMessageToAllClients("UpdateDisplay " + p.getPlayerName() + " " +  playerDisplay);
				sendMessageToAllClients("Update score " + p.getPlayerName() + " " + p.totalCardValue);	
			}
			
			System.out.println("Playing outwit on " + ruleEngine.playOutwitOn);
			sendMessageToAllClients("Player " + clientsToPlayers.get(ID).getPlayerName() + " played action card'outwit' on " + ruleEngine.playOutwitOn);
			checkSomeonePlayedAction = 1;
			ruleEngine.resetOutwit();
			
		}
		
		if(input.equals("who starts the game now?")) {
			
			if ((tournamentCount == 0) || (tournamentCount == 1)) {
				
				/* get the player seated to the left of the player who got a purple token by calling the rule */
				playerToStartTheGameFirst  = ruleEngine.startTournamentFirst(players);
				
				for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
					if(entry.getValue().getPlayerName().equals(playerToStartTheGameFirst)) {
						keyForPlayerWhoStartsFirst = entry.getKey();
						entry.getValue().myTurnToPlay = true;

						for (int i = 0; i < players.size(); i++) {
							if (entry.getValue().getPlayerName().equals(players.get(i).getPlayerName()))
								turnToPlay = i;
						}
					}
				}
				
				/* Check here now if all the cards the player sited to the left of the person to start the game is all action 
				 * if it is then pass the turn else just send a message to the player sited to the left of the guy */
				ArrayList<String> playerHandOfCards = new ArrayList<String>();
				int playerToStartGameFirstIndex     = 0;
				
				for(int i = 0; i < players.size(); i++) {
					if(playerToStartTheGameFirst.equals(players.get(i).getPlayerName())) {
						/* Get this players index */
						playerToStartGameFirstIndex = i;
						break;
					}
				}
				
				playerHandOfCards = (ArrayList<String>) players.get(playerToStartGameFirstIndex).handCards.clone();
				boolean hasOnlyActionCards = ruleEngine.playerHasOnlyActions(playerHandOfCards);
				
				doesPlayerToStartHaveOnlyActionCards = hasOnlyActionCards;
				
				if(hasOnlyActionCards) {
					/* get the player sitting next to him or her */
					String nextPlayerToStartGame = ruleEngine.passTurnToNextPlayerIfCurrentPlayerHasOnlyActionCards(players, playerToStartTheGameFirst);
					int keyForNextPlayer = 0;
					
					/* Get the key for this player */
					for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
						if(entry.getValue().getPlayerName().equals(nextPlayerToStartGame)) {
							keyForNextPlayer = entry.getKey();
							keyForPlayerWhoIsNextToPlayerWithOnlyActionCards = keyForNextPlayer;
							entry.getValue().myTurnToPlay = true;

							for (int i = 0; i < players.size(); i++) {
								if (entry.getValue().getPlayerName().equals(players.get(i).getPlayerName()))
									turnToPlay = i;
							}
						}
					}
					
					sendToOneClient("Draw a card player", keyForNextPlayer);
					sendToOneClient("Your turn was passed to somone else", keyForPlayerWhoStartsFirst);
					sendToOneClient("Pick the tournament colour to begin", keyForNextPlayer);
				}
				
				else {
					sendToOneClient("Draw a card player", keyForPlayerWhoStartsFirst);
					sendToOneClient("Pick the tournament colour to begin", keyForPlayerWhoStartsFirst);
				}
			}
			
			else {
				
				playerToStartTheGameFirst  = overallWinnerOfATournament;
				
				for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
					if(entry.getValue().getPlayerName().equals(playerToStartTheGameFirst)) {
						keyForPlayerWhoStartsFirst = entry.getKey();
						entry.getValue().myTurnToPlay = true;

						for (int i = 0; i < players.size(); i++) {
							if (entry.getValue().getPlayerName().equals(players.get(i).getPlayerName()))
								turnToPlay = i;
						}
					}
				}
				
				/* Check here now if all the cards the player sited to the left of the person to start the game is all action 
				 * if it is then pass the turn else just send a message to the player sited to the left of the guy */
				ArrayList<String> playerHandOfCards = new ArrayList<String>();
				int playerToStartGameFirstIndex     = 0;
				
				for(int i = 0; i < players.size(); i++) {
					if(playerToStartTheGameFirst.equals(players.get(i).getPlayerName())) {
						/* Get this players index */
						playerToStartGameFirstIndex = i;
						break;
					}
				}
				
				playerHandOfCards = (ArrayList<String>) players.get(playerToStartGameFirstIndex).handCards.clone();
				boolean hasOnlyActionCards = ruleEngine.playerHasOnlyActions(playerHandOfCards);
				
				doesPlayerToStartHaveOnlyActionCards = hasOnlyActionCards;
				
				System.out.println("doesPlayerToStartHaveOnlyActionCards: "+doesPlayerToStartHaveOnlyActionCards);
				
				if(hasOnlyActionCards) {
					/* get the player sitting next to him or her */
					String nextPlayerToStartGame = ruleEngine.passTurnToNextPlayerIfCurrentPlayerHasOnlyActionCards(players, playerToStartTheGameFirst);
					int keyForNextPlayer = 0;
					
					/* Get the key for this player */
					for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
						if(entry.getValue().getPlayerName().equals(nextPlayerToStartGame)) {
							keyForNextPlayer = entry.getKey();
							keyForPlayerWhoIsNextToPlayerWithOnlyActionCards = keyForNextPlayer;
							entry.getValue().myTurnToPlay = true;

							for (int i = 0; i < players.size(); i++) {
								if (entry.getValue().getPlayerName().equals(players.get(i).getPlayerName()))
									turnToPlay = i;
							}
						}
					}
					
					sendToOneClient("Draw a card player", keyForNextPlayer);
					sendToOneClient("Your turn was passed to somone else", keyForPlayerWhoStartsFirst);
					sendToOneClient("Pick the tournament colour to begin", keyForNextPlayer);
					overallWinnerOfATournament = "";
				}
				
				else {
					sendToOneClient("Draw a card player", keyForPlayerWhoStartsFirst);
					sendToOneClient("Pick the tournament colour to begin", keyForPlayerWhoStartsFirst);
					overallWinnerOfATournament = "";
				}
			} 
		}

		if(input.equals("I have picked a card now I want to choose a colour for the tournament") && !checkOneCardFirst) {

			/* Now I can pick a tournament color */
			/* Before you send this message check if this player who has this key has all action cards */

			if(doesPlayerToStartHaveOnlyActionCards) {
				sendToOneClient("choose the tournament colour", keyForPlayerWhoIsNextToPlayerWithOnlyActionCards);
			}

			else {
				System.out.println("Key for  a player who startsFirst : " + keyForPlayerWhoStartsFirst);
				sendToOneClient("choose the tournament colour", keyForPlayerWhoStartsFirst);
			}
			checkOneCardFirst = true;
		}

		if((clientCount == numberOfPlayersSpecified) && (checkNames == 0)) {
			for (int i = 0; i < numberOfPlayersSpecified; i++) {
				oldPlayers.add(players.get(i));
			}

			String names = "";

			for (int i = 0; i < players.size(); i++) {
				names += players.get(i).getPlayerName() + " ";
			}

			System.out.println(names);
			sendMessageToAllClients("player Names " + names );
			checkNames = 1;
		}

		if(input.equals("now pick your colour")) {
			if(!ruleEngine.getTournamentColour().equals("purple")) {
				sendToOneClient("okay I will serve you a list of colours " + tournamentCount + " " + "prevTournamentColor:"+"empty", ID);
			}
			
			else {
				sendToOneClient("okay I will serve you a list of colours " + tournamentCount + " " + "prevTournamentColor:"+currentColourOfPrevTournament, ID);
			}
		}
		
		if(input.startsWith("colour tournament")) {
			String[] splitIt = input.split(" ");
			String tournamentColour = splitIt[2].toLowerCase();

			if (ruleEngine.colourIsValid(clientsToPlayers.get(ID), tournamentColour)) {

				/* Call rule engine to set tournament color */
				ruleEngine.setTournamentColour(tournamentColour);
				
				tournamentColourChosen = true;
				
				System.out.println("Tournament Colour is now: " + tournamentColourChosen);
				
				sendMessageToAllClients("tournament colour is " + tournamentColour);

				/* Set this tournament so the server knows too */
				previousTournamentColour = ruleEngine.getTournamentColour();
				currentColourOfPrevTournament = ruleEngine.getTournamentColour();

				sendMessageToAllClients("tournamentNumber1 " + String.valueOf(tournamentCount));
			}

			else 
				sendToOneClient("Wrong Colour", ID);
			
				/* Set a default color for the tournament if cancel button was clicked */
		}
		
		if (input.startsWith("tournament colour was changed")) {
			String[] splitIt = input.split(" ");
			String tournamentColour = splitIt[4].toLowerCase();
			ruleEngine.setTournamentColour(tournamentColour);
			sendMessageToAllClients("tournament colour is " + tournamentColour);
			/* Set this tournament so the server knows too */
			previousTournamentColour = ruleEngine.getTournamentColour();
			checkSomeonePlayedAction = 1;
		}

		if (input.equals("Give me a card")) {

			sendToOneClient(clientsToPlayers.get(ID).getPlayerName() + " Your new card is " + (ruleEngine.drawCard(clientsToPlayers.get(ID), ruleEngine.deckOfColorAndSupporters)), ID);
			clientsToPlayers.get(ID).IgotANewCard = true;

			if((countForCardGivenFirstTournament == 0)) {

				for (Integer key: clientsToPlayers.keySet()) {
					if (key != ID) {
						sendToOneClient("first player got a new card now " + clientsToPlayers.get(ID).getPlayerName(), key);
					}
				}

				sendToOneClient("Enable withdraw button for player", ID);
				countForCardGivenFirstTournament = 1;
			}

			else {
				/*send message to all clients to update their screens */
				for (Integer key: clientsToPlayers.keySet()) {
					if (key != ID) {
						sendToOneClient("Someone got a new card " + clientsToPlayers.get(ID).getPlayerName(), key);
					}
				}

				sendToOneClient("Enable withdraw button for player", ID);
			}
		}
		if (input.equals("I am done")) {
			if (ruleEngine.checkHighestValue(clientsToPlayers.get(ID), players)) {
				clientsToPlayers.get(ID).IgotANewCard = false;
				clientsToPlayers.get(ID).myTurnToPlay = false;
				ruleEngine.resetStunned(clientsToPlayers.get(ID));
				/* pass turn to the next player and notify him */
				int holdPlayerSize = players.size();
				holdPlayerSize--;
				if (turnToPlay != holdPlayerSize) {
					turnToPlay++;
				}
				else 
					turnToPlay = 0;

				System.out.println("Player " + players.get(turnToPlay).getPlayerName() + " takes his turn to play");
				players.get(turnToPlay).myTurnToPlay = true;

				for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
					if(entry.getValue().getPlayerName().equals(players.get(turnToPlay).getPlayerName())) {
						System.out.println("I found this guy " + players.get(turnToPlay));
						int key = entry.getKey();
						sendToOneClient("Your turn to play", key);
						sendToOneClient("Draw a card", key);
					}
				}
				
				sendToOneClient("withdraw button disabled", ID);
			}
			else 
				sendToOneClient("You have to withdraw or play a card of higher value", ID);
		}

		if (input.startsWith("Break lance")) {
			String[] splitIt = input.split(" ");
			ruleEngine.playActionCard(clientsToPlayers.get(ID), players, "action_card_break-lance", splitIt[2], discardPile);
			for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
				if(entry.getValue().getPlayerName().equals(splitIt[2])) {
					int key = entry.getKey();
					StringBuilder playerDisplay = new StringBuilder();
					for(int i = 0; i < clientsToPlayers.get(key).playerDisplay.size(); i++) {
						playerDisplay.append(clientsToPlayers.get(key).playerDisplay.get(i));
						playerDisplay.append(" ");
					}
					System.out.println("New set of cards is " + playerDisplay);
					System.out.println("The player to remove cards is " + splitIt[2]);
					sendMessageToAllClients("UpdateDisplay " + splitIt[2] + " " + playerDisplay);
					sendMessageToAllClients("Update score " + splitIt[2] + " "+ clientsToPlayers.get(key).getTotalCardvalue());
				}
			}
			
			sendMessageToAllClients(clientsToPlayers.get(ID).getPlayerName() + " played action card 'break lance' on " + splitIt[2] + "'s display");
			checkSomeonePlayedAction = 1;
		}
		
		if (input.startsWith("Wants to play Ivanhoe")) {
			String split[] = input.split(" ");
			if (split[4].equals("yes")) 
				wantsToPlayIvanhoe = true;
			
			else {
				if (checkSomeonePlayedAction==2) {
					result = ruleEngine.askPlayerActionCard(clientsToPlayers.get(playedLastAction), "action_card_knock-down", players);
					System.out.println("We are in check for someon: Rules engine response is: "+ result);
					sendToOneClient(result, playedLastAction);	
				}
				sendToOneClient("Enable done", playedLastAction);
				wantsToPlayIvanhoe = false;
				checkSomeonePlayedAction = 0;
			}
			
		}
		if (input.startsWith("Dodge")) {
			String[] splitIt = input.split(" ");
			ruleEngine.playActionCard(clientsToPlayers.get(ID), players, "action_card_dodge", splitIt[4] + " " + splitIt[3] , discardPile);
			
			for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
				if(entry.getValue().getPlayerName().equals(splitIt[4])) {
					int key = entry.getKey();
					StringBuilder playerDisplay = new StringBuilder();
					for(int i = 0; i < clientsToPlayers.get(key).playerDisplay.size(); i++) {
						playerDisplay.append(clientsToPlayers.get(key).playerDisplay.get(i));
						playerDisplay.append(" ");
					}
					
					sendMessageToAllClients("UpdateDisplay " + splitIt[4] + " " + playerDisplay);
					sendMessageToAllClients("Update score " + splitIt[4] + " "+ clientsToPlayers.get(key).getTotalCardvalue());
				}
			}
			sendMessageToAllClients(clientsToPlayers.get(ID).getPlayerName() + " played action card 'dodge'");
			checkSomeonePlayedAction = 1;
		}
		if (input.startsWith("Retreat add card ")) {
			String splitIt[] = input.split(" ");
			sendToOneClient("Your card is back " + clientsToPlayers.get(ID).playerDisplay.get(Integer.parseInt(splitIt[3])) + " " + clientsToPlayers.get(ID).getPlayerName(), ID);
			ruleEngine.playActionCard(clientsToPlayers.get(ID), players, "action_card_retreat", 
					clientsToPlayers.get(ID).playerDisplay.get(Integer.parseInt(splitIt[3])), discardPile);
			String playerDisplay = "";
			for (String s:clientsToPlayers.get(ID).playerDisplay) {
				playerDisplay += s;
				playerDisplay += " ";
			}
			
			sendMessageToAllClients("UpdateDisplay " + clientsToPlayers.get(ID).getPlayerName() + " " + playerDisplay);
			sendMessageToAllClients("Update score " + clientsToPlayers.get(ID).getPlayerName() + " "+ clientsToPlayers.get(ID).getTotalCardvalue());
			sendMessageToAllClients(clientsToPlayers.get(ID).getPlayerName() + " played action card 'retreat'");
			checkSomeonePlayedAction = 1;
			
		}
		
		if (input.startsWith("Riposte")) {
			String[] splitIt = input.split(" ");
			ruleEngine.playActionCard(clientsToPlayers.get(ID), players, "action_card_riposte", splitIt[1], discardPile);
			for (Player p:players) {
				if (p.getPlayerName().equals(clientsToPlayers.get(ID).getPlayerName()) || p.getPlayerName().equals(splitIt[1])) {
					String playerDisplay = "";
					for (String s : p.playerDisplay) {
						playerDisplay += s + " ";
					}
					sendMessageToAllClients("UpdateDisplay " + p.getPlayerName() + " " +  playerDisplay);
					sendMessageToAllClients("Update score " + p.getPlayerName() + " " + p.totalCardValue);
				}
			}
			sendMessageToAllClients(clientsToPlayers.get(ID).getPlayerName() + " played action card 'riposte' on " + splitIt[1] + "'s display");
			checkSomeonePlayedAction = 1;
		}
		
		if (input.startsWith("outmaneuver")) {
			ruleEngine.playActionCard(clientsToPlayers.get(ID), players, "action_card_outmaneuver", "", discardPile);
			for (Player p: players) {
				String playerDisplay = "";
				for (String s : p.playerDisplay) {
					playerDisplay += s + " ";
				}
				sendMessageToAllClients("UpdateDisplay " + p.getPlayerName() + " " +  playerDisplay);
				sendMessageToAllClients("Update score " + p.getPlayerName() + " " + p.totalCardValue);	
			}
			sendMessageToAllClients(clientsToPlayers.get(ID).getPlayerName() + " played action card 'outmaneuver'");
			checkSomeonePlayedAction = 1;
		}
		
		if (input.equals("I played adapt")) {
			for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
				if(!ruleEngine.compareValue(entry.getValue())) {
					int key = entry.getKey();
					System.out.println("We got hereeee");
					sendToOneClient("You have to remove cards of the same value " + clientsToPlayers.get(key).getPlayerName(), key);
					ruleEngine.playersToRemoveAdapt++;
					sendToOneClient("Done should be disabled", ID);
				}
			}
		}
		
		if (input.startsWith("adapt remove card")) {
			String splitIt[] = input.split(" ");
			int index = Integer.parseInt(splitIt[3]);
			ruleEngine.playActionCard(clientsToPlayers.get(ID), players, "action_card_adapt", clientsToPlayers.get(ID).playerDisplay.get(index), discardPile);
			StringBuilder playerDisplay = new StringBuilder();
			for(int i = 0; i < clientsToPlayers.get(ID).playerDisplay.size(); i++) {
				playerDisplay.append(clientsToPlayers.get(ID).playerDisplay.get(i));
				playerDisplay.append(" ");
			}
			
			if (ruleEngine.compareValue(clientsToPlayers.get(ID))) {
				sendToOneClient("You don't need to remove any cards.", ID);
				ruleEngine.playersWhoRemovedAdapt++;
				if (ruleEngine.playersToRemoveAdapt == ruleEngine.playersWhoRemovedAdapt) {
					ruleEngine.adaptJustPlayed = false;
					for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
						if(entry.getValue().getPlayerName().equals(ruleEngine.playedAdapt)) {
							int key = entry.getKey();
							sendToOneClient("Enable done", key);
							checkSomeonePlayedAction = 1;
							
						}
					}
					sendMessageToAllClients(clientsToPlayers.get(ID).getPlayerName() + " played action card 'adapt'");
				}
			}
			
			else
				sendToOneClient("You have to remove cards of the same value " + clientsToPlayers.get(ID).getPlayerName(), ID);
		
			sendMessageToAllClients("UpdateDisplay " + clientsToPlayers.get(ID).getPlayerName() + " " +  playerDisplay);
			sendMessageToAllClients("Update score " + clientsToPlayers.get(ID).getPlayerName() + " " + clientsToPlayers.get(ID).totalCardValue);
			
		}
		
		if (input.startsWith("disgrace") || input.startsWith("charge") || input.startsWith("counter-charge")) {
			ruleEngine.playActionCard(clientsToPlayers.get(ID), players, "action_card_" + input, "", discardPile);
			for (Player p: players) {
				String playerDisplay = "";
				for (String s : p.playerDisplay) {
					playerDisplay += s + " ";
				}
				sendMessageToAllClients("UpdateDisplay " + p.getPlayerName() + " " +  playerDisplay);
				sendMessageToAllClients("Update score " + p.getPlayerName() + " " + p.totalCardValue);	
			}
			sendMessageToAllClients(clientsToPlayers.get(ID).getPlayerName() + " played action card" +  "'" + input +"'");
			checkSomeonePlayedAction = 1;
			
		}
		
		
		if (input.startsWith("Knock-Down")) {
			String[] splitIt = input.split(" ");
			ruleEngine.playActionCard(clientsToPlayers.get(ID), players, "action_card_knock-down", splitIt[1], discardPile);
			for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
				if(entry.getValue().getPlayerName().equals(splitIt[1])) {
					int key = entry.getKey();
					int disable = clientsToPlayers.get(key).holdKnockDownInd;
					System.out.println("Card to give: " + clientsToPlayers.get(key).handCards.get(clientsToPlayers.get(key).holdKnockDownInd));
					sendToOneClient(clientsToPlayers.get(ID).getPlayerName() + " Your new card is " + clientsToPlayers.get(key).handCards.get(clientsToPlayers.get(key).holdKnockDownInd), ID);
					sendToOneClient("Disable " + ++disable + " " + clientsToPlayers.get(key).getPlayerName(), key);
					sendMessageToAllClients(clientsToPlayers.get(ID).getPlayerName() + " played action card 'knock down' on " + splitIt[1] + "'s display");
					clientsToPlayers.get(key).handCards.set(clientsToPlayers.get(key).holdKnockDownInd, "");
				}
			}
			
		}
		
		if (input.startsWith("Card was played")) {
			String[] splitIt = input.split(" ");
			int index = Integer.parseInt(splitIt[3]);
			index--;
			System.out.println("Card I'm trying to play is " + clientsToPlayers.get(ID).handCards.get(index));

			if(tournamentColourChosen == true) {

				if (clientsToPlayers.get(ID).handCards.get(index).contains("ivanhoe") && !ruleEngine.lastActionCardPlayed.equals("") && wantsToPlayIvanhoe) {
					int holdIndex = index + 1;
					sendToOneClient("Disable " + holdIndex + " " + clientsToPlayers.get(ID).getPlayerName(), ID);
					String response =ruleEngine.ivanhoePlayed(clientsToPlayers.get(ID), players);
					System.out.println("Rule engine said: " + response );
					for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
						for (Player p: players) {
							if(entry.getValue().getPlayerName().equals(p.getPlayerName())) {
								int key = entry.getKey();
								clientsToPlayers.put(key, p);
							}
						}
					}

					ArrayList<Player> tempArrayOld = new ArrayList<Player>();
					for (Player p: players) {
						for (Player old: oldPlayers) {
							if (p.getPlayerName().equals(old.getPlayerName())) {
								tempArrayOld.add(p);
							}
						}
					}
					oldPlayers = tempArrayOld;
					if (ruleEngine.lastActionCardPlayed.equals("retreat"))
						sendToOneClient(response, playedLastAction);
					else 
						sendMessageToAllClients(response);

					for (Player p : players) {
						StringBuilder playerDisplay = new StringBuilder();
						for(int i = 0; i < p.playerDisplay.size(); i++) {
							playerDisplay.append(p.playerDisplay.get(i));
							playerDisplay.append(" ");
						}
						sendMessageToAllClients("UpdateDisplay " + p.getPlayerName() + " " + playerDisplay);
						sendMessageToAllClients("Update score " + p.getPlayerName() + " "+ p.getTotalCardvalue());
						if (p.hasShield) 
							sendMessageToAllClients("add shield " +p.getPlayerName());
						else 
							sendMessageToAllClients("remove shield " + p.getPlayerName());
						
						if (p.stunnedPlayedOnMe) 
							sendMessageToAllClients("add stunned " + p.getPlayerName());
						else
							sendMessageToAllClients("remove stunned " + p.getPlayerName());
					}
					sendMessageToAllClients(clientsToPlayers.get(ID).getPlayerName() + " played Ivanhoe!");
					sendToOneClient("Enable done", playedLastAction);
					checkSomeonePlayedAction = 0;
					wantsToPlayIvanhoe = false;
				}

				else if (clientsToPlayers.get(ID).myTurnToPlay) {
					if (checkSomeonePlayedAction == 0) {
						if (clientsToPlayers.get(ID).IgotANewCard ) {
							if (ruleEngine.playColorOrSupporterCard(clientsToPlayers.get(ID), clientsToPlayers.get(ID).handCards.get(index))) {
								System.out.println("Your card was played ");
								sendToOneClient("Enable done", ID);
								int holdIndex = index + 1;
								sendToOneClient("Disable " + holdIndex + " " + clientsToPlayers.get(ID).getPlayerName(), ID);
								int sizeOfDisplay = clientsToPlayers.get(ID).playerDisplay.size();
								StringBuilder playerDisplay = new StringBuilder();
								for(int i = 0; i < clientsToPlayers.get(ID).playerDisplay.size(); i++) {
									playerDisplay.append(clientsToPlayers.get(ID).playerDisplay.get(i));
									playerDisplay.append(" ");
								}

								sendMessageToAllClients("UpdateDisplay " + clientsToPlayers.get(ID).getPlayerName() + " " + playerDisplay);
								sendMessageToAllClients("Update score " + clientsToPlayers.get(ID).getPlayerName() + " "+ clientsToPlayers.get(ID).getTotalCardvalue());
							}
							
							else if (ruleEngine.playingAction(clientsToPlayers.get(ID).handCards.get(index), clientsToPlayers.get(ID))) {
								String actionCard  = clientsToPlayers.get(ID).handCards.get(index);
								boolean checkIfIvanhoe = false;
								int keyHasIvanhoe = 0;
								for(Entry<Integer, Player> entry: clientsToPlayers.entrySet()) {
									for (String card : entry.getValue().handCards) {
										if (card.contains("ivanhoe")) {
											checkIfIvanhoe = true;
											keyHasIvanhoe = entry.getKey();
										}
									}
								}
								if (actionCard.equals("action_card_knock-down") && checkIfIvanhoe) {
									checkSomeonePlayedAction=2;
									ruleEngine.lastActionCardPlayed = "action_card_knock-down";
									if (ID!=keyHasIvanhoe)
										sendToOneClient("Wait for Ivanhoe holder to make a decision!", ID);
									sendToOneClient("You have Ivanhoe card. Do you want to play it? ", keyHasIvanhoe);
									sendToOneClient("Done should be disabled", ID);
								}
								
								System.out.println("Action card " + actionCard + " was played");
								
								result = ruleEngine.askPlayerActionCard(clientsToPlayers.get(ID), actionCard, players);
								System.out.println("Rules engine response is: "+ result);
								if (!result.equals(""))  {
									playedLastAction = ID;
									System.out.println("Result isn't null");		
									ruleEngine.actionCardPlayedRemove(clientsToPlayers.get(ID), actionCard, discardPile);
									int holdIndex = index + 1;
									sendToOneClient("Disable " + holdIndex + " " + clientsToPlayers.get(ID).getPlayerName(), ID);
									//update all players' displays and score
									for (Player p : players) {
										StringBuilder playerDisplay = new StringBuilder();
										for(int i = 0; i < p.playerDisplay.size(); i++) {
											playerDisplay.append(p.playerDisplay.get(i));
											playerDisplay.append(" ");
										}
										sendMessageToAllClients("UpdateDisplay " + p.getPlayerName() + " " + playerDisplay);
										sendMessageToAllClients("Update score " + p.getPlayerName() + " " + p.getTotalCardvalue());
									}
									if (checkSomeonePlayedAction!=2) {
										sendToOneClient(result, ID);
										sendToOneClient("Enable done", ID);
									}
								}

								else {
									sendToOneClient("You are trying to play wrong card" , ID);
								}
							}

							else {
								sendToOneClient("You are trying to play wrong card" , ID);
							}
						}

						else 
							sendToOneClient("You have to pick a card first", ID);
					}
				}
					else 
						sendToOneClient("Not your turn to play", ID);
			}

			else {
				sendToOneClient("You cannot play choose tournament colour", ID);
			}

		}

		if(input.startsWith("The winner of the purple tournament has chosen a color ")) {

			String[] splitIt = input.split(" ");
			String tokenColourChosen = splitIt[10];
		
			/* call rule engine and pass in that this into it */
			String winner = ruleEngine.winTournament(players, ruleEngine.getTournamentColour(), tokenColourChosen);
			
			if(winner.equals("You already have this token")) {
				sendToOneClient("you already have this token " + tokenColourChosen, playerWhoWonThePurpleTournamentKey);
			
			}

			else {
				sendToOneClient("Server is done adding token to my list of tokens " + tokenColourChosen + " " + players.get(0).getPlayerName(), playerWhoWonThePurpleTournamentKey);
				sendMessageToAllClients("make token visible to other players " + tokenColourChosen + " " + players.get(0).getPlayerName());
					
			}
		}

		if (input.startsWith("I chose to remove ")) {
			String splitIt[] = input.split(" ");
			ruleEngine.removeToken(clientsToPlayers.get(ID), splitIt[4]);
			sendMessageToAllClients("Remove a token " + splitIt[4] + " player " + clientsToPlayers.get(ID).getPlayerName());


		}

		if(input.equals("I want to withdraw")) {
			boolean canWithdraw = false;
			/*check if he has a maiden on his display */
			if (!ruleEngine.hasToGiveUpToken(clientsToPlayers.get(ID), players) || gaveUpToken) {
				if(ruleEngine.Withdraw(clientsToPlayers.get(ID).getPlayerName(), players, discardPile, winningTokens, withdrawnPlayers)) {
					String LeftPlayerName = clientsToPlayers.get(ID).getPlayerName();

					sendToOneClient("I left. Disable all my buttons", ID);
					sendMessageToAllClients("Left " + LeftPlayerName);
					canWithdraw = true;
					int holdSize = players.size();

					if (turnToPlay == holdSize) {
						turnToPlay=0;
					}
					
					players.get(turnToPlay).myTurnToPlay = true;
					for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
						if(entry.getValue().getPlayerName().equals(players.get(turnToPlay).getPlayerName())) {
							int key = entry.getKey();
							
							if(players.size() != 1) {
								sendToOneClient("Your turn to play", key);
								sendToOneClient("Draw a card", key);
							}
						}
					}
				}
			}

			else {
				/* If player has a maiden in his display, give up a token */
				canWithdraw = true;
				StringBuilder allTokensOfAPlayer = new StringBuilder();

				for(int i = 0; i < clientsToPlayers.get(ID).playerTokens.size(); i++) {
					allTokensOfAPlayer.append(clientsToPlayers.get(ID).playerTokens.get(i));
					allTokensOfAPlayer.append("_");
				}
				String messageToSent = "Give up a token " + allTokensOfAPlayer;
				sendToOneClient(messageToSent, ID);
				gaveUpToken = true;
				
			}

			if (!canWithdraw) {
				sendToOneClient("You cannot withdraw", ID);
			}

			if(players.size() == 1) {

				String tournamentColour = ruleEngine.getTournamentColour();	

				/* check who the winner of the tournament is */
				if(!tournamentColour.equalsIgnoreCase("purple")) {
					String winner = ruleEngine.winTournament(players, tournamentColour, "");

					if(winner.equals("You already have this token")) {
						playerWhoWonTheCurrentTournament = players.get(0).getPlayerName();
						overallWinnerOfATournament       = playerWhoWonTheCurrentTournament;

						for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
							if(entry.getValue().getPlayerName().equals(playerWhoWonTheCurrentTournament)) {
								playerWhoWonCurrentTournamentKey = entry.getKey();
								holdWinnerOfPreviousTournamentKey = playerWhoWonCurrentTournamentKey;

								sendToOneClient("you already have this token " + ruleEngine.getTournamentColour(), playerWhoWonCurrentTournamentKey);
								sendToOneClient("you are the winner of this tournament " + playerWhoWonTheCurrentTournament, playerWhoWonCurrentTournamentKey);
							}
						}
						
						for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
							if(!(entry.getValue().getPlayerName().equals(playerWhoWonTheCurrentTournament))) {
								sendToOneClient("tournament finished new tournament starting", entry.getKey());
							}
						}
					}

					else {	
						playerWhoWonTheCurrentTournament  = players.get(0).getPlayerName();
						overallWinnerOfATournament        = playerWhoWonTheCurrentTournament;

						for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
							if(entry.getValue().getPlayerName().equals(playerWhoWonTheCurrentTournament)) {
								playerWhoWonCurrentTournamentKey = entry.getKey();
								holdWinnerOfPreviousTournamentKey = playerWhoWonCurrentTournamentKey;
								sendToOneClient("you are the winner of this tournament " + playerWhoWonTheCurrentTournament, playerWhoWonCurrentTournamentKey);
								sendToOneClient("Server is done adding token to my list of tokens " + ruleEngine.getTournamentColour() + " " + playerWhoWonTheCurrentTournament, playerWhoWonCurrentTournamentKey);
								sendMessageToAllClients("make token visible to other players " + ruleEngine.getTournamentColour() + " " + playerWhoWonTheCurrentTournament);
							}
						}
						
						for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
							if(!(entry.getValue().getPlayerName().equals(playerWhoWonTheCurrentTournament))) {
								sendToOneClient("tournament finished new tournament starting", entry.getKey());
							}
						}
					}
				}

				else {
					playerWhoWonTheCurrentTournament  = players.get(0).getPlayerName();
					playerWhoWonThePurpleTournament   = playerWhoWonTheCurrentTournament;
					overallWinnerOfATournament        = playerWhoWonTheCurrentTournament;

					for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
						if(entry.getValue().getPlayerName().equals(playerWhoWonTheCurrentTournament)) {
							playerWhoWonCurrentTournamentKey = entry.getKey();
							playerWhoWonThePurpleTournamentKey = playerWhoWonCurrentTournamentKey;
							holdWinnerOfPreviousTournamentKey = playerWhoWonCurrentTournamentKey;

							sendToOneClient("you are the winner of this tournament " + playerWhoWonTheCurrentTournament, playerWhoWonCurrentTournamentKey);
							sendToOneClient("This is a purple tournament choose your colour", playerWhoWonCurrentTournamentKey);
						}
					}
					
					for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
						if(!(entry.getValue().getPlayerName().equals(playerWhoWonTheCurrentTournament))) {
							sendToOneClient("tournament finished new tournament starting", entry.getKey());
						}
					}
				}
			}
		}
		if(input.equals("Tournament done!")) {

			String playerName = "";
			if (ruleEngine.winGame(players, oldPlayers.size()) == null) {
				for (Player p : withdrawnPlayers) {
					playerName += p.getPlayerName() + " "; 
				}

				tournamentCount += 1;	

				if(previousTournamentColour.equals("purple")) {

					sendToOneClient("distributes cards first", playerWhoWonCurrentTournamentKey);
				}

				else {

					sendToOneClient("distributes cards first", playerWhoWonCurrentTournamentKey);
				}

				sendMessageToAllClients("Enable hand of cards");
				sendMessageToAllClients("players who withdrew we have to enable " + playerName);

				/* put back all players in the game */
				for (int i = 0; i < numberOfPlayersSpecified; i++) {
					players = new ArrayList<Player>();
					players.addAll(oldPlayers);
				}
				for (Player p:players) {
					ruleEngine.resetMyCards(discardPile, p);
				}
				withdrawnPlayers.removeAll(withdrawnPlayers);

				ruleEngine.putBackCardsInDsicardPileIntoDeck(ruleEngine.deckOfColorAndSupporters, discardPile);
				previousTournamentColour = "";

				for (int i = 0; i < players.size(); i++) {
					if (players.get(i).getPlayerName().equals(playerWhoWonTheCurrentTournament)) {
						turnToPlay = i;
					}
				}

				/* reset display value as well */
				for(int i = 0; i < players.size(); i++) {
					players.get(i).resetTotalCardValue();
					players.get(i).resetDisplay();
				}

//				System.out.println("Tokens of the winner: ");
//				for (Player p: players) {
//					if (p.getPlayerName().equals(playerWhoWonTheCurrentTournament)) {
//						for (String s : p.playerTokens) {
//							System.out.println(s);
//						}
//					}
//				}
				playerWhoWonTheCurrentTournament = "";
				playerWhoWonCurrentTournamentKey = 0;
				countForCardGivenFirstTournament = 0;
				distributionReceived = 0;
				tournamentColourChosen = false;
				wantsToPlayIvanhoe = false;
				playedLastAction = 0;
				checkSomeonePlayedAction = 0;
				checkOneCardFirst = false;
			}
			else {
				//System.out.println("We have a winner!!!");
				for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
					if((entry.getValue().getPlayerName().equals(players.get(0).getPlayerName()))) {
						sendToOneClient("Winner of this game is " +  players.get(0).getPlayerName(), entry.getKey());
						break;
					}
				}
				
				for (Map.Entry<Integer, Player> entry : clientsToPlayers.entrySet()) {
					if(!(entry.getValue().getPlayerName().equals(players.get(0).getPlayerName()))) {
						sendToOneClient("Game is done! Sorry try again next time", entry.getKey());
					}
				}
			}
		}
		
		if (checkSomeonePlayedAction==1  && !wantsToPlayIvanhoe)  {
			boolean checkIfIvanhoe  = false;
			for(Entry<Integer, Player> entry: clientsToPlayers.entrySet()) {
				for (String card : entry.getValue().handCards) {
					if (card.contains("ivanhoe")) {
						checkIfIvanhoe = true;
						sendToOneClient("You have Ivanhoe card. Do you want to play it? ", entry.getKey());
						if (entry.getKey()!=ID)
							sendToOneClient("Wait for Ivanhoe holder to make a decision!", ID);
						sendToOneClient("Done should be disabled", ID);
					}
				}
			}
			if (!checkIfIvanhoe)
				checkSomeonePlayedAction = 0;
		}
		System.out.println(input);

	}
	/* Send message to all players */
	public void sendMessageToAllClients(String input) {
		for (ServerThread to : clients.values()) {
			to.send(String.format("%s\n", input));
		}
	}

	/* Send message to only one player */
	public void sendToOneClient(String input, int ID) {
		ServerThread from = clients.get(ID);
		from.send(String.format("%s\n", input));
	}

	/** Try and shutdown the client cleanly */
	public synchronized void remove(int ID) {
		if (clients.containsKey(ID)) {
			ServerThread toTerminate = clients.get(ID);
			clients.remove(ID);
			clientCount--;

			toTerminate.close();
			toTerminate = null;
		}
	}

	/** Shutdown the server cleanly */
	public void shutdown() {
		Set<Integer> keys = clients.keySet();

		if (thread != null) {
			thread = null;
		}

		try {
			for (Integer key : keys) {
				clients.get(key).close();
				clients.put(key, null);
			}
			clients.clear();
			server.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Server Shutdown cleanly" + server );
	}

}