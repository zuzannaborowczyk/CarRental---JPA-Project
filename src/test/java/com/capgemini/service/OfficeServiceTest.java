package com.capgemini.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.domain.EmployeePosition;
import com.capgemini.types.AddressTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.LoanTO;
import com.capgemini.types.OfficeTO;

//@DirtiesContext
@RunWith(SpringRunner.class)
@SpringBootTest
public class OfficeServiceTest {

	@Autowired
	OfficeService officeService;

	@Autowired
	EmployeeService employeeService;

	@Test
	public void shouldSaveOffice() {
		// given
		AddressTO officeAddress0 = AddressTO.builder().street("Szkolna").streetNumber(5).city("Warszawa")
				.postalCode("87-546").build();
		OfficeTO office0 = OfficeTO.builder().phoneNumber("9876345456").address(officeAddress0).build();
		OfficeTO savedOffice = officeService.saveOffice(office0);
		// when
		OfficeTO foundOffice = officeService.findOfficeById(savedOffice.getId());
		// then
		assertThat(foundOffice.getId()).isEqualTo(savedOffice.getId());
	}

	@Test
	public void shouldDeleteOffice() {
		// given
		int initialOfficesCount = officeService.findAllOffices().size();
		AddressTO officeAddress1 = AddressTO.builder().street("Szkolna").streetNumber(5).city("Warszawa")
				.postalCode("87-546").build();
		OfficeTO office1 = OfficeTO.builder().phoneNumber("9876345456").address(officeAddress1).build();
		AddressTO officeAddress2 = AddressTO.builder().street("Szkolna").streetNumber(5).city("Warszawa")
				.postalCode("87-546").build();
		OfficeTO office2 = OfficeTO.builder().phoneNumber("9876345456").address(officeAddress1).build();
		AddressTO officeAddress3 = AddressTO.builder().street("Szkolna").streetNumber(5).city("Warszawa")
				.postalCode("87-546").build();
		OfficeTO office3 = OfficeTO.builder().phoneNumber("9876345456").address(officeAddress1).build();
		OfficeTO savedOffice1 = officeService.saveOffice(office1);
		OfficeTO savedOffice2 = officeService.saveOffice(office2);
		OfficeTO savedOffice3 = officeService.saveOffice(office3);

		// when
		
		officeService.deleteOffice(savedOffice2);
		List<OfficeTO> allOffices = officeService.findAllOffices();
		


		// then

		assertThat(allOffices.size()).isEqualTo(initialOfficesCount + 2);
	}

	@Test
	public void shouldUpdateOffice() {
		// given
		String newPhoneNumber = "765436554";
		AddressTO officeAddress5 = AddressTO.builder().street("Szkolna").streetNumber(5).city("Warszawa")
				.postalCode("87-546").build();
		OfficeTO office5 = OfficeTO.builder().phoneNumber("9876345456").address(officeAddress5).build();
		OfficeTO savedOffice5 = officeService.saveOffice(office5);
		// when
		OfficeTO selectedOffice = officeService.findOfficeById(savedOffice5.getId());
		selectedOffice.setPhoneNumber(newPhoneNumber);
		officeService.updateOffice(selectedOffice);
		// then
		assertThat(officeService.findOfficeById(savedOffice5.getId()).getPhoneNumber()).isEqualTo(newPhoneNumber);
	}

