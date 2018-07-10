package com.emprendesoftcr.type;

import org.json.JSONObject;

public interface RespuestaHacienda {

    String clave();

    String fecha();

    String indEstado();

    MensajeHacienda mensajeHacienda();

    @Override
    String toString();

    static interface Builder {

        public Builder clave(String clave);

        public Builder clave(JSONObject jsonObject);

        public Builder fecha(String fecha);

        public Builder fecha(JSONObject jsonObject);

        public Builder indEstado(String indEstado);

        public Builder indEstado(JSONObject jsonObject);

        public Builder mensajeHacienda(MensajeHacienda mensajeHacienda);

        public Builder mensajeHacienda(JSONObject jsonObject);

        public RespuestaHacienda build();
    }
}
