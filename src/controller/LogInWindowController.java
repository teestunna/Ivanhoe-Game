package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import networking.ClientApp;
import userinterface.LoginWindow;
import userinterface.MyBoard;


public class LogInWindowController extends JFrame {
	
	LoginWindow view;
	
	public LogInWindowController(String name) {
		super(name);
		
		view = new LoginWindow(name);
		getContentPane().add(view);
		
		view.startGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//view.playerIPLabel.setText("localhost");
				String playerName = view.playernameLabel.getText().trim();
				String host       = view.playerIPLabel.getText().trim();
				int    port       = Integer.parseInt(view.playerPortLabel.getText().trim());
			
				
				/* Now create a new client to connect */
				new ClientApp(host, port, playerName);
				
				closeThisWindow();
			}	
		});
		
		setSize(1280,800);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void closeThisWindow() {
		this.setVisible(false);
	}
}