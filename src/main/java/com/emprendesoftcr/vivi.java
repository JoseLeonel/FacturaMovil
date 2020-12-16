package com.emprendesoftcr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.XmlHelper;
import com.itextpdf.text.DocumentException;

public class vivi {

	public static void main(String[] args) throws ParseException, FileNotFoundException, DocumentException {

		Double lado = 4d;
		Double lado1 = 4d;
		Double resultado = lado1 * lado;

//		String t = parseTime("2020-11-13T10:22:27"); 

		System.out.println("Yo naci:" + resultado);

		try {

			String filePath = "/home/jose/" + "mr-automatico/" + "fe1605721320379-sinmata.xml";

			String facturaXmlFinal = "";

			try {
				facturaXmlFinal = new String(Files.readAllBytes(Paths.get(filePath)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				facturaXmlFinal = "";
			}
			Document document = convertXMLFileToXMLDocument(filePath);
			// Normalize the XML Structure; It's just too important !!
			document.getDocumentElement().normalize();
			// Here comes the root node
			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());
			// Get all employees
			NodeList nList = document.getElementsByTagName("FacturaElectronica");
			Node node = nList.item(0);
			// Encabezado
			getEncabezado(node);
			System.out.println(document.getFirstChild().getNodeName());
			// Emisor
			NodeList nListEmisor = document.getElementsByTagName("Emisor");
			if (nListEmisor.getLength() >= 0 && nListEmisor != null) {
				obtenerEmisor(nListEmisor.item(0), document);
			}
			NodeList nListReceptor = document.getElementsByTagName("Receptor");
			if (nListReceptor.getLength() >= 0 && nListReceptor != null) {
				obtenerReceptor(nListReceptor.item(0), document);
			}
			obtenerDetalle(document);
			resumenFacturaTotal(document);
		} catch (Exception e2) {

		}
	}

	private static void getEncabezado(Node node) {

		try {

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				Node node1 = eElement.getElementsByTagName("Clave").item(0);
				System.out.println("Clave:" + node1.getTextContent());
				node1 = eElement.getElementsByTagName("CodigoActividad").item(0);
				System.out.println("CodigoActividad:" + node1.getTextContent());
				node1 = eElement.getElementsByTagName("NumeroConsecutivo").item(0);
				System.out.println("NumeroConsecutivo:" + node1.getTextContent());
				node1 = eElement.getElementsByTagName("FechaEmision").item(0);
				System.out.println("FechaEmision:" + node1.getTextContent());
				node1 = eElement.getElementsByTagName("CondicionVenta").item(0);
				System.out.println("CondicionVenta:" + node1.getTextContent());
				node1 = eElement.getElementsByTagName("PlazoCredito").item(0);
				System.out.println("PlazoCredito:" + node1.getTextContent());
				node1 = eElement.getElementsByTagName("MedioPago").item(0);
				System.out.println("MedioPago:" + node1.getTextContent());
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private static void obtenerEmisor(Node node, Document document) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) node;
			Node node1 = eElement.getElementsByTagName("Nombre").item(0);
			System.out.println("Nombre:" + node1.getTextContent());
			node1 = eElement.getElementsByTagName("CorreoElectronico").item(0);
			System.out.println("CorreoElectronico:" + node1.getTextContent());
			NodeList nListEmisor = document.getElementsByTagName("Identificacion");
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
	}

	private static void obtenerReceptor(Node node, Document document) {
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
	}

	private static void obtenerDetalle(Document document) {
		NodeList nListDetalleServicio = document.getElementsByTagName("DetalleServicio");
		NodeList nLineaDetalle = nListDetalleServicio.item(0).getChildNodes();
		if (nLineaDetalle.getLength() >= 0 && nLineaDetalle != null) {
			for (int i = 0; i < nLineaDetalle.getLength(); i++) {
				Node node = nLineaDetalle.item(i);
				System.out.println("\nCurrent Element :" + node.getNodeName());

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					   NodeList  nodeList = eElement.getElementsByTagName("CodigoComercial");
					   if (nodeList.getLength() >= 0 && nodeList != null) {
								Node nodeTemp = nodeList.item(0);
								if(nodeTemp != null) {
									System.out.println("\nCurrent Element :" + nodeTemp.getNodeName());
									System.out.println("CodigoComercial -> Tipo:" + valorString(nodeTemp,"Tipo",0));		
									System.out.println("CodigoComercial -> Codigo:" + valorString(nodeTemp,"Codigo",0));
								}
					   }
					   
						nodeList = eElement.getElementsByTagName("Impuesto");
						if (nodeList.getLength() >= 0 && nodeList != null) {
							Node nodeTemp1 = nodeList.item(0);
							if(nodeTemp1 != null) {
								//	System.out.println("\nCurrent Element :" + nodeTemp1.getNodeName());
								System.out.println("Impuesto -> Codigo:" + valorString(nodeTemp1,"Codigo",0));		
								System.out.println("Impuesto -> CodigoTarifa:" + valorString(nodeTemp1,"CodigoTarifa",0));
								System.out.println("Impuesto -> Tarifa:" + valorString(nodeTemp1,"Tarifa",0));
								System.out.println("Impuesto -> Monto:" + valorString(nodeTemp1,"Monto",0));
								
							}
							
						}
						nodeList = eElement.getElementsByTagName("Exoneracion");
						if (nodeList.getLength() >= 0 && nodeList != null) {
							Node nodeTemp1 = nodeList.item(0);
							if(nodeTemp1 != null) {
								//	System.out.println("\nCurrent Element :" + nodeTemp1.getNodeName());
								System.out.println("Impuesto -> TipoDocumento:" + valorString(nodeTemp1,"TipoDocumento",0));		
								System.out.println("Impuesto -> NumeroDocumento:" + valorString(nodeTemp1,"NumeroDocumento",0));
								System.out.println("Impuesto -> NombreInstitucion:" + valorString(nodeTemp1,"NombreInstitucion",0));
								System.out.println("Impuesto -> FechaEmision:" + valorString(nodeTemp1,"FechaEmision",0));
								System.out.println("Impuesto -> PorcentajeExoneracion:" + valorString(nodeTemp1,"PorcentajeExoneracion",0));
								System.out.println("Impuesto -> MontoExoneracion:" + valorString(nodeTemp1,"MontoExoneracion",0));
								
							}
							
						}
						
						System.out.println("NumeroLinea:" + valorString(node,"NumeroLinea",i));
					System.out.println("Codigo:" + valorString(node,"Codigo",i));
					System.out.println("Cantidad :" + valorString(node,"Cantidad",i));
					System.out.println("UnidadMedida :" + valorString(node,"UnidadMedida",i));
					System.out.println("Detalle :" + valorString(node,"Detalle",i));
					System.out.println("PrecioUnitario :" + valorString(node,"PrecioUnitario",i));
					System.out.println("MontoTotal :" + valorString(node,"MontoTotal",i));
					System.out.println("SubTotal :" + valorString(node,"SubTotal",i));
					System.out.println("ImpuestoNeto :" + valorString(node,"ImpuestoNeto",i));
					System.out.println("MontoTotalLinea :" + valorString(node,"MontoTotalLinea",i));
				}


				}

			}
		}
	
	
	private static String valorString(Node node,String name,Integer index) {
		if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) node;
			Node node1 = eElement.getElementsByTagName(name).item(0);	
			 
