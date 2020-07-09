package com.example.course.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.course.entity.Price;

/**
 * JPA repository for Price entity
 * 
 * @author vrajesh
 *
 */
public interface PriceRepository extends JpaRepository<Price, Long> {

	/**
	 * returns Price based on provided courseId and countryId
	 * 
	 * @param courseId
	 * @param countryId
	 * @return Price
	 */
	Optional<Price> findByCourse_CourseIdAndCountry_CountryId(Long courseId, Long countryId);
}
