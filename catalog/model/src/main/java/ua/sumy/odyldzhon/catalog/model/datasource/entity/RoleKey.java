package ua.sumy.odyldzhon.catalog.model.datasource.entity;

import java.io.Serializable;
import java.math.BigInteger;

public class RoleKey implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private BigInteger userId;
	private String role;
	
	@Override
	public int hashCode(){
		return (int)(getUserId().hashCode()
				^getRole().hashCode()>>>32);
	}
	
	@Override
	public String toString(){
		return "user_id = "+getUserId()+" role = "+getRole();
	}
	
	@Override
	public boolean equals(Object object){
		if(object == null)
			return false;
		if(object == this)
			return true;
		if(this.getClass().equals(object.getClass())){
			RoleKey key = (RoleKey)object;
			return (key.getUserId().equals(getUserId())
					&& key.getRole().equals(getRole()));
		}else
			return false;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUser(BigInteger user) {
		this.userId = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
