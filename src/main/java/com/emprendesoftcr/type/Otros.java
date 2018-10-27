package com.emprendesoftcr.type;

import org.json.JSONObject;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface Otros {

    String getOtroTexto();

    String getOtroContenido();

    @Override
    String toString();

    static interface Builder {
        Builder otroTexto(String otroTexto);

        Builder otroTexto(JSONObject jsonObject);

        Builder otroContenido(String otroContenido);

        Builder otroContenido(JSONObject jsonObject);

        Otros build();
    }
}
