package com.emprendesoftcr.pdf;

import com.emprendesoftcr.modelo.Articulo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooterGondola extends PdfPageEventHelper {
	private PdfTemplate					total;
	private Articulo articulo;
	
	

	public HeaderFooterGondola(Articulo articulo) {
		super();
		this.articulo = articulo;
	}


	@Override
	public void onOpenDocument(PdfWriter writer, Document document) {
		total = writer.getDirectContent().createTemplate(30, 16);
	}
	
	
	@Override
	public void onStartPage(PdfWriter writer, Document document) {
		try {

  		document.add(new Paragraph(this.articulo.getDescripcion().length() >40?this.articulo.getDescripcion().trim().substring(0, 40):this.articulo.getDescripcion().trim(),UtilsPdf.bigFont12));
  		document.add(new Paragraph(this.articulo.getPrecioPublicoSTR(),UtilsPdf.bigFont40));

		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}

	}

	
	@Override
	public void onEndPage(PdfWriter writer, Document document) {

		try {
		} catch (Exception e) {
			System.out.println("Error con pie de pagina");
		}
	}

	@Override
	public void onCloseDocument(PdfWriter writer, Document document) {
		ColumnText.showTextAligned(total, Element.ALIGN_LEFT, new Phrase(String.valueOf(writer.getPageNumber() - 1)), 2, 2, 0);
	}

	
}
