package com.micocci.twitterchallenge.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.micocci.twitterchallenge.model.Tweet;
import com.micocci.twitterchallenge.service.TwitterSearchService;

import twitter4j.Query.ResultType;

@ExtendWith(MockitoExtension.class)
public class TweetsControllerTest {

	private TweetsController tweetsController;

	@Mock
	private TwitterSearchService twitterSearchService;

	@BeforeEach
	public void setUp() throws Exception {
		tweetsController = new TweetsController(twitterSearchService);
	}

	@Test
	public void shouldReturnAListofStatus() throws Exception {
		String query = "%23AWS";
		ResultType type = ResultType.recent;
		int count = 100;
		List<Tweet> statuses = tweetsController.tweets(query, type, count);
		assertThat(statuses);
	}
}
