package com.emprendesoftcr.service.impl;



import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emprendesoftcr.fisco.OpenIDConnectHacienda;
import com.emprendesoftcr.service.OpenIDConnectHaciendaService;

@Service("openIDConnectHaciendaService")
@EnableTransactionManagement
public class OpenIDConnectHaciendaServiceImpl implements OpenIDConnectHaciendaService {
	
	private static final String IDP_URI = "https://idp.comprobanteselectronicos.go.cr/auth/realms/rut-stag/protocol/openid-connect";
  private static final String IDP_CLIENT_ID = "api-stag";
  private Logger	log= LoggerFactory.getLogger(this.getClass());

	public OpenIDConnectHacienda findbyAcceso(String usuario, String clave) throws Exception {
		OpenIDConnectHacienda openIDConnectHacienda = null;
		try {
			
			Client client = ClientBuilder.newClient();
		  WebTarget target = client.target(IDP_URI + "/token");
		  Form form = new Form();
		  form.param("grant_type", "password")
		               .param("username", usuario)
		               .param("password", clave)
		               .param("client_id", IDP_CLIENT_ID);
		  Response response = target.request().post(Entity.form(form));
			
		} catch (Exception e) {
			log.info("** Error  findbyAcceso: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		
		return openIDConnectHacienda;
	}

}
