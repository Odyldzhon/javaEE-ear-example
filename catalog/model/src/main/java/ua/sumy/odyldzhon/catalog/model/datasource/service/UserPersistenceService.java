package ua.sumy.odyldzhon.catalog.model.datasource.service;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;

import ua.sumy.odyldzhon.catalog.model.datasource.entity.User;

/**
 * 
 * This class can be use for creating new users
 *
 */
public class UserPersistenceService extends
		AbstractPesistenceService<BigInteger, User> {
	
	public UserPersistenceService(EntityManager em) {
		super(em);
	}

	@Override
	public Long getTotalResoult() {
		return (Long)em.createNamedQuery("select count(u) from User u").getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		return em.createNamedQuery("select u from User u").getResultList();
	}
}
