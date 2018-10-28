package com.emprendesoftcr.type.json;

import static com.emprendesoftcr.fisco.FacturaElectronicaUtils.base64Decode;
import static com.emprendesoftcr.fisco.FacturaElectronicaUtils.getJsonObject;
import static com.emprendesoftcr.fisco.FacturaElectronicaUtils.jsonElement;
import static com.emprendesoftcr.fisco.FacturaElectronicaUtils.jsonObject;
import static com.emprendesoftcr.fisco.Keys.CLAVE;
import static com.emprendesoftcr.fisco.Keys.FECHA;
import static com.emprendesoftcr.fisco.Keys.INDICATIVO_ESTADO;
import static com.emprendesoftcr.fisco.Keys.RESPUESTA_XML;

import org.json.JSONObject;

import com.emprendesoftcr.type.MensajeHacienda;
import com.emprendesoftcr.type.RespuestaHacienda;


public class RespuestaHaciendaJson implements RespuestaHacienda {

    private final static Builder DEFAULT_BUILDER = new Builder();
    private final String clave;
    private final String fecha;
    private final String indEstado;
    private final MensajeHacienda mensajeHacienda;

    private RespuestaHaciendaJson(String clave, String fecha, String indEstado, MensajeHacienda mensajeHacienda) {
        this.clave = clave;
        this.fecha = fecha;
        this.indEstado = indEstado;
        this.mensajeHacienda = mensajeHacienda;
    }

    public static RespuestaHaciendaJson instance(String clave, String fecha, String indEstado, MensajeHacienda mensajeHacienda) {
        return new RespuestaHaciendaJson(clave, fecha, indEstado, mensajeHacienda);
    }

    @Override
    public String clave() {
        return clave;
    }

    @Override
    public String fecha() {
        return fecha;
    }

    @Override
    public String indEstado() {
        return indEstado;
    }

    @Override
    public MensajeHacienda mensajeHacienda() {
        return mensajeHacienda;
    }

    public static RespuestaHacienda from (String json) {
        JSONObject jsonObject = new JSONObject(json);
        return DEFAULT_BUILDER.clave(jsonObject).fecha(jsonObject).indEstado(jsonObject).mensajeHacienda(jsonObject).build();
    }

    public static class Builder implements RespuestaHacienda.Builder {
        private String clave;
        private String fecha;
        private String indEstado;
        private MensajeHacienda mensajeHacienda;

        @Override
        public RespuestaHacienda.Builder clave(String clave) {
            this.clave = clave;
            return this;
        }

        @Override
        public RespuestaHacienda.Builder clave(JSONObject jsonObject) {
            this.clave = getJsonObject(jsonObject, CLAVE) ? jsonObject.getString(CLAVE) : null;
            return this;
        }

        @Override
        public RespuestaHacienda.Builder fecha(String fecha) {
            this.fecha = fecha;
            return this;
        }

        @Override
        public RespuestaHacienda.Builder fecha(JSONObject jsonObject) {
            this.fecha = getJsonObject(jsonObject, FECHA) ? jsonObject.getString(FECHA) : null;
            return this;
        }

        @Override
        public RespuestaHacienda.Builder indEstado(String indEstado) {
            this.indEstado = indEstado;
            return this;
        }

        @Override
        public RespuestaHacienda.Builder indEstado(JSONObject jsonObject) {
            this.indEstado = getJsonObject(jsonObject, INDICATIVO_ESTADO) ? jsonObject.getString(INDICATIVO_ESTADO) : null;
            return this;
        }

        @Override
        public RespuestaHacienda.Builder mensajeHacienda(MensajeHacienda mensajeHacienda) {
            this.mensajeHacienda = mensajeHacienda;
            return this;
        }

        @Override
        public RespuestaHacienda.Builder mensajeHacienda(JSONObject jsonObject) {
            String respuestaXml = getJsonObject(jsonObject, RESPUESTA_XML) ? jsonObject.getString(RESPUESTA_XML) : null;
            if (respuestaXml != null) {
                this.mensajeHacienda = MensajeHaciendaXml.from(base64Decode(respuestaXml));
            } else {
                this.mensajeHacienda = null;
            }
            return this;
        }

        @Override
        public RespuestaHacienda build() {
            return RespuestaHaciendaJson.instance(this.clave, this.fecha, this.indEstado, this.mensajeHacienda);
        }
    }

    @Override
    public String toString() {
        return "{"
                + jsonElement(CLAVE, true) + jsonElement(clave, false) + ","
                + jsonElement(FECHA, true) + jsonElement(fecha, false) + ","
                + jsonElement(INDICATIVO_ESTADO, true) + jsonElement(indEstado, false) + ","
                + jsonElement(RESPUESTA_XML, true) + jsonObject(mensajeHacienda)
                + "}";
    }
}
