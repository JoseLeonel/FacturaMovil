package com.emprendesoftcr.type;

import org.json.JSONObject;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface CertificateInfo {

    String privateKey();

    String certificate();

    String x509SerialNumber();

    String x509IssuerName();

    String certHash();

    @Override
    String toString();

    static interface Builder {

        public Builder privateKey(String privateKey);

        public Builder privateKey(JSONObject jsonObject);

        public Builder certificate(String certificate);

        public Builder certificate(JSONObject jsonObject);

        public Builder x509SerialNumber(String x509SerialNumber);

        public Builder x509SerialNumber(JSONObject jsonObject);

        public Builder x509IssuerName(String x509IssuerName);

        public Builder x509IssuerName(JSONObject jsonObject);

        public Builder certHash(String certHash);

        public Builder certHash(JSONObject jsonObject);

        public CertificateInfo build();
    }
}
