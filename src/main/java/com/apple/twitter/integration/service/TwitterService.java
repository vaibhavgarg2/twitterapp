package com.apple.twitter.integration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import com.apple.twitter.integration.model.CustomTweet;

/**
 * Service class to search for tweets 
 * 
 * @author vaibhavgarg
 *
 */
@Service
public class TwitterService {
	
	@Autowired
	private Twitter twitter;
	
	/**
	 * Method to search tweets by tags; integrates with Twitter 
	 * 
	 * @param searchText - Text to be searched for
	 * @param numResults - Number of results to be returned
	 * @return - List<CustomTweet> - Returns List of CustomTweet objects
	 */
	public List<CustomTweet> searchTweetsByTag(String searchText, int numResults) {
		List<CustomTweet> customTweetList = new ArrayList<CustomTweet>();
		
		SearchResults searchResults = twitter.searchOperations().search(searchText, numResults);
		List<Tweet> tweetsList = searchResults.getTweets();
		customTweetList = tweetsList.stream().map(tweet -> {
			return new CustomTweet.CustomTweetBuilder().setId(tweet.getId()).setFromUser(tweet.getFromUser()).setFromUserId(tweet.getFromUserId())
					.setCreatedAt(tweet.getCreatedAt()).setRetweetCount(tweet.getRetweetCount()).setText(tweet.getText()).build();
		}).collect(Collectors.toList());
		return customTweetList;
	}
}
