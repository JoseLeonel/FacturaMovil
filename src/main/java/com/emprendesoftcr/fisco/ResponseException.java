package com.emprendesoftcr.fisco;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public class ResponseException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public static ResponseException instance(String message) {
        return new ResponseException(message);
    }

    public static ResponseException instance(String message, Throwable throwable) {
        return new ResponseException(message, throwable);
    }

    private ResponseException(String message) {
        super(message);
    }

    private ResponseException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
