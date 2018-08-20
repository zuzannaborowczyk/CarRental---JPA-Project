package com.capgemini.service.impl;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.CustomerDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.LoanDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.CustomerEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.LoanEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.mappers.CarMapper;
import com.capgemini.mappers.CustomerMapper;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.mappers.LoanMapper;
import com.capgemini.mappers.OfficeMapper;
import com.capgemini.service.OfficeService;
import com.capgemini.types.CustomerTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.LoanTO;
import com.capgemini.types.OfficeTO;

@Service
@Transactional
public class OfficeServiceImpl implements OfficeService {
	
	@Autowired
	OfficeDao officeRepository;

	@Autowired
	EmployeeDao employeeRepository;
	
	@Autowired
	LoanDao loanRepository;
	
	@Autowired
	CarDao carRepository;
	
	@Autowired
	CustomerDao customerRepository;
	
	@Override
	public OfficeTO saveOffice(OfficeTO newOffice) {
		OfficeEntity officeToSave = officeRepository.save(OfficeMapper.mapToEntity(newOffice));
		return OfficeMapper.mapToTO(officeToSave);
	}

	@Override
	public OfficeTO findOfficeById(long id) {
		OfficeEntity searchedOffice = officeRepository.findOne(id);
		return OfficeMapper.mapToTO(searchedOffice);
	}

	@Override
	public void deleteOffice(OfficeTO officeToDelete) {
		officeRepository.delete(officeToDelete.getId());
		
	}

	@Override
	public OfficeTO updateOffice(OfficeTO officeToUpdate) {
		OfficeEntity officeEntity = officeRepository.findOne(officeToUpdate.getId());
		OfficeEntity officeUpdate = officeRepository.update(OfficeMapper.toEntity(officeToUpdate, officeEntity));
		return OfficeMapper.mapToTO(officeUpdate);
	}

	@Override
	public void addEmployeeToOffice(EmployeeTO employeeToAdd, long officeId) {
		
		EmployeeEntity employeeEntity = employeeRepository.findOne(employeeToAdd.getId());
        OfficeEntity office = officeRepository.findOne(officeId);
        office.addEmployee(employeeEntity);
       // officeRepository.update(office);
       
		
		
	}

	@Override
	public void removeEmployeeFromOffice(EmployeeTO employeeToRemove, long officeId) {
		EmployeeEntity employeeEntity = employeeRepository.findOne(employeeToRemove.getId());
        OfficeEntity office = officeRepository.findOne(officeId);
        office.removeEmployee(employeeEntity);
		
		
	}

	@Override
	public EmployeeTO addEmployee(EmployeeTO newEmployee) {
		EmployeeEntity employeeEntity = employeeRepository.save(EmployeeMapper.toEmployeeEntity(newEmployee));
		
		return EmployeeMapper.toEmployeeTO(employeeEntity);
	}

	
	@Override
	public List<EmployeeTO> getAllEmployees() {
		
		return EmployeeMapper.map2TOs(officeRepository.getAllEmployees());
	}

	@Override
	public List<OfficeTO> findAllOffices() {
		
		return OfficeMapper.Map2TOs(officeRepository.findAll());
	}

	@Override
	public CustomerTO addCustomer(CustomerTO newCustomer) {
		CustomerEntity customerEntity = customerRepository.save(CustomerMapper.mapToEntity(newCustomer));
		return CustomerMapper.mapToTO(customerEntity);
	}

	@Override
	public LoanTO addLoan(LoanTO newLoan) {
		CustomerEntity foundCustomer = customerRepository.findOne(newLoan.getCustomerId());
		CarEntity foundCar = carRepository.findOne(newLoan.getCarId());
		OfficeEntity foundOfficeFrom = officeRepository.findOne(newLoan.getOfficeFromId());
		OfficeEntity foundOfficeTo = officeRepository.findOne(newLoan.getOfficeToId());
		
		LoanEntity newLoanEntity = LoanMapper.mapToEntity(newLoan, foundCustomer, foundCar, foundOfficeFrom, foundOfficeTo);
		
		LoanEntity savedLoan = loanRepository.save(newLoanEntity);
		
		
		return LoanMapper.toTO(savedLoan);
	}

	@Override
	public LoanTO findLoanById(long id) {
		
		return LoanMapper.toTO(loanRepository.findOne(id));
	}

	

}
