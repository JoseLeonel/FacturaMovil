package com.emprendesoftcr.fisco;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public class ParsingInvoiceResponseException extends Exception {

    public static ParsingInvoiceResponseException instance(String message) {
        return new ParsingInvoiceResponseException(message);
    }

    public static ParsingInvoiceResponseException instance(String message, Throwable throwable) {
        return new ParsingInvoiceResponseException(message, throwable);
    }

    private ParsingInvoiceResponseException(String message) {
        super(message);
    }

    private ParsingInvoiceResponseException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
