package com.emprendesoftcr.error;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public class P12Exception extends Exception {
    

	

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public static P12Exception instance(String message) {
        return new P12Exception(message);
    }

    public static P12Exception instance(String message, Throwable throwable) {
         return new P12Exception(message, throwable);
    }
    
    private P12Exception(String message) {
        super(message);
    }

    private P12Exception(String message, Throwable throwable) {
        super(message, throwable);
    }
}
