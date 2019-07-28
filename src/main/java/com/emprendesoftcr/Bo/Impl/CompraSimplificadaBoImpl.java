package com.emprendesoftcr.Bo.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.CompraSimplificadaBo;
import com.emprendesoftcr.Bo.EmpresaBo;
import com.emprendesoftcr.Dao.CompraSimplificadaDao;
import com.emprendesoftcr.Dao.DetalleCompraSimplificadaDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.CompraSimplificada;
import com.emprendesoftcr.modelo.DetalleCompraSimplificada;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.TipoCambio;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.CompraSimplificadaCommand;
import com.emprendesoftcr.web.command.DetalleCompraSimplificadaCommand;
import com.google.gson.Gson;

/**
 * Reglas de negocio para las compras hacia el inventario CompraBoImpl.
 * @author jose.
 * @since 27 may. 2018
 */

@EnableTransactionManagement
@Service("compraSimplificadaBo")
public class CompraSimplificadaBoImpl implements CompraSimplificadaBo {

	@Autowired
	private CompraSimplificadaDao							compraSimplificadaDao;

	@Autowired
	private DetalleCompraSimplificadaDao			detalleCompraSimplificadaDao;


//	@Autowired
//	private KardexDao							kardexDao;


	
	
	@Autowired
	private EmpresaBo							empresaBo;
	
	private Logger								log	= LoggerFactory.getLogger(this.getClass());
	
	private final ReentrantLock lock = new ReentrantLock();

