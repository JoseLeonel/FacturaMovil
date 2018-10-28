package com.emprendesoftcr.type;

import org.json.JSONObject;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface Normativa {

    String getNumeroResolucion();

    String getFechaResolucion();

    @Override
    String toString();

    static interface Builder {
        Builder numeroResolucion(String numeroResolucion);

        Builder numeroResolucion(JSONObject jsonObject);

        Builder fechaResolucion(String fechaResolucion);

        Builder fechaResolucion(JSONObject jsonObject);

        Normativa build();
    }
}
