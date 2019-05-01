package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

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
				if (compra.getFechaCompra().equals(Constantes.EMPTY)) {
					compra.setFechaCompra(new Date());
				} else {
					compra.setFechaCompra(Utils.pasarADate(compraCommand.getFechaCompra(), "yyyy-MM-dd"));
				}
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
					// compraDao.modificar(compra);
					if (compra.getEstado().equals(Constantes.COMPRA_ESTADO_INGRESADA_INVENTARIO)) {
						if (articulo.getContable().equals(Constantes.CONTABLE_SI)) {
							aplicarInventario(compra, detalleCompra, articulo);
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
			compra.setTotalCompra(Utils.roundFactura(montoTotalLinea, 2));
			compra.setTotalDescuento(Utils.roundFactura(totalDescuento, 2));
			compra.setTotalImpuesto(Utils.roundFactura(totalImpuesto, 2));
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
			ProveedorArticulo proveedorArticulo = proveedorArticuloDao.findByCodigo(detalleCompra.getArticulo(), proveedor);
			if (proveedorArticulo != null) {
				proveedorArticulo.setUpdated_at(new Date());
				proveedorArticulo.setCosto(detalleCompra.getCosto());
				proveedorArticuloDao.modificar(proveedorArticulo);

			} else {
				proveedorArticulo = new ProveedorArticulo();
				proveedorArticulo.setCreated_at(new Date());
				proveedorArticulo.setUpdated_at(new Date());
				proveedorArticulo.setArticulo(detalleCompra.getArticulo());
				proveedorArticulo.setCodigo(detalleCompra.getArticulo().getCodigo());
				proveedorArticulo.setCosto(detalleCompra.getCosto());
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
	@Transactional
	public void aplicarInventario(Compra compra, DetalleCompra detalleCompra, Articulo articulo) throws Exception {
		try {
			Double totalLinea = detalleCompra.getMontoTotalLinea() != null ? detalleCompra.getMontoTotalLinea() : Constantes.ZEROS_DOUBLE;
			totalLinea = totalLinea > 0 ? totalLinea / detalleCompra.getCantidad() : Constantes.ZEROS_DOUBLE;
			Double costo = totalLinea;
			Double costoPromedio =articuloDao.costoPromedio(articulo.getCosto(), costo, articulo.getCantidad(), detalleCompra.getCantidad()); 
			Double porcentajeGanancia =articuloDao.porcentanjeDeGanancia(articulo.getCosto(), articulo.getImpuesto(), detalleCompra.getPrecio());
			String leyenda = Constantes.MOTIVO_INGRESO_INVENTARIO_COMPRA + compra.getProveedor().getNombreCompleto();
			kardexDao.entrada(articulo, articulo.getCantidad(), detalleCompra.getCantidad(), compra.getNota(), compra.getConsecutivo(), Constantes.KARDEX_TIPO_ENTRADA, leyenda, compra.getUsuarioCreacion());
			articulo.setCosto(costoPromedio);
			articulo.setGananciaPrecioPublico(porcentajeGanancia);
			articulo.setUpdated_at(new Date());
			articulo.setUsuario(compra.getUsuarioCreacion());
			articulo.setPrecioPublico(detalleCompra.getPrecio());
			articuloDao.modificar(articulo);

		} catch (Exception e) {
			log.info("** Error  aplicarInventario: " + e.getMessage() + " fecha " + new Date());
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
	public TotalComprasAceptadasCommand sumarComprasAceptadas(Date fechaInicio, Date fechaFinal, Integer idEmpresa) {
		return compraDao.sumarComprasAceptadas(fechaInicio, fechaFinal, idEmpresa);
	}

	@Override
	@Transactional
	public void anularCompra(Compra compra) throws Exception {
		try {
			compra.setUpdated_at(new Date());
			compra.setEstado(Constantes.COMPRA_ESTADO_ANULADA);
			compraDao.modificar(compra);
			CuentaPagar cuentaPagar = cuentaPagarDao.findByConsecutivoAndEmpresa(compra.getConsecutivo(),compra.getEmpresa());
			if(cuentaPagar !=null) {
				cuentaPagarDao.eliminar(cuentaPagar);
			}
			Collection<DetalleCompra> detalleCompras = detalleCompraDao.findByCompra(compra);
			if (detalleCompras != null) {
				for (DetalleCompra detalleCompra : detalleCompras) {
					Articulo articulo = articuloDao.buscarPorCodigoYEmpresa(detalleCompra.getArticulo().getCodigo(), compra.getEmpresa());
					if (articulo != null) {
						disminuirInventario(articulo,compra,detalleCompra);
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

}