	@Transactional
	@Override
	public void agregar(CompraSimplificada compraSimplificada) {
		compraSimplificadaDao.agregar(compraSimplificada);

	}
	
	
	@Override
	@Transactional
	public synchronized CompraSimplificada crearCompraSimplificada(CompraSimplificadaCommand compraSimplificadaCommand, Usuario usuario, TipoCambio tipoCambio) throws Exception {
		CompraSimplificada compraSimplificada = null;
		lock.lock();
		try {
			long id = Thread.currentThread().getId();
			System.out.println(String.format("--start transaccion--> Thread=%d %s", id, "Fecha:" + new Date()));

			Empresa empresa = empresaBo.buscar(compraSimplificadaCommand.getEmpresa().getId());
			// Se actualizan los datos de la factura command
			if (empresa.getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA) && !compraSimplificadaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
				compraSimplificadaCommand.setTipoDoc(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA);
			}
			compraSimplificadaCommand.setTotal(compraSimplificadaCommand.getTotal() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotal());
			compraSimplificadaCommand.setTotalBanco(compraSimplificadaCommand.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalBanco());
			compraSimplificadaCommand.setTotalCambio(compraSimplificadaCommand.getTotalCambio() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalCambio());
			compraSimplificadaCommand.setTotalComprobante(compraSimplificadaCommand.getTotalComprobante() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalComprobante());
			compraSimplificadaCommand.setTotalCambioPagar(compraSimplificadaCommand.getTotalCambioPagar() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalCambioPagar());
			compraSimplificadaCommand.setTotalCredito(compraSimplificadaCommand.getTotalCredito() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalCredito());
			compraSimplificadaCommand.setTotalDescuentos(compraSimplificadaCommand.getTotalDescuentos() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalDescuentos());
			compraSimplificadaCommand.setTotalEfectivo(compraSimplificadaCommand.getTotalEfectivo() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalEfectivo());
			compraSimplificadaCommand.setTotalExento(compraSimplificadaCommand.getTotalExento() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalExento());
			compraSimplificadaCommand.setTotalGravado(compraSimplificadaCommand.getTotalGravado() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalGravado());
			compraSimplificadaCommand.setTotalImpuesto(compraSimplificadaCommand.getTotalImpuesto() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalImpuesto());
			compraSimplificadaCommand.setTotalMercanciasExentas(compraSimplificadaCommand.getTotalMercanciasExentas() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalMercanciasExentas());
			compraSimplificadaCommand.setTotalMercanciasGravadas(compraSimplificadaCommand.getTotalMercanciasGravadas() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalMercanciasGravadas());
			compraSimplificadaCommand.setMedioPago(compraSimplificadaCommand.getMedioPago() == null ? Constantes.MEDIO_PAGO_EFECTIVO : compraSimplificadaCommand.getMedioPago());
			compraSimplificadaCommand.setMontoCambio(compraSimplificadaCommand.getMontoCambio() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getMontoCambio());
			compraSimplificadaCommand.setNumeroConsecutivo(compraSimplificadaCommand.getNumeroConsecutivo() == null ? Constantes.ZEROS : compraSimplificadaCommand.getNumeroConsecutivo());
			compraSimplificadaCommand.setPlazoCredito(compraSimplificadaCommand.getPlazoCredito() == null ? Constantes.ZEROS : compraSimplificadaCommand.getPlazoCredito());
			compraSimplificadaCommand.setCodigoMoneda(compraSimplificadaCommand.getCodigoMoneda() != null ? compraSimplificadaCommand.getCodigoMoneda() : Constantes.CODIGO_MONEDA_COSTA_RICA);
			compraSimplificadaCommand.setTotalTarjeta(compraSimplificadaCommand.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalTarjeta());
			// Proformas
			if (compraSimplificadaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				compraSimplificadaCommand.setEstado(Constantes.FACTURA_ESTADO_PROFORMAS);
			}
			// Tiquete de uso interno
			if (compraSimplificadaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE_USO_INTERNO)) {
				compraSimplificadaCommand.setEstado(Constantes.FACTURA_ESTADO_TIQUETE_USO_INTERNO);
			}
			// Se anula las facturas
			if (!compraSimplificadaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE_USO_INTERNO) && !compraSimplificadaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA) && !compraSimplificadaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE) && !compraSimplificadaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				if (compraSimplificadaCommand.getReferenciaNumero() != null && compraSimplificadaCommand.getReferenciaNumero() != Constantes.EMPTY) {
					CompraSimplificada compraSimplificadaAnular = findByConsecutivoAndEmpresa(compraSimplificadaCommand.getReferenciaNumero(), empresa);
					compraSimplificadaAnular = compraSimplificadaAnular == null ? compraSimplificadaDao.findByConsecutivoAndEmpresa(compraSimplificadaCommand.getReferenciaNumero(), usuario.getEmpresa()) : compraSimplificadaAnular;
					if (compraSimplificadaAnular != null) {
						compraSimplificadaAnular.setEstado(Constantes.FACTURA_ESTADO_ANULADA);
						modificar(compraSimplificadaAnular);
						compraSimplificadaCommand.setProveedorSimplificado(compraSimplificadaAnular.getProveedorSimplificado());
					}
				}
			}
			// Se forman los detalles command de las factura
			ArrayList<DetalleCompraSimplificadaCommand> detallesFacturaCommand = this.formaDetallesCommand(compraSimplificadaCommand);
			// --------------------------------------------- Se trabaja con el objeto a
			// registrar en bd -----------------------------------------------------
			// Se forma el objeto factura
			compraSimplificada = this.formaFactura(compraSimplificadaCommand, usuario);
			// Se asociando los detalles a la factura

			try {
				// Generar el consecutivo de venta
				if (compraSimplificadaCommand.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {

					compraSimplificada.setNumeroConsecutivo(empresaBo.generarConsecutivoCompraSimplificada(empresa, usuario));
					
					if (empresa.getNoFacturaElectronica() != null && empresa.getNoFacturaElectronica().equals(Constantes.SI_APLICA_FACTURA_ELECTRONICA)) {
						compraSimplificada.setClave(empresaBo.generaClaveFacturaTributacion(empresa, compraSimplificada.getNumeroConsecutivo(), FacturaElectronicaUtils.COMPROBANTE_ELECTRONICO_NORMAL));

						compraSimplificada.setEmpresa(empresa);
					}
				}
				// Verifica si esta facturado para cambiar el estado firma y enviar a crear el
				// xml en el proceso automatico
					if (compraSimplificada.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
						compraSimplificada.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_PENDIENTE);
					} else {
						compraSimplificada.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_EN_PROCESOS);
					}
				
				// Se almacena la factura, se deja en estado en proceso para que no lo tome los
				// procesos de hacienda
				if (compraSimplificada.getId() == null) {
					compraSimplificada.setCreated_at(new Date());
					agregar(compraSimplificada);
				} else {
					compraSimplificada.setCreated_at(new Date());
					modificar(compraSimplificada);
				}
				// Se asociando los detalles a la factura
				this.asociaDetallesFactura(compraSimplificada, compraSimplificadaCommand, usuario, detallesFacturaCommand);

				// Efectivo Banco Tarjeta
				if (!compraSimplificada.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
					if (compraSimplificada.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) || compraSimplificada.getEstado().equals(Constantes.FACTURA_ESTADO_TIQUETE_USO_INTERNO)) {
						// montos en ceros de pagar
						if (compraSimplificada.getTotalEfectivo().equals(Constantes.ZEROS_DOUBLE) && compraSimplificada.getTotalBanco().equals(Constantes.ZEROS_DOUBLE) && compraSimplificada.getTotalTarjeta().equals(Constantes.ZEROS_DOUBLE) && compraSimplificada.getTotalCredito().equals(Constantes.ZEROS_DOUBLE)) {
							if (!compraSimplificada.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
								compraSimplificada.setTotalEfectivo(compraSimplificada.getTotalComprobante());
								compraSimplificada.setTotalCambioPagar(Constantes.ZEROS_DOUBLE);
								compraSimplificada.setTotalTarjeta(Constantes.ZEROS_DOUBLE);
								compraSimplificada.setTotalBanco(Constantes.ZEROS_DOUBLE);
								compraSimplificada.setTotalCredito(Constantes.ZEROS_DOUBLE);
							}
						}
						// credito
						if (compraSimplificada.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
							compraSimplificada.setTotalEfectivo(Constantes.ZEROS_DOUBLE);
							compraSimplificada.setTotalCambioPagar(Constantes.ZEROS_DOUBLE);
							compraSimplificada.setTotalTarjeta(Constantes.ZEROS_DOUBLE);
							compraSimplificada.setTotalBanco(Constantes.ZEROS_DOUBLE);
							compraSimplificada.setTotalCredito(compraSimplificada.getTotalComprobante());
						}
						// Paga solo en efectivo
						if (!compraSimplificada.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO) && compraSimplificada.getTotalBanco().equals(Constantes.ZEROS_DOUBLE) && compraSimplificada.getTotalEfectivo() > Constantes.ZEROS_DOUBLE && compraSimplificada.getTotalTarjeta().equals(Constantes.ZEROS_DOUBLE)) {
							compraSimplificada.setTotalEfectivo(compraSimplificada.getTotalComprobante());
							compraSimplificada.setTotalTarjeta(Constantes.ZEROS_DOUBLE);
							compraSimplificada.setTotalBanco(Constantes.ZEROS_DOUBLE);
							compraSimplificada.setTotalCredito(Constantes.ZEROS_DOUBLE);
						}
						// Paga solo en banco
						if (!compraSimplificada.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO) && compraSimplificada.getTotalBanco() > Constantes.ZEROS_DOUBLE && compraSimplificada.getTotalEfectivo().equals(Constantes.ZEROS_DOUBLE) && compraSimplificada.getTotalTarjeta().equals(Constantes.ZEROS_DOUBLE)) {
							compraSimplificada.setTotalBanco(compraSimplificada.getTotalComprobante());
							compraSimplificada.setTotalEfectivo(Constantes.ZEROS_DOUBLE);
							compraSimplificada.setTotalTarjeta(Constantes.ZEROS_DOUBLE);
						}
						// Paga solo en tarjeta
						if (!compraSimplificada.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO) && compraSimplificada.getTotalBanco().equals(Constantes.ZEROS_DOUBLE) && compraSimplificada.getTotalEfectivo().equals(Constantes.ZEROS_DOUBLE) && compraSimplificada.getTotalTarjeta() > Constantes.ZEROS_DOUBLE) {
							compraSimplificada.setTotalBanco(Constantes.ZEROS_DOUBLE);
							compraSimplificada.setTotalEfectivo(Constantes.ZEROS_DOUBLE);
							compraSimplificada.setTotalTarjeta(compraSimplificada.getTotalComprobante());
						}
						// Paga a credito
						if (compraSimplificada.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
							compraSimplificada.setTotalBanco(Constantes.ZEROS_DOUBLE);
							compraSimplificada.setTotalEfectivo(Constantes.ZEROS_DOUBLE);
							compraSimplificada.setTotalTarjeta(Constantes.ZEROS_DOUBLE);
						}
						// Paga tarjeta y efectivo
						if (!compraSimplificada.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO) && compraSimplificada.getTotalBanco().equals(Constantes.ZEROS_DOUBLE) && compraSimplificada.getTotalEfectivo() > Constantes.ZEROS_DOUBLE && compraSimplificada.getTotalTarjeta() > Constantes.ZEROS_DOUBLE) {
							Double resultado = compraSimplificada.getTotalComprobante() - compraSimplificada.getTotalTarjeta();
							compraSimplificada.setTotalBanco(Constantes.ZEROS_DOUBLE);
							compraSimplificada.setTotalEfectivo(resultado);
						}
						// Paga tarjeta y banco
						if (!compraSimplificada.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO) && compraSimplificada.getTotalBanco() > Constantes.ZEROS_DOUBLE && compraSimplificada.getTotalEfectivo().equals(Constantes.ZEROS_DOUBLE) && compraSimplificada.getTotalTarjeta() > Constantes.ZEROS_DOUBLE) {
							Double resultado = compraSimplificada.getTotalComprobante() - compraSimplificada.getTotalTarjeta();
							compraSimplificada.setTotalBanco(resultado);
							compraSimplificada.setTotalEfectivo(Constantes.ZEROS_DOUBLE);
						}
						// Paga tarjeta banco y efectivo
						if (!compraSimplificada.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO) && compraSimplificada.getTotalBanco() > Constantes.ZEROS_DOUBLE && compraSimplificada.getTotalEfectivo() > Constantes.ZEROS_DOUBLE && compraSimplificada.getTotalTarjeta() > Constantes.ZEROS_DOUBLE) {
							Double resultado = compraSimplificada.getTotalComprobante() - compraSimplificada.getTotalTarjeta();
							resultado = resultado - compraSimplificada.getTotalBanco();
							compraSimplificada.setTotalEfectivo(resultado);
						}

