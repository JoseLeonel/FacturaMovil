package com.emprendesoftcr.Bo.Impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.DetalleBo;
import com.emprendesoftcr.Dao.DetalleDao;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaUtilidadNative;
import com.emprendesoftcr.web.command.TotalDetallesCommand;

@EnableTransactionManagement
@Service("detalleBo")
public class DetalleBoImpl implements DetalleBo {

	@Autowired
	private DetalleDao	detalleDao;

	private Logger			log	= LoggerFactory.getLogger(this.getClass());

	@Transactional
	@Override
	public void agregar(Detalle detalle) {
		detalleDao.agregar(detalle);

	}

	@Transactional
	@Override
	public void modificar(Detalle detalle) {
		detalleDao.modificar(detalle);
	}

	@Transactional
	@Override
	public void eliminar(Detalle detalle) {
		detalleDao.eliminar(detalle);

	}

	@Transactional
	@Override
	public Integer eliminarDetalleFactura(Factura factura) throws Exception {
		try {
			return detalleDao.eliminarDetalleFactura(factura);
		} catch (Exception e) {
			log.info("** Error  eliminarDetalleFactura: " + e.getMessage() + " fecha " + new Date());

			throw e;
		}

	}

	@Override
	public Collection<Detalle> facturasRangoEstado(Integer estado, Date fechaInicio, Date fechaFin, Empresa empresa) {
		return detalleDao.facturasRangoEstado(estado, fechaInicio, fechaFin, empresa);
	}

	@Override
	public Collection<Detalle> facturasRango(Integer estado, Date fechaInicio, Date fechaFin, Empresa empresa, String tipoImpuesto, String actividadEconomica) {
		return detalleDao.facturasRango(estado, fechaInicio, fechaFin, empresa, tipoImpuesto, actividadEconomica);
	}

	@Override
	public Collection<Detalle> findByFactura(Factura factura) {
		return detalleDao.findByFactura(factura);
	}

	@Override
	public TotalDetallesCommand totalVentasPorDetalle(Empresa empresa, Date fechaInicio, Date FechaFinal, String tipoImpuesto, Integer estado, String actividadEconomica) {
		return detalleDao.totalVentasPorDetalle(empresa, fechaInicio, FechaFinal, tipoImpuesto, estado, actividadEconomica);
	}

	@Override
	public Detalle findByCodigoAndEmpresa(String codigo, Empresa empresa) {
		return detalleDao.findByCodigoAndEmpresa(codigo, empresa);
	}

	@Override
	public Detalle findById(Long idDetalle) {

		return detalleDao.findById(idDetalle);
	}

	@Override
	public Collection<Detalle> findbyIdFactura(Long idFactura) {

		return detalleDao.findbyIdFactura(idFactura);
	}

	@Override
	public Collection<Detalle> findbyConsecutivoAndEmpresa(String consecutivo, Empresa empresa) {
	
		return detalleDao.findbyConsecutivoAndEmpresa(consecutivo, empresa);
	}

