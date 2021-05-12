package com.emprendesoftcr.Bo.Impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.DetalleCompraBo;
import com.emprendesoftcr.Dao.DetalleCompraDao;
import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.ConteoManualCaja;
import com.emprendesoftcr.modelo.DetalleCompra;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RecepcionFacturaDetalle;
import com.emprendesoftcr.modelo.sqlNativo.CompraIVA;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.CompraVentasResumenCommand;

@EnableTransactionManagement
@Service("detalleCompraBo")
public class DetalleCompraBoImpl implements DetalleCompraBo {

	@Autowired
	private DetalleCompraDao detalleCompraDao;
	

	@Autowired
  public DataSource dataSource;
	
	@Autowired
  private JdbcTemplate jdbcTemplate;

	@Transactional
	@Override
	public void agregar(DetalleCompra detalleCompra) {
		detalleCompraDao.agregar(detalleCompra);

	}

	@Transactional
	@Override
	public void modificar(DetalleCompra detalleCompra) {
		detalleCompraDao.modificar(detalleCompra);
	}

	@Transactional
	@Override
	public void eliminar(DetalleCompra detalleCompra) {
		detalleCompraDao.eliminar(detalleCompra);

	}

	@Override
	public Collection<DetalleCompra> findByCompra(Compra compra) {
		return detalleCompraDao.findByCompra(compra);
	}
	
	/**
	 * Formulario de contadores
	 */
	@Override
	public ByteArrayInputStream createExcelResumen(	Collection<CompraIVA> lista, String fechaInicio, String fechaFin, Empresa empresa) throws Exception {
		
		Workbook workbook = WorkbookFactory.create(new File("")); 
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Sheet sheet = workbook.getSheetAt(0);
		CompraVentasResumenCommand compraVentasResumenCommand = new CompraVentasResumenCommand();
		DoubleSummaryStatistics doubleSummaryStatistics = lista.stream().collect(Collectors.summarizingDouble(CompraIVA::getMontoIva0));
		compraVentasResumenCommand.setTreceVentaPorciento(doubleSummaryStatistics.getSum());
		doubleSummaryStatistics = lista.stream().collect(Collectors.summarizingDouble(CompraIVA::getMontoIva0));
		
		
				
		workbook.write(stream);
		workbook.close();
		return new ByteArrayInputStream(stream.toByteArray());
	}


