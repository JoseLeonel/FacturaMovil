package com.emprendesoftcr.pdf;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;

import com.emprendesoftcr.Utils.Constantes;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
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
public class HeaderFooter extends PdfPageEventHelper {

	private PdfTemplate					total;
	private FacturaElectronica	facturaElectronica;

	public HeaderFooter(FacturaElectronica facturaElectronica) {
		this.facturaElectronica = facturaElectronica;
	}

	@Override
	public void onOpenDocument(PdfWriter writer, Document document) {
		total = writer.getDirectContent().createTemplate(30, 16);
	}

	@Override
	public void onStartPage(PdfWriter writer, Document document) {
		Image img_logo = null;
		try {

			String dir = System.getProperty("user.dir");
				if (this.facturaElectronica.get_logo() != null) {
					if (!this.facturaElectronica.get_logo().equals(Constantes.EMPTY)) {
						img_logo = Image.getInstance(dir + "/data/logos/" + this.facturaElectronica.get_logo());
					}

				}

		
			String codigoQR = this.facturaElectronica.getClave();

			BarcodeQRCode qrcode = new BarcodeQRCode(codigoQR, 80, 80, null);
			Image qrcodeImage = qrcode.getImage();
			qrcodeImage.setAbsolutePosition(495f, 765f);
			document.add(qrcodeImage);

			UtilsPdf utils_pdf = new UtilsPdf();
			PdfPTable tabla_cabezera = new PdfPTable(2);
			tabla_cabezera.setWidthPercentage(100);
			tabla_cabezera.setTotalWidth(570f);
			tabla_cabezera.setLockedWidth(true);
			float[] header_espacio = { 310, 260 };
			tabla_cabezera.setWidths(header_espacio);
			tabla_cabezera.getDefaultCell().setBorder(0);
			tabla_cabezera.setSpacingAfter(0);
			tabla_cabezera.setSpacingBefore(0);

			PdfPTable izquierda = new PdfPTable(1);

			// String logo_ = "/opt/reportes/logo01.png";
			// Image img_logo = Image.getInstance(logo_);
			img_logo.setAlignment(Image.ALIGN_LEFT);
			//img_logo.scaleAbsolute(80, 120);
			//img_logo.scalePercent(12);
			img_logo.scalePercent(12, 20);
			
			// img_logo.setBorder(Image.BOX);
			// img_logo.setBorderWidth(0);
			// img_logo.setBorderColor(BaseColor.BLACK);

			PdfPCell cell_logo = new PdfPCell(img_logo, false);
			cell_logo.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
			cell_logo.setColspan(1);
			cell_logo.setBorder(Rectangle.NO_BORDER);
			izquierda.addCell(cell_logo);

			izquierda.addCell(utils_pdf.obtenerCeldaNormal("Clave: " + this.facturaElectronica.getClave(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));
			tabla_cabezera.addCell(izquierda);

			PdfPTable derecha = new PdfPTable(1);

			derecha.addCell(utils_pdf.obtenerCeldaNormal("Tiquete Electrónico ver. 10.2", UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));

			derecha.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getEmisorNombreComercial(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));

			derecha.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getEmisorNombre(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));

			derecha.addCell(utils_pdf.obtenerCeldaNormal("110010978", UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));

			derecha.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getEmisorTelefono(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));
			derecha.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getEmisorCorreo(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));

			derecha.addCell(utils_pdf.obtenerCeldaNormal("direccion", UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));

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

			derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal("CRC-Colones Costa Rica", UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));

			derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal("Tipo cambio", UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));

			derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal("1.0", UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));

			PdfPCell celda_derecha_redondeada = new PdfPCell(derecha_inferior);
			celda_derecha_redondeada.setBorder(Rectangle.NO_BORDER);
			celda_derecha_redondeada.setCellEvent(utils_pdf.getRoundRectangle());

			derecha.addCell(celda_derecha_redondeada);

			tabla_cabezera.addCell(derecha);

			document.add(tabla_cabezera);

			PdfPTable tabla_segunda_tabla = new PdfPTable(2);
			tabla_segunda_tabla.setWidthPercentage(100);
			tabla_segunda_tabla.setTotalWidth(570f);
			tabla_segunda_tabla.setLockedWidth(true);
			float[] header_espacio_02 = { 310, 260 };
			tabla_segunda_tabla.setWidths(header_espacio_02);
			tabla_segunda_tabla.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			tabla_segunda_tabla.setSpacingAfter(0);
			tabla_segunda_tabla.setSpacingBefore(0);

			PdfPCell cell_recep = new PdfPCell(new Paragraph("\nNombre del Receptor: VIVIANA MARTINEZ GRANADOS", UtilsPdf.font_cabezera_tabla));
			cell_recep.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
			cell_recep.setColspan(1);
			// cell_recep.setCellEvent(new RoundRectangle_tabla_sup_izq());
			cell_recep.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);

			tabla_segunda_tabla.addCell(cell_recep);

			PdfPCell cell_id_recep = new PdfPCell(new Paragraph("\nIdentificación Receptor: 110010978", UtilsPdf.font_cabezera_tabla));
			cell_id_recep.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
			cell_id_recep.setColspan(1);
			cell_id_recep.setBorder(Rectangle.BOTTOM);

			tabla_segunda_tabla.addCell(cell_id_recep);

			PdfPCell cell_dir_recep = new PdfPCell(new Paragraph("Dirección Receptor:", UtilsPdf.font_cabezera_tabla));
			cell_dir_recep.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
			cell_dir_recep.setColspan(1);
			cell_dir_recep.setBorder(Rectangle.RIGHT);

			tabla_segunda_tabla.addCell(cell_dir_recep);

			PdfPCell cell_cond_venta = new PdfPCell(new Paragraph("josehernandezchaverri@gmail.com \n\n", UtilsPdf.font_cabezera_tabla));
			cell_cond_venta.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
			cell_cond_venta.setColspan(1);
			cell_cond_venta.setBorder(Rectangle.NO_BORDER);

			tabla_segunda_tabla.addCell(cell_cond_venta);

			PdfPCell celda_grande02 = new PdfPCell(tabla_segunda_tabla);
			celda_grande02.setColspan(1);
			celda_grande02.setCellEvent(utils_pdf.getRoundRectangle_tabla_sup_izq());
			celda_grande02.setBorder(Rectangle.NO_BORDER);

			PdfPTable segunda_tabla_redondeada = new PdfPTable(1);
			segunda_tabla_redondeada.setWidthPercentage(100);
			segunda_tabla_redondeada.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			// segunda_tabla_redondeada.getDefaultCell().setCellEvent(new RoundRectangle_tabla_sup_izq());
			segunda_tabla_redondeada.addCell(celda_grande02);
			document.add(segunda_tabla_redondeada);
			document.add(new Paragraph("\n", UtilsPdf.pequeFont));

		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		} catch (IOException ex) {
			Logger.getLogger(HeaderFooter.class.getName()).log(Level.SEVERE, null, ex);
		}
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

			String text = "Emitida conforme lo establecido en la resolución de Facturación Electrónica, N° DGT-R-48-2016 del 7/10/16 08:00:00 , a las 08/01/2019 09:33:09 horas";
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
