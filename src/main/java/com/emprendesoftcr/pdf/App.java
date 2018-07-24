package com.emprendesoftcr.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
	
	  BaseColor bColor = new BaseColor(0, 156, 152);

    public static ByteArrayOutputStream main(Long consecutivo, String tipoDoc, FacturaElectronica facturaElectronica) throws IOException, DocumentException, WriterException {
        String dir = System.getProperty("user.dir");
        App app = new App();
        String fontSansRegular = dir + "/data/fonts/OpenSans-Regular.ttf";
        String fontSansBold = dir + "/data/fonts/OpenSans-Bold.ttf";
        Document document = new Document(PageSize.TABLOID.rotate(), 10, 10, 10,10);
        String fileName = tipoDoc +"-"+ consecutivo + ".pdf";
//        FileOutputStream fileOutputStream = new FileOutputStream(dir + "/data/" + fileName);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, stream);
        
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        BaseFont sansRegular = BaseFont.createFont(fontSansRegular, BaseFont.WINANSI, BaseFont.EMBEDDED);
        BaseFont sansBold = BaseFont.createFont(fontSansBold, BaseFont.WINANSI, BaseFont.EMBEDDED);
        Font font10 = new Font(sansRegular, 10);
        Font font12 = new Font(sansRegular, 12);
        Font font10B = new Font(sansBold, 10);
        Font font14 = new Font(sansBold, 14);
        
        app.addLogo(cb, dir, document);
        app.addEmisor(cb, font10B, font10, facturaElectronica);
        app.addClient(cb, font10B, font10, facturaElectronica);
        app.addRectanguleProducts(cb, font10B);
        app.addProductsTittle(cb, font10B);
        app.addProducts(cb, font10, document, facturaElectronica, dir);
        app.addTotals(cb, font10, font10B, facturaElectronica);
        
        document.close();
        
        return stream;
    }

    private void addLogo(PdfContentByte cb, String dir, Document document) throws IOException, DocumentException {
        //Cuadro 1
        Image img = Image.getInstance(dir + "/data/logos/kolbi-logo.png");  
        cb.setColorStroke(bColor);
        cb.setColorFill(BaseColor.WHITE);
        cb.setLineWidth(1f);
        cb.roundRectangle(10, PageSize.TABLOID.rotate().getHeight() - 100, 640, 90, 5);
        cb.fillStroke();
        //Fondo
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

    private void addEmisor(PdfContentByte cb, Font font14, Font font12, FacturaElectronica facturaElectronica) {
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
      	//Cuadro 2
        cb.setColorStroke(bColor);
        cb.setColorFill(BaseColor.WHITE);
        cb.setLineWidth(1f);
        cb.roundRectangle(660, PageSize.TABLOID.rotate().getHeight() - 180, 555, 170, 5);
        cb.fillStroke();
        //Fondo
        cb.setColorFill(bColor);
        cb.roundRectangle(660, PageSize.TABLOID.rotate().getHeight() - 180, 115, 170, 5);
        cb.fill();
        cb.setColorFill(BaseColor.WHITE);        
        //
        addText(cb, "Nombre", font14, leftColTitle, firstRow, Element.ALIGN_LEFT);
        addText(cb, "Identificacion", font14, leftColTitle, secondRow, Element.ALIGN_LEFT);
        addText(cb, "Telefono", font14, leftColTitle, thirdRow, Element.ALIGN_LEFT);
        addText(cb, "Fax", font14, leftColTitle, fourthRow, Element.ALIGN_LEFT);
        addText(cb, "Correo", font14, leftColTitle, fiveRow, Element.ALIGN_LEFT);
        addText(cb, "Tipo Documento", font14, rightColTitle, firstRow, Element.ALIGN_LEFT);
        addText(cb, "No.Clave", font14, rightColTitle, secondRow, Element.ALIGN_LEFT);
        addText(cb, "No.Consecutivo", font14, rightColTitle, thirdRow, Element.ALIGN_LEFT);
        addText(cb, "Fecha emsion", font14, rightColTitle, fourthRow, Element.ALIGN_LEFT);
        addText(cb, "Plazo credito", font14, rightColTitle, fiveRow, Element.ALIGN_LEFT);
        addText(cb, "Condicion venta", font14, rightColTitle, sixRow, Element.ALIGN_LEFT);
        addText(cb, "Medio pago", font14, rightColTitle, sevenRow, Element.ALIGN_LEFT);
        addText(cb, "Moneda", font14, rightColTitle, eightRow, Element.ALIGN_LEFT);
        addText(cb, "Tipo cambio $ dolar", font14, rightColTitle, nineRow, Element.ALIGN_LEFT);
        //
        cb.setColorFill(BaseColor.BLACK);
        //
        addText(cb, facturaElectronica.getEmisorNombre(), font12, leftColContent, firstRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getEmisorCedula(), font12, leftColContent, secondRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getEmisorTelefono(), font12, leftColContent, thirdRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getEmisorFax(), font12, leftColContent, fourthRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getEmisorCorreo(), font12, leftColContent, fiveRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getTipoDocumento(), font12, rightColContent, firstRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getClave(), font12, rightColContent, secondRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getConsecutivo(), font12, rightColContent, thirdRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getFechaEmision(), font12, rightColContent, fourthRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getPlazoCredito(), font12, rightColContent, fiveRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getCondicionVenta(), font12, rightColContent, sixRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getMedioPago(), font12, rightColContent, sevenRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getMoneda(), font12, rightColContent, eightRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getTipoCambio(), font12, rightColContent, nineRow, Element.ALIGN_LEFT);          
        //Referencia en caso que sea nota de credito o nota de debito
        //Cuadro 4
        if (!facturaElectronica.getTipoDocumento().equals("Factura Electronica")){
	        cb.setColorStroke(bColor);
	        cb.setColorFill(BaseColor.WHITE);
	        cb.setLineWidth(1f);
	        cb.roundRectangle(660, PageSize.TABLOID.rotate().getHeight() - 300, 555, 100, 5);
	        cb.fillStroke();
	        ////Fondo
	        cb.setColorFill(bColor);
	        cb.roundRectangle(660, PageSize.TABLOID.rotate().getHeight() - 300, 115, 100, 5);
	        cb.fill();
	        cb.setColorFill(BaseColor.WHITE);        
	        //
	        addText(cb, "Referencia", font14, rightColTitle, tenRow, Element.ALIGN_LEFT);
	        addText(cb, "Tipo documento", font14, rightColTitle, elevenRow, Element.ALIGN_LEFT);
	        addText(cb, "No.Clave", font14, rightColTitle, twelveRow, Element.ALIGN_LEFT);
	        addText(cb, "No.Consecutivo", font14, rightColTitle, thirteenRow, Element.ALIGN_LEFT);
	        addText(cb, "Fecha emsion", font14, rightColTitle, fourteenRow, Element.ALIGN_LEFT);
	        //
	        cb.setColorFill(BaseColor.BLACK);
	        addText(cb, facturaElectronica.getTipoDocumento(), font12, rightColContent, elevenRow, Element.ALIGN_LEFT);
	        addText(cb, facturaElectronica.getReferencia(), font12, rightColContent, twelveRow, Element.ALIGN_LEFT);
	        addText(cb, facturaElectronica.getConsecutivo(), font12, rightColContent, thirteenRow, Element.ALIGN_LEFT);
	        addText(cb, facturaElectronica.getFechaEmision(), font12, rightColContent, fourteenRow, Element.ALIGN_LEFT);
        }
    }

    private void addClient(PdfContentByte cb, Font font14, Font font12, FacturaElectronica facturaElectronica) {
        float zeroRow = PageSize.TABLOID.rotate().getHeight() - 140;
        float leftColTitle = 20;
        float leftColContent = 140;
        float firstRow = PageSize.TABLOID.rotate().getHeight() - 160;
        float secondRow = PageSize.TABLOID.rotate().getHeight() - 175;
        float thirdRow = PageSize.TABLOID.rotate().getHeight() - 190;
        float fourthRow = PageSize.TABLOID.rotate().getHeight() - 210;
        float fiveRow = PageSize.TABLOID.rotate().getHeight() - 230;
        float sixRow = PageSize.TABLOID.rotate().getHeight() - 245;
        float seventRow = PageSize.TABLOID.rotate().getHeight() - 260;
        float eightRow = PageSize.TABLOID.rotate().getHeight() - 275;
        float nineRow = PageSize.TABLOID.rotate().getHeight() - 290;
        float tenRow = PageSize.TABLOID.rotate().getHeight() - 305;
        float elevenRow = PageSize.TABLOID.rotate().getHeight() - 320;
        //Cuadro 3
        cb.setColorStroke(bColor);
        cb.setColorFill(BaseColor.WHITE);
        cb.setLineWidth(1f);
        cb.roundRectangle(10, PageSize.TABLOID.rotate().getHeight() - 330, 640, 205, 5);
        cb.fillStroke();
        ////Fondo
        cb.setColorFill(bColor);
        cb.roundRectangle(10, PageSize.TABLOID.rotate().getHeight() - 330, 127, 205, 5);
        cb.fill();
        cb.setColorFill(BaseColor.WHITE);
        //Cliente
        addText(cb, "CLIENTE", font14, leftColTitle, zeroRow, Element.ALIGN_LEFT);
        addText(cb, "Nombre", font14, leftColTitle, firstRow, Element.ALIGN_LEFT);
        addText(cb, "Nombre Comercial", font14, leftColTitle, secondRow, Element.ALIGN_LEFT);
        addText(cb, "Correo", font14, leftColTitle, thirdRow, Element.ALIGN_LEFT);
        //Mas informacion
        addText(cb, "MAS INFORMACION", font14, leftColTitle, fourthRow, Element.ALIGN_LEFT);
        addText(cb, "Cedula", font14, leftColTitle, fiveRow, Element.ALIGN_LEFT);
        addText(cb, "Telefono", font14, leftColTitle, sixRow, Element.ALIGN_LEFT);
        addText(cb, "Cta.Cliente y Grupo", font14, leftColTitle, seventRow, Element.ALIGN_LEFT);
        addText(cb, "Mes Cobro", font14, leftColTitle, eightRow, Element.ALIGN_LEFT);
        addText(cb, "Periodo de Cobro", font14, leftColTitle, nineRow, Element.ALIGN_LEFT);
        addText(cb, "Fecha Vence", font14, leftColTitle, tenRow, Element.ALIGN_LEFT);
        addText(cb, "Paquete Comercial", font14, leftColTitle, elevenRow, Element.ALIGN_LEFT);
        //
        cb.setColorFill(BaseColor.BLACK);
        addText(cb, facturaElectronica.getClienteNombre(), font12, leftColContent, firstRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getClienteNombreComercial(), font12, leftColContent, secondRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getClienteCorreo(), font12, leftColContent, thirdRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getClienteCedula(), font12, leftColContent, fiveRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getClienteTelefono(), font12, leftColContent, sixRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getClienteCtaGrupo(), font12, leftColContent, seventRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getClienteMesCobro() , font12, leftColContent, eightRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getClientePeriodoCobro(), font12, leftColContent, nineRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getClienteFechaVence(), font12, leftColContent, tenRow, Element.ALIGN_LEFT);
        addText(cb, facturaElectronica.getClientePaqueteComercial(), font12, leftColContent, elevenRow, Element.ALIGN_LEFT);           
    }

    private void addProductsTittle(PdfContentByte cb, Font font) {
        float row = PageSize.TABLOID.rotate().getHeight() - 370;
        float col1 = 20;
        float col2 = 70;
        float col3 = 223;
        float col4 = 275;
        float col5 = 505;
        float col6 = 603;
        float col7 = 683;
        float col8 = 795;
        float col9 = 845;
        float col10 = 948;
        float col11 = 1065;
        float col12 = 1148;
        cb.setColorFill(bColor);
        cb.rectangle(10, PageSize.TABLOID.rotate().getHeight() - 390, 1205, 40);
        cb.fill();
        cb.setColorFill(BaseColor.WHITE);
        addText(cb, "Código", font, col1, row, Element.ALIGN_LEFT);
        addText(cb, "Unidad", font, col2, row, Element.ALIGN_LEFT);
        addText(cb, "Cantidad", font, col3, row, Element.ALIGN_LEFT);
        addText(cb, "Detalle", font, col4, row, Element.ALIGN_LEFT);
        addText(cb, "Precio", font, col5, row, Element.ALIGN_LEFT);
        addText(cb, "Monto", font, col6, row, Element.ALIGN_LEFT);
        addText(cb, "Descuento", font, col7, row, Element.ALIGN_LEFT);
        addText(cb, "Sub Total", font, col8, row, Element.ALIGN_LEFT);
        addText(cb, "Tarifa IVA", font, col9, row, Element.ALIGN_LEFT);
        addText(cb, "Monto IVA", font, col10, row, Element.ALIGN_LEFT);
        addText(cb, "Exento", font, col11, row, Element.ALIGN_LEFT);
        addText(cb, "Total linea", font, col12, row, Element.ALIGN_LEFT);
    }

    private void addProducts(PdfContentByte cb, Font font, Document document, FacturaElectronica facturaElectronica, String dir) throws IOException, DocumentException {    	  
        float row1 = PageSize.TABLOID.rotate().getHeight() - 400;
        float row2= PageSize.TABLOID.rotate().getHeight() - 420;
        float row3= PageSize.TABLOID.rotate().getHeight() - 440;
        float row4= PageSize.TABLOID.rotate().getHeight() - 460;
        float row5= PageSize.TABLOID.rotate().getHeight() - 480;
        float row6= PageSize.TABLOID.rotate().getHeight() - 500;
        float row7= PageSize.TABLOID.rotate().getHeight() - 520;
        float row8= PageSize.TABLOID.rotate().getHeight() - 540;
        float row9= PageSize.TABLOID.rotate().getHeight() - 560;
        float row10= PageSize.TABLOID.rotate().getHeight() - 580;
        float row11= PageSize.TABLOID.rotate().getHeight() - 600;
        float row12= PageSize.TABLOID.rotate().getHeight() - 620;
        float row13= PageSize.TABLOID.rotate().getHeight() - 640;
        float row14= PageSize.TABLOID.rotate().getHeight() - 660;
        float row15= PageSize.TABLOID.rotate().getHeight() - 680;
        float row16= PageSize.TABLOID.rotate().getHeight() - 700;
        //Ultima Linea
        float row20= PageSize.TABLOID.rotate().getHeight() - 780;
        //
        float col1 = 64;
        float col2 = 70;
        float col3 = 266;
        float col4 = 275;
        float col5 = 535;
        float col6 = 635;
        float col7 = 737;
        float col8 = 843;
        float col9 = 896;
        float col10 = 1000;
        float col11 = 1100;
        float col12 = 1201;
        float col20 = 150;
        cb.setColorFill(BaseColor.BLACK);
        Integer contadorLinea = 0;
        Integer pag = 1;
        //
        List<DetalleFacturaElectronica> detalleFacturaElectronica = facturaElectronica.getDetalleFacturaElectronica();
				for (int i = 0; i < detalleFacturaElectronica.size(); i++) {
					contadorLinea = contadorLinea + 1;		     
					if (contadorLinea > 16){
						document.newPage();
	          contadorLinea = 1;
	          pag = pag + 1;
	          String fontSansRegular = dir + "/data/fonts/OpenSans-Regular.ttf";
	          String fontSansBold = dir + "/data/fonts/OpenSans-Bold.ttf";
	          BaseFont sansRegular = BaseFont.createFont(fontSansRegular, BaseFont.WINANSI, BaseFont.EMBEDDED);
	          BaseFont sansBold = BaseFont.createFont(fontSansBold, BaseFont.WINANSI, BaseFont.EMBEDDED);
	          this.addLogo(cb, dir, document);
	          Font font10 = new Font(sansRegular, 10);
	          Font font10B = new Font(sansBold, 10);
	          this.addEmisor(cb, font10B, font10, facturaElectronica);
	          this.addClient(cb, font10B, font10, facturaElectronica);
	          this.addRectanguleProducts(cb, font10B);
	          this.addProductsTittle(cb, font10B);
	          cb.setColorFill(BaseColor.BLACK);
					}
					
          switch (contadorLinea) {
						case 1:
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCodigo()), font, col1, row1, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getUnidad(), font, col2, row1, Element.ALIGN_LEFT);
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCantidad()), font, col3, row1, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getDescripcion(), font, col4, row1, Element.ALIGN_LEFT);
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
		          addText(cb, "Emitida conforme lo establecido en la resolución de Facturación Electrónica, N° DGT-02-09 nueve de enero de dos mil nueve de la Dirección General de Tributación, a las " +  fechaCompleta.format(new Date()) +" horas", font, col20, row20, Element.ALIGN_LEFT);
		          addText(cb, "Pag: "+ pag, font, 1180, row20, Element.ALIGN_LEFT);		          
		          //		          
							break;
						case 2:
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCodigo()), font, col1, row2, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getUnidad(), font, col2, row2, Element.ALIGN_LEFT);
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCantidad()), font, col3, row2, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getDescripcion(), font, col4, row2, Element.ALIGN_LEFT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getPrecioU()), font, col5, row2, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getMonto()), font, col6, row2, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getDescuento()), font, col7, row2, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getSubtotal()), font, col8, row2, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.2f", detalleFacturaElectronica.get(i).getTarifaIva()), font, col9, row2, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getImpuesto()), font, col10, row2, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getExento()), font, col11, row2, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getTotal()), font, col12, row2, Element.ALIGN_RIGHT);
							break;
						case 3:
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCodigo()), font, col1, row3, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getUnidad(), font, col2, row3, Element.ALIGN_LEFT);
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCantidad()), font, col3, row3, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getDescripcion(), font, col4, row3, Element.ALIGN_LEFT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getPrecioU()), font, col5, row3, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getMonto()), font, col6, row3, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getDescuento()), font, col7, row3, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getSubtotal()), font, col8, row3, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.2f", detalleFacturaElectronica.get(i).getTarifaIva()), font, col9, row3, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getImpuesto()), font, col10, row3, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getExento()), font, col11, row3, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getTotal()), font, col12, row3, Element.ALIGN_RIGHT);
							break;
						case 4:
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCodigo()), font, col1, row4, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getUnidad(), font, col2, row4, Element.ALIGN_LEFT);
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCantidad()), font, col3, row4, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getDescripcion(), font, col4, row4, Element.ALIGN_LEFT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getPrecioU()), font, col5, row4, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getMonto()), font, col6, row4, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getDescuento()), font, col7, row4, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getSubtotal()), font, col8, row4, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.2f", detalleFacturaElectronica.get(i).getTarifaIva()), font, col9, row4, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getImpuesto()), font, col10, row4, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getExento()), font, col11, row4, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getTotal()), font, col12, row4, Element.ALIGN_RIGHT);
							break;
						case 5:
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCodigo()), font, col1, row5, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getUnidad(), font, col2, row5, Element.ALIGN_LEFT);
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCantidad()), font, col3, row5, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getDescripcion(), font, col4, row5, Element.ALIGN_LEFT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getPrecioU()), font, col5, row5, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getMonto()), font, col6, row5, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getDescuento()), font, col7, row5, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getSubtotal()), font, col8, row5, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.2f", detalleFacturaElectronica.get(i).getTarifaIva()), font, col9, row5, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getImpuesto()), font, col10, row5, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getExento()), font, col11, row5, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getTotal()), font, col12, row5, Element.ALIGN_RIGHT);
							break;
						case 6:
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCodigo()), font, col1, row6, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getUnidad(), font, col2, row6, Element.ALIGN_LEFT);
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCantidad()), font, col3, row6, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getDescripcion(), font, col4, row6, Element.ALIGN_LEFT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getPrecioU()), font, col5, row6, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getMonto()), font, col6, row6, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getDescuento()), font, col7, row6, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getSubtotal()), font, col8, row6, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.2f", detalleFacturaElectronica.get(i).getTarifaIva()), font, col9, row6, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getImpuesto()), font, col10, row6, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getExento()), font, col11, row6, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getTotal()), font, col12, row6, Element.ALIGN_RIGHT);
		          break;
						case 7:
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCodigo()), font, col1, row7, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getUnidad(), font, col2, row7, Element.ALIGN_LEFT);
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCantidad()), font, col3, row7, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getDescripcion(), font, col4, row7, Element.ALIGN_LEFT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getPrecioU()), font, col5, row7, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getMonto()), font, col6, row7, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getDescuento()), font, col7, row7, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getSubtotal()), font, col8, row7, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.2f", detalleFacturaElectronica.get(i).getTarifaIva()), font, col9, row7, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getImpuesto()), font, col10, row7, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getExento()), font, col11, row7, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getTotal()), font, col12, row7, Element.ALIGN_RIGHT);
		          break;
						case 8:
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCodigo()), font, col1, row8, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getUnidad(), font, col2, row8, Element.ALIGN_LEFT);
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCantidad()), font, col3, row8, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getDescripcion(), font, col4, row8, Element.ALIGN_LEFT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getPrecioU()), font, col5, row8, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getMonto()), font, col6, row8, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getDescuento()), font, col7, row8, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getSubtotal()), font, col8, row8, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.2f", detalleFacturaElectronica.get(i).getTarifaIva()), font, col9, row8, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getImpuesto()), font, col10, row8, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getExento()), font, col11, row8, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getTotal()), font, col12, row8, Element.ALIGN_RIGHT);
							break;							
						case 9:
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCodigo()), font, col1, row9, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getUnidad(), font, col2, row9, Element.ALIGN_LEFT);
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCantidad()), font, col3, row9, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getDescripcion(), font, col4, row9, Element.ALIGN_LEFT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getPrecioU()), font, col5, row9, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getMonto()), font, col6, row9, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getDescuento()), font, col7, row9, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getSubtotal()), font, col8, row9, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.2f", detalleFacturaElectronica.get(i).getTarifaIva()), font, col9, row9, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getImpuesto()), font, col10, row9, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getExento()), font, col11, row9, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getTotal()), font, col12, row9, Element.ALIGN_RIGHT);
							break;						
						case 10:
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCodigo()), font, col1, row10, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getUnidad(), font, col2, row10, Element.ALIGN_LEFT);
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCantidad()), font, col3, row10, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getDescripcion(), font, col4, row10, Element.ALIGN_LEFT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getPrecioU()), font, col5, row10, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getMonto()), font, col6, row10, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getDescuento()), font, col7, row10, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getSubtotal()), font, col8, row10, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.2f", detalleFacturaElectronica.get(i).getTarifaIva()), font, col9, row10, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getImpuesto()), font, col10, row10, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getExento()), font, col11, row10, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getTotal()), font, col12, row10, Element.ALIGN_RIGHT);
							break;		
						case 11:
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCodigo()), font, col1, row11, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getUnidad(), font, col2, row11, Element.ALIGN_LEFT);
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCantidad()), font, col3, row11, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getDescripcion(), font, col4, row11, Element.ALIGN_LEFT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getPrecioU()), font, col5, row11, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getMonto()), font, col6, row11, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getDescuento()), font, col7, row11, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getSubtotal()), font, col8, row11, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.2f", detalleFacturaElectronica.get(i).getTarifaIva()), font, col9, row11, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getImpuesto()), font, col10, row11, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getExento()), font, col11, row11, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getTotal()), font, col12, row11, Element.ALIGN_RIGHT);
							break;	
						case 12:
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCodigo()), font, col1, row12, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getUnidad(), font, col2, row12, Element.ALIGN_LEFT);
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCantidad()), font, col3, row12, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getDescripcion(), font, col4, row12, Element.ALIGN_LEFT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getPrecioU()), font, col5, row12, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getMonto()), font, col6, row12, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getDescuento()), font, col7, row12, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getSubtotal()), font, col8, row12, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.2f", detalleFacturaElectronica.get(i).getTarifaIva()), font, col9, row12, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getImpuesto()), font, col10, row12, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getExento()), font, col11, row12, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getTotal()), font, col12, row12, Element.ALIGN_RIGHT);
							break;								
						case 13:
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCodigo()), font, col1, row13, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getUnidad(), font, col2, row13, Element.ALIGN_LEFT);
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCantidad()), font, col3, row13, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getDescripcion(), font, col4, row13, Element.ALIGN_LEFT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getPrecioU()), font, col5, row13, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getMonto()), font, col6, row13, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getDescuento()), font, col7, row13, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getSubtotal()), font, col8, row13, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.2f", detalleFacturaElectronica.get(i).getTarifaIva()), font, col9, row13, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getImpuesto()), font, col10, row13, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getExento()), font, col11, row13, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getTotal()), font, col12, row13, Element.ALIGN_RIGHT);
		          break;
						case 14:
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCodigo()), font, col1, row14, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getUnidad(), font, col2, row14, Element.ALIGN_LEFT);
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCantidad()), font, col3, row14, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getDescripcion(), font, col4, row14, Element.ALIGN_LEFT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getPrecioU()), font, col5, row14, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getMonto()), font, col6, row14, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getDescuento()), font, col7, row14, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getSubtotal()), font, col8, row14, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.2f", detalleFacturaElectronica.get(i).getTarifaIva()), font, col9, row14, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getImpuesto()), font, col10, row14, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getExento()), font, col11, row14, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getTotal()), font, col12, row14, Element.ALIGN_RIGHT);
							break;
						case 15:
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCodigo()), font, col1, row15, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getUnidad(), font, col2, row15, Element.ALIGN_LEFT);
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCantidad()), font, col3, row15, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getDescripcion(), font, col4, row15, Element.ALIGN_LEFT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getPrecioU()), font, col5, row15, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getMonto()), font, col6, row15, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getDescuento()), font, col7, row15, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getSubtotal()), font, col8, row15, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.2f", detalleFacturaElectronica.get(i).getTarifaIva()), font, col9, row15, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getImpuesto()), font, col10, row15, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getExento()), font, col11, row15, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getTotal()), font, col12, row15, Element.ALIGN_RIGHT);
							break;								
						case 16:
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCodigo()), font, col1, row16, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getUnidad(), font, col2, row16, Element.ALIGN_LEFT);
		          addText(cb, String.valueOf(detalleFacturaElectronica.get(i).getCantidad()), font, col3, row16, Element.ALIGN_RIGHT);
		          addText(cb, detalleFacturaElectronica.get(i).getDescripcion(), font, col4, row16, Element.ALIGN_LEFT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getPrecioU()), font, col5, row16, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getMonto()), font, col6, row16, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getDescuento()), font, col7, row16, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getSubtotal()), font, col8, row16, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.2f", detalleFacturaElectronica.get(i).getTarifaIva()), font, col9, row16, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getImpuesto()), font, col10, row16, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getExento()), font, col11, row16, Element.ALIGN_RIGHT);
		          addText(cb, String.format("%.5f", detalleFacturaElectronica.get(i).getTotal()), font, col12, row16, Element.ALIGN_RIGHT);
							break;				
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
      float row1= PageSize.TABLOID.rotate().getHeight() - 720;
      float row2= PageSize.TABLOID.rotate().getHeight() - 740;
      //Cuadro 5
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 750, 538, PageSize.TABLOID.rotate().getHeight() - 750);
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 750, 1215, PageSize.TABLOID.rotate().getHeight() - 705);
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 705, 538, PageSize.TABLOID.rotate().getHeight() - 705);
      addLine(cb, BaseColor.GRAY, 538, PageSize.TABLOID.rotate().getHeight() - 705, 538, PageSize.TABLOID.rotate().getHeight() - 750);
      //Fondo
      cb.setColorFill(bColor);
      cb.rectangle(538, PageSize.TABLOID.rotate().getHeight() - 725, 677, 20);
      cb.fill();
      cb.setColorFill(BaseColor.WHITE);        
      //Etiquetas Totales
      addText(cb, "Total venta", fontB, col7, row1, Element.ALIGN_LEFT);
      addText(cb, "Total descuento", fontB, col8, row1, Element.ALIGN_LEFT);
      addText(cb, "Total venta neta", fontB, col9, row1, Element.ALIGN_LEFT);
      addText(cb, "Total impuestos", fontB, col10, row1, Element.ALIGN_LEFT);
      addText(cb, "Total exento", fontB, col11, row1, Element.ALIGN_LEFT);
      addText(cb, "Total comprobante", fontB, col12, row1, Element.ALIGN_LEFT);
      
      //Sumatorias Totales
      cb.setColorFill(BaseColor.BLACK);
      double monto = 0;
      double descuento = 0;
      double subTotal = 0;
      double impuesto = 0;
      double exento = 0;
      double total = 0;
      List<DetalleFacturaElectronica> detalleFacturaElectronica = facturaElectronica.getDetalleFacturaElectronica();
			for (int i = 0; i < detalleFacturaElectronica.size(); i++) {
					monto = monto + (double)Math.round(detalleFacturaElectronica.get(i).getMonto() * 100000d) / 100000d;
					descuento = descuento + (double)Math.round(detalleFacturaElectronica.get(i).getDescuento() * 100000d) / 100000d;
					subTotal = subTotal + (double)Math.round(detalleFacturaElectronica.get(i).getSubtotal() * 100000d) / 100000d;
					impuesto = impuesto + (double)Math.round(detalleFacturaElectronica.get(i).getImpuesto() * 100000d) / 100000d;
					exento = exento + (double)Math.round(detalleFacturaElectronica.get(i).getExento() * 100000d) / 100000d;
					total = total + (double)Math.round(detalleFacturaElectronica.get(i).getTotal() * 100000d) / 100000d;
			}
      addText(cb, String.format("%.5f",monto), font, col1, row2, Element.ALIGN_RIGHT);
      addText(cb, String.format("%.5f",descuento), font, col2, row2, Element.ALIGN_RIGHT);
      addText(cb, String.format("%.5f",subTotal), font, col3, row2, Element.ALIGN_RIGHT);
      addText(cb, String.format("%.5f",impuesto), font, col4, row2, Element.ALIGN_RIGHT);
      addText(cb, String.format("%.5f",exento), font, col5, row2, Element.ALIGN_RIGHT);
      addText(cb, String.format("%.5f",total), fontB, col6, row2, Element.ALIGN_RIGHT);
	      
    }
    
    private void addRectanguleProducts(PdfContentByte cb, Font font) {
      //Cuadro 6
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 705, 10, PageSize.TABLOID.rotate().getHeight() - 705);
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 705, 1215, PageSize.TABLOID.rotate().getHeight() - 350);
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 350, 10, PageSize.TABLOID.rotate().getHeight() - 350);
      addLine(cb, BaseColor.GRAY, 10, PageSize.TABLOID.rotate().getHeight() - 350, 10, PageSize.TABLOID.rotate().getHeight() - 705);
      //Lineas verticales
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 406, 10, PageSize.TABLOID.rotate().getHeight() - 406);
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 426, 10, PageSize.TABLOID.rotate().getHeight() - 426);
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 446, 10, PageSize.TABLOID.rotate().getHeight() - 446);
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 466, 10, PageSize.TABLOID.rotate().getHeight() - 466);
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 486, 10, PageSize.TABLOID.rotate().getHeight() - 486);      
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 506, 10, PageSize.TABLOID.rotate().getHeight() - 506);
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 526, 10, PageSize.TABLOID.rotate().getHeight() - 526);
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 546, 10, PageSize.TABLOID.rotate().getHeight() - 546);
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 566, 10, PageSize.TABLOID.rotate().getHeight() - 566);
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 586, 10, PageSize.TABLOID.rotate().getHeight() - 586);
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 606, 10, PageSize.TABLOID.rotate().getHeight() - 606);
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 626, 10, PageSize.TABLOID.rotate().getHeight() - 626);
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 646, 10, PageSize.TABLOID.rotate().getHeight() - 646);
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 666, 10, PageSize.TABLOID.rotate().getHeight() - 666);
      addLine(cb, BaseColor.GRAY, 1215, PageSize.TABLOID.rotate().getHeight() - 686, 10, PageSize.TABLOID.rotate().getHeight() - 686);
      //Lineas Horizontales
      addLine(cb, BaseColor.GRAY, 68, PageSize.TABLOID.rotate().getHeight() - 705, 68, PageSize.TABLOID.rotate().getHeight() - 350);
      addLine(cb, BaseColor.GRAY, 221, PageSize.TABLOID.rotate().getHeight() - 705, 221, PageSize.TABLOID.rotate().getHeight() - 350);
      addLine(cb, BaseColor.GRAY, 271, PageSize.TABLOID.rotate().getHeight() - 705, 271, PageSize.TABLOID.rotate().getHeight() - 350);
      addLine(cb, BaseColor.GRAY, 437, PageSize.TABLOID.rotate().getHeight() - 705, 437, PageSize.TABLOID.rotate().getHeight() - 350);
      addLine(cb, BaseColor.GRAY, 538, PageSize.TABLOID.rotate().getHeight() - 705, 538, PageSize.TABLOID.rotate().getHeight() - 350);
      addLine(cb, BaseColor.GRAY, 637, PageSize.TABLOID.rotate().getHeight() - 705, 637, PageSize.TABLOID.rotate().getHeight() - 350);
      addLine(cb, BaseColor.GRAY, 740, PageSize.TABLOID.rotate().getHeight() - 705, 740, PageSize.TABLOID.rotate().getHeight() - 350);
      addLine(cb, BaseColor.GRAY, 846, PageSize.TABLOID.rotate().getHeight() - 705, 846, PageSize.TABLOID.rotate().getHeight() - 350);
      addLine(cb, BaseColor.GRAY, 900, PageSize.TABLOID.rotate().getHeight() - 705, 900, PageSize.TABLOID.rotate().getHeight() - 350);
      addLine(cb, BaseColor.GRAY, 1002, PageSize.TABLOID.rotate().getHeight() - 705, 1002, PageSize.TABLOID.rotate().getHeight() - 350);
      addLine(cb, BaseColor.GRAY, 1103, PageSize.TABLOID.rotate().getHeight() - 705, 1103, PageSize.TABLOID.rotate().getHeight() - 350);
    }
    
    private void addText(PdfContentByte cb, String text, Font font, float x, float y, int align) {
        Phrase phrase = new Phrase(text, font);
        ColumnText.showTextAligned(cb, align, phrase, x, y, 0);
        cb.saveState();
        cb.stroke();
        cb.restoreState();
    }

    private void addLine(PdfContentByte cb, BaseColor baseColor, float mx, float my, float lx, float ly) {
        cb.setColorStroke(baseColor);
        cb.moveTo(mx, my);
        cb.lineTo(lx, ly);
        cb.stroke();
    }
}

