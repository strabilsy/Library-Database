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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * The DatabaseDriver class contains a main method that interacts with the user
 */
public class DatabaseDriver {
	/**
	 * The main method presents a menu for the user to interact with. The menu has the following options:
	 * <br><br>
	 	(D) Displays all books in a neat tabular format.
	 	<br><br>
        (G) Get Book by ISBN
        <br><br>
        (L) Accepts a file name and records all books specified in the file.
        <br><br>
        (R) Prompts the user for book information, and record this book.
        <br><br>
        (Q) Terminates the program and save the data to library.obj.
        <br>
	 * @param args
	 */
	public static void main(String[] args) {
		String menu = "\n(D) Displays Books\n(G) Get Book\n(L) Load File\n(R) Record Book\n(Q) Quit\n"
				+ "\nEnter a selection: ";
		HashedLibrary myLibrary;
		try {
		     FileInputStream file = new FileInputStream("library.obj");
		     ObjectInputStream fin  = new ObjectInputStream(file);
		     myLibrary = (HashedLibrary) fin.readObject(); //readObject() returns Object, so must typecast to HashedLibrary
		     fin.close();
		     System.out.println("Loaded HashedLibrary from library.obj.\n");
		} catch(IOException | ClassNotFoundException e){
			System.out.println("library.obj is not found. Using a new HashedLibrary.\n");
			myLibrary = new HashedLibrary();
		}
		
		Scanner input;
		String choice;
		String title, author, publisher, isbn;
		char letter;
		do {
			System.out.print(menu);
			input = new Scanner(System.in);
		    choice = input.next();
		    System.out.println();
		    letter = choice.charAt(0);
		    switch(Character.toUpperCase(letter)) {

		    case('D'):
		    	
		    	if(myLibrary.size() == 0) {
		    		System.out.println("The library is empty.");
		    	}
		    	else {
		    		myLibrary.printCatalog();
		    	}
		    	break;
		    
		    case('G'):
		    	
		    	if(myLibrary.size() == 0) {
		    		System.out.println("The library is empty.");
		    	}
		    	else {
		    		System.out.print("Enter Book ISBN: ");
		    		isbn = input.next();
		    		Book book = myLibrary.getBookByisbn(isbn); 
		    		if (book != null) {
		    			System.out.print(String.format("\n%-15s%-40s%-30s%-36s", "Book ISBN", "Title", "Author", "Publisher")
		    					+ "\n--------------------------------------------------------------------------------------"
		    					+ "------------------------------------\n");
		    			System.out.printf("%-15s%-40s%-30s%-36s\n", isbn, book.getTitle(), book.getAuthor(), book.getPublisher());
		    		}
		    		else {
		    			System.out.println("There is no book in the library with that ISBN.");
		    		}
		    	}
		    	break;
		    	
		    case('L'):
		    	
		    	System.out.print("Enter the file to load: ");
			    String fileName = input.next();
			    System.out.println();
			    myLibrary.addAllBookInfo(fileName);
			    break;

		    case('R'):
		    	
		    	System.out.print("Enter book title: ");
		    	input.nextLine();
			    title = input.nextLine();
			    System.out.print("Enter book author: ");
			    author = input.nextLine();
			    System.out.print("Enter book publisher: ");
			    publisher = input.nextLine();
			    System.out.print("Enter book ISBN: ");
			    isbn = input.next();
			    
			    try {
					myLibrary.addBook(title, author, publisher, isbn);
				} catch (DuplicateKeyException e) {
			    	System.out.println(e.getMessage());
				}
			    break;
			    
		    case('Q'):
		    	try {
		    	      FileOutputStream file = new FileOutputStream("library.obj");
		    	      ObjectOutputStream fout = new ObjectOutputStream(file);
		    	      fout.writeObject(myLibrary); //Writes myLibrary to library.obj
		    	      fout.close();
		    	      System.out.println("\nHashedLibrary is saved into file library.obj.");
		    	} catch (IOException e){
		    		System.out.println("Could not save the data to library.obj");
		    	}
		    	System.out.print("Program terminating normally...");
		    	input.close();
		    	System.exit(0);
		    }
		}while(letter!='Q');
	}

}
