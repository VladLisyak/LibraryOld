package ua.nure.lisyak.SummaryTask4.exception;


/**
 * ParserException is thrown when the {@link Parser} 
 * cannot extract or parse object.
 */
public class ParserException extends RuntimeException {

	private static final long serialVersionUID = -3927017346247474522L;

	/**
     * Creates a new ParserException object with a specified reason message and cause.
     *
     * @param reason message of the exception
     * @param cause  initial exception
     */
    public ParserException(String reason, Throwable cause) {
        super(reason, cause);
    }

}
