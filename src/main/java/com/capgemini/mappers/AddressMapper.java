package com.capgemini.mappers;


import com.capgemini.domain.AddressEntity;
import com.capgemini.types.AddressTO;

public class AddressMapper {

	
	public static AddressTO addressToTO(AddressEntity addressEntity) {
		if (addressEntity == null) {
			return null;
		}
		return AddressTO.builder().city(addressEntity.getCity()).street(addressEntity.getStreet()).postalCode(addressEntity.getPostalCode())
				.streetNumber(addressEntity.getStreetNumber()).build();
	}
	public static AddressEntity addressToEntity(AddressTO addressTO) {
		return AddressEntity.builder().city(addressTO.getCity()).street(addressTO.getStreet()).postalCode(addressTO.getPostalCode())
				.streetNumber(addressTO.getStreetNumber()).build();
	}
}
