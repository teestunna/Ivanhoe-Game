package networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import config.Config;
import controller.Controller;

public class ClientApp implements Runnable {

	private int ID = 0;
	private Socket socket            = null;
	private Thread thread            = null;
	private ClientThread   client    = null;
	private BufferedReader console   = null;
	private BufferedReader streamIn  = null;
	private BufferedWriter streamOut = null;
	public int			   numberPlayersSpecified = 0;
	Controller controller;

	public ClientApp (String serverName, int serverPort, String pName) {  

		System.out.println("Establishing connection. Please wait ...");

		try {  
			this.socket = new Socket(serverName, serverPort);
			this.ID = socket.getLocalPort();
			System.out.println(ID + ": Connected to server: " + socket.getInetAddress());
			System.out.println(ID + ": Connected to portid: " + socket.getLocalPort());

			this.start();

			/* Send player name to the server */
			send("nameOfPlayer " + pName);

		} catch(UnknownHostException uhe) {  
			System.err.println(ID + ": Unknown Host");
			System.out.println(uhe.getMessage());
		} catch(IOException ioe) {  
			System.out.println(ID + ": Unexpected exception");
			System.out.println(ioe.getMessage());  
		}
	}

	public int getID () {
		return this.ID;
	}
	
	public String messageReceivedFromServer() throws IOException {
		String sentence = streamIn.readLine();
		
		return sentence;
	}

