package com.emprendesoftcr.Bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.emprendesoftcr.modelo.Attachment;

public interface  MailBo {
	void enviarConAttach(final Collection<Attachment> attachments, ArrayList<String> correoList, String from, String subjet, String email,  Map<String, Object> model);
}
