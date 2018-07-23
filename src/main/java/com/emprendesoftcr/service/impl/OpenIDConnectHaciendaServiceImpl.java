package com.emprendesoftcr.service.impl;



import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.fisco.OpenIDConnectHacienda;
import com.emprendesoftcr.service.OpenIDConnectHaciendaService;

@Lazy
@Service("openIDConnectHaciendaService")
@Transactional
public class OpenIDConnectHaciendaServiceImpl implements OpenIDConnectHaciendaService {
	
	private static final String IDP_URI = "https://idp.comprobanteselectronicos.go.cr/auth/realms/rut-stag/protocol/openid-connect";
  private static final String IDP_CLIENT_ID = "api-stag";
  

	public OpenIDConnectHacienda findbyAcceso(String usuario, String clave) {
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
			// TODO: handle exception
		}
		
		return openIDConnectHacienda;
	}

}