//						// Se agrega solo si no existe en la caja de usuario, casos de reintentos
//						if (usuarioCajaFacturaDao.findByFacturaId(factura.getId()) == null) {
//							UsuarioCajaFactura usuarioCajaFactura = new UsuarioCajaFactura();
//							usuarioCajaFactura.setCreated_at(new Date());
//							usuarioCajaFactura.setUpdated_at(new Date());
//							usuarioCajaFactura.setFactura(factura);
//							usuarioCajaFactura.setUsuarioCaja(usuarioCaja);
//							usuarioCajaFacturaDao.agregar(usuarioCajaFactura);
//						}
					}
				}
				// Actualiza articulo y inventario
//				this.actualizaArticulosInventario(factura, usuario);
//
//				// Crear Credito del cliente
//				if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA) || factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
//					if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) || factura.getEstado().equals(Constantes.FACTURA_ESTADO_TIQUETE_USO_INTERNO)) {
//						if (factura.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
//							cuentaCobrarDao.crearCuentaXCobrar(factura);
//						}
//					}
//				}

			} catch (Exception e) {
				throw e;
			}

//			// Anulacion de la factura anterior
//			if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
//				if (factura.getReferenciaNumero() != null) {
//					if (factura.getReferenciaNumero() != Constantes.EMPTY) {
//						Factura facturaAnterior = findByConsecutivoAndEmpresa(factura.getReferenciaNumero(), empresa);
//						facturaAnterior = facturaAnterior == null ? facturaDao.findByClaveAndEmpresa(factura.getReferenciaNumero(), usuario.getEmpresa()) : facturaAnterior;
//						if (facturaAnterior != null) {
//							CuentaCobrar cuentaCobrar = cuentaCobrarDao.buscarPorConsecutivo(factura.getEmpresa(), facturaAnterior.getNumeroConsecutivo());
//							if (cuentaCobrar != null) {
//								// Eliminar la cuenta por cobrar si el tipo de anulacio es total
//								if (factura.getReferenciaCodigo().equals(Constantes.FACTURA_CODIGO_REFERENCIA_ANULA_DOCUMENTO)) {
//									cuentaCobrarDao.eliminar(cuentaCobrar);
//								} else if (!factura.getReferenciaCodigo().equals(Constantes.FACTURA_CODIGO_REFERENCIA_ANULA_DOCUMENTO)) {
//									cuentaCobrar.setTotal(Utils.roundFactura(factura.getTotalComprobante(), 2));
//									cuentaCobrar.setTotalSaldo(Utils.roundFactura(factura.getTotalComprobante(), 2));
//									cuentaCobrarDao.modificar(cuentaCobrar);
//
//								}
//							}
//
//							facturaAnterior.setEstado(Constantes.FACTURA_ESTADO_ANULADA);
//							modificar(facturaAnterior);
//							if (facturaAnterior.getClave() != null) {
//								Hacienda hacienda = haciendaDao.findByEmpresaAndClave(empresa, facturaAnterior.getClave());
//								if (hacienda != null) {
//									hacienda.setEstado(Constantes.HACIENDA_ESTADO_ANULADA);
//									haciendaDao.modificar(hacienda);
//								}
//							}
//						}
//					}
//				}
//			}
			System.out.println(String.format("--Finaliza transaccion--> Thread=%d %s", id, "Fecha:" + new Date()));

		} catch (Exception e) {
			log.error("** Error  crear la compraSimplificada: " + e.getMessage() + " fecha " + new Date());
			throw e;
		} finally {
			lock.unlock();
		}

		return compraSimplificada;
	}

	
	private Double getTotalEfectivo(CompraSimplificadaCommand compraSimplificadaCommand) throws Exception {
		Double resultado = Constantes.ZEROS_DOUBLE;
		try {
			if (compraSimplificadaCommand.getTotalBanco() == null && compraSimplificadaCommand.getTotalTarjeta() == null & compraSimplificadaCommand.getTotalEfectivo() == null) {
				return compraSimplificadaCommand.getTotalComprobante();
			}
			// Si hay montos en banco y tarjeta debe ser igual en efectivo
			if (compraSimplificadaCommand.getTotalBanco() > 0 || compraSimplificadaCommand.getTotalTarjeta() > 0) {
				resultado = compraSimplificadaCommand.getTotalEfectivo();
			} else {
				resultado = compraSimplificadaCommand.getTotalComprobante();
			}
		} catch (Exception e) {
			log.error("** Error  aplicar compraSimplificadaCommand : " + e.getMessage() + " fecha " + new Date());
			throw e;

		}

		return resultado;
	}



