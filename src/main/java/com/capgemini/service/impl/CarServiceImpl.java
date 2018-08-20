package com.capgemini.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.mappers.CarMapper;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.service.CarService;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;

@Service
@Transactional
public class CarServiceImpl implements CarService {
	
	@Autowired
	EmployeeDao employeeRepository;

	@Autowired
	CarDao carRepository;

	@Override
	public CarTO saveCar(CarTO newCar) {
		CarEntity carToSave = CarMapper.toCarEntity(newCar);
		return CarMapper.toCarTO(carRepository.save(carToSave));
	}

	@Override
	public void deleteCar(CarTO deletedCar) {
		carRepository.delete(CarMapper.toCarEntity(deletedCar));
		
	}

	@Override
	public CarTO update(CarTO carToUpdate) {
		CarEntity carEntity = carRepository.findOne(carToUpdate.getId());
		CarEntity carUpdate = carRepository.update(CarMapper.toCarEntity(carToUpdate, carEntity));
		return CarMapper.toCarTO(carUpdate);
	}

	@Override
	public void assignCarToOwner(CarTO carToAssign, EmployeeTO employeeToAssign) {
		CarEntity newCar = carRepository.findOne(carToAssign.getId());
		
		EmployeeEntity newEmployee = employeeRepository.findOne(employeeToAssign.getId());
		carRepository.assignCarToOwner(newCar, newEmployee);
		carRepository.update(newCar);

	}

	@Override
	public List<CarTO> findByCarTypeAndCarBrand(String carType, String carBrand) {
		List<CarEntity> carsByType = carRepository.findByCarTypeAndCarBrand(carType, carBrand);
		return CarMapper.Map2TOs(carsByType);
	}

	@Override
	public Set<CarTO> findByOwner(long employeeId) {
		Set<CarEntity> carsByOwner = carRepository.findByOwner(employeeId);
		return CarMapper.Map2TOs(carsByOwner);
	}

	@Override
	public CarTO findCarById(long id) {
		CarEntity searchedCar = carRepository.findOne(id);
		return CarMapper.toCarTO(searchedCar);
	}

	@Override
	public List<CarTO> findAllCars() {
		
		return CarMapper.Map2TOs(carRepository.findAll());
	}

	@Override
	public List<CarTO> findCarsByDifferentCustomers(int numberOfLoans) {
		
		return CarMapper.Map2TOs(carRepository.findCarsByDifferentCustomers(numberOfLoans));
	}

	@Override
	public List<Long> countCarsRentedInSpecificTime(Date dateFrom, Date dateTo) {
		
		return carRepository.countCarsRentedInSpecificTime(dateFrom, dateTo);
	}

}
