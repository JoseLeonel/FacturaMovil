package com.emprendesoftcr.components;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.emprendesoftcr.Bo.CorreoAutomaticoBo;
import com.emprendesoftcr.Bo.IFEMensajeReceptorAutomaticoBo;
import com.emprendesoftcr.Bo.RecepcionFacturaBo;
import com.emprendesoftcr.modelo.CorreoAutomatico;
import com.emprendesoftcr.modelo.FEMensajeReceptorAutomatico;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.UnzipFiles;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.utils.XmlHelper;

@Component
public class MensajeReceptorAutomatico {

	@Value("${path.upload.files.api}")
	private String													pathUploadFilesApi;

	@Autowired
	private IFEMensajeReceptorAutomaticoBo	_mrService;

	@Autowired
	public JavaMailSender										emailSender;

	@Value("${correo.de.distribucion}")
	private String													correoDistribucion;

	@Value("${api.host}")
	private String													apiHost;

	@Value("${api.port}")
	private String													apiPort;

	@Value("${api.userName}")
	private String													apiUserName;

	@Value("${api.password}")
	private String													apiPassword;

	@Value("${api.tipo.gasto}")
	private String													apiTipoGasto;

	@Value("${api.notificar.cliente}")
	private String													apiNotificarCliente;

	private final Logger										log	= LoggerFactory.getLogger(getClass());

	private ZipFile													zipFile;

	@Autowired
	private CorreoAutomaticoBo							correoAutomaticoBo;
	@Autowired
	private RecepcionFacturaBo							recepcionFacturaBo;

	public static class Attachment {

		private final String	contentType;
		private final String	fileName;
		private final byte[]	contents;

		public Attachment(String contentType, String fileName, byte[] contents) {
			this.contentType = contentType;
			this.fileName = fileName;
			this.contents = contents;
		}

		public String getContentType() {
			return contentType;
		}

		public byte[] getContents() {
			return contents;
		}

		public String getFilename() {
			return fileName;
		}
	}

