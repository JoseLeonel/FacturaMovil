package com.emprendesoftcr.Bo.Impl;

import java.util.Date;

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
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.CompraCommand;
import com.emprendesoftcr.web.command.DetalleCompraCommand;
import com.google.gson.Gson;

/**
 * Reglas de negocio para las compras hacia el inventario CompraBoImpl.
 * @author jose.
 * @since 27 may. 2018
 */
@Transactional
@EnableTransactionManagement
@Service("compraBo")
public class CompraBoImpl implements CompraBo {

	@Autowired
	private CompraDao							compraDao;

	@Autowired
	private ArticuloDao						articuloDao;

	@Autowired
	private KardexDao							kardexDao;

	@Autowired
	private ProveedorArticuloDao	proveedorArticuloDao;

	@Autowired
	private CuentaPagarDao				cuentaPagarDao;

	private Logger								log	= LoggerFactory.getLogger(this.getClass());

	public void agregar(Compra compra) {
		compraDao.agregar(compra);

	}

	/**
	 * Se realiza la creacion de la compra con sus detalles de productos listos para ingresar al inventario
	 * @param compraCommand
	 * @throws Exception
	 */
	@Override
	public void crearCompra(CompraCommand compraCommand, Usuario usuario) throws Exception {
		try {
			Compra compra = compraCommand.getId() == null || compraCommand.getId() == Constantes.ZEROS_LONG ? new Compra() : compraDao.findById(compraCommand.getId());
			compra.setConsecutivo(compraCommand.getConsecutivo());
			compra.setEmpresa(compraCommand.getEmpresa());
			compra.setEstado(compraCommand.getEstado());
			compra.setFormaPago(compraCommand.getFormaPago());
			compra.setFechaCompra(Utils.pasarADate(compraCommand.getFechaCompra(), "yyyy-MM-dd"));
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
			Double subTotal = Constantes.ZEROS_DOUBLE;
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
					compra.addDetalleCompra(detalleCompra);
					// compraDao.modificar(compra);
					if (compra.getEstado().equals(Constantes.COMPRA_ESTADO_INGRESADA_INVENTARIO)) {
						aplicarInventario(compra, detalleCompra, articulo);
						actualizarProveedor(detalleCompra, articulo, compra.getProveedor());
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
			subTotal = montoTotalLinea - totalDescuento;
			subTotal = montoTotalLinea - totalImpuesto;
			compra.setTotalCompra(Utils.roundFactura(montoTotalLinea, 5));
			compra.setTotalDescuento(Utils.roundFactura(totalDescuento, 5));
			compra.setTotalImpuesto(Utils.roundFactura(totalImpuesto, 5));
			compra.setSubTotal(Utils.roundFactura(subTotal, 5));
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
			// Crear Credito del cliente
			if (compra.getEstado().equals(Constantes.COMPRA_ESTADO_INGRESADA_INVENTARIO)) {
				if (compra.getFormaPago().equals(Constantes.COMPRA_FORMA_PAGO_CREDITO)) {
					CuentaPagar cuentaPagar = new CuentaPagar();
					cuentaPagar.setConsecutivo(compra.getConsecutivo());
					cuentaPagar.setCreated_at(new Date());
					cuentaPagar.setUpdated_at(new Date());
					cuentaPagar.setEmpresa(compra.getEmpresa());
					cuentaPagar.setTotal(compra.getTotalCompra());
					cuentaPagar.setFechaCredito(compra.getFechaCredito());
					cuentaPagar.setTotalSaldo(compra.getTotalCompra());
					cuentaPagar.setProveedor(compra.getProveedor());
					cuentaPagar.setTotalAbono(Constantes.ZEROS_DOUBLE);
					cuentaPagar.setUsuarioCreacion(usuario);
					cuentaPagar.setEstado(Constantes.CUENTA_POR_PAGAR_ESTADO_PENDIENTE);
					cuentaPagarDao.agregar(cuentaPagar);

				}
			}
		} catch (Exception e) {
			log.info("** Error  crearCompra: " + e.getMessage() + " fecha " + new Date());

			throw e;
		}

	}

	/**
	 * Lista de un proveedor
	 * @param detalleCompra
	 * @param articulo
	 */
	private void actualizarProveedor(DetalleCompra detalleCompra, Articulo articulo, Proveedor proveedor) {
		try {
			ProveedorArticulo proveedorArticulo = proveedorArticuloDao.findByCodigo(articulo.getCodigo(), proveedor);
			if (proveedorArticulo != null) {
				proveedorArticulo.setUpdated_at(new Date());
				proveedorArticulo.setCosto(detalleCompra.getCosto());
				proveedorArticuloDao.modificar(proveedorArticulo);

			} else {
				proveedorArticulo = new ProveedorArticulo();
				proveedorArticulo.setCreated_at(new Date());
				proveedorArticulo.setUpdated_at(new Date());
				proveedorArticulo.setArticulo(articulo);
				proveedorArticulo.setCodigo(detalleCompra.getArticulo().getCodigo());
				proveedorArticulo.setProveedor(proveedor);
				proveedorArticuloDao.agregar(proveedorArticulo);
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
	public void aplicarInventario(Compra compra, DetalleCompra detalleCompra, Articulo articulo) throws Exception {
		try {
			Double cantidadTotal = Utils.roundFactura(articulo.getCantidad() + detalleCompra.getCantidad(), 5);
			String leyenda = Constantes.MOTIVO_INGRESO_INVENTARIO_COMPRA + compra.getProveedor().getNombreCompleto();
			kardexDao.entrada(articulo, articulo.getCantidad(), detalleCompra.getCantidad(), compra.getNota(), compra.getConsecutivo(), Constantes.KARDEX_TIPO_ENTRADA, leyenda, compra.getUsuarioCreacion());
			articulo.setCosto(articuloDao.costoPromedio(articulo.getCosto(), detalleCompra.getCosto(), articulo.getCantidad(), detalleCompra.getCantidad()));
			articulo.setGananciaPrecioPublico(articuloDao.porcentanjeDeGanancia(articulo.getCosto(), articulo.getImpuesto(), detalleCompra.getPrecio()));
			articulo.setUpdated_at(new Date());
			articulo.setUsuario(compra.getUsuarioCreacion());
			articulo.setCantidad(cantidadTotal);
			articulo.setPrecioPublico(Utils.roundFactura(detalleCompra.getPrecio(), 5));
			articuloDao.modificar(articulo);

		} catch (Exception e) {
			log.info("** Error  aplicarInventario: " + e.getMessage() + " fecha " + new Date());
			throw e;

		}

	}

	/**
	 * Modificar una compra
	 * @see com.emprendesoftcr.Bo.CompraBo#modificar(com.emprendesoftcr.modelo.Compra)
	 */
	@Override
	public void modificar(Compra compra) {
		compraDao.modificar(compra);
	}

	/**
	 * Eliminar una compra
	 * @see com.emprendesoftcr.Bo.CompraBo#eliminar(com.emprendesoftcr.modelo.Compra)
	 */
	@Override
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
	public void eliminarDetalleComprasPorSP(Compra compra) throws Exception {
		compraDao.eliminarDetalleComprasPorSP(compra);
	}

}