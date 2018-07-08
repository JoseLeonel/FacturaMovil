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

import com.emprendesoftcr.Bo.FacturaBo;
import com.emprendesoftcr.Bo.TipoCambioBo;
import com.emprendesoftcr.Dao.ArticuloDao;
import com.emprendesoftcr.Dao.CuentaCobrarDao;
import com.emprendesoftcr.Dao.DetalleDao;
import com.emprendesoftcr.Dao.EmpresaDao;
import com.emprendesoftcr.Dao.FacturaDao;
import com.emprendesoftcr.Dao.InventarioDao;
import com.emprendesoftcr.Dao.KardexDao;
import com.emprendesoftcr.Dao.UsuarioCajaDao;
import com.emprendesoftcr.Dao.UsuarioCajaFacturaDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Inventario;
import com.emprendesoftcr.modelo.TipoCambio;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.modelo.UsuarioCajaFactura;
import com.emprendesoftcr.service.DetallesService;
import com.emprendesoftcr.web.command.DetalleFacturaCommand;
import com.emprendesoftcr.web.command.FacturaCommand;
import com.google.gson.Gson;

@Transactional
@EnableTransactionManagement
@Service("facturaBo")
public class FacturaBoImpl implements FacturaBo {

	@Autowired
	FacturaDao						facturaDao;

	@Autowired
	TipoCambioBo						tipoCambioBo;
	
	@Autowired
	DetalleDao						detalleDao;

	@Autowired
	EmpresaDao						empresaDao;

	@Autowired
	ArticuloDao						articuloDao;

	@Autowired
	DetallesService				detallesService;

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
	 * @see com.emprendesoftcr.Bo.FacturaBo#modificar(com.emprendesoftcr.modelo.Factura)
	 */
	@Override
	public void modificar(Factura factura) {
		facturaDao.modificar(factura);
	}

	/**
	 * Eliminar una factura
	 * @see com.emprendesoftcr.Bo.FacturaBo#eliminar(com.emprendesoftcr.modelo.Factura)
	 */
	@Override
	public void eliminar(Factura factura) {
		facturaDao.eliminar(factura);
	}

	/**
	 * Buscar por id
	 * @see com.emprendesoftcr.Bo.FacturaBo#findById(java.lang.Integer)
	 */
	@Override
	public Factura findById(Integer id) {
		return facturaDao.findById(id);
	}

