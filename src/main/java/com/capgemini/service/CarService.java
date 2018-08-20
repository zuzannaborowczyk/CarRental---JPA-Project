package com.capgemini.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;

public interface CarService {

	CarTO saveCar(CarTO newCar);

	CarTO findCarById(long id);
	
	void deleteCar(CarTO carToDelete);

	CarTO update(CarTO carToUpdate);

	void assignCarToOwner(CarTO carToAssign, EmployeeTO employeeToAssign);

	List<CarTO> findByCarTypeAndCarBrand(String carType, String carBrand);

	Set<CarTO> findByOwner(long ownersId);

	List<CarTO> findAllCars();
	
	List<CarTO> findCarsByDifferentCustomers(int numberOfLoans);
	
	List<Long> countCarsRentedInSpecificTime(Date dateFrom, Date dateTo);

}
