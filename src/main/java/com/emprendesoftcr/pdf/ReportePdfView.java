
package com.emprendesoftcr.pdf;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
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

public class ReportePdfView {

	static Font						bigFont							= FontFactory.getFont("Helvetica", "Windows-1254", 14.0F, 1, BaseColor.BLACK);
	static Font						bigFont12						= FontFactory.getFont("Helvetica", "Windows-1254", 12.0F, 1, BaseColor.BLACK);
	static Font						pequeFont						= FontFactory.getFont("Helvetica", "Windows-1254", 11.0F, 1, BaseColor.BLACK);
	static Font						titulopequeFont			= FontFactory.getFont("Helvetica", "Windows-1254", 8.0F, 1, BaseColor.BLACK);
	static Font						peque								= FontFactory.getFont("Arial", 14, BaseColor.BLACK);
	static Font						fontmed08						= FontFactory.getFont("Arial", 8, BaseColor.BLACK);
	static Font						fontmed11						= FontFactory.getFont("Arial", 11, BaseColor.BLACK);
	static Font						fontmed12						= FontFactory.getFont("Arial", 11, BaseColor.BLACK);
	static Font						fontmed12_bold			= FontFactory.getFont("Arial", 11, 1, BaseColor.BLACK);
	static Font						font_cabezera_tabla	= FontFactory.getFont("Helvetica", "Windows-1254", 8.0F, 1, BaseColor.BLACK);

	public PdfPCellEvent	roundRectangle;

	public static ByteArrayOutputStream main(String consecutivo, String tipoDoc, FacturaElectronica facturaElectronica) throws Exception {
		ReportePdfView reporte01PdfView = new ReportePdfView();
		Document document = new Document(PageSize.A4);
		document.setMargins(10, 10, 12, 55);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();

		PdfWriter writer = PdfWriter.getInstance(document, stream);
		Rectangle rct = new Rectangle(36, 54, 559, 788);
		// Definimos un nombre y un tamaño para el PageBox los nombres posibles son: “crop”, “trim”, “art” and “bleed”.
		writer.setBoxSize("art", rct);
		HeaderFooter event = new HeaderFooter(facturaElectronica, tipoDoc);
		writer.setPageEvent(event);
		document.open();

//		PdfContentByte cb = writer.getDirectContent();

		reporte01PdfView.buildPdfDocument(facturaElectronica, document, writer,tipoDoc);

		document.close();

		return stream;
	}

