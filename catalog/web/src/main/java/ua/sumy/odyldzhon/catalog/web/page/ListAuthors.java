package ua.sumy.odyldzhon.catalog.web.page;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.javatuples.Pair;

import ua.sumy.odyldzhon.catalog.model.CatalogFacade;

@Named("ListAuthors")
@RequestScoped
public class ListAuthors{
	
	@EJB
	private CatalogFacade facade;
	
	@SuppressWarnings("rawtypes")
	public List<Pair> getAuthors(){
		return facade.getAuthors();
	}
	
	@SuppressWarnings("rawtypes")
	public void remove(Pair author){
		facade.removeAuthor(author);
	}

}
