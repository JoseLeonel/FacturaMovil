package com.emprendesoftcr.Bo.Impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.DetalleBo;
import com.emprendesoftcr.Dao.DetalleDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaUtilidadNative;
import com.emprendesoftcr.web.command.TotalDetallesCommand;
import com.emprendesoftcr.web.command.VentasByCategoriasCommand;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

@EnableTransactionManagement
@Service("detalleBo")
public class DetalleBoImpl implements DetalleBo {

	@Autowired
	private DetalleDao	detalleDao;

	@Autowired
  public DataSource dataSource;
	
  private JdbcTemplate jdbcTemplate;
  
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
		List<String> headers = Arrays.asList("Usuario", "Fecha Emision", "Tipo Documento", "Codigo", "Descripcion", "Clave", "# Documento", "#Proforma", "Cedula", "Cliente", "Nombre a", "Cantidad", "Precio Unitario", "Monto Total", "Descuento", "IVA", "Tarifa 0% Exento", "Tarifa 1%", "Tarifa 2%", "Tarifa 4%", "Transitorio 0%", "Transitorio 4%", "Transitorio 8%", "Tarifa general 13%", "Mercancia Gravada","Mercancia Exenta","Mercancia Exonerada","Servicios Gravados","Servicios Exentos","Servicios Exonerados", "Total", "Tipo Moneda", "Tipo Cambio");
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
		  Double tipoCambio = Constantes.ZEROS_DOUBLE;
			for(Detalle detalle : detalles) {
			  tipoCambio = detalle.getFactura().getTipoCambio();
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
			// Codigo Tarifa 0 %
				cell = row.createCell(16);
				Utils.getCel(cell, styles, getMontoImpuestoTotal(detalle, Constantes.CODIGO_TARIFA_0_PORCIENTO, tipoCambio));

				// Codigo Tarifa 1 %
				cell = row.createCell(17);
				Utils.getCel(cell, styles, getMontoImpuestoTotal(detalle, Constantes.CODIGO_TARIFA_1_PORCIENTO, tipoCambio));

				// Codigo Tarifa 2 %

				cell = row.createCell(18);
				Utils.getCel(cell, styles, getMontoImpuestoTotal(detalle, Constantes.CODIGO_TARIFA_2_PORCIENTO, tipoCambio));

				// Codigo Tarifa 4 %

				cell = row.createCell(19);
				Utils.getCel(cell, styles, getMontoImpuestoTotal(detalle, Constantes.CODIGO_TARIFA_4_PORCIENTO, tipoCambio));

				// Codigo Tarifa Transitorio 0 %
				cell = row.createCell(20);
				Utils.getCel(cell, styles, getMontoImpuestoTotal(detalle, Constantes.CODIGO_TARIFA_0_TRANSITORIO_PORCIENTO, tipoCambio));

				// Codigo Tarifa Transitorio 4 %
				cell = row.createCell(21);
				Utils.getCel(cell, styles, getMontoImpuestoTotal(detalle, Constantes.CODIGO_TARIFA_4_TRANSITORIO_PORCIENTO, tipoCambio));

				// Codigo Tarifa Transitorio 8 %
				cell = row.createCell(22);
				Utils.getCel(cell, styles, getMontoImpuestoTotal(detalle, Constantes.CODIGO_TARIFA_8_TRANSITORIO_PORCIENTO, tipoCambio));
				// Codigo Tarifa General 13 %
				cell = row.createCell(23);
				Utils.getCel(cell, styles, getMontoImpuestoTotal(detalle, Constantes.CODIGO_TARIFA_13_GENERAL_PORCIENTO, tipoCambio));

				 // Total mercancia grabada
				 cell = row.createCell(24);
				 Utils.getCel(cell,styles,detalle.getTotalMercanciaGravada());

			 
				// Total mercancia exenta
				 cell = row.createCell(25);
				 Utils.getCel(cell,styles,detalle.getTotalMercanciaExenta());
			 
  	
  		 // Total mercancia exonerada
			 cell = row.createCell(26);
			 Utils.getCel(cell,styles,detalle.getTotalMercanciaExonerada());

  		 // Total servicios gravados
			 cell = row.createCell(27);
			 Utils.getCel(cell,styles,detalle.getTotalServicioGravados());

  		 // Total servicios exentos
			 cell = row.createCell(28);
			 Utils.getCel(cell,styles,detalle.getTotalServicioExentos());

			 // Total servicios exonerados
			 cell = row.createCell(29);
			 Utils.getCel(cell,styles,detalle.getTotalServicioExonerados());

			 // Total linea
			 cell = row.createCell(30);
			 Utils.getCel(cell,styles,detalle.getMontoTotalLineaNC());
			 
			 // Codigo moneda
			 cell = row.createCell(31);
			 Utils.getCelSTR(cell,styles,detalle.getFactura().getCodigoMoneda());
			 
			 // Tipo cambio 
			 cell = row.createCell(32);
			 Utils.getCel(cell,styles,detalle.getFactura().getTipoCambio());

			 rownum++;
			}
	    int contnum = rownum;
			Row sumRow = sheet.createRow(rownum++);

