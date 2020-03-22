package com.apple.twitter.integration.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.apple.twitter.integration.model.CustomTweet;
import com.apple.twitter.integration.service.TwitterService;


/**
 * Controller class to handle tweet search requests
 * 
 * @author vaibhavgarg
 *
 */
@RestController
@Validated
public class TwitterController {

	@Autowired
	private TwitterService twitterService;

	@RequestMapping("/")
	public ModelAndView hello() {
		String helloWorldMessage = "Hello Twitter from Apple!";
		return new ModelAndView("index", "message", helloWorldMessage);
	}

	/**
	 * Method to search for tweets based on tag and number of results
	 * 
	 * @param tag - search tag
	 * @param num - number of results to be searched
	 * @return
	 * @throws ExecutionException - Exception during execution
	 */
	@GetMapping(value = "/tweets", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ModelAndView searchTweets(@RequestParam @NotBlank(message = "Please provide a search text value") String tag,
			@RequestParam @Max(value = 200) @Min(value = 1) @NotEmpty(message = "Please provide the result count") String num)
			throws ExecutionException {
		int resultCount = Integer.parseInt(num);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		
		try {
			List<CustomTweet> tweetList = twitterService.searchTweetsByTag(tag, resultCount);
			modelAndView.setStatus(HttpStatus.OK);
			modelAndView.addObject("tweetResponse", tweetList);
		} catch(Exception e) {
			modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			modelAndView.addObject("errorResponse", "An error occured!!Please try again after sometime");
		}
		
		return modelAndView;
		
	}

}