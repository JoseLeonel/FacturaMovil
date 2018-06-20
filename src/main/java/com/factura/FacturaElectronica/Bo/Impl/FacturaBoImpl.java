package com.factura.FacturaElectronica.Bo.Impl;

import java.math.BigDecimal;
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

import com.factura.FacturaElectronica.Bo.FacturaBo;
import com.factura.FacturaElectronica.Dao.ArticuloDao;
import com.factura.FacturaElectronica.Dao.CuentaCobrarDao;
import com.factura.FacturaElectronica.Dao.DetalleDao;
import com.factura.FacturaElectronica.Dao.EmpresaDao;
import com.factura.FacturaElectronica.Dao.FacturaDao;
import com.factura.FacturaElectronica.Dao.InventarioDao;
import com.factura.FacturaElectronica.Dao.KardexDao;
import com.factura.FacturaElectronica.Dao.UsuarioCajaDao;
import com.factura.FacturaElectronica.Dao.UsuarioCajaFacturaDao;
import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.Utils.Utils;
import com.factura.FacturaElectronica.modelo.Articulo;
import com.factura.FacturaElectronica.modelo.Detalle;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Factura;
import com.factura.FacturaElectronica.modelo.Inventario;
import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.modelo.UsuarioCaja;
import com.factura.FacturaElectronica.modelo.UsuarioCajaFactura;
import com.factura.FacturaElectronica.web.command.DetalleFacturaCommand;
import com.factura.FacturaElectronica.web.command.FacturaCommand;
import com.google.gson.Gson;

@Transactional
@EnableTransactionManagement
@Service("facturaBo")
public class FacturaBoImpl implements FacturaBo {

	@Autowired
	FacturaDao						facturaDao;

	@Autowired
	DetalleDao						detalleDao;

	@Autowired
	EmpresaDao						empresaDao;

	@Autowired
	ArticuloDao						articuloDao;

	@Autowired
	KardexDao							kardexDao;

	@Autowired
	CuentaCobrarDao				cuentaCobrarDao;

	@Autowired
	UsuarioCajaFacturaDao	usuarioCajaFacturaDao;

	@Autowired
	UsuarioCajaDao				usuarioCajaDao;

	@Autowired
	InventarioDao					inventarioDao;

	private Logger				log	= LoggerFactory.getLogger(this.getClass());

	public void agregar(Factura factura) {
		facturaDao.agregar(factura);

	}

	/**
	 * Modificar una factura
	 * @see com.factura.FacturaElectronica.Bo.FacturaBo#modificar(com.factura.FacturaElectronica.modelo.Factura)
	 */
	@Override
	public void modificar(Factura factura) {
		facturaDao.modificar(factura);
	}

	/**
	 * Eliminar una factura
	 * @see com.factura.FacturaElectronica.Bo.FacturaBo#eliminar(com.factura.FacturaElectronica.modelo.Factura)
	 */
	@Override
	public void eliminar(Factura factura) {
		facturaDao.eliminar(factura);
	}

	/**
	 * Buscar por id
	 * @see com.factura.FacturaElectronica.Bo.FacturaBo#findById(java.lang.Integer)
	 */
	@Override
	public Factura findById(Integer id) {
		return facturaDao.findById(id);
	}

