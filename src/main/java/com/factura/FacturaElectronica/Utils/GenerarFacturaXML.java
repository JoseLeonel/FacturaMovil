package com.factura.FacturaElectronica.Utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import com.factura.FacturaElectronica.modelo.Cliente;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Factura;
import com.factura.FacturaElectronica.xml.EmisorType;
import com.factura.FacturaElectronica.xml.FacturaElectronica;
import com.factura.FacturaElectronica.xml.IdentificacionType;
import com.factura.FacturaElectronica.xml.ReceptorType;
import com.factura.FacturaElectronica.xml.TelefonoType;
import com.factura.FacturaElectronica.xml.UbicacionType;

public final class GenerarFacturaXML {

	/**
	 * Generar el XML de la Factura Electronica
	 * @param factura
	 */
	void generarXML(Factura factura) {

		try {
			// necesito hacer un contexto se crea la instancia
			JAXBContext context = JAXBContext.newInstance(FacturaElectronica.class);

			// Escribir el xml se utiliza el objete marshaller
			Marshaller marshaller = context.createMarshaller();

		} catch (Exception e) {
		}

	}

	public FacturaElectronica crearFacturaElectronica(Factura factura) {
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
		
		//Resumen de la Factura
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
		// código QR
		facturaElectronica.setClave(Constantes.BLANK);
		// Numero consecutivo de la factura
		facturaElectronica.setNumeroConsecutivo(factura.getNumeroConsecutivo());
		// Fecha Emision
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
		telefonoType.setNumTelefono(empresa.getTelefono());
		telefonoType.setCodigoPais(empresa.getCodigoPais());

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
//		telefonoType.setCodigoPais(cliente.getCodigoPais());
//		telefonoType.setNumTelefono(cliente.getTelefono());
		receptorType.setTelefono(telefonoType);

		return receptorType;
	}

	/**
	 * Resumen de la Factura electronica
	 * @param factura
	 * @return
	 */
	private FacturaElectronica.ResumenFactura getResumen(Factura factura) {
		FacturaElectronica.ResumenFactura resumenFactura = new FacturaElectronica.ResumenFactura();
		resumenFactura.setCodigoMoneda(factura.getCodigoMoneda());
//		resumenFactura.setTipoCambio(factura.getTipoCambio());
//		resumenFactura.setTotalServGravados(factura.getTotalServGravados());
//		resumenFactura.setTotalServExentos(factura.getTotalServExentos());
//		resumenFactura.setTotalMercanciasGravadas(factura.getTotalMercanciasGravadas());
//		resumenFactura.setTotalMercanciasExentas(factura.getTotalMercanciasExentas());
//		resumenFactura.setTotalGravado(factura.getTotalGravado());
//		resumenFactura.setTotalExento(factura.getTotalExento());
//		resumenFactura.setTotalVenta(factura.getTotalVenta());
//		resumenFactura.setTotalDescuentos(factura.getTotalDescuentos());
//		resumenFactura.setTotalVentaNeta(factura.getTotalVentaNeta());
//		resumenFactura.setTotalImpuesto(factura.getTotalImpuesto());
//		resumenFactura.setTotalComprobante(factura.getTotalComprobante());
//		resumenFactura.setTotalExento(factura.getTotalExento());

		return resumenFactura;
	}

}
