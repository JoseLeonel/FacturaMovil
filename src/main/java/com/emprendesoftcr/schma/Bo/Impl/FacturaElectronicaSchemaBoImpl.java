package com.emprendesoftcr.schma.Bo.Impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.CompraBo;
import com.emprendesoftcr.Bo.EmpresaBo;
import com.emprendesoftcr.Dao.ProveedorDao;
import com.emprendesoftcr.Dao.RecepcionFacturaDao;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.modelo.RecepcionFacturaDetalle;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.schma.Bo.FacturaElectronicaSchemaBo;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.xml.schema.factura.electronica.CodigoType;
import com.emprendesoftcr.xml.schema.factura.electronica.DescuentoType;
import com.emprendesoftcr.xml.schema.factura.electronica.EmisorType;
import com.emprendesoftcr.xml.schema.factura.electronica.FacturaElectronicaSchema;
import com.emprendesoftcr.xml.schema.factura.electronica.ImpuestoType;
import com.emprendesoftcr.xml.schema.factura.electronica.ReceptorType;

@EnableTransactionManagement
@Service("facturaElectronicaSchemaBo")
public class FacturaElectronicaSchemaBoImpl implements FacturaElectronicaSchemaBo {

	@Autowired
	RecepcionFacturaDao		recepcionFacturaDao;

	@Autowired
	private EmpresaBo			empresaBo;

	@Autowired
	private ProveedorDao	proveedorDao;

	@Autowired
	private CompraBo			compraBo;

	@Value("${path.upload.files.api}")
	private String				pathFacturaXML;

	private Logger				log	= LoggerFactory.getLogger(this.getClass());

