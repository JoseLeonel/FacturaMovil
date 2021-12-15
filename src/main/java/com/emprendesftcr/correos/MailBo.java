package com.emprendesftcr.correos;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.google.inject.spi.Message;

import io.jsonwebtoken.io.IOException;

public class EnviarCorreo {

	public static void enviaCorreoElectronico(String addressTo, String from, String body, String emisor, List<File> adjuntos, String smtAddrs, String ambienteEnvioCorreo) throws Exception {
		// String textoMensaje = null;
		try {
			if (addressTo != null && !addressTo.isEmpty()) {
				// textoMensaje = body;
				Properties props = new Properties();
				props.put("mail.smtp.host", smtAddrs);
				props.setProperty("mail.smtp.auth", "false");
				Session sessionB = Session.getInstance(props);
				sessionB.setDebug(false);
				MimeMessage message = new MimeMessage(sessionB);
				message.addHeader("Content-type", "text/HTML;charset-UTF-8");
				message.addHeader("format", "flowed");
				message.addHeader("Content-Transfer-Encoding", "8bit");

				message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(addressTo.trim(), false));

				MimeBodyPart mimeBodyPart1 = new MimeBodyPart();
				MimeMultipart mimeMultipart = new MimeMultipart();

				// mimeMultipart.addBodyPart((BodyPart) mimeBodyPart1);
				if (adjuntos != null && !adjuntos.isEmpty()) {
					for (File archivoAdjunto : adjuntos) {
						MimeBodyPart attachPart = new MimeBodyPart();
						attachPart.attachFile(archivoAdjunto);
						attachPart.setFileName(archivoAdjunto.getName());
						mimeMultipart.addBodyPart(attachPart);
					}
				}
				String plantillaHtml = PlantillaCorreo.PLANTILLA_HTML.replace("contenido", body);
				mimeBodyPart1.setContent(plantillaHtml, "text/html"); // 5
				mimeMultipart.addBodyPart(mimeBodyPart1);

				message.setFrom((Address) new InternetAddress(emisor, ambienteEnvioCorreo));
				message.setSubject(from, "UTF-8");
				message.setSentDate(new Date());
				// mimeBodyPart1.setText(textoMensaje, "UTF-8");
				message.setContent((Multipart) mimeMultipart);
				Transport t = sessionB.getTransport("smtp");
				t.connect();
				t.sendMessage(message, message.getAllRecipients());
				t.close();
				System.out.println("*** Email Enviado desde SISO fue exitoso ***");
			} else {
				throw new Exception("** No hay correos remitentes ***");
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (MessagingException e) {
			e.printStackTrace();
			throw e;
		}
	}

}
