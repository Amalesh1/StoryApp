package com.storyapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storyapp.model.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

	List<Story> findByGenre(String genre);

	List<Story> findByStoryName(String storyName);

	Story findByStoryId(long id);

}
