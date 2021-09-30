package com.rest.restApi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Entity
public class Book {

	@Id
	@NotNull
	@Column(name = "id", nullable = false, updatable = false)
	private Long Id;

	@NotNull
	@Size(max = 64, message = "max is 64 alpha")
	@Column(name = "author", nullable = false)
	private String author;

	@NotNull(message = "not null")
	@Size(max = 64, message = "max is 64 alpha")
	@Column(name = "title", nullable = false)
	private String title;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
	private Category category;

	public Book() {

	}

	public Book(@NotNull Long id, @NotNull @Size(max = 64) String author, @NotNull @Size(max = 64) String title,
			Category category) {
		super();
		Id = id;
		this.author = author;
		this.title = title;
		this.category = category;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return Category return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "Book [Id=" + Id + ", author=" + author + ", category=" + category + ", title=" + title + "]";
	}

}
