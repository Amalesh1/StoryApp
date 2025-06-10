package com.storyapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storyapp.model.Story;
import com.storyapp.repository.StoryRepository;

@Service
public class StoryService {
	@Autowired
	private StoryRepository storyRepository;
	
	public String addStory(Story story) {
		storyRepository.save(story);
		return "Added";
	}
	
	public List<Story> showStoriesByTitle(String title) {
		return storyRepository.showAllSoriesByTitle(title);
	}
	
	public List<Story> showStoriesByAuthor(String authorName){
		return storyRepository.showAllStoriesByAuthor(authorName);
	}
}
