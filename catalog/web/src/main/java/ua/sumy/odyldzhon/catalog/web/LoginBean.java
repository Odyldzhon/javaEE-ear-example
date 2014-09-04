package ua.sumy.odyldzhon.catalog.web;  
  
import java.io.Serializable;   
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;  
import javax.faces.context.FacesContext;  
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest; 


@Named("loginBean")
@RequestScoped
public class LoginBean implements Serializable {  
    
	private static final long serialVersionUID = 1L;  
	
	private String username;
	private String password;
    
	public String login() throws Exception {  
		ExternalContext cntxt = FacesContext.getCurrentInstance().getExternalContext();  
		HttpServletRequest req = (HttpServletRequest) cntxt.getRequest();  
		try {  
			req.login(getUsername(), getPassword());    
			return "/catalog/index.xhtml";  
		}catch(Exception e) {  
			cntxt.getFlash().put("message", "Invalid login. "
					+ "Please try again");
		    return "/login.xhtml?faces-redirect=true"; 
		}
	}  
    
	public String logout() {  
		ExternalContext cntxt = FacesContext.getCurrentInstance().getExternalContext();  
		HttpServletRequest req = (HttpServletRequest) cntxt.getRequest();
		try {  
			req.logout(); 
			return "/login.xhtml?faces-redirect=true";
		}catch(Exception e) {  
			return "/login.xhtml?faces-redirect=true"; 
		}  
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}  
	
	
}  