package com.emprendesoftcr.Bo.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.emprendesoftcr.Bo.MailBo;
import com.emprendesoftcr.modelo.Attachment;
import com.emprendesoftcr.utils.Utils;



@Service("mailService")
@Transactional
@EnableTransactionManagement
public class MailBoImpl implements MailBo {
	@Autowired
	private JavaMailSender	mailSender;
	
	@Autowired
	private TemplateEngine templateEngine;

	private Logger					log	= LoggerFactory.getLogger(this.getClass());

	@Override
	public void enviarConAttach(Collection<com.emprendesoftcr.modelo.Attachment> attachments, ArrayList<String> correoList, String from, String subjet, String email, Map<String, Object> model) {
		
			try {

				mailSender.send(new MimeMessagePreparator() {

					public void prepare(MimeMessage mimeMessage) throws MessagingException {
						Integer cantidadDocumentos = 1;
						try {
							MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
							for (Iterator<String> iterator = correoList.iterator(); iterator.hasNext();) {
								String correo = (String) iterator.next();
								if (Utils.validarCorreo(correo)) {
									message.addTo(new InternetAddress(correo));
								}
							}
							if (attachments != null) {
								cantidadDocumentos = 1;
								for (Attachment attachment : attachments) {
									if (cantidadDocumentos <= 3) {
										message.addAttachment(attachment.getNombre(), attachment.getAttachment());
									}
									cantidadDocumentos++;
								}
							}
							message.setSubject(subjet);
							message.setFrom(from);
							message.setSentDate(new Date());
						// Crear cuerpo del mensaje
					    Context context = new Context();
					  
					    context.setVariables(model);
					    context.setVariable("fechaEmision", model.get("fechaEmision"));
					    
					   
					    String emailContent = templateEngine.process(email, context);
							// String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, email, "UTF-8", model);
							
							message.setText(emailContent,true);
						} catch (Exception e) {
							log.error("Error al enviar el mail: ", e.getMessage());
							throw e;
						}
					}
				});

			} catch (MailSendException ex1) {
				log.error("Error al enviar el mail: ", ex1.getMessage());
				throw ex1;

			} catch (MailException ex) {
				log.error("Error al enviar el mail: ", ex.getMessage());
				throw ex;

			} catch (Exception e) {
				log.error("Error al enviar el mail: ", e.getMessage());
				throw e;
			}

	
	
		
	}



	

}