	@Override
	public ByteArrayInputStream createExcelUtilidad(Collection<ConsultaUtilidadNative> facturas,Empresa empresa,String fechaInicio,String fechaFin) throws Exception {
		List<String> headers = Arrays.asList("Fecha Emision", "Factura","Categoria", "Codigo", "Descripcion", "Costo", "Venta", "Utilidad");
		//Libro excel
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		 Map<String, CellStyle> styles = Utils.createStyles(workbook);
		Sheet sheet  = workbook.createSheet("Utilidad");
		
		
	//title row
		 Row title = sheet.createRow(0);
		 title.setHeightInPoints(25);
	    Cell titleCell = title.createCell(0);
	    titleCell.setCellValue("Utilidad de Ventas del "+fechaInicio+" al "+ fechaFin);
	    titleCell.setCellStyle(styles.get("title"));
	    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$H$1"));
		
    Row titleEmpresa = sheet.createRow(1);
    titleEmpresa.setHeightInPoints(25);
    Cell titleCell1 = titleEmpresa.createCell(0);
    titleCell1.setCellValue(empresa.getNombre());
    titleCell1.setCellStyle(styles.get("title"));
    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$H$2"));
    

    Row titleCedula = sheet.createRow(2);
    titleCedula.setHeightInPoints(25);
    Cell titleCell2 = titleCedula.createCell(0);
    titleCell2.setCellValue("Cedula:"+ empresa.getCedula());
    titleCell2.setCellStyle(styles.get("title"));
    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$H$3"));
    
    
	  Row row = sheet.createRow(3);
    row.setHeightInPoints(25);
    Cell headerCell;
		for (int i = 0; i < headers.size(); i++) {
			 headerCell = row.createCell(i);
       headerCell.setCellValue(headers.get(i));
       headerCell.setCellStyle(styles.get("header"));
       
		}
		//style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		// Freeze just one row
		//sheet.createFreezePane( 0, 2, 0, 2 );

		
		
	
		int rownum = 4 ;
	
		for(ConsultaUtilidadNative consultaUtilidadNative : facturas) {
			row = sheet.createRow(rownum);
			Cell cell = row.createCell(0);
			cell.setCellStyle(styles.get("cell"));
			cell.setCellValue(consultaUtilidadNative.getFechaEmision());
	//		row.createCell(0).setCellStyle(styles.get("cell"));
			 cell = row.createCell(1);
			 Utils.getCelSTR(cell,styles,consultaUtilidadNative.getNumeroConsecutivo());
			cell = row.createCell(2);
			Utils.getCelSTR(cell,styles,consultaUtilidadNative.getNombreCategoria());
			cell = row.createCell(3);
			Utils.getCelSTR(cell,styles,consultaUtilidadNative.getCodigo());
			
			cell = row.createCell(4);
			Utils.getCelSTR(cell,styles,consultaUtilidadNative.getNombreArticulo());
			
			
			cell = row.createCell(5);
			Utils.getCel(cell,styles,consultaUtilidadNative.getCosto());
		
			cell = row.createCell(6);
			Utils.getCel(cell,styles,consultaUtilidadNative.getVenta());
			
			cell = row.createCell(7);
			Utils.getCel(cell,styles,consultaUtilidadNative.getTotalUtilidad());  
      
			rownum++;
		}
    int contnum = rownum;
		Row sumRow = sheet.createRow(rownum++);

		for (int j = 0; j <=7 ; j++) {
      Cell cell = sumRow.createCell(j);
      if(j == 4 ) {
        cell.setCellValue("Totales  :");
        cell.setCellStyle(styles.get("formula"));
        
      }
      if(j == 0 || j == 1 || j ==  2 || j ==  3 ) {
      	cell.setCellStyle(styles.get("formula"));
      }
      if(j == 5 ) {
        //the 10th cell contains sum over week days, e.g. SUM(C3:I3)
        String ref = "F" +4+ ":F" + contnum;
        cell.setCellFormula("SUM("+ref+")");
        cell.setCellStyle(styles.get("formula"));
      }
      if(j == 6 ) {
        //the 10th cell contains sum over week days, e.g. SUM(C3:I3)
        String ref = "G" +4+ ":G" + contnum;
        cell.setCellFormula("SUM("+ref+")");
        cell.setCellStyle(styles.get("formula"));
      }
      if(j == 7 ) {
        //the 10th cell contains sum over week days, e.g. SUM(C3:I3)
        String ref = "H" +4+ ":H" + contnum;
        cell.setCellFormula("SUM("+ref+")");
        cell.setCellStyle(styles.get("formula"));
      }

      sheet.setColumnWidth(j,8000);
		}

    
		//sheet.setColumnWidth(0, 50);
		//sheet.autoSizeColumn(1);
		workbook.write(stream);
		workbook.close();
		return new ByteArrayInputStream(stream.toByteArray());
	}

