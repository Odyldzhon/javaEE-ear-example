package ua.sumy.odyldzhon.catalog.model.datasource.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import ua.sumy.odyldzhon.catalog.model.datasource.entity.EntityMark;

public class AbstractPesistenceService<K, E extends EntityMark> implements PersistenceService<K, E>{

	protected Class<E> entityClass;
	
	protected EntityManager em;
	
	public AbstractPesistenceService(EntityManager em){
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	protected void init(){
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}

	@Override
	public E save(E entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public E update(E entity) {
		em.merge(entity);
		em.flush();
		return entity;
	}

	@Override
	public void remove(E entity) {
		em.remove(entity);
		
	}

	@Override
	public E findById(K id) {
		return em.find(entityClass, id);
	}
	
	@Override
	public Long getTotalResoult() {
		return null;
	}
	
	@Override
	public List<E> findAll() {
		return null;
	}
	
}
