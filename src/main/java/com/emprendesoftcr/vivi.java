package com.emprendesoftcr;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;

import org.springframework.web.client.RestTemplate;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class vivi {

	public static void main(String[] args) throws ParseException, FileNotFoundException, DocumentException {

		Double montoTotal = 19469.02655;
//		19469.02655
		Double montoDescuento = 1946.90265d;
		Double resultado = montoTotal - montoDescuento;
		resultado = Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;
		
		System.out.println("Resultado:" + resultado.toString());
		
//	// request url
//		String url = "https://api.hacienda.go.cr/fe/ae?identificacion=109360677";
//
//		// create an instance of RestTemplate
//		RestTemplate restTemplate = new RestTemplate();
//
//		// make an HTTP GET request
//		String json = restTemplate.getForObject(url, String.class);
//
//		// print json
//		System.out.println(json);
//		
//		
//		
//		// Se crea el documento
//		Document documento = new Document(PageSize.A4, 50, 50, 50, 50);
//		
//
//	// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
//	FileOutputStream ficheroPdf = new FileOutputStream("fichero.pdf");
////Se abre el documento.
//	
//	// Se asocia el documento al OutputStream y se indica que el espaciado entre
//	// lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
//	PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
//	documento.open();
//  Paragraph paragraphHello = new Paragraph();
//  paragraphHello.add("Hello iText paragraph!");
//  paragraphHello.setAlignment(Element.ALIGN_JUSTIFIED);
//
//  documento.add(paragraphHello);
  

//	documento.add(new Paragraph("Este es el segundo y tiene una fuente rara",
//					FontFactory.getFont("arial",   // fuente
//					22,                            // tamaño
//					Font.ITALIC,                   // estilo
//					BaseColor.CYAN)));             // color

	
//	PdfPTable tabla = new PdfPTable(3);
//	for (int i = 0; i < 15; i++)
//	{
//		tabla.addCell("celda " + i);
//	}
//	documento.add(tabla);
//	
//	documento.close();
//	
	
	}

	

}
