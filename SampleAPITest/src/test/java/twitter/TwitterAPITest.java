package twitter;




import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import twitter4j.TwitterException;

public class TwitterAPITest {

	/**
	 * In order run this jUnit test you need to configure your API details in the twitter4j.properties
	 */
	public String tweet = null;
	 
	@BeforeClass
	public void setUpData() {
		 
		 Random random = new Random(); 
		 int ranNum = random.nextInt(1000);
		 tweet="TestData"+ranNum;
		
		
	}

	@Test(description = "Test Create Tweet", priority = 1)
	public void verifyCreateTweet() throws TwitterException {
		String text = TwitterAPIConnect.createTweet(tweet);
		assertEquals(tweet, text);
	}
	
	@Test(description = "Test not able to create tweet", priority = 2)
	public void verifyDuplicateTweet() throws TwitterException {			
	 boolean thrown = false;
		try {
			  TwitterAPIConnect.createTweet(tweet);
			  } catch (Exception e) {
			    thrown = true;
			  }

			  assertTrue(thrown);
	}
	@Test(description = "Test recently created is able to fetch", priority = 3)
	public void verifyGetTweets() throws TwitterException {
		List<String> statuses = TwitterAPIConnect.getTimeLine();
		List<String> expectedStatuses = new ArrayList<String>();
		expectedStatuses.add(tweet);
		assertTrue(expectedStatuses.contains(statuses.get(0)));	
	}
	
	@Test(description = "Test able to get 20 tweets", priority = 4)
	public void verifyNoOfTweets() throws TwitterException {
		List<String> statuses = TwitterAPIConnect.getTimeLine();
		assertTrue(statuses.size()==20);	
	}
	
	@Test(dataProvider ="test-data", description = "Test recently created is able to fetch", priority = 5)
	public void verifyMultipleTweetsAvailable(String tweets) throws TwitterException {
		List<String> statuses = TwitterAPIConnect.getTimeLine();
		assertTrue(statuses.contains(tweets));	
	}
  	@DataProvider(name = "test-data")
  	public Object[][] dataProvFunc(){
        	return new Object[][]{
              	{"TestData830"},{"TestData1"}
        	};
  	}

}