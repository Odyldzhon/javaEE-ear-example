package ua.sumy.odyldzhon.catalog.web.page;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;

import org.javatuples.Pair;
import org.javatuples.Quartet;

import ua.sumy.odyldzhon.catalog.model.CatalogFacade;

@Named("ModifyBook")
@RequestScoped
public class ModifyBook{

	@EJB
	private CatalogFacade facade;
	
	private String title;
	private String description;
	private Date date;
	@SuppressWarnings("rawtypes")
	private List<Pair> selectedAuthors;
	
	private String oldTitle;
	private String oldDescription;
	private Date oldDate;
	
	@SuppressWarnings("rawtypes")
	public String addUpdateBook(){
		String result;
		if(oldTitle != null){
			Quartet oldPair = Quartet.with(getOldTitle(), getOldDescription(), 
					getOldDate(), getSelectedAuthors());
			Quartet newPair = Quartet.with(getTitle(), getDescription(), 
					getDate(), getSelectedAuthors());
			result = facade.updateBook(oldPair, newPair);
		}else{
			Quartet quartet = Quartet.with(title,description,date,selectedAuthors);
			result = facade.addBook(quartet);
		}
		Flash flash = FacesContext.getCurrentInstance().
				getExternalContext().getFlash();
		flash.put("result", result);
		if(result.equals("saved") || result.equals("updated"))
			return "listBooks";
		else
			return null;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Pair> getAuthors(){
		return facade.getAuthors();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@SuppressWarnings("rawtypes")
	public void setSelectedAuthors(List<Pair> authors){
		selectedAuthors = authors;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Pair> getSelectedAuthors() {
		return selectedAuthors;
	}
	
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getOldTitle() {
		return oldTitle;
	}

	public void setOldTitle(String oldTitle) {
		this.oldTitle = oldTitle;
		title = oldTitle;
	}

	public String getOldDescription() {
		return oldDescription;
	}

	public void setOldDescription(String oldDescription) {
		this.oldDescription = oldDescription;
		setDescription(oldDescription);
	}

	public Date getOldDate() {
		return oldDate;
	}

	public void setOldDate(Date oldDate){
		this.oldDate = oldDate;
		setDate(oldDate);
	}
}
