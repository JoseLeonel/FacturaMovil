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

import com.emprendesoftcr.Bo.CuentaCobrarBo;
import com.emprendesoftcr.Dao.AbonoDao;
import com.emprendesoftcr.Dao.CuentaCobrarDao;
import com.emprendesoftcr.modelo.Abono;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.CuentaCobrar;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.SaldoCommand;
import com.emprendesoftcr.web.command.TotalCuentaPorCobrarCommand;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

/**
 * cuentas por cobrar que se debe cobrar a los clientes CuentaCobrarBoImpl.
 * @author jose.
 * @since 25 mar. 2018
 */

@EnableTransactionManagement
@Service("cuentaCobrarBo")
public class CuentaCobrarBoImpl implements CuentaCobrarBo {

	@Autowired
	private CuentaCobrarDao	cuentaCobrarDao;

	@Autowired
	private AbonoDao				abonoDao;

	@Autowired
	public DataSource				dataSource;

	private JdbcTemplate		jdbcTemplate;

	private Logger					log	= LoggerFactory.getLogger(this.getClass());

	@Transactional
	@Override
	public void agregar(CuentaCobrar cuentaCobrar) {
		cuentaCobrarDao.agregar(cuentaCobrar);
	}

	@Transactional
	@Override
	public void modificar(CuentaCobrar cuentaCobrar) {
		cuentaCobrarDao.modificar(cuentaCobrar);
	}

	@Transactional
	@Override
	public void eliminar(CuentaCobrar cuentaCobrar) {
		cuentaCobrarDao.eliminar(cuentaCobrar);
	}

	/**
	 * Busca por Id
	 * @see com.factura.bo.CuentaCobrarBo#buscar(java.lang.Integer)
	 */
	@Override
	public CuentaCobrar buscar(Long id) {
		return cuentaCobrarDao.buscar(id);
	}

	@Override
	public TotalCuentaPorCobrarCommand sumarCuentasPorCobrar(Date fechaInicio, Date fechaFinal, Integer idEmpresa, Cliente cliente) {
		return cuentaCobrarDao.sumarCuentasPorCobrar(fechaInicio, fechaFinal, idEmpresa, cliente);
	}

	/**
	 * Genera lista de cuentas por cobrar de clientes
	 * @see com.emprendesoftcr.Bo.CuentaCobrarBo#cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(java.util.Date, java.util.Date, com.emprendesoftcr.modelo.Empresa, com.emprendesoftcr.modelo.Cliente, java.lang.Integer)
	 */
	@Override
	public Collection<CuentaCobrar> cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(Date fechaInicio, Date fechaFin, Empresa empresa, Cliente cliente, String estado) {
		return cuentaCobrarDao.cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(fechaInicio, fechaFin, empresa, cliente, estado);

	}

	@Override
	public Collection<CuentaCobrar> cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(Empresa empresa, Cliente cliente, String estado) {
		return cuentaCobrarDao.cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(empresa, cliente, estado);

	}

	@Override
	public Collection<CuentaCobrar> cuentasPorCobrarbyEstado(String estado) {

		return cuentaCobrarDao.cuentasPorCobrarbyEstado(Constantes.CUENTA_POR_COBRAR_ESTADO_PENDIENTE);
	}