			for (int j = 0; j <33 ; j++) {
	      Cell cell = sumRow.createCell(j);
	      if(j == 12 ) {
	        cell.setCellValue("Totales  :");
	        cell.setCellStyle(styles.get("formula"));
	        
	      }
	      if(j <= 15 || j == 31 || j == 32  ) {
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
	     
	      if(j == 16 ) {
	        //the 10th cell contains sum over week days, e.g. SUM(C3:I3)
	        String ref = "Q" +4+ ":Q" + contnum;
	        cell.setCellFormula("SUM("+ref+")");
	        cell.setCellStyle(styles.get("formula"));
	      }
	      if(j == 17 ) {
	        //the 10th cell contains sum over week days, e.g. SUM(C3:I3)
	        String ref = "R" +4+ ":R" + contnum;
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
	      if(j == 27 ) {
	        String ref = "AB" +4+ ":AB" + contnum;
	        cell.setCellFormula("SUM("+ref+")");
	        cell.setCellStyle(styles.get("formula"));
	      }
	      if(j == 28 ) {
	        String ref = "AC" +4+ ":AC" + contnum;
	        cell.setCellFormula("SUM("+ref+")");
	        cell.setCellStyle(styles.get("formula"));
	      }
	      if(j == 29 ) {
	        String ref = "AD" +4+ ":AD" + contnum;
	        cell.setCellFormula("SUM("+ref+")");
	        cell.setCellStyle(styles.get("formula"));
	      }
	      if(j == 30 ) {
	        String ref = "AE" +4+ ":AE" + contnum;
	        cell.setCellFormula("SUM("+ref+")");
	        cell.setCellStyle(styles.get("formula"));
	      }

	      sheet.setColumnWidth(j,8000);
			}

	    
			
			workbook.write(stream);
			workbook.close();
			return new ByteArrayInputStream(stream.toByteArray());
	}

	private Double getMonto(String tipoDoc, Double tipoCambio, Double monto) {
		monto = monto == null ? Constantes.ZEROS_DOUBLE : monto;
		return tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) ? monto * tipoCambio * -1 : monto * tipoCambio;
	}

	private Double getMontoImpuestoTotal(Detalle detalle, String tarifa, Double tipoCambio) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		if (detalle.getCodigoTarifa() != null && detalle.getCodigoTarifa().equals(tarifa)) {
			resultado = detalle.getImpuestoNeto() != null ? detalle.getImpuestoNeto() * tipoCambio : Constantes.ZEROS_DOUBLE;
		} 

