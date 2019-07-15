package com.emprendesoftcr;

import java.math.BigDecimal;

public class vivi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(truncateDecimal(530.973451, 5));
		
		
	}
	
	private static BigDecimal truncateDecimal(double x,int numberofDecimals)
	{
	    if ( x > 0) {
	        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
	    } else {
	        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
	    }
	}

}
