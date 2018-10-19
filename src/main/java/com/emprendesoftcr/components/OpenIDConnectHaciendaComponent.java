package com.emprendesoftcr.components;

import static com.emprendesoftcr.fisco.Keys.CLIENT_ID;
import static com.emprendesoftcr.fisco.Keys.GRANT_TYPE;
import static com.emprendesoftcr.fisco.Keys.PASSWORD;
import static com.emprendesoftcr.fisco.Keys.POST_HEADERS;
import static com.emprendesoftcr.fisco.Keys.POST_RESPONSE;
import static com.emprendesoftcr.fisco.Keys.POST_STATUS_CODE;
import static com.emprendesoftcr.fisco.Keys.POST_X_ERROR_CAUSE;
import static com.emprendesoftcr.fisco.Keys.USER_NAME;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.fisco.ClientPost;
import com.emprendesoftcr.fisco.OpenIDConnectHacienda;
import com.emprendesoftcr.fisco.TokenInfo;
import com.emprendesoftcr.fisco.TokenInfoJson;
import com.emprendesoftcr.modelo.Empresa;
import com.google.common.collect.ImmutableMap;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Componente para acceder al token de hacienda OpenIDConnectHaciendaComponent.
 * @author jose.
 * @since 13 jul. 2018
 */
@Component
@Transactional
@EnableTransactionManagement
public class OpenIDConnectHaciendaComponent {

	private Logger				log	= LoggerFactory.getLogger(this.getClass());

	private final String	IDP_URI;
	private final String	IDP_CLIENT_ID;

	@Autowired
	public OpenIDConnectHaciendaComponent(@Value("${open.id.connect.hacienda.idp.uri.token}") final String IDP_URI, @Value("${OpenIDConnectHacienda.IDP_CLIENT_ID}") final String IDP_CLIENT_ID) {
		this.IDP_URI = IDP_URI;
		this.IDP_CLIENT_ID = IDP_CLIENT_ID;

	}

	/**
	 * Comunicacion para obtener el token en tributacion
	 * @param username
	 * @param password
	 * @return
	 * @throws IOException
	 */

	public OpenIDConnectHacienda getToken(Empresa empresa) throws IOException {
		OpenIDConnectHacienda openIDConnectHacienda = new OpenIDConnectHacienda();
		try {
   		TokenInfo tokenInfo = getTokenUrlHacienda(empresa);
			if(tokenInfo !=null) {
				openIDConnectHacienda.setAccess_token(tokenInfo.getAccessToken());
				openIDConnectHacienda.setRefresh_token(tokenInfo.getRefreshToken());
				
			}else {
				log.info("** Error  NO SE OCTUVO EL TOKEN: "  + " fecha " + new Date() + " empresa:" + empresa.getNombre());
			}
			
		} catch (Exception e) {
			log.info("** Error  getToken: " + e.getMessage() + " fecha " + new Date() + " empresa:" + empresa.getNombre());
			throw e;
		}

		return openIDConnectHacienda;

	}

	private TokenInfo getTokenUrlHacienda(Empresa empresa) {
		ClientPost clientPost = null;
		
		MultivaluedMap multivaluedMap = asMap(empresa);
    
		String idp_uri = Constantes.EMPTY;
		
		if(empresa.getEstadoProduccion() !=null) {
			if(empresa.getEstadoProduccion().equals(Constantes.ESTADO_ACTIVO)) {
				idp_uri = Constantes.IDP_URI_PRODUCCION;
			}else {
				idp_uri = this.IDP_URI;
			}
		}
		
		Map response = send(idp_uri + "/token", multivaluedMap, MediaType.APPLICATION_FORM_URLENCODED_TYPE, ImmutableMap.of("Accept", "application/json", "User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2"));
		String body = (String) response.get(POST_RESPONSE);
		int statusCode = (int) response.get(POST_STATUS_CODE);
		if (statusCode < 300) {
			return TokenInfoJson.from(body);
		} else if (statusCode >= 500 && statusCode < 600) {
			log.info("** Error  getTokenUrlHacienda: "  + " fecha " + new Date() + " empresa:" + empresa.getNombre());          
		} else {

		}
		return null;
	//	return TokenInfoJson.from(body);
	}

