package com.emprendesoftcr.pdf;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.emprendesoftcr.Utils.Constantes;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
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
	private String							tipoDoc;

	public HeaderFooter(FacturaElectronica facturaElectronica, String tipoDoc) {
		this.facturaElectronica = facturaElectronica;
		this.tipoDoc = tipoDoc;
	}

	@Override
	public void onOpenDocument(PdfWriter writer, Document document) {
		total = writer.getDirectContent().createTemplate(30, 16);
	}

	private String tipoDocVersion() {
		String resultado = Constantes.EMPTY;
		if (this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
			return this.facturaElectronica.getEsquemaXML().equals(Constantes.ESQUEMA_XML_4_2) ? Constantes.TIQUETE_ELECTRONICO_VERSION_4_2 : Constantes.TIQUETE_ELECTRONICO_VERSION_4_3;
		} else if (this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
			return this.facturaElectronica.getEsquemaXML().equals(Constantes.ESQUEMA_XML_4_2) ? Constantes.FACTURA_ELECTRONICO_VERSION_4_2 : Constantes.FACTURA_ELECTRONICO_VERSION_4_3;
		} else if (this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.facturaElectronica.getEsquemaXML().equals(Constantes.ESQUEMA_XML_4_2) ? Constantes.NOTA_CREDITO_ELECTRONICO_VERSION_4_2 : Constantes.NOTA_CREDITO_ELECTRONICO_VERSION_4_3;
		} else if (this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
			return this.facturaElectronica.getEsquemaXML().equals(Constantes.ESQUEMA_XML_4_2) ? Constantes.NOTA_DEBITO_ELECTRONICO_VERSION_4_2 : Constantes.NOTA_DEBITO_ELECTRONICO_VERSION_4_3;
		} else if (this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
			return Constantes.PROFORMA_VERSION;
		}

		return resultado;
	}

	@Override
	public void onStartPage(PdfWriter writer, Document document) {
		Image img_logo = null;
		// Cuadro 1
		try {

			String dir = System.getProperty("user.dir");
			if (this.facturaElectronica.get_logo() != null) {
				if (!this.facturaElectronica.get_logo().equals(Constantes.EMPTY)) {
					img_logo = Image.getInstance(dir + "/data/logos/" + this.facturaElectronica.get_logo());
				}

			}

		} catch (IOException ex) {
			Logger.getLogger(HeaderFooter.class.getName()).log(Level.SEVERE, null, ex);
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			if (this.facturaElectronica.getClave() != null) {
				if (!this.facturaElectronica.getClave().equals(Constantes.EMPTY)) {
					String codigoQR = this.facturaElectronica.getClave();
					BarcodeQRCode qrcode = new BarcodeQRCode(codigoQR, 80, 80, null);
					Image qrcodeImage = qrcode.getImage();
					qrcodeImage.setAbsolutePosition(520f, 765f);
					qrcodeImage.setAlignment(Image.ALIGN_RIGHT);
					document.add(qrcodeImage);
				}

			}

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

			PdfPTable izquierda = new PdfPTable(1);
			if (img_logo != null) {
				img_logo.setAlignment(Image.ALIGN_LEFT);
				img_logo.scaleAbsolute(230, 130);
				img_logo.setAbsolutePosition(20, PageSize.TABLOID.rotate().getHeight() - 100);
				img_logo.setAlignment(Image.ALIGN_RIGHT);
				PdfPCell cell_logo = img_logo == null ? new PdfPCell() : new PdfPCell(img_logo, false);
				cell_logo.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
				cell_logo.setColspan(1);
				cell_logo.setBorder(Rectangle.NO_BORDER);
				izquierda.addCell(cell_logo);

			}
			if (!this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				izquierda.addCell(utils_pdf.obtenerCeldaNormal("Clave: " + this.facturaElectronica.getClave(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));
			}
			
			tabla_cabezera.addCell(izquierda);

			PdfPTable derecha = new PdfPTable(1);
			derecha.addCell(utils_pdf.obtenerCeldaNormal(tipoDocVersion(), this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS) ? UtilsPdf.bigFont16 : UtilsPdf.bigFont12, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));
			derecha.addCell(utils_pdf.obtenerCeldaNormal("Actividad Comercial:" + this.facturaElectronica.get_codigoActividadComercial(), this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS) ? UtilsPdf.bigFont16 : UtilsPdf.bigFont12, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));
			derecha.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getEmisorNombreComercial(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));

			derecha.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getEmisorNombre(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));

			derecha.addCell(utils_pdf.obtenerCeldaNormal("Ced:" + this.facturaElectronica.getEmisorCedula(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));

			derecha.addCell(utils_pdf.obtenerCeldaNormal("Telf:" + this.facturaElectronica.getEmisorTelefono(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));
			derecha.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getEmisorCorreo(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));

			derecha.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getEmisorDireccion(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.NO_BORDER));

			derecha.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			PdfPTable derecha_inferior = new PdfPTable(2);
			derecha_inferior.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			// derecha_inferior.getDefaultCell().setCellEvent(new RoundRectangle());
			if (this.facturaElectronica.getConsecutivo() != null) {
				if (!this.facturaElectronica.getConsecutivo().equals(Constantes.EMPTY)) {
					derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal("Consecutivo No.:", UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));
					derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getConsecutivo(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));

				}
			}
			if (facturaElectronica.getConsecutivoProforma() != null) {
				if (!facturaElectronica.getConsecutivoProforma().equals(Constantes.EMPTY)) {
					derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal("No.Proforma:", UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));
					derecha_inferior.addCell(utils_pdf.obtenerCeldaNormal(this.facturaElectronica.getConsecutivoProforma(), UtilsPdf.font_cabezera_tabla, 1, false, Paragraph.ALIGN_LEFT, Rectangle.BOTTOM));
				}
			}

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

			PdfPTable tabla_segunda_tabla = new PdfPTable(2);
			tabla_segunda_tabla.setWidthPercentage(100);
			tabla_segunda_tabla.setTotalWidth(570f);
			tabla_segunda_tabla.setLockedWidth(true);
			float[] header_espacio_02 = { 310, 260 };
			tabla_segunda_tabla.setWidths(header_espacio_02);
			tabla_segunda_tabla.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			tabla_segunda_tabla.setSpacingAfter(0);
			tabla_segunda_tabla.setSpacingBefore(0);

			String nombreCliente = facturaElectronica.getClienteNombreComercial() != null ? facturaElectronica.getClienteNombre() : Constantes.EMPTY;
			PdfPCell cell_recep = new PdfPCell(new Paragraph("\nNombre del Receptor:" + nombreCliente, UtilsPdf.font_cabezera_tabla));
			cell_recep.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
			cell_recep.setColspan(1);
			// cell_recep.setCellEvent(new RoundRectangle_tabla_sup_izq());
			cell_recep.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);

			tabla_segunda_tabla.addCell(cell_recep);

			String cedulaCliente = Constantes.EMPTY;
			String telefonoCliente = Constantes.EMPTY;
			String correoCliente = Constantes.EMPTY;
			// Cliente
			if (facturaElectronica.getClienteCedula() != null) {
				if (!facturaElectronica.getClienteCedula().equals(Constantes.EMPTY)) {
					if (!facturaElectronica.getClienteCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
						cedulaCliente = "\nIdentificación Receptor:" + this.facturaElectronica.getClienteCedula();
						telefonoCliente = "Telefono:" + this.facturaElectronica.getClienteTelefono() == null ? Constantes.EMPTY : this.facturaElectronica.getClienteTelefono();
						correoCliente = this.facturaElectronica.getClienteCorreo();
					}
				}
			}

			PdfPCell cell_id_recep = new PdfPCell(new Paragraph(cedulaCliente, UtilsPdf.font_cabezera_tabla));
			cell_id_recep.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
			cell_id_recep.setColspan(1);
			cell_id_recep.setBorder(Rectangle.BOTTOM);

			tabla_segunda_tabla.addCell(cell_id_recep);

