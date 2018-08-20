package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.capgemini.domain.AddressEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.CustomerEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.CustomerTO;
import com.capgemini.types.OfficeTO;

public class CustomerMapper {

	public static CustomerTO mapToTO(CustomerEntity customerEntity) {
		
		return toTO(new CustomerTO(), customerEntity);
		
	}

	public static CustomerEntity mapToEntity(CustomerTO customerTO) {
		return toEntity(customerTO, new CustomerEntity());
	}

	
	
	public static CustomerEntity toEntity(CustomerTO customerTO, CustomerEntity customerEntity) {
		if(customerTO == null || customerEntity == null) {
			return null;
		}
		customerEntity.setId(customerEntity.getId());
		customerEntity.setFirstName(customerTO.getFirstName());
		customerEntity.setLastName(customerTO.getLastName());
		customerEntity.setEmail(customerTO.getEmail());
		customerEntity.setCustomerBirthDate(customerTO.getCustomerBirthDate());
		customerEntity.setAddress(AddressMapper.addressToEntity(customerTO.getAddress()));
		
		
		
		
		return customerEntity;
	}
	public static CustomerTO toTO(CustomerTO customerTO, CustomerEntity customerEntity) {
		if (customerEntity == null || customerTO == null) {
			throw new IllegalArgumentException("We expect not null arguments here");
		}
		customerTO.setId(customerEntity.getId());
		customerTO.setFirstName(customerEntity.getFirstName());
		customerTO.setLastName(customerEntity.getLastName());
		customerTO.setCustomerBirthDate(customerEntity.getCustomerBirthDate());
		customerTO.setEmail(customerEntity.getEmail());
		customerTO.setAddress(AddressMapper.addressToTO(customerEntity.getAddress()));
		
		
		
		
		return customerTO;
	}
}
