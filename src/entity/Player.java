package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import config.Config;

public class Player implements Cloneable{
	
	public 	String 					tokenColour;
	private String 					playerName;
	public  int    					totalCardValue;
	public  ArrayList<String> 		playerTokens;
	public 	ArrayList<String> 		handCards;
	public 	ArrayList<String>       playerDisplay;
	public boolean 					myTurnToPlay;
	public boolean 					IgotANewCard;
	public int 						holdKnockDownInd;
	public boolean					hasShield;
	public boolean					oneCardPlayed;
	public boolean 					stunnedPlayedOnMe;
	
	public Player() {
		IgotANewCard 				= false;
		myTurnToPlay 				= false;
		this.playerName  			= "computer"; 
		totalCardValue   			= 0;
		playerTokens     			= new ArrayList<String>(Config.numberOfTokens);
		handCards					= new ArrayList<String>(Config.numberOfCardsForEachPlayer);
		playerDisplay				= new ArrayList<String>();
	}
	

	public Player(String playerName) {
		hasShield					= false;
		stunnedPlayedOnMe			= false;
		oneCardPlayed				= false;
		this.playerName  			= playerName; 
		totalCardValue   			= 0;
		holdKnockDownInd			= 0;
		playerTokens     			= new ArrayList<String>(Config.numberOfTokens);
		handCards					= new ArrayList<String>(Config.numberOfCardsForEachPlayer);
		playerDisplay				= new ArrayList<String>();
	}
	
	public Player clone(Player oldPlayer) {
		this.playerName = oldPlayer.getPlayerName();
		this.hasShield = oldPlayer.hasShield;
		this.stunnedPlayedOnMe = oldPlayer.stunnedPlayedOnMe;
		this.oneCardPlayed = oldPlayer.oneCardPlayed;
		this.totalCardValue = oldPlayer.totalCardValue;
		this.holdKnockDownInd = oldPlayer.holdKnockDownInd;
		this.tokenColour = oldPlayer.tokenColour;
		this.myTurnToPlay = oldPlayer.myTurnToPlay;
		this.IgotANewCard = oldPlayer.IgotANewCard;
		for (String s: oldPlayer.playerTokens) {
			this.playerTokens.add(s);
		}
		
		for (String card: oldPlayer.handCards) {
			this.handCards.add(card);
		}
		
		for (String card: oldPlayer.playerDisplay) {
			this.playerDisplay.add(card);
		}
		
		return this;
	}

	public int getTotalCardvalue() {
		return totalCardValue;
	}

	public String getPlayerName() {
		return playerName;
	}
	
	public String getTokenColour() {
		return tokenColour;
	}
	
	public void resetTotalCardValue() {
		totalCardValue = 0;
	}
	
	public void resetDisplay() {
		playerDisplay = new ArrayList<String>();
	}
	
	
}
