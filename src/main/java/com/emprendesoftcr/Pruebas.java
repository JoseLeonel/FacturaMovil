package com.emprendesoftcr;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Pruebas {

	public static void main(String[] args) {
		
	  Double valorIni =133D;
	  Double valor =0D;
	  Integer cont =100;
	  for (int i = 1; i < cont; i++) {
			valor =  valorIni * new Double(i);
		  Double valorsinimpuestos = valor / 1.13;
		  Double impuestos = valor  - valorsinimpuestos;
		  System.out.println("Precio Unitario redondeo " + valorsinimpuestos +  "  --  " + round(valorsinimpuestos, 5));
		  System.out.println("Total de impuesto " + impuestos +  "  --  " + round(impuestos, 5));
		  System.out.println("total " + valor +  "  --  " + (round(impuestos, 5) + round(valorsinimpuestos, 5)));

	  }
	  

	}

	
	
	private static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();
 
    BigDecimal bd = new BigDecimal(Double.toString(value));
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
}
}