	public Map send(String serviceUrl, MultivaluedMap bodyParams, MediaType contentType, Map<String, String> headers) {
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(serviceUrl);
			WebResource.Builder resBuilder = webResource.type(contentType);
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				resBuilder = resBuilder.header(entry.getKey(), entry.getValue());
			}
			ClientResponse response = resBuilder.post(ClientResponse.class, bodyParams);
			ImmutableMap headersResponse = ImmutableMap.copyOf(response.getHeaders());
			String strResponse = response.getEntity(String.class);
			if (response.getStatus() > 299) {
				List err = (List) headersResponse.get(POST_X_ERROR_CAUSE);
				String headerError = err != null && err.size() > 0 ? (String) err.get(0) : null;
				strResponse = headerError != null && headerError != "" ? headerError : strResponse;
			}
			return ImmutableMap.of(POST_RESPONSE, strResponse, POST_STATUS_CODE, response.getStatus(), POST_HEADERS, headersResponse);
		} catch (ClientHandlerException exc) {
			return ImmutableMap.of(POST_RESPONSE, "{}", POST_STATUS_CODE, 500, POST_HEADERS, ImmutableMap.of());
		}
	}

	/**
	 * 
	 * @param empresa
	 * @return
	 */
	private MultivaluedMap asMap(Empresa empresa) {
		MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		String idp_client_id = Constantes.EMPTY;
		if(empresa.getEstadoProduccion() !=null) {
			if(empresa.getEstadoProduccion().equals(Constantes.ESTADO_ACTIVO)) {
				idp_client_id = Constantes.IDP_CLIENT_ID_PRODUCCION;
			}else {
				idp_client_id = this.IDP_CLIENT_ID;
			}
		}
		formData.add(GRANT_TYPE, "password");
		formData.add(CLIENT_ID, idp_client_id);
		formData.add(USER_NAME, empresa.getUsuarioEnvioComprobante());
		formData.add(PASSWORD, empresa.getPasswordEnvioComprobante());
		return formData;
	}
/**
 * Desconecta el token
 * @param empresa
 * @param openIDConnectHacienda
 * @return
 * @throws IOException
 */
	
	public Map desconectarToken(Empresa empresa,OpenIDConnectHacienda openIDConnectHacienda) throws IOException {
		 try {
				ImmutableMap<String, String> headers = ImmutableMap.of("Accept", "application/json", "Authorization", ("Bearer " + openIDConnectHacienda.getAccess_token()), "User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
				MultivaluedMap multivaluedMap = asMap(empresa);
				Client client = Client.create();
				String idp_uri = Constantes.EMPTY;
				if(empresa.getEstadoProduccion() !=null) {
					if(empresa.getEstadoProduccion().equals(Constantes.ESTADO_ACTIVO)) {
						idp_uri = Constantes.IDP_URI_PRODUCCION;
					}else {
						idp_uri = this.IDP_URI;
					}
				}
				
				WebResource webResource = client.resource(this.IDP_URI+"/logout");
				WebResource.Builder resBuilder = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE);
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					resBuilder = resBuilder.header(entry.getKey(), entry.getValue());
				}
				ClientResponse response = resBuilder.post(ClientResponse.class, multivaluedMap);
				ImmutableMap headersResponse = ImmutableMap.copyOf(response.getHeaders());
				String strResponse = response.getEntity(String.class);
				if (response.getStatus() > 299) {
					List err = (List) headersResponse.get(POST_X_ERROR_CAUSE);
					String headerError = err != null && err.size() > 0 ? (String) err.get(0) : null;
					strResponse = headerError != null && headerError != "" ? headerError : strResponse;
				}
				return ImmutableMap.of(POST_RESPONSE, strResponse, POST_STATUS_CODE, response.getStatus(), POST_HEADERS, headersResponse);
			} catch (ClientHandlerException exc) {
				return ImmutableMap.of(POST_RESPONSE, "{}", POST_STATUS_CODE, 500, POST_HEADERS, ImmutableMap.of());
			}
	 }
			

	

  
}
