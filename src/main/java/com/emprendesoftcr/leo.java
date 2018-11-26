package com.emprendesoftcr;

import java.io.IOException;
import java.text.DecimalFormat;

import net.sf.jasperreports.engine.JRException;

public class leo {

	public static void main(String[] args) throws JRException, IOException {

		DecimalFormat formateador = new DecimalFormat("###,###.00");
		// Este daria a la salida 1,000
		System.out.println(formateador.format(100000000.8585588888888));

		// Este otro 10,000
		System.out.println(formateador.format(10000));

	}

}