	private void addAttachments(String direccionPathCarpetaXML, String correoCompras, Multipart parts, String enviarA) throws Exception {

		String saveDirectory = direccionPathCarpetaXML + "/mr-automatico";
//		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//		SimpleDateFormat formato1 = new SimpleDateFormat("dd/mm/YYYY HH:mm:ss a");
		String emisorFactura = "";
		String emisorTipoIdentificacion = "";
		String emisorIdentificacion = "";
		String fechaEmision = "";
		String moneda = "";
		String tipoCambio = "";
		String totalImpuestos = "";
		String totalComprobante = "";
		String receptorTipoIdentificacion = "";
		String receptorIdentificacion = "";
		String claveFactura = "";
		String facturaXml = "";
		String facturaXmlZip = "";
		String facturaPdfZip = "";
		String extension = "";
		String consecutivoFactura = "";
		String condicionVenta = "";
		String tipo_doc = "";

		XPath xPath = XPathFactory.newInstance().newXPath();

		int partes = parts.getCount();
		String condicion = "N";

		String prefijo = "";
		if (partes > 4) {

			/**
			 * 4 Partes por que puede venir el además de los 3 archivos 1 adjunto más (he notado que mandan el estado de cuenta también) Si tiene más de 4 partes entonces puede ser que el cliente esta envíando varias facturas en un mismo correo
			 */
			prefijo = "";
			condicion = "S";

		} else {
			prefijo = System.currentTimeMillis() + "-";
		}

		for (int i = 0, n = partes; i < n; i++) {

			MimeBodyPart part = (MimeBodyPart) parts.getBodyPart(i);
			if (isAttachment(part)) {

				if (condicion.equalsIgnoreCase("S")) {
					prefijo = System.currentTimeMillis() + "-";
				}

				String fileName = "";
				try {
					fileName = prefijo + "" + "sinmata" + this.getFileName(part).substring(this.getFileName(part).toString().lastIndexOf(".")).toLowerCase();
				} catch (Exception e) {
					fileName = "";
				}

				String rutaAchivoGuardado = saveDirectory + File.separator + fileName;
				String rutaAchivoGuardado2 = saveDirectory + File.separator;

				int j = fileName.lastIndexOf('.');
				extension = fileName.substring(j + 1);

				if (extension.equalsIgnoreCase("xml") || extension.equalsIgnoreCase("pdf") || extension.equalsIgnoreCase("zip")) {

					/**
					 * Guardo los archivos
					 */
					((MimeBodyPart) part).saveFile(rutaAchivoGuardado);

					if (extension.equalsIgnoreCase("zip")) {

						zipFile = new ZipFile(rutaAchivoGuardado);
						new UnzipFiles().unzip(rutaAchivoGuardado, rutaAchivoGuardado2);

						Enumeration<? extends ZipEntry> entries = zipFile.entries();
						while (entries.hasMoreElements()) {

							ZipEntry entry = entries.nextElement();
							String nameFile = entry.getName();

							int n2 = nameFile.lastIndexOf('.');
							extension = nameFile.substring(n2 + 1);
							if (extension.equalsIgnoreCase("xml")) {

								/**
								 * Ruta donde se encuentra el XML para poder leerlo
								 */
								Document xml = XmlHelper.getDocument(rutaAchivoGuardado2 + nameFile);

								/**
								 * Datos del emisor del comprobante
								 */
								if (extension.equalsIgnoreCase("pdf")) {
									facturaPdfZip = facturaPdfZip + nameFile;
								}

								claveFactura = getNameFieldXml(xPath, xml, "Clave");
								log.info("-->Clave Factura: " + claveFactura);
								consecutivoFactura = getNameFieldXml(xPath, xml, "NumeroConsecutivo");
								condicionVenta = getNameFieldXml(xPath, xml, "CondicionVenta");
								tipo_doc = Utils.obtenerTipoDocumentoConsecutivo(consecutivoFactura);
								log.info("-->Consecutivo: " + consecutivoFactura);
								if (claveFactura.length() > 30) {

									fechaEmision = getNameFieldXml(xPath, xml, "FechaEmision");
									emisorFactura = getNameFieldXml(xPath, xml, "Emisor/Nombre");
									emisorTipoIdentificacion = getNameFieldXml(xPath, xml, "Emisor/Identificacion/Tipo");
									emisorIdentificacion = getNameFieldXml(xPath, xml, "Emisor/Identificacion/Numero");
									RecepcionFactura recepcionFactura = recepcionFacturaBo.findByClaveAndCedulaEmisor(claveFactura, emisorIdentificacion);

									try {
										moneda = getNameFieldXml(xPath, xml, "ResumenFactura/CodigoTipoMoneda/CodigoMoneda");
										tipoCambio = getNameFieldXml(xPath, xml, "ResumenFactura/CodigoTipoMoneda/TipoCambio");
									} catch (Exception e) {
										moneda = "CRC";
										tipoCambio = "1.00";
									}
									try {
										/**
										 * Capturo la exeption, por que si la factura es exenta y como no tiene el nodo de impuestos va a reventar
										 */
										totalImpuestos = getNameFieldXml(xPath, xml, "ResumenFactura/TotalImpuesto");
									} catch (Exception e) {
										totalImpuestos = "0.00";
									}
									totalComprobante = getNameFieldXml(xPath, xml, "ResumenFactura/TotalComprobante");
									/**
									 * Datos del receptor
									 */
									receptorTipoIdentificacion = getNameFieldXml(xPath, xml, "Receptor/Identificacion/Tipo");
									receptorIdentificacion = getNameFieldXml(xPath, xml, "Receptor/Identificacion/Numero");
									facturaXmlZip = nameFile;

									try {
										if (recepcionFactura == null) {
											FEMensajeReceptorAutomatico mr = new FEMensajeReceptorAutomatico();
											log.info("-->Correo Compra: " + correoCompras);
											log.info("-->Emisor: " + emisorFactura);
											mr.setCorreoCompras(correoCompras);
											mr.setClave(claveFactura);
											mr.setTipoDoc(tipo_doc);
											mr.setConsecutivo(consecutivoFactura);
											mr.setCondicionVenta(condicionVenta);

											log.info("-->Clave Factura: " + claveFactura);
											mr.setCorreoFrom(enviarA);
											mr.setEmisorFactura(emisorFactura);
											mr.setEmisorTipoIdentificacion(emisorTipoIdentificacion);
											mr.setEmisorIdentificacion(emisorIdentificacion);
											mr.setFechaEmision(fechaEmision);
											mr.setTotalImpuestos(totalImpuestos);
											mr.setTotalComprobante(totalComprobante);
											mr.setReceptorTipoIdentificacion(receptorTipoIdentificacion);
											mr.setReceptorIdentificacion(receptorIdentificacion);
											mr.setFechaCreacion(new Date());
											mr.setFacturaPdf(fileName);
											mr.setFacturaXml(facturaXmlZip);
											mr.setMoneda(moneda);
											mr.setTipoCambio(tipoCambio);
											mr.setEstado("P");
											mr.setFechaCreacion(new Date());
											mr.setTipoGasto(this.apiTipoGasto);

											_mrService.save(mr);
										}
									} catch (Exception e) {
										e.printStackTrace();

										log.info("Notifico a " + enviarA + " que ya la factura existe " + claveFactura + emisorFactura);
										if (this.apiNotificarCliente.equalsIgnoreCase("S")) {

											log.info("Notifico a " + enviarA + " que ya la factura existe " + claveFactura + emisorFactura);

//											String empresaSaluda = "Soluciones Informáticas Mata";
//											String asunto = "Notificación del sistema de recepción automático - La Factura Electrónica generada por " + emisorFactura + ", ya fue recibida anteriormente.";
//											Date _fechaEmision_ = formato.parse(fechaEmision);

											log.info("Se enviara una notificación a :" + enviarA);

											// this.enviaNotificacionMR(claveFactura, emisorFactura, empresaSaluda, formato1.format(_fechaEmision_), totalComprobante, enviarA, asunto);

										}

									}

									facturaPdfZip = "";

								}
							}

						}

					}

					/**
					 * XML Normal
					 */
					if (extension.equalsIgnoreCase("xml")) {

						/**
						 * Ruta donde se encuentra el XML para poder leerlo
						 */
						Document xml = XmlHelper.getDocument(rutaAchivoGuardado);

						/**
						 * Datos del emisor del comprobante
						 */
						claveFactura = getNameFieldXml(xPath, xml, "Clave");
						consecutivoFactura = getNameFieldXml(xPath, xml, "NumeroConsecutivo");
						condicionVenta = getNameFieldXml(xPath, xml, "CondicionVenta");
						tipo_doc = Utils.obtenerTipoDocumentoConsecutivo(consecutivoFactura);
						fechaEmision = getNameFieldXml(xPath, xml, "FechaEmision");
						emisorFactura = getNameFieldXml(xPath, xml, "Emisor/Nombre");
						emisorTipoIdentificacion = getNameFieldXml(xPath, xml, "Emisor/Identificacion/Tipo");
						emisorIdentificacion = getNameFieldXml(xPath, xml, "Emisor/Identificacion/Numero");

						try {
							moneda = getNameFieldXml(xPath, xml, "ResumenFactura/CodigoTipoMoneda/CodigoMoneda");
							tipoCambio = getNameFieldXml(xPath, xml, "ResumenFactura/CodigoTipoMoneda/TipoCambio");
						} catch (Exception e) {
							moneda = "CRC";
							tipoCambio = "1.00";
						}

						try {
							/**
							 * Capturo la exeption, por que si la factura es exenta y como no tiene el nodo de impuestos va a reventar
							 */
							totalImpuestos = getNameFieldXml(xPath, xml, "ResumenFactura/TotalImpuesto");
						} catch (Exception e) {
							totalImpuestos = "0.00";
						}
						totalComprobante = getNameFieldXml(xPath, xml, "ResumenFactura/TotalComprobante");

						/**
						 * Datos del receptor
						 */
						receptorTipoIdentificacion = getNameFieldXml(xPath, xml, "Receptor/Identificacion/Tipo");
						receptorIdentificacion = getNameFieldXml(xPath, xml, "Receptor/Identificacion/Numero");
						if (claveFactura.length() > 30) {

							File file = new File(rutaAchivoGuardado);

							/**
							 * Renombro el archivo
							 */
							String nameFe = "fe" + fileName;
							File file2 = new File(saveDirectory + File.separator + nameFe);
							file.renameTo(file2);

							facturaXml = nameFe;
							RecepcionFactura recepcionFactura = recepcionFacturaBo.findByClaveAndCedulaEmisor(claveFactura, emisorIdentificacion);
							try {
								if (recepcionFactura == null) {
									FEMensajeReceptorAutomatico mr = new FEMensajeReceptorAutomatico();
									mr.setClave(claveFactura);
									mr.setCorreoCompras(correoCompras);
									mr.setTipoDoc(tipo_doc);
									mr.setConsecutivo(consecutivoFactura);
									mr.setCondicionVenta(condicionVenta);
									mr.setCorreoFrom(enviarA);
									mr.setEmisorFactura(emisorFactura);
									mr.setEmisorTipoIdentificacion(emisorTipoIdentificacion);
									mr.setEmisorIdentificacion(emisorIdentificacion);
									mr.setFechaEmision(fechaEmision);
									mr.setTotalImpuestos(totalImpuestos);
									mr.setTotalComprobante(totalComprobante);
									mr.setReceptorTipoIdentificacion(receptorTipoIdentificacion);
									mr.setReceptorIdentificacion(receptorIdentificacion);
									mr.setFechaCreacion(new Date());

									mr.setFacturaPdf(prefijo + "sinmata.pdf");
									/**
									 * Si vienen varias facturas anulo el nombre por que no puedo recuperar el PDF
									 */
									if (condicion.equalsIgnoreCase("S")) {
										mr.setFacturaPdf(null);
									}

									mr.setFacturaXml(facturaXml);
									mr.setMoneda(moneda);
									mr.setTipoCambio(tipoCambio);
									mr.setEstado("P");
									mr.setTipoGasto(this.apiTipoGasto);
									_mrService.save(mr);
								}
							} catch (Exception e) {
								log.info("Notifico a " + enviarA + " que ya la factura existe " + claveFactura + emisorFactura);
								if (this.apiNotificarCliente.equalsIgnoreCase("S")) {

									log.info("Notifico a " + enviarA + " que ya la factura existe " + claveFactura + emisorFactura);

//									String empresaSaluda = "Soluciones Informáticas Mata";
//									String asunto = "Notificación del sistema de recepción automático - La Factura Electrónica generada por " + emisorFactura + ", ya fue recibida anteriormente.";
//									Date _fechaEmision_ = formato.parse(fechaEmision);

									log.info("Se enviara una notificación a :" + enviarA);

								//	this.enviaNotificacionMR(claveFactura, emisorFactura, empresaSaluda, formato1.format(_fechaEmision_), totalComprobante, enviarA, asunto);

								}
							}
						}
					}
				}
			} else {
				try {
					if (part.getContent() instanceof Multipart) {
						addAttachments(direccionPathCarpetaXML, correoCompras, (Multipart) part.getContent(), "");
					}
				} catch (UnsupportedEncodingException e) {
					// ignore because it's probably not a multipart part anyway
					// if the encoding is unsupported
					log.warn("Unsupported encoding found for part while trying to discover attachments. " + "Attachment will be ignored.", e);
				}
			}
		}
	}

