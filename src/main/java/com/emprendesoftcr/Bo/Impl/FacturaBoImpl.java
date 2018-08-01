package com.emprendesoftcr.Bo.Impl;

import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

@Lazy
@Transactional
@EnableTransactionManagement
@Service("facturaBo")
public class FacturaBoImpl implements FacturaBo {

	@Lazy
	@Autowired
	FacturaDao						facturaDao;

	@Lazy
	@Autowired
	TipoCambioBo					tipoCambioBo;

	@Lazy
	@Autowired
	DetalleDao						detalleDao;

	@Lazy
	@Autowired
	EmpresaDao						empresaDao;

	@Lazy
	@Autowired
	ArticuloDao						articuloDao;

	@Lazy
	@Autowired
	DetallesService				detallesService;

	@Lazy
	@Autowired
	KardexDao							kardexDao;

	@Lazy
	@Autowired
	CuentaCobrarDao				cuentaCobrarDao;

	@Lazy
	@Autowired
	UsuarioCajaFacturaDao	usuarioCajaFacturaDao;

	@Lazy
	@Autowired
	UsuarioCajaDao				usuarioCajaDao;

	@Lazy
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
	public Factura findById(Long id) {
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
	public Factura crearFactura(FacturaCommand facturaCommand, Usuario usuario, UsuarioCaja usuarioCaja, TipoCambio tipoCambio) throws Exception {
		Factura factura = null;
		try {
			facturaCommand.setTotal(facturaCommand.getTotal() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotal());
			facturaCommand.setTotalBanco(facturaCommand.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalBanco());
			facturaCommand.setTotalCambio(facturaCommand.getTotalCambio() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalCambio());
			facturaCommand.setTotalComprobante(facturaCommand.getTotalComprobante() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalComprobante());
			facturaCommand.setTotalCambioPagar(facturaCommand.getTotalCambioPagar() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalCambioPagar());
			facturaCommand.setTotalCredito(facturaCommand.getTotalCredito() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalCredito());
			facturaCommand.setTotalDescuentos(facturaCommand.getTotalDescuentos() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalDescuentos());
			facturaCommand.setTotalEfectivo(facturaCommand.getTotalEfectivo() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalEfectivo());
			facturaCommand.setTotalExento(facturaCommand.getTotalExento() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalExento());
			facturaCommand.setTotalGravado(facturaCommand.getTotalGravado() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalGravado());
			facturaCommand.setTotalImpuesto(facturaCommand.getTotalImpuesto() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalImpuesto());
			facturaCommand.setTotalMercanciasExentas(facturaCommand.getTotalMercanciasExentas() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalMercanciasExentas());
			facturaCommand.setTotalMercanciasGravadas(facturaCommand.getTotalMercanciasGravadas() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalMercanciasGravadas());
			facturaCommand.setMedioPago(facturaCommand.getMedioPago() == null ? Constantes.MEDIO_PAGO_EFECTIVO : facturaCommand.getMedioPago());
			facturaCommand.setMontoCambio(facturaCommand.getMontoCambio() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getMontoCambio());
			facturaCommand.setNumeroConsecutivo(facturaCommand.getNumeroConsecutivo() == null ? Constantes.ZEROS : facturaCommand.getNumeroConsecutivo());
			facturaCommand.setPlazoCredito(facturaCommand.getPlazoCredito() == null ? Constantes.ZEROS : facturaCommand.getPlazoCredito());
			facturaCommand.setCodigoMoneda(facturaCommand.getCodigoMoneda() !=null?facturaCommand.getCodigoMoneda():Constantes.CODIGO_MONEDA_COSTA_RICA);
			facturaCommand.setTotalTarjeta(facturaCommand.getTotalTarjeta() ==null?Constantes.ZEROS_DOUBLE:facturaCommand.getTotalTarjeta());

			factura = facturaCommand.getId() == null || facturaCommand.getId() == Constantes.ZEROS_LONG ? new Factura() : facturaDao.findById(facturaCommand.getId());
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

		//	if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO) || facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
		//		Factura facturaTem = facturaDao.findByConsecutivoAndEmpresa(facturaCommand.getReferenciaNumero(), usuario.getEmpresa());
		if(facturaCommand.getReferenciaCodigo() !=null) {
					factura.setReferenciaTipoDoc(facturaCommand.getReferenciaTipoDoc());
					factura.setReferenciaNumero(facturaCommand.getReferenciaNumero());
					factura.setReferenciaCodigo(facturaCommand.getReferenciaCodigo());
					factura.setReferenciaRazon(facturaCommand.getReferenciaRazon());
					factura.setReferenciaFechaEmision(Utils.parseDate2(facturaCommand.getReferenciaFechaEmision()));
			} else {
				factura.setReferenciaTipoDoc(Constantes.EMPTY);
				factura.setReferenciaNumero(Constantes.EMPTY);
				factura.setReferenciaCodigo(Constantes.EMPTY);
				factura.setReferenciaRazon(Constantes.EMPTY);

			}

			factura.setUsuarioCreacion(usuario);
			factura.setEmpresa(usuario.getEmpresa());
			factura.setVendedor(facturaCommand.getVendedor());
			factura.setCliente(facturaCommand.getCliente());
			factura.setFechaEmision(new Date());
			factura.setMedioEfectivo(Constantes.EMPTY);

			if (facturaCommand.getTotalEfectivo() > Constantes.ZEROS_DOUBLE) {
				factura.setMedioEfectivo(Constantes.MEDIO_PAGO_EFECTIVO);
			}
			factura.setMedioBanco(Constantes.EMPTY);
			if (facturaCommand.getTotalBanco() > Constantes.ZEROS_DOUBLE) {
				factura.setMedioBanco(Constantes.FACTURA_MEDIO_PAGO_TRANSFERENCIA);
			}
			factura.setMedioTarjeta(Constantes.EMPTY);
			if (facturaCommand.getTotalTarjeta() > Constantes.ZEROS_DOUBLE) {
				factura.setMedioTarjeta(Constantes.FACTURA_MEDIO_PAGO_TARJETA);
			}

			factura.setTipoDoc(facturaCommand.getTipoDoc());

			factura.setNombreFactura(facturaCommand.getNombreFactura());
			factura.setDireccion(facturaCommand.getDireccion());
			factura.setNota(facturaCommand.getNota());
			factura.setComanda(facturaCommand.getComanda());
			factura.setSubTotal(facturaCommand.getSubTotal() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getSubTotal());
			factura.setTotalTransporte(facturaCommand.getTotalTransporte() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalTransporte());
			factura.setTotalServGravados(facturaCommand.getTotalServGravados() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalServGravados());
			factura.setTotalServExentos(facturaCommand.getTotalServExentos() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalServExentos());
			factura.setTotalMercanciasExentas(facturaCommand.getTotalMercanciasExentas() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalMercanciasExentas());
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
			factura.setTipoCambio(Constantes.CODIGO_MONEDA_COSTA_RICA_CAMBIO);
			factura.setEstado(facturaCommand.getEstado());

			if (factura.getId() == Constantes.ZEROS_LONG) {
				factura.setCreated_at(new Date());
			}

			if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
				factura.setFechaEmision(new Date());
				factura.setUpdated_at(new Date());

			}

			// Generar el consecutivo de venta
			if (facturaCommand.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
				factura.setNumeroConsecutivo(empresaDao.generarConsecutivoFactura(facturaCommand.getEmpresa(), usuario, factura));
				factura.setClave(empresaDao.generaClaveFacturaTributacion(factura.getEmpresa(), factura.getNumeroConsecutivo(), FacturaElectronicaUtils.COMPROBANTE_ELECTRONICO_NORMAL));
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
				Integer numeroLinea = 1;
				for (int i = 0; i < jsonArrayDetalleFactura.size(); i++) {
					DetalleFacturaCommand detalleFacturaCommand = gson.fromJson(jsonArrayDetalleFactura.get(i).toString(), DetalleFacturaCommand.class);

					Articulo articulo = articuloDao.buscarPorCodigoYEmpresa(detalleFacturaCommand.getCodigo(), usuario.getEmpresa());
					Detalle detalle = new Detalle(detalleFacturaCommand);
					detalle.setUsuario(usuario);

					detalle.setNaturalezaDescuento(Constantes.FORMATO_NATURALEZA_DESCUENTO);
					detalle.setNumeroLinea(numeroLinea);
					detalle.setCreated_at(new Date());
					detalle.setUpdated_at(new Date());
					detalle.setDescripcion(articulo.getDescripcion());
					detalle.setTipoCodigo(articulo.getTipoCodigo());
					detalle.setCodigo(articulo.getCodigo());
					detalle.setUnidadMedida(articulo.getUnidadMedida());
					detalle.setTipoImpuesto(articulo.getTipoImpuesto());
					numeroLinea += 1;
					factura.addDetalle(detalle);
					modificar(factura);

					Inventario inventario = inventarioDao.findByArticuloAndEstado(articulo, Constantes.ESTADO_ACTIVO);
					if (inventario != null) {
						if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO) ) {
							aplicarInventario(factura, inventario, detalle, articulo);	
						}
						
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
	 * Toda nota credito se devuelve al inventario los productos
	 */
	private void aplicarInventario(Factura factura, Inventario inventario, Detalle detalle, Articulo articulo) throws Exception {
		try {
			if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
				if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)){
					String leyenda = Constantes.MOTIVO_INGRESO_INVENTARIO_NOTA_CREDITO + factura.getNumeroConsecutivo();
					kardexDao.entrada(inventario, inventario.getCantidad(), detalle.getCantidad(), Constantes.EMPTY, factura.getNumeroConsecutivo().toString(), Constantes.KARDEX_TIPO_SALIDA, leyenda, factura.getUsuarioCreacion());
				}else {
					String leyenda = Constantes.MOTIVO_SALIDA_INVENTARIO_VENTA + factura.getNumeroConsecutivo();
					kardexDao.salida(inventario, inventario.getCantidad(), detalle.getCantidad(), Constantes.EMPTY, factura.getNumeroConsecutivo().toString(), Constantes.KARDEX_TIPO_SALIDA, leyenda, factura.getUsuarioCreacion());
					
				}

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