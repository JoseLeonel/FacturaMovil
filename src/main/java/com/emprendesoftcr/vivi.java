package com.emprendesoftcr;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import com.emprendesoftcr.utils.Utils;
import com.itextpdf.text.DocumentException;


public class vivi {

	public static void main(String[] args) throws ParseException, FileNotFoundException, DocumentException {
		
		Double lado = 4d;
		Double lado1 = 4d;
		Double resultado = lado1 * lado;
		
		String t = parseTime("2020-11-13T10:22:27"); 
		
//		float  total  = Utils.getTotalExonerado(0f,42384.0708); 
//		System.out.println("Yo naci:" + total);
//		
		
		
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
	
  public static String parseTime(String date){
    SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
    try {
        Date date1 = format.parse(date);
        String d= new SimpleDateFormat("yyyy/dd/MM HH:mm:ss").format(date1);
        return d;
    }catch (Exception e){
        e.printStackTrace();
    }
    return "";
}

}


