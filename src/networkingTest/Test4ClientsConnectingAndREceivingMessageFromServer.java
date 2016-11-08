package networkingTest;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import config.Config;
import networking.ClientApp;
import networking.ServerApp;

public class Test4ClientsConnectingAndREceivingMessageFromServer {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("@setUpBeforeClass(): TestNetworking\n\n");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("@After(): NetworkingTest\n\n");
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("@tearDown(): NetworkingTest\n\n");
	}

	@Test
	public void  test4ClientsAndReceiveMessageFromServer() throws IOException, ClassNotFoundException {
		
		int     PORT    				  = 3004;
		String  host    				  = "127.0.0.1";
		String  messageToSendToClient1    = "Welcome client1";
		String  messageToSendToClient2    = "Welcome client2";
		String  messageToSendToClient3    = "Welcome client3";
		String  messageToSendToClient4    = "Welcome client4";
		
		boolean player1ReceivedAMessage   = false;
		boolean player2ReceivedAMessage   = false;
		boolean player3ReceivedAMessage   = false;
		boolean player4ReceivedAMessage   = false;
		
		ServerApp appServer = new ServerApp(PORT, 4);
		
		ClientApp client1 = new ClientApp(host ,PORT, "Tope");
		appServer.sendToOneClient(messageToSendToClient1, client1.getID());
		appServer.resetPort();
		
		ClientApp client2 = new ClientApp(host ,PORT, "Aziza");
		appServer.sendToOneClient(messageToSendToClient2, client2.getID());
		appServer.resetPort();
		
		ClientApp client3 = new ClientApp(host, PORT, "chuboy");
		appServer.sendToOneClient(messageToSendToClient3, client3.getID());
		appServer.resetPort();
		
		ClientApp client4 = new ClientApp(host, PORT, "chuboy");
		appServer.sendToOneClient(messageToSendToClient4, client4.getID());
		appServer.resetPort();
		
		if(client1.messageReceivedFromServer().equals("")) {
			player1ReceivedAMessage = false;
		}
		
		else {
			player1ReceivedAMessage = true;
		}
		
		
		if(client2.messageReceivedFromServer().equals("")) {
			player2ReceivedAMessage = false;
		}
		
		else {
			player2ReceivedAMessage = true;
		}
		
		
		if(client3.messageReceivedFromServer().equals("")) {
			player3ReceivedAMessage = false;
		}
		
		else {
			player3ReceivedAMessage = true;
		}
		
		
		if(client4.messageReceivedFromServer().equals("")) {
			player4ReceivedAMessage = false;
		}
		
		else {
			player4ReceivedAMessage = true;
		}
		
		assertTrue(player1ReceivedAMessage);
		assertTrue(player2ReceivedAMessage);
		assertTrue(player3ReceivedAMessage);
		assertTrue(player4ReceivedAMessage);
		
		appServer.remove(client1.getID());
		appServer.remove(client2.getID());
		appServer.remove(client3.getID());
		appServer.remove(client4.getID());
	}

}