private void asociaDetallesFactura(CompraSimplificada compraSimplificada, CompraSimplificadaCommand compraSimplificadaCommand, Usuario usuario, ArrayList<DetalleCompraSimplificadaCommand> detalleComprasSimplificadaCommand) throws Exception {

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
	Double costo = Constantes.ZEROS_DOUBLE;

	Double montoTotalLinea = Constantes.ZEROS_DOUBLE;
	for (Iterator<DetalleCompraSimplificadaCommand> iterator = detalleComprasSimplificadaCommand.iterator(); iterator.hasNext();) {
		DetalleCompraSimplificadaCommand detalleCompraSimplificadaCommand = (DetalleCompraSimplificadaCommand) iterator.next();
		unidadMedida = Constantes.UNIDAD_MEDIDA;
		if (detalleCompraSimplificadaCommand.getUnidadMedida() != null) {
			if (detalleCompraSimplificadaCommand.getUnidadMedida().equals(Constantes.EMPTY)) {
				detalleCompraSimplificadaCommand.setUnidadMedida(unidadMedida);
			}
		} else {
			detalleCompraSimplificadaCommand.setUnidadMedida(unidadMedida);
		}
		DetalleCompraSimplificada detalleCompraSimplificada = new DetalleCompraSimplificada(detalleCompraSimplificadaCommand);
		detalleCompraSimplificada.setCosto(costo);
		detalleCompraSimplificada.setGanancia(gananciaProducto);
		detalleCompraSimplificada.setMontoGanancia(gananciaProducto);
		detalleCompraSimplificada.setPorcentajeGanancia(getPorcentajeGananciaProducto(detalleCompraSimplificadaCommand.getPrecioUnitario(), detalleCompraSimplificadaCommand.getCosto() != null ? detalleCompraSimplificadaCommand.getCosto() : Constantes.ZEROS));
		detalleCompraSimplificada.setUsuario(usuario);
		detalleCompraSimplificadaCommand.setTipoImpuesto(detalleCompraSimplificadaCommand.getTipoImpuesto() != null ? detalleCompraSimplificadaCommand.getTipoImpuesto() : Constantes.EMPTY);
		detalleCompraSimplificada.setImpuesto(detalleCompraSimplificadaCommand.getImpuesto() != null ? detalleCompraSimplificadaCommand.getImpuesto() : Constantes.ZEROS_DOUBLE);
		detalleCompraSimplificada.setCodigoTarifa(detalleCompraSimplificadaCommand.getCodigoTarifa() != null ?detalleCompraSimplificadaCommand.getCodigoTarifa()  : Constantes.EMPTY);
		detalleCompraSimplificada.setTipoImpuesto(!detalleCompraSimplificadaCommand.getTipoImpuesto().equals(Constantes.EMPTY) ? detalleCompraSimplificadaCommand.getTipoImpuesto() : Constantes.EMPTY);
		detalleCompraSimplificada.setFechaEmisionExoneracion(detalleCompraSimplificadaCommand.getFechaEmisionExoneracion());
		detalleCompraSimplificada.setNombreInstitucionExoneracion(detalleCompraSimplificadaCommand.getNombreInstitucionExoneracion() == null ? Constantes.EMPTY : detalleCompraSimplificadaCommand.getNombreInstitucionExoneracion());
		detalleCompraSimplificada.setNumeroDocumentoExoneracion(detalleCompraSimplificadaCommand.getNumeroDocumentoExoneracion() == null ? Constantes.EMPTY : detalleCompraSimplificadaCommand.getNumeroDocumentoExoneracion());
		detalleCompraSimplificada.setTipoDocumentoExoneracion(detalleCompraSimplificadaCommand.getTipoDocumentoExoneracion() == null ? Constantes.EMPTY : detalleCompraSimplificadaCommand.getTipoDocumentoExoneracion());
		detalleCompraSimplificada.setPorcentajeExoneracion(detalleCompraSimplificadaCommand.getPorcentajeExoneracion() == null ? Constantes.ZEROS : detalleCompraSimplificadaCommand.getPorcentajeExoneracion());

		detalleCompraSimplificada.setMontoTotal(getMontoTotal(detalleCompraSimplificadaCommand.getPrecioUnitario(), detalleCompraSimplificadaCommand.getCantidad()));
		detalleCompraSimplificada.setMontoDescuento(getDescuento(detalleCompraSimplificadaCommand.getMontoTotal(), detalleCompraSimplificadaCommand.getPorcentajeDesc()));
		detalleCompraSimplificada.setSubTotal(getSubtotal(detalleCompraSimplificadaCommand.getMontoTotal(), detalleCompraSimplificadaCommand.getMontoDescuento()));
		detalleCompraSimplificada.setMontoExoneracion(getMontoExoneracion(detalleCompraSimplificadaCommand.getTipoDocumentoExoneracion(), detalleCompraSimplificadaCommand.getPorcentajeExoneracion(), detalleCompraSimplificada.getMontoImpuesto()));

		detalleCompraSimplificada.setMontoImpuesto(getMontoImpuestoCon13(detalleCompraSimplificadaCommand.getSubTotal(),detalleCompraSimplificadaCommand.getMontoImpuesto(), detalleCompraSimplificadaCommand.getImpuesto()));

		detalleCompraSimplificada.setImpuestoNeto(getImpuestoNetoTotal(detalleCompraSimplificada.getTipoDocumentoExoneracion(), detalleCompraSimplificada.getPorcentajeExoneracion(), detalleCompraSimplificada.getMontoImpuesto(),  detalleCompraSimplificada.getMontoExoneracion()));

		detalleCompraSimplificada.setNaturalezaDescuento(detalleCompraSimplificadaCommand.getMontoDescuento() > Constantes.ZEROS_DOUBLE ? Constantes.FORMATO_NATURALEZA_DESCUENTO : Constantes.EMPTY);
		detalleCompraSimplificada.setNumeroLinea(numeroLinea);
		detalleCompraSimplificada.setCreated_at(new Date());
		detalleCompraSimplificada.setUpdated_at(new Date());
		detalleCompraSimplificada.setTipoCodigo(detalleCompraSimplificadaCommand.getTipoCodigo() != null ? detalleCompraSimplificadaCommand.getTipoCodigo() : Constantes.EMPTY);
		detalleCompraSimplificada.setUnidadMedida(detalleCompraSimplificadaCommand.getUnidadMedida() != null ? detalleCompraSimplificadaCommand.getUnidadMedida() : Constantes.EMPTY);

		// cambios de doble impuesto

		totalServGravados = totalServGravados + getTotalServicioGravados(detalleCompraSimplificada.getTipoImpuesto(), detalleCompraSimplificada.getUnidadMedida(), detalleCompraSimplificada.getMontoTotal(), detalleCompraSimplificada.getMontoImpuesto());
		totalServExonerado = totalServExonerado + getTotalServExonerado(detalleCompraSimplificada.getTipoImpuesto(), detalleCompraSimplificada.getUnidadMedida(), detalleCompraSimplificada.getMontoExoneracion());
		totalMercExonerada = totalMercExonerada + getTotalMercExonerada(detalleCompraSimplificada.getTipoImpuesto(), detalleCompraSimplificada.getUnidadMedida(), detalleCompraSimplificada.getMontoTotal(), detalleCompraSimplificada.getPorcentajeExoneracion());

		totalImpuesto = totalImpuesto + getTotalImpuesto(detalleCompraSimplificada.getMontoImpuesto(), detalleCompraSimplificada.getTipoDocumentoExoneracion(), detalleCompraSimplificada.getImpuestoNeto());
		totalMercanciasGravadas = totalMercanciasGravadas + getTotalMercanciasGravadas(detalleCompraSimplificada.getTipoImpuesto(), detalleCompraSimplificada.getUnidadMedida(), detalleCompraSimplificada.getMontoImpuesto(), detalleCompraSimplificada.getMontoTotal(), detalleCompraSimplificada.getPorcentajeExoneracion());
		totalMercanciasExentas = totalMercanciasExentas + getTotalMercanciasExentas(detalleCompraSimplificada.getTipoImpuesto(), detalleCompraSimplificada.getUnidadMedida(), detalleCompraSimplificada.getMontoImpuesto(),  detalleCompraSimplificada.getMontoTotal());

		totalServExentos = totalServExentos + getTotalServExentos(detalleCompraSimplificada.getTipoImpuesto(), detalleCompraSimplificada.getUnidadMedida(), detalleCompraSimplificada.getMontoImpuesto(), detalleCompraSimplificada.getMontoTotal());

		totalExento = totalExento + getTotalExentos(detalleCompraSimplificada.getMontoImpuesto(),  detalleCompraSimplificada.getMontoTotal());

		totalDescuentos = totalDescuentos + Utils.Maximo5Decimales(detalleCompraSimplificada.getMontoDescuento());
		montoTotalLinea = getMontoTotalLinea(detalleCompraSimplificada.getSubTotal(), detalleCompraSimplificada.getMontoImpuesto(),  detalleCompraSimplificada.getImpuestoNeto(), detalleCompraSimplificada.getTipoDocumentoExoneracion());
		totalComprobante = totalComprobante + montoTotalLinea;

		detalleCompraSimplificada.setMontoTotalLinea(montoTotalLinea);
		subTotal = subTotal + detalleCompraSimplificada.getSubTotal();
		detalleCompraSimplificada.setNumeroLinea(numeroLinea);
		numeroLinea += 1;
		detalleCompraSimplificada.setCompraSimplificada(compraSimplificada);
		detalleCompraSimplificadaDao.agregar(detalleCompraSimplificada);

	}
	totalExonerado = totalExonerado + getTotalExonerado(totalServExonerado, totalMercExonerada);
	totalGravado = totalGravado + totalMercanciasGravadas + totalServGravados;
	totalVenta = totalVenta + totalExento + totalGravado + totalExonerado;
	if (compraSimplificada.getTotalOtrosCargos() == null)

	{
		compraSimplificada.setTotalOtrosCargos(Constantes.ZEROS_DOUBLE);
		compraSimplificada.setTipoDocumentoOtroCargo(Constantes.EMPTY);
		compraSimplificada.setDetalleOtroCargo(Constantes.EMPTY);
	}

	// Se actualoza el resumen de la factura con los detalles asociados
	totalVentaNeta = totalVenta - totalDescuentos;

	if (totalOtrosCargos > Constantes.ZEROS_DOUBLE) {
		compraSimplificada.setTotalOtrosCargos(totalOtrosCargos);
	} else {
		if (compraSimplificada.getTotalOtrosCargos() != null) {
			compraSimplificada.setTotalOtrosCargos(compraSimplificada.getTotalOtrosCargos());
			totalImpServicios = totalImpServicios + compraSimplificada.getTotalOtrosCargos();

		}
	}
	totalComprobante = compraSimplificada.getTotalOtrosCargos() + totalImpuesto + totalVentaNeta;
	totalComprobante = totalComprobante - totalIVADevuelto;

	compraSimplificada.setTotalServExonerado(Utils.aplicarRedondeo(totalServExonerado) ? Utils.roundFactura(totalServExonerado, 5) : totalServExonerado);
	compraSimplificada.setTotalMercExonerada(Utils.aplicarRedondeo(totalMercExonerada) ? Utils.roundFactura(totalMercExonerada, 5) : totalMercExonerada);
	compraSimplificada.setTotalExonerado(Utils.aplicarRedondeo(totalExonerado) ? Utils.roundFactura(totalExonerado, 6) : totalExonerado);
	compraSimplificada.setTotalIVADevuelto(Utils.aplicarRedondeo(totalIVADevuelto) ? Utils.roundFactura(totalIVADevuelto, 5) : totalIVADevuelto);
	compraSimplificada.setTotalMercanciasGravadas(Utils.aplicarRedondeo(totalMercanciasGravadas) ? Utils.roundFactura(totalMercanciasGravadas, 5) : totalMercanciasGravadas);
	compraSimplificada.setTotalMercanciasExentas(Utils.aplicarRedondeo(totalMercanciasExentas) ? Utils.roundFactura(totalMercanciasExentas, 5) : totalMercanciasExentas);
	compraSimplificada.setTotalServExentos(Utils.aplicarRedondeo(totalServExentos) ? Utils.roundFactura(totalServExentos, 5) : totalServExentos);
	compraSimplificada.setTotalServGravados(Utils.aplicarRedondeo(totalServGravados) ? Utils.roundFactura(totalServGravados, 5) : totalServGravados);
	compraSimplificada.setTotalGravado(Utils.aplicarRedondeo(totalGravado) ? Utils.roundFactura(totalGravado, 5) : totalGravado);
	compraSimplificada.setTotalExento(Utils.aplicarRedondeo(totalExento) ? Utils.roundFactura(totalExento, 5) : totalExento);
	compraSimplificada.setTotalVenta(Utils.aplicarRedondeo(totalVenta) ? Utils.roundFactura(totalVenta, 5) : totalVenta);
	compraSimplificada.setTotalVentaNeta(Utils.aplicarRedondeo(totalVentaNeta) ? Utils.roundFactura(totalVentaNeta, 5) : totalVentaNeta);
	compraSimplificada.setTotalDescuentos(Utils.aplicarRedondeo(totalDescuentos) ? Utils.roundFactura(totalDescuentos, 5) : totalDescuentos);
	compraSimplificada.setTotalImpuesto(Utils.aplicarRedondeo(totalImpuesto) ? Utils.roundFactura(totalImpuesto, 5) : totalImpuesto);
	compraSimplificada.setTotalImpuestoServicio(Utils.aplicarRedondeo(totalImpServicios) ? Utils.roundFactura(totalImpServicios, 5) : totalImpServicios);
	compraSimplificada.setTotalComprobante(Utils.aplicarRedondeo(totalComprobante) ? Utils.roundFactura(totalComprobante, 5) : totalComprobante);

}


