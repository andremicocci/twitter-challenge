package com.micocci.twitterchallenge.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.micocci.twitterchallenge.model.Tweet;

/**
 * Repositorio MongoDB
 * @author andremicocci@gmail.com
 *
 */
public interface TwitterRepository extends MongoRepository<Tweet, String> {
	
	/**
	 * Retorna os 5 usuarios com mais seguidores
	 * @return
	 */
	List<Tweet> findTop5UserNameDistinctByOrderByUserFollowersCountDesc();
}