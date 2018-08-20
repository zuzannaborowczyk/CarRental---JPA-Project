package com.capgemini.types;

import java.util.List;
import java.util.Set;

import com.capgemini.domain.AddressEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.LoanEntity;
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
public class OfficeTO {
	private Long id;
	private AddressTO address;
	private String phoneNumber;
	private List<EmployeeTO> employees;
	//private Set<LoanEntity> officeFrom;
	//private Set<LoanEntity> officeTo;
}
