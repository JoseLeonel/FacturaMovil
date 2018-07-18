package com.emprendesoftcr.fisco;

import static com.emprendesoftcr.fisco.Checks.checkString;
import static com.emprendesoftcr.fisco.FacturaElectronicaUtils.getJsonObject;
import static com.emprendesoftcr.fisco.FacturaElectronicaUtils.jsonElement;
import static com.emprendesoftcr.fisco.Keys.ACCESS_TOKEN;
import static com.emprendesoftcr.fisco.Keys.REFRESH_TOKEN;

import org.json.JSONObject;

public class TokenInfoJson implements TokenInfo {

	private final static Builder	DEFAULT_BUILDER	= new Builder();
	private final String					accessToken;
	private final String					refreshToken;

	private TokenInfoJson(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public static TokenInfoJson instance(String accessToken, String refreshToken) {
		checkString(accessToken, "accessToken");
		checkString(refreshToken, "refreshToken");
		return new TokenInfoJson(accessToken, refreshToken);
	}

	@Override
	public String getAccessToken() {
		return accessToken;
	}

	@Override
	public String getRefreshToken() {
		return refreshToken;
	}

	public static TokenInfo from(String json) {
		JSONObject jsonObject = new JSONObject(json);
		return DEFAULT_BUILDER.accessToken(jsonObject).refreshToken(jsonObject).build();
	}

	public static class Builder {

		private String	accessToken;
		private String	refreshToken;

		public Builder accessToken(String accessToken) {
			this.accessToken = accessToken;
			return this;
		}

		public Builder accessToken(JSONObject jsonObject) {
			this.accessToken = getJsonObject(jsonObject, ACCESS_TOKEN) ? jsonObject.getString(ACCESS_TOKEN) : null;
			return this;
		}

		public Builder refreshToken(String refreshToken) {
			this.refreshToken = refreshToken;
			return this;
		}

		public Builder refreshToken(JSONObject jsonObject) {
			this.refreshToken = getJsonObject(jsonObject, REFRESH_TOKEN) ? jsonObject.getString(REFRESH_TOKEN) : null;
			return this;
		}

		public TokenInfo build() {
			return TokenInfoJson.instance(this.accessToken, this.refreshToken);
		}
	}

	@Override
	public String toString() {
		return "{" + jsonElement(ACCESS_TOKEN, true) + jsonElement(accessToken, false) + "," + jsonElement(REFRESH_TOKEN, true) + jsonElement(refreshToken, false) + "}";
	}
}
