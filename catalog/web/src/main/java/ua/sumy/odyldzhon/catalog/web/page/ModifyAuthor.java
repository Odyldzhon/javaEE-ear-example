package ua.sumy.odyldzhon.catalog.web.page;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;

import org.javatuples.Pair;

import ua.sumy.odyldzhon.catalog.model.CatalogFacade;

@Named("ModifyAuthor")
@RequestScoped
public class ModifyAuthor {
	
	@EJB
	private CatalogFacade facade;
	
	private String name;
	private String surname;

	private String oldName;
	private String oldSurname;
	
	@SuppressWarnings("rawtypes")
	public String addUpdateAuthor(){
		String result;
		if(oldName!=null && oldSurname != null){
			Pair oldPair = Pair.with(getOldName(), getOldSurname());
			Pair newPair = Pair.with(getName(), getSurname());
			result = facade.updateAuthor(oldPair, newPair);
		}else{
			Pair pair = Pair.with(getName(), getSurname());
			result = facade.addAuthor(pair);
		}
		Flash flash = FacesContext.getCurrentInstance().
				getExternalContext().getFlash();
		flash.put("result", result);
		if(result.equals("saved") || result.equals("updated"))
			return "listAuthors";
		else
			return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getOldSurname() {
		return oldSurname;
	}

	public void setOldSurname(String oldSurame) {
		this.oldSurname = oldSurame;
	}
}
