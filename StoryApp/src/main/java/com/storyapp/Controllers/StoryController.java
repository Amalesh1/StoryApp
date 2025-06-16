package com.storyapp.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.storyapp.Service.StoryService;
import com.storyapp.dto.StoryDTO;
import com.storyapp.model.Story;
import com.storyapp.model.Users;
import com.storyapp.repository.UserRepository;

@Controller
public class StoryController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StoryService storyService;
	
	@PostMapping("/write/story")
	public String writeStory(@ModelAttribute Story story){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		try {
			long userId =(long) userRepository.findByEmail(authentication.getName()).getUserId();
			story.setUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		storyService.addStory(story);
		return "home";
	}
	
	@GetMapping("/{userId}/stories")
	public ResponseEntity<List<Story>> userStories(@PathVariable long userId){
		List<Story> stories = storyService.showStoriesOfUser(userId);
		return new ResponseEntity<List<Story>> (stories,HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/write/story")
	public String showStoryForm(Model model, Authentication authentication) {
	    long userId = userRepository.findByEmail(authentication.getName()).getUserId();

	    Story story = new Story();
	    story.setUserId(userId);

	    model.addAttribute("story", story);
	    return "create-story"; // Thymeleaf template
	}
	
	@GetMapping("/stories/read")
	public String getStories(Model model) {
		List<Story> stories = storyService.showAllStories();
		List<StoryDTO> storyDTOs = new ArrayList<>();

		for (Story story : stories) {
		    Users user = userRepository.findById(story.getUserId()).orElse(null);
		    String authorName = (user != null) ? user.getName() : "Unknown";

		    StoryDTO dto = new StoryDTO();
		    dto.setStoryId(story.getStoryId());
		    dto.setStoryName(story.getStoryName());
		    dto.setGenre(story.getGenre());
		    dto.setDescription(story.getDescription());
		    dto.setAuthorName(authorName);

		    storyDTOs.add(dto);
		}

		model.addAttribute("stories", storyDTOs);
		return "stories";

	}

}
