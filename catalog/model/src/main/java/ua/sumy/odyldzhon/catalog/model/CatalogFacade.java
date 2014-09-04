package ua.sumy.odyldzhon.catalog.model;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.LockType;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.javatuples.Pair;
import org.javatuples.Quartet;

import ua.sumy.odyldzhon.catalog.model.datasource.entity.*;
import ua.sumy.odyldzhon.catalog.model.datasource.service.*;

@Singleton
@LocalBean
@Startup
@DependsOn("ModelViewConverter")
@Lock(LockType.READ)
public class CatalogFacade {
	
	@EJB
	private ModelViewConverter converter;
	
	@PersistenceContext(unitName = "postgresqlP")
	public EntityManager em;
	
	@Resource 
	SessionContext ctx;
	
	private AuthorPersistenceService authorService;
	private BookPersistenceService bookService;
	
	@PostConstruct
	void init(){
		authorService = new AuthorPersistenceService(em);
		bookService = new BookPersistenceService(em);
	}
	
	
	@SuppressWarnings("rawtypes")
	public List<Quartet>getBooks(){
		List<Book> books = bookService.findAll();
		return converter.convertViewBooks(books);
	}
	
	@SuppressWarnings("rawtypes")
	public List<Quartet>getBooksByAuthor(String name, String surname){
		Author author = authorService.findAuthorByUniqueValue(name, surname);
		List<Book> books = bookService.findBooksByAuthor(author);
		return converter.convertViewBooks(books);
	}
	
	@SuppressWarnings("rawtypes")
	public List<Quartet>getBooksByTitle(String titile){
		List<Book> books = bookService.findBooksByTitle(titile);
		return converter.convertViewBooks(books);
	}
	
	@SuppressWarnings("rawtypes")
	public List<Pair>getAuthors(){
		List<Author> authors = authorService.findAll();
		return converter.convertViewAuthors(authors);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@SuppressWarnings("rawtypes")
	public String addBook(Quartet quartet){
		try{
			Book book = converter.convertModelBook(quartet);
			List<Author> authors = book.getAuthors();
			authors = authorService.getAndCheckAuthors(authors);
			book.setAuthors(authors);
			bookService.update(book);
			return "saved";
		}catch(PersistenceException e){
			ctx.setRollbackOnly();
			//Optimistic case
			return "duplicate";
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@SuppressWarnings("rawtypes")
	public String addAuthor(Pair pair){
		try{
			Author author = converter.convertModelAuthor(pair);
			authorService.save(author);
			return "saved";
		}catch(PersistenceException e){
			ctx.setRollbackOnly();
			//Optimistic case
			return "duplicate";
		}

	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@SuppressWarnings("rawtypes")
	public String updateAuthor(Pair oldPair, Pair newPair){
		try{
			Author oldAuthor = converter.convertModelAuthor(oldPair);
			oldAuthor = authorService.findByFullName(oldAuthor.getName(), 
					oldAuthor.getSurname());
			Author newAuthor = converter.convertModelAuthor(newPair);
			oldAuthor.setName(newAuthor.getName());
			oldAuthor.setSurname(newAuthor.getSurname());
			authorService.update(oldAuthor);
		}catch(PersistenceException e){
			ctx.setRollbackOnly();
			//Optimistic case
			return "duplicate";
		}
		return "updated";
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@SuppressWarnings("rawtypes")
	public String updateBook(Quartet oldQuartet, Quartet newQuartet){
		try{
			Book oldBook = converter.convertModelBook(oldQuartet);
			oldBook = bookService.findBookByUniqueValue(oldBook.getTitle(), 
					oldBook.getDescription(),
					oldBook.getPublishingDate());
			Book newBook = converter.convertModelBook(newQuartet);
			oldBook.setTitle(newBook.getTitle());
			oldBook.setDescription(newBook.getDescription());
			oldBook.setAuthors(authorService.getAndCheckAuthors(newBook.getAuthors()));
			oldBook.setPublishingDate(newBook.getPublishingDate());
			bookService.update(oldBook);
			return "updated";
		}catch(PersistenceException e){
			ctx.setRollbackOnly();
			//Optimistic case
			return "duplicate";
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@SuppressWarnings("rawtypes")
	public void removeAuthor(Pair pair){
		Author author = converter.convertModelAuthor(pair);
		author = authorService.findAuthorByUniqueValue(author.getName(),
				author.getSurname());
		List<Book> booksOnlyAuthor = bookService.findBooksSoleAuthor(author.getAuthorId());
		List<Book> books = bookService.findBooksByAuthor(author);
		authorService.removeBooksFromAuthor(author, books);
		if(booksOnlyAuthor.size()>0){
			bookService.remove(booksOnlyAuthor);
		}
		authorService.remove(author);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@SuppressWarnings("rawtypes")
	public void removeBook(Quartet quartet){
		Book book = converter.convertModelBook(quartet);
		book = bookService.findBookByUniqueValue(book.getTitle(), 
				book.getDescription(), book.getPublishingDate());
		bookService.remove(book);
	}
	
}
