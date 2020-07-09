package com.example.course.pojo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Plain object which will be used to send response to the API client
 * 
 * @author vrajesh
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PriceBreakup {

	Long courseId;
	Long countryId;
	BigDecimal basePrice;
	BigDecimal taxes;
	BigDecimal otherCharges;
	BigDecimal conversionFees;
	BigDecimal totalPrice;
	String paymentStrategy;
}
