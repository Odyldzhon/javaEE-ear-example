package ua.sumy.odyldzhon.catalog.model.datasource.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ua.sumy.odyldzhon.catalog.model.datasource.entity.Author;
import ua.sumy.odyldzhon.catalog.model.datasource.entity.Book;

public class BookPersistenceService extends
	AbstractPesistenceService<BigInteger, Book>{
	
	
	public BookPersistenceService(EntityManager em) {
		super(em);
	}

	@Override
	public Long getTotalResoult() {
		return (Long)em.createQuery("select count(b) from Book b").getSingleResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Book> findAll() {
		return em.createQuery("select b from Book b").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> findBooksByAuthor(Author author){
		Query query = em.createQuery(
				"select b from Book b "
				+ "join b.authors a where a = :author"
		);
		query.setParameter("author", author);
		return (List<Book>)query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> findBooksByTitle(String title){
		Query query = em.createQuery(
				"select b from Book b"
				+ " where b.title like :title"
		);
		query.setParameter("title", "%"+title+"%");
		return (List<Book>)query.getResultList();
	}
	
	public Book findBookByUniqueValue(String title, String description, 
			Date publishingDate){
		Query query = em.createQuery(
				"select b from Book b "
				+ "where b.title = :title "
				+ "and b.description = :description "
				+ "and b.publishingDate = :publishingDate");
		query.setParameter("title", title);
		query.setParameter("description", description);
		query.setParameter("publishingDate", publishingDate);
		return (Book)query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> findBooksSoleAuthor(BigInteger authorId){
		Query query = em.createQuery(
				"select distinct b from Book b, in(b.authors) as a "
				+ "where a.authorId = :authorId "
				+ "and size(b.authors) = 1");
		query.setParameter("authorId", authorId);
		return (List<Book>) query.getResultList();
	}
	
	public void remove(List<Book> books){
		Query query = em.createQuery(
				"delete from Book b "
				+ "where b in ( :books )");
		query.setParameter("books", books);
		query.executeUpdate();
	}
}
