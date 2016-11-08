package ruleEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import config.Config;
import entity.Player;

public class GameRuleEngine {
	
	public String playerWhoGotPurple        = "";
	public String tournamentColourBeforeChanged="";
	public String tournamentColour          = "";
	public String playOutwitOn				="";
	public String cardToGive				="";
	public String cardToTake				="";
	public String playedAdapt				="";
	public int 	  playersToRemoveAdapt		= 0;
	public int 	  playersWhoRemovedAdapt	= 0;
	public boolean	  adaptJustPlayed		= false; 
	public ArrayList<String>				  			deckOfColorAndSupporters 	= new ArrayList<String>(Arrays.asList("color_card_purple_3_1","color_card_purple_3_2", "color_card_purple_3_3","color_card_purple_3_4",
																														"color_card_purple_4_10", "color_card_purple_4_2", "color_card_purple_4_3", "color_card_purple_4_4", "color_card_purple_5_1", "color_card_purple_5_2", "color_card_purple_5_3", "color_card_purple_5_4", "color_card_purple_7_1", "color_card_purple_7_2", 	
																														"color_card_red_3_1", "color_card_red_3_2", "color_card_red_3_3", "color_card_red_3_4", "color_card_red_3_5", "color_card_red_3_6","color_card_red_4_1", "color_card_red_4_2", "color_card_red_4_3", "color_card_red_4_4", "color_card_red_4_5", "color_card_red_4_6", "color_card_red_5_1", "color_card_red_5_2",
																														"color_card_blue_2_1","color_card_blue_2_2","color_card_blue_2_3", "color_card_blue_2_4", "color_card_blue_3_1","color_card_blue_3_2", "color_card_blue_3_3", "color_card_blue_3_4", "color_card_blue_4_1","color_card_blue_4_2", "color_card_blue_4_3", "color_card_blue_4_4", "color_card_blue_5_1",  "color_card_blue_5_2", 
																														"color_card_yellow_2_1", "color_card_yellow_2_2", "color_card_yellow_2_3", "color_card_yellow_2_4", "color_card_yellow_3_1", "color_card_yellow_3_2", "color_card_yellow_3_3", "color_card_yellow_3_4", "color_card_yellow_3_5", "color_card_yellow_3_6", "color_card_yellow_3_7", "color_card_yellow_3_8", "color_card_yellow_4_1", "color_card_yellow_4_2",
																														"color_card_green_1_1","color_card_green_1_2","color_card_green_1_3","color_card_green_1_4","color_card_green_1_5","color_card_green_1_6","color_card_green_1_7","color_card_green_1_8","color_card_green_1_9","color_card_green_1_10", "color_card_green_1_11", "color_card_green_1_12", "color_card_green_1_13", "color_card_green_1_14", 
																														"supporter_card_2_1", "supporter_card_2_2", "supporter_card_2_3", "supporter_card_2_4", "supporter_card_2_5", "supporter_card_2_6", "supporter_card_2_7", "supporter_card_2_8",
																												     	"supporter_card_3_1", "supporter_card_3_2", "supporter_card_3_3", "supporter_card_3_4", "supporter_card_3_5", "supporter_card_3_6", "supporter_card_3_7", "supporter_card_3_8",
																														"supporter_card_6_1", "supporter_card_6_2","supporter_card_6_3", "supporter_card_6_4",
																														"action_card_unhorse", "action_card_change-weapon", "action_card_drop-weapon",  "action_card_break-lance", "action_card_riposte","action_card_riposte_2","action_card_riposte_3", "action_card_dodge", "action_card_retreat",
																														"action_card_outmaneuver", "action_card_knock-down","action_card_knock-down_2", "action_card_charge", "action_card_counter-charge", "action_card_disgrace", "action_card_adapt","action_card_outwit", "action_card_shield", "action_card_stunned",
																														"action_card_ivanhoe" )); 


	
	/*need those for ivanhoe action card */
	public String lastActionCardPlayed			= "";
	public ArrayList<Player>  cardPlayedPlayers	= new ArrayList<Player>();
	
	/* Game rule Engine constructor */
	public GameRuleEngine() {
			
	}
	
	/* Check if the player got back a purple token */
	public boolean didPlayerGetAPurpleToken(String tokenColor, String playerName) {
		boolean flag = false;
		
		if (tokenColor.equals("purple")) {
				flag = true;
				playerWhoGotPurple = playerName;
		}
		
		return flag;
	}
	
	public String getTournamentColour() {
		return tournamentColour;
	}
	
	/* set tournament color when game has started and user chooses color for that specific tournament */
	public void setTournamentColour(String tColour) {
		tournamentColour = tColour;
	}
	
	/* Give a token to a player 
	 * Takes the list of tokens and returns the random token as a string*/
	public String distributeTokens(List<String> tokens) {
		
		int random = (int) Math.floor(tokens.size() * Math.random());
		String playersToken = tokens.get(random);
		tokens.remove(random);
		return playersToken;	
	}
	
