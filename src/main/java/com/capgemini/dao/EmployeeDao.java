package com.capgemini.dao;


import java.util.List;

import com.capgemini.domain.EmployeeEntity;

public interface EmployeeDao extends Dao<EmployeeEntity, Long> {

	
	List<EmployeeEntity> findAllEmployeesFromOffice(Long officeId);
	List<EmployeeEntity> findAllOwnersOfCarFromOffice(Long officeId, Long carId);
	List<EmployeeEntity> findEmployeesByPosition(Long positionId);
}