//			Exoneracion inicio
			String fechaExoneracion = Constantes.EMPTY;
			String numeroDocumentoExoneracion = Constantes.EMPTY;
			Boolean tieneExoneracion = Boolean.FALSE;

			for (DetalleFacturaElectronica item : this.facturaElectronica.getDetalleFacturaElectronica()) {
				if (item.getMontoExoneracion() != null) {
					if (item.getMontoExoneracion() > Constantes.ZEROS_DOUBLE) {
						fechaExoneracion = item.getFechaEmisionExoneracion();
						numeroDocumentoExoneracion = item.getNumeroDocumentoExoneracion() != null?item.getNumeroDocumentoExoneracion():Constantes.EMPTY;
						tieneExoneracion = Boolean.TRUE;
						if(numeroDocumentoExoneracion.equals(Constantes.DOCUMENTO_LIBRE_IVA)) {
							tieneExoneracion = Boolean.FALSE;
						}
					}
				}
			}

			if (tieneExoneracion) {
				PdfPCell cell_recep1 = new PdfPCell(new Paragraph("Fecha Exoneracion:" + fechaExoneracion, UtilsPdf.font_cabezera_tabla));
				cell_recep1.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
				cell_recep1.setColspan(1);
				cell_recep1.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
				tabla_segunda_tabla.addCell(cell_recep1);
				PdfPCell cell_id_recep1 = new PdfPCell(new Paragraph("Numero de Exoneracion:" + numeroDocumentoExoneracion, UtilsPdf.font_cabezera_tabla));
				cell_id_recep1.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
				cell_id_recep1.setColspan(1);
				cell_id_recep1.setBorder(Rectangle.BOTTOM);
				tabla_segunda_tabla.addCell(cell_id_recep1);
			}
