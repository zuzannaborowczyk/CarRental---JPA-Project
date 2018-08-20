package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.capgemini.domain.AddressEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.OfficeTO;

public class OfficeMapper {

	public static OfficeTO mapToTO(OfficeEntity officeEntity) {
		
		return toTO(new OfficeTO(), officeEntity);
	}

	public static OfficeEntity mapToEntity(OfficeTO officeTO) {
		return toEntity(officeTO, new OfficeEntity());
	}

	public static List<OfficeTO> Map2TOs(List<OfficeEntity> list) {
		List<OfficeTO> officeToEntities = new ArrayList<>();
		for (OfficeEntity office : list) {
			officeToEntities.add(OfficeMapper.mapToTO(office));
		}
		return officeToEntities;

	}

	public static Set<OfficeEntity> Map2Entities(Set<OfficeTO> officeTOs) {
		Set<OfficeEntity> officeToTO = new HashSet<>();
		for (OfficeTO office : officeTOs) {
			officeToTO.add(OfficeMapper.mapToEntity(office));
		}
		return officeToTO;
	}
	
	public static OfficeEntity toEntity(OfficeTO officeTO, OfficeEntity officeEntity) {
		if(officeTO == null || officeEntity == null) {
			return null;
		}
		officeEntity.setId(officeEntity.getId());
		officeEntity.setAddress(AddressMapper.addressToEntity(officeTO.getAddress()));
		officeEntity.setPhoneNumber(officeTO.getPhoneNumber());
		
		
		
		return officeEntity;
	}
	public static OfficeTO toTO(OfficeTO officeTO, OfficeEntity officeEntity) {
		if (officeEntity == null || officeTO == null) {
			throw new IllegalArgumentException("We expect not null arguments here");
		}
		officeTO.setId(officeEntity.getId());
		officeTO.setAddress(AddressMapper.addressToTO(officeEntity.getAddress()));
		officeTO.setPhoneNumber(officeEntity.getPhoneNumber());
		
		
		
		return officeTO;
	}
}
