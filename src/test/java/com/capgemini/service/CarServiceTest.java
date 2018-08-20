package com.capgemini.service;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeePosition;
import com.capgemini.types.AddressTO;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;
import com.capgemini.types.CarTO.CarTOBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class CarServiceTest {

	@Autowired
	CarService carService;
	
	@Autowired
	EmployeeService employeeService;

	@Autowired
	CarDao carRepository;

	@Autowired
	EmployeeDao employeeRepository;

	@Autowired
	OfficeService officeService;

	@PersistenceContext
	EntityManager entityManager;

	@Test
	@Transactional
	public void shouldAddNewCar() {

		// given
		CarTO carTO = CarTO.builder().carType("Jeep").carBrand("Volvo").color("blue").engineCapacity(4).mileAge(560)
				.power(55).productionYear(2013).build();

		// when
		CarTO carToAdd = carService.saveCar(carTO);
		long searchedId = carToAdd.getId();
		CarTO searchedCar = carService.findCarById(searchedId);
		// then
		assertEquals("Jeep", carToAdd.getCarType());

	}

	@Test
	
	public void shouldFindCarsByCarTypeAndCarBrand() {
		// given
		String searchedCarBrand = "Volvo";
		String searchedCarType = "Jeep";
		CarTO carTO1 = CarTO.builder().carType(searchedCarType).carBrand(searchedCarBrand).color("blue")
				.engineCapacity(4).mileAge(560).power(55).productionYear(2013).build();
		CarTO carTO2 = CarTO.builder().carType(searchedCarType).carBrand(searchedCarBrand).color("black")
				.engineCapacity(2).mileAge(570).power(45).productionYear(2013).build();
		carService.saveCar(carTO1);
		carService.saveCar(carTO2);

		// when
		List<CarTO> selectedCars = carService.findByCarTypeAndCarBrand(searchedCarType, searchedCarBrand);
		// then
		assertNotNull(selectedCars);
		assertFalse(selectedCars.isEmpty());
		assertTrue(selectedCars.stream().anyMatch(b -> b.getCarBrand().equals(searchedCarBrand)));

	}

	@Test
	
	public void shouldDeleteCar() {
		// given
		CarTO carTO1 = CarTO.builder().carType("Jeep").carBrand("Volvo").color("blue").engineCapacity(4).mileAge(560)
				.power(55).productionYear(2013).build();
		CarTO carTO2 = CarTO.builder().carType("Jeep").carBrand("Honda").color("red").engineCapacity(4).mileAge(560)
				.power(55).productionYear(2013).build();
		CarTO carTO3 = CarTO.builder().carType("Sedan").carBrand("Volvo").color("black").engineCapacity(3).mileAge(300)
				.power(55).productionYear(2011).build();
		CarTO carTO4 = CarTO.builder().carType("Jeep").carBrand("Volvo").color("white").engineCapacity(2).mileAge(200)
				.power(55).productionYear(2013).build();
		CarTO carTO5 = CarTO.builder().carType("Sedan").carBrand("Volvo").color("green").engineCapacity(4).mileAge(670)
				.power(55).productionYear(2011).build();
		// when

		CarTO carToRemove1 = carService.saveCar(carTO1);
		CarTO carToRemove2 = carService.saveCar(carTO2);
		CarTO carToRemove3 = carService.saveCar(carTO3);
		CarTO carToRemove4 = carService.saveCar(carTO4);
		CarTO carToRemove5 = carService.saveCar(carTO5);
		carService.deleteCar(carToRemove3);
		List<CarTO> cars = carService.findAllCars();

		// then
		assertEquals(cars.size(), 4);
	}

	@Test

	public void shouldUpdateCar() {
		// given
		int newMileAge = 660;
		CarTO blueJeep = CarTO.builder().carType("Jeep").carBrand("Volvo").color("blue").engineCapacity(4).mileAge(560)
				.power(55).productionYear(2013).build();
		CarTO savedCar = carService.saveCar(blueJeep);
		// when
		CarTO carToUpdate = carService.findCarById(savedCar.getId());
		carToUpdate.setMileAge(newMileAge);
		carService.update(carToUpdate);
		// then
		assertThat(carService.findCarById(carToUpdate.getId()).getMileAge()).isEqualTo(newMileAge);
	}

	@Test
	
	public void shouldAssignCarToOwner() {
		// given
		EmployeeTO newOwner = EmployeeTO.builder().firstName("Robert").lastName("Majorczyk").officeId(4L)
				.position(EmployeePosition.SELLER).birthdate("1985/8/11").build();
		CarTO blueJeep = CarTO.builder().carType("Jeep").carBrand("Volvo").color("blue").engineCapacity(4).mileAge(560)
				.power(55).productionYear(2013).build();
		AddressTO officeAddress = AddressTO.builder().street("Szkolna").streetNumber(5).city("Warszawa")
				.postalCode("87-546").build();
		OfficeTO newOffice = OfficeTO.builder().phoneNumber("9876345456").address(officeAddress).build();

		EmployeeTO savedOwner = officeService.addEmployee(newOwner);
		CarTO savedCar = carService.saveCar(blueJeep);
		OfficeTO savedOffice = officeService.saveOffice(newOffice);
		officeService.addEmployeeToOffice(savedOwner, savedOffice.getId());
		// when
		carService.assignCarToOwner(savedCar, savedOwner);
		CarTO carTO = carService.findCarById(savedCar.getId());
		Set<CarTO> carsOwnedByEmployee = carService.findByOwner(savedOwner.getId());
		OfficeTO officeTO = officeService.findOfficeById(savedOffice.getId());
		// then
		assertNotNull(carsOwnedByEmployee);
		assertFalse(carsOwnedByEmployee.isEmpty());
		assertTrue(carsOwnedByEmployee.stream().anyMatch(b -> b.getId().equals(carTO.getId())));
		assertEquals(1, carsOwnedByEmployee.size());
	}

	@Test
	
	public void shouldFindCarByOwner() {
		// given
		EmployeeTO newOwner = EmployeeTO.builder().firstName("Robert").lastName("Majorczyk")
				.position(EmployeePosition.SELLER).birthdate("1985/8/11").build();
		CarTO blueJeep = CarTO.builder().carType("Jeep").carBrand("Volvo").color("blue").engineCapacity(4)
				.mileAge(560).power(55).productionYear(2013).build();
		CarTO redJeep = CarTO.builder().carType("Jeep").carBrand("Volvo").color("red").engineCapacity(4)
				.mileAge(560).power(55).productionYear(2013).build();
		CarTO savedBlueJeep = carService.saveCar(blueJeep);
		CarTO savedRedJeep = carService.saveCar(redJeep);
		EmployeeTO savedOwner = officeService.addEmployee(newOwner);
		carService.assignCarToOwner(savedBlueJeep, savedOwner);
		carService.assignCarToOwner(savedRedJeep, savedOwner);
		long ownerId = employeeService.findAllEmployees().get(0).getId();
		// when
		Set<CarTO> res = carService.findByOwner(savedOwner.getId());
		// then
		assertEquals(2, res.size());
		assertNotNull(res);

	}
	
	@Test
	public void shouldFindCarsByDifferentCustomers() {
		//given
		
		//when
		
		//then
		
		
	}
	
	@Test
	public void countCarsRentedInSpecificTime() {
		//given
		
		//when
				
		//then
	}
}
