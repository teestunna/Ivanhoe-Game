package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import userinterface.StartUp;


public class StartGameController extends JFrame {
	
	StartUp view;
	
	public StartGameController(String name) {
		super(name);
		
		view = new StartUp(name);
		getContentPane().add(view);
		
		view.startGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LogInWindowController loginCred = new LogInWindowController("Welcome to Ivanhoe Warriors");
				loginCred.setVisible(true);
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