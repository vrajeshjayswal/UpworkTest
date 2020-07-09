package com.example.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.course.pojo.PriceBreakup;
import com.example.course.service.PriceService;
import com.example.course.service.PriceServiceImpl;

import exception.PriceNotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * Rest controller that exposes API end points
 * 
 * @author vrajesh
 *
 */
@Slf4j
@RestController
public class PriceController {

	private PriceService priceService;

	@Autowired
	public PriceController(PriceServiceImpl priceService) {
		this.priceService = priceService;
	}

	/**
	 * returns course price breakup for provided courseId and countryId
	 * 
	 * @param courseId
	 * @param countryId
	 * @return PriceBreakup as Json
	 */
	@GetMapping("/price-breakup/{courseId}/{countryId}")
	PriceBreakup getCoursePriceBreakup(@PathVariable Long courseId, @PathVariable Long countryId) {
		log.info("Enter - PriceController - getCoursePriceBreakup() courseId={} , countryId={}", courseId, countryId);
		try {
			return this.priceService.getCoursePriceBreakup(courseId, countryId);
		} catch (PriceNotFoundException e) {
			log.error("PriceController - getCoursePriceBreakup() - {}", e.toString());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

	/**
	 * returns course base price for provided courseId and countryId
	 * 
	 * @param courseId
	 * @param countryId
	 * @return basePrise as Json
	 */
	@GetMapping("/base-price/{courseId}/{countryId}")
	PriceBreakup getCourseBasePrice(@PathVariable Long courseId, @PathVariable Long countryId) {
		log.info("Enter - PriceController - getCourseBasePrice() courseId={} , countryId={}", courseId, countryId);
		try {
			return this.priceService.getCourseBasePrice(courseId, countryId);
		} catch (PriceNotFoundException e) {
			log.error("PriceController - getCourseBasePrice() - {}", e.toString());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

	/**
	 * returns course whole price for provided courseId and countryId
	 * 
	 * @param courseId
	 * @param countryId
	 * @return totalPrice as Json
	 */
	@GetMapping("/whole-price/{courseId}/{countryId}")
	PriceBreakup getCourseWholePrice(@PathVariable Long courseId, @PathVariable Long countryId) {
		log.info("Enter - PriceController - getCourseWholePrice() courseId={} , countryId={}", courseId, countryId);
		try {
			return this.priceService.getCourseWholePrice(courseId, countryId);
		} catch (PriceNotFoundException e) {
			log.error("PriceController - getCourseWholePrice() - {}", e.toString());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
}