	public void resetAdapt() {
		playedAdapt = "";
		playersToRemoveAdapt = 0;
		playersWhoRemovedAdapt = 0;
	}
	
	/* Take in array of cards and distribute eight cards to each player */
	public ArrayList<String> distributeCardsToPlayers(String playerName, ArrayList<String> deckOfCards) {
		ArrayList<String> eightCardsEach = new ArrayList<String>();
		String getTheRandomString = "";
		
		/* Shuffle deck of cards first */
		int shuffleDeckOfCards;
		
		for(int i = 0; i < Config.numCardsToDistribute; i++) {
			shuffleDeckOfCards = (int) Math.floor(deckOfCards.size() * Math.random());

			getTheRandomString = deckOfCards.get(shuffleDeckOfCards);
			eightCardsEach.add(getTheRandomString);
			deckOfCards.remove(shuffleDeckOfCards);
		}
		
		return eightCardsEach;
	}
	
	/*Take one card from the deck and give it to a player 
	 * generate number at random, take card at random position 
	 * and put it in player's array of cards. Return player's array*/
	public String drawCard (Player p, ArrayList<String> deckOfCards) {
        int random = (int )(Math.random() * (deckOfCards.size()-1) + 0);
        boolean check = false;
        String newCard = deckOfCards.get(random);
        for (int i=0;i<p.handCards.size();i++) {
            if (p.handCards.get(i).equals("")) {
                p.handCards.set(i, newCard);
                check = true;
                deckOfCards.remove(random);
                break;
            }
        }
        
        if (check == false ) {
            p.handCards.add(deckOfCards.get(random));
            deckOfCards.remove(random);
        }
           return newCard;
        
    }
	
	
	public boolean isPreviousTournamentPurple(String previousTournamentColor) {
		
		boolean canIStartWithPurple = false;
		
		if(previousTournamentColor.equals("purple")) {
			
			canIStartWithPurple = false;
		}
		
		else {
			canIStartWithPurple = true;
		}
		
		return canIStartWithPurple;
	}
	

	public boolean playerHasOnlyActions (ArrayList<String> handOfCards) {
		
		if(tournamentColour.equalsIgnoreCase("purple")) {
			for (int i = 0; i < handOfCards.size(); i++) {
				
				String[] parts = handOfCards.get(i).split("_");
				
				if (!parts[0].equals("action") && !parts[2].equals("purple")) {
					return false;
				}
			}
		}
		
		else {
			for (int i = 0; i < handOfCards.size(); i++) {
				
				String[] parts = handOfCards.get(i).split("_");
				if (!parts[0].equals("action")) {
					return false;
				}
			}
		}
		
		return true;
	}

	/* Start a tournament by playing anti-clockwise from left of the dealer
	 * who deals the card to each player
	 */
	public String startTournamentFirst (ArrayList<Player> players) {
		int q = 0;
		String playerToStartsGame = "";
		
		for (int i = 0; i < players.size(); i++){
			if (didPlayerGetAPurpleToken(players.get(i).getTokenColour(), players.get(i).getPlayerName())) {
				if (i == players.size()-1) {
					playerToStartsGame = players.get(0).getPlayerName();
				}
				else  {
					q = i+1;
					playerToStartsGame = players.get(q).getPlayerName();
				}
			}
		}
		return playerToStartsGame;
	}
	
	//check that player has a valid color to start the tournament he wants to start
	public boolean colourIsValid(Player p, String chosenColour) {
		for (String s: p.handCards) {
			String[] splitIt = s.split("_");
			if ((splitIt[0].equals("color") && (splitIt[2].equals(chosenColour))) || splitIt[0].equals("supporter")) {
				return true;
			}
		}
		
		return false;
	}
	
	public void removeToken(Player p, String token) {
		for (int i =0; i<p.playerTokens.size();i++) {
			if (token.equalsIgnoreCase(p.playerTokens.get(i))) {
				p.playerTokens.remove(i);
			}
		}
	}
	
	public boolean hasToGiveUpToken(Player p, List<Player> players) {
		for(int b = 0; b < p.playerDisplay.size(); b++) {
			if(p.playerDisplay.get(b).startsWith("supporter_card_6") && p.myTurnToPlay) {
				if(p.playerTokens.size() >= 1) {
					return true;
			}
		  }
		}
		
		return false;
	}
	
	
	