//			Final exoneracion  
			if (this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO) || this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
				PdfPCell cell_dir_recep = new PdfPCell(new Paragraph(telefonoCliente + "       Factura/Tiquete Referencia: " + this.facturaElectronica.getReferenciaNumero(), UtilsPdf.font_cabezera_tabla));
				cell_dir_recep.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
				cell_dir_recep.setColspan(1);
				cell_dir_recep.setBorder(Rectangle.RIGHT);
				tabla_segunda_tabla.addCell(cell_dir_recep);

			} else {

				PdfPCell cell_dir_recep = new PdfPCell(new Paragraph(telefonoCliente, UtilsPdf.font_cabezera_tabla));
				cell_dir_recep.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
				cell_dir_recep.setColspan(1);
				cell_dir_recep.setBorder(Rectangle.RIGHT);
				tabla_segunda_tabla.addCell(cell_dir_recep);
			}

			PdfPCell cell_cond_venta = new PdfPCell(new Paragraph(correoCliente + " \n\n", UtilsPdf.font_cabezera_tabla));
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
		}

	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {

		// ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(String.format("Página %d of", writer.getPageNumber())), (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);
		// ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(String.format("____________________________________________________________________________", writer.getRootOutline())), (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);
		try {
			/* dibujo de linea */
//			PdfContentByte cb = writer.getDirectContent();
//			cb.setLineWidth(1f);
//			cb.moveTo(11.5f, 57);
//			cb.lineTo(583, 50);
//			cb.stroke();
			// Ultima Linea
//			float row20 = 680;
//			DateFormat fechaCompleta = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//			String text = "Emitida conforme lo establecido en la resolución de Facturación Electrónica, N° DGT-R-48-2016 del 7/10/16 08:00:00 , a las " + fechaCompleta.format(new Date()) + " horas";
//			
//			if(!this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)){
//				addText(cb, text, UtilsPdf.fontmed08, 300,row20, PdfContentByte.ALIGN_CENTER);	
//			}

			// BaseFont bf = BaseFont.createFont();
			// cb.setFontAndSize(bf, 8);

			// cb.showTextAlignedKerned(PdfContentByte.ALIGN_CENTER, text, 300, row20, 0);
		} catch (Exception e) {
			System.out.println("Error con pie de pagina");
		}
	}

	private void addText(PdfContentByte cb, String text, Font font, float x, float y, int align) {
		Phrase phrase = new Phrase(text, font);
		ColumnText.showTextAligned(cb, align, phrase, x, y, 0);
		cb.saveState();
		cb.stroke();
		cb.restoreState();
	}

	@Override
	public void onCloseDocument(PdfWriter writer, Document document) {
		ColumnText.showTextAligned(total, Element.ALIGN_LEFT, new Phrase(String.valueOf(writer.getPageNumber() - 1)), 2, 2, 0);
	}
}