	/**
	 * Buscar por consecutivo y empresa
	 * @see com.emprendesoftcr.Bo.FacturaBo#findByConsecutivoAndEmpresa(java.lang.String, com.emprendesoftcr.modelo.Empresa)
	 */
	@Override
	public Factura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) throws Exception {
		Factura factura = null;
		try {
			factura = facturaDao.findByConsecutivoAndEmpresa(consecutivo, empresa);
		} catch (Exception e) {
			log.info("** Error  findByConsecutivoAndEmpresa: " + e.getMessage() + " fecha " + new Date());

			throw e;
		}
		return factura;
	}

	/**
	 * Crear la factura o el tiquete temporal
	 * @see com.emprendesoftcr.Bo.FacturaBo#crearFactura(com.emprendesoftcr.web.command.FacturaCommand, com.emprendesoftcr.modelo.Usuario)
	 */
	@Override
	public Factura crearFactura(FacturaCommand facturaCommand, Usuario usuario, UsuarioCaja usuarioCaja , TipoCambio tipoCambio) throws Exception {
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
			factura.setSubTotal(facturaCommand.getSubTotal() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getSubTotal());
			factura.setTotalTransporte(facturaCommand.getTotalTransporte() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalTransporte());
			factura.setTotal(facturaCommand.getTotal() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotal());
			factura.setTotalServGravados(facturaCommand.getTotalServGravados() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalServGravados());
			factura.setTotalServExentos(facturaCommand.getTotalServExentos() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalServExentos());
			factura.setTotalMercanciasExentas(factura.getTotalMercanciasExentas() == null ? Constantes.ZEROS_DOUBLE : factura.getTotalMercanciasExentas());
			factura.setTotalMercanciasGravadas(facturaCommand.getTotalMercanciasGravadas() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalMercanciasGravadas());
			factura.setTotalGravado(facturaCommand.getTotalGravado() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalGravado());
			factura.setTotalExento(facturaCommand.getTotalExento() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalExento());
			factura.setTotalVenta(facturaCommand.getTotalVenta() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalVenta());
			factura.setTotalDescuentos(facturaCommand.getTotalDescuentos() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalDescuentos());
			factura.setTotalVentaNeta(facturaCommand.getTotalVentaNeta() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalVentaNeta());
			factura.setTotalImpuesto(facturaCommand.getTotalImpuesto() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalImpuesto());
			factura.setTotalComprobante(facturaCommand.getTotalComprobante() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalComprobante());
			factura.setTotalEfectivo(getTotalEfectivo(facturaCommand));
			factura.setTotalTarjeta(facturaCommand.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalTarjeta());
			factura.setTotalBanco(facturaCommand.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalBanco());
			factura.setTotalCredito(facturaCommand.getTotalCredito() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalCredito());
			factura.setMontoCambio(facturaCommand.getMontoCambio() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getMontoCambio());
			factura.setTotalCambio(facturaCommand.getTotalCambio() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalCambio());
			factura.setTotalCambioPagar(facturaCommand.getTotalCambioPagar() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalCambioPagar());
			
			factura.setTipoCambio(tipoCambio.getTotal());
			factura.setCambioMoneda(tipoCambioBo.conversionMoneda(factura.getTotalVentaNeta(), tipoCambio));
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
				factura.setClave(empresaDao.generaClaveFacturaTributacion(factura.getEmpresa(), factura.getNumeroConsecutivo(),FacturaElectronicaUtils.COMPROBANTE_ELECTRONICO_NORMAL));
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
				usuarioCajaDao.actualizarCaja(usuarioCaja, factura.getTotalEfectivo(), factura.getTotalTarjeta(), factura.getTotalBanco(), factura.getTotalCredito(), Constantes.ZEROS_DOUBLE);
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
					detalle.setCreated_at(new Date());
					detalle.setUpdated_at(new Date());

					factura.addDetalle(detalle);
					modificar(factura);

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
	private void aplicarInventario(Factura factura, Inventario inventario, Detalle detalle, Articulo articulo) throws Exception {
		try {
			if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
				String leyenda = Constantes.MOTIVO_SALIDA_INVENTARIO_VENTA + factura.getNumeroConsecutivo();
				kardexDao.salida(inventario,inventario.getCantidad(), detalle.getCantidad(), Constantes.EMPTY, factura.getNumeroConsecutivo().toString(), Constantes.KARDEX_TIPO_SALIDA, leyenda, factura.getUsuarioCreacion());

			}

		} catch (Exception e) {
			log.info("** Error  aplicar en el inventario: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	/**
	 * Obtiene el efectivo
	 * @see com.emprendesoftcr.Bo.FacturaBo#getTotalEfectivo(com.emprendesoftcr.web.command.FacturaCommand)
	 */
	@Override
	public Double getTotalEfectivo(FacturaCommand facturaCommand) throws Exception {
		Double resultado = Constantes.ZEROS_DOUBLE;
		try {
			if (facturaCommand.getTotalBanco() == null || facturaCommand.getTotalTarjeta() == null || facturaCommand.getTotalEfectivo() == null) {
				return resultado;
			}
			if (facturaCommand.getTotalBanco() == 0 || facturaCommand.getTotalTarjeta() == 0) {
				resultado = facturaCommand.getTotalEfectivo() - facturaCommand.getTotalCambioPagar();
			} else {
				resultado = facturaCommand.getTotalVentaNeta();
			}
		} catch (Exception e) {
			log.info("** Error  aplicar getTotalEfectivo : " + e.getMessage() + " fecha " + new Date());
			throw e;

		}

		return resultado;
	}

}