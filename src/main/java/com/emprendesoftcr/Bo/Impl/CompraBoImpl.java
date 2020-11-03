package com.emprendesoftcr.Bo.Impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.CompraBo;
import com.emprendesoftcr.Dao.ArticuloDao;
import com.emprendesoftcr.Dao.CompraDao;
import com.emprendesoftcr.Dao.CuentaPagarDao;
import com.emprendesoftcr.Dao.DetalleCompraDao;
import com.emprendesoftcr.Dao.KardexDao;
import com.emprendesoftcr.Dao.ProveedorArticuloDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.CuentaPagar;
import com.emprendesoftcr.modelo.DetalleCompra;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.ProveedorArticulo;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.CompraCommand;
import com.emprendesoftcr.web.command.DetalleCompraCommand;
import com.emprendesoftcr.web.command.TotalComprasAceptadasCommand;
import com.google.gson.Gson;

/**
 * Reglas de negocio para las compras hacia el inventario CompraBoImpl.
 * @author jose.
 * @since 27 may. 2018
 */

@EnableTransactionManagement
@Service("compraBo")
public class CompraBoImpl implements CompraBo {

	@Autowired
	private CompraDao							compraDao;

	@Autowired
	private DetalleCompraDao			detalleCompraDao;

	@Autowired
	private ArticuloDao						articuloDao;

	@Autowired
	private KardexDao							kardexDao;

	@Autowired
	private ProveedorArticuloDao	proveedorArticuloDao;

	@Autowired
	private CuentaPagarDao				cuentaPagarDao;

	private Logger								log	= LoggerFactory.getLogger(this.getClass());

	@Transactional
	@Override
	public void agregar(Compra compra) {
		compraDao.agregar(compra);

	}

