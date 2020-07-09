package com.example.course.service;

import com.example.course.pojo.PriceBreakup;

import exception.PriceNotFoundException;

/**
 * Interface defines methods to get different prices for the course
 * 
 * @author vrajesh
 *
 */
public interface PriceService {

	PriceBreakup getCourseBasePrice(Long courseId, Long countryId) throws PriceNotFoundException;

	PriceBreakup getCourseWholePrice(Long courseId, Long countryId) throws PriceNotFoundException;

	PriceBreakup getCoursePriceBreakup(Long courseId, Long countryId) throws PriceNotFoundException;
}
