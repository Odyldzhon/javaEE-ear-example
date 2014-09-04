package ua.sumy.odyldzhon.catalog.model.datasource.service;

import java.util.List;

interface PersistenceService<K,E> {
	E save(E entity);
	
	E update(E entity);
	
	void remove(E entity);
	
	E findById(K id);
	
	Long getTotalResoult();
	
	List<E> findAll();
}
