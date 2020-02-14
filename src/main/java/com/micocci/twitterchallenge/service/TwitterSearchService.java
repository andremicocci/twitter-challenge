package com.micocci.twitterchallenge.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micocci.twitterchallenge.model.Tweet;
import com.micocci.twitterchallenge.repository.TwitterRepository;

import twitter4j.Query;
import twitter4j.Query.ResultType;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Classe de negocio responsavel por interagir com o Controller, API Twitter e com o Repository (Mongo)
 * @author andremicocci@gmail.com
 *
 */
@Service
public class TwitterSearchService {
	
	private static final Logger log = LoggerFactory.getLogger(TwitterSearchService.class);

	@Autowired
	private TwitterRepository repository;
	
	@Value("${twitter4j.oauth.consumerKey}")
	private String twitterOAuthConsumerKey;
	
	@Value("${twitter4j.oauth.consumerSecret}")
	private String twitterOAuthConsumerSecret;
	
	@Value("${twitter4j.oauth.accessToken}")
	private String twitterOAuthAccessToken;

	@Value("${twitter4j.oauth.accessTokenSecret}")
	private String twitterOAuthAccessTokenSecret;
	
	/**
	 * Efetua a Busca de Tweets atraves de uma query
	 * @param query - Hashtags no formato %23param1+%23param2
	 * @param type - Tipo de resultado - popular, mixed, recent(default)
	 * @param count - Quantidade de registros a serem retornados, 100 (default)
	 * @return List<Tweet>
	 * @throws TwitterException
	 * @throws JsonProcessingException
	 */
	public List<Tweet> searchByQuery(final String query, final ResultType type, int count) throws TwitterException, JsonProcessingException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(false).setOAuthConsumerKey(twitterOAuthConsumerKey)
				.setOAuthConsumerSecret(twitterOAuthConsumerSecret)
				.setOAuthAccessToken(twitterOAuthAccessToken)
				.setOAuthAccessTokenSecret(twitterOAuthAccessTokenSecret)
				.setIncludeEntitiesEnabled(false)
				.setTweetModeExtended(true);
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitterService = tf.getInstance();
		
		List<String> hashTags = Arrays.asList(query.split("\\+"));
		List<Tweet> tweets = new ArrayList<Tweet>();
		
		for (String hashTag : hashTags) {
			log.info("===> Calling with HASHTAG {}", hashTag);
			Query twitterServiceQuery = new Query(hashTag);
			twitterServiceQuery.setCount(count);
			twitterServiceQuery.setResultType(type);
			QueryResult result = twitterService.search(twitterServiceQuery);
		    
			for (Status status : result.getTweets()) {
		    	Tweet tweet = new Tweet();
		    	tweet.setId(status.getId());
		    	tweet.setHashTag(hashTag);
		    	tweet.setText(status.getText());
		    	tweet.setUserId(status.getUser().getId());
		    	tweet.setUserName(status.getUser().getName());
		    	tweet.setUserFollowersCount(status.getUser().getFollowersCount());
		    	tweet.setPayloadReturned(new ObjectMapper().writeValueAsString(status));
		    	tweets.add(tweet);
		    }
	    }
		repository.saveAll(tweets);
	    return tweets;
	}
	
	/**
	 * Efetua a busca dos 5 Usuarios com mais seguidores
	 * @return List<Tweet>
	 */
	public List<Tweet> top5Users() {
		return repository.findTop5UserNameDistinctByOrderByUserFollowersCountDesc();
	}
}