	@Test
	public void shouldAddEmployeeToOffice() {
		// given
		AddressTO currentAddress = AddressTO.builder().street("Szkolna").streetNumber(5).city("Warszawa")
				.postalCode("87-546").build();
		OfficeTO currentOffice = OfficeTO.builder().phoneNumber("9876345456").address(currentAddress).build();
		AddressTO newAddress = AddressTO.builder().street("Szkolna").streetNumber(5).city("Warszawa")
				.postalCode("87-546").build();
		OfficeTO newOffice = OfficeTO.builder().phoneNumber("9876345456").address(newAddress).build();
		OfficeTO savedCurrentOffice = officeService.saveOffice(currentOffice);
		
		
		EmployeeTO employee1 = EmployeeTO.builder().firstName("Robert").lastName("Majorczyk")
				.position(EmployeePosition.SELLER).birthdate("1985/8/11").officeId(5L).build();
		EmployeeTO employee2 = EmployeeTO.builder().firstName("Robert").lastName("Krawczyk")
				.position(EmployeePosition.SELLER).birthdate("1985/8/11").officeId(7L).build();
		
		OfficeTO savedNewOffice = officeService.saveOffice(newOffice);
		
		
		
		EmployeeTO savedEmployee1 = officeService.addEmployee(employee1);
		EmployeeTO savedEmployee2 = officeService.addEmployee(employee2);
		// when
		officeService.addEmployeeToOffice(savedEmployee1, savedNewOffice.getId());
		officeService.addEmployeeToOffice(savedEmployee2, savedNewOffice.getId());
		
		
		// then
		long searchedId = savedNewOffice.getId();
	
		
		EmployeeTO searchedEmployee = employeeService.findEmployeeById(savedEmployee2.getId());
		assertThat(searchedEmployee.getOfficeId()).isEqualTo(savedNewOffice.getId());
		

	}

	@Test
	public void shouldRemoveEmployeeFromOffice() {
		// given
		AddressTO officeAddress1 = AddressTO.builder().street("Szkolna").streetNumber(5).city("Warszawa")
				.postalCode("87-546").build();
		OfficeTO office1 = OfficeTO.builder().phoneNumber("9876345456").address(officeAddress1).build();
		OfficeTO savedOffice = officeService.saveOffice(office1);
		EmployeeTO employee1 = EmployeeTO.builder().firstName("Robert").lastName("Majorczyk")
				.position(EmployeePosition.SELLER).birthdate("1985/8/11").build();
		EmployeeTO employee2 = EmployeeTO.builder().firstName("Robert").lastName("Krawczyk")
				.position(EmployeePosition.SELLER).birthdate("1985/8/11").build();
		EmployeeTO savedEmployee1 = officeService.addEmployee(employee1);
		EmployeeTO savedEmployee2 = officeService.addEmployee(employee2);

		officeService.addEmployeeToOffice(savedEmployee1, savedOffice.getId());
		officeService.addEmployeeToOffice(savedEmployee2, savedOffice.getId());

		// when
		officeService.removeEmployeeFromOffice(savedEmployee2, savedOffice.getId());;

		// then
		assertNull(employeeService.findEmployeeById(savedEmployee2.getId()).getOfficeId());
	}

	@Test
	public void shouldAddLoan() {
		// given
		LoanTO newLoan1 = LoanTO.builder().dateFrom(Date.valueOf("2018-07-10")).dateTo(Date.valueOf("2018-08-01")).loanPrice(520)
				.carId(4L).customerId(5L).officeFromId(2L).officeToId(3L).build();
		LoanTO newLoan2 = LoanTO.builder().dateFrom(Date.valueOf("2018-07-10")).dateTo(Date.valueOf("2018-08-01")).loanPrice(540)
				.carId(4L).customerId(5L).officeFromId(2L).officeToId(3L).build();
		LoanTO newLoan3 = LoanTO.builder().dateFrom(Date.valueOf("2018-07-10")).dateTo(Date.valueOf("2018-08-01")).loanPrice(540)
				.carId(4L).customerId(5L).officeFromId(2L).officeToId(3L).build();
		LoanTO savedLoan1 = officeService.addLoan(newLoan1, );
		LoanTO savedLoan2 = officeService.addLoan(newLoan2);
		LoanTO savedLoan3 = officeService.addLoan(newLoan3);
		// when
		LoanTO foundLoan = officeService.findLoanById(savedLoan1.getId());
		// then
		assertThat(foundLoan.getLoanPrice()).isEqualTo(520);
	}
}
