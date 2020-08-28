package com.emprendesoftcr.Bo.Impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

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

import com.emprendesoftcr.Bo.CuentaCobrarBo;
import com.emprendesoftcr.Bo.EmpresaBo;
import com.emprendesoftcr.Bo.FacturaBo;
import com.emprendesoftcr.Dao.ArticuloDao;
import com.emprendesoftcr.Dao.CuentaCobrarDao;
import com.emprendesoftcr.Dao.DetalleDao;
import com.emprendesoftcr.Dao.FacturaDao;
import com.emprendesoftcr.Dao.KardexDao;
import com.emprendesoftcr.Dao.UsuarioCajaFacturaDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.TipoCambio;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.modelo.UsuarioCajaFactura;
import com.emprendesoftcr.modelo.sqlNativo.ListarFacturasNativa;
import com.emprendesoftcr.web.command.DetalleFacturaCommand;
import com.emprendesoftcr.web.command.FacturaCommand;
import com.emprendesoftcr.web.command.TotalFacturaCommand;
import com.google.gson.Gson;

/**
 * Reglas de la factura FacturaBoImpl.
 * @author jose.
 * @since 3 nov. 2018
 */
@EnableTransactionManagement
@Service("facturaBo")
public class FacturaBoImpl implements FacturaBo {

	@Autowired
	private FacturaDao						facturaDao;

	@Autowired
	private EmpresaBo							empresaBo;

	@Autowired
	private ArticuloDao						articuloDao;

	@Autowired
	private KardexDao							kardexDao;

	@Autowired
	private CuentaCobrarBo				cuentaCobrarBo;

	@Autowired
	private CuentaCobrarDao				cuentaCobrarDao;

	@Autowired
	private UsuarioCajaFacturaDao	usuarioCajaFacturaDao;

	@Autowired
	private DetalleDao						detalleDao;

	private Logger								log	= LoggerFactory.getLogger(this.getClass());

	@Transactional
	@Override
	public void agregar(Factura factura) {
		facturaDao.agregar(factura);

	}

	/**
	 * Modificar una factura
	 * @see com.emprendesoftcr.Bo.FacturaBo#modificar(com.emprendesoftcr.modelo.Factura)
	 */

	@Transactional
	@Override
	public void modificar(Factura factura) {
		facturaDao.modificar(factura);
	}

	/**
	 * Eliminar una factura
	 * @see com.emprendesoftcr.Bo.FacturaBo#eliminar(com.emprendesoftcr.modelo.Factura)
	 */
	@Transactional
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

	@Override
	public Factura findByConsecutivoProformaAndEmpresa(String consecutivo, Empresa empresa) throws Exception {
		Factura factura = null;
		try {
			factura = facturaDao.findByConsecutivoProformaAndEmpresa(consecutivo, empresa);
		} catch (Exception e) {
			log.info("** Error  findByConsecutivoProformaAndEmpresa: " + e.getMessage() + " fecha " + new Date());

			throw e;
		}
		return factura;
	}

	@Override
	public Factura findByClaveAndEmpresa(String clave, Empresa empresa) throws Exception {
		Factura factura = null;
		try {
			factura = facturaDao.findByClaveAndEmpresa(clave, empresa);
		} catch (Exception e) {
			log.info("** Error  findByClaveAndEmpresa: " + e.getMessage() + " fecha " + new Date());

			throw e;
		}
		return factura;
	}

