package com.emprendesoftcr.components;

import static com.emprendesoftcr.fisco.Keys.POST_HEADERS;
import static com.emprendesoftcr.fisco.Keys.POST_RESPONSE;
import static com.emprendesoftcr.fisco.Keys.POST_STATUS_CODE;
import static com.emprendesoftcr.fisco.Keys.POST_X_ERROR_CAUSE;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.emprendesoftcr.fisco.OpenIDConnectHacienda;
import com.google.common.collect.ImmutableMap;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Enviar documento hacia tributacion
 * EnvioHaciendaComponent.
 * @author jose.
 * @since 18 jul. 2018
 */
@Component
public class EnvioHaciendaComponent {

	

	private final String								IDP_URI;

	private final MediaType							contentype;

	private static final String URI = "https://api.comprobanteselectronicos.go.cr/recepcion-sandbox/v1/";
	@Autowired
	public EnvioHaciendaComponent(@Value("${open.id.connect.hacienda.idp.uri.documentos}")final String iDP_URI) {
		this.IDP_URI = iDP_URI;
		this.contentype = MediaType.APPLICATION_JSON_TYPE;

	}

	/**
	 * Envia hacia hacienda
	 * @return
	 */
	public Map enviarDocumentoElectronico(final String body, final OpenIDConnectHacienda openIDConnectHacienda) {
		try {
			ImmutableMap<String, String> headers = ImmutableMap.of("Accept", "application/json", "Authorization", ("Bearer " + openIDConnectHacienda.getAccess_token()), "User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");

			Client client = Client.create();
			WebResource webResource = client.resource(this.IDP_URI);
			WebResource.Builder resBuilder = webResource.type(this.contentype);
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				resBuilder = resBuilder.header(entry.getKey(), entry.getValue());
			}
			ClientResponse response = resBuilder.post(ClientResponse.class, body);
		// Se envía un POST. con los datos del documento que deseamos registrar, observe que colocamos como
		// atributo el objeto que configuramos en el apartado de 'Preparación'
		
		switch (response.getStatus()) {
			case 202:
				// Éste código de retorno se da por recibido a la plataforma el documento. Posteriormente
				// debe validarse su estado de aceptación o rechazo. Es importante hacer notar que se
				// regresa un header "Location" que corresponde a un URL. donde se puede validar el
				// estado del documento, por ejemplo:
				// https://api.comprobanteselectronicos.go.cr/recepcion-sandbox/v1/recepcion/50601011600310112345600100010100000000011999999999/

		
				break;
			case 400:
				// Se da si se detecta un error en las validaciones, por ejemplo: si intento enviar más de una
				// vez un documento. El encabezado "X-Error-Cause" indica la causa del problema.
				
		
				break;
		}
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
	 * Envia hacia hacienda
	 * @return
	 */
	public Map comprobarDocumentoElectronico(final String clave, final OpenIDConnectHacienda openIDConnectHacienda) {
		try {
			ImmutableMap<String, String> headers = ImmutableMap.of("Accept", "application/json", "Authorization", ("Bearer " + openIDConnectHacienda.getAccess_token()), "User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");

			Client client = Client.create();
			WebResource webResource = client.resource(this.IDP_URI+"/"+clave);
			WebResource.Builder resBuilder = webResource.type(this.contentype);
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				resBuilder = resBuilder.header(entry.getKey(), entry.getValue());
			}
			ClientResponse response = resBuilder.get(ClientResponse.class);
		
		// Se envía un POST. con los datos del documento que deseamos registrar, observe que colocamos como
		// atributo el objeto que configuramos en el apartado de 'Preparación'
		
		switch (response.getStatus()) {
			case 202:
				// Éste código de retorno se da por recibido a la plataforma el documento. Posteriormente
				// debe validarse su estado de aceptación o rechazo. Es importante hacer notar que se
				// regresa un header "Location" que corresponde a un URL. donde se puede validar el
				// estado del documento, por ejemplo:
				// https://api.comprobanteselectronicos.go.cr/recepcion-sandbox/v1/recepcion/50601011600310112345600100010100000000011999999999/

		
				break;
			case 400:
				// Se da si se detecta un error en las validaciones, por ejemplo: si intento enviar más de una
				// vez un documento. El encabezado "X-Error-Cause" indica la causa del problema.
				
		
				break;
		}
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
