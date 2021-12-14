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
import com.emprendesoftcr.Bo.EmpresaBo;
import com.emprendesoftcr.Dao.DetalleDao;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaUtilidadNative;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.DetalleVentaArticuloCommand;
import com.emprendesoftcr.web.command.TotalDetallesCommand;
import com.emprendesoftcr.web.command.TotalbyImpuestosCommand;
import com.emprendesoftcr.web.command.VentasByCategoriasCommand;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

@EnableTransactionManagement
@Service("detalleBo")
public class DetalleBoImpl implements DetalleBo {

	@Autowired
	private DetalleDao		detalleDao;

	@Autowired
	private EmpresaBo			empresaBo;
	@Autowired
	public DataSource			dataSource;

	private JdbcTemplate	jdbcTemplate;

	private Logger				log	= LoggerFactory.getLogger(this.getClass());

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
	public ByteArrayInputStream createExcelUtilidad(Collection<ConsultaUtilidadNative> facturas, Empresa empresa, String fechaInicio, String fechaFin) throws Exception {
		List<String> headers = Arrays.asList("Fecha Emision", "Factura", "Categoria", "Codigo", "Descripcion", "Costo", "Venta", "Utilidad");
		// Libro excel
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Map<String, CellStyle> styles = Utils.createStyles(workbook);
		Sheet sheet = workbook.createSheet("Utilidad");

		// title row
		Row title = sheet.createRow(0);
		title.setHeightInPoints(25);
		Cell titleCell = title.createCell(0);
		titleCell.setCellValue("Utilidad de Ventas del " + fechaInicio + " al " + fechaFin);
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
		titleCell2.setCellValue("Cedula:" + empresa.getCedula());
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
		// style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		// Freeze just one row
		// sheet.createFreezePane( 0, 2, 0, 2 );

		int rownum = 4;

		for (ConsultaUtilidadNative consultaUtilidadNative : facturas) {
			row = sheet.createRow(rownum);
			Cell cell = row.createCell(0);
			cell.setCellStyle(styles.get("cell"));
			cell.setCellValue(consultaUtilidadNative.getFechaEmision());
			// row.createCell(0).setCellStyle(styles.get("cell"));
			cell = row.createCell(1);
			Utils.getCelSTR(cell, styles, consultaUtilidadNative.getNumeroConsecutivo());
			cell = row.createCell(2);
			Utils.getCelSTR(cell, styles, consultaUtilidadNative.getNombreCategoria());
			cell = row.createCell(3);
			Utils.getCelSTR(cell, styles, consultaUtilidadNative.getCodigo());

			cell = row.createCell(4);
			Utils.getCelSTR(cell, styles, consultaUtilidadNative.getNombreArticulo());

			cell = row.createCell(5);
			Utils.getCel(cell, styles, consultaUtilidadNative.getTotalCosto());

			cell = row.createCell(6);
			Utils.getCel(cell, styles, consultaUtilidadNative.getVenta());

			cell = row.createCell(7);
			Utils.getCel(cell, styles, consultaUtilidadNative.getTotalUtilidad());

			rownum++;
		}
		int contnum = rownum;
		Row sumRow = sheet.createRow(rownum++);

		for (int j = 0; j <= 7; j++) {
			Cell cell = sumRow.createCell(j);
			if (j == 4) {
				cell.setCellValue("Totales  :");
				cell.setCellStyle(styles.get("formula"));

			}
			if (j == 0 || j == 1 || j == 2 || j == 3) {
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 5) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "F" + 4 + ":F" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 6) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "G" + 4 + ":G" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 7) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "H" + 4 + ":H" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}

			sheet.setColumnWidth(j, 8000);
		}

		// sheet.setColumnWidth(0, 50);
		// sheet.autoSizeColumn(1);
		workbook.write(stream);
		workbook.close();
		return new ByteArrayInputStream(stream.toByteArray());
	}

	@Override
	public ByteArrayInputStream createExcelVentasXCodigo(ArrayList<TotalbyImpuestosCommand> detalles, String fechaInicio, String fechaFinal, Empresa empresa, String actividadEconomica) throws Exception {
		List<String> headers = Arrays.asList("Fecha Emision", "Tipo Documento", "Clave", "# Documento", "#Proforma", "Cedula", "Cliente", "Nombre a", "Tarifa 0% Exento", "Tarifa 1%", "Tarifa 2%", "Tarifa 4%", "Transitorio 0%", "Transitorio 4%", "Transitorio 8%", "Tarifa general 13%", "Total IVA", "Total Comprobante", "Total 10%", "Total Exoneracion", "Total Serv.Gravados", "Total.Serv.Exentos", "Total.Mercancia.Gravadas", "Total Mercancia Exenta", "Total exento", "Total Gravado", "Total Mercancia Exenta", "Total Servicios Exentos");
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Map<String, CellStyle> styles = Utils.createStyles(workbook);
		Sheet sheet = workbook.createSheet("Venta por detalle de codigo");

		// title row
		Row title = sheet.createRow(0);
		title.setHeightInPoints(25);
		Cell titleCell = title.createCell(0);
		titleCell.setCellValue("Ventas  del " + fechaInicio + " al " + fechaFinal);
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
		for (TotalbyImpuestosCommand detalle : detalles) {
			row = sheet.createRow(rownum);
			Cell cell = row.createCell(0);

			// Fecha Emision
			Utils.getCelSTR(cell, styles, detalle.getFechaEmisionSTR());
			// Tipo de Documento
			cell = row.createCell(1);
			Utils.getCelSTR(cell, styles, detalle.getTipoDocSTR());
			// clave

			cell = row.createCell(2);
			Utils.getCelSTR(cell, styles, detalle.getClave());
			// Documento

			cell = row.createCell(3);
			Utils.getCelSTR(cell, styles, detalle.getNumero_consecutivo());
			// Proforma

			cell = row.createCell(4);
			Utils.getCelSTR(cell, styles, detalle.getConsecutivo_proforma());
			// Cedula

			cell = row.createCell(5);
			Utils.getCelSTR(cell, styles, detalle.getCedula());
			// Cliente

			cell = row.createCell(6);
			Utils.getCelSTR(cell, styles, detalle.getNombre_completo());

			// Nombre de la Factura

			cell = row.createCell(7);
			Utils.getCelSTR(cell, styles, detalle.getNombre_factura());

			// Codigo Tarifa 0 %
			cell = row.createCell(8);
			Utils.getCel(cell, styles, detalle.getImp_01());

			// Codigo Tarifa 1 %

			cell = row.createCell(9);
			Utils.getCel(cell, styles, detalle.getImp_02());

			// Codigo Tarifa 2 %

			cell = row.createCell(10);
			Utils.getCel(cell, styles, detalle.getImp_03());

			// Codigo Tarifa 4 %

			cell = row.createCell(11);
			Utils.getCel(cell, styles, detalle.getImp_04());

			// Codigo Tarifa Transitorio 0 %

			cell = row.createCell(12);
			Utils.getCel(cell, styles, detalle.getImp_05());

			// Codigo Tarifa Transitorio 4 %

			cell = row.createCell(13);
			Utils.getCel(cell, styles, detalle.getImp_06());

			// Codigo Tarifa Transitorio 8 %

			cell = row.createCell(14);
			Utils.getCel(cell, styles, detalle.getImp_07());
			// Codigo Tarifa General 13 %

			cell = row.createCell(15);
			Utils.getCel(cell, styles, detalle.getImp_08());

			// Total impuesto

			cell = row.createCell(16);
			Utils.getCel(cell, styles, detalle.getTotal_impuesto());

			// Total comprobante

			cell = row.createCell(17);
			Utils.getCel(cell, styles, detalle.getTotal_comprobante());
			// Total 10%
			cell = row.createCell(18);
			Utils.getCel(cell, styles, detalle.getTotal_otros_cargos());

			// Total Exoneracion

			cell = row.createCell(19);
			Utils.getCel(cell, styles, detalle.getTotal_exo());
			// Total Serv.Gravados

			cell = row.createCell(20);
			Utils.getCel(cell, styles, detalle.getTotal_serv_gravados());

			// Total.Serv.Exentos

			cell = row.createCell(21);
			Utils.getCel(cell, styles, detalle.getTotal_serv_exentos());

			// Total.Mercancia.Gravadas

			cell = row.createCell(22);
			Utils.getCel(cell, styles, detalle.getTotal_mercancias_gravadas());
			// Total Mercancia Exenta

			cell = row.createCell(23);
			Utils.getCel(cell, styles, detalle.getTotal_mercancias_exentas());
			// Total exento

			cell = row.createCell(24);
			Utils.getCel(cell, styles, detalle.getTotal_exento());

			// Total Gravado
			cell = row.createCell(25);
			Utils.getCel(cell, styles, detalle.getTotal_gravado());
			// Total Mercancia Exenta
			cell = row.createCell(26);
			Utils.getCel(cell, styles, detalle.getTotal_mercancias_exentas());

			// Total Servicios Exentos
			cell = row.createCell(27);
			Utils.getCel(cell, styles, detalle.getTotal_serv_exentos());

			rownum++;
		}
		int contnum = rownum;
		Row sumRow = sheet.createRow(rownum++);

		for (int j = 0; j < 33; j++) {
			Cell cell = sumRow.createCell(j);
			if (j == 7) {
				cell.setCellValue("Totales  :");
				cell.setCellStyle(styles.get("formula"));

			}
			if (j <= 7) {
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 8) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "I" + 4 + ":I" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 9) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "J" + 4 + ":J" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
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
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "N" + 4 + ":N" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 14) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "O" + 4 + ":O" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 15) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "P" + 4 + ":P" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 16) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
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
			if (j == 27) {
				String ref = "AB" + 4 + ":AB" + contnum;
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
	public List<Map<String, Object>> ventasbyCategoria(String fechaInicial, String fechaFinal, Integer estado, Long idCategoria, Integer idEmpresa) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select  ifnull(cat.id,'9999') as id, ifnull(cat.descripcion,'Sin categoria') as nom_cat ,det.cod_tarifa,det.impuesto,det.tipo_impuesto,fact.estado,  \n" + "                      sum(ifnull(if(fact.tipo_doc = '03' or fact.tipo_doc = '86',ROUND(det.cantidad,2) * -1,ROUND(det.cantidad,2)),0)) cantidad,  \n" + "                      sum(ifnull(if(fact.tipo_doc = '03' or fact.tipo_doc = '86',ROUND((det.costo * det.cantidad) * fact.tipo_cambio,2) * -1,ROUND(det.costo * det.cantidad,2)) * tipo_cambio,0)) as total_costo,\n" + "                      sum(ifnull(if(fact.tipo_doc = '03' or fact.tipo_doc = '86',det.imp_neto *  -1,det.imp_neto ),0) * fact.tipo_cambio) as total_neto,\n"
				+ "                      sum(ifnull(if(fact.tipo_doc = '03' or fact.tipo_doc = '86',ROUND(det.monto_descuento,2) * - 1 * fact.tipo_cambio,ROUND(det.monto_descuento,2)),0)) total_desc,\n" + "                      sum(ifnull(if(fact.tipo_doc = '03' or fact.tipo_doc = '86',ROUND(det.monto_total,2) * -1 * fact.tipo_cambio,ROUND(det.monto_total,2)),0)) mont_total,\n" + "                      sum(ifnull(if(fact.tipo_doc = '03' or fact.tipo_doc = '86',ROUND(det.sub_total,2) * -1 * fact.tipo_cambio,ROUND(det.sub_total,2)),0)) sub_tota,\n" + "                      sum(ifnull(if(fact.tipo_doc = '03' or fact.tipo_doc = '86',det.monto_total_linea * -1 ,det.monto_total_linea),0) * fact.tipo_cambio) as total_linea,\n"
				+ "                      sum(ifnull(if(fact.tipo_doc = '03' or fact.tipo_doc = '86',ROUND(det.mont_exone,2) * -1 * fact.tipo_cambio,ROUND(det.mont_exone,2)),0)) mont_exon\n" + "                    from detalles det \n" + "                    inner join facturas fact on  fact.id = det.factura_id \n" + "                    left join articulos art on art.codigo = det.codigo  and fact.empresa_id = art.empresa_id \n" + "                    inner join categorias  cat on cat.id = art.categoria_id  \n" + "                    where  fact.estado = :estado  \n" + "                           and fact.empresa_id = :idempresa\n" + "                           and cat.id = " + "                           and fact.fecha_emision >= :fecha_inicial  and fact.fecha_emision <= :fecha_final\n"
				+ "                    group by cat.id ,cat.descripcion,det.tipo_impuesto,det.cod_tarifa,det.impuesto,fact.estado \n" + "                    order by cat.descripcion ,det.tipo_impuesto,det.cod_tarifa ";

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("fecha_inicial", fechaInicial);
		parameters.addValue("fecha_final", fechaFinal);
		parameters.addValue("idempresa", idEmpresa);
		if (estado > Constantes.ZEROS) {
			parameters.addValue("estado", estado);
		} else {
			sql = sql.replaceAll(" fact.estado = :estado", " fact.estado in (2,6,7) ");
		}
		if (idCategoria > Constantes.ZEROS) {

			sql = sql.replaceAll("and cat.id = ", "and cat.id = :idcaegoria ");
			parameters.addValue("idcaegoria", idCategoria);
		} else {
			sql = sql.replaceAll("and cat.id = ", " ");
		}

		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<Map<String, Object>> listaObjetos = namedParameterJdbcTemplate.queryForList(sql, parameters);

		return listaObjetos;
	}

	@Override
	public List<Map<String, Object>> ventasbyArticulo(String fechaInicial, String fechaFinal, String codigoArticulo, Integer idEmpresa, Integer estado) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = " Select DATE_FORMAT(f.fecha_emision, \"%Y-%m-%d\")  as fechaEmision,\n" + "      d.codigo,d.descripcion,sum(d.cantidad)as cantidad,\n" + "      sum(d.monto_descuento) as descuento,\n" + "      sum(IFNULL(if(f.tipo_doc = '03' or f.tipo_doc = '86' ,d.mont_exone * -1,d.mont_exone), 0) * f.tipo_cambio) as totalExoneraciones ,\n" + "      sum(IFNULL(if(f.tipo_doc = '03' or f.tipo_doc = '86' ,d.imp_neto * f.tipo_cambio * -1,d.imp_neto* f.tipo_cambio), 0) ) as totalImpuesto,\n" + "      sum(IFNULL(if(f.tipo_doc = '03' or f.tipo_doc = '86' ,d.monto_total_linea * -1,d.monto_total_linea), 0) * f.tipo_cambio) as totalVentas \n" + "      from detalles d \n" + "      inner join facturas f on f.id = d.factura_id \n"
				+ "      where f.empresa_id = :idempresa and f.fecha_emision >= :fecha_inicial  and f.fecha_emision <= :fecha_final " + " and f.estado = :Pestado \n" + " and d.codigo = :codigoArticulo \n" + "       and f.tipo_doc in ('04','86','87','01','03')\n" + "      GROUP by fechaEmision,d.codigo,d.descripcion " + "order by DATE_FORMAT(f.fecha_emision, \"%Y-%m-%d\") asc";

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("fecha_inicial", fechaInicial);
		parameters.addValue("fecha_final", fechaFinal);
		parameters.addValue("idempresa", idEmpresa);

		if (codigoArticulo != null && !codigoArticulo.equals(Constantes.EMPTY)) {

			sql = sql.replaceAll("and d.codigo = :codigoArticulo", "and d.codigo = :codigoArticulo ");
			parameters.addValue("codigoArticulo", codigoArticulo);
		} else {
			sql = sql.replaceAll("and d.codigo = :codigoArticulo ", " ");
		}
	
			sql = sql.replaceAll(" f.estado = :Pestado", " f.estado in (2,6,7) ");
		
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<Map<String, Object>> listaObjetos = namedParameterJdbcTemplate.queryForList(sql, parameters);

		return listaObjetos;
	}

	@Override
	public ByteArrayInputStream ventasbyArticuloExcel(String fechaInicial, String fechaFinal, String codigoArticulo, Integer idEmpresa, Integer estado) throws IOException {
		Empresa empresa = empresaBo.buscar(idEmpresa);
		List<Map<String, Object>> lista = ventasbyArticulo(fechaInicial, fechaFinal, codigoArticulo, idEmpresa, estado);
		@SuppressWarnings("rawtypes")
		ArrayList arrayList = new ArrayList();
		arrayList = (ArrayList<Map<String, Object>>) lista;
		JsonArray jsonArray1 = new Gson().toJsonTree(arrayList).getAsJsonArray();
		ArrayList<DetalleVentaArticuloCommand> listaDatos = new ArrayList<DetalleVentaArticuloCommand>();
		Gson gson = new Gson();
		if (jsonArray1 != null) {
			for (int i = 0; i < jsonArray1.size(); i++) {
				DetalleVentaArticuloCommand detalleVentaArticuloCommand = gson.fromJson(jsonArray1.get(i).toString(), DetalleVentaArticuloCommand.class);
				listaDatos.add(detalleVentaArticuloCommand);
			}
		}
		List<String> headers = Arrays.asList("Fecha", "Codigo", "Descripcion", "Cantidad", "Descuento", "Exoneraciones", "Total Impuesto", "Total venta");
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Map<String, CellStyle> styles = Utils.createStyles(workbook);
		Sheet sheet = workbook.createSheet("Venta por articulos");

		// title row
		Row title = sheet.createRow(0);
		title.setHeightInPoints(25);
		Cell titleCell = title.createCell(0);
		titleCell.setCellValue("Ventas por articulos del " + fechaInicial.replace("00:00:00", "") + " al " + fechaFinal.replace("00:00:00", ""));
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

		for (DetalleVentaArticuloCommand detalleVentaArticuloCommand : listaDatos) {
			row = sheet.createRow(rownum);
			// Usuario
			Cell cell = row.createCell(0);
			Utils.getCelSTR(cell, styles, detalleVentaArticuloCommand.getFechaEmision());
			// Fecha Emision
			cell = row.createCell(1);
			Utils.getCelSTR(cell, styles, detalleVentaArticuloCommand.getCodigo());
			// Tipo de Documento
			cell = row.createCell(2);
			Utils.getCelSTR(cell, styles, detalleVentaArticuloCommand.getDescripcion());
			// Codigo
			cell = row.createCell(3);
			Utils.getCel(cell, styles, detalleVentaArticuloCommand.getCantidad());
			// Descripcion
			cell = row.createCell(4);
			Utils.getCel(cell, styles, detalleVentaArticuloCommand.getDescuento());
			// clave
			cell = row.createCell(5);
			Utils.getCel(cell, styles, detalleVentaArticuloCommand.getTotalExoneraciones());
			// Documento
			cell = row.createCell(6);
			Utils.getCel(cell, styles, detalleVentaArticuloCommand.getTotalImpuesto());
			// Proforma
			cell = row.createCell(7);
			Utils.getCel(cell, styles, detalleVentaArticuloCommand.getTotalVentas());
			
			rownum++;
		}
		int contnum = rownum;
		Row sumRow = sheet.createRow(rownum++);

		for (int j = 0; j < 8; j++) {
			Cell cell = sumRow.createCell(j);
			if (j == 2) {
				cell.setCellValue("Totales  :");
				cell.setCellStyle(styles.get("formula"));

			}
			if (j <= 2) {
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 3) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "D" + 4 + ":D" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 4) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "E" + 4 + ":E" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 5) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "F" + 4 + ":F" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 6) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "G" + 4 + ":G" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 7) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "H" + 4 + ":H" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}

			sheet.setColumnWidth(j, 8000);
		}

		workbook.write(stream);
		workbook.close();
		return new ByteArrayInputStream(stream.toByteArray());
	}

	@Override
	public List<Map<String, Object>> totalbyImpuestos(String fechaInicial, String fechaFinal, Integer estado, Integer idEmpresa, String codigoComercial) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select t.fecha_emision,t.tipo_doc,t.clave,t.numero_consecutivo,t.consecutivo_proforma,t.cedula,t.identificacion_extranjero,\n" + "		t.nombre_completo,t.nombre_factura,t.total_exo ,t.total_otros_cargos,total_descuentos,\n" + "        t.total_serv_gravados,t.total_serv_exentos,t.total_impuesto,\n" + "        t.total_mercancias_gravadas,t.total_mercancias_exentas,\n" + "        t.total_exento,t.total_gravado,t.total_comprobante,t.total_merc_exo,t.total_serv_exo, t.tipo_cambio, \n" + "        sum(if(t.cod_tarifa = '01', t.total_tarifa,0)) imp_01,\n" + "        sum(if(t.cod_tarifa = '02', t.total_tarifa,0)) imp_02,\n" + "        sum(if(t.cod_tarifa = '03', t.total_tarifa,0)) imp_03,\n" + "        sum(if(t.cod_tarifa = '04', t.total_tarifa,0)) imp_04,\n"
				+ "        sum(if(t.cod_tarifa = '05', t.total_tarifa,0)) imp_05,\n" + "        sum(if(t.cod_tarifa = '06', t.total_tarifa,0)) imp_06,\n" + "        sum(if(t.cod_tarifa = '07', t.total_tarifa,0)) imp_07,\n" + "        sum(if(t.cod_tarifa = '08', t.total_tarifa,0)) imp_08,\n" + "        sum(if(t.cod_tarifa = '01', t.sub_total,0)) venta_imp_01,\n" + "        sum(if(t.cod_tarifa = '02', t.sub_total,0)) venta_imp_02,\n" + "        sum(if(t.cod_tarifa = '03', t.sub_total,0)) venta_imp_03,\n" + "        sum(if(t.cod_tarifa = '04', t.sub_total,0)) venta_imp_04,\n" + "        sum(if(t.cod_tarifa = '05', t.sub_total,0)) venta_imp_05,\n" + "        sum(if(t.cod_tarifa = '06', t.sub_total,0)) venta_imp_06,\n" + "        sum(if(t.cod_tarifa = '07', t.sub_total,0)) venta_imp_07,\n"
				+ "        sum(if(t.cod_tarifa = '08', t.sub_total,0)) venta_imp_08,\n" + "        sum(total_otros_impuestos) total_otros_impuestos,\n" + "        sum(total_otros_impuestos_v) total_otros_impuestos_v,\n" + "        sum(total_imp_cemento) total_imp_cemento,\n" + "        sum(total_imp_cemento_v) total_imp_cemento_v\n" +

				"from (\n" + "\n" + "select f.fecha_emision,f.tipo_doc,f.numero_consecutivo,f.consecutivo_proforma ,f.clave,c.cedula,c.identificacion_extranjero,\n" + "		c.nombre_completo,f.nombre_factura,d.tipo_impuesto,f.total_exo ,f.total_otros_cargos,f.total_serv_gravados,f.total_serv_exentos,f.total_mercancias_gravadas,f.total_mercancias_exentas,f.total_impuesto,f.total_exento," + "  f.total_gravado,f.total_comprobante,f.total_merc_exo,f.total_serv_exo, f.total_descuentos,\n" + "        d.cod_tarifa,d.impuesto,f.tipo_cambio,\n" + "        sum(if(f.tipo_doc = '03' or f.tipo_doc = '86' ,d.sub_total * f.tipo_cambio * -1,d.sub_total* f.tipo_cambio)) as sub_total ,\n"
				+ "        sum(if(f.tipo_doc = '03' or f.tipo_doc = '86' ,d.imp_neto * f.tipo_cambio * -1,d.imp_neto* f.tipo_cambio)) as total_tarifa, \n" + "       sum(if(d.tipo_impuesto ='99',if(f.tipo_doc = '03' or f.tipo_doc = '86' ,d.imp_neto * f.tipo_cambio * -1,d.imp_neto* f.tipo_cambio),0)) as total_otros_impuestos, \n" + "       sum(if(d.tipo_impuesto ='99',if(f.tipo_doc = '03' or f.tipo_doc = '86' ,d.sub_total * f.tipo_cambio * -1,d.sub_total* f.tipo_cambio),0)) as total_otros_impuestos_v, \n" + "       sum(if(d.tipo_impuesto ='12',if(f.tipo_doc = '03' or f.tipo_doc = '86' ,d.sub_total * f.tipo_cambio * -1,d.sub_total* f.tipo_cambio),0)) as total_imp_cemento, \n"
				+ "       sum(if(d.tipo_impuesto ='12',if(f.tipo_doc = '03' or f.tipo_doc = '86' ,d.imp_neto * f.tipo_cambio * -1,d.imp_neto* f.tipo_cambio),0)) as total_imp_cemento_v \n" + "         from detalles d\n" + "       inner join facturas f on f.id = d.factura_id\n" + "       inner join clientes c on c.id = f.cliente_id\n" + "	   where f.estado = :estado  \n" + "	   and f.empresa_id = :idempresa \n" + "	   and f.fecha_emision >= :fecha_inicial \n" + "	   and f.fecha_emision <= :fecha_final\n" + "       GROUP by f.fecha_emision,f.tipo_doc,f.clave,f.numero_consecutivo,f.consecutivo_proforma \n" + "				,f.clave,c.cedula,c.identificacion_extranjero,\n" + "				c.nombre_completo,f.nombre_factura,d.tipo_impuesto,\n"
				+ "                f.total_exo ,f.total_otros_cargos,f.total_serv_gravados,\n" + "                f.total_serv_exentos,f.total_mercancias_gravadas,\n" + "                f.total_mercancias_exentas,f.total_impuesto,\n" + "                f.total_exento,f.total_gravado,f.total_comprobante,f.total_merc_exo,f.total_serv_exo, f.total_descuentos,\n" + "				d.cod_tarifa,d.impuesto,f.tipo_cambio\n" + "	   order by f.fecha_emision,d.tipo_impuesto,d.cod_tarifa,d.impuesto) t\n" + "       GROUP by t.fecha_emision,t.tipo_doc,t.clave,t.numero_consecutivo,t.consecutivo_proforma,t.cedula,t.identificacion_extranjero,\n" + "				t.nombre_completo,t.nombre_factura,t.total_exo ,\n" + "                t.total_otros_cargos,t.total_serv_gravados,\n"
				+ "                t.total_serv_exentos,t.total_impuesto,\n" + "                t.total_mercancias_gravadas,t.total_mercancias_exentas,\n" + "                t.total_exento,t.total_gravado,t.total_comprobante,t.total_merc_exo,t.total_serv_exo,t.total_descuentos,t.tipo_cambio";

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("fecha_inicial", fechaInicial);
		parameters.addValue("fecha_final", fechaFinal);
		parameters.addValue("idempresa", idEmpresa);
		if (estado > Constantes.ZEROS) {
			parameters.addValue("estado", estado);
		} else {
			sql = sql.replaceAll(" fact.estado = :estado", " fact.estado in (2,6,7) ");
		}
		if (codigoComercial.equals(Constantes.COMBO_TODOS)) {
			sql = sql.replaceAll(" and f.act_comercial = :codigoComercial", "");
		} else {
			parameters.addValue("codigoComercial", codigoComercial);
		}

		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<Map<String, Object>> listaObjetos = namedParameterJdbcTemplate.queryForList(sql, parameters);

		return listaObjetos;
	}

	@Override
	public ByteArrayInputStream ventasbyCategoriaExcel(String fechaInicial, String fechaFinal, Integer estado, Long idCategoria, Empresa empresa) throws IOException {

		List<Map<String, Object>> lista = ventasbyCategoria(fechaInicial, fechaFinal, estado, idCategoria, empresa.getId());
		@SuppressWarnings("rawtypes")
		ArrayList arrayList = new ArrayList();
		arrayList = (ArrayList<Map<String, Object>>) lista;
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
		Sheet sheet = workbook.createSheet("Venta por Categoria");

		// title row
		Row title = sheet.createRow(0);
		title.setHeightInPoints(25);
		Cell titleCell = title.createCell(0);
		titleCell.setCellValue("Ventas por categoria del " + fechaInicial.replace("00:00:00", "") + " al " + fechaFinal.replace("00:00:00", ""));
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

		for (VentasByCategoriasCommand ventasByCategoriasCommand : listaDatos) {
			row = sheet.createRow(rownum);
			// Usuario
			Cell cell = row.createCell(0);
			Utils.getCelSTR(cell, styles, ventasByCategoriasCommand.getEstadoSTR());
			// Fecha Emision
			cell = row.createCell(1);
			Utils.getCelSTR(cell, styles, ventasByCategoriasCommand.getDescrpcion());
			// Tipo de Documento
			cell = row.createCell(2);
			Utils.getCelSTR(cell, styles, ventasByCategoriasCommand.getTipoImpuestoSTR());
			// Codigo
			cell = row.createCell(3);
			Utils.getCelSTR(cell, styles, ventasByCategoriasCommand.getCodigoTarifaSTR());
			// Descripcion
			cell = row.createCell(4);
			Utils.getCel(cell, styles, ventasByCategoriasCommand.getImpuesto());
			// clave
			cell = row.createCell(5);
			Utils.getCel(cell, styles, ventasByCategoriasCommand.getTotal_costo());
			// Documento
			cell = row.createCell(6);
			Utils.getCel(cell, styles, ventasByCategoriasCommand.getTotal_desc());
			// Proforma
			cell = row.createCell(7);
			Utils.getCel(cell, styles, ventasByCategoriasCommand.getTotal_neto());
			// Cedula
			cell = row.createCell(8);
			Utils.getCel(cell, styles, ventasByCategoriasCommand.getTotal_linea());
			// Cliente
			cell = row.createCell(9);
			Utils.getCel(cell, styles, ventasByCategoriasCommand.getMont_exon());

			rownum++;
		}
		int contnum = rownum;
		Row sumRow = sheet.createRow(rownum++);

		for (int j = 0; j < 10; j++) {
			Cell cell = sumRow.createCell(j);
			if (j == 4) {
				cell.setCellValue("Totales  :");
				cell.setCellStyle(styles.get("formula"));

			}
			if (j <= 3) {
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 5) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "F" + 4 + ":F" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 6) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "G" + 4 + ":G" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 7) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "H" + 4 + ":H" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 8) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "I" + 4 + ":I" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 9) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "J" + 4 + ":J" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}

			sheet.setColumnWidth(j, 8000);
		}

		workbook.write(stream);
		workbook.close();
		return new ByteArrayInputStream(stream.toByteArray());
	}

}
