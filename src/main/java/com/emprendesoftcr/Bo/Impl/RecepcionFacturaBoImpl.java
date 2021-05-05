package com.emprendesoftcr.Bo.Impl;

import java.io.File;
import java.util.Collection;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.emprendesoftcr.Bo.RecepcionFacturaBo;
import com.emprendesoftcr.Dao.RecepcionFacturaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.modelo.RecepcionFacturaDetalle;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.schma.Bo.FacturaElectronicaSchemaBo;
import com.emprendesoftcr.schma.Bo.NotaCreditoElectronicaSchemaBo;
import com.emprendesoftcr.schma.Bo.NotaDebitoElectronicaSchemaBo;

@EnableTransactionManagement
@Service("recepcionFacturaBo")
public class RecepcionFacturaBoImpl implements RecepcionFacturaBo {

	@Autowired
	RecepcionFacturaDao											recepcionFacturaDao;

	@Autowired
	private NotaDebitoElectronicaSchemaBo		notaDebitoElectronicaSchemaBo;
	@Autowired
	private NotaCreditoElectronicaSchemaBo	notaCreditoElectronicaSchemaBo;
	@Autowired
	private FacturaElectronicaSchemaBo			facturaElectronicaSchemaBo;

	@Value("${path.upload.files.api}")
	private String													pathFacturaXML;

	private Logger													log	= LoggerFactory.getLogger(this.getClass());

	/**
	 * Pasa del xml a la factura
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void getPasarXMLAFactura(String ruta, Empresa empresa, Usuario usuarioSesion, String condicionImpuesto, Integer tipoGasto, String codigoActividad, String mensaje, String detalleMensaje) throws Exception {
		try {

			Document document = convertXMLFileToXMLDocument(pathFacturaXML + "mr-automatico/" + ruta);
			document.getDocumentElement().normalize();
			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());
			if (root.getNodeName().contains("FacturaElectronica")) {
				log.info("Factura Electronica _______________________________ ");
				facturaElectronicaSchemaBo.aplicarFacturaXMLToModelo(ruta, empresa, usuarioSesion, condicionImpuesto, tipoGasto, codigoActividad, mensaje, detalleMensaje);
			} else if (root.getNodeName().contains("NotaCreditoElectronica")) {
				log.info("Nota Credito _______________________________ ");
				notaCreditoElectronicaSchemaBo.aplicarFacturaXMLToModelo(ruta, empresa, usuarioSesion, condicionImpuesto, tipoGasto, codigoActividad, mensaje, detalleMensaje);
			} else if (root.getNodeName().contains("NotaDebitoElectronica")) {
				log.info("Nota debito _______________________________ ");
				notaDebitoElectronicaSchemaBo.aplicarFacturaXMLToModelo(ruta, empresa, usuarioSesion, condicionImpuesto, tipoGasto, codigoActividad, mensaje, detalleMensaje);
			}

		} catch (Exception e) {
			log.error(String.format("--error Compra formateda del xml :" + e.getMessage() + new Date()));
			throw e;
		}

	}

	@Override
	@Transactional
	public void agregar(RecepcionFactura recepcionFactura) {
		recepcionFacturaDao.agregar(recepcionFactura);
	}

	@Override
	@Transactional
	public void agregar(RecepcionFacturaDetalle recepcionFacturaDetalle) {
		recepcionFacturaDao.agregar(recepcionFacturaDetalle);
	}

	@Override
	@Transactional
	public void modificar(RecepcionFactura recepcionFactura) {
		recepcionFacturaDao.modificar(recepcionFactura);
	}

	@Override
	public RecepcionFactura findById(Long id) {
		return recepcionFacturaDao.findById(id);
	}

	@Override
	public RecepcionFactura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) throws Exception {
		return recepcionFacturaDao.findByConsecutivoAndEmpresa(consecutivo, empresa);
	}

	@Override
	public Collection<RecepcionFactura> findByEstadoFirma(Integer estadoFirma, Integer reEstadoFirma) {
		return recepcionFacturaDao.findByEstadoFirma(estadoFirma, reEstadoFirma);
	}

	@Override
	public Collection<RecepcionFactura> findByClave(String cedulaEmisor, String clave) {
		return recepcionFacturaDao.findByClave(cedulaEmisor, clave);
	}

	@Override
	public Collection<RecepcionFactura> findByFechaInicioAndFechaFinalAndCedulaEmisor(Date fechaInicio, Date fechaFin, Empresa empresa, String cedula, Integer estado, Integer tipoGasto, String actividadEconocimica) {
		return recepcionFacturaDao.findByFechaInicioAndFechaFinalAndCedulaEmisor(fechaInicio, fechaFin, empresa, cedula, estado, tipoGasto, actividadEconocimica);
	}

	@Override
	public Collection<RecepcionFacturaDetalle> findByDetalleAndFechaInicioAndFechaFinalAndCedulaEmisor(Date fechaInicio, Date fechaFin, Empresa empresa, String cedula, Integer estado, Integer tipoGasto, String actividadEconocimica) {

		return recepcionFacturaDao.findByDetalleAndFechaInicioAndFechaFinalAndCedulaEmisor(fechaInicio, fechaFin, empresa, cedula, estado, tipoGasto, actividadEconocimica);
	}

	

	private Document convertXMLFileToXMLDocument(String filePath) {
		// Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// API to obtain DOM Document instance
		DocumentBuilder builder = null;
		try {
			// Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();
			// Parse the content to Document object
			Document doc = builder.parse(new File(filePath));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RecepcionFactura findByClaveAndCedulaEmisor(String clave, String cedula) throws Exception {

		return recepcionFacturaDao.findByClaveAndCedulaEmisor(clave, cedula);
	}

}