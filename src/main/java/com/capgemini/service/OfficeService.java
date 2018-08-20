package com.capgemini.service;

import java.util.List;
import java.util.Set;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.CustomerTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.LoanTO;
import com.capgemini.types.OfficeTO;

public interface OfficeService {

	List<EmployeeTO> getAllEmployees();

	OfficeTO saveOffice(OfficeTO newOffice);

	OfficeTO findOfficeById(long id);

	List<OfficeTO> findAllOffices();

	void deleteOffice(OfficeTO officeToDelete);

	OfficeTO updateOffice(OfficeTO officeToUpdate);

	void addEmployeeToOffice(EmployeeTO employeeToAdd, long officeId);

	void removeEmployeeFromOffice(EmployeeTO employeeToRemove, long officeId);

	EmployeeTO addEmployee(EmployeeTO newEmployee);
	
	CustomerTO addCustomer(CustomerTO newCustomer);
	
	LoanTO addLoan(LoanTO newLoan);
	
	LoanTO findLoanById(long id);

}
