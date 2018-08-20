package com.capgemini.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeePosition;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.types.AddressTO;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	OfficeService officeService;

	@Autowired
	CarService carService;
	
	@Autowired
	OfficeDao officeRepository;
	
	@Autowired
	CarDao carRepository;

	@Test
	public void shouldFindAllEmployeesFromOffice() {
		// given
		AddressTO officeAddress1 = AddressTO.builder().street("Szkolna").streetNumber(5).city("Warszawa")
				.postalCode("87-546").build();
		OfficeTO office1 = OfficeTO.builder().phoneNumber("9876345456").address(officeAddress1).build();
		AddressTO officeAddress2 = AddressTO.builder().street("Szkolna").streetNumber(5).city("Warszawa")
				.postalCode("87-546").build();
		OfficeTO office2 = OfficeTO.builder().phoneNumber("9876345456").address(officeAddress2).build();
		OfficeTO savedOffice1 = officeService.saveOffice(office1);
		OfficeTO savedOffice2 = officeService.saveOffice(office2);
		EmployeeTO employee1 = EmployeeTO.builder().firstName("Robert").lastName("Majorczyk")
				.position(EmployeePosition.SELLER).birthdate("1985/8/11").build();
		EmployeeTO employee2 = EmployeeTO.builder().firstName("Robert").lastName("Krawczyk")
				.position(EmployeePosition.SELLER).birthdate("1985/8/11").build();

		EmployeeTO savedEmployee1 = officeService.addEmployee(employee1);
		EmployeeTO savedEmployee2 = officeService.addEmployee(employee1);
		EmployeeTO savedEmployee3 = officeService.addEmployee(employee1);

		EmployeeTO savedEmployee4 = officeService.addEmployee(employee2);
		EmployeeTO savedEmployee5 = officeService.addEmployee(employee2);

		officeService.addEmployeeToOffice(savedEmployee1, savedOffice1.getId());
		officeService.addEmployeeToOffice(savedEmployee2, savedOffice1.getId());
		officeService.addEmployeeToOffice(savedEmployee3, savedOffice1.getId());
		officeService.addEmployeeToOffice(savedEmployee4, savedOffice2.getId());
		officeService.addEmployeeToOffice(savedEmployee5, savedOffice2.getId());

		// when
		List<EmployeeTO> workersByOffice1 = employeeService.findAllEmployeesFromOffice(savedOffice1);
		List<EmployeeTO> workersByOffice2 = employeeService.findAllEmployeesFromOffice(savedOffice2);
		// then
		assertThat(workersByOffice1.size()).isEqualTo(3);
		assertThat(workersByOffice2.size()).isEqualTo(2);
	}

	@Test
	
	public void shouldFindAllOwnersOfCarFromOffice() {
		// given
		AddressTO officeAddress1 = AddressTO.builder().street("Szkolna").streetNumber(5).city("Warszawa")
				.postalCode("87-546").build();
		OfficeTO office1 = OfficeTO.builder().phoneNumber("9876345456").address(officeAddress1).build();

		OfficeTO savedOffice1 = officeService.saveOffice(office1);
		EmployeeTO employee1 = EmployeeTO.builder().firstName("Robert").lastName("Majorczyk")
				.officeId(savedOffice1.getId()).position(EmployeePosition.SELLER).birthdate("1985/8/11").build();
		EmployeeTO employee2 = EmployeeTO.builder().firstName("Marek").lastName("Krawczyk")
				.officeId(savedOffice1.getId()).position(EmployeePosition.SELLER).birthdate("1985/8/11").build();
		EmployeeTO savedEmployee1 = officeService.addEmployee(employee1);
		EmployeeTO savedEmployee2 = officeService.addEmployee(employee2);
		CarTO blueJeep = CarTO.builder().carType("Jeep").carBrand("Volvo").color("blue").engineCapacity(4).mileAge(560)
				.power(55).productionYear(2013).build();
		CarTO savedBlueJeep = carService.saveCar(blueJeep);

		// when
		officeService.addEmployeeToOffice(savedEmployee1, savedOffice1.getId());
		officeService.addEmployeeToOffice(savedEmployee2, savedOffice1.getId());
		carService.assignCarToOwner(savedBlueJeep, savedEmployee1);
		carService.assignCarToOwner(savedBlueJeep, savedEmployee2);
		OfficeTO searchedOffice = officeService.findOfficeById(savedOffice1.getId());
		CarTO searchedCar = carService.findCarById(savedBlueJeep.getId());
		List<EmployeeTO> ownersOfBlueJeepFromOffice1 = employeeService.findAllOwnersOfCarFromOffice(searchedOffice,
				searchedCar);
		EmployeeTO owner1 = ownersOfBlueJeepFromOffice1.get(0);
		EmployeeTO owner2 = ownersOfBlueJeepFromOffice1.get(1);
		// then
		assertThat(ownersOfBlueJeepFromOffice1.size()).isEqualTo(2);
		assertThat(owner1.getPosition()).isEqualTo(EmployeePosition.SELLER);
		assertThat(owner2.getLastName()).isEqualTo("Krawczyk");
	}

	@Test
	public void shouldAddEmployee() {
		// given
		EmployeeTO newEmployee = EmployeeTO.builder().firstName("Katarzyna").lastName("Michalczyk").officeId(4L)
				.position(EmployeePosition.SELLER).birthdate("1985/8/11").build();
		// when
		EmployeeTO employeeToAdd = officeService.addEmployee(newEmployee);
		long searchedId = employeeToAdd.getId();
		EmployeeTO searchedEmployee = employeeService.findEmployeeById(searchedId);
		// then
		assertEquals("Katarzyna", searchedEmployee.getFirstName());
	}

	@Test
	public void shouldFindEmployeeByPosition() {
		// given
		EmployeeTO newAccountant = EmployeeTO.builder().firstName("Anna").lastName("Nowak").officeId(4L)
				.position(EmployeePosition.ACCOUNTANT).birthdate("1985/8/11").build();
		// when
		EmployeeTO employeeToAdd = officeService.addEmployee(newAccountant);
		long searchedId = employeeToAdd.getId();
		EmployeeTO searchedPosition = employeeService.findEmployeeById(searchedId);
		// then
		assertEquals(EmployeePosition.ACCOUNTANT, searchedPosition.getPosition());
	}

}
