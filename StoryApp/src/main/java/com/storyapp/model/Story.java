package com.storyapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Story {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long storyId;
	private String storyName;
	private long userId;
	private String genre;
	private String description;
}
