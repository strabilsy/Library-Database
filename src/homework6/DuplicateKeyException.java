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
/**
 * The DuplicateKeyException class is thrown to indicate that there is another book with the same key is already recorded
 */
public class DuplicateKeyException extends Exception {
	public DuplicateKeyException(String msg) {
		super(msg);
	}
}
