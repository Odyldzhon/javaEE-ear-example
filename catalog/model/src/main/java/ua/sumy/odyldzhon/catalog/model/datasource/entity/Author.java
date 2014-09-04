package ua.sumy.odyldzhon.catalog.model.datasource.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="authors",
	uniqueConstraints=@UniqueConstraint(
			columnNames = {"name" , "surname"}
	)
)
public class Author implements Serializable, EntityMark{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "author_id")
	private BigInteger authorId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "surname", nullable = false)
	private String surname;
	
	@ManyToMany(mappedBy = "authors", fetch=FetchType.LAZY)
	private List<Book> books;
	
	public Author(){
		super();
	}
	
	public Author(String name, String surname){
		setName(name);
		setSurname(surname);
	}

	public BigInteger getAuthorId() {
		return authorId;
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

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", name=" + name + ", surname="
				+ surname + ", books=" + books + "]";
	}
	
	
	
}
