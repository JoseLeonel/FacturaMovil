package com.emprendesoftcr.fisco;

public interface TokenInfo {

    String getAccessToken();

    String getRefreshToken();

    @Override
    String toString();
}
