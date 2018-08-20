package com.capgemini.dao;


import java.util.List;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;

public interface OfficeDao extends Dao<OfficeEntity, Long> {

	void addEmployeeToOffice(EmployeeEntity employeeToAdd, long officeId);
	void removeEmployeeFromOffice(EmployeeEntity employeeToRemove, long officeId);
	List<EmployeeEntity> getAllEmployees();
}
