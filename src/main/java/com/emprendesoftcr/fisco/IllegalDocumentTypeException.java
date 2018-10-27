package com.emprendesoftcr.fisco;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public class IllegalDocumentTypeException extends Exception {
    
    private final String type;
    
    public static IllegalDocumentTypeException instance(String message, String type) {
        return new IllegalDocumentTypeException(message, type);
    }

    public static IllegalDocumentTypeException instance(String message,
                                                        Throwable throwable, String type) {
         return new IllegalDocumentTypeException(message, throwable, type);
    }
    
    private IllegalDocumentTypeException(String message, String type) {
        super(message);
        this.type = type;
    }

    private IllegalDocumentTypeException(String message, Throwable throwable, String type) {
        super(message, throwable);
        this.type = type;
    }
    
    public String getType () {
        return this.type;
    }
}
