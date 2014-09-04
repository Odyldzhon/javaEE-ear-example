package ua.sumy.odyldzhon.catalog.model.datasource.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="books",
	uniqueConstraints= @UniqueConstraint(
			columnNames={"title", "description","publishing_date"}
	)
)
public class Book implements Serializable, EntityMark {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "book_id")
	private BigInteger bookId;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "publishing_date", nullable = false)
	private Date publishingDate;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "nix_references",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "author_id"))
	private List<Author> authors;
	
	public Book() {
		super();
	}
	public Book(String title, String description, Date publishingDate){
		setTitle(title);
		setDescription(description);
		setPublishingDate(publishingDate);
	}

	
	public BigInteger getBookId() {
		return bookId;
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
	public Date getPublishingDate() {
		return publishingDate;
	}
	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", description="
				+ description + ", publishingDate=" + publishingDate
				+ ", authors=" + authors + "]";
	}
}
