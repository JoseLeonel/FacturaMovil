package com.emprendesoftcr;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import com.itextpdf.text.DocumentException;


public class vivi {

	public static void main(String[] args) throws ParseException, FileNotFoundException, DocumentException {
		
		Double lado = 4d;
		Double lado1 = 4d;
		Double resultado = lado1 * lado;
		
		
		
		
		System.out.println("Yo naci:" + resultado);
		
		
		
//		
//		
//		String fechaF2 = "2020-07-01";
//
//		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
//
//		Date fecha1 = new Date();
//		Date fecha2 = formato.parse(fechaF2);
//
//		if(fecha1.before(fecha2)){
//		   System.out.println( "Fecha 1 es mayor o igual a fecha2" );
//		}else{
//		   System.out.println( "Fecha2 es menor que fecha1");
//		}	
//	
//		LocalDate today = LocalDate.now();
//		int month = today.getMonthValue();
//		month = month-1;
//		System.out.println( "mes" + month);
	}
	
	

}