	@Override
	@Transactional
	public void eliminarDetalleFacturaPorSP(Factura factura) throws Exception {
		try {
			facturaDao.eliminarDetalleFacturaPorSP(factura);
		} catch (Exception e) {
			log.info("** Error  eliminarDetalleFacturaPorSP: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	@Override
	public Collection<Factura> findByEstadoFirma(Integer estadoFirma, Integer reEstadoFirma) {
		return facturaDao.findByEstadoFirma(estadoFirma, reEstadoFirma);
	}

	@Override
	public Collection<Factura> findByClienteAndEmpresa(Cliente cliente, Empresa empresa) {
		return facturaDao.findByClienteAndEmpresa(cliente, empresa);
	}

	@Transactional
	private Factura formaFactura(FacturaCommand facturaCommand, Usuario usuario) throws Exception {
		// Se forma objeto factura
		Factura factura = null;
		try {
			Empresa empresa = usuario.getEmpresa();
			// Se actualizan los datos de la factura command
			if (empresa.getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
				facturaCommand.setTipoDoc(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA);
			}
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
			facturaCommand.setCodigoMoneda(facturaCommand.getCodigoMoneda() != null ? facturaCommand.getCodigoMoneda() : Constantes.CODIGO_MONEDA_COSTA_RICA);
			facturaCommand.setTotalTarjeta(facturaCommand.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalTarjeta());
			// Proformas
			if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				facturaCommand.setEstado(Constantes.FACTURA_ESTADO_PROFORMAS);
			}
			// Tiquete de uso interno
			if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE_USO_INTERNO)) {
				facturaCommand.setEstado(Constantes.FACTURA_ESTADO_TIQUETE_USO_INTERNO);
			}

			// Se busca la factura por id o se crea un nuevo objeto
			factura = facturaCommand.getId() == null || facturaCommand.getId() == Constantes.ZEROS_LONG ? new Factura() : facturaDao.findById(facturaCommand.getId());
			// Se complentan los datos de la factura
			factura.setCondicionVenta(facturaCommand.getCondicionVenta());
			factura.setRebajaInventario(facturaCommand.getRebajaInventario() == null ? Constantes.NO_APLICA_REBAJO_INVENTARIO_POR_NOTA : facturaCommand.getRebajaInventario());

			// Fecha de credito
			if (facturaCommand.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
				if (facturaCommand.getFechaCredito() != null) {
					factura.setFechaCredito(Utils.pasarADate(facturaCommand.getFechaCredito(), "yyyy-MM-dd"));
					factura.setPlazoCredito(facturaCommand.getPlazoCredito() == null ? Constantes.CANTIDAD_DIAS_MINIMO_CREDITO : facturaCommand.getPlazoCredito());
				}
			} else {
				factura.setFechaCredito(null);
				factura.setPlazoCredito(Constantes.ZEROS);
			}
			factura.setTotalOtrosCargos(Constantes.ZEROS_DOUBLE);
			factura.setTipoDocumentoOtroCargo(Constantes.EMPTY);
			factura.setDetalleOtroCargo(Constantes.EMPTY);

			factura.setVersionEsquemaXML(Constantes.ESQUEMA_XML_4_3);

			factura.setCorreoAlternativo(facturaCommand.getCorreoAlternativo());

			// No se cambia el usuario en la venta solo en la anulacion
			if (factura.getId() == null) {
				factura.setUsuarioCreacion(usuario);
			}

			factura.setEmpresa(usuario.getEmpresa());
			factura.setVendedor(facturaCommand.getVendedor());
			factura.setCliente(facturaCommand.getCliente());
			factura.setFechaEmision(new Date());
			factura.setMedioEfectivo(Constantes.EMPTY);
			factura.setNombreFactura(facturaCommand.getNombreFactura());

			factura.setEmpresa(usuario.getEmpresa());
			factura.setVendedor(facturaCommand.getVendedor());
			factura.setCliente(facturaCommand.getCliente());
			factura.setFechaEmision(new Date());
			factura.setMedioEfectivo(Constantes.EMPTY);
			factura.setNombreFactura(facturaCommand.getNombreFactura());
			factura.setCodigoActividad(facturaCommand.getCodigoActividad() == null ? usuario.getEmpresa().getCodigoActividad() : facturaCommand.getCodigoActividad());
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
			factura.setPesoTransporteTotal(facturaCommand.getPesoTransporteTotal() != null ? facturaCommand.getPesoTransporteTotal() : Constantes.ZEROS_DOUBLE);
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
			if (facturaCommand.getCodigoMoneda() != null) {
				// Costa Rica Colones
				if (facturaCommand.getCodigoMoneda().equals(Constantes.CODIGO_MONEDA_COSTA_RICA)) {
					factura.setCodigoMoneda(Constantes.CODIGO_MONEDA_COSTA_RICA);
					factura.setTipoCambio(Constantes.CODIGO_MONEDA_COSTA_RICA_CAMBIO);
				} else if (facturaCommand.getCodigoMoneda().equals(Constantes.CODIGO_MONEDA_DOLAR)) {// Dollar
					factura.setCodigoMoneda(Constantes.CODIGO_MONEDA_DOLAR);
					factura.setTipoCambio(facturaCommand.getTipoCambioMoneda());
				}
			} else {
				factura.setCodigoMoneda(Constantes.CODIGO_MONEDA_COSTA_RICA);
				factura.setTipoCambio(Constantes.CODIGO_MONEDA_COSTA_RICA_CAMBIO);

			}
			factura.setEstado(facturaCommand.getEstado());
			factura.setMesa(facturaCommand.getMesa());
			factura.setCreated_at(new Date());
			factura.setFechaEmision(new Date());
			if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO) || factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO_INTERNO)) {
				factura.setEstado(Constantes.FACTURA_ESTADO_FACTURADO);
				factura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_COMPLETO);
				factura.setClave(Constantes.EMPTY);
			}

			// Verifica si esta facturado para cambiar el estado firma y enviar a crear el
			// xml en el proceso automatico
			if (factura.getEmpresa().getNoFacturaElectronica() != null && factura.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA) || factura.getEstado().equals(Constantes.FACTURA_ESTADO_TIQUETE_USO_INTERNO) || facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				factura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_COMPLETO);

			} else {
				if (!factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO) && !factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO_INTERNO)) {
					if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) || facturaCommand.getEstado().equals(Constantes.FACTURA_ESTADO_ACEPTADA)) {
						factura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_PENDIENTE);
					} else {
						factura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_EN_PROCESOS);
					}

				}
			}

		} catch (Exception e) {
			log.error(String.format("--error formaFactura :" + e.getMessage() + new Date()));
			throw e;
		}
		return factura;
	}

	/**
	 * Mueve los datos correspondientes a notas de creditos
	 * @param factura
	 * @param facturaCommand
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	@Transactional
	private Factura getNotaCreditoOrDebito(Factura factura, FacturaCommand facturaCommand, Usuario usuario) throws Exception {
		try {
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE_USO_INTERNO) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				factura.setReferenciaTipoDoc(facturaCommand.getReferenciaTipoDoc());
				factura.setReferenciaNumero(facturaCommand.getReferenciaNumero());
				factura.setReferenciaCodigo(facturaCommand.getReferenciaCodigo());
				factura.setReferenciaRazon(facturaCommand.getReferenciaRazon());
				if (facturaCommand.getReferenciaFechaEmision() != null) {
					factura.setReferenciaFechaEmision(Utils.parseDate2(facturaCommand.getReferenciaFechaEmision()));
				}
				Factura facturaReferencia = facturaDao.findByConsecutivoAndEmpresa(facturaCommand.getReferenciaNumero(), usuario.getEmpresa());

				facturaReferencia = facturaReferencia == null ? facturaDao.findByClaveAndEmpresa(facturaCommand.getReferenciaNumero(), usuario.getEmpresa()) : facturaReferencia;
				// Si la factura se encuentra en el sistema se agregan los datos propios de ella
				if (facturaReferencia != null) {
					if (facturaReferencia.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO)) {
						factura.setTipoDoc(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO);
					}
					factura.setReferenciaNumero(facturaReferencia.getClave());
					factura.setReferenciaTipoDoc(facturaReferencia.getTipoDoc());
					factura.setReferenciaFechaEmision(facturaReferencia.getFechaEmision());
					factura.setCodigoMoneda(facturaReferencia.getCodigoMoneda());
					factura.setTipoCambio(facturaReferencia.getTipoCambio());
					factura.setDetalleOtroCargo(facturaReferencia.getDetalleOtroCargo() == null ? Constantes.EMPTY : facturaReferencia.getDetalleOtroCargo());
					factura.setTotalOtrosCargos(facturaReferencia.getTotalOtrosCargos() == null ? Constantes.ZEROS_DOUBLE : facturaReferencia.getTotalOtrosCargos());
					factura.setTipoDocumentoOtroCargo(facturaReferencia.getTipoDocumentoOtroCargo() == null ? Constantes.EMPTY : facturaReferencia.getTipoDocumentoOtroCargo());

					if (facturaReferencia.getVersionEsquemaXML().equals(Constantes.ESQUEMA_XML_4_2)) {
						factura.setVersionEsquemaXML(Constantes.ESQUEMA_XML_4_2);
					}
					// Anula documento completo
					if (factura.getReferenciaCodigo().equals(Constantes.FACTURA_CODIGO_REFERENCIA_ANULA_DOCUMENTO)) {
						factura.setSubTotal(facturaReferencia.getSubTotal());
						factura.setTotalTransporte(facturaReferencia.getTotalTransporte());
						factura.setTotalServGravados(facturaReferencia.getTotalServExentos());
						factura.setTotalServExentos(facturaReferencia.getTotalServExentos());
						factura.setTotalMercanciasExentas(facturaReferencia.getTotalMercanciasExentas());
						factura.setTotalMercanciasGravadas(facturaReferencia.getTotalMercanciasGravadas());
						factura.setTotalGravado(facturaReferencia.getTotalGravado());
						factura.setTotalExento(facturaReferencia.getTotalExento());
						factura.setTotalVenta(facturaReferencia.getTotalVenta());
						factura.setTotalDescuentos(facturaReferencia.getTotalDescuentos());
						factura.setTotalVentaNeta(facturaReferencia.getTotalVentaNeta());
						factura.setTotalImpuesto(facturaReferencia.getTotalImpuesto());
						factura.setTotalComprobante(facturaReferencia.getTotalComprobante());
						factura.setTotalEfectivo(facturaReferencia.getTotalEfectivo());
						factura.setTotalTarjeta(facturaReferencia.getTotalTarjeta());
						factura.setTotalBanco(facturaReferencia.getTotalBanco());
						factura.setTotalCredito(facturaReferencia.getTotalCredito());
						factura.setMontoCambio(facturaReferencia.getMontoCambio());
						factura.setTotalCambio(facturaReferencia.getTotalCambio());
						factura.setTotalCambioPagar(facturaReferencia.getTotalCambioPagar());

						facturaReferencia.setAnuladaCompleta(Constantes.FACTURA_ANULACION_COMPLETA_SI);

						this.modificar(facturaReferencia);

					}
					if (facturaReferencia.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO)) {
						if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
							factura.setTipoDoc(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO);
						} else {
							factura.setTipoDoc(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO_INTERNO);
						}

					}
					factura.setNota(getNotaRazon(facturaCommand, facturaReferencia.getNumeroConsecutivo().trim()));
					// modificar(facturaAnular);
					factura.setCliente(facturaReferencia.getCliente());
					factura.setNoAplicarEnCaja(Constantes.SI_APLICA_EN_CAJA);
					// Si la factura que se le va aplicar la nota de credito o debido es de un dia anterior
					if (facturaReferencia != null) {
						if (facturaReferencia.getFechaEmision() != null) {
							Integer valor = Utils.fechaDiaAnterior(facturaReferencia.getFechaEmision(), new Date());
							if (valor > Constantes.ZEROS) {
								factura.setNoAplicarEnCaja(Constantes.NO_APLICA_EN_CAJA);
							}

						}
					}

					// Nota de Credito por ajuste montos se crea abono a la cuenta cobrar.
					factura = aplicarModificarCuentaPorCobrar(factura, facturaReferencia);

				}

			} else {
				factura.setVersionEsquemaXML(Constantes.ESQUEMA_XML_4_3);
				factura.setReferenciaTipoDoc(Constantes.EMPTY);
				factura.setReferenciaNumero(Constantes.EMPTY);
				factura.setReferenciaCodigo(Constantes.EMPTY);
				factura.setReferenciaRazon(Constantes.EMPTY);
				factura.setAnuladaCompleta(Constantes.ZEROS);
				factura.setReferenciaFechaEmision(null);
			}

		} catch (Exception e) {
			log.error(String.format("--error getNotaCreditoOrDebito :" + e.getMessage() + new Date()));
			throw e;
		}

		return factura;
	}

	/**
	 * @param factura
	 * @param facturaReferencia
	 * @return
	 */
	@Transactional
	private Factura aplicarModificarCuentaPorCobrar(Factura factura, Factura facturaReferencia) {
		try {

			// Nota de Credito por ajuste montos se crea abono a la cuenta cobrar.
			// Nota de Credito Anulacion Documento
			if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) || factura.getEstado().equals(Constantes.FACTURA_ESTADO_TIQUETE_USO_INTERNO)) {
					if (factura.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
						if (facturaReferencia != null) {
							cuentaCobrarBo.modificarCuentaXCobrarPorNotaCredito(factura, facturaReferencia);
						}
					}
				}
			}
			// Si es nota de debito se crea un abono por nota de debito
			if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
				if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) || factura.getEstado().equals(Constantes.FACTURA_ESTADO_TIQUETE_USO_INTERNO)) {
					if (factura.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
						if (facturaReferencia != null) {
							cuentaCobrarBo.modificarCuentaXCobrarPorNotaDebito(factura, facturaReferencia);
						}
					}
				}
			}

		} catch (Exception e) {
			log.error(String.format("--error aplicarCuentaPorCobrar :" + e.getMessage() + new Date()));
			throw e;
		}

		return factura;
	}

	/**
	 * Obtiene el consecutivo de la orden o proforma
	 * @param factura
	 * @param facturaCommand
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	@Transactional
	private Factura getConsecutivoAndClave(Factura factura, FacturaCommand facturaCommand, Usuario usuario) throws Exception {
		try {
			// Generar el consecutivo de venta
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO_INTERNO)) {
				if (facturaCommand.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) || facturaCommand.getEstado().equals(Constantes.FACTURA_ESTADO_ACEPTADA)) {
					factura.setNumeroConsecutivo(empresaBo.spGenerarConsecutivoFactura(factura.getEmpresa(), usuario, factura.getTipoDoc()));

					if (factura.getEmpresa().getNoFacturaElectronica() != null && factura.getEmpresa().getNoFacturaElectronica().equals(Constantes.SI_APLICA_FACTURA_ELECTRONICA)) {
						factura.setClave(empresaBo.generaClaveFacturaTributacion(factura.getEmpresa(), factura.getNumeroConsecutivo(), FacturaElectronicaUtils.COMPROBANTE_ELECTRONICO_NORMAL));
					}

				}
			}

			if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_PROFORMAS)) {
				if (factura.getConsecutivoProforma() != null) {
					if (factura.getConsecutivoProforma().equals(Constantes.EMPTY)) {
						factura.setConsecutivoProforma(empresaBo.generarConsecutivoProforma(factura.getEmpresa(), usuario));
					}
				} else {
					factura.setConsecutivoProforma(empresaBo.generarConsecutivoProforma(factura.getEmpresa(), usuario));
				}
			}
			if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				factura.setNumeroConsecutivo(empresaBo.generarConsecutivoNotaCreditoInterno(factura.getEmpresa(), usuario));
			}
			if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO_INTERNO)) {
				factura.setNumeroConsecutivo(empresaBo.generarConsecutivoNotaDebitoInterno(factura.getEmpresa(), usuario));
			}

		} catch (Exception e) {
			log.error(String.format("--error getConsecutivoAndClave :" + e.getMessage() + new Date()));
			throw e;
		}

		return factura;
	}

	@Transactional
	@Override
	public ArrayList<DetalleFacturaCommand> formaDetallesCommand(FacturaCommand facturaCommand) throws Exception {
		// Detalles, se forma el detalle de la factura, se contabiliza los totales para
		// evitar problemas con el tema de los decimales en el front
		JSONObject json = null;
		ArrayList<DetalleFacturaCommand> detallesFacturaCommand = new ArrayList<>();
		try {
			json = (JSONObject) new JSONParser().parse(facturaCommand.getDetalleFactura());
			// Agregar Lineas de Detalle
			JSONArray jsonArrayDetalleFactura = (JSONArray) json.get("data");
			Gson gson = new Gson();
			if (jsonArrayDetalleFactura != null) {
				Integer numeroLinea = 1;
				for (int i = 0; i < jsonArrayDetalleFactura.size(); i++) {
					DetalleFacturaCommand detalleFacturaCommand = gson.fromJson(jsonArrayDetalleFactura.get(i).toString(), DetalleFacturaCommand.class);
					detalleFacturaCommand.setCantidadAplicadaNotaCredito(detalleFacturaCommand.getCantidadAplicadaNotaCredito() == null ? Constantes.ZEROS_DOUBLE : detalleFacturaCommand.getCantidadAplicadaNotaCredito());
					detallesFacturaCommand.add(detalleFacturaCommand);
					numeroLinea += 1;
				}
			}
		} catch (org.json.simple.parser.ParseException e) {
			log.error(String.format("--error formaDetallesCommand :" + e.getMessage() + new Date()));
			throw e;

		}
		return detallesFacturaCommand;
	}

	/**
	 * Actualiza el caja del usuario
	 * @param factura
	 * @param usuarioCaja
	 */
	@Transactional
	private void aplicarCajaDinero(Factura factura, UsuarioCaja usuarioCaja) {
		try {
			// Efectivo Banco Tarjeta
			if (!factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) || factura.getEstado().equals(Constantes.FACTURA_ESTADO_TIQUETE_USO_INTERNO)) {
					// Se agrega solo si no existe en la caja de usuario, casos de reintentos
					UsuarioCajaFactura UsuarioCajaBD = usuarioCajaFacturaDao.findByFacturaId(factura.getId());
					if (UsuarioCajaBD == null) {
						UsuarioCajaFactura usuarioCajaFactura = new UsuarioCajaFactura();
						usuarioCajaFactura.setCreated_at(new Date());
						usuarioCajaFactura.setUpdated_at(new Date());
						usuarioCajaFactura.setFactura(factura);
						usuarioCajaFactura.setUsuarioCaja(usuarioCaja);
						usuarioCajaFacturaDao.agregar(usuarioCajaFactura);
					}
				}
			}

		} catch (Exception e) {
			log.error(String.format("--error aplicarCajaDinero :" + e.getMessage() + new Date()));
			throw e;
		}
	}

	/**
	 * Aplica el Inventario
	 * @param factura
	 * @param usuario
	 * @throws Exception
	 */
	@Transactional
	private void aplicarInventario(Factura factura, Usuario usuario) throws Exception {
		try {
			// Actualiza articulo y inventario
			if (factura.getEmpresa().getTieneInventario().equals(Constantes.ESTADO_ACTIVO)) {
				if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) || factura.getEstado().equals(Constantes.FACTURA_ESTADO_TIQUETE_USO_INTERNO)) {
					this.actualizaArticulosInventario(factura, usuario);
				}
			}

		} catch (Exception e) {
			log.error(String.format("--error aplicarInventario :" + e.getMessage() + new Date()));
			throw e;
		}
	}

	/**
	 * Aplica cuentas por cobrar
	 * @param factura
	 */
	@Transactional
	private void aplicarCuentaPorCobrar(Factura factura) {
		try {

			if (!factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO) && !factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) && !factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) || factura.getEstado().equals(Constantes.FACTURA_ESTADO_TIQUETE_USO_INTERNO)) {
					if (factura.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
						cuentaCobrarDao.crearCuentaXCobrar(factura);
					}
				}
			}
		} catch (Exception e) {
			log.error(String.format("--error aplicarCuentaPorCobrar :" + e.getMessage() + new Date()));
			throw e;
		}
	}

	@Transactional
	private Factura actualizaOrCrearFactura(Factura factura) {
		try {
			factura.setNotificacionNoElectronicio(Constantes.ZEROS);
			if (factura.getAnuladaCompleta() == null) {
				factura.setAnuladaCompleta(Constantes.ZEROS);
			}
			if (factura.getConsecutivoProforma() == null) {
				factura.setConsecutivoProforma(Constantes.EMPTY);
			}
			if (factura.getCorreoAlternativo() == null) {
				factura.setCorreoAlternativo(Constantes.EMPTY);
			}
			if (factura.getCambioMoneda() == null) {
				factura.setCambioMoneda(Constantes.ZEROS_DOUBLE);
			}
			if (factura.getEmpresa().getNoFacturaElectronica() != null) {
				if (factura.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA_REINTEGRO_GASTOS) || factura.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA)) {
					factura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_COMPLETO);
					factura.setNoAplicarEnCaja(Constantes.SI_APLICA_EN_CAJA);
				}

			}
			if (factura.getNoAplicarEnCaja() == null) {
				factura.setNoAplicarEnCaja(Constantes.SI_APLICA_EN_CAJA);
			}
			if (factura.getId() == null) {

				factura.setCreated_at(new Date());
				this.agregar(factura);
			} else {
				factura.setCreated_at(new Date());
				this.modificar(factura);
			}
		} catch (Exception e) {
			log.error(String.format("--error actualizaOrCrearFactura :" + e.getMessage() + new Date()));
			throw e;
		}

		return factura;
	}

	/**
	 * Crear la factura o el tiquete temporal
	 * @see com.emprendesoftcr.Bo.FacturaBo#crearFactura(com.emprendesoftcr.web.command.FacturaCommand, com.emprendesoftcr.modelo.Usuario)
	 */
	private final ReentrantLock lock = new ReentrantLock();

	@Override
	@Transactional
	public synchronized Factura crearFactura(FacturaCommand facturaCommand, Usuario usuario, UsuarioCaja usuarioCaja, TipoCambio tipoCambio, ArrayList<DetalleFacturaCommand> detallesFacturaCommand, ArrayList<DetalleFacturaCommand> detallesNotaCredito) throws Exception {
		Factura factura = null;
		lock.lock();
		try {
			long id = Thread.currentThread().getId();
			log.info(String.format("--start transaccion--> Thread=%d %s", id, "Fecha:" + new Date()));
//			if (lock.tryLock(1000000, TimeUnit.HOURS)) {
			// Se forman los detalles command de las factura

			// --------------------------------------------- Se trabaja con el objeto a
			// registrar en bd -----------------------------------------------------
			// Se forma el objeto factura
			factura = this.formaFactura(facturaCommand, usuario);

			// Aplicar si es Nota de Credito
			factura = this.getNotaCreditoOrDebito(factura, facturaCommand, usuario);

			// Obtener el consecutivo
			factura = this.getConsecutivoAndClave(factura, facturaCommand, usuario);

			// Aplica actualizacion o creacion de la factura
			factura = this.actualizaOrCrearFactura(factura);
			// Se asociando los detalles a la factura
			this.asociaDetallesFactura(factura, facturaCommand, usuario, detallesFacturaCommand);
			// Aplicar la Caja de Dinero
			this.aplicarCajaDinero(factura, usuarioCaja);
			// Aplicar inventario
			this.aplicarInventario(factura, usuario);
			// aplicar cuenta por cobrar
			this.aplicarCuentaPorCobrar(factura);
//			}

			log.info(String.format("--Finaliza transaccion--> Thread=%d %s", id, "Fecha:" + new Date()));

		} catch (InterruptedException e) {
			log.error("** Error  crear la factura: " + e.getMessage() + " fecha " + new Date());
			e.printStackTrace();
			throw e;
		} finally {
			lock.unlock();
		}

		return factura;
	}

	/**
	 * Asociar Los detalles de la factura
	 * @param factura
	 * @param facturaCommand
	 * @param usuario
	 * @param detallesFacturaCommand
	 * @throws Exception
	 */
	@Transactional
	private void asociaDetallesFactura(Factura factura, FacturaCommand facturaCommand, Usuario usuario, ArrayList<DetalleFacturaCommand> detallesFacturaCommand) throws Exception {

		// Detalles, se forma el detalle de la factura, se contabiliza los totales para
		// evitar problemas con el tema de los decimales en el front

		// Se inicializan los totales
		Double totalServGravados = Constantes.ZEROS_DOUBLE;
		Double totalServExentos = Constantes.ZEROS_DOUBLE;
		Double totalMercanciasGravadas = Constantes.ZEROS_DOUBLE;
		Double totalMercanciasExentas = Constantes.ZEROS_DOUBLE;
		Double totalGravado = Constantes.ZEROS_DOUBLE;
		Double totalExento = Constantes.ZEROS_DOUBLE;
		Double totalVenta = Constantes.ZEROS_DOUBLE;
		Double totalDescuentos = Constantes.ZEROS_DOUBLE;
		Double totalVentaNeta = Constantes.ZEROS_DOUBLE;
		Double totalImpuesto = Constantes.ZEROS_DOUBLE;
		Double totalComprobante = Constantes.ZEROS_DOUBLE;
		Double subTotal = Constantes.ZEROS_DOUBLE;
		Double totalImpServicios = Constantes.ZEROS_DOUBLE;
		Double totalServExonerado = Constantes.ZEROS_DOUBLE;
		Double totalMercExonerada = Constantes.ZEROS_DOUBLE;
		Double totalExonerado = Constantes.ZEROS_DOUBLE;
		Double totalIVADevuelto = Constantes.ZEROS_DOUBLE;
		Double totalOtrosCargos = Constantes.ZEROS_DOUBLE;
		String unidadMedida = Constantes.EMPTY;
		// Agregar Lineas de Detalle
		Integer numeroLinea = 1;
		Double gananciaProducto = Constantes.ZEROS_DOUBLE;
		Double precioUnitario = Constantes.ZEROS_DOUBLE;
		Double costo = Constantes.ZEROS_DOUBLE;
		Double descuentoTotal = Constantes.ZEROS_DOUBLE;
		Double montoTotalLinea = Constantes.ZEROS_DOUBLE;
		ArrayList<Detalle> listaDetalles = new ArrayList<Detalle>();
		for (Iterator<DetalleFacturaCommand> iterator = detallesFacturaCommand.iterator(); iterator.hasNext();) {
			DetalleFacturaCommand detalleFacturaCommand = (DetalleFacturaCommand) iterator.next();

			Articulo articulo = articuloDao.buscarPorCodigoYEmpresa(detalleFacturaCommand.getCodigo(), usuario.getEmpresa());
			if (articulo != null) {
				detalleFacturaCommand.setTipoCodigo(articulo.getTipoCodigo() == null ? Constantes.TIPO_CODIGO_ARTICULO_USO_INTERNO : articulo.getTipoCodigo());
				detalleFacturaCommand.setUnidadMedida(articulo.getUnidadMedida());
				articulo.setUpdated_at(new Date());
				articuloDao.modificar(articulo);
			}
			unidadMedida = Constantes.UNIDAD_MEDIDA;
			if (detalleFacturaCommand.getUnidadMedida() != null) {
				if (detalleFacturaCommand.getUnidadMedida().equals(Constantes.EMPTY)) {
					detalleFacturaCommand.setUnidadMedida(unidadMedida);
				}
			} else {
				detalleFacturaCommand.setUnidadMedida(unidadMedida);
			}
			gananciaProducto = Constantes.ZEROS_DOUBLE;
			precioUnitario = Constantes.ZEROS_DOUBLE;
			costo = Constantes.ZEROS_DOUBLE;
			if (detalleFacturaCommand.getPrecioUnitario() != null) {
				precioUnitario = Utils.Maximo5Decimales(Utils.aplicarRedondeo(detalleFacturaCommand.getPrecioUnitario()) ? Utils.roundFactura(detalleFacturaCommand.getPrecioUnitario(), 5) : detalleFacturaCommand.getPrecioUnitario());
			}
			if (articulo != null) {
				if (articulo.getCosto() != null) {
					costo = articulo.getCosto();

				}
			}
			gananciaProducto = Utils.Maximo5Decimales(Utils.getGananciaProducto(precioUnitario * detalleFacturaCommand.getCantidad(), costo * detalleFacturaCommand.getCantidad(), detalleFacturaCommand.getMontoDescuento()));
			Detalle detalle = new Detalle(detalleFacturaCommand);
			detalle.setId(null);
			detalle.setPesoTransporte(detalleFacturaCommand.getPesoTransporte() != null ? detalleFacturaCommand.getPesoTransporte() : Constantes.ZEROS_DOUBLE);
			detalle.setPesoTransporteTotal(detalleFacturaCommand.getPesoTransporteTotal() != null ? detalleFacturaCommand.getPesoTransporteTotal() : Constantes.ZEROS_DOUBLE);
			detalle.setCosto(Utils.Maximo5Decimales(costo));
			detalle.setGanancia(gananciaProducto);
			detalle.setMontoGanancia(gananciaProducto);
			detalle.setPorcentajeGanancia(Utils.getPorcentajeGananciaProducto(detalleFacturaCommand.getPrecioUnitario(), detalleFacturaCommand.getCosto() != null ? detalleFacturaCommand.getCosto() : Constantes.ZEROS));
			detalle.setMontoGanancia(detalleFacturaCommand.getMontoGanancia() != null ? detalleFacturaCommand.getMontoGanancia() : Constantes.ZEROS_DOUBLE);
			detalle.setUsuario(usuario);
			detalleFacturaCommand.setTipoImpuesto(detalleFacturaCommand.getTipoImpuesto() != null ? detalleFacturaCommand.getTipoImpuesto() : Constantes.EMPTY);
			detalleFacturaCommand.setTipoImpuestoMag(detalleFacturaCommand.getTipoImpuestoMag() != null ? detalleFacturaCommand.getTipoImpuestoMag() : Constantes.EMPTY);
			detalle.setImpuesto(detalleFacturaCommand.getImpuesto() != null ? detalleFacturaCommand.getImpuesto() : Constantes.ZEROS_DOUBLE);
			detalle.setImpuestoMag(detalleFacturaCommand.getImpuestoMag() != null ? detalleFacturaCommand.getImpuestoMag() : Constantes.ZEROS_DOUBLE);
			detalle.setCodigoTarifa(articulo.getCodigoTarifa() != null ? articulo.getCodigoTarifa() : Constantes.EMPTY);
			detalle.setCodigoTarifaMag(articulo.getCodigoTarifaMag() != null ? articulo.getCodigoTarifaMag() : Constantes.EMPTY);
			detalle.setTipoImpuesto(detalleFacturaCommand.getTipoImpuesto() == null ? Constantes.EMPTY : detalleFacturaCommand.getTipoImpuesto());
			
			detalle.setFechaEmisionExoneracion(detalleFacturaCommand.getFechaEmisionExoneracion());
			detalle.setNombreInstitucionExoneracion(detalleFacturaCommand.getNombreInstitucionExoneracion() == null ? Constantes.EMPTY : detalleFacturaCommand.getNombreInstitucionExoneracion());
			detalle.setNumeroDocumentoExoneracion(detalleFacturaCommand.getNumeroDocumentoExoneracion() == null ? Constantes.EMPTY : detalleFacturaCommand.getNumeroDocumentoExoneracion());
			detalle.setTipoDocumentoExoneracion(detalleFacturaCommand.getTipoDocumentoExoneracion() == null ? Constantes.EMPTY : detalleFacturaCommand.getTipoDocumentoExoneracion());
			 
			detalle.setPorcentajeExoneracion(getPorcentajeExoneracion(detalleFacturaCommand.getPorcentajeExoneracion(), detalle.getImpuesto()));
			
			detalle.setMontoTotal(Utils.getMontoTotal(detalle.getPrecioUnitario(), detalle.getCantidad()));
			detalle.setMontoDescuento(Utils.getDescuento(detalle.getMontoTotal(), detalle.getPorcentajeDesc()));
			detalle.setSubTotal(Utils.getSubtotal(detalle.getMontoTotal(), detalle.getMontoDescuento()));
			detalle.setMontoImpuestoMag(Constantes.ZEROS_DOUBLE);
			detalle.setMontoExoneracion1(Constantes.ZEROS_DOUBLE);
			detalle.setMontoImpuesto(Utils.getMontoImpuesto(detalle.getSubTotal(), Constantes.ZEROS_DOUBLE, detalle.getMontoExoneracion(), detalle.getImpuesto()));
      
			detalle.setMontoExoneracion(Utils.getMontoExoneracionSubTotal(detalle.getTipoDocumentoExoneracion(),detalle.getImpuesto(), detalle.getPorcentajeExoneracion(), detalle.getSubTotal()));

			detalle.setMontoExoneracion1(Constantes.ZEROS_DOUBLE);

			detalle.setImpuestoNeto(Utils.getImpuestoNetoTotal(detalle.getMontoImpuesto(), detalle.getMontoExoneracion()));
			Integer baseImponible = articulo.getBaseImponible() != null ? articulo.getBaseImponible() : Constantes.ZEROS;
			detalle.setBaseImponible(Utils.Maximo5Decimales(Utils.getBaseImponibleTotal(articulo.getTipoImpuesto(), detalle.getSubTotal(), baseImponible)));
			if (detalle.getMontoDescuento() == null) {
				detalle.setMontoDescuento(Constantes.ZEROS_DOUBLE);
			}
			detalle.setNaturalezaDescuento(detalle.getMontoDescuento() > Constantes.ZEROS_DOUBLE ? Constantes.FORMATO_NATURALEZA_DESCUENTO : Constantes.EMPTY);
			detalle.setNumeroLinea(numeroLinea);
			detalle.setCreated_at(new Date());
			detalle.setUpdated_at(new Date());
			detalle.setTipoCodigo(articulo == null ? detalleFacturaCommand.getTipoCodigo() : articulo.getTipoCodigo());
			detalle.setUnidadMedida(articulo == null ? detalleFacturaCommand.getUnidadMedida() : articulo.getUnidadMedida());
			montoTotalLinea = Utils.getMontoTotalLinea(detalle.getSubTotal(), detalle.getImpuestoNeto());
			detalle.setMontoTotalLinea(montoTotalLinea);

			// cambios de doble impuesto

			totalServGravados = totalServGravados + Utils.getTotalServicioGravadosSubTotal(detalle.getTipoImpuesto(), detalle.getUnidadMedida(), detalle.getMontoTotal(), detalle.getImpuesto(), detalle.getPorcentajeExoneracion());
			totalMercanciasGravadas = totalMercanciasGravadas + Utils.getTotalMercanciasGravadasSubTotal(detalle.getTipoImpuesto(), detalle.getUnidadMedida(), detalle.getMontoTotal(), detalle.getImpuesto(), detalle.getPorcentajeExoneracion());
			totalServExonerado = totalServExonerado + Utils.getTotalServExoneradoSubTotal(detalle.getTipoImpuesto(), detalle.getUnidadMedida(), detalle.getPorcentajeExoneracion(), detalle.getImpuesto(), detalle.getMontoTotal());
			totalMercExonerada = totalMercExonerada + Utils.getTotalMercExoneradaSubTotal(detalle.getTipoImpuesto(), detalle.getUnidadMedida(), detalle.getPorcentajeExoneracion(), detalle.getImpuesto(), detalle.getMontoTotal());
			totalImpuesto = totalImpuesto + Utils.getTotalImpuesto(detalle.getImpuestoNeto());
			totalMercanciasExentas = totalMercanciasExentas + Utils.getTotalMercanciasExentas(detalle.getTipoImpuesto(), detalle.getUnidadMedida(), detalle.getMontoImpuesto(), Constantes.ZEROS_DOUBLE, detalle.getMontoTotal());

			totalServExentos = totalServExentos + Utils.getTotalServExentos(detalle.getTipoImpuesto(), detalle.getUnidadMedida(), detalle.getMontoImpuesto(), Constantes.ZEROS_DOUBLE, detalle.getMontoTotal());

			totalExento = totalExento + Utils.getTotalExentos(detalle.getTipoImpuesto(), detalle.getMontoImpuesto(), Constantes.ZEROS_DOUBLE, detalle.getMontoTotal());

			totalDescuentos = totalDescuentos + Utils.Maximo5Decimales(detalle.getMontoDescuento());

			totalComprobante = totalComprobante + montoTotalLinea;

			descuentoTotal = descuentoTotal + detalle.getMontoDescuento();
			subTotal = subTotal + detalle.getSubTotal();
			detalle.setNumeroLinea(numeroLinea);
			numeroLinea += 1;
			detalle.setFactura(factura);
			detalleDao.agregar(detalle);
			listaDetalles.add(detalle);
		}
		totalExonerado = totalExonerado + Utils.getTotalExonerado(totalServExonerado, totalMercExonerada);
		totalGravado = totalGravado + totalMercanciasGravadas + totalServGravados;
		totalVenta = totalVenta + totalExento + totalGravado + totalExonerado;
		if (factura.getTotalOtrosCargos() == null)

		{
			factura.setTotalOtrosCargos(Constantes.ZEROS_DOUBLE);
			factura.setTipoDocumentoOtroCargo(Constantes.EMPTY);
			factura.setDetalleOtroCargo(Constantes.EMPTY);
		}
		// Se agrega un detalle para el costo por servicio de restaurante y se afecta el
		// monto total de la factura
		if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) && facturaCommand.getMesa() != null && !facturaCommand.getMesa().getId().equals(0L) && facturaCommand.getMesa().getImpuestoServicio()) {
			factura.setTotalOtrosCargos(subTotal * 0.10);
			factura.setTipoDocumentoOtroCargo(Constantes.TIPO_DOCUMENTO_IMPUESTO_SERVICIO_OTRO_CARGO);
			factura.setDetalleOtroCargo(Constantes.IMPUESTO_SERVICIO_OTRO_CARGO_RESTAURANTE);
			totalOtrosCargos = totalOtrosCargos + factura.getTotalOtrosCargos();
			totalImpServicios = totalImpServicios + factura.getTotalOtrosCargos();
		}

		// Se actualoza el resumen de la factura con los detalles asociados
		totalVentaNeta = totalVenta - totalDescuentos;

		if (totalOtrosCargos > Constantes.ZEROS_DOUBLE) {
			factura.setTotalOtrosCargos(Utils.Maximo5Decimales(totalOtrosCargos));
		} else {
			if (factura.getTotalOtrosCargos() != null) {
				factura.setTotalOtrosCargos(Utils.Maximo5Decimales(factura.getTotalOtrosCargos()));
				totalImpServicios = totalImpServicios + factura.getTotalOtrosCargos();

			}
		}
		totalComprobante = factura.getTotalOtrosCargos() + totalImpuesto + totalVentaNeta;
		totalComprobante = totalComprobante - totalIVADevuelto;

		factura.setTotalServExonerado(Utils.Maximo5Decimales(Utils.aplicarRedondeo(totalServExonerado) ? Utils.roundFactura(totalServExonerado, 5) : totalServExonerado));
		factura.setTotalMercExonerada(Utils.Maximo5Decimales(Utils.aplicarRedondeo(totalMercExonerada) ? Utils.roundFactura(totalMercExonerada, 5) : totalMercExonerada));
		factura.setTotalExonerado(Utils.Maximo5Decimales(Utils.aplicarRedondeo(totalExonerado) ? Utils.roundFactura(totalExonerado, 6) : totalExonerado));
		factura.setTotalIVADevuelto(Utils.Maximo5Decimales(Utils.aplicarRedondeo(totalIVADevuelto) ? Utils.roundFactura(totalIVADevuelto, 5) : totalIVADevuelto));
		factura.setTotalMercanciasGravadas(Utils.Maximo5Decimales(Utils.aplicarRedondeo(totalMercanciasGravadas) ? Utils.roundFactura(totalMercanciasGravadas, 5) : totalMercanciasGravadas));
		factura.setTotalMercanciasExentas(Utils.Maximo5Decimales(Utils.aplicarRedondeo(totalMercanciasExentas) ? Utils.roundFactura(totalMercanciasExentas, 5) : totalMercanciasExentas));
		factura.setTotalServExentos(Utils.Maximo5Decimales(Utils.aplicarRedondeo(totalServExentos) ? Utils.roundFactura(totalServExentos, 5) : totalServExentos));
		factura.setTotalServGravados(Utils.Maximo5Decimales(Utils.aplicarRedondeo(totalServGravados) ? Utils.roundFactura(totalServGravados, 5) : totalServGravados));
		factura.setTotalGravado(Utils.Maximo5Decimales(Utils.aplicarRedondeo(totalGravado) ? Utils.roundFactura(totalGravado, 5) : totalGravado));
		factura.setTotalExento(Utils.Maximo5Decimales(Utils.aplicarRedondeo(totalExento) ? Utils.roundFactura(totalExento, 5) : totalExento));
		factura.setTotalVenta(Utils.Maximo5Decimales(Utils.aplicarRedondeo(totalVenta) ? Utils.roundFactura(totalVenta, 5) : totalVenta));
		factura.setTotalVentaNeta(Utils.Maximo5Decimales(Utils.aplicarRedondeo(totalVentaNeta) ? Utils.roundFactura(totalVentaNeta, 5) : totalVentaNeta));
		factura.setTotalDescuentos(Utils.Maximo5Decimales(Utils.aplicarRedondeo(totalDescuentos) ? Utils.roundFactura(totalDescuentos, 5) : totalDescuentos));
		factura.setTotalImpuesto(Utils.Maximo5Decimales(Utils.aplicarRedondeo(totalImpuesto) ? Utils.roundFactura(totalImpuesto, 5) : totalImpuesto));
		factura.setTotalImpuestoServicio(Utils.Maximo5Decimales(Utils.aplicarRedondeo(totalImpServicios) ? Utils.roundFactura(totalImpServicios, 5) : totalImpServicios));
		factura.setTotalComprobante(Utils.Maximo5Decimales(Utils.aplicarRedondeo(totalComprobante) ? Utils.roundFactura(totalComprobante, 5) : totalComprobante));

	}
	
	private Integer getPorcentajeExoneracion(Integer porcentajeExoneracion, Double impuesto) {
		Integer valor = Constantes.ZEROS;
		
		if(porcentajeExoneracion != null) {
			if(porcentajeExoneracion > Constantes.ZEROS) {
				if(impuesto.intValue() < porcentajeExoneracion ) {
           valor = impuesto.intValue(); 					
				}else {
					valor = porcentajeExoneracion;
				}
			}
			
			
		}
		
		
		return valor;
	}

	/**
	 * Actualiza inventari
	 * @param factura
	 * @param usuario
	 * @throws Exception
	 */
	@Transactional
	private void actualizaArticulosInventario(Factura factura, Usuario usuario) throws Exception {

		Collection<Detalle> detalles = detalleDao.findByFactura(factura);
		if (detalles != null) {
			for (Detalle detalle : detalles) {

				Articulo articulo = articuloDao.buscarPorCodigoYEmpresa(detalle.getCodigo(), usuario.getEmpresa());
				if (articulo != null) {
					if (articulo.getContable() != null) {
						if (articulo.getContable().equals(Constantes.CONTABLE_SI)) {
							aplicarInventario(factura, detalle, articulo);
						}
					}

				}
			}

		}
	}

	private String getNotaRazon(FacturaCommand facturaCommand, String consecutivo) {
		String resultado = Constantes.EMPTY;
		if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO) || facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO_INTERNO)) {
			resultado = "N.Credio:" + consecutivo;
		}
		if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO) || facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			resultado = "Fact/Tiq:" + consecutivo;
		}

		return resultado;
	}

	/**
	 * Aplicar el inventario si estado de la venta es facturada Toda nota credito se devuelve al inventario los productos
	 */
	@Transactional
	private void aplicarInventario(Factura factura, Detalle detalle, Articulo articulo) throws Exception {
		try {
			factura.setRebajaInventario(factura.getRebajaInventario() == null ? Constantes.ZEROS : factura.getRebajaInventario());
			if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				if (factura.getRebajaInventario().equals(Constantes.APLICA_SUMA_INVENTARIO_POR_NOTA)) {
					String leyenda = Constantes.MOTIVO_INGRESO_INVENTARIO_NOTA_CREDITO + factura.getNumeroConsecutivo();
					kardexDao.entrada(articulo, articulo.getCantidad(), detalle.getCantidad(), Constantes.EMPTY, factura.getNumeroConsecutivo().toString(), Constantes.KARDEX_TIPO_ENTRADA, leyenda, factura.getUsuarioCreacion());
				}
			} else {

				String leyenda = factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO_INTERNO) || factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO) ? Constantes.MOTIVO_SALIDA_INVENTARIO_NOTA_DEBITO + factura.getNumeroConsecutivo() : Constantes.MOTIVO_SALIDA_INVENTARIO_VENTA + factura.getNumeroConsecutivo();

				if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO_INTERNO) || factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
					if (factura.getRebajaInventario() != null) {
						if (factura.getRebajaInventario().equals(Constantes.APLICA_REBAJO_INVENTARIO_POR_NOTA)) {
							kardexDao.salida(articulo, articulo.getCantidad(), detalle.getCantidad(), Constantes.EMPTY, factura.getNumeroConsecutivo().toString(), Constantes.KARDEX_TIPO_SALIDA, leyenda, factura.getUsuarioCreacion());
						}
					}
				} else {
					kardexDao.salida(articulo, articulo.getCantidad(), detalle.getCantidad(), Constantes.EMPTY, factura.getNumeroConsecutivo().toString(), Constantes.KARDEX_TIPO_SALIDA, leyenda, factura.getUsuarioCreacion());
				}

			}

		} catch (Exception e) {
			log.error("** Error  aplicar en el inventario: " + e.getMessage() + " fecha " + new Date());
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
			if (facturaCommand.getTotalBanco() == null && facturaCommand.getTotalTarjeta() == null & facturaCommand.getTotalEfectivo() == null) {
				return facturaCommand.getTotalComprobante();
			}
			// Si hay montos en banco y tarjeta debe ser igual en efectivo
			if (facturaCommand.getTotalBanco() > 0 || facturaCommand.getTotalTarjeta() > 0) {
				resultado = facturaCommand.getTotalEfectivo();
			} else {
				resultado = facturaCommand.getTotalComprobante();
			}
		} catch (Exception e) {
			log.error("** Error  aplicar getTotalEfectivo : " + e.getMessage() + " fecha " + new Date());
			throw e;

		}

		return resultado;
	}

	@Override
	public TotalFacturaCommand sumarFacturas(Date fechaInicio, Date fechaFinal, Integer idEmpresa, Integer estado, String actividadEconomica) {
		return facturaDao.sumarFacturas(fechaInicio, fechaFinal, idEmpresa, estado, actividadEconomica);
	}

	@Override
	public Collection<Factura> facturasRangoEstado(Integer estado, Date fechaInicio, Date fechaFin, Integer idEmpresa, String actividadEconomica) {
		return facturaDao.facturasRangoEstado(estado, fechaInicio, fechaFin, idEmpresa, actividadEconomica);
	}

	public List<Object[]> proformasByState(Integer estado, Integer idEmpresa) {
		return facturaDao.proformasByState(estado, idEmpresa);
	}

	@Override
	public Collection<Factura> findByEnvioCorreoSimplificado(Integer estadoFirma) {

		return facturaDao.findByEnvioCorreoSimplificado(estadoFirma);
	}

	@Override
	public ArrayList<String> listaCorreosAsociadosFactura(Factura factura) {
		ArrayList<String> listaCorreos = new ArrayList<String>();
		if (factura.getCorreoAlternativo() != null) {
			if (!factura.getCorreoAlternativo().equals(Constantes.EMPTY)) {
				listaCorreos.add(factura.getCorreoAlternativo());
			}
		}
		if (!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE) && !factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_CREDITO)) {
			if (factura.getCliente().getCorreoElectronico() != null) {
				if (!factura.getCliente().getCorreoElectronico().equals(Constantes.EMPTY)) {
					listaCorreos.add(factura.getCliente().getCorreoElectronico());
				}
			}
			if (factura.getCliente().getCorreoElectronico1() != null) {
				if (!factura.getCliente().getCorreoElectronico1().equals(Constantes.EMPTY)) {
					listaCorreos.add(factura.getCliente().getCorreoElectronico1());
				}
			}
			if (factura.getCliente().getCorreoElectronico2() != null) {
				if (!factura.getCliente().getCorreoElectronico2().equals(Constantes.EMPTY)) {
					listaCorreos.add(factura.getCliente().getCorreoElectronico2());
				}

			}
			if (factura.getCliente().getCorreoElectronico3() != null) {
				if (!factura.getCliente().getCorreoElectronico3().equals(Constantes.EMPTY)) {
					listaCorreos.add(factura.getCliente().getCorreoElectronico3());
				}
			}
			String correo = Constantes.EMPTY;

			if (factura.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
				if (factura.getEmpresa().getCorreoCredito() != null) {
					if (!factura.getEmpresa().getCorreoCredito().equals(Constantes.EMPTY)) {
						correo = factura.getEmpresa().getCorreoCredito();
					}
				}
			}
			if (correo.equals(Constantes.EMPTY)) {
				listaCorreos.add(factura.getEmpresa().getCorreoElectronico());
			} else {
				listaCorreos.add(correo);
			}
		}

		return listaCorreos;

	}

	@Override
	public Collection<Factura> findBySinNotificarCorreo() {

		return facturaDao.findBySinNotificarCorreo();
	}

	@Override
	public TotalFacturaCommand sumarFacturasNoElectronica(Date fechaInicio, Date fechaFinal, Integer idEmpresa, Integer estado, String actividadEconomica) {

		return facturaDao.sumarFacturasNoElectronica(fechaInicio, fechaFinal, idEmpresa, estado, actividadEconomica);
	}

	@Override
	public ByteArrayInputStream createExcelFacturas(Collection<ListarFacturasNativa> facturas, Empresa empresa, String fechaInicio, String fechaFinal, Integer estado, Cliente cliente) throws IOException {
		List<String> headers = Arrays.asList("Fecha Emision", "Cedula", "Cliente", "A nombre", "Usuaro Responsable", "Actividad Economica", "Tipo Documento", "Condicion Venta", "# Documento", "#Proforma", "Exonerado", "Gravados", "Exentos", "Venta neta", "Impuesto", "Descuento", "Otros Cargos", "Total", "Tipo Moneda", "Tipo Cambio", "Total Colones", "Total efectivo", "Total Tarjeta ", "Total Banco", "Total Credito", "Nota");
		// Libro excel
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Map<String, CellStyle> styles = Utils.createStyles(workbook);
		Sheet sheet = workbook.createSheet("Utilidad");

		// title row
		Row title = sheet.createRow(0);
		title.setHeightInPoints(25);
		Cell titleCell = title.createCell(0);
		titleCell.setCellValue("Resumen de Ventas del " + fechaInicio + " al " + fechaFinal);
		titleCell.setCellStyle(styles.get("title1"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$Z$1"));

		Row titleEmpresa = sheet.createRow(1);
		titleEmpresa.setHeightInPoints(25);
		Cell titleCell1 = titleEmpresa.createCell(0);
		titleCell1.setCellValue(empresa.getNombre());
		titleCell1.setCellStyle(styles.get("title1"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$Z$2"));

		Row titleCedula = sheet.createRow(2);
		titleCedula.setHeightInPoints(25);
		Cell titleCell2 = titleCedula.createCell(0);
		titleCell2.setCellValue("Cedula:" + empresa.getCedula());
		titleCell2.setCellStyle(styles.get("title1"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$Z$3"));

		Row row = sheet.createRow(3);
		row.setHeightInPoints(25);
		Cell headerCell;
		for (int i = 0; i < headers.size(); i++) {
			headerCell = row.createCell(i);
			headerCell.setCellValue(headers.get(i));
			headerCell.setCellStyle(styles.get("header"));

		}
		int rownum = 4;

		for (ListarFacturasNativa listarFacturasNativa : facturas) {
			row = sheet.createRow(rownum);
			Cell cell = row.createCell(0);
			Utils.getCelSTR(cell, styles, listarFacturasNativa.getFechaEmisionSTR());

			cell = row.createCell(1);
			Utils.getCelSTR(cell, styles, listarFacturasNativa.getCedulaPrin());

			cell = row.createCell(2);
			Utils.getCelSTR(cell, styles, listarFacturasNativa.getNombreCompleto());

			cell = row.createCell(3);
			Utils.getCelSTR(cell, styles, listarFacturasNativa.getNombreFactura());

			cell = row.createCell(4);
			Utils.getCelSTR(cell, styles, listarFacturasNativa.getNombreUsuario());

			cell = row.createCell(5);
			Utils.getCelSTR(cell, styles, listarFacturasNativa.getCodigoActividad());

			cell = row.createCell(6);
			Utils.getCelSTR(cell, styles, listarFacturasNativa.getTipoDocSTR());

			cell = row.createCell(7);
			Utils.getCelSTR(cell, styles, listarFacturasNativa.getCondicionVentaSTR());

			cell = row.createCell(8);
			Utils.getCelSTR(cell, styles, listarFacturasNativa.getNumeroConsecutivo());

			cell = row.createCell(9);
			Utils.getCelSTR(cell, styles, listarFacturasNativa.getConsecutivoProforma());

			cell = row.createCell(10);
			Utils.getCel(cell, styles, listarFacturasNativa.getTotalExonerado());

			cell = row.createCell(11);
			Utils.getCel(cell, styles, listarFacturasNativa.getTotalGravado());

			cell = row.createCell(12);
			Utils.getCel(cell, styles, listarFacturasNativa.getTotalExento());

			cell = row.createCell(13);
			Utils.getCel(cell, styles, listarFacturasNativa.getTotalVentaNeta());

			cell = row.createCell(14);
			Utils.getCel(cell, styles, listarFacturasNativa.getTotalImpuesto());

			cell = row.createCell(15);
			Utils.getCel(cell, styles, listarFacturasNativa.getTotalDescuentos());

			cell = row.createCell(16);
			Utils.getCel(cell, styles, listarFacturasNativa.getTotalOtrosCargos());

			cell = row.createCell(17);
			Utils.getCel(cell, styles, listarFacturasNativa.getTotalComprobante());

			cell = row.createCell(18);
			Utils.getCelSTR(cell, styles, listarFacturasNativa.getCodigoMoneda());

			cell = row.createCell(19);
			Utils.getCel(cell, styles, listarFacturasNativa.getTipoCambio());

			cell = row.createCell(20);
			Utils.getCel(cell, styles, listarFacturasNativa.getTotalColonesNC());

			cell = row.createCell(21);
			Utils.getCel(cell, styles, listarFacturasNativa.getTotalEfectivoNC());

			cell = row.createCell(22);
			Utils.getCel(cell, styles, listarFacturasNativa.getTotalTarjetaNC());

			cell = row.createCell(23);
			Utils.getCel(cell, styles, listarFacturasNativa.getTotalBancoNC());

			cell = row.createCell(24);
			Utils.getCel(cell, styles, listarFacturasNativa.getTotalCreditoNC());

			cell = row.createCell(25);
			Utils.getCelSTR(cell, styles, listarFacturasNativa.getNota());

			rownum++;
		}

		int contnum = rownum;
		Row sumRow = sheet.createRow(rownum++);
		for (int j = 0; j <= 25; j++) {
			Cell cell = sumRow.createCell(j);
			if (j == 9) {
				cell.setCellValue("Totales  :");
				cell.setCellStyle(styles.get("formula"));

			}
			if (j <= 9 || j == 18 || j == 19 || j == 20 || j == 25) {
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 8000);
			}

			if (j == 10) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "K" + 4 + ":K" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 11) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "L" + 4 + ":L" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 12) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "M" + 4 + ":M" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 13) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "N" + 4 + ":N" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 14) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "O" + 4 + ":O" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 15) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "P" + 4 + ":P" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 16) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "Q" + 4 + ":Q" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 17) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "R" + 4 + ":R" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 20) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "U" + 4 + ":U" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 21) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "V" + 4 + ":V" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 22) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "W" + 4 + ":W" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 23) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "X" + 4 + ":X" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 24) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "Y" + 4 + ":Y" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}

		}

		workbook.write(stream);
		workbook.close();
		return new ByteArrayInputStream(stream.toByteArray());
	}

	@Override
	public ByteArrayInputStream createExcelFacturasTotalMensual(Collection<Factura> facturas, Empresa empresa, String fechaInicio, String fechaFinal, Integer estado, String actividadEconomica) throws IOException {
		List<String> headers = Arrays.asList("Fecha Emision", "Cedula", "Cliente", "A nombre", "Actividad Economica", "Tipo Documento", "Condicion Venta", "Fecha Credito", "# Documento", "#Proforma", "Gravados", "Exentos", "Venta neta", "Impuesto", "Descuento", "Otros Cargos", "Total", "Tipo Moneda", "Tipo Cambio", "Total Colones", "Total efectivo", "Total Tarjeta ", "Total Banco", "Total Credito", "Nota");
		// Libro excel
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Map<String, CellStyle> styles = Utils.createStyles(workbook);
		Sheet sheet = workbook.createSheet("Utilidad");

		String tituloActividad = "Todas";
		if (!actividadEconomica.equals(Constantes.COMBO_TODOS)) {
			tituloActividad = actividadEconomica;

		}
		Row titleActividad = sheet.createRow(0);
		titleActividad.setHeightInPoints(25);
		Cell titleCellA = titleActividad.createCell(0);
		titleCellA.setCellValue("Actividad Economica: " + tituloActividad);
		titleCellA.setCellStyle(styles.get("title1"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$Z$1"));

		// title row
		Row title = sheet.createRow(1);
		title.setHeightInPoints(25);
		Cell titleCell = title.createCell(0);
		titleCell.setCellValue("Resumen de Ventas del " + fechaInicio + " al " + fechaFinal);
		titleCell.setCellStyle(styles.get("title1"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$Z$2"));

		Row titleEmpresa = sheet.createRow(2);
		titleEmpresa.setHeightInPoints(25);
		Cell titleCell1 = titleEmpresa.createCell(0);
		titleCell1.setCellValue(empresa.getNombre());
		titleCell1.setCellStyle(styles.get("title1"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$Z$3"));

		Row titleCedula = sheet.createRow(3);
		titleCedula.setHeightInPoints(25);
		Cell titleCell2 = titleCedula.createCell(0);
		titleCell2.setCellValue("Cedula:" + empresa.getCedula());
		titleCell2.setCellStyle(styles.get("title1"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$Z$4"));

		Row row = sheet.createRow(4);
		row.setHeightInPoints(25);
		Cell headerCell;
		for (int i = 0; i < headers.size(); i++) {
			headerCell = row.createCell(i);
			headerCell.setCellValue(headers.get(i));
			headerCell.setCellStyle(styles.get("header"));

		}
		int rownum = 5;

		for (Factura factura : facturas) {
			row = sheet.createRow(rownum);
			Cell cell = row.createCell(0);
			Utils.getCelSTR(cell, styles, factura.getFechaEmisionSTR());

			cell = row.createCell(1);
			Utils.getCelSTR(cell, styles, factura.getCedulaCliente());

			cell = row.createCell(2);
			Utils.getCelSTR(cell, styles, factura.getNombreCliente());

			cell = row.createCell(3);
			Utils.getCelSTR(cell, styles, factura.getNombreFactura());

			cell = row.createCell(4);
			Utils.getCelSTR(cell, styles, factura.getCodigoActividad());

			cell = row.createCell(5);
			Utils.getCelSTR(cell, styles, factura.getTipoDocSTR());

			cell = row.createCell(6);
			Utils.getCelSTR(cell, styles, factura.getCondicionVentaSTR());

			cell = row.createCell(7);
			Utils.getCelSTR(cell, styles, factura.getFechaCreditoSTR());
			cell = row.createCell(8);
			Utils.getCelSTR(cell, styles, factura.getNumeroConsecutivo());

			cell = row.createCell(9);
			Utils.getCelSTR(cell, styles, factura.getConsecutivoProforma());

			cell = row.createCell(10);
			Utils.getCel(cell, styles, factura.getTotalGravadoNC());

			cell = row.createCell(11);
			Utils.getCel(cell, styles, factura.getTotalExentoNC());

			cell = row.createCell(12);
			Utils.getCel(cell, styles, factura.getTotalVentaNetaNC());

			cell = row.createCell(13);
			Utils.getCel(cell, styles, factura.getTotalImpuestoNC());

			cell = row.createCell(14);
			Utils.getCel(cell, styles, factura.getTotalDescuentosNC());

			cell = row.createCell(15);
			Utils.getCel(cell, styles, factura.getTotalOtrosCargosNC());

			cell = row.createCell(16);
			Utils.getCel(cell, styles, factura.getTotalComprobanteNC());

			cell = row.createCell(17);
			Utils.getCelSTR(cell, styles, factura.getCodigoMoneda());

			cell = row.createCell(18);
			Utils.getCel(cell, styles, factura.getTipoCambio());

			cell = row.createCell(19);
			Utils.getCel(cell, styles, factura.getTotalColonesNC());

			cell = row.createCell(20);
			Utils.getCel(cell, styles, factura.getTotalEfectivoNC());

			cell = row.createCell(21);
			Utils.getCel(cell, styles, factura.getTotalTarjetaNC());

			cell = row.createCell(22);
			Utils.getCel(cell, styles, factura.getTotalBancoNC());

			cell = row.createCell(23);
			Utils.getCel(cell, styles, factura.getTotalCreditoNC());

			cell = row.createCell(24);
			Utils.getCelSTR(cell, styles, factura.getNota());

			rownum++;
		}

		int contnum = rownum;
		Row sumRow = sheet.createRow(rownum++);
		for (int j = 0; j <= 25; j++) {
			Cell cell = sumRow.createCell(j);
			if (j == 9) {
				cell.setCellValue("Totales  :");
				cell.setCellStyle(styles.get("formula"));

			}
			if (j <= 9 || j == 18 || j == 19 || j == 17 || j == 20) {
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 8000);
			}
			if (j == 24) {
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 18000);
			}
			if (j == 2) {
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 18000);
			}
			if (j == 10) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "K" + 4 + ":K" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 11) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "L" + 5 + ":L" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 12) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "M" + 5 + ":M" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 13) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "N" + 5 + ":N" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 14) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "O" + 4 + ":O" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 15) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "P" + 5 + ":P" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 16) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "Q" + 5 + ":Q" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}

			if (j == 19) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "T" + 5 + ":T" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 20) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "U" + 5 + ":U" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 21) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "V" + 5 + ":V" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 22) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "W" + 5 + ":W" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}
			if (j == 23) {
				// the 10th cell contains sum over week days, e.g. SUM(C3:I3)
				String ref = "X" + 5 + ":X" + contnum;
				cell.setCellFormula("SUM(" + ref + ")");
				cell.setCellStyle(styles.get("formula"));
				sheet.setColumnWidth(j, 5000);
			}

		}

		workbook.write(stream);
		workbook.close();
		return new ByteArrayInputStream(stream.toByteArray());
	}
}