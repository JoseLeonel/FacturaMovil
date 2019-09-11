
package com.emprendesoftcr.pdf;

import java.io.ByteArrayOutputStream;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.Articulo;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GondolaArticuloPdfView {

	static Font						bigFont							= FontFactory.getFont("Helvetica", "Windows-1254", 14.0F, 1, BaseColor.BLACK);
	static Font						bigFont12						= FontFactory.getFont("Helvetica", "Windows-1254", 12.0F, 1, BaseColor.BLACK);
	static Font						bigFont20						= FontFactory.getFont("Helvetica", "Windows-1254", 20.0F, 1, BaseColor.BLACK);
	static Font						pequeFont						= FontFactory.getFont("Helvetica", "Windows-1254", 11.0F, 1, BaseColor.BLACK);
	static Font						titulopequeFont			= FontFactory.getFont("Helvetica", "Windows-1254", 8.0F, 1, BaseColor.BLACK);
	static Font						peque								= FontFactory.getFont("Arial", 14, BaseColor.BLACK);
	static Font						fontmed08						= FontFactory.getFont("Arial", 8, BaseColor.BLACK);
	static Font						fontmed11						= FontFactory.getFont("Arial", 11, BaseColor.BLACK);
	static Font						fontmed12						= FontFactory.getFont("Arial", 11, BaseColor.BLACK);
	static Font						fontmed12_bold			= FontFactory.getFont("Arial", 11, 1, BaseColor.BLACK);
	static Font						font_cabezera_tabla	= FontFactory.getFont("Helvetica", "Windows-1254", 8.0F, 1, BaseColor.BLACK);

	public PdfPCellEvent	roundRectangle;

	public static ByteArrayOutputStream main(Articulo articulo) throws Exception {
		 Document document = new Document(PageSize.A7);
 		document.setMargins(3, 1, 1, 3);
 		ByteArrayOutputStream stream = new ByteArrayOutputStream();

 		PdfWriter writer = PdfWriter.getInstance(document, stream);
				
		
		HeaderFooterGondola event = new HeaderFooterGondola(articulo);
		writer.setPageEvent(event);
		document.open();
    String codigo = Constantes.EMPTY;
    if(articulo.getCodigo().length() >20) {
    	codigo = articulo.getCodigo().substring(0, 19);
    }else {
    	codigo = articulo.getCodigo();
    }
		document.add(new Paragraph("Cod:"+codigo.trim(),bigFont));
		document.close();

		return stream;
	}




	public class CellBackground implements PdfPCellEvent {

		@Override
		public void cellLayout(PdfPCell cell, Rectangle rect, PdfContentByte[] canvas) {
			PdfContentByte cb = canvas[PdfPTable.BACKGROUNDCANVAS];
			cb.roundRectangle(rect.getLeft() + 1.5f, rect.getBottom() + 1.5f, rect.getWidth() - 3, rect.getHeight() - 3, 4);
			cb.setCMYKColorFill(0x00, 0x00, 0x00, 0x00);
			cb.fill();
		}
	}
}
