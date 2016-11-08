package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;

import config.Config;
import networking.ServerApp;
import userinterface.ServerUI;
import userinterface.StartUp;


public class ServerControllerStartUp extends JFrame {

	ServerUI view;

	private static Boolean   done      = Boolean.FALSE;
	private static ServerApp appServer = null;
	private static boolean   connected = false;
	int    numberOfPlayers				= 0;

	public ServerControllerStartUp(String name) {
		super(name);

		view = new ServerUI(name);
		getContentPane().add(view);

		view.startText.setText("START");
		view.startText.setEditable(false);

		view.runServer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String startTextField = view.startText.getText().trim();
				int    portNumber     = Integer.parseInt(view.portText.getText().trim());
				numberOfPlayers = Integer.parseInt(view.numberOfPlayersTextField.getText().trim());
				System.out.println("Number of players specified is: " + numberOfPlayers);

				if (startTextField.equalsIgnoreCase("START") && ((numberOfPlayers >1)  && ( numberOfPlayers < 6)))					
				{	
					System.out.println("Starting server ...");
					appServer = new ServerApp(portNumber, numberOfPlayers);
					view.messageRestriction.setText("Server has now started....");
					view.messageRestriction.setVisible(true);
				}

				else {
					view.messageRestriction.setVisible(true);
				}


			}	
		});

		setSize(1280,800);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}