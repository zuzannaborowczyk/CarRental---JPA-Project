package com.capgemini.dao.impl;


import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeePosition;

@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {

	
	@Override
	public List<EmployeeEntity> findAllEmployeesFromOffice(Long officeId) {
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
				"select employee from EmployeeEntity employee where employee.officeEntity.id = :officeId", EmployeeEntity.class);
        query.setParameter("officeId", officeId);
        return query.getResultList();
	}

	@Override
	public List<EmployeeEntity> findAllOwnersOfCarFromOffice(Long officeId, Long carId) {
		CarEntity car = entityManager.getReference(CarEntity.class, carId);
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
		"select employee from EmployeeEntity employee join employee.cars car  where (:officeId = employee.officeEntity.id and :carId=car.id))", EmployeeEntity.class);
        query.setParameter("officeId", officeId);
        query.setParameter("carId", carId);
        return query.getResultList();
	}

	@Override
	public List<EmployeeEntity> findEmployeesByPosition(Long positionId) {
		EmployeePosition position = entityManager.getReference(EmployeePosition.class, positionId);
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
			 	"select employee from EmployeeEntity employee where position =:position" , EmployeeEntity.class);
		query.setParameter("position", position);
		return query.getResultList();
	}

	//"select employee from EmployeeEntity employee where :officeEntity = employee.officeEntity and :car member of employee.cars)", EmployeeEntity.class);

	//select employee from EmployeeEntity employee join employee.cars car where (employee.officeEntity.id = :officeId and car.id = :carId and car member of employee.cars
}
