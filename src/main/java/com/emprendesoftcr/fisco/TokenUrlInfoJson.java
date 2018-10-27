package com.emprendesoftcr.fisco;

import static com.emprendesoftcr.fisco.FacturaElectronicaUtils.getJsonObject;
import static com.emprendesoftcr.fisco.FacturaElectronicaUtils.jsonElement;
import static com.emprendesoftcr.fisco.Keys.CLIENT_ID;
import static com.emprendesoftcr.fisco.Keys.GRANT_TYPE;
import static com.emprendesoftcr.fisco.Keys.PASSWORD;
import static com.emprendesoftcr.fisco.Keys.USER_NAME;

import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONObject;

import com.sun.jersey.core.util.MultivaluedMapImpl;

public class TokenUrlInfoJson implements TokenUrlInfo {

    private final static Builder DEFAULT_BUILDER = new Builder();
    private final String grantType;
    private final String clientId;
    private final String username;
    private final String password;

    private TokenUrlInfoJson(String clientId, String username, String password) {
        this.grantType = "password";
        this.clientId = clientId;
        this.username = username;
        this.password = password;
    }

    public static TokenUrlInfoJson instance (String clientId, String username, String password) {
        return new TokenUrlInfoJson(clientId, username, password);
    }

    public static TokenUrlInfoJson instance (String username, String password) {
        return new TokenUrlInfoJson(System.getenv("HACIENDA_CLIENT_ID"), username, password);
    }

    @Override
    public MultivaluedMap asMap() {
        MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
        formData.add(GRANT_TYPE, grantType);
        formData.add(CLIENT_ID, clientId);
        formData.add(USER_NAME, username);
        formData.add(PASSWORD, password);
        return formData;
    }

    public static TokenUrlInfo from (String json) {
        JSONObject jsonObject = new JSONObject(json);
        return DEFAULT_BUILDER.clientId(jsonObject).username(jsonObject).password(jsonObject).build();
    }

    public static class Builder implements TokenUrlInfo.Builder {
        private String clientId;
        private String username;
        private String password;

        @Override
        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        @Override
        public Builder clientId(JSONObject jsonObject) {
            this.clientId = getJsonObject(jsonObject, CLIENT_ID) ? jsonObject.getString(CLIENT_ID) : null;
            return this;
        }

        @Override
        public Builder username(String username) {
            this.username = username;
            return this;
        }

        @Override
        public Builder username(JSONObject jsonObject) {
            this.username = getJsonObject(jsonObject, USER_NAME) ? jsonObject.getString(USER_NAME) : null;
            return this;
        }

        @Override
        public Builder password(String password) {
            this.password = password;
            return this;
        }

        @Override
        public Builder password(JSONObject jsonObject) {
            this.password = getJsonObject(jsonObject, PASSWORD) ? jsonObject.getString(PASSWORD) : null;
            return this;
        }

        @Override
        public TokenUrlInfo build() {
            return TokenUrlInfoJson.instance(this.clientId, this.username, this.password);
        }
    }

    @Override
    public String toString() {
        return "{"
                + jsonElement(GRANT_TYPE, true) + jsonElement(grantType, false) + ","
                + jsonElement(CLIENT_ID, true) + jsonElement(clientId, false) + ","
                + jsonElement(USER_NAME, true) + jsonElement(username, false) + ","
                + jsonElement(PASSWORD, true) + jsonElement(password, false)
                + "}";
    }
}
