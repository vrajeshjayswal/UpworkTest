package com.example.course.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity class that represents database table - Country
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
public class Country {

	@Id
	@GeneratedValue
	private Long countryId;

	@NonNull
	private BigDecimal conversionFees;

	@NonNull
	private String iso;

	@NonNull
	private String name;

}
