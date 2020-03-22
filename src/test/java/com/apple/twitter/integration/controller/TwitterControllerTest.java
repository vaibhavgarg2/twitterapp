package com.apple.twitter.integration.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.apple.twitter.integration.model.CustomTweet;
import com.apple.twitter.integration.service.TwitterService;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes=com.apple.twitter.integration.controller.TwitterController.class)
public class TwitterControllerTest {
	@Autowired
	private MockMvc mockMVC;
	@MockBean
	private TwitterService twitterServiceMock;

	@Test
	public void searchTweet() throws Exception {
		CustomTweet tweet1 = new CustomTweet.CustomTweetBuilder().setId(1234L).setCreatedAt(new Date()).setFromUser("user1").setFromUserId(101L).
				setRetweetCount(20).setText("iphone11 provides best camera experience").build();
		CustomTweet tweet2 = new CustomTweet.CustomTweetBuilder().setId(6789L).setCreatedAt(new Date()).setFromUser("user2").setFromUserId(102L).
				setRetweetCount(4).setText(" apple iphones are coolest smartphones").build();
		CustomTweet tweet3 = new CustomTweet.CustomTweetBuilder().setId(1947L).setCreatedAt(new Date()).setFromUser("user3").setFromUserId(103L).
				setRetweetCount(15).setText(" apple planning to launch new iphone soon").build();
		CustomTweet tweet4 = new CustomTweet.CustomTweetBuilder().setId(4790L).setCreatedAt(new Date()).setFromUser("user4").setFromUserId(104L).
				setRetweetCount(89).setText("dont use plastic").build();
		CustomTweet tweet5 = new CustomTweet.CustomTweetBuilder().setId(3456L).setCreatedAt(new Date()).setFromUser("user5").setFromUserId(105L).
				setRetweetCount(168).setText("save water").build();

		List<CustomTweet> tweetsList = new ArrayList<CustomTweet>();
		tweetsList.add(tweet1);
		tweetsList.add(tweet3);
		when(twitterServiceMock.searchTweetsByTag("iphone",2)).thenReturn(tweetsList);
		mockMVC.perform(get("/tweets?tag=iphone&num=2")).andExpect(model().attribute("tweetResponse", tweetsList))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testInvalidParameters() throws Exception{
		mockMVC.perform(get("/tweets?tag=abc")).andExpect(status().is4xxClientError());
	}
	
}
