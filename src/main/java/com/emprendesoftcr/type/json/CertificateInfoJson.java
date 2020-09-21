package com.emprendesoftcr.type.json;



import org.json.JSONException;
import org.json.JSONObject;

import com.emprendesoftcr.fisco.Checks;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.type.CertificateInfo;
import com.emprendesoftcr.utils.Constantes;

public class CertificateInfoJson implements CertificateInfo {

    private final static Builder DEFAULT_BUILDER = new Builder();
    private final String privateKey;
    private final String certificate;
    private final String x509SerialNumber;
    private final String x509IssuerName;
    private final String certHash;
  

    private CertificateInfoJson(String privateKey, String certificate, String x509SerialNumber,
                               String x509IssuerName, String certHash) {
        this.privateKey = privateKey;
        this.certificate = certificate;
        this.x509SerialNumber = x509SerialNumber;
        this.x509IssuerName = x509IssuerName;
        this.certHash = certHash;
    }

    public static CertificateInfoJson instance(String privateKey, String certificate, String x509SerialNumber,
                               String x509IssuerName, String	 certHash) {
    	Checks.checkString(privateKey, "privateKey");
    	Checks.checkString(certificate, "certificate");
    	Checks.checkString(x509SerialNumber, "x509SerialNumber");
    	Checks.checkString(x509IssuerName, "x509IssuerName");
    	Checks.checkString(certHash, "certHash");
        return new CertificateInfoJson(privateKey, certificate, x509SerialNumber, x509IssuerName, certHash);
    }

    @Override
    public String privateKey() {
        return privateKey;
    }

    @Override
    public String certificate() {
        return certificate;
    }

    @Override
    public String x509SerialNumber() {
        return x509SerialNumber;
    }

    @Override
    public String x509IssuerName() {
        return x509IssuerName;
    }

    @Override
    public String certHash() {
        return certHash;
    }

    public static CertificateInfo from (String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        return DEFAULT_BUILDER.privateKey(jsonObject).certificate(jsonObject).x509SerialNumber(jsonObject).
                x509IssuerName(jsonObject).certHash(jsonObject).build();
    }

    public static class Builder implements CertificateInfo.Builder {

        private String privateKey;
        private String certificate;
        private String x509SerialNumber;
        private String x509IssuerName;
        private String certHash;
        

        @Override
        public Builder privateKey(String privateKey) {
            this.privateKey = privateKey;
            return this;
        }

        @Override
        public Builder privateKey(JSONObject jsonObject)  {
            this.privateKey = FacturaElectronicaUtils.getJsonObject(jsonObject, Constantes.PRIVATE_KEY) ? jsonObject.getString(Constantes.PRIVATE_KEY) : null;
            return this;
        }

        @Override
        public Builder certificate(String certificate) {
            this.certificate = certificate;
            return this;
        }

        @Override
        public Builder certificate(JSONObject jsonObject) {
            this.certificate = FacturaElectronicaUtils.getJsonObject(jsonObject, Constantes.CERTIFICATE) ? jsonObject.getString(Constantes.CERTIFICATE) : null;
            return this;
        }

        @Override
        public Builder x509SerialNumber(String x509SerialNumber) {
            this.x509SerialNumber = x509SerialNumber;
            return this;
        }

        @Override
        public Builder x509SerialNumber(JSONObject jsonObject) {
            this.x509SerialNumber = FacturaElectronicaUtils.getJsonObject(jsonObject, Constantes.X509_SERIAL_NUMBER) ? jsonObject.getString(Constantes.X509_SERIAL_NUMBER) : null;
            return this;
        }

        @Override
        public Builder x509IssuerName(String x509IssuerName) {
            this.x509IssuerName = x509IssuerName;
            return this;
        }

        @Override
        public Builder x509IssuerName(JSONObject jsonObject) {
            this.x509IssuerName = FacturaElectronicaUtils.getJsonObject(jsonObject, Constantes.X509_ISSUER_NAME) ? jsonObject.getString(Constantes.X509_ISSUER_NAME) : null;
            return this;
        }

        @Override
        public Builder certHash(String certHash) {
            this.certHash = certHash;
            return this;
        }

        @Override
        public Builder certHash(JSONObject jsonObject) {
            this.certHash = FacturaElectronicaUtils.getJsonObject(jsonObject, Constantes.CERT_HASH) ? jsonObject.getString(Constantes.CERT_HASH) : null;
            return this;
        }

        @Override
        public CertificateInfo build() {
            return CertificateInfoJson.instance(privateKey, certificate, x509SerialNumber, x509IssuerName, certHash);
        }
    }

    @Override
    public String toString() {
        return "{"
                + FacturaElectronicaUtils.jsonElement(Constantes.PRIVATE_KEY, true) + FacturaElectronicaUtils.jsonElement(privateKey, false) + ","
                + FacturaElectronicaUtils.jsonElement(Constantes.CERTIFICATE, true) + FacturaElectronicaUtils.jsonElement(certificate, false) + ","
                + FacturaElectronicaUtils.jsonElement(Constantes.X509_SERIAL_NUMBER, true) + FacturaElectronicaUtils.jsonElement(x509SerialNumber, false) + ","
                + FacturaElectronicaUtils.jsonElement(Constantes.X509_ISSUER_NAME, true) + FacturaElectronicaUtils.jsonElement(x509IssuerName, false) + ","
                + FacturaElectronicaUtils.jsonElement(Constantes.CERT_HASH, true) + FacturaElectronicaUtils.jsonElement(certHash, false)
                + "}";
    }
}
