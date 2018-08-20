package com.capgemini.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;

public interface CarDao extends Dao<CarEntity, Long> {

	void assignCarToOwner(CarEntity carToAssign, EmployeeEntity employeeToAssign);

	List<CarEntity> findByCarTypeAndCarBrand(String carType, String carBrand);

	Set<CarEntity> findByOwner(long employeeId);
	
	List<CarEntity> findCarsByDifferentCustomers(int numberOfLoans);
	
	List<Long> countCarsRentedInSpecificTime(Date dateFrom, Date dateTo);
	
}
