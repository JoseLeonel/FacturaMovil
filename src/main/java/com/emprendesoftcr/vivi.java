package com.emprendesoftcr;

import java.math.BigDecimal;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;

public class vivi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Double valor =9.29204;
		
//		521.6814159292036
//		523.6460176991151
	
		Boolean resultado =  Boolean.FALSE;
		String numero = valor.toString();
		System.out.println("Cantidad decimales "+ Utils.Maximo5Decimales(valor));
		
		
		String[] cantidadDecimales = valor.toString().split(".");
		
		String[] splitter = valor.toString().split("\\.");
		splitter[0].length();   // Before Decimal Count
		splitter[1].length();   // After  Decimal Count
		
		System.out.println("Cantidad decimales "+ splitter[1].toString());
		
		if (splitter[1].length()>5 ) {
				String digitos = splitter[1];
				
				
				int num=Character.getNumericValue( digitos.charAt(5));
				if  (num > 5) {
					System.out.println(" es: "+ num);
					System.out.println(" es mayor 0 ");
				}else {
					System.out.println(" es menor 0 ");
				}
		}else {
			System.out.println(" no es mayor a 5 decimales ");
		}
			
		System.out.println(Utils.roundFactura(valor, 5));
		
		
	}
	
	public static Double aplicarRedondeo(Double valor) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		String[] splitter = valor.toString().split("\\.");
		splitter[0].length(); // Before Decimal Count
		splitter[1].length(); // After Decimal Count
		if (splitter[1].length() > 5) {
			String digitos = splitter[1];
			String decimales = digitos.substring(0, 5);
			String valor1  = splitter[0] + "." + decimales;
			resultado = Double.parseDouble(valor1);
		}

		return resultado;

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
