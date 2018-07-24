package com.emprendesoftcr.components;

import static com.emprendesoftcr.fisco.Keys.ERROR;
import static com.emprendesoftcr.fisco.Keys.POST_HEADERS;
import static com.emprendesoftcr.fisco.Keys.POST_RESPONSE;
import static com.emprendesoftcr.fisco.Keys.POST_STATUS_CODE;
import static com.emprendesoftcr.fisco.Keys.POST_X_ERROR_CAUSE;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.HaciendaBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.fisco.MapEnums;
import com.emprendesoftcr.fisco.OpenIDConnectHacienda;
import com.emprendesoftcr.modelo.Hacienda;
import com.google.common.collect.ImmutableMap;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Enviar documento hacia tributacion EnvioHaciendaComponent.
 * @author jose.
 * @since 18 jul. 2018
 */
@Transactional
@EnableTransactionManagement
@Component
public class EnvioHaciendaComponent {

	private Logger					log	= LoggerFactory.getLogger(this.getClass());

	private final String		IDP_URI;

	private final MediaType	contentype;

	@Autowired
	HaciendaBo							haciendaBo;

	@Autowired
	public EnvioHaciendaComponent(@Value("${open.id.connect.hacienda.idp.uri.documentos}") final String iDP_URI) {
		this.IDP_URI = iDP_URI;
		this.contentype = MediaType.APPLICATION_JSON_TYPE;

	}

	/**
	 * Envia hacia hacienda el documento firmado y actualiza los codigos
	 * @return
	 */
	public  void enviarDocumentoElectronico(final String body, final OpenIDConnectHacienda openIDConnectHacienda, Hacienda hacienda) throws Exception {
		try {
			ImmutableMap<String, String> headers = ImmutableMap.of("Accept", "application/json", "Authorization", ("Bearer " + openIDConnectHacienda.getAccess_token()), "User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			Client client = Client.create();
			WebResource webResource = client.resource(this.IDP_URI);
			WebResource.Builder resBuilder = webResource.type(this.contentype);
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				resBuilder = resBuilder.header(entry.getKey(), entry.getValue());
			}
			ClientResponse response = resBuilder.post(ClientResponse.class, body);
			ImmutableMap headersResponse = ImmutableMap.copyOf(response.getHeaders());
			String strResponse = response.getEntity(String.class);
			// Problemas de recibido en Hacienda
			if (response.getStatus() > 299) {
				if (response.getStatus() < 500) {//Si es mayor de 500 problemas de comunicacion
					List err = (List) headersResponse.get(POST_X_ERROR_CAUSE);
					String headerError = err != null && err.size() > 0 ? (String) err.get(0) : null;
					strResponse = headerError != null && headerError != "" ? headerError : strResponse;
					hacienda.setEstado(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA_ERROR);
					hacienda.setUpdated_at(new Date());
					hacienda.setxErrorCause(FacturaElectronicaUtils.convertirStringToblod(headerError));
					hacienda.setStatus(response.getStatus());
					hacienda.setReintentos(hacienda.getReintentos() != null?hacienda.getReintentos() + 1:1);
					haciendaBo.modificar(hacienda);
					
				}

			} else {// Exitoso de resibido
				 // Error por conexion
        
				hacienda.setEstado(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA);
				hacienda.setUpdated_at(new Date());
				hacienda.setxErrorCause(FacturaElectronicaUtils.convertirStringToblod(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA_str));
				hacienda.setReintentos(Constantes.ZEROS);
				haciendaBo.modificar(hacienda);
			}
		
		} catch (ClientHandlerException exc) {

			log.info("** Error  envioHaciendaFacturas: " + exc.getMessage() + " fecha " + new Date() + " Hacienta:" + hacienda.getEmpresa().getNombre());
			throw exc;
		}
	}
	/**
	 * Retorna el status de la respuesta de hacienda
	 * @param indEstado Elemento ind-estado de la respuesta de hacienda
	 * @return Estado de la respuesta de hacienda OK o ERROR
	 */
	private static String getHaciendaStatus(String indEstado) {
		return MapEnums.ENUM_CODIGO_RESPUESTA_HACIENDA.containsKey(indEstado) ? MapEnums.ENUM_CODIGO_RESPUESTA_HACIENDA.get(indEstado) : ERROR;
	}

	/**
	 * Envia hacia hacienda
	 * @return
	 */
	public Map comprobarDocumentoElectronico(final String clave, final OpenIDConnectHacienda openIDConnectHacienda) {
		try {
			ImmutableMap<String, String> headers = ImmutableMap.of("Accept", "application/json", "Authorization", ("Bearer " + openIDConnectHacienda.getAccess_token()), "User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");

			Client client = Client.create();
			WebResource webResource = client.resource(this.IDP_URI + "/" + clave);
			WebResource.Builder resBuilder = webResource.type(this.contentype);
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				resBuilder = resBuilder.header(entry.getKey(), entry.getValue());
			}
			ClientResponse response = resBuilder.get(ClientResponse.class);

			
			ImmutableMap headersResponse = ImmutableMap.copyOf(response.getHeaders());
			String strResponse = response.getEntity(String.class);
			if (response.getStatus() > 299) {//Si es mayor de 500 problemas de comunicacion
				if (response.getStatus() < 500) {//Si es mayor de 500 problemas de comunicacion
					
				List err = (List) headersResponse.get(POST_X_ERROR_CAUSE);
				String headerError = err != null && err.size() > 0 ? (String) err.get(0) : null;
				strResponse = headerError != null && headerError != "" ? headerError : strResponse;
				}
			}
			return ImmutableMap.of(POST_RESPONSE, strResponse, POST_STATUS_CODE, response.getStatus(), POST_HEADERS, headersResponse);
		} catch (ClientHandlerException exc) {
			return ImmutableMap.of(POST_RESPONSE, "{}", POST_STATUS_CODE, 500, POST_HEADERS, ImmutableMap.of());

		}
	}
	
	
	 

}
