package com.emprendesoftcr.type;

import org.json.JSONObject;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface Codigo {

    String getTipo();

    String getCodigo();

    @Override
    String toString();

    static interface Builder {

        Builder tipo(String tipo);

        Builder tipo(JSONObject jsonObject);

        Builder codigo(String codigo);

        Builder codigo(JSONObject jsonObject);

        Codigo build();
    }
}
