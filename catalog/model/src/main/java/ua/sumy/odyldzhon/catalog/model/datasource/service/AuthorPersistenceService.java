package ua.sumy.odyldzhon.catalog.model.datasource.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ua.sumy.odyldzhon.catalog.model.datasource.entity.Author;
import ua.sumy.odyldzhon.catalog.model.datasource.entity.Book;

public class AuthorPersistenceService extends
		AbstractPesistenceService<BigInteger, Author> {
	
	public AuthorPersistenceService(EntityManager em) {
		super(em);
	}

	@Override
	public Long getTotalResoult() {
		return (Long)em.createNamedQuery("select count(a) from Author a").getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Author> findAll() {
		return em.createQuery("select a from Author a").getResultList();
	}
	
	public Author findByFullName(String name, String surname){
		Query query = em.createQuery(
						"select a from Author a "
						+ "where a.name = :name "
						+ "and a.surname = :surname");
		query.setParameter("name", name);
		query.setParameter("surname", surname);
		return (Author)query.getSingleResult();
	}
	
	public Author findAuthorByUniqueValue(String name, String surname){
		Query query = em.createQuery(
				"select a from Author a "
				+ "where a.name = :name "
				+ "and a.surname = :surname ");
		query.setParameter("name", name);
		query.setParameter("surname", surname);
		return (Author)query.getSingleResult();
	}
	
	public List<Author> getAndCheckAuthors(List<Author> authors){
		List<Author> result = new ArrayList<Author>();
		for(Author author : authors){
			result.add(findAuthorByUniqueValue(
					author.getName(),author.getSurname()));
		}
		return result;
	}
	
	public void removeBooksFromAuthor(Author author, List<Book> books){
		for(Book book : books)
			book.getAuthors().remove(author);
	}
	
}
