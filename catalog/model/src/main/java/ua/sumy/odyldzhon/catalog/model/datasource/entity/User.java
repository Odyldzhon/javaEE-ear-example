package ua.sumy.odyldzhon.catalog.model.datasource.entity;

import java.io.Serializable;
import java.lang.String;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "users", 
	uniqueConstraints = @UniqueConstraint(
		columnNames = {"name", "pass"}
	)
)
public class User implements Serializable, EntityMark {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger userId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "pass", nullable = false)
	private String pass;
	
	@OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE,
			fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName="user_id")
	private List<Role>roles;
	
	public User() {
		super();
	}   
	
	public User(String name, String pass){
		setName(name);
		setPass(pass);
	}

	public BigInteger getUserId() {
		return userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getPass() {
		return this.pass;
	}

	public void setPass(String surname) {
		this.pass = surname;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", pass=" + pass
				+ "]";
	}
   
}
