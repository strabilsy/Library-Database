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

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

import big.data.*;

/**
 * The HashedLibrary class contains information for all books saved in the electronic database. 
 */
public class HashedLibrary implements Serializable {
	private HashMap<String, Book> books;
	
	public HashedLibrary() {
		books = new HashMap<String, Book>();
	}
	
	/**
	 * Records a book into the hash and print a message if successfully recorded. 
	 * If another book with the same key is already recorded, throw an exception.
	 * 
	 * @param title
	 * @param author
	 * @param publisher
	 * @param isbn
	 * 
	 * <dt><b>Precondition:</b><dd> The book being added is not already recorded in the library
	 * 
	 * @throws DuplicateKeyException Indicates that there is another book with the same key already recorded
	 */
	public void addBook(String title, String author, String publisher, String isbn) throws DuplicateKeyException{
		Book newBook = new Book(title, author, publisher, isbn);
		if (books.put(isbn, newBook) != null)
			throw new DuplicateKeyException(newBook + " is already recorded.");
		else {
			System.out.println(newBook + " recorded.");
		}
	}
	
	/**
	 * Using the fileName provided, parses a file for XML file names. 
	 * Adds a record for each XML file name provided, and prints a message if a Book id recorded successfully.
	 * 
	 * @param fileName
	 * 
	 * <dt><b>Precondition:</b><dd> The books being added are not already recorded in the library
	 * 
	 * @throws DuplicateKeyException Indicates that there is another book with the same key already recorded
	 */
	public void addAllBookInfo(String fileName) {

		Scanner reader;
		try {
			reader = new Scanner(new File(fileName));
			String title, author, publisher, isbn;
			String xmlFileName;

			while (reader.hasNext()) {
				try {
					xmlFileName = reader.nextLine();
					DataSource ds = DataSource.connect("http://www.cs.stonybrook.edu/~cse214/hw/hw6/" + xmlFileName + ".xml").load();
					title = ds.fetchString("title");
					author = ds.fetchString("author");
					publisher = ds.fetchString("publisher");
					isbn = ds.fetchString("isbn");
					addBook(title, author, publisher, isbn);
				}catch (DuplicateKeyException e2) {
					System.out.println(e2.getMessage());
				} catch (DataSourceException e) {
					//System.out.println("That XML file does not exist or could not be read.");
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Using the isbn as the key, looks for a particular book in the library
	 * 
	 * @param isbn
	 * @return the Book reference if it exists, null otherwise
	 */
	public Book getBookByisbn(String isbn) {
		return books.get(isbn);
	}
	
	/**
	 * Prints all the books stored in the library
	 */
	public void printCatalog() {
		System.out.print(String.format("%-15s%-40s%-30s%-36s", "Book ISBN", "Title", "Author", "Publisher")
				+ "\n--------------------------------------------------------------------------------------"
				+ "------------------------------------\n");
		for (Book book: books.values()){
			System.out.printf("%-15s%-40s%-30s%-36s\n", book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher());
		} 
	}
	
	/**
	 * Returns the number of books in the library
	 * 
	 * @return the number of books in the library
	 */
	public int size() {
		return books.size();
	}
	
}
