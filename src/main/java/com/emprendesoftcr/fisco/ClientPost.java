package com.emprendesoftcr.fisco;

import com.google.common.collect.ImmutableMap;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;
import java.util.Map;
import static com.emprendesoftcr.fisco.Keys.POST_X_ERROR_CAUSE;
import static com.emprendesoftcr.fisco.Keys.POST_HEADERS;
import static com.emprendesoftcr.fisco.Keys.POST_RESPONSE;
import static com.emprendesoftcr.fisco.Keys.POST_STATUS_CODE;


public class ClientPost {

    private ClientPost(){

    }

    public static ClientPost instance () {
        return new ClientPost();
    }

    public Map send (String serviceUrl, String body, MediaType contentType, Map<String, String> headers) {
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(serviceUrl);
            WebResource.Builder resBuilder = webResource.type(contentType);
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                resBuilder = resBuilder.header(entry.getKey(), entry.getValue());
            }
            ClientResponse response = resBuilder.post(ClientResponse.class, body);
            ImmutableMap headersResponse = ImmutableMap.copyOf(response.getHeaders());
            String strResponse = response.getEntity(String.class);
            if (response.getStatus() > 299) {
                List err = (List)headersResponse.get(POST_X_ERROR_CAUSE);
                String headerError = err != null && err.size() > 0 ? (String)err.get(0) : null;
                strResponse =  headerError != null && headerError != "" ? headerError : strResponse;
            }
            return ImmutableMap.of(POST_RESPONSE, strResponse,
                    POST_STATUS_CODE, response.getStatus(), POST_HEADERS, headersResponse);
        } catch (ClientHandlerException exc) {
            return ImmutableMap.of(POST_RESPONSE, "{}",
                    POST_STATUS_CODE, 500, POST_HEADERS, ImmutableMap.of());
        }
    }

    public Map send (String serviceUrl, MultivaluedMap bodyParams, MediaType contentType, Map<String, String> headers) {
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
                List err = (List)headersResponse.get(POST_X_ERROR_CAUSE);
                String headerError = err != null && err.size() > 0 ? (String)err.get(0) : null;
                strResponse =  headerError != null && headerError != "" ? headerError : strResponse;
            }
            return ImmutableMap.of(POST_RESPONSE, strResponse,
                    POST_STATUS_CODE, response.getStatus(), POST_HEADERS, headersResponse);
        } catch (ClientHandlerException exc) {
            return ImmutableMap.of(POST_RESPONSE, "{}",
                    POST_STATUS_CODE, 500, POST_HEADERS, ImmutableMap.of());
        }
    }
}