	/**
	 * Se realiza la creacion de la compra con sus detalles de productos listos para ingresar al inventario
	 * @param compraCommand
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void crearCompra(CompraCommand compraCommand, Usuario usuario) throws Exception {
		try {
			Compra compra = compraCommand.getId() == null || compraCommand.getId() == Constantes.ZEROS_LONG ? new Compra() : compraDao.findById(compraCommand.getId());
			compra.setConsecutivo(compraCommand.getConsecutivo());
			compra.setEmpresa(compraCommand.getEmpresa());
			compra.setEstado(compraCommand.getEstado());
			compra.setFormaPago(compraCommand.getFormaPago());
			if (compra.getFechaCompra() != null) {
				compra.setFechaCompra(Utils.pasarADate(compraCommand.getFechaCompra(), "yyyy-MM-dd"));
			} else {
				compra.setFechaCompra(new Date());
			}

			if (compra.getFormaPago().equals(Constantes.COMPRA_FORMA_PAGO_CREDITO)) {
				compra.setFechaCredito(Utils.pasarADate(compraCommand.getFechaCredito(), "yyyy-MM-dd"));
			} else {
				compra.setFechaCredito(null);
			}
			compra.setNota(compraCommand.getNota());
			compra.setUsuarioCreacion(compraCommand.getUsuarioCreacion());
			compra.setTipoDocumento(compraCommand.getTipoDocumento());
			compra.setProveedor(compraCommand.getProveedor());

			if (compra.getEstado().equals(Constantes.COMPRA_ESTADO_INGRESADA_INVENTARIO)) {
				compra.setFechaIngreso(new Date());
				compra.setUpdated_at(new Date());
			}
			if (compraCommand.getId() != null) {
				if (compraCommand.getId() > 0) {
					compraDao.eliminarDetalleComprasPorSP(compra);
				}
			}
			if (compra.getId() == null) {
				compra.setCreated_at(new Date());
				compra.setUpdated_at(new Date());
				compra.setUsuarioCreacion(usuario);
				compra.setUsuarioIngresoInventario(usuario);
				agregar(compra);
			} else {
				compra.setCreated_at(new Date());
				compra.setUsuarioIngresoInventario(usuario);
				modificar(compra);
			}

			JSONObject json = null;
			try {
				json = (JSONObject) new JSONParser().parse(compraCommand.getDetalleCompra());
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}
			// Agregar Lineas de Detalle
			JSONArray jsonArrayDetalleCompra = (JSONArray) json.get("data");
			Gson gson = new Gson();
			Double montoTotalLinea = Constantes.ZEROS_DOUBLE;
			Double totalDescuento = Constantes.ZEROS_DOUBLE;
			Double totalImpuesto = Constantes.ZEROS_DOUBLE;
			if (jsonArrayDetalleCompra != null) {
				for (int i = 0; i < jsonArrayDetalleCompra.size(); i++) {
					DetalleCompraCommand detalleCompraCommand = gson.fromJson(jsonArrayDetalleCompra.get(i).toString(), DetalleCompraCommand.class);
					Articulo articulo = articuloDao.buscar(detalleCompraCommand.getArticulo_id());
					DetalleCompra detalleCompra = new DetalleCompra(detalleCompraCommand);
					detalleCompra.setArticulo(articulo);
					detalleCompra.setCompra(compra);
					detalleCompra.setCreated_at(new Date());
					detalleCompra.setUpdated_at(new Date());
					// detalleCompraDao.agregar(detalleCompra);
					detalleCompra.setCompra(compra);
					detalleCompraDao.agregar(detalleCompra);
					articulo.setConsecutivoCompra(compra.getConsecutivo());
					articulo.setFechaUltimaCompra(compra.getFechaCompra());
					articulo.setGananciaPrecioPublico(detalleCompra.getGanancia());
					articulo.setUpdated_at(new Date());

					articuloDao.modificar(articulo);
					// compraDao.modificar(compra);
					if (compra.getEstado().equals(Constantes.COMPRA_ESTADO_INGRESADA_INVENTARIO)) {
						if (articulo.getContable().equals(Constantes.CONTABLE_SI)) {
							aplicarInventario(compra, detalleCompra, articulo);
						} else {
							articulo.setCosto(detalleCompra.getCosto());
							articulo.setPrecioPublico(detalleCompra.getPrecio());
							articuloDao.modificar(articulo);

						}

						actualizarProveedor(detalleCompra, compra.getProveedor());
					}

					if (detalleCompra.getMontoTotalLinea() != null) {
						montoTotalLinea = detalleCompra.getMontoTotalLinea() + montoTotalLinea;
					}
					if (detalleCompra.getTotalDescuento() != null) {
						totalDescuento = detalleCompra.getTotalDescuento() + totalDescuento;
					}
					if (detalleCompra.getTotalImpuesto() != null) {
						totalImpuesto = totalImpuesto + detalleCompra.getTotalImpuesto();
					}
				}
			}
			compra.setTotalCompra(montoTotalLinea != null && montoTotalLinea > Constantes.ZEROS_DOUBLE ? Utils.roundFactura(montoTotalLinea, 2) : Constantes.ZEROS_DOUBLE);
			compra.setTotalDescuento(totalDescuento != null && totalDescuento > Constantes.ZEROS_DOUBLE ? Utils.roundFactura(totalDescuento, 2) : Constantes.ZEROS_DOUBLE);
			compra.setTotalImpuesto(Utils.roundFactura(totalImpuesto != null && totalImpuesto > Constantes.ZEROS_DOUBLE ? totalImpuesto : Constantes.ZEROS_DOUBLE, 2));
			compraDao.modificar(compra);

			// Crear Credito del cliente
			if (compra.getEstado().equals(Constantes.COMPRA_ESTADO_INGRESADA_INVENTARIO)) {
				if (compra.getFormaPago().equals(Constantes.COMPRA_FORMA_PAGO_CREDITO)) {
					CuentaPagar cuentaPagar = new CuentaPagar();
					cuentaPagar.setConsecutivo(compra.getConsecutivo());
					cuentaPagar.setCreated_at(new Date());
					cuentaPagar.setUpdated_at(new Date());
					cuentaPagar.setEmpresa(compra.getEmpresa());
					cuentaPagar.setTotal(Utils.roundFactura(compra.getTotalCompra(), 2));
					cuentaPagar.setFechaCredito(compra.getFechaCredito());
					cuentaPagar.setTotalSaldo(Utils.roundFactura(compra.getTotalCompra(), 2));
					cuentaPagar.setProveedor(compra.getProveedor());
					cuentaPagar.setTotalAbono(Constantes.ZEROS_DOUBLE);
					cuentaPagar.setUsuarioCreacion(usuario);
					cuentaPagar.setEstado(Constantes.CUENTA_POR_PAGAR_ESTADO_PENDIENTE);
					cuentaPagarDao.agregar(cuentaPagar);

				}
			}
		} catch (Exception e) {
			log.info("** Error  crearCompra: " + e.getMessage() + " fecha " + new Date() + "  Compra:" + compraCommand.getConsecutivo());

			throw e;
		}

	}

	/**
	 * Lista de un proveedor
	 * @param detalleCompra
	 * @param articulo
	 */
	private void actualizarProveedor(DetalleCompra detalleCompra, Proveedor proveedor) {
		try {
			Double cantidad = detalleCompra.getCantidad() != null && detalleCompra.getCantidad()>Constantes.ZEROS_DOUBLE ? detalleCompra.getCantidad():Constantes.ZEROS_DOUBLE;
			Double costo  = detalleCompra.getCosto() != null && detalleCompra.getCosto() >Constantes.ZEROS_DOUBLE ? detalleCompra.getCosto():Constantes.ZEROS_DOUBLE;
			Double totalLinea = costo!= null ? costo : Constantes.ZEROS_DOUBLE;
			Double totalDescuento = detalleCompra.getTotalDescuento() != null && detalleCompra.getTotalDescuento() > Constantes.ZEROS_DOUBLE?detalleCompra.getTotalDescuento():Constantes.ZEROS_DOUBLE;
			Double descuento = totalDescuento > Constantes.ZEROS_DOUBLE  ? totalDescuento / cantidad:Constantes.ZEROS_DOUBLE;
			Double costoNuevo = descuento > Constantes.ZEROS_DOUBLE ?totalLinea - descuento:totalLinea;
			ProveedorArticulo proveedorArticulo = proveedorArticuloDao.findByCodigo(detalleCompra.getArticulo(), proveedor);
			if (proveedorArticulo != null) {
				proveedorArticulo.setUpdated_at(new Date());
				proveedorArticulo.setCosto(costoNuevo);
				proveedorArticuloDao.modificar(proveedorArticulo);

			} else {
				ProveedorArticulo	proveedorArticuloNuevo = new ProveedorArticulo();
				proveedorArticuloNuevo.setId(null);
				proveedorArticuloNuevo.setCreated_at(new Date());
				proveedorArticuloNuevo.setUpdated_at(new Date());
				proveedorArticuloNuevo.setArticulo(detalleCompra.getArticulo());
				proveedorArticuloNuevo.setCodigo(detalleCompra.getArticulo().getCodigo());
				proveedorArticuloNuevo.setCosto(costoNuevo);
				proveedorArticuloNuevo.setProveedor(proveedor);
				proveedorArticuloDao.agregar(proveedorArticuloNuevo);
			}

		} catch (Exception e) {
			log.info("** Error  actualizarProveedor: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	/**
	 * Aplicar el inventario si estado de la compras es ingresar al inventario
	 * @param compra
	 * @param inventario
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void aplicarInventario(Compra compra, DetalleCompra detalleCompra, Articulo articulo) throws Exception {
		try {
			Double cantidad = detalleCompra.getCantidad() != null && detalleCompra.getCantidad() > Constantes.ZEROS_DOUBLE ? detalleCompra.getCantidad() : Constantes.ZEROS_DOUBLE;
			Double totalLinea = detalleCompra.getCosto() != null ? detalleCompra.getCosto() : Constantes.ZEROS_DOUBLE;
			Double descuento = detalleCompra.getTotalDescuento() == null && detalleCompra.getTotalDescuento() > Constantes.ZEROS_DOUBLE ? detalleCompra.getTotalDescuento() / cantidad : Constantes.ZEROS_DOUBLE;
			Double costo = descuento > Constantes.ZEROS_DOUBLE ? totalLinea - descuento : totalLinea;
			String leyenda = Constantes.MOTIVO_INGRESO_INVENTARIO_COMPRA + compra.getConsecutivo();
			kardexDao.entradaCosto(articulo, costo, cantidad, compra.getNota(), compra.getConsecutivo(), Constantes.KARDEX_TIPO_ENTRADA, leyenda, compra.getUsuarioCreacion());
			Double porcentajeGanancia = articuloDao.porcentanjeDeGanancia(articulo.getCosto(), articulo.getImpuesto(), detalleCompra.getPrecio());
			articulo.setGananciaPrecioPublico(porcentajeGanancia);
			articulo.setUpdated_at(new Date());
			articulo.setUsuario(compra.getUsuarioCreacion());
			articulo.setPrecioPublico(detalleCompra.getPrecio());
			articuloDao.modificar(articulo);

		} catch (Exception e) {
			log.info("** Error  aplicarInventario: " + e.getMessage() + " fecha " + new Date() + " Codigo " + articulo.getCodigo());
			throw e;

		}

	}

	public Collection<Compra> findByFechaInicioAndFechaFinalAndProveedor(Date fechaInicio, Date fechaFin, Empresa empresa, Proveedor proveedor) {
		return compraDao.findByFechaInicioAndFechaFinalAndProveedor(fechaInicio, fechaFin, empresa, proveedor);
	}

	/**
	 * Modificar una compra
	 * @see com.emprendesoftcr.Bo.CompraBo#modificar(com.emprendesoftcr.modelo.Compra)
	 */
	@Override
	@Transactional
	public void modificar(Compra compra) {
		compraDao.modificar(compra);
	}

	/**
	 * Eliminar una compra
	 * @see com.emprendesoftcr.Bo.CompraBo#eliminar(com.emprendesoftcr.modelo.Compra)
	 */
	@Override
	@Transactional
	public void eliminar(Compra compra) {
		compraDao.eliminar(compra);
	}

	/**
	 * Buscar por id
	 * @see com.emprendesoftcr.Bo.CompraBo#findById(java.lang.Integer)
	 */
	@Override
	public Compra findById(Long id) {
		return compraDao.findById(id);
	}

	/**
	 * Buscar por consecutivo y empresa
	 * @see com.emprendesoftcr.Bo.CompraBo#findByConsecutivoAndEmpresa(java.lang.String, com.emprendesoftcr.modelo.Empresa)
	 */
	@Override
	public Compra findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) {
		return compraDao.findByConsecutivoAndEmpresa(consecutivo, empresa);
	}

	/**
	 * Elimina los detalles de una compra
	 * @see com.emprendesoftcr.Bo.CompraBo#eliminarDetalleComprasPorSP(com.emprendesoftcr.modelo.Compra)
	 */
	@Override
	@Transactional
	public void eliminarDetalleComprasPorSP(Compra compra) throws Exception {
		compraDao.eliminarDetalleComprasPorSP(compra);
	}

	@Override
	public TotalComprasAceptadasCommand sumarComprasAceptadas(Date fechaInicio, Date fechaFinal, Integer idEmpresa, Integer estado, String actividadEconocimica, String tipoGasto) {
		return compraDao.sumarComprasAceptadas(fechaInicio, fechaFinal, idEmpresa, estado, actividadEconocimica, tipoGasto);
	}

	@Override
	@Transactional
	public void anularCompra(Compra compra) throws Exception {
		try {
			compra.setUpdated_at(new Date());
			compra.setEstado(Constantes.COMPRA_ESTADO_ANULADA);
			compraDao.modificar(compra);
			CuentaPagar cuentaPagar = cuentaPagarDao.findByConsecutivoAndEmpresa(compra.getConsecutivo(), compra.getEmpresa());
			if (cuentaPagar != null) {
				cuentaPagarDao.eliminar(cuentaPagar);
			}
			Collection<DetalleCompra> detalleCompras = detalleCompraDao.findByCompra(compra);
			if (detalleCompras != null) {
				for (DetalleCompra detalleCompra : detalleCompras) {
					Articulo articulo = articuloDao.buscarPorCodigoYEmpresa(detalleCompra.getArticulo().getCodigo(), compra.getEmpresa());
					if (articulo != null) {
						disminuirInventario(articulo, compra, detalleCompra);
					}
				}
			}

		} catch (Exception e) {
			log.info("** Error  anular Compra: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	private void disminuirInventario(Articulo articulo, Compra compra, DetalleCompra detalleCompra) throws Exception {
		try {
			if (articulo.getContable() != null) {
				if (articulo.getContable().equals(Constantes.CONTABLE_SI)) {
					String leyenda = Constantes.MOTIVO_SALIDA_INVENTARIO_COMPRA_ANULACION + compra.getConsecutivo();
					kardexDao.salida(articulo, articulo.getCantidad(), detalleCompra.getCantidad(), Constantes.EMPTY, compra.getConsecutivo().toString(), Constantes.KARDEX_TIPO_SALIDA, leyenda, compra.getUsuarioIngresoInventario());
				}
			}

		} catch (Exception e) {
			log.info("** Error  anular Compra: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	@Override
	public ByteArrayInputStream createExcelCompras(Collection<Compra> compras, Empresa empresa, String fechaInicio, String fechaFinal, Proveedor proveedor) throws IOException {
		List<String> headers = Arrays.asList("Id", "Fecha Compra", "Fecha Credito", "Fecha Ingreso", "# Documento Receptor", "Proveedor", "Total Impuestos", "Total", "usuario");
		// Libro excel
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Map<String, CellStyle> styles = Utils.createStyles(workbook);
		Sheet sheet = workbook.createSheet("Compras");
		// title row
		Row title = sheet.createRow(0);
		title.setHeightInPoints(25);
		Cell titleCell = title.createCell(0);
		titleCell.setCellValue("Resumen de Compras del " + fechaInicio + " al " + fechaFinal);
		titleCell.setCellStyle(styles.get("title1"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$H$2"));

		Row titleEmpresa = sheet.createRow(1);
		titleEmpresa.setHeightInPoints(25);
		Cell titleCell1 = titleEmpresa.createCell(0);
		titleCell1.setCellValue(empresa.getNombre());
		titleCell1.setCellStyle(styles.get("title1"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$H$3"));

		Row titleCedula = sheet.createRow(2);
		titleCedula.setHeightInPoints(25);
		Cell titleCell2 = titleCedula.createCell(0);
		titleCell2.setCellValue("Cedula:" + empresa.getCedula());
		titleCell2.setCellStyle(styles.get("title1"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$H$4"));

		Row row = sheet.createRow(4);
		row.setHeightInPoints(25);
		Cell headerCell;
		for (int i = 0; i < headers.size(); i++) {
			headerCell = row.createCell(i);
			headerCell.setCellValue(headers.get(i));
			headerCell.setCellStyle(styles.get("header"));

		}
		int rownum = 5;
		for (Compra compra : compras) {
			row = sheet.createRow(rownum);
			Cell cell = row.createCell(0);
			Utils.getCelSTR(cell, styles, compra.getId() + "");

			cell = row.createCell(1);
			Utils.getCelSTR(cell, styles, compra.getFechaCompraSTR());

			cell = row.createCell(2);
			Utils.getCelSTR(cell, styles, compra.getFechaCreditoSTR());

			cell = row.createCell(3);
			Utils.getCelSTR(cell, styles, compra.getFechaIngresoSTR());

			cell = row.createCell(4);
			Utils.getCelSTR(cell, styles, compra.getConsecutivo());

			cell = row.createCell(5);
			Utils.getCelSTR(cell, styles, compra.getProveedorSTR());

			cell = row.createCell(6);
			Utils.getCel(cell, styles, compra.getTotalImpuesto());

			cell = row.createCell(7);
			Utils.getCel(cell, styles, compra.getTotalCompra());

			cell = row.createCell(8);
			Utils.getCelSTR(cell, styles, compra.getUsuarioIngresoInventario().getNombre());
			rownum++;
		}
		int contnum = rownum;
		Row sumRow = sheet.createRow(rownum++);
		for (int j = 0; j <= headers.size(); j++) {
			Cell cell = sumRow.createCell(j);
			if (j == 5) {
				cell.setCellValue("Totales  :");
				cell.setCellStyle(styles.get("formula"));
			}
			if (j <= 6 || j == 8) {
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 8000);
			}
			if (j == 6) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "G" + 6 + ":G" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 8000);
			}
			if (j == 7) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "H" + 6 + ":H" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 8000);
			}

		}

		workbook.write(stream);
		workbook.close();
		return new ByteArrayInputStream(stream.toByteArray());
	}

	@Override
	public ByteArrayInputStream createExcelRecepcionCompra(Collection<RecepcionFactura> lista, String fechaInicio, String fechaFin, Empresa empresa) throws Exception {
		List<String> headers = Arrays.asList("Actividad Economica", "Estado Hacienda", "Aceptacion Receptor", "Fecha Ingreso", "Fecha Emision", "Clave", "# Documento Receptor", "Cedula Emisor", "Nombre Emisor", "Correo", "Telefono", "# Compra", "Tipo Moneda", "Tipo Cambio", "Total Impuesto(total impuesto X tipoCambio)", "Total(total X tipoCambio)", "Tipo Documento", "Tipo de Gasto");

		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Map<String, CellStyle> styles = Utils.createStyles(workbook);
		Sheet sheet = workbook.createSheet("Compras");
		// title row
		Row title = sheet.createRow(0);
		title.setHeightInPoints(25);
		Cell titleCell = title.createCell(0);
		titleCell.setCellValue("Compras Resumen " + fechaInicio + " al " + fechaFin);
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
		for (RecepcionFactura recepcionFactura : lista) {
			row = sheet.createRow(rownum);
			// Codigo Actividad
			Cell cell = row.createCell(0);
			Utils.getCelSTR(cell, styles, recepcionFactura.getCodigoActividad());
			// Estado
			cell = row.createCell(1);
			Utils.getCelSTR(cell, styles, recepcionFactura.getEstadoSTR());
			// Aceptacion Receptor
			cell = row.createCell(2);
			Utils.getCelSTR(cell, styles, recepcionFactura.getMensajeSTR());

			// Fecha Crecion
			cell = row.createCell(3);
			Utils.getCelSTR(cell, styles, recepcionFactura.getCreated_atSTR());
			// Fecha Emision
			cell = row.createCell(4);
			Utils.getCelSTR(cell, styles, recepcionFactura.getFechaEmisionSTR());
			// Clave
			cell = row.createCell(5);
			Utils.getCelSTR(cell, styles, recepcionFactura.getFacturaClave());
			// Consecutivo receptor
			cell = row.createCell(6);
			Utils.getCelSTR(cell, styles, recepcionFactura.getNumeroConsecutivoReceptor());
			// Cedula
			cell = row.createCell(7);
			Utils.getCelSTR(cell, styles, recepcionFactura.getEmisorCedula());
			// Nombre Emisor
			cell = row.createCell(8);
			Utils.getCelSTR(cell, styles, recepcionFactura.getEmisorNombre());
			// Emisor Correo
			cell = row.createCell(9);
			Utils.getCelSTR(cell, styles, recepcionFactura.getEmisorCorreo());
			// Emisor telefono
			cell = row.createCell(10);
			Utils.getCelSTR(cell, styles, recepcionFactura.getEmisorTelefono());
			// Compra
			cell = row.createCell(11);
			Utils.getCelSTR(cell, styles, recepcionFactura.getFacturaConsecutivo());

			// CodigoMoneda
			cell = row.createCell(12);
			Utils.getCelSTR(cell, styles, recepcionFactura.getFacturaCodigoMoneda());

			// Tipo Cambio
			cell = row.createCell(13);
			Utils.getCel(cell, styles, recepcionFactura.getFacturaTipoCambio());

			// Total Impuesto * tipo cambio
			cell = row.createCell(14);
			Utils.getCel(cell, styles, recepcionFactura.getTotalImpuestosSTRTipoCambio());
			// Total Facturas * tipo Cambio
			cell = row.createCell(15);
			Utils.getCel(cell, styles, recepcionFactura.getTotalFacturaSTRTipoCambio());

			cell = row.createCell(16);
			Utils.getCelSTR(cell, styles, recepcionFactura.getTipoDocumentoStr());
			cell = row.createCell(17);
			Utils.getCelSTR(cell, styles, recepcionFactura.getTipoGastoStr());
			rownum++;

		}
		int contnum = rownum;
		Row sumRow = sheet.createRow(rownum++);

		for (int j = 0; j < 18; j++) {
			Cell cell = sumRow.createCell(j);
			if (j == 13) {
				cell.setCellValue("Totales  :");
				cell.setCellStyle(styles.get("formula"));

			}
			if (j <= 12 || j == 16 || j == 17) {
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

			sheet.setColumnWidth(j, 8500);
		}

		workbook.write(stream);
		workbook.close();
		return new ByteArrayInputStream(stream.toByteArray());
	}

}