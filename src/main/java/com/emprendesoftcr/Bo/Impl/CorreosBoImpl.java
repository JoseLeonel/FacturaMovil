package com.emprendesoftcr.Bo.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.emprendesoftcr.Bo.CorreosBo;
import com.emprendesoftcr.fisco.VelocityEngineUtils;
import com.emprendesoftcr.modelo.Attachment;
import com.emprendesoftcr.utils.Utils;

@Service("correosBo")
public class CorreosBoImpl implements CorreosBo {

	@Autowired
	private JavaMailSender	mailSender;

	@Autowired
	private VelocityEngine	velocityEngine;

	private Logger					log	= LoggerFactory.getLogger(this.getClass());

	@Override
	public Boolean enviarConAttach(final Collection<Attachment> attachments, ArrayList<String> correoList, final String from, final String subjet, final String email, final Map<String, Object> model) {
		Boolean resultado = Boolean.FALSE;
		
		try {
			mailSender.send(new MimeMessagePreparator() {

				public void prepare(MimeMessage mimeMessage) throws MessagingException {
					Integer cantidadDocumentos = 1;
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

					for (Iterator<String> iterator = correoList.iterator(); iterator.hasNext();) {
						String correo = (String) iterator.next();
						if (Utils.validarCorreo(correo)) {
							message.addTo(new InternetAddress(correo));
						}
						if (attachments != null) {
							cantidadDocumentos = 1;
							for (Attachment attachment : attachments) {
								if(cantidadDocumentos <= 3 ) {
									message.addAttachment(attachment.getNombre(), attachment.getAttachment());	
								}
								cantidadDocumentos ++;
								
							}
						}

					}

					message.setSubject(subjet);
					message.setFrom(from);

					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, email, "UTF-8", model);
					message.setText(text, true);


				}
			});
			
		} catch (Exception e) {
			log.error("Error al enviar el mail: ", e);
			throw e;
		
		}finally {
			resultado = Boolean.TRUE;
		}
		return resultado;
	}

	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		message.setFrom("emprendesoftcr@facturaemprendesoftcr.com ");
		this.mailSender.send(message);

	}

}