	 if(node1 == null) 
		 return Constantes.EMPTY;
	   return node1.getTextContent().toString();
	 }
		
		return Constantes.EMPTY;
	} 

	private static void resumenFacturaTotal(Document document) {
		NodeList nListResumenFactura = document.getElementsByTagName("ResumenFactura");
		Node node = nListResumenFactura.item(0);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) node;
			NodeList nodeList = eElement.getElementsByTagName("CodigoTipoMoneda");	
			for (int j = 0; j < nodeList.getLength(); j++) {
				Node nodeTemp = nodeList.item(j);
				System.out.println("CodigoTipoMoneda -> CodigoMoneda:" + valorString(nodeTemp,"CodigoMoneda",j));		
				System.out.println("CodigoTipoMoneda -> TipoCambio:" + valorString(nodeTemp,"TipoCambio",j));
			}
			node = eElement.getElementsByTagName("TotalServGravados").item(0);
			System.out.println("TotalServGravados:" + node.getTextContent());
			node = eElement.getElementsByTagName("TotalServExentos").item(0);
			System.out.println("TotalServExentos:" + node.getTextContent());

			node = eElement.getElementsByTagName("TotalServExonerado").item(0);
			System.out.println("TotalServExonerado:" + node.getTextContent());
			node = eElement.getElementsByTagName("TotalMercanciasGravadas").item(0);
			System.out.println("TotalMercanciasGravadas:" + node.getTextContent());
			node = eElement.getElementsByTagName("TotalMercanciasExentas").item(0);
			System.out.println("TotalMercanciasExentas:" + node.getTextContent());
			node = eElement.getElementsByTagName("TotalMercExonerada").item(0);
			System.out.println("TotalMercExonerada:" + node.getTextContent());
			node = eElement.getElementsByTagName("TotalGravado").item(0);
			System.out.println("TotalGravado:" + node.getTextContent());
			node = eElement.getElementsByTagName("TotalExento").item(0);
			System.out.println("TotalExento:" + node.getTextContent());
			node = eElement.getElementsByTagName("TotalExonerado").item(0);
			System.out.println("TotalExonerado:" + node.getTextContent());
			node = eElement.getElementsByTagName("TotalVenta").item(0);
			System.out.println("TotalVenta:" + node.getTextContent());
			node = eElement.getElementsByTagName("TotalDescuentos").item(0);
			System.out.println("TotalDescuentos:" + node.getTextContent());
			node = eElement.getElementsByTagName("TotalVentaNeta").item(0);
			System.out.println("TotalVentaNeta:" + node.getTextContent());
			node = eElement.getElementsByTagName("TotalImpuesto").item(0);
			System.out.println("TotalImpuesto:" + node.getTextContent());

			node = eElement.getElementsByTagName("TotalIVADevuelto").item(0);
			System.out.println("TotalIVADevuelto:" + node.getTextContent());

			node = eElement.getElementsByTagName("TotalComprobante").item(0);
			System.out.println("TotalComprobante:" + node.getTextContent());
			
		}

	}

	private static Document convertXMLFileToXMLDocument(String filePath) {
		// Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// API to obtain DOM Document instance
		DocumentBuilder builder = null;
		try {
			// Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();

			// Parse the content to Document object
		//	Document doc = builder.parse(new File(filePath));
			Document doc = XmlHelper.getDocument(filePath);
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//		
//		
//		String fechaF2 = "2020-07-01";
//
//		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
//
//		Date fecha1 = new Date();
//		Date fecha2 = formato.parse(fechaF2);
//
//		if(fecha1.before(fecha2)){
//		   System.out.println( "Fecha 1 es mayor o igual a fecha2" );
//		}else{
//		   System.out.println( "Fecha2 es menor que fecha1");
//		}	
//	
//		LocalDate today = LocalDate.now();
//		int month = today.getMonthValue();
//		month = month-1;
//		System.out.println( "mes" + month);

	public static String parseTime(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
		try {
			Date date1 = format.parse(date);
			String d = new SimpleDateFormat("yyyy/dd/MM HH:mm:ss").format(date1);
			return d;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
