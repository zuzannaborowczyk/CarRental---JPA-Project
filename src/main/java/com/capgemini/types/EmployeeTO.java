package com.capgemini.types;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeePosition;
import com.capgemini.domain.LoanEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.types.CarTO.CarTOBuilder;

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
public class EmployeeTO {
	private Long id;
	private String firstName;
	private String lastName;
	private Long officeId;
	private EmployeePosition position;
	private String birthdate;
	//private OfficeTO officeEntity;
	//private Set<CarTO> cars = new HashSet<>();
}
