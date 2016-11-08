package networkingTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ConnectionTest.class,
	TestMaximumNumberOfPlayers.class,
	Test3ClientsConnectingAndReceivingMessageFromServer.class,
	Test4ClientsConnectingAndREceivingMessageFromServer.class,
})

public class NetworkingTestSuite {

}
