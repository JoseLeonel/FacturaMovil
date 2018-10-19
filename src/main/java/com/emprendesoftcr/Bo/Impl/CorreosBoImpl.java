package com.emprendesoftcr.Bo.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.emprendesoftcr.Bo.CorreosBo;
import com.emprendesoftcr.fisco.VelocityEngineUtils;
import com.emprendesoftcr.modelo.Attachment;

@Service("correosBo")
public class CorreosBoImpl implements CorreosBo {

	@Autowired
	private JavaMailSender	mailSender;

	@Autowired
	private VelocityEngine	velocityEngine;

	@Override
	public void enviarConAttach(final Collection<Attachment> attachments,  ArrayList<String> correoList, final String from, final String subjet, final String email, final Map<String, Object> model) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

				for (Iterator<String> iterator = correoList.iterator(); iterator.hasNext();) {
					String correo = (String) iterator.next();
					message.addTo(correo);
				}
				
				
				message.setFrom(from);
				message.setSubject(subjet);
      
 				for (Iterator<Attachment> iterator = attachments.iterator(); iterator.hasNext();) {
					Attachment attachment = iterator.next();
					message.addAttachment(attachment.getNombre(), attachment.getAttachment());
				}
      	 
       

				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, email, "UTF-8", model);
				message.setText(text, true);
			}
		};
		this.mailSender.send(preparator);
	}
	
	@Override
	public void sendSimpleMessage( String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        message.setFrom("SoporteEmpredesoftCR@emprendesoftcr.com");
        this.mailSender.send(message);
     
    }

}
