package twitter;


/**
 * This is class methods provides connection to Twitter APIs,Creates tweet and Gets timeline
 */


import java.util.List;
import java.util.stream.Collectors;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class TwitterAPIConnect {

	public static Twitter getTwitterinstance() throws TwitterException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("5R8BJQa0XjIaPzYhmiAlKqsF5")
		.setOAuthConsumerSecret("XzGiNHQn5XYwIlfohXRildt7tPKIunWNte1QEyttgnSDaAaXU8")
		.setOAuthAccessToken("1367507704504221696-Z3IU3n2akz1LIrzkG2Wb5Ysn0dWlLF")
		.setOAuthAccessTokenSecret("0NJEVtxF7pfGROPnjxdEGhDnWBkgLzJHgN6YmUNF2gSkA");
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter4j.Twitter twitter =  tf.getInstance();
		return twitter;
	}
	public static String createTweet(String tweet) throws TwitterException {
		Twitter twitter = getTwitterinstance();
		Status status = twitter.updateStatus(tweet);
	        return status.getText();
	}
	
	public static List<String> getTimeLine() throws TwitterException {
		Twitter twitter = getTwitterinstance();
		List<Status> statuses = twitter.getHomeTimeline();
		return statuses.stream().map(
				item -> item.getText()).collect(
						Collectors.toList());
	}



}