private Double getMontoTotalLinea(Double subTotal, Double montoImpuesto,  Double montoImpuestoNeto, String tipoDocumentoExoneracion) {
	Double resultado = Constantes.ZEROS_DOUBLE;

	if (!tipoDocumentoExoneracion.equals(Constantes.EMPTY)) {
		resultado = Utils.Maximo5Decimales(subTotal) + Utils.Maximo5Decimales(montoImpuestoNeto);
	} else {
		resultado = Utils.Maximo5Decimales(subTotal) + Utils.Maximo5Decimales(montoImpuesto) ;
	}

	return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;
}

private Double getTotalServExentos(String tipoImpuesto, String unidadMedida, Double montoImpuesto, Double subTotal) {
	Double resultado = Constantes.ZEROS_DOUBLE;
	Boolean esMercancia = Boolean.TRUE;

	if (!unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) && !unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) && !unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) && !unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
		esMercancia = Boolean.FALSE;
	}
	if (esMercancia) {

		if (montoImpuesto.equals(Constantes.ZEROS_DOUBLE)) {
			resultado = subTotal;
		}

	}
	return resultado;
}

/**
 * @param montoImpuesto
 * @param montoImpuesto1
 * @param subTotal
 * @return
 */
private Double getTotalExentos(Double montoImpuesto,Double subTotal) {
	Double resultado = Constantes.ZEROS_DOUBLE;
	if (montoImpuesto.equals(Constantes.ZEROS_DOUBLE) ) {
		resultado = subTotal;
	}
	return resultado;
}

