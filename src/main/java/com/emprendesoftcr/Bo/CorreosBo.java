package com.emprendesoftcr.Bo;
import java.util.Collection;
import java.util.Map;

import com.emprendesoftcr.modelo.Attachment;


public interface CorreosBo {
	
	//Envio de correos con documentos adjuntos
	void enviarConAttach(Collection<Attachment> attachments, String to, String from, String subjet, String email, Map<String, Object> model);
	

}
