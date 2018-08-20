package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

public interface EmployeeService {
	
	EmployeeTO findEmployeeById(long id);
	List<EmployeeTO> findAllEmployeesFromOffice(OfficeTO officeTO);
	List<EmployeeTO> findAllOwnersOfCarFromOffice(OfficeTO officeTO, CarTO currentCar);
	List<EmployeeTO> findEmployeesByPosition(Long positionId);
	List<EmployeeTO> findAllEmployees();
}
