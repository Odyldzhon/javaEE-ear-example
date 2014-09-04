package ua.sumy.odyldzhon.catalog.model.datasource.service;

import java.util.List;

import javax.persistence.EntityManager;

import ua.sumy.odyldzhon.catalog.model.datasource.entity.Role;
import ua.sumy.odyldzhon.catalog.model.datasource.entity.RoleKey;



/**
 * This class can be use for creating new users
 *
 */
public class RolePersistenceService extends 
		AbstractPesistenceService<RoleKey, Role> {
	
	public RolePersistenceService(EntityManager em) {
		super(em);
	}

	@Override
	public Long getTotalResoult() {
		return (Long)em.createNamedQuery("select count(r) from Role r").getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findAll() {
		return em.createNamedQuery("select r from Role r").getResultList();
	}
}