/**
 * Total Mercancias Excentas
 * @param montoImpuesto
 * @param montoImpuesto1
 * @param subTotal
 * @return
 */
private Double getTotalMercanciasExentas(String tipoImpuesto, String unidadMedida, Double montoImpuesto,  Double subTotal) {
	Double resultado = Constantes.ZEROS_DOUBLE;
	Boolean esMercancia = Boolean.TRUE;


	if (unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
		esMercancia = Boolean.FALSE;
	}
	if (esMercancia) {

		if (montoImpuesto.equals(Constantes.ZEROS_DOUBLE)) {
			resultado = subTotal;
		}

	}
	return resultado;
}

private Double getTotalMercanciasGravadas(String tipoImpuesto, String unidadMedida, Double montoImpuesto, Double subTotal, Integer porcentajeExoneracion) {
	Double resultado = Constantes.ZEROS_DOUBLE;
	Boolean esMercancia = Boolean.TRUE;

	if (unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
		esMercancia = Boolean.FALSE;
	}
	if (esMercancia) {
		if ( montoImpuesto > Constantes.ZEROS_DOUBLE) {
			if (porcentajeExoneracion > Constantes.ZEROS) {
				Double porcentaValor = porcentajeExoneracion / 100d;
				porcentaValor = 1 - porcentaValor;
				resultado = subTotal * porcentaValor;
			} else {
				resultado = subTotal;
			}
		}

	}
	return resultado;
}

private Double getTotalImpuesto(Double montoImpuesto, String tipoDocumentoExoneracion, Double montoImpuestoNeto) {
	Double resultado = Constantes.ZEROS_DOUBLE;
	if (!tipoDocumentoExoneracion.equals(Constantes.EMPTY)) {
		resultado = montoImpuestoNeto;
	} else {
		resultado = Utils.Maximo5Decimales(montoImpuesto) ;
	}

	return resultado;
}

private Double getTotalExonerado(Double totalServExonerado, Double totalMercExonerada) {
	Double resultado = totalServExonerado + totalMercExonerada;
	return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;
}

private Double getTotalMercExonerada(String tipoImpuesto, String unidadMedida, Double montoTotal, Integer porcentajeExoneracion) {
	Double resultado = Constantes.ZEROS_DOUBLE;
	Boolean esGravado = Boolean.TRUE;
	if (unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
		esGravado = Boolean.FALSE;
	}
	if (esGravado && porcentajeExoneracion > Constantes.ZEROS) {
		Double porcentaValor = porcentajeExoneracion / 100d;
		resultado = montoTotal * porcentaValor;

	}
	return resultado;
}

/**
 * @param tipoImpuesto
 * @param unidadMedida
 * @param montoExonerado
 * @return
 */
private Double getTotalServExonerado(String tipoImpuesto, String unidadMedida, Double montoExonerado) {

	Double resultado = Constantes.ZEROS_DOUBLE;

	if (unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
		resultado = montoExonerado;
	}
	return resultado;
}

private Double getTotalServicioGravados(String tipoImpuesto, String unidadMedida, Double SubTotal, Double montoImpuesto) {
	Double resultado = Constantes.ZEROS_DOUBLE;
	if (montoImpuesto.equals(Constantes.ZEROS_DOUBLE) ) {
		return resultado;
	}

	if (unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
		resultado = SubTotal;
	}

	return resultado;
}



private Double getImpuestoNetoTotal(String tipoDocumentoExonerado, Integer porcentajeExonerado, Double montoImpuesto, Double montoExonerado) {
	Double resultado = Constantes.ZEROS_DOUBLE;
	if (!tipoDocumentoExonerado.equals(Constantes.EMPTY)) {
		if (porcentajeExonerado < 100) {
			resultado = Utils.Maximo5Decimales(montoImpuesto) ;
			resultado = resultado - Utils.Maximo5Decimales(montoExonerado);
		}

	} else {
		resultado = Utils.Maximo5Decimales(montoImpuesto) ;
	}

	return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;

}

/**
 * Monto de exonaracion = monto del impuesto * porcentaje de la exoneracion
 * @param tipodocumento
 * @param porcentajeExoneracion
 * @param montoImpuesto
 * @return
 */
private Double getMontoExoneracion(String tipoDocumentoExonerado, Integer porcentajeExoneracion, Double montoImpuesto) {
	if (tipoDocumentoExonerado.equals(Constantes.EMPTY)) {
		return Constantes.ZEROS_DOUBLE;
	}
	Double porcentaje = Double.parseDouble(porcentajeExoneracion.toString()) / 100;
	Double resultado = montoImpuesto * porcentaje;
	return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;
}

/**
 * Es la suma del subtotal + primer impuesto por la tarifa sacamos el impuesto 13
 * @param subTotal
 * @param montoPrimerImpuesto
 * @param tarifa
 * @return
 */
private Double getMontoImpuestoCon13(Double subTotal, Double montoPrimerImpuesto, Double tarifa) {
	Double valor = tarifa / 100d;
	Double resultado = Constantes.ZEROS_DOUBLE;
		resultado = subTotal + montoPrimerImpuesto;
		resultado = resultado * valor;
	return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;
}



/**
 * SubTotal = Monto Total - Monto Descuento
 * @param montoDescuento
 * @param montoTotal
 * @return
 */
private Double getSubtotal(Double montoTotal, Double montoDescuento) {

	Double resultado = Utils.Maximo5Decimales(montoTotal) - Utils.Maximo5Decimales(montoDescuento);
	return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;

}

/**
 * Total Descuento de la linea
 * @param montoTotal
 * @param porcentajeDescuento
 * @return
 */
private Double getDescuento(Double montoTotal, Double porcentajeDescuento) {
	Double valor = porcentajeDescuento / 100d;
	Double resultado = montoTotal * valor;
	return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;
}

/**
 * Monto Total
 * @param precioUnitario
 * @param cantidad
 * @return
 */
private Double getMontoTotal(Double precioUnitario, Double cantidad) {
	Double resultado = precioUnitario * cantidad;

	return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;
}