	private void buildPdfDocument(FacturaElectronica fac_electro, Document document, PdfWriter writer,String tipoDoc) throws Exception {

		// document.add(new Paragraph("\n", pequeFont));

		PdfPTable tabla_tercera_tabla = new PdfPTable(9);
		tabla_tercera_tabla.setWidthPercentage(100);
		tabla_tercera_tabla.setTotalWidth(570f);
		tabla_tercera_tabla.setLockedWidth(true);
		float[] header_espacio_03 = { 23, 62, 145, 18, 28, 35,17,35, 40 };
		tabla_tercera_tabla.setWidths(header_espacio_03);
		tabla_tercera_tabla.setSplitLate(false);
		tabla_tercera_tabla.setSplitRows(false);

		tabla_tercera_tabla.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		tabla_tercera_tabla.getDefaultCell().setCellEvent(new RoundRectangle());
		// tabla_tercera_tabla.getDefaultCell().setBorder(0);
		tabla_tercera_tabla.setSpacingAfter(0);
		tabla_tercera_tabla.setSpacingBefore(0);

		tabla_tercera_tabla.addCell(obtenerCeldaNormal("Línea", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM));
		tabla_tercera_tabla.addCell(obtenerCeldaNormal("Código", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM));
		tabla_tercera_tabla.addCell(obtenerCeldaNormal("Detalle", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM));
		tabla_tercera_tabla.addCell(obtenerCeldaNormal("Unid", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM));

		tabla_tercera_tabla.addCell(obtenerCeldaNormal("Cantidad", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM));
		tabla_tercera_tabla.addCell(obtenerCeldaNormal("Precio Unit.", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.TOP | Rectangle.RIGHT | Rectangle.BOTTOM));
		tabla_tercera_tabla.addCell(obtenerCeldaNormal("Imp", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.TOP | Rectangle.RIGHT | Rectangle.BOTTOM));
		tabla_tercera_tabla.addCell(obtenerCeldaNormal("Total Imp", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.TOP | Rectangle.RIGHT | Rectangle.BOTTOM));
		tabla_tercera_tabla.addCell(obtenerCeldaNormal("Total Linea", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.TOP | Rectangle.RIGHT | Rectangle.BOTTOM));

//tabla_tercera_tabla.setKeepRowsTogather(true);
		tabla_tercera_tabla.setHeaderRows(1);
		tabla_tercera_tabla.setSplitRows(true);
		tabla_tercera_tabla.setComplete(false);
//tabla_tercera_tabla.setSplitLate(false);
//tabla_tercera_tabla.setKeepTogether(true);

//tabla_tercera_tabla.setFooterRows(1);

		int indice_ = 0;
		for (DetalleFacturaElectronica item : fac_electro.getDetalleFacturaElectronica()) {
			tabla_tercera_tabla.addCell(obtenerCeldaNormal(String.valueOf(item.getLinea()), font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT));

			tabla_tercera_tabla.addCell(obtenerCeldaNormal(String.valueOf(item.getCodigo()), font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT));

			tabla_tercera_tabla.addCell(obtenerCeldaNormal(item.getDescripcion(), font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.LEFT));

			tabla_tercera_tabla.addCell(obtenerCeldaNormal(item.getUnidad(), font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.LEFT));

			tabla_tercera_tabla.addCell(obtenerCeldaNormal(String.valueOf(item.getCantidad()), font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, Rectangle.LEFT));
			tabla_tercera_tabla.addCell(obtenerCeldaNormal(Utils.formateadorMiles(item.getPrecioU()), font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, Rectangle.LEFT));
			tabla_tercera_tabla.addCell(obtenerCeldaNormal(String.valueOf(item.getTarifaIva()), font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, Rectangle.LEFT | Rectangle.RIGHT));
			tabla_tercera_tabla.addCell(obtenerCeldaNormal(Utils.formateadorMiles(item.getImpuesto()), font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, Rectangle.LEFT | Rectangle.RIGHT));
			tabla_tercera_tabla.addCell(obtenerCeldaNormal(Utils.formateadorMiles(item.getTotal()), font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, Rectangle.LEFT | Rectangle.RIGHT));
			indice_++;
			/*
			 * if(indice_ == 27){ agregaLineasBlanco02(tabla_tercera_tabla, 7); }
			 */
		}

		tabla_tercera_tabla.addCell(obtenerCeldaNormal("", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT));
		tabla_tercera_tabla.addCell(obtenerCeldaNormal("", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT));
		PdfPCell ultima_linea_ = obtenerCeldaNormal("----------------------------- Última línea -----------------------------", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT);

		tabla_tercera_tabla.addCell(ultima_linea_);
		tabla_tercera_tabla.addCell(obtenerCeldaNormal("", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT));
		tabla_tercera_tabla.addCell(obtenerCeldaNormal("", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT));
		tabla_tercera_tabla.addCell(obtenerCeldaNormal("", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT));
		tabla_tercera_tabla.addCell(obtenerCeldaNormal("", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT));
		tabla_tercera_tabla.addCell(obtenerCeldaNormal("", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT));
		tabla_tercera_tabla.addCell(obtenerCeldaNormal("", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT));
		
		agregaLineasBlanco(tabla_tercera_tabla, indice_ + 2, true);

		document.add(tabla_tercera_tabla);

		PdfPTable tabla_ultima = new PdfPTable(3);
		tabla_ultima.setWidthPercentage(100);
		tabla_ultima.setTotalWidth(570f);
		tabla_ultima.setLockedWidth(true);
		float[] header_espacio_04 = { 350, 110, 110 };
		tabla_ultima.setWidths(header_espacio_04);
		tabla_ultima.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		tabla_ultima.setSpacingAfter(2);
		tabla_ultima.setSpacingBefore(2);

		PdfPTable izquierda_inferior_ultima = new PdfPTable(1);
		izquierda_inferior_ultima.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		izquierda_inferior_ultima.getDefaultCell().setCellEvent(new RoundRectangle());
    String nota = Constantes.EMPTY;    
		if(tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO) || tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			nota = fac_electro.getReferenciaRazon();
		}else {
			nota = fac_electro.get_nota();
		}
		
		izquierda_inferior_ultima.addCell(obtenerCeldaNormal("Nota: " + nota, font_cabezera_tabla, 1, true, Paragraph.ALIGN_LEFT, PdfPCell.NO_BORDER));

		tabla_ultima.addCell(izquierda_inferior_ultima);

