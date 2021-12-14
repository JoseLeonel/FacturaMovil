package com.emprendesftcr.correos;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EnvioEmail {
	 //Importante hacer la inyección de dependencia de JavaMailSender:
  @Autowired
  private JavaMailSender mailSender;
//Pasamos por parametro: destinatario, asunto y el mensaje
  public void sendEmail(String to,String from, String subject, String content) throws UnsupportedEncodingException, MessagingException {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message);
      helper.setFrom("josehernandezchaverri@gmail.com", "db1995");
      helper.setText("<h1 style='color: #654321;text-align: center;'>H1 Tag</h1><p>Some text...</p>", true);
      helper.setSubject(subject);
      
      
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

			message.setFrom("josehernandezchaverri@gmail.com", "db1995");
			message.setSubject(from, "UTF-8");
			message.setSentDate(new Date());
			// mimeBodyPart1.setText(textoMensaje, "UTF-8");
			message.setContent((Multipart) mimeMultipart);
			
      SimpleMailMessage email = new SimpleMailMessage();

      email.setTo(to);
     
      email.setSubject(subject);
      email.setText(content);
     
      mailSender.send(email);
  }
}