	public boolean Withdraw(String name, List<Player> players, ArrayList<String> discardPile, ArrayList<String> token, ArrayList<Player> withdrawnPlayers) {
		boolean flag = false;	

		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getPlayerName().equals(name) && (players.get(i).myTurnToPlay == true)) {
				for(int a = 0; a < players.get(i).handCards.size(); a++) {
					if (!players.get(i).handCards.get(a).equals("")) {
						discardPile.add(players.get(i).handCards.get(a));
						}
				}
				
				players.get(i).myTurnToPlay = false;
				players.get(i).IgotANewCard = false;
				players.get(i).handCards.removeAll(players.get(i).handCards);
				discardPile.addAll(players.get(i).playerDisplay);
				players.get(i).playerDisplay.removeAll(players.get(i).playerDisplay);
				withdrawnPlayers.add(players.get(i));
				players.remove(i);
				
				flag = true;
			}
		}
		
		return flag;
	}
		
	/* takes list of players and color of tournament,  */
	/* checks if a player won a tournament, 
	 * gives him token of tournament color or lets him to choose token  */
	public void putBackCardsInDsicardPileIntoDeck(ArrayList<String> deckOfCards, ArrayList<String> discardPile) {
		for(String s: discardPile) {
			if (!s.equals(""))
				deckOfCards.add(s);
		}
		discardPile.removeAll(discardPile);
	}
	
	
	public String winTournament(ArrayList<Player> players, String colorOfTournament, String tokenChosen) {
		
		String  playerName = "";
		
		int sizeOfPlayerTokenArray = players.get(0).playerTokens.size();
		
		if(players.size() == 1) {
			if(sizeOfPlayerTokenArray == 0) {
				if(!colorOfTournament.equals("purple")) {
					players.get(0).playerTokens.add(colorOfTournament.toLowerCase());
					playerName = players.get(0).getPlayerName();
				}
				
				else if(colorOfTournament.equals("purple")){
					players.get(0).playerTokens.add(tokenChosen.toLowerCase());
					playerName = players.get(0).getPlayerName();
				}
			}
			
			else if(sizeOfPlayerTokenArray > 0) {
				if(!colorOfTournament.equals("purple")) {
					for(int i = 0; i < players.get(0).playerTokens.size(); i++) {
						if(players.get(0).playerTokens.contains(colorOfTournament)) {
							playerName = "You already have this token";
							break;
						}
						
						else if(!players.get(0).playerTokens.contains(colorOfTournament)){
							playerName = players.get(0).getPlayerName();
							players.get(0).playerTokens.add(colorOfTournament.toLowerCase());
							break;
						}
					}
				}
				
				else {
					for(int i = 0; i < players.get(0).playerTokens.size(); i++) {
						if(players.get(0).playerTokens.contains(tokenChosen.toLowerCase())) {
							playerName = "You already have this token";
							break;
						}
						
						else {
							players.get(0).playerTokens.add(tokenChosen.toLowerCase());
							playerName = players.get(0).getPlayerName();
							break;
						}
					}
				}
			}
		}
		return playerName;
	}
	
	
	public void resetMyCards(ArrayList<String> discardPile, Player player) {
		discardPile.addAll(player.handCards);
		discardPile.addAll(player.playerDisplay);
		if (player.hasShield){
			player.hasShield = false;
		}
		if (player.stunnedPlayedOnMe) {
			player.stunnedPlayedOnMe = false;
			player.oneCardPlayed = false;
		}
		player.holdKnockDownInd = -1;
		player.playerDisplay.removeAll(player.playerDisplay);
		player.handCards.removeAll(player.handCards);
	}
	
	
	public String winGame(List<Player> players, Integer playersatStart) {
		String winner = null;
		if (playersatStart <=3) {
			if (players.get(0).playerTokens.size() == 5) {
				winner = players.get(0).getPlayerName();
			}
		}
		else {
			if (players.get(0).playerTokens.size() == 4)  {
				winner = players.get(0).getPlayerName();
			}
		}
		return winner;
	}
	
	
	public boolean checkHighestValue(Player current, ArrayList<Player> players) {
		for (Player p: players) {
			if ((current.totalCardValue <= p.totalCardValue)) {
				if (!current.getPlayerName().equals(p.getPlayerName()))
					return false;
			}
		}
		return true;
		
	}
	
	public void resetStunned(Player p) {
		p.oneCardPlayed = false;
	}
	
	public boolean playColorOrSupporterCard(Player player, String card) {
		int score = 0;
		String[] splitIt = card.split("_");
		boolean flag1 = true;

		if (player.myTurnToPlay) {
			if ((!player.stunnedPlayedOnMe) || (player.stunnedPlayedOnMe && !player.oneCardPlayed)) {
				if (splitIt[0].equals("supporter"))  {
					if (splitIt[2].equals("6")) {
						for (int i = 0; i< player.playerDisplay.size();i++) {
							if((player.playerDisplay.get(i).startsWith("supporter_card_6"))) {
									return false;
							}
						}
					}

					if (flag1) {
						score = Integer.parseInt(splitIt[2]);
						player.playerDisplay.add(card);
						player.oneCardPlayed = true;
						for (int i=0;i<player.handCards.size();i++) {
							if (card.equals(player.handCards.get(i))) {
								player.handCards.set(i, "");
							}
						}
						if (!tournamentColour.equalsIgnoreCase("green")) {
							player.totalCardValue += score;
						}
						else 
							player.totalCardValue +=1;
						return true;
					}
				}

				else if (splitIt[0].equals("color") && tournamentColour.equalsIgnoreCase(splitIt[2])) {

					score = Integer.parseInt(splitIt[3]);
					player.playerDisplay.add(card);
					for (int i=0;i<player.handCards.size();i++) {
						if (card.equals(player.handCards.get(i))) {
							player.handCards.set(i, "");
						}
					}
					player.oneCardPlayed = true;
					player.totalCardValue += score;
					return true;
				}
			}
			}

		return false;
	}

	public boolean playingAction(String card, Player p) {
		String[] splitIt = card.split("_");
		if (splitIt[0].equals("action") && (p.myTurnToPlay)) {
			return true;
		}
		return false;
	}

	/* if player plays an action card, ask him what exactly he wants to do, 
	 * if no answer in required return an empty string. Otherwise, return a question. 
	 * Ivanhoe action card is not covered as it's a special case
	 */ 
	
	public String askPlayerActionCard(Player player, String card, ArrayList<Player> players) {
		String[] splitIt = card.split("_");
		String message = "";
	
		if (splitIt[2].equals("unhorse")) {
			if (tournamentColour.equals("purple")) {
				message = "Change tournament colour to red, blue or yellow. Pick the colour";
				lastActionCardPlayed = splitIt[2];
				tournamentColourBeforeChanged = tournamentColour;
			}
		}
		
		else if (splitIt[2].equals("change-weapon")) {
			if (tournamentColour.equals("red") || tournamentColour.equals("yellow") 
					|| tournamentColour.equals("blue")) {
				message = "Change tournament colour to red, blue or yellow. Pick the colour";
				lastActionCardPlayed = splitIt[2];
				tournamentColourBeforeChanged = tournamentColour;
			}

		}
		
		else if (splitIt[2].equals("drop-weapon")) {
			if (tournamentColour.equals("red") || tournamentColour.equals("yellow") 
					|| tournamentColour.equals("blue")) {
				message = "Change tournament colour to green.";
				for (Player p: players) {
					int newScore = 0;
					for (String s: p.playerDisplay) {
						String splitCard[] = s.split("_");
						if (splitCard[0].equals("color")||splitCard[0].equals("supporter")) {
							newScore++;
						}
					}
					p.totalCardValue = newScore;
				}
			}
			lastActionCardPlayed = splitIt[2];
			tournamentColourBeforeChanged = tournamentColour;
		}
		else if (splitIt[2].equals("break-lance")) {
			String allPlayersNames = "";
			for (Player p : players) {
				boolean check = false;
				if (!p.myTurnToPlay && !p.hasShield && p.playerDisplay.size()>1) {
					for (String s : p.playerDisplay) {
						if (s.contains("purple")) 
							check = true;
					}
					if (check) {
						allPlayersNames += p.getPlayerName();
						allPlayersNames += " ";
					}
				}
			}
			if (!allPlayersNames.equals(""))
				message = "Choose an opponent to discard all purple cards " + allPlayersNames;
		}
		
		else if (splitIt[2].equals("riposte")) {
			String allPlayersNames = "";
			for (Player p : players) {
				if (!p.myTurnToPlay && !p.hasShield && p.playerDisplay.size()>1) {
					if (!p.playerDisplay.isEmpty()) {
						allPlayersNames += p.getPlayerName();
						allPlayersNames += " ";
					}
				}
			}
			if (!allPlayersNames.equals(""))
				message = "Choose an opponent to get last played card " + allPlayersNames;
		}
		
		else if (splitIt[2].equals("dodge")) {
			String allPlayersNames = "";
			for (Player p : players) {
				if (!p.myTurnToPlay && !p.hasShield && p.playerDisplay.size()>1){
					if (!p.playerDisplay.isEmpty()) {
						allPlayersNames += p.getPlayerName();
						allPlayersNames += " ";
					}
				}
				if (!allPlayersNames.equals(""))
					message = "Choose an opponent to get any played card " + allPlayersNames;
			}
		}
		
		else if (splitIt[2].equals("retreat")) {
			if (player.playerDisplay.size()>1)
				message = player.getPlayerName() + " take the card from your display back into your hand";
		}
		
		else if (splitIt[2].equals("knock-down")) {
			String allPlayersNames = "";
			for (Player p : players) {
				if (!p.myTurnToPlay && !p.hasShield) {
						allPlayersNames += p.getPlayerName();
						allPlayersNames += " ";
					}
			}
			if (!allPlayersNames.equals(""))
				message = "Choose an opponent to draw a random card " + allPlayersNames;
		}
		
		else if (splitIt[2].equals("charge") ||
				splitIt[2].equals("counter-charge") || splitIt[2].equals("disgrace")
				)  {
			boolean check = false;
			if (splitIt[2].equals("charge") || splitIt[2].equals("counter-charge") ) {
				for (Player p: players) {
					if (p.playerDisplay.size()>0) {
						for (String s: p.playerDisplay) {
							if (s.contains("color"))
								check = true;
						}
					}
				}
			}
			
			else if (splitIt[2].equals("disgrace")) {
				for (Player p: players) {
					if (p.playerDisplay.size()>0) {
						for (String s: p.playerDisplay) {
							if (s.contains("supporter"))
								check = true;
						}
					}
				}
			}
			
			if (check)
				message = splitIt[2];
		}
		else if (splitIt[2].equals("outmaneuver")
				)  {
			boolean check = false;
			for (Player p: players) {
				if ((p.playerDisplay.size()>1) && !p.myTurnToPlay) {
					check = true;
				}
			}
			if (check)
				message = splitIt[2];
		}
		
	
		else if (splitIt[2].equals("outwit")) {
			String allPlayersNames = "";
			for (Player p : players) {
				if (!p.myTurnToPlay ) {
					if (!p.playerDisplay.isEmpty() || p.hasShield || p.stunnedPlayedOnMe) {
						allPlayersNames += p.getPlayerName();
						allPlayersNames += " ";
						}
				}
			}
			if (!allPlayersNames.equals(""))
				message = "Choose an opponent to swap your card with " + allPlayersNames;

		}
		
		else if (splitIt[2].equals("adapt")) {
			for (Player p : players) {
				if (!compareValue(p)) {
					message = "You played adapt. Now tell everyone to remove cards";
					playedAdapt = player.getPlayerName();
				}
			}
		}

		else if (splitIt[2].equals("shield")) {
			player.hasShield = true;
			message = "shield was played " + player.getPlayerName();
			lastActionCardPlayed = "shield";
		}
		
		else if (splitIt[2].equals("stunned")) {
			String allPlayersNames = "";
			for (Player p : players) {
				if (!p.myTurnToPlay && !p.hasShield) {
						allPlayersNames += p.getPlayerName();
						allPlayersNames += " ";
						}
			}
			if (!allPlayersNames.equals(""))
				message = "Choose an opponent to play stunned card on " + allPlayersNames;
			
		}
		
		
		return message;
	}
	
	public boolean compareValue(Player p) {
		boolean differentCards = true;
		for (String s : p.playerDisplay) {
			for (String m: p.playerDisplay) {
				if (s.contains("color") && m.contains("color") && (!s.equals(m))) {
					String one[] = s.split("_");
					String two [] = m.split("_");
					if (one[3].equals(two[3])) {
						differentCards = false;
					}
				}
			}
		}
		return differentCards;
	}
	
	public String adaptCardToDiscard(Player p) {
		String discardCards = "";
		for (String s : p.playerDisplay) {
			for (String m: p.playerDisplay) {
				if (s.contains("color") && m.contains("color") && (!s.equals(m))) {
					String one[] = s.split("_");
					String two [] = m.split("_");
					if (one[3].equals(two[3])) {
						discardCards += s;
						discardCards += m;
						discardCards += " ";
					}
				}
			}
		}
			return discardCards;
	}
	
	public void actionCardPlayedRemove(Player p, String card, List<String> discardPile) {
		discardPile.add(card);
		for (int i=0;i<p.handCards.size();i++) {
			if (card.equals(p.handCards.get(i))) {
				p.handCards.set(i, "");
			}
		}
		
	}
	
	public void addCardUpdateScore(Player p, String card) {
		String splitCard[] = card.split("_");
		int plus = 0;
		if (tournamentColour.equalsIgnoreCase("green")) {
			plus = 1;
		}
		else if (splitCard[0].equals("color"))	 {
			plus = Integer.parseInt(splitCard[3]);
			
		}
		else {
			plus = Integer.parseInt(splitCard[2]);
		}
		p.playerDisplay.add(card);
		p.totalCardValue = p.totalCardValue +  plus;
	}
	
	public void removeCardUpdateScore(Player p, String card) {
		String splitCard[] = card.split("_");
		int minus = 0;
			if (tournamentColour.equalsIgnoreCase("green")) {
			minus = 1;
		}
		else if (splitCard[0].equals("color"))	 {
			minus = Integer.parseInt(splitCard[3]);
			
		}
		else {
			minus = Integer.parseInt(splitCard[2]);
		}
		p.playerDisplay.remove(card);
		p.totalCardValue = p.totalCardValue -  minus;
	}
	
	public void cancelActionEffect() {
		
	}
	/*play an action card accordingly to the player's message
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void playActionCard(Player player, ArrayList<Player> players, String card, String message, List<String> discardPile) {
		String[] splitIt = card.split("_");
		String cardName = splitIt[2];
		Player keepPlayer = new Player();
		
		ArrayList<String> newDisplay = new ArrayList<String>();
//		
//		for (int i=0;i<player.handCards.size();i++) {
//			if (player.handCards.get(i).equals(card))
//				player.handCards.set(i, "");
//				discardPile.add(card);
//		}
		if (cardName.equals("adapt") && !adaptJustPlayed) {
			cardPlayedPlayers = new ArrayList<Player>();
			for (Player p: players) {
				Player addOne = new Player();
				cardPlayedPlayers.add(addOne.clone(p));
			}
			adaptJustPlayed = true;
		}
		
		else  if (!cardName.equals("adapt")){
			cardPlayedPlayers = new ArrayList<Player>();
			for (Player p: players) {
				Player addOne = new Player();
				cardPlayedPlayers.add(addOne.clone(p));
			}
		}

		lastActionCardPlayed = cardName;
		if (cardName.equals("break-lance")) {
			String lastPurple = ""; 
			/*find the matching player and remove all purple cards from his display
			 */
			for (int i = 0; i < players.size(); i++) {	
				if (players.get(i).getPlayerName().equals(message)) {
					keepPlayer = players.get(i);
					break;
				}
			}
			
			for (int j = 0; j <  keepPlayer.playerDisplay.size(); j++) {
				if (!keepPlayer.playerDisplay.get(j).contains("purple")) {
					newDisplay.add(keepPlayer.playerDisplay.get(j));
				}
				else {
					lastPurple = keepPlayer.playerDisplay.get(j);
					int minus = 0;
					String splitCard[] = keepPlayer.playerDisplay.get(j).split("_");
					discardPile.add(keepPlayer.playerDisplay.get(j));
					if (!tournamentColour.equalsIgnoreCase("green"))
						minus = Integer.parseInt(splitCard[3]);
					else 
						minus = 1;
					
					keepPlayer.totalCardValue = keepPlayer.totalCardValue- minus; 
				}
			}
			keepPlayer.playerDisplay = newDisplay;
			if (newDisplay.isEmpty() && !lastPurple.equals("")) {
				addCardUpdateScore(keepPlayer, lastPurple);
				}
		}
		
		else if (cardName.equals("riposte")) {
			for (int i = 0; i< players.size(); i++) {
				if (players.get(i).getPlayerName().equals(message)) {
					String lastCardOnDisplay = players.get(i).playerDisplay.get((players.get(i).playerDisplay.size())-1);
					player.playerDisplay.add(lastCardOnDisplay);
					String[] splitCard = lastCardOnDisplay.split("_");
					if (tournamentColour.equalsIgnoreCase("green")) {
						player.totalCardValue +=1;
					}
					else {
						if (splitCard[0].equals("color"))	 {
							player.totalCardValue += Integer.parseInt(splitCard[3]);
						}
						else {
							player.totalCardValue += Integer.parseInt(splitCard[2]);
						}
					}
					removeCardUpdateScore(players.get(i), lastCardOnDisplay);
				}
			}
		}

		else if (cardName.equals("dodge")) {
			String[] splitName = message.split(" ");
			for (Player p: players) {
				if (p.getPlayerName().equals(splitName[0])) {
					removeCardUpdateScore(p, p.playerDisplay.get(Integer.parseInt(splitName[1])));
					
				}
			}
			
		}
		
		else if (cardName.equals("retreat")) {
			boolean check = false;
				for (int i=0;i<player.handCards.size();i++) {
					if (player.handCards.get(i).equals("")) {
						player.handCards.set(i, message);
						check = true;
					}		
				}
				if (check == false )
					player.handCards.add(message);
				removeCardUpdateScore(player, message);
			}
		
		else if (cardName.equals("knock-down")) {
			int idx = -10;
			for (Player pl:players) {
				String randomCard = "";
				if (pl.getPlayerName().equals(message)) {
					boolean check = false;
					while(true) {
						idx = new Random().nextInt(pl.handCards.size());
						if (!pl.handCards.get(idx).equals("")) {
							randomCard = pl.handCards.get(idx);
							break;
						}
					}
					pl.holdKnockDownInd = idx;
					for (int i=0;i<player.handCards.size();i++) {
						if (player.handCards.get(i).equals("")) {
							player.handCards.set(i, randomCard);
							check = true;
						}		
					}
					if (check == false )
						player.handCards.add(randomCard);
				}
			}
		}
		
		else if (cardName.equals("outmaneuver")) {
			for (int i = 0; i< players.size(); i++) {
				if (!players.get(i).myTurnToPlay && !players.get(i).hasShield) {
					if (players.get(i).playerDisplay.size()>1) {
						String lastCardOnDisplay = players.get(i).playerDisplay.get((players.get(i).playerDisplay.size())-1);
						discardPile.add(lastCardOnDisplay);
						removeCardUpdateScore(players.get(i), lastCardOnDisplay);
					}
				}
			}
		}

		else if (cardName.equals("charge")) {
			String keepLastCard = "";
			int min = 100;
			for (int i = 0; i< players.size(); i++) {
					for (int j =0;j<players.get(i).playerDisplay.size();j++) {
						String cardsIn[] = players.get(i).playerDisplay.get(j).split("_");
						if (cardsIn[0].equals("color"))  {
							if (Integer.parseInt(cardsIn[3]) <= min)
								min = Integer.parseInt(cardsIn[3]);

					}
				}
			}
			
			for (Player pl :players) {
				ArrayList<String> newPlayersDisplay = new ArrayList<String>();
				if (!pl.hasShield) {
					for (String s: pl.playerDisplay) {
						String cardsIn[] = s.split("_");
						if (cardsIn[0].equals("color")) {
							if (Integer.parseInt(cardsIn[3]) == min) {
								keepLastCard  = s;
								discardPile.add(s);
								pl.totalCardValue -= min;
							}
							else 
								newPlayersDisplay.add(s);
						}
						else 
							newPlayersDisplay.add(s);
					}
					pl.playerDisplay = newPlayersDisplay;
					if (pl.playerDisplay.isEmpty() && !keepLastCard.equals(""))  {
						addCardUpdateScore(pl, keepLastCard);
					}
				}
			}
		}
		
		else if (cardName.equals("counter-charge")) {
			int max = 0;
			String keepLastCard = "";
			for (int i = 0; i< players.size(); i++) {
				for (int j =0;j<players.get(i).playerDisplay.size();j++) {
					String cardsIn[] = players.get(i).playerDisplay.get(j).split("_");
					if (cardsIn[0].equals("color"))  {
						if (Integer.parseInt(cardsIn[3]) >= max)
							max = Integer.parseInt(cardsIn[3]);
					}

				}
			}

			for (Player pl :players) {
				ArrayList<String> newPlayersDisplay = new ArrayList<String>();
				if (!pl.hasShield) {
					for (String s: pl.playerDisplay) {
						String cardsIn[] = s.split("_");
						if (cardsIn[0].equals("color")) {
							if (Integer.parseInt(cardsIn[3]) == max) {
								keepLastCard = s;
								discardPile.add(s);
								pl.totalCardValue -= max;
							}
							else 
								newPlayersDisplay.add(s);
						}
						else 
							newPlayersDisplay.add(s);
					}
					pl.playerDisplay = newPlayersDisplay;
					if (pl.playerDisplay.isEmpty() && !keepLastCard.equals("")) 
						addCardUpdateScore(pl, keepLastCard);
				}
			}
		}

		else if (cardName.equals("disgrace")) {
			String keepLastCard = "";
			for (Player pl: players) {
				if (!pl.hasShield) {
					ArrayList<String> newplayerDisplay = new ArrayList<String>();
					for (int j=0;j<pl.playerDisplay.size();j++) {
						String cardsIn[] = pl.playerDisplay.get(j).split("_");
						if (cardsIn[0].equals("supporter")) {
							keepLastCard = pl.playerDisplay.get(j);
							discardPile.add(pl.playerDisplay.get(j));
							if (!tournamentColour.equalsIgnoreCase("green"))
								pl.totalCardValue -= Integer.parseInt(cardsIn[2]);
							else 
								pl.totalCardValue --;
						}
						else 
							newplayerDisplay.add(pl.playerDisplay.get(j));
					}
					pl.playerDisplay = newplayerDisplay;
					if (pl.playerDisplay.isEmpty() && !keepLastCard.equals("")) 
						addCardUpdateScore(pl, keepLastCard);
				}
			}
		}	
		
		else if (cardName.equals("stunned")) {
			for (Player pl: players) {
				if (pl.getPlayerName().equals(message)) {
					pl.stunnedPlayedOnMe = true;
				}
			}
		}
		
		else if (cardName.equals("outwit")) {	
			Player outwitPlayed = new Player();
			for (Player p: players) {
				if (p.getPlayerName().equals(playOutwitOn)) {
					outwitPlayed = p;
				}
			}
			if (!cardToTake.equals("stunned") && !cardToTake.equals("shield")) {
				addCardUpdateScore(player, cardToTake);
				removeCardUpdateScore(outwitPlayed, cardToTake);
			}
			
			else if (cardToTake.equals("stunned")) {
				player.stunnedPlayedOnMe = true;
				outwitPlayed.stunnedPlayedOnMe = false;
				outwitPlayed.oneCardPlayed = false;
			}
			
			else if (cardToTake.equals("shield")) {
				player.hasShield = true;
				outwitPlayed.hasShield = false;
			}
			
			if (!cardToGive.equals("stunned") && !cardToGive.equals("shield")) {
				addCardUpdateScore(outwitPlayed, cardToGive);
				removeCardUpdateScore(player, cardToGive);
			}
			
			else if (cardToGive.equals("stunned")) {
				player.stunnedPlayedOnMe = false;
				player.oneCardPlayed = false;
				outwitPlayed.stunnedPlayedOnMe = true;
			}
			
			else if (cardToGive.equals("shield")) {
				player.hasShield = false;
				outwitPlayed.hasShield = true;
			}
		}
		
		else if (cardName.equals("adapt")) {
			//check if player played the right card
			boolean goodCard = false;
			if (adaptCardToDiscard(player).contains(message)) {
					goodCard = true;
				}
			
			if (goodCard) {
				removeCardUpdateScore(player, message);
			}
		}
	}
	
	public String ivanhoePlayed(Player p, ArrayList<Player> players) {
		String message = "";
		
		if (lastActionCardPlayed.equals("unhorse") || lastActionCardPlayed.equals("drop-weapon") || lastActionCardPlayed.equals("change-weapon")) {
			tournamentColour = tournamentColourBeforeChanged;
			message += "tournament colour is " + tournamentColour;
		}
		else if (lastActionCardPlayed.equals("break-lance") ||  lastActionCardPlayed.equals("riposte") || lastActionCardPlayed.equals("dodge") || lastActionCardPlayed.equals("outmaneuver") ||
				lastActionCardPlayed.equals("charge") || lastActionCardPlayed.equals("counter-charge") || lastActionCardPlayed.equals("disgrace") 
				|| lastActionCardPlayed.equals("adapt") || lastActionCardPlayed.equals("outwit")) {
			
				players.removeAll(players);
				players.addAll(cardPlayedPlayers);
				
				
				}
		
		else if (lastActionCardPlayed.equals("shield")) {
			for (Player pl: players) {
				if (pl.hasShield) {
					pl.hasShield = false;			
				}
			}
		}
		
		else if (lastActionCardPlayed.equals("stunned")) {
			for (Player pl:players) {
				if (pl.stunnedPlayedOnMe) {
					pl.stunnedPlayedOnMe = false;
				}
			}
		}
		
		else if (lastActionCardPlayed.equals("retreat")) {
			message+="put last card played back";
			players.removeAll(players);
			players.addAll(cardPlayedPlayers);
		}
		
		for (Player pl: players) {
            for (int i=0;i<pl.handCards.size();i++) {
                if (pl.handCards.get(i).contains("ivanhoe")) {
                    pl.handCards.set(i, "");
                }
            }
            
        }

		return message;
	}

	public boolean checkIvanhoe (Player p) {
		for (String s: p.handCards) {
			if (s.contains("ivanhoe")) {
				return true;
			}
		}
		return false;
	}

	public String passTurnToNextPlayerIfCurrentPlayerHasOnlyActionCards(List<Player> players, String playerToStartTheTournament) {

		int nextPlayerToStart 				  = 0;
		String nextPlayerToStartTheTournament = "";

		for(int i = 0; i < players.size(); i++) {
			if(playerToStartTheTournament.equalsIgnoreCase(players.get(i).getPlayerName())) {
				nextPlayerToStart = i + 1;
				break;
			}
		}

		if(nextPlayerToStart != players.size()) {
			nextPlayerToStartTheTournament = players.get(nextPlayerToStart).getPlayerName();
		}

		else {
			nextPlayerToStartTheTournament = players.get(0).getPlayerName();
		}

		return nextPlayerToStartTheTournament;
	}
	public void resetOutwit () {
		playOutwitOn ="";
		cardToGive	="";
		cardToTake	="";
	}
	public boolean EndOfTurn(Player player, List<Player> players) {
		boolean flag = true;
		
		for (int i =0; i< players.size();i++) {
			if (player.totalCardValue < players.get(i).totalCardValue)
				flag = false;
		}
		return flag;
	}
	
	public boolean checkDeckOfCardsEmpty(List<String> deckOfCards) {
		boolean flag = false;

		if(deckOfCards.isEmpty()) {
			flag = true;
		}	
		else {
			flag = false;
		}	
		return flag;
	}
	
			
	@SuppressWarnings("unchecked")
	public static ArrayList<String> reShuffleDiscardPileWhenDrawDeckIsEmpty(List<String> deckOfCards, ArrayList<String> discardPile) {

		ArrayList<String> newDeckOfCards = new ArrayList<String>();

		// Shuffle the discard pile cards
		Collections.shuffle(discardPile);

		// Copy what is in the discard pile to the new deck of card
		newDeckOfCards = (ArrayList<String>) discardPile.clone();

		// now empty discard pile since we copied everything to the new deck of card
		discardPile.removeAll(discardPile);

		return newDeckOfCards;
	}
	
}
