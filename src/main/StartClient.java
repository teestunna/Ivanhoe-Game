

package main;

import controller.StartGameController;

public class StartClient {
	
	public static void main(String[] args) {
		StartGameController startGame = new StartGameController("Welcome to Ivanhoe Warriors");
		startGame.setVisible(true);
	}

}

