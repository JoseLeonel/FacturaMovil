package com.emprendesoftcr.type;

import org.json.JSONObject;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface Identificacion {

    String getTipo();

    String getNumero();

    @Override
    String toString();

    static interface Builder {

        public Builder tipo(String tipo);

        public Builder tipo(JSONObject jsonObject);

        public Builder numero(String numero);

        public Builder numero(JSONObject jsonObject);

        public Identificacion build();
    }
}
