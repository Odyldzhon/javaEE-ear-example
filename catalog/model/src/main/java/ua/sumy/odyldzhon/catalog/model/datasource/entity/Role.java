package ua.sumy.odyldzhon.catalog.model.datasource.entity;

import java.io.Serializable;
import java.lang.String;
import java.math.BigInteger;

import javax.persistence.*;


@IdClass(RoleKey.class)
@Entity
@Table(name = "roles",
	uniqueConstraints = @UniqueConstraint(
		columnNames = {"user_id","role"}
	)
)
public class Role implements Serializable, EntityMark {

	private static final long serialVersionUID = 1L;
	

	@Id
	private BigInteger userId;
	
	@Id
	@Column(name = "role", nullable = false)
	private String role;

	public Role() {
		super();
	}   
	
	public Role(BigInteger userId, String role){
		setUserId(userId);
		setRole(role);
	}
 
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	
	
   
}
