package com.emprendesoftcr.fisco;

import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONObject;

public interface TokenUrlInfo {

    MultivaluedMap asMap();

    @Override
    String toString();

    static interface Builder {

        public Builder clientId(String clientId);

        public Builder clientId(JSONObject jsonObject);

        public Builder username(String username);

        public Builder username(JSONObject jsonObject);

        public Builder password(String password);

        public Builder password(JSONObject jsonObject);

        public TokenUrlInfo build();
    }
}
