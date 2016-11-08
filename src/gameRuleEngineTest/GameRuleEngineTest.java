package gameRuleEngineTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import config.Config;
import entity.Player;
import ruleEngine.GameRuleEngine;

public class GameRuleEngineTest {
	
	public String playerWhoGotPurple        = "";
	public String tournamentColour   = "";
	public GameRuleEngine ruleEngine;
	
    private ArrayList<String>				  			deckOfAllCards 	= new ArrayList<String>(Arrays.asList("color_card_purple_3_1","color_card_purple_3_2", "color_card_purple_3_3","color_card_purple_3_4",
			"color_card_purple_4_1", "color_card_purple_4_2", "color_card_purple_4_3", "color_card_purple_4_4", "color_card_purple_5_1", "color_card_purple_5_2", "color_card_purple_5_3", "color_card_purple_5_4", "color_card_purple_7_1", "color_card_purple_7_2", 	
			"color_card_red_3_1", "color_card_red_3_2", "color_card_red_3_3", "color_card_red_3_4", "color_card_red_3_5", "color_card_red_3_6","color_card_red_4_1", "color_card_red_4_2", "color_card_red_4_3", "color_card_red_4_4", "color_card_red_4_5", "color_card_red_4_6", "color_card_red_5_1", "color_card_red_5_2",
			"color_card_blue_2_1","color_card_blue_2_2","color_card_blue_2_3", "color_card_blue_2_4", "color_card_blue_3_1","color_card_blue_3_2", "color_card_blue_3_3", "color_card_blue_3_4", "color_card_blue_4_1","color_card_blue_4_2", "color_card_blue_4_3", "color_card_blue_4_4", "color_card_blue_5_1",  "color_card_blue_5_2", 
			"color_card_yellow_2_1", "color_card_yellow_2_2", "color_card_yellow_2_3", "color_card_yellow_2_4", "color_card_yellow_3_1", "color_card_yellow_3_2", "color_card_yellow_3_3", "color_card_yellow_3_4", "color_card_yellow_3_5", "color_card_yellow_3_6", "color_card_yellow_3_7", "color_card_yellow_3_8", "color_card_yellow_4_1", "color_card_yellow_4_2",
			"color_card_green_1_1","color_card_green_1_2","color_card_green_1_3","color_card_green_1_4","color_card_green_1_5","color_card_green_1_6","color_card_green_1_7","color_card_green_1_8","color_card_green_1_9","color_card_green_1_10", "color_card_green_1_11", "color_card_green_1_12", "color_card_green_1_13", "color_card_green_1_14", 
			"supporter_card_2_1", "supporter_card_2_2", "supporter_card_2_3", "supporter_card_2_4", "supporter_card_2_5", "supporter_card_2_6", "supporter_card_2_7", "supporter_card_2_8",
			"supporter_card_3_1", "supporter_card_3_2", "supporter_card_3_3", "supporter_card_3_4", "supporter_card_3_5", "supporter_card_3_6", "supporter_card_3_7", "supporter_card_3_8",
			"supporter_card_6_1", "supporter_card_6_2","supporter_card_6_3", "supporter_card_6_4",
			"action_card_unhorse", "action_card_change-weapon", "action_card_drop-weapon",  "action_card_break-lance", "action_card_riposte","action_card_riposte_2","action_card_riposte_3", "action_card_dodge", "action_card_retreat",
			"action_card_outmaneuver", "action_card_knock-down","action_card_knock-down_2", "action_card_charge", "action_card_counter-charge", "action_card_disgrace", "action_card_adapt","action_card_outwit", "action_card_shield", "action_card_stunned", "action_card_ivanhoe" ));

	
	 ArrayList<String>				  			deckOfColorAndSupporters = new ArrayList<String>(Arrays.asList("color_card_purple_3_1","color_card_purple_3_2", "color_card_purple_3_3","color_card_purple_3_4",
			"color_card_purple_4_1", "color_card_purple_4_2", "color_card_purple_4_3", "color_card_purple_4_4", "color_card_purple_5_1", "color_card_purple_5_2", "color_card_purple_5_3", "color_card_purple_5_4", "color_card_purple_7_1", "color_card_purple_7_2", 
			"color_card_red_3_1", "color_card_red_3_2", "color_card_red_3_3", "color_card_red_3_4", "color_card_red_3_5", "color_card_red_3_6","color_card_red_4_1", "color_card_red_4_2", "color_card_red_4_3", "color_card_red_4_4", "color_card_red_4_5", "color_card_red_4_6", "color_card_red_5_1", "color_card_red_5_2",
			"color_card_blue_2_1","color_card_blue_2_2","color_card_blue_2_3", "color_card_blue_2_4", "color_card_blue_3_1","color_card_blue_3_2", "color_card_blue_3_3", "color_card_blue_3_4", "color_card_blue_4_1","color_card_blue_4_2", "color_card_blue_4_3", "color_card_blue_4_4", "color_card_blue_5_1",  "color_card_blue_5_2", 
			"color_card_yellow_2_1", "color_card_yellow_2_2", "color_card_yellow_2_3", "color_card_yellow_2_4", "color_card_yellow_3_1", "color_card_yellow_3_2", "color_card_yellow_3_3", "color_card_yellow_3_4", "color_card_yellow_3_5", "color_card_yellow_3_6", "color_card_yellow_3_7", "color_card_yellow_3_8", "color_card_yellow_4_1", "color_card_yellow_4_2",
			"color_card_green_1_1","color_card_green_1_2","color_card_green_1_3","color_card_green_1_4","color_card_green_1_5","color_card_green_1_6","color_card_green_1_7","color_card_green_1_8","color_card_green_1_9","color_card_green_1_10", "color_card_green_1_11", "color_card_green_1_12", "color_card_green_1_13", "color_card_green_1_14", 
			"supporter_card_2_1", "supporter_card_2_2", "supporter_card_2_3", "supporter_card_2_4", "supporter_card_2_5", "supporter_card_2_6", "supporter_card_2_7", "supporter_card_2_8",
			"supporter_card_3_1", "supporter_card_3_2", "supporter_card_3_3", "supporter_card_3_4", "supporter_card_3_5", "supporter_card_3_6", "supporter_card_3_7", "supporter_card_3_8",
			"supporter_card_6_1", "supporter_card_6_2","supporter_card_6_3", "supporter_card_6_4"));
	
	 
	    ArrayList<String> hand 			= new ArrayList<String>();
		ArrayList<String> tokens 		= new ArrayList<String>();
		GameRuleEngine engine 			= new GameRuleEngine();
		ArrayList<String> discardPile 	= new ArrayList<String>(); 
		ArrayList<Player> players 		= new ArrayList<Player>(5);
		ArrayList<Player> withdrawn		= new ArrayList<Player>();
		
		Player first  					= new Player("Aziza");
		Player second 					= new Player("Kevin");
		Player third 					= new Player("Christi");
		Player forth 					= new Player("Tope");
		Player five 					= new Player("Mike");
	
	
	 @BeforeClass
		public static void setUpBeforeClass() throws Exception {
			System.out.println("@setUpBeforeClass(): TestRuleEngine\n\n");
		}

		@AfterClass
		public static void tearDownAfterClass() throws Exception {
			System.out.println("@After(): RuleEngineTest\n\n");
		}

		@Before
		public void setUp() throws Exception {
			
			ruleEngine = new GameRuleEngine();
			
			int numberOfCard = 0;
			
			
			tokens.add("red");
			tokens.add("blue");
			tokens.add("purple");
			tokens.add("green");
			tokens.add("yellow");
			
			players.add(first);
			players.add(second);
			players.add(third);
			players.add(forth);
			players.add(five);
		}

		@After
		public void tearDown() throws Exception {
			System.out.println("@tearDown(): RuleEngineTest\n\n");
		}
		
	
		
		@Test
		public void testPlayingAction() {
			String card = "action_card_adapt";
			String card2 = "action_card_break_lance";
			String card3  = "color_card_blue_2_4";
			String card4  = "color_card_blue_5_2";
			
			assertFalse(ruleEngine.playingAction(card, first));
			first.myTurnToPlay = true;
			assertTrue(ruleEngine.playingAction(card, first));
			assertFalse(ruleEngine.playingAction(card3, first));
			assertFalse(ruleEngine.playingAction(card4, first));
		}
		
		@Test
		public void testDrawingCard() {
			boolean check = true;
		   String newCard = engine.drawCard(first, deckOfColorAndSupporters);
			for (String s: deckOfColorAndSupporters) {
				if (s.equals(newCard)) {
					check = false;
				}
			}
			
			assertEquals(first.handCards.get(0), newCard);
			assertTrue(check);
			
			String anotherCard = engine.drawCard(second, deckOfColorAndSupporters);
			assertEquals(second.handCards.get(0), anotherCard);
			assertNotEquals(second.handCards.get(0), first.handCards.get(0));
			
			for (String s: deckOfColorAndSupporters) {
				if (s.equals(anotherCard)) {
					check = false;
				}
			}
			assertTrue(check);
		 }
		
