package com.capgemini.dao;

import com.capgemini.domain.AdressEntity;
import com.capgemini.domain.UserEntity;

public interface AdressDao extends Dao<AdressEntity, Long> {
	void assignAddressToUser(UserEntity user, AdressEntity address);
}
