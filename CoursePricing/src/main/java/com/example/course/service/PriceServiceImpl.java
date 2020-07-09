package com.example.course.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import com.example.course.entity.Price;
import com.example.course.pojo.PriceBreakup;
import com.example.course.repository.PriceRepository;

import exception.PriceNotFoundException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Provide implementation of PriceService methods
 * 
 * @author vrajesh
 *
 */
@Slf4j
@RefreshScope
@Component
@NoArgsConstructor
public class PriceServiceImpl implements PriceService {

	private PriceRepository priceRepository;

	//paymentStrategy value will be read from properties file through configuration server
	//if config-server is down or this property not available then it will set default value as ONETIME
	@Value("${paymentStrategy:ONETIME}")
	private String paymentStrategy;

	@Autowired
	public PriceServiceImpl(PriceRepository priceRepository) {
		this.priceRepository = priceRepository;
	}

	@Override
	public PriceBreakup getCourseBasePrice(Long courseId, Long countryId) throws PriceNotFoundException {
		log.info("Enter - PriceServiceImpl - getCourseBasePrice() courseId={} , countryId={}", courseId, countryId);
		Optional<Price> priceOptional = this.priceRepository.findByCourse_CourseIdAndCountry_CountryId(courseId,
				countryId);
		if (priceOptional.isPresent()) {
			Price price = priceOptional.get();
			PriceBreakup priceBreakup = new PriceBreakup();
			priceBreakup.setBasePrice(price.getBasePrice());
			log.info("Exit - PriceServiceImpl - getCourseBasePrice()");
			return priceBreakup;
		} else {
			throw new PriceNotFoundException(courseId, countryId);
		}
	}

	@Override
	public PriceBreakup getCourseWholePrice(Long courseId, Long countryId) throws PriceNotFoundException {
		log.info("Enter - PriceServiceImpl - getCourseWholePrice() courseId={} , countryId={}", courseId, countryId);
		Optional<Price> priceOptional = this.priceRepository.findByCourse_CourseIdAndCountry_CountryId(courseId,
				countryId);
		if (priceOptional.isPresent()) {
			Price price = priceOptional.get();
			PriceBreakup priceBreakup = new PriceBreakup();
			BigDecimal totalPrice = price.getBasePrice().add(price.getTaxes()).add(price.getOtherCharges())
					.add(price.getCountry().getConversionFees());
			priceBreakup.setTotalPrice(totalPrice);
			log.info("Exit - PriceServiceImpl - getCourseWholePrice()");
			return priceBreakup;
		} else {
			throw new PriceNotFoundException(courseId, countryId);
		}
	}

	@Override
	public PriceBreakup getCoursePriceBreakup(Long courseId, Long countryId) throws PriceNotFoundException {
		log.info("Enter - PriceServiceImpl - getCoursePriceBreakup() courseId={} , countryId={}", courseId, countryId);
		Optional<Price> priceOptional = this.priceRepository.findByCourse_CourseIdAndCountry_CountryId(courseId,
				countryId);
		if (priceOptional.isPresent()) {
			Price price = priceOptional.get();
			PriceBreakup priceBreakup = new PriceBreakup();
			priceBreakup.setBasePrice(price.getBasePrice());
			priceBreakup.setConversionFees(price.getCountry().getConversionFees());
			priceBreakup.setOtherCharges(price.getOtherCharges());
			priceBreakup.setTaxes(price.getTaxes());
			priceBreakup.setTotalPrice(priceBreakup.getBasePrice().add(priceBreakup.getTaxes())
					.add(priceBreakup.getOtherCharges()).add(priceBreakup.getConversionFees()));
			if (null != price.getCourse().getPaymentStrategy()) {
				priceBreakup.setPaymentStrategy(price.getCourse().getPaymentStrategy().name());
			} else {
				priceBreakup.setPaymentStrategy(paymentStrategy);
			}
			priceBreakup.setCourseId(courseId);
			priceBreakup.setCountryId(countryId);
			log.info("Exit - PriceServiceImpl - getCoursePriceBreakup()");
			return priceBreakup;
		} else {
			throw new PriceNotFoundException(courseId, countryId);
		}
	}

}