	@Scheduled(fixedDelay = 160000)
	public void downloadEmailAttachments() throws Exception {
		String correoProblemas = Constantes.EMPTY;
		Properties properties = new Properties();
		properties.put("mail.store.protocol", "imaps");
		properties.put("mail.imaps.port", apiPort);
		Session session = Session.getDefaultInstance(properties, null);

		try {
			log.info("Inicio del proceso de revision de correos  ");
			Collection<CorreoAutomatico> lista = correoAutomaticoBo.allEmails();
			if (!lista.isEmpty() && lista != null) {
				for (CorreoAutomatico correoAutomatico : lista) {
					correoProblemas = correoAutomatico.getCorreoAceptacion();
					log.info("Correo----------------------->:  " + correoAutomatico.getCorreoAceptacion());
					Store store = session.getStore("imaps");
					store.connect(this.apiHost, correoAutomatico.getCorreoAceptacion(), correoAutomatico.getClave());
					downloadEmailAttachmentsVer(store, correoAutomatico.getDirecionDirectorio(), correoAutomatico.getCorreoAceptacion());

				}

			}

		} catch (Exception e) {
			log.error("** Error  ejecutar la reccion de compras automaticas: " + e.getMessage() + " fecha " + new Date());
			log.error("** Error  correo con problemas: " + correoProblemas);
		} finally {
			log.info("fin del proceso de revision de correos  ");
		}
	}

