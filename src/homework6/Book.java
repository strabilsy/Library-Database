/**
 * Samier Trabilsy
 * Student ID: 109839226
 * Homework #6
 * Thursday: R04
 * Gustavo Poscidonio
 * Mahsa Torkaman
 * @author Samier
 */
package homework6;

import java.io.Serializable;

/**
 * The Book class represents a book with a title, author, publisher, and ISBN
 */
public class Book implements Serializable{
	private String title, author, publisher, isbn;
	
	public Book() {
		title = ""; 
		author = "";
		publisher = "";
		isbn = "";
	}
	
	public Book(String title, String author, String pub, String isbn) {
		this.title = title;
		this.author = author;
		publisher = pub;
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String toString() {
		return isbn + ": " + title + " by " + author;
	}
}
