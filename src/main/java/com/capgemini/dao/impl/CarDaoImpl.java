package com.capgemini.dao.impl;


import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
@Repository
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	EmployeeDao employeeDao;

	@Override
	public void assignCarToOwner(CarEntity carToAssign, EmployeeEntity employeeToAssign) {
		EmployeeEntity newEmployee = employeeDao.findOne(employeeToAssign.getId());
		CarEntity newCar= findOne(carToAssign.getId());
		newEmployee.addCar(newCar);
		
	}

		@Override
	public Set<CarEntity> findByOwner(long ownersId) {
		 EmployeeEntity employeeEntity = employeeDao.findOne(ownersId);
		Set<CarEntity> carsByOwner = employeeEntity.getAllCars();
		return carsByOwner;
	}

	@Override
	public List<CarEntity> findByCarTypeAndCarBrand(String carType, String carBrand) {
		TypedQuery<CarEntity> query = entityManager.createQuery(
                "select car from CarEntity car where upper(car.carType) like concat(upper(:carType), '%') and upper(car.carBrand) like concat(upper(:carBrand), '%')", CarEntity.class);
        query.setParameter("carType", carType).setParameter("carBrand", carBrand);
        return query.getResultList();
	}

	@Override
	public List<CarEntity> findCarsByDifferentCustomers(int numberOfLoan) {
		TypedQuery<CarEntity> query = entityManager.createQuery(
		        "select car from CarEntity car where car.id in "
		        + "(select loan.car.id from LoanEntity loan group by loan.car.id "
		        + "having count(distinct loan.customer.id) > :numberOfLoan)", CarEntity.class);
		query.setParameter("numberOfLoan", numberOfLoan);
		        return query.getResultList();
	}

	@Override
	public List<Long> countCarsRentedInSpecificTime(Date dateFrom, Date dateTo) {
		TypedQuery<Long> query = entityManager.createQuery(
			 	"select count(loan) from LoanEntity loan where dateFrom < :dateFrom and dateTo > :dateTo",Long.class);
		query.setParameter("dateFrom", dateFrom);
		query.setParameter("dateTo", dateTo);
		return query.getResultList();
	}

	
}
