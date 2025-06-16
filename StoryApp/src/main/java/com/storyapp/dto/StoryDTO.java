package com.storyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoryDTO {
    private long storyId;
    private String storyName;
    private String genre;
    private String description;
    private String authorName;

    // Constructors, getters, setters
}