	/**
	 * Buscar por consecutivo y empresa
	 * @see com.factura.FacturaElectronica.Bo.FacturaBo#findByConsecutivoAndEmpresa(java.lang.String, com.factura.FacturaElectronica.modelo.Empresa)
	 */
	@Override
	public Factura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) {
		return facturaDao.findByConsecutivoAndEmpresa(consecutivo, empresa);
	}

	/**
	 * Crear la factura o el tiquete temporal
	 * @see com.factura.FacturaElectronica.Bo.FacturaBo#crearFactura(com.factura.FacturaElectronica.web.command.FacturaCommand, com.factura.FacturaElectronica.modelo.Usuario)
	 */
	@Override
	public Factura crearFactura(FacturaCommand facturaCommand, Usuario usuario, UsuarioCaja usuarioCaja) throws Exception {
		Factura factura = null;
		try {
			factura = facturaCommand.getId() == null || facturaCommand.getId() == Constantes.ZEROS ? new Factura() : facturaDao.findById(facturaCommand.getId());
			factura.setCondicionVenta(facturaCommand.getCondicionVenta());
			// Fecha de credito
			if (facturaCommand.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
				if (facturaCommand.getFechaCredito() != null) {
					factura.setFechaCredito(Utils.pasarADate(facturaCommand.getFechaCredito(), "yyyy-MM-dd"));
					factura.setPlazoCredito(facturaCommand.getPlazoCredito());

				}
			} else {
				factura.setFechaCredito(null);
				factura.setPlazoCredito(Constantes.ZEROS);
			}

			factura.setUsuarioCreacion(usuario);
			factura.setEmpresa(usuario.getEmpresa());
			factura.setVendedor(facturaCommand.getVendedor());
			factura.setCliente(facturaCommand.getCliente());
			factura.setFechaEmision(new Date());

			factura.setTipoDoc(facturaCommand.getTipoDoc());
			factura.setMedioPago(facturaCommand.getMedioPago());
			factura.setNombreFactura(facturaCommand.getNombreFactura());
			factura.setDireccion(facturaCommand.getDireccion());
			factura.setNota(facturaCommand.getNota());
			factura.setComanda(facturaCommand.getComanda());
			factura.setSubTotal(facturaCommand.getSubTotal() == null ? BigDecimal.ZERO : facturaCommand.getSubTotal());
			factura.setTotalTransporte(facturaCommand.getTotalTransporte() == null ? BigDecimal.ZERO : facturaCommand.getTotalTransporte());
			factura.setTotal(facturaCommand.getTotal() == null ? BigDecimal.ZERO : facturaCommand.getTotal());
			factura.setTotalServGravados(facturaCommand.getTotalServGravados() == null ? BigDecimal.ZERO : facturaCommand.getTotalServGravados());
			factura.setTotalServExentos(facturaCommand.getTotalServExentos() == null ? BigDecimal.ZERO : facturaCommand.getTotalServExentos());
			factura.setTotalMercanciasExentas(factura.getTotalMercanciasExentas() == null ? BigDecimal.ZERO : factura.getTotalMercanciasExentas());
			factura.setTotalMercanciasGravadas(facturaCommand.getTotalMercanciasGravadas() == null ? BigDecimal.ZERO : facturaCommand.getTotalMercanciasGravadas());
			factura.setTotalGravado(facturaCommand.getTotalGravado() == null ? BigDecimal.ZERO : facturaCommand.getTotalGravado());
			factura.setTotalExento(facturaCommand.getTotalExento() == null ? BigDecimal.ZERO : facturaCommand.getTotalExento());
			factura.setTotalVenta(facturaCommand.getTotalVenta() == null ? BigDecimal.ZERO : facturaCommand.getTotalVenta());
			factura.setTotalDescuentos(facturaCommand.getTotalDescuentos() == null ? BigDecimal.ZERO : facturaCommand.getTotalDescuentos());
			factura.setTotalVentaNeta(facturaCommand.getTotalVentaNeta() == null ? BigDecimal.ZERO : facturaCommand.getTotalVentaNeta());
			factura.setTotalImpuesto(facturaCommand.getTotalImpuesto() == null ? BigDecimal.ZERO : facturaCommand.getTotalImpuesto());
			factura.setTotalComprobante(facturaCommand.getTotalComprobante() == null ? BigDecimal.ZERO : facturaCommand.getTotalComprobante());
			factura.setTotalEfectivo(getTotalEfectivo(facturaCommand));
			factura.setTotalTarjeta(facturaCommand.getTotalTarjeta() == null ? BigDecimal.ZERO : facturaCommand.getTotalTarjeta());
			factura.setTotalBanco(facturaCommand.getTotalBanco() == null ? BigDecimal.ZERO : facturaCommand.getTotalBanco());
			factura.setTotalCredito(facturaCommand.getTotalCredito() == null ? BigDecimal.ZERO : facturaCommand.getTotalCredito());
			factura.setMontoCambio(facturaCommand.getMontoCambio() == null ? BigDecimal.ZERO : facturaCommand.getMontoCambio());
			factura.setTotalCambio(facturaCommand.getTotalCambio() == null ? BigDecimal.ZERO : facturaCommand.getTotalCambio());
			factura.setTotalCambioPagar(facturaCommand.getTotalCambioPagar() == null ? BigDecimal.ZERO : facturaCommand.getTotalCambioPagar());
			factura.setCodigoMoneda(Constantes.CODIGO_MONEDA_COSTA_RICA);
			factura.setEstado(facturaCommand.getEstado());
			if (factura.getId() == Constantes.ZEROS) {
				factura.setCreated_at(new Date());
			}

			if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
				factura.setFechaEmision(new Date());
				factura.setUpdated_at(new Date());

			}

			// Generar el consecutivo de venta
			if (facturaCommand.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
				factura.setNumeroConsecutivo(empresaDao.generarConsecutivoFactura(facturaCommand.getEmpresa(), usuario, factura));
			}

			if (factura.getId() == null) {
				factura.setCreated_at(new Date());
				agregar(factura);
			} else {
				modificar(factura);
			}

			// Eliminar detalles si existe
			if (facturaCommand.getId() != null) {
				if (facturaCommand.getId() > 0) {
					facturaDao.eliminarDetalleFacturaPorSP(factura);
				}
			}
			// Se asocia a la caja si la factura pasa de estado pendiente a facturado
			if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
				UsuarioCajaFactura usuarioCajaFactura = new UsuarioCajaFactura();
				usuarioCajaFactura.setCreated_at(new Date());
				usuarioCajaFactura.setUpdated_at(new Date());
				usuarioCajaFactura.setFactura(factura);
				usuarioCajaFactura.setUsuarioCaja(usuarioCaja);
				usuarioCajaFacturaDao.agregar(usuarioCajaFactura);
				usuarioCajaDao.actualizarCaja(usuarioCaja ,factura.getTotalEfectivo(),factura.getTotalTarjeta(),factura.getTotalBanco() ,factura.getTotalCredito(),BigDecimal.ZERO);
			}

			JSONObject json = null;
			try {
				json = (JSONObject) new JSONParser().parse(facturaCommand.getDetalleFactura());
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}
			// Agregar Lineas de Detalle
			JSONArray jsonArrayDetalleFactura = (JSONArray) json.get("data");
			Gson gson = new Gson();
			if (jsonArrayDetalleFactura != null) {
				for (int i = 0; i < jsonArrayDetalleFactura.size(); i++) {
					DetalleFacturaCommand detalleFacturaCommand = gson.fromJson(jsonArrayDetalleFactura.get(i).toString(), DetalleFacturaCommand.class);
					Articulo articulo = articuloDao.buscar(detalleFacturaCommand.getArticulo_id());
					Detalle detalle = new Detalle(detalleFacturaCommand);
					detalle.setUsuario(usuario);
					detalle.setArticulo(articulo);

					detalle.setFactura(factura);
					detalleDao.agregar(detalle);

					Inventario inventario = inventarioDao.findByArticuloAndEstado(detalle.getArticulo(), Constantes.ESTADO_ACTIVO);
					if (inventario != null) {
						aplicarInventario(factura, inventario, detalle, articulo);
					}

				}
			}
			// Crear Credito del cliente
			if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) && factura.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
				cuentaCobrarDao.crearCuentaXCobrar(factura);
			}

		} catch (Exception e) {
			log.info("** Error  crearCompra: " + e.getMessage() + " fecha " + new Date());

			throw e;
		}

		return factura;

	}

	/**
	 * Aplicar el inventario si estado de la venta es facturada
	 */
	private void aplicarInventario(Factura factura, Inventario inventario, Detalle detalle, Articulo articulo) {
		if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
			String leyenda = Constantes.MOTIVO_SALIDA_INVENTARIO_VENTA + factura.getNumeroConsecutivo();
			kardexDao.salida(inventario, detalle.getCantidad(), Constantes.EMPTY, factura.getNumeroConsecutivo().toString(), Constantes.KARDEX_TIPO_SALIDA, leyenda, factura.getUsuarioCreacion());

		}

	}

	/**
	 * Obtiene el efectivo
	 * @see com.factura.FacturaElectronica.Bo.FacturaBo#getTotalEfectivo(com.factura.FacturaElectronica.web.command.FacturaCommand)
	 */
	@Override
	public BigDecimal getTotalEfectivo(FacturaCommand facturaCommand) {
		BigDecimal resultado = BigDecimal.ZERO;

		if (facturaCommand.getTotalBanco().doubleValue() > Constantes.ZEROS_DOUBLE || facturaCommand.getTotalTarjeta().doubleValue() > Constantes.ZEROS_DOUBLE) {
			resultado = facturaCommand.getTotalEfectivo().doubleValue() != Constantes.ZEROS_DOUBLE ? facturaCommand.getTotalEfectivo() : BigDecimal.ZERO;
		}else {
			resultado = facturaCommand.getTotalEfectivo().doubleValue() > Constantes.ZEROS_DOUBLE ? facturaCommand.getTotalVentaNeta() : BigDecimal.ZERO;
		}

		return resultado;
	}

}