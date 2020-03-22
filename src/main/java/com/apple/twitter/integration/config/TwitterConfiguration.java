package com.apple.twitter.integration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

/**
 * Configuration class for setting Twitter configurations 
 * 
 * @author vaibhavgarg
 *
 */
@Configuration
public class TwitterConfiguration {

	@Value("${spring.social.twitter.appId}")
	private String consumerKey;
	
	@Value("${spring.social.twitter.appSecret}")
	private String consumerSecret;
	
	@Value("${twitter.access.token}")
	private String accessToken;
	
	@Value("${twitter.access.token.secret}")
	private String accessTokenSecret;
	
	@Bean
	TwitterTemplate getTwitterTemplate(){
		TwitterTemplate template = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
		return template;
	}
	
}