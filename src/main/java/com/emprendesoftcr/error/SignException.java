package com.emprendesoftcr.error;


public class SignException extends Exception {
    
   
	private static final long serialVersionUID = 1L;

		public static SignException instance(String message) {
        return new SignException(message);
    }

    public static SignException instance(String message, Throwable throwable) {
         return new SignException(message, throwable);
    }
    
    private SignException(String message) {
        super(message);
    }

    private SignException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