	private void downloadEmailAttachmentsVer(Store store, String direccionPathCarpetaXML, String correoCompras) throws Exception {
		try {
			log.info("================================================> Abriendo INBOX");
			Folder folderInbox = store.getFolder("INBOX");
			folderInbox.open(Folder.READ_WRITE);

			Message[] arrayMessages = folderInbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
			for (int i = 0; i < arrayMessages.length; i++) {

				Message message = arrayMessages[i];

				Address[] fromAddress = message.getFrom();
				String from = fromAddress[0].toString();
				Pattern pattern = Pattern.compile("<(.*?)>");
				Matcher matcher = pattern.matcher(from);
				String enviarA = "";
				if (matcher.find()) {
					enviarA = matcher.group(1).toString();
				}

				String contentType = message.getContentType();
				@SuppressWarnings("unused")
				String messageContent = "";

				if (contentType.contains("multipart")) {

					Multipart parts = (Multipart) message.getContent();
					addAttachments(direccionPathCarpetaXML, correoCompras, parts, enviarA.toString());

				}

			}

			log.info("================================================> Cerrando INBOX");

			/**
			 * Me desconecto
			 */
			folderInbox.close(true);
			store.close();

		} catch (NoSuchProviderException ex) {

			log.info("NoSuchProviderException mr inbox" + ex.getMessage());

		} catch (MessagingException ex) {

			log.info("Could not connect to the message store " + ex.getMessage());

		} catch (IOException ex) {

			log.info("Otro error generado por el MR inbox " + ex.getMessage());

		}
	}