	@Override
	public ByteArrayInputStream createExcelDetalleCompra(Collection<RecepcionFacturaDetalle> lista, String fechaInicio, String fechaFin, Empresa empresa) throws Exception {
		List<String> headers = Arrays.asList("Actividad Economica", "Estado Hacienda", "Aceptacion Receptor", "Fecha Ingreso", "Fecha Emision", "Clave", "# Documento Receptor", "Cedula Emisor", "Nombre Emisor", "Nombre Comercial", "Correo", "Telefono", "# Compra", "Tipo Moneda", "Tipo Cambio", "Tipo Documento", "Total Exento 0%", "Total Tarifa reducida 1%", "Total Tarifa reducida 2%", "Total Tarifa reducida 4%", "Total Transitorio 0%", "Total Transitorio 4%", "Total Transitorio 8%", "Total Tarifa General 13%","Otros impuestos", "Total Descuentos", "Total X Tipo cambio", "Tipo Moneda", "Tipo cambio");
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Map<String, CellStyle> styles = Utils.createStyles(workbook);
		Sheet sheet = workbook.createSheet("Compras del detalle");
		// title row
		Row title = sheet.createRow(0);
		title.setHeightInPoints(25);
		Cell titleCell = title.createCell(0);
		titleCell.setCellValue("Compras por articulo del " + fechaInicio + " al " + fechaFin);
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
		titleCell2.setCellValue("Cedula:" + empresa.getCedula());
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
		int rownum = 4;
		for (RecepcionFacturaDetalle recepcionFacturaDetalle : lista) {
			row = sheet.createRow(rownum);
			Double tipoCambio = recepcionFacturaDetalle.getRecepcionFactura().getFacturaTipoCambio() == null ? 1 : recepcionFacturaDetalle.getRecepcionFactura().getFacturaTipoCambio();
			// Codigo Actividad
			Cell cell = row.createCell(0);
			Utils.getCelSTR(cell, styles, recepcionFacturaDetalle.getRecepcionFactura().getCodigoActividad());
			// Estado Hacienda
			cell = row.createCell(1);
			Utils.getCelSTR(cell, styles, recepcionFacturaDetalle.getRecepcionFactura().getEstadoSTR());
			// Estado Hacienda
			cell = row.createCell(2);
			Utils.getCelSTR(cell, styles, recepcionFacturaDetalle.getRecepcionFactura().getMensajeSTR());

			// Fecha ingreso
			cell = row.createCell(3);
			Utils.getCelSTR(cell, styles, recepcionFacturaDetalle.getRecepcionFactura().getCreated_atSTR());

			// Fecha Emision
			cell = row.createCell(4);
			Utils.getCelSTR(cell, styles, recepcionFacturaDetalle.getRecepcionFactura().getFechaEmisionSTR());

			// Clave
			cell = row.createCell(5);
			Utils.getCelSTR(cell, styles, recepcionFacturaDetalle.getRecepcionFactura().getFacturaClave());

			// Consecutivo del receptor
			cell = row.createCell(6);
			Utils.getCelSTR(cell, styles, recepcionFacturaDetalle.getRecepcionFactura().getNumeroConsecutivoReceptor());

			// Cedula del receptor
			cell = row.createCell(7);
			Utils.getCelSTR(cell, styles, recepcionFacturaDetalle.getRecepcionFactura().getEmisorCedula());

			// nombre del receptor
			cell = row.createCell(8);
			Utils.getCelSTR(cell, styles, recepcionFacturaDetalle.getRecepcionFactura().getEmisorNombre());

			// nombre Comercial
			cell = row.createCell(9);
			Utils.getCelSTR(cell, styles, recepcionFacturaDetalle.getRecepcionFactura().getEmisorNombreComercial());

			// Correo Electronico
			cell = row.createCell(10);
			Utils.getCelSTR(cell, styles, recepcionFacturaDetalle.getRecepcionFactura().getEmisorCorreo());
			// Telefono
			cell = row.createCell(11);
			Utils.getCelSTR(cell, styles, recepcionFacturaDetalle.getRecepcionFactura().getEmisorTelefono());

			// #Compra
			cell = row.createCell(12);
			Utils.getCelSTR(cell, styles, recepcionFacturaDetalle.getRecepcionFactura().getFacturaConsecutivo());

			// Tipo Moneda
			cell = row.createCell(13);
			Utils.getCelSTR(cell, styles, recepcionFacturaDetalle.getRecepcionFactura().getFacturaCodigoMoneda());

			// Tipo Cambio
			cell = row.createCell(14);
			Utils.getCel(cell, styles, recepcionFacturaDetalle.getRecepcionFactura().getFacturaTipoCambio());

			// Tipo Documento
			cell = row.createCell(15);
			Utils.getCelSTR(cell, styles, recepcionFacturaDetalle.getRecepcionFactura().getTipoDocumentoStr());

			// Codigo Tarifa 0 %
			cell = row.createCell(16);
			Utils.getCel(cell, styles, getMontoImpuestoTotal(recepcionFacturaDetalle, Constantes.CODIGO_TARIFA_0_PORCIENTO, tipoCambio));

			// Codigo Tarifa 1 %
			cell = row.createCell(17);
			Utils.getCel(cell, styles, getMontoImpuestoTotal(recepcionFacturaDetalle, Constantes.CODIGO_TARIFA_1_PORCIENTO, tipoCambio));

			// Codigo Tarifa 2 %

			cell = row.createCell(18);
			Utils.getCel(cell, styles, getMontoImpuestoTotal(recepcionFacturaDetalle, Constantes.CODIGO_TARIFA_2_PORCIENTO, tipoCambio));

			// Codigo Tarifa 4 %

			cell = row.createCell(19);
			Utils.getCel(cell, styles, getMontoImpuestoTotal(recepcionFacturaDetalle, Constantes.CODIGO_TARIFA_4_PORCIENTO, tipoCambio));

			// Codigo Tarifa Transitorio 0 %
			cell = row.createCell(20);
			Utils.getCel(cell, styles, getMontoImpuestoTotal(recepcionFacturaDetalle, Constantes.CODIGO_TARIFA_0_TRANSITORIO_PORCIENTO, tipoCambio));

			// Codigo Tarifa Transitorio 4 %
			cell = row.createCell(21);
			Utils.getCel(cell, styles, getMontoImpuestoTotal(recepcionFacturaDetalle, Constantes.CODIGO_TARIFA_4_TRANSITORIO_PORCIENTO, tipoCambio));

			// Codigo Tarifa Transitorio 8 %
			cell = row.createCell(22);
			Utils.getCel(cell, styles, getMontoImpuestoTotal(recepcionFacturaDetalle, Constantes.CODIGO_TARIFA_8_TRANSITORIO_PORCIENTO, tipoCambio));
			// Codigo Tarifa General 13 %
			cell = row.createCell(23);
			Utils.getCel(cell, styles, getMontoImpuestoTotal(recepcionFacturaDetalle, Constantes.CODIGO_TARIFA_13_GENERAL_PORCIENTO, tipoCambio));

			// Otros impuestos
			cell = row.createCell(24);
			Utils.getCel(cell, styles, getMontoImpuestoTotalOtros(recepcionFacturaDetalle,  tipoCambio));

			// Codigo Descuento
			cell = row.createCell(25);
			Utils.getCel(cell, styles, getMonto(recepcionFacturaDetalle.getRecepcionFactura().getTipoDoc(), tipoCambio, recepcionFacturaDetalle.getDescuentoMonto()));

			// Monto Linea * tipo cambio
			cell = row.createCell(26);
			Utils.getCel(cell, styles, getMonto(recepcionFacturaDetalle.getRecepcionFactura().getTipoDoc(), tipoCambio, recepcionFacturaDetalle.getMontoTotalLinea()));
			// Monto Linea * tipo cambio
			cell = row.createCell(27);
			Utils.getCelSTR(cell, styles, recepcionFacturaDetalle.getRecepcionFactura().getFacturaCodigoMoneda());
			cell = row.createCell(28);
			Utils.getCel(cell, styles, recepcionFacturaDetalle.getRecepcionFactura().getFacturaTipoCambio());

			rownum++;
		}
		int contnum = rownum;
		Row sumRow = sheet.createRow(rownum++);

		for (int j = 0; j < 28; j++) {
			Cell cell = sumRow.createCell(j);
			
			if (j <= 15 || j == 27 || j == 28 ) {
				cell.setCellStyle(styles.get("formula"));
			}

			if (j == 16) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "Q" + 4 + ":Q" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 17) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "R" + 4 + ":R" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 18) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "S" + 4 + ":S" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 19) {
				String ref = "T" + 4 + ":T" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}

			if (j == 20) {
				String ref = "U" + 4 + ":U" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 21) {
				String ref = "V" + 4 + ":V" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 22) {
				String ref = "W" + 4 + ":W" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 23) {
				String ref = "X" + 4 + ":X" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 24) {
				String ref = "Y" + 4 + ":Y" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 25) {
				String ref = "Z" + 4 + ":Z" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 26) {
				String ref = "AA" + 4 + ":AA" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}

			sheet.setColumnWidth(j, 8500);
		}

		workbook.write(stream);
		workbook.close();
		return new ByteArrayInputStream(stream.toByteArray());
	}
	@Override
	public ByteArrayInputStream createExcelDetalleCompraResumen(Collection<CompraIVA> lista, String fechaInicio, String fechaFin, Empresa empresa) throws Exception {
		List<String> headers = Arrays.asList("Fecha Emision","Clave", "# Documento ", "Cedula Emisor", "Nombre Emisor", "Nombre Comercial","Correo Electronico", "Tipo Moneda", "Tipo Cambio", "Tipo Documento", "Total Exento 0%", "Total Tarifa reducida 1%", "Total Tarifa reducida 2%", "Total Tarifa reducida 4%", "Total Transitorio 0%", "Total Transitorio 4%", "Total Transitorio 8%", "Total Tarifa General 13%","Total Otros impuestos(Tipo impuesto=99)", "Total Descuentos", "Total Impuesto","Total Comprobante","Otros Cargos","Total Gravada","Total Exentas","Tipo Gasto");
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Map<String, CellStyle> styles = Utils.createStyles(workbook);
		Sheet sheet = workbook.createSheet("Compras totalizadas");
		// title row
		Row title = sheet.createRow(0);
		title.setHeightInPoints(25);
		Cell titleCell = title.createCell(0);
		titleCell.setCellValue("Compras por articulo del " + fechaInicio + " al " + fechaFin);
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
		titleCell2.setCellValue("Cedula:" + empresa.getCedula());
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
		int rownum = 4;
		for (CompraIVA compraIVA : lista) {
			row = sheet.createRow(rownum);
				
			// Fecha Emision
			Cell cell = row.createCell(0);
			Utils.getCelSTR(cell, styles, compraIVA.getFechaEmisionSTR());
//
			// Clave
			cell = row.createCell(1);
			Utils.getCelSTR(cell, styles, compraIVA.getFacturaClave());
			
//		// #consecutivo
		cell = row.createCell(2);
		Utils.getCelSTR(cell, styles, compraIVA.getNumeroConsecutivo());

//
//
			// Cedula del Emisor
			cell = row.createCell(3);
			Utils.getCelSTR(cell, styles, compraIVA.getEmisorCedula());

			// nombre 
			cell = row.createCell(4);
			Utils.getCelSTR(cell, styles, compraIVA.getEmisorNombre());
//
//			// nombre Comercial
			cell = row.createCell(5);
			Utils.getCelSTR(cell, styles,Constantes.EMPTY);

//		// Correo Electronico
		cell = row.createCell(6);
		Utils.getCelSTR(cell, styles, compraIVA.getEmisorCorreo());

			// tipo Moneda
			cell = row.createCell(7);
			Utils.getCelSTR(cell, styles, compraIVA.getFacturaCodigoMoneda());

			
			// tipo de cambio
		cell = row.createCell(8);
		Utils.getCel(cell, styles, compraIVA.getFacturaTipoCambio());

	// tipo de documento
			cell = row.createCell(9);
			Utils.getCelSTR(cell, styles, compraIVA.getTipoDocumentoStr());
			
			// 0% impuesto valor agregado
			cell = row.createCell(10);
			Utils.getCel(cell, styles, getMonto(compraIVA.getEmisorTipoDocumento(),  compraIVA.getMontoIva0(),compraIVA.getFacturaTipoCambio()) );

			// 1% impuesto valor agregado
			cell = row.createCell(11);
			Utils.getCel(cell, styles, getMonto(compraIVA.getEmisorTipoDocumento(),  compraIVA.getMontoIva1(),compraIVA.getFacturaTipoCambio()));
			// 2% impuesto valor agregado
			cell = row.createCell(12);
			Utils.getCel(cell, styles, getMonto(compraIVA.getEmisorTipoDocumento(),  compraIVA.getMontoIva2(),compraIVA.getFacturaTipoCambio()));
			
		// 4% impuesto valor agregado
					cell = row.createCell(13);
					Utils.getCel(cell, styles, getMonto(compraIVA.getEmisorTipoDocumento(),  compraIVA.getMontoIva4(),compraIVA.getFacturaTipoCambio()));
			
				// 0% impuesto transitorio
					cell = row.createCell(14);
					Utils.getCel(cell, styles, getMonto(compraIVA.getEmisorTipoDocumento(),  compraIVA.getMontoTrans0(),compraIVA.getFacturaTipoCambio()));
	
					// 4% impuesto transitorio
					cell = row.createCell(15);
					Utils.getCel(cell, styles, getMonto(compraIVA.getEmisorTipoDocumento(),  compraIVA.getMontoTrans4(),compraIVA.getFacturaTipoCambio()));
					// 8% impuesto transitorio
					cell = row.createCell(16);
					Utils.getCel(cell, styles, getMonto(compraIVA.getEmisorTipoDocumento(),  compraIVA.getMontoTrans8(),compraIVA.getFacturaTipoCambio()));
					// 13% impuesto transitorio
					cell = row.createCell(17);
					Utils.getCel(cell, styles, getMonto(compraIVA.getEmisorTipoDocumento(),  compraIVA.getMontoIva13(),compraIVA.getFacturaTipoCambio()));
					// otros% impuesto 
					cell = row.createCell(18);
					Utils.getCel(cell, styles, getMonto(compraIVA.getEmisorTipoDocumento(),  compraIVA.getMontoIVAOtros(),compraIVA.getFacturaTipoCambio()));

					
					// Total Descuento
					
					cell = row.createCell(19);
					Utils.getCel(cell, styles,getMonto(compraIVA.getEmisorTipoDocumento(),  compraIVA.getTotalDescuento(),compraIVA.getFacturaTipoCambio()) );
					// Total Impuesto
					cell = row.createCell(20);
					Utils.getCel(cell, styles, getMonto(compraIVA.getEmisorTipoDocumento(),  compraIVA.getFacturaTotalImpuestos(),compraIVA.getFacturaTipoCambio()) );

					// Total comprobante
					cell = row.createCell(21);
					Utils.getCel(cell, styles, getMonto(compraIVA.getEmisorTipoDocumento(),  compraIVA.getFacturaTotalComprobante(),compraIVA.getFacturaTipoCambio()) );
					
					// Total Otros cargos
					cell = row.createCell(22);
					Utils.getCel(cell, styles, getMonto(compraIVA.getEmisorTipoDocumento(),  compraIVA.getTotal_otros_cargos(),compraIVA.getFacturaTipoCambio()));

					// Total Mercancia Gravada
					cell = row.createCell(23);
					Utils.getCel(cell, styles, getMonto(compraIVA.getEmisorTipoDocumento(),  compraIVA.getTotalMercanciaGravada(),compraIVA.getFacturaTipoCambio()));
					// Total Mercancia Exenta
					cell = row.createCell(24);
					Utils.getCel(cell, styles, getMonto(compraIVA.getEmisorTipoDocumento(),  compraIVA.getTotalMercanciasExentas(),compraIVA.getFacturaTipoCambio()));
			
					// Tipo Gasto
					cell = row.createCell(25);
					Utils.getCelSTR(cell, styles, compraIVA.getTipoGastoStr());

			rownum++;
		}
		int contnum = rownum;
		Row sumRow = sheet.createRow(rownum++);

		for (int j = 0; j < 28; j++) {
			Cell cell = sumRow.createCell(j);
			
			if (j <= 9  ) {
				cell.setCellStyle(styles.get("formula"));
			}

			if (j == 10) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "K" + 4 + ":K" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 11) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "L" + 4 + ":L" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 12) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "M" + 4 + ":M" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 13) {
				String ref = "N" + 4 + ":N" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}

			if (j == 14) {
				String ref = "O" + 4 + ":O" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 15) {
				String ref = "P" + 4 + ":P" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 16) {
				String ref = "Q" + 4 + ":Q" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 17) {
				String ref = "R" + 4 + ":R" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 18) {
				String ref = "S" + 4 + ":S" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}

			if (j == 19) {
				String ref = "T" + 4 + ":T" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 20) {
				String ref = "U" + 4 + ":U" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 21) {
				String ref = "V" + 4 + ":V" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 22) {
				String ref = "W" + 4 + ":W" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 23) {
				String ref = "X" + 4 + ":X" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 24) {
				String ref = "Y" + 4 + ":Y" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}


			sheet.setColumnWidth(j, 8500);
		}

		workbook.write(stream);
		workbook.close();
		return new ByteArrayInputStream(stream.toByteArray());
	}
	
	private Double getMonto(String tipoDoc, Double tipoCambio, Double monto) {
		monto = monto == null ? Constantes.ZEROS_DOUBLE : monto;
		return tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) ? monto * tipoCambio * -1 : monto * tipoCambio;
	}

	private Double getMontoImpuestoTotalOtros(RecepcionFacturaDetalle recepcionFacturaDetalle, Double tipoCambio) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		if(getVerificaCodigoImpuestoOtros(recepcionFacturaDetalle.getImpuestoCodigo())) {
			resultado += recepcionFacturaDetalle.getImpuestoMonto() != null ? recepcionFacturaDetalle.getImpuestoMonto() * tipoCambio : Constantes.ZEROS_DOUBLE;
		}
		if(recepcionFacturaDetalle.getImpuestoCodigo1() != null && getVerificaCodigoImpuestoOtros(recepcionFacturaDetalle.getImpuestoCodigo1())) {
			resultado += recepcionFacturaDetalle.getImpuestoMonto1() != null ? recepcionFacturaDetalle.getImpuestoMonto1() * tipoCambio : Constantes.ZEROS_DOUBLE;
		}	
		if(recepcionFacturaDetalle.getImpuestoCodigo2() != null && getVerificaCodigoImpuestoOtros(recepcionFacturaDetalle.getImpuestoCodigo2())) {
			resultado += recepcionFacturaDetalle.getImpuestoMonto2() != null ? recepcionFacturaDetalle.getImpuestoMonto2() * tipoCambio : Constantes.ZEROS_DOUBLE;
		}			
		if(recepcionFacturaDetalle.getImpuestoCodigo3() != null && getVerificaCodigoImpuestoOtros(recepcionFacturaDetalle.getImpuestoCodigo3())) {
			resultado += recepcionFacturaDetalle.getImpuestoMonto3() != null ? recepcionFacturaDetalle.getImpuestoMonto3() * tipoCambio : Constantes.ZEROS_DOUBLE;
		}	
		if(recepcionFacturaDetalle.getImpuestoCodigo4() != null && getVerificaCodigoImpuestoOtros(recepcionFacturaDetalle.getImpuestoCodigo4())) {
			resultado += recepcionFacturaDetalle.getImpuestoMonto4() != null ? recepcionFacturaDetalle.getImpuestoMonto4() * tipoCambio : Constantes.ZEROS_DOUBLE;
		}	
		if(recepcionFacturaDetalle.getImpuestoCodigo5() != null && getVerificaCodigoImpuestoOtros(recepcionFacturaDetalle.getImpuestoCodigo5())) {
			resultado += recepcionFacturaDetalle.getImpuestoMonto5() != null ? recepcionFacturaDetalle.getImpuestoMonto5() * tipoCambio : Constantes.ZEROS_DOUBLE;
		}	
		if(recepcionFacturaDetalle.getImpuestoCodigo6() != null && getVerificaCodigoImpuestoOtros(recepcionFacturaDetalle.getImpuestoCodigo6())) {
			resultado += recepcionFacturaDetalle.getImpuestoMonto6() != null ? recepcionFacturaDetalle.getImpuestoMonto6() * tipoCambio : Constantes.ZEROS_DOUBLE;
		}	
			

		return recepcionFacturaDetalle.getRecepcionFactura().getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) ? resultado * -1 : resultado;

	}
	private Boolean getVerificaCodigoImpuestoOtros(String codigo) {
		Boolean resultado = Boolean.FALSE;
		codigo = codigo == null?Constantes.EMPTY:codigo;
		if( codigo.equals("02") || codigo.equals("03") || codigo.equals("04")|| codigo.equals("05")|| codigo.equals("06")|| codigo.equals("08") || codigo.equals("12") || codigo.equals("99")  ) {
			resultado = Boolean.TRUE;
		}
		return resultado;
	}

	
	private Double getMontoImpuestoTotal(RecepcionFacturaDetalle recepcionFacturaDetalle, String tarifa, Double tipoCambio) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		if (recepcionFacturaDetalle.getImpuestoCodigoTarifa() != null && recepcionFacturaDetalle.getImpuestoCodigoTarifa().equals(tarifa)) {
			resultado = recepcionFacturaDetalle.getImpuestoMonto() != null ? recepcionFacturaDetalle.getImpuestoMonto() * tipoCambio : Constantes.ZEROS_DOUBLE;
		} else if (recepcionFacturaDetalle.getImpuestoCodigoTarifa1() != null && recepcionFacturaDetalle.getImpuestoCodigoTarifa1().equals(tarifa)) {
			resultado = recepcionFacturaDetalle.getImpuestoMonto1() != null ? recepcionFacturaDetalle.getImpuestoMonto1() * tipoCambio : Constantes.ZEROS_DOUBLE;
		} else if (recepcionFacturaDetalle.getImpuestoCodigoTarifa2() != null && recepcionFacturaDetalle.getImpuestoCodigoTarifa2().equals(tarifa)) {
			resultado = recepcionFacturaDetalle.getImpuestoMonto2() != null ? recepcionFacturaDetalle.getImpuestoMonto2() * tipoCambio : Constantes.ZEROS_DOUBLE;
		} else if (recepcionFacturaDetalle.getImpuestoCodigoTarifa3() != null && recepcionFacturaDetalle.getImpuestoCodigoTarifa3().equals(tarifa)) {
			resultado = recepcionFacturaDetalle.getImpuestoMonto3() != null ? recepcionFacturaDetalle.getImpuestoMonto3() * tipoCambio : Constantes.ZEROS_DOUBLE;
		} else if (recepcionFacturaDetalle.getImpuestoCodigoTarifa4() != null && recepcionFacturaDetalle.getImpuestoCodigoTarifa4().equals(tarifa)) {
			resultado = recepcionFacturaDetalle.getImpuestoMonto4() != null ? recepcionFacturaDetalle.getImpuestoMonto4() * tipoCambio : Constantes.ZEROS_DOUBLE;
		} else if (recepcionFacturaDetalle.getImpuestoCodigoTarifa5() != null && recepcionFacturaDetalle.getImpuestoCodigoTarifa5().equals(tarifa)) {
			resultado = recepcionFacturaDetalle.getImpuestoMonto5() != null ? recepcionFacturaDetalle.getImpuestoMonto5() * tipoCambio : Constantes.ZEROS_DOUBLE;
		} else if (recepcionFacturaDetalle.getImpuestoCodigoTarifa6() != null && recepcionFacturaDetalle.getImpuestoCodigoTarifa6().equals(tarifa)) {
			resultado = recepcionFacturaDetalle.getImpuestoMonto6() != null ? recepcionFacturaDetalle.getImpuestoMonto6() * tipoCambio : Constantes.ZEROS_DOUBLE;
		}

		return recepcionFacturaDetalle.getRecepcionFactura().getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) ? resultado * -1 : resultado;

	}

	/**
	 * Detalle de las compras sin ingresar en el inventario
	 */
	@Override
	public List<Map<String, Object>> detalleCompraSinIngresar(Long idCompra) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String sql = "SELECT d.numero_linea,d.id,c.id as idCompra,d.descripcion, d.cantidad ,d.impuesto,\n" + 
				"d.estado ,d.codigo as cod_proveedor,\n" + 
				"       part.codigo as cod_invet ,\n" + 
				"       d.costo as costo_prove,a.costo as costo_inv, \n" + 
				"       a.ganancia_precio_publico as ganancia,\n" + 
				"       a.precio_publico, a.cod_tarifa,\n" + 
				"       a.impuesto  as imp_art\n" + 
				"FROM detalles_compras d\n" + 
				"inner join compras c on c.id = d.compra_id\n" + 
				"left JOIN proveedor_articulo part ON part.cod_provee in( d.CODIGO,d.codigo_comercial_codigo1) and part.proveedor_id = c.proveedor_id "+
				"left join articulos a on a.id = part.articulo_id and c.proveedor_id = part.proveedor_id\n" + 
				"where c.estado = 6 and c.id = :idCompra and d.estado = 1 order by d.numero_linea asc ";
    parameters.addValue("idCompra", idCompra);
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate( jdbcTemplate );
    List<Map<String, Object>> listaObjetos = namedParameterJdbcTemplate.queryForList(sql, parameters);  
		return listaObjetos;
	}

	@Override
	public DetalleCompra findById(Long idDetalleCompra) {
	
		return detalleCompraDao.findById(idDetalleCompra);
	}

	@Override
	public Integer ContarDetalleCompraSinIngresar(Long idCompra) {
		// TODO Auto-generated method stub
		return detalleCompraDao.ContarDetalleCompraSinIngresar(idCompra);
	}

}
