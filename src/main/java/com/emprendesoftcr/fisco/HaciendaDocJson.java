package com.emprendesoftcr.fisco;

import static com.emprendesoftcr.fisco.FacturaElectronicaUtils.*;
import static com.emprendesoftcr.fisco.Keys.*;

import org.json.JSONObject;

import com.emprendesoftcr.type.HaciendaDoc;
import com.emprendesoftcr.type.Identificacion;
import com.emprendesoftcr.type.json.IdentificacionJson;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public class HaciendaDocJson implements HaciendaDoc {

    private final static Builder DEFAULT_BUILDER = new Builder();
    private final String callbackUrl;
    private final String clave;
    private final String fecha;
    private final Identificacion identificacion;
    private final String responseUrl;
    private final String comprobanteXml;

    public static HaciendaDocJson instance(String callbackUrl, String clave, String fecha,
                                           Identificacion identificacion,
                                           String responseUrl, String comprobanteXml) {
        return new HaciendaDocJson(callbackUrl, clave, fecha,
                identificacion, responseUrl, comprobanteXml);
    }

    public static HaciendaDocJson instance(String callbackUrl, String clave, String fecha,
                                           String tipoIdentificacion, String numeroIdentificacion,
                                           String responseUrl, String comprobanteXml) {
        return new HaciendaDocJson(callbackUrl, clave, fecha,
                IdentificacionJson.instance(tipoIdentificacion, numeroIdentificacion),
                responseUrl, comprobanteXml);
    }

    private HaciendaDocJson(String callbackUrl, String clave, String fecha,
                            Identificacion identificacion, String responseUrl, String comprobanteXml) {
        this.callbackUrl = callbackUrl;
        this.clave = clave;
        this.fecha = fecha;
        this.identificacion = identificacion;
        this.responseUrl = responseUrl;
        this.comprobanteXml = comprobanteXml;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public String getClave() {
        return clave;
    }

    public String getFecha() {
        return fecha;
    }

    public Identificacion getIdentificacion() {
        return identificacion;
    }

    public String getResponseUrl() {
        return responseUrl;
    }

    public String getComprobanteXml() {
        return comprobanteXml;
    }

    public static Builder copy(HaciendaDoc hd) {
        return DEFAULT_BUILDER.clave(hd.getClave()).comprobanteXml(hd.getComprobanteXml()).
                identificacion(hd.getIdentificacion()).
                fecha(hd.getFecha()).responseUrl(hd.getResponseUrl()).
                callbackUrl(hd.getCallbackUrl());
    }

    public static HaciendaDoc from (String json) {
        JSONObject jsonObject = new JSONObject(json);
        return DEFAULT_BUILDER.clave(jsonObject).comprobanteXml(jsonObject).
                identificacion(jsonObject).
                fecha(jsonObject).responseUrl(jsonObject).
                callbackUrl(jsonObject).build();
    }
    
    public static class Builder implements HaciendaDoc.Builder  {
        private final static Identificacion.Builder DEFAULT_BUILDER_ID = new IdentificacionJson.Builder();
        private String callbackUrl;
        private String clave;
        private String fecha;
        private Identificacion identificacion;
        private String responseUrl = null;
        private String comprobanteXml;
        
        public Builder() {            
        }

        public Builder callbackUrl(String callbackUrl) {
            this.callbackUrl = callbackUrl;
            return this;
        }

        public Builder callbackUrl(JSONObject jsonObject) {
            this.callbackUrl = getJsonObject(jsonObject, CALLBACK_URL)
                    ? jsonObject.getString(CALLBACK_URL) : null;
            return this;
        }

        public Builder clave(String clave) {
            this.clave = clave;
            return this;
        }

        public Builder clave(JSONObject jsonObject) {
            this.clave = getJsonObject(jsonObject, CLAVE)
                    ? jsonObject.getString(CLAVE) : null;
            return this;
        }
        
        public Builder fecha(String fecha) {
            this.fecha = fecha;
            return this;
        }

        public Builder fecha(JSONObject jsonObject) {
            this.fecha = getJsonObject(jsonObject, FECHA)
                    ? jsonObject.getString(FECHA) : null;
            return this;
        }

        public Builder identificacion(Identificacion identificacion) {
            this.identificacion = identificacion;
            return this;
        }

        public Builder identificacion(String tipoIdentificacion, String numeroIdentificacion) {
            this.identificacion = IdentificacionJson.instance(tipoIdentificacion, numeroIdentificacion);
            return this;
        }

        public Builder identificacion(JSONObject jsonObject) {
            JSONObject objIdentificacion = getJsonObject(jsonObject, UBICACION)
                    ? jsonObject.getJSONObject(UBICACION) : null;
            if (objIdentificacion == null) {
                this.identificacion = null;
            } else {
                this.identificacion = DEFAULT_BUILDER_ID.tipo(objIdentificacion).numero(objIdentificacion).build();
            }
            return this;
        }

        public Builder responseUrl(String responseUrl) {
            this.responseUrl = responseUrl;
            return this;
        }

        public Builder responseUrl(JSONObject jsonObject) {
            this.responseUrl = getJsonObject(jsonObject, NOMBRE_COMERCIAL)
                    ? jsonObject.getString(NOMBRE_COMERCIAL) : null;
            return this;
        }

        public Builder comprobanteXml(String comprobanteXml) {
            this.comprobanteXml = comprobanteXml;
            return this;
        }

        public Builder comprobanteXml(JSONObject jsonObject) {
            this.comprobanteXml = getJsonObject(jsonObject, NOMBRE_COMERCIAL)
                    ? jsonObject.getString(NOMBRE_COMERCIAL) : null;
            return this;
        }
        
        public HaciendaDoc build() {
            return HaciendaDocJson.instance(this.callbackUrl, this.clave, this.fecha,
                this.identificacion, this.responseUrl, this.comprobanteXml);
        }
    }
    
    @Override
    public String toString() {
        return "{"
                + jsonElement(CALLBACK_URL, true) + jsonElement(callbackUrl, false)  + ","
                + jsonElement(CLAVE, true) + jsonElement(clave, false)  + ","
                + jsonElement(FECHA, true) + jsonElement(fecha, false)  + ","
                + jsonElement(EMISOR, true) + "{"
                    + jsonElement(TIPO_IDENTIFICACION, true) + jsonElement(identificacion.getTipo(), false)  + ","
                    + jsonElement(NUMERO_IDENTIFICACION, true) + jsonElement(identificacion.getNumero(), false)  + "}, "
                + (responseUrl == null ? "" : (jsonElement(RESPONSE_URL, true) + jsonElement(responseUrl, false) + ","))
                + jsonElement("comprobanteXml", true) + jsonElement(comprobanteXml, false)
                + "}";
    }
}