	public void start() throws IOException {  
		try {
			console	  = new BufferedReader(new InputStreamReader(System.in));
			streamIn  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			streamOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			controller = new Controller(Config.GAME_NAME, this);

			if (thread == null) {  
				client = new ClientThread(this, socket);
				thread = new Thread(this);                   
				thread.start();
			}
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());  
			throw ioe;
		}
	}

	/** The server processes the messages and passes it to the client to send it */
	public void send(String input) {

		try {
			streamOut.write(input + "\n");
			streamOut.flush();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	public void run() { 
		System.out.println("\n" + ID + ": Client Started...\n");
	}


	public void handle (String msg) throws IOException {

		String[] splitIt = msg.split(" ");

		if (msg.startsWith("number of players in the game is:")) {
			numberPlayersSpecified = Integer.parseInt(splitIt[7]);
		}
		
		if (msg.startsWith("Names of players")) {
			int    numberOfPlayersCurrently = Integer.parseInt(splitIt[3]);
			controller.updatePlayers(msg, numberOfPlayersCurrently);
			controller.setVisible(true);
		}

		if(msg.equals("Pick a token")) {
			controller.DisplayPickAToken();
		}

		if (msg.startsWith("token")) {

			String tokenColor = splitIt[1];
			controller.popUpTokenGenerated(tokenColor);

			/* send a message to the server that a token was finally generated after pop up */
			send("token was generated");
		}

		if(msg.equals("now you can distribute cards")) {
			controller.enableButtonForDistributeCard();
		}

		if(msg.equals("You have to pick a card first")) {
			ImageIcon pickfirst = new ImageIcon("./img/pick_first.png");
			controller.popUp(msg, "Pick a card first",pickfirst);
		}

		if (msg.startsWith("Hand of card")) {
			controller.splitCardsAndSetIt(msg);
			System.out.println(msg);
		}

		if(msg.startsWith("tournamentNumber1")) {

			String tournamentNumber = splitIt[1];
			controller.setTournamentNumber(tournamentNumber);
		}
		if (msg.equals("Wait for Ivanhoe holder to make a decision!")) {
			ImageIcon wait =  new ImageIcon("./img/wait_decision.png");
			controller.popUp("WAIT FOR IVANHOE HOLDER","WAIT FOR DECISION",wait);
		}

		if(msg.equals("Now everyone got a card set your self invisible")) {
			controller.disableButtonForDistributeCard();
			send("who starts the game now?");
		}

		if(msg.startsWith("player Names")) {
			if(numberPlayersSpecified == 3) {

				String pName1 = splitIt[2];
				String pName2 = splitIt[3];
				String pName3 = splitIt[4];

				controller.setvisibilityForPlayerName(pName1, pName2, pName3);
			}
			if(numberPlayersSpecified == 2) {

				String pName1 = splitIt[2];
				String pName2 = splitIt[3];

				controller.setvisibilityFor2PlayerName(pName1, pName2);
			}
			if(numberPlayersSpecified == 4) {

				String pName1 = splitIt[2];
				String pName2 = splitIt[3];
				String pName3 = splitIt[4];
				String pName4 = splitIt[5];

				controller.setvisibilityFor4PlayerName(pName1, pName2, pName3, pName4);
			}

			if(numberPlayersSpecified == 5) {

				String pName1 = splitIt[2];
				String pName2 = splitIt[3];
				String pName3 = splitIt[4];
				String pName4 = splitIt[5];
				String pName5 = splitIt[6];

				controller.setvisibilityFor5PlayerName(pName1, pName2, pName3, pName4, pName5);
			}
		}

		if(msg.equals("choose the tournament colour")) {
			controller.enableTournamentColourButton();
		}

		if(msg.startsWith("okay I will serve you a list of colours ")) {
			
			/* Split it here */
			splitIt = msg.split(" ");
			
			int tournamentNumber = Integer.parseInt(splitIt[9]);
			String prevColourOfTournament = splitIt[10];
			
			String[] splitIt1 = prevColourOfTournament.split(":");
			String tournamentColor1 = splitIt1[1];
			
			System.out.println("Prev tournament color: " + tournamentColor1 + " Tournament number: " + tournamentNumber);
			
			controller.popUpMenuToChooseTournamentColor(tournamentNumber, tournamentColor1);
		}

		if(msg.startsWith("tournament colour is")) {
			String tournamentColour = splitIt[3];

			controller.popUpForTournamentColour(tournamentColour);
		}
		
		if(msg.equals("Enable withdraw button for player")) {
			controller.enableWithDrawButtonForPlayer();
		}
		
		if(msg.equals("withdraw button disabled")) {
			controller.disableWithDrawButtonForPlayer();
		}
		if (msg.equals("Not your turn to play")) {
			ImageIcon notyourTurn = new ImageIcon("./img/not_your_turn.png");
			controller.popUp(msg,"NOT YOUR TURN",notyourTurn);
		}

		if(msg.startsWith("Left")) {
			controller.withdraw(splitIt[1]);
		}
		if (msg.startsWith("I left. Disable all my buttons")) {
			controller.disableAllButtons();
		}

		if(msg.startsWith("This player withdrew from the")) {

			String nameOfPlayerWhoWithdrew = splitIt[5];
			controller.disableStuffs(nameOfPlayerWhoWithdrew);
		}

		if(msg.equals("You cannot withdraw")) {
			controller.popUpNotYourTurn();
		}

		if (msg.equals("Wrong Colour")) {
			controller.popUpWrongColour();
		}

		if (msg.equals("Draw a card")) {
			controller.enablePickCard();
		}
		if (msg.equals("Draw a card player")) {
			controller.enablePickCard();
		}

		if (msg.contains("played Ivanhoe!")) {
			ImageIcon ivanhoe = new ImageIcon("./img/360_cards/action_card_ivanhoe.png");
			controller.popUp(splitIt[0] + " played Ivanhoe!", "Ivanhoe Played", ivanhoe);
		}
		
		if (msg.contains("Your new card is" )) {
			controller.addNewCard(splitIt[5], splitIt[0]);
			controller.popUpNewCard(splitIt[5]);
		}
		
		if (msg.startsWith("Your card is back" )) {
			controller.addNewCard(splitIt[4], splitIt[5]);
		}
		
		if (msg.equals("put last card played back")) {
			controller.LastCardBack();
		}
		
		if (msg.contains("Choose an opponent to discard all purple cards")) {
			controller.popUpChooseOpponent(msg, "Break lance");
		}
		
		if (msg.contains("Choose an opponent to get last played card")) {
			controller.popUpChooseOpponent(msg, "Riposte");
		}
		
		if (msg.startsWith("Choose your card to swap")) {
			ImageIcon swapCard = new ImageIcon("./img/card_swap.png");
			controller.popUp(msg,"Swap Your Card",swapCard);
			controller.outwitChooseCard(splitIt[5], "Played outwit selected my card ");
		}
		
		if (msg.contains("Choose your opponent's card to swap")) {
            ImageIcon outWit = new ImageIcon("./img/90_card/action_card_outwit.png");
			
			controller.popUp("Choose " + splitIt[6] + "'s card", "OutWit Card Played!", outWit);
			controller.outwitChooseCard(splitIt[6], "Played outwit selected opponent's card ");
		}
		
		if (msg.startsWith("You played adapt. Now tell everyone to remove cards")) {
			send("I played adapt");
		}
		
		if (msg.startsWith("You have to remove cards of the same value")) {
			ImageIcon removecard = new ImageIcon("./img/remove_card.png");
			controller.popUp("remove card","REMOVE CARD!!!",removecard);
			controller.addListenerScrolling(splitIt[9], "adapt");
		}
		if (msg.equals("You don't need to remove any cards.")) {
			ImageIcon dontremovecard = new ImageIcon("./img/dont_remove.png");
			controller.popUp("You dont need to remove","DONT REMOVE!!!",dontremovecard);
		}
		
		if (msg.contains("Choose an opponent to draw a random card ")) {
			controller.popUpChooseOpponent(msg, "Knock-Down");
		}
		if (msg.contains("Choose an opponent to swap your card with")) {
			controller.popUpChooseOpponent(msg, "Outwit");
			
		}
		
		if (msg.contains("Choose an opponent to get any played card ")) {
			controller.popUpChooseOpponentDodge(msg);
			
		}
		
		if (msg.startsWith("shield was played")) {
			send("Player " + splitIt[3] + " played shield" );
		//	controller.popUp("Player " + splitIt[3] + " played shield" );
			
		}
		if (msg.equals("You have Ivanhoe card. Do you want to play it? ")) {
			//show popUp
			controller.popUpWantIvanhoe();
		}
		
		if (msg.contains("Result of outwit is " )) {
			if (splitIt[4].equals("shield")) {
				controller.addShield(splitIt[5]);
				controller.removeShield(splitIt[6]);
			}
			else if (splitIt[4].equals("stunned")) {
				System.out.println("Name of a player is " + splitIt[5]);
				controller.addStunned(splitIt[5]);
				controller.removeStunned(splitIt[6]);
			}
		}
		if (msg.startsWith("add shield")) {
			controller.addShield(splitIt[2]);
		}
		if (msg.startsWith("add stunned")) {
			controller.addStunned(splitIt[2]);
		}
		if (msg.startsWith("remove shield")) {
			controller.removeShield(splitIt[2]);
		}
		if (msg.startsWith("remove stunned")) {
			controller.removeStunned(splitIt[2]);
		}
		
		if (msg.contains("played shield")) {
			controller.addShield(splitIt[1]);
			ImageIcon shield = new ImageIcon("./img/90_card/action_card_shield.png");
			controller.popUp(msg, "Shield Card was Played!", shield);
		}
		
		if (msg.contains("played stunned on ")) {
			controller.addStunned(splitIt[5]);
			ImageIcon stunned = new ImageIcon("./img/90_card/action_card_stunned.png");
			controller.popUp(msg, "Stunned Played", stunned);
		}
		
		if (msg.equals("outmaneuver")) {
			send(msg);
		}
		if (msg.equals("disgrace")||msg.equals("charge")||msg.equals("counter-charge")) {
			send(msg);
		}
		
		if (msg.contains("played action card")) {
			ImageIcon actionCardPlayed = new ImageIcon("./img/played_action.jpg");
			controller.popUp(msg, "Action Card Played!", actionCardPlayed);
		}
		
		if (msg.startsWith("Change tournament colour to red, blue or yellow")) {
			System.out.println("PopUp dialog should appear");
			controller.popUpMenuColoursCustom("red blue yellow", "Tournament colour", "Select new tournament colour!");
		}
		
		if (msg.equals("Change tournament colour to green.")) {
			send("tournament colour was changed green");
		}
		
		if (msg.startsWith("You are trying to play wrong card")) {
			ImageIcon cantplay = new ImageIcon("./img/you_cant_play.png");
			controller.popUp("YOU CANNOT PLAY THIS CARD", "Wrong card!!!",cantplay);
		}
		
		
		if (msg.startsWith("Winner of this game is")) {
			String winnerOfTheGame = splitIt[5];
			controller.showWinningScreen(winnerOfTheGame);
		}

		if(msg.equals("as a winner now you can distribute cards")) {
			controller.enableButtonForDistributeCard();
		}
		
		if(msg.equals("tournament finished new tournament starting")) {
			controller.newTournamentStarting();
		}
		
		if(msg.equals("Game is done! Sorry try again next time")) {
			controller.gameIsDone(msg);
		}

		if (msg.startsWith("Someone got a new card ")) {
			controller.AddCardToOtherDisplays(splitIt[5]);
		}

		if(msg.equals("This is a purple tournament choose your colour")) {
			String chosenColour = controller.chosenTokenColour();
			send("The winner of the purple tournament has chosen a color " + chosenColour);
		}

		if(msg.startsWith("Server is done adding token to my list of tokens")) {

			String playerTokenColour = splitIt[10];
			String playerWhoWon        = splitIt[11];

			controller.setTokenVisibleForPlayerWhoWon(playerWhoWon, playerTokenColour.toLowerCase());		
			send("Tournament done!");
		}

		if(msg.startsWith("make token visible to other players")) {
			String playerTokenColour = splitIt[6];
			String playerWhoWon        = splitIt[7];

			controller.setTokenVisibleForPlayerWhoWon(playerWhoWon, playerTokenColour.toLowerCase());	
		}

		if(msg.startsWith("players who withdrew we have to enable")) {
			controller.setVisibilityForWithDrawnPlayersToTrue(msg);
		}
		
		if (msg.startsWith("Choose an opponent to play stunned card on")) {
			controller.popUpChooseOpponent(msg, "Stunned");
		}
		
		if(msg.equals("Choose colour of tournament")) {
			controller.popUpMenuToChooseTournamentColorAfterTournament();
		}


		if (msg.equals("Enable hand of cards")) {
			controller.EnableHandForEveryone();
		}

		if (msg.startsWith("Give up a token")) {
			controller.popUpMenuToRemoveToken(splitIt[4], "You have to give up one of your tokens. Choose a token to give up" );

		}

		if (msg.startsWith("Remove a token")) {
			String TokenColorToRemove = splitIt[3];
			String playerName = splitIt[5];
			controller.removeTokenAllDisplays(playerName, TokenColorToRemove);
		}



		if(msg.startsWith("you are the winner of this tournament")) {
			String playerNameWhoWon = splitIt[7];

			controller.popUpToNotifyWinnerOfTheTournament(playerNameWhoWon);
		}


		if(msg.startsWith("you already have this token")) {
            String tokenColor = splitIt[5];
			
			controller.popUpToNotifyAlreadyHaveToken(tokenColor);
			send("Tournament done!");
		}

		if (msg.startsWith("UpdateDisplay" )) {
			String[] cards = Arrays.copyOfRange(splitIt, 2, splitIt.length);
			String[] newOne = {};
			for (String s : cards) {
				System.out.println("element one:" + s);
			}
			if (cards.length>0)
				controller.addCardsDisplay(cards, splitIt[1]);
			else 
				controller.addCardsDisplay(newOne, splitIt[1]);
			
		}

		if (msg.startsWith("Update score")){
			controller.updateScore(splitIt[2], splitIt[3]);
		}
		if (msg.startsWith("Disable")) {
			controller.disableCardPlayed(splitIt[1], splitIt[2]);
		}
		if (msg.contains("take the card from your display")) {
			ImageIcon retreatPlayed = new ImageIcon("./img/90_card/action_card_retreat.png");
			controller.popUp(msg, "Retreat Card Played!", retreatPlayed);
			controller.retreatPlayed(splitIt[0]);
		}
		if (msg.equals("Enable done")) {
			controller.enableDone();
		}
		if (msg.equals("Done should be disabled")) {
			controller.disableDone();
		}

		if (msg.equals("Your turn to play")) {
			ImageIcon yourTurn = new ImageIcon("./img/your_turn.png");
			controller.popUp("It's your turn to play","YOUR TURN!!!",yourTurn);
		}

		if (msg.startsWith("You have to withdraw")) {
			ImageIcon withdrawOrplay = new ImageIcon("./img/withdraw_play.png");
			controller.popUp("You have to withdraw","WITHDRAW OR PLAY!!!",withdrawOrplay);

		}
		
		if(msg.equals("distributes cards first")) {
			controller.enableButtonForDistributeCard();
		}
		
		if(msg.startsWith("first player got a new card now")) {

			controller.AddCardToOtherDisplays(splitIt[7]);
			send("I have picked a card now I want to choose a colour for the tournament");
		}
		
		if(msg.equals("Your turn was passed to somone else")) {
			controller.popUpToNotifyAPlayerHisTurnWasPassedToTheNextPlayer();
		}
		
		if(msg.equals("Pick the tournament colour to begin")) {
			controller.popUpToNotifyAPlayerToChooseATournamentColour();
		}
		
		if(msg.equals("You cannot play choose tournament colour")) {
			controller.notifyPlayerToChooseColourFirst();
		}

	}

	public void stop() {  
		try { 
			if (thread != null) thread = null;
			if (console != null) console.close();
			if (streamIn != null) streamIn.close();
			if (streamOut != null) streamOut.close();

			if (socket != null) socket.close();

			this.socket = null;
			this.console = null;
			this.streamIn = null;
			this.streamOut = null;    	  
		} catch(IOException ioe) {  
			System.out.println(ioe.getMessage());  
		}
		client.close();  
	}
}