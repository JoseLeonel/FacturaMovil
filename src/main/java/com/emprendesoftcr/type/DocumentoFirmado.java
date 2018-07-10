package com.emprendesoftcr.type;

import org.json.JSONObject;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface DocumentoFirmado {

    String documento();

    String clave();

    String fecha();

    Identificacion identificacion();

    static interface Builder {

        public Builder documento(String documento);

        public Builder documento(JSONObject jsonObject);

        public Builder clave(String clave);

        public Builder clave(JSONObject jsonObject);

        public Builder fecha(String fecha);

        public Builder fecha(JSONObject jsonObject);

        public Builder identificacion(Identificacion identificacion);

        public Builder identificacion(JSONObject jsonObject);

        public DocumentoFirmado build();
    }
}
