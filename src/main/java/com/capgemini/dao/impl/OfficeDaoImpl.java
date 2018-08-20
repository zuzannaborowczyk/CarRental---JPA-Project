package com.capgemini.dao.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;

@Repository
public class OfficeDaoImpl extends AbstractDao<OfficeEntity, Long> implements OfficeDao {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	EmployeeDao employeeRepository;

	@Override
	public void addEmployeeToOffice(EmployeeEntity employeeToAdd, long officeId) {

	}

	@Override
	public void removeEmployeeFromOffice(EmployeeEntity employeeToRemove, long officeId) {
		/*
		 * long employeeId = employeeToRemove.getId(); TypedQuery<OfficeEntity>
		 * query = entityManager.createQuery(
		 * "select office from OfficeEntity office where office.id like :employeeId"
		 * , OfficeEntity.class); query.setParameter("employeeId", employeeId);
		 * OfficeEntity currentOffice = query.getSingleResult();
		 * currentOffice.removeEmployee(employeeToRemove);
		 * entityManager.persist(currentOffice);
		 */

	}

	@Override
	public List<EmployeeEntity> getAllEmployees() {
		OfficeEntity officeEntity = new OfficeEntity();
		return officeEntity.getAllEmployees();
	}

}