		PdfPTable central_inferior_ultima = new PdfPTable(1);
		central_inferior_ultima.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		central_inferior_ultima.getDefaultCell().setCellEvent(new RoundRectangle());

//		double monto = 0;
//		double descuento = 0;
//		double subTotal = 0;
//		double impuesto = 0;
//		double exento = 0;
//		double gravado = 0;
//		double total = 0;
//		List<DetalleFacturaElectronica> detalleFacturaElectronica = fac_electro.getDetalleFacturaElectronica();
//		for (int i = 0; i < detalleFacturaElectronica.size(); i++) {
//			monto = monto + (double) Math.round(detalleFacturaElectronica.get(i).getMonto() * 100000d) / 100000d;
//			descuento = descuento + (double) Math.round(detalleFacturaElectronica.get(i).getDescuento() * 100000d) / 100000d;
//			subTotal = subTotal + (double) Math.round(detalleFacturaElectronica.get(i).getSubtotal() * 100000d) / 100000d;
//			impuesto = impuesto + (double) Math.round(detalleFacturaElectronica.get(i).getImpuesto() * 100000d) / 100000d;
//			exento = exento + (double) Math.round(detalleFacturaElectronica.get(i).getImpuesto() == Constantes.ZEROS_DOUBLE ? detalleFacturaElectronica.get(i).getTotal() * 100000d : Constantes.ZEROS_DOUBLE) / 100000d;
//			gravado = gravado + (double) Math.round(detalleFacturaElectronica.get(i).getImpuesto() > Constantes.ZEROS_DOUBLE ? detalleFacturaElectronica.get(i).getTotal() * 100000d : Constantes.ZEROS_DOUBLE) / 100000d;
//			total = total + (double) Math.round(detalleFacturaElectronica.get(i).getTotal() * 100000d) / 100000d;
//		}
//		
		//Total Factura
		Double totalExentos = Constantes.ZEROS_DOUBLE;
		Double totalGravados = Constantes.ZEROS_DOUBLE;
		Double totalVenta = Constantes.ZEROS_DOUBLE;
		Double totalDescuento = Constantes.ZEROS_DOUBLE;
		Double totalImpuesto = Constantes.ZEROS_DOUBLE;
		Double totalVentaNeta = Constantes.ZEROS_DOUBLE;
		Double totalComprobante = Constantes.ZEROS_DOUBLE;
		//Ventas Exentas
		if(fac_electro.getFooterTotalServiciosExentos() != null) {
			totalExentos = fac_electro.getFooterTotalServiciosExentos();
		}
		if(fac_electro.getFooterTotalExento() != null) {
			totalExentos += fac_electro.getFooterTotalExento();
		}
		//Ventas Gravadas
		if(fac_electro.getFooterTotalServiciosGravados() != null) {
			totalGravados = fac_electro.getFooterTotalServiciosGravados();
		}
		if(fac_electro.getFooterTotalMercanciasGravadas() != null) {
			totalGravados += fac_electro.getFooterTotalMercanciasGravadas();
		}
		
		//Total Ventas
		if(fac_electro.getFooterTotalVenta() != null) {
			totalVenta = fac_electro.getFooterTotalVenta();
		}
  	//Total Descuento
		if(fac_electro.getFooterTotalDescuento() != null) {
			totalDescuento = fac_electro.getFooterTotalDescuento();
		}    		
  	//Total Impuesto
		if(fac_electro.getFooterTotalImpuesto() != null) {
			totalImpuesto = fac_electro.getFooterTotalImpuesto();
		}    		
   	// Total Ventas Netas
			if(fac_electro.getFooterTotalVentaNeta() != null) {
				totalVentaNeta = fac_electro.getFooterTotalVentaNeta();
			}
		// Total Comprobante
			if(fac_electro.getFooterTotalComprobante() != null) {
				totalComprobante = fac_electro.getFooterTotalComprobante();
			}
				
	


		central_inferior_ultima.addCell(obtenerCeldaNormal("venta", font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, PdfPCell.NO_BORDER));
		if (totalDescuento > Constantes.ZEROS_DOUBLE) {
			central_inferior_ultima.addCell(obtenerCeldaNormal("Descuentos", font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, PdfPCell.NO_BORDER));
		}
		central_inferior_ultima.addCell(obtenerCeldaNormal("Venta Neta", font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, PdfPCell.NO_BORDER));
		central_inferior_ultima.addCell(obtenerCeldaNormal("Impuestos", font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, PdfPCell.NO_BORDER));
		
