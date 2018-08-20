package com.capgemini.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.mappers.CarMapper;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.mappers.OfficeMapper;
import com.capgemini.service.EmployeeService;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeDao employeeRepository;
	
	@Autowired
	OfficeDao officeRepository;
	
	@Autowired
	CarDao carRepository;

	@Override
	public List<EmployeeTO> findAllEmployeesFromOffice(OfficeTO officeTO) {
		
		List<EmployeeEntity> employeesByOffice = employeeRepository.findAllEmployeesFromOffice(officeTO.getId());
		return EmployeeMapper.map2TOs(employeesByOffice);
	}

	@Override
	public List<EmployeeTO> findAllOwnersOfCarFromOffice(OfficeTO officeTO, CarTO currentCar) {
		
		List<EmployeeEntity> employeesByOfficeAndCar = employeeRepository.findAllOwnersOfCarFromOffice(officeTO.getId(), currentCar.getId());
		return EmployeeMapper.map2TOs(employeesByOfficeAndCar);
	}

	@Override
	public EmployeeTO findEmployeeById(long id) {
		EmployeeEntity searchedEmployee = employeeRepository.findOne(id);
		return EmployeeMapper.toEmployeeTO(searchedEmployee);
	}

	@Override
	public List<EmployeeTO> findEmployeesByPosition(Long positionId) {
		return EmployeeMapper.map2TOs(employeeRepository.findEmployeesByPosition(positionId));
	}

	@Override
	public List<EmployeeTO> findAllEmployees() {
		
		return EmployeeMapper.map2TOs(employeeRepository.findAll());
	}

}
