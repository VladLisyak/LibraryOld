package ua.nure.lisyak.SummaryTask4.exception;

/**
 * {@code DAException} is Data Access Exception that is thrown 
 *  when error occurred while manipulating with DB. Is is thrown in DAOs.
 */
public class DAException extends RuntimeException {

	private static final long serialVersionUID = 1592459971630626629L;

	/**
     * Creates a new {@code DAException} object with a specified message and initial error.
     *
     * @param reason message of exception
     * @param cause exception that caused current exception
     */
    public DAException(String reason, Throwable cause) {
        super(reason, cause);
    }

}
