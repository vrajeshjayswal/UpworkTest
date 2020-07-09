package com.example.course.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity class that represents database table - Price
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
public class Price {

	@Id
	@GeneratedValue
	private Long priceId;

	@NonNull
	private BigDecimal basePrice;

	@NonNull
	private BigDecimal taxes;

	@NonNull
	private BigDecimal otherCharges;

	@ManyToOne
	@JoinColumn(name = "countryId", nullable = false, insertable = false, updatable = false)
	private Country country;

	@ManyToOne
	@JoinColumn(name = "courseId", nullable = false, insertable = false, updatable = false)
	private Course course;

}
