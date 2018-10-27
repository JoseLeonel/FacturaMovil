package com.emprendesoftcr.fisco;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessToken {
	 @JsonProperty("access_token")
	  private String token;

	  @JsonProperty("expires_in")
	  private int expiresIn;

	  @JsonProperty("token_type")
	  private String tokenType;

	  @JsonProperty("message")
	  private String errorMessage;

	  public AccessToken() {
	  }

	  public String getToken() {
	    return token;
	  }

	  public int getExpiresIn() {
	    return expiresIn;
	  }

	  public String getTokenType() {
	    return tokenType;
	  }

	  public String getErrorMessage() {
	    return errorMessage;
	  }

	

}
