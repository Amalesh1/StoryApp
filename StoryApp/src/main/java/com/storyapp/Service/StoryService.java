package com.storyapp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storyapp.Exceptions.StoryNotFoundException;
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
		return storyRepository.findByStoryName(title);
	}

	public List<Story> showStoriesByAuthor(String genre) {
		return storyRepository.findByGenre(genre);
	}

	public String deleteStory(long id) throws StoryNotFoundException {

		Optional<Story> story = storyRepository.findById(id);
		if (story == null) {
			throw new StoryNotFoundException("Story Not Found");
		}
		storyRepository.deleteById(id);
		return "Deleted";
	}

	public String updateStory(long id, Story story) {
		Story story2 = storyRepository.findByStoryId(id);
		story2.setDescription(story.getDescription());
		storyRepository.save(story2);
		return "Updated";
	}

	public List<Story> showStoriesOfUser(long userId) {
		// TODO Auto-generated method stub
		return storyRepository.findAllByUserId(userId);
	}

	public List<Story> showAllStories() {
		// TODO Auto-generated method stub
		return storyRepository.findAll();
	}
}
