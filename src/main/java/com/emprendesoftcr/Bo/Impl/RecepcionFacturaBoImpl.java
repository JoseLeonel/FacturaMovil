package com.emprendesoftcr.Bo.Impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.emprendesoftcr.Bo.RecepcionFacturaBo;
import com.emprendesoftcr.Dao.RecepcionFacturaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.modelo.RecepcionFacturaDetalle;
import com.emprendesoftcr.utils.Utils;

@EnableTransactionManagement
@Service("recepcionFacturaBo")
public class RecepcionFacturaBoImpl implements RecepcionFacturaBo {

	@Autowired
	RecepcionFacturaDao recepcionFacturaDao;

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

	/**
	 * Pasa del xml a la factura
	 */
	@Transactional
	@Override
	public RecepcionFactura getPasarXMLAFactura(String ruta) {
		RecepcionFactura recepcionFactura = new RecepcionFactura();
		try {

			Document document = convertXMLFileToXMLDocument(ruta);
			// Normalize the XML Structure; It's just too important !!
			document.getDocumentElement().normalize();
			// Here comes the root node
			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());
			// Get all employees
			NodeList nList = document.getElementsByTagName("FacturaElectronica");
			Node node = nList.item(0);
			// Encabezado
			recepcionFactura = getEncabezado(node, recepcionFactura);
			recepcionFactura = obtenerEmisor(node, document, recepcionFactura);
			recepcionFactura = obtenerReceptor(node, document, recepcionFactura);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return recepcionFactura;
	}

	private RecepcionFactura getEncabezado(Node node, RecepcionFactura recepcionFactura) {

		try {

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				Node node1 = eElement.getElementsByTagName("Clave").item(0);
				recepcionFactura.setFacturaClave(node1.getTextContent());
				node1 = eElement.getElementsByTagName("CodigoActividad").item(0);
				recepcionFactura.setFacturaCodigoActividad(node1.getTextContent());
				node1 = eElement.getElementsByTagName("NumeroConsecutivo").item(0);
				recepcionFactura.setFacturaConsecutivo(node1.getTextContent());
				node1 = eElement.getElementsByTagName("FechaEmision").item(0);
				recepcionFactura.setFacturaFechaEmision(Utils.parseDate2(node1.getTextContent()));
				node1 = eElement.getElementsByTagName("CondicionVenta").item(0);
				recepcionFactura.setFacturaCondicionVenta(node1.getTextContent());
				node1 = eElement.getElementsByTagName("PlazoCredito").item(0);
				recepcionFactura.setFacturaPlazoCredito(node1.getTextContent());
				node1 = eElement.getElementsByTagName("MedioPago").item(0);
				recepcionFactura.setFacturaMedioPago(node1.getTextContent());
			}
		} catch (Exception e) {

		} finally {
			return recepcionFactura;
		}
	}

	
	private RecepcionFactura obtenerEmisor(Node node, Document document, RecepcionFactura recepcionFactura) {
		try {
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				Node node1 = eElement.getElementsByTagName("Nombre").item(0);
				recepcionFactura.setEmisorNombre(node1.getTextContent());
				
				node1 = eElement.getElementsByTagName("CorreoElectronico").item(0);
				recepcionFactura.setEmisorCorreo( node1.getTextContent());
				
				NodeList nListEmisor = document.getElementsByTagName("Identificacion");
				if (nListEmisor.getLength() >= 0 && nListEmisor != null) {
					node1 = eElement.getElementsByTagName("Tipo").item(0);
					recepcionFactura.setEmisorTipoCedula(node1.getTextContent());
					
					node1 = eElement.getElementsByTagName("Numero").item(0);
					recepcionFactura.setEmisorCedula(node1.getTextContent());
					NodeList nListEmisorUbicacion = document.getElementsByTagName("Ubicacion");
					Element eElementUbicacion = (Element) nListEmisorUbicacion.item(0);
					node1 = eElementUbicacion.getElementsByTagName("Provincia").item(0);
					recepcionFactura.setEmisorCodigoProvincia(node1.getTextContent());
					node1 = eElementUbicacion.getElementsByTagName("Canton").item(0);
					recepcionFactura.setEmisorCanton(node1.getTextContent());
					node1 = eElementUbicacion.getElementsByTagName("Distrito").item(0);
					recepcionFactura.setEmisorDistrito(node1.getTextContent());;
					node1 = eElementUbicacion.getElementsByTagName("OtrasSenas").item(0);
					recepcionFactura.setEmisorOtraSena(node1.getTextContent());

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			return recepcionFactura;
		}
	}

	/**
	 * @param node
	 * @param document
	 * @param recepcionFactura
	 * @return
	 */
	private RecepcionFactura obtenerReceptor(Node node, Document document, RecepcionFactura recepcionFactura) {
		try {
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				Node node1 = eElement.getElementsByTagName("Nombre").item(0);
				System.out.println("Nombre:" + node1.getTextContent());
				node1 = eElement.getElementsByTagName("CorreoElectronico").item(0);
				System.out.println("CorreoElectronico:" + node1.getTextContent());
				NodeList nListEmisor = document.getElementsByTagName("Emisor");
				if (nListEmisor.getLength() >= 0 && nListEmisor != null) {
					node1 = eElement.getElementsByTagName("Tipo").item(0);
					System.out.println("Tipo:" + node1.getTextContent());
					node1 = eElement.getElementsByTagName("Numero").item(0);
					String Numero = node1.getTextContent();
					System.out.println("Numero:" + Numero);
					NodeList nListEmisorUbicacion = document.getElementsByTagName("Ubicacion");
					Element eElementUbicacion = (Element) nListEmisorUbicacion.item(0);
					node1 = eElementUbicacion.getElementsByTagName("Provincia").item(0);
					System.out.println("Provincia:" + node1.getTextContent());
					node1 = eElementUbicacion.getElementsByTagName("Canton").item(0);
					System.out.println("Canton:" + node1.getTextContent());
					node1 = eElementUbicacion.getElementsByTagName("Distrito").item(0);
					System.out.println("Distrito:" + node1.getTextContent());
					node1 = eElementUbicacion.getElementsByTagName("OtrasSenas").item(0);
					System.out.println("OtrasSenas:" + node1.getTextContent());

				}

			}
		} catch (Exception e) {
			
		}finally {
			return recepcionFactura;
		}

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

}