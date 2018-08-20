package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.capgemini.domain.CarEntity;

import com.capgemini.types.CarTO;
import com.capgemini.types.CarTO.CarTOBuilder;

public class CarMapper {

	public static CarTO toCarTO(CarEntity carEntity) {

		return toCarTO(new CarTO(), carEntity);

	}

	public static CarEntity toCarEntity(CarTO carTO) {

		return toCarEntity(carTO, new CarEntity());
	}

	public static Set<CarTO> Map2TOs(Set<CarEntity> carEntities) {
		Set<CarTO> carsToEntities = new HashSet<>();
		for (CarEntity car : carEntities) {
			carsToEntities.add(CarMapper.toCarTO(car));
		}
		return carsToEntities;

	}

	public static Set<CarEntity> Map2Entities(Set<CarTO> carTOs) {
		Set<CarEntity> carsToTO = new HashSet<>();
		for (CarTO car : carTOs) {
			carsToTO.add(CarMapper.toCarEntity(car));
		}
		return carsToTO;
	}

	public static List<CarTO> Map2TOs(List<CarEntity> carEntities) {
		List<CarTO> carsToEntities = new ArrayList<>();
		for (CarEntity car : carEntities) {
			carsToEntities.add(CarMapper.toCarTO(car));
		}
		return carsToEntities;

	}

	public static List<CarEntity> Map2Entities(List<CarTO> carTOs) {
		List<CarEntity> carsToTO = new ArrayList<>();
		for (CarTO car : carTOs) {
			carsToTO.add(CarMapper.toCarEntity(car));
		}
		return carsToTO;
	}

	public static CarEntity toCarEntity(CarTO carTO, CarEntity carEntity) {
		if (carTO == null && carEntity == null) {
			return null;
		}
		carEntity.setId(carTO.getId());
		carEntity.setCarType(carTO.getCarType());
		carEntity.setCarBrand(carTO.getCarBrand());
		carEntity.setColor(carTO.getColor());
		carEntity.setEngineCapacity(carTO.getEngineCapacity());
		carEntity.setMileAge(carTO.getMileAge());
		carEntity.setPower(carTO.getPower());
		carEntity.setProductionYear(carTO.getProductionYear());

		return carEntity;
	}

	public static CarTO toCarTO(CarTO carTO, CarEntity carEntity) {
		if (carTO == null && carEntity == null) {
			return null;
		}
		carTO.setId(carEntity.getId()== null ? null : carEntity.getId());
		carTO.setCarType(carEntity.getCarType());
		carTO.setCarBrand(carEntity.getCarBrand());
		carTO.setColor(carEntity.getColor());
		carTO.setEngineCapacity(carEntity.getEngineCapacity());
		carTO.setMileAge(carEntity.getMileAge());
		carTO.setPower(carEntity.getPower());
		carTO.setProductionYear(carEntity.getProductionYear());

		return carTO;
	}

}
