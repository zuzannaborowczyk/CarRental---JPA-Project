package com.capgemini.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.LoanDao;
import com.capgemini.domain.CustomerEntity;
import com.capgemini.domain.LoanEntity;

@Repository
public class LoanDaoImpl extends AbstractDao<LoanEntity, Long> implements LoanDao {

	@PersistenceContext
	EntityManager entityManager;


	
	
}