		return detalle.getFactura().getTipoDoc() !=null && detalle.getFactura().getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) ? resultado * -1 : resultado;

	}
	
	@Override
	public List<Map<String, Object>>  ventasbyCategoria(String fechaInicial ,String fechaFinal,Integer estado,Long idCategoria,Integer idEmpresa){
	  jdbcTemplate = new JdbcTemplate(dataSource);
    String sql = "select  ifnull(cat.id,'9999') as id, ifnull(cat.descripcion,'Sin categoria') as nom_cat ,det.cod_tarifa,det.impuesto,det.tipo_impuesto,fact.estado,  \n" + 
    		"                      sum(ifnull(if(fact.tipo_doc = '03' or fact.tipo_doc = '86',ROUND(det.cantidad,2) * -1,ROUND(det.cantidad,2)),0)) cantidad,  \n" + 
    		"                      sum(ifnull(if(fact.tipo_doc = '03' or fact.tipo_doc = '86',ROUND((det.costo * det.cantidad) * fact.tipo_cambio,2) * -1,ROUND(det.costo * det.cantidad,2)) * tipo_cambio,0)) as total_costo,\n" + 
    		"                      sum(ifnull(if(fact.tipo_doc = '03' or fact.tipo_doc = '86',det.imp_neto *  -1,det.imp_neto ),0) * fact.tipo_cambio) as total_neto,\n" + 
    		"                      sum(ifnull(if(fact.tipo_doc = '03' or fact.tipo_doc = '86',ROUND(det.monto_descuento,2) * - 1 * fact.tipo_cambio,ROUND(det.monto_descuento,2)),0)) total_desc,\n" + 
    		"                      sum(ifnull(if(fact.tipo_doc = '03' or fact.tipo_doc = '86',ROUND(det.monto_total,2) * -1 * fact.tipo_cambio,ROUND(det.monto_total,2)),0)) mont_total,\n" + 
    		"                      sum(ifnull(if(fact.tipo_doc = '03' or fact.tipo_doc = '86',ROUND(det.sub_total,2) * -1 * fact.tipo_cambio,ROUND(det.sub_total,2)),0)) sub_tota,\n" + 
    		"                      sum(ifnull(if(fact.tipo_doc = '03' or fact.tipo_doc = '86',det.monto_total_linea * -1 ,det.monto_total_linea),0) * fact.tipo_cambio) as total_linea,\n" + 
    		"                      sum(ifnull(if(fact.tipo_doc = '03' or fact.tipo_doc = '86',ROUND(det.mont_exone,2) * -1 * fact.tipo_cambio,ROUND(det.mont_exone,2)),0)) mont_exon\n" + 
    		"                    from detalles det \n" + 
    		"                    inner join facturas fact on  fact.id = det.factura_id \n" + 
    		"                    left join articulos art on art.codigo = det.codigo  and fact.empresa_id = art.empresa_id \n" + 
    		"                    inner join categorias  cat on cat.id = art.categoria_id  \n" + 
    		"                    where  fact.estado = :estado  \n" + 
    		"                           and fact.empresa_id = :idempresa\n" + 
    		"                           and cat.id = " + 
    		"                           and fact.fecha_emision >= :fecha_inicial  and fact.fecha_emision <= :fecha_final\n" + 
    		"                    group by cat.id ,cat.descripcion,det.tipo_impuesto,det.cod_tarifa,det.impuesto,fact.estado \n" + 
    		"                    order by cat.descripcion ,det.tipo_impuesto,det.cod_tarifa ";
    
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("fecha_inicial", fechaInicial);
    parameters.addValue("fecha_final",fechaFinal);
    parameters.addValue("idempresa", idEmpresa);
  	if (estado > Constantes.ZEROS) {
  		parameters.addValue("estado",estado);
		} else {
			sql = sql.replaceAll(" fact.estado = :estado", " fact.estado in (2,6,7) ");
		}
  	if (idCategoria > Constantes.ZEROS) {
  		
  		sql = sql.replaceAll("and cat.id = ", "and cat.id = :idcaegoria ");
  		parameters.addValue("idcaegoria",idCategoria);
		} else {
			sql = sql.replaceAll("and cat.id = ", " ");
		}
    
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate( jdbcTemplate );
    List<Map<String, Object>> listaObjetos = namedParameterJdbcTemplate.queryForList(sql, parameters);  
		
		return listaObjetos;
	}

	@Override
	public ByteArrayInputStream ventasbyCategoriaExcel(String fechaInicial, String fechaFinal, Integer estado, Long idCategoria, Empresa empresa) throws IOException {

		List<Map<String, Object>> lista  = ventasbyCategoria( fechaInicial, fechaFinal, estado,idCategoria, empresa.getId());
		@SuppressWarnings("rawtypes")
		ArrayList arrayList = new ArrayList();
    arrayList = (ArrayList) lista;
    JsonArray jsonArray1 = new Gson().toJsonTree(arrayList).getAsJsonArray();
		ArrayList<VentasByCategoriasCommand> listaDatos = new ArrayList<>();
		Gson gson = new Gson();
		if (jsonArray1 != null) {
			for (int i = 0; i < jsonArray1.size(); i++) {
				VentasByCategoriasCommand ventasByCategoriasCommand = gson.fromJson(jsonArray1.get(i).toString(), VentasByCategoriasCommand.class);
				listaDatos.add(ventasByCategoriasCommand);
			}
		}
		List<String> headers = Arrays.asList("Estado", "Categoria", "Tipo Impuesto", "Tarifa", "Impuesto", "Costo", "Descuento", "Total IVA", "Total", "Total Exonerado");
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		 Map<String, CellStyle> styles = Utils.createStyles(workbook);
		Sheet sheet  = workbook.createSheet("Venta por Categoria");
		
		
	//title row
		 Row title = sheet.createRow(0);
		 title.setHeightInPoints(25);
	    Cell titleCell = title.createCell(0);
	    titleCell.setCellValue("Ventas por categoria del "+fechaInicial.replace("00:00:00", "")+" al "+ fechaFinal.replace("00:00:00", ""));
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
	
		for(VentasByCategoriasCommand ventasByCategoriasCommand : listaDatos) {
			row = sheet.createRow(rownum);
			// Usuario
			Cell cell = row.createCell(0);
			Utils.getCelSTR(cell,styles,ventasByCategoriasCommand.getEstadoSTR());
			// Fecha Emision
		 cell = row.createCell(1);
		 Utils.getCelSTR(cell,styles,ventasByCategoriasCommand.getDescrpcion());
		 // Tipo de Documento
		 cell = row.createCell(2);
		 Utils.getCelSTR(cell,styles,ventasByCategoriasCommand.getTipoImpuestoSTR());
		 // Codigo
		 cell = row.createCell(3);
		 Utils.getCelSTR(cell,styles,ventasByCategoriasCommand.getCodigoTarifaSTR());
		 // Descripcion
		 cell = row.createCell(4);
		 Utils.getCel(cell,styles,ventasByCategoriasCommand.getImpuesto());
		 // clave
		 cell = row.createCell(5);
		 Utils.getCel(cell,styles,ventasByCategoriasCommand.getTotal_costo());
		 // Documento
		 cell = row.createCell(6);
		 Utils.getCel(cell,styles,ventasByCategoriasCommand.getTotal_desc());
		 // Proforma
		 cell = row.createCell(7);
		 Utils.getCel(cell,styles,ventasByCategoriasCommand.getTotal_neto());
		 // Cedula
		 cell = row.createCell(8);
		 Utils.getCel(cell,styles,ventasByCategoriasCommand.getTotal_linea());
		 // Cliente
		 cell = row.createCell(9);
		 Utils.getCel(cell,styles,ventasByCategoriasCommand.getMont_exon());
			
		 

		 rownum++;
		}
    int contnum = rownum;
		Row sumRow = sheet.createRow(rownum++);

		for (int j = 0; j < 10; j++) {
      Cell cell = sumRow.createCell(j);
      if(j == 4 ) {
        cell.setCellValue("Totales  :");
        cell.setCellStyle(styles.get("formula"));
        
      }
      if(j <= 3 ) {
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
      if(j == 8 ) {
        //the 10th cell contains sum over week days, e.g. SUM(C3:I3)
        String ref = "I" +4+ ":I" + contnum;
        cell.setCellFormula("SUM("+ref+")");
        cell.setCellStyle(styles.get("formula"));
      }
      if(j == 9 ) {
        //the 10th cell contains sum over week days, e.g. SUM(C3:I3)
        String ref = "J" +4+ ":J" + contnum;
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