	@Transactional
	@Override
	public void aplicarFacturaXMLToModelo(String ruta, Empresa empresa, Usuario usuarioSesion, String condicionImpuesto, Integer tipoGasto, String codigoActividad, String mensaje, String detalleMensaje) throws Exception {
		RecepcionFactura recepcionFactura = new RecepcionFactura();

		try {
			File file = new File(pathFacturaXML + "mr-automatico/" + ruta);

			JAXBContext jAXBContext = JAXBContext.newInstance(FacturaElectronicaSchema.class);

			Unmarshaller jaxbUnmarshaller = jAXBContext.createUnmarshaller();
			FacturaElectronicaSchema facturaElectronica = (FacturaElectronicaSchema) jaxbUnmarshaller.unmarshal(file);

			recepcionFactura.setMensaje(mensaje);
			recepcionFactura.setTipoGasto(tipoGasto);
			recepcionFactura.setDetalleMensaje(detalleMensaje == null ? "Aceptacion automatica de la compra" : detalleMensaje);
			recepcionFactura.setCondicionImpuesto(condicionImpuesto);
			recepcionFactura.setCodigoActividad(codigoActividad);

			recepcionFactura = getEncabezado(facturaElectronica, recepcionFactura);
			recepcionFactura = obtenerEmisor(facturaElectronica.getEmisor(), recepcionFactura);
			recepcionFactura = obtenerReceptor(facturaElectronica.getReceptor(), recepcionFactura);
			recepcionFactura = resumenFacturaTotal(facturaElectronica.getResumenFactura(), recepcionFactura);
			recepcionFactura.setVersion_doc("4.3");
			recepcionFactura.setCreated_at(new Date());
			recepcionFactura.setUpdated_at(new Date());
			recepcionFactura.setEmpresa(empresa);
			recepcionFactura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_PENDIENTE);
			recepcionFactura.setEstado(Constantes.FACTURA_ESTADO_FACTURADO);
			recepcionFactura.setNumeroConsecutivoReceptor(empresaBo.generarConsecutivoRecepcionFactura(usuarioSesion.getEmpresa(), usuarioSesion, recepcionFactura));
			if (recepcionFactura.getFacturaConsecutivo() != null && !recepcionFactura.getFacturaConsecutivo().equals(Constantes.EMPTY)) {
				recepcionFactura.setTipoDocEmisor(Utils.obtenerTipoDocumentoConsecutivo(recepcionFactura.getFacturaConsecutivo()));
				recepcionFactura.setTipoDoc(Utils.obtenerTipoDocumentoConsecutivo(recepcionFactura.getFacturaConsecutivo()));
			}
			if (recepcionFactura.getFacturaTipoCambio().equals(Constantes.ZEROS_DOUBLE) && recepcionFactura.getFacturaTipoCambio() != null) {
				if (recepcionFactura.getFacturaCodigoMoneda().equals(Constantes.CODIGO_MONEDA_COSTA_RICA)) {
					recepcionFactura.setFacturaTipoCambio(Constantes.CODIGO_MONEDA_COSTA_RICA_CAMBIO);
				} else {
					recepcionFactura.setFacturaTipoCambio(570d);
				}
			}

			if (recepcionFactura.getFacturaCodigoMoneda().equals(Constantes.CODIGO_MONEDA_COSTA_RICA) || recepcionFactura.getFacturaCodigoMoneda().equals(Constantes.EMPTY)) {
				recepcionFactura.setFacturaCodigoMoneda(Constantes.CODIGO_MONEDA_COSTA_RICA);
				recepcionFactura.setFacturaTipoCambio(Constantes.CODIGO_MONEDA_COSTA_RICA_CAMBIO);

			}
			recepcionFactura.setTotalImpuestoAcreditar(recepcionFactura.getFacturaTotalImpuestos());
			recepcionFactura.setTotalDeGastoAplicable(recepcionFactura.getFacturaTotalComprobante() - recepcionFactura.getFacturaTotalImpuestos());

			recepcionFacturaDao.agregar(recepcionFactura);
			Proveedor proveedor = proveedorDao.buscarPorCedulaYEmpresa(recepcionFactura.getEmisorCedula(), usuarioSesion.getEmpresa());

			if (proveedor == null) {
				proveedor = new Proveedor();
				proveedor.setCedula(recepcionFactura.getEmisorCedula());
				proveedor.setNombreCompleto(recepcionFactura.getEmisorNombreComercial() != null && recepcionFactura.getEmisorNombreComercial().equals(Constantes.EMPTY) ? recepcionFactura.getEmisorNombreComercial() : recepcionFactura.getEmisorNombre());
				proveedor.setCreated_at(new Date());
				proveedor.setEstado(Constantes.ESTADO_ACTIVO);
				proveedor.setEmail(recepcionFactura.getEmisorCorreo());
				proveedor.setDireccion(recepcionFactura.getEmisorOtraSena());
				proveedor.setMovil(recepcionFactura.getEmisorTelefono());
				proveedor.setRazonSocial(recepcionFactura.getEmisorNombre());
				proveedor.setRepresentante(Constantes.EMPTY);
				proveedor.setUpdated_at(new Date());
				proveedor.setId(null);
				proveedor.setEmpresa(usuarioSesion.getEmpresa());
				proveedorDao.agregar(proveedor);

			}else {
				proveedor.setEmail(recepcionFactura.getEmisorCorreo());
				proveedor.setNombreCompleto(recepcionFactura.getEmisorNombreComercial() != null && recepcionFactura.getEmisorNombreComercial().equals(Constantes.EMPTY) ? recepcionFactura.getEmisorNombreComercial() : recepcionFactura.getEmisorNombre());
				proveedor.setDireccion(recepcionFactura.getEmisorOtraSena());
				proveedor.setMovil(recepcionFactura.getEmisorTelefono());
				proveedor.setRazonSocial(recepcionFactura.getEmisorNombre());
				proveedorDao.modificar(proveedor);
			}

			// Agregar las linea de detalle de la factura electronica
			List<RecepcionFacturaDetalle> list = obtenerDetalle(facturaElectronica.getDetalleServicio());
			for (RecepcionFacturaDetalle recepcionFacturaDetalle : list) {
				recepcionFacturaDetalle.setRecepcionFactura(recepcionFactura);
				recepcionFacturaDao.agregar(recepcionFacturaDetalle);
			}
			if (recepcionFactura.getTipoGasto().equals(Constantes.TIPO_GASTO_ACEPTACION_COMPRAS_INVENTARIO) && !list.isEmpty() && list != null) {
				Collection<RecepcionFacturaDetalle> lista = recepcionFacturaDao.findByIdRecepcionFactura(recepcionFactura.getId());
				compraBo.crearCompra(recepcionFactura, usuarioSesion, proveedor, lista);
			}

		} catch (Exception e) {
			log.error(String.format("--error Compra aplicarFacturaXMLToModelo del xml :" + e.getMessage() + new Date()));
			throw e;
		}
	}

	/**
	 * Detalles de la factura
	 * @param detalles
	 * @return
	 * @throws Exception
	 */
	private List<RecepcionFacturaDetalle> obtenerDetalle(FacturaElectronicaSchema.DetalleServicio detalles) throws Exception {
		List<RecepcionFacturaDetalle> lista = new ArrayList<RecepcionFacturaDetalle>();
		try {
			if(detalles != null && !detalles.getLineaDetalle().isEmpty()) {
				for (FacturaElectronicaSchema.DetalleServicio.LineaDetalle detalle : detalles.getLineaDetalle()) {
					RecepcionFacturaDetalle recepcionFacturaDetalleNueva = new RecepcionFacturaDetalle();
					// codigos comerciales
					recepcionFacturaDetalleNueva = getCodigosComerciales(detalle, recepcionFacturaDetalleNueva);
					// ** Descuentos
					recepcionFacturaDetalleNueva = getDescuentosFactura(detalle.getDescuento(), recepcionFacturaDetalleNueva);
					// Impuestos
					recepcionFacturaDetalleNueva = getImpuestosFactura(detalle.getImpuesto(), recepcionFacturaDetalleNueva);
					// resumen final
					recepcionFacturaDetalleNueva = getDetalleResumen(detalle, recepcionFacturaDetalleNueva);

					lista.add(recepcionFacturaDetalleNueva);
				}
			}
		} catch (

		Exception e) {
			log.error(String.format("--error Compra formateda del xml->obtenerDetalle :" + e.getMessage() + new Date()));
			throw e;

		}
		return lista;

	}

	/**
	 * Impuestos de la linea de detalle
	 * @param impuestos
	 * @param recepcionFacturaDetalleNueva
	 * @return
	 */
	private RecepcionFacturaDetalle getImpuestosFactura(List<ImpuestoType> impuestos, RecepcionFacturaDetalle recepcionFacturaDetalleNueva) {
		try {
			if(impuestos != null && !impuestos.isEmpty()) {
				for (ImpuestoType impuestoType : impuestos) {
					if (recepcionFacturaDetalleNueva.getImpuestoCodigo() == null) {
						recepcionFacturaDetalleNueva.setImpuestoCodigo(impuestoType.getCodigo() != null ? impuestoType.getCodigo() : Constantes.EMPTY);
						recepcionFacturaDetalleNueva.setImpuestoTarifa(impuestoType.getTarifa() != null ? impuestoType.getTarifa().doubleValue() : Constantes.ZEROS_DOUBLE);
						recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa(impuestoType.getCodigoTarifa() != null ? impuestoType.getCodigoTarifa() : Constantes.EMPTY);
						recepcionFacturaDetalleNueva.setImpuestoMonto(impuestoType.getMonto() != null ? impuestoType.getMonto().doubleValue() : Constantes.ZEROS_DOUBLE);
					} else if (recepcionFacturaDetalleNueva.getImpuestoCodigo1() == null) {
						recepcionFacturaDetalleNueva.setImpuestoCodigo1(impuestoType.getCodigo() != null ? impuestoType.getCodigo() : Constantes.EMPTY);
						recepcionFacturaDetalleNueva.setImpuestoTarifa1(impuestoType.getTarifa() != null ? impuestoType.getTarifa().doubleValue() : Constantes.ZEROS_DOUBLE);
						recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa1(impuestoType.getCodigoTarifa() != null ? impuestoType.getCodigoTarifa() : Constantes.EMPTY);
						recepcionFacturaDetalleNueva.setImpuestoMonto1(impuestoType.getMonto() != null ? impuestoType.getMonto().doubleValue() : Constantes.ZEROS_DOUBLE);

					} else if (recepcionFacturaDetalleNueva.getImpuestoCodigo2() == null) {
						recepcionFacturaDetalleNueva.setImpuestoCodigo2(impuestoType.getCodigo() != null ? impuestoType.getCodigo() : Constantes.EMPTY);
						recepcionFacturaDetalleNueva.setImpuestoTarifa2(impuestoType.getTarifa() != null ? impuestoType.getTarifa().doubleValue() : Constantes.ZEROS_DOUBLE);
						recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa2(impuestoType.getCodigoTarifa() != null ? impuestoType.getCodigoTarifa() : Constantes.EMPTY);
						recepcionFacturaDetalleNueva.setImpuestoMonto2(impuestoType.getMonto() != null ? impuestoType.getMonto().doubleValue() : Constantes.ZEROS_DOUBLE);

					} else if (recepcionFacturaDetalleNueva.getImpuestoCodigo3() == null) {
						recepcionFacturaDetalleNueva.setImpuestoCodigo3(impuestoType.getCodigo() != null ? impuestoType.getCodigo() : Constantes.EMPTY);
						recepcionFacturaDetalleNueva.setImpuestoTarifa3(impuestoType.getTarifa() != null ? impuestoType.getTarifa().doubleValue() : Constantes.ZEROS_DOUBLE);
						recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa3(impuestoType.getCodigoTarifa() != null ? impuestoType.getCodigoTarifa() : Constantes.EMPTY);
						recepcionFacturaDetalleNueva.setImpuestoMonto3(impuestoType.getMonto() != null ? impuestoType.getMonto().doubleValue() : Constantes.ZEROS_DOUBLE);

					} else if (recepcionFacturaDetalleNueva.getImpuestoCodigo4() == null) {
						recepcionFacturaDetalleNueva.setImpuestoCodigo4(impuestoType.getCodigo() != null ? impuestoType.getCodigo() : Constantes.EMPTY);
						recepcionFacturaDetalleNueva.setImpuestoTarifa4(impuestoType.getTarifa() != null ? impuestoType.getTarifa().doubleValue() : Constantes.ZEROS_DOUBLE);
						recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa4(impuestoType.getCodigoTarifa() != null ? impuestoType.getCodigoTarifa() : Constantes.EMPTY);
						recepcionFacturaDetalleNueva.setImpuestoMonto4(impuestoType.getMonto() != null ? impuestoType.getMonto().doubleValue() : Constantes.ZEROS_DOUBLE);

					} else if (recepcionFacturaDetalleNueva.getImpuestoCodigo5() == null) {
						recepcionFacturaDetalleNueva.setImpuestoCodigo5(impuestoType.getCodigo() != null ? impuestoType.getCodigo() : Constantes.EMPTY);
						recepcionFacturaDetalleNueva.setImpuestoTarifa5(impuestoType.getTarifa() != null ? impuestoType.getTarifa().doubleValue() : Constantes.ZEROS_DOUBLE);
						recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa5(impuestoType.getCodigoTarifa() != null ? impuestoType.getCodigoTarifa() : Constantes.EMPTY);
						recepcionFacturaDetalleNueva.setImpuestoMonto5(impuestoType.getMonto() != null ? impuestoType.getMonto().doubleValue() : Constantes.ZEROS_DOUBLE);

					} else if (recepcionFacturaDetalleNueva.getImpuestoCodigo6() == null) {
						recepcionFacturaDetalleNueva.setImpuestoCodigo6(impuestoType.getCodigo() != null ? impuestoType.getCodigo() : Constantes.EMPTY);
						recepcionFacturaDetalleNueva.setImpuestoTarifa6(impuestoType.getTarifa() != null ? impuestoType.getTarifa().doubleValue() : Constantes.ZEROS_DOUBLE);
						recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa6(impuestoType.getCodigoTarifa() != null ? impuestoType.getCodigoTarifa() : Constantes.EMPTY);
						recepcionFacturaDetalleNueva.setImpuestoMonto6(impuestoType.getMonto() != null ? impuestoType.getMonto().doubleValue() : Constantes.ZEROS_DOUBLE);

					}
					recepcionFacturaDetalleNueva.setImpuestoExoneracionTipoDocumento(impuestoType.getExoneracion() != null ? impuestoType.getExoneracion().getTipoDocumento() : Constantes.EMPTY);
					recepcionFacturaDetalleNueva.setImpuestoExoneracionNumeroDocumento(impuestoType.getExoneracion() != null ? impuestoType.getExoneracion().getNumeroDocumento() : Constantes.EMPTY);
					recepcionFacturaDetalleNueva.setImpuestoExoneracionNombreInstitucion(impuestoType.getExoneracion() != null ? impuestoType.getExoneracion().getNombreInstitucion() : Constantes.EMPTY);
					recepcionFacturaDetalleNueva.setImpuestoExoneracionFechaEmision(impuestoType.getExoneracion() != null ? impuestoType.getExoneracion().getFechaEmision().toString() : null);
					recepcionFacturaDetalleNueva.setImpuestoExoneracionPorcentaje(impuestoType.getExoneracion() != null ? (double) impuestoType.getExoneracion().getPorcentajeExoneracion().intValue() : Constantes.ZEROS);
					recepcionFacturaDetalleNueva.setImpuestoExoneracionMonto(impuestoType.getExoneracion() != null ? impuestoType.getExoneracion().getMontoExoneracion().doubleValue() : Constantes.ZEROS_DOUBLE);
				}
				
			}

		} catch (Exception e) {
			log.error(String.format("--error Compra formateda del xml->getImpuestosFactura :" + e.getMessage() + new Date()));
			throw e;
		}
		return recepcionFacturaDetalleNueva;
	}

	/**
	 * Descuento de la linea de detalle
	 * @param descuentos
	 * @param recepcionFacturaDetalleNueva
	 * @return
	 */
	private RecepcionFacturaDetalle getDescuentosFactura(List<DescuentoType> descuentos, RecepcionFacturaDetalle recepcionFacturaDetalleNueva) {
		try {
			if(descuentos != null && !descuentos.isEmpty()) {
				for (DescuentoType descuentoType : descuentos) {
					if (recepcionFacturaDetalleNueva.getDescuentoMonto() != null && recepcionFacturaDetalleNueva.getDescuentoMonto().equals(Constantes.ZEROS_DOUBLE)) {
						recepcionFacturaDetalleNueva.setDescuentoMonto(descuentoType.getMontoDescuento() != null ? descuentoType.getMontoDescuento().doubleValue() : Constantes.ZEROS_DOUBLE);
						recepcionFacturaDetalleNueva.setDescuentoNaturaleza(descuentoType.getNaturalezaDescuento() != null ? descuentoType.getNaturalezaDescuento() : Constantes.EMPTY);

					}
				}
				
			}
		} catch (Exception e) {
			log.error(String.format("--error Compra formateda del xml->getDescuentosFactura :" + e.getMessage() + new Date()));
			throw e;

		}
		return recepcionFacturaDetalleNueva;
	}

	/**
	 * Codigo comercial
	 * @param lineaDetalle
	 * @param recepcionFacturaDetalleNueva
	 * @return
	 */
	private RecepcionFacturaDetalle getCodigosComerciales(FacturaElectronicaSchema.DetalleServicio.LineaDetalle lineaDetalle, RecepcionFacturaDetalle recepcionFacturaDetalleNueva) {
		try {
			if(lineaDetalle != null && !lineaDetalle.getCodigoComercial().isEmpty()) {
				for (CodigoType codigoType : lineaDetalle.getCodigoComercial()) {
					if (recepcionFacturaDetalleNueva.getCodigoComercialTipo() == null) {
						recepcionFacturaDetalleNueva.setCodigoComercialTipo(codigoType.getTipo() != null ? codigoType.getTipo() : Constantes.EMPTY);
						recepcionFacturaDetalleNueva.setCodigoComercialCodigo(codigoType.getCodigo() != null ? codigoType.getCodigo() : Constantes.EMPTY);
					} else if (recepcionFacturaDetalleNueva.getCodigoComercialTipo1() == null) {
						recepcionFacturaDetalleNueva.setCodigoComercialTipo1(codigoType.getTipo() != null ? codigoType.getTipo() : Constantes.EMPTY);
						recepcionFacturaDetalleNueva.setCodigoComercial1(codigoType.getCodigo() != null ? codigoType.getCodigo() : Constantes.EMPTY);
					} else if (recepcionFacturaDetalleNueva.getCodigoComercialTipo2() == null) {
						recepcionFacturaDetalleNueva.setCodigoComercialTipo2(codigoType.getTipo() != null ? codigoType.getTipo() : Constantes.EMPTY);
						recepcionFacturaDetalleNueva.setCodigoComercial2(codigoType.getCodigo() != null ? codigoType.getCodigo() : Constantes.EMPTY);

					}

				}
				
			}

		} catch (Exception e) {
			log.error(String.format("--error Compra formateda del xml->getCodigosComerciales :" + e.getMessage() + new Date()));
			throw e;

		}
		return recepcionFacturaDetalleNueva;
	}

	/**
	 * REsumen de cada detalle de la factura
	 * @param lineaDetalle
	 * @param recepcionFacturaDetalleNueva
	 * @return
	 */
	private  RecepcionFacturaDetalle getDetalleResumen(FacturaElectronicaSchema.DetalleServicio.LineaDetalle lineaDetalle, RecepcionFacturaDetalle recepcionFacturaDetalleNueva) {
		try {
			recepcionFacturaDetalleNueva.setNumeroLinea(lineaDetalle.getNumeroLinea() != null ? lineaDetalle.getNumeroLinea().intValue() : Constantes.ZEROS);
			recepcionFacturaDetalleNueva.setCodigoCabys(lineaDetalle.getCodigo());
			recepcionFacturaDetalleNueva.setCantidad(lineaDetalle.getCantidad() != null ? lineaDetalle.getCantidad().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFacturaDetalleNueva.setUnidadMedida(lineaDetalle.getUnidadMedida() != null ? lineaDetalle.getUnidadMedida() : Constantes.EMPTY);
			recepcionFacturaDetalleNueva.setDetalle(lineaDetalle.getDetalle() != null ? lineaDetalle.getDetalle() : Constantes.EMPTY);
			recepcionFacturaDetalleNueva.setPrecioUnitario(lineaDetalle.getPrecioUnitario() != null ? lineaDetalle.getPrecioUnitario().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFacturaDetalleNueva.setBaseImponible(lineaDetalle.getBaseImponible() != null ? lineaDetalle.getBaseImponible().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFacturaDetalleNueva.setMontoTotal(lineaDetalle.getMontoTotal() != null ? lineaDetalle.getMontoTotal().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFacturaDetalleNueva.setSubTotal(lineaDetalle.getSubTotal() != null ? lineaDetalle.getSubTotal().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFacturaDetalleNueva.setImpuestoNeto(lineaDetalle.getImpuestoNeto() != null ? lineaDetalle.getImpuestoNeto().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFacturaDetalleNueva.setMontoTotalLinea(lineaDetalle.getMontoTotalLinea() != null ? lineaDetalle.getMontoTotalLinea().doubleValue() : Constantes.ZEROS_DOUBLE);

		} catch (Exception e) {
			log.error(String.format("--error Compra formateda del xml->getDetalleResumen :" + e.getMessage() + new Date()));
			throw e;

		}
		return recepcionFacturaDetalleNueva;

	}

	/**
	 * Encabezados
	 * @param facturaElectronica
	 * @param recepcionFactura
	 * @return
	 * @throws Exception
	 */
	private  RecepcionFactura getEncabezado(FacturaElectronicaSchema facturaElectronica, RecepcionFactura recepcionFactura) throws Exception {

		try {

			recepcionFactura.setFacturaClave(facturaElectronica.getClave() != null ? facturaElectronica.getClave() : Constantes.EMPTY);
			recepcionFactura.setFacturaCodigoActividad(facturaElectronica.getCodigoActividad() != null ? facturaElectronica.getCodigoActividad() : Constantes.EMPTY);
			recepcionFactura.setFacturaConsecutivo(facturaElectronica.getNumeroConsecutivo() != null ? facturaElectronica.getNumeroConsecutivo() : Constantes.EMPTY);
			recepcionFactura.setFacturaFechaEmision(FacturaElectronicaUtils.parseStringtoISO8601Date(facturaElectronica.getFechaEmision().toString()));
			recepcionFactura.setFacturaCondicionVenta(facturaElectronica.getCondicionVenta() != null ? facturaElectronica.getCondicionVenta() : Constantes.EMPTY);
			recepcionFactura.setFacturaPlazoCredito(facturaElectronica.getPlazoCredito() != null ? facturaElectronica.getPlazoCredito() : Constantes.EMPTY);
			recepcionFactura.setFacturaMedioPago(facturaElectronica.getMedioPago().get(0));

		} catch (Exception e) {
			log.error(String.format("--error Compra formateda del xml->getEncabezado :" + e.getMessage() + new Date()));
			throw e;

		}
		return recepcionFactura;
	}

	/**
	 * Emisor
	 * @param emisorType
	 * @param recepcionFactura
	 * @return
	 */
	private RecepcionFactura obtenerEmisor(EmisorType emisorType, RecepcionFactura recepcionFactura) {
		try {
			recepcionFactura.setEmisorNombre(emisorType.getNombre());
			recepcionFactura.setEmisorNombreComercial(emisorType.getNombreComercial() != null? emisorType.getNombreComercial():emisorType.getNombre());
			
			recepcionFactura.setEmisorCorreo(emisorType.getCorreoElectronico());
			recepcionFactura.setEmisorTipoCedula(emisorType.getIdentificacion().getTipo());
			recepcionFactura.setEmisorCedula(emisorType.getIdentificacion().getNumero());
			recepcionFactura.setEmisorProvincia(emisorType.getUbicacion() != null?Utils.bigIntegerToStrig(emisorType.getUbicacion().getProvincia()):Constantes.EMPTY);
			recepcionFactura.setEmisorCanton(emisorType.getUbicacion() != null?Utils.bigIntegerToStrig(emisorType.getUbicacion().getCanton()):Constantes.EMPTY);
			recepcionFactura.setEmisorDistrito(emisorType.getUbicacion() != null?Utils.bigIntegerToStrig(emisorType.getUbicacion().getDistrito()):Constantes.EMPTY);
			recepcionFactura.setEmisorOtraSena(emisorType.getUbicacion() != null ?emisorType.getUbicacion().getOtrasSenas():Constantes.EMPTY);
			recepcionFactura.setEmisorTelefono(emisorType.getTelefono() != null ? Utils.bigIntegerToStrig(emisorType.getTelefono().getValue().getNumTelefono()):Constantes.EMPTY);
		} catch (Exception e) {
			log.error(String.format("--error Compra formateda del xml->obtenerEmisor :" + e.getMessage() + new Date()));
			throw e;

		}
		return recepcionFactura;
	}

	private RecepcionFactura obtenerReceptor(ReceptorType receptorType, RecepcionFactura recepcionFactura) throws Exception {
		try {
			recepcionFactura.setReceptorNombre(receptorType.getNombre());
			recepcionFactura.setReceptorNombreComercial(receptorType.getNombreComercial() != null?receptorType.getNombreComercial():receptorType.getNombre());
			recepcionFactura.setReceptorCorreo(receptorType.getCorreoElectronico());
			recepcionFactura.setReceptorTipoCedula(receptorType.getIdentificacion().getTipo());
			recepcionFactura.setReceptorCedula(receptorType.getIdentificacion().getNumero());
			recepcionFactura.setReceptorProvincia(receptorType.getUbicacion() != null? Utils.bigIntegerToStrig(receptorType.getUbicacion().getProvincia()):Constantes.EMPTY);
			recepcionFactura.setReceptorCanton(receptorType.getUbicacion() != null?Utils.bigIntegerToStrig(receptorType.getUbicacion().getCanton()):Constantes.EMPTY);
			recepcionFactura.setReceptorDistrito(receptorType.getUbicacion() != null?Utils.bigIntegerToStrig(receptorType.getUbicacion().getDistrito()):Constantes.EMPTY);
			recepcionFactura.setReceptorOtraSena(receptorType.getUbicacion() != null?receptorType.getUbicacion().getOtrasSenas():Constantes.EMPTY);
		} catch (Exception e) {
			log.error(String.format("--error Compra formateda del xml->obtenerReceptor :" + e.getMessage() + new Date()));
			throw e;

		}
		return recepcionFactura;
	}

	/**
	 * Resumen de la factura
	 * @param resumenFactura
	 * @param recepcionFactura
	 * @return
	 * @throws Exception
	 */
	private RecepcionFactura resumenFacturaTotal(FacturaElectronicaSchema.ResumenFactura resumenFactura, RecepcionFactura recepcionFactura) throws Exception {
		try {

			recepcionFactura.setFacturaCodigoMoneda(resumenFactura.getCodigoTipoMoneda() != null?resumenFactura.getCodigoTipoMoneda().getCodigoMoneda():Constantes.EMPTY);
			recepcionFactura.setFacturaTipoCambio(resumenFactura.getCodigoTipoMoneda() != null ? resumenFactura.getCodigoTipoMoneda().getTipoCambio().doubleValue() : Constantes.ZEROS_DOUBLE);

			if (recepcionFactura.getFacturaCodigoMoneda().equals(Constantes.EMPTY) && recepcionFactura.getFacturaCodigoMoneda() != null) {
				recepcionFactura.setFacturaCodigoMoneda(Constantes.CODIGO_MONEDA_COSTA_RICA);
				recepcionFactura.setFacturaTipoCambio(Constantes.CODIGO_MONEDA_COSTA_RICA_CAMBIO);
			}
			recepcionFactura.setFacturaTipoCambio(recepcionFactura.getFacturaTipoCambio() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTipoCambio());

			if (recepcionFactura.getFacturaTipoCambio().equals(Constantes.ZEROS_DOUBLE)) {
				recepcionFactura.setFacturaCodigoMoneda(Constantes.CODIGO_MONEDA_COSTA_RICA);
				recepcionFactura.setFacturaTipoCambio(Constantes.CODIGO_MONEDA_COSTA_RICA_CAMBIO);
			}
			recepcionFactura.setFacturaTotalServExentos(resumenFactura.getTotalServExentos() != null ? resumenFactura.getTotalServExentos().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFactura.setFacturaTotalServGravados(resumenFactura.getTotalServGravados() != null ? resumenFactura.getTotalServGravados().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFactura.setFacturaTotalServExonerado(resumenFactura.getTotalServExonerado() != null ? resumenFactura.getTotalServExonerado().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFactura.setFacturaTotalMercExonerada(resumenFactura.getTotalMercExonerada() != null ? resumenFactura.getTotalMercExonerada().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFactura.setFacturaTotalMercanciasGravadas(resumenFactura.getTotalMercanciasGravadas() != null ? resumenFactura.getTotalMercanciasGravadas().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFactura.setFacturaTotalMercanciasExentas(resumenFactura.getTotalMercanciasExentas() != null ? resumenFactura.getTotalMercanciasExentas().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFactura.setFacturaTotalGravado(resumenFactura.getTotalGravado() != null ? resumenFactura.getTotalGravado().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFactura.setFacturaTotalExento(resumenFactura.getTotalExento() != null ? resumenFactura.getTotalExento().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFactura.setFacturaTotalExonerado(resumenFactura.getTotalExonerado() != null ? resumenFactura.getTotalExonerado().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFactura.setFacturaTotalVenta(resumenFactura.getTotalVenta() != null ? resumenFactura.getTotalVenta().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFactura.setFacturaTotalVentaNeta(resumenFactura.getTotalVentaNeta() != null ? resumenFactura.getTotalVentaNeta().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFactura.setFacturaTotalDescuentos(resumenFactura.getTotalDescuentos() != null ? resumenFactura.getTotalDescuentos().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFactura.setFacturaTotalImpuestos(resumenFactura.getTotalImpuesto() != null ? resumenFactura.getTotalImpuesto().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFactura.setFacturaTotalIVADevuelto(resumenFactura.getTotalIVADevuelto() != null ? resumenFactura.getTotalIVADevuelto().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFactura.setFacturaTotalComprobante(resumenFactura.getTotalComprobante() != null ? resumenFactura.getTotalComprobante().doubleValue() : Constantes.ZEROS_DOUBLE);
			recepcionFactura.setFacturaTotalOtrosCargos(resumenFactura.getTotalOtrosCargos() != null ? resumenFactura.getTotalOtrosCargos().doubleValue() : Constantes.ZEROS_DOUBLE);

		} catch (Exception e) {
			log.error(String.format("--error Compra formateda del xml->resumenFacturaTotal :" + e.getMessage() + new Date()));
			throw e;
		}

		return recepcionFactura;
	}

}
