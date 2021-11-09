package com.service;
import java.util.ArrayList;
import java.util.List;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.advices.UnknownDeveloperException;
import com.advices.UnknownFeedException;
import com.model.Feeds;
import com.repository.IFeedRepository;
@Service
public class IFeedService {
	@Autowired
	IFeedRepository repo;
	
public  Feeds addFeed(Feeds feed)
{
	 repo.save(feed);
	 return feed;

}
public List<Feeds>editFeed(Feeds feed)
{
	int id=feed.getFeedId();
	Supplier s1=()->new ResourceNotFoundException("Feeds Does not exist in the database");
	Feeds feed1=repo.findById(id).orElseThrow();
	feed.setKeyword(feed.getKeyword());
	repo.save(feed);
	return (List<Feeds>) feed1;
	
	
}


 public Feeds likeFeed(int feedId) throws UnknownFeedException { 
	 Feeds feed=repo.getById(feedId);
	 int c=feed.getRelevance();
	 //System.out.println(c);
	 feed.setRelevance(c+1);
	 //System.out.println(feed.getRelevance());
	  
	  return feed; 
	  } 
  
public  Feeds getFeed(int feedId)throws UnknownFeedException {
	Feeds feed=repo.getById(feedId);
	return feed ;
	
	  
  
  }
 
 public Feeds removeFeed(int feedId) throws UnknownFeedException {
Feeds feed=repo.getById(feedId);
		repo.deleteById(feedId);
		return feed;
		
 }
 public Feeds deleteFeed(Feeds feed) {
	 int id=feed.getFeedId();
	 Feeds feed1=repo.getById(id);	
		repo.delete(feed);
		return feed1;
	}

	 public List<Feeds>getFeedsByDeveloper(int devId)throws UnknownDeveloperException {
		 Feeds feeds=repo.findById(devId).orElseThrow();
		 List<Feeds> feed=new ArrayList<>();
		 feed.add(feeds);
		return feed;
		 
	 }

		
		  public List<Feeds>getFeedsByKeyword(String keyword)
		  { 
			  if(keyword!=null)
			  {
		  return repo.findByKeyword(keyword); 
		  } 
			  return repo.findAll(); 
			  }
		 
	  public List<Feeds>getFeedsByTopic(String topic) {
	  List<Feeds>lfeed1=repo.findByTopic(topic); 
	  return lfeed1; 
	  
	}
	 
}


