package com.micocci.twitterchallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.micocci.twitterchallenge.model.Tweet;
import com.micocci.twitterchallenge.service.TwitterSearchService;

import twitter4j.Query.ResultType;
import twitter4j.TwitterException;

@RestController
public class TweetsController {
	
	private TwitterSearchService twitterSearchService;
	
    @Autowired
    public TweetsController(final TwitterSearchService twitterSearchService) {
        this.twitterSearchService = twitterSearchService;
    }	

	@GetMapping("/api/v1/tweets")
	@ResponseBody
	public List<Tweet> tweets(@RequestParam(value = "q") String query,
							 @RequestParam(value = "type", defaultValue = "recent") ResultType type,
							 @RequestParam(value = "count", defaultValue = "100") int count) throws TwitterException, JsonProcessingException {
		return twitterSearchService.searchByQuery(query, type, count);
	}
	
	@GetMapping("api/v1/tweets/top5Users")
	@ResponseBody
	public List<Tweet> top5() throws TwitterException, JsonProcessingException {
		return twitterSearchService.top5Users();
	}
	
}