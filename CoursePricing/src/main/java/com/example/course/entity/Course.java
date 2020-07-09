package com.example.course.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity class that represents database table - Course
 * 
 * @author vrajesh
 *
 */
@Entity
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Course {

	@Id
	@GeneratedValue
	private Long courseId;

	@NonNull
	private String title;

	@NonNull
	private String description;

	@NonNull
	@Enumerated(EnumType.STRING)
	private PaymentStrategy paymentStrategy;

	@OneToMany(mappedBy = "course")
	private Set<Price> price;
}
