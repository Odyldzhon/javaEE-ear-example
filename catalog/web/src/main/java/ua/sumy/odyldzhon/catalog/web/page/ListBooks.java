package ua.sumy.odyldzhon.catalog.web.page;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.javatuples.Quartet;

import ua.sumy.odyldzhon.catalog.model.CatalogFacade;

@Named("ListBooks")
@RequestScoped
public class ListBooks {
	
	@EJB
	private CatalogFacade facade;
	
	private String authorName;
	private String authorSurname;
	private String title;
	
	@SuppressWarnings("rawtypes")
	public List<Quartet> getBooks(){
		if(title != null)
			return facade.getBooksByTitle(title);
		else if(authorName != null && authorSurname != null)
			return facade.getBooksByAuthor(authorName, authorSurname);
		else
			return facade.getBooks();
	}
	
	@SuppressWarnings("rawtypes")
	public void removeBook(Quartet book){
		facade.removeBook(book);
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorSurname() {
		return authorSurname;
	}

	public void setAuthorSurname(String authorSurname) {
		this.authorSurname = authorSurname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