	@Transactional
	@Override
	public void modificarCuentaXCobrarPorNotaCredito(Factura notaCredito, Factura facturaAplicar) {

		try {
			CuentaCobrar cuentaCobrar = cuentaCobrarDao.buscarPorConsecutivo(notaCredito.getEmpresa(), facturaAplicar.getNumeroConsecutivo());
			if (cuentaCobrar != null) {
				Double saldo = cuentaCobrar.getTotalSaldo() == null ? Constantes.ZEROS_DOUBLE : cuentaCobrar.getTotalSaldo();
				saldo = Utils.roundFactura(saldo, 2) - Utils.roundFactura(notaCredito.getTotalComprobante(), 2);
				String estado = Constantes.CUENTA_POR_COBRAR_ESTADO_PENDIENTE;
				if (saldo <= Constantes.ZEROS_DOUBLE) {
					estado = Constantes.CUENTA_POR_COBRAR_ESTADO_CERRADO;
				}
				cuentaCobrar.setTotalSaldo(saldo);
				cuentaCobrar.setTotalAbono(Utils.roundFactura(cuentaCobrar.getTotalAbono(), 2) + Utils.roundFactura(notaCredito.getTotalComprobante(), 2));
				cuentaCobrar.setEstado(estado);
				modificar(cuentaCobrar);
				Abono abono = new Abono();
				abono.setCreated_at(new Date());
				abono.setUpdated_at(new Date());
				abono.setCuentaCobrar(cuentaCobrar);
				abono.setEstado(Constantes.ABONO_PAGAR_ESTADO_PAGADO);
				abono.setRecibo("NC:" + facturaAplicar.getNumeroConsecutivo());
				abono.setNota("Por concepto de nota credito,factura referencia:" + facturaAplicar.getNumeroConsecutivo());
				abono.setTotal(Utils.roundFactura(notaCredito.getTotalComprobante(), 2));
				abono.setTotalBanco(Constantes.ZEROS_DOUBLE);
				abono.setTotalEfectivo(Utils.roundFactura(notaCredito.getTotalComprobante(), 2));
				abono.setTotalTarjeta(Constantes.ZEROS_DOUBLE);
				abono.setTransferencia(Constantes.EMPTY);
				abono.setUsuario(notaCredito.getUsuarioCreacion());
				abono.setFechaPago(new Date());
				abonoDao.agregar(abono);

			}

		} catch (Exception e) {
			log.error("** Error  crear la factura: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	@Transactional
	@Override
	public void modificarCuentaXCobrarPorNotaDebito(Factura notaCredito, Factura facturaAplicar) {

		try {
			CuentaCobrar cuentaCobrar = cuentaCobrarDao.buscarPorConsecutivo(notaCredito.getEmpresa(), facturaAplicar.getNumeroConsecutivo());
			if (cuentaCobrar != null) {
				Double saldo = cuentaCobrar.getTotalSaldo() == null ? Constantes.ZEROS_DOUBLE : cuentaCobrar.getTotalSaldo();
				saldo = Utils.roundFactura(saldo, 2) + Utils.roundFactura(notaCredito.getTotalComprobante(), 2);
				String estado = Constantes.CUENTA_POR_COBRAR_ESTADO_PENDIENTE;
				if (saldo <= Constantes.ZEROS_DOUBLE) {
					estado = Constantes.CUENTA_POR_COBRAR_ESTADO_CERRADO;
				}
				cuentaCobrar.setTotalSaldo(saldo);
				cuentaCobrar.setTotalAbono(Utils.roundFactura(cuentaCobrar.getTotalAbono(), 2) - Utils.roundFactura(notaCredito.getTotalComprobante(), 2));
				cuentaCobrar.setEstado(estado);
				modificar(cuentaCobrar);
				Abono abono = new Abono();
				abono.setCreated_at(new Date());
				abono.setUpdated_at(new Date());
				abono.setCuentaCobrar(cuentaCobrar);
				abono.setEstado(Constantes.ABONO_PAGAR_ESTADO_PAGADO);
				abono.setRecibo("NB:" + facturaAplicar.getNumeroConsecutivo());
				abono.setNota("Por concepto de nota debito,factura referencia:" + facturaAplicar.getNumeroConsecutivo());
				abono.setTotal(Utils.roundFactura(notaCredito.getTotalComprobante() * -1, 2));
				abono.setTotalBanco(Constantes.ZEROS_DOUBLE);
				abono.setTotalEfectivo(Utils.roundFactura(notaCredito.getTotalComprobante() * -1, 2));
				abono.setTotalTarjeta(Constantes.ZEROS_DOUBLE);
				abono.setTransferencia(Constantes.EMPTY);
				abono.setUsuario(notaCredito.getUsuarioCreacion());
				abono.setFechaPago(new Date());
				abonoDao.agregar(abono);

			}

		} catch (Exception e) {
			log.error("** Error  crear la factura: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	@Override
	public ByteArrayInputStream createExcelCuentaCobrar(Collection<CuentaCobrar> cuentaCobrars, Empresa empresa, String fechaInicio, String fechaFinal, String estado, Cliente cliente) throws IOException {
		List<String> headers = Arrays.asList("#cuenta", "Fecha Emisión", "#Documento", "Cliente", "Moneda", "Facturación", "Saldo", "Abono");
		// Libro excel
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Map<String, CellStyle> styles = Utils.createStyles(workbook);
		Sheet sheet = workbook.createSheet("Utilidad");
		String descripcion = Constantes.EMPTY;
		if (estado.equals(Constantes.CUENTA_POR_COBRAR_ESTADO_PENDIENTE)) {
			descripcion = "Pendiente";
		} else if (estado.equals(Constantes.CUENTA_POR_COBRAR_ESTADO_CERRADO)) {
			descripcion = "Canceladas";
		} else if (estado.equals(Constantes.CUENTA_POR_COBRAR_ESTADO_ANULADA)) {
			descripcion = "Anuladas";
		}

		// title row
		Row title = sheet.createRow(0);
		title.setHeightInPoints(25);
		Cell titleCell = title.createCell(0);
		if (fechaInicio.length() != 0) {
			titleCell.setCellValue("Facturación a crédito del   " + fechaInicio + " al " + fechaFinal + "  " + descripcion);
		} else {
			titleCell.setCellValue("Facturación a crédito " + descripcion);
		}

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
		titleCell2.setCellValue("Cédula:" + empresa.getCedula());
		titleCell2.setCellStyle(styles.get("title"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$H$3"));
		Boolean tieneCliente = Boolean.FALSE;
		if (cliente != null) {
			Row titleCliente = sheet.createRow(3);
			titleCliente.setHeightInPoints(25);
			Cell cell = titleCliente.createCell(0);
			cell.setCellValue(cliente.getNombreCompleto());
			cell.setCellStyle(styles.get("title"));
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$H$4"));
			tieneCliente = Boolean.TRUE;
		}

		Row row = null;
		int rownum = 4;
		int inicioFormula = 4;
		if (tieneCliente) {
			row = sheet.createRow(4);
			rownum = 5;
			inicioFormula = 5;
		} else {
			row = sheet.createRow(3);
		}

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

		for (CuentaCobrar cuentaCobrar : cuentaCobrars) {
			row = sheet.createRow(rownum);
			Cell cell = row.createCell(0);
			cell.setCellStyle(styles.get("cell"));
			cell.setCellValue(cuentaCobrar.getId());
			// row.createCell(0).setCellStyle(styles.get("cell"));
			cell = row.createCell(1);
			cell.setCellStyle(styles.get("cell"));
			cell.setCellValue(cuentaCobrar.getCreated_atSTR());
			cell = row.createCell(2);
			cell.setCellStyle(styles.get("cell"));
			cell.setCellValue(cuentaCobrar.getFactura());
			cell = row.createCell(3);
			cell.setCellStyle(styles.get("cell"));

			cell.setCellValue(cuentaCobrar.getNombreClienteSTR());
			cell = row.createCell(4);
			cell.setCellStyle(styles.get("cell"));

			cell.setCellValue(cuentaCobrar.getCodigoMoneda());
			cell = row.createCell(5);
			cell.setCellStyle(styles.get("cell"));

			cell.setCellValue(cuentaCobrar.getTotal());
			cell = row.createCell(6);
			cell.setCellStyle(styles.get("cell"));

			cell.setCellValue(cuentaCobrar.getTotalSaldo());
			cell = row.createCell(7);
			cell.setCellStyle(styles.get("cell"));

			cell.setCellValue(cuentaCobrar.getTotalAbono());

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
				String ref = "F" + inicioFormula + ":F" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 6) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "G" + inicioFormula + ":G" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j == 7) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "H" + inicioFormula + ":H" + contnum;
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

	/**
	 * Credito disponible
	 */
	@Override
	public Double getDisponible(Integer idEmpresa, Cliente cliente) {
		Double disponible = Constantes.ZEROS_DOUBLE;
		try {
			jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "SELECT sum(c.total_saldo) as saldo FROM cuentas_cobrar c\n"
					+ "      where  c.estado = :estado and c.empresa_id = :idempresa "
					+ "             and c.cliente_id = :idCliente  \n" ;
					

			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("idempresa", idEmpresa);
			parameters.addValue("estado", Constantes.CUENTA_POR_COBRAR_ESTADO_PENDIENTE);
			parameters.addValue("idCliente", cliente.getId());

			
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
			List<Map<String, Object>> listaObjetos = namedParameterJdbcTemplate.queryForList(sql, parameters);
			ArrayList arrayList = new ArrayList();
			arrayList = (ArrayList) listaObjetos;
			JsonArray jsonArray1 = new Gson().toJsonTree(arrayList).getAsJsonArray();
			Gson gson = new Gson();
			if (jsonArray1 != null) {
				for (int i = 0; i < jsonArray1.size(); i++) {
					SaldoCommand valor = gson.fromJson(jsonArray1.get(i).toString(), SaldoCommand.class);
					disponible = valor.getSaldo();

				}
			}
			disponible = disponible == null ? Constantes.ZEROS_DOUBLE : disponible;
			Double limite = cliente.getLimiteCredito() == null ? Constantes.ZEROS_DOUBLE : cliente.getLimiteCredito();
			disponible = limite - disponible;
			disponible = disponible < 0 ? Constantes.ZEROS_DOUBLE : disponible;

		} catch (Exception e) {
			log.info("** Error  credito disponible cliente: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

		return disponible;
	}

}