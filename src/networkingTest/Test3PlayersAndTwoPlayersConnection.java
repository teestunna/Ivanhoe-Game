package networkingTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import config.Config;
import networking.ClientApp;
import networking.ServerApp;

public class Test3PlayersAndTwoPlayersConnection {

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
	public void test3Clients() {
		
		int    PORT    = 3003;
		String host    = "127.0.0.1";
		
		ServerApp appServer = new ServerApp(PORT, 3);
		
		ClientApp client1 = new ClientApp(host ,PORT, "Tope");
		assertTrue(appServer.connected);
		
		ClientApp client2 = new ClientApp(host ,PORT, "Aziza");
		assertTrue(appServer.connected);
		
		ClientApp client3 = new ClientApp(host ,PORT, "Chuboy");
		assertTrue(appServer.connected);
		
	}
	
	@Test
	public void test2Clients() {
		
		int    PORT    = 8000;
		String host    = "127.0.0.1";
		
		ServerApp appServer = new ServerApp(PORT, 2);
		
		ClientApp client1 = new ClientApp(host ,PORT, "Tope");
		assertTrue(appServer.connected);
		
		ClientApp client2 = new ClientApp(host ,PORT, "Aziza");
		assertTrue(appServer.connected);
		
	}

}
