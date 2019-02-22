package com.emprendesoftcr.pdf;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.emprendesoftcr.Utils.Constantes;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * HeaderFooter.
 * @author jose.
 * @since 24 ene. 2019
 */
public class HeaderFooterTiquete extends PdfPageEventHelper {

	private PdfTemplate					total;
	private FacturaElectronica	facturaElectronica;
	private String							tipoDoc;

	public HeaderFooterTiquete(FacturaElectronica facturaElectronica, String tipoDoc) {
		this.facturaElectronica = facturaElectronica;
		this.tipoDoc = tipoDoc;
	}

	@Override
	public void onOpenDocument(PdfWriter writer, Document document) {
		total = writer.getDirectContent().createTemplate(30, 16);
	}

	private String tipoDocVersion() {
		String resultado = Constantes.EMPTY;
		if(this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
			return Constantes.TIQUETE_ELECTRONICO_VERSION;
		}else if(this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
			return Constantes.FACTURA_ELECTRONICO_VERSION;
		}else if(this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return Constantes.NOTA_CREDITO_ELECTRONICO_VERSION;
		}else if(this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
			return Constantes.NOTA_DEBITO_ELECTRONICO_VERSION;
		}else if(this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
			return Constantes.PROFORMA_VERSION;
		} 
		
		
		
		
		return resultado;
	}
	@Override
	public void onStartPage(PdfWriter writer, Document document) {
			try {

//		
//			if(this.facturaElectronica.getClave() !=null) {
//				if(!this.facturaElectronica.getClave().equals(Constantes.EMPTY)) {
//					String codigoQR = this.facturaElectronica.getClave();
//
//					BarcodeQRCode qrcode = new BarcodeQRCode(codigoQR, 80, 80, null);
//					Image qrcodeImage = qrcode.getImage();
//					qrcodeImage.setAbsolutePosition(495f, 765f);
//					document.add(qrcodeImage);
//					
//				}
//				
//			}

			UtilsPdf utils_pdf = new UtilsPdf();
			PdfPTable tabla_cabezera = new PdfPTable(2);
			tabla_cabezera.setWidthPercentage(100);
			tabla_cabezera.setTotalWidth(570f);
			tabla_cabezera.setLockedWidth(true);
			float[] header_espacio = { 310, 270 };
			tabla_cabezera.setWidths(header_espacio);
			tabla_cabezera.getDefaultCell().setBorder(0);
			tabla_cabezera.setSpacingAfter(0);
			tabla_cabezera.setSpacingBefore(0);

		
  	

			PdfPTable derecha = new PdfPTable(2);
			if(this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				derecha.addCell(utils_pdf.obtenerCeldaNormal(tipoDocVersion(), UtilsPdf.bigFont16, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));	
			}else {
				derecha.addCell(utils_pdf.obtenerCeldaNormal(tipoDocVersion(), UtilsPdf.bigFont12, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));
			} 
			

			derecha.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getEmisorNombreComercial(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));

			derecha.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getEmisorNombre(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));

			derecha.addCell(utils_pdf.obtenerCeldaNormal("Ced:"+this.facturaElectronica.getEmisorCedula(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));

			derecha.addCell(utils_pdf.obtenerCeldaNormal("Telf:"+this.facturaElectronica.getEmisorTelefono(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));
			derecha.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getEmisorCorreo(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));

			derecha.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getEmisorDireccion(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));

			derecha.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			PdfPTable derecha_inferior = new PdfPTable(2);
			derecha_inferior.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			// derecha_inferior.getDefaultCell().setCellEvent(new RoundRectangle());

			derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal("Consecutivo No.:", UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));

			derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getConsecutivo(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));

			derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal("Fecha de emisión:", UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));

			derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getFechaEmision(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));

			derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal("Plazo crédito:", UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));

			derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getPlazoCredito(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));

			derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal("Condición venta:", UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));

			derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getCondicionVenta(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));

			derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal("Medio de pago", UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));

			derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getMedioEfectivo(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));

			derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal("Moneda", UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));

			derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getMoneda(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));

			derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal("Tipo cambio", UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));

			derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getTipoCambio(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));

			PdfPCell celda_derecha_redondeada = new PdfPCell(derecha_inferior);
			celda_derecha_redondeada.setBorder(Rectangle.NO_BORDER);
			celda_derecha_redondeada.setCellEvent(utils_pdf.getRoundRectangle());

			derecha.addCell(celda_derecha_redondeada);

			tabla_cabezera.addCell(derecha);

			document.add(tabla_cabezera);

						
			
			
			
		
			
			

		
		
			document.add(new Paragraph("\n", UtilsPdf.pequeFont));

		} catch (DocumentException de) {
			throw new ExceptionConverter(de);}
		
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {

		Rectangle rect = writer.getBoxSize("art");

		// ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(String.format("Página %d of", writer.getPageNumber())), (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);
		// ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(String.format("____________________________________________________________________________", writer.getRootOutline())), (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);
		try {
			/* dibujo de linea */
			PdfContentByte cb = writer.getDirectContent();
			cb.setLineWidth(1f);
			cb.moveTo(11.5f, 61);
			cb.lineTo(583, 61);
			cb.stroke();
			DateFormat fechaCompleta = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String text = "Emitida conforme lo establecido en la resolución de Facturación Electrónica, N° DGT-R-48-2016 del 7/10/16 08:00:00 , a las " + fechaCompleta.format(new Date()) + " horas";
			BaseFont bf = BaseFont.createFont();
			cb.setFontAndSize(bf, 8);

			cb.showTextAlignedKerned(PdfContentByte.ALIGN_CENTER, text, 300, 41, 0);
		} catch (Exception e) {
			System.out.println("Error con pie de pagina");
		}
	}

	@Override
	public void onCloseDocument(PdfWriter writer, Document document) {
		ColumnText.showTextAligned(total, Element.ALIGN_LEFT, new Phrase(String.valueOf(writer.getPageNumber() - 1)), 2, 2, 0);
	}
}
