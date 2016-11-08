package networkingTest;

	import static org.junit.Assert.*;

	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.ObjectInputStream;

	import org.junit.After;
	import org.junit.AfterClass;
	import org.junit.Before;
	import org.junit.BeforeClass;
	import org.junit.Test;

	import config.Config;
	import networking.ClientApp;
	import networking.ServerApp;

	public class ConnectionTest {
		
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
		public void testConnectionAnd1Client() {
			
			int    PORT    = 3000;
			String host    = "127.0.0.1";
			
			ServerApp appServer = new ServerApp(PORT, 1);
			
			ClientApp client = new ClientApp(host ,PORT, "Tope");
			assertTrue(appServer.connected);
		}

}
