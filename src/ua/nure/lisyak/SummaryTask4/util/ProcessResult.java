package ua.nure.lisyak.SummaryTask4.util;

import java.io.Serializable;

public final class ProcessResult implements Serializable {

	private static final long serialVersionUID = 3287417889188410730L;

	/**
     * Displays success of some process.
     */
    private boolean succeeded;

    /**
     * More datailed info about process.
     */
    private String message;

    /**
     * Gets result of some process
     *
     * @return {@code true} if process finished successfully, {@code false} otherwise
     */
    public boolean isSucceeded() {
        return succeeded;
    }

   
    public void setSucceeded(boolean succeeded){
        this.succeeded = succeeded;
    }

    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
