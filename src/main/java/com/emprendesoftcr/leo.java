package com.emprendesoftcr;

import java.text.DecimalFormat;

public class leo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			DecimalFormat formateador = new DecimalFormat("###,###.00");
		//Este daria a la salida 1,000
		System.out.println (formateador.format (100000000.8585588888888));

		//Este otro 10,000
		System.out.println (formateador.format (10000));



	}

}
