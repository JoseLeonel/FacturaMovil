package com.emprendesoftcr.type;

import org.json.JSONObject;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface HaciendaDoc {

    String getCallbackUrl();

    String getClave();

    String getFecha();

    Identificacion getIdentificacion();

    String getResponseUrl();

    String getComprobanteXml();

    @Override
    String toString();

    static interface Builder {

        public Builder callbackUrl(String clave);

        public Builder callbackUrl(JSONObject jsonObject);

        public Builder clave(String clave);

        public Builder clave(JSONObject jsonObject);

        public Builder fecha(String fecha);

        public Builder fecha(JSONObject jsonObject);

        public Builder identificacion(Identificacion identificacion);

        public Builder identificacion(String tipoIdentificacion, String numeroIdentificacion0);

        public Builder identificacion(JSONObject jsonObject);

        public Builder responseUrl(String responseUrl);

        public Builder responseUrl(JSONObject jsonObject);

        public Builder comprobanteXml(String comprobanteXml);

        public Builder comprobanteXml(JSONObject jsonObject);

        public HaciendaDoc build();
    }
}
