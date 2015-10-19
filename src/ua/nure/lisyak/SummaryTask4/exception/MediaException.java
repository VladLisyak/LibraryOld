package ua.nure.lisyak.SummaryTask4.exception;

/**
 * {@code FileException} is thrown when 
 *  the error occurs while operating with media files(images).
 */
public class MediaException extends RuntimeException{
	
	private static final long serialVersionUID = -1518201963107573362L;

	/**
     * Creates a new MediaException object with message.
     *
     * @param reason message of the exception
     */
    public MediaException(String reason){
        super(reason);
    }

    /**
     * Creates a new MediaException object with a specified reason message and initial exception.
     *
     * @param reason  reason message of exception
     * @param cause   initial exception
     */
    public MediaException(String reason, Throwable cause) {
        super(reason, cause);
    }

    /**
     * Creates a new MediaException object with a specified initial error.
     *
     * @param cause initial exception
     */
    public MediaException(Throwable cause) {
        super(cause);
    }

}
