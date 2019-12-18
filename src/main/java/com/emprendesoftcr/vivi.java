package com.emprendesoftcr;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.Year;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.emprendesoftcr.Utils.Constantes;

public class vivi {

	public static void main(String[] args) throws ParseException, IOException, ParserConfigurationException, SAXException {
//      File directorio = Utils.crearDirectorioServidor("109360677");
//      if(directorio.exists()) {
//      	System.out.println("Listo");
//      }
		
//		
		
//		 String s = Utils.leerXMLServidor();
//		 System.out.println(s);
		// TODO Auto-generated method stub
//		Double valor = 9.29204;
//		
//		Double resultado = Utils.Maximo6Decimales(4689.999991d);
//		Double resultado1 = Utils.roundFactura(4689.99999, 2);
		
			
////	 
//       	System.out.println("resultado: "+resultado);
//       	System.out.println("resultado: "+resultado1);
//       	Boolean valor = Utils.aplicarRedondeo(4689.999991d);
//       	System.out.println("resultado: "+valor);
//		String resultado= "45123";
//			resultado = FacturaElectronicaUtils.replazarConZeros(resultado,Constantes.FORMATO_CODIGO_ACTIVIDAD);
//			System.out.println(resultado);
//
////		521.6814159292036
//		523.6460176991151

//		Boolean resultado =  Boolean.FALSE;
//		String numero = valor.toString();
//		System.out.println("Cantidad decimales "+ Utils.Maximo5Decimales(valor));
//		
//		
//		String[] cantidadDecimales = valor.toString().split(".");
//		
//		String[] splitter = valor.toString().split("\\.");
//		splitter[0].length();   // Before Decimal Count
//		splitter[1].length();   // After  Decimal Count
//		
//		System.out.println("Cantidad decimales "+ splitter[1].toString());
//		
//		if (splitter[1].length()>5 ) {
//				String digitos = splitter[1];
//				
//				
//				int num=Character.getNumericValue( digitos.charAt(5));
//				if  (num > 5) {
//					System.out.println(" es: "+ num);
//					System.out.println(" es mayor 0 ");
//				}else {
//					System.out.println(" es menor 0 ");
//				}
//		}else {
//			System.out.println(" no es mayor a 5 decimales ");
//		}
//		if (validarCedula("9999999918899")) {
//			System.out.println("cedula diferente caracter");
//		} else {
//			System.out.println("cedula igual caracter");
//		}
		
		Year anno = Year.now(); 
    System.out.printf("Este año:" + anno.getValue()); 
//		List<String>  ejemplos = Arrays.asList("leo","alberto","pedro");
//		String ejemplo = ejemplos.stream().collect(Collectors.joining(","));
//		System.out.println(ejemplo);

//		System.out.println(Utils.roundFactura(valor, 5));

	}
	


	public static Boolean validarCedula(String valor) {
		Boolean resultado = Boolean.FALSE;
		if (valor == null) {
			return Boolean.FALSE;
		}
		if (valor.length() == 0) {
			return Boolean.FALSE;
		}
		char letraIgual = valor.charAt(1);
		for (int i = 0; i < valor.length(); i++) {
			char c = valor.charAt(i);
			if (letraIgual != c) {
				resultado = Boolean.TRUE;
			}
		}
		return resultado;
	}

	public static Double aplicarRedondeo(Double valor) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		String[] splitter = valor.toString().split("\\.");
		splitter[0].length(); // Before Decimal Count
		splitter[1].length(); // After Decimal Count
		if (splitter[1].length() > 5) {
			String digitos = splitter[1];
			String decimales = digitos.substring(0, 5);
			String valor1 = splitter[0] + "." + decimales;
			resultado = Double.parseDouble(valor1);
		}

		return resultado;

	}

	private static BigDecimal truncateDecimal(double x, int numberofDecimals) {
		if (x > 0) {
			return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
		} else {
			return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
		}
	}

}
