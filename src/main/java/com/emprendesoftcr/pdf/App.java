package com.emprendesoftcr.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.google.zxing.WriterException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class App {

	// BaseColor bColor = new BaseColor(109, 202, 66);
	BaseColor bColor = new BaseColor(220, 220, 220);

	public static ByteArrayOutputStream main(String consecutivo, String tipoDoc, FacturaElectronica facturaElectronica) throws IOException, DocumentException, WriterException {
		String dir = System.getProperty("user.dir");
		App app = new App();
		String fontSansRegular = dir + "/data/fonts/OpenSans-Regular.ttf";
		String fontSansBold = dir + "/data/fonts/OpenSans-Bold.ttf";
		Document document = new Document(PageSize.TABLOID.rotate(), 10, 10, 10, 10);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PdfWriter writer = PdfWriter.getInstance(document, stream);

		document.open();
		PdfContentByte cb = writer.getDirectContent();
		BaseFont sansRegular = BaseFont.createFont(fontSansRegular, BaseFont.WINANSI, BaseFont.EMBEDDED);
		BaseFont sansBold = BaseFont.createFont(fontSansBold, BaseFont.WINANSI, BaseFont.EMBEDDED);
		Font font10 = new Font(sansRegular, 10);
		Font font10B = new Font(sansBold, 10);

		// Logo
		app.addLogo(cb, dir, document, facturaElectronica.get_logo());
		// Emisor
		app.addEmisor(cb, font10B, font10, facturaElectronica, dir);

		// Cliente
		if (facturaElectronica.getClienteCedula() != null) {
			if (!facturaElectronica.getClienteCedula().equals(Constantes.EMPTY)) {
				app.addClient(cb, font10B, font10, facturaElectronica);
			}
		}

		app.addRectanguleProducts(cb, font10B);
		app.addProductsTittle(cb, font10B);
		app.addProducts(cb, font10, document, facturaElectronica, dir);
		app.addTotals(cb, font10, font10B, facturaElectronica);

		document.close();

		return stream;
	}

	private void addLogo(PdfContentByte cb, String dir, Document document, String logo) throws IOException, DocumentException {
		// Cuadro 1
		Image img = Image.getInstance(dir + "/data/logos/" + logo);
		cb.setColorStroke(bColor);
		cb.setColorFill(BaseColor.WHITE);
		cb.setLineWidth(1f);
		cb.roundRectangle(10, PageSize.TABLOID.rotate().getHeight() - 100, 640, 90, 5);
		cb.fillStroke();
		// Fondo
		cb.setColorFill(bColor);
		cb.roundRectangle(10, PageSize.TABLOID.rotate().getHeight() - 100, 127, 90, 5);
		cb.fill();
		cb.setColorFill(BaseColor.WHITE);
		//

		img.setAbsolutePosition(450, PageSize.TABLOID.rotate().getHeight() - 90);
		img.setAlignment(Image.ALIGN_RIGHT);
		img.scaleAbsolute(140, 70);
		cb.addImage(img);
	}

	private void addEmisor(PdfContentByte cb, Font font14, Font font12, FacturaElectronica facturaElectronica, String dir) throws MalformedURLException, IOException, DocumentException {
		// Cuadro 1

		float leftColTitle = 20;
		float leftColContent = 140;
		float rightColTitle = PageSize.TABLOID.rotate().getWidth() - 550;
		float rightColContent = PageSize.TABLOID.rotate().getWidth() - 445;
		float firstRow = PageSize.TABLOID.rotate().getHeight() - 30;
		float secondRow = PageSize.TABLOID.rotate().getHeight() - 45;
		float thirdRow = PageSize.TABLOID.rotate().getHeight() - 60;
		float fourthRow = PageSize.TABLOID.rotate().getHeight() - 75;
		float fiveRow = PageSize.TABLOID.rotate().getHeight() - 90;
		float sixRow = PageSize.TABLOID.rotate().getHeight() - 105;
		float sevenRow = PageSize.TABLOID.rotate().getHeight() - 120;
		float eightRow = PageSize.TABLOID.rotate().getHeight() - 135;
		float nineRow = PageSize.TABLOID.rotate().getHeight() - 150;
		float tenRow = PageSize.TABLOID.rotate().getHeight() - 220;
		float elevenRow = PageSize.TABLOID.rotate().getHeight() - 240;
		float twelveRow = PageSize.TABLOID.rotate().getHeight() - 255;
		float thirteenRow = PageSize.TABLOID.rotate().getHeight() - 270;
		float fourteenRow = PageSize.TABLOID.rotate().getHeight() - 285;
		// Cuadro 2
		cb.setColorStroke(bColor);
		cb.setColorFill(BaseColor.WHITE);
		cb.setLineWidth(1f);
		cb.roundRectangle(660, PageSize.TABLOID.rotate().getHeight() - 180, 555, 170, 5);
		cb.fillStroke();
		// Fondo
		cb.setColorFill(bColor);
		cb.roundRectangle(660, PageSize.TABLOID.rotate().getHeight() - 180, 115, 170, 5);
		cb.fill();
		cb.setColorFill(BaseColor.BLACK);
		// Agregar el QR
		File file = FacturaElectronicaUtils.crearQR(facturaElectronica.getClave(), dir + "/data/qr.png");
		Image img = Image.getInstance(file.getPath());

		img.setAbsolutePosition(1100, PageSize.TABLOID.rotate().getHeight() - 120);
		img.setAlignment(Image.ALIGN_RIGHT);
		img.scaleAbsolute(200, 200);
		img.scaleToFit(100, 100);
		cb.addImage(img);
		//
		addText(cb, "Nombre", font12, leftColTitle, firstRow, Element.ALIGN_LEFT);
		addText(cb, "Identificación", font12, leftColTitle, secondRow, Element.ALIGN_LEFT);
		addText(cb, "Teléfono", font12, leftColTitle, thirdRow, Element.ALIGN_LEFT);
		addText(cb, "Fax", font12, leftColTitle, fourthRow, Element.ALIGN_LEFT);
		addText(cb, "Correo", font12, leftColTitle, fiveRow, Element.ALIGN_LEFT);
		addText(cb, "Tipo Documento", font12, rightColTitle, firstRow, Element.ALIGN_LEFT);
		addText(cb, "No.Clave", font12, rightColTitle, secondRow, Element.ALIGN_LEFT);
		addText(cb, "No.Consecutivo", font12, rightColTitle, thirdRow, Element.ALIGN_LEFT);
		addText(cb, "Fecha emisión", font12, rightColTitle, fourthRow, Element.ALIGN_LEFT);
		addText(cb, "Plazo crédito", font12, rightColTitle, fiveRow, Element.ALIGN_LEFT);
		addText(cb, "Condición venta", font12, rightColTitle, sixRow, Element.ALIGN_LEFT);
		addText(cb, "Medio pago", font12, rightColTitle, sevenRow, Element.ALIGN_LEFT);
		addText(cb, "Moneda", font12, rightColTitle, eightRow, Element.ALIGN_LEFT);
		addText(cb, "Tipo cambio ", font12, rightColTitle, nineRow, Element.ALIGN_LEFT);
		//
		cb.setColorFill(BaseColor.BLACK);
		//
		addText(cb, facturaElectronica.getEmisorNombre() != null ? facturaElectronica.getEmisorNombre() : Constantes.EMPTY, font12, leftColContent, firstRow, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getEmisorCedula() != null ? facturaElectronica.getEmisorCedula() : Constantes.EMPTY, font12, leftColContent, secondRow, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getEmisorTelefono() != null ? facturaElectronica.getEmisorTelefono() : Constantes.EMPTY, font12, leftColContent, thirdRow, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getEmisorFax() != null ? facturaElectronica.getEmisorFax() : Constantes.EMPTY, font12, leftColContent, fourthRow, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getEmisorCorreo() != null ? facturaElectronica.getEmisorCorreo() : Constantes.EMPTY, font12, leftColContent, fiveRow, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getTipoDocumento() != null ? facturaElectronica.getTipoDocumento() : Constantes.EMPTY, font12, rightColContent, firstRow, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getClave() != null ? facturaElectronica.getClave() : Constantes.EMPTY, font12, rightColContent, secondRow, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getConsecutivo() != null ? facturaElectronica.getConsecutivo() : Constantes.EMPTY, font12, rightColContent, thirdRow, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getFechaEmision() != null ? facturaElectronica.getFechaEmision() : Constantes.EMPTY, font12, rightColContent, fourthRow, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getPlazoCredito() != null ? facturaElectronica.getPlazoCredito() : Constantes.EMPTY, font12, rightColContent, fiveRow, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getCondicionVenta() != null ? facturaElectronica.getCondicionVenta() : Constantes.EMPTY, font12, rightColContent, sixRow, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getMedioEfectivo() != null ? facturaElectronica.getMedioEfectivo() : Constantes.EMPTY, font12, rightColContent, sevenRow, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getMoneda() != null ? facturaElectronica.getMoneda() : Constantes.EMPTY, font12, rightColContent, eightRow, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getTipoCambio() != null ? facturaElectronica.getTipoCambio() : Constantes.EMPTY, font12, rightColContent, nineRow, Element.ALIGN_LEFT);
		cb.setColorFill(BaseColor.BLACK);
		addText(cb, "Referencia:", font14, 20, PageSize.TABLOID.rotate().getHeight() - 145, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getReferenciaCodigo(), font12, 102, PageSize.TABLOID.rotate().getHeight() - 145, Element.ALIGN_LEFT);
		addText(cb, "Tipo documento:", font14, 20, PageSize.TABLOID.rotate().getHeight() - 160, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getReferenciaTipoDoc(), font12, 102, PageSize.TABLOID.rotate().getHeight() - 160, Element.ALIGN_LEFT);
		addText(cb, "No.Consecutivo:", font14, 240, PageSize.TABLOID.rotate().getHeight() - 160, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getReferenciaNumero(), font12, 325, PageSize.TABLOID.rotate().getHeight() - 160, Element.ALIGN_LEFT);

		addText(cb, "Fecha emisión:", font14, 460, PageSize.TABLOID.rotate().getHeight() - 160, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getReferenciaFechaEmision(), font12, 530, PageSize.TABLOID.rotate().getHeight() - 160, Element.ALIGN_LEFT);

		addText(cb, "Razón:", font14, 20, PageSize.TABLOID.rotate().getHeight() - 180, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getReferenciaRazon(), font12, 55, PageSize.TABLOID.rotate().getHeight() - 180, Element.ALIGN_LEFT);

		cb.setColorFill(BaseColor.BLACK);

	}

	// }

	private void addClient(PdfContentByte cb, Font font14, Font font12, FacturaElectronica facturaElectronica) {
		addLine(cb, BaseColor.BLACK, 1215, PageSize.TABLOID.rotate().getHeight() - 184, 10, PageSize.TABLOID.rotate().getHeight() - 184);
		float firstRow = PageSize.TABLOID.rotate().getHeight() - 195;
		float colLabelCliente = 9;
		float colNombreCliente = 50;
		float colLabelNit = 400;
		float colCedulaCliente = 450;
		float colLabelCorreo = 650;
		float colCorreo = 700;
		float colLabelTelefono = 1100;
		float colTelefono = 1150;

		cb.setColorFill(BaseColor.BLACK);
		// Cliente
		addText(cb, "Cliente:", font14, colLabelCliente, firstRow, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getClienteNombreComercial() != null ? facturaElectronica.getClienteNombre() : Constantes.EMPTY, font12, colNombreCliente, firstRow, Element.ALIGN_LEFT);
		addText(cb, "Cédula:", font14, colLabelNit, firstRow, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getClienteCedula() != null ? facturaElectronica.getClienteCedula() : Constantes.EMPTY, font12, colCedulaCliente, firstRow, Element.ALIGN_LEFT);
		addText(cb, "Correo:", font14, colLabelCorreo, firstRow, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getClienteCorreo() != null ? facturaElectronica.getClienteCorreo() : Constantes.EMPTY, font12, colCorreo, firstRow, Element.ALIGN_LEFT);
		addText(cb, "Teléfono:", font14, colLabelTelefono, firstRow, Element.ALIGN_LEFT);
		addText(cb, facturaElectronica.getClienteTelefono() != null ? facturaElectronica.getClienteTelefono() : Constantes.EMPTY, font12, colTelefono, firstRow, Element.ALIGN_LEFT);

	}

	private void addProductsTittle(PdfContentByte cb, Font font) {
		float row = PageSize.TABLOID.rotate().getHeight() - 227;
		float col1 = 11;
		float col2 = 70;
		float col3 = 140;
		float colUnid = 560;
		float col4 = 600;
		float col5 = 665;
		float col6 = 750;
		float col7 = 810;
		float col8 = 885;
		float col9 = 945;
		float col10 = 1000;
		float col11 = 1075;
		float col12 = 1148;

		cb.setColorFill(bColor);
		cb.rectangle(10, PageSize.TABLOID.rotate().getHeight() - 230, 1205, 15);
		cb.fill();
		cb.setColorFill(BaseColor.BLACK);
		addText(cb, "Línea", font, col1, row, Element.ALIGN_LEFT);
		addText(cb, "Código", font, col2, row, Element.ALIGN_LEFT);
		addText(cb, "Detalle", font, col3, row, Element.ALIGN_LEFT);
		addText(cb, "Unidad", font, colUnid, row, Element.ALIGN_LEFT);
		addText(cb, "Cantidad", font, col4, row, Element.ALIGN_LEFT);
		addText(cb, "Precio", font, col5, row, Element.ALIGN_LEFT);
		addText(cb, "Monto", font, col6, row, Element.ALIGN_LEFT);
		addText(cb, "Descuento", font, col7, row, Element.ALIGN_LEFT);
		addText(cb, "Sub Total", font, col8, row, Element.ALIGN_LEFT);
		addText(cb, "Impuestos", font, col9, row, Element.ALIGN_LEFT);
		addText(cb, "Monto Imp", font, col10, row, Element.ALIGN_LEFT);
		addText(cb, "Exento", font, col11, row, Element.ALIGN_LEFT);
		addText(cb, "Total linea", font, col12, row, Element.ALIGN_LEFT);
	}

	private void addProducts(PdfContentByte cb, Font font, Document document, FacturaElectronica facturaElectronica, String dir) throws IOException, DocumentException {
		float row1 = PageSize.TABLOID.rotate().getHeight() - 245;
		float row = 245;

		// Ultima Linea
		float row20 = PageSize.TABLOID.rotate().getHeight() - 780;
		//
		float col1 = 30;
		float col2 = 35;
		float col3 = 138;
		float colUnidad = 585;
		float col4 = 646;
		float col5 = 715;
		float col6 = 790;
		float col7 = 862;
		float col8 = 940;
		float col9 = 980;
		float col10 = 1060;
		float col11 = 1130;
		float col12 = 1205;
		float col20 = 150;
		cb.setColorFill(BaseColor.BLACK);
		Integer contadorLinea = 0;
		Integer pag = 1;
		//
		List<DetalleFacturaElectronica> detalleFacturaElectronica = facturaElectronica.getDetalleFacturaElectronica();
		for (int i = 0; i < detalleFacturaElectronica.size(); i++) {
			contadorLinea += 1;
			if (contadorLinea > 32) {
				row = 245;
				document.newPage();
				contadorLinea = 1;
				pag = pag + 1;
				String fontSansRegular = dir + "/data/fonts/OpenSans-Regular.ttf";
				String fontSansBold = dir + "/data/fonts/OpenSans-Bold.ttf";
				BaseFont sansRegular = BaseFont.createFont(fontSansRegular, BaseFont.WINANSI, BaseFont.EMBEDDED);
				BaseFont sansBold = BaseFont.createFont(fontSansBold, BaseFont.WINANSI, BaseFont.EMBEDDED);
				this.addLogo(cb, dir, document, facturaElectronica.get_logo());
				Font font10 = new Font(sansRegular, 10);
				Font font10B = new Font(sansBold, 10);
				this.addEmisor(cb, font10B, font10, facturaElectronica, dir);
				this.addClient(cb, font10B, font10, facturaElectronica);
				this.addRectanguleProducts(cb, font10B);
				this.addProductsTittle(cb, font10B);
				cb.setColorFill(BaseColor.BLACK);
			}
			if (contadorLinea == 1) {
				addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getLinea()), font, col1, row1, Element.ALIGN_RIGHT);
				addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCodigo()), font, col2, row1, Element.ALIGN_LEFT);
				addText(cb, detalleFacturaElectronica.get(i).getDescripcion(), font, col3, row1, Element.ALIGN_LEFT);
				addText(cb, detalleFacturaElectronica.get(i).getUnidad(), font, colUnidad, row1, Element.ALIGN_RIGHT);
				addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCantidad()), font, col4, row1, Element.ALIGN_RIGHT);
				addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getPrecioU()), font, col5, row1, Element.ALIGN_RIGHT);
				addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getMonto()), font, col6, row1, Element.ALIGN_RIGHT);
				addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getDescuento()), font, col7, row1, Element.ALIGN_RIGHT);
				addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getSubtotal()), font, col8, row1, Element.ALIGN_RIGHT);
				addText(cb, String.format("%.2f", detalleFacturaElectronica.get(i).getTarifaIva()), font, col9, row1, Element.ALIGN_RIGHT);
				addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getImpuesto()), font, col10, row1, Element.ALIGN_RIGHT);
				addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getExento()), font, col11, row1, Element.ALIGN_RIGHT);
				addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getTotal()), font, col12, row1, Element.ALIGN_RIGHT);
				//
				DateFormat fechaCompleta = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				addText(cb, "Emitida conforme lo establecido en la resolución de Facturación Electrónica, N° DGT-02-09 nueve de enero de dos mil nueve de la Dirección General de Tributación, a las " + fechaCompleta.format(new Date()) + " horas", font, col20, row20, Element.ALIGN_LEFT);
				addText(cb, "Pag: " + pag, font, 1180, row20, Element.ALIGN_LEFT);
				//

			} else {
				row += 15;
				float rowDinamico = PageSize.TABLOID.rotate().getHeight() - row;
				addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getLinea()), font, col1, rowDinamico, Element.ALIGN_RIGHT);
				addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCodigo()), font, col2, rowDinamico, Element.ALIGN_LEFT);
				addText(cb, detalleFacturaElectronica.get(i).getDescripcion(), font, col3, rowDinamico, Element.ALIGN_LEFT);
				addText(cb, detalleFacturaElectronica.get(i).getUnidad(), font, colUnidad, rowDinamico, Element.ALIGN_RIGHT);
				addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCantidad()), font, col4, rowDinamico, Element.ALIGN_RIGHT);
				addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getPrecioU()), font, col5, rowDinamico, Element.ALIGN_RIGHT);
				addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getMonto()), font, col6, rowDinamico, Element.ALIGN_RIGHT);
				addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getDescuento()), font, col7, rowDinamico, Element.ALIGN_RIGHT);
				addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getSubtotal()), font, col8, rowDinamico, Element.ALIGN_RIGHT);
				addText(cb, String.format("%.2f", detalleFacturaElectronica.get(i).getTarifaIva()), font, col9, rowDinamico, Element.ALIGN_RIGHT);
				addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getImpuesto()), font, col10, rowDinamico, Element.ALIGN_RIGHT);
				addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getExento()), font, col11, rowDinamico, Element.ALIGN_RIGHT);
				addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getTotal()), font, col12, rowDinamico, Element.ALIGN_RIGHT);

			}
		}

	}

	private void addTotals(PdfContentByte cb, Font font, Font fontB, FacturaElectronica facturaElectronica) {
		float col1 = 635;
		float col2 = 738;
		float col3 = 842;
		float col4 = 1001;
		float col5 = 1101;
		float col6 = 1201;
		//
		float col7 = 579;
		float col8 = 657;
		float col9 = 760;
		float col10 = 920;
		float col11 = 1037;
		float col12 = 1106;
		//
		float row1 = PageSize.TABLOID.rotate().getHeight() - 723;
		float row2 = PageSize.TABLOID.rotate().getHeight() - 743;
		// Cuadro 5
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 755, 538, PageSize.TABLOID.rotate().getHeight() - 755);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 755, 1215, PageSize.TABLOID.rotate().getHeight() - 715);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 715, 538, PageSize.TABLOID.rotate().getHeight() - 715);
		addLine(cb, BaseColor.GRAY, 538, PageSize.TABLOID.rotate().getHeight() - 715, 538, PageSize.TABLOID.rotate().getHeight() - 755);
		// Fondo
		cb.setColorFill(bColor);
		cb.rectangle(538, PageSize.TABLOID.rotate().getHeight() - 732, 677, 20);
		cb.fill();
		cb.setColorFill(BaseColor.BLACK);
		// Etiquetas Totales
		addText(cb, "Total venta", fontB, col7, row1, Element.ALIGN_LEFT);
		addText(cb, "Total descuento", fontB, col8, row1, Element.ALIGN_LEFT);
		addText(cb, "Total venta neta", fontB, col9, row1, Element.ALIGN_LEFT);
		addText(cb, "Total impuestos", fontB, col10, row1, Element.ALIGN_LEFT);
		addText(cb, "Total exento", fontB, col11, row1, Element.ALIGN_LEFT);
		addText(cb, "Total comprobante", fontB, col12, row1, Element.ALIGN_LEFT);

		// Sumatorias Totales
		cb.setColorFill(BaseColor.BLACK);
		double monto = 0;
		double descuento = 0;
		double subTotal = 0;
		double impuesto = 0;
		double exento = 0;
		double total = 0;
		List<DetalleFacturaElectronica> detalleFacturaElectronica = facturaElectronica.getDetalleFacturaElectronica();
		for (int i = 0; i < detalleFacturaElectronica.size(); i++) {
			monto = monto + (double) Math.round(detalleFacturaElectronica.get(i).getMonto() * 100000d) / 100000d;
			descuento = descuento + (double) Math.round(detalleFacturaElectronica.get(i).getDescuento() * 100000d) / 100000d;
			subTotal = subTotal + (double) Math.round(detalleFacturaElectronica.get(i).getSubtotal() * 100000d) / 100000d;
			impuesto = impuesto + (double) Math.round(detalleFacturaElectronica.get(i).getImpuesto() * 100000d) / 100000d;
			exento = exento + (double) Math.round(detalleFacturaElectronica.get(i).getExento() * 100000d) / 100000d;
			total = total + (double) Math.round(detalleFacturaElectronica.get(i).getTotal() * 100000d) / 100000d;
		}
		addText(cb, String.format("%.5f", monto), font, col1, row2, Element.ALIGN_RIGHT);
		addText(cb, String.format("%.5f", descuento), font, col2, row2, Element.ALIGN_RIGHT);
		addText(cb, String.format("%.5f", subTotal), font, col3, row2, Element.ALIGN_RIGHT);
		addText(cb, String.format("%.5f", impuesto), font, col4, row2, Element.ALIGN_RIGHT);
		addText(cb, String.format("%.5f", exento), font, col5, row2, Element.ALIGN_RIGHT);
		addText(cb, String.format("%.5f", total), fontB, col6, row2, Element.ALIGN_RIGHT);

	}

	private void addRectanguleProducts(PdfContentByte cb, Font font) {
		// Cuadro 6
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 230, 10, PageSize.TABLOID.rotate().getHeight() - 230);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 710, 1215, PageSize.TABLOID.rotate().getHeight() - 230);
		// linea1
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 247, 10, PageSize.TABLOID.rotate().getHeight() - 247);
		// linea2
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 263, 10, PageSize.TABLOID.rotate().getHeight() - 263);
		// linea3
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 277, 10, PageSize.TABLOID.rotate().getHeight() - 277);

		// linea4
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 293, 10, PageSize.TABLOID.rotate().getHeight() - 293);

		// linea5
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 308, 10, PageSize.TABLOID.rotate().getHeight() - 308);
		// linea6
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 324, 10, PageSize.TABLOID.rotate().getHeight() - 324);

		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 340, 10, PageSize.TABLOID.rotate().getHeight() - 340);

		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 354, 10, PageSize.TABLOID.rotate().getHeight() - 354);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 370, 10, PageSize.TABLOID.rotate().getHeight() - 370);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 384, 10, PageSize.TABLOID.rotate().getHeight() - 384);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 400, 10, PageSize.TABLOID.rotate().getHeight() - 400);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 414, 10, PageSize.TABLOID.rotate().getHeight() - 414);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 428, 10, PageSize.TABLOID.rotate().getHeight() - 428);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 442, 10, PageSize.TABLOID.rotate().getHeight() - 442);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 458, 10, PageSize.TABLOID.rotate().getHeight() - 458);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 472, 10, PageSize.TABLOID.rotate().getHeight() - 472);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 488, 10, PageSize.TABLOID.rotate().getHeight() - 488);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 504, 10, PageSize.TABLOID.rotate().getHeight() - 504);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 518, 10, PageSize.TABLOID.rotate().getHeight() - 518);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 534, 10, PageSize.TABLOID.rotate().getHeight() - 534);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 548, 10, PageSize.TABLOID.rotate().getHeight() - 548);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 564, 10, PageSize.TABLOID.rotate().getHeight() - 564);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 578, 10, PageSize.TABLOID.rotate().getHeight() - 578);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 592, 10, PageSize.TABLOID.rotate().getHeight() - 592);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 607, 10, PageSize.TABLOID.rotate().getHeight() - 607);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 622, 10, PageSize.TABLOID.rotate().getHeight() - 622);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 637, 10, PageSize.TABLOID.rotate().getHeight() - 637);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 652, 10, PageSize.TABLOID.rotate().getHeight() - 652);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 667, 10, PageSize.TABLOID.rotate().getHeight() - 667);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 682, 10, PageSize.TABLOID.rotate().getHeight() - 682);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 696, 10, PageSize.TABLOID.rotate().getHeight() - 696);
		addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 711, 10, PageSize.TABLOID.rotate().getHeight() - 711);

		addLine(cb, BaseColor.GRAY, 10, PageSize.TABLOID.rotate().getHeight() - 710, 10, PageSize.TABLOID.rotate().getHeight() - 230);
		// Lineas verticales
		// addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 230, 10, PageSize.TABLOID.rotate().getHeight() - 20);
		// addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 230, 10, PageSize.TABLOID.rotate().getHeight() - 20);
		// Lineas Horizontales

		addLine(cb, BaseColor.GRAY, 34, PageSize.TABLOID.rotate().getHeight() - 230, 34, PageSize.TABLOID.rotate().getHeight() - 710);
		addLine(cb, BaseColor.GRAY, 135, PageSize.TABLOID.rotate().getHeight() - 230, 135, PageSize.TABLOID.rotate().getHeight() - 710);
		addLine(cb, BaseColor.GRAY, 560, PageSize.TABLOID.rotate().getHeight() - 230, 560, PageSize.TABLOID.rotate().getHeight() - 710);
		addLine(cb, BaseColor.GRAY, 595, PageSize.TABLOID.rotate().getHeight() - 230, 595, PageSize.TABLOID.rotate().getHeight() - 710);
		addLine(cb, BaseColor.GRAY, 650, PageSize.TABLOID.rotate().getHeight() - 230, 650, PageSize.TABLOID.rotate().getHeight() - 710);
		addLine(cb, BaseColor.GRAY, 720, PageSize.TABLOID.rotate().getHeight() - 230, 720, PageSize.TABLOID.rotate().getHeight() - 710);
		addLine(cb, BaseColor.GRAY, 795, PageSize.TABLOID.rotate().getHeight() - 230, 795, PageSize.TABLOID.rotate().getHeight() - 710);
		addLine(cb, BaseColor.GRAY, 865, PageSize.TABLOID.rotate().getHeight() - 230, 865, PageSize.TABLOID.rotate().getHeight() - 710);
		addLine(cb, BaseColor.GRAY, 945, PageSize.TABLOID.rotate().getHeight() - 230, 945, PageSize.TABLOID.rotate().getHeight() - 710);
		addLine(cb, BaseColor.GRAY, 1000, PageSize.TABLOID.rotate().getHeight() - 230, 1000, PageSize.TABLOID.rotate().getHeight() - 710);
		addLine(cb, BaseColor.GRAY, 1060, PageSize.TABLOID.rotate().getHeight() - 230, 1060, PageSize.TABLOID.rotate().getHeight() - 710);
		addLine(cb, BaseColor.GRAY, 1130, PageSize.TABLOID.rotate().getHeight() - 230, 1130, PageSize.TABLOID.rotate().getHeight() - 710);
	}

	private void addText(PdfContentByte cb, String text, Font font, float x, float y, int align) {
		Phrase phrase = new Phrase(text, font);
		ColumnText.showTextAligned(cb, align, phrase, x, y, 0);
		cb.saveState();
		cb.stroke();
		cb.restoreState();
	}

	private static void addLine(PdfContentByte cb, BaseColor baseColor, float mx, float my, float lx, float ly) {
		cb.setColorStroke(baseColor);
		cb.moveTo(mx, my);
		cb.lineTo(lx, ly);
		cb.stroke();
	}

}
