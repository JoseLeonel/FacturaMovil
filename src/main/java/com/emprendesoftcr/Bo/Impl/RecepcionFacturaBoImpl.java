package com.emprendesoftcr.Bo.Impl;

import java.io.File;
import java.util.Collection;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.emprendesoftcr.Bo.EmpresaBo;
import com.emprendesoftcr.Bo.RecepcionFacturaBo;
import com.emprendesoftcr.Dao.RecepcionFacturaDao;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.modelo.RecepcionFacturaDetalle;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;

@EnableTransactionManagement
@Service("recepcionFacturaBo")
public class RecepcionFacturaBoImpl implements RecepcionFacturaBo {

	@Autowired
	RecepcionFacturaDao	recepcionFacturaDao;

	@Autowired
	private EmpresaBo		empresaBo;

	private Logger			log	= LoggerFactory.getLogger(this.getClass());

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
	public RecepcionFactura getPasarXMLAFactura(String ruta, Empresa empresa, Usuario usuarioSesion, String condicionImpuesto, Integer tipoGasto, String codigoActividad, String mensaje, String detalleMensaje) {
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
			recepcionFactura.setId(null);
			recepcionFactura.setMensaje(mensaje);
			recepcionFactura.setTipoGasto(tipoGasto);
			recepcionFactura.setDetalleMensaje(detalleMensaje == null ? "Aceptacion automatica de la compra" : detalleMensaje);
			recepcionFactura.setCondicionImpuesto(condicionImpuesto);
			recepcionFactura.setCodigoActividad(codigoActividad);
			recepcionFactura = getEncabezado(node, recepcionFactura);
			recepcionFactura = obtenerEmisor(node, document, recepcionFactura);
			recepcionFactura = obtenerReceptor(node, document, recepcionFactura);
			recepcionFactura.setVersion_doc("4.3");
			recepcionFactura.setCreated_at(new Date());
			recepcionFactura.setUpdated_at(new Date());
			recepcionFactura.setEmpresa(empresa);
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
			recepcionFactura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_PENDIENTE);
			recepcionFactura.setEstado(Constantes.FACTURA_ESTADO_FACTURADO);
			recepcionFactura.setNumeroConsecutivoReceptor(empresaBo.generarConsecutivoRecepcionFactura(usuarioSesion.getEmpresa(), usuarioSesion, recepcionFactura));
			recepcionFactura.setId(null);
			recepcionFactura.setMensaje(mensaje);
			recepcionFactura.setTipoGasto(tipoGasto);
			recepcionFactura.setDetalleMensaje(detalleMensaje == null ? "Aceptacion automatica de la compra" : detalleMensaje);
			recepcionFactura.setCondicionImpuesto(condicionImpuesto);
			recepcionFactura.setCodigoActividad(codigoActividad);
			recepcionFactura = resumenFacturaTotal(document, recepcionFactura);
			recepcionFactura.setTotalImpuestoAcreditar(recepcionFactura.getFacturaTotalImpuestos());
			recepcionFactura.setTotalDeGastoAplicable(recepcionFactura.getFacturaTotalComprobante() - recepcionFactura.getFacturaTotalImpuestos());
			recepcionFacturaDao.agregar(recepcionFactura);
			obtenerDetalle(document, recepcionFactura);
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
				recepcionFactura.setFacturaFechaEmision(FacturaElectronicaUtils.parseStringtoISO8601Date(node1.getTextContent()));
				node1 = eElement.getElementsByTagName("CondicionVenta").item(0);
				recepcionFactura.setFacturaCondicionVenta(node1.getTextContent());
				node1 = eElement.getElementsByTagName("PlazoCredito").item(0);
				recepcionFactura.setFacturaPlazoCredito(node1.getTextContent());
				node1 = eElement.getElementsByTagName("MedioPago").item(0);
				recepcionFactura.setFacturaMedioPago(node1.getTextContent());
			}

		} catch (Exception e) {

		}
		return recepcionFactura;
	}

	private RecepcionFactura obtenerEmisor(Node node, Document document, RecepcionFactura recepcionFactura) {
		try {
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				Node node1 = eElement.getElementsByTagName("Nombre").item(0);
				recepcionFactura.setEmisorNombre(node1.getTextContent());

				node1 = eElement.getElementsByTagName("CorreoElectronico").item(0);
				recepcionFactura.setEmisorCorreo(node1.getTextContent());

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
					recepcionFactura.setEmisorDistrito(node1.getTextContent());
					;
					node1 = eElementUbicacion.getElementsByTagName("OtrasSenas").item(0);
					recepcionFactura.setEmisorOtraSena(node1.getTextContent());

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return recepcionFactura;
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
				recepcionFactura.setReceptorNombre(node1.getTextContent());

				node1 = eElement.getElementsByTagName("CorreoElectronico").item(0);
				recepcionFactura.setReceptorCorreo(node1.getTextContent());

				NodeList nListEmisor = document.getElementsByTagName("Emisor");
				if (nListEmisor.getLength() >= 0 && nListEmisor != null) {
					node1 = eElement.getElementsByTagName("Tipo").item(0);
					recepcionFactura.setReceptorTipoCedula(node1.getTextContent());
					node1 = eElement.getElementsByTagName("Numero").item(0);
					recepcionFactura.setReceptorCedula(node1.getTextContent());
					NodeList nListEmisorUbicacion = document.getElementsByTagName("Ubicacion");
					Element eElementUbicacion = (Element) nListEmisorUbicacion.item(0);
					node1 = eElementUbicacion.getElementsByTagName("Provincia").item(0);
					recepcionFactura.setReceptorProvincia(node1.getTextContent());
					node1 = eElementUbicacion.getElementsByTagName("Canton").item(0);
					recepcionFactura.setReceptorCanton(node1.getTextContent());
					node1 = eElementUbicacion.getElementsByTagName("Distrito").item(0);
					recepcionFactura.setReceptorDistrito(node1.getTextContent());
					node1 = eElementUbicacion.getElementsByTagName("OtrasSenas").item(0);
					recepcionFactura.setReceptorOtraSena(node1.getTextContent());

				}

			}
		} catch (Exception e) {

		}
		return recepcionFactura;
	}

	private void obtenerDetalle(Document document, RecepcionFactura recepcionFactura) {
		try {
			NodeList nListDetalleServicio = document.getElementsByTagName("DetalleServicio");
			NodeList nLineaDetalle = nListDetalleServicio.item(0).getChildNodes();
			if (nLineaDetalle.getLength() >= 0 && nLineaDetalle != null) {
				for (int i = 0; i < nLineaDetalle.getLength(); i++) {
					Node node = nLineaDetalle.item(i);
					if (node != null) {
						RecepcionFacturaDetalle recepcionFacturaDetalleNueva = new RecepcionFacturaDetalle();
						recepcionFacturaDetalleNueva.setRecepcionFactura(recepcionFactura);
						recepcionFacturaDetalleNueva.setNumeroLinea(Utils.stringToInteger(valorString(node, "NumeroLinea", i)));
						recepcionFacturaDetalleNueva.setCodigoCabys(valorString(node, "Codigo", i));
						recepcionFacturaDetalleNueva.setCantidad(Utils.stringToDouble(valorString(node, "Cantidad", i)));
						recepcionFacturaDetalleNueva.setUnidadMedida(valorString(node, "UnidadMedida", i));
						recepcionFacturaDetalleNueva.setDetalle(valorString(node, "Detalle", i));
						recepcionFacturaDetalleNueva.setPrecioUnitario(Utils.stringToDouble(valorString(node, "PrecioUnitario", i)));
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) node;
							NodeList nodeList = eElement.getElementsByTagName("CodigoComercial");
							if (nodeList.getLength() >= 0 && nodeList != null) {
								Node nodeTemp = nodeList.item(0);
								if (nodeTemp != null) {
									recepcionFacturaDetalleNueva.setCodigoComercialTipo(valorString(nodeTemp, "Tipo", 0));
									recepcionFacturaDetalleNueva.setCodigoComercialCodigo(valorString(nodeTemp, "Codigo", 0));
								}
							}
							nodeList = eElement.getElementsByTagName("Impuesto");
							if (nodeList.getLength() >= 0 && nodeList != null) {
								Node nodeTemp1 = nodeList.item(0);
								if (nodeTemp1 != null) {
									// System.out.println("\nCurrent Element :" + nodeTemp1.getNodeName());
									if (recepcionFacturaDetalleNueva.getImpuestoCodigo() != null && recepcionFacturaDetalleNueva.getImpuestoCodigo().equals(Constantes.EMPTY)) {
										recepcionFacturaDetalleNueva.setImpuestoCodigo(valorString(nodeTemp1, "Codigo", 0));
										recepcionFacturaDetalleNueva.setImpuestoTarifa(Utils.stringToDouble(valorString(nodeTemp1, "CodigoTarifa", 0)));
										recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa(valorString(nodeTemp1, "Tarifa", 0));
										recepcionFacturaDetalleNueva.setImpuestoMonto(Utils.stringToDouble(valorString(nodeTemp1, "Monto", 0)));
									} else if (recepcionFacturaDetalleNueva.getImpuestoCodigo1() != null && recepcionFacturaDetalleNueva.getImpuestoCodigo1().equals(Constantes.EMPTY)) {
										recepcionFacturaDetalleNueva.setImpuestoCodigo1(valorString(nodeTemp1, "Codigo", 0));
										recepcionFacturaDetalleNueva.setImpuestoTarifa1(Utils.stringToDouble(valorString(nodeTemp1, "CodigoTarifa", 0)));
										recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa1(valorString(nodeTemp1, "Tarifa", 0));
										recepcionFacturaDetalleNueva.setImpuestoMonto1(Utils.stringToDouble(valorString(nodeTemp1, "Monto", 0)));

									} else if (recepcionFacturaDetalleNueva.getImpuestoCodigo2() != null && recepcionFacturaDetalleNueva.getImpuestoCodigo2().equals(Constantes.EMPTY)) {
										recepcionFacturaDetalleNueva.setImpuestoCodigo2(valorString(nodeTemp1, "Codigo", 0));
										recepcionFacturaDetalleNueva.setImpuestoTarifa2(Utils.stringToDouble(valorString(nodeTemp1, "CodigoTarifa", 0)));
										recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa2(valorString(nodeTemp1, "Tarifa", 0));
										recepcionFacturaDetalleNueva.setImpuestoMonto2(Utils.stringToDouble(valorString(nodeTemp1, "Monto", 0)));

									} else if (recepcionFacturaDetalleNueva.getImpuestoCodigo3() != null && recepcionFacturaDetalleNueva.getImpuestoCodigo3().equals(Constantes.EMPTY)) {
										recepcionFacturaDetalleNueva.setImpuestoCodigo3(valorString(nodeTemp1, "Codigo", 0));
										recepcionFacturaDetalleNueva.setImpuestoTarifa3(Utils.stringToDouble(valorString(nodeTemp1, "CodigoTarifa", 0)));
										recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa3(valorString(nodeTemp1, "Tarifa", 0));
										recepcionFacturaDetalleNueva.setImpuestoMonto3(Utils.stringToDouble(valorString(nodeTemp1, "Monto", 0)));

									} else if (recepcionFacturaDetalleNueva.getImpuestoCodigo4() != null && recepcionFacturaDetalleNueva.getImpuestoCodigo4().equals(Constantes.EMPTY)) {
										recepcionFacturaDetalleNueva.setImpuestoCodigo4(valorString(nodeTemp1, "Codigo", 0));
										recepcionFacturaDetalleNueva.setImpuestoTarifa4(Utils.stringToDouble(valorString(nodeTemp1, "CodigoTarifa", 0)));
										recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa4(valorString(nodeTemp1, "Tarifa", 0));
										recepcionFacturaDetalleNueva.setImpuestoMonto4(Utils.stringToDouble(valorString(nodeTemp1, "Monto", 0)));

									} else if (recepcionFacturaDetalleNueva.getImpuestoCodigo5() != null && recepcionFacturaDetalleNueva.getImpuestoCodigo5().equals(Constantes.EMPTY)) {
										recepcionFacturaDetalleNueva.setImpuestoCodigo5(valorString(nodeTemp1, "Codigo", 0));
										recepcionFacturaDetalleNueva.setImpuestoTarifa5(Utils.stringToDouble(valorString(nodeTemp1, "CodigoTarifa", 0)));
										recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa5(valorString(nodeTemp1, "Tarifa", 0));
										recepcionFacturaDetalleNueva.setImpuestoMonto5(Utils.stringToDouble(valorString(nodeTemp1, "Monto", 0)));

									} else if (recepcionFacturaDetalleNueva.getImpuestoCodigo6() != null && recepcionFacturaDetalleNueva.getImpuestoCodigo6().equals(Constantes.EMPTY)) {
										recepcionFacturaDetalleNueva.setImpuestoCodigo6(valorString(nodeTemp1, "Codigo", 0));
										recepcionFacturaDetalleNueva.setImpuestoTarifa6(Utils.stringToDouble(valorString(nodeTemp1, "CodigoTarifa", 0)));
										recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa6(valorString(nodeTemp1, "Tarifa", 0));
										recepcionFacturaDetalleNueva.setImpuestoMonto6(Utils.stringToDouble(valorString(nodeTemp1, "Monto", 0)));

									}

								}
							}

							nodeList = eElement.getElementsByTagName("Exoneracion");
							if (nodeList.getLength() >= 0 && nodeList != null) {
								Node nodeTemp1 = nodeList.item(0);
								if (nodeTemp1 != null) {
									recepcionFacturaDetalleNueva.setImpuestoExoneracionTipoDocumento(valorString(nodeTemp1, "TipoDocumento", 0));
									recepcionFacturaDetalleNueva.setImpuestoExoneracionNumeroDocumento(valorString(nodeTemp1, "NumeroDocumento", 0));
									recepcionFacturaDetalleNueva.setImpuestoExoneracionNombreInstitucion(valorString(nodeTemp1, "NombreInstitucion", 0));
									recepcionFacturaDetalleNueva.setImpuestoExoneracionFechaEmision(valorString(nodeTemp1, "FechaEmision", 0));
									recepcionFacturaDetalleNueva.setImpuestoExoneracionPorcentaje(Utils.stringToDouble(valorString(nodeTemp1, "PorcentajeExoneracion", 0)));
									recepcionFacturaDetalleNueva.setImpuestoExoneracionMonto(Utils.stringToDouble(valorString(nodeTemp1, "MontoExoneracion", 0)));
									recepcionFacturaDetalleNueva.setBaseImponible(Constantes.ZEROS_DOUBLE);
								}
							}
							recepcionFacturaDetalleNueva.setMontoTotal(Utils.stringToDouble(valorString(node, "MontoTotal", i)));
							recepcionFacturaDetalleNueva.setSubTotal(Utils.stringToDouble(valorString(node, "SubTotal", i)));
							recepcionFacturaDetalleNueva.setImpuestoNeto(Utils.stringToDouble(valorString(node, "ImpuestoNeto", i)));
							recepcionFacturaDetalleNueva.setMontoTotalLinea(Utils.stringToDouble(valorString(node, "MontoTotalLinea", i)));
							recepcionFacturaDao.agregar(recepcionFacturaDetalleNueva);
						}

					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private RecepcionFactura resumenFacturaTotal(Document document, RecepcionFactura recepcionFactura) {
		try {
			NodeList nListResumenFactura = document.getElementsByTagName("ResumenFactura");
			Node node = nListResumenFactura != null?nListResumenFactura.item(0) : null;
			if (node.getNodeType() == Node.ELEMENT_NODE && node != null) {
				Element eElement = (Element) node;
				NodeList nodeList = eElement.getElementsByTagName("CodigoTipoMoneda");
				for (int j = 0; j < nodeList.getLength(); j++) {
					Node nodeTemp = nodeList.item(j);
					recepcionFactura.setFacturaCodigoMoneda(valorString(nodeTemp, "CodigoMoneda", j));
					recepcionFactura.setFacturaTipoCambio(Utils.stringToDouble(valorString(nodeTemp, "TipoCambio", j)));
				}
				node = eElement.getElementsByTagName("TotalServGravados").item(0);
				recepcionFactura.setFacturaTotalServExentos(Utils.stringToDouble(node.getTextContent()));
				node = eElement.getElementsByTagName("TotalServExentos").item(0);
				recepcionFactura.setFacturaTotalServExentos(Utils.stringToDouble(node.getTextContent()));
				node = eElement.getElementsByTagName("TotalServExonerado").item(0);
				recepcionFactura.setFacturaTotalServExonerado(Utils.stringToDouble(node.getTextContent()));
				node = eElement.getElementsByTagName("TotalMercanciasGravadas").item(0);
				recepcionFactura.setFacturaTotalMercanciasGravadas(Utils.stringToDouble(node.getTextContent()));
				node = eElement.getElementsByTagName("TotalMercanciasExentas").item(0);
				recepcionFactura.setFacturaTotalMercanciasExentas(Utils.stringToDouble(node.getTextContent()));
				node = eElement.getElementsByTagName("TotalMercExonerada").item(0);
				recepcionFactura.setFacturaTotalMercanciasExentas(Utils.stringToDouble(node.getTextContent()));
				node = eElement.getElementsByTagName("TotalGravado").item(0);
				recepcionFactura.setFacturaTotalGravado(Utils.stringToDouble(node.getTextContent()));
				node = eElement.getElementsByTagName("TotalExento").item(0);
				recepcionFactura.setFacturaTotalExento(Utils.stringToDouble(node.getTextContent()));
				node = eElement.getElementsByTagName("TotalExonerado").item(0);
				recepcionFactura.setFacturaTotalExonerado(Utils.stringToDouble(node.getTextContent()));
				node = eElement.getElementsByTagName("TotalVenta").item(0);
				recepcionFactura.setFacturaTotalVenta(Utils.stringToDouble(node.getTextContent()));
				node = eElement.getElementsByTagName("TotalDescuentos").item(0);
				recepcionFactura.setFacturaTotalDescuentos(Utils.stringToDouble(node.getTextContent()));
				node = eElement.getElementsByTagName("TotalVentaNeta").item(0);
				recepcionFactura.setFacturaTotalVentaNeta(Utils.stringToDouble(node.getTextContent()));
				node = eElement.getElementsByTagName("TotalImpuesto").item(0);
				recepcionFactura.setFacturaTotalImpuestos(Utils.stringToDouble(node.getTextContent()));
				node = eElement.getElementsByTagName("TotalIVADevuelto").item(0);
				recepcionFactura.setFacturaTotalIVADevuelto(Utils.stringToDouble(node.getTextContent()));
				node = eElement.getElementsByTagName("TotalComprobante").item(0);
				recepcionFactura.setFacturaTotalComprobante(Utils.stringToDouble(node.getTextContent()));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return recepcionFactura;
	}

	private static String valorString(Node node, String name, Integer index) {
		if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) node;
			Node node1 = eElement.getElementsByTagName(name).item(0);

			if (node1 == null)
				return Constantes.EMPTY;
			return node1.getTextContent().toString();
		}

		return Constantes.EMPTY;
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