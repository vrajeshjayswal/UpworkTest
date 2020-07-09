package com.example.course.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.course.entity.Country;
import com.example.course.entity.Course;
import com.example.course.entity.PaymentStrategy;
import com.example.course.entity.Price;
import com.example.course.pojo.PriceBreakup;
import com.example.course.repository.PriceRepository;

import exception.PriceNotFoundException;

/**
 * Unit testing for PriceServiceImpl class
 * 
 * @author vrajesh
 *
 */
@SpringBootTest
public class PriceServiceImplTest {

	private static final BigDecimal BASE_PRICE = BigDecimal.ONE;
	private static final BigDecimal WHOLE_PRICE = BigDecimal.valueOf(1.45);
	private static final BigDecimal OTHER_CHARGES = BigDecimal.valueOf(0.25);
	private static final BigDecimal TAXES = BigDecimal.valueOf(0.15);
	private static final BigDecimal CONVERSION_FEES = BigDecimal.valueOf(0.05);
	private static final Long COURSE_ID = 3L;
	private static final Long COUNTRY_ID = 2L;
	private static final Long INVALID_COURSE_ID = 1L;

	@Mock
	private PriceRepository priceRepository;

	@InjectMocks
	private PriceServiceImpl priceService;

	@BeforeEach
	void setMockOutput() {
		Course course = new Course();
		course.setPaymentStrategy(PaymentStrategy.MONTHLY_SUBSCRIPTION);
		Country country = new Country();
		country.setConversionFees(CONVERSION_FEES);
		Price price = new Price();
		price.setBasePrice(BASE_PRICE);
		price.setOtherCharges(OTHER_CHARGES);
		price.setTaxes(TAXES);
		price.setCountry(country);
		price.setCourse(course);
		Optional<Price> priceOptional = Optional.of(price);

		when(priceRepository.findByCourse_CourseIdAndCountry_CountryId(COURSE_ID, COUNTRY_ID))
				.thenReturn(priceOptional);
	}

	@DisplayName("PriceService - getCourseBasePrice - valid scenario")
	@Test
	void testGetCourseBasePrice() throws PriceNotFoundException {
		PriceBreakup priceBreakup = priceService.getCourseBasePrice(COURSE_ID, COUNTRY_ID);
		assertNotNull(priceBreakup);
		assertEquals(BASE_PRICE, priceBreakup.getBasePrice());
		assertNull(priceBreakup.getTotalPrice());
	}

	@DisplayName("PriceService - getCourseBasePrice - Price not found scenario")
	@Test
	void testGetCourseBasePriceRecordNotFound() {
		assertThrows(PriceNotFoundException.class,
				() -> priceService.getCourseBasePrice(INVALID_COURSE_ID, COUNTRY_ID));
	}

	@DisplayName("PriceService - getCourseWholePrice - valid scenario")
	@Test
	void testGetCourseWholePrice() throws PriceNotFoundException {
		PriceBreakup priceBreakup = priceService.getCourseWholePrice(COURSE_ID, COUNTRY_ID);
		assertNotNull(priceBreakup);
		assertEquals(WHOLE_PRICE, priceBreakup.getTotalPrice());
		assertNull(priceBreakup.getBasePrice());
	}

	@DisplayName("PriceService - getCourseWholePrice - Price not found scenario")
	@Test
	void testGetCourseWholePriceRecordNotFound() {
		assertThrows(PriceNotFoundException.class,
				() -> priceService.getCourseWholePrice(INVALID_COURSE_ID, COUNTRY_ID));
	}

	@DisplayName("PriceService - getCoursePriceBreakup - valid scenario")
	@Test
	void getCoursePriceBreakup() throws PriceNotFoundException {
		PriceBreakup priceBreakup;
		priceBreakup = priceService.getCoursePriceBreakup(COURSE_ID, COUNTRY_ID);
		assertNotNull(priceBreakup);
		assertEquals(BASE_PRICE, priceBreakup.getBasePrice());
		assertEquals(WHOLE_PRICE, priceBreakup.getTotalPrice());
		assertEquals(CONVERSION_FEES, priceBreakup.getConversionFees());
		assertEquals(OTHER_CHARGES, priceBreakup.getOtherCharges());
		assertEquals(TAXES, priceBreakup.getTaxes());
		assertEquals(COUNTRY_ID, priceBreakup.getCountryId());
		assertEquals(COURSE_ID, priceBreakup.getCourseId());
		assertEquals(PaymentStrategy.MONTHLY_SUBSCRIPTION.name(), priceBreakup.getPaymentStrategy());

	}

	@DisplayName("PriceService - getCoursePriceBreakup - Price not found scenario")
	@Test
	void getCoursePriceBreakupRecordNotFound() {
		assertThrows(PriceNotFoundException.class,
				() -> priceService.getCoursePriceBreakup(INVALID_COURSE_ID, COUNTRY_ID));
	}

}