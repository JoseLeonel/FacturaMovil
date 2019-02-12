
package com.emprendesoftcr.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * Propiedades propias del PDF
 * UtilsPdf.
 * @author jose.
 * @since 24 ene. 2019
 */
public final class UtilsPdf {
	public static Font	bigFont16							= FontFactory.getFont("Helvetica", "Windows-1254", 20.0F, 1, BaseColor.BLACK);
	public static Font	bigFont							= FontFactory.getFont("Helvetica", "Windows-1254", 14.0F, 1, BaseColor.BLACK);
	public static Font	bigFont12						= FontFactory.getFont("Helvetica", "Windows-1254", 12.0F, 1, BaseColor.BLACK);
	public static Font	pequeFont						= FontFactory.getFont("Helvetica", "Windows-1254", 11.0F, 1, BaseColor.BLACK);
	public static Font	titulopequeFont			= FontFactory.getFont("Helvetica", "Windows-1254", 8.0F, 1, BaseColor.BLACK);
	public static Font	peque								= FontFactory.getFont("Arial", 14, BaseColor.BLACK);
	public static Font	fontmed08						= FontFactory.getFont("Arial", 8, BaseColor.BLACK);
	public static Font	fontmed11						= FontFactory.getFont("Arial", 11, BaseColor.BLACK);
	public static Font	fontmed12						= FontFactory.getFont("Arial", 11, BaseColor.BLACK);
	public static Font	fontmed12_bold			= FontFactory.getFont("Arial", 11, 1, BaseColor.BLACK);
	public static Font	font_cabezera_tabla	= FontFactory.getFont("Helvetica", "Windows-1254", 8.0F, 1, BaseColor.BLACK);

	public UtilsPdf() {
	}

	public PdfPCell obtenerCeldaNormal(String texto_, Font fuente_, int colspan_, boolean redondeado_, int tipo_alienacion, int border_) {
		PdfPCell celda_ = new PdfPCell(new Paragraph(texto_, fuente_));

		celda_.setHorizontalAlignment(tipo_alienacion);
		celda_.setBorder(border_);
		celda_.setColspan(colspan_);
		if (redondeado_) {
			celda_.setCellEvent(new UtilsPdf.RoundRectangle());
		}

		return celda_;

	}

	public RoundRectangle getRoundRectangle() {
		return new RoundRectangle();
	}

	public RoundRectangle_tabla_sup_izq getRoundRectangle_tabla_sup_izq() {
		return new RoundRectangle_tabla_sup_izq();
	}

	class RoundRectangle implements PdfPCellEvent {

		/**
		 * @see com.lowagie.text.pdf.PdfPCellEvent#cellLayout(com.lowagie.text.pdf.PdfPCell, com.lowagie.text.Rectangle, com.lowagie.text.pdf.PdfContentByte[])
		 */
		@Override
		public void cellLayout(PdfPCell cell, Rectangle rect, PdfContentByte[] canvas) {
			PdfContentByte cb = canvas[PdfPTable.LINECANVAS];
			cb.roundRectangle(rect.getLeft() + 0.5f, rect.getBottom() + 0.5f, rect.getWidth() - 1, rect.getHeight() - 1, 2);
			cb.stroke();
		}
	}

	class RoundRectangle_tabla_sup_izq implements PdfPCellEvent {

		/**
		 * @see com.lowagie.text.pdf.PdfPCellEvent#cellLayout(com.lowagie.text.pdf.PdfPCell, com.lowagie.text.Rectangle, com.lowagie.text.pdf.PdfContentByte[])
		 */
		@Override
		public void cellLayout(PdfPCell cell, Rectangle rect, PdfContentByte[] canvas) {
			PdfContentByte cb = canvas[PdfPTable.LINECANVAS];
			// cb.setColorStroke(new GrayColor(0.8f));
			cb.roundRectangle(rect.getLeft() + 1.5f, rect.getBottom() + 1.5f, rect.getWidth() - 3, rect.getHeight() - 3, 5);
			cb.stroke();
		}
	}
}
