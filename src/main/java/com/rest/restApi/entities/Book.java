package com.rest.restApi.entities;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
public class Book {

	@Id
	@NotNull
	@Column(name = "id" ,nullable = false,updatable = false)
	private Long Id;
	
	@NotNull
	@Size(max = 64)
	@Column(name = "author", nullable = false)
	private String author;
	
	@NotNull
	@Size(max = 64)
	@Column(name = "title", nullable = false)
	private String title;

	public Book() {

	}

	/**
	 * @param id
	 * @param author
	 * @param title
	 */
	public Book(@NotNull final Long id, @NotNull @Size(max = 64)  final String author, @NotNull @Size(max = 64) final String title) {
		super();
		Id = id;
		this.author = author;
		this.title = title;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		Id = id;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book [Id=" + Id + ", author=" + author + ", title=" + title + "]";
	}


	
	
}
