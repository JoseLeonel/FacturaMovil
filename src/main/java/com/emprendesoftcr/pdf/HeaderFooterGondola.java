package com.emprendesoftcr.pdf;

import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Articulo;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * HeaderFooter.
 * @author jose.
 * @since 24 ene. 2019
 */
public class HeaderFooterGondola extends PdfPageEventHelper {

	private PdfTemplate	total;
	private Articulo		articulo;

	public HeaderFooterGondola(Articulo articulo) {
		this.articulo = articulo;
	}

	@Override
	public void onOpenDocument(PdfWriter writer, Document document) {
		total = writer.getDirectContent().createTemplate(30, 16);
	}

	@Override
	public void onStartPage(PdfWriter writer, Document document) {
		try {
			
			//document.add(new Paragraph(articulo.getDescripcion(), UtilsPdf.bigFont16));
			
			Paragraph paragraph = new Paragraph(Utils.formateadorMilesSinDecimales(articulo.getPrecioPublico()), FontFactory.getFont("Times New Roman", 65, Font.BOLD, BaseColor.BLACK));
			paragraph.setFont(UtilsPdf.fontmed18_bold);
			paragraph.setAlignment( Element.ALIGN_CENTER );
			
			document.add(paragraph);
			document.add(new Paragraph("\n", UtilsPdf.pequeFont));
			UtilsPdf utils_pdf = new UtilsPdf();
			PdfPTable tabla_cabezera = new PdfPTable(1);
			tabla_cabezera.setWidthPercentage(100);
			tabla_cabezera.setTotalWidth(300f);
			tabla_cabezera.setLockedWidth(true);
			float[] header_espacio = { 300f };
			tabla_cabezera.setWidths(header_espacio);
			tabla_cabezera.getDefaultCell().setBorder(0);
			tabla_cabezera.setSpacingAfter(0);
			tabla_cabezera.setSpacingBefore(0);
			tabla_cabezera.addCell(utils_pdf.obtenerCeldaNormal(articulo.getDescripcion(), UtilsPdf.fontmed08, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));
			
			document.add(tabla_cabezera);
//
//			Paragraph paragraph1 = new Paragraph(articulo.getCodigo().trim(), FontFactory.getFont("Times New Roman", 20, Font.BOLD, BaseColor.BLACK));
//			paragraph1.setFont(UtilsPdf.fontmed18_bold);
//			paragraph1.setAlignment( Element.ALIGN_CENTER );
//			
//			document.add(paragraph);
//			

		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}

	}

	@Override
	public void onCloseDocument(PdfWriter writer, Document document) {
		ColumnText.showTextAligned(total, Element.ALIGN_CENTER, new Phrase(String.valueOf(writer.getPageNumber() - 1)), 2, 2, 0);
	}
}