		central_inferior_ultima.addCell(obtenerCeldaNormal("Gravadas", font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, PdfPCell.NO_BORDER));
		central_inferior_ultima.addCell(obtenerCeldaNormal("Exento", font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, PdfPCell.NO_BORDER));
		central_inferior_ultima.addCell(obtenerCeldaNormal("Total comprobante", font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, PdfPCell.NO_BORDER));

		tabla_ultima.addCell(central_inferior_ultima);

		PdfPTable derecha_inferior_ultima = new PdfPTable(1);
		// derecha_inferior_ultima.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		derecha_inferior_ultima.getDefaultCell().setCellEvent(new RoundRectangle());

		derecha_inferior_ultima.addCell(obtenerCeldaNormal(Utils.formateadorMiles(totalVenta), font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM));
		if (totalDescuento > Constantes.ZEROS_DOUBLE) {
			derecha_inferior_ultima.addCell(obtenerCeldaNormal(Utils.formateadorMiles(totalDescuento), font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM));
		}

		derecha_inferior_ultima.addCell(obtenerCeldaNormal(Utils.formateadorMiles(totalVentaNeta), font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM));
		derecha_inferior_ultima.addCell(obtenerCeldaNormal(Utils.formateadorMiles(totalImpuesto), font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM));
		derecha_inferior_ultima.addCell(obtenerCeldaNormal(Utils.formateadorMiles(totalGravados), font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM));
		derecha_inferior_ultima.addCell(obtenerCeldaNormal(Utils.formateadorMiles(totalExentos), font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM));
		derecha_inferior_ultima.addCell(obtenerCeldaNormal(Utils.formateadorMiles(totalComprobante), font_cabezera_tabla, 1, false, Paragraph.ALIGN_RIGHT, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM));
		
		tabla_ultima.addCell(derecha_inferior_ultima);
		document.add(tabla_ultima);

	}

	public void agregaLineasBlanco(PdfPTable tabla_, int longitud_lista, boolean primera_pagina) {
		int lineas_por_pagina = 0;

		if (longitud_lista > 35) {
			// lineas_por_pagina=36-(longitud_lista%36);
		} else {
			lineas_por_pagina = 35 - longitud_lista;
		}
		for (int i = 0; i < lineas_por_pagina; i++) {
			tabla_.addCell(obtenerCeldaNormal("\n", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT));
			tabla_.addCell(obtenerCeldaNormal("\n", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT));
			tabla_.addCell(obtenerCeldaNormal("\n", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT));
			tabla_.addCell(obtenerCeldaNormal("\n", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT));
			tabla_.addCell(obtenerCeldaNormal("\n", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT));
			tabla_.addCell(obtenerCeldaNormal("\n", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT));
			tabla_.addCell(obtenerCeldaNormal("\n", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT));
			tabla_.addCell(obtenerCeldaNormal("\n", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT));
		}

		// Final de la tabla
		tabla_.addCell(obtenerCeldaNormal("", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM));
		tabla_.addCell(obtenerCeldaNormal("", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM));
		tabla_.addCell(obtenerCeldaNormal("", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM));
		tabla_.addCell(obtenerCeldaNormal("", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM));
		tabla_.addCell(obtenerCeldaNormal("", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM));
		tabla_.addCell(obtenerCeldaNormal("", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM));
		tabla_.addCell(obtenerCeldaNormal("", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM));
		tabla_.addCell(obtenerCeldaNormal("", font_cabezera_tabla, 1, false, Paragraph.ALIGN_CENTER, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM));

	}

	class RoundRectangle implements PdfPCellEvent {

		/**
		 * @see com.lowagie.text.pdf.PdfPCellEvent#cellLayout(com.lowagie.text.pdf.PdfPCell, com.lowagie.text.Rectangle, com.lowagie.text.pdf.PdfContentByte[])
		 */
		@Override
		public void cellLayout(PdfPCell cell, Rectangle rect, PdfContentByte[] canvas) {
			PdfContentByte cb = canvas[PdfPTable.LINECANVAS];
			// cb.setColorStroke(new GrayColor(0.8f));
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

	private PdfPCell obtenerCeldaNormal(String texto_, Font fuente_, int colspan_, boolean redondeado_, int tipo_alienacion, int border_) {
		PdfPCell celda_ = new PdfPCell(new Paragraph(texto_, fuente_));

		celda_.setHorizontalAlignment(tipo_alienacion);
		celda_.setBorder(border_);
		celda_.setColspan(colspan_);
		if (redondeado_) {
			celda_.setCellEvent(new RoundRectangle());
		}

		return celda_;

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
