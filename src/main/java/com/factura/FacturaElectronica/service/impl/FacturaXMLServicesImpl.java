package com.factura.FacturaElectronica.service.impl;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.Utils.Utils;
import com.factura.FacturaElectronica.modelo.Cliente;
import com.factura.FacturaElectronica.modelo.Detalle;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Factura;
import com.factura.FacturaElectronica.service.FacturaXMLServices;
import com.factura.FacturaElectronica.xml.CodigoType;
import com.factura.FacturaElectronica.xml.EmisorType;
import com.factura.FacturaElectronica.xml.FacturaElectronica;
import com.factura.FacturaElectronica.xml.IdentificacionType;
import com.factura.FacturaElectronica.xml.ReceptorType;
import com.factura.FacturaElectronica.xml.TelefonoType;
import com.factura.FacturaElectronica.xml.UbicacionType;

@Service("facturaXMLServices")
@Transactional
public class FacturaXMLServicesImpl implements FacturaXMLServices {

	public static final String CODIGO_PRODUCTO_VENDEDOR = "01";

	public void generarFacturaElectronicaTributacionXML(Factura factura) {
		try {

			// necesito hacer un contexto se crea la instancia
			JAXBContext context = JAXBContext.newInstance(FacturaElectronica.class);

			// Escribir el xml se utiliza el objete marshaller
			Marshaller marshaller = context.createMarshaller();
			FacturaElectronica facturaElectronica = crearFacturaElectronica(factura);
			// tabular el documento xml
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// salida
			marshaller.marshal(facturaElectronica, System.out);

			marshaller.marshal(facturaElectronica, new FileWriter("leo.xml"));

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private FacturaElectronica crearFacturaElectronica(Factura factura) {
		FacturaElectronica facturaElectronica = new FacturaElectronica();
		facturaElectronica = encabezado(factura, facturaElectronica);
		// Emisor
		EmisorType emisorType = new EmisorType();
		emisorType = getEmisorType(factura.getEmpresa());
		facturaElectronica.setEmisor(emisorType);
		// Receptor
		ReceptorType receptorType = new ReceptorType();
		receptorType = getReceptorType(factura.getCliente());
		facturaElectronica.setReceptor(receptorType);
		// Detalle
		facturaElectronica.setDetalleServicio(detallesFactura(factura));
		// Resumen de la Factura
		facturaElectronica.setResumenFactura(getResumen(factura));

		return facturaElectronica;
	}

	/**
	 * Encabezado de la factura
	 * @param factura
	 * @param facturaElectronica
	 * @return
	 */
	private FacturaElectronica encabezado(Factura factura, FacturaElectronica facturaElectronica) {

		// Encabezado
		facturaElectronica.setClave(factura.getClave());
		facturaElectronica.setNumeroConsecutivo(factura.getNumeroConsecutivo());
		facturaElectronica.setFechaEmision(Utils.formatoXMLGregorianCalendar(factura.getFechaEmision()));
		facturaElectronica.setCondicionVenta(factura.getCondicionVenta());
		facturaElectronica.setPlazoCredito(factura.getPlazoCredito().toString());

		return facturaElectronica;
	}

	/**
	 * Se define el emisor de la factura
	 * @param empresa
	 * @return
	 */
	private EmisorType getEmisorType(Empresa empresa) {
		EmisorType emisorType = new EmisorType();
		emisorType.setNombre(empresa.getNombre());
		emisorType.setCorreoElectronico(empresa.getCorreoElectronico());

		IdentificacionType identificacionType = new IdentificacionType();
		identificacionType.setNumero(empresa.getCedula());
		identificacionType.setTipo(empresa.getTipoCedula());
		emisorType.setIdentificacion(identificacionType);
		emisorType.setNombreComercial(empresa.getNombreComercial());
		// Telefono
		TelefonoType telefonoType = new TelefonoType();
		BigInteger telefono = BigInteger.valueOf(empresa.getTelefono() == null?Constantes.ZEROS:empresa.getTelefono());
		telefonoType.setNumTelefono(telefono);
		BigInteger codigoPais = BigInteger.valueOf(empresa.getCodigoPais()== null?Constantes.ZEROS:empresa.getCodigoPais());
		telefonoType.setCodigoPais(codigoPais);

		JAXBElement<TelefonoType> jaxbElement = new JAXBElement<TelefonoType>(new QName(TelefonoType.class.getSimpleName()), TelefonoType.class, telefonoType);
		emisorType.setTelefono(jaxbElement);
		// Ubicacion
		UbicacionType ubicacionType = new UbicacionType();
		ubicacionType.setBarrio(empresa.getBarrio());
		ubicacionType.setCanton(empresa.getCanton());
		ubicacionType.setProvincia(empresa.getProvincia());
		ubicacionType.setOtrasSenas(empresa.getOtraSenas());
		emisorType.setUbicacion(ubicacionType);

		return emisorType;

	}

	/**
	 * Obtener la informacion del cliente
	 * @param cliente
	 * @return
	 */
	private ReceptorType getReceptorType(Cliente cliente) {
		ReceptorType receptorType = new ReceptorType();

		// Receptor
		receptorType.setNombre(cliente.getNombreCompleto());
		receptorType.setNombreComercial(cliente.getNombreComercial());
		receptorType.setCorreoElectronico(cliente.getCorreoElectronico());
		IdentificacionType identificacionType = new IdentificacionType();
		identificacionType.setNumero(cliente.getCedula());
		identificacionType.setTipo(cliente.getTipoCedula());
		receptorType.setIdentificacion(identificacionType);

		receptorType.setIdentificacionExtranjero(cliente.getIdentificacionExtranjero());
		UbicacionType ubicacionType = new UbicacionType();
		ubicacionType.setProvincia(cliente.getProvincia());
		ubicacionType.setCanton(cliente.getCanton());
		ubicacionType.setDistrito(cliente.getDistrito());
		ubicacionType.setBarrio(cliente.getBarrio());
		ubicacionType.setOtrasSenas(cliente.getOtraSena());
		receptorType.setUbicacion(ubicacionType);
		TelefonoType telefonoType = new TelefonoType();
		BigInteger codigoPais = BigInteger.valueOf(cliente.getCodigoPais()==null?Constantes.ZEROS:cliente.getCodigoPais());
		telefonoType.setCodigoPais(codigoPais);
		BigInteger telefono = BigInteger.valueOf(cliente.getTelefono() == null?Constantes.ZEROS:cliente.getTelefono());
		telefonoType.setNumTelefono(telefono);
		receptorType.setTelefono(telefonoType);

		return receptorType;
	}

	/**
	 * Returna todos los detalles de la factura
	 * @param factura
	 * @return
	 */
	private FacturaElectronica.DetalleServicio detallesFactura(Factura factura) {
		FacturaElectronica.DetalleServicio detalleServicio = new FacturaElectronica.DetalleServicio();

		for (Detalle detalle : factura.getDetalles()) {

			FacturaElectronica.DetalleServicio.LineaDetalle lineaDetalle = new FacturaElectronica.DetalleServicio.LineaDetalle();

			CodigoType codigoType = new CodigoType();
			codigoType.setCodigo(detalle.getArticulo().getCodigo());
			codigoType.setTipo(CODIGO_PRODUCTO_VENDEDOR);

			lineaDetalle.setCodigo(codigoType);

			lineaDetalle.setCantidad(getValorBigdecimal(detalle.getCantidad()));
			lineaDetalle.setDetalle(detalle.getArticulo().getDescripcion());
			lineaDetalle.setMontoDescuento(getValorBigdecimal(detalle.getMontoDescuento()));
			lineaDetalle.setMontoTotal(getValorBigdecimal(detalle.getMontoTotal()));

			// Pendiente de corregir no es el tipo igual al del xml
			lineaDetalle.setNaturalezaDescuento(Constantes.EMPTY);

			BigInteger numeroLinea = BigInteger.valueOf(detalle.getNumeroLinea());
			lineaDetalle.setNumeroLinea(numeroLinea);
			lineaDetalle.setPrecioUnitario(getValorBigdecimal(detalle.getPrecioUnitario()));
			lineaDetalle.setSubTotal(getValorBigdecimal(detalle.getSubTotal()));
			lineaDetalle.setUnidadMedida(detalle.getArticulo().getUnidadMedida());

			// Pendiente de corregir
			lineaDetalle.setUnidadMedidaComercial(Constantes.EMPTY);
			detalleServicio.getLineaDetalle().add(lineaDetalle);

		}

		return detalleServicio;

	}

	/**
	 * Obtiene el valor en Big Decimal
	 * @param valor
	 * @return
	 */
	private BigDecimal getValorBigdecimal(Double valor) {
		valor = valor == null?Constantes.ZEROS_DOUBLE:valor;
		BigDecimal resultado = BigDecimal.valueOf(valor);
		return resultado;
	}

	/**
	 * Resumen de la Factura electronica
	 * @param factura
	 * @return
	 */
	private FacturaElectronica.ResumenFactura getResumen(Factura factura) {
		FacturaElectronica.ResumenFactura resumenFactura = new FacturaElectronica.ResumenFactura();
		resumenFactura.setCodigoMoneda(factura.getCodigoMoneda());
		BigDecimal tipoCambio = BigDecimal.valueOf(factura.getTipoCambio());
		resumenFactura.setTipoCambio(tipoCambio);
		BigDecimal totalServGravado = BigDecimal.valueOf(factura.getTotalServGravados());
		resumenFactura.setTotalServGravados(totalServGravado);
		BigDecimal totalServExento = BigDecimal.valueOf(factura.getTotalServExentos());
		resumenFactura.setTotalServExentos(totalServExento);
		BigDecimal totalMercanciaGravada = BigDecimal.valueOf(factura.getTotalMercanciasGravadas());
		resumenFactura.setTotalMercanciasGravadas(totalMercanciaGravada);
		BigDecimal totalMercanciaExenta = BigDecimal.valueOf(factura.getTotalMercanciasExentas());
		resumenFactura.setTotalMercanciasExentas(totalMercanciaExenta);
		BigDecimal totalGravado = BigDecimal.valueOf(factura.getTotalGravado());
		resumenFactura.setTotalGravado(totalGravado);
		BigDecimal totalExento = BigDecimal.valueOf(factura.getTotalExento());
		resumenFactura.setTotalExento(totalExento);
		BigDecimal totalVenta = BigDecimal.valueOf(factura.getTotalVenta());
		resumenFactura.setTotalVenta(totalVenta);
		BigDecimal totalDescuentos = BigDecimal.valueOf(factura.getTotalDescuentos());
		resumenFactura.setTotalDescuentos(totalDescuentos);
		BigDecimal totalVentaNeta = BigDecimal.valueOf(factura.getTotalVentaNeta());
		resumenFactura.setTotalVentaNeta(totalVentaNeta);
		BigDecimal totalImpuesto = BigDecimal.valueOf(factura.getTotalImpuesto());
		resumenFactura.setTotalImpuesto(totalImpuesto);
		BigDecimal totalComprobante = BigDecimal.valueOf(factura.getTotalComprobante());
		resumenFactura.setTotalComprobante(totalComprobante);

		return resumenFactura;
	}

}
