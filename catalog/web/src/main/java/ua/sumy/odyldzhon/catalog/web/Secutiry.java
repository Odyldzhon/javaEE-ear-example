package ua.sumy.odyldzhon.catalog.web;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("Security")
@RequestScoped
public class Secutiry {
	
	public boolean isUserAllowedAccess(){
		return FacesContext.getCurrentInstance().getExternalContext().
				isUserInRole("ADMIN");
	}

}