	@Override
	public ByteArrayInputStream createExcelVentasXCodigo(Collection<Detalle> detalles,String fechaInicio, String fechaFinal, Empresa empresa, String actividadEconomica) throws Exception {
		List<String> headers = Arrays.asList("Usuario", "Fecha Emision", "Tipo Documento", "Codigo", "Descripcion", "Clave", "# Documento", "#Proforma", "Cedula", "Cliente", "Nombre a", "Cantidad", "Precio Unitario", "Monto Total", "Descuento", "IVA", "Tarifa", "%IVA", "Total IVA","Total IVA Neto","Mercancia Gravada","Mercancia Exenta","Mercancia Exonerada","Servicios Gravados","Servicios Exentos","Servicios Exonerados", "Total", "Tipo Moneda", "Tipo Cambio");
			Workbook workbook = new HSSFWorkbook();
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			 Map<String, CellStyle> styles = Utils.createStyles(workbook);
			Sheet sheet  = workbook.createSheet("Venta por detalle de codigo");
			
			
		//title row
			 Row title = sheet.createRow(0);
			 title.setHeightInPoints(25);
		    Cell titleCell = title.createCell(0);
		    titleCell.setCellValue("Ventas por articulo del "+fechaInicio+" al "+ fechaFinal);
		    titleCell.setCellStyle(styles.get("title1"));
		    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AC$1"));
			
	    Row titleEmpresa = sheet.createRow(1);
	    titleEmpresa.setHeightInPoints(25);
	    Cell titleCell1 = titleEmpresa.createCell(0);
	    titleCell1.setCellValue(empresa.getNombre());
	    titleCell1.setCellStyle(styles.get("title1"));
	    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$AC$2"));
	    

	    Row titleCedula = sheet.createRow(2);
	    titleCedula.setHeightInPoints(25);
	    Cell titleCell2 = titleCedula.createCell(0);
	    titleCell2.setCellValue("Cedula:"+ empresa.getCedula());
	    titleCell2.setCellStyle(styles.get("title1"));
	    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$AC$3"));
	    
	    
		  Row row = sheet.createRow(3);
	    row.setHeightInPoints(25);
	    Cell headerCell;
			for (int i = 0; i < headers.size(); i++) {
				 headerCell = row.createCell(i);
	       headerCell.setCellValue(headers.get(i));
	       headerCell.setCellStyle(styles.get("header"));
	       
			}
			
		//
			int rownum = 4 ;
		
			for(Detalle detalle : detalles) {
				row = sheet.createRow(rownum);
				// Usuario
				Cell cell = row.createCell(0);
				Utils.getCelSTR(cell,styles,detalle.getFactura().getUsuarioCreacion().getNombreUsuario());
				// Fecha Emision
			 cell = row.createCell(1);
			 Utils.getCelSTR(cell,styles,detalle.getFactura().getFechaEmisionSTR());
  		 // Tipo de Documento
			 cell = row.createCell(2);
			 Utils.getCelSTR(cell,styles,detalle.getFactura().getTipoDocSTR());
  		 // Codigo
			 cell = row.createCell(3);
			 Utils.getCelSTR(cell,styles,detalle.getCodigo());
  		 // Descripcion
			 cell = row.createCell(4);
			 Utils.getCelSTR(cell,styles,detalle.getDescripcion());
  		 // clave
			 cell = row.createCell(5);
			 Utils.getCelSTR(cell,styles,detalle.getFactura().getClave());
  		 // Documento
			 cell = row.createCell(6);
			 Utils.getCelSTR(cell,styles,detalle.getFactura().getNumeroConsecutivo());
  		 // Proforma
			 cell = row.createCell(7);
			 Utils.getCelSTR(cell,styles,detalle.getFactura().getConsecutivoProforma());
  		 // Cedula
			 cell = row.createCell(8);
			 Utils.getCelSTR(cell,styles,detalle.getFactura().getCedulaCliente());
  		 // Cliente
			 cell = row.createCell(9);
			 Utils.getCelSTR(cell,styles,detalle.getFactura().getNombreCliente());
				
  		 // Nombre de la Factura
			 cell = row.createCell(10);
			 Utils.getCelSTR(cell,styles,detalle.getFactura().getNombreFactura());
  		 // Cantidad
			 cell = row.createCell(11);
			 Utils.getCelSTR(cell,styles,detalle.getCantidad().toString());
  		 // Precio Unitario
			 cell = row.createCell(12);
			 Utils.getCel(cell,styles,detalle.getPrecioUnitario());

  		 // Monto Total
			 cell = row.createCell(13);
			 Utils.getCel(cell,styles,detalle.getMontoTotalNC());
			 
  		 // Monto Descuentos
			 cell = row.createCell(14);
			 Utils.getCel(cell,styles,detalle.getMontoDescuentoNC());

  		 // IVA
			 cell = row.createCell(15);
			 Utils.getCelSTR(cell,styles,detalle.getTipoImpuestoSTR());

  		 // Tarifa
			 cell = row.createCell(16);
			 Utils.getCelSTR(cell,styles,detalle.getCodigoTarifaSTR());
  		 // impuesto
			 cell = row.createCell(17);
			 Utils.getCel(cell,styles,detalle.getImpuesto());
  		 // Monto del Impuesto
			 cell = row.createCell(18);
			 Utils.getCel(cell,styles,detalle.getMontoImpuestoNC());
  		 // Monto del Impuesto  Neto
			 cell = row.createCell(19);
			 Utils.getCel(cell,styles,detalle.getImpuestoNeto());
  		 // Total mercancia grabada
			 cell = row.createCell(20);
			 Utils.getCel(cell,styles,detalle.getTotalMercanciaGravada());

  		 // Total mercancia exenta
			 cell = row.createCell(21);
			 Utils.getCel(cell,styles,detalle.getTotalMercanciaExenta());
  		 // Total mercancia exonerada
			 cell = row.createCell(22);
			 Utils.getCel(cell,styles,detalle.getTotalMercanciaExonerada());

  		 // Total servicios gravados
			 cell = row.createCell(23);
			 Utils.getCel(cell,styles,detalle.getTotalServicioGravados());

  		 // Total servicios exentos
			 cell = row.createCell(24);
			 Utils.getCel(cell,styles,detalle.getTotalServicioExentos());

			 // Total servicios exonerados
			 cell = row.createCell(25);
			 Utils.getCel(cell,styles,detalle.getTotalServicioExonerados());

			 // Total linea
			 cell = row.createCell(26);
			 Utils.getCel(cell,styles,detalle.getMontoTotalLineaNC());
			 
			 // Codigo moneda
			 cell = row.createCell(27);
			 Utils.getCelSTR(cell,styles,detalle.getFactura().getCodigoMoneda());
			 
			 // Tipo cambio 
			 cell = row.createCell(28);
			 Utils.getCel(cell,styles,detalle.getFactura().getTipoCambio());

			 rownum++;
			}
	    int contnum = rownum;
			Row sumRow = sheet.createRow(rownum++);

			for (int j = 0; j <29 ; j++) {
	      Cell cell = sumRow.createCell(j);
	      if(j == 12 ) {
	        cell.setCellValue("Totales  :");
	        cell.setCellStyle(styles.get("formula"));
	        
	      }
	      if(j <= 12 || j == 27 || j == 28 || j == 15 || j == 16 || j == 17 ) {
	      	cell.setCellStyle(styles.get("formula"));
	      }
	      if(j == 13 ) {
	        //the 10th cell contains sum over week days, e.g. SUM(C3:I3)
	        String ref = "N" +4+ ":N" + contnum;
	        cell.setCellFormula("SUM("+ref+")");
	        cell.setCellStyle(styles.get("formula"));
	      }
	      if(j == 14 ) {
	        //the 10th cell contains sum over week days, e.g. SUM(C3:I3)
	        String ref = "O" +4+ ":O" + contnum;
	        cell.setCellFormula("SUM("+ref+")");
	        cell.setCellStyle(styles.get("formula"));
	      }
	     
	      
	      
	      if(j == 18 ) {
	        //the 10th cell contains sum over week days, e.g. SUM(C3:I3)
	        String ref = "S" +4+ ":S" + contnum;
	        cell.setCellFormula("SUM("+ref+")");
	        cell.setCellStyle(styles.get("formula"));
	      }
	      if(j == 19 ) {
	        //the 10th cell contains sum over week days, e.g. SUM(C3:I3)
	        String ref = "T" +4+ ":T" + contnum;
	        cell.setCellFormula("SUM("+ref+")");
	        cell.setCellStyle(styles.get("formula"));
	      }
	      if(j == 20 ) {
	        //the 10th cell contains sum over week days, e.g. SUM(C3:I3)
	        String ref = "U" +4+ ":U" + contnum;
	        cell.setCellFormula("SUM("+ref+")");
	        cell.setCellStyle(styles.get("formula"));
	      }
	      if(j == 21 ) {
	        //the 10th cell contains sum over week days, e.g. SUM(C3:I3)
	        String ref = "V" +4+ ":V" + contnum;
	        cell.setCellFormula("SUM("+ref+")");
	        cell.setCellStyle(styles.get("formula"));
	      }
	      if(j == 22 ) {
	        //the 10th cell contains sum over week days, e.g. SUM(C3:I3)
	        String ref = "W" +4+ ":W" + contnum;
	        cell.setCellFormula("SUM("+ref+")");
	        cell.setCellStyle(styles.get("formula"));
	      }
	      if(j == 23 ) {
	        String ref = "X" +4+ ":X" + contnum;
	        cell.setCellFormula("SUM("+ref+")");
	        cell.setCellStyle(styles.get("formula"));
	      }
	      if(j == 24 ) {
	        String ref = "Y" +4+ ":Y" + contnum;
	        cell.setCellFormula("SUM("+ref+")");
	        cell.setCellStyle(styles.get("formula"));
	      }
	      if(j == 25 ) {
	        String ref = "Z" +4+ ":Z" + contnum;
	        cell.setCellFormula("SUM("+ref+")");
	        cell.setCellStyle(styles.get("formula"));
	      }
	      if(j == 26 ) {
	        String ref = "AA" +4+ ":AA" + contnum;
	        cell.setCellFormula("SUM("+ref+")");
	        cell.setCellStyle(styles.get("formula"));
	      }

	      sheet.setColumnWidth(j,8000);
			}

	    
			
			workbook.write(stream);
			workbook.close();
			return new ByteArrayInputStream(stream.toByteArray());
	}

	
	 

}
