package com.storyapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storyapp.model.Story;

public interface StoryRepository extends JpaRepository<Story, Long> {

	List<Story> showAllSoriesByTitle(String title);

	List<Story> showAllStoriesByAuthor(String authorName);

}