	private static boolean isAttachment(MimeBodyPart part) throws MessagingException {
		return Part.ATTACHMENT.equals(part.getDisposition()) || Part.INLINE.equals(part.getDisposition()) || (part.getDisposition() == null && part.getFileName() != null);
	}

	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}

	/**
	 * Notificación Mensaje Receptor
	 */
	public void enviaNotificacionMR(String clave, String emisorFactura, String empresaSaluda, String fechaEmision, String totalComprobante, String emailTo, String asunto) throws IOException, SQLException, MessagingException {

		try {

			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			String msj = "";
			String consecutivo = clave.substring(21, 41);
			msj += "<p style=\"font-family: Arial;\">Estimado cliente,</p>";
			msj += "<p style=\"font-family: Arial;\">El comprobante de Factura Electrónica con la consecutivo: <b>" + consecutivo + "</b>, generada por <b>" + emisorFactura + "</b> por un monto de <b>" + totalComprobante + "</b> ya fue recibida anteriormente.</b></p>";
			msj += "<p style=\"font-family: Arial;\">Este correo se genero de forma automática, por favor no responder.</b></p>";
			msj += "<p style=\"font-family: Arial;\">Saludos,</p>";
			msj += "<p style=\"font-family: Arial;\"><b>" + empresaSaluda + "</b></p>";
			helper.setTo(new InternetAddress(emailTo.trim(), "Notificación del sistema de recepción automatico - " + empresaSaluda));
			helper.setFrom(new InternetAddress(this.correoDistribucion, "Notificación del sistema de recepción automatico"));
			helper.setSubject(asunto);
			helper.setText(msj, true);

			try {

				emailSender.send(message);
				log.info("Se envío un mail a " + emailTo);

			} catch (Exception e) {
				log.info("No se pudo enviar el correo a " + emailTo);
				e.printStackTrace();
			}

		} catch (Exception ex) {
			System.out.println("Error del reporte: " + ex.getMessage());
		}

	}

	private String getFileName(Part part) throws MessagingException, UnsupportedEncodingException {
		if (!(part instanceof MimeBodyPart)) {
			return part.getFileName();
		}
		String contentType = part.getContentType();
		String ret = null;
		try {
			ret = javax.mail.internet.MimeUtility.decodeText(part.getFileName());

			if (contentType.contains("application/xml") || contentType.contains("APPLICATION/XML")) {
				// ret = "sinmata.xml";

				// ret = "sinmata.xml";

			}
			if (contentType.contains("application/pdf") || contentType.contains("APPLICATION/PDF")) {
				// ret = "sinmata.pdf";
			}
		} catch (NullPointerException ex) {

			if (contentType.contains("application/xml") || contentType.contains("APPLICATION/XML")) {
				// ret = "sinmata.xml";
			}

			if (contentType.contains("application/pdf") || contentType.contains("APPLICATION/PDF")) {
				// ret = "sinmata.pdf";
			}
		}
		return ret == null ? "" : ret;
	}

	private String getNameFieldXml(XPath xPath, Document xml, String field) {
		String j = "";
		NodeList fe = null, nc = null, fec = null;
		try {

			try {
				fe = (NodeList) xPath.evaluate("/FacturaElectronica/" + field, xml.getDocumentElement(), XPathConstants.NODESET);
				j = fe.item(0).getTextContent();
				log.info("FE _______________________________ " + j);
			} catch (Exception e) {
				// log.info("NO ES FE");
			}

			try {
				nc = (NodeList) xPath.evaluate("/NotaCreditoElectronica/" + field, xml.getDocumentElement(), XPathConstants.NODESET);
				j = nc.item(0).getTextContent();
				log.info("NC _______________________________ " + j);
			} catch (Exception e) {
				// log.info("NO ES NC");
			}

			try {
				fec = (NodeList) xPath.evaluate("/FacturaElectronicaCompra/" + field, xml.getDocumentElement(), XPathConstants.NODESET);
				j = fec.item(0).getTextContent();
				log.info("FEC _______________________________ " + j);
			} catch (Exception e) {
				// log.info("NO ES FEC");
			}

		} catch (Exception e) {
			j = "";
		}
		return j;
	}

}