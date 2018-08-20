package com.capgemini.types;

import java.util.Set;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.LoanEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CarTO {
	private Long id;
	private String carType;
	private String carBrand;
	private String color;
	private int mileAge;
	private int power;
	private int engineCapacity;
	private int productionYear;
	
}
