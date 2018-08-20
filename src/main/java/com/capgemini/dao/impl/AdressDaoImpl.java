package com.capgemini.dao.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.dao.AdressDao;
import com.capgemini.dao.UserDao;
import com.capgemini.domain.AdressEntity;
import com.capgemini.domain.UserEntity;

@Repository
@Transactional(Transactional.TxType.REQUIRED)
public class AdressDaoImpl extends AbstractDao<AdressEntity, Long> implements AdressDao{
	
	@Autowired
	UserDao userDao;
	
	@Override
	@Transactional(Transactional.TxType.REQUIRED)
	public void assignAddressToUser(UserEntity user, AdressEntity address) {
		user = userDao.find(user.getId());
		address = findOne(address.getId());
		
	//	address.setUser(user);
		user.getAddresses().add(address);
		
		
	}

}