		@Test
		public void testplayerToStartTournament() {
			first.tokenColour = "yellow";
			second.tokenColour = "blue";
			third.tokenColour = "red";
			forth.tokenColour = "green";
			five.tokenColour = "purple";

			String playerToStart = engine.startTournamentFirst(players);
			assertEquals(first.getPlayerName(), playerToStart);
			
			first.tokenColour = "purple";
			second.tokenColour = "blue";
			third.tokenColour = "red";
			forth.tokenColour = "green";
			five.tokenColour = "yellow";
			assertEquals(second.getPlayerName(), engine.startTournamentFirst(players));

			first.tokenColour = "red";
			second.tokenColour = "blue";
			third.tokenColour = "purple";
			forth.tokenColour = "green";
			five.tokenColour = "yellow";
			assertEquals(forth.getPlayerName(), engine.startTournamentFirst(players));

			
		}
		@Test
		public void testCantGetTokenIfPlayerAlreadyHasThatToken() {
			/* Assuming I make player 1 get a purple token so he can start the game */
			first.tokenColour = "green";
			second.tokenColour = "red";
			third.tokenColour = "purple";
			forth.tokenColour = "yellow";
			five.tokenColour = "blue";
			
			
			/* I set the rule engine to know player who got a purple */
			ruleEngine.playerWhoGotPurple = third.getPlayerName();
			
			/* Now player who got a purple has to distribute the cards to all other players */
			if(ruleEngine.didPlayerGetAPurpleToken(third.getTokenColour(), third.getPlayerName())) {
				/* Distribute cards to all other players */
				for(int i = 0; i < players.size(); i++) {
					players.get(i).handCards = ruleEngine.distributeCardsToPlayers(players.get(i).getPlayerName(), deckOfColorAndSupporters);
				}
			}
			
			/* now each players hand should contain 8 cards */
			assertEquals(first.handCards.size(), 8);
			assertEquals(second.handCards.size(), 8);
			assertEquals(third.handCards.size(), 8);
			assertEquals(forth.handCards.size(), 8);
			assertEquals(five.handCards.size(), 8);
			
			/* Get the person seated to the left of the person who distributed a card technically who got a purple token 
			 * which should be the second player technically using our algorithm */
		
			String playerWhoStartsTheGame = ruleEngine.startTournamentFirst(players);
			
			assertEquals(playerWhoStartsTheGame, forth.getPlayerName());
			
			/* It's the person seated to the left of the player who distributed cards turn which is basically
			 * the second player
			 */
			forth.myTurnToPlay = true;
			
			/* The person seated to the left of the player that distributed the cards
			 * starts the game by picking a card and choosing a tournament color */
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i).getPlayerName().equals(playerWhoStartsTheGame)) {
					
					/* He picks a card first */
					ruleEngine.drawCard(players.get(i), deckOfColorAndSupporters);
					
					/* Then he now chooses a tournament color */
					ruleEngine.setTournamentColour("purple");
					break;
				}
			}
			
			/* Now the first players hand of card should be a total of 9 */
			assertEquals(forth.handCards.size(), 9);
			
			/* Also just to make sure the tournament color is actually red I check to see that is true */
			assertEquals(ruleEngine.getTournamentColour(), "purple");
			
			/* Now that he picked a card and chose a tournament color he plays some cards  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(1));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(3));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(2));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(0));
			
			/* I remove this card from his hand now he has played */
			forth.handCards.remove(forth.handCards.get(1));
			forth.handCards.remove(forth.handCards.get(3));
			forth.handCards.remove(forth.handCards.get(2));
			forth.handCards.remove(forth.handCards.get(0));
			
			
			/* Therefore there should be a total of 5 cards on his hand now */
			assertEquals(forth.handCards.size(), 5);
			
			/* set his turn to false now since he played a card already */
			forth.myTurnToPlay = false;
			
			
			/* I make fifth players turn to be next */
			five.myTurnToPlay = true;
			
			/* now he picks a card first */
			ruleEngine.drawCard(five, deckOfColorAndSupporters);
			
			/* his hand of card should be total of 9 after picking a card */
			assertEquals(five.handCards.size(), 9);
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(five, five.handCards.get(2));
			ruleEngine.playColorOrSupporterCard(five, five.handCards.get(4));
			ruleEngine.playColorOrSupporterCard(five, five.handCards.get(3));
			
			/* reduce hand of card by one after he's done playing */
			five.handCards.remove(five.handCards.get(2));
			five.handCards.remove(five.handCards.get(4));
			five.handCards.remove(five.handCards.get(3));
			
			/* his total hand of card now should be 3 in number */
			assertEquals(five.handCards.size(), 6);
			
			five.myTurnToPlay = false;
			
			
			/* I make third players turn to be next */
			third.myTurnToPlay = true;
			
			/* now he picks a card first */
			ruleEngine.drawCard(third, deckOfColorAndSupporters);
			
			/* his hand of card should be total of 9 after picking a card */
			assertEquals(third.handCards.size(), 9);
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(1));
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(4));
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(5));
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(0));
			
			/* reduce hand of card by one after he's done playing */
			third.handCards.remove(third.handCards.get(1));
			third.handCards.remove(third.handCards.get(4));
			third.handCards.remove(third.handCards.get(5));
			third.handCards.remove(third.handCards.get(0));
			
			/* his total hand of card now should be 5 in number */
			assertEquals(forth.handCards.size(), 5);
			
			third.myTurnToPlay = false;
			System.out.println(players.size());
			
			
			/* Assuming the second player withdraws now that its his turn again */
			second.myTurnToPlay = true;
			
			assertTrue(ruleEngine.Withdraw(second.getPlayerName(), players, discardPile, tokens, withdrawn));
			
			second.myTurnToPlay = false;
			
			/* Assuming the first player withdraws now that its his turn again */
			first.myTurnToPlay = true;
			
			assertTrue(ruleEngine.Withdraw(first.getPlayerName(), players, discardPile, tokens, withdrawn));
			
			first.myTurnToPlay = false;
			
			/* Assuming the fourth player withdraws now that its his turn again */
			forth.myTurnToPlay = true;
			
			assertTrue(ruleEngine.Withdraw(forth.getPlayerName(), players, discardPile, tokens, withdrawn));
			
			forth.myTurnToPlay = false;
			
			/* Assuming the third player withdraws now that its his turn again */
			five.myTurnToPlay = true;
			
			assertTrue(ruleEngine.Withdraw(five.getPlayerName(), players, discardPile, tokens, withdrawn));
			
			five.myTurnToPlay = false;
			
			
			/* Size of player should be one now */
			assertEquals(players.size(), 1);
			
			/* Now there should be a winner which I assume is the third player and notice before he had no tokens
			 * we can check this to be sure
			 */
			assertEquals(third.playerTokens.size(), 0);
			
			/* Lets check if he's the winner of the tournament and notice the parameter I pass in which means I 
			 * am choosing a token color as the winner of this tournament */
			String tournamentWinner = ruleEngine.winTournament(players, ruleEngine.getTournamentColour(), "red");
			assertEquals(tournamentWinner, third.getPlayerName());
			
			/* Now size of his token should increase to 1 */
			assertEquals(third.playerTokens.size(), 1);
			
			/* We make sure the token he got is the same token he chose which is a red token */
			assertEquals(third.playerTokens.get(0), "red");
			
			/* Now again assuming that player has won another tournament and I want to give him a red token which he 
			 * already had it should return a message saying he already has this token
			 */
			assertEquals(ruleEngine.winTournament(players, ruleEngine.getTournamentColour(), "red"), "You already have this token");
		}
		
		@Test
		public void testAskPlayerActionCard() {
			Player p = new Player();
			
			//tests for unhorse
			
			engine.tournamentColour = "purple";
			assertEquals(engine.askPlayerActionCard(p, "action_card_unhorse", players), "Change tournament colour to red, blue or yellow. Pick the colour");
			engine.tournamentColour = "yellow";
			assertEquals(engine.askPlayerActionCard(p, "action_card_unhorse", players), "");
			engine.tournamentColour = "blue";
			assertEquals(engine.askPlayerActionCard(p, "action_card_unhorse", players), "");
			
			//tests for change weapon
			assertEquals(engine.askPlayerActionCard(p, "action_card_change-weapon", players), "Change tournament colour to red, blue or yellow. Pick the colour");
			engine.tournamentColour = "yellow";
			assertEquals(engine.askPlayerActionCard(p, "action_card_change-weapon", players), "Change tournament colour to red, blue or yellow. Pick the colour");
			engine.tournamentColour = "red";
			assertEquals(engine.askPlayerActionCard(p, "action_card_change-weapon", players), "Change tournament colour to red, blue or yellow. Pick the colour");
			engine.tournamentColour = "purple";
			assertEquals(engine.askPlayerActionCard(p, "action_card_change-weapon", players), "");
			engine.tournamentColour = "green";
			assertEquals(engine.askPlayerActionCard(p, "action_card_change-weapon", players), "");
			
			//tests for drop weapon
			first.playerDisplay = ruleEngine.distributeCardsToPlayers(first.getPlayerName(), deckOfColorAndSupporters);
			second.playerDisplay = ruleEngine.distributeCardsToPlayers(second.getPlayerName(), deckOfColorAndSupporters);
			second.playerDisplay.add("color_card_purple_4_1");
			engine.tournamentColour = "blue";
			assertNotEquals(first.totalCardValue, 8);
			assertNotEquals(second.totalCardValue, 9);
			assertEquals(engine.askPlayerActionCard(p, "action_card_drop-weapon", players), "Change tournament colour to green.");
			assertEquals(first.totalCardValue, 8);
			assertEquals(second.totalCardValue, 9);
			engine.tournamentColour = "yellow";
			assertEquals(engine.askPlayerActionCard(p, "action_card_drop-weapon", players),"Change tournament colour to green.");
			engine.tournamentColour = "red";
			assertEquals(engine.askPlayerActionCard(p, "action_card_drop-weapon", players),  "Change tournament colour to green.");
			engine.tournamentColour = "purple";
			assertEquals(engine.askPlayerActionCard(p, "action_card_drop-weapon", players), "");
			engine.tournamentColour = "green";
			assertEquals(engine.askPlayerActionCard(p, "action_card_drop-weapon", players), "");
			
			//tests for break lance
			first.myTurnToPlay = true;
			assertEquals(engine.askPlayerActionCard(first, "action_card_break-lance", players), "Choose an opponent to discard all purple cards "+second.getPlayerName()+" ");
			
			second.hasShield = true;
			assertEquals(engine.askPlayerActionCard(first, "action_card_break-lance", players), "");
			
			second.myTurnToPlay = true;
			assertEquals(engine.askPlayerActionCard(first, "action_card_break-lance", players), "");
			
			//tests for riposte
			players.remove(second);
			assertEquals(engine.askPlayerActionCard(first, "action_card_riposte", players), "");
			
			third.playerDisplay = ruleEngine.distributeCardsToPlayers(third.getPlayerName(), deckOfColorAndSupporters);
			forth.playerDisplay = ruleEngine.distributeCardsToPlayers(forth.getPlayerName(), deckOfColorAndSupporters);
			assertEquals(engine.askPlayerActionCard(first, "action_card_riposte", players), "Choose an opponent to get last played card " + third.getPlayerName() + " " + forth.getPlayerName() + " ");
			
			forth.hasShield = true;
			assertEquals(engine.askPlayerActionCard(first, "action_card_riposte", players), "Choose an opponent to get last played card " + third.getPlayerName() + " ");
			
			five.playerDisplay = ruleEngine.distributeCardsToPlayers(five.getPlayerName(), deckOfColorAndSupporters);
			third.playerDisplay.removeAll(third.playerDisplay);
			assertEquals(engine.askPlayerActionCard(first, "action_card_riposte", players), "Choose an opponent to get last played card " + five.getPlayerName() + " ");
			
			//tests for dodge
			players.remove(second);
			
			third.playerDisplay = ruleEngine.distributeCardsToPlayers(third.getPlayerName(), deckOfColorAndSupporters);
			forth.playerDisplay = ruleEngine.distributeCardsToPlayers(forth.getPlayerName(), deckOfColorAndSupporters);
			forth.hasShield = false;
			five.hasShield  = true;
			assertEquals(engine.askPlayerActionCard(first, "action_card_dodge", players), "Choose an opponent to get any played card " + third.getPlayerName() + " " + forth.getPlayerName() + " ");
			
			forth.hasShield = true;
			assertEquals(engine.askPlayerActionCard(first, "action_card_dodge", players), "Choose an opponent to get any played card " + third.getPlayerName() + " ");
			
			five.playerDisplay = ruleEngine.distributeCardsToPlayers(five.getPlayerName(), deckOfColorAndSupporters);
			third.playerDisplay.removeAll(third.playerDisplay);
			five.hasShield = false;
			assertEquals(engine.askPlayerActionCard(first, "action_card_dodge", players), "Choose an opponent to get any played card " + five.getPlayerName() + " ");
			
			//tests for retreat
			first.playerDisplay = ruleEngine.distributeCardsToPlayers(first.getPlayerName(), deckOfColorAndSupporters);
			assertEquals(engine.askPlayerActionCard(first, "action_card_retreat", players), first.getPlayerName() + " take the card from your display back into your hand");
			first.playerDisplay.removeAll(first.playerDisplay);
			assertEquals(engine.askPlayerActionCard(first, "action_card_retreat", players), "");
			
			second.playerDisplay = ruleEngine.distributeCardsToPlayers(first.getPlayerName(), deckOfColorAndSupporters);
			assertEquals(engine.askPlayerActionCard(second, "action_card_retreat", players), second.getPlayerName() + " take the card from your display back into your hand");
			second.playerDisplay.removeAll(second.playerDisplay);
			assertEquals(engine.askPlayerActionCard(second, "action_card_retreat", players), "");
			
			//tests for "knock-down"
			players.add(second);
			for (Player pla: players) {
				pla.hasShield = false;
				pla.myTurnToPlay = false;
			}
			
			third.myTurnToPlay = true;
			assertEquals(engine.askPlayerActionCard(third, "action_card_knock-down", players), "Choose an opponent to draw a random card "+ first.getPlayerName() + " " + forth.getPlayerName() + " " + five.getPlayerName() + " " + second.getPlayerName()  + " ");
			
			forth.hasShield = true;
			assertEquals(engine.askPlayerActionCard(third, "action_card_knock-down", players), "Choose an opponent to draw a random card "+ first.getPlayerName() + " "  + five.getPlayerName() + " " + second.getPlayerName()  + " ");
			
			second.hasShield = true;
			assertEquals(engine.askPlayerActionCard(third, "action_card_knock-down", players), "Choose an opponent to draw a random card "+ first.getPlayerName() + " "  + five.getPlayerName() + " ");
			
			//test for charge
			forth.hasShield = false;
			second.hasShield = false;
			assertEquals(engine.askPlayerActionCard(third, "action_card_charge", players), "charge");
			ArrayList<Player> toTest = new ArrayList<Player>();
			toTest.add(second);
			second.playerDisplay = ruleEngine.distributeCardsToPlayers(first.getPlayerName(), deckOfColorAndSupporters);
			assertEquals(engine.askPlayerActionCard(second, "action_card_charge", toTest), "charge");
			
			second.playerDisplay.removeAll(second.playerDisplay);
			assertEquals(engine.askPlayerActionCard(second, "action_card_charge", toTest), "");
			
			
			//assertEquals(engine.askPlayerActionCard(p, "action_card_adapt", players), "You played adapt. Now tell everyone to remove cards");
		}
		
		@Test 
		  public void askPlayerAction2() {
		   //test for counter-charge
		   ArrayList<Player> toTest = new ArrayList<Player>();
		   toTest.add(second);
		   second.playerDisplay = ruleEngine.distributeCardsToPlayers(first.getPlayerName(), deckOfColorAndSupporters);
		   assertEquals(engine.askPlayerActionCard(second, "action_card_counter-charge", toTest), "counter-charge");
		   
		   second.playerDisplay.removeAll(second.playerDisplay);
		   assertEquals(engine.askPlayerActionCard(second, "action_card_counter-charge", toTest), "");
		   
		   
		   //test for outmaneuver
		   second.playerDisplay = ruleEngine.distributeCardsToPlayers(first.getPlayerName(), deckOfColorAndSupporters);
		   assertEquals(engine.askPlayerActionCard(second, "action_card_outmaneuver", toTest), "outmaneuver");
		   
		   second.playerDisplay.removeAll(second.playerDisplay);
		   assertEquals(engine.askPlayerActionCard(second, "action_card_outmaneuver", toTest), "");
		   
		   //test for outwit
		   toTest.removeAll(toTest);
		   second.hasShield = true;
		   second.myTurnToPlay = false;
		   first.hasShield = true;
		   toTest.add(second);
		   toTest.add(third);
		   third.myTurnToPlay = true;
		   assertEquals(engine.askPlayerActionCard(third, "action_card_outwit", toTest),  "Choose an opponent to swap your card with " + second.getPlayerName()+ " ");
		   second.hasShield = false;
		   assertEquals(engine.askPlayerActionCard(third, "action_card_outwit", toTest),  "");
		   second.stunnedPlayedOnMe = true;
		   assertEquals(engine.askPlayerActionCard(third, "action_card_outwit", toTest),  "Choose an opponent to swap your card with " + second.getPlayerName()+ " ");
		   second.stunnedPlayedOnMe = false;
		   second.playerDisplay = ruleEngine.distributeCardsToPlayers(first.getPlayerName(), deckOfColorAndSupporters);
		   assertEquals(engine.askPlayerActionCard(third, "action_card_outwit", toTest),  "Choose an opponent to swap your card with " + second.getPlayerName()+ " ");
		      toTest.add(first);
		      assertEquals(engine.askPlayerActionCard(third, "action_card_outwit", toTest),  "Choose an opponent to swap your card with " + second.getPlayerName()+ " " + first.getPlayerName() + " ");
		   
		      //test for adapt
		      first.playerDisplay.add("color_card_purple_3_3");
		      first.playerDisplay.add("color_card_purple_3_4");
		      assertEquals(engine.askPlayerActionCard(third, "action_card_adapt", toTest), "You played adapt. Now tell everyone to remove cards");
		      first.playerDisplay = new ArrayList<String>(Arrays.asList("color_card_purple_3_1","color_card_yellow_2_1", 
		        "color_card_red_1_4"));
		      second.playerDisplay = new ArrayList<String>(Arrays.asList("color_card_purple_3_1","color_card_purple_2_1", 
		        "color_card_purple_1_4"));
		      third.playerDisplay =  new ArrayList<String>(Arrays.asList("color_card_purple_3_1","color_card_purple_2_1", 
		        "color_card_purple_1_4"));
		      assertEquals(engine.askPlayerActionCard(third, "action_card_adapt", toTest), "");
		      
		      //test for shield
		      assertEquals(engine.askPlayerActionCard(third, "action_card_shield", toTest), "shield was played " + third.getPlayerName());
		      assertTrue(third.hasShield);
		      
		      //test for stunned
		   toTest.removeAll(toTest);
		   second.hasShield = true;
		   second.myTurnToPlay = false;
		   first.hasShield = true;
		   toTest.add(second);
		   toTest.add(third);
		   third.myTurnToPlay = true;
		   second.hasShield = false;
		   assertEquals(engine.askPlayerActionCard(third, "action_card_stunned", toTest),  "Choose an opponent to play stunned card on " + second.getPlayerName()+ " ");
		   second.stunnedPlayedOnMe = false;
		   second.playerDisplay = ruleEngine.distributeCardsToPlayers(first.getPlayerName(), deckOfColorAndSupporters);
		   assertEquals(engine.askPlayerActionCard(third, "action_card_outwit", toTest),  "Choose an opponent to swap your card with " + second.getPlayerName()+ " ");
		      toTest.add(first);
		      assertEquals(engine.askPlayerActionCard(third, "action_card_outwit", toTest),  "Choose an opponent to swap your card with " + second.getPlayerName()+ " " + first.getPlayerName() + " ");
		   
		      
		     
		  }
		@Test
		public void testCompareValue() {
			Player p = new Player();
			p.playerDisplay =  new ArrayList<String>(Arrays.asList("color_card_purple_3_1","color_card_purple_3_2", 
					"color_card_purple_3_3","color_card_purple_3_4", "color_card_purple_4_1", "color_card_purple_4_2"));
			assertFalse(engine.compareValue(p));
			p.playerDisplay = new ArrayList<String>(Arrays.asList("color_card_purple_3_1","color_card_purple_4_1", 
					"color_card_red_5_2", "action_card_outwit"));
			assertTrue(engine.compareValue(p));
			p.playerDisplay =  new ArrayList<String>(Arrays.asList(""));
			assertTrue(engine.compareValue(p));
					
		}
		
		@Test
		public void playInvalidCard() {
			ArrayList<Player> IvanhoePlayers = new ArrayList<Player>();
			assertEquals(deckOfAllCards.size(), 110);
			Player player1 = new Player("Patrick");
			Player player2 = new Player("Antony");
			Player player3 = new Player("Lizz");
			IvanhoePlayers.add(player1);
			IvanhoePlayers.add(player2);
			IvanhoePlayers.add(player3);
			
			ruleEngine.tournamentColour = "purple";
			
			//try to play bunch of different cards
			player2.myTurnToPlay = true;
			assertTrue(ruleEngine.playColorOrSupporterCard(player2, "color_card_purple_3_1"));
			assertTrue(ruleEngine.playColorOrSupporterCard(player2,  "supporter_card_3_6"));
			assertTrue(ruleEngine.playColorOrSupporterCard(player2,  "color_card_purple_6_1"));
			assertFalse(ruleEngine.playColorOrSupporterCard(player2,  "color_card_red_4_1"));
			assertFalse(ruleEngine.playColorOrSupporterCard(player2,  "color_card_red_5_1"));
			
			//2 purple cards and one supporter cards were added, when two red cards were not
			assertEquals(player2.playerDisplay.size(), 3);
			assertEquals(player2.totalCardValue, 12);
		}
		
		//check that player has a valid colour to start the tournament he wants to start
		@Test
		public void colourIsValid() {
			String colourOfTournament = "red";
			first.handCards.add("color_card_red_3_1");
			assertTrue(engine.colourIsValid(first, colourOfTournament));
			first.handCards.add("color_card_blue_2_2");
			
			assertTrue(engine.colourIsValid(first, "blue"));
			first.handCards.add("color_card_yellow_4_3");
			assertTrue(engine.colourIsValid(first, "yellow"));
			first.handCards.add("color_card_green_4_4"); 
			first.handCards.add("supporter_card_3_1");
			assertTrue(engine.colourIsValid(first, "purple"));
			
		}
		
		@Test
		public void testRemoveToken() {
			boolean check = true;
			second.playerTokens.add("purple");
			second.playerTokens.add("blue");
			second.playerTokens.add("red");
			second.playerTokens.add("green");
			engine.removeToken(second, "purple");
			for (String s: second.playerTokens) {
				if (s.equals("purple")) {
					check = false;
				}
			}
			assertEquals(3, second.playerTokens.size());
			assertTrue(check);
			engine.removeToken(second, "green");
			assertEquals(2, second.playerTokens.size());
			for (String s : second.playerTokens) {
				if (s.equals("green")) {
					check = false;
				}
			}
			assertTrue(check);
			
		}
		
		@Test
		public void testHasToGiveUpToken () {
			third.playerDisplay.add("supporter_card_6_2");
			third.playerDisplay.add("color_card_green_4_4");
			third.playerDisplay.add("color_card_red_4_2");
			assertEquals(engine.hasToGiveUpToken(third, players), false);
			third.myTurnToPlay = true;
			assertEquals(engine.hasToGiveUpToken(third, players), false);
			third.playerTokens.add("purple");
			assertEquals(engine.hasToGiveUpToken(third, players), true);
			
		}
		
		@Test
		public void testplayerWithdraw() {
			ArrayList<String> tokens = new ArrayList<String>();
			ArrayList<Player> withdrawnPlayers = new ArrayList<Player>();
			List<Player> newPlayers = new ArrayList<Player>();
			ArrayList<String> discard = new ArrayList<String>();
			for (Player p: players) {
				newPlayers.add(p);
			}
			first.playerTokens.add("red");
			first.handCards.add("action");
			first.handCards.add( "color");
			first.handCards.add( "action");
			first.handCards.add("Maiden");
			
			assertEquals(false, engine.Withdraw(first.getPlayerName(), newPlayers, discard, tokens, withdrawnPlayers));
			
			first.myTurnToPlay = true;
			assertEquals(true, engine.Withdraw(first.getPlayerName(), newPlayers, discard, tokens, withdrawnPlayers));
			
			assertEquals(players.size(), 5);
			assertEquals(withdrawnPlayers.get(0), first);
		}
		
		@Test
		public void testWinningTournament() {
			int payersAtStart = 3;
			players.remove(4);
			players.remove(3);
			players.remove(0);
			players.remove(1);
			assertEquals(players.get(0).playerTokens.size(), 0);
			
			String colorOfTheTournament = "blue";
			assertEquals(engine.winTournament(players, colorOfTheTournament, ""), players.get(0).getPlayerName());
			
			colorOfTheTournament = "purple";
			assertEquals(engine.winTournament(players, colorOfTheTournament, "blue"), "You already have this token");
			
			assertEquals(engine.winTournament(players, colorOfTheTournament, "yellow"), players.get(0).getPlayerName());
			
			players.get(0).playerTokens.clear();
			assertEquals(engine.winTournament(players, colorOfTheTournament, "yellow"), players.get(0).getPlayerName());
			
		}
		
		@Test
		  public void resetMyCards() {
		   ArrayList<String> discard = new ArrayList<String>();
		   first.handCards = engine.distributeCardsToPlayers(first.getPlayerName(), deckOfColorAndSupporters);
		   ruleEngine.resetMyCards(discard, first);
		   assertEquals(discard.size(), 8);
		   assertEquals(first.handCards.size(), 0);
		   
		   first.hasShield = true;
		   first.playerDisplay.add("color_card_red_3_4");
		   first.playerDisplay.add("color_card_green_3_4");
		   ruleEngine.resetMyCards(discard, first);
		   assertEquals(discard.size(), 10);
		   assertFalse(first.hasShield);
		   assertEquals(first.handCards.size(), 0);
		   assertEquals(first.playerDisplay.size(), 0);
		   first.stunnedPlayedOnMe = true;
		   ruleEngine.resetMyCards(discard, first);
		   assertEquals(discard.size(), 10);
		   assertFalse(first.stunnedPlayedOnMe);
		   assertEquals(first.handCards.size(), 0);
		   assertEquals(first.playerDisplay.size(), 0);
		  }
		
		@Test
		public void testWinningGame() {
			
			/* set token color for players */
			first.tokenColour = "purple";
			second.tokenColour = "yellow";
			third.tokenColour = "blue";
			forth.tokenColour = "green";
			five.tokenColour = "red";
			
			/* Assuming I set some player to have some tokens in their array of token which in reality means 
			 * they won previous tournaments
			 */
			first.playerTokens.add("red");
			first.playerTokens.add("green");
			first.playerTokens.add("yellow");
			
			third.playerTokens.add("green");
			third.playerTokens.add("red");
		
			
			/* we assume that first player starts the game by distributing cards to everyone */
			if(first.getTokenColour().equals("purple")) {
				for(int i = 0; i < players.size(); i++) {
					players.get(i).handCards = ruleEngine.distributeCardsToPlayers(players.get(i).getPlayerName(), deckOfColorAndSupporters);
				}
			}
			
			/* check hand of card for the players to make sure it's 8 in number */
			for (int i = 0; i < players.size(); i++) {
				assertEquals(players.get(i).handCards.size(), 8);
			}
			
			/* next first player picks a card and then set tournament color */
			first.myTurnToPlay = true;
			
			ruleEngine.drawCard(first, deckOfColorAndSupporters);
			
			/* His hand of card should be 9 now */
			assertEquals(first.handCards.size(), 9);
			
			/* now he picks tournament color */
			ruleEngine.setTournamentColour("purple");
			
			/* and then lets assume he plays a card and ends his turn */
			ruleEngine.playColorOrSupporterCard(first, first.handCards.get(3));
			
			first.myTurnToPlay = false;
			
			
			/* now its the third players turn to play */
			third.myTurnToPlay = true;
			
			/* Assuming I withdraw this guy right away */
			assertTrue(ruleEngine.Withdraw(third.getPlayerName(), players, discardPile, tokens, withdrawn));
			
			/* Size of withdraw should go up to 1 and discard pile should be of size 8 */
			assertEquals(discardPile.size(), 8);
			assertEquals(withdrawn.size(), 1);
			
			third.myTurnToPlay = false;
			
			/* I withdraw the rest of the players as well except the first player */
			forth.myTurnToPlay = true;
			assertTrue(ruleEngine.Withdraw(forth.getPlayerName(), players, discardPile, tokens, withdrawn));
			
			/* Size of withdraw should go up to 1 and discard pile should be of size 8 */
			assertEquals(discardPile.size(), 16);
			assertEquals(withdrawn.size(), 2);
			
			forth.myTurnToPlay = false;
			
			five.myTurnToPlay = true;
			assertTrue(ruleEngine.Withdraw(five.getPlayerName(), players, discardPile, tokens, withdrawn));
			
			/* Size of withdraw should go up to 1 and discard pile should be of size 8 */
			assertEquals(discardPile.size(), 24);
			assertEquals(withdrawn.size(), 3);
			
			five.myTurnToPlay = false;
			
			second.myTurnToPlay = true;
			assertTrue(ruleEngine.Withdraw(second.getPlayerName(), players, discardPile, tokens, withdrawn));
			
			/* Size of withdraw should go up to 1 and discard pile should be of size 8 */
			assertEquals(discardPile.size(), 32);
			assertEquals(withdrawn.size(), 4);
			
			second.myTurnToPlay = false;
			
			
			/* now there should be a winner which is the first player and there should be only 1 player
			 * left in the array of players
			 */
			assertEquals(players.size(), 1);
			
			/* Now since he won this tournament we he get this chooses a token of his choice and adds it to a 
			 * list of his tokens */
			assertEquals(ruleEngine.winTournament(players, ruleEngine.getTournamentColour(), "blue"), first.getPlayerName());
			
			/* Player one size of token should increase to 4 now */
			assertEquals(first.playerTokens.size(), 4);
			
			/* If there are 5 players in a game a player only needs four token to win the game so now he has 4 tokens he
			 * emerges as the winner of the game which should be true for the first player lets check this and confirm
			 */
			assertEquals(ruleEngine.winGame(players, 5), first.getPlayerName());
			
			
			
			/* Similarly assuming this whole process I just did happens again and number of player now is 3 and above 
			 * then the player needs a total of 5 tokens to win the game lets confirm this. Assuming i add one more token
			 * to the first players token to make it 5 assuming that he has won another tournament he should emerge as the
			 * winner if there are only 2 to 4 players
			 */
			first.playerTokens.add("purple");
			assertEquals(ruleEngine.winGame(players, 3), first.getPlayerName());
			
		}
		
		
		@Test
		public void testDeckOfCardsIsNotEmpty() {
			/* now deck is populated so it should not be empty */
			assertFalse(ruleEngine.checkDeckOfCardsEmpty(deckOfColorAndSupporters));
		}
		
		@Test
		public void testDeckOfCardsEmpty() {
			
			/* Assuming deck is empty */
			deckOfColorAndSupporters.removeAll(deckOfColorAndSupporters);
			assertTrue(ruleEngine.checkDeckOfCardsEmpty(deckOfColorAndSupporters));
			
		}
		
		@Test
		public void checkCardsInDeck() {
			assertEquals(ruleEngine.deckOfColorAndSupporters.size(), 110);
			Set<String> set = new HashSet<String>(ruleEngine.deckOfColorAndSupporters);

			assertEquals(set.size(),  ruleEngine.deckOfColorAndSupporters.size());
			 
		}
		
		@Test
		public void testCheckHighestValue() {
			
			Player current = new Player();
			current.totalCardValue = 25;
			first.totalCardValue = 4;
			second.totalCardValue = 6;
			third.totalCardValue = 8;
			forth.totalCardValue = 10;
			five.totalCardValue = 12;
			
			assertTrue(engine.checkHighestValue(current, players));
			
			current.totalCardValue = 5;
			assertFalse(engine.checkHighestValue(current, players));
			
		}
		
		@Test
		public void testPutBackCardsInDsicardPileIntoDeck() {
			
			ArrayList<String> deckOfCards = new ArrayList<String>();
			ArrayList<String> discardPile = new ArrayList<String>();
			
			discardPile.add("A");
			discardPile.add("B");
			
			
			int a = discardPile.size();
			
			engine.putBackCardsInDsicardPileIntoDeck(deckOfCards, discardPile);
			
			assertEquals(a,deckOfCards.size());
			assertEquals(discardPile.size(), 0);
			discardPile.add("");
			assertEquals(discardPile.size(), 1);
		}
		
		@Test
		public void testEndOfTurn() {
			
			Player player1 = new Player();
			Player player2 = new Player();
			Player player3 = new Player();
			player1.totalCardValue = 4;
			player2.totalCardValue = 2;
			player3.totalCardValue = 10;
			
			
			List<Player> players = new ArrayList<Player>(3);
			
			players.add(player1);
			players.add(player2);
			players.add(player3);
		
			assertEquals(ruleEngine.EndOfTurn(player3, players), true);
			
			
		}
		
		@Test
		public void testReShuffleDiscardPileWhenDrawDeckIsEmpty() {
			
			List<String> deckOfCards      = new ArrayList<String>();
			ArrayList<String> discardPile = new ArrayList<String>();
			
			discardPile.add("A");
			discardPile.add("B");
			discardPile.add("C");
			discardPile.add("D");
			
			int a = discardPile.size();
			System.out.println(a);
			
			deckOfCards = engine.reShuffleDiscardPileWhenDrawDeckIsEmpty(deckOfCards, discardPile);
			
			assertEquals(deckOfCards.size(),a);
			
		}
		
		@Test
		public void testDidPlayerGetAPurpleToken() {
			
			String tokenColor = "purple";
			String playerName = "Aziza";
			assertTrue(engine.didPlayerGetAPurpleToken(tokenColor, playerName));
			assertEquals(engine.playerWhoGotPurple, playerName);
			assertFalse(engine.didPlayerGetAPurpleToken("yellow", "Ryan"));
			assertFalse(engine.didPlayerGetAPurpleToken("red", "Tom"));
			assertEquals(engine.playerWhoGotPurple, playerName);
		}
		
		@Test
		public void testStartWithSupporters() {
			Player newOne = new Player("Michael");
			engine.distributeCardsToPlayers(newOne.getPlayerName(), deckOfColorAndSupporters);
			assertFalse(engine.playColorOrSupporterCard(newOne, "color_card_red_4"));
			newOne.myTurnToPlay = true;
			//starting with a supporter
			engine.tournamentColour = "red";
			assertTrue(engine.playColorOrSupporterCard(newOne, "supporter_card_6"));
			assertEquals(newOne.totalCardValue, 6);
			assertTrue(engine.playColorOrSupporterCard(newOne, "color_card_red_4"));
			assertEquals(newOne.totalCardValue, 10);
			assertFalse(engine.playColorOrSupporterCard(newOne, "color_card_blue_4"));
			assertEquals(newOne.totalCardValue, 10);
			assertTrue(engine.playColorOrSupporterCard(newOne, "color_card_red_5"));
			assertEquals(newOne.totalCardValue, 15);
			
			//start with a few supporters
			newOne = new Player("Michael");
			newOne.myTurnToPlay = true;
			assertTrue(engine.playColorOrSupporterCard(newOne, "supporter_card_6"));
			assertEquals(newOne.totalCardValue, 6);
			assertTrue(engine.playColorOrSupporterCard(newOne, "supporter_card_4"));
			assertEquals(newOne.totalCardValue, 10);
			assertTrue(engine.playColorOrSupporterCard(newOne, "supporter_card_3"));
			assertEquals(newOne.totalCardValue, 13);
			
			assertTrue(engine.playColorOrSupporterCard(newOne, "color_card_red_4"));
			assertEquals(newOne.totalCardValue, 17);
			
		}
		
		@Test 
		public void testPlaySupportersRounds() {
			ArrayList<Player> IvanhoePlayers = new ArrayList<Player>();
			//set tournament colour
			engine.tournamentColour = "purple";
			//add players to the new array
			Player player1 = new Player("Patrick");
			Player player2 = new Player("Antony");
			Player player3 = new Player("Lizz");
	
			IvanhoePlayers.add(player1);
			IvanhoePlayers.add(player2);
			IvanhoePlayers.add(player3);
	
			//player 2 starts the tournament 
			player2.myTurnToPlay = true;
			
			//we have to manually distribute cards, so that we can have enough supporters	
			player2.handCards = new ArrayList<String>(Arrays.asList("color_card_purple_3_1", "color_card_blue_4_1", 
					"supporter_card_2_4", "color_card_yellow_4_1", "color_card_purple_4_2", "color_card_purple_3_3", "supporter_card_3_6", "supporter_card_3_3"));
			player1.handCards = new ArrayList<String>(Arrays.asList("color_card_purple_7_1", "color_card_blue_5_1", 
					"supporter_card_3_8", "color_card_yellow_4_1", "color_card_purple_4_2", "color_card_purple_3_3", "supporter_card_6_2", "supporter_card_6_1"));
			
			player3.handCards = new ArrayList<String>(Arrays.asList("color_card_purple_4_4", "color_card_red_3_1", 
					"supporter_card_3_8", "color_card_yellow_4_2", "color_card_purple_5_2", "color_card_purple_3_4", "supporter_card_4_5", "supporter_card_3_3"));
			
			ruleEngine.drawCard(player2, deckOfColorAndSupporters);
			assertTrue(engine.playColorOrSupporterCard(player2, "color_card_purple_3_1"));
			assertTrue(engine.playColorOrSupporterCard(player2,  "supporter_card_3_6"));
			assertEquals(player2.totalCardValue, 6);
			
			//pass the turn to player 3
			player2.myTurnToPlay = false;
			player3.myTurnToPlay = true;
			
			ruleEngine.drawCard(player3, deckOfColorAndSupporters);
			assertTrue(engine.playColorOrSupporterCard(player3, "supporter_card_4_5"));
			assertTrue(engine.playColorOrSupporterCard(player3,  "color_card_purple_4_4"));
			assertEquals(player3.totalCardValue, 8);
			
			player3.myTurnToPlay = false;
			player1.myTurnToPlay = true;
			
			ruleEngine.drawCard(player1, deckOfColorAndSupporters);
			assertTrue(engine.playColorOrSupporterCard(player1, "supporter_card_3_8"));
			assertTrue(engine.playColorOrSupporterCard(player1, "supporter_card_6_1"));
			assertEquals(player1.totalCardValue, 9);
			
			player2.myTurnToPlay = true;
			player1.myTurnToPlay = false;
			
			ruleEngine.drawCard(player2, deckOfColorAndSupporters);
			assertTrue(engine.playColorOrSupporterCard(player2, "supporter_card_3_3"));
			assertTrue(engine.playColorOrSupporterCard(player2, "supporter_card_6_1"));
			assertTrue(engine.playColorOrSupporterCard(player2, "supporter_card_2_4"));
			assertEquals(player2.totalCardValue, 17);
			
			player2.myTurnToPlay = false;
			player3.myTurnToPlay = true;
			
			ruleEngine.drawCard(player3, deckOfColorAndSupporters);
			assertTrue(engine.playColorOrSupporterCard(player3, "supporter_card_4_5"));
			assertTrue(engine.playColorOrSupporterCard(player3,  "color_card_purple_4_4"));
			assertTrue(engine.playColorOrSupporterCard(player3, "supporter_card_6_3"));
			
			//not able to have more than one maiden in the game
			assertFalse(engine.playColorOrSupporterCard(player3, "supporter_card_6_1"));
			assertEquals(player3.totalCardValue, 22);
			
			player3.myTurnToPlay = false;
			player1.myTurnToPlay = true;
			ruleEngine.drawCard(player1, deckOfColorAndSupporters);
			assertTrue(engine.playColorOrSupporterCard(player1, "supporter_card_3_8"));
			assertFalse(engine.playColorOrSupporterCard(player1,  "color_card_red_4_4"));
			assertTrue(engine.playColorOrSupporterCard(player1,  "color_card_purple_7_4"));
			assertTrue(engine.playColorOrSupporterCard(player1, "supporter_card_4_2"));
			assertTrue(engine.playColorOrSupporterCard(player1, "supporter_card_3_8"));
			assertEquals(player1.totalCardValue, 26);
		
		}
		
		@Test
		public void playNotTheHighestValue() {
			ArrayList<Player> IvanhoePlayers = new ArrayList<Player>();
			//set tournament colour
			engine.tournamentColour = "red";
			//add players to the new array
			Player player1 = new Player("Patrick");
			Player player2 = new Player("Antony");
			Player player3 = new Player("Lizz");
	
			IvanhoePlayers.add(player1);
			IvanhoePlayers.add(player2);
			IvanhoePlayers.add(player3);
	
			//player 2 starts the tournament 
			player2.myTurnToPlay = true;
			
			//we have to manually distribute cards, so that we can have enough supporters	
			player2.handCards = new ArrayList<String>(Arrays.asList("color_card_red_3_1", "color_card_blue_4_1", 
					"supporter_card_2_4", "color_card_red_4_1", "color_card_red_4_2", "color_card_purple_3_3", "supporter_card_3_6", "supporter_card_3_3"));
			player1.handCards = new ArrayList<String>(Arrays.asList("color_card_red_6_1", "color_card_red_5_1", 
					"supporter_card_3_8", "color_card_yellow_4_1", "color_card_red_4_2", "color_card_purple_3_3", "supporter_card_6_2", "supporter_card_6_1"));
			
			player3.handCards = new ArrayList<String>(Arrays.asList("color_card_red_4_4", "color_card_red_3_1", 
					"supporter_card_3_8", "color_card_yellow_4_2", "color_card_red_5_2", "color_card_purple_3_4", "supporter_card_4_5", "supporter_card_3_3"));
			
			ruleEngine.drawCard(player2, deckOfColorAndSupporters);
			assertTrue(engine.playColorOrSupporterCard(player2, "color_card_red_3_1"));
			assertTrue(engine.playColorOrSupporterCard(player2,  "supporter_card_3_6"));
			assertEquals(player2.totalCardValue, 6);
			
			//returns true as other players scores equal to 0
			assertTrue(engine.checkHighestValue(player2, IvanhoePlayers));
			
			//pass the turn to player 3
			player2.myTurnToPlay = false;
			player3.myTurnToPlay = true;
			
			ruleEngine.drawCard(player3, deckOfColorAndSupporters);
			assertTrue(engine.playColorOrSupporterCard(player3, "supporter_card_4_5"));
			assertFalse(engine.playColorOrSupporterCard(player3,  "color_card_purple_4_4"));
			assertEquals(player3.totalCardValue, 4);
			
			//returns false as player1 has higher score
			assertFalse(engine.checkHighestValue(player3, IvanhoePlayers));
			
			//now play more cards
			assertTrue(engine.playColorOrSupporterCard(player3,  "color_card_red_4_4"));
			assertTrue(engine.playColorOrSupporterCard(player3,  "supporter_card_6_1"));
			assertEquals(player3.totalCardValue, 14);
			
			//returns true as now player3 has the highest score among all players
			assertTrue(engine.checkHighestValue(player3, IvanhoePlayers));
			
			player3.myTurnToPlay = false;
			player1.myTurnToPlay = true;
			
			assertTrue(engine.playColorOrSupporterCard(player1, "supporter_card_3_8"));
			assertTrue(engine.playColorOrSupporterCard(player1, "supporter_card_6_1"));
			assertEquals(player1.totalCardValue, 9);
			
			assertFalse(engine.checkHighestValue(player1, IvanhoePlayers));
			assertTrue(engine.playColorOrSupporterCard(player1, "color_card_red_5_1"));
			assertTrue(engine.playColorOrSupporterCard(player1, "color_card_red_5_2"));
			assertEquals(player1.totalCardValue, 19);
			
			assertTrue(engine.checkHighestValue(player1, IvanhoePlayers));
			
			player1.myTurnToPlay = false;
			player2.myTurnToPlay = true;
			
			assertFalse(engine.checkHighestValue(player2, IvanhoePlayers));
			assertTrue(engine.playColorOrSupporterCard(player2, "color_card_red_5_1"));
			assertFalse(engine.playColorOrSupporterCard(player2, "color_card_yellow_5_2"));
			assertEquals(player2.totalCardValue, 11);
			
			assertFalse(engine.checkHighestValue(player2, IvanhoePlayers));
			
		}
		
		@Test
		public void chargeGreenTournament() {
			ArrayList<Player> IvanhoePlayers = new ArrayList<Player>();
			assertEquals(deckOfAllCards.size(), 110);
			Player player1 = new Player("Patrick");
			Player player2 = new Player("Antony");
			Player player3 = new Player("Lizz");
			IvanhoePlayers.add(player1);
			IvanhoePlayers.add(player2);
			IvanhoePlayers.add(player3);
			
			//add cards to the player3 display, so that player1 can choose to play break lance either on player2 or player3
			ruleEngine.tournamentColour = "green";
			player3.myTurnToPlay = true;
			ruleEngine.playColorOrSupporterCard(player3, "color_card_green_1_5");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_green_1_6");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_green_1_7");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_green_1_1");
			
			player2.myTurnToPlay = true;
			ruleEngine.playColorOrSupporterCard(player2, "color_card_green_1_5");
			ruleEngine.playColorOrSupporterCard(player2, "color_card_green_1_6");
			ruleEngine.playColorOrSupporterCard(player2, "color_card_green_1_7");
			ruleEngine.playColorOrSupporterCard(player2, "color_card_green_1_1");
			
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_charge", "", discardPile);
			assertEquals(player1.playerDisplay.size(),0);
			assertEquals(player3.playerDisplay.size(),1);
			assertEquals(player2.playerDisplay.size(),1);
			
		}
		
		@Test 
		public void playActionOnUnshield() {
			ArrayList<Player> IvanhoePlayers = new ArrayList<Player>();
			assertEquals(deckOfAllCards.size(), 110);
			Player player1 = new Player("Patrick");
			Player player2 = new Player("Antony");
			Player player3 = new Player("Lizz");
			IvanhoePlayers.add(player1);
			IvanhoePlayers.add(player2);
			IvanhoePlayers.add(player3);
	
			//we have to manually distribute cards, so that we can have enough supporters	
			player2.handCards = new ArrayList<String>(Arrays.asList("color_card_red_3_1", "color_card_blue_4_1", 
					"supporter_card_2_4", "color_card_red_4_1", "color_card_red_4_2", "color_card_purple_3_3", "supporter_card_3_6", "supporter_card_3_3"));
			player1.handCards = new ArrayList<String>(Arrays.asList("color_card_red_6_1", "color_card_red_5_1", 
					"supporter_card_3_8", "color_card_yellow_4_1", "color_card_red_4_2", "color_card_purple_3_3", "supporter_card_6_2", "supporter_card_6_1"));
			
			player3.handCards = new ArrayList<String>(Arrays.asList("color_card_red_4_4", "color_card_red_3_1", 
					"supporter_card_3_8", "color_card_yellow_4_2", "color_card_red_5_2", "color_card_purple_3_4", "supporter_card_4_5", "supporter_card_3_3"));
			
			
			//play break lance on unshielded player 
			player2.myTurnToPlay = true;
			//add cards to the player2 display, so that player1 can choose to play break lance on player2
			ruleEngine.tournamentColour = "purple";
			ruleEngine.playColorOrSupporterCard(player2, "color_card_purple_3_4");
			ruleEngine.playColorOrSupporterCard(player2, "supporter_card_4_5");
			ruleEngine.playColorOrSupporterCard(player2, "supporter_card_3_3");
			assertEquals(player2.playerDisplay.size(), 3);
			player2.myTurnToPlay = false;
			player1.myTurnToPlay = true;
			
			assertEquals(ruleEngine.askPlayerActionCard(player1, "action_card_break-lance", IvanhoePlayers), "Choose an opponent to discard all purple cards "+player2.getPlayerName()+" ");
			
			player3.myTurnToPlay = true;
			//add cards to the player3 display, so that player1 can choose to play break lance either on player2 or player3
			ruleEngine.playColorOrSupporterCard(player3, "color_card_purple_3_4");
			ruleEngine.playColorOrSupporterCard(player3, "supporter_card_6_5");
			ruleEngine.playColorOrSupporterCard(player3, "supporter_card_3_3");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_blue_3_2");
			
			player3.myTurnToPlay = false;
			assertEquals(ruleEngine.askPlayerActionCard(player1, "action_card_break-lance", IvanhoePlayers), "Choose an opponent to discard all purple cards "+player2.getPlayerName()+" " + player3.getPlayerName() + " ");
			
			//now play break-lance on player 2 display 
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_break-lance", player2.getPlayerName(), discardPile);
			assertEquals(player2.playerDisplay.size(), 2);
			assertEquals(player2.totalCardValue, 7);
			
			//play break-lance on player3 display 
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_break-lance", player3.getPlayerName(), discardPile);
			assertEquals(player3.playerDisplay.size(), 2);
			assertEquals(player3.totalCardValue, 9);
			
			//test riposte on a player who doesn't have shield
			assertEquals(ruleEngine.askPlayerActionCard(player1, "action_card_riposte", IvanhoePlayers), "Choose an opponent to get last played card " + player2.getPlayerName() + " " + player3.getPlayerName() + " ");
			
			//now play riposte on player 3 display
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_riposte", player3.getPlayerName(), discardPile);
			assertEquals(player3.playerDisplay.size(), 1);
			assertEquals(player3.totalCardValue, 6);
			assertEquals(player1.totalCardValue, 3);
			assertTrue(player1.playerDisplay.contains("supporter_card_3_3"));
			
			/*test dodge on a player who doesn't have shield and 
			has more than one card on his display. So should be just player 2*/
			assertEquals(ruleEngine.askPlayerActionCard(player1, "action_card_dodge", IvanhoePlayers), "Choose an opponent to get any played card "  + player2.getPlayerName() + " " );
			
			//now play dodge on player 2 display 
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_dodge", player2.getPlayerName() + " 0", discardPile);
			assertEquals(player2.playerDisplay.size(), 1);
			assertEquals(player2.totalCardValue, 3);
			
			ruleEngine.tournamentColour = "yellow";
			//add some cards to player 1 display, so that we can test retreat 
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_3_1");
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_3_3");
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_3_2");
			ruleEngine.playColorOrSupporterCard(player1, "supporter_card_3_3");
			assertEquals(player1.totalCardValue, 15);
			
			/*test retreat on player 1 display */
			assertEquals(ruleEngine.askPlayerActionCard(player1, "action_card_retreat", IvanhoePlayers), player1.getPlayerName() + " take the card from your display back into your hand");
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_retreat", "color_card_yellow_3_1", discardPile);
			assertEquals(player1.playerDisplay.size(), 4);
			assertFalse(player1.playerDisplay.contains("color_card_yellow_3_1"));
			assertEquals(player1.handCards.size(), 9);
			
			/*test knock down on player 2 hand of cards */
			assertEquals(ruleEngine.askPlayerActionCard(player1, "action_card_knock-down", IvanhoePlayers), "Choose an opponent to draw a random card " + player2.getPlayerName() + " " + player3.getPlayerName() + " ");
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_knock-down", player3.getPlayerName(), discardPile);
			
			//compare hand sizes to make sure card was taken from player 1 display and put to player2 display
			assertEquals(player1.handCards.size(), 10);
			assertEquals(player2.handCards.size(), 8);
			
			/* test outmaneuver on players' displays */
			player2.myTurnToPlay = true;
			ruleEngine.playColorOrSupporterCard(player2, "color_card_yellow_3_5");
			player2.myTurnToPlay = false;
			
			player3.myTurnToPlay = true;
			ruleEngine.playColorOrSupporterCard(player3, "supporter_card_3_3");
			player3.myTurnToPlay = false;
			
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_outmaneuver", "", discardPile);
			assertEquals(player3.playerDisplay.size(), 1);
			assertEquals(player2.playerDisplay.size(), 1);
			assertFalse(player3.playerDisplay.contains("supporter_card_3_3"));
			assertFalse(player2.playerDisplay.contains("color_card_yellow_3_5"));
			
			//player 3 and 2 displays should be of size 1 now, as we cannot take the last card on the display
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_outmaneuver", "", discardPile);
			assertEquals(player3.playerDisplay.size(), 1);
			assertEquals(player3.totalCardValue, 6);
			assertEquals(player2.playerDisplay.size(), 1);
			assertEquals(player2.totalCardValue, 3);
			
			/* test charge action card */
			player1.myTurnToPlay  = true;
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_2_3");
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_2_2");
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_2_1");
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_3_1");
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_3_2");
			player1.myTurnToPlay  = false;
			
			player2.myTurnToPlay  = true;
			ruleEngine.playColorOrSupporterCard(player2, "color_card_yellow_2_4");
			ruleEngine.playColorOrSupporterCard(player2, "color_card_yellow_3_3");
			ruleEngine.playColorOrSupporterCard(player2, "color_card_yellow_3_4");
			ruleEngine.playColorOrSupporterCard(player2, "color_card_yellow_3_5");
			ruleEngine.playColorOrSupporterCard(player2, "color_card_yellow_3_6");
			player2.myTurnToPlay = false;
			
			player3.myTurnToPlay = true;
			ruleEngine.playColorOrSupporterCard(player3, "color_card_yellow_2_4");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_yellow_2_2");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_yellow_3_8");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_yellow_4_2");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_yellow_4_1");
			player3.myTurnToPlay = false;
			
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_charge", "", discardPile);
			assertEquals(player3.playerDisplay.size(), 4);
			assertEquals(player3.totalCardValue, 17);
			
			assertEquals(player2.playerDisplay.size(), 5);
			assertEquals(player2.totalCardValue, 15);
			
			assertEquals(player1.playerDisplay.size(), 6);
			assertEquals(player1.totalCardValue, 18);
			
			/* test counter-charge action card */
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_counter-charge", "", discardPile);
			assertEquals(player3.playerDisplay.size(), 2);
			assertEquals(player3.totalCardValue, 9);
			assertEquals(player2.playerDisplay.size(), 5);
			assertEquals(player2.totalCardValue, 15);
			assertEquals(player1.playerDisplay.size(), 6);
			assertEquals(player1.totalCardValue, 18);
			
			/* test disgrace action card */
			player2.myTurnToPlay = true;
			player3.myTurnToPlay = true;
			player1.myTurnToPlay = true;
			ruleEngine.playColorOrSupporterCard(player1, "supporter_card_3_8");
			ruleEngine.playColorOrSupporterCard(player1, "supporter_card_6_1");
			ruleEngine.playColorOrSupporterCard(player2, "supporter_card_4_3");
			ruleEngine.playColorOrSupporterCard(player2, "supporter_card_6_1");
			ruleEngine.playColorOrSupporterCard(player3, "supporter_card_2_3");
			ruleEngine.playColorOrSupporterCard(player3, "supporter_card_6_1");
			
			player2.myTurnToPlay = true;
			ruleEngine.playActionCard(player2, IvanhoePlayers, "action_card_disgrace", "", discardPile);
			assertEquals(player3.playerDisplay.size(), 1);
			assertEquals(player3.totalCardValue, 3);
			
			assertEquals(player2.playerDisplay.size(), 4);
			assertEquals(player2.totalCardValue, 12);
			
			assertEquals(player1.playerDisplay.size(), 4);
			assertEquals(player1.totalCardValue, 12);
			
			/* test adapt action card */
			player1.myTurnToPlay = true;
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_4_2");
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_2_2");
			
			player3.myTurnToPlay = true;
			ruleEngine.playColorOrSupporterCard(player3, "color_card_yellow_4_2");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_yellow_2_2");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_yellow_2_3");
			player3.myTurnToPlay = false;
			player1.myTurnToPlay = false;
			ruleEngine.playActionCard(player2, IvanhoePlayers, "action_card_adapt", "color_card_yellow_3_3", discardPile);
			ruleEngine.playActionCard(player2, IvanhoePlayers, "action_card_adapt", "color_card_yellow_3_4", discardPile);
			ruleEngine.playActionCard(player2, IvanhoePlayers, "action_card_adapt", "color_card_yellow_3_5", discardPile);
			assertEquals(player2.playerDisplay.size(), 1);
			assertEquals(player2.totalCardValue, 3);
			
			
			ruleEngine.playActionCard(player3, IvanhoePlayers, "action_card_adapt", "color_card_yellow_2_2", discardPile);
			ruleEngine.playActionCard(player3, IvanhoePlayers, "action_card_adapt", "color_card_yellow_4_2", discardPile);
			assertEquals(player3.playerDisplay.size(), 3);
			assertEquals(player3.totalCardValue, 9);
			
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_adapt", "color_card_yellow_3_3", discardPile);
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_adapt", "color_card_yellow_3_2", discardPile);
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_adapt", "color_card_yellow_3_1", discardPile);
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_adapt", "color_card_yellow_4_2", discardPile);
			for (String s: player2.playerDisplay) {
				System.out.println(s);
			}
			assertEquals(player1.playerDisplay.size(), 3);
			assertEquals(player1.totalCardValue, 9);
			
			/* test outwit action card */
			assertEquals(ruleEngine.askPlayerActionCard(player2, "action_card_outwit", IvanhoePlayers), "Choose an opponent to swap your card with "+ player1.getPlayerName()+ " " + player3.getPlayerName()+ " ");
			ruleEngine.playOutwitOn = player1.getPlayerName();
			player2.stunnedPlayedOnMe = true;
			ruleEngine.cardToTake = "color_card_yellow_3_2";
			ruleEngine.cardToGive = "stunned";
			ruleEngine.playActionCard(player2, IvanhoePlayers, "action_card_outwit", "", discardPile);
			assertTrue(player2.playerDisplay.contains("color_card_yellow_3_2"));
			assertFalse(player1.playerDisplay.contains("color_card_yellow_3_2"));
			assertFalse(player2.stunnedPlayedOnMe);
			assertTrue(player1.stunnedPlayedOnMe);
			
			/* test shield */
			assertEquals(ruleEngine.askPlayerActionCard(player2, "action_card_shield", IvanhoePlayers), "shield was played " + player2.getPlayerName());
			assertTrue(player2.hasShield);
			
		
			/* test stunned */ 
			assertEquals(ruleEngine.askPlayerActionCard(player2, "action_card_stunned", IvanhoePlayers), "Choose an opponent to play stunned card on "  + player1.getPlayerName() + " " + player3.getPlayerName()+ " ");
			ruleEngine.playActionCard(player2, IvanhoePlayers, "action_card_stunned", player1.getPlayerName(), discardPile);
			assertTrue(player1.stunnedPlayedOnMe);
				
		}
		
		@Test
		public void playActionOneRemains() {
			ArrayList<Player> IvanhoePlayers = new ArrayList<Player>();
			assertEquals(deckOfAllCards.size(), 110);
			Player player1 = new Player("Patrick");
			Player player2 = new Player("Antony");
			Player player3 = new Player("Lizz");
			IvanhoePlayers.add(player1);
			IvanhoePlayers.add(player2);
			IvanhoePlayers.add(player3);
			
			//add cards to the player3 display, so that player1 can choose to play break lance either on player2 or player3
			player3.myTurnToPlay = true;
			ruleEngine.tournamentColour = "purple";
			ruleEngine.playColorOrSupporterCard(player3, "color_card_purple_3_4");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_purple_6_2");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_purple_3_2");
			
			player2.myTurnToPlay = true;
			ruleEngine.playColorOrSupporterCard(player2, "color_card_purple_3_4");
			ruleEngine.playColorOrSupporterCard(player2, "color_card_purple_6_2");
			ruleEngine.playColorOrSupporterCard(player2, "color_card_purple_3_2");
			assertEquals(player2.playerDisplay.size(), 3);
			
			player3.myTurnToPlay = false;
			player2.myTurnToPlay = false;
			player1.myTurnToPlay = true;
			//now play break-lance on player 3 display. He has only purple cards, so we discard all except last one
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_break-lance", player3.getPlayerName(), discardPile);
			assertEquals(player3.playerDisplay.size(), 1);
			assertEquals(player3.totalCardValue, 3);
			
			//test riposte on a player who has more than one card on his display: 
			//player 3 isn't an option as he has only one card on the display
			assertEquals(ruleEngine.askPlayerActionCard(player1, "action_card_riposte", IvanhoePlayers), "Choose an opponent to get last played card " + player2.getPlayerName() + " " );
			
			/*test dodge on a  a player who has more than one card on his display: 
			player 3 isn't an option as he has only one card on the display */
			assertEquals(ruleEngine.askPlayerActionCard(player1, "action_card_dodge", IvanhoePlayers), "Choose an opponent to get any played card " + player2.getPlayerName() + " " );
			
			assertEquals(player2.playerDisplay.size(), 3);
			assertEquals(player2.totalCardValue, 12);
			
			//check retreat. shouldn't be able to play as he has only one action card on the display
			ruleEngine.removeCardUpdateScore(player2, "color_card_purple_3_2");
			ruleEngine.removeCardUpdateScore(player2, "color_card_purple_6_2");
			
			
			//player 3 and 2 displays should be of size 1 now, as we cannot take the last card on the display
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_outmaneuver", "", discardPile);
			assertEquals(player3.playerDisplay.size(), 1);
			assertEquals(player3.totalCardValue, 3);
			assertEquals(player2.playerDisplay.size(), 1);
			assertEquals(player2.totalCardValue, 3);
			
			assertEquals(player1.playerDisplay.size(), 0);
			
			//check charge
			//player 3 and 2 displays should be of size 1 now, as we cannot take  card from the display
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_charge", "", discardPile);
			assertEquals(player3.playerDisplay.size(), 1);
			assertEquals(player3.totalCardValue, 3);
			assertEquals(player2.playerDisplay.size(), 1);
			assertEquals(player2.totalCardValue, 3);
			
			player2.myTurnToPlay = true;
			ruleEngine.playColorOrSupporterCard(player2, "color_card_purple_6_2");
			//check counter-charge
			//player 3 and 2 displays should be of size 1 now, as we cannot take  card from the display
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_counter-charge", "", discardPile);
			assertEquals(player3.playerDisplay.size(), 1);
			assertEquals(player3.totalCardValue, 3);
			assertEquals(player2.playerDisplay.size(), 1);
			assertEquals(player2.totalCardValue, 3);
			
			player2.myTurnToPlay = true;
			ruleEngine.playColorOrSupporterCard(player2, "color_card_purple_6_2");
			assertEquals(player2.playerDisplay.size(), 2);
			
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_counter-charge", "", discardPile);
			//remove purple card 6 from player 2 display 
			assertEquals(player3.playerDisplay.size(), 1);
			assertEquals(player3.totalCardValue, 3);
			assertEquals(player2.playerDisplay.size(), 1);
			assertEquals(player2.totalCardValue, 3);
			
			//check disgrace 
			//player 3 and 2 displays should be of size 1 now, as we cannot take cards from their display
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_disgrace", "", discardPile);
			assertEquals(player3.playerDisplay.size(), 1);
			assertEquals(player3.totalCardValue, 3);
			assertEquals(player2.playerDisplay.size(), 1);
			assertEquals(player2.totalCardValue, 3);
			
			//add supporters and play disgrace make sure player still has at least one card
			player3.myTurnToPlay = true;
			ruleEngine.removeCardUpdateScore(player3, player3.playerDisplay.get(0));
			ruleEngine.playColorOrSupporterCard(player3, "supporter_card_3_2");
			ruleEngine.playColorOrSupporterCard(player3, "supporter_card_6_2");
			
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_disgrace", "", discardPile);
			assertEquals(player3.playerDisplay.size(), 1);
			assertEquals(player3.totalCardValue, 6);
			assertEquals(player2.playerDisplay.size(), 1);
			assertEquals(player2.totalCardValue, 3);
			
		}
		
		
		@Test
		public void playActionOnShield() {
			ArrayList<Player> IvanhoePlayers = new ArrayList<Player>();
			assertEquals(deckOfAllCards.size(), 110);
			Player player1 = new Player("Patrick");
			Player player2 = new Player("Antony");
			Player player3 = new Player("Lizz");
			IvanhoePlayers.add(player1);
			IvanhoePlayers.add(player2);
			IvanhoePlayers.add(player3);
			
			//play break lance on unshielded player 
			player2.myTurnToPlay = true;
			//add cards to the player2 display, so that player1 can choose to play break lance on player2
			ruleEngine.tournamentColour = "purple";
			ruleEngine.playColorOrSupporterCard(player2, "color_card_purple_3_4");
			ruleEngine.playColorOrSupporterCard(player2, "supporter_card_4_5");
			ruleEngine.playColorOrSupporterCard(player2, "supporter_card_3_3");
			assertEquals(player2.playerDisplay.size(), 3);
			player2.myTurnToPlay = false;
			player1.myTurnToPlay = true;
			
			assertEquals(ruleEngine.askPlayerActionCard(player1, "action_card_break-lance", IvanhoePlayers), "Choose an opponent to discard all purple cards "+player2.getPlayerName()+" ");
			
			player3.myTurnToPlay = true;
			//add cards to the player3 display, so that player1 can choose to play break lance either on player2 or player3
			ruleEngine.playColorOrSupporterCard(player3, "color_card_purple_3_4");
			ruleEngine.playColorOrSupporterCard(player3, "supporter_card_6_5");
			ruleEngine.playColorOrSupporterCard(player3, "supporter_card_3_3");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_blue_3_2");
			
			player3.myTurnToPlay = false;
			assertEquals(ruleEngine.askPlayerActionCard(player1, "action_card_break-lance", IvanhoePlayers), "Choose an opponent to discard all purple cards "+player2.getPlayerName()+" " + player3.getPlayerName() + " ");
			
			//add shield to player3 so that the "break-lance card cannot be played on him
			player3.hasShield = true;
			assertEquals(ruleEngine.askPlayerActionCard(player1, "action_card_break-lance", IvanhoePlayers), "Choose an opponent to discard all purple cards "+player2.getPlayerName()+" " );
			
			//add shield to player3 so that the "break-lance card cannot be played on him
			player2.hasShield = true;
			assertEquals(ruleEngine.askPlayerActionCard(player1, "action_card_break-lance", IvanhoePlayers), "" );
			
			/*test riposte
			player 2 has shield, thus not gonna be an option to play it on a player2  */
			player3.hasShield = false;
			assertEquals(ruleEngine.askPlayerActionCard(player1, "action_card_riposte", IvanhoePlayers), "Choose an opponent to get last played card " + player3.getPlayerName() + " ");
			
			
			/*test dodge on a player who doesn't have shield and 
			has more than one card on his display. So player3 */
			assertEquals(ruleEngine.askPlayerActionCard(player1, "action_card_dodge", IvanhoePlayers), "Choose an opponent to get any played card "  + player3.getPlayerName() + " " );
			
			ruleEngine.tournamentColour = "yellow";
			//add some cards to player 1 display 
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_3_1");
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_3_3");
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_3_2");
			ruleEngine.playColorOrSupporterCard(player1, "supporter_card_3_3");
			assertEquals(player1.totalCardValue, 12);
			
			/*test knock down on player 3 hand of cards as player 2 has shield. */
			assertEquals(ruleEngine.askPlayerActionCard(player1, "action_card_knock-down", IvanhoePlayers), "Choose an opponent to draw a random card "  + player3.getPlayerName() + " ");
			
			/*try to remove shield from player 2 and add to the player 3*/
			player2.hasShield = false;
			player3.hasShield = true;
			assertEquals(ruleEngine.askPlayerActionCard(player1, "action_card_knock-down", IvanhoePlayers), "Choose an opponent to draw a random card "  + player2.getPlayerName() + " ");
			
			
			/* test outmaneuver on players' displays */
			player2.myTurnToPlay = true;
			ruleEngine.playColorOrSupporterCard(player2, "color_card_yellow_3_5");
			player2.myTurnToPlay = false;
			
			player3.myTurnToPlay = true;
			ruleEngine.playColorOrSupporterCard(player3, "supporter_card_3_3");
			player3.myTurnToPlay = false;
			
			assertEquals(player3.playerDisplay.size(), 4);
			assertEquals(player2.playerDisplay.size(), 4);
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_outmaneuver", "", discardPile);
			
			/*player's 3 display remains the same as before playing outmaneuver as he has shield
			 * and player 3 keeps last played card */ 
			assertEquals(player3.playerDisplay.size(), 4);
			assertEquals(player2.playerDisplay.size(), 3);
			assertTrue(player3.playerDisplay.contains("supporter_card_3_3"));
			assertFalse(player2.playerDisplay.contains("color_card_yellow_3_5"));
			
			
			
			/* test charge action card */
			player1.myTurnToPlay  = true;
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_2_3");
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_2_2");
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_2_1");
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_3_1");
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_3_2");
			player1.myTurnToPlay  = false;
			
			player2.myTurnToPlay  = true;
			ruleEngine.playColorOrSupporterCard(player2, "color_card_yellow_2_4");
			ruleEngine.playColorOrSupporterCard(player2, "color_card_yellow_3_3");
			ruleEngine.playColorOrSupporterCard(player2, "color_card_yellow_3_4");
			ruleEngine.playColorOrSupporterCard(player2, "color_card_yellow_3_5");
			ruleEngine.playColorOrSupporterCard(player2, "color_card_yellow_3_6");
			player2.myTurnToPlay = false;
			
			player3.myTurnToPlay = true;
			ruleEngine.playColorOrSupporterCard(player3, "color_card_yellow_2_4");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_yellow_2_2");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_yellow_3_8");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_yellow_4_2");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_yellow_4_1");
			player3.myTurnToPlay = false;
			
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_charge", "", discardPile);
			/*player 3 display remains the same as it was before playing charge */
			
			assertEquals(player3.playerDisplay.size(), 9);
			assertEquals(player3.totalCardValue, 30);
			
			assertEquals(player2.playerDisplay.size(), 7);
			assertEquals(player2.totalCardValue, 22);
			
			assertEquals(player1.playerDisplay.size(), 6);
			assertEquals(player1.totalCardValue, 18);
			
			/* test counter-charge action card */
			//now pass shield to the player 2 
			player2.hasShield = true;
			player3.hasShield = false;
			ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_counter-charge", "", discardPile);
			
			//player 3 display has been modified
			assertEquals(player3.playerDisplay.size(), 7);
			assertEquals(player3.totalCardValue, 22);
			
			//player 2 display has not been modified
			assertEquals(player2.playerDisplay.size(), 7);
			assertEquals(player2.totalCardValue, 22);
			
			assertEquals(player1.playerDisplay.size(), 6);
			assertEquals(player1.totalCardValue, 18);
			
			/* test disgrace action card */
			player2.myTurnToPlay = true;
			player3.myTurnToPlay = true;
			player1.myTurnToPlay = true;
			ruleEngine.playColorOrSupporterCard(player1, "supporter_card_3_8");
			ruleEngine.playColorOrSupporterCard(player1, "supporter_card_6_1");
			ruleEngine.playColorOrSupporterCard(player2, "supporter_card_4_3");
			ruleEngine.playColorOrSupporterCard(player3, "supporter_card_2_3");
			ruleEngine.playColorOrSupporterCard(player3, "supporter_card_6_1");
			
			player2.myTurnToPlay = true;
			ruleEngine.playActionCard(player2, IvanhoePlayers, "action_card_disgrace", "", discardPile);
			assertEquals(player3.playerDisplay.size(), 4);
			assertEquals(player3.totalCardValue, 10);
			
			assertEquals(player2.playerDisplay.size(), 8);
			assertEquals(player2.totalCardValue, 26);
			
			assertEquals(player1.playerDisplay.size(), 5);
			assertEquals(player1.totalCardValue, 15);
			
			/* test adapt action card */
			player1.myTurnToPlay = true;
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_4_2");
			ruleEngine.playColorOrSupporterCard(player1, "color_card_yellow_2_2");
			
			player3.myTurnToPlay = true;
			ruleEngine.playColorOrSupporterCard(player3, "color_card_yellow_4_2");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_yellow_2_2");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_yellow_2_3");
			player3.myTurnToPlay = false;
			player1.myTurnToPlay = false;
			ruleEngine.playActionCard(player2, IvanhoePlayers, "action_card_adapt", "color_card_yellow_3_3", discardPile);
			ruleEngine.playActionCard(player2, IvanhoePlayers, "action_card_adapt", "color_card_yellow_3_4", discardPile);
			ruleEngine.playActionCard(player2, IvanhoePlayers, "action_card_adapt", "color_card_yellow_3_5", discardPile);
			//player 2 display remains the same
			assertEquals(player2.playerDisplay.size(), 5);
			assertEquals(player2.totalCardValue, 17);
			
			
		
			/* test stunned */ 
			player2.hasShield = false;
			player1.hasShield = true;
			//can't choose player 1 name from the pop Up as he has shield
			assertEquals(ruleEngine.askPlayerActionCard(player2, "action_card_stunned", IvanhoePlayers), "Choose an opponent to play stunned card on "  + player3.getPlayerName()+ " ");
			
		}
		
		@Test
		public void testUndoInvahoe() {
			ArrayList<Player> IvanhoePlayers = new ArrayList<Player>();
			assertEquals(deckOfAllCards.size(), 110);
			Player player1 = new Player("Patrick");
			Player player2 = new Player("Antony");
			Player player3 = new Player("Lizz");
			IvanhoePlayers.add(player1);
			IvanhoePlayers.add(player2);
			IvanhoePlayers.add(player3);
		
			player2.myTurnToPlay = true;
			
			// test undoing all effects of break-lance card with ivanhoe
			player1.myTurnToPlay = true;
			ruleEngine.tournamentColour = "purple";
			ruleEngine.playColorOrSupporterCard(player1, "color_card_purple_3_1");
			ruleEngine.playColorOrSupporterCard(player1, "color_card_red_3_1");
			ruleEngine.playColorOrSupporterCard(player1,  "color_card_purple_4_2");
			assertEquals(player1.getTotalCardvalue(), 7);
			player1.myTurnToPlay = false;
			
			ruleEngine.playActionCard(player2, IvanhoePlayers, "action_card_break-lance", player1.getPlayerName(), discardPile);
			//4 as we cannot have a display with less than one card on it
			assertEquals(player1.getTotalCardvalue(), 4);
			assertEquals(player2.playerDisplay.size(), 0);
			
			ruleEngine.ivanhoePlayed(IvanhoePlayers.get(0), IvanhoePlayers);
			assertEquals(IvanhoePlayers.get(0).getTotalCardvalue(), 7);
			
			//test unhorse and ivanhoe
			ruleEngine.lastActionCardPlayed = "unhorse";
			ruleEngine.tournamentColour = "yellow";
			ruleEngine.tournamentColourBeforeChanged = "purple";
			ruleEngine.ivanhoePlayed(IvanhoePlayers.get(1), IvanhoePlayers);
			
			assertTrue(ruleEngine.tournamentColour.equals("purple"));
			
			//test drop weapon and ivanhoe
			ruleEngine.lastActionCardPlayed = "drop-weapon";
			ruleEngine.tournamentColour = "green";
			ruleEngine.tournamentColourBeforeChanged = "yellow";
			ruleEngine.ivanhoePlayed(IvanhoePlayers.get(1), IvanhoePlayers);
			
			assertTrue(ruleEngine.tournamentColour.equals("yellow"));
			
			//test change weapon and ivanhoe
			ruleEngine.lastActionCardPlayed = "change-weapon";
			ruleEngine.tournamentColour = "red";
			ruleEngine.tournamentColourBeforeChanged = "blue";
			ruleEngine.ivanhoePlayed(IvanhoePlayers.get(1), IvanhoePlayers);
			
			player1 = IvanhoePlayers.get(0);
			player2 = IvanhoePlayers.get(1);
			player3 = IvanhoePlayers.get(2);
			
			player1.myTurnToPlay  = true;
			player2.myTurnToPlay = true;
			player3.myTurnToPlay = true;
			
			assertTrue(ruleEngine.tournamentColour.equals("blue"));
			ruleEngine.tournamentColour = "red";
			ruleEngine.playColorOrSupporterCard(player2, "color_card_red_3_1");
			ruleEngine.playColorOrSupporterCard(player3, "color_card_red_4_1");
			ruleEngine.playColorOrSupporterCard(player3,  "supporter_card_4_3");
			assertEquals(player3.playerDisplay.size(), 2);
			
	
		}
		
		@Test
		  public void undoIvanhoeStartingRiposte() {
		   ArrayList<Player> IvanhoePlayers = new ArrayList<Player>();
		   assertEquals(deckOfAllCards.size(), 110);
		   Player player1 = new Player("Patrick");
		   Player player2 = new Player("Antony");
		   Player player3 = new Player("Lizz");
		   IvanhoePlayers.add(player1);
		   IvanhoePlayers.add(player2);
		   IvanhoePlayers.add(player3);
		  
		   player3.myTurnToPlay = true;
		   ruleEngine.tournamentColour = "purple";
		   ruleEngine.playColorOrSupporterCard(player3, "color_card_purple_3_1");
		   ruleEngine.playColorOrSupporterCard(player3, "color_card_red_3_1");
		   ruleEngine.playColorOrSupporterCard(player3,  "color_card_purple_4_2");
		   
		   player2.myTurnToPlay = true;
		   ruleEngine.playColorOrSupporterCard(player2,  "color_card_purple_4_2");
		   player1.myTurnToPlay = true;
		   player3.myTurnToPlay = false;
		   player2.myTurnToPlay = false;
		   assertEquals(ruleEngine.askPlayerActionCard(player1, "action_card_riposte", IvanhoePlayers), "Choose an opponent to get last played card "  + player3.getPlayerName() + " ");
		   assertEquals(player2.playerDisplay.size(), 1);
		   
		   assertEquals(IvanhoePlayers.get(2).playerDisplay.size(), 2);
		   assertEquals(IvanhoePlayers.get(2).totalCardValue, 7);
		   assertEquals(IvanhoePlayers.get(1).totalCardValue, 4);
		   
		   //now play riposte on player 3 display as player2 has only one card
		   ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_riposte", player3.getPlayerName(), discardPile);
		   
		   assertEquals(IvanhoePlayers.get(2).playerDisplay.size(), 1);
		   assertEquals(IvanhoePlayers.get(2).totalCardValue, 3);
		   assertEquals(IvanhoePlayers.get(0).totalCardValue, 4);
		   assertEquals(IvanhoePlayers.get(0).playerDisplay.size(), 1);
		   
		   assertTrue(IvanhoePlayers.get(0).playerDisplay.contains("color_card_purple_4_2"));
		   assertEquals(IvanhoePlayers.size(), 3);
		   ruleEngine.ivanhoePlayed(IvanhoePlayers.get(2), IvanhoePlayers);
		   assertEquals(IvanhoePlayers.size(), 3);
		   assertEquals(IvanhoePlayers.get(2).playerDisplay.size(), 2);
		   assertEquals(IvanhoePlayers.get(2).totalCardValue, 7);
		   assertEquals(IvanhoePlayers.get(0).totalCardValue, 0);
		   assertFalse(IvanhoePlayers.get(0).playerDisplay.contains("color_card_purple_4_2"));
		   assertEquals(IvanhoePlayers.get(1).playerDisplay.size(), 1);
		   assertEquals(IvanhoePlayers.get(1).totalCardValue, 4);
		   
		   
		   /*play ivanhoe against dodge */
		   
		   /*test dodge on a player who doesn't have shield and 
		   has more than one card on his display. So player3 */
		   assertEquals(ruleEngine.askPlayerActionCard(IvanhoePlayers.get(0), "action_card_dodge", IvanhoePlayers), "Choose an opponent to get any played card "  + IvanhoePlayers.get(2).getPlayerName() + " " );
		   assertEquals(IvanhoePlayers.get(2).playerDisplay.size(), 2);
		   assertEquals(IvanhoePlayers.get(2).totalCardValue, 7);
		   
		   ruleEngine.playActionCard(IvanhoePlayers.get(0), IvanhoePlayers, "action_card_dodge", IvanhoePlayers.get(2).getPlayerName() + " 0", discardPile);
		   assertEquals(IvanhoePlayers.get(2).playerDisplay.size(), 1);
		   assertEquals(IvanhoePlayers.get(2).totalCardValue, 4);
		   assertEquals(IvanhoePlayers.size(), 3);
		   ruleEngine.ivanhoePlayed(IvanhoePlayers.get(2), IvanhoePlayers);
		   
		   assertEquals(IvanhoePlayers.size(), 3);
		   assertEquals(IvanhoePlayers.get(2).playerDisplay.size(), 2);
		   assertEquals(player2.totalCardValue, 4);
		   
		   ruleEngine.tournamentColour = "yellow";
		   //add some cards to player 1 display, so that we can test retreat 
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(0), "color_card_yellow_3_1");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(0), "color_card_yellow_3_3");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(0), "color_card_yellow_3_2");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(0), "supporter_card_3_3");
		   IvanhoePlayers.get(0).handCards = ruleEngine.distributeCardsToPlayers(IvanhoePlayers.get(0).getPlayerName(), deckOfAllCards);
		   assertEquals(IvanhoePlayers.get(0).totalCardValue, 12);
		   assertEquals(IvanhoePlayers.get(0).playerDisplay.size(), 4);
		   
		   //play retreat then ivanhoe
		   assertEquals(ruleEngine.askPlayerActionCard(IvanhoePlayers.get(0), "action_card_retreat", IvanhoePlayers), IvanhoePlayers.get(0).getPlayerName() + " take the card from your display back into your hand");
		   ruleEngine.playActionCard(IvanhoePlayers.get(0), IvanhoePlayers, "action_card_retreat", "color_card_yellow_3_1", discardPile);
		   assertEquals(IvanhoePlayers.get(0).playerDisplay.size(), 3);
		   assertFalse(IvanhoePlayers.get(0).playerDisplay.contains("color_card_yellow_3_1"));
		   assertEquals(IvanhoePlayers.get(0).handCards.size(), 9);
		   
		   ruleEngine.ivanhoePlayed(IvanhoePlayers.get(2), IvanhoePlayers);
		   assertEquals(IvanhoePlayers.get(0).totalCardValue, 12);
		   assertEquals(IvanhoePlayers.get(0).playerDisplay.size(), 4);
		   assertEquals(IvanhoePlayers.get(0).handCards.size(), 8);
		   
		   /*test knock down on player 3 hand of cards 8 */ 
		   IvanhoePlayers.get(1).handCards = ruleEngine.distributeCardsToPlayers(IvanhoePlayers.get(1).getPlayerName(), deckOfAllCards);
		   IvanhoePlayers.get(2).handCards = ruleEngine.distributeCardsToPlayers(IvanhoePlayers.get(2).getPlayerName(), deckOfAllCards);
		   
		   assertEquals(IvanhoePlayers.get(2).handCards.size(), 8);
		   assertEquals(IvanhoePlayers.get(0).handCards.size(), 8);
		   
		   assertEquals(ruleEngine.askPlayerActionCard(IvanhoePlayers.get(0), "action_card_knock-down", IvanhoePlayers), "Choose an opponent to draw a random card " + IvanhoePlayers.get(1).getPlayerName() + " " + IvanhoePlayers.get(2).getPlayerName() + " ");
		   assertEquals(IvanhoePlayers.get(0).handCards.size(), 8);
		   assertEquals(IvanhoePlayers.get(2).handCards.size(), 8);
		   
		   ruleEngine.ivanhoePlayed(IvanhoePlayers.get(2), IvanhoePlayers);
		   assertEquals(IvanhoePlayers.size(), 3);
		   assertEquals(IvanhoePlayers.get(0).handCards.size(), 8);
		   
		   
		   /* test outmaneuver on players' displays  and ivanhoe */
		   IvanhoePlayers.get(1).myTurnToPlay= true;
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(1), "supporter_card_3_3");
		   IvanhoePlayers.get(1).myTurnToPlay= false;
		   
		   assertEquals(IvanhoePlayers.get(0).totalCardValue, 12);
		   assertEquals(IvanhoePlayers.get(0).playerDisplay.size(), 4);
		   
		   assertEquals(IvanhoePlayers.get(1).totalCardValue, 7);
		   assertEquals(IvanhoePlayers.get(1).playerDisplay.size(), 2);
		   assertEquals(IvanhoePlayers.get(2).totalCardValue, 7);
		   assertEquals(IvanhoePlayers.get(2).playerDisplay.size(), 2);
		   ruleEngine.playActionCard(IvanhoePlayers.get(0), IvanhoePlayers, "action_card_outmaneuver", "", discardPile);
		   
		   //played outmaneuver on player 1 and 2 displays
		   assertEquals(IvanhoePlayers.get(1).totalCardValue, 4);
		   assertEquals(IvanhoePlayers.get(1).playerDisplay.size(), 1);
		   assertEquals(IvanhoePlayers.get(2).totalCardValue, 3);
		   assertEquals(IvanhoePlayers.get(2).playerDisplay.size(), 1);
		   
		   //play ivanhoe now
		   ruleEngine.ivanhoePlayed(IvanhoePlayers.get(2), IvanhoePlayers);
		   
		   assertEquals(IvanhoePlayers.get(1).totalCardValue, 7);
		   assertEquals(IvanhoePlayers.get(1).playerDisplay.size(), 2);
		   assertEquals(IvanhoePlayers.get(2).totalCardValue, 7);
		   assertEquals(IvanhoePlayers.get(2).playerDisplay.size(), 2);
		   
		   
		   
		   /* test charge action card and ivahnhoe action card*/
		   ruleEngine.tournamentColour = "yellow";
		   IvanhoePlayers.get(0).myTurnToPlay  = true;
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(0), "color_card_yellow_2_3");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(0), "color_card_yellow_2_2");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(0), "color_card_yellow_2_1");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(0), "color_card_yellow_3_1");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(0), "color_card_yellow_3_2");
		   
		   IvanhoePlayers.get(1).myTurnToPlay  = true;
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(1), "color_card_yellow_2_4");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(1), "color_card_yellow_3_3");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(1), "color_card_yellow_3_4");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(1), "color_card_yellow_3_5");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(1), "color_card_yellow_3_6");
		   IvanhoePlayers.get(1).myTurnToPlay = false;
		   
		   IvanhoePlayers.get(2).myTurnToPlay = true;
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(2), "color_card_yellow_2_4");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(2), "color_card_yellow_2_2");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(2), "color_card_yellow_3_8");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(2), "color_card_yellow_4_2");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(2), "color_card_yellow_4_1");
		   IvanhoePlayers.get(2).myTurnToPlay = false;
		   
		   ruleEngine.playActionCard(IvanhoePlayers.get(0), IvanhoePlayers, "action_card_charge", "", discardPile);
		   assertEquals(IvanhoePlayers.get(2).playerDisplay.size(), 5);
		   assertEquals(IvanhoePlayers.get(2).totalCardValue, 18);
		   
		   assertEquals(IvanhoePlayers.get(1).playerDisplay.size(), 6);
		   assertEquals(IvanhoePlayers.get(1).totalCardValue, 19);
		   
		   assertEquals(IvanhoePlayers.get(0).playerDisplay.size(), 6);
		   assertEquals(IvanhoePlayers.get(0).totalCardValue, 18);
		   
		   //play ivanhoe now
		   ruleEngine.ivanhoePlayed(IvanhoePlayers.get(2), IvanhoePlayers);
		   
		   assertEquals(IvanhoePlayers.get(2).playerDisplay.size(), 7);
		   assertEquals(IvanhoePlayers.get(2).totalCardValue, 22);
		   
		   assertEquals(IvanhoePlayers.get(1).playerDisplay.size(), 7);
		   assertEquals(IvanhoePlayers.get(1).totalCardValue, 21);
		   
		   assertEquals(IvanhoePlayers.get(0).playerDisplay.size(), 9);
		   assertEquals(IvanhoePlayers.get(0).totalCardValue, 24);
		   
		   /* test counter-charge action card  */
		   
		   ruleEngine.playActionCard(player1, IvanhoePlayers, "action_card_counter-charge", "", discardPile);
		   assertEquals(IvanhoePlayers.get(2).playerDisplay.size(), 4);
		   assertEquals(IvanhoePlayers.get(2).totalCardValue, 10);
		   
		   assertEquals(IvanhoePlayers.get(1).playerDisplay.size(), 6);
		   assertEquals(IvanhoePlayers.get(1).totalCardValue, 17);
		   
		   assertEquals(IvanhoePlayers.get(0).playerDisplay.size(), 9);
		   assertEquals(IvanhoePlayers.get(0).totalCardValue, 24);
		   
		   //play ivanhoe now
		   ruleEngine.ivanhoePlayed(IvanhoePlayers.get(2), IvanhoePlayers);
		   
		   assertEquals(IvanhoePlayers.get(2).playerDisplay.size(), 7);
		   assertEquals(IvanhoePlayers.get(2).totalCardValue, 22);
		   
		   assertEquals(IvanhoePlayers.get(1).playerDisplay.size(), 7);
		   assertEquals(IvanhoePlayers.get(1).totalCardValue, 21);
		   
		   assertEquals(IvanhoePlayers.get(0).playerDisplay.size(), 9);
		   assertEquals(IvanhoePlayers.get(0).totalCardValue, 24);
		   
		   
		   /* test disgrace action card and ivanhoe */
		   IvanhoePlayers.get(0).myTurnToPlay = true;
		   IvanhoePlayers.get(2).myTurnToPlay = true;
		   IvanhoePlayers.get(1).myTurnToPlay = true;
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(0), "supporter_card_3_8");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(0), "supporter_card_6_1");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(1), "supporter_card_4_3");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(1), "supporter_card_6_1");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(2), "supporter_card_2_3");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(2), "supporter_card_6_1");
		   assertEquals(IvanhoePlayers.get(2).playerDisplay.size(), 9);
		   IvanhoePlayers.get(0).myTurnToPlay = false;
		   IvanhoePlayers.get(2).myTurnToPlay = false;
		   
		   player2.myTurnToPlay = true;
		   ruleEngine.playActionCard(player2, IvanhoePlayers, "action_card_disgrace", "", discardPile);
		   assertEquals(IvanhoePlayers.get(2).playerDisplay.size(), 7);
		   assertEquals(IvanhoePlayers.get(2).totalCardValue, 22);
		   
		   assertEquals(IvanhoePlayers.get(1).playerDisplay.size(), 6);
		   assertEquals(IvanhoePlayers.get(1).totalCardValue, 18);
		   
		   assertEquals(IvanhoePlayers.get(0).playerDisplay.size(), 8);
		   assertEquals(IvanhoePlayers.get(0).totalCardValue, 21);
		   
		   //now play ivanhoe
		   ruleEngine.ivanhoePlayed(IvanhoePlayers.get(2), IvanhoePlayers);

		   assertEquals(IvanhoePlayers.get(2).playerDisplay.size(), 9);
		   assertEquals(IvanhoePlayers.get(2).totalCardValue, 30);
		   
		   assertEquals(IvanhoePlayers.get(1).playerDisplay.size(), 9);
		   assertEquals(IvanhoePlayers.get(1).totalCardValue, 31);
		   
		   assertEquals(IvanhoePlayers.get(0).playerDisplay.size(), 11);
		   assertEquals(IvanhoePlayers.get(0).totalCardValue, 33);
		   
		   
		   
		   /* test adapt action card and ivanhoe*/
		   IvanhoePlayers.get(0).myTurnToPlay = true;
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(0), "color_card_yellow_4_2");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(0), "color_card_yellow_2_2");
		   assertEquals(IvanhoePlayers.get(0).playerDisplay.size(), 13);
		   assertEquals(IvanhoePlayers.get(0).totalCardValue,39);
		   
		   IvanhoePlayers.get(2).myTurnToPlay = true;
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(2), "color_card_yellow_4_2");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(2), "color_card_yellow_2_2");
		   ruleEngine.playColorOrSupporterCard(IvanhoePlayers.get(2), "color_card_yellow_2_3");
		   IvanhoePlayers.get(2).myTurnToPlay = false;
		   IvanhoePlayers.get(2).myTurnToPlay = false;
		   ruleEngine.playActionCard(IvanhoePlayers.get(1), IvanhoePlayers, "action_card_adapt", "color_card_yellow_3_3", discardPile);
		   ruleEngine.playActionCard(IvanhoePlayers.get(1), IvanhoePlayers, "action_card_adapt", "color_card_yellow_3_4", discardPile);
		   ruleEngine.playActionCard(IvanhoePlayers.get(1), IvanhoePlayers, "action_card_adapt", "color_card_yellow_3_5", discardPile);
		   assertEquals(IvanhoePlayers.get(1).playerDisplay.size(), 6);
		   assertEquals(IvanhoePlayers.get(1).totalCardValue,22);
		   
		   
		   ruleEngine.playActionCard(IvanhoePlayers.get(2), IvanhoePlayers, "action_card_adapt", "color_card_yellow_2_2", discardPile);
		   ruleEngine.playActionCard(IvanhoePlayers.get(2), IvanhoePlayers, "action_card_adapt", "color_card_yellow_4_2", discardPile);
		   assertEquals(IvanhoePlayers.get(2).playerDisplay.size(), 10);
		   assertEquals(IvanhoePlayers.get(2).totalCardValue, 32);
		   
		   ruleEngine.playActionCard(IvanhoePlayers.get(0), IvanhoePlayers, "action_card_adapt", "color_card_yellow_3_3", discardPile);
		   ruleEngine.playActionCard(IvanhoePlayers.get(0), IvanhoePlayers, "action_card_adapt", "color_card_yellow_3_2", discardPile);
		   ruleEngine.playActionCard(IvanhoePlayers.get(0), IvanhoePlayers, "action_card_adapt", "color_card_yellow_3_1", discardPile);
		   ruleEngine.playActionCard(IvanhoePlayers.get(0), IvanhoePlayers, "action_card_adapt", "color_card_yellow_4_2", discardPile);
		   
		   for (String s: player2.playerDisplay) {
		    System.out.println(s);
		   }
		   assertEquals(IvanhoePlayers.get(0).playerDisplay.size(), 10);
		   assertEquals(IvanhoePlayers.get(0).totalCardValue, 30);
		   
		   //played ivanhoe 
		   ruleEngine.ivanhoePlayed(IvanhoePlayers.get(2), IvanhoePlayers);
		   
		   //check that all players got their cards back
		   assertEquals(IvanhoePlayers.get(2).playerDisplay.size(), 12);
		   assertEquals(IvanhoePlayers.get(2).totalCardValue, 38);
		   assertEquals(IvanhoePlayers.get(0).playerDisplay.size(), 13);
		   assertEquals(IvanhoePlayers.get(0).totalCardValue, 39);
		   assertEquals(IvanhoePlayers.get(1).playerDisplay.size(), 9);
		   assertEquals(IvanhoePlayers.get(1).totalCardValue,31);
		   
		   /* test outwit action card and ivanhoe */
		    IvanhoePlayers.get(0).myTurnToPlay = false;
		   assertEquals(ruleEngine.askPlayerActionCard(IvanhoePlayers.get(1), "action_card_outwit", IvanhoePlayers), "Choose an opponent to swap your card with "+ IvanhoePlayers.get(0).getPlayerName()+ " " + IvanhoePlayers.get(2).getPlayerName()+ " ");
		   ruleEngine.playOutwitOn = IvanhoePlayers.get(0).getPlayerName();
		   IvanhoePlayers.get(1).stunnedPlayedOnMe = true;
		   ruleEngine.cardToTake = "color_card_yellow_3_2";
		   ruleEngine.cardToGive = "stunned";
		   IvanhoePlayers.get(0).playerDisplay.remove(2);
		   
		   for (String card: IvanhoePlayers.get(0).playerDisplay) {
		    System.out.println(card);
		   }
		   ruleEngine.playActionCard(IvanhoePlayers.get(1), IvanhoePlayers, "action_card_outwit", "", discardPile);
		   assertTrue(IvanhoePlayers.get(1).playerDisplay.contains("color_card_yellow_3_2"));
		  
		   assertFalse(IvanhoePlayers.get(0).playerDisplay.contains("color_card_yellow_3_2"));
		   assertFalse(IvanhoePlayers.get(1).stunnedPlayedOnMe);
		   assertTrue(IvanhoePlayers.get(0).stunnedPlayedOnMe);
		   
		   /* test shield  */
		   assertEquals(ruleEngine.askPlayerActionCard(IvanhoePlayers.get(1), "action_card_shield", IvanhoePlayers), "shield was played " + IvanhoePlayers.get(1).getPlayerName());
		   assertTrue(IvanhoePlayers.get(1).hasShield); 
		   ruleEngine.ivanhoePlayed(IvanhoePlayers.get(2), IvanhoePlayers);
		   assertFalse(IvanhoePlayers.get(1).hasShield);    
		  
		   /* test stunned */ 
		   assertEquals(ruleEngine.askPlayerActionCard(IvanhoePlayers.get(1), "action_card_stunned", IvanhoePlayers), "Choose an opponent to play stunned card on "  + IvanhoePlayers.get(0).getPlayerName() + " " + IvanhoePlayers.get(2).getPlayerName()+ " ");   
		   ruleEngine.playActionCard(IvanhoePlayers.get(1), IvanhoePlayers, "action_card_stunned", IvanhoePlayers.get(0).getPlayerName(), discardPile);
		   assertTrue(IvanhoePlayers.get(0).stunnedPlayedOnMe);
		   
		   //play ivanhoe 
		   ruleEngine.ivanhoePlayed(IvanhoePlayers.get(2), IvanhoePlayers);
		   assertFalse(IvanhoePlayers.get(1).stunnedPlayedOnMe); 
		  
		  }
		
		@Test 
		public void testResetAdapt() {
			ruleEngine.playedAdapt = "Nick";
			ruleEngine.playersToRemoveAdapt = 5;
			ruleEngine.playersWhoRemovedAdapt = 2;
			assertNotEquals(ruleEngine.playedAdapt, "");
			assertNotEquals(ruleEngine.playersToRemoveAdapt, 0);
			ruleEngine.resetAdapt();
			assertEquals(ruleEngine.playedAdapt, "");
			assertEquals(ruleEngine.playersWhoRemovedAdapt, 0);
			assertEquals(ruleEngine.playersToRemoveAdapt, 0);
		}
		
		@Test
		public void testDistributeTokens() {
			
			boolean flag = false;
			
			String myToken = engine.distributeTokens(tokens);
			for (String token : tokens) {
				if (token.equals(myToken)) {
					flag = false;
				}
				
				else {
					flag = true;
				}
			}
			
			assertTrue(flag);
			
		}
		
		@Test
		public void testDistributeCardsToPlayers() {
			String playerName = "Aziza";
			assertEquals(8, engine.distributeCardsToPlayers(playerName, deckOfColorAndSupporters).size());
			
			String player2Name = "Antony";
			assertEquals(8, engine.distributeCardsToPlayers(player2Name, deckOfColorAndSupporters).size());
			
			//check that they are not getting same cards
			assertNotEquals(engine.distributeCardsToPlayers(playerName, deckOfColorAndSupporters), engine.distributeCardsToPlayers(player2Name, deckOfColorAndSupporters));
			
		}
		
		@Test
		public void testPlayerDrawsOrStartsButOthersDoesNotParticipate() {
			
			/* Assuming I make player 1 get a purple token so he can start the game */
			first.tokenColour = "purple";
			second.tokenColour = "red";
			third.tokenColour = "green";
			forth.tokenColour = "blue";
			five.tokenColour = "yellow";
			
			/* I set the rule engine to know player who got a purple */
			ruleEngine.playerWhoGotPurple = first.getPlayerName();
			
			/* Now player who got a purple has to distribute the cards to all other players */
			if(ruleEngine.didPlayerGetAPurpleToken(first.getTokenColour(), first.getPlayerName())) {
				/* Distribute cards to all other players */
				for(int i = 0; i < players.size(); i++) {
					players.get(i).handCards = ruleEngine.distributeCardsToPlayers(players.get(i).getPlayerName(), deckOfColorAndSupporters);
				}
			}
			
			/* now each players hand should contain 8 cards */
			assertEquals(first.handCards.size(), 8);
			assertEquals(second.handCards.size(), 8);
			assertEquals(third.handCards.size(), 8);
			assertEquals(forth.handCards.size(), 8);
			assertEquals(five.handCards.size(), 8);
			
			/* Get the person seated to the left of the person who distributed a card technically who got a purple token 
			 * which should be the second player technically using our algorithm */
		
			String playerWhoStartsTheGame = ruleEngine.startTournamentFirst(players);
			
			assertEquals(playerWhoStartsTheGame, second.getPlayerName());
			
			/* It's the person seated to the left of the player who distributed cards turn which is basically
			 * the second player
			 */
			second.myTurnToPlay = true;
			
			/* The person seated to the left of the player that distributed the cards
			 * starts the game by picking a card and choosing a tournament color */
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i).getPlayerName().equals(playerWhoStartsTheGame)) {
					
					/* He picks a card first */
					ruleEngine.drawCard(players.get(i), deckOfColorAndSupporters);
					
					/* Then he now chooses a tournament color */
					ruleEngine.setTournamentColour("red");
					break;
				}
			}
			
			/* Now the second players hand of card should be a total of 9 */
			assertEquals(second.handCards.size(), 9);
			
			/* Also just to make sure the tournament color is actually red I check to see that is true */
			assertEquals(ruleEngine.getTournamentColour(), "red");
			
			/* end of the second players turn */
			second.myTurnToPlay = false;
			
			/* now I want to withdraw the third player but if it's not his turn it shouldn't let him withdraw let
			 * the third player withdraw lets see how this works... :). So this should return false
			 */
			boolean withdrawnOrNotPlayer3 = ruleEngine.Withdraw(third.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertFalse(withdrawnOrNotPlayer3);
			
			/* Now I set it to his turn so he should be able to withdraw since it's his turn */
			third.myTurnToPlay = true;
			
			/* Now if I try to remove this player it should result to true since its his turn to play */
			boolean withdrawnOrNotPlayer3_1 = ruleEngine.Withdraw(third.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertTrue(withdrawnOrNotPlayer3_1);
			
			/* Set the turn to false when I am done */
			third.myTurnToPlay = false;
			
			/* Check size of players there should be only 4 players left */
			assertEquals(players.size(), 4);
			
			/* The size of withdraw player array should also increase to 1 */
			assertEquals(withdrawn.size(), 1);
			
			
			
			/* now I want to withdraw the first player but if it's not his turn it shouldn't let him withdraw let
			 * the third player withdraw lets see how this works... :). So this should return false
			 */
			boolean withdrawnOrNotPlayer1 = ruleEngine.Withdraw(first.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertFalse(withdrawnOrNotPlayer1);
			
			/* Now I set it to his turn so he should be able to withdraw since it's his turn */
			first.myTurnToPlay = true;
			
			/* Now if I try to remove this player it should result to true since its his turn to play */
			boolean withdrawnOrNotPlayer1_1 = ruleEngine.Withdraw(first.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertTrue(withdrawnOrNotPlayer1_1);
			
			/* Set the turn to false when I am done */
			first.myTurnToPlay = false;
			
			/* Check size of players there should be only 3 players left */
			assertEquals(players.size(), 3);
			
			
			/* The size of withdraw player array should also increase to 3 */
			assertEquals(withdrawn.size(), 2);

			
			
			/* now I want to withdraw the fourth player but if it's not his turn it shouldn't let him withdraw let
			 * the fourth player withdraw lets see how this works... :). So this should return false
			 */
			boolean withdrawnOrNotPlayer4 = ruleEngine.Withdraw(forth.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertFalse(withdrawnOrNotPlayer4);
			
			/* Now I set it to his turn so he should be able to withdraw since it's his turn */
			forth.myTurnToPlay = true;
			
			/* Now if I try to remove this player it should result to true since its his turn to play */
			boolean withdrawnOrNotPlayer4_4 = ruleEngine.Withdraw(forth.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertTrue(withdrawnOrNotPlayer4_4);
			
			/* Set the turn to false when I am done */
			forth.myTurnToPlay = false;
			
			/* Check size of players there should be only 2 players left */
			assertEquals(players.size(), 2);
			
			
			/* The size of withdraw player array should also increase to 3 */
			assertEquals(withdrawn.size(), 3);

			
			
			/* now I want to withdraw the fifth player but if it's not his turn it shouldn't let him withdraw let
			 * the fifth player withdraw lets see how this works... :). So this should return false
			 */
			boolean withdrawnOrNotPlayer5 = ruleEngine.Withdraw(five.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertFalse(withdrawnOrNotPlayer5);
			
			/* Now I set it to his turn so he should be able to withdraw since it's his turn */
			five.myTurnToPlay = true;
			
			/* Now if I try to remove this player it should result to true since its his turn to play */
			boolean withdrawnOrNotPlayer5_5 = ruleEngine.Withdraw(five.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertTrue(withdrawnOrNotPlayer5_5);
			
			/* Set the turn to false when I am done */
			five.myTurnToPlay = false;
			
			/* Check size of players there should be only 2 players left */
			assertEquals(players.size(), 1);
			
			
			/* The size of withdraw player array should also increase to 3 */
			assertEquals(withdrawn.size(), 4);
			
			
			/* now there is only one player left which is player 2 which is automatically the winner of the 
			 * tournament */
			String playerWhoWonTheTournament = ruleEngine.winTournament(players, ruleEngine.getTournamentColour(), "");
			assertEquals(playerWhoWonTheTournament, second.getPlayerName());
			
			/* His token size should now increase to size 1 since he had no tokens before */
			assertEquals(second.playerTokens.size(), 1);
			
			/* Also make sure the token he got matches the tournament token color */
			assertEquals(second.playerTokens.get(0), "red");
		}
		
		
		
		@Test
		public void testPlayerDrawsOrStartsButOnlyOneParticipatesByPlayingACard() {
			
			/* Assuming I make player 1 get a purple token so he can start the game */
			first.tokenColour 	= "purple";
			second.tokenColour 	= "green";
			third.tokenColour 	= "red";
			forth.tokenColour 	= "yellow";
			five.tokenColour 	= "blue";
			
			/* I set the rule engine to know player who got a purple */
			ruleEngine.playerWhoGotPurple = first.getPlayerName();
			
			/* Now player who got a purple has to distribute the cards to all other players */
			if(ruleEngine.didPlayerGetAPurpleToken(first.getTokenColour(), first.getPlayerName())) {
				/* Distribute cards to all other players */
				for(int i = 0; i < players.size(); i++) {
					players.get(i).handCards = ruleEngine.distributeCardsToPlayers(players.get(i).getPlayerName(), deckOfColorAndSupporters);
				}
			}
			
			/* now each players hand should contain 8 cards */
			assertEquals(first.handCards.size(), 8);
			assertEquals(second.handCards.size(), 8);
			assertEquals(third.handCards.size(), 8);
			assertEquals(forth.handCards.size(), 8);
			assertEquals(five.handCards.size(), 8);
			
			/* Get the person seated to the left of the person who distributed a card technically who got a purple token 
			 * which should be the second player technically using our algorithm */
		
			String playerWhoStartsTheGame = ruleEngine.startTournamentFirst(players);
			
			assertEquals(playerWhoStartsTheGame, second.getPlayerName());
			
			/* It's the person seated to the left of the player who distributed cards turn which is basically
			 * the second player
			 */
			second.myTurnToPlay = true;
			
			/* The person seated to the left of the player that distributed the cards
			 * starts the game by picking a card and choosing a tournament color */
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i).getPlayerName().equals(playerWhoStartsTheGame)) {
					
					/* He picks a card first */
					ruleEngine.drawCard(players.get(i), deckOfColorAndSupporters);
					
					/* Then he now chooses a tournament color */
					ruleEngine.setTournamentColour("red");
					break;
				}
			}
			
			/* Now the second players hand of card should be a total of 9 */
			assertEquals(second.handCards.size(), 9);
			
			/* Also just to make sure the tournament color is actually red I check to see that is true */
			assertEquals(ruleEngine.getTournamentColour(), "red");
			
			/* Now that he picked a card and chose a tournament color he plays a card  since I assume it's his turn */
			boolean playedCard = ruleEngine.playColorOrSupporterCard(second, second.handCards.get(3));
			
			/* Check if the color of the card played matches the tournament color if it does then its true else its false
			 * because cards are generated randomly
			 */
			if(playedCard) {
				assertTrue(playedCard);
				
				/* after this players plays a card from his hand remove that card from his hand of cards */
				second.handCards.remove(second.handCards.get(3));
				
				/* Check his hand of card it should be 8 now */
				assertEquals(second.handCards.size(), 8);
			}
			
			else {
				assertFalse(playedCard);
				
				/* Else the color of the card doesn't match the tournament color so his hand of 
				 * card still remains a total of 9
				 */
				assertEquals(second.handCards.size(), 9);
			}
			
			/* end of the second players turn */
			second.myTurnToPlay = false;
			
			
			/* now I want to withdraw the third player but if it's not his turn it shouldn't let him withdraw let
			 * the third player withdraw lets see how this works... :). So this should return false
			 */
			boolean withdrawnOrNotPlayer3 = ruleEngine.Withdraw(third.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertFalse(withdrawnOrNotPlayer3);
			
			/* Now I set it to his turn so he should be able to withdraw since it's his turn */
			third.myTurnToPlay = true;
			
			/* Now if I try to remove this player it should result to true since its his turn to play */
			boolean withdrawnOrNotPlayer3_1 = ruleEngine.Withdraw(third.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertTrue(withdrawnOrNotPlayer3_1);
			
			/* Set the turn to false when I am done */
			third.myTurnToPlay = false;
			
			/* Check size of players there should be only 4 players left */
			assertEquals(players.size(), 4);
			
			/* The size of withdraw player array should also increase to 1 */
			assertEquals(withdrawn.size(), 1);
			
			
			
			/* now I want to withdraw the first player but if it's not his turn it shouldn't let him withdraw let
			 * the third player withdraw lets see how this works... :). So this should return false
			 */
			boolean withdrawnOrNotPlayer1 = ruleEngine.Withdraw(first.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertFalse(withdrawnOrNotPlayer1);
			
			/* Now I set it to his turn so he should be able to withdraw since it's his turn */
			first.myTurnToPlay = true;
			
			/* Now if I try to remove this player it should result to true since its his turn to play */
			boolean withdrawnOrNotPlayer1_1 = ruleEngine.Withdraw(first.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertTrue(withdrawnOrNotPlayer1_1);
			
			/* Set the turn to false when I am done */
			first.myTurnToPlay = false;
			
			/* Check size of players there should be only 3 players left */
			assertEquals(players.size(), 3);
			
			
			/* The size of withdraw player array should also increase to 3 */
			assertEquals(withdrawn.size(), 2);

			
			
			/* now I want to withdraw the fourth player but if it's not his turn it shouldn't let him withdraw let
			 * the fourth player withdraw lets see how this works... :). So this should return false
			 */
			boolean withdrawnOrNotPlayer4 = ruleEngine.Withdraw(forth.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertFalse(withdrawnOrNotPlayer4);
			
			/* Now I set it to his turn so he should be able to withdraw since it's his turn */
			forth.myTurnToPlay = true;
			
			/* Now if I try to remove this player it should result to true since its his turn to play */
			boolean withdrawnOrNotPlayer4_4 = ruleEngine.Withdraw(forth.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertTrue(withdrawnOrNotPlayer4_4);
			
			/* Set the turn to false when I am done */
			forth.myTurnToPlay = false;
			
			/* Check size of players there should be only 2 players left */
			assertEquals(players.size(), 2);
			
			
			/* The size of withdraw player array should also increase to 3 */
			assertEquals(withdrawn.size(), 3);

			
			
			/* now I want to withdraw the fifth player but if it's not his turn it shouldn't let him withdraw let
			 * the fifth player withdraw lets see how this works... :). So this should return false
			 */
			boolean withdrawnOrNotPlayer5 = ruleEngine.Withdraw(five.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertFalse(withdrawnOrNotPlayer5);
			
			/* Now I set it to his turn so he should be able to withdraw since it's his turn */
			five.myTurnToPlay = true;
			
			/* Now if I try to remove this player it should result to true since its his turn to play */
			boolean withdrawnOrNotPlayer5_5 = ruleEngine.Withdraw(five.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertTrue(withdrawnOrNotPlayer5_5);
			
			/* Set the turn to false when I am done */
			five.myTurnToPlay = false;
			
			/* Check size of players there should be only 2 players left */
			assertEquals(players.size(), 1);
			
			
			/* The size of withdraw player array should also increase to 3 */
			assertEquals(withdrawn.size(), 4);
			
			
			/* now there is only one player left which is player 2 which is automatically the winner of the 
			 * tournament */
			String playerWhoWonTheTournament = ruleEngine.winTournament(players, ruleEngine.getTournamentColour(), "");
			assertEquals(playerWhoWonTheTournament, second.getPlayerName());
			
			/* His token size should now increase to size 1 since he had no tokens before */
			assertEquals(second.playerTokens.size(), 1);
			
			/* Also make sure the token he got matches the tournament token color */
			assertEquals(second.playerTokens.get(0), "red");
		}
		
		
		@Test
		public void testPlayerDrawsOrStartsButOnlyOneParticipatesByPlayingSeveralCards() {
			
			/* Assuming I make player 1 get a purple token so he can start the game */
			first.tokenColour	= "green";
			second.tokenColour 	= "red";
			third.tokenColour 	= "purple";
			forth.tokenColour 	= "yellow";
			five.tokenColour  	= "blue"; 
			
			/* I set the rule engine to know player who got a purple */
			ruleEngine.playerWhoGotPurple = third.getPlayerName();
			
			/* Now player who got a purple has to distribute the cards to all other players */
			if(ruleEngine.didPlayerGetAPurpleToken(third.getTokenColour(), third.getPlayerName())) {
				/* Distribute cards to all other players */
				for(int i = 0; i < players.size(); i++) {
					players.get(i).handCards = ruleEngine.distributeCardsToPlayers(players.get(i).getPlayerName(), deckOfColorAndSupporters);
				}
			}
			
			/* now each players hand should contain 8 cards */
			assertEquals(first.handCards.size(), 8);
			assertEquals(second.handCards.size(), 8);
			assertEquals(third.handCards.size(), 8);
			assertEquals(forth.handCards.size(), 8);
			assertEquals(five.handCards.size(), 8);
			
			/* Get the person seated to the left of the person who distributed a card technically who got a purple token 
			 * which should be the second player technically using our algorithm */
			String playerWhoStartsTheGame = ruleEngine.startTournamentFirst(players);
			assertEquals(playerWhoStartsTheGame, forth.getPlayerName());
			
			/* It's the person seated to the left of the player who distributed cards turn which is basically
			 * the fourth player
			 */
			forth.myTurnToPlay = true;
			
			/* The person seated to the left of the player that distributed the cards
			 * starts the game by picking a card and choosing a tournament color */
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i).getPlayerName().equals(playerWhoStartsTheGame)) {
					
					/* He picks a card first */
					ruleEngine.drawCard(players.get(i), deckOfColorAndSupporters);
					
					/* Then he now chooses a tournament color */
					ruleEngine.setTournamentColour("yellow");
					break;
				}
			}
			
			/* Now the second players hand of card should be a total of 9 */
			assertEquals(forth.handCards.size(), 9);
			
			/* Also just to make sure the tournament color is actually red I check to see that is true */
			assertEquals(ruleEngine.getTournamentColour(), "yellow");
			
			/* Now that he picked a card and chose a tournament color he plays several cards  since I assume it's his turn */
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(3));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(2));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(6));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(5));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(4));
			
			/* After he's done playing a card remove the card from his hand of cards */
			forth.handCards.remove(forth.handCards.get(3));
			forth.handCards.remove(forth.handCards.get(2));
			forth.handCards.remove(forth.handCards.get(6));
			forth.handCards.remove(forth.handCards.get(5));
			forth.handCards.remove(forth.handCards.get(4));
			
			/* Total hand of card for fourth player should be 4 now */
			assertEquals(forth.handCards.size(), 4);
			
			/* end of the second players turn */
			forth.myTurnToPlay = false;
			
			
			/* now I want to withdraw the third player but if it's not his turn it shouldn't let him withdraw let
			 * the third player withdraw lets see how this works... :). So this should return false
			 */
			boolean withdrawnOrNotPlayer3 = ruleEngine.Withdraw(third.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertFalse(withdrawnOrNotPlayer3);
			
			/* Now I set it to his turn so he should be able to withdraw since it's his turn */
			third.myTurnToPlay = true;
			
			/* Now if I try to remove this player it should result to true since its his turn to play */
			boolean withdrawnOrNotPlayer3_1 = ruleEngine.Withdraw(third.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertTrue(withdrawnOrNotPlayer3_1);
			
			/* Set the turn to false when I am done */
			third.myTurnToPlay = false;
			
			/* Check size of players there should be only 4 players left */
			assertEquals(players.size(), 4);
			
			/* The size of withdraw player array should also increase to 1 */
			assertEquals(withdrawn.size(), 1);
			
			
			
			/* now I want to withdraw the first player but if it's not his turn it shouldn't let him withdraw let
			 * the first player withdraw lets see how this works... :). So this should return false
			 */
			boolean withdrawnOrNotPlayer1 = ruleEngine.Withdraw(first.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertFalse(withdrawnOrNotPlayer1);
			
			/* Now I set it to his turn so he should be able to withdraw since it's his turn */
			first.myTurnToPlay = true;
			
			/* Now if I try to remove this player it should result to true since its his turn to play */
			boolean withdrawnOrNotPlayer1_1 = ruleEngine.Withdraw(first.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertTrue(withdrawnOrNotPlayer1_1);
			
			/* Set the turn to false when I am done */
			first.myTurnToPlay = false;
			
			/* Check size of players there should be only 3 players left */
			assertEquals(players.size(), 3);
			
			/* The size of withdraw player array should also increase to 3 */
			assertEquals(withdrawn.size(), 2);

			
			
			/* now I want to withdraw the fourth player but if it's not his turn it shouldn't let him withdraw let
			 * the fourth player withdraw lets see how this works... :). So this should return false
			 */
			boolean withdrawnOrNotPlayer2 = ruleEngine.Withdraw(second.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertFalse(withdrawnOrNotPlayer2);
			
			/* Now I set it to his turn so he should be able to withdraw since it's his turn */
			second.myTurnToPlay = true;
			
			/* Now if I try to remove this player it should result to true since its his turn to play */
			boolean withdrawnOrNotPlayer2_2 = ruleEngine.Withdraw(second.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertTrue(withdrawnOrNotPlayer2_2);
			
			/* Set the turn to false when I am done */
			second.myTurnToPlay = false;
			
			/* Check size of players there should be only 2 players left */
			assertEquals(players.size(), 2);
			
			/* The size of withdraw player array should also increase to 3 */
			assertEquals(withdrawn.size(), 3);

			
			
			/* now I want to withdraw the fifth player but if it's not his turn it shouldn't let him withdraw let
			 * the fifth player withdraw lets see how this works... :). So this should return false
			 */
			boolean withdrawnOrNotPlayer5 = ruleEngine.Withdraw(five.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertFalse(withdrawnOrNotPlayer5);
			
			/* Now I set it to his turn so he should be able to withdraw since it's his turn */
			five.myTurnToPlay = true;
			
			/* Now if I try to remove this player it should result to true since its his turn to play */
			boolean withdrawnOrNotPlayer5_5 = ruleEngine.Withdraw(five.getPlayerName(), players, discardPile, tokens, withdrawn);
			assertTrue(withdrawnOrNotPlayer5_5);
			
			/* Set the turn to false when I am done */
			five.myTurnToPlay = false;
			
			/* Check size of players there should be only 2 players left */
			assertEquals(players.size(), 1);
				
			/* The size of withdraw player array should also increase to 3 */
			assertEquals(withdrawn.size(), 4);
			
			
			
			/* now there is only one player left which is player 2 which is automatically the winner of the 
			 * tournament */
			String playerWhoWonTheTournament = ruleEngine.winTournament(players, ruleEngine.getTournamentColour(), "");
			assertEquals(playerWhoWonTheTournament, forth.getPlayerName());
			
			/* His token size should now increase to size 1 since he had no tokens before */
			assertEquals(forth.playerTokens.size(), 1);
			
			/* Also make sure the token he got matches the tournament token color */
			assertEquals(forth.playerTokens.get(0), "yellow");
		}
		
		@Test
		public void onePlayerDrawsOrStartsOthersDrawWhileSomeParticipateByPlayingACard() {
			
			/* Assuming I make player 1 get a purple token so he can start the game */
			first.tokenColour = "green";
			second.tokenColour = "red";
			third.tokenColour = "blue";
			forth.tokenColour = "yellow";
			five.tokenColour = "purple";
			
			
			/* I set the rule engine to know player who got a purple */
			ruleEngine.playerWhoGotPurple = five.getPlayerName();
			
			/* Now player who got a purple has to distribute the cards to all other players */
			if(ruleEngine.didPlayerGetAPurpleToken(five.getTokenColour(), five.getPlayerName())) {
				/* Distribute cards to all other players */
				for(int i = 0; i < players.size(); i++) {
					players.get(i).handCards = ruleEngine.distributeCardsToPlayers(players.get(i).getPlayerName(), deckOfColorAndSupporters);
				}
			}
			
			/* now each players hand should contain 8 cards */
			assertEquals(first.handCards.size(), 8);
			assertEquals(second.handCards.size(), 8);
			assertEquals(third.handCards.size(), 8);
			assertEquals(forth.handCards.size(), 8);
			assertEquals(five.handCards.size(), 8);
			
			/* Get the person seated to the left of the person who distributed a card technically who got a purple token 
			 * which should be the second player technically using our algorithm */
		
			String playerWhoStartsTheGame = ruleEngine.startTournamentFirst(players);
			
			assertEquals(playerWhoStartsTheGame, first.getPlayerName());
			
			/* It's the person seated to the left of the player who distributed cards turn which is basically
			 * the second player
			 */
			first.myTurnToPlay = true;
			
			/* The person seated to the left of the player that distributed the cards
			 * starts the game by picking a card and choosing a tournament color */
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i).getPlayerName().equals(playerWhoStartsTheGame)) {
					
					/* He picks a card first */
					ruleEngine.drawCard(players.get(i), deckOfColorAndSupporters);
					
					/* Then he now chooses a tournament color */
					ruleEngine.setTournamentColour("blue");
					break;
				}
			}
			
			/* Now the first players hand of card should be a total of 9 */
			assertEquals(first.handCards.size(), 9);
			
			/* Also just to make sure the tournament color is actually red I check to see that is true */
			assertEquals(ruleEngine.getTournamentColour(), "blue");
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(first, first.handCards.get(3));
			
			/* I remove this card from his hand now he has played */
			first.handCards.remove(first.handCards.get(3));
			
			/* Therefore there should be a total of 8 cards on his hand now */
			assertEquals(first.handCards.size(), 8);
			
			/* set his turn to false now since he played a card already */
			first.myTurnToPlay = false;
			
			
			/* I make third players turn to be next */
			third.myTurnToPlay = true;
			
			/* now he picks a card first */
			ruleEngine.drawCard(third, deckOfColorAndSupporters);
			
			/* his hand of card should be total of 9 after picking a card */
			assertEquals(third.handCards.size(), 9);
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(5));
			
			/* reduce hand of card by one after he's done playing */
			third.handCards.remove(third.handCards.get(5));
			
			/* his total hand of card now should be 8 in number */
			assertEquals(third.handCards.size(), 8);
			
			third.myTurnToPlay = false;
			
			
			/* I make forth players turn to be next */
			forth.myTurnToPlay = true;
			
			/* now he picks a card first */
			ruleEngine.drawCard(forth, deckOfColorAndSupporters);
			
			/* his hand of card should be total of 9 after picking a card */
			assertEquals(forth.handCards.size(), 9);
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(1));
			
			/* reduce hand of card by one after he's done playing */
			forth.handCards.remove(forth.handCards.get(1));
			
			/* his total hand of card now should be 8 in number */
			assertEquals(forth.handCards.size(), 8);
			
			forth.myTurnToPlay = false;
			
			
			/* Check if any of the players had a highest value or not */
			for(int i = 0; i < players.size(); i++) {
				boolean highetsValOrNot = ruleEngine.checkHighestValue(players.get(i), players);
				
				if(highetsValOrNot) {
					assertTrue(highetsValOrNot);
				}
				
				else {
					assertFalse(highetsValOrNot);
				}
			}
			
			/* There should be no winner for this tournament since no one withdrew let's check to make sure 
			 * this is the case
			 */
			String winOrNot = ruleEngine.winTournament(players, ruleEngine.getTournamentColour(), "");
			assertEquals(winOrNot, "");
		}
		
		
		@Test
		public void onePlayerDrawsOrStartsOthersDrawWhileSomeParticipateByPlayingSeveralCardsCard() {
			
			/* Assuming I make player 1 get a purple token so he can start the game */
			first.tokenColour = "green";
			second.tokenColour = "red";
			third.tokenColour = "purple";
			forth.tokenColour = "yellow";
			five.tokenColour = "blue";
			
			
			/* I set the rule engine to know player who got a purple */
			ruleEngine.playerWhoGotPurple = third.getPlayerName();
			
			/* Now player who got a purple has to distribute the cards to all other players */
			if(ruleEngine.didPlayerGetAPurpleToken(third.getTokenColour(), third.getPlayerName())) {
				/* Distribute cards to all other players */
				for(int i = 0; i < players.size(); i++) {
					players.get(i).handCards = ruleEngine.distributeCardsToPlayers(players.get(i).getPlayerName(), deckOfColorAndSupporters);
				}
			}
			
			/* now each players hand should contain 8 cards */
			assertEquals(first.handCards.size(), 8);
			assertEquals(second.handCards.size(), 8);
			assertEquals(third.handCards.size(), 8);
			assertEquals(forth.handCards.size(), 8);
			assertEquals(five.handCards.size(), 8);
			
			/* Get the person seated to the left of the person who distributed a card technically who got a purple token 
			 * which should be the second player technically using our algorithm */
		
			String playerWhoStartsTheGame = ruleEngine.startTournamentFirst(players);
			
			assertEquals(playerWhoStartsTheGame, forth.getPlayerName());
			
			/* It's the person seated to the left of the player who distributed cards turn which is basically
			 * the second player
			 */
			forth.myTurnToPlay = true;
			
			/* The person seated to the left of the player that distributed the cards
			 * starts the game by picking a card and choosing a tournament color */
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i).getPlayerName().equals(playerWhoStartsTheGame)) {
					
					/* He picks a card first */
					ruleEngine.drawCard(players.get(i), deckOfColorAndSupporters);
					
					/* Then he now chooses a tournament color */
					ruleEngine.setTournamentColour("green");
					break;
				}
			}
			
			/* Now the first players hand of card should be a total of 9 */
			assertEquals(forth.handCards.size(), 9);
			
			/* Also just to make sure the tournament color is actually red I check to see that is true */
			assertEquals(ruleEngine.getTournamentColour(), "green");
			
			/* Now that he picked a card and chose a tournament color he plays some cards  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(1));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(3));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(2));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(0));
			
			/* I remove this card from his hand now he has played */
			forth.handCards.remove(forth.handCards.get(1));
			forth.handCards.remove(forth.handCards.get(3));
			forth.handCards.remove(forth.handCards.get(2));
			forth.handCards.remove(forth.handCards.get(0));
			
			
			/* Therefore there should be a total of 5 cards on his hand now */
			assertEquals(forth.handCards.size(), 5);
			
			/* set his turn to false now since he played a card already */
			forth.myTurnToPlay = false;
			
			
			/* I make fifth players turn to be next */
			five.myTurnToPlay = true;
			
			/* now he picks a card first */
			ruleEngine.drawCard(five, deckOfColorAndSupporters);
			
			/* his hand of card should be total of 9 after picking a card */
			assertEquals(five.handCards.size(), 9);
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(five, five.handCards.get(2));
			ruleEngine.playColorOrSupporterCard(five, five.handCards.get(4));
			ruleEngine.playColorOrSupporterCard(five, five.handCards.get(3));
			
			/* reduce hand of card by one after he's done playing */
			five.handCards.remove(five.handCards.get(2));
			five.handCards.remove(five.handCards.get(4));
			five.handCards.remove(five.handCards.get(3));
			
			/* his total hand of card now should be 3 in number */
			assertEquals(five.handCards.size(), 6);
			
			five.myTurnToPlay = false;
			
			
			/* I make third players turn to be next */
			third.myTurnToPlay = true;
			
			/* now he picks a card first */
			ruleEngine.drawCard(third, deckOfColorAndSupporters);
			
			/* his hand of card should be total of 9 after picking a card */
			assertEquals(third.handCards.size(), 9);
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(1));
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(4));
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(5));
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(0));
			
			
			/* reduce hand of card by one after he's done playing */
			third.handCards.remove(third.handCards.get(1));
			third.handCards.remove(third.handCards.get(4));
			third.handCards.remove(third.handCards.get(5));
			third.handCards.remove(third.handCards.get(0));
			
			
			/* his total hand of card now should be 5 in number */
			assertEquals(forth.handCards.size(), 5);
			
			third.myTurnToPlay = false;
			
			
			/* Check if any of the players had a highest value or not */
			for(int i = 0; i < players.size(); i++) {
				boolean highetsValOrNot = ruleEngine.checkHighestValue(players.get(i), players);
				
				if(highetsValOrNot) {
					assertTrue(highetsValOrNot);
				}
				
				else {
					assertFalse(highetsValOrNot);
				}
			}
			
			
			/* There should be no winner for this tournament since no one withdrew let's check to make sure 
			 * this is the case
			 */
			String winOrNot = ruleEngine.winTournament(players, ruleEngine.getTournamentColour(), "");
			assertEquals(winOrNot, "");
		}
		
		
		@Test
		public void onePlayerDrawsOrStartsOthersDrawWhileAllParticipateByPlayingACard() {
			
			/* Assuming I make player 1 get a purple token so he can start the game */
			first.tokenColour = "green";
			second.tokenColour = "red";
			third.tokenColour = "blue";
			forth.tokenColour = "yellow";
			five.tokenColour = "purple";
			
			
			/* I set the rule engine to know player who got a purple */
			ruleEngine.playerWhoGotPurple = five.getPlayerName();
			
			/* Now player who got a purple has to distribute the cards to all other players */
			if(ruleEngine.didPlayerGetAPurpleToken(five.getTokenColour(), five.getPlayerName())) {
				/* Distribute cards to all other players */
				for(int i = 0; i < players.size(); i++) {
					players.get(i).handCards = ruleEngine.distributeCardsToPlayers(players.get(i).getPlayerName(), deckOfColorAndSupporters);
				}
			}
			
			/* now each players hand should contain 8 cards */
			assertEquals(first.handCards.size(), 8);
			assertEquals(second.handCards.size(), 8);
			assertEquals(third.handCards.size(), 8);
			assertEquals(forth.handCards.size(), 8);
			assertEquals(five.handCards.size(), 8);
			
			/* Get the person seated to the left of the person who distributed a card technically who got a purple token 
			 * which should be the second player technically using our algorithm */
		
			String playerWhoStartsTheGame = ruleEngine.startTournamentFirst(players);
			
			assertEquals(playerWhoStartsTheGame, first.getPlayerName());
			
			/* It's the person seated to the left of the player who distributed cards turn which is basically
			 * the second player
			 */
			first.myTurnToPlay = true;
			
			/* The person seated to the left of the player that distributed the cards
			 * starts the game by picking a card and choosing a tournament color */
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i).getPlayerName().equals(playerWhoStartsTheGame)) {
					
					/* He picks a card first */
					ruleEngine.drawCard(players.get(i), deckOfColorAndSupporters);
					
					/* Then he now chooses a tournament color */
					ruleEngine.setTournamentColour("blue");
					break;
				}
			}
			
			/* Now the first players hand of card should be a total of 9 */
			assertEquals(first.handCards.size(), 9);
			
			/* Also just to make sure the tournament color is actually red I check to see that is true */
			assertEquals(ruleEngine.getTournamentColour(), "blue");
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(first, first.handCards.get(5));
			
			/* I remove this card from his hand now he has played */
			first.handCards.remove(first.handCards.get(5));
			
			/* Therefore there should be a total of 8 cards on his hand now */
			assertEquals(first.handCards.size(), 8);
			
			/* set his turn to false now since he played a card already */
			first.myTurnToPlay = false;
			
			
			/* I make second players turn to be next */
			second.myTurnToPlay = true;
			
			/* now he picks a card first */
			ruleEngine.drawCard(second, deckOfColorAndSupporters);
			
			/* his hand of card should be total of 9 after picking a card */
			assertEquals(second.handCards.size(), 9);
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(second, second.handCards.get(3));
			
			/* reduce hand of card by one after he's done playing */
			second.handCards.remove(second.handCards.get(3));
			
			/* his total hand of card now should be 8 in number */
			assertEquals(third.handCards.size(), 8);
			
			second.myTurnToPlay = false;
			
			
			
			/* I make third players turn to be next */
			third.myTurnToPlay = true;
			
			/* now he picks a card first */
			ruleEngine.drawCard(third, deckOfColorAndSupporters);
			
			/* his hand of card should be total of 9 after picking a card */
			assertEquals(third.handCards.size(), 9);
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(0));
			
			/* reduce hand of card by one after he's done playing */
			third.handCards.remove(third.handCards.get(0));
			
			/* his total hand of card now should be 8 in number */
			assertEquals(third.handCards.size(), 8);
			
			third.myTurnToPlay = false;
			
			
			/* I make forth players turn to be next */
			forth.myTurnToPlay = true;
			
			/* now he picks a card first */
			ruleEngine.drawCard(forth, deckOfColorAndSupporters);
			
			/* his hand of card should be total of 9 after picking a card */
			assertEquals(forth.handCards.size(), 9);
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(1));
			
			/* reduce hand of card by one after he's done playing */
			forth.handCards.remove(forth.handCards.get(1));
			
			/* his total hand of card now should be 8 in number */
			assertEquals(forth.handCards.size(), 8);
			
			forth.myTurnToPlay = false;
			
			
			
			/* I make fifth players turn to be next */
			five.myTurnToPlay = true;
			
			/* now he picks a card first */
			ruleEngine.drawCard(five, deckOfColorAndSupporters);
			
			/* his hand of card should be total of 9 after picking a card */
			assertEquals(five.handCards.size(), 9);
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(five, five.handCards.get(3));
			
			/* reduce hand of card by one after he's done playing */
			five.handCards.remove(five.handCards.get(3));
			
			/* his total hand of card now should be 8 in number */
			assertEquals(five.handCards.size(), 8);
			
			five.myTurnToPlay = false;
			
			
			/* Check if any of the players had a highest value or not */
			for(int i = 0; i < players.size(); i++) {
				boolean highetsValOrNot = ruleEngine.checkHighestValue(players.get(i), players);
				
				if(highetsValOrNot) {
					assertTrue(highetsValOrNot);
				}
				
				else {
					assertFalse(highetsValOrNot);
				}
			}
			
			/* There should be no winner for this tournament since no one withdrew let's check to make sure 
			 * this is the case
			 */
			String winOrNot = ruleEngine.winTournament(players, ruleEngine.getTournamentColour(), "");
			assertEquals(winOrNot, "");
		}
		
		
		@Test
		public void onePlayerDrawsOrStartsOthersDrawWhileAllParticipateByPlayingSeveralCardsCard() {
			
			/* Assuming I make player 1 get a purple token so he can start the game */
			first.tokenColour = "green";
			second.tokenColour = "red";
			third.tokenColour = "purple";
			forth.tokenColour = "yellow";
			five.tokenColour = "blue";
			
			
			/* I set the rule engine to know player who got a purple */
			ruleEngine.playerWhoGotPurple = third.getPlayerName();
			
			/* Now player who got a purple has to distribute the cards to all other players */
			if(ruleEngine.didPlayerGetAPurpleToken(third.getTokenColour(), third.getPlayerName())) {
				/* Distribute cards to all other players */
				for(int i = 0; i < players.size(); i++) {
					players.get(i).handCards = ruleEngine.distributeCardsToPlayers(players.get(i).getPlayerName(), deckOfColorAndSupporters);
				}
			}
			
			/* now each players hand should contain 8 cards */
			assertEquals(first.handCards.size(), 8);
			assertEquals(second.handCards.size(), 8);
			assertEquals(third.handCards.size(), 8);
			assertEquals(forth.handCards.size(), 8);
			assertEquals(five.handCards.size(), 8);
			
			/* Get the person seated to the left of the person who distributed a card technically who got a purple token 
			 * which should be the second player technically using our algorithm */
		
			String playerWhoStartsTheGame = ruleEngine.startTournamentFirst(players);
			
			assertEquals(playerWhoStartsTheGame, forth.getPlayerName());
			
			/* It's the person seated to the left of the player who distributed cards turn which is basically
			 * the second player
			 */
			forth.myTurnToPlay = true;
			
			/* The person seated to the left of the player that distributed the cards
			 * starts the game by picking a card and choosing a tournament color */
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i).getPlayerName().equals(playerWhoStartsTheGame)) {
					
					/* He picks a card first */
					ruleEngine.drawCard(players.get(i), deckOfColorAndSupporters);
					
					/* Then he now chooses a tournament color */
					ruleEngine.setTournamentColour("green");
					break;
				}
			}
			
			/* Now the first players hand of card should be a total of 9 */
			assertEquals(forth.handCards.size(), 9);
			
			/* Also just to make sure the tournament color is actually red I check to see that is true */
			assertEquals(ruleEngine.getTournamentColour(), "green");
			
			/* Now that he picked a card and chose a tournament color he plays some cards  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(1));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(3));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(2));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(0));
			
			/* I remove this card from his hand now he has played */
			forth.handCards.remove(forth.handCards.get(1));
			forth.handCards.remove(forth.handCards.get(3));
			forth.handCards.remove(forth.handCards.get(2));
			forth.handCards.remove(forth.handCards.get(0));
			
			
			/* Therefore there should be a total of 5 cards on his hand now */
			assertEquals(forth.handCards.size(), 5);
			
			/* set his turn to false now since he played a card already */
			forth.myTurnToPlay = false;
			
			
			
			/* I make fifth players turn to be next */
			five.myTurnToPlay = true;
			
			/* now he picks a card first */
			ruleEngine.drawCard(five, deckOfColorAndSupporters);
			
			/* his hand of card should be total of 9 after picking a card */
			assertEquals(five.handCards.size(), 9);
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(five, five.handCards.get(2));
			ruleEngine.playColorOrSupporterCard(five, five.handCards.get(4));
			ruleEngine.playColorOrSupporterCard(five, five.handCards.get(3));
			
			/* reduce hand of card by one after he's done playing */
			five.handCards.remove(five.handCards.get(2));
			five.handCards.remove(five.handCards.get(4));
			five.handCards.remove(five.handCards.get(3));
			
			/* his total hand of card now should be 3 in number */
			assertEquals(five.handCards.size(), 6);
			
			five.myTurnToPlay = false;
			
			
			
			/* I make third players turn to be next */
			third.myTurnToPlay = true;
			
			/* now he picks a card first */
			ruleEngine.drawCard(third, deckOfColorAndSupporters);
			
			/* his hand of card should be total of 9 after picking a card */
			assertEquals(third.handCards.size(), 9);
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(1));
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(4));
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(5));
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(0));
			
			
			/* reduce hand of card by one after he's done playing */
			third.handCards.remove(third.handCards.get(1));
			third.handCards.remove(third.handCards.get(4));
			third.handCards.remove(third.handCards.get(5));
			third.handCards.remove(third.handCards.get(0));
			
			/* his total hand of card now should be 5 in number */
			assertEquals(forth.handCards.size(), 5);
			
			third.myTurnToPlay = false;
			
			
			
			/* I make second players turn to be next */
			second.myTurnToPlay = true;
			
			/* now he picks a card first */
			ruleEngine.drawCard(second, deckOfColorAndSupporters);
			
			/* his hand of card should be total of 9 after picking a card */
			assertEquals(second.handCards.size(), 9);
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(second, second.handCards.get(2));
			ruleEngine.playColorOrSupporterCard(second, second.handCards.get(3));
			ruleEngine.playColorOrSupporterCard(second, second.handCards.get(0));
			ruleEngine.playColorOrSupporterCard(second, second.handCards.get(1));
			ruleEngine.playColorOrSupporterCard(second, second.handCards.get(4));
			
			/* reduce hand of card by one after he's done playing */
			second.handCards.remove(second.handCards.get(2));
			second.handCards.remove(second.handCards.get(3));
			second.handCards.remove(second.handCards.get(0));
			second.handCards.remove(second.handCards.get(1));
			second.handCards.remove(second.handCards.get(4));
			
			/* his total hand of card now should be 4 in number */
			assertEquals(second.handCards.size(), 4);
			
			second.myTurnToPlay = false;
			
			
			
			/* I make first players turn to be next */
			first.myTurnToPlay = true;
			
			/* now he picks a card first */
			ruleEngine.drawCard(first, deckOfColorAndSupporters);
			
			/* his hand of card should be total of 9 after picking a card */
			assertEquals(first.handCards.size(), 9);
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(first, first.handCards.get(5));
			ruleEngine.playColorOrSupporterCard(first, first.handCards.get(3));
			ruleEngine.playColorOrSupporterCard(first, first.handCards.get(4));
			ruleEngine.playColorOrSupporterCard(first, first.handCards.get(2));
			
			/* reduce hand of card by one after he's done playing */
			first.handCards.remove(first.handCards.get(5));
			first.handCards.remove(first.handCards.get(3));
			first.handCards.remove(first.handCards.get(4));
			first.handCards.remove(first.handCards.get(2));
			
			/* his total hand of card now should be 4 in number */
			assertEquals(second.handCards.size(), 4);
			
			
			
			/* Check if any of the players had a highest value or not */
			for(int i = 0; i < players.size(); i++) {
				boolean highetsValOrNot = ruleEngine.checkHighestValue(players.get(i), players);
				
				if(highetsValOrNot) {
					assertTrue(highetsValOrNot);
				}
				
				else {
					assertFalse(highetsValOrNot);
				}
			}
			
			
			/* There should be no winner for this tournament since no one withdrew let's check to make sure 
			 * this is the case
			 */
			String winOrNot = ruleEngine.winTournament(players, ruleEngine.getTournamentColour(), "");
			assertEquals(winOrNot, "");
		}
		
		
		@Test 
		public void testRestrictionToOneMaidenPerTournament() {
			
			/* make a new deck and populate it with all maidens */
			 ArrayList<String>				  			deckOfMaidens = new ArrayList<String>(Arrays.asList("supporter_card_6","supporter_card_6", "supporter_card_6","supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6", 
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6", 
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6", 
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6"));
			 
			 
			 /* Assuming this is first tournament and Assuming I make player 1 get a purple token so he can start the game */
				first.tokenColour = "green";
				second.tokenColour = "red";
				third.tokenColour = "purple";
				forth.tokenColour = "yellow";
				five.tokenColour = "blue";
				
				
				/* I set the rule engine to know player who got a purple */
				ruleEngine.playerWhoGotPurple = third.getPlayerName();
				
				/* Now player who got a purple has to distribute the cards to all other players */
				if(ruleEngine.didPlayerGetAPurpleToken(third.getTokenColour(), third.getPlayerName())) {
					/* Distribute cards to all other players */
					for(int i = 0; i < players.size(); i++) {
						players.get(i).handCards = ruleEngine.distributeCardsToPlayers(players.get(i).getPlayerName(), deckOfMaidens);
					}
				}
				
				/* now each players hand should contain 8 cards */
				assertEquals(first.handCards.size(), 8);
				assertEquals(second.handCards.size(), 8);
				assertEquals(third.handCards.size(), 8);
				assertEquals(forth.handCards.size(), 8);
				assertEquals(five.handCards.size(), 8);
				
				/* Get the person seated to the left of the person who distributed a card technically who got a purple token 
				 * which should be the second player technically using our algorithm */
			
				String playerWhoStartsTheGame = ruleEngine.startTournamentFirst(players);
				
				assertEquals(playerWhoStartsTheGame, forth.getPlayerName());
				
				/* It's the person seated to the left of the player who distributed cards turn which is basically
				 * the second player
				 */
				forth.myTurnToPlay = true;
				
				/* The person seated to the left of the player that distributed the cards
				 * starts the game by picking a card and choosing a tournament color */
				for(int i = 0; i < players.size(); i++) {
					if(players.get(i).getPlayerName().equals(playerWhoStartsTheGame)) {
						
						/* He picks a card first */
						ruleEngine.drawCard(players.get(i), deckOfMaidens);
						
						/* Then he now chooses a tournament color */
						ruleEngine.setTournamentColour("blue");
						break;
					}
				}
				
				/* Now the first players hand of card should be a total of 9 */
				assertEquals(forth.handCards.size(), 9);
				
				/* Also just to make sure the tournament color is actually red I check to see that is true */
				assertEquals(ruleEngine.getTournamentColour(), "blue");
				
				/* Assume he plays one maiden this should let him play a maiden */
				boolean maidenPlayed = ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(2));
				assertTrue(maidenPlayed);
				
				/* remove this card */
				forth.handCards.remove(forth.handCards.get(2));
				
				/* Size should be 8 now */
				assertEquals(forth.handCards.size(), 8);
				
				
				/* If he tries to play another maiden now it should return false since only one maiden is
				 * allowed per tournament and he already played one before 
				 */
				boolean maidenPlayed1 = ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(3));
				assertFalse(maidenPlayed1);
				
				/* remove this card */
				forth.handCards.remove(forth.handCards.get(3));
				
				/* Size should be 8 now */
				assertEquals(forth.handCards.size(), 7);
				
				/* Therefore there should be a total of 5 cards on his hand now */
				//assertEquals(forth.handCards.size(), 5);
				
				/* set his turn to false now since he played a card already */
				forth.myTurnToPlay = false;
		}
		
		
		@Test
		public void winningAndGettingAToken() {
			/* Assuming I make player 1 get a purple token so he can start the game */
			first.tokenColour = "green";
			second.tokenColour = "red";
			third.tokenColour = "purple";
			forth.tokenColour = "yellow";
			five.tokenColour = "blue";
			
			
			/* I set the rule engine to know player who got a purple */
			ruleEngine.playerWhoGotPurple = third.getPlayerName();
			
			/* Now player who got a purple has to distribute the cards to all other players */
			if(ruleEngine.didPlayerGetAPurpleToken(third.getTokenColour(), third.getPlayerName())) {
				/* Distribute cards to all other players */
				for(int i = 0; i < players.size(); i++) {
					players.get(i).handCards = ruleEngine.distributeCardsToPlayers(players.get(i).getPlayerName(), deckOfColorAndSupporters);
				}
			}
			
			/* now each players hand should contain 8 cards */
			assertEquals(first.handCards.size(), 8);
			assertEquals(second.handCards.size(), 8);
			assertEquals(third.handCards.size(), 8);
			assertEquals(forth.handCards.size(), 8);
			assertEquals(five.handCards.size(), 8);
			
			/* Get the person seated to the left of the person who distributed a card technically who got a purple token 
			 * which should be the second player technically using our algorithm */
		
			String playerWhoStartsTheGame = ruleEngine.startTournamentFirst(players);
			
			assertEquals(playerWhoStartsTheGame, forth.getPlayerName());
			
			/* It's the person seated to the left of the player who distributed cards turn which is basically
			 * the second player
			 */
			forth.myTurnToPlay = true;
			
			/* The person seated to the left of the player that distributed the cards
			 * starts the game by picking a card and choosing a tournament color */
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i).getPlayerName().equals(playerWhoStartsTheGame)) {
					
					/* He picks a card first */
					ruleEngine.drawCard(players.get(i), deckOfColorAndSupporters);
					
					/* Then he now chooses a tournament color */
					ruleEngine.setTournamentColour("green");
					break;
				}
			}
			
			/* Now the first players hand of card should be a total of 9 */
			assertEquals(forth.handCards.size(), 9);
			
			/* Also just to make sure the tournament color is actually red I check to see that is true */
			assertEquals(ruleEngine.getTournamentColour(), "green");
			
			/* Now that he picked a card and chose a tournament color he plays some cards  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(1));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(3));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(2));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(0));
			
			/* I remove this card from his hand now he has played */
			forth.handCards.remove(forth.handCards.get(1));
			forth.handCards.remove(forth.handCards.get(3));
			forth.handCards.remove(forth.handCards.get(2));
			forth.handCards.remove(forth.handCards.get(0));
			
			
			/* Therefore there should be a total of 5 cards on his hand now */
			assertEquals(forth.handCards.size(), 5);
			
			/* set his turn to false now since he played a card already */
			forth.myTurnToPlay = false;
			
			
			/* I make fifth players turn to be next */
			five.myTurnToPlay = true;
			
			/* now he picks a card first */
			ruleEngine.drawCard(five, deckOfColorAndSupporters);
			
			/* his hand of card should be total of 9 after picking a card */
			assertEquals(five.handCards.size(), 9);
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(five, five.handCards.get(2));
			ruleEngine.playColorOrSupporterCard(five, five.handCards.get(4));
			ruleEngine.playColorOrSupporterCard(five, five.handCards.get(3));
			
			/* reduce hand of card by one after he's done playing */
			five.handCards.remove(five.handCards.get(2));
			five.handCards.remove(five.handCards.get(4));
			five.handCards.remove(five.handCards.get(3));
			
			/* his total hand of card now should be 3 in number */
			assertEquals(five.handCards.size(), 6);
			
			five.myTurnToPlay = false;
			
			
			/* I make third players turn to be next */
			third.myTurnToPlay = true;
			
			/* now he picks a card first */
			ruleEngine.drawCard(third, deckOfColorAndSupporters);
			
			/* his hand of card should be total of 9 after picking a card */
			assertEquals(third.handCards.size(), 9);
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(1));
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(4));
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(5));
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(0));
			
			/* reduce hand of card by one after he's done playing */
			third.handCards.remove(third.handCards.get(1));
			third.handCards.remove(third.handCards.get(4));
			third.handCards.remove(third.handCards.get(5));
			third.handCards.remove(third.handCards.get(0));
			
			/* his total hand of card now should be 5 in number */
			assertEquals(forth.handCards.size(), 5);
			
			third.myTurnToPlay = false;
			System.out.println(players.size());
			
			
			/* Assuming the second player withdraws now that its his turn again */
			second.myTurnToPlay = true;
			
			assertTrue(ruleEngine.Withdraw(second.getPlayerName(), players, discardPile, tokens, withdrawn));
			
			second.myTurnToPlay = false;
			
			/* Assuming the first player withdraws now that its his turn again */
			first.myTurnToPlay = true;
			
			assertTrue(ruleEngine.Withdraw(first.getPlayerName(), players, discardPile, tokens, withdrawn));
			
			first.myTurnToPlay = false;
			
			/* Assuming the fourth player withdraws now that its his turn again */
			forth.myTurnToPlay = true;
			
			assertTrue(ruleEngine.Withdraw(forth.getPlayerName(), players, discardPile, tokens, withdrawn));
			
			forth.myTurnToPlay = false;
			
			/* Assuming the third player withdraws now that its his turn again */
			third.myTurnToPlay = true;
			
			assertTrue(ruleEngine.Withdraw(third.getPlayerName(), players, discardPile, tokens, withdrawn));
			
			third.myTurnToPlay = false;
			
			
			/* Size of player should be one now */
			assertEquals(players.size(), 1);
			
			/* Now there should be a winner which I assume is the fifth player and notice before he had no tokens
			 * we can check this to be sure
			 */
			assertEquals(five.playerTokens.size(), 0);
			
			/* Lets check if he's the winner of the tournament */
			String tournamentWinner = ruleEngine.winTournament(players, ruleEngine.getTournamentColour(), "");
			assertEquals(tournamentWinner, five.getPlayerName());
			
			/* Now size of his token should increase to 1 */
			assertEquals(five.playerTokens.size(), 1);
			
			/* We make sure the token he got is same token as the tournament color which is green */
			assertEquals(five.playerTokens.get(0), "green");
		}
		
		
		@Test
		public void winningAndGettingATokenWhenItsPurpleTournament() {
			/* Assuming I make player 1 get a purple token so he can start the game */
			first.tokenColour = "green";
			second.tokenColour = "red";
			third.tokenColour = "purple";
			forth.tokenColour = "yellow";
			five.tokenColour = "blue";
			
			
			/* I set the rule engine to know player who got a purple */
			ruleEngine.playerWhoGotPurple = third.getPlayerName();
			
			/* Now player who got a purple has to distribute the cards to all other players */
			if(ruleEngine.didPlayerGetAPurpleToken(third.getTokenColour(), third.getPlayerName())) {
				/* Distribute cards to all other players */
				for(int i = 0; i < players.size(); i++) {
					players.get(i).handCards = ruleEngine.distributeCardsToPlayers(players.get(i).getPlayerName(), deckOfColorAndSupporters);
				}
			}
			
			/* now each players hand should contain 8 cards */
			assertEquals(first.handCards.size(), 8);
			assertEquals(second.handCards.size(), 8);
			assertEquals(third.handCards.size(), 8);
			assertEquals(forth.handCards.size(), 8);
			assertEquals(five.handCards.size(), 8);
			
			/* Get the person seated to the left of the person who distributed a card technically who got a purple token 
			 * which should be the second player technically using our algorithm */
		
			String playerWhoStartsTheGame = ruleEngine.startTournamentFirst(players);
			
			assertEquals(playerWhoStartsTheGame, forth.getPlayerName());
			
			/* It's the person seated to the left of the player who distributed cards turn which is basically
			 * the second player
			 */
			forth.myTurnToPlay = true;
			
			/* The person seated to the left of the player that distributed the cards
			 * starts the game by picking a card and choosing a tournament color */
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i).getPlayerName().equals(playerWhoStartsTheGame)) {
					
					/* He picks a card first */
					ruleEngine.drawCard(players.get(i), deckOfColorAndSupporters);
					
					/* Then he now chooses a tournament color */
					ruleEngine.setTournamentColour("purple");
					break;
				}
			}
			
			/* Now the first players hand of card should be a total of 9 */
			assertEquals(forth.handCards.size(), 9);
			
			/* Also just to make sure the tournament color is actually red I check to see that is true */
			assertEquals(ruleEngine.getTournamentColour(), "purple");
			
			/* Now that he picked a card and chose a tournament color he plays some cards  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(1));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(3));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(2));
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(0));
			
			/* I remove this card from his hand now he has played */
			forth.handCards.remove(forth.handCards.get(1));
			forth.handCards.remove(forth.handCards.get(3));
			forth.handCards.remove(forth.handCards.get(2));
			forth.handCards.remove(forth.handCards.get(0));
			
			
			/* Therefore there should be a total of 5 cards on his hand now */
			assertEquals(forth.handCards.size(), 5);
			
			/* set his turn to false now since he played a card already */
			forth.myTurnToPlay = false;
			
			
			/* I make fifth players turn to be next */
			five.myTurnToPlay = true;
			
			/* now he picks a card first */
			ruleEngine.drawCard(five, deckOfColorAndSupporters);
			
			/* his hand of card should be total of 9 after picking a card */
			assertEquals(five.handCards.size(), 9);
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(five, five.handCards.get(2));
			ruleEngine.playColorOrSupporterCard(five, five.handCards.get(4));
			ruleEngine.playColorOrSupporterCard(five, five.handCards.get(3));
			
			/* reduce hand of card by one after he's done playing */
			five.handCards.remove(five.handCards.get(2));
			five.handCards.remove(five.handCards.get(4));
			five.handCards.remove(five.handCards.get(3));
			
			/* his total hand of card now should be 3 in number */
			assertEquals(five.handCards.size(), 6);
			
			five.myTurnToPlay = false;
			
			
			/* I make third players turn to be next */
			third.myTurnToPlay = true;
			
			/* now he picks a card first */
			ruleEngine.drawCard(third, deckOfColorAndSupporters);
			
			/* his hand of card should be total of 9 after picking a card */
			assertEquals(third.handCards.size(), 9);
			
			/* Now that he picked a card and chose a tournament color he plays a card  since it's his turn to play */
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(1));
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(4));
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(5));
			ruleEngine.playColorOrSupporterCard(third, third.handCards.get(0));
			
			/* reduce hand of card by one after he's done playing */
			third.handCards.remove(third.handCards.get(1));
			third.handCards.remove(third.handCards.get(4));
			third.handCards.remove(third.handCards.get(5));
			third.handCards.remove(third.handCards.get(0));
			
			/* his total hand of card now should be 5 in number */
			assertEquals(forth.handCards.size(), 5);
			
			third.myTurnToPlay = false;
			System.out.println(players.size());
			
			
			/* Assuming the second player withdraws now that its his turn again */
			second.myTurnToPlay = true;
			
			assertTrue(ruleEngine.Withdraw(second.getPlayerName(), players, discardPile, tokens, withdrawn));
			
			second.myTurnToPlay = false;
			
			/* Assuming the first player withdraws now that its his turn again */
			first.myTurnToPlay = true;
			
			assertTrue(ruleEngine.Withdraw(first.getPlayerName(), players, discardPile, tokens, withdrawn));
			
			first.myTurnToPlay = false;
			
			/* Assuming the fourth player withdraws now that its his turn again */
			forth.myTurnToPlay = true;
			
			assertTrue(ruleEngine.Withdraw(forth.getPlayerName(), players, discardPile, tokens, withdrawn));
			
			forth.myTurnToPlay = false;
			
			/* Assuming the third player withdraws now that its his turn again */
			five.myTurnToPlay = true;
			
			assertTrue(ruleEngine.Withdraw(five.getPlayerName(), players, discardPile, tokens, withdrawn));
			
			five.myTurnToPlay = false;
			
			
			/* Size of player should be one now */
			assertEquals(players.size(), 1);
			
			/* Now there should be a winner which I assume is the third player and notice before he had no tokens
			 * we can check this to be sure
			 */
			assertEquals(third.playerTokens.size(), 0);
			
			/* Lets check if he's the winner of the tournament and notice the parameter I pass in which means I 
			 * am choosing a token color as the winner of this tournament */
			String tournamentWinner = ruleEngine.winTournament(players, ruleEngine.getTournamentColour(), "red");
			assertEquals(tournamentWinner, third.getPlayerName());
			
			/* Now size of his token should increase to 1 */
			assertEquals(third.playerTokens.size(), 1);
			
			/* We make sure the token he got is the same token he chose which is a red token */
			assertEquals(third.playerTokens.get(0), "red");
		}
		
		
		@Test
		public void loosingWithAMaidenInYourDisplay() {
			
			/* make a new deck and populate it with all maidens */
			 ArrayList<String>				  			deckOfMaidens = new ArrayList<String>(Arrays.asList("supporter_card_6","supporter_card_6", "supporter_card_6","supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6", 
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6", 
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6", 
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6",
																											"supporter_card_6", "supporter_card_6", "supporter_card_6", "supporter_card_6"));
			 
			 
			 /* Assuming this is first tournament and Assuming I make player 1 get a purple token so he can start the game */
				first.tokenColour = "green";
				second.tokenColour = "red";
				third.tokenColour = "purple";
				forth.tokenColour = "yellow";
				five.tokenColour = "blue";
				
				/* Assuming I give some players some tokens assuming they won some tournaments previously */
				first.playerTokens.add("red");
				first.playerTokens.add("yellow");
				
				second.playerTokens.add("green");
				
				forth.playerTokens.add("yellow");
				forth.playerTokens.add("blue");
				
				
				/* I set the rule engine to know player who got a purple */
				ruleEngine.playerWhoGotPurple = third.getPlayerName();
				
				/* Now player who got a purple has to distribute the cards to all other players */
				if(ruleEngine.didPlayerGetAPurpleToken(third.getTokenColour(), third.getPlayerName())) {
					/* Distribute cards to all other players */
					for(int i = 0; i < players.size(); i++) {
						players.get(i).handCards = ruleEngine.distributeCardsToPlayers(players.get(i).getPlayerName(), deckOfMaidens);
					}
				}
				
				/* now each players hand should contain 8 cards */
				assertEquals(first.handCards.size(), 8);
				assertEquals(second.handCards.size(), 8);
				assertEquals(third.handCards.size(), 8);
				assertEquals(forth.handCards.size(), 8);
				assertEquals(five.handCards.size(), 8);
				
				/* Get the person seated to the left of the person who distributed a card technically who got a purple token 
				 * which should be the second player technically using our algorithm */
			
				String playerWhoStartsTheGame = ruleEngine.startTournamentFirst(players);
				
				assertEquals(playerWhoStartsTheGame, forth.getPlayerName());
				
				/* It's the person seated to the left of the player who distributed cards turn which is basically
				 * the second player
				 */
				forth.myTurnToPlay = true;
				
				/* The person seated to the left of the player that distributed the cards
				 * starts the game by picking a card and choosing a tournament color */
				for(int i = 0; i < players.size(); i++) {
					if(players.get(i).getPlayerName().equals(playerWhoStartsTheGame)) {
						
						/* He picks a card first */
						ruleEngine.drawCard(players.get(i), deckOfMaidens);
						
						/* Then he now chooses a tournament color */
						ruleEngine.setTournamentColour("blue");
						break;
					}
				}
				
				/* Now the first players hand of card should be a total of 9 */
				assertEquals(forth.handCards.size(), 9);
				
				/* Also just to make sure the tournament color is actually red I check to see that is true */
				assertEquals(ruleEngine.getTournamentColour(), "blue");
				
				/* Assume he plays one maiden this should let him play a maiden */
				boolean maidenPlayed = ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(2));
				assertTrue(maidenPlayed);
				
				/* remove this card */
				forth.handCards.remove(forth.handCards.get(2));
				
				/* Size should be 8 now */
				assertEquals(forth.handCards.size(), 8);
				
				/* Now he's done playing assuming he has the lowest score of everyone and has to withdraw
				 * before he withdraws I have to check his display to know if he has a maiden or not if he
				 * does then he has to give up a token from one of his two tokens as initialized above
				 */
				boolean doesHeHaveAMaidenInHisDisplay = ruleEngine.hasToGiveUpToken(forth, players);
				
				/* This should basically return true because he has a maiden in his display */
				assertTrue(doesHeHaveAMaidenInHisDisplay);
				
				/* Since its true he has a maiden in his display I take off one token for him off him tokens
				 * using the function in our rule engine
				 */
				ruleEngine.removeToken(forth, forth.playerTokens.get(forth.playerTokens.size()-1));
				
				/* Now the size of his token should be one */
				assertEquals(forth.playerTokens.size(), 1);
				
				/* Now he can be withdrawn from the game session successfully */
				assertTrue(ruleEngine.Withdraw(forth.getPlayerName(), players, discardPile, tokens, withdrawn));
				
				forth.myTurnToPlay = false;
				
		}
		
		
		@Test
		public void playerCannotStartATournament() {
			
			/* FIRST SCENARIO: When a player who is meant to start possess all action cards then he passes
			 * his turn to someone else 
			 * 
			 * So I will create a deck and populate it with bunch of action cards 
			 */
			
			ArrayList<String>				  			deckOfActionCards = new ArrayList<String>(Arrays.asList("action_card_adapt","action_card_adapt", "action_card_charge","action_card_adapt",
																												"action_card_adapt","action_card_adapt", "action_card_charge","action_card_adapt",
																												"action_card_adapt","action_card_adapt", "action_card_charge","action_card_adapt",
																												"action_card_adapt","action_card_adapt", "action_card_charge","action_card_adapt",
																												"action_card_adapt","action_card_adapt", "action_card_charge","action_card_adapt",
																												"action_card_adapt","action_card_adapt", "action_card_charge","action_card_adapt",
																												"action_card_adapt","action_card_adapt", "action_card_charge","action_card_adapt",
																												"action_card_adapt","action_card_adapt", "action_card_charge","action_card_adapt",
																												"action_card_adapt","action_card_adapt", "action_card_charge","action_card_adapt",
																												"action_card_adapt","action_card_adapt", "action_card_charge","action_card_adapt",
																												"action_card_adapt","action_card_adapt", "action_card_charge","action_card_adapt",
																												"action_card_adapt","action_card_adapt", "action_card_charge","action_card_adapt",
																												"action_card_adapt","action_card_adapt", "action_card_charge","action_card_adapt",
																												"action_card_adapt","action_card_adapt", "action_card_charge","action_card_adapt",
																												"action_card_adapt","action_card_adapt", "action_card_charge","action_card_adapt"));
			
			/* Now everyone gets a token and then the person who got a purple starts by distributing cards
			 * to all other players
			 */
			first.tokenColour = "green";
			second.tokenColour = "red";
			third.tokenColour = "purple";
			forth.tokenColour = "yellow";
			five.tokenColour = "blue";
			
			/* I set the rule engine to know player who got a purple */
			ruleEngine.playerWhoGotPurple = third.getPlayerName();
			
			/* Now player who got a purple has to distribute the cards to all other players */
			if(ruleEngine.didPlayerGetAPurpleToken(third.getTokenColour(), third.getPlayerName())) {
				/* Distribute cards to all other players */
				for(int i = 0; i < players.size(); i++) {
					players.get(i).handCards = ruleEngine.distributeCardsToPlayers(players.get(i).getPlayerName(), deckOfActionCards);
				}
			}
			
			/* now each players hand should contain 8 cards */
			assertEquals(first.handCards.size(), 8);
			assertEquals(second.handCards.size(), 8);
			assertEquals(third.handCards.size(), 8);
			assertEquals(forth.handCards.size(), 8);
			assertEquals(five.handCards.size(), 8);
			
			/* Get the person seated to the left of the person who distributed a card technically who got a purple token 
			 * which should be the second player technically using our algorithm but in reality he can't because he possess
			 * only all action cards */
			String playerWhoWasSupposedToStartTheGame = ruleEngine.startTournamentFirst(players);
			assertEquals(playerWhoWasSupposedToStartTheGame, forth.getPlayerName());
			
			/* We now check if the player who is meant to start possess all action cards which is true */
			boolean hasOnlyAllActionCards = ruleEngine.playerHasOnlyActions(forth.handCards);
			assertTrue(hasOnlyAllActionCards);
			
			/* Since this is true he has to pass his turn to someone else which is the person seated to the left
			 * of the player who was initially meant to start the game which is technically the fifth player
			 */
			String playerToStartNow = ruleEngine.passTurnToNextPlayerIfCurrentPlayerHasOnlyActionCards(players, playerWhoWasSupposedToStartTheGame);
			
			/* The player who should start now should be player 5 since the other player who was meant to start possessed all
			 * action cards
			 */
			assertEquals(playerToStartNow, five.getPlayerName());
			
			
			/* SCENARIO 2: Assuming player had only action and purple cards and then the previous tournament wss purple he has to pass his 
			 * turn to the person seated to the left of him as well
			 */
			
			ArrayList<String>				  			deckOfActionAndColorCards = new ArrayList<String>(Arrays.asList("action_card_adapt","color_card_purple_4_4", "action_card_charge","color_card_purple_4_4",
																														"action_card_adapt","color_card_purple_4_4", "action_card_charge","color_card_purple_4_4",
																														"action_card_adapt","color_card_purple_4_4", "action_card_charge","color_card_purple_4_4",
																														"action_card_adapt","color_card_purple_4_4", "action_card_charge","color_card_purple_4_4",
																														"action_card_adapt","color_card_purple_4_4", "action_card_charge","color_card_purple_4_4",
																														"action_card_adapt","color_card_purple_4_4", "action_card_charge","color_card_purple_4_4",
																														"action_card_adapt","color_card_purple_4_4", "action_card_charge","color_card_purple_4_4",
																														"action_card_adapt","color_card_purple_4_4", "action_card_charge","color_card_purple_4_4",
																														"action_card_adapt","color_card_purple_4_4", "action_card_charge","color_card_purple_4_4",
																														"action_card_adapt","color_card_purple_4_4", "action_card_charge","color_card_purple_4_4",
																														"action_card_adapt","color_card_purple_4_4", "action_card_charge","color_card_purple_4_4",
																														"action_card_adapt","color_card_purple_4_4", "action_card_charge","color_card_purple_4_4",
																														"action_card_adapt","color_card_purple_4_4", "action_card_charge","color_card_purple_4_4",
																														"action_card_adapt","color_card_purple_4_4", "action_card_charge","color_card_purple_4_4",
																														"action_card_adapt","color_card_purple_4_4", "action_card_charge","color_card_purple_4_4"));
			
			ruleEngine.setTournamentColour("purple");
			
			/* Now everyone gets a token and then the person who got a purple starts by distributing cards
			 * to all other players
			 */
			first.tokenColour = "green";
			second.tokenColour = "red";
			third.tokenColour = "blue";
			forth.tokenColour = "yellow";
			five.tokenColour = "purple";
			
			/* I set the rule engine to know player who got a purple */
			ruleEngine.playerWhoGotPurple = first.getPlayerName();
			
			/* Now player who got a purple has to distribute the cards to all other players */
			if(ruleEngine.didPlayerGetAPurpleToken(third.getTokenColour(), third.getPlayerName())) {
				/* Distribute cards to all other players */
				for(int i = 0; i < players.size(); i++) {
					players.get(i).handCards = ruleEngine.distributeCardsToPlayers(players.get(i).getPlayerName(), deckOfActionAndColorCards);
				}
			}
			
			/* now each players hand should contain 8 cards */
			assertEquals(first.handCards.size(), 8);
			assertEquals(second.handCards.size(), 8);
			assertEquals(third.handCards.size(), 8);
			assertEquals(forth.handCards.size(), 8);
			assertEquals(five.handCards.size(), 8);
			
			/* Get the person seated to the left of the person who distributed a card technically who got a purple token 
			 * which should be the second player technically using our algorithm but in reality he can't because he possess
			 * only all action cards */
			String playerWhoWasSupposedToStartTheGame1 = ruleEngine.startTournamentFirst(players);
			assertEquals(playerWhoWasSupposedToStartTheGame1, first.getPlayerName());
			
			/* We now check if the player who is meant to start possess all action cards and purple cards which is true */
			boolean hasOnlyAllActionCardsAndPurpleCard = ruleEngine.playerHasOnlyActions(first.handCards);
			assertTrue(hasOnlyAllActionCardsAndPurpleCard);
			
			/* Since this is true he has to pass his turn to someone else which is the person seated to the left
			 * of the player who was initially meant to start the game which is technically the second player
			 */
			String playerToStartNow1 = ruleEngine.passTurnToNextPlayerIfCurrentPlayerHasOnlyActionCards(players, playerWhoWasSupposedToStartTheGame1);
			
			/* The player who should start now should be player 5 since the other player who was meant to start possessed all
			 * action cards
			 */
			assertEquals(playerToStartNow1, second.getPlayerName());
		}
		
		
		@Test
		public void lastTournamentWasPurpleCannotBePurpleAgain() {
			
			/* Assuming I set last tournament color to be purple */
			ruleEngine.setTournamentColour("purple");
			 
			/* Now I check if the the previous tournament was purple which is true so if it's true it should return true */
			boolean canIStart = ruleEngine.isPreviousTournamentPurple(ruleEngine.getTournamentColour());
			
			/* This should return false because the previous tournament color was purple */
			assertFalse(canIStart);
		}
		
		
		@Test
		public void comingToTheEndOfTheDeck() {
			
			/* Create another deck of cards with size 10 */
			ArrayList<String>				  			deckOfColorCards = new ArrayList<String>(Arrays.asList("color_card_purple_4_2","color_card_purple_4_4", "color_card_purple_4_2","color_card_purple_4_4",
																												"color_card_purple_4_2","color_card_purple_4_4", "color_card_purple_4_2","color_card_purple_4_4",
																												"color_card_purple_4_2","color_card_purple_4_4"));
		
			assertEquals(deckOfColorCards.size(), 10);
			
			/* Assuming I make player 1 get a purple token so he can start the game */
			first.tokenColour	= "green";
			second.tokenColour 	= "red";
			third.tokenColour 	= "purple";
			forth.tokenColour 	= "yellow";
			five.tokenColour  	= "blue"; 
			
			/* Since third player got a purple token he distributes card to everyone but in this case we seated the left of player 3 to get 8 card*/
			if(third.getTokenColour().equals("purple")) {
				players.get(2).handCards = ruleEngine.distributeCardsToPlayers(players.get(2).getPlayerName(), deckOfColorCards);
				players.get(3).handCards = new ArrayList<String>(Arrays.asList("color_card_purple_7_1", "color_card_blue_5_1", 
						"supporter_card_3_8", "color_card_yellow_4_1", "color_card_purple_4_2", "color_card_purple_3_3", "supporter_card_6_2", "supporter_card_6_1"));
				
			}
			forth.myTurnToPlay = true;
			
			/* now the fourth players hand of card should be 8 in number and there should be only 2 cards left in the deck of cards */
			assertEquals(forth.handCards.size(), 8);
			
			/* now he has to pick a card before he can choose a tournament color and then play */
			ruleEngine.drawCard(forth, deckOfColorCards);
			assertEquals(forth.handCards.size(), 9);
			
			
			/* There should be only 1 card left now on the deck because i only made a deck of 10 cards after distributing there were 
			 * 2 cards left and then now he draws a card and then there is only 1 left*/
			assertEquals(deckOfColorCards.size(), 1);
			
			/* he has to pick a tournament color assuming we set it to be purple */
			ruleEngine.setTournamentColour("purple");
			
			/* Then he has to play now */
			ruleEngine.playColorOrSupporterCard(forth, forth.handCards.get(0));
			assertEquals(forth.playerDisplay.size(), 1);
			
			/* His hand of cards now should contain an empty string now*/
			assertTrue(forth.handCards.contains(""));
			
			//check that card was actually removed from his hand
			int cardCount = 0;
			for (String card: forth.handCards) {
				if (!card.equals("")) {
					cardCount++;
				}
			}
			
			assertEquals(cardCount, 8);
			
			/* Now lets assume it's his turn again and he picks another from the deck of cards */
			ruleEngine.drawCard(forth, deckOfColorCards);
		
			
			/* The deck should be zero now since I drew the last card from the deck of cards */
			assertEquals(deckOfColorCards.size(), 0);
			
			/* Check if the deck is empty this should return true because there is zero cards left in the deck of cards */
			assertTrue(ruleEngine.checkDeckOfCardsEmpty(deckOfColorCards));
			assertEquals(forth.handCards.size(), 9);
			
			//he draw a card and it was succesfully put back
			cardCount = 0;
			for (String card: forth.handCards) {
				if (!card.equals("")) {
					cardCount++;
				}
			}
			
			assertEquals(cardCount, 9);
			assertEquals(forth.playerDisplay.size(), 1);
			
			/* If the deck is empty assuming that this player withdraws */
			assertTrue(ruleEngine.Withdraw(forth.getPlayerName(), players, discardPile, tokens, withdrawn));
			
			/* withdrawn player should be of size one now */
			assertEquals(withdrawn.size(), 1);
			
			/* discard pile should be a total of 10 cards since he drew twice and played only once and remember
			 * the deck of cards contained only 10 card
			 */
			System.out.println("Cards in a discard pile");
			for (String s: discardPile) {
				System.out.println("Card is " + s);
			}
			assertEquals(discardPile.size(), 10);
			
			/* Now assuming someone else wants to pick a card i have to do a check to take cards from discard pile and 
			 * put it back into the deck of cards
			 */
			ruleEngine.putBackCardsInDsicardPileIntoDeck(deckOfColorCards, discardPile);
			
			/* lets check to make sure the new deck of card is now 9 again */
			assertEquals(deckOfColorCards.size(), 10);
			
			forth.myTurnToPlay = false;
		}
}
