package com.emprendesoftcr.type;

public interface TokenInfo {

    String getAccessToken();

    String getRefreshToken();

    @Override
    String toString();
}
