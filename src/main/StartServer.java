
package main;

import controller.ServerControllerStartUp;

public class StartServer {

	public static void main(String[] args) {
		ServerControllerStartUp startServer = new ServerControllerStartUp("Welcome to Ivanhoe Warriors Server");
		startServer.setVisible(true);
	}
}