private Double getPorcentajeGananciaProducto(Double precioUnitario, Double costo) {
	// si el costo supera al precio unitario el costo es cero
	if (costo > precioUnitario) {
		return 100d;
	}

	Double resultado = costo / precioUnitario;
	resultado = 1 - resultado;

	return Utils.roundFactura(resultado * 100, 5);
}


	@Transactional
	private CompraSimplificada formaFactura(CompraSimplificadaCommand compraSimplificadaCommand, Usuario usuario) throws Exception {
		// Se forma objeto factura
		CompraSimplificada compraSimplificada = null;
		try {

			// Se busca la factura por id o se crea un nuevo objeto
			compraSimplificada = compraSimplificadaCommand.getId() == null || compraSimplificadaCommand.getId() == Constantes.ZEROS_LONG ? new CompraSimplificada() : compraSimplificadaDao.findById(compraSimplificadaCommand.getId());
			// Se complentan los datos de la factura
			compraSimplificada.setCondicionVenta(compraSimplificadaCommand.getCondicionVenta());

			// Fecha de credito
			if (compraSimplificadaCommand.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
				if (compraSimplificadaCommand.getFechaCredito() != null) {
					compraSimplificada.setFechaCredito(Utils.pasarADate(compraSimplificadaCommand.getFechaCredito(), "yyyy-MM-dd"));
					compraSimplificada.setPlazoCredito(compraSimplificadaCommand.getPlazoCredito() == null ? Constantes.CANTIDAD_DIAS_MINIMO_CREDITO : compraSimplificadaCommand.getPlazoCredito());
				}
			} else {
				compraSimplificada.setFechaCredito(null);
				compraSimplificada.setPlazoCredito(Constantes.ZEROS);
			}
			compraSimplificada.setTotalOtrosCargos(Constantes.ZEROS_DOUBLE);
			compraSimplificada.setTipoDocumentoOtroCargo(Constantes.EMPTY);
			compraSimplificada.setDetalleOtroCargo(Constantes.EMPTY);

			compraSimplificada.setVersionEsquemaXML(Constantes.ESQUEMA_XML_4_3);
			if (!compraSimplificadaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE_USO_INTERNO) && !compraSimplificadaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA) && !compraSimplificadaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE) && !compraSimplificadaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				compraSimplificada.setReferenciaTipoDoc(compraSimplificadaCommand.getReferenciaTipoDoc());
				compraSimplificada.setReferenciaNumero(compraSimplificadaCommand.getReferenciaNumero());
				compraSimplificada.setReferenciaCodigo(compraSimplificadaCommand.getReferenciaCodigo());
				compraSimplificada.setReferenciaRazon(compraSimplificadaCommand.getReferenciaRazon());
				if (compraSimplificadaCommand.getReferenciaFechaEmision() != null) {
					compraSimplificada.setReferenciaFechaEmision(Utils.parseDate2(compraSimplificadaCommand.getReferenciaFechaEmision()));
				}
				CompraSimplificada compraSimplificadaReferencia = compraSimplificadaDao.findByConsecutivoAndEmpresa(compraSimplificadaCommand.getReferenciaNumero(), usuario.getEmpresa());
		
				compraSimplificadaReferencia = compraSimplificadaReferencia == null ? compraSimplificadaDao.findByClaveAndEmpresa(compraSimplificadaCommand.getReferenciaNumero(), usuario.getEmpresa()) : compraSimplificadaReferencia;
				// Si la factura se encuentra en el sistema se agregan los datos propios de ella
				if (compraSimplificadaReferencia != null) {
					compraSimplificada.setReferenciaNumero(compraSimplificadaReferencia.getClave());
					compraSimplificada.setReferenciaTipoDoc(compraSimplificadaReferencia.getTipoDoc());
					compraSimplificada.setReferenciaFechaEmision(compraSimplificadaReferencia.getFechaEmision());
					compraSimplificada.setCodigoMoneda(compraSimplificadaReferencia.getCodigoMoneda());
					compraSimplificada.setTipoCambio(compraSimplificadaReferencia.getTipoCambio());
					compraSimplificada.setDetalleOtroCargo(compraSimplificadaReferencia.getDetalleOtroCargo() == null ? Constantes.EMPTY : compraSimplificadaReferencia.getDetalleOtroCargo());
					compraSimplificada.setTotalOtrosCargos(compraSimplificadaReferencia.getTotalOtrosCargos() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaReferencia.getTotalOtrosCargos());
					compraSimplificada.setTipoDocumentoOtroCargo(compraSimplificadaReferencia.getTipoDocumentoOtroCargo() == null ? Constantes.EMPTY : compraSimplificadaReferencia.getTipoDocumentoOtroCargo());

					if (compraSimplificadaReferencia.getVersionEsquemaXML().equals(Constantes.ESQUEMA_XML_4_2)) {
						compraSimplificada.setVersionEsquemaXML(Constantes.ESQUEMA_XML_4_2);
					}

				}

			} else {
				compraSimplificada.setVersionEsquemaXML(Constantes.ESQUEMA_XML_4_3);
				compraSimplificada.setReferenciaTipoDoc(Constantes.EMPTY);
				compraSimplificada.setReferenciaNumero(Constantes.EMPTY);
				compraSimplificada.setReferenciaCodigo(Constantes.EMPTY);
				compraSimplificada.setReferenciaRazon(Constantes.EMPTY);
				compraSimplificada.setFechaEmision(null);
			}
			compraSimplificada.setUsuarioCreacion(usuario);
			compraSimplificada.setEmpresa(usuario.getEmpresa());
			compraSimplificada.setProveedorSimplificado(compraSimplificadaCommand.getProveedorSimplificado());
			compraSimplificada.setFechaEmision(new Date());
			compraSimplificada.setMedioEfectivo(Constantes.EMPTY);
			compraSimplificada.setNombreFactura(compraSimplificadaCommand.getNombreFactura());
			compraSimplificada.setCodigoActividad(compraSimplificadaCommand.getCodigoActividad() == null ? usuario.getEmpresa().getCodigoActividad() : compraSimplificadaCommand.getCodigoActividad());
			if (compraSimplificadaCommand.getTotalEfectivo() > Constantes.ZEROS_DOUBLE) {
				compraSimplificada.setMedioEfectivo(Constantes.MEDIO_PAGO_EFECTIVO);
			}
			compraSimplificada.setMedioBanco(Constantes.EMPTY);
			if (compraSimplificadaCommand.getTotalBanco() > Constantes.ZEROS_DOUBLE) {
				compraSimplificada.setMedioBanco(Constantes.FACTURA_MEDIO_PAGO_TRANSFERENCIA);
			}
			compraSimplificada.setMedioTarjeta(Constantes.EMPTY);
			if (compraSimplificadaCommand.getTotalTarjeta() > Constantes.ZEROS_DOUBLE) {
				compraSimplificada.setMedioTarjeta(Constantes.FACTURA_MEDIO_PAGO_TARJETA);
			}
			compraSimplificada.setTipoDoc(compraSimplificadaCommand.getTipoDoc());
			compraSimplificada.setSubTotal(compraSimplificadaCommand.getSubTotal() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getSubTotal());
			compraSimplificada.setTotalServGravados(compraSimplificadaCommand.getTotalServGravados() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalServGravados());
			compraSimplificada.setTotalServExentos(compraSimplificadaCommand.getTotalServExentos() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalServExentos());
			compraSimplificada.setTotalMercanciasExentas(compraSimplificadaCommand.getTotalMercanciasExentas() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalMercanciasExentas());
			compraSimplificada.setTotalMercanciasGravadas(compraSimplificadaCommand.getTotalMercanciasGravadas() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalMercanciasGravadas());
			compraSimplificada.setTotalGravado(compraSimplificadaCommand.getTotalGravado() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalGravado());
			compraSimplificada.setTotalExento(compraSimplificadaCommand.getTotalExento() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalExento());
			compraSimplificada.setTotalVenta(compraSimplificadaCommand.getTotalVenta() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalVenta());
			compraSimplificada.setTotalDescuentos(compraSimplificadaCommand.getTotalDescuentos() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalDescuentos());
			compraSimplificada.setTotalVentaNeta(compraSimplificadaCommand.getTotalVentaNeta() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalVentaNeta());
			compraSimplificada.setTotalImpuesto(compraSimplificadaCommand.getTotalImpuesto() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalImpuesto());
			compraSimplificada.setTotalComprobante(compraSimplificadaCommand.getTotalComprobante() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalComprobante());
			compraSimplificada.setTotalEfectivo(getTotalEfectivo(compraSimplificadaCommand));
			compraSimplificada.setTotalTarjeta(compraSimplificadaCommand.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalTarjeta());
			compraSimplificada.setTotalBanco(compraSimplificadaCommand.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalBanco());
			compraSimplificada.setTotalCredito(compraSimplificadaCommand.getTotalCredito() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalCredito());
			compraSimplificada.setMontoCambio(compraSimplificadaCommand.getMontoCambio() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getMontoCambio());
			compraSimplificada.setTotalCambio(compraSimplificadaCommand.getTotalCambio() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalCambio());
			compraSimplificada.setTotalCambioPagar(compraSimplificadaCommand.getTotalCambioPagar() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalCambioPagar());
			if (compraSimplificadaCommand.getCodigoMoneda() != null) {
				// Costa Rica Colones
				if (compraSimplificadaCommand.getCodigoMoneda().equals(Constantes.CODIGO_MONEDA_COSTA_RICA)) {
					compraSimplificada.setCodigoMoneda(Constantes.CODIGO_MONEDA_COSTA_RICA);
					compraSimplificada.setTipoCambio(Constantes.CODIGO_MONEDA_COSTA_RICA_CAMBIO);
				} else if (compraSimplificadaCommand.getCodigoMoneda().equals(Constantes.CODIGO_MONEDA_DOLAR)) {// Dollar
					compraSimplificada.setCodigoMoneda(Constantes.CODIGO_MONEDA_DOLAR);
					compraSimplificada.setTipoCambio(compraSimplificadaCommand.getTipoCambioMoneda());
				}
			} else {
				compraSimplificada.setCodigoMoneda(Constantes.CODIGO_MONEDA_COSTA_RICA);
				compraSimplificada.setTipoCambio(Constantes.CODIGO_MONEDA_COSTA_RICA_CAMBIO);

			}
			compraSimplificada.setEstado(compraSimplificadaCommand.getEstado());
			compraSimplificada.setCreated_at(new Date());
			// }
			compraSimplificada.setFechaEmision(new Date());

		} catch (Exception e) {
			throw e;
		}
		return compraSimplificada;
	}
	
	private ArrayList<DetalleCompraSimplificadaCommand> formaDetallesCommand(CompraSimplificadaCommand compraSimplificadaCommand) throws Exception {
		// Detalles, se forma el detalle de la factura, se contabiliza los totales para
		// evitar problemas con el tema de los decimales en el front
		JSONObject json = null;
		ArrayList<DetalleCompraSimplificadaCommand> detallesFacturaCommand = new ArrayList<>();
		try {
			json = (JSONObject) new JSONParser().parse(compraSimplificadaCommand.getDetalleFactura());
			// Agregar Lineas de Detalle
			JSONArray jsonArrayDetalleFactura = (JSONArray) json.get("data");
			Gson gson = new Gson();
			if (jsonArrayDetalleFactura != null) {
				Integer numeroLinea = 1;
				for (int i = 0; i < jsonArrayDetalleFactura.size(); i++) {
					DetalleCompraSimplificadaCommand detalleCompraSimplificadaCommand = gson.fromJson(jsonArrayDetalleFactura.get(i).toString(), DetalleCompraSimplificadaCommand.class);
					detallesFacturaCommand.add(detalleCompraSimplificadaCommand);
					numeroLinea += 1;
				}
			}
		} catch (org.json.simple.parser.ParseException e) {
			throw e;
		}
		return detallesFacturaCommand;
	}



	

	/**
	 * Modificar una compra
	 * @see com.emprendesoftcr.Bo.CompraBo#modificar(com.emprendesoftcr.modelo.Compra)
	 */
	@Override
	@Transactional
	public void modificar(CompraSimplificada compra) {
		compraSimplificadaDao.modificar(compra);
	}

	/**
	 * Eliminar una compra
	 * @see com.emprendesoftcr.Bo.CompraBo#eliminar(com.emprendesoftcr.modelo.Compra)
	 */
	@Override
	@Transactional
	public void eliminar(CompraSimplificada compraSimplificad) {
		compraSimplificadaDao.eliminar(compraSimplificad);
	}

	/**
	 * Buscar por id
	 * @see com.emprendesoftcr.Bo.CompraBo#findById(java.lang.Integer)
	 */
	@Override
	public CompraSimplificada findById(Long id) {
		return compraSimplificadaDao.findById(id);
	}

	/**
	 * Buscar por consecutivo y empresa
	 * @see com.emprendesoftcr.Bo.CompraBo#findByConsecutivoAndEmpresa(java.lang.String, com.emprendesoftcr.modelo.Empresa)
	 */
	@Override
	public CompraSimplificada findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) {
		return compraSimplificadaDao.findByConsecutivoAndEmpresa(consecutivo, empresa);
	}

	/**
	 * Elimina los detalles de una compra
	 * @see com.emprendesoftcr.Bo.CompraBo#eliminarDetalleComprasPorSP(com.emprendesoftcr.modelo.Compra)
	 */
	@Override
	@Transactional
	public void eliminarDetalleComprasPorSP(CompraSimplificada compraSimplificad) throws Exception {
		compraSimplificadaDao.eliminarDetalleComprasPorSP(compraSimplificad);
	}

	

}