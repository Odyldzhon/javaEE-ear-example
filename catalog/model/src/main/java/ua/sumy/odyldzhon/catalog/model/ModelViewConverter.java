package ua.sumy.odyldzhon.catalog.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.javatuples.Pair;
import org.javatuples.Quartet;

import ua.sumy.odyldzhon.catalog.model.datasource.entity.*;

@Singleton
@LocalBean
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN) 
public class ModelViewConverter {
	
	@SuppressWarnings("rawtypes")
	public List<Quartet> convertViewBooks(List<Book> books){
		List<Quartet> result = new ArrayList<Quartet>();
		List<Pair> authors;
		Quartet quartet;
		for(Book book : books){
			authors = new ArrayList<Pair>();
			for (Author author : book.getAuthors())
				authors.add(Pair.with(author.getName(), author.getSurname()));
			quartet = Quartet.with(book.getTitle(), 
				book.getDescription(),
				book.getPublishingDate(), 
				authors
			);
			result.add(quartet);
		}
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Book convertModelBook(Quartet quartet){
		Book book = new Book();
		book.setTitle((String)quartet.getValue0());
		book.setDescription((String)quartet.getValue1());
		book.setPublishingDate((Date)quartet.getValue2());
		List<Pair> pairs = (List<Pair>)quartet.getValue3();
		List<Author> authors = new ArrayList();
		for(Pair pair : pairs){
			authors.add(convertModelAuthor(pair));
		}
		book.setAuthors(authors);
		return book;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Pair> convertViewAuthors(List<Author> authors){
		List<Pair> result = new ArrayList<Pair>();
		Pair pair;
		for(Author author : authors){
			pair = Pair.with(author.getName(), 
				author.getSurname()
			);
			result.add(pair);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public Author convertModelAuthor(Pair pair){
		Author author = new Author();
		author.setName((String)pair.getValue0());
		author.setSurname((String)pair.getValue1());
		return author;
	}
}
