package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeePosition;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;

public class EmployeeMapper {

	public static EmployeeTO toEmployeeTO(EmployeeEntity employeeEntity) {
		if (employeeEntity == null) {
			return null;
		}
		return EmployeeTO.builder().
				id(employeeEntity.getId()).
				birthdate(employeeEntity.getBirthdate()).
				firstName(employeeEntity.getFirstName())
				.lastName(employeeEntity.getLastName())
				.officeId(employeeEntity.getOfficeEntity() == null ? null : employeeEntity.getOfficeEntity().getId())
				.position(employeeEntity.getPosition()).
				build();
	}

	public static EmployeeEntity toEmployeeEntity(EmployeeTO employeeTO) {
		if (employeeTO == null) {
			return null;
		}
		return EmployeeEntity.builder().
				id(employeeTO.getId()).
				birthdate(employeeTO.getBirthdate()).
				firstName(employeeTO.getFirstName()).
				lastName(employeeTO.getLastName()).
				position(employeeTO.getPosition()).
				build();
	}

	public static List<EmployeeTO> map2TOs(List<EmployeeEntity> employeeEntities) {
		List<EmployeeTO> employeeToEntities = new ArrayList<>();
		for (EmployeeEntity employee : employeeEntities) {
			employeeToEntities.add(EmployeeMapper.toEmployeeTO(employee));
		}
		return employeeToEntities;

	}

	public static List<EmployeeEntity> map2Entities(List<EmployeeTO> employeeTOs) {
		List<EmployeeEntity> employeeToTO = new ArrayList<>();
		for (EmployeeTO employee : employeeTOs) {
			employeeToTO.add(EmployeeMapper.toEmployeeEntity(employee));
		}
		return employeeToTO;
	}
}
