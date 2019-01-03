package com.emprendesoftcr;

import java.io.IOException;
import java.util.Date;

import net.sf.jasperreports.engine.JRException;

public class leo {

	public static void main(String[] args) throws JRException, IOException {

		 Date fecha1 = new Date();
		 Date fecha2 = new Date();
		 long tiempoInicial=fecha1.getTime();
		 long tiempoFinal=fecha2.getTime(); 
		 long resta=tiempoFinal - tiempoInicial;
		 
		 //el metodo getTime te devuelve en mili segundos para saberlo en mins debes hacer
		 resta=resta /(1000*60);
//		DecimalFormat formateador = new DecimalFormat("###,###.00");
//		// Este daria a la salida 1,000
		System.out.println(resta);
//
//		// Este otro 10,000
//		System.out.println(formateador.format(10000));
		
//		String numeros = "`distribuidoramaoskar@hotmail.com,dcamacho@binatekcr.com";
//    String[] numerosComoArray = numeros.split("`");
//    numerosComoArray = numeros.split("`");
//    numerosComoArray = numeros.split(",");
//    for (int i = 0; i < numerosComoArray.length; i++) {
//        System.out.println(numerosComoArray[i]);
//    }

	}

}
